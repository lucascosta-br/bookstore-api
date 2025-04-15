package br.com.sousinhacode.integrationtests.dto.wrappers.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WrapperBookDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private BookEmbeddedDTO _embedded;

    public WrapperBookDTO() {}

    public BookEmbeddedDTO get_embedded() {
        return _embedded;
    }

    public void set_embedded(BookEmbeddedDTO _embedded) {
        this._embedded = _embedded;
    }
}
