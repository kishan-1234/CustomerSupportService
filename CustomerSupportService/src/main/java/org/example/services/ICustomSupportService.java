package org.example.services;

import org.example.exceptions.ServiceException;
import org.example.models.IssueFilter;
import org.example.models.IssueStatus;
import org.example.models.IssueType;

import java.util.List;
import java.util.Map;

public interface ICustomSupportService {

    Integer createIssue(String transactionId, IssueType issueType, String subject, String description, String email);
    Integer addAgent(String agentEmail, String agentName , List<IssueType> experties);
    Integer assignIssue(int issueId) throws ServiceException;
    List<Integer> getIssue(IssueFilter.FilterType issueType, String filterValue);
    void updateIssue(int issueId, IssueStatus issueStatus, String resolution);
    Map<Integer, List<Integer>> viewAgentsHistory();
}
