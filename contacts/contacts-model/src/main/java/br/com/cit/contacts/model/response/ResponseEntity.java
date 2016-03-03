package br.com.cit.contacts.model.response;

import com.google.common.base.MoreObjects;

public class ResponseEntity {

    private final int status;
    private final String reasonPhrase;
    private final Object response;

    public ResponseEntity(int status, String reasonPhrase, Object response) {
        this.status = status;
        this.reasonPhrase = reasonPhrase;
        this.response = response;
    }

    public ResponseEntity(int status, String reasonPhrase) {
        this.status = status;
        this.reasonPhrase = reasonPhrase;
        this.response = null;
    }

    public int getStatus() {
        return status;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public Object getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("status", status)
                .add("reasonPhrase", reasonPhrase)
                .add("response", response)
                .toString();
    }
}
