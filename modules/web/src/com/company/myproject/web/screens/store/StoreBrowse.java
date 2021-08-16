/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.store;

import com.company.myproject.entity.Producer;
import com.company.myproject.entity.Retailer;
import com.company.myproject.entity.StoreProduct;
import com.company.myproject.web.ProducerOption;
import com.company.myproject.web.RetailerOption;
import com.company.myproject.web.StoreOption;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.Store;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UiController("myproject_Store.browse")
@UiDescriptor("store-browse.xml")
@LookupComponent("storesTable")
@LoadDataBeforeShow
public class StoreBrowse extends StandardLookup<Store> {
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionLoader<Store> storesDl;
    @Inject
    private CollectionContainer<Store> storesDc;

    private List<UUID> storeList = new ArrayList<>();

    @Subscribe
    public void onInit(InitEvent event) {
        ScreenOptions screenOptions = event.getOptions();
        if (screenOptions instanceof RetailerOption) {
            Retailer retailer = ((RetailerOption) screenOptions).getRetailer();
            List<Store> stores = dataManager.load(Store.class).
                    query("select e from myproject_Store e where e.retailer = :retailer")
                    .view("store-view")
                    .parameter("retailer", retailer)
                    .list();
            for (Store store : stores) {
                storeList.add(store.getId());
            }
            storesDl.setQuery("select e from myproject_Store e where e.id IN :store_id");
            storesDl.setView("store-view");
            storesDl.setParameter("store_id", storeList);
        } else {
            storesDl.setQuery("select e from myproject_Store e");
        }
        storesDl.setContainer(storesDc);
        storesDl.setDataContext(getScreenData().getDataContext());
    }
}