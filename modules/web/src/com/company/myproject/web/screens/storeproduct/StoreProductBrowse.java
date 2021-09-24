/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.storeproduct;

import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.StoreProduct;

@UiController("myproject_StoreProduct.browse")
@UiDescriptor("store-product-browse.xml")
@LookupComponent("storeProductsTable")
@LoadDataBeforeShow
public class StoreProductBrowse extends StandardLookup<StoreProduct> {
}