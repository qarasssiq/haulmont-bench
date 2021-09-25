/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.store;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.company.myproject.config.StoreProductsConfig;
import com.company.myproject.entity.StoreProduct;
import com.company.myproject.service.EmailSendService;
import com.haulmont.addon.maps.gis.utils.GeometryUtils;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.Store;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.reports.app.service.ReportService;
import com.haulmont.reports.entity.Report;
import com.haulmont.reports.gui.ReportGuiManager;
import com.haulmont.yarg.reporting.ReportOutputDocument;
import org.locationtech.jts.geom.Point;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UiController("myproject_Store.edit")
@UiDescriptor("store-edit.xml")
@EditedEntityContainer("storeDc")
@LoadDataBeforeShow
public class StoreEdit extends StandardEditor<Store> {
    @Inject
    private CollectionPropertyContainer<StoreProduct> storeProductDc;
    @Inject
    private DataContext dataContext;
    @Inject
    private Table<StoreProduct> storeProductsTable;
    @Inject
    private StoreProductsConfig storeProductsConfig;
    @Inject
    private ReportGuiManager reportGuiManager;
    @Inject
    private ReportService reportService;
    @Inject
    private Notifications notifications;
    @Inject
    private EmailSendService emailSendService;

    @Subscribe
    public void onInit(InitEvent event) {
        setLowAmountStyleIfNeeded();
    }

    // Исключает дублирование одинаковых товаров при добавлении нового
    @Install(to = "storeProductsTable.create", subject = "afterCommitHandler")
    private void storeProductsTableCreateAfterCommitHandler(StoreProduct addedProduct) {
        List<StoreProduct> storeProducts = storeProductDc.getMutableItems();
        for (int i = 0; i < storeProducts.size() - 1; i++) {
            StoreProduct existingProduct = storeProducts.get(i);
            if (existingProduct.getProduct().getId() == addedProduct.getProduct().getId()) {
                existingProduct.setAmount(existingProduct.getAmount() + addedProduct.getAmount());
                storeProducts.remove(storeProducts.size() - 1);
                dataContext.remove(addedProduct);
            }
        }
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        setStoreLocation();
    }

    @Subscribe
    public void onAfterCommitChanges(AfterCommitChangesEvent event) {
        // Отправка письма администратору магазина в случае малого кол-ва товаров
        if (!(PersistenceHelper.isNew(getEditedEntity()) || getEditedEntity().getStoreProducts() == null)) {
            List<StoreProduct> storeProductsWithLowAmount = getEditedEntity().getStoreProducts().stream()
                    .filter(storeProduct -> storeProduct.getAmount() < storeProductsConfig.getAmount())
                    .collect(Collectors.toList());
            if (!storeProductsWithLowAmount.isEmpty()) {
                User loggedUser = AppBeans.get(UserSessionSource.class).getUserSession().getUser();
                if (loggedUser.getEmail() == null) {
                    notifications.create(Notifications.NotificationType.WARNING)
                            .withCaption("This user doesn't have email address. Unable to send email.").show();
                } else {
                    EmailAttachment emailAttachment = makeAttachment(getEditedEntity(), loggedUser);
                    emailSendService.sendReportByEmail(loggedUser, emailAttachment, storeProductsConfig.getAmount(), getEditedEntity());
                    notifications.create(Notifications.NotificationType.HUMANIZED)
                            .withCaption("Email sent to " + loggedUser.getEmail()).show();
                }
            }
        }
    }

    // Метод подсвечивает красным товары, количество которых меньше заданного глобального параметра
    private void setLowAmountStyleIfNeeded() {
        storeProductsTable.setStyleProvider((storeProduct, property) -> {
            if (storeProduct.getAmount() < storeProductsConfig.getAmount()) {
                return "low-amount";
            }
            return null;
        });
    }

    // Метод выставляет локацию магазина, исходя из его адреса
    private void setStoreLocation() {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("07941c06ed07481a8f0a026eef2954a5");
        String query = getEditedEntity().getAddress().getBuilding() + ' ' +
                getEditedEntity().getAddress().getStreet() + ", " +
                getEditedEntity().getAddress().getCity() + ", RU";
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(query);
        request.setRestrictToCountryCode("ru");

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition();
        Point newPoint = GeometryUtils.createPoint(firstResultLatLng.getLng(), firstResultLatLng.getLat());
        getEditedEntity().setLocation(newPoint);
    }

    // Метод создаёт и возвращает приложение (отчет по товарам магазина) к письму
    private EmailAttachment makeAttachment(Store store, User user) {
        List<Report> reports = reportGuiManager.getAvailableReports(this.getId(), user, store.getMetaClass());// search reports available for the entity for current user

        Report targetReport = reports.get(0);
        for (Report report : reports) {
            if (report.getName().equals("Report for Store")) {
                targetReport = report;
                break;
            }
        }
        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("store", store);
        reportParams.put("amount", storeProductsConfig.getAmount());
        String fileName = store.getName() + ".xlsx";

        String templateCode;
        if (user.getLanguage() == null) templateCode = "DEFAULT";
        else if (user.getLanguage().equals("en")) templateCode = "Template_EN";
        else if (user.getLanguage().equals("ru")) templateCode = "Template_RU";
        else templateCode = "DEFAULT";

        ReportOutputDocument outputDocument = reportService.createReport(targetReport, templateCode, reportParams);

        return new EmailAttachment(outputDocument.getContent(), fileName);
    }
}