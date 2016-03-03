package br.com.cit.contacts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class Mail extends Entity {

    private Long id;

    private String mail;

    @JsonBackReference
    @Ignore
    private Contact contact;

    public Mail() {
    }

    public Mail(Long id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Ignore
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Mail withId(final Long id) {
        this.id = id;
        return this;
    }

    public Mail withMail(final String mail) {
        this.mail = mail;
        return this;
    }

    public Mail withContact(final Contact contact) {
        this.contact = contact;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mail)) return false;
        Mail mail = (Mail) o;
        return Objects.equal(getId(), mail.getId()) &&
                Objects.equal(getVersion(), mail.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getVersion());
    }

    @Override
    public Object getPk() {
        return getId();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("mail", mail)
                .add("version", getVersion())
                .add("createdAt", getCreatedAt())
                .add("updatedAt", getUpdatedAt())
                .toString();
    }
}
