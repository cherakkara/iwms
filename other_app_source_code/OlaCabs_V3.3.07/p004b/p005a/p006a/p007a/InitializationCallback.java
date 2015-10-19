package p004b.p005a.p006a.p007a;

/* renamed from: b.a.a.a.f */
public interface InitializationCallback<T> {
    public static final InitializationCallback f400d;

    /* renamed from: b.a.a.a.f.a */
    public static class InitializationCallback implements InitializationCallback<Object> {
        private InitializationCallback() {
        }

        public void m530a(Object obj) {
        }

        public void m529a(Exception exception) {
        }
    }

    void m496a(Exception exception);

    void m497a(T t);

    static {
        f400d = new InitializationCallback();
    }
}
