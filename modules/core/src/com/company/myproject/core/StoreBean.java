/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.core;

import com.company.myproject.entity.Purchase;
import com.company.myproject.entity.Retailer;
import com.company.myproject.entity.Store;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component(StoreBean.NAME)
public class StoreBean {
    public static final String NAME = "myproject_StoreBean";
    @Inject
    private DataManager dataManager;

    public Integer getNumberOfPurchasesInStore(Store store) {
        List<Purchase> purchases = dataManager.load(Purchase.class).
                query("select e from myproject_Purchase e where e.store = :store")
                .view("purchase-view")
                .parameter("store", store)
                .list();
        return purchases.size();
    }

    public Integer getNumberOfRetailerPurchases(Retailer retailer) {
        List<Store> stores = dataManager.load(Store.class).
                query("select e from myproject_Store e where e.retailer = :retailer")
                .view("store-view")
                .parameter("retailer", retailer)
                .list();
        return stores.stream().mapToInt(this::getNumberOfPurchasesInStore).sum();
    }
}