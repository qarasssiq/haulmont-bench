/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.web.screens.producer;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.screen.*;
import com.company.myproject.entity.Producer;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;

@UiController("myproject_Producer.browse")
@UiDescriptor("producer-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class ProducerBrowse extends MasterDetailScreen<Producer> {
    @Inject
    private PickerField<User> producerUserField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        producerUserField.setValue(AppBeans.get(UserSessionSource.class).getUserSession().getUser());
        producerUserField.setEditable(false);
    }

    @Subscribe("createBtn")
    public void onCreateBtnClick(Button.ClickEvent event) {
        producerUserField.setValue(AppBeans.get(UserSessionSource.class).getUserSession().getUser());
    }


}