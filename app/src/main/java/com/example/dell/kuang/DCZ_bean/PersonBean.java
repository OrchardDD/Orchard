package com.example.dell.kuang.DCZ_bean;

import org.litepal.crud.DataSupport;

/**
 * Created by DELL on 2017/10/19.
 */

public class PersonBean extends DataSupport {
    private long id;
    private String name;
    private int age;
    private int isMale;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIsMale() {
        return isMale;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setIsMale(int isMale) {
        this.isMale = isMale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
