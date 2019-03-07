package com.lifeshs.dao.information;

import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.information.InformationCollectDTO;
import com.lifeshs.pojo.information.InformationDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by XuZhanSi on 2017/1/10 0010.
 */
@Repository
public interface IInformationDao {
    /**
     * 根据父栏目获取所有子栏目
     * @return
     */
    List<TInfomationColumn> loadColumnsByParentName(String name);

    /**
     *  查询付栏目下的子栏目信息
     *  @author yuhang.weng 
     *  @DateTime 2017年12月6日 上午9:56:47
     *
     *  @param parentColumnId 父栏目id
     *  @return
     */
    List<TInfomationColumn> findColumnByParentId(@Param("parentColumnId") int parentColumnId);
    
    /**
     * 加载资讯列表
     *
     * @param condition
     * @param startPage
     * @param maxLimit
     * @return
     */
    List<TInformation> loadInformationList(@Param("condition") Map<String, Object> condition, @Param(value = "startPage") int startPage, @Param(value = "maxLimit") int maxLimit);

    /**
     * @Description: 根据columId获取所有咨询题目
     * @author: wenxian.cai
     * @create: 2017/4/28 10:38
     */
    List<TInformation> listInformationName(@Param("columnIds") List columnId);

    /**
     * 获取资讯列表的数量
     *
     * @param condition
     * @return
     */
    int getCountFromInformation(@Param("condition") Map<String, Object> condition);

    /**
     * 获取点击量高的资讯
     * @param limit
     * @return
     */
    List<TInformation> findHotInformation(int limit);

    /**
     * 删除访客对应资讯的ip地址
     * @param newsId
     * @param ipList
     * @return
     */
    int deleteExistNewsVisitor(@Param(value = "newsId") int newsId, @Param(value = "ipList") List<String> ipList);

    /**
     * @Description: 添加资讯收藏信息
     * @author: wenxian.cai
     * @create: 2017/5/2 10:26
     */
    int addInformationCollect(InformationCollectDTO collect);

    /**
     * @Description: 删除资讯收藏信息（支持批量删除）
     * @author: wenxian.cai
     * @create: 2017/5/2 10:37
     * @param ids：资讯收藏Id集合
     */
    int deleteInformationCollect(@Param(value = "ids")List ids, @Param(value = "userId") Integer userId);

    /**
     * @Description: 根据具体条件获取资讯收藏信息
     * @author: wenxian.cai
     * @create: 2017/5/2 10:54
     */
    List<InformationCollectDTO> listInformationCollects(@Param(value = "userId") Integer userId);

    /**
     * @Description: 根据用户收藏信息获取资讯列表
     * @author: wenxian.cai
     * @create: 2017/5/2 15:16
     */
    List<InformationDTO> listInformationByCollect(@Param(value = "userId") Integer userId, @Param(value = "pageIndex") Integer pageIndex,
                                                  @Param(value = "pageSize") Integer pageSize);

    /**
     * @Description: 判断资讯是否已被用户收藏
     * @author: wenxian.cai
     * @create: 2017/5/9 18:00
     */
    int isCollectedOfInformation(@Param(value = "userId") Integer userId, @Param(value = "informationId") Integer informationId);
}
