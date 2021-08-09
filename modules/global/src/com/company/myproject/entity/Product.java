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
import java.math.BigDecimal;

@Table(name = "MYPROJECT_PRODUCT")
@Entity(name = "myproject_Product")
@NamePattern("%s|productName")
public class Product extends StandardEntity {
    private static final long serialVersionUID = 1517050633050893209L;

    @NotNull
    @Column(name = "PRODUCT_NAME", nullable = false, unique = true)
    private String productName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_PRODUCER_ID")
    private Producer productProducer;

    @NotNull
    @Column(name = "PRODUCT_PRICE", nullable = false)
    private BigDecimal productPrice;

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Producer getProductProducer() {
        return productProducer;
    }

    public void setProductProducer(Producer productProducer) {
        this.productProducer = productProducer;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}