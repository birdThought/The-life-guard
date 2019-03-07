package com.lifeshs.dao1.store;


import com.lifeshs.po.StorePO;
import org.apache.ibatis.annotations.Param;

public interface IStoreDao {

    /**
     * 根据服务师获取门店
     * @param orgUserId
     * @return
     */
    StorePO findStoreByServices(@Param("orgUserId") int orgUserId);
}
