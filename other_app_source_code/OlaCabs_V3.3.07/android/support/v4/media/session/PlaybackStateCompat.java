package android.support.v4.media.session;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.text.TextUtils;

public final class PlaybackStateCompat implements Parcelable {
    public static final long ACTION_FAST_FORWARD = 64;
    public static final long ACTION_PAUSE = 2;
    public static final long ACTION_PLAY = 4;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048;
    public static final long ACTION_PLAY_PAUSE = 512;
    public static final long ACTION_REWIND = 8;
    public static final long ACTION_SEEK_TO = 256;
    public static final long ACTION_SET_RATING = 128;
    public static final long ACTION_SKIP_TO_NEXT = 32;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096;
    public static final long ACTION_STOP = 1;
    public static final Creator<PlaybackStateCompat> CREATOR;
    public static final long PLAYBACK_POSITION_UNKNOWN = -1;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    public static final int STATE_STOPPED = 1;
    private final long mActions;
    private final long mBufferedPosition;
    private final CharSequence mErrorMessage;
    private final long mPosition;
    private final float mSpeed;
    private final int mState;
    private Object mStateObj;
    private final long mUpdateTime;

    /* renamed from: android.support.v4.media.session.PlaybackStateCompat.1 */
    static class C00451 implements Creator<PlaybackStateCompat> {
        C00451() {
        }

        public PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(null);
        }

