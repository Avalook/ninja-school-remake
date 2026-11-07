package javax.microedition.midlet;
public abstract class MIDlet {
  protected abstract void startApp() throws MIDletStateChangeException;
  protected abstract void pauseApp();
  protected abstract void destroyApp(boolean b) throws MIDletStateChangeException;
  public final void notifyDestroyed(){ System.err.println("[emu] notifyDestroyed"); harness.Emu.destroyed=true; }
  public final boolean platformRequest(String s){ System.err.println("[emu] platformRequest "+s); return false; }
  public final String getAppProperty(String k){ return null; }
}
