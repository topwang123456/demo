package com.example.demo.spring.paginate.dialect;

import org.apache.ibatis.mapping.MappedStatement;

import com.example.demo.spring.paginate.PageBounds;


/**
 * Oracle Dialect
 * 
 */
public class OracleDialect extends Dialect {

    public OracleDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
        super(mappedStatement, parameterObject, pageBounds);
    }

    protected String getLimitString(String sql, int offset, int limit) {
        sql = sql.trim();
        boolean isForUpdate = false;
        if (sql.toLowerCase().endsWith(" FOR UPDATE")) {
            sql = sql.substring(0, sql.length() - 11);
            isForUpdate = true;
        }

        StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);
        if (offset > 0) {
            pagingSelect.append("SELECT * FROM ( SELECT ROW_.*, ROWNUM ROWNUM_ FROM ( ");
        } else {
            pagingSelect.append("SELECT * FROM ( ");
        }
        pagingSelect.append(sql);
        if (offset > 0) {
            pagingSelect.append(" ) ROW_ ) WHERE ROWNUM_ <= ").append(offset + limit).append(" AND ROWNUM_ > ")
                    .append(offset);
        } else {
            pagingSelect.append(" ) WHERE ROWNUM <= ").append(limit);
        }

        if (isForUpdate) {
            pagingSelect.append(" FOR UPDATE");
        }

        return pagingSelect.toString();
    }

}
