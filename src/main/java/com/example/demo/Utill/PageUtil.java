package com.example.demo.Utill;

import com.example.demo.DTO.Request.PagingRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class PageUtil {
    public static <T> PageRequest getPageRequest(PagingRequest<T> requestPaging) {
        return StringUtils.isEmpty(requestPaging.getSortField())
                ? PageRequest.of(requestPaging.getPage() - 1, requestPaging.getSize()) //defaultPage:0
                : PageRequest.of(requestPaging.getPage() - 1, requestPaging.getSize(),
                Sort.by(requestPaging.getSortBy(), new String[]{requestPaging.getSortField()}));
    }
}
