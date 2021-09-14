/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.purchaseproduct;

import com.company.myproject.entity.Product;
import com.company.myproject.entity.StoreProduct;
import com.company.myproject.web.StoreOption;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.PurchaseProduct;

import javax.inject.Inject;
import java.math.BigDecimal;

@UiController("myproject_PurchaseProduct.edit")
@UiDescriptor("purchase-product-edit.xml")
@EditedEntityContainer("purchaseProductDc")
@LoadDataBeforeShow
public class PurchaseProductEdit extends StandardEditor<PurchaseProduct> {
    @Inject
    private Label<String> priceLabel;
    @Inject
    private Label<String> amountLabel;
    @Inject
    private PickerField<Product> productField;

    @Install(to = "productField.lookup", subject = "screenOptionsSupplier")
    private ScreenOptions productFieldLookupScreenOptionsSupplier() {
        return new StoreOption(getEditedEntity().getPurchase().getStore());
    }

    @Subscribe("productField")
    public void onProductFieldValueChange(HasValue.ValueChangeEvent<Product> event) {
        if (event.getValue() == null) {
            priceLabel.setValue("Price: ");
            amountLabel.setValue("Amount in the store: ");
        } else {
            StoreProduct storeProduct = getEditedEntity().getPurchase().getStore().getStoreProducts().stream()
                    .filter(storeProduct1 -> event.getValue().equals(storeProduct1.getProduct()))
                    .findAny()
                    .orElse(null);
            priceLabel.setValue("Price: " + storeProduct.getPrice());
            amountLabel.setValue("Amount in the store: " + storeProduct.getAmount());
        }
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if(!productField.isEmpty()) {
            productField.setEditable(false);
        }
    }
}