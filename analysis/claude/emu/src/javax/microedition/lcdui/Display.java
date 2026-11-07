package javax.microedition.lcdui;
public class Display { static Display d=new Display(); public static Display getDisplay(javax.microedition.midlet.MIDlet m){return d;}
  public void setCurrent(Displayable x){ harness.Emu.current=x; System.err.println("[emu] setCurrent "+x.getClass().getName()); } public Displayable getCurrent(){return harness.Emu.current;} }
