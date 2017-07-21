package com.example.demo.spring.paginate.dialect;

import org.apache.ibatis.mapping.MappedStatement;

import com.example.demo.spring.paginate.PageBounds;




/**
 * Mysql Dialect
 * 
 * @author Toppy
 */
public class MySQLDialect extends Dialect {

    public MySQLDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
        super(mappedStatement, parameterObject, pageBounds);
    }

    /**
     * 重新分页sql文
     */
    protected String getLimitString(String sql, int offset, int limit) {
        StringBuilder buffer = new StringBuilder(sql.length() + 20).append(sql);
        if (offset > 0) {
            buffer.append(" LIMIT ").append(offset).append(", ").append(limit);
        } else {
            buffer.append(" LIMIT ").append(limit);
        }
        return buffer.toString();
    }

}
