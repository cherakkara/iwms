package p004b.p005a.p006a.p007a.p008a.p010b;

import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.MotionEventCompat;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: b.a.a.a.a.b.q */
public class QueueFile implements Closeable {
    private static final Logger f189b;
    int f190a;
    private final RandomAccessFile f191c;
    private int f192d;
    private QueueFile f193e;
    private QueueFile f194f;
    private final byte[] f195g;

    /* renamed from: b.a.a.a.a.b.q.c */
    public interface QueueFile {
        void read(InputStream inputStream, int i) throws IOException;
    }

    /* renamed from: b.a.a.a.a.b.q.1 */
    class QueueFile implements QueueFile {
        boolean f180a;
        final /* synthetic */ StringBuilder f181b;
        final /* synthetic */ QueueFile f182c;

        QueueFile(QueueFile queueFile, StringBuilder stringBuilder) {
            this.f182c = queueFile;
            this.f181b = stringBuilder;
            this.f180a = true;
        }

        public void read(InputStream inputStream, int i) throws IOException {
            if (this.f180a) {
                this.f180a = false;
            } else {
                this.f181b.append(", ");
            }
            this.f181b.append(i);
        }
    }

    /* renamed from: b.a.a.a.a.b.q.a */
    static class QueueFile {
        static final QueueFile f183a;
        final int f184b;
        final int f185c;

        static {
            f183a = new QueueFile(0, 0);
        }

        QueueFile(int i, int i2) {
            this.f184b = i;
            this.f185c = i2;
        }

        public String toString() {
            return getClass().getSimpleName() + "[" + "position = " + this.f184b + ", length = " + this.f185c + "]";
        }
    }

    /* renamed from: b.a.a.a.a.b.q.b */
    private final class QueueFile extends InputStream {
        final /* synthetic */ QueueFile f186a;
        private int f187b;
        private int f188c;

        private QueueFile(QueueFile queueFile, QueueFile queueFile2) {
            this.f186a = queueFile;
            this.f187b = queueFile.m237b(queueFile2.f184b + 4);
            this.f188c = queueFile2.f185c;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            QueueFile.m239b(bArr, "buffer");
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (this.f188c <= 0) {
                return -1;
            } else {
                if (i2 > this.f188c) {
                    i2 = this.f188c;
                }
                this.f186a.m240b(this.f187b, bArr, i, i2);
                this.f187b = this.f186a.m237b(this.f187b + i2);
                this.f188c -= i2;
                return i2;
            }
        }

        public int read() throws IOException {
            if (this.f188c == 0) {
                return -1;
            }
            this.f186a.f191c.seek((long) this.f187b);
            int read = this.f186a.f191c.read();
            this.f187b = this.f186a.m237b(this.f187b + 1);
            this.f188c--;
            return read;
        }
    }

    static {
        f189b = Logger.getLogger(QueueFile.class.getName());
    }

    public QueueFile(File file) throws IOException {
        this.f195g = new byte[16];
        if (!file.exists()) {
            QueueFile.m235a(file);
        }
        this.f191c = QueueFile.m238b(file);
        m244e();
    }

