package com.maybank.mbb.merchant.CasaOnFile.dto;

import lombok.Getter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Getter
public class COFTokenDto {

    private String userId;
    private String merchantId;

    private String merchantTxnRefNo;

    private String txnDescription;

    private Date dateTime = new Date();

    private String txnType;

    private String nonce;

    private byte[] checksum;

    public COFTokenDto(String userId, String merchantId, String merchantTxnRefNo, String txnDescription, Date dateTime, String txnType, String nonce, byte[] checksum) throws NoSuchAlgorithmException {
        this.userId = userId;
        this.merchantId = merchantId;
        this.merchantTxnRefNo = merchantTxnRefNo;
        this.txnDescription = txnDescription;
        this.dateTime = dateTime;
        this.txnType = txnType;
        this.nonce = nonce;
        this.checksum = calculateChecksum();
    }

    private byte[] calculateChecksum() throws NoSuchAlgorithmException {

        String data = userId.concat("|").concat(merchantId).concat("|").concat(merchantTxnRefNo)
                .concat("|").concat(txnDescription).concat("|").concat(String.valueOf(dateTime)).
                concat("|").concat(txnType).concat("|").concat(nonce);

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(data.getBytes());
    }

}
