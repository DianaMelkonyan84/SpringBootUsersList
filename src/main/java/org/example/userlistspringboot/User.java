package org.example.userlistspringboot;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Address address;
    private List<Profession> professions;

    public User(int id, String firstName, String lastName, LocalDate dateOfBirth, Address address, List<Profession> professions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.professions = professions;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
