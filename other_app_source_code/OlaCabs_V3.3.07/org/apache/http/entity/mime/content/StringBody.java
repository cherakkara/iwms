package org.apache.http.entity.mime.content;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.apache.http.entity.mime.MIME;
import org.apache.http.protocol.HTTP;

public class StringBody extends AbstractContentBody {
    private final Charset charset;
    private final byte[] content;

    public static StringBody create(String str, String str2, Charset charset) throws IllegalArgumentException {
        try {
            return new StringBody(str, str2, charset);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Charset " + charset + " is not supported", e);
        }
    }

    public static StringBody create(String str, Charset charset) throws IllegalArgumentException {
        return create(str, null, charset);
    }

    public static StringBody create(String str) throws IllegalArgumentException {
        return create(str, null, null);
    }

    public StringBody(String str, String str2, Charset charset) throws UnsupportedEncodingException {
        super(str2);
        if (str == null) {
            throw new IllegalArgumentException("Text may not be null");
        }
        if (charset == null) {
            charset = Charset.forName(HTTP.US_ASCII);
        }
        this.content = str.getBytes(charset.name());
        this.charset = charset;
    }

    public StringBody(String str, Charset charset) throws UnsupportedEncodingException {
        this(str, HTTP.PLAIN_TEXT_TYPE, charset);
    }

    public StringBody(String str) throws UnsupportedEncodingException {
        this(str, HTTP.PLAIN_TEXT_TYPE, null);
    }

    public Reader getReader() {
        return new InputStreamReader(new ByteArrayInputStream(this.content), this.charset);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(this.content);
        byte[] bArr = new byte[AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH];
        while (true) {
            int read = byteArrayInputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    public String getTransferEncoding() {
        return MIME.ENC_8BIT;
    }

    public String getCharset() {
        return this.charset.name();
    }

    public long getContentLength() {
        return (long) this.content.length;
    }

    public String getFilename() {
        return null;
    }
}
