/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.service;

import com.company.myproject.core.StoreBean;
import com.company.myproject.entity.Retailer;
import com.company.myproject.entity.Store;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(StoreService.NAME)
public class StoreServiceBean implements StoreService {

    @Inject
    private StoreBean storeBean;

    @Override
    public Integer getNumberOfPurchasesInStore(Store store) {
        return storeBean.getNumberOfPurchasesInStore(store);
    }

    @Override
    public Integer getNumberOfRetailerPurchases(Retailer retailer) {
        return storeBean.getNumberOfRetailerPurchases(retailer);
    }
}