package org.example.models;

public class IssueFilter {

    public enum FilterType {EMAIL, ISSUE_TYPE};
    private FilterType filterType;
    private String value;

    public IssueFilter(FilterType filterType, String filterValue) {
        this.filterType = filterType;
        this.value = filterValue;
    }

    public FilterType getFilterType() {
        return this.filterType;
    }

    public String getValue() {
        return this.value;
    }
}
