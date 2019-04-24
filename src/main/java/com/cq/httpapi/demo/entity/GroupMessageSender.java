package com.cq.httpapi.demo.entity;

public class GroupMessageSender {

    private Long user_id;
    private String nickname;
    private String card;
    private String sex;
    private Long age;
    private String area;
    private String level;
    private String role;
    private String title;

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

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GroupMessageSender{" +
                "user_id=" + user_id +
                ", nickname='" + nickname + '\'' +
                ", card='" + card + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", area='" + area + '\'' +
                ", level='" + level + '\'' +
                ", role='" + role + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
