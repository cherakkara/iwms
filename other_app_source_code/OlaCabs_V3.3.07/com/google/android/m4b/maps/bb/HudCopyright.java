package com.google.android.m4b.maps.bb;

import android.content.res.Resources;
import android.widget.TextView;
import com.google.android.m4b.maps.R.R;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: com.google.android.m4b.maps.bb.c */
public final class HudCopyright {
    private final Resources f5403a;
    private final TextView f5404b;

    /* renamed from: com.google.android.m4b.maps.bb.c.1 */
    class HudCopyright implements Runnable {
        private /* synthetic */ String f5401a;
        private /* synthetic */ HudCopyright f5402b;

        HudCopyright(HudCopyright hudCopyright, String str) {
            this.f5402b = hudCopyright;
            this.f5401a = str;
        }

        public final void run() {
            this.f5402b.f5404b.setText(this.f5401a);
        }
    }

    public HudCopyright(Resources resources, TextView textView) {
        this.f5403a = resources;
        this.f5404b = textView;
    }

    private static String m8181a(HashSet<String> hashSet) {
        if (hashSet.size() == 0) {
            return Trace.NULL;
        }
        Iterator it = hashSet.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        while (it.hasNext()) {
            Object obj2;
            if (obj == null) {
                stringBuilder.append(", ");
                obj2 = obj;
            } else {
                obj2 = null;
            }
            stringBuilder.append((String) it.next());
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    public final void m8182a(HashSet<String> hashSet, HashSet<String> hashSet2, int i) {
        if (i == -1) {
            i = Calendar.getInstance().get(1);
        }
        String a = HudCopyright.m8181a((HashSet) hashSet);
        String a2 = HudCopyright.m8181a((HashSet) hashSet2);
        if (!hashSet.isEmpty() && !hashSet2.isEmpty()) {
            a = this.f5403a.getString(R.dav_map_copyrights_full, new Object[]{Integer.valueOf(i), Integer.valueOf(i), a2, Integer.valueOf(i), a});
        } else if (hashSet.isEmpty() && hashSet2.isEmpty()) {
            a = this.f5403a.getString(R.dav_map_copyrights_google_only, new Object[]{Integer.valueOf(i)});
        } else if (hashSet2.isEmpty()) {
            a = this.f5403a.getString(R.dav_map_copyrights_map_data_only, new Object[]{Integer.valueOf(i), Integer.valueOf(i), a});
        } else {
            a = this.f5403a.getString(R.dav_map_copyrights_imagery_only, new Object[]{Integer.valueOf(i), Integer.valueOf(i), a2});
        }
        this.f5404b.post(new HudCopyright(this, a.replaceAll("&copy;", "\u00a9")));
    }
}
