package javax.microedition.lcdui;
public class TextBox extends Screen { String s; public TextBox(String t,String s,int m,int c){this.s=s==null?"":s;} public String getString(){return s;} public void setString(String x){s=x;} }
