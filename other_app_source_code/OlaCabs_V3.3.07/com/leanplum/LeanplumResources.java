package com.leanplum;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LeanplumResources extends Resources {
    public LeanplumResources(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
    }

    final <T> Var<T> m12501a(int i) {
        try {
            String resourceEntryName = getResourceEntryName(i);
            String resourceTypeName = getResourceTypeName(i);
            if (FileManager.f8547a == null) {
                return null;
            }
            String str;
            HashMap hashMap = (HashMap) FileManager.f8547a.objectForKeyPath(new Object[0]);
            Map hashMap2 = new HashMap();
            synchronized (aT.f8686a) {
                for (String str2 : hashMap.keySet()) {
                    if (str2.toLowerCase().startsWith(resourceTypeName)) {
                        Object obj = null;
                        for (String str3 : ((HashMap) hashMap.get(str2)).keySet()) {
                            String replace = str3.replace("\\.", ".");
                            int lastIndexOf = replace.lastIndexOf(46);
                            if (lastIndexOf >= 0) {
                                replace = replace.substring(0, lastIndexOf);
                            }
                            if (replace.equals(resourceEntryName)) {
                                obj = str3;
                            }
                        }
                        if (obj != null) {
                            hashMap2.put(str2, obj);
                        }
                    }
                }
            }
            Map hashMap3 = new HashMap();
            for (String str4 : hashMap2.keySet()) {
                hashMap3.put(str4, al.m12733a(str4));
            }
            Configuration configuration = getConfiguration();
            DisplayMetrics displayMetrics = getDisplayMetrics();
            Set hashSet = new HashSet();
            for (String str42 : hashMap2.keySet()) {
                al alVar = (al) hashMap3.get(str42);
                for (am amVar : alVar.f8749a.keySet()) {
                    if (amVar.m12734a().m12616a(alVar.f8749a.get(amVar), configuration)) {
                        hashSet.add(str42);
                    }
                }
            }
            am[] values = am.values();
            int length = values.length;
            Set set = hashSet;
            int i2 = 0;
            while (i2 < length) {
                Set set2;
                am amVar2 = values[i2];
                Map hashMap4 = new HashMap();
                for (String str422 : r2) {
                    Object obj2 = ((al) hashMap3.get(str422)).f8749a.get(amVar2);
                    if (obj2 != null) {
                        hashMap4.put(str422, obj2);
                    }
                }
                Map a = amVar2.m12734a().m12615a(hashMap4, displayMetrics);
                if (a.isEmpty()) {
                    set2 = r2;
                } else {
                    set2 = a.keySet();
                }
                i2++;
                set = set2;
            }
            Iterator it = hashMap2.keySet().iterator();
            if (it.hasNext()) {
                str422 = (String) it.next();
                return aT.m12672b("__Android Resources." + str422 + "." + ((String) hashMap2.get(str422)));
            }
            return null;
        } catch (Throwable e) {
            Log.e("Leanplum", "Error getting resource", e);
        }
    }

    public Drawable getDrawable(int i) {
        Var a = m12501a(i);
        if (a != null) {
            int overrideResId = a.overrideResId();
            if (overrideResId != 0) {
                return super.getDrawable(overrideResId);
            }
            if (!a.f8637a.equals(a.defaultValue())) {
                Drawable createFromStream = Drawable.createFromStream(a.stream(), a.fileValue());
                if (createFromStream != null) {
                    return createFromStream;
                }
            }
        }
        return super.getDrawable(i);
    }
}
