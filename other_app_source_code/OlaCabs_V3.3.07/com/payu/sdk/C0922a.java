package com.payu.sdk;

import com.newrelic.agent.android.instrumentation.Trace;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.payu.sdk.a */
public class C0922a {
    public static Set<String> f11520a;

    static {
        f11520a = new HashSet();
        f11520a.add("504435");
        f11520a.add("504645");
        f11520a.add("504645");
        f11520a.add("504775");
        f11520a.add("504809");
        f11520a.add("504993");
        f11520a.add("600206");
        f11520a.add("603845");
        f11520a.add("622018");
    }

    public static Boolean m14938a(String str) {
        return str.length() < 12 ? Boolean.valueOf(false) : (C0922a.m14940c(str).contentEquals("VISA") && str.length() == 16) ? C0922a.m14939b(str) : (C0922a.m14940c(str).contentEquals("MAST") && str.length() == 16) ? C0922a.m14939b(str) : ((C0922a.m14940c(str).contentEquals("MAES") || C0922a.m14940c(str).contentEquals("SMAE")) && str.length() >= 12 && str.length() <= 19) ? C0922a.m14939b(str) : (C0922a.m14940c(str).contentEquals("DINR") && str.length() == 14) ? C0922a.m14939b(str) : (C0922a.m14940c(str).contentEquals("AMEX") && str.length() == 15) ? C0922a.m14939b(str) : (C0922a.m14940c(str).contentEquals("JCB") && str.length() == 16) ? C0922a.m14939b(str) : Boolean.valueOf(false);
    }

    public static Boolean m14939b(String str) {
        int length = str.length() - 1;
        boolean z = false;
        int i = 0;
        while (length >= 0) {
            int parseInt = Integer.parseInt(str.substring(length, length + 1));
            if (z) {
                parseInt *= 2;
                if (parseInt > 9) {
                    parseInt = (parseInt % 10) + 1;
                }
            }
            i += parseInt;
            length--;
            z = !z;
        }
        return i % 10 == 0 ? Boolean.valueOf(true) : Boolean.valueOf(false);
    }

    public static String m14940c(String str) {
        return str.startsWith("4") ? "VISA" : str.matches("^((6304)|(6706)|(6771)|(6709))[\\d]+") ? "LASER" : str.matches("6(?:011|5[0-9]{2})[0-9]{12}[\\d]+") ? "LASER" : (str.matches("(5[06-8]|6\\d)\\d{14}(\\d{2,3})?[\\d]+") || str.matches("(5[06-8]|6\\d)[\\d]+") || str.matches("((504([435|645|774|775|809|993]))|(60([0206]|[3845]))|(622[018])\\d)[\\d]+")) ? (str.length() <= 6 || !f11520a.contains(str.substring(0, 6))) ? "MAES" : "SMAE" : str.matches("^5[1-5][\\d]+") ? "MAST" : str.matches("^3[47][\\d]+") ? "AMEX" : (str.startsWith("36") || str.matches("^30[0-5][\\d]+") || str.matches("2(014|149)[\\d]+")) ? "DINR" : str.matches("^35(2[89]|[3-8][0-9])[\\d]+") ? "JCB" : Trace.NULL;
    }
}
