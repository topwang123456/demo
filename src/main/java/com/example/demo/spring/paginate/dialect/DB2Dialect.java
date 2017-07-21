package com.example.demo.spring.paginate.dialect;

import org.apache.ibatis.mapping.MappedStatement;

import com.example.demo.spring.paginate.PageBounds;


/**
 * DB2Dialect
 * 
 * @author Toppy
 */
public class DB2Dialect extends Dialect {

    public DB2Dialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
        super(mappedStatement, parameterObject, pageBounds);
    }

    private static String getRowNumber(String sql) {
        StringBuilder rownumber = new StringBuilder(50).append("ROWNUMBER() OVER(");

        int orderByIndex = sql.toLowerCase().indexOf("ORDER BY");

        if (orderByIndex > 0 && !hasDistinct(sql)) {
            rownumber.append(sql.substring(orderByIndex));
        }

        rownumber.append(") AS ROWNUMBER_,");

        return rownumber.toString();
    }

    private static boolean hasDistinct(String sql) {
        return sql.toLowerCase().indexOf("SELECT DISTINCT") >= 0;
    }

    protected String getLimitString(String sql, int offset, int limit) {
        int startOfSelect = sql.toLowerCase().indexOf("SELECT");

        StringBuilder pagingSelect = new StringBuilder(sql.length() + 100).append(sql.substring(0, startOfSelect)) // add
                                                                                                                   // the
                                                                                                                   // comment
                .append("SELECT * FROM ( SELECT ") // nest the main query in an outer select
                .append(getRowNumber(sql)); // add the rownnumber bit into the outer query select list

        if (hasDistinct(sql)) {
            pagingSelect.append(" ROW_.* FROM ( ") // add another (inner) nested select
                    .append(sql.substring(startOfSelect)) // add the main query
                    .append(" ) AS ROW_"); // close off the inner nested select
        } else {
            pagingSelect.append(sql.substring(startOfSelect + 6)); // add the main query
        }

        pagingSelect.append(" ) AS TEMP_ WHERE ROWNUMBER_ ");

        // add the restriction to the outer select
        if (offset > 0) {
            pagingSelect.append(" BETWEEN ").append(offset + 1).append(" AND ").append(offset + limit);
        } else {
            pagingSelect.append(" <= ").append(limit);
        }

        return pagingSelect.toString();
    }
}
