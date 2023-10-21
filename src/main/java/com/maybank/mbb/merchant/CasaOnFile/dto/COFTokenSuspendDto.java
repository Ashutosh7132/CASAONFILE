package com.maybank.mbb.merchant.CasaOnFile.dto;

import lombok.Getter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
@Getter
public class COFTokenSuspendDto {

    private String userId;
    private String merchantId;

    private String merchantTxnRefNo;
    private String tokenRefNo;

    private String reason;

    private Date dateTime = new Date();

    private String txnType;

    private String nonce;

    private byte[] checksum;

    public COFTokenSuspendDto(String userId, String merchantId, String merchantTxnRefNo, String tokenRefNo, String reason,Date dateTime, String txnType, String nonce, byte[] checksum) throws NoSuchAlgorithmException {
        this.userId = userId;
        this.merchantId = merchantId;
        this.merchantTxnRefNo = merchantTxnRefNo;
        this.tokenRefNo = tokenRefNo;
        this.reason = reason;
        this.dateTime = dateTime;
        this.txnType = txnType;
        this.nonce = nonce;
        this.tokenRefNo = tokenRefNo;
        this.checksum = calculateChecksum();
    }

    private byte[] calculateChecksum() throws NoSuchAlgorithmException {

        String data = userId.concat("|").concat(merchantId).concat("|").concat(merchantTxnRefNo)
                .concat("|").concat(tokenRefNo).concat("|").concat(reason).concat("|").concat(String.valueOf(dateTime)).
                concat("|").concat(txnType).concat("|").concat(nonce);

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(data.getBytes());
    }
}