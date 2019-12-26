package com.gzl.next.document.util;

import com.github.pagehelper.PageInfo;
import lombok.Getter;

import java.util.List;

/**
 * @author GaoZhilai
 * @date 19/12/7 19:02
 * No Description
 */
@Getter
public class PageData<T> {
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 每页的数量
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 是否为第一页
     */
    private boolean isFirstPage;
    /**
     * 是否为最后一页
     */
    private boolean isLastPage;

    private List<T> pageData;

    private PageData() {

    }

    public static<T> PageData<T> converPageData(List<T> list) {
        PageData<T> pageData = new PageData<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        pageData.pageNum = pageInfo.getPageNum();
        pageData.pageSize = pageInfo.getPageSize();
        pageData.pages = pageInfo.getPages();
        pageData.isFirstPage = pageInfo.isIsFirstPage();
        pageData.isLastPage = pageInfo.isIsLastPage();
        pageData.pageData = list;
        return pageData;
    }

    public static<T, R> PageData<R> converPageData(List<T> list, List<R> listVO) {
        PageData<R> pageData = new PageData<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        pageData.pageNum = pageInfo.getPageNum();
        pageData.pageSize = pageInfo.getPageSize();
        pageData.pages = pageInfo.getPages();
        pageData.isFirstPage = pageInfo.isIsFirstPage();
        pageData.isLastPage = pageInfo.isIsLastPage();
        pageData.pageData = listVO;
        return pageData;
    }
}
