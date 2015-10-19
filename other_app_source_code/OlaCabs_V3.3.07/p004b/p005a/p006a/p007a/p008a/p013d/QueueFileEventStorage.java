package p004b.p005a.p006a.p007a.p008a.p013d;

import android.content.Context;
import com.newrelic.agent.android.api.v1.Defaults;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.QueueFile;

/* renamed from: b.a.a.a.a.d.m */
public class QueueFileEventStorage implements EventsStorage {
    private final Context f262a;
    private final File f263b;
    private final String f264c;
    private final File f265d;
    private QueueFile f266e;
    private File f267f;

    public QueueFileEventStorage(Context context, File file, String str, String str2) throws IOException {
        this.f262a = context;
        this.f263b = file;
        this.f264c = str2;
        this.f265d = new File(this.f263b, str);
        this.f266e = new QueueFile(this.f265d);
        m314e();
    }

    private void m314e() {
        this.f267f = new File(this.f263b, this.f264c);
        if (!this.f267f.exists()) {
            this.f267f.mkdirs();
        }
    }

    public void m320a(byte[] bArr) throws IOException {
        this.f266e.m248a(bArr);
    }

    public int m315a() {
        return this.f266e.m246a();
    }

    public void m318a(String str) throws IOException {
        this.f266e.close();
        m313a(this.f265d, new File(this.f267f, str));
        this.f266e = new QueueFile(this.f265d);
    }

    private void m313a(File file, File file2) throws IOException {
        Closeable fileInputStream;
        Throwable th;
        Closeable closeable = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                closeable = m316a(file2);
                CommonUtils.m169a((InputStream) fileInputStream, (OutputStream) closeable, new byte[Defaults.RESPONSE_BODY_LIMIT]);
                CommonUtils.m167a(fileInputStream, "Failed to close file input stream");
                CommonUtils.m167a(closeable, "Failed to close output stream");
                file.delete();
            } catch (Throwable th2) {
                th = th2;
                CommonUtils.m167a(fileInputStream, "Failed to close file input stream");
                CommonUtils.m167a(closeable, "Failed to close output stream");
                file.delete();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            CommonUtils.m167a(fileInputStream, "Failed to close file input stream");
            CommonUtils.m167a(closeable, "Failed to close output stream");
            file.delete();
            throw th;
        }
    }

    public OutputStream m316a(File file) throws IOException {
        return new FileOutputStream(file);
    }

    public List<File> m317a(int i) {
        List<File> arrayList = new ArrayList();
        for (Object add : this.f267f.listFiles()) {
            arrayList.add(add);
            if (arrayList.size() >= i) {
                break;
            }
        }
        return arrayList;
    }

    public void m319a(List<File> list) {
        for (File file : list) {
            CommonUtils.m164a(this.f262a, String.format("deleting sent analytics file %s", new Object[]{file.getName()}));
            file.delete();
        }
    }

    public List<File> m323c() {
        return Arrays.asList(this.f267f.listFiles());
    }

    public void m324d() {
        try {
            this.f266e.close();
        } catch (IOException e) {
        }
        this.f265d.delete();
    }

    public boolean m322b() {
        return this.f266e.m251b();
    }

    public boolean m321a(int i, int i2) {
        return this.f266e.m250a(i, i2);
    }
}
