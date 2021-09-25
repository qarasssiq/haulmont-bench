/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.service;

import com.company.myproject.entity.Store;
import com.haulmont.cuba.core.global.EmailAttachment;
import com.haulmont.cuba.security.entity.User;

public interface EmailSendService {
    String NAME = "myproject_EmailSendService";

    void sendReportByEmail(User user, EmailAttachment emailAttachment, Integer minimumAmount, Store store);
}