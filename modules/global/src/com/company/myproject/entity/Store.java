/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.addon.maps.gis.Geometry;
import com.haulmont.addon.maps.gis.converters.wkt.CubaPointWKTConverter;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "MYPROJECT_STORE")
@Entity(name = "myproject_Store")
@NamePattern("%s, %s|number,name")
public class Store extends StandardEntity {
    private static final long serialVersionUID = -4059720126704569118L;

    @NotNull
    @Column(name = "NUMBER", nullable = false, unique = true)
    private String number;

    @Transient
    @MetaProperty
    private Integer totalProductsQuantity;

    @MetaProperty(datatype = "GeoPoint")
    @Column(name = "LOCATION")
    @Geometry
    @Convert(converter = CubaPointWKTConverter.class)
    private Point location;

    @JoinTable(name = "MYPROJECT_STORE_USER_LINK",
            joinColumns = @JoinColumn(name = "STORE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    @ManyToMany
    private List<User> staff;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "store")
    private List<StoreProduct> storeProducts;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RETAILER_ID")
    @OnDeleteInverse(DeletePolicy.CASCADE)
    private Retailer retailer;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    private Address address;

    @Column(name = "TYPE", nullable = false)
    @NotNull
    private String type;

    @Transient
    @MetaProperty(related = {"storeProducts"})
    public Integer getTotalProductsQuantity() {
        Integer totalProductsQuantity = 0;
        if(storeProducts != null) {
            for (StoreProduct storeProduct : storeProducts) {
                totalProductsQuantity += storeProduct.getAmount();
            }
        }
        return totalProductsQuantity;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public List<User> getStaff() {
        return staff;
    }

    public void setStaff(List<User> staff) {
        this.staff = staff;
    }

    public StoreType getType() {
        return type == null ? null : StoreType.fromId(type);
    }

    public void setType(StoreType type) {
        this.type = type == null ? null : type.getId();
    }

    public List<StoreProduct> getStoreProducts() {
        return storeProducts;
    }

    public void setStoreProducts(List<StoreProduct> storeProducts) {
        this.storeProducts = storeProducts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}