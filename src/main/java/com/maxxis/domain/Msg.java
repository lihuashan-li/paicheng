package com.maxxis.domain;

public class Msg {
    public String zheng;
    public  String cuo;

    public String getZheng() {
        return zheng;
    }

    public void setZheng(String zheng) {
        this.zheng = zheng;
    }

    public String getCuo() {
        return cuo;
    }

    public void setCuo(String cuo) {
        this.cuo = cuo;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "zheng='" + zheng + '\'' +
                ", cuo='" + cuo + '\'' +
                '}';
    }
}
