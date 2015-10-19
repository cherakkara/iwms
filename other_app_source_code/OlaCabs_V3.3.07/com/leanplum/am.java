package com.leanplum;

enum am {
    MCC(new an()),
    MNC(new ay()),
    LANGUAGE(new aA()),
    REGION(new aB()),
    LAYOUT_DIRECTION(new aC()),
    SMALLEST_WIDTH(new aD()),
    AVAILABLE_WIDTH(new aE()),
    AVAILABLE_HEIGHT(new aF()),
    SCREEN_SIZE(new aG()),
    SCREEN_ASPECT(new ao()),
    SCREEN_ORIENTATION(new ap()),
    UI_MODE(new aq()),
    NIGHT_MODE(new ar()),
    SCREEN_PIXEL_DENSITY(new as()),
    TOUCHSCREEN_TYPE(new at()),
    KEYBOARD_AVAILABILITY(new au()),
    PRIMARY_TEXT_INPUTMETHOD(new av()),
    NAVIGATION_KEY_AVAILABILITY(new aw()),
    PRIMARY_NON_TOUCH_NAVIGATION_METHOD(new ax()),
    PLATFORM_VERSION(new az());
    
    private aH f8771u;

    private am(aH aHVar) {
        this.f8771u = aHVar;
    }

    public final aH m12734a() {
        return this.f8771u;
    }
}
