/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "MYPROJECT_PRODUCT")
@Entity(name = "myproject_Product")
public class Product extends StandardEntity {
    private static final long serialVersionUID = 1517050633050893209L;

    @NotNull
    @Column(name = "PRODUCT_NAME", nullable = false, unique = true)
    private String product_name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_PRODUCER_ID")
    private Producer product_producer;

    @NotNull
    @Column(name = "PRODUCT_PRICE", nullable = false)
    private BigDecimal product_price;

    public BigDecimal getProduct_price() {
        return product_price;
    }

    public void setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
    }

    public Producer getProduct_producer() {
        return product_producer;
    }

    public void setProduct_producer(Producer product_producer) {
        this.product_producer = product_producer;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}