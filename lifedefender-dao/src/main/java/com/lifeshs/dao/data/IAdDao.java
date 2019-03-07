package com.lifeshs.dao.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.common.constants.common.AdType;
import com.lifeshs.pojo.data.AdDTO;

@Repository(value = "adDao")
public interface IAdDao {

    /**
     *  获取广告
     *  @author yuhang.weng 
     *	@DateTime 2017年4月12日 上午10:49:11
     *
     *  @param type
     *  @param limit
     *  @return
     */
    List<AdDTO> listAd(@Param("type") AdType type, @Param("limit") int limit);
}
