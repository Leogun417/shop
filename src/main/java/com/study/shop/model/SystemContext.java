package com.study.shop.model;

/**
 * Created by 傲然 on 2017/1/31.
 */
public class SystemContext {
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
    private static ThreadLocal<Integer> index = new ThreadLocal<Integer>();
    private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
    private static ThreadLocal<String> order = new ThreadLocal<String>();//升序或降序
    private static ThreadLocal<String> sortBy = new ThreadLocal<String>();//排序依据


    public static int getPageSize() {
        return pageSize.get();
    }

    public static void setPageSize(int _pageSize) {
        pageSize.set(_pageSize);
    }

    public static void removePageSize() {
        pageSize.remove();
    }

    public static int getIndex() {
        return index.get();
    }

    public static void setIndex(int _index) {
        index.set(_index);
    }

    public static void removeIndex() {
        index.remove();
    }

    public static int getOffset() {
        return offset.get();
    }

    public static void setOffset(int _offset) {
        offset.set(_offset);
    }

    public static void removeOffset() {
        offset.remove();
    }

    public static String getOrder() {
        return order.get();
    }

    public static void setOrder(String _order) {
        order.set(_order);
    }

    public static void removeOrder() {
        order.remove();
    }

    public static String getSortBy() {
        return sortBy.get();
    }

    public static void setSortBy(String _sortBy) {
        sortBy.set(_sortBy);
    }

    public static void removeSortBy() {
        sortBy.remove();
    }
}
