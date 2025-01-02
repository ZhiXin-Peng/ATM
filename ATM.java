package com.pzx.ATM;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    private ArrayList<account> accounts = new ArrayList<>();


    public void Start(){
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=====欢迎来到ATM系统=====");
            System.out.println("请选择需要办理的业务：");
            System.out.println("1.用户登录");
            System.out.println("2.用户开户");
            System.out.print("请选择：");


            String choice = sc.next();
            switch(choice){
                case "1":
                    login();
                    break;
                case "2":
                    AddAccount();
                    break;
                default:
                    System.out.println("您输入的指令非法请重新输入！");
                    break;
            }
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        account ac = new account();

        System.out.println("=====登录=====");


        if (accounts.isEmpty()) {
            System.out.println("还未创建任何用户，请开户后再进行操作！");
            System.out.println("是否需要去创建用户？(Y/N)");
            String command = sc.next();
            if (command.equalsIgnoreCase("Y")) {
                AddAccount();
            }else if (command.equalsIgnoreCase("N")) {
                Start();
            }
        }

        while (true) {
            System.out.println("请输入账号：");
            String Id = sc.next();
            System.out.println("请输入密码：");
            String Pd = sc.next();

            boolean flag = false;
            for (int i = 0; i < accounts.size(); i++) {
                ac = accounts.get(i);
                if(ac.getAccount_No().equals(Id) && ac.getPassword().equals(Pd)){
                    System.out.println("=====登录成功！=====");
                    System.out.println("=====欢迎您!" + ac.getAccount_name() + (ac.getSex() == '男' ? "先生" : "女士") + "进入ATM系统！=====");
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
            System.out.println("=====登录失败，密码或账号错误!!!=====");
            System.out.println("是否重试?Y/N");
            String command = sc.next();
            if (command.equalsIgnoreCase("N")) {
                Start();
            }
        }

        //登陆成功，进入系统了
        while (true) {
            System.out.println(ac.getAccount_name() + (ac.getSex() == '男' ? "先生" : "女士") + " ,请您选择要办理的业务：");
            System.out.println("1.查询账户");
            System.out.println("2.存款");
            System.out.println("3.取款");
            System.out.println("4.转账");
            System.out.println("5.修改密码");
            System.out.println("6.退出");
            System.out.println("7.注销账户");
            System.out.print("请选择：");

            String choice = sc.next();
            switch (choice) {
                case "1":
                    Search(ac);
                    break;
                case "2":
                    Save(ac);
                    break;
                case "3":
                    Get(ac);
                    break;
                case "4":
                    Trans(ac);
                    break;
                case "5":
                    ChangePassword(ac);
                    break;
                case "6":
                    System.out.println("退出系统成功！");
                    return;
                case "7":
                    Delete(ac);
                    break;
                default:
                    System.out.println("当前操作不存在！");
                    break;
            }
        }


    }

    private void Search(account ac){
        System.out.println("=====查询用户=====");

        String Name = ac.getAccount_name();
        String Number = ac.getAccount_No();
        char Sex = ac.getSex();
        double Money = ac.getRestMoney();
        double Limit = ac.getLimitMoney();
        System.out.println("=====" + ac.getAccount_name() + (ac.getSex() == '男' ? "先生" : "女士") + "的账户信息如下=====");
        System.out.println("姓名：" + Name);
        System.out.println("账号：" + Number);
        System.out.println("性别："+ Sex);
        System.out.println("账户余额" + Money);
        System.out.println("转账限额是：" + Limit);

    }

    private void Delete(account ac) {
        System.out.println("=====销户=====");

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的账号：");
        String Id = sc.next();

        if (ac.getRestMoney() > 0){
            System.out.println("钱还没有取完，不能销户！！！");
            return;
        }

        System.out.println("账户已经空空，确认要注销账户吗？Y/N");
        String command = sc.next();


        if (command.equalsIgnoreCase("Y")) {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getAccount_No().equals(Id) && accounts.get(i).getAccount_No().equals(ac.getAccount_No())) {
                    accounts.remove(i);
                    System.out.println("账户已经注销");
                    break;
                }
            }
        }
        Start();

    }

    private void ChangePassword(account ac) {
        System.out.println("=====改密码=====");

        Scanner sc = new Scanner(System.in);

        String password = ac.getPassword();

        while (true) {
            System.out.println("请输入原始密码：");
            String password1 = sc.next();

            if(password.equals(password1)){
                System.out.println("请输入修改后的密码：");
                String password2 = sc.next();
                ac.setPassword(password2);
                break;
            }else{
                System.out.println("密码错误，请重试！");
            }
        }

    }

    private void Trans(account ac) {
        System.out.println("=====转账=====");

        if (accounts.size() == 1){
            System.out.println("目前仅有一个账户无法进行转账操作，请开户后再试一试！");
            return;
        }

        Scanner sc = new Scanner(System.in);
        account ac1 = new account();
        boolean flag = false;
        String toId ;
        while (true) {
            System.out.println("请输入要转账的对方账号：");
            toId= sc.next();
            for (int i = 0; i < accounts.size(); i++) {
                ac1 = accounts.get(i);
                if(ac1.getAccount_No().equals(toId)){
                    flag = true;
                    break;
                }else{
                    System.out.println("您要转账的对象不存在！请重试！");
                    break;
                }
            }
            if(flag) break;
        }

        while (true) {
            if(flag){
                System.out.println("您将要转账的对方账号是" + toId);
                System.out.println("请输入转账金额:");

                double toMoney = sc.nextDouble();
                double restmoney = ac.getRestMoney();
                double restmoney1 = ac1.getRestMoney();
                if(restmoney > toMoney && toMoney >= 0 && toMoney < ac.getLimitMoney()){
                    ac.setRestMoney(restmoney - toMoney);
                    ac1.setRestMoney(restmoney1 + toMoney);
                    System.out.println("转账成功！！！");
                    break;
                }else{
                    System.out.println("转账错误，金额不足或转账金额非法！");
                }
            }
        }
    }

    private void Save(account ac) {
        //存钱
        Scanner sc = new Scanner(System.in);
        double restMoney = ac.getRestMoney();


        while (true) {
            System.out.println("=====存钱=====");
            System.out.println("请输入要存的金额：");
            double moneytosave = sc.nextDouble();
            System.out.println("请确认要存的金额是：" + moneytosave);

            System.out.println("确认无误？Y/N");
            String command = sc.next();
            if(command.equalsIgnoreCase("Y")){
                ac.setRestMoney(moneytosave + restMoney);
                break;
            }
        }

    }

    private void Get(account ac) {
        //取钱·
        System.out.println("=====取钱=====");
        Scanner sc = new Scanner(System.in);
        double restMoney = ac.getRestMoney();
        double limit = ac.getLimitMoney();

        while (true) {
            System.out.println("请输入要取的数额");
            double money = sc.nextDouble();
            if (money <= limit && money >= 0 && money < restMoney) {
                money = restMoney - money;
                ac.setRestMoney(money);
                break;
            }else{
                System.out.println("您输入的存钱数额有问题，请重试！");
            }
        }

    }

    private void AddAccount(){
        account account = new account();
        Scanner sc = new Scanner(System.in);

        System.out.println("=====开户=====");
        System.out.println("请您输入您的账户名称：");
        account.setAccount_name(sc.next());

        while (true) {
            System.out.println("请您输入您的性别：");
            String sex = sc.next();
            if(sex.length() == 1 && (sex.equals("男") || sex.equals("女"))){
                char sex1 = sex.charAt(0);
                account.setSex(sex1);
                break;
            }else{
                System.out.println("您输入的性别有误请重新输入：");
            }
        }

        while (true) {
            System.out.println("请您输入您的密码：");
            String password = sc.next();
            System.out.println("请再次输入您的密码");
            String password2 = sc.next();
            if(password.equals(password2)){
                account.setPassword(password);
                break;
            }else{
                System.out.println("您两次输入的密码不一致，请确认后重新输入！");
            }
        }

        System.out.println("请输入您每次取款额度");
        double limit = sc.nextDouble();
        while (true) {

            if (limit > 0) {
                account.setLimitMoney(limit);
                break;
            }else{
                System.out.println("请输入正确的限额（大于0的数字）");
            }
        }

        //随机生成一个6位数卡号，纯数字
        Random r = new Random();
        String cardNo = "";
        for (int i = 0; i < 6; i++) {
            cardNo += r.nextInt(10);
        }
        account.setAccount_No(cardNo);
        System.out.println("系统随机生成的卡号为：" + cardNo);


        System.out.println("恭喜你，开户成功！ 您的账户名是：" + account.getAccount_name() + "   您的卡号是：" + account.getAccount_No());
        accounts.add(account);

        System.out.println("是否需要去登录？(Y/N)");
        String command = sc.next();
        if (command.equalsIgnoreCase("Y")) {
            login();
        }else if (command.equalsIgnoreCase("N")) {
            Start();
        }
    }
}
