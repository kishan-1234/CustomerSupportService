package org.example;

import org.example.models.IssueFilter;
import org.example.models.IssueStatus;
import org.example.models.IssueType;
import org.example.services.CustomerSupportServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    private static CustomerSupportServiceImpl customerSupportService = CustomerSupportServiceImpl.getInstance();

    public static void main(String[] args) {
        int issueId = createIssue("T1", IssueType.PAYMENT_RELATED, "Payment Failed", "My payment failed but money is debited", "testUser1@test.com");
        int issueId2 = createIssue("T2", IssueType.MUTUAL_FUND_RELATED, "Purchase Failed", "Unable to purchase Mutual Fund", "testUser2@test.com");
        int issueId3 = createIssue("T3", IssueType.PAYMENT_RELATED, "Payment Failed", "My payment failed but money is debited", "testUser2@test.com");
        int agentId = addAgent("agent1@test.com", "Agent 1", Arrays.asList(IssueType.PAYMENT_RELATED, IssueType.GOLD_RELATED));
        int agentId2 = addAgent("agent2@test.com", "Agent 2", Arrays.asList(IssueType.MUTUAL_FUND_RELATED));
        int assignedAgentId = assignIssue(issueId);
        int assignedAgentId2 = assignIssue(issueId2);
        int assignedAgentI3 = assignIssue(issueId3);
        List<Integer> issues = getIssue(IssueFilter.FilterType.ISSUE_TYPE, IssueType.PAYMENT_RELATED.toString());
        List<Integer> issues2 = getIssue(IssueFilter.FilterType.EMAIL, "testUser2@test.com");
        updateIssue(3, IssueStatus.IN_PROGRESS, "Waiting for payment confirmation");
        updateIssue(3, IssueStatus.RESOLVED, "PaymentFailed debited amount will get reversed");
        Map<Integer, List<Integer>> agentsHistory = viewAgentsHistory();
        System.out.println("Hello world!");
    }

    private static int createIssue(String transactionId, IssueType issueType, String subject, String description, String email) {
        return customerSupportService.createIssue(transactionId, issueType, subject, description, email);
    }

    private static int addAgent(String agentEmail, String agentName, List<IssueType> issueTypes) {
        return customerSupportService.addAgent(agentEmail, agentName, issueTypes);
    }

    private static int assignIssue(int issueId) {
        return customerSupportService.assignIssue(issueId);
    }

    private static List<Integer> getIssue(IssueFilter.FilterType filterType, String value) {
        return customerSupportService.getIssue(filterType, value);
    }

    private static void updateIssue(int issueId, IssueStatus status, String resoltion)  {
         customerSupportService.updateIssue(issueId, status, resoltion);
    }

    private static Map<Integer, List<Integer>> viewAgentsHistory() {
        return customerSupportService.viewAgentsHistory();
    }
}