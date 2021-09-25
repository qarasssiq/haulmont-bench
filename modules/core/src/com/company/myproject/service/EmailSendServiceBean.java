/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.service;

import com.company.myproject.core.MyEmailSendBean;
import com.company.myproject.entity.Store;
import com.haulmont.cuba.core.global.EmailAttachment;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(EmailSendService.NAME)
public class EmailSendServiceBean implements EmailSendService {
    @Inject
    private MyEmailSendBean myEmailSendBean;

    @Override
    public void sendReportByEmail(User user, EmailAttachment emailAttachment, Integer minimumAmount, Store store) {
        myEmailSendBean.sendReportByEmail(user, emailAttachment, minimumAmount, store);
    }

}