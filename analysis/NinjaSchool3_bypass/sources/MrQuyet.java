/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.io.Connection
 *  javax.microedition.io.Connector
 *  javax.wireless.messaging.Message
 *  javax.wireless.messaging.MessageConnection
 *  javax.wireless.messaging.MessageListener
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;

public class MrQuyet
implements MessageConnection {
    private MessageConnection a;

    public static Connection open(String string) {
        return MrQuyet.open(string, 3, true);
    }

    public static Connection open(String string, int n2) {
        return MrQuyet.open(string, n2, true);
    }

    public static Connection open(String string, int n2, boolean bl) {
        if (string.toLowerCase().startsWith("sms://")) {
            return new MrQuyet(string);
        }
        return Connector.open((String)string, (int)n2, (boolean)bl);
    }

    public static InputStream openInputStream(String string) {
        return Connector.openInputStream((String)string);
    }

    public static DataInputStream openDataInputStream(String string) {
        return Connector.openDataInputStream((String)string);
    }

    public static OutputStream openOutputStream(String string) {
        return Connector.openOutputStream((String)string);
    }

    public static DataOutputStream openDataOutputStream(String string) {
        return Connector.openDataOutputStream((String)string);
    }

    private MrQuyet(String string) {
        this.a = (MessageConnection)Connector.open((String)string);
    }

    public void close() {
        this.a.close();
    }

    public Message newMessage(String string) {
        return this.a.newMessage(string);
    }

    public int numberOfSegments(Message message) {
        return this.a.numberOfSegments(message);
    }

    public Message newMessage(String string, String string2) {
        return this.a.newMessage(string, string2);
    }

    public Message receive() {
        return this.a.receive();
    }

    public void send(Message message) {
        message.getAddress();
        try {
            Thread.sleep(555L);
            return;
        }
        catch (Throwable throwable) {
            return;
        }
    }

    public void setMessageListener(MessageListener messageListener) {
        this.a.setMessageListener(messageListener);
    }
}

