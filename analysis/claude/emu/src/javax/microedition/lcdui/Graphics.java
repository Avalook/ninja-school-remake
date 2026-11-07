package javax.microedition.lcdui;
import java.awt.*; import java.awt.geom.AffineTransform; import java.awt.image.BufferedImage;
public class Graphics { public static final int HCENTER=1,VCENTER=2,LEFT=4,RIGHT=8,TOP=16,BOTTOM=32,BASELINE=64,SOLID=0,DOTTED=1;
  Graphics2D g; int tx,ty; Font font=Font.getDefaultFont(); int color; BufferedImage target;
  public Graphics(BufferedImage b){ target=b; g=b.createGraphics(); g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);}
  public void translate(int x,int y){tx+=x;ty+=y;g.translate(x,y);} public int getTranslateX(){return tx;} public int getTranslateY(){return ty;}
  public void setColor(int c){color=c; g.setColor(new Color(c&0xffffff));} public void setColor(int r,int gg,int b){setColor((r<<16)|(gg<<8)|b);} public int getColor(){return color;}
  public void setFont(Font f){font=f; g.setFont(f.f);} public Font getFont(){return font;}
  public void setClip(int x,int y,int w,int h){ g.setClip(x,y,w,h);} public void clipRect(int x,int y,int w,int h){g.clipRect(x,y,w,h);}
  public int getClipX(){Rectangle r=g.getClipBounds();return r==null?0:r.x;} public int getClipY(){Rectangle r=g.getClipBounds();return r==null?0:r.y;}
  public int getClipWidth(){Rectangle r=g.getClipBounds();return r==null?harness.Emu.W:r.width;} public int getClipHeight(){Rectangle r=g.getClipBounds();return r==null?harness.Emu.H:r.height;}
  public void fillRect(int x,int y,int w,int h){g.fillRect(x,y,w,h);} public void drawRect(int x,int y,int w,int h){g.drawRect(x,y,w,h);}
  public void drawLine(int a,int b,int c,int d){g.drawLine(a,b,c,d);}
  public void fillRoundRect(int x,int y,int w,int h,int a,int b){g.fillRoundRect(x,y,w,h,a,b);} public void drawRoundRect(int x,int y,int w,int h,int a,int b){g.drawRoundRect(x,y,w,h,a,b);}
  public void fillArc(int x,int y,int w,int h,int s,int a){g.fillArc(x,y,w,h,s,a);} public void drawArc(int x,int y,int w,int h,int s,int a){g.drawArc(x,y,w,h,s,a);}
  public void fillTriangle(int a,int b,int c,int d,int e,int f){g.fillPolygon(new int[]{a,c,e},new int[]{b,d,f},3);}
  int ax(int x,int w,int an){ if((an&HCENTER)!=0) return x-w/2; if((an&RIGHT)!=0) return x-w; return x;}
  int ay(int y,int h,int an){ if((an&VCENTER)!=0) return y-h/2; if((an&BOTTOM)!=0) return y-h; return y;}
  public void drawImage(Image i,int x,int y,int an){ if(i==null)return; harness.Emu.draw(i.name,x+tx,y+ty,an,0,-1,-1,-1,-1); g.drawImage(i.img,ax(x,i.getWidth(),an),ay(y,i.getHeight(),an),null);}
  public void drawString(String s,int x,int y,int an){ harness.Emu.text(s,x+tx,y+ty); FontMetrics fm=g.getFontMetrics(); int w=fm.stringWidth(s); int xx=ax(x,w,an); int yy; if((an&BOTTOM)!=0) yy=y-fm.getDescent(); else if((an&BASELINE)!=0) yy=y; else yy=y+fm.getAscent(); g.drawString(s,xx,yy);}
  public void drawSubstring(String s,int o,int l,int x,int y,int an){drawString(s.substring(o,o+l),x,y,an);} public void drawChar(char c,int x,int y,int an){drawString(""+c,x,y,an);}
  public void drawRegion(Image src,int sx,int sy,int w,int h,int t,int x,int y,int an){ if(src==null)return; harness.Emu.draw(src.name,x+tx,y+ty,an,t,sx,sy,w,h);
    BufferedImage sub=src.img.getSubimage(sx,sy,w,h); int dw=(t>=4)?h:w, dh=(t>=4)?w:h; AffineTransform at=new AffineTransform();
    switch(t){case 0:break; case 2: at.translate(w,0);at.scale(-1,1);break; case 1: at.translate(0,h);at.scale(1,-1);break; case 3: at.translate(w,h);at.rotate(Math.PI);break;
      case 5: at.translate(h,0);at.rotate(Math.PI/2);break; case 6: at.translate(0,w);at.rotate(-Math.PI/2);break; case 7: at.translate(h,w);at.rotate(Math.PI/2);at.scale(1,-1);at.translate(0,0);break; case 4: at.rotate(Math.PI/2);at.scale(1,-1);break;}
    Graphics2D g2=(Graphics2D)g.create(); g2.translate(ax(x,dw,an),ay(y,dh,an)); g2.drawImage(sub,at,null); g2.dispose(); }
  public void drawRGB(int[] d,int o,int sl,int x,int y,int w,int h,boolean a){ BufferedImage b=new BufferedImage(w,h,2); b.setRGB(0,0,w,h,d,o,sl); g.drawImage(b,x,y,null);}
}
