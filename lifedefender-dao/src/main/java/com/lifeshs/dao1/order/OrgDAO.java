package com.lifeshs.dao1.order;

import com.lifeshs.po.OrgPO;

public interface OrgDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(OrgPO record);

    int insertSelective(OrgPO record);

    OrgPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrgPO record);

    int updateByPrimaryKeyWithBLOBs(OrgPO record);

    int updateByPrimaryKey(OrgPO record);
}