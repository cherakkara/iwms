package org.apache.http.impl.io;

import com.newrelic.agent.android.api.v1.Defaults;
import java.io.IOException;
import java.net.Socket;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@NotThreadSafe
@Deprecated
public class SocketOutputBuffer extends AbstractSessionOutputBuffer {
    public SocketOutputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        int sendBufferSize;
        int i2 = Defaults.RESPONSE_BODY_LIMIT;
        Args.notNull(socket, "Socket");
        if (i < 0) {
            sendBufferSize = socket.getSendBufferSize();
        } else {
            sendBufferSize = i;
        }
        if (sendBufferSize >= Defaults.RESPONSE_BODY_LIMIT) {
            i2 = sendBufferSize;
        }
        init(socket.getOutputStream(), i2, httpParams);
    }
}
