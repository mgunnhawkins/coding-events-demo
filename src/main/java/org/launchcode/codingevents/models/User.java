package org.launchcode.codingevents.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity{
    @NotNull
    private String username;

    @NotNull
    private String pwHash;

//encoder creates hash from givenn password
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() { return username; }

//    checks to see if password is a match for the has stored by the object
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }


}
