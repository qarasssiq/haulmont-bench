/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.storeproduct;

import com.company.myproject.entity.Product;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.StoreProduct;

import javax.inject.Inject;
import java.math.BigDecimal;

@UiController("myproject_StoreProduct.edit")
@UiDescriptor("store-product-edit.xml")
@EditedEntityContainer("storeProductDc")
@LoadDataBeforeShow
public class StoreProductEdit extends StandardEditor<StoreProduct> {
    @Inject
    private Label<String> producerPriceLabel;
    @Inject
    private PickerField<Product> productField;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if(!productField.isEmpty()) {
            productField.setEditable(false);
        }
    }

    @Subscribe("productField")
    public void onProductFieldValueChange(HasValue.ValueChangeEvent<Product> event) {
        if (event.getValue() == null) {
            producerPriceLabel.setValue("Producer's price: ");
        } else {
            producerPriceLabel.setValue("Producer's price: " + event.getValue().getPrice());
        }
    }
}