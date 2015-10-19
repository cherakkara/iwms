package android.support.v4.media;

import android.os.Build.VERSION;
import android.support.v4.media.VolumeProviderCompatApi21.Delegate;

public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private Callback mCallback;
    private final int mControlType;
    private int mCurrentVolume;
    private final int mMaxVolume;
    private Object mVolumeProviderObj;

    /* renamed from: android.support.v4.media.VolumeProviderCompat.1 */
    class C00351 implements Delegate {
        C00351() {
        }

        public void onSetVolumeTo(int i) {
            VolumeProviderCompat.this.onSetVolumeTo(i);
        }

        public void onAdjustVolume(int i) {
            VolumeProviderCompat.this.onAdjustVolume(i);
        }
    }

    public static abstract class Callback {
        public abstract void onVolumeChanged(VolumeProviderCompat volumeProviderCompat);
    }

    public VolumeProviderCompat(int i, int i2, int i3) {
        this.mControlType = i;
        this.mMaxVolume = i2;
        this.mCurrentVolume = i3;
    }

    public final int getCurrentVolume() {
        return this.mCurrentVolume;
    }

    public final int getVolumeControl() {
        return this.mControlType;
    }

    public final int getMaxVolume() {
        return this.mMaxVolume;
    }

    public final void setCurrentVolume(int i) {
        if (this.mCallback != null) {
            this.mCallback.onVolumeChanged(this);
        }
    }

    public void onSetVolumeTo(int i) {
    }

    public void onAdjustVolume(int i) {
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public Object getVolumeProvider() {
        if (this.mVolumeProviderObj != null || VERSION.SDK_INT < 21) {
            return this.mVolumeProviderObj;
        }
        this.mVolumeProviderObj = VolumeProviderCompatApi21.createVolumeProvider(this.mControlType, this.mMaxVolume, this.mCurrentVolume, new C00351());
        return this.mVolumeProviderObj;
    }
}
