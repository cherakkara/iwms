package com.android.volley;

import android.content.Intent;

/* renamed from: com.android.volley.a */
public class AuthFailureError extends VolleyError {
    private Intent f466b;

    public AuthFailureError(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public String getMessage() {
        if (this.f466b != null) {
            return "User needs to (re)enter credentials.";
        }
        return super.getMessage();
    }
}
