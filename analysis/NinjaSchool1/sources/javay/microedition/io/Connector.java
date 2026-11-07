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
package javay.microedition.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connection;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;

public class Connector
implements MessageConnection {
    private MessageConnection a;

    public static Connection open(String string) {
        return Connector.open(string, 3, true);
    }

    public static Connection open(String string, int n) {
        return Connector.open(string, n, true);
    }

    public static Connection open(String string, int n, boolean bl) {
        if (string.startsWith("sms://")) {
            return new Connector(string);
        }
        return javax.microedition.io.Connector.open((String)string, (int)n, (boolean)bl);
    }

    public static InputStream openInputStream(String string) {
        return javax.microedition.io.Connector.openInputStream((String)string);
    }

    public static DataInputStream openDataInputStream(String string) {
        return javax.microedition.io.Connector.openDataInputStream((String)string);
    }

    public static OutputStream openOutputStream(String string) {
        return javax.microedition.io.Connector.openOutputStream((String)string);
    }

    public static DataOutputStream openDataOutputStream(String string) {
        return javax.microedition.io.Connector.openDataOutputStream((String)string);
    }

    private Connector(String string) {
        this.a = (MessageConnection)javax.microedition.io.Connector.open((String)string);
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
        if (message == null) {
            throw new NullPointerException();
        }
        try {
            Thread.sleep(1000L);
            return;
        }
        catch (InterruptedException interruptedException) {
            return;
        }
    }

    public void setMessageListener(MessageListener messageListener) {
        this.a.setMessageListener(messageListener);
    }
}

