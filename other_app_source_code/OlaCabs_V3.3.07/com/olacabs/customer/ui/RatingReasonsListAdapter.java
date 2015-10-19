package com.olacabs.customer.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/* renamed from: com.olacabs.customer.ui.z */
public class RatingReasonsListAdapter extends BaseAdapter {
    private ArrayList<String> f11463a;
    private HashMap<Integer, Boolean> f11464b;
    private LayoutInflater f11465c;
    private Context f11466d;
    private boolean f11467e;

    /* renamed from: com.olacabs.customer.ui.z.1 */
    class RatingReasonsListAdapter implements OnClickListener {
        final /* synthetic */ RatingReasonsListAdapter f11456a;
        final /* synthetic */ RatingReasonsListAdapter f11457b;

        RatingReasonsListAdapter(RatingReasonsListAdapter ratingReasonsListAdapter, RatingReasonsListAdapter ratingReasonsListAdapter2) {
            this.f11457b = ratingReasonsListAdapter;
            this.f11456a = ratingReasonsListAdapter2;
        }

        public void onClick(View view) {
            if (this.f11456a.f11461b.isChecked()) {
                this.f11456a.f11461b.setChecked(false);
            } else {
                this.f11456a.f11461b.setChecked(true);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.z.2 */
    class RatingReasonsListAdapter implements OnCheckedChangeListener {
        final /* synthetic */ int f11458a;
        final /* synthetic */ RatingReasonsListAdapter f11459b;

        RatingReasonsListAdapter(RatingReasonsListAdapter ratingReasonsListAdapter, int i) {
            this.f11459b = ratingReasonsListAdapter;
            this.f11458a = i;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f11459b.f11464b.put(Integer.valueOf(this.f11458a), Boolean.valueOf(z));
        }
    }

    /* renamed from: com.olacabs.customer.ui.z.a */
    private class RatingReasonsListAdapter {
        protected TextView f11460a;
        protected CheckBox f11461b;
        final /* synthetic */ RatingReasonsListAdapter f11462c;

        private RatingReasonsListAdapter(RatingReasonsListAdapter ratingReasonsListAdapter) {
            this.f11462c = ratingReasonsListAdapter;
        }
    }

    public RatingReasonsListAdapter(Context context, ArrayList<String> arrayList, ArrayList<Integer> arrayList2) {
        int i = 0;
        this.f11464b = new HashMap();
        this.f11467e = false;
        this.f11466d = context;
        this.f11463a = arrayList;
        while (i < arrayList2.size()) {
            this.f11464b.put(arrayList2.get(i), Boolean.valueOf(true));
            i++;
        }
        this.f11465c = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public void m14893a(ArrayList<String> arrayList, ArrayList<Integer> arrayList2, boolean z) {
        this.f11463a = arrayList;
        this.f11467e = z;
        for (int i = 0; i < arrayList2.size(); i++) {
            this.f11464b.put(arrayList2.get(i), Boolean.valueOf(true));
        }
    }

    public int getCount() {
        return this.f11463a.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        RatingReasonsListAdapter ratingReasonsListAdapter;
        if (view == null) {
            ratingReasonsListAdapter = new RatingReasonsListAdapter();
            view = this.f11465c.inflate(R.layout.view_rate_ride_list_item, null);
            ratingReasonsListAdapter.f11460a = (TextView) view.findViewById(R.id.issue_text);
            ratingReasonsListAdapter.f11461b = (CheckBox) view.findViewById(R.id.issue_check);
            view.setTag(ratingReasonsListAdapter);
            Animation loadAnimation = AnimationUtils.loadAnimation(this.f11466d, R.anim.rating_list_left_to_right);
            loadAnimation.setDuration(300);
            loadAnimation.setStartOffset((long) (i * 100));
            ratingReasonsListAdapter.f11460a.startAnimation(loadAnimation);
            loadAnimation = AnimationUtils.loadAnimation(this.f11466d, R.anim.rating_list_right_to_left);
            loadAnimation.setDuration(300);
            loadAnimation.setDuration((long) (i * 100));
            ratingReasonsListAdapter.f11461b.startAnimation(loadAnimation);
        } else {
            ratingReasonsListAdapter = (RatingReasonsListAdapter) view.getTag();
        }
        String str = (String) this.f11463a.get(i);
        if (str != null) {
            if (ratingReasonsListAdapter.f11460a != null) {
                ratingReasonsListAdapter.f11460a.setText(str);
            }
            if (str.equalsIgnoreCase("Driver overcharged") && this.f11467e) {
                view.setEnabled(false);
                ratingReasonsListAdapter.f11460a.setTextColor(this.f11466d.getResources().getColor(R.color.ola_vm_text_color));
                ratingReasonsListAdapter.f11461b.setEnabled(false);
            } else {
                view.setEnabled(true);
                ratingReasonsListAdapter.f11460a.setTextColor(this.f11466d.getResources().getColor(R.color.ola_black_text));
                ratingReasonsListAdapter.f11461b.setEnabled(true);
            }
        }
        ratingReasonsListAdapter.f11461b.setOnCheckedChangeListener(null);
        if (this.f11464b.containsKey(Integer.valueOf(i)) && ((Boolean) this.f11464b.get(Integer.valueOf(i))).booleanValue()) {
            ratingReasonsListAdapter.f11461b.setChecked(true);
        } else {
            ratingReasonsListAdapter.f11461b.setChecked(false);
        }
        view.setOnClickListener(new RatingReasonsListAdapter(this, ratingReasonsListAdapter));
        ratingReasonsListAdapter.f11461b.setOnCheckedChangeListener(new RatingReasonsListAdapter(this, i));
        return view;
    }

    public String m14892a() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry entry : this.f11464b.entrySet()) {
            if (((Boolean) entry.getValue()).booleanValue()) {
                stringBuffer.append(entry.getKey());
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2 != null ? stringBuffer2 : Trace.NULL;
    }

    public ArrayList<Integer> m14894b() {
        ArrayList<Integer> arrayList = new ArrayList();
        for (Entry entry : this.f11464b.entrySet()) {
            if (((Boolean) entry.getValue()).booleanValue()) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }
}
