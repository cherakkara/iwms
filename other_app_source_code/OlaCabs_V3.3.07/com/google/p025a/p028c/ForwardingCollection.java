package com.google.p025a.p028c;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.google.a.c.r */
public abstract class ForwardingCollection<E> extends ForwardingObject implements Collection<E> {
    protected abstract Collection<E> m2965a();

    protected /* synthetic */ Object m2966b() {
        return m2965a();
    }

    protected ForwardingCollection() {
    }

    public Iterator<E> iterator() {
        return m2965a().iterator();
    }

    public int size() {
        return m2965a().size();
    }

    public boolean removeAll(Collection<?> collection) {
        return m2965a().removeAll(collection);
    }

    public boolean isEmpty() {
        return m2965a().isEmpty();
    }

    public boolean contains(Object obj) {
        return m2965a().contains(obj);
    }

    public boolean add(E e) {
        return m2965a().add(e);
    }

    public boolean remove(Object obj) {
        return m2965a().remove(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return m2965a().containsAll(collection);
    }

    public boolean addAll(Collection<? extends E> collection) {
        return m2965a().addAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return m2965a().retainAll(collection);
    }

    public void clear() {
        m2965a().clear();
    }

    public Object[] toArray() {
        return m2965a().toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return m2965a().toArray(tArr);
    }
}
