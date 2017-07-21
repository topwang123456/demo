package com.example.demo.spring.paginate.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * 包含“分页”信息的List
 * 
 * <p>要得到总页数请使用 toPaginator().getTotalPages();</p>
 * 
 * @author badqiu
 * @author miemiedev
 */
public class PageList<E> extends ArrayList<E> {
    private static final long serialVersionUID = 1412759446332294208L;

    private Paginator paginator;

    public PageList() {
    }

    public PageList(Collection<? extends E> c) {
        super(c);
        if (c != null && c.size() > 0) {
            this.paginator = new Paginator(0, 0, c.size());
        } else {
            this.paginator = new Paginator(0, 0, 0);
        }
    }

    public PageList(Collection<? extends E> c, Paginator p) {
        super(c);
        this.paginator = p;
    }

    public PageList(Paginator p) {
        this.paginator = p;
    }

    /**
     * 得到分页器，通过Paginator可以得到总页数等值
     * @return
     */
    public Paginator getPaginator() {
        return paginator;
    }

}