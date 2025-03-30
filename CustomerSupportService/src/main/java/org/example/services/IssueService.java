package org.example.services;

import org.example.exceptions.ServiceException;
import org.example.models.Issue;
import org.example.models.IssueFilter;
import org.example.models.IssueStatus;
import org.example.models.IssueType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class IssueService {

    private static IssueService issueService;
    private IssueService() {}
    public static IssueService getInstance() {
        if(issueService==null) {
            synchronized (IssueService.class) {
                if(issueService==null) {
                    issueService = new IssueService();
                }
            }
        }
        return issueService;
    }
    private AtomicInteger issueId = new AtomicInteger(0);
    private Map<Integer, Issue> issueIdMap = new HashMap<>();

    public int createIssue(String transactionId, IssueType issueType, String subject, String description, String email) {

        Issue issue = new Issue(transactionId, issueId.addAndGet(1), issueType, subject, description, email);
        issueIdMap.put(issue.getIssueId(), issue);
        return issue.getIssueId();
    }

    public Issue getIssue(int issueId) throws ServiceException {

        if(!issueIdMap.containsKey(issueId)) {
            throw new ServiceException("IssueId not found");
        }
        return issueIdMap.get(issueId);
    }

    public List<Issue> filterIssue(IssueFilter issueFilter) {

        if(issueFilter.getFilterType()==IssueFilter.FilterType.ISSUE_TYPE) {
            return issueIdMap.entrySet().stream()
                    .filter(issueEntry -> issueEntry.getValue().getIssueType().equals(IssueType.valueOf(issueFilter.getValue())))
                    .map(issueEntry -> issueEntry.getValue())
                    .collect(Collectors.toList());
        } else if (issueFilter.getFilterType()== IssueFilter.FilterType.EMAIL) {
            return issueIdMap.entrySet().stream()
                    .filter(issueEntry -> issueEntry.getValue().getEmail().equals(issueFilter.getValue()))
                    .map(issueEntry -> issueEntry.getValue())
                    .collect(Collectors.toList());
        }
        throw new ServiceException("Filter unimplemented");
    }

    public void updateIssue(int issueId, IssueStatus status, String resolution) throws ServiceException {

        Issue issue = getIssue(issueId);
        issue.setIssueStatus(status);
        if(status.equals(IssueStatus.RESOLVED)) {
            issue.setResoltion(resolution);
        }
    }
}
