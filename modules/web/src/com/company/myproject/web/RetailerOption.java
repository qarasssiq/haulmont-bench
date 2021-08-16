/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web;

import com.company.myproject.entity.Retailer;
import com.haulmont.cuba.gui.screen.ScreenOptions;

public class RetailerOption implements ScreenOptions {
    private final Retailer retailer;

    public RetailerOption(Retailer retailer) {
        this.retailer = retailer;
    }

    public Retailer getRetailer() {
        return retailer;
    }
}
