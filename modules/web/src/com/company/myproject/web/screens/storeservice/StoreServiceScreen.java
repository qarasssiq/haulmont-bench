/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.storeservice;

import com.company.myproject.entity.Retailer;
import com.company.myproject.entity.Store;
import com.company.myproject.service.StoreService;
import com.company.myproject.web.RetailerOption;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("myproject_StoreServiceScreen")
@UiDescriptor("store-service-screen.xml")
public class StoreServiceScreen extends Screen {
    @Inject
    private PickerField<Store> storeField1;
    @Inject
    private StoreService storeService;
    @Inject
    private Label<String> purchasesNumber1;
    @Inject
    private Notifications notifications;
    @Inject
    private Label<String> purchasesNumber2;
    @Inject
    private PickerField<Retailer> retailerField;

    @Subscribe("button1")
    public void onButton1Click(Button.ClickEvent event) {
        if(storeField1.isEmpty()) {
            notifications.create(Notifications.NotificationType.WARNING).withCaption("Please enter all required parameters.").show();
        } else {
            purchasesNumber1.setValue("The number of purchases: " + storeService.getNumberOfPurchasesInStore(storeField1.getValue()).toString());
        }
    }

    @Subscribe("storeField1")
    public void onStoreFieldValueChange(HasValue.ValueChangeEvent<Store> event) {
        if (event.getValue() == null || !event.getValue().equals(event.getPrevValue())) {
            purchasesNumber1.setValue("The number of purchases: ");
        }
    }

    @Subscribe("button2")
    public void onButton2Click(Button.ClickEvent event) {
        if(retailerField.isEmpty()) {
            notifications.create(Notifications.NotificationType.WARNING).withCaption("Please enter all required parameters.").show();
        } else {
            purchasesNumber2.setValue("The number of purchases: " +
                    storeService.getNumberOfRetailerPurchases(retailerField.getValue()));
        }
    }

    @Subscribe("retailerField")
    public void onRetailerFieldValueChange(HasValue.ValueChangeEvent<Retailer> event) {
        if(event.getValue() == null || !event.getValue().equals((event.getPrevValue()))) {
            purchasesNumber2.setValue("The number of purchases: ");
        }
    }
}