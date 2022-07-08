package ru.nsu.gorin.shift.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "execution_requests")
public class ExecutionRequestEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "customer_nickname", nullable = false)
    private String customerNickname;

    @Column(name = "executor_nickname", nullable = false)
    private String executorNickname;

    @Column(name = "actual_karma_count", nullable = false)
    private int actualKarmaCount;

    @Column(name = "suggested_karma_count")
    private int suggestedKarmaCount;

    @Column(name = "execution_request_info", nullable = false)
    private String executionRequestInfo;

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

    public String getExecutorNickname() {
        return executorNickname;
    }

    public void setExecutorNickname(String executorNickname) {
        this.executorNickname = executorNickname;
    }

    public String getExecutionRequestInfo() {
        return executionRequestInfo;
    }

    public void setExecutionRequestInfo(String executionRequestInfo) {
        this.executionRequestInfo = executionRequestInfo;
    }

    public int getActualKarmaCount() {
        return actualKarmaCount;
    }

    public void setActualKarmaCount(int actualKarmaCount) {
        this.actualKarmaCount = actualKarmaCount;
    }

    public int getSuggestedKarmaCount() {
        return suggestedKarmaCount;
    }

    public void setSuggestedKarmaCount(int suggestedKarmaCount) {
        this.suggestedKarmaCount = suggestedKarmaCount;
    }


}
