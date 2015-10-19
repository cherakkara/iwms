package com.olacabs.customer.p076d;

import java.util.ArrayList;

/* compiled from: RidesCategoryList */
/* renamed from: com.olacabs.customer.d.cu */
public class cu {
    private ArrayList<co> all;
    private ArrayList<co> completed;
    private ArrayList<co> upcoming;

    public ArrayList<co> getAll() {
        if (this.all == null) {
            this.all = new ArrayList();
        }
        return this.all;
    }

    public ArrayList<co> getUpcoming() {
        if (this.upcoming == null) {
            this.upcoming = new ArrayList();
        }
        return this.upcoming;
    }

    public ArrayList<co> getCompleted() {
        if (this.completed == null) {
            this.completed = new ArrayList();
        }
        return this.completed;
    }
}
