package org.example.services;

import org.example.exceptions.ServiceException;
import org.example.models.Agent;
import org.example.models.Issue;
import org.example.models.IssueStatus;

import java.util.List;

public class FreeAgentAssignmentStratergy implements IIssueAssignmentStratergy{

    private static IssueService issueService = IssueService.getInstance();
    private static AgentService agentService = AgentService.getInstance();
    private static FreeAgentAssignmentStratergy freeAgentAssignmentStratergy;

    private FreeAgentAssignmentStratergy() {};
    public static FreeAgentAssignmentStratergy getInstance() {
        if(freeAgentAssignmentStratergy==null) {
            synchronized (FreeAgentAssignmentStratergy.class) {
                if(freeAgentAssignmentStratergy==null) {
                    freeAgentAssignmentStratergy = new FreeAgentAssignmentStratergy();
                }
            }
        }
        return freeAgentAssignmentStratergy;
    }

    @Override
    public int assignIssue(int issueId) throws ServiceException {

        Issue issue = issueService.getIssue(issueId);
        List<Agent> freeAgents = agentService.getAgentsByIssueType(issue.getIssueType());
        if(freeAgents.isEmpty()) {
            throw new ServiceException("No Agent with Expertise Found");
        }
        Agent assignedAgent = freeAgents.get(0);
        assignedAgent.setAssignedIssueId(issueId);
        issue.setAssignedAgentId(assignedAgent.getAgentId());
        issue.setIssueStatus(IssueStatus.ASSIGNED);
        return assignedAgent.getAgentId();
    }

}
