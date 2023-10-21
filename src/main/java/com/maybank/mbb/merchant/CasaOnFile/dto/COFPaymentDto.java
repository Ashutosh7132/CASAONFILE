package com.maybank.mbb.merchant.CasaOnFile.dto;

import lombok.Getter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
@Getter
public class COFPaymentDto {

    private String userId;
    private String merchantId;

    private String merchantTxnRefNo;

    private String txnAmount;
    private String txnCurrency;
    private String txnDescription;
    private Date dateTime = new Date();

    private String txnType;
    private String tokenRefNo;

    private String nonce;

    private byte[] checksum;

    public COFPaymentDto(String userId, String merchantId, String merchantTxnRefNo, String txnAmount,String txnCurrency,String txnDescription,
                         Date dateTime, String txnType, String tokenRefNo,String nonce, byte[] checksum) throws NoSuchAlgorithmException {
        this.userId = userId;
        this.merchantId = merchantId;
        this.merchantTxnRefNo = merchantTxnRefNo;
        this.txnAmount = txnAmount;
        this.txnCurrency = txnCurrency;
        this.txnDescription = txnDescription;
        this.dateTime = dateTime;
        this.txnType = txnType;
        this.tokenRefNo = tokenRefNo;
        this.nonce = nonce;
        this.checksum = calculateChecksum();
    }

    private byte[] calculateChecksum() throws NoSuchAlgorithmException {

        String data = userId.concat("|").concat(merchantId).concat("|").concat(merchantTxnRefNo)
                .concat("|").concat(txnAmount).concat("|").concat(txnCurrency).concat("|")
                .concat(txnDescription).concat("|").concat(String.valueOf(dateTime)).concat("|").concat(txnType).concat("|").concat(tokenRefNo)
                .concat("|").concat(nonce);

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(data.getBytes());
    }

}
