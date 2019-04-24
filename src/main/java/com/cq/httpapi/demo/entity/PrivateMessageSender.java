package com.cq.httpapi.demo.entity;

public class PrivateMessageSender {

    private Long user_id;
    private String nickname;
    private String sex;
    private Long age;

    @Override
    public String toString() {
        return "PrivateMessageSender{" +
                "user_id=" + user_id +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
