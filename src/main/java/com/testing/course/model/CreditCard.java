package com.testing.course.model;

import jakarta.persistence.Entity;

@Entity
public class CreditCard {

    String number;
    boolean valid;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
