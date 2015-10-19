package com.olacabs.customer.p076d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: Fares */
/* renamed from: com.olacabs.customer.d.av */
public class av {
    private static final String JSON_ARRAY_TAG = "fares";
    private static final String JSON_OBJECT_TAG = "fares";
    public static final String TAG;
    public HashMap<String, ArrayList<au>> fares;

    static {
        TAG = as.class.getSimpleName();
    }

    public au getFaresForCity(String str, String str2) {
        if (this.fares == null || this.fares.size() == 0) {
            return null;
        }
        if (!this.fares.containsKey(str)) {
            return null;
        }
        Iterator it = ((ArrayList) this.fares.get(str)).iterator();
        while (it.hasNext()) {
            au auVar = (au) it.next();
            if (auVar.getId().equalsIgnoreCase(str2)) {
                return auVar;
            }
        }
        return null;
    }

    public String getJsonObjectTag() {
        return JSON_OBJECT_TAG;
    }

    public String getJsonArrayTag() {
        return JSON_OBJECT_TAG;
    }
}
