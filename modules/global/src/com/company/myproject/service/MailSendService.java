/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.service;

import com.company.myproject.entity.StoreProduct;
import com.haulmont.cuba.security.entity.User;

import java.util.ArrayList;

public interface MailSendService {
    String NAME = "myproject_MailSendService";

    void sendReportByEmail(ArrayList<StoreProduct> storeProducts, User user);
}