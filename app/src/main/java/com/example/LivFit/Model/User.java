package com.example.LivFit.Model;

public class User {
    private String fname;
    private String lname;
    private String uname;
    private String pword;
    private String email;
    private Integer age;
    private Double nowWeight;
    private Double height;
    private Double targetWeight;
    private Double BMI;
    private String gender;
    private Double calGoal;
    private Double calConsumption;
    private Double calBurned;
    private Integer waterCount;

    //default constructor
    public User() {

    }

    //getters and setters
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getNowWeight() {
        return nowWeight;
    }

    public void setNowWeight(Double nowWeight) {
        this.nowWeight = nowWeight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public Double getBMI() {
        return BMI;
    }

    public void setBMI(Double BMI) {
        this.BMI = BMI;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getCalGoal() {
        return calGoal;
    }

    public void setCalGoal(Double calGoal) {
        this.calGoal = calGoal;
    }

    public Double getCalConsumption() {
        return calConsumption;
    }

    public void setCalConsumption(Double calConsumption) {
        this.calConsumption = calConsumption;
    }

    public Double getCalBurned() {
        return calBurned;
    }

    public void setCalBurned(Double calBurned) {
        this.calBurned = calBurned;
    }

    public Integer getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(Integer waterCount) {
        this.waterCount = waterCount;
    }
}
