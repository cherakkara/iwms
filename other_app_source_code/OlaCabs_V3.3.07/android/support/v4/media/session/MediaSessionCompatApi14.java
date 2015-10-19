package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.os.Bundle;
import android.os.ResultReceiver;

public class MediaSessionCompatApi14 {
    private static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    private static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    private static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    private static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    private static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    private static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    private static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    private static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    private static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    private static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    private static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    private static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    private static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    private static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    private static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    static final int RCC_PLAYSTATE_NONE = 0;
    static final int STATE_BUFFERING = 6;
    static final int STATE_CONNECTING = 8;
    static final int STATE_ERROR = 7;
    static final int STATE_FAST_FORWARDING = 4;
    static final int STATE_NONE = 0;
    static final int STATE_PAUSED = 2;
    static final int STATE_PLAYING = 3;
    static final int STATE_REWINDING = 5;
    static final int STATE_SKIPPING_TO_NEXT = 10;
    static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    static final int STATE_STOPPED = 1;

    public interface Callback {
        void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        void onFastForward();

        boolean onMediaButtonEvent(Intent intent);

        void onPause();

        void onPlay();

        void onRewind();

        void onSeekTo(long j);

        void onSetRating(Object obj);

        void onSkipToNext();

        void onSkipToPrevious();

        void onStop();
    }

    public static Object createRemoteControlClient(PendingIntent pendingIntent) {
        return new RemoteControlClient(pendingIntent);
    }

    public static void setState(Object obj, int i) {
        ((RemoteControlClient) obj).setPlaybackState(getRccStateFromState(i));
    }

    public static void setMetadata(Object obj, Bundle bundle) {
        MetadataEditor editMetadata = ((RemoteControlClient) obj).editMetadata(true);
        buildOldMetadata(bundle, editMetadata);
        editMetadata.apply();
    }

    public static void registerRemoteControlClient(Context context, Object obj) {
        ((AudioManager) context.getSystemService("audio")).registerRemoteControlClient((RemoteControlClient) obj);
    }

    public static void unregisterRemoteControlClient(Context context, Object obj) {
        ((AudioManager) context.getSystemService("audio")).unregisterRemoteControlClient((RemoteControlClient) obj);
    }

    static int getRccStateFromState(int i) {
        switch (i) {
            case STATE_NONE /*0*/:
                return STATE_NONE;
            case STATE_STOPPED /*1*/:
                return STATE_STOPPED;
            case STATE_PAUSED /*2*/:
                return STATE_PAUSED;
            case STATE_PLAYING /*3*/:
                return STATE_PLAYING;
            case STATE_FAST_FORWARDING /*4*/:
                return STATE_FAST_FORWARDING;
            case STATE_REWINDING /*5*/:
                return STATE_REWINDING;
            case STATE_BUFFERING /*6*/:
            case STATE_CONNECTING /*8*/:
                return STATE_CONNECTING;
            case STATE_ERROR /*7*/:
                return STATE_SKIPPING_TO_PREVIOUS;
            case STATE_SKIPPING_TO_PREVIOUS /*9*/:
                return STATE_ERROR;
            case STATE_SKIPPING_TO_NEXT /*10*/:
                return STATE_BUFFERING;
            default:
                return -1;
        }
    }

    static void buildOldMetadata(Bundle bundle, MetadataEditor metadataEditor) {
        if (bundle.containsKey(METADATA_KEY_ALBUM)) {
            metadataEditor.putString(STATE_STOPPED, bundle.getString(METADATA_KEY_ALBUM));
        }
        if (bundle.containsKey(METADATA_KEY_ALBUM_ARTIST)) {
            metadataEditor.putString(13, bundle.getString(METADATA_KEY_ALBUM_ARTIST));
        }
        if (bundle.containsKey(METADATA_KEY_ARTIST)) {
            metadataEditor.putString(STATE_PAUSED, bundle.getString(METADATA_KEY_ARTIST));
        }
        if (bundle.containsKey(METADATA_KEY_AUTHOR)) {
            metadataEditor.putString(STATE_PLAYING, bundle.getString(METADATA_KEY_AUTHOR));
        }
        if (bundle.containsKey(METADATA_KEY_COMPILATION)) {
            metadataEditor.putString(15, bundle.getString(METADATA_KEY_COMPILATION));
        }
        if (bundle.containsKey(METADATA_KEY_COMPOSER)) {
            metadataEditor.putString(STATE_FAST_FORWARDING, bundle.getString(METADATA_KEY_COMPOSER));
        }
        if (bundle.containsKey(METADATA_KEY_DATE)) {
            metadataEditor.putString(STATE_REWINDING, bundle.getString(METADATA_KEY_DATE));
        }
        if (bundle.containsKey(METADATA_KEY_DISC_NUMBER)) {
            metadataEditor.putLong(14, bundle.getLong(METADATA_KEY_DISC_NUMBER));
        }
        if (bundle.containsKey(METADATA_KEY_DURATION)) {
            metadataEditor.putLong(STATE_SKIPPING_TO_PREVIOUS, bundle.getLong(METADATA_KEY_DURATION));
        }
        if (bundle.containsKey(METADATA_KEY_GENRE)) {
            metadataEditor.putString(STATE_BUFFERING, bundle.getString(METADATA_KEY_GENRE));
        }
        if (bundle.containsKey(METADATA_KEY_NUM_TRACKS)) {
            metadataEditor.putLong(STATE_SKIPPING_TO_NEXT, bundle.getLong(METADATA_KEY_NUM_TRACKS));
        }
        if (bundle.containsKey(METADATA_KEY_TITLE)) {
            metadataEditor.putString(STATE_ERROR, bundle.getString(METADATA_KEY_TITLE));
        }
        if (bundle.containsKey(METADATA_KEY_TRACK_NUMBER)) {
            metadataEditor.putLong(STATE_NONE, bundle.getLong(METADATA_KEY_TRACK_NUMBER));
        }
        if (bundle.containsKey(METADATA_KEY_WRITER)) {
            metadataEditor.putString(11, bundle.getString(METADATA_KEY_WRITER));
        }
        if (bundle.containsKey(METADATA_KEY_YEAR)) {
            metadataEditor.putString(STATE_CONNECTING, bundle.getString(METADATA_KEY_YEAR));
        }
    }
}
