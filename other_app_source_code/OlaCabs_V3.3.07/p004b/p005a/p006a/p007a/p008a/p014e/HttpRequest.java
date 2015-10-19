package p004b.p005a.p006a.p007a.p008a.p014e;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpStatus;
import org.apache.http.entity.mime.MIME;
import org.apache.http.protocol.HTTP;

/* renamed from: b.a.a.a.a.e.d */
public class HttpRequest {
    private static final String[] f287b;
    private static HttpRequest f288c;
    public final URL f289a;
    private HttpURLConnection f290d;
    private final String f291e;
    private HttpRequest f292f;
    private boolean f293g;
    private boolean f294h;
    private boolean f295i;
    private int f296j;
    private String f297k;
    private int f298l;

    /* renamed from: b.a.a.a.a.e.d.d */
    protected static abstract class HttpRequest<V> implements Callable<V> {
        protected abstract V m338b() throws HttpRequest, IOException;

        protected abstract void m339c() throws IOException;

        protected HttpRequest() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V call() throws p004b.p005a.p006a.p007a.p008a.p014e.HttpRequest.HttpRequest {
            /*
            r3 = this;
            r1 = 1;
            r2 = 0;
            r0 = r3.m338b();	 Catch:{ c -> 0x0011, IOException -> 0x0018, all -> 0x0028 }
            r3.m339c();	 Catch:{ IOException -> 0x000a }
            return r0;
        L_0x000a:
            r0 = move-exception;
            r1 = new b.a.a.a.a.e.d$c;
            r1.<init>(r0);
            throw r1;
        L_0x0011:
            r0 = move-exception;
            throw r0;	 Catch:{ all -> 0x0013 }
        L_0x0013:
            r0 = move-exception;
        L_0x0014:
            r3.m339c();	 Catch:{ IOException -> 0x001f }
        L_0x0017:
            throw r0;
        L_0x0018:
            r0 = move-exception;
            r2 = new b.a.a.a.a.e.d$c;	 Catch:{ all -> 0x0013 }
            r2.<init>(r0);	 Catch:{ all -> 0x0013 }
            throw r2;	 Catch:{ all -> 0x0013 }
        L_0x001f:
            r2 = move-exception;
            if (r1 != 0) goto L_0x0017;
        L_0x0022:
            r0 = new b.a.a.a.a.e.d$c;
            r0.<init>(r2);
            throw r0;
        L_0x0028:
            r0 = move-exception;
            r1 = r2;
            goto L_0x0014;
            */
            throw new UnsupportedOperationException("Method not decompiled: b.a.a.a.a.e.d.d.call():V");
        }
    }

    /* renamed from: b.a.a.a.a.e.d.a */
    protected static abstract class HttpRequest<V> extends HttpRequest<V> {
        private final Closeable f280a;
        private final boolean f281b;

        protected HttpRequest(Closeable closeable, boolean z) {
            this.f280a = closeable;
            this.f281b = z;
        }

        protected void m340c() throws IOException {
            if (this.f280a instanceof Flushable) {
                ((Flushable) this.f280a).flush();
            }
            if (this.f281b) {
                try {
                    this.f280a.close();
                    return;
                } catch (IOException e) {
                    return;
                }
            }
            this.f280a.close();
        }
    }

    /* renamed from: b.a.a.a.a.e.d.1 */
    class HttpRequest extends HttpRequest<HttpRequest> {
        final /* synthetic */ InputStream f282a;
        final /* synthetic */ OutputStream f283b;
        final /* synthetic */ HttpRequest f284c;

        HttpRequest(HttpRequest httpRequest, Closeable closeable, boolean z, InputStream inputStream, OutputStream outputStream) {
            this.f284c = httpRequest;
            this.f282a = inputStream;
            this.f283b = outputStream;
            super(closeable, z);
        }

        public /* synthetic */ Object m342b() throws HttpRequest, IOException {
            return m341a();
        }

        public HttpRequest m341a() throws IOException {
            byte[] bArr = new byte[this.f284c.f296j];
            while (true) {
                int read = this.f282a.read(bArr);
                if (read == -1) {
                    return this.f284c;
                }
                this.f283b.write(bArr, 0, read);
            }
        }
    }

    /* renamed from: b.a.a.a.a.e.d.b */
    public interface HttpRequest {
        public static final HttpRequest f285a;

        /* renamed from: b.a.a.a.a.e.d.b.1 */
        static class HttpRequest implements HttpRequest {
            HttpRequest() {
            }

            public HttpURLConnection m345a(URL url) throws IOException {
                return (HttpURLConnection) HttpInstrumentation.openConnection(url.openConnection());
            }

