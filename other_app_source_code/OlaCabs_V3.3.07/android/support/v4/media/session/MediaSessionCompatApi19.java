package android.support.v4.media.session;

import android.graphics.Bitmap;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompatApi14.Callback;
import org.apache.http.HttpStatus;

public class MediaSessionCompatApi19 {
    private static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    private static final String METADATA_KEY_ART = "android.media.metadata.ART";
    private static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    private static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";

    static class OnMetadataUpdateListener<T extends Callback> implements android.media.RemoteControlClient.OnMetadataUpdateListener {
        protected final T mCallback;

        public OnMetadataUpdateListener(T t) {
            this.mCallback = t;
        }

        public void onMetadataUpdate(int i, Object obj) {
            if (i == 268435457 && (obj instanceof Rating)) {
                this.mCallback.onSetRating(obj);
            }
        }
    }

    public static Object createMetadataUpdateListener(Callback callback) {
        return new OnMetadataUpdateListener(callback);
    }

    public static void setMetadata(Object obj, Bundle bundle, boolean z) {
        MetadataEditor editMetadata = ((RemoteControlClient) obj).editMetadata(true);
        MediaSessionCompatApi14.buildOldMetadata(bundle, editMetadata);
        addNewMetadata(bundle, editMetadata);
        if (z && VERSION.SDK_INT > 19) {
            editMetadata.addEditableKey(268435457);
        }
        editMetadata.apply();
    }

    public static void setOnMetadataUpdateListener(Object obj, Object obj2) {
        ((RemoteControlClient) obj).setMetadataUpdateListener((android.media.RemoteControlClient.OnMetadataUpdateListener) obj2);
    }

    static void addNewMetadata(Bundle bundle, MetadataEditor metadataEditor) {
        if (bundle.containsKey(METADATA_KEY_RATING)) {
            metadataEditor.putObject(HttpStatus.SC_SWITCHING_PROTOCOLS, bundle.getParcelable(METADATA_KEY_RATING));
        }
        if (bundle.containsKey(METADATA_KEY_USER_RATING)) {
            metadataEditor.putObject(268435457, bundle.getParcelable(METADATA_KEY_USER_RATING));
        }
        if (bundle.containsKey(METADATA_KEY_ART)) {
            metadataEditor.putBitmap(100, (Bitmap) bundle.getParcelable(METADATA_KEY_ART));
        } else if (bundle.containsKey(METADATA_KEY_ALBUM_ART)) {
            metadataEditor.putBitmap(100, (Bitmap) bundle.getParcelable(METADATA_KEY_ALBUM_ART));
        }
    }
}
