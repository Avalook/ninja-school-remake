package javax.wireless.messaging; public interface MessageConnection extends javax.microedition.io.Connection { Message newMessage(String t); void send(Message m) throws java.io.IOException; }