            public HttpURLConnection m346a(URL url, Proxy proxy) throws IOException {
                return (HttpURLConnection) HttpInstrumentation.openConnectionWithProxy(url.openConnection(proxy));
            }
        }

        HttpURLConnection m343a(URL url) throws IOException;

        HttpURLConnection m344a(URL url, Proxy proxy) throws IOException;

        static {
            f285a = new HttpRequest();
        }
    }

    /* renamed from: b.a.a.a.a.e.d.c */
    public static class HttpRequest extends RuntimeException {
        public /* synthetic */ Throwable getCause() {
            return m347a();
        }

        protected HttpRequest(IOException iOException) {
            super(iOException);
        }

        public IOException m347a() {
            return (IOException) super.getCause();
        }
    }

    /* renamed from: b.a.a.a.a.e.d.e */
    public static class HttpRequest extends BufferedOutputStream {
        private final CharsetEncoder f286a;

        public HttpRequest(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.f286a = Charset.forName(HttpRequest.m361f(str)).newEncoder();
        }

        public HttpRequest m348a(String str) throws IOException {
            ByteBuffer encode = this.f286a.encode(CharBuffer.wrap(str));
            super.write(encode.array(), 0, encode.limit());
            return this;
        }
    }

    static {
        f287b = new String[0];
        f288c = HttpRequest.f285a;
    }

    private static String m361f(String str) {
        return (str == null || str.length() <= 0) ? HTTP.UTF_8 : str;
    }

    private static StringBuilder m353a(String str, StringBuilder stringBuilder) {
        if (str.indexOf(58) + 2 == str.lastIndexOf(47)) {
            stringBuilder.append('/');
        }
        return stringBuilder;
    }

    private static StringBuilder m356b(String str, StringBuilder stringBuilder) {
        int indexOf = str.indexOf(63);
        int length = stringBuilder.length() - 1;
        if (indexOf == -1) {
            stringBuilder.append('?');
        } else if (indexOf < length && str.charAt(length) != '&') {
            stringBuilder.append('&');
        }
        return stringBuilder;
    }

