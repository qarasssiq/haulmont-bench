/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.producer;

import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.Producer;

@UiController("myproject_Producer.browse")
@UiDescriptor("producer-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class ProducerBrowse extends MasterDetailScreen<Producer> {
}