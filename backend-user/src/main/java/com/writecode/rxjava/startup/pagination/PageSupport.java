package com.writecode.rxjava.startup.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PageSupport<T> {

    public static final String FIRST_PAGE_NUM = "0";
    public static final String DEFAULT_PAGE_SIZE = "20";

    public List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElement;

    public PageSupport() {

    }

    public PageSupport(List<T> content, int pageNumber, int pageSize, long totalElement) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElement = totalElement;
    }

    @JsonProperty
    public long totalPages()
    {
        return pageSize > 0 ? (totalElement - 1) / pageSize + 1 : 0;
    }

    @JsonProperty
    public boolean first()
    {
        return pageNumber == Integer.parseInt(FIRST_PAGE_NUM);
    }

    @JsonProperty
    public boolean last()
    {
        return (pageNumber + 1) * pageSize >= totalElement;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }
}
