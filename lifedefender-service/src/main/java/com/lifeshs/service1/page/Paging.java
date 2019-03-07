package com.lifeshs.service1.page;

import java.util.ArrayList;
import java.util.List;

import com.lifeshs.pojo.page.PaginationDTO;

/**
 * 处理分页的查询和返回封装
 * Created by dengfeng on 2017/7/8 0008.
 * com.lifeshs.service1.page.Paging<T>
 */
public class Paging<T> {
    private int pageIndex;  //要查的当前页
    private int pageSize;   //每页行数
    private int startRow;   //要查的当前行数
    private IPagingQueryProc<T> queryProc;  //执行查询总条数的接口

    public Paging(int pageIndex, int pageSize){
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.startRow = (pageIndex - 1) * pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setQueryProc(IPagingQueryProc<T> queryProc) {
        this.queryProc = queryProc;
    }

    /**
     * 返回分页数据，不需要查询总条数和总页数
     * @return
     */
    public List<T> getData(){
        return queryProc.queryData(startRow, pageSize);
    }

    /**
     * 返回分页数据，包含总条数和总页数
     * @return
     */
    public PaginationDTO<T> getPagination() {
        PaginationDTO<T> paginationDTO = new PaginationDTO<>();
        paginationDTO.setNowPage(pageIndex);
        paginationDTO.setTotalSize(queryProc.queryTotal());
        List<T> data = new ArrayList<>();
        if(paginationDTO.getTotalSize() == 0){
            paginationDTO.setTotalPage(0);
            paginationDTO.setData(data);
            return paginationDTO;
        }
        //计算总页数
        int totalPage = paginationDTO.getTotalSize() / pageSize;
        if(paginationDTO.getTotalSize() % pageSize != 0)
            totalPage++;
        paginationDTO.setTotalPage(totalPage);
        //记录和页数没有越界，则进行查询
        if (startRow < paginationDTO.getTotalSize() && pageIndex <= totalPage) {
            data =getData();
        }
        paginationDTO.setData(data);
        return paginationDTO;
    }
}
