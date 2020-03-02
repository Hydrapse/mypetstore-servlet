package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Enzo Cotter on 2019/10/9.
 */
public class AccountDAOImpl implements AccountDAO {
    private static final String GET_ACCOUNT_BY_USERNAME = "SELECT\n" + //username
            "        SIGNON.USERNAME,\n" +
            "        ACCOUNT.EMAIL,\n" +
            "        ACCOUNT.FIRSTNAME,\n" +
            "        ACCOUNT.LASTNAME,\n" +
            "        ACCOUNT.STATUS,\n" +
            "        ACCOUNT.ADDR1 AS address1,\n" +
            "        ACCOUNT.ADDR2 AS address2,\n" +
            "        ACCOUNT.CITY,\n" +
            "        ACCOUNT.STATE,\n" +
            "        ACCOUNT.ZIP,\n" +
            "        ACCOUNT.COUNTRY,\n" +
            "        ACCOUNT.PHONE,\n" +
            "        PROFILE.LANGPREF AS languagePreference,\n" +
            "        PROFILE.FAVCATEGORY AS favouriteCategoryId,\n" +
            "        PROFILE.MYLISTOPT AS listOption,\n" +
            "        PROFILE.BANNEROPT AS bannerOption,\n" +
            "        BANNERDATA.BANNERNAME\n" +
            "        FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA\n" +
            "        WHERE ACCOUNT.USERID = ?\n" +
            "        AND SIGNON.USERNAME = ACCOUNT.USERID\n" +
            "        AND PROFILE.USERID = ACCOUNT.USERID\n" +
            "        AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT\n" + //username+password
            "        SIGNON.USERNAME,\n" +
            "        ACCOUNT.EMAIL,\n" +
            "        ACCOUNT.FIRSTNAME,\n" +
            "        ACCOUNT.LASTNAME,\n" +
            "        ACCOUNT.STATUS,\n" +
            "        ACCOUNT.ADDR1 AS address1,\n" +
            "        ACCOUNT.ADDR2 AS address2,\n" +
            "        ACCOUNT.CITY,\n" +
            "        ACCOUNT.STATE,\n" +
            "        ACCOUNT.ZIP,\n" +
            "        ACCOUNT.COUNTRY,\n" +
            "        ACCOUNT.PHONE,\n" +
            "        PROFILE.LANGPREF AS languagePreference,\n" +
            "        PROFILE.FAVCATEGORY AS favouriteCategoryId,\n" +
            "        PROFILE.MYLISTOPT AS listOption,\n" +
            "        PROFILE.BANNEROPT AS bannerOption,\n" +
            "        BANNERDATA.BANNERNAME\n" +
            "        FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA\n" +
            "        WHERE ACCOUNT.USERID = ?\n" +
            "        AND SIGNON.PASSWORD = ?\n" +
            "        AND SIGNON.USERNAME = ACCOUNT.USERID\n" +
            "        AND PROFILE.USERID = ACCOUNT.USERID\n" +
            "        AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String INSERT_ACCOUNT = "INSERT " +
            "        INTO ACCOUNT\n" +
            "        (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)\n" +
            "        VALUES\n" +
            "        (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_PROFILE = "INSERT " +
            "        INTO PROFILE (LANGPREF, FAVCATEGORY, USERID, MYLISTOPT, BANNEROPT)\n" +
            "        VALUES (?, ?, ?, ?, ?)";

    private static final String INSERT_SIGNON = "INSERT " +
            "        INTO SIGNON (PASSWORD,USERNAME)\n" +
            "        VALUES (?, ?)";

    private static final String UPDATE_ACCOUNT = "UPDATE " +
            "        ACCOUNT SET\n" +
            "        EMAIL = ?,\n" +
            "        FIRSTNAME = ?,\n" +
            "        LASTNAME = ?,\n" +
            "        STATUS = ?,\n" +
            "        ADDR1 = ?,\n" +
            "        ADDR2 = ?,\n" +
            "        CITY = ?,\n" +
            "        STATE = ?,\n" +
            "        ZIP = ?,\n" +
            "        COUNTRY = ?,\n" +
            "        PHONE = ?\n" +
            "        WHERE USERID = ?";

    private static final String UPDATE_PROFILE = "UPDATE " +
            "        PROFILE SET\n" +
            "        LANGPREF = ?,\n" +
            "        FAVCATEGORY = ?,\n" +
            "        MYLISTOPT = ?,\n" +
            "        BANNEROPT = ?\n" +
            "        WHERE USERID = ?";

    private static final String UPDATE_SIGNON = "UPDATE " +
            "        SIGNON SET PASSWORD = ?\n" +
            "        WHERE USERNAME = ?";

    private static final String GET_BANNER_NAME = "SELECT " +
            "bannername FROM BANNERDATA WHERE favcategory = ?";

    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setStatus(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account accountRtn = null;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                accountRtn = new Account();
                accountRtn.setUsername(resultSet.getString(1));
                accountRtn.setEmail(resultSet.getString(2));
                accountRtn.setFirstName(resultSet.getString(3));
                accountRtn.setLastName(resultSet.getString(4));
                accountRtn.setStatus(resultSet.getString(5));
                accountRtn.setAddress1(resultSet.getString(6));
                accountRtn.setAddress2(resultSet.getString(7));
                accountRtn.setCity(resultSet.getString(8));
                accountRtn.setState(resultSet.getString(9));
                accountRtn.setZip(resultSet.getString(10));
                accountRtn.setCountry(resultSet.getString(11));
                accountRtn.setPhone(resultSet.getString(12));
                accountRtn.setLanguagePreference(resultSet.getString(13));
                accountRtn.setFavouriteCategoryId(resultSet.getString(14));
                accountRtn.setListOption(resultSet.getBoolean(15));
                accountRtn.setBannerOption(resultSet.getBoolean(16));
                accountRtn.setBannerName(resultSet.getString(17));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return accountRtn;
    }

    @Override
    public void insertAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.setInt(4, account.isListOption() ? 1 : 0);
            preparedStatement.setInt(5, account.isBannerOption() ? 1 : 0);
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SIGNON);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setInt(3, account.isListOption() ? 1 : 0);
            preparedStatement.setInt(4, account.isBannerOption() ? 1 : 0);
            preparedStatement.setString(5, account.getUsername());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SIGNON);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.execute();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getBannerName(String category) throws SQLException {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BANNER_NAME);
        preparedStatement.setString(1, category);
        ResultSet resultSet = preparedStatement.executeQuery();
        String rtn = "";
        if(resultSet.next()){
            rtn = resultSet.getString(1);
        }
        return rtn;
    }
}
