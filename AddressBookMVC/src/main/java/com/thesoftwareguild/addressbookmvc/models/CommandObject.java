
package com.thesoftwareguild.addressbookmvc.models;

import org.hibernate.validator.constraints.NotEmpty;

public class CommandObject {
    private Integer id;
    @NotEmpty(message="Must enter first name")
    private String firstName;
    @NotEmpty(message="Must enter last name")
    private String lastName;
    @NotEmpty(message="Must enter street")
    private String street;
    @NotEmpty(message="Must enter city")
    private String city;
    @NotEmpty(message="Must enter state")
    private String state;
    @NotEmpty(message="Must enter zip")
    private String zip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    
    
}
