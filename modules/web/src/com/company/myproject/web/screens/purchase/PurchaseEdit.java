/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.purchase;

import com.company.myproject.entity.PurchaseProduct;
import com.company.myproject.entity.Store;
import com.company.myproject.entity.StoreProduct;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.Purchase;

import javax.inject.Inject;
import java.util.List;

@UiController("myproject_Purchase.edit")
@UiDescriptor("purchase-edit.xml")
@EditedEntityContainer("purchaseDc")
@LoadDataBeforeShow
public class PurchaseEdit extends StandardEditor<Purchase> {
    @Inject
    private CollectionPropertyContainer<PurchaseProduct> purchaseProductDc;
    @Inject
    private DataContext dataContext;
    @Inject
    private Button createBtn;
    @Inject
    private Notifications notifications;
    @Inject
    private PickerField<Store> storeField;
    @Inject
    private Table<PurchaseProduct> productsTable;
    @Inject
    private Button editButton;
    @Inject
    private Button removeButton;
    @Inject
    private Button commitAndCloseBtn;

    @Install(to = "productsTable.create", subject = "afterCommitHandler")
    private void productsTableCreateAfterCommitHandler(PurchaseProduct addedPurchaseProduct) {
        List<PurchaseProduct> purchaseProducts = purchaseProductDc.getMutableItems();
        for (int i = 0; i < purchaseProducts.size() - 1; i++) {
            PurchaseProduct existingPurchaseProduct = purchaseProducts.get(i);
            if (existingPurchaseProduct.getProduct().getId() == addedPurchaseProduct.getProduct().getId()) {
                existingPurchaseProduct.setAmount(existingPurchaseProduct.getAmount() + addedPurchaseProduct.getAmount());
                purchaseProducts.remove(purchaseProducts.size() - 1);
                dataContext.remove(addedPurchaseProduct);
            }
        }
    }

    @Subscribe("storeField")
    public void onStoreFieldValueChange(HasValue.ValueChangeEvent<Store> event) {
        createBtn.setEnabled(event.getValue() != null);
        if(event.getPrevValue() == null) {
            return;
        }
        if (event.getValue() == null || !event.getValue().equals(event.getPrevValue())) {
          purchaseProductDc.getMutableItems().forEach(x -> dataContext.remove(x));
          purchaseProductDc.getMutableItems().clear();
        }
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        createBtn.setEnabled(false);
        if(PersistenceHelper.isDetached(getEditedEntity())){
            storeField.setEditable(false);
            productsTable.setEditable(false);
            productsTable.removeAllActions();
            createBtn.setVisible(false);
            editButton.setVisible(false);
            removeButton.setVisible(false);
            commitAndCloseBtn.setVisible(false);
        }
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        List<PurchaseProduct> purchaseProducts = getEditedEntity().getPurchaseProducts();
        if (purchaseProducts == null || purchaseProducts.isEmpty()) {
            notifications.create(Notifications.NotificationType.WARNING).withCaption("Purchase products list is empty.").show();
            event.preventCommit();
            return;
        }
        boolean notEnoughAmount = false;
        StringBuilder notEnoughAmountMessage = new StringBuilder("The following products doesn't have required amount in the store: ");
        for (PurchaseProduct purchaseProduct : purchaseProducts) {
            StoreProduct storeProduct = getEditedEntity().getStore().getStoreProducts().stream()
                    .filter(storeProduct1 -> storeProduct1.getProduct().equals(purchaseProduct.getProduct()))
                    .findAny()
                    .orElse(null);
            if (purchaseProduct.getAmount() > storeProduct.getAmount()) {
                notEnoughAmountMessage.append(purchaseProduct.getProduct().getName()).append(", ");
                notEnoughAmount = true;
            }
        }
        notEnoughAmountMessage.replace(notEnoughAmountMessage.length() - 2, notEnoughAmountMessage.length() - 1, ".");
        if (notEnoughAmount) {
            notifications.create(Notifications.NotificationType.WARNING).withCaption(notEnoughAmountMessage.toString()).show();
            event.preventCommit();
        } else {
            for (PurchaseProduct purchaseProduct : purchaseProducts) {
                StoreProduct storeProduct = getEditedEntity().getStore().getStoreProducts().stream()
                        .filter(storeProduct1 -> storeProduct1.getProduct().equals(purchaseProduct.getProduct()))
                        .findAny()
                        .orElse(null);
                storeProduct.setAmount(storeProduct.getAmount() - purchaseProduct.getAmount());
                dataContext.merge(storeProduct);
            }
        }
    }
}