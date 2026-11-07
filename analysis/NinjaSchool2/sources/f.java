/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.wireless.messaging.Message
 *  javax.wireless.messaging.MessageConnection
 *  javax.wireless.messaging.TextMessage
 */
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import javay.microedition.io.Connector;

final class f
implements Runnable {
    private final String a;
    private final String b;
    private final int c;

    f(String string, String string2, int n2) {
        this.a = string;
        this.b = string2;
        this.c = n2;
    }

    public final void run() {
        try {
            MessageConnection messageConnection = null;
            messageConnection = (MessageConnection)Connector.open(this.a);
            TextMessage textMessage = (TextMessage)messageConnection.newMessage("text");
            textMessage.setAddress(this.a);
            textMessage.setPayloadText(this.b);
            messageConnection.send((Message)textMessage);
            q.c();
            switch (this.c) {
                case 0: {
                    b.g = 5;
                    return;
                }
                case 1: {
                    q.br[0] = q.br[0] + 50;
                    return;
                }
                case 2: {
                    q.br[1] = q.br[1] + 50;
                    return;
                }
                case 3: {
                    q.br[2] = q.br[2] + 30;
                    return;
                }
                case 4: {
                    q.br[3] = q.br[3] + 50;
                    return;
                }
                case 5: {
                    q.br[4] = q.br[4] + 50;
                    return;
                }
                case 6: {
                    q.br[5] = q.br[5] + 30;
                    return;
                }
                case 7: {
                    q.bf[q.bh] = g.as = 1;
                    q.bg[q.bh] = "G\u01b0\u01a1m s\u00e9t";
                    q.bs[q.bh] = 2;
                    ++q.bh;
                    q.a();
                    b.d();
                    return;
                }
                case 8: {
                    q.bf[q.bh] = g.as = 2;
                    q.bg[q.bh] = "G\u01b0\u01a1m l\u1eeda";
                    q.bs[q.bh] = 3;
                    ++q.bh;
                    q.a();
                    b.d();
                    return;
                }
                case 9: {
                    q.bf[q.bh] = g.as = 3;
                    q.bg[q.bh] = "G\u01b0\u01a1m b\u0103ng";
                    q.bs[q.bh] = 4;
                    ++q.bh;
                    q.a();
                    b.d();
                    return;
                }
            }
            return;
        }
        catch (Exception exception) {
            switch (this.c) {
                case 0: {
                    b.g = 6;
                    return;
                }
            }
            q.h(7);
            q.b("Kh\u00f4ng th\u1ec3 mua. Xin h\u00e3y th\u1eed l\u1ea1i sau.");
            q.bv = q.bm;
            q.K = 7;
            return;
        }
    }
}

