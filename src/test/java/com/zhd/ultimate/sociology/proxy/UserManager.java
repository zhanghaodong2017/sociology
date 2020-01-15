package com.zhd.ultimate.sociology.proxy;


public interface UserManager {
    //新增用户抽象方法
    void addUser(String userName, String password);

    //删除用户抽象方法
    void delUser(String userName);

    String doWork(String name);

}