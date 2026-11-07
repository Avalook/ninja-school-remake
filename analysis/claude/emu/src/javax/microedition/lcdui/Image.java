package javax.microedition.lcdui;
import java.awt.image.BufferedImage;
public class Image { public BufferedImage img; public String name;
  Image(BufferedImage b,String n){img=b;name=n;}
  public static Image createImage(String path) throws java.io.IOException { java.io.InputStream in=Image.class.getResourceAsStream(path); if(in==null) throw new java.io.IOException("missing "+path); BufferedImage b=javax.imageio.ImageIO.read(in); BufferedImage c=new BufferedImage(b.getWidth(),b.getHeight(),BufferedImage.TYPE_INT_ARGB); c.getGraphics().drawImage(b,0,0,null); harness.Emu.loaded(path); return new Image(c,path);}
  public static Image createImage(int w,int h){ BufferedImage c=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB); java.awt.Graphics g=c.getGraphics(); g.setColor(java.awt.Color.WHITE); g.fillRect(0,0,w,h); return new Image(c,"mutable");}
  public static Image createImage(byte[] d,int o,int l){ try{ return new Image(javax.imageio.ImageIO.read(new java.io.ByteArrayInputStream(d,o,l)),"bytes"); }catch(Exception e){throw new RuntimeException(e);} }
  public static Image createImage(Image s,int x,int y,int w,int h,int t){ return new Image(s.img.getSubimage(x,y,w,h),s.name+"#sub"); }
  public int getWidth(){return img.getWidth();} public int getHeight(){return img.getHeight();}
  public Graphics getGraphics(){ return new Graphics(img); }
  public boolean isMutable(){return name.equals("mutable");}
}
