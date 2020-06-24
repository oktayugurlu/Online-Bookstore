package com.BoomBook.Model;

public class UpdateUserStatus {

    private boolean manageInvalidField;
    private boolean manageSuccess;
    private boolean passwordInvalidField;
    private boolean passwordSuccess;
    private boolean firstEntering;
    private boolean isComeFromManage;
    private boolean isComeFromPassword;
    private boolean passwordWrongPassword;


    public void setAllFalse(){

        manageInvalidField = false;
        manageSuccess = false ;
        passwordInvalidField=false ;
        passwordSuccess=false ;
        firstEntering=false ;
        isComeFromManage=false ;
        isComeFromPassword=false;
        passwordWrongPassword = false;

    }

    public boolean isPasswordWrongPassword() {
        return passwordWrongPassword;
    }

    public void setPasswordWrongPassword(boolean passwordWrongPassword) {
        this.passwordWrongPassword = passwordWrongPassword;
    }

    public boolean isManageInvalidField() {
        return manageInvalidField;
    }

    public void setManageInvalidField(boolean manageInvalidField) {
        this.manageInvalidField = manageInvalidField;
    }

    public boolean isManageSuccess() {
        return manageSuccess;
    }

    public void setManageSuccess(boolean manageSuccess) {
        this.manageSuccess = manageSuccess;
    }

    public boolean isPasswordInvalidField() {
        return passwordInvalidField;
    }

    public void setPasswordInvalidField(boolean passwordInvalidField) {
        this.passwordInvalidField = passwordInvalidField;
    }

    public boolean isPasswordSuccess() {
        return passwordSuccess;
    }

    public void setPasswordSuccess(boolean passwordSuccess) {
        this.passwordSuccess = passwordSuccess;
    }

    public boolean isFirstEntering() {
        return firstEntering;
    }

    public void setFirstEntering(boolean firstEntering) {
        this.firstEntering = firstEntering;
    }

    public boolean isComeFromManage() {
        return isComeFromManage;
    }

    public void setComeFromManage(boolean comeFromManage) {
        isComeFromManage = comeFromManage;
    }

    public boolean isComeFromPassword() {
        return isComeFromPassword;
    }

    public void setComeFromPassword(boolean comeFromPassword) {
        isComeFromPassword = comeFromPassword;
    }
}
