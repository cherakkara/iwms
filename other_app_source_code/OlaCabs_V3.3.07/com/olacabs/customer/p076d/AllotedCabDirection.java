package com.olacabs.customer.p076d;

/* renamed from: com.olacabs.customer.d.c */
public class AllotedCabDirection {
    private int cab_index;
    private String cab_reached_message;
    private String cab_type;
    private ap distance;
    private as duration;
    private String id;
    private String last_booking_id;

    public String getDuration() {
        if (this.duration == null) {
            return this.cab_reached_message;
        }
        return this.duration.getValue();
    }

    public boolean isCabAlloted() {
        return this.last_booking_id != null;
    }

    public String getCab_type() {
        return this.cab_type;
    }

    public int getCabIndex() {
        return this.cab_index;
    }

    public String getAllotedCabId() {
        return this.id;
    }

    public String getLastBookingId() {
        return this.last_booking_id;
    }
}
