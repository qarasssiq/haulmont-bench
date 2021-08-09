/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.retailer;

import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.Retailer;

@UiController("myproject_Retailer.edit")
@UiDescriptor("retailer-edit.xml")
@EditedEntityContainer("retailerDc")
@LoadDataBeforeShow
public class RetailerEdit extends StandardEditor<Retailer> {
}