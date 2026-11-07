package javax.microedition.lcdui;
public class Font { public static final int FACE_SYSTEM=0,FACE_MONOSPACE=32,FACE_PROPORTIONAL=64,STYLE_PLAIN=0,STYLE_BOLD=1,STYLE_ITALIC=2,STYLE_UNDERLINED=4,SIZE_SMALL=8,SIZE_MEDIUM=0,SIZE_LARGE=16;
  public java.awt.Font f; static java.awt.image.BufferedImage bi=new java.awt.image.BufferedImage(1,1,2); java.awt.FontMetrics fm;
  Font(int face,int style,int size){ int px= size==8?11:(size==16?16:13); f=new java.awt.Font("Arial",(style&1)!=0?java.awt.Font.BOLD:java.awt.Font.PLAIN,px); fm=bi.getGraphics().getFontMetrics(f);}
  public static Font getFont(int a,int b,int c){ return new Font(a,b,c);} public static Font getDefaultFont(){return new Font(0,0,0);}
  public int getHeight(){return fm.getHeight();} public int stringWidth(String s){return fm.stringWidth(s);} public int charWidth(char c){return fm.charWidth(c);} public int getBaselinePosition(){return fm.getAscent();}
  public int substringWidth(String s,int o,int l){return fm.stringWidth(s.substring(o,o+l));}
}
