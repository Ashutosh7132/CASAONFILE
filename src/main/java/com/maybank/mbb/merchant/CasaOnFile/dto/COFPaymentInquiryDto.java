package com.maybank.mbb.merchant.CasaOnFile.dto;

import lombok.Getter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Getter
public class COFPaymentInquiryDto {

    private String userId;
    private String merchantId;

    private String merchantTxnRefNo;
    private String txnFrequency;

    private String txnAmount;
    private String txnCurrency;
    private Date dateTime = new Date();

    private String txnType;

    private String tokenRefNo;

    private String nonce;

    private byte[] checksum;

    public COFPaymentInquiryDto(String userId, String merchantId, String merchantTxnRefNo, String txnFrequency,String txnAmount,String txnCurrency,Date dateTime, String txnType, String tokenRefNo, String nonce, byte[] checksum) throws NoSuchAlgorithmException {
        this.userId = userId;
        this.merchantId = merchantId;
        this.merchantTxnRefNo = merchantTxnRefNo;
        this.txnFrequency = txnFrequency;
        this.txnAmount = txnAmount;
        this.txnCurrency = txnCurrency;
        this.dateTime = dateTime;
        this.txnType = txnType;
        this.tokenRefNo = tokenRefNo;
        this.nonce = nonce;
        this.checksum = calculateChecksum();
    }

    private byte[] calculateChecksum() throws NoSuchAlgorithmException {

        String data = userId.concat("|").concat(merchantId).concat("|").concat(merchantTxnRefNo)
                .concat("|").concat(txnFrequency).concat("|").concat(txnAmount).concat("|").concat(txnCurrency)
                .concat("|").concat(String.valueOf(dateTime)).concat("|")
                .concat(txnType).concat("|").concat(tokenRefNo).concat("|").concat(nonce);

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(data.getBytes());
    }

}
