/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web;

import com.company.myproject.entity.Producer;
import com.haulmont.cuba.gui.screen.ScreenOptions;

public class ProducerOption implements ScreenOptions {
    private final Producer producer;

    public ProducerOption(Producer producer) {
        this.producer = producer;
    }

    public Producer getProducer() {
        return producer;
    }
}
