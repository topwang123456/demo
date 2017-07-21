package com.example.demo.spring.paginate.dialect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;

import com.example.demo.spring.paginate.PageBounds;
import com.example.demo.spring.paginate.model.Order;



/**
 * 类似hibernate的Dialect,但只精简出分页部分
 * 
 * @author Toppy
 */
public class Dialect {
    protected MappedStatement mappedStatement;
    protected PageBounds pageBounds;
    protected Object parameterObject;
    protected BoundSql boundSql;
    protected Map<String, Object> pageParameters = new HashMap<String, Object>();

    private String pageSQL;
    private String countSQL;

    public Dialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
        this.mappedStatement = mappedStatement;
        this.parameterObject = parameterObject;
        this.pageBounds = pageBounds;

        init();
    }

    protected void init() {
        boundSql = mappedStatement.getBoundSql(parameterObject);
        StringBuffer bufferSql = new StringBuffer(boundSql.getSql().trim());
        if (bufferSql.lastIndexOf(";") == bufferSql.length() - 1) {
            bufferSql.deleteCharAt(bufferSql.length() - 1);
        }
        String sql = bufferSql.toString();
        pageSQL = sql;
        if (pageBounds.getOrders() != null && !pageBounds.getOrders().isEmpty()) {
            pageSQL = getSortString(sql, pageBounds.getOrders());
        }
        if (pageBounds.getOffset() != RowBounds.NO_ROW_OFFSET || pageBounds.getLimit() != RowBounds.NO_ROW_LIMIT) {
            pageSQL = getLimitString(pageSQL, pageBounds.getOffset(), pageBounds.getLimit());
        }

        countSQL = getCountString(sql);
    }

    /**
     * 获取分页SQL文
     * 
     * @return
     */
    public String getPageSQL() {
        return pageSQL;
    }

    /**
     * 获取数据总数SQL文
     * 
     * @return
     */
    public String getCountSQL() {
        return countSQL;
    }

    /**
     * 将sql变成分页sql语句
     */
    protected String getLimitString(String sql, int offset, int limit) {
        throw new UnsupportedOperationException("paged queries not supported");
    }

    /**
     * 将sql转换为总记录数SQL
     * 
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    protected String getCountString(String sql) {
        return "SELECT COUNT(1) FROM (" + sql + ") TMP_COUNT";
    }

    /**
     * 将sql转换为带排序的SQL
     * 
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    protected String getSortString(String sql, List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return sql;
        }

        StringBuffer buffer = new StringBuffer("SELECT * FROM (").append(sql).append(") TEMP_ORDER ORDER BY ");
        for (Order order : orders) {
            if (order != null) {
                buffer.append(order.toString()).append(", ");
            }

        }
        buffer.delete(buffer.length() - 2, buffer.length());
        return buffer.toString();
    }
}
