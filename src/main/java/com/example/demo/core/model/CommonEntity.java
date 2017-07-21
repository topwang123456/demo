package com.example.demo.core.model;

import java.io.Serializable;

/**
 * 共通Model
 */
public class CommonEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    // 分页处理用
    protected Integer page;
    protected Integer rows;
    protected String sort;
    protected String order;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
