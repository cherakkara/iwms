package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.sothree.slidinguppanel.p086a.R.R;

class ConnectivityManagerCompatGingerbread {
    ConnectivityManagerCompatGingerbread() {
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return true;
        }
        switch (activeNetworkInfo.getType()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return true;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return false;
            default:
                return true;
        }
    }
}
