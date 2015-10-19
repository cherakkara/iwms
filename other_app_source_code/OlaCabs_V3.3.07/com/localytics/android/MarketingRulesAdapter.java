package com.localytics.android;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class MarketingRulesAdapter extends BaseAdapter {
    private final Context mContext;
    private final MarketingHandler mHandler;
    private final List<MarketingMessage> mMarketingRules;

    /* renamed from: com.localytics.android.MarketingRulesAdapter.1 */
    class C07151 implements Callable<Boolean> {
        C07151() {
        }

        public Boolean call() throws Exception {
            boolean z = false;
            for (MarketingMessage add : MarketingRulesAdapter.this.mHandler.getMarketingMessages()) {
                MarketingRulesAdapter.this.mMarketingRules.add(add);
                z = true;
            }
            return Boolean.valueOf(z);
        }
    }

    /* renamed from: com.localytics.android.MarketingRulesAdapter.2 */
    class C07162 extends Thread {
        final /* synthetic */ FutureTask val$fTask;

        C07162(FutureTask futureTask) {
            this.val$fTask = futureTask;
        }

        public void run() {
            try {
                this.val$fTask.run();
            } catch (Throwable e) {
                Log.m12802w("Caught an exception", e);
            }
        }
    }

    MarketingRulesAdapter(Context context, MarketingHandler marketingHandler) {
        this.mContext = context;
        this.mMarketingRules = new ArrayList();
        this.mHandler = marketingHandler;
    }

    boolean updateDataSet() {
        this.mMarketingRules.clear();
        FutureTask futureTask = new FutureTask(new C07151());
        new C07162(futureTask).start();
        try {
            return ((Boolean) futureTask.get()).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    public int getCount() {
        return this.mMarketingRules.size();
    }

    public Map<String, Object> getItem(int i) {
        return (Map) this.mMarketingRules.get(i);
    }

    public long getItemId(int i) {
        return (long) ((Integer) ((MarketingMessage) this.mMarketingRules.get(i)).get("campaign_id")).intValue();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new LinearLayout(this.mContext);
            view.setLayoutParams(new LayoutParams(-1, -2));
            view.setOrientation(0);
            View linearLayout = new LinearLayout(this.mContext);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            linearLayout.setGravity(16);
            linearLayout.setOrientation(1);
            int i2 = (int) ((this.mContext.getResources().getDisplayMetrics().density * 8.0f) + 0.5f);
            linearLayout.setPadding(i2 << 1, i2, i2 << 1, i2);
            view.addView(linearLayout);
            View textView = new TextView(this.mContext);
            textView.setId(1);
            textView.setTextSize(16.0f);
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            View textView2 = new TextView(this.mContext);
            textView2.setId(2);
            textView2.setTextSize(24.0f);
            textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            linearLayout.addView(textView2);
            linearLayout.addView(textView);
        }
        TextView textView3 = (TextView) view.findViewById(2);
        ((TextView) view.findViewById(1)).setText(String.format("Campaign ID: %d", new Object[]{Long.valueOf(getItemId(i))}));
        textView3.setText((String) getItem(i).get("rule_name_non_unique"));
        return view;
    }
}
