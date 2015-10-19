package p004b.p005a.p006a.p007a.p008a.p010b;

import org.apache.http.HttpStatus;

/* renamed from: b.a.a.a.a.b.r */
public class ResponseParser {
    public static int m254a(int i) {
        if (i >= HttpStatus.SC_OK && i <= 299) {
            return 0;
        }
        if (i >= HttpStatus.SC_MULTIPLE_CHOICES && i <= 399) {
            return 1;
        }
        if (i >= HttpStatus.SC_BAD_REQUEST && i <= 499) {
            return 0;
        }
        if (i >= HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            return 1;
        }
        return 1;
    }
}
