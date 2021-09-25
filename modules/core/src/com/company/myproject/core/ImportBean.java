/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.core;

import com.haulmont.cuba.core.app.importexport.EntityImportExportAPI;
import com.haulmont.cuba.core.global.Resources;
import com.haulmont.cuba.core.sys.events.AppContextStartedEvent;
import com.haulmont.cuba.security.app.Authenticated;
import com.haulmont.reports.ReportImportExportAPI;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.Objects;

@Component(ImportBean.NAME)
public class ImportBean {
    public static final String NAME = "myproject_ImportBean";
    private static final String PATH_TO_REPORTS = "com/company/myproject/core/data/Reports.zip";
    //private static final String PATH_TO_LOCKS = "com/company/myproject/core/data/Locks.zip";
    @Inject
    private ReportImportExportAPI reportImportExportAPI;
    @Inject
    private Logger log;
    @Inject
    private Resources resources;
    @Inject
    private EntityImportExportAPI entityImportExportAPI;
    @Authenticated
    @EventListener
    public void onApplicationContextStarted(AppContextStartedEvent event) {
        try {
            byte[] reportsZip;
            try (InputStream is = resources.getResourceAsStream(PATH_TO_REPORTS)) {
                Objects.requireNonNull(is);
                reportsZip = IOUtils.toByteArray(is);
            }
            reportImportExportAPI.importReports(reportsZip);
            /*byte[] locksZip;
            try (InputStream is2 = resources.getResourceAsStream(PATH_TO_LOCKS)) {
                Objects.requireNonNull(is2);
                locksZip = IOUtils.toByteArray(is2);
            }
            EntityImportView locksImportView = new EntityImportView(LockDescriptor.class);
            locksImportView.addProperties("name", "timeoutSec", "createdBy", "createTs");
            entityImportExportAPI.importEntitiesFromZIP(locksZip, locksImportView);*/
        } catch (Exception e) {
            log.error("Failed to import reports on server start", e);
        }
    }
}