package com.lifeshs.service.terminal;

import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.page.PaginationDTO;

import java.util.List;

/**
 * Created by XuZhanSi on 2017/2/13 0013.
 */
public interface IWebIndexService {
    /**
     * 加载资讯栏目
     * @param parentColumnName 对应父栏目
     * @return
     */
    List<TInfomationColumn> loadInformationColumns(String parentColumnName);

    /**
     * 加载资讯列表信息
     * @param col 栏目id
     * @param page 当前页数
     * @param max 最大条目数
     * @return
     */
    PaginationDTO getInformationsByPage(Integer col, Integer page, Integer max);
}
