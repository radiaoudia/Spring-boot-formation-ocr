package com.openclassrooms.webapp.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
public class Employee {

    private Long id;
    private String nom;
    private String prenom;
    private String mail;
    private String password;

}
