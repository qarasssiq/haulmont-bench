/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.producerservice;

import com.company.myproject.entity.*;
import com.company.myproject.service.ProducerService;
import com.company.myproject.web.ProducerOption;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.List;

@UiController("myproject_ProducerServiceScreen")
@UiDescriptor("producer-service-screen.xml")
public class ProducerServiceScreen extends Screen {
    @Inject
    private PickerField<Producer> producerPickerField1;
    @Inject
    private PickerField<Store> storePickerField;
    @Inject
    private TextField<Integer> amountTextField;
    @Inject
    private ProducerService producerService;
    @Inject
    private CollectionContainer<ProductWhoseAmountIsLessThan> productWhoseAmountIsLessThanDc;
    @Inject
    private Notifications notifications;
    @Inject
    private PickerField<Product> productField;
    @Inject
    private PickerField<Producer> producerField2;
    @Inject
    private CollectionContainer<StoreWithNoSuchProduct> storeWithNoSuchProductsDc;

    @Install(to = "productField.lookup", subject = "screenOptionsSupplier")
    private ScreenOptions productFieldLookupScreenOptionsSupplier() {
        return new ProducerOption(producerField2.getValue());
    }

    @Subscribe("button1")
    public void onButtonClick(Button.ClickEvent event) {
        if (producerPickerField1.isEmpty() || storePickerField.isEmpty() || amountTextField.isEmpty()) {
            notifications.create(Notifications.NotificationType.WARNING).withCaption("Please enter all required parameters.").show();
        } else if (amountTextField.getValue() <= 0) {
            notifications.create(Notifications.NotificationType.WARNING).withCaption("Amount parameter must be positive.").show();
        } else {
            List<ProductWhoseAmountIsLessThan> products = productWhoseAmountIsLessThanDc.getMutableItems();
            products.clear();
            products.addAll(producerService.getProductsWhoseAmountIsLessThan(
                    producerPickerField1.getValue(),
                    storePickerField.getValue(),
                    amountTextField.getValue()));
        }
    }

    @Subscribe("button2")
    public void onButton2Click(Button.ClickEvent event) {
        if(producerField2.isEmpty() || productField.isEmpty()) {
            notifications.create(Notifications.NotificationType.WARNING).withCaption("Please enter all required parameters.").show();
        } else {
            List<StoreWithNoSuchProduct> stores = storeWithNoSuchProductsDc.getMutableItems();
            stores.clear();
            stores.addAll(producerService.getStoresWithNoSuchProduct(producerField2.getValue(), productField.getValue()));
        }
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        productField.setEnabled(false);
    }

    @Subscribe("producerField2")
    public void onProducerField2ValueChange(HasValue.ValueChangeEvent<Producer> event) {
        productField.setEnabled(event.getValue() != null);
        if (event.getValue() == null) {
            productField.clear();
            productField.setEnabled(false);
        } else if(!event.getValue().equals(event.getPrevValue())) {
            productField.clear();
            productField.setEnabled(true);
        }
    }
}