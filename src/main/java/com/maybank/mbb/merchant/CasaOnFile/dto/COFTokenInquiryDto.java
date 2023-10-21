package com.maybank.mbb.merchant.CasaOnFile.dto;

import lombok.Getter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Getter
public class COFTokenInquiryDto {

    private String userId;
    private String merchantId;

    private String merchantTxnRefNo;
    private String mbbTxnRefNo;

    private Date dateTime = new Date();

    private String txnFrequency;

    private String txnType;

    private String nonce;

    private byte[] checksum;

    public COFTokenInquiryDto(String userId, String merchantId, String merchantTxnRefNo, String mbbTxnRefNo, Date dateTime, String txnType, String txnFrequency,String nonce, byte[] checksum) throws NoSuchAlgorithmException {
        this.userId = userId;
        this.merchantId = merchantId;
        this.merchantTxnRefNo = merchantTxnRefNo;
        this.mbbTxnRefNo = mbbTxnRefNo;
        this.dateTime = dateTime;
        this.txnType = txnType;
        this.txnFrequency = txnFrequency;
        this.nonce = nonce;
        this.checksum = calculateChecksum();
    }

    private byte[] calculateChecksum() throws NoSuchAlgorithmException {

        String data = userId.concat("|").concat(merchantId).concat("|").concat(merchantTxnRefNo)
                .concat("|").concat(mbbTxnRefNo).concat("|").concat(String.valueOf(dateTime)).concat("|").concat(txnType).
                concat("|").concat(txnFrequency).concat("|").concat(nonce);

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(data.getBytes());
    }
}