    public static String m351a(CharSequence charSequence) throws HttpRequest {
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                host = host + ':' + Integer.toString(port);
            }
            try {
                String toASCIIString = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), null).toASCIIString();
                int indexOf = toASCIIString.indexOf(63);
                if (indexOf > 0 && indexOf + 1 < toASCIIString.length()) {
                    toASCIIString = toASCIIString.substring(0, indexOf + 1) + toASCIIString.substring(indexOf + 1).replace("+", "%2B");
                }
                return toASCIIString;
            } catch (Throwable e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new HttpRequest(iOException);
            }
        } catch (IOException e2) {
            throw new HttpRequest(e2);
        }
    }

    public static String m352a(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder stringBuilder = new StringBuilder(charSequence2);
        HttpRequest.m353a(charSequence2, stringBuilder);
        HttpRequest.m356b(charSequence2, stringBuilder);
        Iterator it = map.entrySet().iterator();
        Entry entry = (Entry) it.next();
        stringBuilder.append(entry.getKey().toString());
        stringBuilder.append('=');
        Object value = entry.getValue();
        if (value != null) {
            stringBuilder.append(value);
        }
        while (it.hasNext()) {
            stringBuilder.append('&');
            entry = (Entry) it.next();
            stringBuilder.append(entry.getKey().toString());
            stringBuilder.append('=');
            value = entry.getValue();
            if (value != null) {
                stringBuilder.append(value);
            }
        }
        return stringBuilder.toString();
    }

    public static HttpRequest m354b(CharSequence charSequence) throws HttpRequest {
        return new HttpRequest(charSequence, "GET");
    }

    public static HttpRequest m350a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = HttpRequest.m352a(charSequence, (Map) map);
        if (z) {
            a = HttpRequest.m351a(a);
        }
        return HttpRequest.m354b(a);
    }

    public static HttpRequest m357c(CharSequence charSequence) throws HttpRequest {
        return new HttpRequest(charSequence, "POST");
    }

    public static HttpRequest m355b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = HttpRequest.m352a(charSequence, (Map) map);
        if (z) {
            a = HttpRequest.m351a(a);
        }
        return HttpRequest.m357c(a);
    }

    public static HttpRequest m358d(CharSequence charSequence) throws HttpRequest {
        return new HttpRequest(charSequence, "PUT");
    }

    public static HttpRequest m359e(CharSequence charSequence) throws HttpRequest {
        return new HttpRequest(charSequence, "DELETE");
    }

    public HttpRequest(CharSequence charSequence, String str) throws HttpRequest {
        this.f290d = null;
        this.f294h = true;
        this.f295i = false;
        this.f296j = AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD;
        try {
            this.f289a = new URL(charSequence.toString());
            this.f291e = str;
        } catch (IOException e) {
            throw new HttpRequest(e);
        }
    }

    private Proxy m362q() {
        return new Proxy(Type.HTTP, new InetSocketAddress(this.f297k, this.f298l));
    }

    private HttpURLConnection m363r() {
        try {
            HttpURLConnection a;
            if (this.f297k != null) {
                a = f288c.m344a(this.f289a, m362q());
            } else {
                a = f288c.m343a(this.f289a);
            }
            a.setRequestMethod(this.f291e);
            return a;
        } catch (IOException e) {
            throw new HttpRequest(e);
        }
    }

    public String toString() {
        return m402p() + ' ' + m401o();
    }

    public HttpURLConnection m377a() {
        if (this.f290d == null) {
            this.f290d = m363r();
        }
        return this.f290d;
    }

    public int m378b() throws HttpRequest {
        try {
            m397k();
            return m377a().getResponseCode();
        } catch (IOException e) {
            throw new HttpRequest(e);
        }
    }

    public boolean m384c() throws HttpRequest {
        return HttpStatus.SC_OK == m378b();
    }

    protected ByteArrayOutputStream m387d() {
        int j = m396j();
        if (j > 0) {
            return new ByteArrayOutputStream(j);
        }
        return new ByteArrayOutputStream();
    }

    public String m376a(String str) throws HttpRequest {
        OutputStream d = m387d();
        try {
            m366a(m392f(), d);
            return d.toString(HttpRequest.m361f(str));
        } catch (IOException e) {
            throw new HttpRequest(e);
        }
    }

    public String m389e() throws HttpRequest {
        return m376a(m394h());
    }

    public BufferedInputStream m392f() throws HttpRequest {
        return new BufferedInputStream(m393g(), this.f296j);
    }

    public InputStream m393g() throws HttpRequest {
        if (m378b() < HttpStatus.SC_BAD_REQUEST) {
            try {
                InputStream inputStream = m377a().getInputStream();
            } catch (IOException e) {
                throw new HttpRequest(e);
            }
        }
        inputStream = m377a().getErrorStream();
        if (inputStream == null) {
            try {
                inputStream = m377a().getInputStream();
            } catch (IOException e2) {
                throw new HttpRequest(e2);
            }
        }
        if (!this.f295i || !"gzip".equals(m395i())) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e22) {
            throw new HttpRequest(e22);
        }
    }

    public HttpRequest m365a(int i) {
        m377a().setConnectTimeout(i);
        return this;
    }

    public HttpRequest m368a(String str, String str2) {
        m377a().setRequestProperty(str, str2);
        return this;
    }

    public HttpRequest m374a(Entry<String, String> entry) {
        return m368a((String) entry.getKey(), (String) entry.getValue());
    }

    public String m380b(String str) throws HttpRequest {
        m398l();
        return m377a().getHeaderField(str);
    }

    public int m382c(String str) throws HttpRequest {
        return m364a(str, -1);
    }

    public int m364a(String str, int i) throws HttpRequest {
        m398l();
        return m377a().getHeaderFieldInt(str, i);
    }

    public String m381b(String str, String str2) {
        return m383c(m380b(str), str2);
    }

    protected String m383c(String str, String str2) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        int indexOf = str.indexOf(59) + 1;
        if (indexOf == 0 || indexOf == length) {
            return null;
        }
        int indexOf2 = str.indexOf(59, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = indexOf;
            indexOf = length;
        } else {
            int i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        while (indexOf2 < indexOf) {
            int indexOf3 = str.indexOf(61, indexOf2);
            if (indexOf3 != -1 && indexOf3 < indexOf && str2.equals(str.substring(indexOf2, indexOf3).trim())) {
                String trim = str.substring(indexOf3 + 1, indexOf).trim();
                indexOf3 = trim.length();
                if (indexOf3 != 0) {
                    if (indexOf3 > 2 && '\"' == trim.charAt(0) && '\"' == trim.charAt(indexOf3 - 1)) {
                        return trim.substring(1, indexOf3 - 1);
                    }
                    return trim;
                }
            }
            indexOf++;
            indexOf2 = str.indexOf(59, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = length;
            }
            i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        return null;
    }

    public String m394h() {
        return m381b(HTTP.CONTENT_TYPE, "charset");
    }

    public HttpRequest m375a(boolean z) {
        m377a().setUseCaches(z);
        return this;
    }

    public String m395i() {
        return m380b(HTTP.CONTENT_ENCODING);
    }

    public HttpRequest m385d(String str) {
        return m386d(str, null);
    }

    public HttpRequest m386d(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return m368a(HTTP.CONTENT_TYPE, str);
        }
        String str3 = HTTP.CHARSET_PARAM;
        return m368a(HTTP.CONTENT_TYPE, str + HTTP.CHARSET_PARAM + str2);
    }

    public int m396j() {
        return m382c(HTTP.CONTENT_LEN);
    }

    protected HttpRequest m366a(InputStream inputStream, OutputStream outputStream) throws IOException {
        return (HttpRequest) new HttpRequest(this, inputStream, this.f294h, inputStream, outputStream).call();
    }

    protected HttpRequest m397k() throws IOException {
        if (this.f292f != null) {
            if (this.f293g) {
                this.f292f.m348a("\r\n--00content0boundary00--\r\n");
            }
            if (this.f294h) {
                try {
                    this.f292f.close();
                } catch (IOException e) {
                }
            } else {
                this.f292f.close();
            }
            this.f292f = null;
        }
        return this;
    }

    protected HttpRequest m398l() throws HttpRequest {
        try {
            return m397k();
        } catch (IOException e) {
            throw new HttpRequest(e);
        }
    }

    protected HttpRequest m399m() throws IOException {
        if (this.f292f == null) {
            m377a().setDoOutput(true);
            this.f292f = new HttpRequest(m377a().getOutputStream(), m383c(m377a().getRequestProperty(HTTP.CONTENT_TYPE), "charset"), this.f296j);
        }
        return this;
    }

    protected HttpRequest m400n() throws IOException {
        if (this.f293g) {
            this.f292f.m348a("\r\n--00content0boundary00\r\n");
        } else {
            this.f293g = true;
            m385d("multipart/form-data; boundary=00content0boundary00").m399m();
            this.f292f.m348a("--00content0boundary00\r\n");
        }
        return this;
    }

    protected HttpRequest m370a(String str, String str2, String str3) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("form-data; name=\"").append(str);
        if (str2 != null) {
            stringBuilder.append("\"; filename=\"").append(str2);
        }
        stringBuilder.append('\"');
        m391f(MIME.CONTENT_DISPOSITION, stringBuilder.toString());
        if (str3 != null) {
            m391f(HTTP.CONTENT_TYPE, str3);
        }
        return m390f((CharSequence) "\r\n");
    }

    public HttpRequest m388e(String str, String str2) {
        return m379b(str, null, str2);
    }

    public HttpRequest m379b(String str, String str2, String str3) throws HttpRequest {
        return m373a(str, str2, null, str3);
    }

    public HttpRequest m373a(String str, String str2, String str3, String str4) throws HttpRequest {
        try {
            m400n();
            m370a(str, str2, str3);
            this.f292f.m348a(str4);
            return this;
        } catch (IOException e) {
            throw new HttpRequest(e);
        }
    }

    public HttpRequest m367a(String str, Number number) throws HttpRequest {
        return m369a(str, null, number);
    }

    public HttpRequest m369a(String str, String str2, Number number) throws HttpRequest {
        return m379b(str, str2, number != null ? number.toString() : null);
    }

    public HttpRequest m371a(String str, String str2, String str3, File file) throws HttpRequest {
        InputStream bufferedInputStream;
        IOException e;
        Throwable th;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                HttpRequest a = m372a(str, str2, str3, bufferedInputStream);
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e2) {
                    }
                }
                return a;
            } catch (IOException e3) {
                e = e3;
                try {
                    throw new HttpRequest(e);
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e = e5;
            bufferedInputStream = null;
            throw new HttpRequest(e);
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    public HttpRequest m372a(String str, String str2, String str3, InputStream inputStream) throws HttpRequest {
        try {
            m400n();
            m370a(str, str2, str3);
            m366a(inputStream, this.f292f);
            return this;
        } catch (IOException e) {
            throw new HttpRequest(e);
        }
    }

    public HttpRequest m391f(String str, String str2) throws HttpRequest {
        return m390f((CharSequence) str).m390f((CharSequence) ": ").m390f((CharSequence) str2).m390f((CharSequence) "\r\n");
    }

    public HttpRequest m390f(CharSequence charSequence) throws HttpRequest {
        try {
            m399m();
            this.f292f.m348a(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new HttpRequest(e);
        }
    }

    public URL m401o() {
        return m377a().getURL();
    }

    public String m402p() {
        return m377a().getRequestMethod();
    }
}
