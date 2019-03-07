package com.lifeshs.dto.manager;

/**
 * 包含分页和搜索的提交数据
 * Created by dengfeng on 2017/7/3 0003.
 */
public class UpSearchPagingData extends UpPagingData {
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
