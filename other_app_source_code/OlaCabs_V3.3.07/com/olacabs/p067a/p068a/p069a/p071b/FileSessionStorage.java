package com.olacabs.p067a.p068a.p069a.p071b;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.olacabs.p067a.p068a.p069a.p071b.p072a.CriteriaStrategy;
import com.olacabs.p067a.p068a.p069a.p074d.MegatronCore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.olacabs.a.a.a.b.a */
public class FileSessionStorage {
    public CriteriaStrategy f8906a;
    private Context f8907b;

    /* renamed from: com.olacabs.a.a.a.b.a.a */
    class FileSessionStorage implements CriteriaStrategy {
        final /* synthetic */ FileSessionStorage f8905a;

        FileSessionStorage(FileSessionStorage fileSessionStorage) {
            this.f8905a = fileSessionStorage;
        }

        public boolean m12809a() {
            if (this.f8905a.m12813e() == null) {
                return false;
            }
            if (System.currentTimeMillis() - Long.parseLong(this.f8905a.m12813e().getName()) > 300000 || this.f8905a.m12814a() > 15) {
                return true;
            }
            return false;
        }
    }

    public FileSessionStorage(Context context) {
        this.f8906a = new FileSessionStorage(this);
        this.f8907b = context;
    }

    public void m12816a(String str) {
        IOException e;
        Throwable th;
        File file = new File(this.f8907b.getCacheDir(), "megatron");
        FileOutputStream fileOutputStream = null;
        try {
            if (file.exists()) {
                m12815a(file);
            } else {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, String.valueOf(System.currentTimeMillis())));
            try {
                fileOutputStream2.write(str.getBytes());
                MegatronCore.m12842e().m12856f();
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                fileOutputStream = fileOutputStream2;
                try {
                    e2.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (IOException e5) {
            e22 = e5;
            e22.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    public int m12814a() {
        if (m12817b() == null) {
            return 0;
        }
        return m12817b().length;
    }

    public File[] m12817b() {
        File file = new File(this.f8907b.getCacheDir(), "megatron");
        if (file != null) {
            return file.listFiles();
        }
        return null;
    }

    public String m12818c() {
        int i = 0;
        int a = m12814a();
        if (a > 0) {
            JSONArray[] jSONArrayArr = new JSONArray[a];
            File[] b = m12817b();
            if (b != null) {
                try {
                    int length = b.length;
                    a = 0;
                    while (i < length) {
                        int i2 = a + 1;
                        jSONArrayArr[a] = JSONArrayInstrumentation.init(m12812b(b[i]));
                        i++;
                        a = i2;
                    }
                    return m12811a(jSONArrayArr);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    private String m12812b(File file) {
        FileInputStream fileInputStream;
        String str;
        IOException e;
        Throwable th;
        String str2 = null;
        try {
            fileInputStream = new FileInputStream(file.getAbsolutePath());
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                }
                fileInputStream.close();
                str2 = stringBuilder.toString();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                        str = str2;
                    } catch (IOException e2) {
                        str = str2;
                    }
                } else {
                    str = str2;
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileInputStream == null) {
                        str = str2;
                    } else {
                        try {
                            fileInputStream.close();
                            str = str2;
                        } catch (IOException e4) {
                            str = str2;
                        }
                    }
                    if (str != null) {
                    }
                    return "[]";
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e6) {
            e = e6;
            Object obj = str2;
            e.printStackTrace();
            if (fileInputStream == null) {
                fileInputStream.close();
                str = str2;
            } else {
                str = str2;
            }
            if (str != null) {
            }
            return "[]";
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = str2;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        if (str != null || str.isEmpty()) {
            return "[]";
        }
        return str;
    }

    private String m12811a(JSONArray... jSONArrayArr) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (JSONArray jSONArray2 : jSONArrayArr) {
            for (int i = 0; i < jSONArray2.length(); i++) {
                jSONArray.put(jSONArray2.get(i));
            }
        }
        return !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray);
    }

    private File m12813e() {
        File[] b = m12817b();
        if (b == null || b.length == 0) {
            return null;
        }
        File file = b[0];
        for (int i = 1; i < b.length; i++) {
            if (file.lastModified() > b[i].lastModified()) {
                file = b[i];
            }
        }
        return file;
    }

    public void m12819d() {
        File[] b = m12817b();
        if (b != null && b.length != 0) {
            for (File delete : b) {
                delete.delete();
            }
        }
    }

    public void m12815a(File file) {
        long j = 0;
        if (file != null && file.exists()) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    j += file2.length();
                }
            }
            if (2097152 <= j) {
                for (File file3 : file.listFiles()) {
                    if (file3.isFile()) {
                        file3.delete();
                    }
                }
            }
        }
    }
}
