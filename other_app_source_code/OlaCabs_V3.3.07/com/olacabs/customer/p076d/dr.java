package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: UserCardData */
/* renamed from: com.olacabs.customer.d.dr */
public class dr {
    @SerializedName(a = "card_bin")
    private String cardBin;
    @SerializedName(a = "card_brand")
    private String cardBrand;
    @SerializedName(a = "card_mode")
    private String cardMode;
    @SerializedName(a = "card_name")
    private String cardName;
    @SerializedName(a = "card_no")
    private String cardNo;
    @SerializedName(a = "card_token")
    private String cardToken;
    @SerializedName(a = "card_type")
    private String cardType;
    @SerializedName(a = "expiry_month")
    private String expiryMonth;
    @SerializedName(a = "expiry_year")
    private String expiryYear;
    private String isDomestic;
    @SerializedName(a = "is_expired")
    private int isExpired;
    @SerializedName(a = "name_on_card")
    private String nameOnCard;

    public String getNameOnCard() {
        return this.nameOnCard;
    }

    public String getCardName() {
        return this.cardName;
    }

    public String getCardType() {
        return this.cardType;
    }

    public int getIsExpired() {
        return this.isExpired;
    }

    public String getCardModel() {
        return this.cardMode;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public String getCardBrand() {
        return this.cardBrand;
    }

    public String getCardBin() {
        return this.cardBin;
    }

    public String getExpiryYear() {
        return this.expiryYear;
    }

    public String getExpiryMonth() {
        return this.expiryMonth;
    }

    public String getCardToken() {
        return this.cardToken;
    }

    public String getCardMode() {
        return this.cardMode;
    }

    public String getIsDomestic() {
        return this.isDomestic;
    }
}
