package com.samandmoore.base.core;

import org.joda.time.DateTime;

/**
 * @author Sam Moore
 * @since 4/23/14 10:41 PM
 */
public class SomeDomainModel {

    private int id;
    private String name;
    private DateTime createdAt;

    public int getId() {

        return id;
    }

    public void setId(final int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public DateTime getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(final DateTime createdAt) {

        this.createdAt = createdAt;
    }
}
