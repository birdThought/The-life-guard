package com.lifeshs.vo.order;

import com.lifeshs.po.order.OrderPO;
import com.lifeshs.po.user.UserPO;

public class StatementOrderVO extends OrderPO {

    /** 用户信息 */
    private UserPO user;

    public UserPO getUser() {
        return user;
    }

    public void setUser(UserPO user) {
        this.user = user;
    }
}
