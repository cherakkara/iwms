package com.olacabs.customer.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import com.leanplum.Leanplum;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.R;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.p076d.CityBaseCarModelDetailsResponse;
import com.olacabs.customer.p076d.aa;
import com.olacabs.customer.ui.widgets.ErrorView;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.olacabs.customer.ui.f */
public class BookingCabConfirmationNewFlowFragment extends BaseBookingCabConfirmationFragment {
    private CityBaseCarModelDetailsResponse f10634r;

    public static BookingCabConfirmationNewFlowFragment m14250a(String str, String str2, String str3, String str4, String str5, String str6, long j, boolean z) {
        BookingCabConfirmationNewFlowFragment bookingCabConfirmationNewFlowFragment = new BookingCabConfirmationNewFlowFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category_id", str);
        bundle.putString("category_name", str2);
        bundle.putString("category_base_rate", str3);
        bundle.putString("category_rate_per_km", str4);
        bundle.putLong("pick_up_time", j);
        bundle.putBoolean("is_ride_now", z);
        bundle.putString("location_tag", str6);
        bundle.putString("current_city", str5);
        bookingCabConfirmationNewFlowFragment.setArguments(bundle);
        return bookingCabConfirmationNewFlowFragment;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        m14251b(((ViewStub) view.findViewById(R.id.stub_surcharge)).inflate());
    }

    private void m14251b(View view) {
        Object obj = " ";
        if (this.m.m13218d().getCityBasedCarModels() != null) {
            this.f10634r = this.m.m13218d().getCityBasedCarModels().getCarModels().getCategoryDetails(this.c);
        }
        if (this.f10634r != null) {
            boolean equalsIgnoreCase = this.f10634r.getSurchargeType().equalsIgnoreCase("multiplier");
            TextView textView = (TextView) view.findViewById(R.id.surcharge_amount);
            TextView textView2 = (TextView) view.findViewById(R.id.surcharge_amount_text);
            TextView textView3 = (TextView) view.findViewById(R.id.surcharge_note1);
            if (this.f10634r.getSurchargeReason() != null) {
                textView3.setText(this.f10634r.getSurchargeReason());
            } else {
                textView3.setText(getActivity().getString(R.string.surcharge_reason));
            }
            String str;
            String str2;
            if (equalsIgnoreCase) {
                str = "Multiplier";
                textView.setText(this.f10634r.getSurchargeAmount() + "x");
                if (this.f10634r.getSurchargeText() != null) {
                    textView2.setText(this.f10634r.getSurchargeHeader());
                    obj = str;
                } else {
                    textView2.setText(getActivity().getString(R.string.multiplier_included_text));
                    str2 = str;
                }
            } else {
                str = "Flat";
                textView.setText(getString(R.string.rs_symbol) + this.f10634r.getSurchargeAmount());
                if (this.f10634r.getSurchargeText() != null) {
                    textView2.setText(this.f10634r.getSurchargeHeader());
                    str2 = str;
                } else {
                    textView2.setText(getActivity().getString(R.string.flat_included));
                    str2 = str;
                }
            }
        }
        Map hashMap = new HashMap();
        hashMap.put("Type", obj);
        hashMap.put("Value", this.f10634r.getSurchargeAmount());
        hashMap.put("Cab category", this.d);
        Leanplum.track("Surcharge Pop Up Shown", hashMap);
        Leanplum.advanceTo("surcharge new confirmation panel shown");
    }

    public void onClick(View view) {
        super.onClick(view);
        OLog.m13313b("Clicked", new Object[0]);
        switch (view.getId()) {
            case R.id.button_ride_conform:
                this.q.setClickable(false);
                if (this.f10634r != null) {
                    Map hashMap = new HashMap();
                    hashMap.put("surcharge amount", this.j);
                    m14179a(this.f10634r.getSurchargeType(), this.f10634r.getSurchargeAmount());
                    Leanplum.advanceTo(null);
                    Leanplum.track("booking confirm surcharge", hashMap);
                }
            case R.id.item_ride_card:
                m14252c(view);
            default:
        }
    }

