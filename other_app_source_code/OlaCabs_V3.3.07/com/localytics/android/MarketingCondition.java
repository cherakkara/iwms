package com.localytics.android;

import com.sothree.slidinguppanel.p086a.R.R;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

final class MarketingCondition {
    private final String mName;
    private final Opt mOpt;
    private String mPkgName;
    private final Vector<String> mValues;

    /* renamed from: com.localytics.android.MarketingCondition.1 */
    static /* synthetic */ class C06921 {
        static final /* synthetic */ int[] $SwitchMap$com$localytics$android$MarketingCondition$Opt;

        static {
            $SwitchMap$com$localytics$android$MarketingCondition$Opt = new int[Opt.values().length];
            try {
                $SwitchMap$com$localytics$android$MarketingCondition$Opt[Opt.EQUAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$localytics$android$MarketingCondition$Opt[Opt.NOT_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$localytics$android$MarketingCondition$Opt[Opt.IN_LIST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$localytics$android$MarketingCondition$Opt[Opt.GREATER_THAN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$localytics$android$MarketingCondition$Opt[Opt.GREATER_THEN_OR_EQUAL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$localytics$android$MarketingCondition$Opt[Opt.LESS_THAN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$localytics$android$MarketingCondition$Opt[Opt.LESS_THAN_OR_EQUAL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$localytics$android$MarketingCondition$Opt[Opt.BETWEEN.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$localytics$android$MarketingCondition$Opt[Opt.INVALID.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    enum Opt {
        INVALID,
        EQUAL,
        NOT_EQUAL,
        GREATER_THAN,
        GREATER_THEN_OR_EQUAL,
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        BETWEEN,
        IN_LIST
    }

    MarketingCondition(String str, String str2, Vector<String> vector) {
        this.mName = str;
        this.mOpt = stringToOperator(str2);
        this.mValues = vector;
    }

    private Opt stringToOperator(String str) {
        if (str.equals("eq")) {
            return Opt.EQUAL;
        }
        if (str.equals("neq")) {
            return Opt.NOT_EQUAL;
        }
        if (str.equals("gt")) {
            return Opt.GREATER_THAN;
        }
        if (str.equals("gte")) {
            return Opt.GREATER_THEN_OR_EQUAL;
        }
        if (str.equals("lt")) {
            return Opt.LESS_THAN;
        }
        if (str.equals("lte")) {
            return Opt.LESS_THAN_OR_EQUAL;
        }
        if (str.equals("btw")) {
            return Opt.BETWEEN;
        }
        if (str.equals("in")) {
            return Opt.IN_LIST;
        }
        return Opt.INVALID;
    }

    void setPackageName(String str) {
        this.mPkgName = str;
    }

    boolean isSatisfiedByAttributes(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        Object obj = map.get(this.mName);
        if (obj == null) {
            obj = map.get(this.mPkgName + ":" + this.mName);
        }
        if (obj == null) {
            Log.m12801w(String.format("Could not find the in-app condition %s in the attributes dictionary.", new Object[]{this.mName}));
            return false;
        }
        boolean isSatisfiedByString;
        if (obj instanceof String) {
            isSatisfiedByString = isSatisfiedByString((String) obj);
        } else if (obj instanceof Number) {
            isSatisfiedByString = isSatisfiedByNumber((String) obj);
        } else {
            Log.m12801w(String.format("Invalid value type %s in the attributes dictionary.", new Object[]{obj.getClass().getCanonicalName()}));
            isSatisfiedByString = false;
        }
        return isSatisfiedByString;
    }

    private boolean isSatisfiedByString(String str) {
        boolean z = true;
        switch (C06921.$SwitchMap$com$localytics$android$MarketingCondition$Opt[this.mOpt.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return str.equals(this.mValues.get(0));
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return !str.equals(this.mValues.get(0));
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                Iterator it = this.mValues.iterator();
                while (it.hasNext()) {
                    if (str.equals((String) it.next())) {
                        return z;
                    }
                }
                z = false;
                return z;
            default:
                return isSatisfiedByNumber(str);
        }
    }

    private boolean isSatisfiedByNumber(String str) {
        boolean z = false;
        BigDecimal bigDecimal = new BigDecimal(str);
        int compareTo = bigDecimal.compareTo(new BigDecimal((String) this.mValues.get(0)));
        int compareTo2 = this.mValues.size() > 1 ? bigDecimal.compareTo(new BigDecimal((String) this.mValues.get(1))) : 0;
        switch (C06921.$SwitchMap$com$localytics$android$MarketingCondition$Opt[this.mOpt.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                if (compareTo == 0) {
                    z = true;
                }
                return z;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (compareTo == 0) {
                    return false;
                }
                return true;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                Iterator it = this.mValues.iterator();
                while (it.hasNext()) {
                    if (bigDecimal.compareTo(new BigDecimal((String) it.next())) == 0) {
                        return true;
                    }
                }
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                if (compareTo <= 0) {
                    return false;
                }
                return true;
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                if (compareTo < 0) {
                    return false;
                }
                return true;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                if (compareTo >= 0) {
                    return false;
                }
                return true;
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                if (compareTo > 0) {
                    return false;
                }
                return true;
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                if (compareTo < 0 || compareTo2 > 0) {
                    return false;
                }
                return true;
        }
        return false;
    }

    private String operatorToString(Opt opt) {
        switch (C06921.$SwitchMap$com$localytics$android$MarketingCondition$Opt[opt.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return "is equal to";
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return "not equal to";
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return "is a member of the list";
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return "is greater than";
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return "is greater than or equal to";
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return "is less than";
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                return "is less than or equal to";
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                return "is in between values";
            default:
                return "INVALID OPERATOR";
        }
    }
}
