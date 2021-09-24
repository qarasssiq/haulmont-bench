/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.core;

import com.company.myproject.entity.Store;
import com.company.myproject.entity.StoreProduct;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.EmailAttachment;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.core.global.EmailInfoBuilder;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.reports.app.service.ReportService;
import com.haulmont.reports.entity.Report;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(MailSendBean.NAME)
public class MailSendBean {
    public static final String NAME = "myproject_MailSendBean";

    @Inject
    private ReportService reportService;

    /*public void sendMail(ArrayList<StoreProduct> storeProducts) {
        Report report = reportService.createReport();
        EmailAttachment emailAttachment = EmailAttachment.cr
        EmailInfo emailInfo = EmailInfoBuilder.create()
                .setAddresses("адрес почты здесь")
                .setCaption("Мало товаров!")
                .setFrom("адрес отправителя")
                .setTemplatePath("com/company/myproject/templates/mail.txt")
                //.setTemplateParameters(Collections.singletonMap("newsItem", newsItem))
                .addAttachment()
                .build();
        emailService.sendEmailAsync(emailInfo);
    }*/

   /* public void sendReportByEmail(ArrayList<StoreProduct> storeProducts, User user) {
        EmailAttachment emailAttachment = makeAttachement(storeProducts, user);
        EmailInfo emailInfo = new EmailInfo(
                invoice.getEmail(), // recipients
                invoice.getBillToName(),// subject
                null,//"from" will be taken from app.properties
                invoice.getDescription(),//body
                emailAttachment
        );
        emailService.sendEmailAsync(emailInfo);
        showNotification("Email sent to " + invoice.getEmail(),NotificationType.HUMANIZED);
    }*/

   /* private EmailAttachment makeAttachement(ArrayList<StoreProduct> storeProducts, User user) {
        List<Report> reports = reportGuiManager.getAvailableReports(this.getId(), user, metaClass);// search reports available for the entity for current user

        Report targetreport = reports.get(0);
        for (Report report : reports) {
            if (report.getName().equals("Report for entity \"Invoice\"")){
                targetreport = report;
                break;
            }
        }
        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("entity", invoice);
        String fileName = invoice.getBillToName() + ".docx";

        ReportOutputDocument outputDocument = reportService.createReport(targetreport, reportParams);

        return new EmailAttachment(outputDocument.getContent(), fileName);
    }

    public void createAttachmentFromReport(Job job, boolean sendEmail) {
        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("job", job);


        FileDescriptor fd = reportService.createAndSaveReport(
                "REPORT_CODE",
                "REPORT_TEMPLATE_CODE",
                reportParams,
                "Job-Order-Report");


        // Create attachment record :
        CommitContext commitContext = new CommitContext();
        JobAttachment jobAttachment = dataManager.create(JobAttachment.class);
        jobAttachment.setDate(new Date());
        jobAttachment.setTitle("Job Order");
        jobAttachment.setJob(job);
        jobAttachment.setFileDescriptor(fd);
        commitContext.addInstanceToCommit(jobAttachment);
        dataManager.commit(commitContext);

        if (sendEmail) {
            // Send email to recipients:
            emailNow(job);
        }
    }

    public void sendReportByEmail(ArrayList<StoreProduct> storeProducts, User user) {
        String sendTo = "a.gorelov@haulmont.com";
        String templateCode = "EMAIL_TEMPLATE_CODE";

        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("storeProducts", storeProducts);

        if (StringUtils.isNotBlank(sendTo)) {
            try {
                emailTemplatesAPI.buildFromTemplate(templateCode)
                        .setTo(sendTo)
                        .setBodyParameter("job", job)
                        .setAttachmentParameters("REPORT_CODE", reportParams)
                        .setAttachmentFiles(files)	// List of files
                        .sendEmailAsync();
            } catch (TemplateNotFoundException | ReportParameterTypeChangedException e) {
                LOG.warn(e.getMessage());
            }
        }
    }*/
}