package com.lifeshs.po.admin;

public class AdminPermissionCheckedPO extends AdminPermissionPO {
private boolean isChecked = false;
    
    public boolean getIsChecked() {
        return isChecked;
    }
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

}
