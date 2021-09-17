/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.EmbeddableEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@MetaClass(name = "myproject_Address")
@Embeddable
@NamePattern("%s, %s, %s|street,building,city")
public class Address extends EmbeddableEntity {
    private static final long serialVersionUID = 1711860107067625010L;

    @Column(name = "CITY", nullable = false)
    @NotNull
    private String city;

    @Column(name = "STREET", nullable = false)
    @NotNull
    private String street;

    @Column(name = "BUILDING", nullable = false)
    @NotNull
    private String building;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}