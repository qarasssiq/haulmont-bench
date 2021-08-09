/*
 * Copyright (c) 2021 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.company.myproject.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "MYPROJECT_PRODUCER")
@Entity(name = "myproject_Producer")
public class Producer extends StandardEntity {
    private static final long serialVersionUID = -4443756371495698389L;

    @NotNull
    @Column(name = "PRODUCER_NAME", nullable = false, unique = true)
    private String producer_name;

    @Column(name = "PRODUCER_FULL_NAME")
    private String producer_full_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCER_USER_ID")
    private User producer_user;

    public User getProducer_user() {
        return producer_user;
    }

    public void setProducer_user(User producer_user) {
        this.producer_user = producer_user;
    }

    public String getProducer_full_name() {
        return producer_full_name;
    }

    public void setProducer_full_name(String producer_full_name) {
        this.producer_full_name = producer_full_name;
    }

    public String getProducer_name() {
        return producer_name;
    }

    public void setProducer_name(String producer_name) {
        this.producer_name = producer_name;
    }
}