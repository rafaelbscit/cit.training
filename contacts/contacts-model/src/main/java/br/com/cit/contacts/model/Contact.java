package br.com.cit.contacts.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

public class Contact extends Entity {

    private Long id;

    private String name;

    @JsonManagedReference
    private List<Mail> mails;

    public Contact() {
    }

    public Contact(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Contact(Long id, String name, List<Mail> mails) {
        this.id = id;
        this.name = name;
        this.mails = mails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Mail> getMails() {
        return mails;
    }

    public void setMails(List<Mail> mails) {
        this.mails = mails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact withId(final Long id) {
        this.id = id;
        return this;
    }

    public Contact withName(final String name) {
        this.name = name;
        return this;
    }

    public Contact withMails(final List<Mail> mails) {
        this.mails = mails;
        return this;
    }

    public Boolean addMail(Mail mail) {
        if (mails == null) {
            mails = new ArrayList<Mail>();
        }

        return mails.add(mail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equal(getId(), contact.getId()) &&
                Objects.equal(getVersion(), contact.getVersion());
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
                .add("name", name)
                .add("mails", mails)
                .add("version", getVersion())
                .add("createdAt", getCreatedAt())
                .add("updatedAt", getUpdatedAt())
                .toString();
    }
}
