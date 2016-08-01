package br.com.cit.contacts.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import br.com.cit.contacts.model.constant.EntityConstant;

@JsonAutoDetect
public abstract class Entity implements Serializable {

    private Date createdAt;
    private Date updatedAt;
    private Long version;

    public abstract Object getPk();

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Entity withVersion(final Long version) {
        this.version = version;
        return this;
    }

    public void initEntity() {
        createdAt = new Date();
        updatedAt = createdAt;
        version = EntityConstant.DEFAULT_VERSION;
    }

    public void updateEntity() {
        updatedAt = new Date();
        version++;
    }

}
