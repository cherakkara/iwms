package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import java.util.List;

/* compiled from: FoodDeliveryConfigurationResponse */
/* renamed from: com.olacabs.customer.d.bb */
public class bb {
    @SerializedName(a = "cancel_reasons")
    public List<String> cancelReasons;
    @SerializedName(a = "contact_numbers")
    public List<bs> contactNumbers;
    @SerializedName(a = "max_single_item_count")
    public int maxSingleItemCount;
    @SerializedName(a = "order_detail_polling_time")
    public int orderPollingTime;

    public List<String> getCancelReasons() {
        return this.cancelReasons;
    }

    public int getOrderPollingTime() {
        return this.orderPollingTime;
    }

    public int getMaxSingleItemCount() {
        return this.maxSingleItemCount;
    }

    public List<bs> getContactNumbers() {
        return this.contactNumbers;
    }
}
