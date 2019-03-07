package com.lifeshs.pojo.client;

/**
 * 会员用户共享数据块
 * 
 * @author yuhang.weng
 * @DateTime 2016年12月9日 下午2:31:38
 */
public class MemberSharingData extends SharingData {

    /** 描述 */
    private static final long serialVersionUID = -3347529996086110670L;

    /** 健康包合计 */
    private Integer healthProduct;

    /** 是否已绑定HL03设备 */
    private boolean isBindHL03;

    /** 是否已绑定HL031设备 */
    private boolean isBindHL031;

    /** 是否已绑定C3设备 */
    private boolean isBindC3;

    /** 身高 */
    private Float height;

    /** 体重 */
    private Float weight;

    /** 腰围 */
    private Float waist;

    /** 胸围 */
    private Float bust;

    /** 臀围 */
    private Float hip;

    public MemberSharingData() {
        this.isBindC3 = false;
        this.isBindHL03 = false;
        this.isBindHL031 = false;
    }

    public Integer getHealthProduct() {
        return healthProduct;
    }

    public void setHealthProduct(Integer healthProduct) {
        this.healthProduct = healthProduct;
    }

    public boolean isBindHL03() {
        return isBindHL03;
    }

    public void setBindHL03(boolean isBindHL03) {
        this.isBindHL03 = isBindHL03;
    }

    public boolean isBindHL031() {
        return isBindHL031;
    }

    public void setBindHL031(boolean isBindHL031) {
        this.isBindHL031 = isBindHL031;
    }

    public boolean isBindC3() {
        return isBindC3;
    }

    public void setBindC3(boolean isBindC3) {
        this.isBindC3 = isBindC3;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getWaist() {
        return waist;
    }

    public void setWaist(Float waist) {
        this.waist = waist;
    }

    public Float getBust() {
        return bust;
    }

    public void setBust(Float bust) {
        this.bust = bust;
    }

    public Float getHip() {
        return hip;
    }

    public void setHip(Float hip) {
        this.hip = hip;
    }
}
