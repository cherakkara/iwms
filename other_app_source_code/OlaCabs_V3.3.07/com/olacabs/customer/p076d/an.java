package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: Denominations */
/* renamed from: com.olacabs.customer.d.an */
public class an {
    String denomination;
    @SerializedName(a = "header_text")
    String headerText;
    @SerializedName(a = "offer_id")
    String offerId;
    @SerializedName(a = "offer_text")
    String offerText;

    public String getDenomination() {
        return this.denomination;
    }

    public void setDenomination(String str) {
        this.denomination = str;
    }

    public String getHeaderText() {
        return this.headerText;
    }

    public void setHeaderText(String str) {
        this.headerText = str;
    }

    public String getOfferText() {
        return this.offerText;
    }

    public void setOfferText(String str) {
        this.offerText = str;
    }

    public String getOfferId() {
        return this.offerId;
    }

    public void setOfferId(String str) {
        this.offerId = str;
    }
}
