package org.example.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum IssueType {
    PAYMENT_RELATED, MUTUAL_FUND_RELATED, GOLD_RELATED;

    public static Set<IssueType> getUniqueIssueType(List<IssueType> issueTypes) {

         return issueTypes.stream()
                .collect(Collectors.toSet());
    }
}
