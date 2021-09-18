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
import java.time.LocalDate;

@Table(name = "MYPROJECT_PRICE_HISTORY")
@Entity(name = "myproject_PriceHistory")
public class PriceHistory extends StandardEntity {
    private static final long serialVersionUID = 779593230315877223L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORE_PRODUCT_ID")
    private StoreProduct storeProduct;

    @NotNull
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "DATE", nullable = false)
    @NotNull
    private LocalDate date;

    public StoreProduct getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(StoreProduct storeProduct) {
        this.storeProduct = storeProduct;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}