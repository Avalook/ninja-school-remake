package harness;
import java.awt.image.BufferedImage; import java.io.*; import java.lang.reflect.*; import java.util.*;
public class Emu {
  public static int W=240,H=320; public static javax.microedition.lcdui.Displayable current; public static javax.microedition.lcdui.CommandListener cmdListener;
  public static volatile boolean repaintReq, destroyed; public static String rmsDir="rms"; public static String outDir="out";
  static BufferedImage fb=new BufferedImage(W,H,BufferedImage.TYPE_INT_RGB); static final Object lock=new Object();
  static PrintWriter drawLog; static boolean logDraws=false; static Set<String> loadedImgs=new TreeSet<>(); static List<String> texts=new ArrayList<>();
  public static void loaded(String p){ synchronized(loadedImgs){loadedImgs.add(p);} }
  public static void draw(String n,int x,int y,int an,int t,int sx,int sy,int w,int h){ if(logDraws&&drawLog!=null) drawLog.println("img "+n+" "+x+" "+y+" a"+an+" t"+t+(sx>=0?(" r"+sx+","+sy+","+w+","+h):"")); }
  public static void text(String s,int x,int y){ if(logDraws&&drawLog!=null) drawLog.println("txt "+x+" "+y+" "+s); synchronized(texts){ texts.add(s); if(texts.size()>4000) texts.subList(0,2000).clear(); } }
  public static void music(){}
  public static void service(javax.microedition.lcdui.Canvas c){ synchronized(lock){ javax.microedition.lcdui.Graphics g=new javax.microedition.lcdui.Graphics(fb); g.setClip(0,0,W,H); try{ c._paint(g);}catch(Throwable t){ System.err.println("[emu] paint err "+t); } repaintReq=false; } }
  static Class<?> A;
  public static void main(String[] args) throws Exception {
    String script=args[0]; outDir=args.length>1?args[1]:"out"; new File(outDir).mkdirs(); rmsDir=outDir+"/rms";
    A=Class.forName("a"); Class<?> M=Class.forName("Midlet"); Object mid=M.getDeclaredConstructor().newInstance(); A=Class.forName("a");
    Method st=M.getDeclaredMethod("startApp"); st.setAccessible(true); st.invoke(mid);
    // background painter in case game doesn't call serviceRepaints
    Thread p=new Thread(()->{ while(!destroyed){ try{Thread.sleep(40);}catch(Exception e){} if(repaintReq && current instanceof javax.microedition.lcdui.Canvas) service((javax.microedition.lcdui.Canvas)current);} }); p.setDaemon(true); p.start();
    if(R.lockstep){ R.done.acquireUninterruptibly(); if(System.getProperty("trace")!=null) R.trace=new PrintWriter(new FileWriter(new File(outDir,"trace.txt"))); }
    BufferedReader br=new BufferedReader(new FileReader(script)); String line; int shot=0;
    while((line=br.readLine())!=null){ line=line.trim(); if(line.isEmpty()||line.startsWith("#")) continue; String[] t=line.split("\\s+");
      switch(t[0]){
        case "wait": if(R.lockstep) R.step((int)(Long.parseLong(t[1])/40)); else Thread.sleep(Long.parseLong(t[1])); break;
        case "press": key(Integer.parseInt(t[1]),true); break;
        case "release": key(Integer.parseInt(t[1]),false); break;
        case "tap": { int n=t.length>2?Integer.parseInt(t[2]):1; for(int i=0;i<n;i++){ key(Integer.parseInt(t[1]),true); pause(80); key(Integer.parseInt(t[1]),false); pause(120);} break; }
        case "hold": key(Integer.parseInt(t[1]),true); pause(Long.parseLong(t[2])); key(Integer.parseInt(t[1]),false); break;
        case "shot": synchronized(lock){ BufferedImage s=new BufferedImage(W*2,H*2,1); s.getGraphics().drawImage(fb,0,0,W*2,H*2,null); javax.imageio.ImageIO.write(s,"png",new File(outDir,t[1]+".png")); } break;
        case "dump": dump(new File(outDir,t[1]+".json")); break;
        case "vars": { StringBuilder sb=new StringBuilder(); for(int i=1;i<t.length;i++){ sb.append(t[i]).append('=').append(val(t[i])).append(' ');} System.out.println("[vars] "+sb); break; }
        case "set": setv(t[1],t[2]); break;
        case "texts": synchronized(texts){ LinkedHashSet<String> u=new LinkedHashSet<>(texts); PrintWriter w=new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(outDir,t[1]+".txt"),true),"UTF-8")); for(String s:u) w.println(s); w.close(); texts.clear(); } break;
        case "drawlog": if(t[1].equals("on")){ drawLog=new PrintWriter(new FileWriter(new File(outDir,t[2]+".log"))); logDraws=true;} else { logDraws=false; drawLog.close(); } break;
        case "call": { Method m=A.getDeclaredMethod(t[1], intArgs(t.length-2)); m.setAccessible(true); Object[] a=new Object[t.length-2]; for(int i=2;i<t.length;i++) a[i-2]=Integer.parseInt(t[i]); synchronized(lock){ m.invoke(null,a);} break; }
        case "images": { PrintWriter w=new PrintWriter(new File(outDir,"loaded-images.txt")); synchronized(loadedImgs){ for(String s:loadedImgs) w.println(s);} w.close(); break; }
        case "quit": System.exit(0);
      }
    }
    System.exit(0);
  }
  static void pause(long ms) throws Exception { if(R.lockstep) R.step((int)(ms/40)); else Thread.sleep(ms); }
  static String[] TF={"d","v","A","B","fh","fi","fR","fS","fV","fX","gc"};
  public static String traceLine(int f){ StringBuilder b=new StringBuilder(); b.append(f); for(String n:TF){ b.append(' ').append(n).append('=').append(val(n)); } return b.toString(); }
  static Class<?>[] intArgs(int n){ Class<?>[] c=new Class<?>[n]; Arrays.fill(c,int.class); return c; }
  static void key(int k,boolean d){ if(current instanceof javax.microedition.lcdui.Canvas) ((javax.microedition.lcdui.Canvas)current)._key(k,d); }
  public static Object val(String n){ try{ Field f=A.getDeclaredField(n); f.setAccessible(true); Object o=f.get(null); return ser(o,0); }catch(Exception e){ return "ERR"; } }
  static void setv(String n,String v) throws Exception { Field f=A.getDeclaredField(n); f.setAccessible(true); if(f.getType()==int.class) f.setInt(null,Integer.parseInt(v)); else if(f.getType()==boolean.class) f.setBoolean(null,Boolean.parseBoolean(v)); }
  static String esc(String s){ StringBuilder b=new StringBuilder("\""); for(char c:s.toCharArray()){ if(c=='"'||c=='\\') b.append('\\').append(c); else if(c<32) b.append(String.format("\\u%04x",(int)c)); else b.append(c);} return b.append('"').toString(); }
  static String ser(Object o,int d){ if(o==null) return "null"; if(o instanceof String) return esc((String)o); if(o instanceof Number||o instanceof Boolean) return o.toString(); if(o instanceof Character) return esc(o.toString());
    if(o instanceof javax.microedition.lcdui.Image) return esc("IMG:"+((javax.microedition.lcdui.Image)o).name);
    if(o.getClass().isArray()){ int n=Array.getLength(o); StringBuilder b=new StringBuilder("["); for(int i=0;i<n;i++){ if(i>0)b.append(','); b.append(ser(Array.get(o,i),d+1)); } return b.append(']').toString(); }
    return esc("OBJ:"+o.getClass().getName()); }
  static void dump(File f) throws Exception { PrintWriter w=new PrintWriter(new OutputStreamWriter(new FileOutputStream(f),"UTF-8")); w.println("{"); boolean first=true;
    for(Field fl:A.getDeclaredFields()){ if(!Modifier.isStatic(fl.getModifiers())) continue; fl.setAccessible(true); Object o; try{o=fl.get(null);}catch(Exception e){continue;}
      if(!first) w.println(","); first=false; w.print(esc(fl.getName()+":"+fl.getType().getSimpleName())+":"+ser(o,0)); }
    w.println("}"); w.close(); }
}
