package com.bgjo.batchexercise.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestVo {

    private String id;
    private String name;
    private String age;
    private String member_type;

    @Override
    public String toString() {
        return "TestVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", member_type='" + member_type + '\'' +
                '}';
    }
}
