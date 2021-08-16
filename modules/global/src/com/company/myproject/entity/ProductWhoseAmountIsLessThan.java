/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import javax.validation.constraints.Positive;

@MetaClass(name = "myproject_ProductWhoseAmountIsLessThan")
public class ProductWhoseAmountIsLessThan extends BaseUuidEntity {
    private static final long serialVersionUID = -2694222226359632229L;

    @MetaProperty
    private Product product;

    @MetaProperty
    @Positive(message = "Amount must be positive!")
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}