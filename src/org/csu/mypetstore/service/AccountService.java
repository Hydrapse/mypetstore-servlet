package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;


public interface AccountService {

    // 根据用户名取得用户，注册的时候用到
    boolean isUsernameExist(String username);

    // 根据用户名和密码取得用户，登录的时候用到
    Account getAccount(Account account);

    // 插入一个新用户，注册的时候用到
    boolean insertAccount(Account account);

    // 更新一个用户，修改用户用到
    void updateAccount(Account account);

    String getBannerName(String category);
}
