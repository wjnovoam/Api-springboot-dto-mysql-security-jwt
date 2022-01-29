package com.wjnovoam.app.exceptions;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    private HttpStatus estatu;
    private String message;

    public BlogAppException(HttpStatus estatu, String message) {
        super();
        this.estatu = estatu;
        this.message = message;
    }

    public BlogAppException(HttpStatus estatu, String message, String message1) {
        super();
        this.estatu = estatu;
        this.message = message;
        this.message = message1;
    }

    public HttpStatus getEstatu() {
        return estatu;
    }

    public void setEstatu(HttpStatus estatu) {
        this.estatu = estatu;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
