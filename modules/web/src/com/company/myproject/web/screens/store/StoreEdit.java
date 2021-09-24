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
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import com.company.myproject.config.StoreProductsConfig;
import com.company.myproject.entity.StoreProduct;
import com.haulmont.addon.maps.gis.utils.GeometryUtils;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.Store;
import com.haulmont.reports.gui.ReportGuiManager;
import org.locationtech.jts.geom.Point;

import javax.inject.Inject;
import java.util.List;

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
    public void onInit(InitEvent event) {
        storeProductsTable.setStyleProvider((storeProduct, property) -> {
            if (storeProduct.getAmount() < storeProductsConfig.getAmount()) {
                return "low-amount";
            }
            return null;
        });
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("07941c06ed07481a8f0a026eef2954a5");
        String query =  getEditedEntity().getAddress().getBuilding() + ' ' +
                        getEditedEntity().getAddress().getStreet() + ", " +
                        getEditedEntity().getAddress().getCity() + ", RU";
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(query);
        request.setRestrictToCountryCode("ru");

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition();
        Point newPoint = GeometryUtils.createPoint(firstResultLatLng.getLng(), firstResultLatLng.getLat());
        getEditedEntity().setLocation(newPoint);
    }
}