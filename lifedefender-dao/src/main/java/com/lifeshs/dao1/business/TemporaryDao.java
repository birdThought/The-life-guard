package com.lifeshs.dao1.business;

import com.lifeshs.vo.business.TemporaryDataVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-03-15
 * 15:03
 * @desc
 */
@Repository("temporaryDao")
public interface TemporaryDao {

    List<TemporaryDataVO> findByAllData(@Param("id") Integer id,@Param("type")Integer type,@Param("superior") Integer superior,@Param("name") String name,@Param("date")String date,@Param("bname") String bname,
                                        @Param("curPage")Integer curPage,@Param("pageSize")int pageSize);


    int findByInteger(@Param("id") Integer id,@Param("type")Integer type,@Param("superior") Integer superior,@Param("name") String name,@Param("date")String date,@Param("bname") String bname);
}
