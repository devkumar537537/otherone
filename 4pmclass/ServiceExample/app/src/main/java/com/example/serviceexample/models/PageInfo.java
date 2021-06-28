package com.example.serviceexample.models;

public class PageInfo{
    public int totalResults;
    public int resultsPerPage;

    public PageInfo(int totalResults, int resultsPerPage) {
        this.totalResults = totalResults;
        this.resultsPerPage = resultsPerPage;
    }

    public PageInfo() {
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
}