    private void m14252c(View view) {
        if (this.f10634r != null) {
            boolean equalsIgnoreCase = this.f10634r.getSurchargeType().equalsIgnoreCase("multiplier");
            View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_cab_rate_card_dynamic, null, false);
            TextView textView = (TextView) inflate.findViewById(R.id.item_options);
            ((TextView) inflate.findViewById(R.id.item_category)).setText(this.d);
            OLog.m13313b("Category : " + this.d, new Object[0]);
            ErrorView a = new ErrorView.ErrorView(getActivity()).m14826b(getParentFragment().getView()).m14823a(inflate).m14825a();
            a.m14833a((ErrorView.ErrorView) this);
            if (!this.n) {
                this.n = true;
                a.m14831a(view);
                m14253a(inflate, this.f10634r, equalsIgnoreCase);
                textView.setText(this.f10634r.getCarNames());
            }
        }
    }

    protected void m14253a(View view, CityBaseCarModelDetailsResponse cityBaseCarModelDetailsResponse, boolean z) {
        String string = getResources().getString(R.string.rs_symbol);
        OLog.m13311a("surcharge value " + cityBaseCarModelDetailsResponse.getSurchargeAmount(), new Object[0]);
        OLog.m13311a("surcharge text " + cityBaseCarModelDetailsResponse.getSurchargeText(), new Object[0]);
        TextView textView = (TextView) view.findViewById(R.id.item_base_fair_text);
        TextView textView2 = (TextView) view.findViewById(R.id.item_base_fair);
        TextView textView3 = (TextView) view.findViewById(R.id.item_rate_per_km);
        TextView textView4 = (TextView) view.findViewById(R.id.item_rate_per_km_text);
        TextView textView5 = (TextView) view.findViewById(R.id.item_rate_per_wait_minute);
        TextView textView6 = (TextView) view.findViewById(R.id.item_rate_per_wait_minute_text);
        TextView textView7 = (TextView) view.findViewById(R.id.fare_header_txt);
        TextView textView8 = (TextView) view.findViewById(R.id.item_desclaimer_text);
        CharSequence header = cityBaseCarModelDetailsResponse != null ? !Trace.NULL.equalsIgnoreCase(cityBaseCarModelDetailsResponse.getHeader()) ? cityBaseCarModelDetailsResponse.getHeader() : getActivity().getString(R.string.ride_confirm_fare_breakup_header) : getActivity().getString(R.string.ride_confirm_fare_breakup_header);
        textView7.setText(header);
        CharSequence note = cityBaseCarModelDetailsResponse != null ? !Trace.NULL.equalsIgnoreCase(cityBaseCarModelDetailsResponse.getNote()) ? cityBaseCarModelDetailsResponse.getNote() : getActivity().getString(R.string.ride_confirm_fare_breakup_note) : getActivity().getString(R.string.ride_confirm_fare_breakup_note);
        textView8.setText(note);
        textView.setText(((aa) cityBaseCarModelDetailsResponse.getCityBaseFareBreakUp().get(0)).getDisplayText());
        textView2.setText(string + ((aa) cityBaseCarModelDetailsResponse.getCityBaseFareBreakUp().get(0)).getValue());
        textView4.setText(((aa) cityBaseCarModelDetailsResponse.getCityBaseFareBreakUp().get(1)).getDisplayText());
        textView3.setText(string + ((aa) cityBaseCarModelDetailsResponse.getCityBaseFareBreakUp().get(1)).getValue() + "/km");
        textView6.setText(((aa) cityBaseCarModelDetailsResponse.getCityBaseFareBreakUp().get(2)).getDisplayText());
        textView5.setText(string + ((aa) cityBaseCarModelDetailsResponse.getCityBaseFareBreakUp().get(2)).getValue() + "/min");
        textView = (TextView) view.findViewById(R.id.item_desclaimer_text1);
        if (z) {
            if (cityBaseCarModelDetailsResponse.getSurchargeText() != null) {
                textView.setText(cityBaseCarModelDetailsResponse.getSurchargeText());
            } else {
                textView.setText(cityBaseCarModelDetailsResponse.getSurchargeAmount() + getActivity().getString(R.string.multiplier_included));
            }
        } else if (cityBaseCarModelDetailsResponse.getSurchargeText() != null) {
            textView.setText(cityBaseCarModelDetailsResponse.getSurchargeText());
        } else {
            textView.setText(string + cityBaseCarModelDetailsResponse.getSurchargeAmount() + getActivity().getString(R.string.flat_included));
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_peak_charge_selected, 0, 0, 0);
        textView.setCompoundDrawablePadding(10);
    }
}
