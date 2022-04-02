package com.exple.stray_care;

public class userdonateNgodatadonate {
    private String name,email,mobile;

    public userdonateNgodatadonate() {
    }

    public userdonateNgodatadonate(String name, String email) {
        this.name = name;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
