package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.localytics.android.Localytics;
import com.olacabs.customer.R;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.aw;
import com.olacabs.customer.p076d.ay;
import com.olacabs.customer.utils.Utils;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.olacabs.customer.ui.k */
public class FavouriteListViewAdapter extends BaseAdapter {
    public static String f10820a;
    public static String f10821b;
    public static String f10822c;
    public static String f10823d;
    FavouritesFragment f10824e;
    ProgressDialog f10825f;
    private String f10826g;
    private Context f10827h;
    private List<aw> f10828i;
    private boolean f10829j;
    private aj f10830k;

    /* renamed from: com.olacabs.customer.ui.k.1 */
    class FavouriteListViewAdapter implements aj {
        final /* synthetic */ FavouriteListViewAdapter f10805a;

        FavouriteListViewAdapter(FavouriteListViewAdapter favouriteListViewAdapter) {
            this.f10805a = favouriteListViewAdapter;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to delete favourite", th);
            this.f10805a.f10825f.dismiss();
        }

        public void onSuccess(Object obj) {
            ay ayVar = (ay) obj;
            if (ayVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13313b(String.valueOf(this.f10805a.f10828i), new Object[0]);
                Collection arrayList = new ArrayList();
                for (aw awVar : this.f10805a.f10828i) {
                    if (ayVar.getFavId() == awVar.getId()) {
                        Localytics.tagEvent("Favorite Deleted");
                        arrayList.add(awVar);
                    }
                }
                if (arrayList.size() > 0) {
                    this.f10805a.f10828i.removeAll(arrayList);
                    this.f10805a.notifyDataSetChanged();
                }
                if (this.f10805a.f10828i.size() < 1) {
                    this.f10805a.f10824e.m14444a();
                }
            } else if (ayVar.getStatus().equalsIgnoreCase("FAILURE")) {
                OLog.m13318e("Delete favourite failed ", new Object[0]);
                if (ayVar.getText() != null) {
                    this.f10805a.m14427a(ayVar.getText());
                } else {
                    String reason = ayVar.getReason();
                    if (reason.equalsIgnoreCase("FAVOURITE_NOT_FOUND")) {
                        this.f10805a.m14427a(FavouriteListViewAdapter.f10820a);
                    } else if (reason.equalsIgnoreCase("USER_FAVOURITE_MISMATCH")) {
                        this.f10805a.m14427a(FavouriteListViewAdapter.f10821b);
                    } else if (reason.equalsIgnoreCase("INVALID_JSON")) {
                        this.f10805a.m14427a(FavouriteListViewAdapter.f10822c);
                    } else if (reason.equalsIgnoreCase("UNKNOWN")) {
                        this.f10805a.m14427a(FavouriteListViewAdapter.f10823d);
                    }
                }
            }
            this.f10805a.f10825f.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.k.2 */
    class FavouriteListViewAdapter implements OnClickListener {
        final /* synthetic */ int f10806a;
        final /* synthetic */ FavouriteListViewAdapter f10807b;

        FavouriteListViewAdapter(FavouriteListViewAdapter favouriteListViewAdapter, int i) {
            this.f10807b = favouriteListViewAdapter;
            this.f10806a = i;
        }

        public void onClick(View view) {
            if (!this.f10807b.f10829j) {
                return;
            }
            if (Utils.m14909a(this.f10807b.f10827h.getApplicationContext())) {
                this.f10807b.m14428a(" Are you sure you would like to delete " + ((aw) this.f10807b.f10828i.get(this.f10806a)).getName() + " ?", this.f10806a, (long) ((aw) this.f10807b.f10828i.get(this.f10806a)).getId());
            } else {
                Toast.makeText(this.f10807b.f10827h, "Internet connection is not available", 0).show();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.k.3 */
    class FavouriteListViewAdapter implements OnClickListener {
        final /* synthetic */ AlertDialog f10808a;
        final /* synthetic */ long f10809b;
        final /* synthetic */ FavouriteListViewAdapter f10810c;

        FavouriteListViewAdapter(FavouriteListViewAdapter favouriteListViewAdapter, AlertDialog alertDialog, long j) {
            this.f10810c = favouriteListViewAdapter;
            this.f10808a = alertDialog;
            this.f10809b = j;
        }

        public void onClick(View view) {
            this.f10808a.dismiss();
            this.f10810c.m14423a(this.f10809b);
        }
    }

    /* renamed from: com.olacabs.customer.ui.k.4 */
    class FavouriteListViewAdapter implements OnClickListener {
        final /* synthetic */ AlertDialog f10811a;
        final /* synthetic */ FavouriteListViewAdapter f10812b;

        FavouriteListViewAdapter(FavouriteListViewAdapter favouriteListViewAdapter, AlertDialog alertDialog) {
            this.f10812b = favouriteListViewAdapter;
            this.f10811a = alertDialog;
        }

        public void onClick(View view) {
            this.f10811a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.k.5 */
    class FavouriteListViewAdapter implements OnClickListener {
        final /* synthetic */ AlertDialog f10813a;
        final /* synthetic */ FavouriteListViewAdapter f10814b;

        FavouriteListViewAdapter(FavouriteListViewAdapter favouriteListViewAdapter, AlertDialog alertDialog) {
            this.f10814b = favouriteListViewAdapter;
            this.f10813a = alertDialog;
        }

        public void onClick(View view) {
            this.f10813a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.k.a */
    public class FavouriteListViewAdapter {
        public ImageView f10815a;
        public ImageView f10816b;
        public TextView f10817c;
        public TextView f10818d;
        final /* synthetic */ FavouriteListViewAdapter f10819e;

        public FavouriteListViewAdapter(FavouriteListViewAdapter favouriteListViewAdapter) {
            this.f10819e = favouriteListViewAdapter;
        }
    }

    static {
        f10820a = "Edit non-existing favourite";
        f10821b = "Edit or Delete other user's favourite";
        f10822c = "Incorrect JSON sent";
        f10823d = "Unknown error";
    }

    public FavouriteListViewAdapter(Context context, List<aw> list, boolean z, FavouritesFragment favouritesFragment) {
        this.f10826g = FavouriteListViewAdapter.class.getSimpleName();
        this.f10830k = new FavouriteListViewAdapter(this);
        this.f10827h = context;
        this.f10828i = list;
        this.f10829j = z;
        this.f10824e = favouritesFragment;
    }

    public int getCount() {
        return this.f10828i.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean isEmpty() {
        return this.f10828i.isEmpty();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            FavouriteListViewAdapter favouriteListViewAdapter = new FavouriteListViewAdapter(this);
            view = ((Activity) this.f10827h).getLayoutInflater().inflate(R.layout.item_search, viewGroup, false);
            favouriteListViewAdapter.f10815a = (ImageView) view.findViewById(R.id.imageItem);
            favouriteListViewAdapter.f10816b = (ImageView) view.findViewById(R.id.arrowId);
            favouriteListViewAdapter.f10817c = (TextView) view.findViewById(R.id.itemText);
            favouriteListViewAdapter.f10818d = (TextView) view.findViewById(R.id.itemAddressText);
            view.setTag(favouriteListViewAdapter);
        }
        FavouriteListViewAdapter favouriteListViewAdapter2 = (FavouriteListViewAdapter) view.getTag();
        favouriteListViewAdapter2.f10817c.setText(((aw) this.f10828i.get(i)).getName());
        favouriteListViewAdapter2.f10818d.setText(URLDecoder.decode(((aw) this.f10828i.get(i)).getAddress()));
        if (this.f10829j) {
            favouriteListViewAdapter2.f10815a.setImageResource(R.drawable.bg_delete_button_favourite);
            favouriteListViewAdapter2.f10816b.setVisibility(0);
        } else {
            favouriteListViewAdapter2.f10816b.setVisibility(8);
            favouriteListViewAdapter2.f10815a.setImageResource(R.drawable.ic_default_favourite);
        }
        favouriteListViewAdapter2.f10815a.setOnClickListener(new FavouriteListViewAdapter(this, i));
        return view;
    }

    protected void m14428a(String str, int i, long j) {
        View inflate = ((LayoutInflater) this.f10827h.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new Builder(this.f10827h).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText("Delete Favourite?");
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_yes).setOnClickListener(new FavouriteListViewAdapter(this, create, j));
        inflate.findViewById(R.id.button_no).setOnClickListener(new FavouriteListViewAdapter(this, create));
        create.show();
    }

    private void m14423a(long j) {
        this.f10825f = new ProgressDialog(this.f10827h, R.style.TransparentProgressDialog);
        this.f10825f.setIndeterminateDrawable(this.f10827h.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10825f.setCancelable(false);
        this.f10825f.show();
        ((OlaApp) this.f10827h.getApplicationContext()).m12878a().m13171a(new WeakReference(this.f10830k), j, this.f10826g);
    }

    protected void m14427a(String str) {
        View inflate = ((LayoutInflater) this.f10827h.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10827h).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText("Failure");
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new FavouriteListViewAdapter(this, create));
        create.show();
    }
}
