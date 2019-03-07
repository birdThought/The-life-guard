package com.lifeshs.service1.page;

import java.util.ArrayList;
import java.util.List;

import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;

/**
 * 分页
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年6月27日 下午2:40:50
 * @param <T>
 */
public class Pagination<T> {

    /** 分页接口，需要手动具体实现 */
    private InterfaceP<T> p;

    public Pagination(InterfaceP<T> p) {
        this.p = p;
    }

    public PaginationDTO<T> getPagination(int totalSize, int curPage, int pageSize) {
        PaginationDTO<T> paginationDTO = new PaginationDTO<>();
        List<T> data = new ArrayList<>();

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(curPage, pageSize, totalSize);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        paginationDTO.setTotalPage(totalPage);
        paginationDTO.setNowPage(curPage);
        if (PaginationDTO.isDataOverFlow(curPage, pageSize, totalSize)) {
            paginationDTO.setData(data);
            return paginationDTO;
        }

        curPage = queryPageData.getCurPage();

        // 调用接口，具体返回参数查看接口的实现
        data = p.getData(startIndex);

        paginationDTO.setData(data);
        paginationDTO.setNowPage(curPage);
        paginationDTO.setTotalPage(totalPage);
        paginationDTO.setTotalSize(totalSize);

        return paginationDTO;
    }
}