package com.BoomBook.Exceptions;

public class PurchaseChecker {
    private String isCard="no";
    private String isTransfer="no";
    private boolean isExpireDateOutOfDate=false;
    private int expireMonth;
    private int expireYear;
    private int paymentService;
    private int transferId=-1;

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getIsCard() {
        return isCard;
    }

    public void setIsCard(String isCard) {
        this.isCard = isCard;
    }

    public String getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer;
    }

    public int getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(int expireMonth) {
        this.expireMonth = expireMonth;
    }

    public int getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(int expireYear) {
        this.expireYear = expireYear;
    }

    public int getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(int paymentService) {
        this.paymentService = paymentService;
    }


    public boolean isExpireDateOutOfDate() {
        return isExpireDateOutOfDate;
    }

    public void setExpireDateOutOfDate(boolean expireDateOutOfDate) {
        isExpireDateOutOfDate = expireDateOutOfDate;
    }
}
