package com.lifeshs.po.drugs;

import lombok.Data;

@Data
public class DrugsTypePO {
    private String firstClassName; //商品一级目录
    private String secondClassName;//商品二级目录
    private String thirdClassName;//商品三级目录
}
