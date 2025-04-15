package br.com.sousinhacode.integrationtests.dto.wrappers.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WrapperPersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private PersonEmbeddedDTO _embedded;

    public WrapperPersonDTO() {}

    public PersonEmbeddedDTO get_embedded() {
        return _embedded;
    }

    public void set_embedded(PersonEmbeddedDTO _embedded) {
        this._embedded = _embedded;
    }
}
