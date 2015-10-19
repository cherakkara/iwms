package com.olacabs.customer.p076d;

import com.olacabs.customer.utils.Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/* compiled from: FoodListingResponse */
/* renamed from: com.olacabs.customer.d.be */
public class be implements dw {
    private FoodListingResponse category;
    private String customer_locality;
    private String end_time;
    private ArrayList<bd> listings;
    private String start_time;
    private String storage_location_id;

    /* renamed from: com.olacabs.customer.d.be.a */
    public class FoodListingResponse implements dw {
        private String description;
        private int id;
        private String name;

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public boolean isValid() {
            return this.id != 0 && Utils.m14924g(this.name);
        }
    }

    public String getStorage_location_id() {
        return this.storage_location_id;
    }

    public String getCustomer_locality() {
        return this.customer_locality;
    }

    public String getStart_time() {
        return this.start_time;
    }

    public String getEnd_time() {
        return this.end_time;
    }

    public FoodListingResponse getFoodCategory() {
        return this.category;
    }

    public ArrayList<bd> getFoodListItems() {
        return this.listings;
    }

    public boolean isServiceAvailable() {
        try {
            Date parse = new SimpleDateFormat("HH:mm:ss").parse(this.start_time);
            Calendar instance = Calendar.getInstance();
            instance.set(11, parse.getHours());
            instance.set(12, parse.getMinutes());
            instance.set(13, parse.getSeconds());
            parse = new SimpleDateFormat("HH:mm:ss").parse(this.end_time);
            Calendar instance2 = Calendar.getInstance();
            instance2.set(11, parse.getHours());
            instance2.set(12, parse.getMinutes());
            instance2.set(13, parse.getSeconds());
            Calendar instance3 = Calendar.getInstance();
            if (instance3.getTime().after(instance.getTime()) && instance3.getTime().before(instance2.getTime())) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isValid() {
        int i;
        int i2 = this.listings != null ? 1 : 0;
        if (i2 != 0) {
            Iterator it = this.listings.iterator();
            i = i2;
            while (it.hasNext()) {
                i = ((bd) it.next()).isValid() & i;
            }
        } else {
            i = i2;
        }
        return i != 0 && this.category != null && this.category.isValid() && Utils.m14924g(this.start_time) && Utils.m14924g(this.end_time);
    }
}
