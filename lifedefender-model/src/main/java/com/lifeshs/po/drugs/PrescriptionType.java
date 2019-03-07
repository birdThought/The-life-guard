package com.lifeshs.po.drugs;

import lombok.Data;

@Data
public class PrescriptionType {
    private int id;
    private String prescriptionType;
    private String prescriptionName;
    private String remark;
}
