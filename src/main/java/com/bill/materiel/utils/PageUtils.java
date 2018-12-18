package com.bill.materiel.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * PageUtils
 *
 * @author Bill
 * @date 2018/12/18 0018
 */
public class PageUtils {
    /**
     * 分页升序
     */
    public static Pageable pageDataAsc(Integer page, Integer rows, String... sortField) {
        Sort sort = new Sort(Sort.Direction.ASC, sortField);
        return PageRequest.of(page, rows, sort);
    }

    /**
     * 分页降序
     */
    public static Pageable pageDataDesc(Integer page, Integer rows, String... sortField) {
        Sort sort = new Sort(Sort.Direction.DESC, sortField);
        return PageRequest.of(page, rows, sort);
    }
}
