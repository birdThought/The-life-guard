package com.lifeshs.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lifeshs.service.shop.CommodityService;
import com.lifeshs.shop.PageRecommendGoodsDTO;

/**
 * 首页推荐分类
 * @author liu
 * @时间 2019年1月2日 上午9:39:19
 * @说明
 */
@Component
public class RecommendGoods {
	
	public static final int limitSize = 2; // 默认取2种类目商品
	
	private static final int days = 1;
	
	@Autowired
	private CommodityService commodityService;
	
    @Autowired
    private TaskPoolService taskService;
    
	@Scheduled(cron = "0 0/5 * * * ?")
	public void execute() {
		// 把需要做的事情添加到任务池中
        taskService.put(new Runnable() {
            
            @Override
            public void run() {
				int passNum = 0;
				long curTime = System.currentTimeMillis();
				List<PageRecommendGoodsDTO> desc = commodityService.getRecommendCategory("DESC", limitSize);
				int maxSort = ((Integer)desc.get(0).getSort()).intValue();
				for(int i = desc.size() - 1; i >= 0; i--) {
					PageRecommendGoodsDTO recommend = desc.get(i);
					Date endTime = (Date)recommend.getEndTime();
					if(endTime == null || endTime.getTime() <= curTime) {
						passNum++;
						Integer id = (Integer)recommend.getId();
						commodityService.moveSortOrSetTime(id, ++maxSort, null);
					}
				}
				if(passNum == 0) {
					return;
				}
				else if(passNum > 0 && passNum < limitSize) {
					for(int i = desc.size() - 1; i >= 0; i--) {
						PageRecommendGoodsDTO recommend = desc.get(i);
						Date endTime = (Date)recommend.getEndTime();
						if(endTime.getTime() > curTime) {
							Integer id = (Integer)recommend.getId();
							commodityService.moveSortOrSetTime(id, ++maxSort, null);
						}
					}
					
				}
				List<PageRecommendGoodsDTO> asc = commodityService.getRecommendCategory("ASC", passNum);
				for(int i = 0; i < asc.size(); i++) {
					PageRecommendGoodsDTO recommend = asc.get(i);
					Integer id = (Integer)recommend.getId();
					Calendar endTime = Calendar.getInstance();
					endTime.setTime(new Date());
					endTime.add(Calendar.DATE, days);
					commodityService.moveSortOrSetTime(id, ++maxSort, endTime.getTime());
				}
            }
        });
	}
}
