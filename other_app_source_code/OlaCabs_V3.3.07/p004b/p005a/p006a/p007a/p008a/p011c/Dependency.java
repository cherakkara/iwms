package p004b.p005a.p006a.p007a.p008a.p011c;

import java.util.Collection;

/* renamed from: b.a.a.a.a.c.b */
public interface Dependency<T> {
    void addDependency(T t);

    boolean areDependenciesMet();

    Collection<T> getDependencies();
}
