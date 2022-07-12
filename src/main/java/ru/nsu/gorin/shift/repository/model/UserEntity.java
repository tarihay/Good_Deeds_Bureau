package ru.nsu.gorin.shift.repository.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "accounts")
public class UserEntity implements UserDetails {
    public static final int NICKNAME_MIN_SIZE = 3;
    public static final int NICKNAME_MAX_SIZE = 15;
    public static final int FIRST_NAME_MIN_SIZE = 1;
    public static final int PASSWORD_MIN_SIZE = 8;

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "nickname", nullable = false, unique = true)
    @Size(min = NICKNAME_MIN_SIZE, max = NICKNAME_MAX_SIZE,
            message = "Incorrect amount of characters")
    private String nickname;

    @Column(name = "first_name", nullable = false)
    @Size(min = FIRST_NAME_MIN_SIZE, message = "You have to type your first name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    @Size(min = PASSWORD_MIN_SIZE,
            message = "Your password have to contain more than 8 characters")
    private String password;

    @Column(name = "karma_count")
    private int karmaCount;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> roles;

    @Transient
    private String passwordConfirm;


    public String getNickname() {
        return nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getKarmaCount() {
        return karmaCount;
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

    public void setKarmaCount(int karmaCount) {
        this.karmaCount = karmaCount;
    }

    public long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
