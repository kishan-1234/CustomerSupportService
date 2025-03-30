package org.example.services;

import org.example.exceptions.ServiceException;

public interface IIssueAssignmentStratergy {

    int assignIssue(int issueId) throws ServiceException;
}
