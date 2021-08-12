/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web;

import com.company.myproject.entity.Store;
import com.haulmont.cuba.gui.screen.ScreenOptions;

public class StoreOption implements ScreenOptions {
    private final Store store;

    public StoreOption(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }
}
