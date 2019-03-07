package com.lifeshs.service.member;

import com.lifeshs.entity.huanxin.TUnregistHx;
import com.lifeshs.pojo.huanxin.HuanxinUserVO;
import com.lifeshs.service1.page.Paging;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-01-31
 * 14:36
 * @desc
 */
public interface TUnregistHxService {
    Paging<TUnregistHx> findByListDate(int page, int pageSize);

    List<HuanxinUserVO> findByAll();
}
