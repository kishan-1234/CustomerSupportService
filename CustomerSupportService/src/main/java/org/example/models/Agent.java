package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Agent {

    private int agentId;
    private String email;
    private String agentName;
    private Set<IssueType> experties;
    private List<Integer> issuesWorkedOn = new ArrayList<>();

    public Agent(int agentId, String email, String agentName, Set<IssueType> experties) {

        this.agentId = agentId;
        this.email = email;
        this.agentName = agentName;
        this.experties = experties;
    }

    public int getAgentId() {
        return agentId;
    }

    public Set<IssueType> getExperties() {
        return experties;
    }

    public List<Integer> getIssuesWorkedOn() {
        return issuesWorkedOn;
    }

    public void setAssignedIssueId(int assignedIssueId) {
        issuesWorkedOn.add(assignedIssueId);
    }
}
