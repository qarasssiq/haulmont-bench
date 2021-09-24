/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.service;

import com.company.myproject.core.MailSendBean;
import com.company.myproject.core.StoreBean;
import com.company.myproject.entity.Retailer;
import com.company.myproject.entity.Store;
import com.company.myproject.entity.StoreProduct;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;

@Service(MailSendService.NAME)
public class MailSendServiceBean implements MailSendService {
    @Inject
    private MailSendBean mailSendBean;

    @Override
    public void sendReportByEmail(ArrayList<StoreProduct> storeProducts, User user) {
        /*mailSendBean.sendReportByEmail(storeProducts, user);*/
    }
}