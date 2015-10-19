package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Utils;

/* compiled from: PaymentHashResponse */
/* renamed from: com.olacabs.customer.d.cd */
public class cd implements dw {
    private String checkSum;
    @SerializedName(a = "delete_card_hash")
    private String deleteCardHash;
    @SerializedName(a = "get_cards_hash")
    private String getCardsHash;
    @SerializedName(a = "header")
    private String header;
    @SerializedName(a = "nb_list_hash")
    private String nbListHash;
    @SerializedName(a = "payment_hash")
    private String paymentHash;
    @SerializedName(a = "request_type")
    private String requestType;
    @SerializedName(a = "save_card_hash")
    private String saveCardHash;
    private String status;
    @SerializedName(a = "text")
    private String text;

    public String getPaymentHash() {
        return this.paymentHash;
    }

    public void setPaymentHash(String str) {
        this.paymentHash = str;
    }

    public String getStoredCardsHash() {
        return this.getCardsHash;
    }

    public void setGetCardsHash(String str) {
        this.getCardsHash = str;
    }

    public String getSaveCardHash() {
        return this.saveCardHash;
    }

    public void setSaveCardHash(String str) {
        this.saveCardHash = str;
    }

    public String getDeleteCardHash() {
        return this.deleteCardHash;
    }

    public void setDeleteCardHash(String str) {
        this.deleteCardHash = str;
    }

    public String getNbListHash() {
        return this.nbListHash;
    }

    public void setNbListHash(String str) {
        this.nbListHash = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }

    public String getCheckSum() {
        return this.checkSum;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String str) {
        this.requestType = str;
    }

    public String getHeader() {
        return this.header;
    }

    public String getText() {
        return this.text;
    }

    public boolean isValid() {
        return Utils.m14924g(this.status) && Utils.m14924g(this.requestType) && Utils.m14924g(this.paymentHash) && Utils.m14924g(this.deleteCardHash) && Utils.m14924g(this.nbListHash);
    }
}
