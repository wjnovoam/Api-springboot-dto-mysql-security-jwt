package com.wjnovoam.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFountException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String nameOfRecurse;
    private String nameOfField;
    private Long valueOfCampo;

    public ResourceNotFountException(String nameOfRecurse, String nameOfField, Long valueOfCampo) {
        super(String.format("%s no encontrada con : %s : '%s'", nameOfRecurse, nameOfField, valueOfCampo));
        this.nameOfRecurse = nameOfRecurse;
        this.nameOfField = nameOfField;
        this.valueOfCampo = valueOfCampo;
    }

    public String getNameOfRecurse() {
        return nameOfRecurse;
    }

    public void setNameOfRecurse(String nameOfRecurse) {
        this.nameOfRecurse = nameOfRecurse;
    }

    public String getNameOfField() {
        return nameOfField;
    }

    public void setNameOfField(String nameOfField) {
        this.nameOfField = nameOfField;
    }

    public Long getValueOfCampo() {
        return valueOfCampo;
    }

    public void setValueOfCampo(Long valueOfCampo) {
        this.valueOfCampo = valueOfCampo;
    }
}
