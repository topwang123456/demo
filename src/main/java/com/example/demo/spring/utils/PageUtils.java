package com.example.demo.spring.utils;

import org.apache.commons.lang.StringUtils;

import com.example.demo.core.model.CommonEntity;
import com.example.demo.spring.paginate.PageBounds;
import com.example.demo.spring.paginate.model.Order;

public class PageUtils {
    private static final int PAGE_SIZE = 30;

    /**
     * 自动生成Page分页信息
     * 
     * @param obj
     * @return
     */
    public static PageBounds getPageBounds(Object obj) {
        return getPageBounds(obj, StringUtils.EMPTY);
    }

    /**
     * 自动生成Page分页信息(根据ID排序)
     * 
     * @param obj
     * @return
     */
    public static PageBounds getPageBoundsOrderID(Object obj) {
        return getPageBounds(obj, "ID.DESC");
    }

    /**
     * 自动生成Page分页信息
     * 
     * @param obj
     * @return
     */
    public static PageBounds getPageBounds(Object obj, String defaultOrder) {
        if (obj instanceof CommonEntity) {
            CommonEntity param = (CommonEntity) obj;
            Integer pageStart = param.getPage();
            Integer pageSize = param.getRows();
            String sort = param.getSort();
            String order = param.getOrder();
            if (pageStart == null) {
                pageStart = 0;
            }
            if (pageSize == null) {
                pageSize = PAGE_SIZE;
            }
            String pageOrder = StringUtils.EMPTY;
            if (StringUtils.isNotEmpty(order) && StringUtils.isNotEmpty(sort)) {
                String[] sorts = sort.split(",");
                String[] orders = order.split(",");
                for (int i = 0; i < sorts.length; i++) {
                    if (StringUtils.isNotEmpty(orders[i]) && StringUtils.isNotEmpty(sorts[i])) {
                        pageOrder += sorts[i] + "." + orders[i] + ",";
                    }
                }
                pageOrder = StringUtils.substringBeforeLast(pageOrder, ",");
            } else {
                if (StringUtils.isNotEmpty(defaultOrder)) {
                    pageOrder = defaultOrder;
                }
            }
            return new PageBounds(pageStart, pageSize, Order.formString(pageOrder));
        }
        return null;
    }


    /**
     * 生成取得Limit条数用条件
     * 
     * @param offset
     * @param order
     * @return
     */
    public static PageBounds getPageBounds4Limit(int offset, String order) {
        return new PageBounds(0, offset, Order.formString(order), false);
    }

    /**
     * 增加排序字段(排序字段后)
     * 
     * @param obj
     * @param sort
     */
    public static void addPageSortAfter(Object obj, String sort) {
        addPageSort(obj, sort, false);
    }

    /**
     * 增加排序字段（前置）
     * 
     * @param obj
     * @param sort
     */
    public static void addPageSortBefore(Object obj, String sort) {
        addPageSort(obj, sort, true);
    }

    /**
     * 增加排序字段
     * 
     * @param obj
     * @param sort
     * @param isBefore
     */
    public static void addPageSort(Object obj, String sort, boolean isBefore) {
        if (obj instanceof CommonEntity) {
            CommonEntity param = (CommonEntity) obj;
            String[] sorts = sort.split(".");
            if (StringUtils.isEmpty(param.getOrder())) {
                param.setOrder(sorts[0]);
                param.setSort(sorts[1]);
            } else {
                if (isBefore) {
                    param.setOrder(sorts[0] + "," + param.getOrder());
                    param.setSort(sorts[1] + "," + param.getSort());
                } else {
                    param.setOrder(param.getOrder() + "," + sorts[0]);
                    param.setSort(param.getSort() + "," + sorts[1]);
                }
            }
        }
    }
}
