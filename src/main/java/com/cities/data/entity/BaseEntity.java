package com.cities.data.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Version
    @Column(name = "row_version")
    protected long rowVersion;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    protected ZonedDateTime createdDate = ZonedDateTime.now();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    protected BaseEntity() {

    }
}
