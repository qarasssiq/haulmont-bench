/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.listeners;

import com.company.myproject.entity.PriceHistory;
import com.company.myproject.entity.StoreProduct;

import java.time.LocalDate;
import java.util.UUID;

import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;

@Component("myproject_StoreProductChangedListener")
public class StoreProductChangedListener {

    @Inject
    private DataManager dataManager;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<StoreProduct, UUID> event) {
        StoreProduct storeProduct = dataManager.load(event.getEntityId()).view("storeProduct-view").one();
        if (((event.getChanges().isChanged("price")) &&
                (event.getType() != EntityChangedEvent.Type.DELETED)) || event.getType() == EntityChangedEvent.Type.CREATED) {
            PriceHistory priceHistory = dataManager.create(PriceHistory.class);
            priceHistory.setStoreProduct(storeProduct);
            priceHistory.setPrice(storeProduct.getPrice());
            priceHistory.setDate(LocalDate.now());

            dataManager.commit(priceHistory);
        }
    }
}