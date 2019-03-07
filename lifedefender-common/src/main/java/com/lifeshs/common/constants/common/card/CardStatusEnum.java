package com.lifeshs.common.constants.common.card;

public enum CardStatusEnum {

    /** 未激活 */
    Normal(0),
    /** 已激活 */
    ACTIVED(1);
    
    private int status;
    
    private CardStatusEnum(int status) {
        this.status = status;
    }
    
    public CardStatusEnum getCardStatus(int status) {
        for (CardStatusEnum s : CardStatusEnum.values()) {
            if (s.getStatus() == status) {
                return s;
            }
        }
        return null;
    }
    
    public int getStatus() {
        return this.status;
    }
}
