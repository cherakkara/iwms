package p004b.p005a.p006a.p007a.p008a.p013d;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: b.a.a.a.a.d.l */
public class GZIPQueueFileEventStorage extends QueueFileEventStorage {
    public GZIPQueueFileEventStorage(Context context, File file, String str, String str2) throws IOException {
        super(context, file, str, str2);
    }

    public OutputStream m325a(File file) throws IOException {
        return new GZIPOutputStream(new FileOutputStream(file));
    }
}
