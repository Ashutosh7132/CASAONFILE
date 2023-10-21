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

    private Date dateTime = new Date();

    private String txnType;
    private String mbbTxnRefNo;

    private String statusCode;

    private String statusDec;

    private String tokenRefNo;
    private String tokenValidity;
    private Integer lastDigits;

    private boolean isHandshakeSuccessful;

    private String nonce;

    private final byte[] checksum;

    public COFPaymentDto(String userId, String merchantId, String merchantTxnRefNo, Date dateTime, String txnType, String mbbTxnRefNo, String statusCode, String statusDec, String tokenRefNo, String tokenValidity, Integer lastDigits, boolean isHandshakeSuccessful, String nonce, byte[] checksum) throws NoSuchAlgorithmException {
        this.userId = userId;
        this.merchantId = merchantId;
        this.merchantTxnRefNo = merchantTxnRefNo;
        this.dateTime = dateTime;
        this.txnType = txnType;
        this.mbbTxnRefNo = mbbTxnRefNo;
        this.statusCode = statusCode;
        this.statusDec = statusDec;
        this.tokenRefNo = tokenRefNo;
        this.tokenValidity = tokenValidity;
        this.lastDigits = lastDigits;
        this.isHandshakeSuccessful = isHandshakeSuccessful;
        this.nonce = nonce;
        this.checksum = calculateChecksum();
    }

    private byte[] calculateChecksum() throws NoSuchAlgorithmException {

        String data = userId.concat("|").concat(merchantId).concat("|").concat(merchantTxnRefNo)
                .concat("|").concat(String.valueOf(dateTime)).concat("|").concat(txnType).concat("|").concat(mbbTxnRefNo).concat("|").concat(statusCode).concat("|").concat(statusDec).concat("|").concat(tokenRefNo).concat("|").concat(tokenValidity).concat("|").concat(String.valueOf(lastDigits)).concat("|").concat(String.valueOf(isHandshakeSuccessful)).concat("|").concat(nonce);

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(data.getBytes());
    }

}
