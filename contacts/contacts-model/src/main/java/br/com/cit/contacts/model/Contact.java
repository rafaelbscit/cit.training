package br.com.cit.contacts.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.HashSet;
import java.util.Set;

public class Contact extends Entity {

    private Long id;
    private String name;
    private Set<Mail> mails;

    public Contact() {
    }

    public Contact(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Contact(Long id, String name, Set<Mail> mails) {
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

    public Set<Mail> getMails() {
        return mails;
    }

    public void setMails(Set<Mail> mails) {
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

    public Contact withMails(final Set<Mail> mails) {
        this.mails = mails;
        return this;
    }

    public Boolean addMail(Mail mail) {
        if (mails == null) {
            mails = new HashSet<Mail>();
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
