/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "MYPROJECT_PRODUCER")
@Entity(name = "myproject_Producer")
@NamePattern("%s|producerName")
public class Producer extends StandardEntity {
    private static final long serialVersionUID = -4443756371495698389L;

    @NotNull
    @Column(name = "PRODUCER_NAME", nullable = false, unique = true)
    private String producerName;

    @Column(name = "PRODUCER_FULL_NAME")
    private String producerFullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCER_USER_ID")
    private User producerUser;

    public User getProducerUser() {
        return producerUser;
    }

    public void setProducerUser(User producerUser) {
        this.producerUser = producerUser;
    }

    public String getProducerFullName() {
        return producerFullName;
    }

    public void setProducerFullName(String producerFullName) {
        this.producerFullName = producerFullName;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }
}