package com.example.validationapi.model;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;

public class CardDetails {

    @NotNull
    @CreditCardNumber(message = "The credit card number is invalid")
    private String ccNumber;

    @NotNull
    @Pattern(regexp = "20\\d{2}/(0[1-9]|1[0-2])", message = "The expiry date format must be in yyyy/MM")
    private String ccExpiration;

    @NotNull(message = "Invalid cvv")
    @Digits(integer = 4, fraction = 0, message = "Invalid CVV")
    private Integer cvv;


    public CardDetails() {
    }

    public CardDetails(String ccNumber, String ccExpiration, Integer cvv) {
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.cvv = cvv;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

   public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public Integer getCvv() {
        return cvv;
    }

  public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
}
