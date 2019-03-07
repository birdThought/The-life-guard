package com.lifeshs.dao1.transfer;


import com.lifeshs.po.transfer.TransferCleaning;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @create 2018-01-18
 * 11:02
 * @desc
 */
@Repository(value = "transferCleaningDao")
public interface TransferCleaningDao {
    /**
     * 添加
     * @param transferCleaning
     * @return
     */
    int saveTransferOrder(TransferCleaning transferCleaning);

    /**
     * 查询
     * @param id
     * @return
     */
    TransferCleaning findByTransferId(@Param("id") Integer id);

    TransferCleaning findByOrderNO(@Param("oderNo") String oderNo);
}
