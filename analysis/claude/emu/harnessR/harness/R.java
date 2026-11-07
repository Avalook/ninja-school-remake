package harness;
import java.util.concurrent.Semaphore;
public class R {
  public static boolean lockstep=Boolean.getBoolean("lockstep");
  static long vt=1000000; static Semaphore go=new Semaphore(0), done=new Semaphore(0); public static int frames=0;
  public static java.io.PrintWriter trace;
  public static java.io.InputStream g(Class<?> c,String n){ java.io.InputStream i=R.class.getResourceAsStream(n); if(i==null) System.err.println("[emu] missing res "+n); return i; }
  public static long now(){ return lockstep? vt : System.currentTimeMillis(); }
  public static void repaint(javax.microedition.lcdui.Canvas c){
    if(!lockstep){ c.repaint(); return; }
    Emu.service(c); frames++; vt+=40;
    try{ if("2".equals(String.valueOf(Emu.val("d")))) Thread.sleep(30); }catch(Exception e){}
    if(trace!=null) trace.println(Emu.traceLine(frames));
    done.release(); go.acquireUninterruptibly();
  }
  public static void step(int n){ for(int i=0;i<n;i++){ go.release(); done.acquireUninterruptibly(); } }
}
