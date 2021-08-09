/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "MYPROJECT_RETAILER")
@Entity(name = "myproject_Retailer")
public class Retailer extends StandardEntity {
    private static final long serialVersionUID = 9221683264528665105L;

    @NotNull
    @Column(name = "RETAILER_NAME", nullable = false, unique = true)
    private String retailer_name;

    @Column(name = "RETAILER_FULL_NAME")
    private String retailer_full_name;

    public String getRetailer_full_name() {
        return retailer_full_name;
    }

    public void setRetailer_full_name(String retailer_full_name) {
        this.retailer_full_name = retailer_full_name;
    }

    public String getRetailer_name() {
        return retailer_name;
    }

    public void setRetailer_name(String retailer_name) {
        this.retailer_name = retailer_name;
    }
}