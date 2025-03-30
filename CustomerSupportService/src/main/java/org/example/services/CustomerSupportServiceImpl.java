package org.example.services;

import org.example.exceptions.ServiceException;
import org.example.models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerSupportServiceImpl implements ICustomSupportService {

    private static IssueService issueService = IssueService.getInstance();
    private static AgentService agentService = AgentService.getInstance();
    private static IIssueAssignmentStratergy issueAssignmentStratergy = FreeAgentAssignmentStratergy.getInstance();
    private static CustomerSupportServiceImpl customerSupportService;
    private CustomerSupportServiceImpl() {}

    public static CustomerSupportServiceImpl getInstance() {
        if(customerSupportService==null) {
            synchronized (CustomerSupportServiceImpl.class) {
                if(customerSupportService==null) {
                    customerSupportService = new CustomerSupportServiceImpl();
                }
            }
        }
        return customerSupportService;
    }

    public static void setIssueAssignmentStratergy(IIssueAssignmentStratergy stratergy) {
        issueAssignmentStratergy = stratergy;
    }

    @Override
    public Integer createIssue(String transactionId, IssueType issueType, String subject, String description, String email) {
        return issueService.createIssue(transactionId, issueType, subject, description, email);
    }

    @Override
    public Integer addAgent(String agentEmail, String agentName, List<IssueType> experties) {
        return agentService.addAgent(agentEmail,agentName, experties);
    }

    @Override
    public Integer assignIssue(int issueId) throws ServiceException {
        return issueAssignmentStratergy.assignIssue(issueId);
    }

    @Override
    public List<Integer> getIssue(IssueFilter.FilterType filterType, String filterValue) {

        IssueFilter issueFilter = new IssueFilter(filterType, filterValue);
        return issueService.filterIssue(issueFilter).stream().map(issue -> issue.getIssueId()).collect(Collectors.toList());
    }

    @Override
    public void updateIssue(int issueId, IssueStatus issueStatus, String resolution) {
        issueService.updateIssue(issueId, issueStatus, resolution);
    }

    @Override
    public Map<Integer, List<Integer>> viewAgentsHistory() {

        Map<Integer, List<Integer>> agentHistory = new HashMap<>();
        List<Agent> agents = agentService.getAllAgents();
        for(Agent agent:agents) {
            agentHistory.put(agent.getAgentId(), agent.getIssuesWorkedOn());
        }
        return agentHistory;
    }
}
