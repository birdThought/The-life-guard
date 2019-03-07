package com.lifeshs.common.constants.app.notice;

public enum ActivityMemberEnum {

    MEASURE_WARNING(2, 1, "com.example.tzdq.lifeshsmanager.view.activity.MemDetailActivity", "userId:$1,date:$2",
            "MemberInfomationVC", "id:$1,measureTime:$2","用户测量数据异常通知"),
    //REFUND_USER(1, 2, "B", "orderId:$","订单退款通知"),   //用户的暂时用TEXT方式，因为APP的订单详情有多个，短时间处理不好
    REFUND_EMPLOYEE(2, 2, "com.example.tzdq.lifeshsmanager.view.activity.RefundProcessorActivity", "orderId:$1",
            "RefundVC", "orderId:$1", "订单退款通知"),
    REPLY_START(1, 2, "com.mgc.lifeguardian.business.mine.view.MineActivity", "fragmentKey:com.mgc.lifeguardian.business.mine.view.InspectionReportFragment",
            "CheckReportVC", null, "测量分析订单通知"),
    REPLY_END(1, 2, "com.mgc.lifeguardian.business.mine.view.MineActivity", "fragmentKey:com.mgc.lifeguardian.business.mine.view.InspectionReportFragment",
            "CheckReportVC", null, "测量分析结果报告通知"),
    SERVE_WILL_END_USER(1, 1, "com.mgc.lifeguardian.business.order.view.OrderMainActivity", "fragmentKey:com.mgc.lifeguardian.business.order.view.Order_Serve_Advisory_DetailFragment,clickText:立即续费,orderId:$1",
            "ConsultOrderDetail", "orderId:$1,clickText:立即续费", "服务即将到期通知"),
    SERVE_END_USER(1, 1, "com.mgc.lifeguardian.business.service.serviceview.ServiceMainActivity", "fragmentKey:com.mgc.lifeguardian.business.service.serviceview.HealthConsultDetailFragment,clickText:再次使用,id:$1",
            "ConsultOrderDetail", "orderId:$1", "服务到期通知"),  //id是t_project_orguser_relation.id
    RECOMMEND_EMPLOYEE(1, 1, "com.mgc.lifeguardian.business.service.serviceview.ServiceMainActivity", "fragmentKey:com.mgc.lifeguardian.business.service.serviceview.ServiceEngineerDetailFragment,type:51,id:$1",
            "DoctorDetailVC", "userId:$1", "系统消息"),  //平台向用户推广服务师
    WILL_FAIL_EMPLOYEE(2, 1, "com.example.tzdq.lifeshsmanager.view.activity.SingleChatActivity", "clickText:立即沟通,userId:$1,name:$2",
            "SendMsgVC", "clickText:立即沟通,userId:$1,name:$2", "用户服务即将到期通知"),  //提醒服务师
    EMPLOYEE_FINISH_ORDER(2, 3, "com.example.tzdq.lifeshsmanager.view.activity.ServiceDetailActivity", "orderId:$1",
            "ServiceDetailVC", "orderId:$1", "门店提醒"),  //门店管理员提醒服务师完成订单
    ORDER_BUY_EMPLOYEE(2, 2, "com.example.tzdq.lifeshsmanager.view.activity.ServiceDetailActivity", "orderId:$1",
            "ServiceDetailVC", "orderId:$1", "订单购买成功");   //用户购买服务后通知服务师

    //用户类型(发送给谁)  1_普通用户，2_门店服务师
    private int userType;
    //消息类型  1_系统消息，2_服务消息，3_门店推送，4_服务师推送，5_订单消息
    private int messageType;
    //打开目标(android),activity名称（含完整包名）
    private String openTarget;
    //附加消息(android),格式：key1:value1,key2:value2
    private String openAttach;
    //打开目标(IOS),页面名称
    private String openTargetIOS;
    //附加消息(IOS),格式：key1:value1,key2:value2
    private String openAttachIOS;
    //消息标题
    private String title;
    
    private ActivityMemberEnum(int userType, int messageType, String openTarget, String openAttach, String openTargetIOS, String openAttachIOS, String title){
        this.userType = userType;
        this.messageType = messageType;
        this.openTarget = openTarget;
        this.openAttach = openAttach;
        this.openTargetIOS = openTargetIOS;
        this.openAttachIOS = openAttachIOS;
        this.title = title;
    }
    
    public int userType(){
        return this.userType;
    }

    public int messageType(){
        return this.messageType;
    }

    public String openTarget(){
        return this.openTarget;
    }

    public String openTargetIOS(){
        return this.openTargetIOS;
    }

    public String openAttach(){
        return this.openAttach;
    }

    public String openAttachIOS(){
        return this.openAttachIOS;
    }

    public String title(){
        return this.title;
    }
}
