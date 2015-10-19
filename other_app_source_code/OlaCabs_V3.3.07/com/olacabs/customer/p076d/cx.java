package com.olacabs.customer.p076d;

import java.util.ArrayList;

/* compiled from: SearchResult */
/* renamed from: com.olacabs.customer.d.cx */
public class cx {
    public static final String TAG;
    private ArrayList<Object> favourites;
    private ArrayList<String> headerSection;
    private ArrayList<cg> places;
    private ArrayList<ch> populars;

    public cx() {
        this.headerSection = new ArrayList();
    }

    static {
        TAG = cx.class.getSimpleName();
    }

    public ArrayList<cg> getPlaces() {
        if (this.places == null) {
            this.places = new ArrayList();
        }
        return this.places;
    }

    public ArrayList<ch> getPopulars() {
        if (this.populars == null) {
            this.populars = new ArrayList();
        }
        return this.populars;
    }

    public ArrayList<String> getHeaderSection() {
        return this.headerSection;
    }

    public void setHeaderSection(ArrayList<String> arrayList) {
        this.headerSection = arrayList;
    }
}
