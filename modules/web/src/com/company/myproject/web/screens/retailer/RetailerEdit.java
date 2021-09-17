/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.retailer;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageComponents;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import com.company.myproject.entity.Address;
import com.company.myproject.entity.Store;
import com.company.myproject.entity.StoreProduct;
import com.company.myproject.web.StoreOption;
import com.company.myproject.web.screens.store.StoreEdit;
import com.haulmont.addon.maps.web.gui.components.GeoMap;
import com.haulmont.addon.maps.web.gui.components.layer.VectorLayer;
import com.haulmont.addon.maps.web.gui.components.layer.style.FontPointIcon;
import com.haulmont.addon.maps.web.gui.components.layer.style.GeometryStyle;
import com.haulmont.addon.maps.web.gui.components.layer.style.PointStyle;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.icons.CubaIcon;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.Retailer;
import org.locationtech.jts.geom.Point;

import javax.inject.Inject;
import java.util.Objects;

@UiController("myproject_Retailer.edit")
@UiDescriptor("retailer-edit.xml")
@EditedEntityContainer("retailerDc")
@LoadDataBeforeShow
public class RetailerEdit extends StandardEditor<Retailer> {
    @Inject
    protected DataManager dataManager;
    @Inject
    private CollectionPropertyContainer<Store> storesDc;
    @Inject
    private DataContext dataContext;
    @Inject
    private GeoMap map;

    @Install(to = "retailerStoresTable.create", subject = "screenConfigurer")
    protected void retailerStoresTableCreateScreenConfigurer(Screen editorScreen) {
        editorScreen.getWindow().getFrame().getComponent("storeRetailerField").setVisible(false);
    }

    @Install(to = "retailerStoresTable.edit", subject = "screenConfigurer")
    protected void retailerStoresTableEditScreenConfigurer(Screen editorScreen) {
        editorScreen.getWindow().getComponent("storeRetailerField").setVisible(false);
    }

    @Install(to = "map.storeLayer", subject = "styleProvider")
    private GeometryStyle storeLayerStyleProvider(Store store) {
        return new PointStyle(
                new FontPointIcon(CubaIcon.SHOPPING_BASKET)
                        .setIconPathFillColor("#0051d3"));
    }

    // Показывает подписи к меткам на карте, при наведении мыши на них
    @Install(to = "map.storeLayer", subject = "tooltipContentProvider")
    private String storeLayerTooltipContentProvider(Store store) {
        return store.getName() + ", " + store.getAddress().getInstanceName();
    }

    @Subscribe(id = "storesDc", target = Target.DATA_CONTAINER)
    public void onStoresDcCollectionChange(CollectionContainer.CollectionChangeEvent<Store> event) {
        Objects.requireNonNull(map.getSelectedLayer()).refresh();
    }

    @Install(to = "retailerStoresTable.create", subject = "afterCommitHandler")
    private void retailerStoresTableCreateAfterCommitHandler(Store store) {
        Objects.requireNonNull(map.getSelectedLayer()).refresh();
    }



    /*@Subscribe("map.storeLayer")
    private void GeoObjectSelectedEvent(VectorLayer.GeoObjectSelectedEvent<Store> event) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("07941c06ed07481a8f0a026eef2954a5");

        JOpenCageReverseRequest request = new JOpenCageReverseRequest(event.getItem().getLocation().getY(), event.getItem().getLocation().getX());
        request.setLanguage("en"); // prioritize results in a specific language using an IETF format language code
        request.setNoDedupe(true); // don't return duplicate results
        request.setLimit(5); // only return the first 5 results (default is 10)
        request.setNoAnnotations(false); // exclude additional info such as calling code, timezone, and currency
        request.setMinConfidence(3); // restrict to results with a confidence rating of at least 3 (out of 10)

        JOpenCageResponse response = jOpenCageGeocoder.reverse(request);

        JOpenCageComponents components = response.getResults().get(0).getComponents();
        Address newAddress = dataManager.create(Address.class);
        newAddress.setBuilding(components.getHouseNumber());
        newAddress.setStreet(components.getRoad());
        newAddress.setCity(components.getCity());
        event.getItem().setAddress(newAddress);
        Store editedStore = storesDc.getMutableItems().stream()
                .filter(store -> event.getItem().equals(store))
                .findAny()
                .orElse(null);
        editedStore.setAddress(newAddress);
        dataContext.merge(event.getItem());
        //TODO попытаться сделать так, чтобы адрес изменялся одновременно с переставлением метки на карте
    }*/
}