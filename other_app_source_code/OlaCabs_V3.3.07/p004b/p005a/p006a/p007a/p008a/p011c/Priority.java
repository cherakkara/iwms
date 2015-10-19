package p004b.p005a.p006a.p007a.p008a.p011c;

/* renamed from: b.a.a.a.a.c.e */
public enum Priority {
    LOW,
    NORMAL,
    HIGH,
    IMMEDIATE;

    static <Y> int m291a(PriorityProvider priorityProvider, Y y) {
        Priority priority;
        if (y instanceof PriorityProvider) {
            priority = ((PriorityProvider) y).getPriority();
        } else {
            priority = NORMAL;
        }
        return priority.ordinal() - priorityProvider.getPriority().ordinal();
    }
}
