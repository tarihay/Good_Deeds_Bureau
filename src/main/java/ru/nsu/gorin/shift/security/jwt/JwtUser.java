package ru.nsu.gorin.shift.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Size;
import java.util.Collection;

public class JwtUser implements UserDetails {
    public static final int NICKNAME_MIN_SIZE = 3;
    public static final int NICKNAME_MAX_SIZE = 15;
    public static final int FIRST_NAME_MIN_SIZE = 1;
    public static final int PASSWORD_MIN_SIZE = 8;

    private long id;

    @Size(min = NICKNAME_MIN_SIZE, max = NICKNAME_MAX_SIZE,
            message = "Incorrect amount of characters")
    private String nickname;

    @Size(min = FIRST_NAME_MIN_SIZE, message = "You have to type your first name")
    private String firstName;

    private String lastName;

    @Size(min = PASSWORD_MIN_SIZE,
            message = "Your password have to contain more than 8 characters")
    private String password;

    public JwtUser(
            String nickname,
            String firstName,
            String lastName,
            String password) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }


    public String getNickname() {
        return nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}