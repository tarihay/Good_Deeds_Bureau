package ru.nsu.gorin.shift.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class RequestEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "customer_nickname", nullable = false)
    private String customerNickname;

    @Column(name = "request_info", nullable = false)
    private String requestInfo;

    @Column(name = "karma_count", nullable = false)
    private int karmaCount;

    public RequestEntity() {

    }

    public RequestEntity(String customerNickname, String requestInfo, int karmaCount) {
        this.customerNickname = customerNickname;
        this.requestInfo = requestInfo;
        this.karmaCount = karmaCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerNickname() {
        return customerNickname;
    }

    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public int getKarmaCount() {
        return karmaCount;
    }

    public void setKarmaCount(int karmaCount) {
        this.karmaCount = karmaCount;
    }
}
