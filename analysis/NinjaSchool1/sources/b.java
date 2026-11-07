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

final class b
implements Runnable {
    private final String a;
    private final String b;
    private final int c;

    b(String string, String string2, int n) {
        this.a = string;
        this.b = string2;
        this.c = n;
    }

    public final void run() {
        try {
            MessageConnection messageConnection = null;
            messageConnection = (MessageConnection)Connector.open(this.a);
            TextMessage textMessage = (TextMessage)messageConnection.newMessage("text");
            textMessage.setAddress(this.a);
            textMessage.setPayloadText(this.b);
            messageConnection.send((Message)textMessage);
            a.d();
            switch (this.c) {
                case 0: {
                    a.Y = 5;
                    return;
                }
                case 1: {
                    a.V[0] = a.V[0] + 50;
                    return;
                }
                case 2: {
                    a.V[1] = a.V[1] + 50;
                    return;
                }
                case 3: {
                    a.V[2] = a.V[2] + 30;
                    return;
                }
                case 4: {
                    a.V[3] = a.V[3] + 50;
                    return;
                }
                case 5: {
                    a.V[4] = a.V[4] + 30;
                    return;
                }
                case 6: {
                    a.V[5] = a.V[5] + 50;
                    return;
                }
                case 7: {
                    a.x = 1;
                    a.a();
                    a.t();
                    return;
                }
                case 8: {
                    a.x = 2;
                    a.a();
                    a.t();
                    return;
                }
                case 9: {
                    a.x = 3;
                    a.a();
                    a.t();
                    return;
                }
            }
            return;
        }
        catch (Exception exception) {
            switch (this.c) {
                case 0: {
                    a.Y = 6;
                    return;
                }
            }
            a.s(7);
            a.a("Kh\u00f4ng th\u1ec3 mua. Xin h\u00e3y th\u1eed l\u1ea1i sau.");
            a.X = a.U;
            a.d = 7;
            return;
        }
    }
}

