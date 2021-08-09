/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "MYPROJECT_STORE")
@Entity(name = "myproject_Store")
public class Store extends StandardEntity {
    private static final long serialVersionUID = -4059720126704569118L;

    @NotNull
    @Column(name = "STORE_NUM", nullable = false, unique = true)
    private String store_num;

    @NotNull
    @Column(name = "STORE_NAME", nullable = false)
    private String store_name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORE_RETAILER_ID")
    private Retailer store_retailer;

    public Retailer getStore_retailer() {
        return store_retailer;
    }

    public void setStore_retailer(Retailer store_retailer) {
        this.store_retailer = store_retailer;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_num() {
        return store_num;
    }

    public void setStore_num(String store_num) {
        this.store_num = store_num;
    }
}