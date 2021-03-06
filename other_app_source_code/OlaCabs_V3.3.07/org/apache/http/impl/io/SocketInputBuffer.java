package org.apache.http.impl.io;

import com.newrelic.agent.android.api.v1.Defaults;
import java.io.IOException;
import java.net.Socket;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.io.EofSensor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@NotThreadSafe
@Deprecated
public class SocketInputBuffer extends AbstractSessionInputBuffer implements EofSensor {
    private boolean eof;
    private final Socket socket;

    public SocketInputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        int receiveBufferSize;
        int i2 = Defaults.RESPONSE_BODY_LIMIT;
        Args.notNull(socket, "Socket");
        this.socket = socket;
        this.eof = false;
        if (i < 0) {
            receiveBufferSize = socket.getReceiveBufferSize();
        } else {
            receiveBufferSize = i;
        }
        if (receiveBufferSize >= Defaults.RESPONSE_BODY_LIMIT) {
            i2 = receiveBufferSize;
        }
        init(socket.getInputStream(), i2, httpParams);
    }

    protected int fillBuffer() throws IOException {
        int fillBuffer = super.fillBuffer();
        this.eof = fillBuffer == -1;
        return fillBuffer;
    }

    public boolean isDataAvailable(int i) throws IOException {
        boolean hasBufferedData = hasBufferedData();
        if (!hasBufferedData) {
            int soTimeout = this.socket.getSoTimeout();
            try {
                this.socket.setSoTimeout(i);
                fillBuffer();
                hasBufferedData = hasBufferedData();
            } finally {
                this.socket.setSoTimeout(soTimeout);
            }
        }
        return hasBufferedData;
    }

    public boolean isEof() {
        return this.eof;
    }
}
