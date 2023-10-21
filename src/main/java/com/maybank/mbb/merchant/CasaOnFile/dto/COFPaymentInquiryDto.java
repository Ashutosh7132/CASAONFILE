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

    private String txnAmount;
    private String txnCurrency;
    private String txnDescription;
    private Date dateTime = new Date();

    private String txnType;

    private String nonce;

    private byte[] checksum;

    public COFPaymentInquiryDto(String userId, String merchantId, String merchantTxnRefNo, Date dateTime, String txnType, String nonce, byte[] checksum, String txnAmount, String txnCurrency, String txnDescription) throws NoSuchAlgorithmException {
        this.userId = userId;
        this.merchantId = merchantId;
        this.merchantTxnRefNo = merchantTxnRefNo;
        this.dateTime = dateTime;
        this.txnType = txnType;
        this.nonce = nonce;
        this.txnAmount = txnAmount;
        this.txnCurrency = txnCurrency;
        this.txnDescription = txnDescription;
        this.checksum = calculateChecksum();
    }

    private byte[] calculateChecksum() throws NoSuchAlgorithmException {

        String data = userId.concat("|").concat(merchantId).concat("|").concat(merchantTxnRefNo)
                .concat("|").concat(String.valueOf(dateTime)).concat("|").concat(txnType).concat("|").concat(txnAmount).concat("|").concat(txnCurrency).concat("|").concat(txnDescription).concat("|").concat(nonce);

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(data.getBytes());
    }

}
