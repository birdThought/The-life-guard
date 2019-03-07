package com.lifeshs.vo.vip;

import java.util.List;

import com.lifeshs.po.vip.VipComboItemPO;
import com.lifeshs.po.vip.VipComboPO;

/**
 *  vip套餐
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月18日 下午2:31:27
 */
public class VipComboVO extends VipComboPO {

    /** 套餐项目列表 */
    private List<VipComboItemPO> itemList;

    public List<VipComboItemPO> getItemList() {
        return itemList;
    }

    public void setItemList(List<VipComboItemPO> itemList) {
        this.itemList = itemList;
    }
}
