package com.lifeshs.dao1.data;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.pojo.data.MeasureSite;
import com.lifeshs.pojo.data.MeasureSiteOpeningTime;
@Repository("measureSiteDao1")
public interface MeasureSiteDao {

	MeasureSite selectSizeStatusById(@Param("orgId") int orgId);

	Integer addDataSize(MeasureSite measureSite);

	Integer addDataSizeOpeningTime(@Param("orgId")Integer orgId, @Param("startTime")String startTime, @Param("endTime")String endTime);

	Integer delDataSizeOpeningTime(@Param("orgId")Integer id);

	MeasureSiteOpeningTime selectSizeTimeById(@Param("SizeId")Integer id);

	Integer addDataSizeOpeningTime2(@Param("SiteId")Integer siteId, @Param("startTime") String startTime, @Param("endTime")String endTime);

	Integer updateDataSize(@Param("SiteId")Integer siteId,@Param("measureSite") MeasureSite measureSite);

	
}
