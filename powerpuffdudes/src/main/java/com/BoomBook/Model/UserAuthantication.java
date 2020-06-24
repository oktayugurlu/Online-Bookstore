package com.BoomBook.Model;

import java.util.List;

public class UserAuthantication {

    private boolean adminLogin;
    private boolean loggedin;
    private boolean registerExist;
    private boolean wrongCredentials;
    private boolean succesfulRegister;
    private boolean isComeFromRegister;
    private boolean loginWrongEmail;
    private boolean loginWrongPassword;
    private boolean isComeFromLogin;
    private String userEmail;
    private List<Cart> userCart;
    private float totalCart;
    private float totalOrder;



    public UserAuthantication(boolean adminLogin, boolean loggedin, boolean registerExist, boolean wrongCredentials, boolean succesfulRegister, boolean isComeFromRegister, boolean loginWrongEmail, boolean loginWrongPassword, boolean isComeFromLogin, String userEmail) {
        this.adminLogin = adminLogin;
        this.loggedin = loggedin;
        this.registerExist = registerExist;
        this.wrongCredentials = wrongCredentials;
        this.succesfulRegister = succesfulRegister;
        this.isComeFromRegister = isComeFromRegister;
        this.loginWrongEmail = loginWrongEmail;
        this.loginWrongPassword = loginWrongPassword;
        this.isComeFromLogin = isComeFromLogin;
        this.userEmail = userEmail;
        this.totalCart = 0;
    }

    public UserAuthantication() {}

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setAllAttributesFalse(){
        adminLogin=false;
        loggedin =false;
        registerExist=false;
        wrongCredentials=false;
        succesfulRegister=false;
        isComeFromRegister=false;
        loginWrongEmail=false;
        loginWrongPassword=false;
        isComeFromLogin=false;
    }
    public void adjustRegisterWrongCredentials(){

        setComeFromRegister(true);
        setWrongCredentials(true);
        setRegisterExist(false);
        setSuccesfulRegister(false);
        setLoginWrongPassword(false);
        setComeFromLogin(false);
        setLoggedin(false);
        setAdminLogin(false);
        setLoginWrongEmail(false);

    }
    public boolean isComeFromRegister() {
        return isComeFromRegister;
    }

    public void setComeFromRegister(boolean comeFromRegister) {
        isComeFromRegister = comeFromRegister;
    }

    public boolean isSuccesfulRegister() {
        return succesfulRegister;
    }

    public void setSuccesfulRegister(boolean succesfulRegister) {
        this.succesfulRegister = succesfulRegister;
    }

    public boolean isRegisterExist() {
        return registerExist;
    }
    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public void setRegisterExist(boolean registerExist) {
        this.registerExist = registerExist;
    }

    public boolean isWrongCredentials() {
        return wrongCredentials;
    }

    public void setWrongCredentials(boolean wrongCredentials) {
        this.wrongCredentials = wrongCredentials;
    }

    public boolean isLoginWrongEmail() {
        return loginWrongEmail;
    }

    public void setLoginWrongEmail(boolean loginWrongEmail) {
        this.loginWrongEmail = loginWrongEmail;
    }

    public boolean isLoginWrongPassword() {
        return loginWrongPassword;
    }

    public void setLoginWrongPassword(boolean loginWrongPassword) {
        this.loginWrongPassword = loginWrongPassword;
    }

    public boolean isAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(boolean adminLogin) {
        this.adminLogin = adminLogin;
    }

    public boolean isComeFromLogin() {
        return isComeFromLogin;
    }

    public void setComeFromLogin(boolean comeFromLogin) {
        isComeFromLogin = comeFromLogin;
    }

    public List<Cart> getUserCart() {
        return userCart;
    }

    public void setUserCart(List<Cart> userCart) {
        this.userCart = userCart;
    }

    public float getTotalCart() {
        return totalCart;
    }

    public void setTotalCart(float totalCart) {
        this.totalCart = totalCart;
    }
    @Override
    public String toString() {
        return "UserAuthantication{" +
                "adminLogin=" + adminLogin +
                ", loggedin=" + loggedin +
                ", registerExist=" + registerExist +
                ", wrongCredentials=" + wrongCredentials +
                ", succesfulRegister=" + succesfulRegister +
                ", isComeFromRegister=" + isComeFromRegister +
                ", loginWrongEmail=" + loginWrongEmail +
                ", loginWrongPassword=" + loginWrongPassword +
                ", isComeFromLogin=" + isComeFromLogin +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
    public float getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(float totalOrder) {
        this.totalOrder = totalOrder;
    }
}
