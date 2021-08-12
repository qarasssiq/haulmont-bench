/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.product;

import com.company.myproject.entity.Store;
import com.company.myproject.entity.StoreProduct;
import com.company.myproject.web.StoreOption;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.Product;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UiController("myproject_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {
    @Inject
    private CollectionContainer<Product> productsDc;
    @Inject
    private CollectionLoader<Product> productsDl;

    private List<UUID> productList = new ArrayList<>();

    @Subscribe
    public void onInit(InitEvent event) {
        ScreenOptions storeOptions = event.getOptions();
        if (storeOptions instanceof StoreOption) {
            Store store = ((StoreOption) storeOptions).getStore();
            List<StoreProduct> storeProductList = store.getStoreProducts();
            for (StoreProduct storeProduct : storeProductList)
                productList.add(storeProduct.getProduct().getId());
            productsDl.setQuery("select e from myproject_Product e where e.id IN :store_id");
            productsDl.setView("product-view");
            productsDl.setParameter("store_id", productList);
        } else {
            productsDl.setQuery("select e from myproject_Product e");
        }
        productsDl.setContainer(productsDc);
        productsDl.setDataContext(getScreenData().getDataContext());
    }
}