package com.google.android.m4b.maps.be;

import com.olacabs.customer.utils.Constants;

/* compiled from: UsageLog */
public interface be {

    /* renamed from: com.google.android.m4b.maps.be.be.1 */
    static class UsageLog implements be {
        UsageLog() {
        }

        public final void m8836a() {
        }

        public final void m8837a(UsageLog usageLog) {
        }

        public final void m8839b(UsageLog usageLog) {
        }

        public final void m8838a(String str) {
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.be.a */
    public enum UsageLog {
        MAP_CREATED("mc"),
        MAP_SET_ZOOM_LIMIT("mz"),
        MAP_SET_ON_MAP_READY_CALLBACK("mrc"),
        MAP_ADD_MARKER("ma"),
        MARKER_REMOVE(Constants.LANDING_PAGE_MY_RIDES),
        MARKER_SET_POSITION("mp"),
        MARKER_TITLE("mt"),
        MARKER_SNIPPET("ms"),
        MARKER_ICON("mI"),
        MARKER_ANCHOR("mA"),
        MARKER_DRAGGABLE("md"),
        MARKER_WAS_DRAGGED("mdw"),
        MARKER_VISIBILITY("mv"),
        MARKER_FLAT("mF"),
        MARKER_ROTATION("mR"),
        MARKER_INFO_WINDOW_ANCHOR("miA"),
        MARKER_ALPHA("mo"),
        MARKER_SHOW_INFO_BUBBLE("mb"),
        MARKER_HIDE_INFO_BUBBLE("mh"),
        MARKER_SET_INFO_CONTENTS_ADAPTER("mi"),
        MARKER_INFO_WINDOW_CLICK_WITH_LISTENER("micwl"),
        MARKER_INFO_WINDOW_CLICK_WITHOUT_LISTENER("micwol"),
        MARKER_CLICK_WITH_LISTENER("mcwl"),
        MARKER_CLICK_WITH_INTERRUPTING_LISTENER("mcwil"),
        MARKER_CLICK_WITHOUT_LISTENER("mcwol"),
        OBSOLETE_MARKER_SHOW_INFO_BUBBLE_CONTENTS("mB"),
        MAP_ADD_POLYLINE("la"),
        POLYLINE_REMOVE("lr"),
        POLYLINE_SET_POINTS("lp"),
        POLYLINE_WIDTH("lw"),
        POLYLINE_COLOR("lc"),
        POLYLINE_Z_INDEX("lz"),
        POLYLINE_VISIBILITY("lv"),
        POLYLINE_GEODESIC("lg"),
        POLYLINE_TEXTURE("lt"),
        POLYLINE_SPANS("ls"),
        POLYLINE_SPANS_GRADIENT("lsG"),
        POLYLINE_SPANS_FRACTIONAL("lsF"),
        MAP_ADD_POLYGON("sa"),
        POLYGON_REMOVE("sr"),
        POLYGON_SET_POINTS("sp"),
        POLYGON_HOLES("sh"),
        POLYGON_WIDTH("sw"),
        POLYGON_STROKE_COLOR("sc"),
        POLYGON_FILL_COLOR("sC"),
        POLYGON_Z_INDEX("sz"),
        POLYGON_VISIBILITY("sv"),
        POLYGON_GEODESIC("sg"),
        MAP_ADD_CIRCLE("cia"),
        CIRCLE_REMOVE("cir"),
        CIRCLE_SET_CENTER("cip"),
        CIRCLE_SET_RADIUS("cis"),
        CIRCLE_WIDTH("ciw"),
        CIRCLE_STROKE_COLOR("cic"),
        CIRCLE_FILL_COLOR("ciC"),
        CIRCLE_Z_INDEX("ciz"),
        CIRCLE_VISIBILITY("civ"),
        MAP_ADD_GROUND_OVERLAY("ga"),
        GROUND_OVERLAY_REMOVE("gr"),
        GROUND_OVERLAY_BEARING("gb"),
        GROUND_OVERLAY_SET_DIMENSIONS("gd"),
        GROUND_OVERLAY_SET_LOCATION("gl"),
        GROUND_OVERLAY_Z_INDEX("gz"),
        GROUND_OVERLAY_VISIBILITY("gv"),
        GROUND_OVERLAY_TRANSPARENCY("gt"),
        GROUND_OVERLAY_SET_IMAGE("gi"),
        MAP_ADD_TILE_OVERLAY("ta"),
        TILE_OVERLAY_CLEAR_CACHE("tc"),
        TILE_OVERLAY_REMOVE(Constants.LANDING_PAGE_TRACK_RIDE),
        TILE_OVERLAY_Z_INDEX("tz"),
        TILE_OVERLAY_VISIBILITY("tv"),
        TILE_OVERLAY_FADE("tf"),
        MAP_ADD_MAPS_ENGINE_OVERLAY("mea"),
        MAPS_ENGINE_OVERLAY_REMOVE("mer"),
        MAPS_ENGINE_OVERLAY_Z_INDEX("mez"),
        MAPS_ENGINE_OVERLAY_VISIBILITY("mev"),
        MAPS_ENGINE_OVERLAY_DEFAULT_UI("meu"),
        MAPS_ENGINE_OVERLAY_CLICK_LISTENER("mel"),
        MAP_ANIMATE_CAMERA("ca"),
        MAP_ANIMATE_CAMERA_WITH_CALLBACK("cac"),
        MAP_ANIMATE_CAMERA_WITH_CALLBACK_AND_CUSTOM_DURATION("cad"),
        MAP_MOVE_CAMERA("cm"),
        MAP_STOP_ANIMATION("cs"),
        CAMERA_UPDATE_ZOOM_IN("Czi"),
        CAMERA_UPDATE_ZOOM_OUT("Czo"),
        CAMERA_UPDATE_SCROLL_BY("Cs"),
        CAMERA_UPDATE_ZOOM_TO("Czt"),
        CAMERA_UPDATE_ZOOM_BY("Czb"),
        CAMERA_UPDATE_ZOOM_BY_FIXING("Czf"),
        CAMERA_UPDATE_NEW_CAMERA_POSITION("Ccp"),
        CAMERA_UPDATE_NEW_LATLNG("Cl"),
        CAMERA_UPDATE_NEW_LATLNG_ZOOM("Clz"),
        CAMERA_UPDATE_NEW_LATLNG_BOUNDS("Clb"),
        CAMERA_UPDATE_NEW_LATLNG_BOUNDS_WITH_DIMENSIONS("Cld"),
        MAP_CLEAR("oc"),
        MAP_SET_MAP_TYPE("ot"),
        MAP_SET_TRAFFIC_DISABLED("oTd"),
        MAP_SET_TRAFFIC_ENABLED("oT"),
        MAP_SET_TRANSIT_DISABLED("oTsd"),
        MAP_SET_TRANSIT_ENABLED("oTs"),
        MAP_SET_MY_LOCATION_DISABLED("omd"),
        MAP_SET_MY_LOCATION_ENABLED("om"),
        MAP_SET_BUILDINGS_DISABLED("obd"),
        MAP_SET_BUILDINGS_ENABLED("ob"),
        MAP_CLEAR_LOCATION_SOURCE("Lc"),
        MAP_GET_MY_LOCATION("Lg"),
        MAP_SET_LOCATION_SOURCE("Lp"),
        MAP_SET_ON_MY_LOCATION_CHANGE_LISTENER("Ll"),
        MAP_SET_ON_MY_LOCATION_BUTTON_CLICK_LISTENER("Lbl"),
        MAP_SET_ON_BUBBLE_CLICK_LISTENER("eb"),
        MAP_SET_ON_BUBBLE_DOUBLE_CLICK_LISTENER("eB"),
        MAP_SET_ON_CAMERA_CHANGE_LISTENER(Constants.LANDING_PAGE_EMERGENCY_CONTACT),
        MAP_SET_ON_INDOOR_LISTENER("ei"),
        OBSOLETE_MAP_SET_ON_MAP_GESTURE_LISTENER("eg"),
        MAP_SET_ON_MAP_CLICK_LISTENER("emc"),
        MAP_SET_ON_MAP_LONG_CLICK_LISTENER("eml"),
        MAP_SET_ON_MARKER_CLICK_LISTENER("em"),
        MAP_SET_ON_MARKER_DRAG_LISTENER("ed"),
        MAP_SET_ON_MAP_IDLE_LISTENER("el"),
        COMPASS_BUTTON_CLICK("uC"),
        MAP_DISABLE_COMPASS("uch"),
        MAP_DISABLE_MY_LOCATION_BUTTON("uLh"),
        MAP_DISABLE_ZOOM_CONTROLS("uzh"),
        MAP_ENABLE_COMPASS("uc"),
        MAP_ENABLE_MY_LOCATION_BUTTON("uL"),
        MAP_ENABLE_ZOOM_CONTROLS("uz"),
        MY_LOCATION_BUTTON_CLICK("ul"),
        GOOGLE_LOGO_CLICK("uG"),
        ZOOM_IN_BUTTON_CLICK("ui"),
        ZOOM_OUT_BUTTON_CLICK("uo"),
        MAP_ENABLE_SCROLL("use"),
        MAP_DISABLE_SCROLL("usd"),
        MAP_ENABLE_ZOOM("uze"),
        MAP_DISABLE_ZOOM("uzd"),
        MAP_ENABLE_ROTATE("ure"),
        MAP_DISABLE_ROTATE("urd"),
        MAP_ENABLE_TILT("ute"),
        MAP_DISABLE_TILT("utd"),
        MAP_ENABLE_ALL_GESTURES("uae"),
        MAP_DISABLE_ALL_GESTURES("uad"),
        MAP_ENABLE_MAP_TOOLBAR("ub"),
        MAP_DISABLE_MAP_TOOLBAR("ubh"),
        MAP_SET_INFO_WINDOW("uiw"),
        MAP_GET_PROJECTION("pg"),
        PROJECTION_FROM_SCREEN_LOCATION("pl"),
        PROJECTION_GET_FRUSTUM("pf"),
        PROJECTION_TO_SCREEN_LOCATION("ps"),
        KEYBOARD_UP("ku"),
        KEYBOARD_DOWN("kd"),
        KEYBOARD_LEFT("kl"),
        KEYBOARD_RIGHT("kr"),
        MAP_ENABLE_INDOOR(Constants.LANDING_PAGE_INVITE_EARN),
        MAP_DISABLE_INDOOR("id"),
        MAP_ENABLE_INDOOR_LEVEL_PICKER("iep"),
        MAP_DISABLE_INDOOR_LEVEL_PICKER("idp"),
        INDOOR_ACTIVATE_LEVEL("isa"),
        INDOOR_GET_ACTIVE_LEVEL("ia"),
        INDOOR_GET_FOCUSED_BULIDING("if"),
        INDOOR_GET_LEVELS("il"),
        INDOOR_GET_VISIBLE_BUILDINGS("iv"),
        INDOOR_GET_DEFAULT_LEVEL("ix"),
        INDOOR_IS_UNDERGROUND("iu"),
        MAP_REQUEST_TILE_PREFETCH_AREA("ra"),
        MAP_SNAPSHOT("Sn"),
        MAP_SNAPSHOT_ALLOCATED_BITMAP("SN"),
        PANORAMA_CREATED("pc"),
        PANORAMA_ADD_MARKER("pma"),
        PANORAMA_REMOVE_MARKER("pmr"),
        PANORAMA_ENABLE_ZOOM("pez"),
        PANORAMA_ENABLE_PANNING("pep"),
        PANORAMA_ENABLE_NAVIGATION("pen"),
        PANORAMA_ENABLE_STREET_NAMES("pes"),
        PANORAMA_ANIMATE_TO("pat"),
        PANORAMA_SET_POSITION_WITH_ID("ppi"),
        PANORAMA_SET_POSITION("psp"),
        PANORAMA_SET_POSITION_WITH_RADIUS("ppr"),
        PANORAMA_SET_CHANGE_LISTENER("pcl"),
        PANORAMA_SET_CAMERA_CHANGE_LISTENER("pccl"),
        PANORAMA_SET_CLICK_LISTENER("pCl"),
        PANORAMA_PROJECT_TO_ORIENTATION("ppo"),
        PANORAMA_PROJECT_TO_POINT("ppp"),
        MAP_SET_VISIBLE_REGION("vr"),
        MAP_SET_OAUTH_TOKEN_PROVIDER("moauth"),
        INTENT_VIEW_NO_MARKERS("gm0"),
        INTENT_VIEW_ONE_MARKER("gm1"),
        INTENT_VIEW_MULTIPLE_MARKERS_ONE_SELECTED("gm2"),
        INTENT_VIEW_MULTIPLE_MARKERS_NONE_SELECTED("gm3"),
        INTENT_DIRECTIONS("gmd"),
        DEPRECATED_MARKER_ICON_FACTORY_CREATE_ICON("mf");
        
        public final String cf;

        private UsageLog(String str) {
            this.cf = str;
        }
    }

    void m8832a();

    void m8833a(UsageLog usageLog);

    void m8834a(String str);

    void m8835b(UsageLog usageLog);

    static {
        UsageLog usageLog = new UsageLog();
    }
}
