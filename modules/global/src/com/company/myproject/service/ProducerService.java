/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.service;

import com.company.myproject.entity.*;

import java.util.List;

public interface ProducerService {
    String NAME = "myproject_ProducerService";

    List<ProductWhoseAmountIsLessThan> getProductsWhoseAmountIsLessThan(Producer producer, Store store, int parameter);

    List<StoreWithNoSuchProduct> getStoresWithNoSuchProduct(Producer producer, Product product);
}