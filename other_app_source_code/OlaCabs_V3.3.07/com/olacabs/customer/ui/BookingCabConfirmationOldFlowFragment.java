package com.olacabs.customer.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.leanplum.Leanplum;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.R;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.CityBaseCarModelDetailsResponse;
import com.olacabs.customer.p076d.aa;
import com.olacabs.customer.p076d.af;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.ui.widgets.ErrorView;
import com.olacabs.customer.utils.Utils;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* renamed from: com.olacabs.customer.ui.g */
public class BookingCabConfirmationOldFlowFragment extends BaseBookingCabConfirmationFragment {
    private aj f10636r;

    /* renamed from: com.olacabs.customer.ui.g.1 */
    class BookingCabConfirmationOldFlowFragment implements aj {
        final /* synthetic */ BookingCabConfirmationOldFlowFragment f10635a;

        BookingCabConfirmationOldFlowFragment(BookingCabConfirmationOldFlowFragment bookingCabConfirmationOldFlowFragment) {
            this.f10635a = bookingCabConfirmationOldFlowFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to fetch surcharge", th);
            this.f10635a.l.dismiss();
            this.f10635a.q.setClickable(true);
            HashMap hashMap = new HashMap();
            if (Utils.m14924g(this.f10635a.i)) {
                hashMap.put("Booking city", this.f10635a.i);
            } else {
                hashMap.put("Booking city", "NA");
            }
            Sherlock.m13338a("Ins booked ride", "Failure", null, this.f10635a.getActivity().getString(R.string.generic_failure_desc), true, hashMap);
            this.f10635a.m14181b(this.f10635a.getActivity().getString(R.string.generic_failure_header), this.f10635a.getActivity().getString(R.string.generic_failure_desc));
        }

        public void onSuccess(Object obj) {
            this.f10635a.l.dismiss();
            this.f10635a.q.setClickable(true);
            this.f10635a.m14258b((af) obj);
        }
    }

    public BookingCabConfirmationOldFlowFragment() {
        this.f10636r = new BookingCabConfirmationOldFlowFragment(this);
    }

    public static BookingCabConfirmationOldFlowFragment m14254a(String str, String str2, String str3, String str4, String str5, String str6, long j, boolean z) {
        BookingCabConfirmationOldFlowFragment bookingCabConfirmationOldFlowFragment = new BookingCabConfirmationOldFlowFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category_id", str);
        bundle.putString("category_name", str2);
        bundle.putString("category_base_rate", str3);
        bundle.putString("category_rate_per_km", str4);
        bundle.putLong("pick_up_time", j);
        bundle.putBoolean("is_ride_now", z);
        bundle.putString("location_tag", str6);
        bundle.putString("current_city", str5);
        bookingCabConfirmationOldFlowFragment.setArguments(bundle);
        return bookingCabConfirmationOldFlowFragment;
    }

    private void m14258b(af afVar) {
        if (afVar.getStatus().equalsIgnoreCase("SUCCESS")) {
            if (afVar.isPeakSurchargeApplicable()) {
                this.k = afVar.isPeakSurchargeApplicable();
                this.j = afVar.getSurchargeAmount();
                Leanplum.advanceTo("surcharge popup shown");
                m14178a(afVar);
                return;
            }
            this.k = false;
            this.j = Trace.NULL;
            m14179a("None", "None");
        } else if (afVar.getStatus().equalsIgnoreCase("FAILURE")) {
            HashMap hashMap = new HashMap();
            if (Utils.m14924g(this.i)) {
                hashMap.put("Booking city", this.i);
            } else {
                hashMap.put("Booking city", "NA");
            }
            Sherlock.m13338a("Ins booked ride", "Failure", null, getActivity().getString(R.string.generic_failure_desc), true, hashMap);
            m14181b(getActivity().getString(R.string.generic_failure_header), getActivity().getString(R.string.generic_failure_desc));
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        OLog.m13313b("Clicked", new Object[0]);
        switch (view.getId()) {
            case R.id.button_ride_conform:
                this.q.setClickable(false);
                m14259c();
            case R.id.item_ride_card:
                m14257b(view);
            default:
        }
    }

    private void m14257b(View view) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_cab_rate_card_dynamic, null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.item_options);
        ((TextView) inflate.findViewById(R.id.item_category)).setText(this.d);
        OLog.m13313b("Category : " + this.d, new Object[0]);
        ErrorView a = new ErrorView.ErrorView(getActivity()).m14826b(getParentFragment().getView()).m14823a(inflate).m14825a();
        a.m14833a((ErrorView.ErrorView) this);
        if (!this.n) {
            this.n = true;
            a.m14831a(view);
            if (this.m.m13218d().getCityBasedFareModels() != null) {
                try {
                    m14255a(inflate, this.m.m13218d().getCityBasedFareModels().getCarModels().getCategoryDetails(this.c));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (this.m.m13218d().getCityBasedFareModels() != null) {
            try {
                textView.setText(this.m.m13218d().getCityBasedFareModels().getCarModels().getCategoryDetails(this.c).getCarNames());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void m14255a(View view, CityBaseCarModelDetailsResponse cityBaseCarModelDetailsResponse) {
        String string = getResources().getString(R.string.rs_symbol);
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
    }

    protected void m14259c() {
        this.l.show();
        this.m.m13183a(new WeakReference(this.f10636r), this.i, this.c, String.valueOf(this.e / 1000), ((BookingFragment) getParentFragment()).m14367e(), this.b, a);
    }
}
