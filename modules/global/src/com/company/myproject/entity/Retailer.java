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
    private String retailerName;

    @Column(name = "RETAILER_FULL_NAME")
    private String retailerFullName;

    public String getRetailerFullName() {
        return retailerFullName;
    }

    public void setRetailerFullName(String retailerFullName) {
        this.retailerFullName = retailerFullName;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }
}