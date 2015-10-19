package p004b.p005a.p006a.p007a.p008a.p013d;

import android.content.Context;
import com.crashlytics.android.core.CrashlyticsCore;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.CurrentTimeProvider;

/* renamed from: b.a.a.a.a.d.d */
public abstract class EventsFilesManager<T> {
    public static final int MAX_FILES_IN_BATCH = 1;
    public static final int MAX_FILES_TO_KEEP = 100;
    public static final String ROLL_OVER_FILE_NAME_SEPARATOR = "_";
    protected final Context context;
    protected final CurrentTimeProvider currentTimeProvider;
    private final int defaultMaxFilesToKeep;
    protected final EventsStorage eventStorage;
    protected volatile long lastRollOverTime;
    protected final List<EventsStorageListener> rollOverListeners;
    protected final EventTransform<T> transform;

    /* renamed from: b.a.a.a.a.d.d.1 */
    class EventsFilesManager implements Comparator<EventsFilesManager> {
        final /* synthetic */ EventsFilesManager f252a;

        EventsFilesManager(EventsFilesManager eventsFilesManager) {
            this.f252a = eventsFilesManager;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m303a((EventsFilesManager) obj, (EventsFilesManager) obj2);
        }

        public int m303a(EventsFilesManager eventsFilesManager, EventsFilesManager eventsFilesManager2) {
            return (int) (eventsFilesManager.f254b - eventsFilesManager2.f254b);
        }
    }

    /* renamed from: b.a.a.a.a.d.d.a */
    static class EventsFilesManager {
        final File f253a;
        final long f254b;

        public EventsFilesManager(File file, long j) {
            this.f253a = file;
            this.f254b = j;
        }
    }

    protected abstract String generateUniqueRollOverFileName();

    public EventsFilesManager(Context context, EventTransform<T> eventTransform, CurrentTimeProvider currentTimeProvider, EventsStorage eventsStorage, int i) throws IOException {
        this.rollOverListeners = new CopyOnWriteArrayList();
        this.context = context.getApplicationContext();
        this.transform = eventTransform;
        this.eventStorage = eventsStorage;
        this.currentTimeProvider = currentTimeProvider;
        this.lastRollOverTime = this.currentTimeProvider.m193a();
        this.defaultMaxFilesToKeep = i;
    }

    public void writeEvent(T t) throws IOException {
        byte[] toBytes = this.transform.toBytes(t);
        rollFileOverIfNeeded(toBytes.length);
        this.eventStorage.m308a(toBytes);
    }

    public void registerRollOverListener(EventsStorageListener eventsStorageListener) {
        if (eventsStorageListener != null) {
            this.rollOverListeners.add(eventsStorageListener);
        }
    }

    public boolean rollFileOver() throws IOException {
        boolean z = true;
        String str = null;
        if (this.eventStorage.m310b()) {
            z = false;
        } else {
            str = generateUniqueRollOverFileName();
            this.eventStorage.m306a(str);
            Context context = this.context;
            String str2 = CrashlyticsCore.TAG;
            Object[] objArr = new Object[MAX_FILES_IN_BATCH];
            objArr[0] = str;
            CommonUtils.m163a(context, 4, str2, String.format(Locale.US, "generated new file %s", objArr));
            this.lastRollOverTime = this.currentTimeProvider.m193a();
        }
        triggerRollOverOnListeners(str);
        return z;
    }

    private void rollFileOverIfNeeded(int i) throws IOException {
        if (!this.eventStorage.m309a(i, getMaxByteSizePerFile())) {
            CommonUtils.m163a(this.context, 4, CrashlyticsCore.TAG, String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", new Object[]{Integer.valueOf(this.eventStorage.m304a()), Integer.valueOf(i), Integer.valueOf(getMaxByteSizePerFile())}));
            rollFileOver();
        }
    }

    protected int getMaxFilesToKeep() {
        return this.defaultMaxFilesToKeep;
    }

    protected int getMaxByteSizePerFile() {
        return 8000;
    }

    public long getLastRollOverTime() {
        return this.lastRollOverTime;
    }

    private void triggerRollOverOnListeners(String str) {
        for (EventsStorageListener onRollOver : this.rollOverListeners) {
            try {
                onRollOver.onRollOver(str);
            } catch (Throwable e) {
                CommonUtils.m165a(this.context, "One of the roll over listeners threw an exception", e);
            }
        }
    }

    public List<File> getBatchOfFilesToSend() {
        return this.eventStorage.m305a((int) MAX_FILES_IN_BATCH);
    }

    public void deleteSentFiles(List<File> list) {
        this.eventStorage.m307a((List) list);
    }

    public void deleteAllEventsFiles() {
        this.eventStorage.m307a(this.eventStorage.m311c());
        this.eventStorage.m312d();
    }

    public void deleteOldestInRollOverIfOverMax() {
        List<File> c = this.eventStorage.m311c();
        int maxFilesToKeep = getMaxFilesToKeep();
        if (c.size() > maxFilesToKeep) {
            int size = c.size() - maxFilesToKeep;
            CommonUtils.m164a(this.context, String.format(Locale.US, "Found %d files in  roll over directory, this is greater than %d, deleting %d oldest files", new Object[]{Integer.valueOf(c.size()), Integer.valueOf(maxFilesToKeep), Integer.valueOf(size)}));
            TreeSet treeSet = new TreeSet(new EventsFilesManager(this));
            for (File file : c) {
                treeSet.add(new EventsFilesManager(file, parseCreationTimestampFromFileName(file.getName())));
            }
            List arrayList = new ArrayList();
            Iterator it = treeSet.iterator();
            while (it.hasNext()) {
                arrayList.add(((EventsFilesManager) it.next()).f253a);
                if (arrayList.size() == size) {
                    break;
                }
            }
            this.eventStorage.m307a(arrayList);
        }
    }

    public long parseCreationTimestampFromFileName(String str) {
        long j = 0;
        String[] split = str.split(ROLL_OVER_FILE_NAME_SEPARATOR);
        if (split.length == 3) {
            try {
                j = Long.valueOf(split[2]).longValue();
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }
}
