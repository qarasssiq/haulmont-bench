/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum StoreType implements EnumClass<String> {

    CONVENIENCE_STORE("Convenience store"),
    SUPERMARKET("Supermarket"),
    HYPERMARKET("Hypermarket");

    private String id;

    StoreType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static StoreType fromId(String id) {
        for (StoreType at : StoreType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}