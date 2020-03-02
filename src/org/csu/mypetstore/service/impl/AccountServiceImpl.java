package org.csu.mypetstore.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.impl.AccountDAOImpl;
import org.csu.mypetstore.service.AccountService;

import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO = new AccountDAOImpl();

    private static Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    @Override
    public boolean isUsernameExist(String username) {
        if (username.isEmpty() || username == null) {
            return false;
        }
        return accountDAO.getAccountByUsername(username) != null;
    }

    @Override
    public Account getAccount(Account account) {
        if (account.getUsername().isEmpty() || account.getUsername() == null || account.getPassword().isEmpty() ||
                account.getPassword() == null) {
            System.out.println("用户名密码为空");
            return null;
        }
        Account accountRtn = accountDAO.getAccountByUsernameAndPassword(account);
        return accountRtn;
    }

    @Override
    public boolean insertAccount(Account account) {
        //用户名密码不能为空
        if (account.getUsername() == null || account.getPassword() == null ||
                account.getUsername().isEmpty() || account.getPassword().isEmpty()) {
            System.out.println("用户密码不能为空");
            return false;
        }

        //用户名不能存在
        Account accountRtn = null;
        accountRtn = accountDAO.getAccountByUsername(account.getUsername());
        if (accountRtn != null) {
            System.out.println("用户名不能存在");
            return false;
        }
        //插入数据
        accountDAO.insertAccount(account);
        accountDAO.insertProfile(account);
        accountDAO.insertSignon(account);

        return true;
    }

    @Override
    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
        accountDAO.updateProfile(account);
        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDAO.updateSignon(account);
        }
    }

    private boolean isVacant(String str) {
        return str.isEmpty() || str == null;
    }

    @Override
    public String getBannerName(String category) {
        if(category == null || category.isEmpty()){
            logger.warn("Favorite Category 名称不为空");
            return "";
        }
        String rtn = "";
        try {
            rtn = accountDAO.getBannerName(category);
        } catch (SQLException e) {
            logger.error("GetBannerName Failed");
            e.printStackTrace();
        }
        return rtn;
    }
}
