/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.core;

import com.company.myproject.entity.Store;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.EmailAttachment;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.core.global.EmailInfoBuilder;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component(MyEmailSendBean.NAME)
public class MyEmailSendBean {
    public static final String NAME = "myproject_MyEmailSendBean";

    @Inject
    private EmailService emailService;

    public void sendReportByEmail(User user, EmailAttachment emailAttachment, Integer minimumAmount, Store store) {
        EmailInfo emailInfo = EmailInfoBuilder.create()
                .setAddresses(user.getEmail())
                .setCaption(user.getName() + ", в магазине " + store.getName() + " недостаточно товаров!")
                .setBody("НУЖНО БОЛЬШЕ ТОВАРОВ!\nУ некоторых товаров количество меньше "
                        + minimumAmount + " единиц.")
                .setAttachments(emailAttachment)
                .build();

        emailService.sendEmailAsync(emailInfo);
    }
}