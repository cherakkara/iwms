package com.olacabs.p067a.p068a.p069a.p070a;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p071b.GenericUtilities;
import com.olacabs.p067a.p068a.p069a.p073c.BatteryStat;
import com.olacabs.p067a.p068a.p069a.p074d.MegatronCore;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.olacabs.a.a.a.a.q */
public class SessionInfo {
    private static transient int mNumForegroundActivities;
    private HashMap<Long, ActivityInfo> mActivityHash;
    private List<ActivityListInfo> mActivityInfo;
    private ArrayList<BatteryListInfo> mBatteryInfo;
    private BatteryStat mBatteryStat;
    Context mContext;
    private EnvironmentInfo mEnvironmentInfo;
    private HashMap<Long, FragmentInfo> mFragmentHash;
    private List<FragmentListInfo> mFragmentInfo;
    private Gson mGson;
    private ArrayList<NetworkListInfo> mNetworkInfo;
    private String mSessionId;

    /* renamed from: com.olacabs.a.a.a.a.q.1 */
    static /* synthetic */ class SessionInfo {
        static final /* synthetic */ int[] f8904a;

        static {
            f8904a = new int[SessionInfo.values().length];
            try {
                f8904a[SessionInfo.ACTIVITY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8904a[SessionInfo.FRAGMENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8904a[SessionInfo.NETWORK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8904a[SessionInfo.BATTERY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* renamed from: com.olacabs.a.a.a.a.q.a */
    public enum SessionInfo {
        ACTIVITY(3),
        FRAGMENT(20),
        NETWORK(8),
        BATTERY(5);
        
        private int value;

        private SessionInfo(int i) {
            this.value = i;
        }
    }

    public SessionInfo(Context context) {
        this.mFragmentInfo = new ArrayList();
        this.mActivityHash = new HashMap();
        this.mFragmentHash = new HashMap();
        this.mNetworkInfo = new ArrayList();
        this.mActivityInfo = new ArrayList();
        this.mBatteryInfo = new ArrayList();
        this.mContext = context;
        this.mGson = new Gson();
    }

    static {
        mNumForegroundActivities = 0;
    }

    public void setSessionId(String str) {
        this.mSessionId = str;
        try {
            if (this.mBatteryStat != null) {
                this.mBatteryStat.cancel();
            }
            this.mBatteryStat = new BatteryStat(this.mContext, str);
            this.mEnvironmentInfo = new EnvironmentInfo(this.mContext, str);
        } catch (Exception e) {
        }
    }

    private ActivityInfo getActivityCurrentElement(List<ActivityListInfo> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        if (size != 0) {
            return ((ActivityListInfo) list.get(size - 1)).getActivityInfo();
        }
        return null;
    }

    private FragmentInfo getFragmentCurrentElement(List<FragmentListInfo> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        if (size != 0) {
            return ((FragmentListInfo) list.get(size - 1)).getFragmentInfo();
        }
        return null;
    }

    private void createNewListEntry(boolean z, long j, long j2, String str, String str2) {
        if (z) {
            ActivityInfo activityInfo = new ActivityInfo();
            activityInfo.setStartTime(j2);
            activityInfo.setClassName(str);
            activityInfo.setLoadType(str2);
            activityInfo.setSessionId(this.mSessionId);
            activityInfo.setRAMUsage();
            this.mActivityHash.put(Long.valueOf(j), activityInfo);
            return;
        }
        FragmentInfo fragmentInfo = new FragmentInfo();
        fragmentInfo.setClassName(str);
        fragmentInfo.setStartTime(j2);
        fragmentInfo.setLoadType(str2);
        fragmentInfo.setSessionId(this.mSessionId);
        fragmentInfo.setRAMUsage();
        this.mFragmentHash.put(Long.valueOf(j), fragmentInfo);
    }

    private boolean isReload(Object obj, long j, String str) {
        if (obj instanceof ActivityInfo) {
            ActivityInfo activityInfo = (ActivityInfo) obj;
            if (activityInfo == null) {
                return false;
            }
            if (!str.equals(activityInfo.getClassName()) || 0 == activityInfo.getStartTime()) {
                return true;
            }
            return false;
        }
        FragmentInfo fragmentInfo = (FragmentInfo) obj;
        if (fragmentInfo == null) {
            return false;
        }
        if (!str.equals(fragmentInfo.getClassName()) || 0 == fragmentInfo.getStartTime()) {
            return true;
        }
        return false;
    }

    public void add(boolean z, long j, String str, String str2, boolean z2, long j2) {
        if (z) {
            if ("onCreate".equals(str2)) {
                createNewListEntry(true, j, j2, str, "Load");
            } else if ("onResume".equals(str2)) {
                ActivityInfo activityInfo = (ActivityInfo) this.mActivityHash.get(Long.valueOf(j));
                if (z2) {
                    if (activityInfo == null) {
                        createNewListEntry(true, j, j2, str, "Reload");
                    }
                    mNumForegroundActivities++;
                } else if (activityInfo != null) {
                    activityInfo.setLoadTime(j2);
                    this.mActivityInfo.add(new ActivityListInfo(activityInfo));
                    this.mActivityHash.remove(Long.valueOf(j));
                    if (shouldWriteInFile(SessionInfo.ACTIVITY)) {
                        MegatronCore.m12842e().m12854c(createJson(SessionInfo.ACTIVITY));
                    }
                }
            } else if ("onPause".equals(str2)) {
                mNumForegroundActivities--;
                MegatronCore.m12842e().m12857g();
            } else if ("onStart".equals(str2) && ((ActivityInfo) this.mActivityHash.get(Long.valueOf(j))) == null) {
                createNewListEntry(true, j, j2, str, "Reload");
            }
        } else if ("onCreate".equals(str2)) {
            createNewListEntry(false, j, j2, str, "Load");
        } else if ("onResume".equals(str2)) {
            FragmentInfo fragmentInfo = (FragmentInfo) this.mFragmentHash.get(Long.valueOf(j));
            if (z2) {
                if (fragmentInfo == null) {
                    createNewListEntry(false, j, j2, str, "Reload");
                }
            } else if (fragmentInfo != null) {
                fragmentInfo.setLoadTime(j2);
                this.mFragmentInfo.add(new FragmentListInfo(fragmentInfo));
                this.mFragmentHash.remove(Long.valueOf(j));
                if (shouldWriteInFile(SessionInfo.FRAGMENT)) {
                    MegatronCore.m12842e().m12854c(createJson(SessionInfo.FRAGMENT));
                }
            }
        } else if ("onStart".equals(str2)) {
            if (((FragmentInfo) this.mFragmentHash.get(Long.valueOf(j))) == null) {
                createNewListEntry(false, j, j2, str, "Reload");
            }
        } else if ("onCreateView".equals(str2) && ((FragmentInfo) this.mFragmentHash.get(Long.valueOf(j))) == null) {
            createNewListEntry(false, j, j2, str, "Reload");
        }
    }

    public void add(Context context, String str, int i, Map<String, String> map, byte[] bArr, int i2, String str2, long j, long j2) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Constants.PHONE);
        String networkOperatorName = telephonyManager.getNetworkOperatorName();
        telephonyManager.getNetworkType();
        NetworkInfo networkInfo = new NetworkInfo();
        networkInfo.setNetworkType(GenericUtilities.m12821a(context));
        networkInfo.setCarrierName(networkOperatorName);
        networkInfo.setRequestStartTime(j);
        networkInfo.setResponseEndTime(j2);
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setUrl(str);
        requestInfo.setBody(bArr);
        requestInfo.setMethodType(i);
        requestInfo.setHeaders(map);
        networkInfo.setRequestInfo(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setStatus(i2);
        if (199 >= i2 || HttpStatus.SC_MULTIPLE_CHOICES <= i2) {
            responseInfo.setErrorMessage(str2);
        } else {
            responseInfo.setResponsePayload(str2.getBytes());
        }
        networkInfo.setResponseInfo(responseInfo);
        networkInfo.setElapsedTime();
        networkInfo.setSessionId(this.mSessionId);
        this.mNetworkInfo.add(new NetworkListInfo(networkInfo));
        if (shouldWriteInFile(SessionInfo.NETWORK)) {
            MegatronCore.m12842e().m12854c(createJson(SessionInfo.NETWORK));
        }
    }

    public void addBatteryInfo(BatteryInfo batteryInfo) {
        batteryInfo.setSessionId(this.mSessionId);
        this.mBatteryInfo.add(new BatteryListInfo(batteryInfo));
    }

    public void endSession() {
        if (mNumForegroundActivities == 0) {
            MegatronCore.m12842e().m12854c(createJson(SessionInfo.ACTIVITY));
            MegatronCore.m12842e().m12854c(createJson(SessionInfo.FRAGMENT));
            MegatronCore.m12842e().m12854c(createJson(SessionInfo.NETWORK));
            MegatronCore.m12842e().m12854c(createJson(SessionInfo.BATTERY));
        }
    }

    public String createJson(SessionInfo sessionInfo) {
        int i = 0;
        JSONArray jSONArray = new JSONArray();
        String str = Trace.NULL;
        str = Trace.NULL;
        str = Trace.NULL;
        str = Trace.NULL;
        str = Trace.NULL;
        try {
            JSONArray init;
            switch (SessionInfo.f8904a[sessionInfo.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    if (!this.mActivityInfo.isEmpty()) {
                        str = this.mGson.m12346a(this.mActivityInfo);
                        this.mActivityInfo.clear();
                        init = JSONArrayInstrumentation.init(str);
                        while (i < init.length()) {
                            jSONArray.put(init.get(i));
                            i++;
                        }
                        if (this.mEnvironmentInfo != null) {
                            jSONArray.put(JSONObjectInstrumentation.init(this.mGson.m12346a(this.mEnvironmentInfo)));
                            break;
                        }
                    }
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    if (!this.mFragmentInfo.isEmpty()) {
                        str = this.mGson.m12346a(this.mFragmentInfo);
                        this.mFragmentInfo.clear();
                        init = JSONArrayInstrumentation.init(str);
                        while (i < init.length()) {
                            jSONArray.put(init.get(i));
                            i++;
                        }
                        if (this.mEnvironmentInfo != null) {
                            jSONArray.put(JSONObjectInstrumentation.init(this.mGson.m12346a(this.mEnvironmentInfo)));
                            break;
                        }
                    }
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    if (!this.mNetworkInfo.isEmpty()) {
                        str = this.mGson.m12346a(this.mNetworkInfo);
                        this.mNetworkInfo.clear();
                        init = JSONArrayInstrumentation.init(str);
                        while (i < init.length()) {
                            jSONArray.put(init.get(i));
                            i++;
                        }
                        if (this.mEnvironmentInfo != null) {
                            jSONArray.put(JSONObjectInstrumentation.init(this.mGson.m12346a(this.mEnvironmentInfo)));
                            break;
                        }
                    }
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    if (!this.mBatteryInfo.isEmpty()) {
                        str = this.mGson.m12346a(this.mBatteryInfo);
                        this.mBatteryInfo.clear();
                        init = JSONArrayInstrumentation.init(str);
                        while (i < init.length()) {
                            jSONArray.put(init.get(i));
                            i++;
                        }
                        if (this.mEnvironmentInfo != null) {
                            jSONArray.put(JSONObjectInstrumentation.init(this.mGson.m12346a(this.mEnvironmentInfo)));
                            break;
                        }
                    }
                    break;
            }
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (jSONArray instanceof JSONArray) {
            return JSONArrayInstrumentation.toString(jSONArray);
        }
        return jSONArray.toString();
    }

    private boolean shouldWriteInFile() {
        return ((this.mActivityInfo.size() + this.mFragmentInfo.size()) + this.mNetworkInfo.size()) + this.mBatteryInfo.size() > 10;
    }

    private boolean shouldWriteInFile(SessionInfo sessionInfo) {
        switch (SessionInfo.f8904a[sessionInfo.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                if (this.mActivityInfo.size() < sessionInfo.ordinal()) {
                    return false;
                }
                return true;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (this.mFragmentInfo.size() < sessionInfo.ordinal()) {
                    return false;
                }
                return true;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                if (this.mNetworkInfo.size() < sessionInfo.ordinal()) {
                    return false;
                }
                return true;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                if (this.mBatteryInfo.size() < sessionInfo.ordinal()) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }
}
