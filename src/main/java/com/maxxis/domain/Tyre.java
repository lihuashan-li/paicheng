package com.maxxis.domain;

import java.io.Serializable;

public class Tyre implements Serializable {
    private String id;  //id 主键
    private  String Size;
    private  Cliet cliet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public Cliet getCliet() {
        return cliet;
    }

    public void setCliet(Cliet cliet) {
        this.cliet = cliet;
    }

    @Override
    public String toString() {
        return "Tyre{" +
                "id='" + id + '\'' +
                ", Size='" + Size + '\'' +
                ", cliet=" + cliet +
                '}';
    }
}
