package com.lifeshs.service.information;

import java.util.List;

import javax.annotation.Nullable;

import com.lifeshs.common.model.DataResult;
import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.information.InformationCollectDTO;
import com.lifeshs.pojo.information.InformationDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service1.page.Paging;

/**
 * Created by XuZhanSi on 2017/1/10 0010.
 */
public interface InformationService {

    /**
     * 加载健康资讯信息数据
     * @param columnName
     * @param fenlei
     * @param page
     * @param search
     * @param 页面大小
     * @return
     */
    DataResult loadNewsInformationDatas(String columnName,@Nullable Integer fenlei, @Nullable Integer page, @Nullable String search, @Nullable Integer max);

    /**
     *  获取资讯信息
     *  <p>columnId如果是父类栏目，会继续往下查找咨询
     *  @author yuhang.weng 
     *  @DateTime 2017年12月6日 上午9:43:28
     *
     *  @param curPage
     *  @param pageSize
     *  @param columnId
     *  @return
     */
    Paging<TInformation> listInformation(int curPage, int pageSize, int columnId);
    
    /**
     * 加载帮助中心资讯数据
     * @param columnName
     * @param fenlei
     * @param page
     * @param search
     * @return
     */
    DataResult loadHelpInformationDatas(String columnName,@Nullable Integer fenlei, @Nullable Integer page, @Nullable String search);

    /**
     * 访客看资讯业务入口
     * @param informationId
     * @return
     */
    DataResult lookNewsById(Integer informationId);

    /**
     * 根据父栏目名称找到该栏目下的子栏目
     * @param parentName
     * @return
     */
    List<TInfomationColumn> loadColumnByParentColName(String parentName);



    /**
     * 根据栏目id加载资讯
     * @param columnId
     * @return
     */
    List<TInformation> getInformationsByColumnId(Integer columnId);

    /**
     * @Description: 添加资讯收藏信息
     * @author: wenxian.cai
     * @create: 2017/5/2 10:26
     */
    boolean addInformationCollect(InformationCollectDTO collect);

    /**
     * @Description: 删除资讯收藏信息（支持批量删除）
     * @author: wenxian.cai
     * @create: 2017/5/2 10:37
     * @param ids：资讯Id集合
     */
    boolean deleteInformationCollect(List ids, int userId);

    /**
     * @Description: 根据具体条件获取资讯收藏信息
     * @author: wenxian.cai
     * @create: 2017/5/2 10:54
     */
    List<InformationCollectDTO> listInformationCollects(Integer userId);

    /**
     * @Description: 获取用户已收藏的资讯列表
     * @author: wenxian.cai
     * @create: 2017/5/2 15:19
     */
    PaginationDTO<InformationDTO> listInformationByCollect(Integer userId, int pageIndex, int pageSize);

    /**
     * @Description: 判断资讯是否已被用户收藏
     * @author: wenxian.cai
     * @create: 2017/5/9 17:46
     */
    boolean isCollectedOfInformation(int id, int userId);

}
