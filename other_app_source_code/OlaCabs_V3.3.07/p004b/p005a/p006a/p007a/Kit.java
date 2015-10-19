package p004b.p005a.p006a.p007a;

import android.content.Context;
import java.io.File;
import java.util.Collection;
import p004b.p005a.p006a.p007a.p008a.p010b.IdManager;
import p004b.p005a.p006a.p007a.p008a.p011c.DependsOn;
import p004b.p005a.p006a.p007a.p008a.p011c.Task;

/* renamed from: b.a.a.a.i */
public abstract class Kit<Result> implements Comparable<Kit> {
    Context context;
    Fabric fabric;
    IdManager idManager;
    InitializationCallback<Result> initializationCallback;
    InitializationTask<Result> initializationTask;

    protected abstract Result doInBackground();

    public abstract String getIdentifier();

    public abstract String getVersion();

    public Kit() {
        this.initializationTask = new InitializationTask(this);
    }

    void injectParameters(Context context, Fabric fabric, InitializationCallback<Result> initializationCallback, IdManager idManager) {
        this.fabric = fabric;
        this.context = new FabricContext(context, getIdentifier(), getPath());
        this.initializationCallback = initializationCallback;
        this.idManager = idManager;
    }

    final void initialize() {
        this.initializationTask.m298a(this.fabric.m524f(), (Void) null);
    }

    protected boolean onPreExecute() {
        return true;
    }

    protected void onPostExecute(Result result) {
    }

    protected void onCancelled(Result result) {
    }

    protected IdManager getIdManager() {
        return this.idManager;
    }

    public Context getContext() {
        return this.context;
    }

    public Fabric getFabric() {
        return this.fabric;
    }

    public String getPath() {
        return ".Fabric" + File.separator + getIdentifier();
    }

    public int compareTo(Kit kit) {
        if (containsAnnotatedDependency(kit)) {
            return 1;
        }
        if (kit.containsAnnotatedDependency(this)) {
            return -1;
        }
        if (hasAnnotatedDependency() && !kit.hasAnnotatedDependency()) {
            return 1;
        }
        if (hasAnnotatedDependency() || !kit.hasAnnotatedDependency()) {
            return 0;
        }
        return -1;
    }

    boolean containsAnnotatedDependency(Kit kit) {
        DependsOn dependsOn = (DependsOn) getClass().getAnnotation(DependsOn.class);
        if (dependsOn != null) {
            for (Object equals : dependsOn.m290a()) {
                if (equals.equals(kit.getClass())) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean hasAnnotatedDependency() {
        return ((DependsOn) getClass().getAnnotation(DependsOn.class)) != null;
    }

    protected Collection<Task> getDependencies() {
        return this.initializationTask.getDependencies();
    }
}
