package com.windfall.rdostub2.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "ID field either missing or malformed")
public class IdNotFoundException extends NullPointerException {

    /**
     *
     */
    private static final long serialVersionUID = -1933739380349456570L;

    public IdNotFoundException() {

        super("Super Message 204");
    }
    public IdNotFoundException(String message) {

        super(message);
    }
}
