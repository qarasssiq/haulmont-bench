/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Table(name = "MYPROJECT_PURCHASE_PRODUCT")
@Entity(name = "myproject_PurchaseProduct")
@NamePattern("%s (%s)|product,amount")
public class PurchaseProduct extends StandardEntity {
    private static final long serialVersionUID = 7415659311139598278L;

    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @NotNull
    @Column(name = "IS_DISCOUNT", nullable = false)
    private Boolean isDiscount = false;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    @Positive(message = "Amount of the product must be positive!")
    private Integer amount;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PURCHASE_ID")
    private Purchase purchase;

    public Boolean getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Boolean isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

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