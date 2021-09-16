package com.malsum.catalogservice.entity;

import java.util.List;

public class Wrapper {

    private List<?> list;

    public Wrapper(List<?> list) {
        this.list = list;
    }

    public Wrapper() {
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
