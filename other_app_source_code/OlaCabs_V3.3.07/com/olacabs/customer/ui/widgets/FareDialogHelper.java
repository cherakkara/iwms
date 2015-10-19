package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.localytics.android.Localytics;
import com.olacabs.customer.R;
import com.olacabs.customer.p076d.CabCategory;
import com.olacabs.customer.p076d.CityBaseCarModelDetailsResponse;
import com.olacabs.customer.p076d.aa;
import com.olacabs.customer.tfs.p081a.TFSFareDetails;
import com.olacabs.customer.tfs.p081a.TFSFareResponse;
import com.olacabs.customer.ui.BookingFragment;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.olacabs.customer.ui.widgets.c */
public class FareDialogHelper {
    public static final String f11380a;
    private Context f11381b;
    private RelativeLayout f11382c;
    private ImageView f11383d;
    private TextView f11384e;
    private TextView f11385f;
    private TextView f11386g;
    private TextView f11387h;
    private TextView f11388i;
    private TextView f11389j;
    private TextView f11390k;
    private TextView f11391l;
    private TextView f11392m;
    private TextView f11393n;
    private TextView f11394o;
    private TextView f11395p;
    private FrameLayout f11396q;
    private RelativeLayout f11397r;
    private LinearLayout f11398s;
    private BookingFragment f11399t;

    static {
        f11380a = FareDialogHelper.class.getSimpleName();
    }

    public FareDialogHelper(RelativeLayout relativeLayout, BookingFragment bookingFragment) {
        this.f11399t = bookingFragment;
        this.f11382c = relativeLayout;
        this.f11381b = relativeLayout.getContext();
        View inflate = ((LayoutInflater) this.f11381b.getSystemService("layout_inflater")).inflate(R.layout.view_rate_card_pop_up, this.f11382c, false);
        m14846a(inflate);
        this.f11382c.addView(inflate);
    }

    private void m14846a(View view) {
        this.f11383d = (ImageView) view.findViewById(R.id.ic_image);
        this.f11384e = (TextView) view.findViewById(R.id.item_category);
        this.f11385f = (TextView) view.findViewById(R.id.item_options);
        this.f11386g = (TextView) view.findViewById(R.id.fare_header_txt);
        this.f11387h = (TextView) view.findViewById(R.id.multiplier_surcharge_base_fare);
        this.f11388i = (TextView) view.findViewById(R.id.multiplier_surcharge_base_fare_text);
        this.f11389j = (TextView) view.findViewById(R.id.multiplier_surcharge_rate_per_km);
        this.f11390k = (TextView) view.findViewById(R.id.multiplier_surcharge_rate_per_km_text);
        this.f11391l = (TextView) view.findViewById(R.id.multiplier_surcharge_per_min);
        this.f11392m = (TextView) view.findViewById(R.id.multiplier_surcharge_per_min_text);
        this.f11393n = (TextView) view.findViewById(R.id.item_desclaimer_text1);
        this.f11394o = (TextView) view.findViewById(R.id.item_desclaimer_text);
        this.f11395p = (TextView) view.findViewById(R.id.item_tfs_powered);
        this.f11396q = (FrameLayout) view.findViewById(R.id.surcharge_layout_content);
        this.f11397r = (RelativeLayout) view.findViewById(R.id.layout_flat_surcharge);
        this.f11398s = (LinearLayout) view.findViewById(R.id.layout_multiplier_surcharge);
    }

