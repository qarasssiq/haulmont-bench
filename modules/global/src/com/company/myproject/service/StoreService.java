/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.service;

import com.company.myproject.entity.Retailer;
import com.company.myproject.entity.Store;

public interface StoreService {
    String NAME = "myproject_StoreService";

    Integer getNumberOfPurchasesInStore(Store store);

    Integer getNumberOfRetailerPurchases(Retailer retailer);
}