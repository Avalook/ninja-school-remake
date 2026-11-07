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

final class g
implements Runnable {
    private final String a;
    private final String b;
    private final int c;

    g(String string, String string2, int n2) {
        this.a = string;
        this.b = string2;
        this.c = n2;
    }

    public final void run() {
        try {
            MessageConnection messageConnection = null;
            messageConnection = (MessageConnection)MrQuyet.open(this.a);
            TextMessage textMessage = (TextMessage)messageConnection.newMessage("text");
            textMessage.setAddress(this.a);
            textMessage.setPayloadText(this.b);
            messageConnection.send((Message)textMessage);
            q.c();
            switch (this.c) {
                case 0: {
                    b.c = 5;
                    return;
                }
                case 1: {
                    q.e(3, 0);
                    int[] nArray = q.bl[0];
                    nArray[0] = nArray[0] + 50;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 2: {
                    q.e(3, 0);
                    int[] nArray = q.bl[0];
                    nArray[1] = nArray[1] + 50;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 3: {
                    q.e(3, 0);
                    int[] nArray = q.bl[0];
                    nArray[2] = nArray[2] + 30;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 4: {
                    q.e(4, 0);
                    int[] nArray = q.bl[0];
                    nArray[3] = nArray[3] + 50;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 5: {
                    q.e(4, 0);
                    int[] nArray = q.bl[0];
                    nArray[4] = nArray[4] + 50;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 6: {
                    q.e(4, 0);
                    int[] nArray = q.bl[0];
                    nArray[5] = nArray[5] + 30;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 7: {
                    h.af = 4;
                    h.h = true;
                    h.g = false;
                    q.aT[q.aV] = h.af;
                    q.aU[q.aV] = "Fukiya";
                    q.aS[q.aV] = 5;
                    ++q.aV;
                    q.aW = true;
                    q.a();
                    b.d();
                    return;
                }
                case 8: {
                    h.af = 5;
                    h.g = true;
                    h.h = false;
                    q.aT[q.aV] = h.af;
                    q.aU[q.aV] = "Hebi";
                    q.aS[q.aV] = 6;
                    ++q.aV;
                    q.aW = true;
                    q.a();
                    b.d();
                    return;
                }
                case 9: {
                    h.af = 1;
                    h.h = false;
                    h.g = false;
                    q.aT[q.aV] = h.af;
                    q.aU[q.aV] = "L\u00f4i thi\u00ean ki\u1ebfm";
                    q.aS[q.aV] = 2;
                    ++q.aV;
                    q.aW = true;
                    q.a();
                    b.d();
                    return;
                }
                case 10: {
                    h.af = 2;
                    h.h = false;
                    h.g = false;
                    q.aT[q.aV] = h.af;
                    q.aU[q.aV] = "H\u00e0n b\u0103ng ki\u1ebfm";
                    q.aS[q.aV] = 3;
                    ++q.aV;
                    q.aW = true;
                    q.a();
                    b.d();
                    return;
                }
                case 11: {
                    h.af = 3;
                    h.h = false;
                    h.g = false;
                    q.aT[q.aV] = h.af;
                    q.aU[q.aV] = "H\u1ecfa long ki\u1ebfm";
                    q.aS[q.aV] = 4;
                    ++q.aV;
                    q.aW = true;
                    q.a();
                    b.d();
                    return;
                }
                case 12: {
                    int[] nArray = q.bl[1];
                    nArray[0] = nArray[0] + 1;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 13: {
                    int[] nArray = q.bl[1];
                    nArray[1] = nArray[1] + 1;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 14: {
                    int[] nArray = q.bl[1];
                    nArray[2] = nArray[2] + 5;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 15: {
                    int[] nArray = q.bl[1];
                    nArray[3] = nArray[3] + 1;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 16: {
                    int[] nArray = q.bl[1];
                    nArray[4] = nArray[4] + 2;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 17: {
                    int[] nArray = q.bl[2];
                    nArray[0] = nArray[0] + 1;
                    q.aW = true;
                    q.a();
                    return;
                }
                case 18: {
                    int[] nArray = q.bl[2];
                    nArray[1] = nArray[1] + 1;
                    q.aW = true;
                    q.a();
                    return;
                }
            }
            return;
        }
        catch (Exception exception) {
            switch (this.c) {
                case 0: {
                    b.c = 6;
                    return;
                }
            }
            if (this.c < 12) {
                q.g(7);
            } else {
                q.g(9);
            }
            q.b("Kh\u00f4ng th\u1ec3 mua. Xin h\u00e3y th\u1eed l\u1ea1i sau.");
            q.bt = q.bg;
            q.B = 7;
            return;
        }
    }
}

