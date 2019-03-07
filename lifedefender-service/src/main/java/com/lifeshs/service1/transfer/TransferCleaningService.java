package com.lifeshs.service1.transfer;

import com.lifeshs.po.transfer.TransferCleaning;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 * @create 2018-01-18
 * 11:22
 * @desc
 */
public interface TransferCleaningService {




    TransferCleaning findByTransferId(Integer id);

    TransferCleaning finfByOrderNo(String oderNo);

    /**
     *
     * @param price 总价格
     * @param customername
     * @param customermobile
     * @param workdistrict
     * @param workaddress
     * @param details 其他说明
     * @param yong 需求时间
     * @param yonggongshichang  服务小时
     * @param area 面积
     * @param gender
     * @param language 语言
     * @param pulation 人口
     * @return
     */
    int saveTransferleaning(BigDecimal totalprice, String customername, String customermobile, String workdistrict, String workaddress, String details,String yoxiqi, String yong, Integer yonggongshichang, String area, Integer gender, Integer language, Integer pulation);


    int saveTransferOrder();

}
