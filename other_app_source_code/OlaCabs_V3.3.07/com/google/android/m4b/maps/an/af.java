package com.google.android.m4b.maps.an;

import android.support.v4.widget.ExploreByTouchHelper;
import com.google.android.m4b.maps.an.SpotlightParameters.SpotlightParameters;
import com.google.android.m4b.maps.an.ak.TransitParameters;
import com.google.android.m4b.maps.an.au.AlternatePaintfeParameters;
import com.google.android.m4b.maps.an.be.HighlightParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;

/* compiled from: TileParameters */
public interface af extends Comparable<af> {

    /* renamed from: com.google.android.m4b.maps.an.af.a */
    public enum TileParameters {
        SPOTLIGHT {
            public final af m5456a(ProtoBuf protoBuf) {
                if (ai.m5501a(protoBuf.m10204d(1)) == ai.f3440r && protoBuf.m10214j(10)) {
                    return new SpotlightParameters().m6075a(protoBuf.m10212h(10)).m6076a();
                }
                return null;
            }
        },
        SPOTLIGHT_DIFFTILE {
            public final af m5457a(ProtoBuf protoBuf) {
                if (ai.m5501a(protoBuf.m10204d(1)) == ai.f3444v && protoBuf.m10214j(27)) {
                    return new SpotlightDescriptionParameters(protoBuf.m10211g(27));
                }
                return null;
            }

            public final ai m5458a() {
                return ai.f3444v;
            }
        },
        HIGHLIGHT {
            public final af m5459a(ProtoBuf protoBuf) {
                if (ai.m5501a(protoBuf.m10204d(1)) == ai.f3441s && protoBuf.m10214j(9)) {
                    return new HighlightParameters().m5762a(protoBuf.m10212h(9)).m5763a();
                }
                return null;
            }
        },
        INDOOR {
            public final af m5460a(ProtoBuf protoBuf) {
                if (ai.m5501a(protoBuf.m10204d(1)) == ai.f3436n && protoBuf.m10214j(6)) {
                    return bj.m5816a(new IndoorLevelReference(FeatureId.m11300b(protoBuf.m10212h(6)), ExploreByTouchHelper.INVALID_ID));
                }
                return null;
            }
        },
        TRANSIT {
            public final af m5461a(ProtoBuf protoBuf) {
                if (ai.m5501a(protoBuf.m10204d(1)) != ai.f3435m) {
                    return null;
                }
                TransitParameters transitParameters = new TransitParameters();
                if (protoBuf.m10214j(9)) {
                    transitParameters.m5563a(com.google.android.m4b.maps.p057t.FeatureId.m11291a(protoBuf.m10212h(9)));
                }
                int k = protoBuf.m10215k(12);
                for (int i = 0; i < k; i++) {
                    transitParameters.m5562a(protoBuf.m10195b(12, i));
                }
                af a = transitParameters.m5564a();
                return a.m5568a(ai.f3435m) ? a : null;
            }
        },
        MAPS_ENGINE {
            public final af m5462a(ProtoBuf protoBuf) {
                ai a = ai.m5501a(protoBuf.m10204d(1));
                if (a != ai.f3446x && a != ai.f3445w) {
                    return null;
                }
                br brVar = new br(protoBuf.m10211g(29));
                if (brVar.m5878a(a)) {
                    return brVar;
                }
                return null;
            }
        },
        ALTERNATE_PAINTFE {
            public final af m5463a(ProtoBuf protoBuf) {
                ai.m5501a(protoBuf.m10204d(1));
                if (protoBuf.m10214j(13)) {
                    return new AlternatePaintfeParameters().m5688a(protoBuf.m10212h(13)).m5689a();
                }
                return null;
            }
        };

        public abstract af m5454a(ProtoBuf protoBuf);

        public ai m5455a() {
            return null;
        }
    }

    TileParameters m5464a();

    void m5465a(ProtoBuf protoBuf);

    boolean m5466a(af afVar);

    boolean m5467a(ai aiVar);
}
