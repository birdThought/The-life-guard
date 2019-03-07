package com.lifeshs.dao.shop.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.shop.PictureDTO;

@Repository("shop_picture_dao")
public interface PictureDao {

	int batchInsertPictures(@Param("pictures") List<PictureDTO> pictures);
	
	int removeByGid(@Param("gid") Integer gid);
}
