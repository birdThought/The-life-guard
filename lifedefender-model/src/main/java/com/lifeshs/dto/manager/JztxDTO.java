package com.lifeshs.dto.manager;

/**
 * @author Administrator
 * @create 2018-01-29
 * 15:18
 * @desc
 */
public class JztxDTO {

    private String OrderNO;
    private String SFZ;
    private String Name;
    private String JzyInfo;

    public void setOrderNO(String orderNO) {
        OrderNO = orderNO;
    }

    public void setSFZ(String SFZ) {
        this.SFZ = SFZ;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setJzyInfo(String jzyInfo) {
        JzyInfo = jzyInfo;
    }

    public String getOrderNO() {
        return OrderNO;
    }

    public String getSFZ() {
        return SFZ;
    }

    public String getName() {
        return Name;
    }

    public String getJzyInfo() {
        return JzyInfo;
    }
}
