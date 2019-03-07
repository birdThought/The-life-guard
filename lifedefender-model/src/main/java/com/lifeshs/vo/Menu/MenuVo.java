package com.lifeshs.vo.Menu;

import lombok.Data;

/**
 * @author Administrator
 * @create 2018-04-13
 * 10:45
 * @desc \
 */
public @Data class MenuVo {

    private String path;
    private String name;
    private Integer weight;

    public MenuVo(String path, String name, Integer weight) {
        this.path = path;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
