package ru.nsu.gorin.shift.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountInfoEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "karma_count")
    private int karmaCount;

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
}
