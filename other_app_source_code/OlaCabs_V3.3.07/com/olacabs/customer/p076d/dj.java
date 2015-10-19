package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* compiled from: TransactionResult */
/* renamed from: com.olacabs.customer.d.dj */
public class dj {
    @SerializedName(a = "request_type")
    private String requestType;
    @SerializedName(a = "results")
    public ArrayList<TransactionResult> results;
    private String status;

    /* renamed from: com.olacabs.customer.d.dj.a */
    public class TransactionResult {
        private float credit_amount;
        private String date;
        private float debit_amount;
        private float ola_money_balance;
        private String transaction;

        public boolean isCredited() {
            return this.credit_amount > 0.0f;
        }

        public int getAmount() {
            return Math.round(this.credit_amount > 0.0f ? this.credit_amount : this.debit_amount);
        }

        public int getBalance() {
            return Math.round(this.ola_money_balance);
        }

        public String getTransactionNote() {
            return this.transaction;
        }

        public Date getDate() {
            try {
                return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(this.date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public ArrayList<TransactionResult> getResults() {
        return this.results;
    }
}