    private static void m241b(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void m236a(byte[] bArr, int... iArr) {
        int i = 0;
        int length = iArr.length;
        int i2 = 0;
        while (i < length) {
            QueueFile.m241b(bArr, i2, iArr[i]);
            i2 += 4;
            i++;
        }
    }

    private static int m228a(byte[] bArr, int i) {
        return ((((bArr[i] & MotionEventCompat.ACTION_MASK) << 24) + ((bArr[i + 1] & MotionEventCompat.ACTION_MASK) << 16)) + ((bArr[i + 2] & MotionEventCompat.ACTION_MASK) << 8)) + (bArr[i + 3] & MotionEventCompat.ACTION_MASK);
    }

    private void m244e() throws IOException {
        this.f191c.seek(0);
        this.f191c.readFully(this.f195g);
        this.f190a = QueueFile.m228a(this.f195g, 0);
        if (((long) this.f190a) > this.f191c.length()) {
            throw new IOException("File is truncated. Expected length: " + this.f190a + ", Actual length: " + this.f191c.length());
        }
        this.f192d = QueueFile.m228a(this.f195g, 4);
        int a = QueueFile.m228a(this.f195g, 8);
        int a2 = QueueFile.m228a(this.f195g, 12);
        this.f193e = m229a(a);
        this.f194f = m229a(a2);
    }

    private void m232a(int i, int i2, int i3, int i4) throws IOException {
        QueueFile.m236a(this.f195g, i, i2, i3, i4);
        this.f191c.seek(0);
        this.f191c.write(this.f195g);
    }

    private QueueFile m229a(int i) throws IOException {
        if (i == 0) {
            return QueueFile.f183a;
        }
        this.f191c.seek((long) i);
        return new QueueFile(i, this.f191c.readInt());
    }

    private static void m235a(File file) throws IOException {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile b = QueueFile.m238b(file2);
        try {
            b.setLength(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
            b.seek(0);
            byte[] bArr = new byte[16];
            QueueFile.m236a(bArr, AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH, 0, 0, 0);
            b.write(bArr);
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } finally {
            b.close();
        }
    }

    private static RandomAccessFile m238b(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    private int m237b(int i) {
        return i < this.f190a ? i : (i + 16) - this.f190a;
    }

    private void m233a(int i, byte[] bArr, int i2, int i3) throws IOException {
        int b = m237b(i);
        if (b + i3 <= this.f190a) {
            this.f191c.seek((long) b);
            this.f191c.write(bArr, i2, i3);
            return;
        }
        int i4 = this.f190a - b;
        this.f191c.seek((long) b);
        this.f191c.write(bArr, i2, i4);
        this.f191c.seek(16);
        this.f191c.write(bArr, i2 + i4, i3 - i4);
    }

    private void m240b(int i, byte[] bArr, int i2, int i3) throws IOException {
        int b = m237b(i);
        if (b + i3 <= this.f190a) {
            this.f191c.seek((long) b);
            this.f191c.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.f190a - b;
        this.f191c.seek((long) b);
        this.f191c.readFully(bArr, i2, i4);
        this.f191c.seek(16);
        this.f191c.readFully(bArr, i2 + i4, i3 - i4);
    }

    public void m248a(byte[] bArr) throws IOException {
        m249a(bArr, 0, bArr.length);
    }

    public synchronized void m249a(byte[] bArr, int i, int i2) throws IOException {
        QueueFile.m239b(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        int i3;
        m242c(i2);
        boolean b = m251b();
        if (b) {
            i3 = 16;
        } else {
            i3 = m237b((this.f194f.f184b + 4) + this.f194f.f185c);
        }
        QueueFile queueFile = new QueueFile(i3, i2);
        QueueFile.m241b(this.f195g, 0, i2);
        m233a(queueFile.f184b, this.f195g, 0, 4);
        m233a(queueFile.f184b + 4, bArr, i, i2);
        m232a(this.f190a, this.f192d + 1, b ? queueFile.f184b : this.f193e.f184b, queueFile.f184b);
        this.f194f = queueFile;
        this.f192d++;
        if (b) {
            this.f193e = this.f194f;
        }
    }

    public int m246a() {
        if (this.f192d == 0) {
            return 16;
        }
        if (this.f194f.f184b >= this.f193e.f184b) {
            return (((this.f194f.f184b - this.f193e.f184b) + 4) + this.f194f.f185c) + 16;
        }
        return (((this.f194f.f184b + 4) + this.f194f.f185c) + this.f190a) - this.f193e.f184b;
    }

    private int m245f() {
        return this.f190a - m246a();
    }

    public synchronized boolean m251b() {
        return this.f192d == 0;
    }

    private void m242c(int i) throws IOException {
        int i2 = i + 4;
        int f = m245f();
        if (f < i2) {
            int i3 = this.f190a;
            do {
                f += i3;
                i3 <<= 1;
            } while (f < i2);
            m243d(i3);
            i2 = m237b((this.f194f.f184b + 4) + this.f194f.f185c);
            if (i2 < this.f193e.f184b) {
                FileChannel channel = this.f191c.getChannel();
                channel.position((long) this.f190a);
                int i4 = i2 - 4;
                if (channel.transferTo(16, (long) i4, channel) != ((long) i4)) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.f194f.f184b < this.f193e.f184b) {
                f = (this.f190a + this.f194f.f184b) - 16;
                m232a(i3, this.f192d, this.f193e.f184b, f);
                this.f194f = new QueueFile(f, this.f194f.f185c);
            } else {
                m232a(i3, this.f192d, this.f193e.f184b, this.f194f.f184b);
            }
            this.f190a = i3;
        }
    }

    private void m243d(int i) throws IOException {
        this.f191c.setLength((long) i);
        this.f191c.getChannel().force(true);
    }

    public synchronized void m247a(QueueFile queueFile) throws IOException {
        int i = this.f193e.f184b;
        for (int i2 = 0; i2 < this.f192d; i2++) {
            QueueFile a = m229a(i);
            queueFile.read(new QueueFile(a, null), a.f185c);
            i = m237b(a.f185c + (a.f184b + 4));
        }
    }

    private static <T> T m239b(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public synchronized void m252c() throws IOException {
        if (m251b()) {
            throw new NoSuchElementException();
        } else if (this.f192d == 1) {
            m253d();
        } else {
            int b = m237b((this.f193e.f184b + 4) + this.f193e.f185c);
            m240b(b, this.f195g, 0, 4);
            int a = QueueFile.m228a(this.f195g, 0);
            m232a(this.f190a, this.f192d - 1, b, this.f194f.f184b);
            this.f192d--;
            this.f193e = new QueueFile(b, a);
        }
    }

    public synchronized void m253d() throws IOException {
        m232a((int) AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH, 0, 0, 0);
        this.f192d = 0;
        this.f193e = QueueFile.f183a;
        this.f194f = QueueFile.f183a;
        if (this.f190a > AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH) {
            m243d(AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH);
        }
        this.f190a = AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH;
    }

    public synchronized void close() throws IOException {
        this.f191c.close();
    }

    public boolean m250a(int i, int i2) {
        return (m246a() + 4) + i <= i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append('[');
        stringBuilder.append("fileLength=").append(this.f190a);
        stringBuilder.append(", size=").append(this.f192d);
        stringBuilder.append(", first=").append(this.f193e);
        stringBuilder.append(", last=").append(this.f194f);
        stringBuilder.append(", element lengths=[");
        try {
            m247a(new QueueFile(this, stringBuilder));
        } catch (Throwable e) {
            f189b.log(Level.WARNING, "read error", e);
        }
        stringBuilder.append("]]");
        return stringBuilder.toString();
    }
}
