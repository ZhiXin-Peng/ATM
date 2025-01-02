package com.pzx.ATM;

public class account {
    private String account_name;
    private char Sex;
    private String Account_No;
    private String password;
    private double limitMoney;
    private double restMoney;

    public account() {
    }

    public account(String account_name, double restMoney, double limitMoney, String password, String account_No, char sex) {
        this.account_name = account_name;
        this.restMoney = restMoney;
        this.limitMoney = limitMoney;
        this.password = password;
        Account_No = account_No;
        Sex = sex;
    }

    public String getAccount_No() {
        return Account_No;
    }

    public void setAccount_No(String account_No) {
        Account_No = account_No;
    }

    public double getRestMoney() {
        return restMoney;
    }

    public void setRestMoney(double restMoney) {
        this.restMoney = restMoney;
    }

    public double getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(double limitMoney) {
        this.limitMoney = limitMoney;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getSex() {
        return Sex;
    }

    public void setSex(char sex) {
        Sex = sex;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }
}
