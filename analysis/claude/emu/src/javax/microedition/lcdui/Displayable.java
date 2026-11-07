package javax.microedition.lcdui;
public abstract class Displayable {
  public int getWidth(){ return harness.Emu.W; } public int getHeight(){ return harness.Emu.H; }
  public void addCommand(Command c){} public void removeCommand(Command c){}
  public void setCommandListener(CommandListener l){ harness.Emu.cmdListener=l; }
}
