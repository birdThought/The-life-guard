package com.lifeshs.dao1.member;

import com.lifeshs.entity.huanxin.TUnregistHx;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Administrator
 * @create 2018-01-31
 * 14:27
 * @desc
 */
@Repository("tUnregistHxDao")
public interface TunregistHxDao {

    List<TUnregistHx> findByList(@Param("curPage")Integer curPage, @Param("pageSize")Integer pageSize);

    int findBySum();

    List<TUnregistHx> findByAll();
}
