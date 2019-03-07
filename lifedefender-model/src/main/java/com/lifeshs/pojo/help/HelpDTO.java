package com.lifeshs.pojo.help;/**
 * Created by Administrator on 2017/4/27.
 */

import com.lifeshs.entity.consult.TInfomationColumn;
import com.lifeshs.entity.consult.TInformation;

import java.util.List;

/**
 * 帮助中心DTO
 *
 * @author wenxian.cai
 * @create 2017-04-27 17:50
 **/

public class HelpDTO {

    /** 帮助中心栏目集合 */
    private  List<TInfomationColumn> helpColumns;

    /** 帮助中心-详细内容集合*/
    private List<TInformation> helps;

    @Override
    public String toString() {
        return "HelpDTO{" +
                "helpColumns=" + helpColumns +
                ", helps=" + helps +
                '}';
    }

    public List<TInfomationColumn> getHelpColumns() {
        return helpColumns;
    }

    public void setHelpColumns(List<TInfomationColumn> helpColumns) {
        this.helpColumns = helpColumns;
    }

    public List<TInformation> getHelps() {
        return helps;
    }

    public void setHelps(List<TInformation> helps) {
        this.helps = helps;
    }
}