        public PlaybackStateCompat[] newArray(int i) {
            return new PlaybackStateCompat[i];
        }
    }

    public static final class Builder {
        private long mActions;
        private long mBufferedPosition;
        private CharSequence mErrorMessage;
        private long mPosition;
        private float mRate;
        private int mState;
        private long mUpdateTime;

        public Builder(PlaybackStateCompat playbackStateCompat) {
            this.mState = playbackStateCompat.mState;
            this.mPosition = playbackStateCompat.mPosition;
            this.mRate = playbackStateCompat.mSpeed;
            this.mUpdateTime = playbackStateCompat.mUpdateTime;
            this.mBufferedPosition = playbackStateCompat.mBufferedPosition;
            this.mActions = playbackStateCompat.mActions;
            this.mErrorMessage = playbackStateCompat.mErrorMessage;
        }

        public Builder setState(int i, long j, float f) {
            return setState(i, j, f, SystemClock.elapsedRealtime());
        }

        public Builder setState(int i, long j, float f, long j2) {
            this.mState = i;
            this.mPosition = j;
            this.mUpdateTime = j2;
            this.mRate = f;
            return this;
        }

        public Builder setBufferedPosition(long j) {
            this.mBufferedPosition = j;
            return this;
        }

        public Builder setActions(long j) {
            this.mActions = j;
            return this;
        }

        public Builder setErrorMessage(CharSequence charSequence) {
            this.mErrorMessage = charSequence;
            return this;
        }

        public PlaybackStateCompat build() {
            return new PlaybackStateCompat(this.mPosition, this.mBufferedPosition, this.mRate, this.mActions, this.mErrorMessage, this.mUpdateTime, null);
        }
    }

    public static final class CustomAction implements Parcelable {
        public static final Creator<CustomAction> CREATOR;
        private final String mAction;
        private final Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;

        /* renamed from: android.support.v4.media.session.PlaybackStateCompat.CustomAction.1 */
        static class C00461 implements Creator<CustomAction> {
            C00461() {
            }

            public CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(null);
            }

            public CustomAction[] newArray(int i) {
                return new CustomAction[i];
            }
        }

        public static final class Builder {
            private final String mAction;
            private Bundle mExtras;
            private final int mIcon;
            private final CharSequence mName;

            public Builder(String str, CharSequence charSequence, int i) {
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
                } else if (TextUtils.isEmpty(charSequence)) {
                    throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
                } else if (i == 0) {
                    throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
                } else {
                    this.mAction = str;
                    this.mName = charSequence;
                    this.mIcon = i;
                }
            }

            public Builder setExtras(Bundle bundle) {
                this.mExtras = bundle;
                return this;
            }

            public CustomAction build() {
                return new CustomAction(this.mName, this.mIcon, this.mExtras, null);
            }
        }

        private CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.mAction = str;
            this.mName = charSequence;
            this.mIcon = i;
            this.mExtras = bundle;
        }

        private CustomAction(Parcel parcel) {
            this.mAction = parcel.readString();
            this.mName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.mIcon = parcel.readInt();
            this.mExtras = parcel.readBundle();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mAction);
            TextUtils.writeToParcel(this.mName, parcel, i);
            parcel.writeInt(this.mIcon);
            parcel.writeBundle(this.mExtras);
        }

        public int describeContents() {
            return PlaybackStateCompat.STATE_NONE;
        }

        static {
            CREATOR = new C00461();
        }

        public String getAction() {
            return this.mAction;
        }

        public CharSequence getName() {
            return this.mName;
        }

        public int getIcon() {
            return this.mIcon;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public String toString() {
            return "Action:mName='" + this.mName + ", mIcon=" + this.mIcon + ", mExtras=" + this.mExtras;
        }
    }

    private PlaybackStateCompat(int i, long j, long j2, float f, long j3, CharSequence charSequence, long j4) {
        this.mState = i;
        this.mPosition = j;
        this.mBufferedPosition = j2;
        this.mSpeed = f;
        this.mActions = j3;
        this.mErrorMessage = charSequence;
        this.mUpdateTime = j4;
    }

    private PlaybackStateCompat(Parcel parcel) {
        this.mState = parcel.readInt();
        this.mPosition = parcel.readLong();
        this.mSpeed = parcel.readFloat();
        this.mUpdateTime = parcel.readLong();
        this.mBufferedPosition = parcel.readLong();
        this.mActions = parcel.readLong();
        this.mErrorMessage = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PlaybackState {");
        stringBuilder.append("state=").append(this.mState);
        stringBuilder.append(", position=").append(this.mPosition);
        stringBuilder.append(", buffered position=").append(this.mBufferedPosition);
        stringBuilder.append(", speed=").append(this.mSpeed);
        stringBuilder.append(", updated=").append(this.mUpdateTime);
        stringBuilder.append(", actions=").append(this.mActions);
        stringBuilder.append(", error=").append(this.mErrorMessage);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public int describeContents() {
        return STATE_NONE;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mState);
        parcel.writeLong(this.mPosition);
        parcel.writeFloat(this.mSpeed);
        parcel.writeLong(this.mUpdateTime);
        parcel.writeLong(this.mBufferedPosition);
        parcel.writeLong(this.mActions);
        TextUtils.writeToParcel(this.mErrorMessage, parcel, i);
    }

    public int getState() {
        return this.mState;
    }

    public long getPosition() {
        return this.mPosition;
    }

    public long getBufferedPosition() {
        return this.mBufferedPosition;
    }

    public float getPlaybackSpeed() {
        return this.mSpeed;
    }

    public long getActions() {
        return this.mActions;
    }

    public CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }

    public long getLastPositionUpdateTime() {
        return this.mUpdateTime;
    }

    public static PlaybackStateCompat fromPlaybackState(Object obj) {
        if (obj == null || VERSION.SDK_INT < 21) {
            return null;
        }
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(obj), PlaybackStateCompatApi21.getPosition(obj), PlaybackStateCompatApi21.getBufferedPosition(obj), PlaybackStateCompatApi21.getPlaybackSpeed(obj), PlaybackStateCompatApi21.getActions(obj), PlaybackStateCompatApi21.getErrorMessage(obj), PlaybackStateCompatApi21.getLastPositionUpdateTime(obj));
        playbackStateCompat.mStateObj = obj;
        return playbackStateCompat;
    }

    public Object getPlaybackState() {
        if (this.mStateObj != null || VERSION.SDK_INT < 21) {
            return this.mStateObj;
        }
        this.mStateObj = PlaybackStateCompatApi21.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime);
        return this.mStateObj;
    }

    static {
        CREATOR = new C00451();
    }
}
