package com.localytics.android;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.webkit.JavascriptInterface;

final class JavaScriptClient {
    private final SparseArray<MarketingCallable> mCallbacks;

    /* renamed from: com.localytics.android.JavaScriptClient.1 */
    class C06901 implements Runnable {
        C06901() {
        }

        public void run() {
            JavaScriptClient.this.invoke(4, null);
        }
    }

    JavaScriptClient(SparseArray<MarketingCallable> sparseArray) {
        this.mCallbacks = sparseArray;
    }

    SparseArray<MarketingCallable> getCallbacks() {
        return this.mCallbacks;
    }

    String getJsGlueCode() {
        return String.format("javascript:(function() {  var localyticsScript = document.createElement('script');  localyticsScript.type = 'text/javascript';  localyticsScript.text = '  localytics.identifers = %s;  localytics.customDimensions = %s;  localytics.attributes = %s;  localytics.libraryVersion = \"%s\";  localytics.tagEvent = function(event, attributes, customerValueIncrease) {     localytics.nativeTagEvent(event, JSON.stringify(attributes), JSON.stringify(customerValueIncrease));  };  localytics.setCustomDimension = function(number, value) {     if (number != null && value != null)        localytics.nativeSetCustomDimension(number, value);  };  window.open = function(url) {     if (url != null)        localytics.navigate(url);  };  localytics.close = function() {     localytics.nativeClose();  };';  document.getElementsByTagName('body')[0].appendChild(localyticsScript);})()", new Object[]{getIdentifiers(), getCustomDimensions(), getAttributes(), Localytics.getLibraryVersion()});
    }

    String getIdentifiers() {
        return (String) invoke(5, null);
    }

    String getCustomDimensions() {
        return (String) invoke(6, null);
    }

    String getAttributes() {
        return (String) invoke(7, null);
    }

    private Object invoke(int i, Object[] objArr) {
        if (this.mCallbacks != null) {
            MarketingCallable marketingCallable = (MarketingCallable) this.mCallbacks.get(i);
            if (marketingCallable != null) {
                return marketingCallable.call(objArr);
            }
        }
        return null;
    }

    @JavascriptInterface
    public void nativeTagEvent(String str, String str2, String str3) {
        Log.m12801w("[JavaScriptClient]: nativeTagEvent is being called");
        invoke(3, new Object[]{str, str2, str3});
    }

    @JavascriptInterface
    public void nativeSetCustomDimension(long j, String str) {
        Log.m12801w("[JavaScriptClient]: nativeSetCustomDimension is being called");
        invoke(8, new Object[]{Integer.valueOf((int) j), str});
    }

    @JavascriptInterface
    public void navigate(String str) {
        invoke(15, new String[]{str});
    }

    @JavascriptInterface
    public void nativeClose() {
        Log.m12801w("[JavaScriptClient]: nativeClose is being called");
        new Handler(Looper.getMainLooper()).post(new C06901());
    }
}
