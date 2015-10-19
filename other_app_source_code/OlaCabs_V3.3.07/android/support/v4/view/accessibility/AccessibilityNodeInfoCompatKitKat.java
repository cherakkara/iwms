package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatKitKat {

    static class CollectionInfo {
        CollectionInfo() {
        }

        static int getColumnCount(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo) obj).getColumnCount();
        }

        static int getRowCount(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo) obj).getRowCount();
        }

        static boolean isHierarchical(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo) obj).isHierarchical();
        }
    }

    static class CollectionItemInfo {
        CollectionItemInfo() {
        }

        static int getColumnIndex(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo) obj).getColumnIndex();
        }

        static int getColumnSpan(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo) obj).getColumnSpan();
        }

        static int getRowIndex(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo) obj).getRowIndex();
        }

        static int getRowSpan(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo) obj).getRowSpan();
        }

        static boolean isHeading(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo) obj).isHeading();
        }
    }

    static class RangeInfo {
        RangeInfo() {
        }

        static float getCurrent(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo) obj).getCurrent();
        }

        static float getMax(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo) obj).getMax();
        }

        static float getMin(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo) obj).getMin();
        }

        static int getType(Object obj) {
            return ((android.view.accessibility.AccessibilityNodeInfo.RangeInfo) obj).getType();
        }
    }

    AccessibilityNodeInfoCompatKitKat() {
    }

    static int getLiveRegion(Object obj) {
        return ((AccessibilityNodeInfo) obj).getLiveRegion();
    }

    static void setLiveRegion(Object obj, int i) {
        ((AccessibilityNodeInfo) obj).setLiveRegion(i);
    }

    static Object getCollectionInfo(Object obj) {
        return ((AccessibilityNodeInfo) obj).getCollectionInfo();
    }

    static Object getCollectionItemInfo(Object obj) {
        return ((AccessibilityNodeInfo) obj).getCollectionItemInfo();
    }

    public static void setCollectionInfo(Object obj, Object obj2) {
        ((AccessibilityNodeInfo) obj).setCollectionInfo((android.view.accessibility.AccessibilityNodeInfo.CollectionInfo) obj2);
    }

    public static void setCollectionItemInfo(Object obj, Object obj2) {
        ((AccessibilityNodeInfo) obj).setCollectionItemInfo((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo) obj2);
    }

    static Object getRangeInfo(Object obj) {
        return ((AccessibilityNodeInfo) obj).getRangeInfo();
    }

    public static Object obtainCollectionInfo(int i, int i2, boolean z, int i3) {
        return android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z);
    }

    public static Object obtainCollectionItemInfo(int i, int i2, int i3, int i4, boolean z) {
        return android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z);
    }
}