    public void m14850a(CabCategory cabCategory, CityBaseCarModelDetailsResponse cityBaseCarModelDetailsResponse) {
        if (this.f11382c.getVisibility() == 8) {
            this.f11382c.setVisibility(0);
        }
        this.f11395p.setVisibility(8);
        if (cabCategory.isKaaliPeeliCab() || cabCategory.isAutoRickshaw() || cabCategory.isDelivery()) {
            m14848b(cabCategory, cityBaseCarModelDetailsResponse);
        } else {
            m14849c(cabCategory, cityBaseCarModelDetailsResponse);
        }
        if (cabCategory.isCabAvailableNow() || cabCategory.isRideNowEnable()) {
            this.f11399t.m14370h();
            this.f11399t.m14382t().m14861a();
        } else {
            this.f11399t.m14371i();
            this.f11399t.m14382t().m14863a(cabCategory.getDisplayText());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Cab category", cabCategory.getName());
        if (cabCategory.isRideLaterEnable()) {
            this.f11399t.m14372j();
        } else {
            this.f11399t.m14373k();
        }
        m14847a(hashMap);
    }

    private void m14848b(CabCategory cabCategory, CityBaseCarModelDetailsResponse cityBaseCarModelDetailsResponse) {
        this.f11396q.setVisibility(8);
        this.f11386g.setVisibility(8);
        this.f11385f.setVisibility(8);
        this.f11393n.setVisibility(0);
        if (cabCategory.isAutoRickshaw()) {
            this.f11384e.setText(R.string.text_auto);
            this.f11383d.setImageResource(R.drawable.ic_auto_selected);
        } else if (cabCategory.isKaaliPeeliCab()) {
            this.f11384e.setText(R.string.text_kaali_peeli);
            this.f11383d.setImageResource(R.drawable.ic_kp_car_selected);
        } else if (cabCategory.isDelivery()) {
            this.f11384e.setText(cityBaseCarModelDetailsResponse.getHeader());
            this.f11383d.setImageResource(m14845a(cabCategory.getImage_name()));
            this.f11383d.setSelected(true);
        }
        this.f11393n.setText(cityBaseCarModelDetailsResponse.getText());
        if (TextUtils.isEmpty(cityBaseCarModelDetailsResponse.getSubText())) {
            this.f11394o.setVisibility(8);
        } else {
            this.f11394o.setText(cityBaseCarModelDetailsResponse.getSubText());
            this.f11394o.setVisibility(0);
        }
        this.f11383d.setVisibility(0);
    }

    private void m14849c(CabCategory cabCategory, CityBaseCarModelDetailsResponse cityBaseCarModelDetailsResponse) {
        CharSequence string;
        this.f11396q.setVisibility(0);
        this.f11383d.setVisibility(8);
        this.f11386g.setVisibility(0);
        this.f11385f.setVisibility(0);
        this.f11394o.setVisibility(0);
        String string2 = this.f11381b.getResources().getString(R.string.rs_symbol);
        this.f11384e.setText(cabCategory.getDisplayName());
        TextView textView = this.f11386g;
        if (TextUtils.isEmpty(cityBaseCarModelDetailsResponse.getHeader())) {
            string = this.f11381b.getString(R.string.ride_confirm_fare_breakup_header);
        } else {
            string = cityBaseCarModelDetailsResponse.getHeader();
        }
        textView.setText(string);
        textView = this.f11394o;
        if (TextUtils.isEmpty(cityBaseCarModelDetailsResponse.getNote())) {
            string = this.f11381b.getString(R.string.ride_confirm_fare_breakup_note);
        } else {
            string = cityBaseCarModelDetailsResponse.getNote();
        }
        textView.setText(string);
        this.f11385f.setText(cityBaseCarModelDetailsResponse.getCarNames());
        List cityBaseFareBreakUp = cityBaseCarModelDetailsResponse.getCityBaseFareBreakUp();
        this.f11393n.setVisibility(8);
        this.f11397r.setVisibility(8);
        this.f11398s.setVisibility(0);
        this.f11387h.setText(string2 + " " + ((aa) cityBaseFareBreakUp.get(0)).getValue());
        this.f11389j.setText(string2 + " " + ((aa) cityBaseFareBreakUp.get(1)).getValue() + "/km");
        this.f11391l.setText(string2 + " " + ((aa) cityBaseFareBreakUp.get(2)).getValue() + "/min");
        this.f11388i.setText(((aa) cityBaseFareBreakUp.get(0)).getDisplayText());
        this.f11390k.setText(((aa) cityBaseFareBreakUp.get(1)).getDisplayText());
        this.f11392m.setText(((aa) cityBaseFareBreakUp.get(2)).getDisplayText());
    }

    private int m14845a(String str) {
        try {
            int identifier = this.f11381b.getResources().getIdentifier(str, "drawable", this.f11381b.getPackageName());
            if (identifier == 0) {
                return R.drawable.bg_delivery_default;
            }
            return identifier;
        } catch (Exception e) {
            e.printStackTrace();
            return R.drawable.bg_delivery_default;
        }
    }

    private void m14847a(HashMap<String, String> hashMap) {
        Localytics.tagEvent("Rate card viewed", hashMap);
    }

    public boolean m14852a() {
        return this.f11382c.getVisibility() == 0;
    }

    public void m14853b() {
        this.f11382c.setVisibility(8);
    }

    public void m14851a(TFSFareResponse tFSFareResponse) {
        HashMap hashMap = new HashMap();
        hashMap.put("Cab category", "TFS");
        m14847a(hashMap);
        if (tFSFareResponse != null && tFSFareResponse.getFares() != null && tFSFareResponse.getFares().isValid()) {
            TFSFareDetails fares = tFSFareResponse.getFares();
            this.f11382c.setVisibility(0);
            this.f11396q.setVisibility(0);
            this.f11383d.setVisibility(8);
            this.f11386g.setVisibility(0);
            this.f11385f.setVisibility(0);
            this.f11394o.setVisibility(0);
            this.f11395p.setVisibility(0);
            this.f11393n.setVisibility(8);
            String string = this.f11381b.getResources().getString(R.string.rs_symbol);
            this.f11384e.setAllCaps(false);
            this.f11384e.setText(this.f11381b.getString(R.string.taxi_for_sure));
            this.f11386g.setText(this.f11381b.getString(R.string.ride_confirm_fare_breakup_header));
            this.f11394o.setText(this.f11381b.getString(R.string.tfs_disclaimer));
            this.f11385f.setText(this.f11381b.getString(R.string.tfs_hatchback));
            this.f11397r.setVisibility(8);
            this.f11398s.setVisibility(0);
            this.f11387h.setText(string + " " + fares.getBaseFare());
            this.f11389j.setText(string + " " + fares.getExtraKmFare() + "/km");
            this.f11391l.setText(string + " " + fares.getTripTimePulseRate() + "/min");
            this.f11388i.setText("First " + fares.getBaseKm() + "Km");
            this.f11390k.setText("After " + fares.getBaseKm() + "Km");
            this.f11392m.setText(this.f11381b.getString(R.string.tfs_ride_time_rate));
        }
    }
}
