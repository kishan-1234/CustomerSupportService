package org.example.models;

public class Issue {
    private int issueId;
    private String transactionId;
    private IssueType issueType;
    private String subject;
    private String description;
    private String email;
    private IssueStatus issueStatus;
    private String resoltion;
    private int assignedAgentId = -1;

    public Issue(String transactionId, int issueId, IssueType issueType, String subject, String description, String email) {
        this.transactionId = transactionId;
        this.issueId = issueId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.issueStatus = IssueStatus.CREATED;
    }

    public int getIssueId() {
        return issueId;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setAssignedAgentId(int assignedAgentId) {
        this.assignedAgentId = assignedAgentId;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    public void setResoltion(String resoltion) {
        this.resoltion = resoltion;
    }

    public String getEmail() {
        return this.email;
    }

    public int getAssignedAgentId() {
        return this.assignedAgentId;
    }
}
