package com.olacabs.customer.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.R;
import com.olacabs.customer.p076d.co;
import com.olacabs.customer.utils.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* compiled from: RidesListViewAdapter */
public class ag extends BaseAdapter {
    private Context f10357a;
    private ArrayList<co> f10358b;

    /* renamed from: com.olacabs.customer.ui.ag.a */
    public class RidesListViewAdapter {
        public TextView f10353a;
        public TextView f10354b;
        public ImageView f10355c;
        final /* synthetic */ ag f10356d;

        public RidesListViewAdapter(ag agVar) {
            this.f10356d = agVar;
        }
    }

    public ag(Context context, ArrayList<co> arrayList) {
        this.f10357a = context;
        this.f10358b = arrayList;
    }

    public int getCount() {
        return this.f10358b.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean isEmpty() {
        return this.f10358b.isEmpty();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        RidesListViewAdapter ridesListViewAdapter;
        CharSequence charSequence;
        if (view == null) {
            ridesListViewAdapter = new RidesListViewAdapter(this);
            view = ((Activity) this.f10357a).getLayoutInflater().inflate(R.layout.item_rides_category, viewGroup, false);
            ridesListViewAdapter.f10353a = (TextView) view.findViewById(R.id.pickUpTimeText);
            ridesListViewAdapter.f10354b = (TextView) view.findViewById(R.id.pickUpDateText);
            ridesListViewAdapter.f10355c = (ImageView) view.findViewById(R.id.pic);
            view.setTag(ridesListViewAdapter);
        } else {
            ridesListViewAdapter = (RidesListViewAdapter) view.getTag();
        }
        StringBuilder stringBuilder = new StringBuilder(Trace.NULL);
        Date date = new Date(Long.parseLong(((co) this.f10358b.get(i)).getPickup_time()) * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a ");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE, dd MMM");
        if (((co) this.f10358b.get(i)).getFavName() == null) {
            stringBuilder.append(simpleDateFormat.format(date) + " from " + ((co) this.f10358b.get(i)).getPickup_address());
        } else {
            stringBuilder.append(simpleDateFormat.format(date) + " from " + ((co) this.f10358b.get(i)).getFavName());
        }
        ridesListViewAdapter.f10353a.setText(stringBuilder.toString());
        int a = (int) m14049a(date.getTime(), 6);
        if (a == 0) {
            charSequence = "Today";
        } else if (a == -1) {
            charSequence = "Yesterday";
        } else if (a == 1) {
            charSequence = "Tomorrow";
        } else {
            charSequence = simpleDateFormat2.format(date);
        }
        ridesListViewAdapter.f10354b.setText(charSequence);
        if (Constants.RIDE_STATUS_COMPLETED.equals(((co) this.f10358b.get(i)).getStatus()) || Constants.RIDE_STATUS_CANCELLED.equals(((co) this.f10358b.get(i)).getStatus())) {
            ridesListViewAdapter.f10355c.setImageResource(R.drawable.ic_ride_clock_icon);
        } else if (Constants.RIDE_STATUS_CONFIRMED.equals(((co) this.f10358b.get(i)).getStatus()) || "ALLOTMENT_PENDING".equals(((co) this.f10358b.get(i)).getStatus())) {
            ridesListViewAdapter.f10355c.setImageResource(R.drawable.ic_clock_icon_upcoming);
        } else if (Constants.RIDE_STATUS_IN_PROGRESS.equals(((co) this.f10358b.get(i)).getStatus()) || Constants.RIDE_STATUS_SCHEDULED.equals(((co) this.f10358b.get(i)).getStatus())) {
            ridesListViewAdapter.f10355c.setImageResource(R.drawable.ic_clock_icon_running);
        }
        return view;
    }

    public long m14049a(long j, int i) {
        long a = m14048a(i);
        return (j / a) - (Calendar.getInstance(TimeZone.getTimeZone("IST")).getTimeInMillis() / a);
    }

    private long m14048a(int i) {
        Calendar instance = Calendar.getInstance();
        long timeInMillis = instance.getTimeInMillis();
        instance.add(i, 1);
        return instance.getTimeInMillis() - timeInMillis;
    }
}
