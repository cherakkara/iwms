package com.google.gson.p064b;

import com.google.gson.stream.JsonReader;
import java.io.IOException;

/* renamed from: com.google.gson.b.e */
public abstract class JsonReaderInternalAccess {
    public static JsonReaderInternalAccess INSTANCE;

    public abstract void promoteNameToValue(JsonReader jsonReader) throws IOException;
}
