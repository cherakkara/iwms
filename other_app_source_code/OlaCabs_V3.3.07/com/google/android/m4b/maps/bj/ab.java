package com.google.android.m4b.maps.bj;

/* compiled from: PanoramaConfigCache */
final class ab {
    private final LRUCache<String, PanoramaConfig> f6315a;

    ab(int i) {
        this.f6315a = new LRUCache(50);
    }

    public final void m9743a(PanoramaConfig panoramaConfig) {
        this.f6315a.m9925b(panoramaConfig.f6592h, panoramaConfig);
    }

    public final PanoramaConfig m9742a(String str) {
        if (str == null) {
            return null;
        }
        PanoramaConfig panoramaConfig = (PanoramaConfig) this.f6315a.m9921a((Object) str);
        if (panoramaConfig == null || !panoramaConfig.m9996a()) {
            return panoramaConfig;
        }
        this.f6315a.m9923b((Object) str);
        return null;
    }
}
