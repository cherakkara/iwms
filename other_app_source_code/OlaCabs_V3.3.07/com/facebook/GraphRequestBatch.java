package com.facebook;

import android.os.Handler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.facebook.n */
public class GraphRequestBatch extends AbstractList<GraphRequest> {
    private static AtomicInteger f1015a;
    private Handler f1016b;
    private List<GraphRequest> f1017c;
    private int f1018d;
    private final String f1019e;
    private List<GraphRequestBatch> f1020f;
    private String f1021g;

    /* renamed from: com.facebook.n.a */
    public interface GraphRequestBatch {
        void m1327a(GraphRequestBatch graphRequestBatch);
    }

    /* renamed from: com.facebook.n.b */
    public interface GraphRequestBatch extends GraphRequestBatch {
        void m1328a(GraphRequestBatch graphRequestBatch, long j, long j2);
    }

    public /* synthetic */ void add(int i, Object obj) {
        m1331a(i, (GraphRequest) obj);
    }

    public /* synthetic */ boolean add(Object obj) {
        return m1334a((GraphRequest) obj);
    }

    public /* synthetic */ Object get(int i) {
        return m1330a(i);
    }

    public /* synthetic */ Object remove(int i) {
        return m1335b(i);
    }

    public /* synthetic */ Object set(int i, Object obj) {
        return m1336b(i, (GraphRequest) obj);
    }

    static {
        f1015a = new AtomicInteger();
    }

    public GraphRequestBatch() {
        this.f1017c = new ArrayList();
        this.f1018d = 0;
        this.f1019e = Integer.valueOf(f1015a.incrementAndGet()).toString();
        this.f1020f = new ArrayList();
        this.f1017c = new ArrayList();
    }

    public GraphRequestBatch(Collection<GraphRequest> collection) {
        this.f1017c = new ArrayList();
        this.f1018d = 0;
        this.f1019e = Integer.valueOf(f1015a.incrementAndGet()).toString();
        this.f1020f = new ArrayList();
        this.f1017c = new ArrayList(collection);
    }

    public int m1329a() {
        return this.f1018d;
    }

    public void m1333a(GraphRequestBatch graphRequestBatch) {
        if (!this.f1020f.contains(graphRequestBatch)) {
            this.f1020f.add(graphRequestBatch);
        }
    }

    public final boolean m1334a(GraphRequest graphRequest) {
        return this.f1017c.add(graphRequest);
    }

    public final void m1331a(int i, GraphRequest graphRequest) {
        this.f1017c.add(i, graphRequest);
    }

    public final void clear() {
        this.f1017c.clear();
    }

    public final GraphRequest m1330a(int i) {
        return (GraphRequest) this.f1017c.get(i);
    }

    public final GraphRequest m1335b(int i) {
        return (GraphRequest) this.f1017c.remove(i);
    }

    public final GraphRequest m1336b(int i, GraphRequest graphRequest) {
        return (GraphRequest) this.f1017c.set(i, graphRequest);
    }

    public final int size() {
        return this.f1017c.size();
    }

    final String m1337b() {
        return this.f1019e;
    }

    final Handler m1338c() {
        return this.f1016b;
    }

    final void m1332a(Handler handler) {
        this.f1016b = handler;
    }

    final List<GraphRequest> m1339d() {
        return this.f1017c;
    }

    final List<GraphRequestBatch> m1340e() {
        return this.f1020f;
    }

    public final String m1341f() {
        return this.f1021g;
    }

    public final List<GraphResponse> m1342g() {
        return m1344i();
    }

    public final GraphRequestAsyncTask m1343h() {
        return m1345j();
    }

    List<GraphResponse> m1344i() {
        return GraphRequest.m755b(this);
    }

    GraphRequestAsyncTask m1345j() {
        return GraphRequest.m757c(this);
    }
}
