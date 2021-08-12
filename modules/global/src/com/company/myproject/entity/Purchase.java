/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "MYPROJECT_PURCHASE")
@Entity(name = "myproject_Purchase")
public class Purchase extends StandardEntity {
    private static final long serialVersionUID = 7681206732561712661L;

    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "purchase")
    @Composition
    private List<PurchaseProduct> purchaseProducts;

    public List<PurchaseProduct> getPurchaseProducts() {
        return purchaseProducts;
    }

    public void setPurchaseProducts(List<PurchaseProduct> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}