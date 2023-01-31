package com.fr.technicaltestoffer.usermanagement.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class User {

    @NotNull(message = "Name is required.")
    private String name;
    @NotNull(message = "The country is required.")
    private String countryOfResidence;
    @NotNull(message = "Birth Date is required.")
    private String birthDate;
    private Integer phoneNumber;
    private String gender;
}
