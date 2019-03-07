package com.lifeshs.dao1.systemManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.vo.systemManage.AppVersionVo;

/**
 * app版本Dao
 * 
 * @author shiqiang.zeng
 * @date 2018.1.30 14:39
 *
 */
@Repository("appVersionDao")
public interface AppVersionDao {

	/**
	 * 获取app版本列表
	 * 
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<AppVersionVo> findAppVersion(@Param("startRow") int startRow, @Param("pageSize") int pageSize);

	/** 统计app版本数量 */
	int countVersion();

	/**
	 * 添加
	 * 
	 * @param appVersionVo
	 */
	void addVersion(AppVersionVo appVersionVo);

	/**
	 * 更改
	 * 
	 * @param appVersionVo
	 */
	void updateVersion(AppVersionVo appVersionVo);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void deleteVersion(@Param("id") Integer id);

}
