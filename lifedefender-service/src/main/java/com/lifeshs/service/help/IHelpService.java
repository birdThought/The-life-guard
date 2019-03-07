package com.lifeshs.service.help;

import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;
import com.lifeshs.pojo.help.HelpDTO;
import com.lifeshs.pojo.help.HelpDetailDto;

import java.util.List;

/**
 * @Author Yue.Li
 * @Date 2017/4/27.
 */
public interface IHelpService {
    /**
     * 获取帮助内容
     * @param id
     * @return
     */
    TInformation getHelpDetail(int id);

    /**
     * @Description: 获取帮助中心所有栏目、具体帮助文档的title
     * @author: wenxian.cai
     * @create: 2017/4/27 17:58
     */
    HelpDTO listHelp(String parentName);


    List<TInfomationColumn> getTest(String name);

}
