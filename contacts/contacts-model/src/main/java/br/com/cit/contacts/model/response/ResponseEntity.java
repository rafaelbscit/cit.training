package br.com.cit.contacts.model.response;

import com.google.common.base.MoreObjects;

public class ResponseEntity {

    private final int value;
    private final String reasonPhrase;
    private final Object response;

    public ResponseEntity(int value, String reasonPhrase, Object response) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
        this.response = response;
    }

    public ResponseEntity(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
        this.response = null;
    }

    public int getValue() {
        return value;
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
                .add("value", value)
                .add("reasonPhrase", reasonPhrase)
                .add("response", response)
                .toString();
    }
}
