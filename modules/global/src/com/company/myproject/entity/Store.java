/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "MYPROJECT_STORE")
@Entity(name = "myproject_Store")
@NamePattern("%s %s|storeNum,storeName")
public class Store extends StandardEntity {
    private static final long serialVersionUID = -4059720126704569118L;

    @NotNull
    @Column(name = "STORE_NUM", nullable = false, unique = true)
    private String storeNum;

    @NotNull
    @Column(name = "STORE_NAME", nullable = false)
    private String storeName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORE_RETAILER_ID")
    private Retailer storeRetailer;

    public Retailer getStoreRetailer() {
        return storeRetailer;
    }

    public void setStoreRetailer(Retailer storeRetailer) {
        this.storeRetailer = storeRetailer;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }
}