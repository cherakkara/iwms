package p004b.p005a.p006a.p007a.p008a.p010b;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;
import p004b.p005a.p006a.p007a.Kit;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpMethod;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequest;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;

/* renamed from: b.a.a.a.a.b.a */
public abstract class AbstractSpiCall {
    public static final String ACCEPT_JSON_VALUE = "application/json";
    public static final String ANDROID_CLIENT_TYPE = "android";
    public static final String CLS_ANDROID_SDK_DEVELOPER_TOKEN = "470fa2b4ae81cd56ecbcda9735803434cec591fa";
    public static final String CRASHLYTICS_USER_AGENT = "Crashlytics Android SDK/";
    public static final int DEFAULT_TIMEOUT = 10000;
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_API_KEY = "X-CRASHLYTICS-API-KEY";
    public static final String HEADER_CLIENT_TYPE = "X-CRASHLYTICS-API-CLIENT-TYPE";
    public static final String HEADER_CLIENT_VERSION = "X-CRASHLYTICS-API-CLIENT-VERSION";
    public static final String HEADER_D = "X-CRASHLYTICS-D";
    public static final String HEADER_DEVELOPER_TOKEN = "X-CRASHLYTICS-DEVELOPER-TOKEN";
    public static final String HEADER_DEVICE_STATE = "X-CRASHLYTICS-DEVICE-STATE";
    public static final String HEADER_REQUEST_ID = "X-REQUEST-ID";
    public static final String HEADER_USER_AGENT = "User-Agent";
    private static final Pattern PROTOCOL_AND_HOST_PATTERN;
    protected final Kit kit;
    private final HttpMethod method;
    private final String protocolAndHostOverride;
    private final HttpRequestFactory requestFactory;
    private final String url;

    static {
        PROTOCOL_AND_HOST_PATTERN = Pattern.compile("http(s?)://[^\\/]+", 2);
    }

    public AbstractSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        if (str2 == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (httpRequestFactory == null) {
            throw new IllegalArgumentException("requestFactory must not be null.");
        } else {
            this.kit = kit;
            this.protocolAndHostOverride = str;
            this.url = overrideProtocolAndHost(str2);
            this.requestFactory = httpRequestFactory;
            this.method = httpMethod;
        }
    }

    protected String getUrl() {
        return this.url;
    }

    protected HttpRequest getHttpRequest() {
        return getHttpRequest(Collections.emptyMap());
    }

    protected HttpRequest getHttpRequest(Map<String, String> map) {
        return this.requestFactory.m329a(this.method, getUrl(), map).m375a(false).m365a((int) DEFAULT_TIMEOUT).m368a(HEADER_USER_AGENT, CRASHLYTICS_USER_AGENT + this.kit.getVersion()).m368a(HEADER_DEVELOPER_TOKEN, CLS_ANDROID_SDK_DEVELOPER_TOKEN);
    }

    private String overrideProtocolAndHost(String str) {
        if (CommonUtils.m180c(this.protocolAndHostOverride)) {
            return str;
        }
        return PROTOCOL_AND_HOST_PATTERN.matcher(str).replaceFirst(this.protocolAndHostOverride);
    }
}
