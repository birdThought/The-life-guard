package com.lifeshs.pojo.page;

import java.util.List;

/**
 * <p>
 * 分页数据传输类
 * 
 * @author yuhang.weng
 * @param <T>
 * @DateTime 2016年8月12日 下午2:37:11
 */
public class PaginationDTO<T> {

	/** 总页数 */
    private Integer totalPage;

    /** 当前页数 */
    private Integer nowPage;

    /** 总记录数 */
    private Integer totalSize;
    
    // 每页数
    private Integer pageSize;

    /** 数据 */
    private List<T> data;

    private List<T> dataObject;

    private Object obj;

    public PaginationDTO() {
    }

    public PaginationDTO(Integer nowPage, Integer max, Integer totalSize, List<T> dataObject) {
        this.nowPage = nowPage;
        this.totalSize = totalSize;
        this.totalPage = (int) (totalSize % max == 0 ? totalSize / max : Math.floor(totalSize / max) + 1);
        this.dataObject = dataObject;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }
    
	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

    public List<T> getDataObject() {
        return dataObject;
    }

    public void setDataObject(List<T> dataObject) {
        this.dataObject = dataObject;
    }

    /**
     * 获取分页查询数据(nowPage/startIndex/totalPage)
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月19日 下午7:51:06
     *
     * @param curPage
     * @param pageSize
     * @param totalSize
     * @return
     */
    public static QueryPageData getQueryPageData(int curPage, int pageSize, int totalSize) {
        QueryPageData queryPageData = new QueryPageData();

        int totalPage = 1;
        if (pageSize <= totalSize) {
            // 如果不能被整除，就数值+1
            totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : (int) Math.floor(totalSize / pageSize) + 1;
        }
        if (curPage <= 0)
            curPage = 1;
        if (curPage > totalPage)
            curPage = totalPage;
        int startIndex = (curPage - 1) * pageSize; // 开始下标

        queryPageData.setCurPage(curPage);
        queryPageData.setStartIndex(startIndex);
        queryPageData.setTotalPage(totalPage);
        return queryPageData;
    }

    public static Boolean isDataOverFlow(int curPage, int pageSize, int totalSize) {
        if (curPage <= 0) {
            return true;
        }
        if ((curPage - 1) * pageSize >= totalSize)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "PaginationDTO{" +
                "totalPage=" + totalPage +
                ", nowPage=" + nowPage +
                ", totalSize=" + totalSize +
                ", data=" + data +
                ", dataObject=" + dataObject +
                ", obj=" + obj +
                '}';
    }
}
