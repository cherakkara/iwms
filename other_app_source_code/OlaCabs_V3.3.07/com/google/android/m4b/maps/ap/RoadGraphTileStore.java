package com.google.android.m4b.maps.ap;

import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.ao.RoadGraphPiece;
import com.google.android.m4b.maps.ap.DashServerMapTileStore.DashServerMapTileStore;
import com.google.android.m4b.maps.ap.DashServerTileStore.DashServerTileStore;
import com.google.android.m4b.maps.ar.DiskTileCacheListener;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.olacabs.customer.p076d.br;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.ap.h */
public final class RoadGraphTileStore extends DashServerMapTileStore {
    private static final List<Integer> f3825e;

    /* renamed from: com.google.android.m4b.maps.ap.h.1 */
    class RoadGraphTileStore extends DashServerMapTileStore {
        RoadGraphTileStore(RoadGraphTileStore roadGraphTileStore) {
            super(roadGraphTileStore);
        }

        protected final aa m6294b(int i) {
            if (this.b[i] == null) {
                return null;
            }
            return RoadGraphPiece.m6129a(m6151a(i).f3796a, this.b[i], 0, Config.m11320a().m11334h().m10152b() + 1209600000);
        }

        protected final int m6296j() {
            int i = 0;
            while (i < this.b.length) {
                if (this.b[i] != null) {
                    try {
                        return RoadGraphPiece.m6128a(this.b[i], 0);
                    } catch (IOException e) {
                    }
                } else {
                    i++;
                }
            }
            return -1;
        }

        protected final byte[] m6295c(int i) {
            return this.b[i];
        }
    }

    static {
        f3825e = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(9)});
    }

    public RoadGraphTileStore(DataRequestDispatcherInterface dataRequestDispatcherInterface, ai aiVar, Locale locale, File file, DiskTileCacheListener diskTileCacheListener) {
        super(dataRequestDispatcherInterface, "rgts" + aiVar.f3449C, aiVar, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, f3825e, 1, 24, br.DEFAULT_BACKOFF_MULT, true, locale, false, file, null);
    }

    protected final DashServerTileStore m6297g() {
        return new RoadGraphTileStore(this);
    }
}
