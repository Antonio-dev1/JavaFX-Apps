package com.TransportationCompany;

public class Payment extends SQL_Connector {
    public int paymentID;
    public String paymentType;
    public double paymentAmount;
    public String transactionID;

    public Payment(String paymentType, double paymentAmount, String transactionID) {

        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.transactionID = transactionID;
    }
    public Payment(){}

    public int getPaymentID() {
        return paymentID;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public double getPaymentAmount() { return paymentAmount;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}
