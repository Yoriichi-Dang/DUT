package com.laptrinhjavaWeb.paging;

import com.laptrinhjavaWeb.sort.Sorter;

public interface Pageable {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
}
