/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.bluetooth.LocalDevice
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.lcdui.TextBox
 *  javax.microedition.midlet.MIDlet
 *  javax.microedition.rms.RecordStore
 */
import java.io.IOException;
import java.io.InputStream;
import javax.bluetooth.LocalDevice;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public final class a
extends Canvas
implements Runnable,
CommandListener {
    private static String Z = "TEAM";
    public static boolean a = true;
    private static int aa = 40;
    private static String[] ab = new String[]{"x1xx", "x2xx", "x3xx", "x4xx", "x5xx", "x6xx", "x7xx"};
    private static int[] ac = new int[]{150, 180, 180, 300, 350, 380, 500};
    private static int[] ad = new int[]{20, 30, 40, 100, 80, 120, 200};
    private static int[] ae = new int[]{150, 140, 120, 240, 280, 290, 300};
    private static int[] af = new int[]{800, 1000, 1200, 3000, 3500, 4000, 6000};
    private static int[][] ag;
    private static int[][] ah;
    private static int[] ai;
    private static int[] aj;
    private static int[] ak;
    private static int[] al;
    private static String[] am;
    private static int[] an;
    private static int[] ao;
    private static int[] ap;
    private static int[] aq;
    private static int[][][] ar;
    private static int[][][] as;
    private static int[] at;
    private static int[] au;
    private static int[][] av;
    private static int[][] aw;
    private static int[][] ax;
    private static int[][] ay;
    private static int[][] az;
    private static int[][] aA;
    private static int[][] aB;
    private static int[][] aC;
    private static int[] aD;
    private static int[] aE;
    private static String[] aF;
    private static String[][] aG;
    private static String[] aH;
    private static String[][] aI;
    private static int[][][] aJ;
    private static int[] aK;
    private static int[] aL;
    private static int aM;
    private static a aN;
    private static boolean aO;
    private static boolean aP;
    private static int aQ;
    private static int aR;
    private static boolean aS;
    private static boolean aT;
    private static boolean[] aU;
    private static boolean[] aV;
    private static int aW;
    private static int aX;
    private static int aY;
    private static int aZ;
    private static int ba;
    public static boolean b;
    public static boolean c;
    private static int bb;
    private static int bc;
    private static int bd;
    private static int be;
    private static int bf;
    private static int bg;
    public static int d;
    private static boolean bh;
    public static int[] e;
    public static int[] f;
    public static int g;
    public static int h;
    public static int i;
    public static int j;
    public static int k;
    public static int l;
    public static int m;
    public static String n;
    public static String o;
    private static int bi;
    private static int bj;
    private static int bk;
    private static int bl;
    private static int bm;
    private static int bn;
    private static int bo;
    private static int bp;
    private static int bq;
    private static int br;
    private static int bs;
    private static int bt;
    private static int bu;
    private static int bv;
    private static int bw;
    private static int bx;
    private static String by;
    private static int bz;
    private static int bA;
    private static int bB;
    private static int bC;
    private static int bD;
    private static int bE;
    public static int p;
    public static int q;
    public static int r;
    private static int bF;
    private static int bG;
    private static int bH;
    private static int bI;
    private static int bJ;
    private static int bK;
    private static int bL;
    private static int bM;
    private static int bN;
    private static int bO;
    private static int bP;
    private static int[] bQ;
    private static int[] bR;
    private static int[] bS;
    private static int[] bT;
    private static int[] bU;
    private static int[] bV;
    private static int bW;
    private static int[] bX;
    private static int[] bY;
    private static int[] bZ;
    private static int[] ca;
    private static int[] cb;
    private static int[] cc;
    private static int[] cd;
    private static int[] ce;
    private static int[] cf;
    private static int[] cg;
    private static int[] ch;
    private static int[] ci;
    private static int[] cj;
    private static int[] ck;
    private static int[] cl;
    private static int[] cm;
    private static int[] cn;
    private static int[] co;
    private static boolean cp;
    private static int cq;
    private static String cr;
    private static int cs;
    private static int[] ct;
    private static int[] cu;
    private static int[] cv;
    private static int[] cw;
    private static int[] cx;
    private static int[] cy;
    private static int[] cz;
    private static int[] cA;
    private static int[] cB;
    private static int[] cC;
    private static int[] cD;
    private static int[] cE;
    private static int[] cF;
    private static int[] cG;
    private static int[] cH;
    private static int[] cI;
    private static int[] cJ;
    private static int[] cK;
    private static int[] cL;
    private static int[] cM;
    private static int[] cN;
    private static int[] cO;
    private static int cP;
    private static int[] cQ;
    private static int[] cR;
    private static int[] cS;
    private static int[] cT;
    private static int[] cU;
    private static int[] cV;
    private static int[] cW;
    private static int[] cX;
    private static int cY;
    private static int[] cZ;
    private static int[] da;
    private static int[] db;
    private static int[] dc;
    private static int[] dd;
    private static int[] de;
    private static boolean[] df;
    private static int dg;
    private static int dh;
    private static int[] di;
    private static int[] dj;
    private static int[] dk;
    private static int[] dl;
    private static int[] dm;
    private static int[] dn;
    private static int[] do;
    private static int[] dp;
    private static boolean dq;
    private static int dr;
    private static int ds;
    private static int dt;
    private static int du;
    private static int dv;
    private static boolean[] dw;
    private static int dx;
    private static String[][] dy;
    private static boolean dz;
    private static boolean dA;
    private static int dB;
    private static int[] dC;
    private static int[] dD;
    private static int[] dE;
    private static int[] dF;
    private static int[] dG;
    private static int[] dH;
    private static int[] dI;
    private static int[] dJ;
    private static int[] dK;
    private static int[] dL;
    private static int[] dM;
    private static int[] dN;
    private static int[] dO;
    private static int[] dP;
    private static int[] dQ;
    private static String[] dR;
    private static int[] dS;
    private static int[] dT;
    private static int[] dU;
    private static int[] dV;
    private static int[] dW;
    private static int[] dX;
    private static int dY;
    private static int dZ;
    private static int ea;
    private static Image[][] eb;
    private static Image[] ec;
    private static Image ed;
    public static Image[] s;
    private static Image[] ee;
    private static Image ef;
    private static Image[] eg;
    private static Image eh;
    public static Image t;
    public static Image u;
    private static Image[] ei;
    private static Image[] ej;
    private static Image ek;
    private static Image[] el;
    private static Image[][] em;
    private static Image[] en;
    private static Image[] eo;
    private static Image[] ep;
    private static Image[] eq;
    private static Image[] er;
    private static Image[] es;
    private static Image[] et;
    private static Image[] eu;
    private static Image[][] ev;
    private static Image[][] ew;
    private static Image[] ex;
    private static Image[] ey;
    private static Image[] ez;
    private static Image[] eA;
    private static Image[] eB;
    private static Image[] eC;
    private static Image eD;
    private static Image[] eE;
    private static Image[] eF;
    private static Image[] eG;
    public static int v;
    public static int w;
    private static boolean eH;
    private static boolean eI;
    private static boolean eJ;
    private static boolean eK;
    private static boolean eL;
    private static boolean eM;
    private static boolean eN;
    private static boolean eO;
    private static boolean eP;
    private static boolean eQ;
    private static boolean eR;
    private static boolean eS;
    private static boolean eT;
    private static boolean eU;
    private static boolean eV;
    private static boolean eW;
    private static boolean eX;
    private static boolean eY;
    private static boolean eZ;
    private static boolean fa;
    private static boolean fb;
    private static boolean fc;
    private static boolean fd;
    private static boolean fe;
    private static int ff;
    private static int fg;
    public static int x;
    private static int fh;
    private static int fi;
    public static int y;
    public static int z;
    public static int A;
    public static int B;
    private static int fj;
    private static int fk;
    private static int fl;
    private static int fm;
    private static int fn;
    private static int fo;
    private static int fp;
    public static int C;
    public static int D;
    private static int fq;
    public static int E;
    public static int F;
    public static int G;
    public static int H;
    public static int I;
    public static int J;
    public static int K;
    public static int L;
    private static int fr;
    private static int fs;
    private static int ft;
    private static boolean fu;
    private static int fv;
    private static int fw;
    public static int M;
    public static int N;
    private static boolean fx;
    private static boolean fy;
    private static boolean fz;
    private static boolean fA;
    public static int O;
    private static int fB;
    private static int fC;
    private static boolean fD;
    private static boolean fE;
    private static int fF;
    private static int fG;
    private static int fH;
    private static int fI;
    private static int fJ;
    private static int fK;
    private static int fL;
    private static int fM;
    private static int fN;
    private static int fO;
    private static int fP;
    private static int fQ;
    private static int fR;
    private static int fS;
    private static int fT;
    private static int fU;
    private static boolean fV;
    private static int fW;
    private static int fX;
    public static int P;
    public static int Q;
    private static boolean fY;
    private static String[] fZ;
    private static int ga;
    private static int gb;
    private static int gc;
    private static boolean gd;
    private static int ge;
    private static int gf;
    private static int gg;
    private static int gh;
    private static int gi;
    private static int gj;
    private static int gk;
    private static int gl;
    private static int gm;
    private static boolean gn;
    private static int go;
    private static int gp;
    private static int gq;
    private static int gr;
    private static int gs;
    private static int gt;
    private static int gu;
    private static int gv;
    private static int gw;
    private static int gx;
    private static int gy;
    private static int gz;
    private static int gA;
    private static int gB;
    private static int gC;
    private static int gD;
    private static int gE;
    private static int gF;
    private static int gG;
    private static int gH;
    private static int gI;
    private static int gJ;
    private static int gK;
    private static int gL;
    private static int gM;
    private static int gN;
    private static int gO;
    private static long gP;
    private static boolean gQ;
    private static int gR;
    private static int gS;
    private static int gT;
    private static int gU;
    private static int gV;
    private static int gW;
    private static int gX;
    private static int gY;
    private static int gZ;
    private static int ha;
    private static int hb;
    private static int hc;
    private static int hd;
    private static int he;
    private static boolean hf;
    private static boolean hg;
    private static boolean hh;
    private static int hi;
    private static int hj;
    private static int hk;
    private static boolean hl;
    private static int hm;
    private static int hn;
    private static int ho;
    private static int hp;
    private static int hq;
    private static int hr;
    private static int hs;
    private static int ht;
    private static int hu;
    private static int hv;
    private static int hw;
    private static int hx;
    private static int hy;
    private static int hz;
    private static int[][] hA;
    private static int[][] hB;
    private static int[][] hC;
    private static int[][] hD;
    private static int[][] hE;
    private static int[][] hF;
    private static int[] hG;
    private static int[] hH;
    private static int[] hI;
    private static int[] hJ;
    private static int[] hK;
    private static int[] hL;
    private static boolean hM;
    private static int hN;
    private static int hO;
    private static int hP;
    private static int hQ;
    private static int hR;
    private static int hS;
    private static int hT;
    private static int hU;
    private static int hV;
    private static int hW;
    private static int hX;
    private static int hY;
    private static int hZ;
    private static int ia;
    private static int ib;
    private static int ic;
    private static int id;
    private static int ie;
    private static int if;
    private static int ig;
    private static boolean ih;
    private static int ii;
    private static int ij;
    private static int ik;
    private static int il;
    private static int im;
    private static int[] in;
    private static int[] io;
    private static int[] ip;
    private static int[] iq;
    private static int[] ir;
    private static int[] is;
    private static int it;
    private static int iu;
    private static int[] iv;
    private static int[] iw;
    private static int[] ix;
    private static int[] iy;
    private static int[] iz;
    private static int[] iA;
    private static int[] iB;
    private static boolean iC;
    private static int iD;
    private static int iE;
    private static int iF;
    private static int iG;
    private static String[][] iH;
    private static int iI;
    private static int iJ;
    private static int iK;
    private static int iL;
    private static int iM;
    private static int iN;
    private static String iO;
    private static int iP;
    private static int iQ;
    private static int iR;
    private static int iS;
    private static byte[] iT;
    private static int[] iU;
    private static boolean[] iV;
    private static Image iW;
    private static Image iX;
    private static Image iY;
    private static Image[] iZ;
    private static int ja;
    private static int[] jb;
    private static int[] jc;
    private static int[] jd;
    private static int[] je;
    private static int[] jf;
    private static Image jg;
    private static int jh;
    private static int ji;
    private static int jj;
    private static int jk;
    private static int jl;
    private static String[] jm;
    private static int[] jn;
    private static int[] jo;
    private static int[] jp;
    private static int jq;
    private static int jr;
    private static int js;
    private static int jt;
    private static int ju;
    private static int jv;
    private static int jw;
    private static int jx;
    private static int jy;
    private static int jz;
    private static int jA;
    private static int jB;
    private static int jC;
    private static int jD;
    static int R;
    private static int jE;
    static int S;
    private static Image jF;
    private static Image jG;
    private static int jH;
    private static int jI;
    public static boolean T;
    private static Image[][][] jJ;
    private static int jK;
    private static int jL;
    private static int[] jM;
    private static int[] jN;
    private static int[] jO;
    private static int[] jP;
    private static int[] jQ;
    private static int[] jR;
    private static int[] jS;
    private static int[] jT;
    private static int[] jU;
    private static int[] jV;
    private static int[] jW;
    private static int[] jX;
    private static int[] jY;
    private static int[] jZ;
    private static int[] ka;
    private static int[] kb;
    private static int[] kc;
    private static int[] kd;
    private static int[] ke;
    private static int[] kf;
    private static int[] kg;
    private static int[] kh;
    private static int[] ki;
    private static int[] kj;
    private static int[] kk;
    private static boolean[] kl;
    private static int[][] km;
    private static int[][] kn;
    private static boolean[][] ko;
    private static int[] kp;
    private static int[] kq;
    static int U;
    private static int kr;
    private static int ks;
    private static int kt;
    private static int ku;
    private static int kv;
    private static int[] kw;
    private static String[][] kx;
    private static String[][] ky;
    private static int[][] kz;
    private static Image[] kA;
    private static String[][] kB;
    private static String[][] kC;
    private static int kD;
    private static int kE;
    private static int kF;
    private static int kG;
    private static int[] kH;
    private static String[] kI;
    static int[] V;
    private static int[] kJ;
    private static Image[] kK;
    private static int kL;
    private static int kM;
    private static int kN;
    private static String[] kO;
    private static String[] kP;
    private static int[] kQ;
    public static boolean[] W;
    private static int[] kR;
    private static int[] kS;
    private static int[] kT;
    private static int[] kU;
    private static Image kV;
    private static Image kW;
    private static int kX;
    private static int kY;
    private static int kZ;
    private static int la;
    private static int lb;
    private static int lc;
    private static int ld;
    private static int le;
    private static int lf;
    private static int lg;
    private static int lh;
    private static int li;
    private static int lj;
    private static int lk;
    private static int ll;
    private static String lm;
    private static Image ln;
    private static boolean lo;
    private static int lp;
    public static int X;
    private static int lq;
    private static int lr;
    private static int ls;
    private static String lt;
    private static String lu;
    private static String[] lv;
    private static Image[][] lw;
    private static int lx;
    private static int[] ly;
    private static int[] lz;
    private static int[] lA;
    private static String[][] lB;
    private static String[][] lC;
    private static String[][] lD;
    private static String[][] lE;
    private static String[][] lF;
    private static String[] lG;
    private static int[] lH;
    private static int[] lI;
    private static int[] lJ;
    private static int lK;
    private static int lL;
    private static int lM;
    private static int lN;
    private static boolean lO;
    private static String lP;
    private static String[] lQ;
    private static String[] lR;
    public static int Y;
    private static int lS;
    private static int lT;
    private static Font lU;
    private static Font lV;
    private static int lW;
    private String lX = "12345678";
    private int lY;
    private static String lZ;
    private static int[] ma;
    private static int mb;
    private int mc = 0;
    private boolean md = false;
    private String me = "";
    private int mf;
    private int mg;

    static {
        int[][] nArrayArray = new int[7][];
        nArrayArray[0] = new int[]{-5, -2};
        nArrayArray[1] = new int[]{-2, -4};
        int[] nArray = new int[2];
        nArray[1] = -5;
        nArrayArray[2] = nArray;
        nArrayArray[3] = new int[]{-6, -6};
        nArrayArray[4] = new int[]{-5, -2};
        nArrayArray[5] = new int[]{-2, -4};
        int[] nArray2 = new int[2];
        nArray2[1] = -5;
        nArrayArray[6] = nArray2;
        ag = nArrayArray;
        ah = new int[][]{{5, 8}, {3, 3}, new int[2], {4, 4}, {5, 8}, {3, 3}, new int[2]};
        ai = new int[]{1, -2, 1, 1, 1, -2, 1};
        aj = new int[]{3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        ak = new int[]{50, 150, 240, 400, 600, 800, 1000, 1500, 2000, 3000, 5000, 7000, 9000, 12000, 15000, 19000, 26000, 32000, 40000, 50000, 60000, 70000, 80000, 90000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000};
        al = new int[]{20, 20, 20, 20, 30, 30, 30, 40, 40, 50, 60, 70, 80, 80, 90, 90, 100, 120, 130, 150, 160, 170, 180, 190, 200, 210, 220, 250, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300};
        am = new String[]{"Ma m\u1ed9t m\u1eaft", "\u1ed1c s\u00ean", "D\u01a1i qu\u1ef7", "Qu\u1ef7 m\u1ed9t ch\u00e2n", "C\u00f3c xanh", "Nh\u1ec7n", "Ong b\u00f2 v\u1ebd", "B\u00f9 nh\u00ecn r\u01a1m", "C\u00e1 c\u1ecdp", "Ninja gi\u00f3", "C\u00e1 c\u1ecdp \u0111\u1ecf", "Ma m\u1ed9t m\u1eaft", "R\u1ebft sa m\u1ea1c", "D\u01a1i v\u00e0ng", "Chu\u1ed9t", "Th\u1ed5 d\u00e2n", "Ong \u0111\u1ecf", "B\u00f2 c\u1ea1p", "Qu\u1ef7 hoa", "Qu\u1ef7 t\u1eed hoa"};
        an = new int[]{140, 70, 150, 120, 150, 150, 60, 1000, 250, 500, 4000, 500, 600, 1000, 1500, 2000, 1000, 3500, 1500, 5500};
        ao = new int[]{15, 10, 30, 25, 25, 25, 5, 10, 20, 40, 30, 40, 130, 30, 40, 100, 60, 160, 120, 200};
        ap = new int[]{35, 20, 55, 40, 45, 50, 30, 130, 60, 65, 70, 75, 120, 85, 100, 120, 140, 160, 180, 200};
        aq = new int[]{20, 10, 50, 25, 30, 40, 15, 50, 60, 70, 80, 100, 120, 130, 160, 180, 170, 210, 190, 220};
        int[][][] nArrayArray2 = new int[14][][];
        int[][] nArrayArray3 = new int[3][];
        int[] nArray3 = new int[3];
        nArray3[1] = -13;
        nArray3[2] = 31;
        nArrayArray3[0] = nArray3;
        int[] nArray4 = new int[3];
        nArray4[1] = -7;
        nArray4[2] = 8;
        nArrayArray3[1] = nArray4;
        int[] nArray5 = new int[3];
        nArray5[1] = -11;
        nArray5[2] = 14;
        nArrayArray3[2] = nArray5;
        nArrayArray2[0] = nArrayArray3;
        int[][] nArrayArray4 = new int[3][];
        int[] nArray6 = new int[3];
        nArray6[1] = -13;
        nArray6[2] = 32;
        nArrayArray4[0] = nArray6;
        int[] nArray7 = new int[3];
        nArray7[1] = -7;
        nArray7[2] = 8;
        nArrayArray4[1] = nArray7;
        int[] nArray8 = new int[3];
        nArray8[1] = -11;
        nArray8[2] = 15;
        nArrayArray4[2] = nArray8;
        nArrayArray2[1] = nArrayArray4;
        int[][] nArrayArray5 = new int[3][];
        nArrayArray5[0] = new int[]{1, -11, 31};
        nArrayArray5[1] = new int[]{1, -9, 16};
        int[] nArray9 = new int[3];
        nArray9[0] = 6;
        nArray9[1] = -1000;
        nArrayArray5[2] = nArray9;
        nArrayArray2[2] = nArrayArray5;
        int[][] nArrayArray6 = new int[3][];
        nArrayArray6[0] = new int[]{2, -11, 32};
        nArrayArray6[1] = new int[]{2, -10, 16};
        int[] nArray10 = new int[3];
        nArray10[0] = 6;
        nArray10[1] = -1000;
        nArrayArray6[2] = nArray10;
        nArrayArray2[3] = nArrayArray6;
        int[][] nArrayArray7 = new int[3][];
        nArrayArray7[0] = new int[]{1, -11, 31};
        nArrayArray7[1] = new int[]{3, -8, 16};
        int[] nArray11 = new int[3];
        nArray11[0] = 6;
        nArray11[1] = -1000;
        nArrayArray7[2] = nArray11;
        nArrayArray2[4] = nArrayArray7;
        int[][] nArrayArray8 = new int[3][];
        nArrayArray8[0] = new int[]{2, -10, 32};
        nArrayArray8[1] = new int[]{7, -9, 17};
        int[] nArray12 = new int[3];
        nArray12[0] = 6;
        nArray12[1] = -1000;
        nArrayArray8[2] = nArray12;
        nArrayArray2[5] = nArrayArray8;
        int[][] nArrayArray9 = new int[3][];
        nArrayArray9[0] = new int[]{1, -10, 31};
        nArrayArray9[1] = new int[]{8, -8, 16};
        int[] nArray13 = new int[3];
        nArray13[0] = 6;
        nArray13[1] = -1000;
        nArrayArray9[2] = nArray13;
        nArrayArray2[6] = nArrayArray9;
        int[][] nArrayArray10 = new int[3][];
        int[] nArray14 = new int[3];
        nArray14[1] = -8;
        nArray14[2] = 35;
        nArrayArray10[0] = nArray14;
        nArrayArray10[1] = new int[]{4, -5, 15};
        nArrayArray10[2] = new int[]{2, -10, 21};
        nArrayArray2[7] = nArrayArray10;
        int[][] nArrayArray11 = new int[3][];
        int[] nArray15 = new int[3];
        nArray15[1] = -8;
        nArray15[2] = 34;
        nArrayArray11[0] = nArray15;
        nArrayArray11[1] = new int[]{5, -6, 13};
        nArrayArray11[2] = new int[]{3, -11, 22};
        nArrayArray2[8] = nArrayArray11;
        int[][] nArrayArray12 = new int[3][];
        int[] nArray16 = new int[3];
        nArray16[1] = -8;
        nArray16[2] = 31;
        nArrayArray12[0] = nArray16;
        nArrayArray12[1] = new int[]{6, -13, 9};
        nArrayArray12[2] = new int[]{4, -10, 18};
        nArrayArray2[9] = nArrayArray12;
        nArrayArray2[10] = new int[][]{{1, -8, 30}, {6, -13, 9}, {5, -8, 17}};
        nArrayArray2[11] = new int[][]{{2, -10, 30}, {6, -13, 9}, {6, -8, 16}};
        nArrayArray2[12] = new int[][]{{2, -10, 29}, {6, -13, 9}, {7, -11, 17}};
        nArrayArray2[13] = new int[][]{{3, -11, 33}, {8, -4, 13}, {8, -15, 25}};
        ar = nArrayArray2;
        int[][][] nArrayArray13 = new int[46][][];
        int[][] nArrayArray14 = new int[3][];
        int[] nArray17 = new int[3];
        nArray17[1] = -10;
        nArray17[2] = 32;
        nArrayArray14[0] = nArray17;
        nArrayArray14[1] = new int[]{1, -7, 7};
        nArrayArray14[2] = new int[]{1, -11, 15};
        nArrayArray13[0] = nArrayArray14;
        int[][] nArrayArray15 = new int[3][];
        int[] nArray18 = new int[3];
        nArray18[1] = -10;
        nArray18[2] = 33;
        nArrayArray15[0] = nArray18;
        nArrayArray15[1] = new int[]{1, -7, 7};
        nArrayArray15[2] = new int[]{1, -11, 16};
        nArrayArray13[1] = nArrayArray15;
        nArrayArray13[2] = new int[][]{{1, -10, 33}, {2, -10, 11}, {2, -9, 16}};
        nArrayArray13[3] = new int[][]{{1, -10, 32}, {3, -11, 9}, {3, -11, 16}};
        nArrayArray13[4] = new int[][]{{1, -10, 34}, {4, -9, 9}, {4, -8, 16}};
        nArrayArray13[5] = new int[][]{{1, -10, 34}, {5, -11, 11}, {5, -10, 17}};
        nArrayArray13[6] = new int[][]{{1, -10, 33}, {6, -9, 9}, {6, -8, 16}};
        int[][] nArrayArray16 = new int[3][];
        int[] nArray19 = new int[3];
        nArray19[1] = -9;
        nArray19[2] = 36;
        nArrayArray16[0] = nArray19;
        nArrayArray16[1] = new int[]{7, -5, 15};
        nArrayArray16[2] = new int[]{7, -10, 21};
        nArrayArray13[7] = nArrayArray16;
        nArrayArray13[8] = new int[][]{{4, -13, 26}, new int[3], new int[3]};
        nArrayArray13[9] = new int[][]{{5, -13, 25}, new int[3], new int[3]};
        nArrayArray13[10] = new int[][]{{6, -12, 26}, new int[3], new int[3]};
        nArrayArray13[11] = new int[][]{{7, -13, 25}, new int[3], new int[3]};
        int[][] nArrayArray17 = new int[3][];
        int[] nArray20 = new int[3];
        nArray20[1] = -9;
        nArray20[2] = 35;
        nArrayArray17[0] = nArray20;
        nArrayArray17[1] = new int[]{8, -4, 13};
        nArrayArray17[2] = new int[]{8, -14, 27};
        nArrayArray13[12] = nArrayArray17;
        int[][] nArrayArray18 = new int[3][];
        int[] nArray21 = new int[3];
        nArray21[1] = -9;
        nArray21[2] = 32;
        nArrayArray18[0] = nArray21;
        nArrayArray18[1] = new int[]{9, -11, 9};
        nArrayArray18[2] = new int[]{10, -10, 18};
        nArrayArray13[13] = nArrayArray18;
        nArrayArray13[14] = new int[][]{{2, -7, 34}, {9, -11, 9}, {11, -8, 16}};
        nArrayArray13[15] = new int[][]{{2, -8, 33}, {9, -11, 9}, {12, -8, 15}};
        nArrayArray13[16] = new int[][]{{2, -7, 33}, {9, -11, 9}, {13, -12, 16}};
        int[][] nArrayArray19 = new int[3][];
        int[] nArray22 = new int[3];
        nArray22[1] = -11;
        nArray22[2] = 32;
        nArrayArray19[0] = nArray22;
        nArrayArray19[1] = new int[]{9, -11, 9};
        nArrayArray19[2] = new int[]{14, -15, 19};
        nArrayArray13[17] = nArrayArray19;
        nArrayArray13[18] = new int[][]{{2, -9, 33}, {9, -11, 9}, {15, -13, 20}};
        nArrayArray13[19] = new int[][]{{2, -9, 32}, {9, -11, 9}, {16, -7, 23}};
        nArrayArray13[20] = new int[][]{{2, -9, 33}, {9, -11, 9}, {17, -11, 19}};
        nArrayArray13[21] = new int[][]{{3, -12, 34}, {8, -4, 13}, {8, -15, 25}};
        int[][] nArrayArray20 = new int[3][];
        int[] nArray23 = new int[3];
        nArray23[1] = -10;
        nArray23[2] = 28;
        nArrayArray20[0] = nArray23;
        nArrayArray20[1] = new int[3];
        nArrayArray20[2] = new int[]{19, -8, 12};
        nArrayArray13[22] = nArrayArray20;
        int[][] nArrayArray21 = new int[3][];
        int[] nArray24 = new int[3];
        nArray24[1] = -9;
        nArray24[2] = 35;
        nArrayArray21[0] = nArray24;
        nArrayArray21[1] = new int[]{8, -4, 13};
        nArrayArray21[2] = new int[]{9, -9, 22};
        nArrayArray13[23] = nArrayArray21;
        int[][] nArrayArray22 = new int[3][];
        int[] nArray25 = new int[3];
        nArray25[1] = -10;
        nArray25[2] = 33;
        nArrayArray22[0] = nArray25;
        nArrayArray22[1] = new int[3];
        nArrayArray22[2] = new int[3];
        nArrayArray13[24] = nArrayArray22;
        nArrayArray13[25] = new int[][]{{9, -4, 24}, new int[3], new int[3]};
        nArrayArray13[26] = new int[][]{{10, -4, 22}, new int[3], new int[3]};
        int[][] nArrayArray23 = new int[3][];
        int[] nArray26 = new int[3];
        nArray26[1] = -9;
        nArray26[2] = 32;
        nArrayArray23[0] = nArray26;
        nArrayArray23[1] = new int[]{8, -4, 9};
        nArrayArray23[2] = new int[]{10, -10, 18};
        nArrayArray13[27] = nArrayArray23;
        nArrayArray13[28] = new int[][]{{2, -7, 34}, {8, -4, 9}, {11, -8, 16}};
        nArrayArray13[29] = new int[][]{{2, -8, 33}, {8, -4, 9}, {12, -8, 15}};
        nArrayArray13[30] = new int[][]{{2, -7, 33}, {8, -4, 9}, {13, -12, 16}};
        int[][] nArrayArray24 = new int[3][];
        int[] nArray27 = new int[3];
        nArray27[1] = -11;
        nArray27[2] = 32;
        nArrayArray24[0] = nArray27;
        nArrayArray24[1] = new int[]{7, -5, 9};
        nArrayArray24[2] = new int[]{14, -15, 19};
        nArrayArray13[31] = nArrayArray24;
        nArrayArray13[32] = new int[][]{{2, -9, 33}, {7, -5, 9}, {15, -13, 20}};
        nArrayArray13[33] = new int[][]{{2, -9, 32}, {7, -5, 9}, {16, -7, 23}};
        nArrayArray13[34] = new int[][]{{2, -9, 33}, {7, -5, 9}, {17, -11, 19}};
        int[][] nArrayArray25 = new int[3][];
        int[] nArray28 = new int[3];
        nArray28[1] = -9;
        nArray28[2] = 32;
        nArrayArray25[0] = nArray28;
        nArrayArray25[1] = new int[]{2, -10, 11};
        nArrayArray25[2] = new int[]{10, -10, 18};
        nArrayArray13[35] = nArrayArray25;
        nArrayArray13[36] = new int[][]{{2, -7, 34}, {3, -11, 9}, {11, -8, 16}};
        nArrayArray13[37] = new int[][]{{2, -8, 33}, {4, -9, 9}, {12, -8, 15}};
        nArrayArray13[38] = new int[][]{{2, -7, 33}, {5, -11, 11}, {13, -12, 16}};
        int[][] nArrayArray26 = new int[3][];
        int[] nArray29 = new int[3];
        nArray29[1] = -11;
        nArray29[2] = 32;
        nArrayArray26[0] = nArray29;
        nArrayArray26[1] = new int[]{2, -10, 11};
        nArrayArray26[2] = new int[]{14, -15, 19};
        nArrayArray13[39] = nArrayArray26;
        nArrayArray13[40] = new int[][]{{2, -9, 33}, {3, -11, 9}, {15, -13, 20}};
        nArrayArray13[41] = new int[][]{{2, -9, 32}, {4, -9, 9}, {16, -7, 23}};
        nArrayArray13[42] = new int[][]{{2, -9, 33}, {5, -11, 11}, {17, -11, 19}};
        nArrayArray13[43] = new int[][]{{1, -8, 38}, new int[3], {18, -8, 21}};
        nArrayArray13[44] = new int[][]{{1, -8, 39}, new int[3], {18, -8, 22}};
        nArrayArray13[45] = new int[][]{{1, -10, 43}, new int[3], {18, -11, 26}};
        as = nArrayArray13;
        at = new int[]{-2, -6, 22, 21, 19, 22, 10, -2, -2, 5, 19};
        au = new int[]{9, 22, 25, 17, 26, 37, 36, 49, 50, 52, 36};
        av = new int[][]{{2, 1, 1, 5, 3}, {2, 4, 3}, {2, 3}, {2, 1, 3}, {6, 3}, {2, 7, 3}, {2, 8, 3}, {2, 9, 3}, {2, 3}, {1, 3}, {2, 3}};
        int[][] nArrayArray27 = new int[11][];
        int[] nArray30 = new int[5];
        nArray30[1] = 1;
        nArray30[2] = 2;
        nArray30[3] = 5;
        nArrayArray27[0] = nArray30;
        int[] nArray31 = new int[3];
        nArray31[0] = 3;
        nArray31[1] = 4;
        nArrayArray27[1] = nArray31;
        int[] nArray32 = new int[2];
        nArray32[0] = 6;
        nArrayArray27[2] = nArray32;
        int[] nArray33 = new int[3];
        nArray33[0] = 7;
        nArray33[1] = 8;
        nArrayArray27[3] = nArray33;
        int[] nArray34 = new int[2];
        nArray34[0] = 9;
        nArrayArray27[4] = nArray34;
        int[] nArray35 = new int[3];
        nArray35[0] = 10;
        nArray35[1] = 11;
        nArrayArray27[5] = nArray35;
        int[] nArray36 = new int[3];
        nArray36[0] = 12;
        nArray36[1] = 13;
        nArrayArray27[6] = nArray36;
        int[] nArray37 = new int[3];
        nArray37[0] = 14;
        nArray37[1] = 15;
        nArrayArray27[7] = nArray37;
        int[] nArray38 = new int[2];
        nArray38[0] = 16;
        nArrayArray27[8] = nArray38;
        int[] nArray39 = new int[2];
        nArray39[0] = 17;
        nArrayArray27[9] = nArray39;
        int[] nArray40 = new int[2];
        nArray40[0] = 18;
        nArrayArray27[10] = nArray40;
        aw = nArrayArray27;
        int[][] nArrayArray28 = new int[11][];
        int[] nArray41 = new int[5];
        nArray41[1] = 360;
        nArray41[2] = 1128;
        nArrayArray28[0] = nArray41;
        nArrayArray28[1] = new int[3];
        nArrayArray28[2] = new int[2];
        int[] nArray42 = new int[3];
        nArray42[1] = 2784;
        nArrayArray28[3] = nArray42;
        int[] nArray43 = new int[2];
        nArray43[0] = 1248;
        nArrayArray28[4] = nArray43;
        nArrayArray28[5] = new int[3];
        nArrayArray28[6] = new int[3];
        nArrayArray28[7] = new int[3];
        nArrayArray28[8] = new int[2];
        nArrayArray28[9] = new int[2];
        nArrayArray28[10] = new int[2];
        ax = nArrayArray28;
        int[][] nArrayArray29 = new int[11][];
        nArrayArray29[0] = new int[5];
        nArrayArray29[1] = new int[3];
        nArrayArray29[2] = new int[2];
        nArrayArray29[3] = new int[3];
        int[] nArray44 = new int[2];
        nArray44[0] = 408;
        nArrayArray29[4] = nArray44;
        nArrayArray29[5] = new int[3];
        nArrayArray29[6] = new int[3];
        nArrayArray29[7] = new int[3];
        nArrayArray29[8] = new int[2];
        nArrayArray29[9] = new int[2];
        nArrayArray29[10] = new int[2];
        ay = nArrayArray29;
        az = new int[][]{{15, 1, 1, 2, 2, 1, 2, 4, 5, 3}, {6, 15, 1, 2, 1, 2, 1, 2, 1, 2, 4, 5, 3}, {6, 1, 2, 8, 11, 4, 7, 1, 2, 1, 2, 3}, {8, 9, 15, 12, 14, 10, 4, 16, 5, 1, 2, 3}, {7, 17}, {4, 1, 2, 17}, {8, 9, 15, 12, 14, 10, 4, 18, 4, 7, 3}, {1, 2, 4, 5, 3}, {6, 4, 19, 3}, {4, 20, 21, 3}, {4, 3}, {4, 22, 3}, {23, 4, 7, 4, 3}, {23, 25, 4, 7, 24, 26, 3}, {27, 4, 7, 4, 28, 3}, {4, 31, 32, 7, 4, 31, 4, 7, 4, 8, 17, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 29, 3}, {4, 30, 3}, {15, 31, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 33, 17, 3}};
        int[][] nArrayArray30 = new int[19][];
        int[] nArray45 = new int[10];
        nArray45[0] = 1;
        nArray45[1] = -4;
        nArray45[2] = -1;
        nArray45[3] = -1;
        nArray45[4] = -4;
        nArray45[5] = -4;
        nArray45[6] = -4;
        nArray45[8] = 20000;
        nArrayArray30[0] = nArray45;
        int[] nArray46 = new int[13];
        nArray46[1] = 2;
        nArray46[2] = -4;
        nArray46[3] = -4;
        nArray46[4] = -5;
        nArray46[5] = -5;
        nArray46[6] = -5;
        nArray46[7] = -5;
        nArray46[8] = -5;
        nArray46[9] = -5;
        nArray46[10] = 1;
        nArray46[11] = 20000;
        nArrayArray30[1] = nArray46;
        int[] nArray47 = new int[12];
        nArray47[1] = -5;
        nArray47[2] = -5;
        nArray47[5] = 2;
        nArray47[7] = 55;
        nArray47[8] = 55;
        nArray47[9] = -4;
        nArray47[10] = -4;
        nArrayArray30[2] = nArray47;
        int[] nArray48 = new int[12];
        nArray48[1] = 1;
        nArray48[3] = 2;
        nArray48[4] = 3;
        nArray48[5] = 4;
        nArray48[6] = 3;
        nArray48[7] = 432;
        nArray48[8] = 20000;
        nArray48[9] = -4;
        nArray48[10] = -4;
        nArrayArray30[3] = nArray48;
        int[] nArray49 = new int[2];
        nArray49[1] = 4;
        nArrayArray30[4] = nArray49;
        int[] nArray50 = new int[4];
        nArray50[0] = 4;
        nArray50[1] = 55;
        nArray50[2] = 55;
        nArrayArray30[5] = nArray50;
        int[] nArray51 = new int[11];
        nArray51[1] = 5;
        nArray51[2] = 3;
        nArray51[3] = 6;
        nArray51[4] = 7;
        nArray51[5] = 8;
        nArray51[6] = 5;
        nArray51[7] = 2;
        nArray51[8] = 6;
        nArrayArray30[6] = nArray51;
        int[] nArray52 = new int[5];
        nArray52[0] = -4;
        nArray52[1] = -4;
        nArray52[2] = 7;
        nArray52[3] = 20000;
        nArrayArray30[7] = nArray52;
        int[] nArray53 = new int[4];
        nArray53[1] = 8;
        nArrayArray30[8] = nArray53;
        int[] nArray54 = new int[4];
        nArray54[0] = 9;
        nArrayArray30[9] = nArray54;
        int[] nArray55 = new int[2];
        nArray55[0] = 10;
        nArrayArray30[10] = nArray55;
        int[] nArray56 = new int[3];
        nArray56[0] = 11;
        nArrayArray30[11] = nArray56;
        int[] nArray57 = new int[5];
        nArray57[1] = 12;
        nArray57[3] = 13;
        nArrayArray30[12] = nArray57;
        int[] nArray58 = new int[7];
        nArray58[2] = 14;
        nArray58[4] = 600;
        nArrayArray30[13] = nArray58;
        int[] nArray59 = new int[6];
        nArray59[1] = 15;
        nArray59[3] = 16;
        nArrayArray30[14] = nArray59;
        int[] nArray60 = new int[12];
        nArray60[0] = 17;
        nArray60[4] = 55;
        nArray60[6] = 56;
        nArray60[8] = 57;
        nArray60[10] = 94;
        nArrayArray30[15] = nArray60;
        int[] nArray61 = new int[38];
        nArray61[0] = 18;
        nArray61[1] = 19;
        nArray61[2] = 20;
        nArray61[3] = 21;
        nArray61[4] = 22;
        nArray61[5] = 23;
        nArray61[6] = 24;
        nArray61[7] = 25;
        nArray61[8] = 26;
        nArray61[9] = 27;
        nArray61[10] = 28;
        nArray61[11] = 29;
        nArray61[12] = 30;
        nArray61[13] = 31;
        nArray61[14] = 32;
        nArray61[15] = 33;
        nArray61[16] = 34;
        nArray61[17] = 35;
        nArray61[18] = 36;
        nArray61[19] = 37;
        nArray61[20] = 38;
        nArray61[21] = 39;
        nArray61[22] = 40;
        nArray61[23] = 41;
        nArray61[24] = 42;
        nArray61[25] = 43;
        nArray61[26] = 44;
        nArray61[27] = 45;
        nArray61[28] = 46;
        nArray61[29] = 47;
        nArray61[30] = 48;
        nArray61[31] = 49;
        nArray61[32] = 50;
        nArray61[33] = 51;
        nArray61[34] = 52;
        nArray61[35] = 53;
        nArrayArray30[16] = nArray61;
        int[] nArray62 = new int[3];
        nArray62[0] = 54;
        nArrayArray30[17] = nArray62;
        int[] nArray63 = new int[16];
        nArray63[0] = 4;
        nArray63[2] = 58;
        nArray63[3] = 59;
        nArray63[4] = 60;
        nArray63[5] = 61;
        nArray63[6] = 62;
        nArray63[7] = 63;
        nArray63[8] = 64;
        nArray63[9] = 65;
        nArray63[10] = 66;
        nArray63[11] = 67;
        nArray63[12] = 68;
        nArrayArray30[18] = nArray63;
        aA = nArrayArray30;
        int[][] nArrayArray31 = new int[19][];
        nArrayArray31[0] = new int[]{5, 26, 30, 33, 41, 48, 53, 57, 58, 60};
        nArrayArray31[1] = new int[]{5, 6, 8, 9, 18, 20, 30, 32, 39, 40, 60, 63, 67};
        nArrayArray31[2] = new int[]{5, 6, 7, 13, 14, 19, 21, 34, 35, 60, 79, 80};
        int[] nArray64 = new int[12];
        nArray64[0] = 1;
        nArray64[1] = 2;
        nArray64[2] = 3;
        nArray64[3] = 20;
        nArray64[4] = 50;
        nArray64[5] = 80;
        nArray64[6] = 85;
        nArray64[7] = 90;
        nArray64[8] = 92;
        nArray64[9] = 93;
        nArray64[10] = 95;
        nArrayArray31[3] = nArray64;
        nArrayArray31[4] = new int[]{5, 6};
        nArrayArray31[5] = new int[]{10, 12, 13, 50};
        nArrayArray31[6] = new int[]{1, 2, 3, 4, 5, 6, 17, 18, 19, 20, 21};
        nArrayArray31[7] = new int[]{5, 10, 20, 30, 32};
        nArrayArray31[8] = new int[]{5, 10, 15, 20};
        nArrayArray31[9] = new int[]{10, 15, 20, 25};
        nArrayArray31[10] = new int[]{20, 25};
        nArrayArray31[11] = new int[]{10, 12, 14};
        nArrayArray31[12] = new int[]{15, 30, 35, 45, 55};
        nArrayArray31[13] = new int[]{10, 12, 30, 40, 45, 50, 75};
        nArrayArray31[14] = new int[]{15, 30, 35, 45, 53, 55};
        nArrayArray31[15] = new int[]{10, 12, 13, 40, 50, 60, 70, 80, 90, 95, 100, 105};
        nArrayArray31[16] = new int[]{10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47};
        nArrayArray31[17] = new int[]{10, 12, 14};
        nArrayArray31[18] = new int[]{10, 11, 60, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74};
        aB = nArrayArray31;
        aC = new int[][]{{1344, 288}, {816, 48}, {816, 192}, {744, 192}, {144, 264}, {1440, 96}, {1368, 288}, {1128, 288}, {840, 312}};
        int[] nArray65 = new int[69];
        nArray65[9] = 2;
        nArray65[21] = 2;
        nArray65[23] = 1;
        nArray65[25] = 4;
        nArray65[27] = 3;
        nArray65[29] = 6;
        nArray65[31] = 2;
        nArray65[33] = 5;
        nArray65[35] = 4;
        nArray65[37] = 1;
        nArray65[39] = 3;
        nArray65[43] = 5;
        nArray65[45] = 4;
        nArray65[47] = 3;
        nArray65[49] = 2;
        nArray65[51] = 2;
        nArray65[53] = 1;
        nArray65[59] = 3;
        nArray65[61] = 3;
        nArray65[64] = 2;
        nArray65[65] = 1;
        nArray65[66] = 3;
        nArray65[67] = 2;
        nArray65[68] = 2;
        aD = nArray65;
        int[] nArray66 = new int[69];
        nArray66[3] = 1;
        nArray66[5] = 1;
        nArray66[9] = 1;
        nArray66[12] = 2;
        nArray66[14] = 2;
        nArray66[15] = 3;
        nArray66[18] = 1;
        nArray66[19] = 2;
        nArray66[20] = 1;
        nArray66[21] = 2;
        nArray66[22] = 1;
        nArray66[23] = 2;
        nArray66[24] = 1;
        nArray66[25] = 2;
        nArray66[26] = 1;
        nArray66[27] = 2;
        nArray66[28] = 1;
        nArray66[29] = 2;
        nArray66[30] = 1;
        nArray66[31] = 2;
        nArray66[32] = 1;
        nArray66[33] = 2;
        nArray66[34] = 1;
        nArray66[35] = 2;
        nArray66[36] = 1;
        nArray66[37] = 2;
        nArray66[38] = 1;
        nArray66[39] = 2;
        nArray66[40] = 1;
        nArray66[41] = 2;
        nArray66[42] = 1;
        nArray66[43] = 2;
        nArray66[44] = 1;
        nArray66[45] = 2;
        nArray66[46] = 1;
        nArray66[47] = 2;
        nArray66[48] = 1;
        nArray66[49] = 2;
        nArray66[50] = 1;
        nArray66[51] = 2;
        nArray66[52] = 1;
        nArray66[53] = 2;
        nArray66[54] = 4;
        nArray66[56] = 4;
        nArray66[58] = 1;
        nArray66[59] = 2;
        nArray66[60] = 1;
        nArray66[61] = 2;
        nArray66[63] = 1;
        nArray66[64] = 2;
        nArray66[65] = 1;
        nArray66[66] = 2;
        nArray66[67] = 1;
        nArray66[68] = 2;
        aE = nArray66;
        aF = new String[]{"Ch\u01a1i m\u1edbi", "Ch\u01a1i ti\u1ebfp", "Ch\u1ec9 d\u1eabn", "Game kh\u00e1c", "Tho\u00e1t"};
        aG = new String[][]{{"R\u1eebng tr\u00fac \u0111\u00e2y r\u1ed3i!", "H\u00f4m nay ta ph\u1ea3i h\u1ea1 \u0111\u01b0\u1ee3c con qu\u00e1i v\u1eadt \u0111\u00f3!", "N\u00f3 ch\u1eafc quanh qu\u1ea9n \u0111\u00e2y th\u00f4i!"}, {"B\u1ecdn qu\u00e1i v\u1eadt nh\u00e3i nh\u00e9p n\u00e0y l\u00e0m g\u00ec \u0111\u01b0\u1ee3c ta ch\u1ee9!"}, {"Con qu\u00e1i v\u1eadt hung h\u0103ng kia r\u1ed3i!", "Ng\u01b0\u01a1i ph\u1ea3i \u0111\u1ec1n t\u1ed9i v\u00ec nh\u1eefng g\u00ec g\u00e2y ra!"}, {"N\u00e0y c\u00e1c con, sau bao n\u0103m th\u00e1ng t\u1eadp luy\u1ec7n...", "\u0110\u00e2y l\u00e0 l\u00fac c\u00e1c con th\u1ec3 hi\u1ec7n t\u00e0i n\u0103ng!", "G\u1ea7n \u0111\u00e2y, trong khu r\u1eebng tr\u00fac...", "..xu\u1ea5t hi\u1ec7n 1 con qu\u00e1i v\u1eadt to l\u1edbn..", "N\u00f3 th\u01b0\u1eddng ph\u00e1 ph\u00e1ch, \u0111e d\u1ecda d\u00e2n l\u00e0ng.", "C\u00e1c con bi\u1ebft ph\u1ea3i l\u00e0m g\u00ec r\u1ed3i ch\u1ee9?", "\u0110\u00e2y l\u00e0 l\u00fac c\u00e1c con ch\u1ee9ng minh kh\u1ea3 n\u0103ng c\u1ee7a m\u00ecnh.. ", "\u0110i \u0111i. V\u00e0 h\u00e3y c\u1ea9n th\u1eadn \u0111\u1ea5y !!"}, {"Ch\u1ebft \u0111i, con qu\u00e1i v\u1eadt hung h\u0103ng kia !!!", "Hahahaha!"}, {"Ch\u00e0o m\u1eebng ng\u01b0\u01a1i \u0111\u1ebfn v\u1edbi tr\u01b0\u1eddng \u0111\u00e0o t\u1ea1o ninja Hirosaki.", "Ta l\u00e0 Okamesama, hi\u1ec7u tr\u01b0\u1edfng tr\u01b0\u1eddng n\u00e0y.", "T\u1ea1i \u0111\u00e2y ch\u00fang ta s\u1ebd \u0111\u00e0o t\u1ea1o c\u00e1c ng\u01b0\u01a1i, tr\u1edf th\u00e0nh nh\u1eefng ninja chuy\u00ean nghi\u1ec7p.", "Gi\u00e1o vi\u00ean ch\u1ee7 nhi\u00eam c\u1ee7a ng\u01b0\u01a1i l\u00e0 th\u1ea7y Takanashi", "H\u00e3y \u0111\u1ebfn g\u1eb7p th\u1ea7y \u0111\u1ec3 nh\u1eadn b\u00e0i t\u1eadp.", "C\u00f3 v\u1ea5n \u0111\u1ec1 g\u00ec, ng\u01b0\u01a1i c\u00f3 th\u1ec3 h\u1ecfi nh\u1eefng \u0111\u1ed3ng m\u00f4n c\u1ee7a m\u00ecnh.", "Ch\u00fac ng\u01b0\u01a1i s\u1edbm \u0111\u1ea1t \u0111\u01b0\u1ee3c th\u00e0nh c\u00f4ng nh\u00e9."}, {"V\u00e2ng, th\u01b0a th\u1ea7y!"}, {"Con \u0111\u01b0\u1eddng hoa \u0111\u00e0o \u0111\u00e2y r\u1ed3i.", "Con b\u00e9 \u1ea5y \u0111\u00e2u r\u1ed3i nh\u1ec9?"}, {"Ch\u00e0o b\u1ea1n! B\u1ea1n c\u00f3 ph\u1ea3i l\u00e0 em g\u00e1i c\u00f4 Ayumi kh\u00f4ng?", "Th\u1ea7y Takanashi b\u1eadn n\u00ean nh\u1edd m\u00ecnh \u0111\u00f3n b\u1ea1n gi\u00fap.", "\u0110i theo m\u00ecnh nh\u00e9, ch\u00fang ta s\u1ebd v\u1ec1 tr\u01b0\u1eddng!"}, {"T\u1ed1t l\u1eafm, con \u0111\u00e3 \u0111\u01b0a c\u00f4 b\u00e9 v\u1ec1 an to\u00e0n!", "\u0110\u00e2y ta c\u00f3 m\u00f3n qu\u00e0 g\u00ec gi\u00e1 tr\u1ecb \u0111\u1ec3 t\u1eb7ng con..", "\u0110\u00e2y l\u00e0 t\u1ea5m b\u1eb1ng l\u00e1i xe c\u00f3 ch\u1eef k\u00fd c\u1ee7a ta. K\u1ec3 t\u1eeb nay con c\u00f3 th\u1ec3 s\u1eed d\u1ee5ng nh\u1eefng chi\u1ebfc xe m\u00e1y r\u1ed3i!", "Nh\u1edb ph\u1ea3i l\u00e1i c\u1ea9n th\u1eadn v\u00e0 \u0111\u1ed9i m\u0169 b\u1ea3o hi\u1ec3m, n\u1ebfu kh\u00f4ng l\u00e0 CSGT ph\u1ea1t \u0111\u00f3!"}, {"Thu\u1ef7 qu\u00e1i h\u1ea1 ngu\u1ed3n s\u00f4ng Waramato, con qu\u00e1i trong truy\u1ec1n thuy\u1ebft !", "M\u1ed9t khi n\u00f3 ch\u01b0a b\u1ecb ti\u00eau di\u1ec7t, ta s\u1ebd kh\u00f4ng \u0111i ti\u1ebfp!"}, {"Ch\u1ebft \u0111i, con qu\u00e1i v\u1eadt kia!", "T\u1eeb nay con s\u00f4ng n\u00e0y s\u1ebd tr\u1edf l\u1ea1i thanh b\u00ecnh r\u1ed3i!", "Ta ti\u1ebfp t\u1ee5c cu\u1ed9c h\u00e0nh tr\u00ecnh th\u00f4i!"}, {"Ng\u01b0\u01a1i c\u0169ng l\u1eb7n l\u1ed9i \u0111\u1ebfn t\u1eadn \u0111\u00e2y \u0111\u01b0\u1ee3c \u00e0, kh\u00e1 \u0111\u1ea5y!", "H\u00e3y b\u1ecf ng\u00f4i tr\u01b0\u1eddng \u1ea5y \u0111i, theo ta l\u1eadp c\u0103n c\u1ee9, ng\u01b0\u01a1i s\u1ebd c\u00f3 t\u1ea5t c\u1ea3.", "N\u1ebfu kh\u00f4ng theo ta, ng\u01b0\u01a1i s\u1ebd h\u1ed1i h\u1eadn \u0111\u1ea5y... HaHaHa!"}, {"Kh\u00f4ng \u0111\u1eddi n\u00e0o, t\u00ean ph\u1ea3n b\u1ed9i kia!"}, {"Ng\u01b0\u01a1i kh\u00e1 l\u1eafm! H\u00e3y \u0111\u1ee3i \u0111\u1ea5y, ta s\u1ebd nh\u1edb m\u1ed1i th\u00f9 n\u00e0y!", "H\u1eb9n g\u1eb7p l\u1ea1i ng\u01b0\u01a1i!!!"}, {"Ch\u00fang ta l\u1ea1i g\u1eb7p nhau r\u1ed3i!", "Ta cho ng\u01b0\u01a1i m\u1ed9t c\u01a1 h\u1ed9i l\u1ea7n cu\u1ed1i. h\u00e3y theo ta, ho\u1eb7c l\u00e0 ch\u1ebft!"}, {"Haha. T\u00ean ph\u1ea3n b\u1ed9i kia, ta s\u1ebd cho mi bi\u1ebft s\u1ee9c m\u1ea1nh m\u1ed9t ninja th\u1ef1c th\u1ee5!"}, {"Cu\u1ed1i c\u00f9ng th\u00ec mi c\u0169ng \u0111\u00e3 ph\u1ea3i d\u1ec1n t\u1ed9i!"}, {"C\u00e1c con h\u00e3y cho bi\u1ebft l\u00e0m c\u00e1ch n\u00e0o \u0111\u1ec3 di chuy\u1ec3n!"}, {"S\u1eed d\u1ee5ng ph\u00edm m\u0169i t\u00ean ho\u1eb7c ph\u00edm 2,4,6,8!"}, {"L\u00e0m th\u1ebf n\u00e0o \u0111\u1ec3 t\u1ea5n c\u00f4ng?, Kunio"}, {"S\u1eed d\u1ee5ng ph\u00edm 5 ho\u1eb7c ph\u00edm ch\u1ecdn gi\u1eefa!"}, {"L\u00e0m th\u1ebf n\u00e0o \u0111\u1ec3 tr\u00f2 chuy\u1ec7n v\u1edbi b\u1ea1n b\u00e8 ho\u1eb7c th\u1ea7y c\u00f4?,Lee"}, {"\u0110\u1ee9ng g\u1ea7n ng\u01b0\u1eddi \u0111\u00f3 r\u1ed3i b\u1ea5m Giao Ti\u1ebfp!"}, {"L\u00e0m th\u1ebf n\u00e0o \u0111\u1ec3 bi\u1ebft m\u00ecnh \u0111ang \u1edf \u0111\u00e2u! Katashi"}, {"Ch\u1ecdn menu/Xem b\u1ea3n \u0111\u1ed3!"}, {"L\u00e0m th\u1ebf n\u00e0o khi h\u1ebft HP ho\u1eb7c MP! Toniku"}, {"Ch\u1ecdn menu/S\u1eed d\u1ee5ng Item, ch\u1ecdn m\u1ed9t b\u00ecnh HP/MP \u0111\u1ec3 s\u1eed d\u1ee5ng!"}, {"Uhm..N\u1ebfu kh\u00f4ng c\u00f2n b\u00ecnh HP/MP n\u00e0o th\u00ec sao? Minori"}, {"G\u1eb7p \u00f4ng b\u00e1n h\u00e0ng mua t\u1ea1i tr\u01b0\u1eddng, \u1edf g\u1ea7n k\u00fd t\u00fac x\u00e1!"}, {"T\u1ed1t. L\u00e0m th\u1ebf n\u00e0o \u0111\u1ec3 di chuy\u1ec3n nhanh t\u1eeb khu v\u1ef1c n\u00e0y \u0111\u1ebfn khu v\u1ef1c kh\u00e1c?"}, {"M\u1edf b\u1ea3n \u0111\u1ed3, ch\u1ecdn 1 \u0111\u1ecba \u0111i\u1ec3m \u0111\u1ec3 di chuy\u1ec3n \u0111\u1ebfn!"}, {"\u0110\u00fang r\u1ed3i, L\u00e0m sao \u0111\u1ec3 s\u1eed d\u1ee5ng phi ti\u00eau?,Ryuu"}, {"B\u1ea5m ph\u00edm 1 s\u1ebd chuy\u1ec3n sang ch\u1ebf \u0111\u1ed9 phi ti\u00eau.", "B\u1ea5m ph\u00edm 1 l\u1ea7n n\u1eefa \u0111\u1ec3 quay l\u1ea1i \u0111\u00e1nh ki\u1ebfm!"}, {"T\u1ed1t. L\u00e0m sao \u0111\u1ec3 s\u1eed d\u1ee5ng khinh c\u00f4ng?"}, {"H\u1ecdc khinh c\u00f4ng t\u1eeb c\u00f4 Ayumi. B\u1ea5m ph\u00edm m\u0169i t\u00ean l\u00ean ho\u1eb7c ph\u00edm 2 nhi\u1ec1u l\u1ea7n!"}, {"Uhm..L\u00e0m sao \u0111\u1ec3 \u0111\u1ed9n th\u1ed5!"}, {"H\u1ecdc \u0111\u1ed9n th\u1ed5 t\u1eeb th\u1ea7y Honda. B\u1ea5m v\u00e0 gi\u1eef lu\u00f4n ph\u00edm xu\u1ed1ng ho\u1eb7c ph\u00edm 8!"}, {"Khi n\u00e0o m\u1edbi c\u00f3 th\u1ec3 l\u00e1i xe m\u00f4t\u00f4?"}, {"H\u1ecdc l\u00e1i xe t\u1eeb th\u1ea7y Takanashi, \u0111\u01b0\u1ee3c c\u1ea5p b\u1eb1ng l\u00e1i", "Sau \u0111\u00f3 c\u00f3 th\u1ec3 nh\u1ea3y l\u00ean ch\u1ea1y b\u1ea5t k\u1ef3 chi\u1ebfc m\u00f4 t\u00f4 n\u00e0o !"}, {"L\u00e0m sao \u0111\u1ec3 ch\u1ea1y tr\u00ean m\u1eb7t n\u01b0\u1edbc? huh ? Sadao"}, {"H\u1ecdc thu\u1eadt ch\u1ea1y n\u01b0\u1edbc/ l\u1eb7n n\u01b0\u1edbc t\u1eeb th\u1ea7y Honda! Sau \u0111\u00f3 c\u00f3 th\u1ec3 ch\u1ea1y tr\u00ean m\u1eb7t n\u01b0\u1edbc!"}, {"L\u00e0m sao \u0111\u1ec3 bi\u1ebfn h\u00ecnh!"}, {"B\u1ea5m ph\u00edm 7. Sau khi \u0111\u00e3 h\u1ecdc ph\u00e9p bi\u1ebfn h\u00ecnh t\u1eeb th\u1ea7y Hiroyaki"}, {"L\u00e0m sao \u0111\u1ec3 t\u00e0ng h\u00ecnh!"}, {"B\u1ea5m ph\u00edm 3. Sau khi \u0111\u00e3 h\u1ecdc ph\u00e9p t\u00e0ng h\u00ecnh t\u1eeb th\u1ea7y Honda"}, {"T\u1ed1t! Cho ta bi\u1ebft ai ph\u00e1t tri\u1ec3n game n\u00e0y!"}, {"Nh\u00f3m TeaMobile!"}, {"C\u1ee5 th\u1ec3 l\u00e0 ai?"}, {"...Con kh\u00f4ng bi\u1ebft..."}, {"Hmm, c\u00f3 th\u1ec3 t\u00ecm th\u00eam nhi\u1ec1u game hay kh\u00e1c \u1edf \u0111\u00e2u?"}, {"http://vechai321.xtgem.com"}, {"T\u1ed1t, c\u00e1c con c\u00f3 th\u1ec3 gi\u1ea3i t\u00e1n !"}, {"Ch\u00fang con ch\u00e0o th\u1ea7y !"}, {"C\u1ea3m \u01a1n, mi th\u1eadt t\u1ed1t b\u1ee5ng. H\u1eb9n g\u1eb7p l\u1ea1i!", "Qu\u00ean m\u1ea5t, l\u00fac n\u00e3y c\u00f4 Ayumi d\u1eb7n ta g\u1eb7p c\u00f4 \u1ea5y ngay.", "Hihi, g\u1eb7p l\u1ea1i mi sau!"}, {"Sakura, b\u1ea1n c\u00f3 sao kh\u00f4ng!"}, {"M\u00ecnh kh\u00f4ng sao, b\u1ea1n kh\u00f4ng b\u1ecb th\u01b0\u01a1ng ch\u1ee9!"}, {"M\u00ecnh c\u0169ng kh\u00f4ng sao! T\u00ean Toniku ch\u1eafc c\u0169ng kh\u00f4ng ch\u1ea1y xa \u0111\u01b0\u1ee3c \u0111\u00e2u , h\u1eafn c\u0169ng s\u1edbm b\u1ecb b\u1eaft th\u00f4i!", "Ch\u00fang ta v\u1ec1 th\u00f4i!"}, {"Toniku, ng\u01b0\u01a1i \u0111\u00e3 bi\u1ebft t\u1ed9i ch\u01b0a!"}, {"..v\u00e2ng..con bi\u1ebft...."}, {"Theo n\u1ed9i quy c\u1ee7a nh\u00e0 tr\u01b0\u1eddng. Ng\u01b0\u01a1i ph\u1ea3i r\u1eddi kh\u1ecfi ng\u00f4i tr\u01b0\u1eddng n\u00e0y.", "V\u0129nh vi\u1ec5n kh\u00f4ng \u0111\u01b0\u1ee3c tr\u1edf l\u1ea1i n\u1eefa.."}, {"......"}, {"Th\u01b0a th\u1ea7y...", "Toniku v\u1ed1n l\u00e0 h\u1ecdc sinh gi\u1ecfi c\u1ee7a tr\u01b0\u1eddng..", " Ch\u1ec9 v\u00ec nghe l\u1eddi x\u00fai gi\u1ee5c c\u1ee7a b\u1ecdn x\u1ea5u n\u00ean m\u1edbi h\u00e0nh \u0111\u1ed9ng nh\u01b0 th\u1ebf...", "\u0110\u00e2y l\u00e0 l\u1ea7n vi ph\u1ea1m \u0111\u1ea7u ti\u00ean, xin nh\u00e0 tr\u01b0\u1eddng tha th\u1ee9 cho b\u1ea1n \u1ea5y..."}, {"......"}, {"Xin c\u00e1c th\u1ea7y h\u00e3y tha th\u1ee9 cho b\u1ea1n \u1ea5y \u1ea1!"}, {"..th\u00f4i \u0111\u01b0\u1ee3c r\u1ed3i..", "T\u1ed9i Toniku r\u1ea5t n\u1eb7ng, \u0111\u00e1ng l\u1ebd ra ph\u1ea3i b\u1ecb \u0111u\u1ed5i h\u1ecdc.", "Con \u0111\u01b0\u1eddng \u0111\u1ec3 th\u00e0nh m\u1ed9t nh\u1eabn gi\u1ea3 th\u1eadt gian lao v\u00e0 ch\u00f4ng gai..", "..ng\u01b0\u01a1i ph\u1ea3i luy\u1ec7n t\u1eadp b\u1eb1ng kh\u1ea3 n\u0103ng v\u00e0 s\u1ee9c l\u1ef1c c\u1ee7a m\u00ecnh", " s\u1ebd kh\u00f4ng c\u00f3 con \u0111\u01b0\u1eddng n\u00e0o kh\u00e1c", " ... c\u00e1c con \u0111\u1ec1u bi\u1ebft ch\u1eef Nh\u1eabn g\u1ed3m ch\u1eef \u0110ao v\u00e0 ch\u1eef T\u00e2m.", "..n\u1ebfu T\u00e2m kh\u00f4ng t\u1ecbnh th\u00ec \u0110ao s\u1ebd b\u1ed5 xu\u1ed1ng!", "C\u00e1c con h\u00e3y l\u1ea5y Toniku m\u00e0 l\u00e0m g\u01b0\u01a1ng ...", "Toniku, ng\u01b0\u01a1i \u0111\u00e3 qu\u00e1 tham lam...", "..nh\u01b0ng ta c\u0169ng th\u00e2y n\u01a1i con ng\u01b0\u1eddi ng\u01b0\u01a1i c\u00f3 t\u1ed1 ch\u1ea5t ti\u1ec1m t\u00e0ng!", "N\u00ean ta quy\u1ebft \u0111\u1ecbnh tha cho con l\u1ea7n n\u00e0y...", "S\u1ebd kh\u00f4ng c\u00f3 l\u1ea7n sau, Toniku, ng\u01b0\u01a1i nh\u1edb \u0111\u1ea5y!!"}, {"V\u00e2ng, con xin c\u1ea3m \u01a1n th\u1ea7y!"}, {"S\u1eb5n \u0111\u00e2y ta mu\u1ed1n nh\u1eafc nh\u1edf th\u00eam c\u00e1c con..", "Hai th\u00e1ng n\u1eefa l\u00e0 \u0111\u1ebfn k\u1ef3 thi k\u1ebft kho\u00e1 cho Genin. C\u00e1c con ph\u1ea3i c\u1ed1 g\u1eafng t\u1eadp luy\u1ec7n h\u01a1n n\u1eefa..", "..M\u1ecdi th\u1ee9 ch\u1ec9 m\u1edbi b\u1eaft \u0111\u1ea7u th\u00f4i!"}, {"V\u00e2ng, ch\u00fang con xin nghe!"}};
        aH = new String[]{"Tr\u01b0\u1eddng \u0111\u1ea1o t\u1ea1o Ninja Hirosaki, M\u00f9a thu 1937", "2 ng\u00e0y sau, t\u1ea1i r\u1eebng tr\u00fac...", "Ch\u1ec9 d\u1eabn: D\u00f9ng ph\u00edm 5 \u0111\u1ec3 t\u1ea5n c\u00f4ng.", "6 th\u00e1ng tr\u01b0\u1edbc, t\u1ea1i tr\u01b0\u1eddng Hirosaki...", "2 ng\u00e0y sau, t\u1ea1i tr\u01b0\u1eddng Hirosaki..."};
        aI = new String[][]{{"Ch\u00fang ta \u0111i th\u00f4i!"}, {"Mu\u1ed1n r\u1ee7 ta \u0111i ch\u01a1i \u00e0!", "Ta ch\u1ec9 ch\u01a1i v\u1edbi nh\u1eefng ng\u01b0\u1eddi c\u00f9ng tr\u00ecnh \u0111\u1ed9 th\u00f4i.", "Ng\u01b0\u01a1i h\u00e3y t\u1eadp luy\u1ec7n \u0111i, bao gi\u1edd kh\u00e1 h\u01a1n th\u00ec quay l\u1ea1i. \u0110\u1eebng \u1edf \u0111\u00f3 t\u00e1n g\u00e1i n\u1eefa!"}, {"Mu\u1ed1n r\u1ee7 ta c\u00f9ng \u0111i \u00e0?", "OK, nh\u01b0ng ch\u1ec9 m\u1ed9t l\u00fac th\u00f4i nh\u00e9, n\u1ebfu kh\u00f4ng ch\u1ecb Ayumi m\u1eafng ta \u0111\u00f3!"}, {"Ta mu\u1ed1n \u0111\u1ebfn mi\u1ebfu Koujin ch\u01a1i, mi c\u00f3 th\u1ec3 d\u1eabn \u0111\u01b0\u1eddng ch\u1ee9!", "N\u1ebfu s\u1ee3, mi c\u00f3 th\u1ec3 \u1edf nh\u00e0, ta s\u1ebd t\u1ef1 \u0111i. Haha!"}};
        aJ = new int[as.length][3][3];
        int n = 0;
        while (n < as.length) {
            int n2 = 0;
            while (n2 < 3) {
                int n3 = 0;
                while (n3 < 3) {
                    a.aJ[n][n2][n3] = as[n][n2][n3];
                    if (n2 == 0 && n3 == 0) {
                        switch (as[n][n2][n3]) {
                            case 1: 
                            case 2: {
                                a.aJ[n][n2][n3] = 1;
                                break;
                            }
                            default: {
                                a.aJ[n][n2][n3] = 0;
                            }
                        }
                    }
                    ++n3;
                }
                ++n2;
            }
            ++n;
        }
        aK = new int[]{-1, 2, 1, -2};
        aL = new int[]{-3, 2, -1, 1};
        aM = -1;
        aO = true;
        aU = new boolean[14];
        aV = new boolean[14];
        ba = 0;
        by = "";
        bJ = -22;
        bK = 18;
        bL = -10;
        bM = -19;
        bN = -12;
        bO = -26;
        eL = false;
        eM = false;
        eN = false;
        eO = false;
        eP = false;
        eQ = false;
        eR = false;
        eS = false;
        eT = false;
        eU = false;
        eV = false;
        eW = false;
        eX = false;
        eY = false;
        eZ = false;
        fa = false;
        fb = false;
        fc = false;
        fd = false;
        fe = false;
        fx = true;
        fV = false;
        fX = 0;
        fZ = new String[]{""};
        gc = 0;
        ge = -1;
        gk = 1;
        gn = false;
        gp = -1;
        hm = 500;
        hn = 60;
        ho = 60;
        hN = 20;
        hO = 60;
        hR = 50;
        ig = 10;
        im = -1;
        iu = -1;
        iC = false;
        try {
            Image.createImage((String)"/attkButton.png");
        }
        catch (IOException iOException) {}
        iO = "oplj";
        jh = 24;
        R = 0;
        jE = 0;
        S = 0;
        kp = null;
        int[] nArray67 = new int[4];
        nArray67[1] = 10;
        nArray67[2] = 15;
        nArray67[3] = 20;
        kq = nArray67;
        kw = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 0xFFFFFF, 16765346, 16693877, 15439166, 13859359, 12281360};
        kx = new String[][]{{"B\u00ecnh HP nh\u1ecf", "B\u00ecnh HP v\u1eeba", "B\u00ecnh HP to", "B\u00ecnh MP nh\u1ecf", "B\u00ecnh MP v\u1eeba", "B\u00ecnh MP to"}, {"Ki\u1ebfm tre", "Th\u1ee7y linh ki\u1ebfm", "H\u1ecfa gi\u00e1c ki\u1ebfm", "Long giao ki\u1ebfm"}};
        ky = new String[][]{{"2000vn\u0111 / 50 b\u00ecnh", "3000vn\u0111 / 50 b\u00ecnh", "3000vn\u0111 / 30 b\u00ecnh", "2000vn\u0111 / 50 b\u00ecnh", "2000vn\u0111 / 30 b\u00ecnh", "3000vn\u0111 / 50 b\u00ecnh"}, {"2000vn\u0111", "3000vn\u0111", "3000vn\u0111", "5000vn\u0111"}};
        kz = new int[][]{{200, 600, 1500, 200, 600, 1200}, {2000, 12000, 45000, 182000}};
        kB = new String[][]{{"BinhHP_Nho(50)", "BinhHP_Vua(50)", "BinhHP_To(30)", "BinhMP_Nho(50)", "BinhMP_Vua(30)", "BinhMP_To(50)"}, {"KiemTre", "ThuyLinhKiem", "HoaGiacKiem", "LongGiaoKiem"}};
        kC = new String[][]{{"sms://" + ab[1], "sms://" + ab[2], "sms://" + ab[2], "sms://" + ab[1], "sms://" + ab[1], "sms://" + ab[1]}, {"sms://" + ab[0], "sms://" + ab[2], "sms://" + ab[3], "sms://" + ab[4]}};
        kH = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 0xFFFFFF, 16765346, 16693877, 15439166, 13859359, 12281360};
        kI = new String[]{"B\u00ecnh HP nh\u1ecf", "B\u00ecnh HP v\u1eeba", "B\u00ecnh HP to", "B\u00ecnh MP nh\u1ecf", "B\u00ecnh MP v\u1eeba", "B\u00ecnh MP to"};
        V = new int[]{5, 2, 1, 5, 2, 1};
        kJ = new int[]{100, 400, 1000, 100, 200, 300};
        kO = new String[]{"S\u1eed d\u1ee5ng item", "Xem b\u1ea3n \u0111\u1ed3", "L\u01b0u v\u00e0 tho\u00e1t", "Nh\u1ea1c: B\u1eadt"};
        kP = new String[]{"Tr\u01b0\u1eddng Hirosaki", "Khu luy\u1ec7n t\u1eadp", "L\u00e0ng Kenshin", "R\u1eebng \u0111\u00e0o Sakura", "R\u1eebng tr\u00fac Ura", "Th\u00e1c Kitajima", "C\u00e1nh \u0111\u1ed3ng Mishima", "S\u00f4ng Watamaro", "Ngh\u0129a \u0111\u1ecba Izuko", "Mi\u1ebfu Kojin", "Mi\u1ebfu Kamo", "Mi\u1ebfu Yazaka", "R\u1eebng g\u1ed7 Kouji", "R\u1eebng ch\u00f4ng Hakata", "V\u00e1ch \u0111\u00e1 Ito", "Thung l\u0169ng Taira", "Th\u00f4n Sanzu", "L\u1ed1i v\u00e0o Orochi", "Ng\u00f4i \u0111\u1ec1n Orochi"};
        kQ = new int[]{50, 60, 70, 80, 90, 70, 100, 120, 150, 180, 220, 250, 300, 320, 350, 370, 390, 420, 450};
        W = new boolean[19];
        kR = new int[]{49, 31, 98, 112, 106, 42, 75, 84, 37, 39, 80, 117, 157, 201, 208, 170, 186, 219, 161};
        kS = new int[]{35, 65, 38, 75, 98, 97, 95, 128, 142, 185, 212, 189, 189, 204, 156, 142, 96, 59, 39};
        kT = new int[]{62, 52, 17, 4, 8, 12, 14, 5, 1, 2, 3, 2, 2, 2, 2, 1, 2, 3, 1};
        kU = new int[]{17, 9, 12, 9, 10, 5, 9, 7, 12, 11, 4, 11, 8, 9, 7, 6, 11, 16, 8};
        lm = "Chuy\u1ec3n \u0111\u1ebfn";
        String[] stringArray = new String[]{"Quy S\u01b0 Ph\u1ee5", "L\u00e2m S\u01b0 Ph\u1ee5"};
        lB = new String[][]{{"..."}, {"\u0110\u00e2y l\u00e0 khu v\u1ef1c luy\u1ec7n t\u1eadp, c\u1ee9 y\u00ean t\u00e2m, n\u1ebfu b\u1ecb th\u01b0\u01a1ng ta s\u1ebd gi\u00fap \u0111\u1ee1.", "\u0110\u00f4i khi t\u1eadp luy\u1ec7n v\u1edbi c\u00e1i b\u00f9 nh\u00ecn r\u01a1m \u0111\u1eb1ng kia c\u0169ng mang l\u1ea1i nhi\u1ec1u b\u1ea5t ng\u1edd \u0111\u1ea5y!"}, {"Th\u00e1c n\u01b0\u1edbc n\u00e0y r\u1ea5t nguy hi\u1ec3m, ta khuy\u00ean ng\u01b0\u01a1i kh\u00f4ng n\u00ean xu\u1ed1ng.", "H\u00e3y quay l\u1ea1i \u0111i, n\u1ebfu c\u00f3 chuy\u1ec7n g\u00ec ta kh\u00f4ng c\u1ee9u \u0111\u01b0\u1ee3c ng\u01b0\u01a1i \u0111\u00e2u."}, {"Khu r\u1eebng tr\u01b0\u1edbc m\u1eb7t ng\u01b0\u01a1i l\u00e0 n\u01a1i s\u1ed1ng c\u1ee7a b\u1ecdn d\u01a1i qu\u1ef7, nh\u1ec7n \u0111\u1ed9c v\u00e0 nh\u1eefng s\u00e1t th\u1ee7 b\u1ecb truy n\u00e3.", "D\u00f9 ng\u01b0\u01a1i t\u00e0i gi\u1ecfi \u0111\u1ebfn \u0111\u00e2u c\u0169ng ph\u1ea3i m\u1ea5t m\u1ea1ng khi \u0111i ngang qua \u0111\u00f3.", "M\u1ed9t s\u1ed1 ng\u01b0\u1eddi c\u00f3 th\u1ec3 qua \u0111\u01b0\u1ee3c b\u1eb1ng c\u00e1ch ch\u1ea1y th\u1eadt nhanh qua.", "N\u1ebfu ng\u01b0\u01a1i t\u1ef1 tin h\u00e3y d\u00f9ng chi\u1ebfc moto kia v\u00e0 li\u1ec1u m\u1ed9t phen xem.", "Ch\u00fac ng\u01b0\u01a1i may m\u1eafn."}};
        lC = new String[][]{{"Con l\u1ea1i t\u00ecm ta c\u00f3 chuy\u1ec7n g\u00ec?", "Th\u1ea7y ch\u1ee7 nhi\u1ec7m Con l\u00e0 Takanashi, mau \u0111\u1ebfn g\u1eb7p th\u1ea7y \u0111\u1ec3 nh\u1eadn b\u00e0i t\u1eadp."}, {"Ch\u00e0o c\u1eadu b\u00e9, ta l\u00e0 Kagome Ayumi, gi\u1ea3ng vi\u00ean cao c\u1ea5p b\u1ed9 m\u00f4n Kh\u00ed.", "Kh\u00ed l\u00e0 m\u00f4n \u0111\u1ea7u ti\u00ean b\u1ea5t k\u1ef3 h\u1ecdc sinh n\u00e0o c\u0169ng ph\u1ea3i h\u1ecdc khi b\u01b0\u1edbc v\u00e0o \u0111\u00e2y.", "B\u00e0i h\u1ecdc \u0111\u00e2u ti\u00ean l\u00e0 k\u1ef9 n\u0103ng khinh c\u00f4ng. Sau khi h\u1ecdc xong con s\u1ebd phi th\u00e2n cao h\u01a1n  g\u1ea5p \u0111\u00f4i b\u00ecnh th\u01b0\u1eddng.", "B\u00e2y gi\u1edd ta ph\u1ea3i ki\u1ec3m tra s\u01a1 b\u1ed9 con. H\u00e3y ch\u1ea1y th\u1eb3ng v\u1ec1 tay tr\u00e1i \u0111\u1ebfn khu luy\u1ec7n t\u1eadp.", "\u0111\u1ebfn \u0111\u00f3 gi\u1ebft \u0111\u1ee7 10 con \u1ed1c s\u00ean \u0111\u00e1, sau \u0111\u00f3 quay l\u1ea1i \u0111\u00e2y!"}, {"Ch\u00e0o con, con \u0111\u00e3 s\u1eb5n s\u00e0ng luy\u1ec7n t\u1eadp r\u1ed3i ch\u1ee9?", "H\u00e3y \u0111\u1ebfn g\u1eb7p c\u00f4 Ayumi \u0111\u1ec3 h\u1ecdc m\u00f4n \u0111\u1ea7u ti\u00ean.", "C\u00f4 \u1ea5y \u0111\u1ee9ng \u0111\u1eb1ng kia, ch\u00fac con h\u1ecdc t\u1ed1t nh\u00e9!", "\u00e0, n\u1ebfu c\u00f4 \u1ea5y c\u00f3 h\u1ecfi g\u00ec v\u1ec1 ta, h\u00e3y n\u00f3i t\u1ed1t v\u1ec1 ta nh\u00e9, ta s\u1ebd th\u01b0\u1edfng."}, {"Ta gi\u00e0 c\u1ea3 r\u1ed3i, t\u1ef1a nh\u01b0 chi\u1ebfc l\u00e1 kh\u00f4..", "L\u1ea1i \u0111\u00e2y, ta s\u1ebd truy\u1ec1n h\u1ebft v\u00f5 c\u00f4ng cho con...", "Toyotomi,...t\u00ecnh y\u00eau c\u1ee7a ta,....em \u1edf n\u01a1i n\u00e0o"}, {"Tr\u00ecnh \u0111\u1ed9 nh\u00e0 ng\u01b0\u01a1i c\u00f2n qu\u00e1 th\u1ea5p. H\u00e3y quay l\u1ea1i \u0111\u00e2y khi \u0111\u00e3 h\u1ecdc xong khinh c\u00f4ng."}, {"Ng\u01b0\u01a1i c\u00f2n ch\u01b0a d\u00f9ng shuriken th\u00ec l\u00e0m sao h\u1ecdc ph\u00e9p thu\u1eadt \u0111\u01b0\u1ee3c."}, {"Ta s\u1ebd d\u1ea1y ng\u01b0\u01a1i d\u00f9ng shuriken n\u1ebfu ng\u01b0\u01a1i c\u00f3 \u0111\u1ee7 kh\u1ea3 n\u0103ng.", "G\u1ea7n c\u1ed5ng tr\u01b0\u1eddng c\u00f3 v\u00e0i c\u00e1i b\u00f9 nh\u00ecn r\u01a1m, h\u00e3y \u0111\u00e1nh g\u00e3y 2 c\u00e1i.", "Nhanh l\u00ean, h\u00e3y ch\u1ee9ng t\u1ecf s\u1ee9c m\u1ea1nh c\u1ee7a m\u00ecnh \u0111i."}, {"Ng\u01b0\u01a1i c\u00f2n kh\u00f4ng mau \u0111i \u0111i!"}, {"Ng\u01b0\u01a1i mu\u1ed1n h\u1ecdc thu\u1eadt \u01b0?", "Kh\u00f4ng nh\u01b0 nh\u1eefng m\u00f4n kh\u00e1c, thu\u1eadt \u0111\u00f2i h\u1ecfi ng\u01b0\u01a1i ph\u1ea3i luy\u1ec7n t\u1eadp h\u00e0ng ng\u00e0y..", "H\u01a1n th\u1ebf n\u1eefa, ng\u01b0\u01a1i ph\u1ea3i h\u1ecdc theo nh\u1eefng quy\u1ec3n b\u00ed k\u00edp.", "Ta s\u1ebd d\u1ea1y cho ng\u01b0\u01a1i m\u00f4n \u0111\u1ed9n th\u1ed5, n\u1ebfu ng\u01b0\u01a1i c\u00f3 th\u1ec3 t\u00ecm \u0111\u01b0\u1ee3c 1 quy\u1ec3n b\u00ed k\u00edp t\u1eeb b\u1ecdn qu\u1ef7 m\u1ed9t ch\u00e2n", "B\u1ecdn ch\u00fang th\u01b0\u1eddng xu\u1ea5t hi\u1ec7n \u1edf th\u00e1c n\u01b0\u1edbc, b\u00ean kia khu luy\u1ec7n t\u1eadp.", "\u0110i \u0111i, h\u00e3y ki\u00ean nh\u1eabn v\u1edbi t\u1eebng t\u00ean , ng\u01b0\u01a1i s\u1ebd t\u00ecm th\u1ea5y th\u00f4i!"}, {"Nhanh l\u00ean con, c\u00f4 b\u00e9 \u1ea5y \u0111ang \u0111\u1ee9ng ch\u1edd \u1edf \u0111\u01b0\u1eddng hoa \u0111\u00e0o \u0111\u1ea5y!", "N\u1ebfu ch\u1eadm, c\u00f4 Ayumi s\u1ebd bu\u1ed3n l\u1eafm \u0111\u00f3!"}, {"Con c\u00f3 th\u1ec3 gi\u00fap ta m\u1ed9t chuy\u1ec7n \u0111\u01b0\u1ee3c kh\u00f4ng?", "C\u00f4 Ayumi c\u00f3 nh\u1edd ta \u0111\u00f3n gi\u00fap c\u00f4 em g\u00e1i. Nh\u01b0ng \u0111\u00fang v\u00e0o l\u00fac ta ph\u1ea3i h\u1ecdp c\u00f9ng th\u1ea7y hi\u1ec7u tr\u01b0\u1edfng.", "Ta r\u1ea5t tin t\u01b0\u1edfng \u1edf con, con h\u00e3y \u0111\u1ebfn \u0111\u1ea7u \u0111\u01b0\u1eddng hoa \u0111\u00e0o, \u0111\u00f3n c\u00f4 \u1ea5y nh\u00e9!", "Nhanh l\u00ean, ta s\u1ebd th\u01b0\u1edfng cho con n\u1ebfu con l\u00e0m t\u1ed1t."}, {"\u0110\u1ec3 tr\u1edf th\u00e0nh 1 ninja gi\u1ecfi ng\u01b0\u01a1i ph\u1ea3i c\u00f3 th\u1ec3 l\u1ef1c t\u1ed1t.", "Ng\u01b0\u01a1i c\u00f3 bi\u1ebft ch\u1eef Nin l\u00e0 Nh\u1eabn kh\u00f4ng!", "Trong Nh\u1eabn th\u00ec c\u00f3 L\u1ef1c v\u00e0 T\u00e2m..", "..\u0111\u1ec3 tr\u1edf th\u00e0nh ninja th\u1ef1c th\u1ee5 th\u00ec ng\u01b0\u01a1i ph\u1ea3i c\u00f3 T\u00e2m v\u00e0 L\u1ef1c", "....m\u00e0 ta n\u00f3i, ng\u01b0\u01a1i c\u00f3 hi\u1ec3u g\u00ec kh\u00f4ng.", "H\u00e3y gi\u1ebft 20 con ong, r\u1ed3i quay v\u1ec1 g\u1eb7p ta. L\u00fac \u0111\u00f3 ta s\u1ebd gi\u00fap ng\u01b0\u01a1i."}, {"Ng\u01b0\u01a1i \u0111\u00e3 bao gi\u1edd v\u01b0\u1ee3t s\u00f4ng Watamoro ch\u01b0a? \u0110\u00f3 l\u00e0 m\u1ed9t con s\u00f4ng r\u1ed9ng v\u00e0 hung d\u1eef v\u1edbi thu\u1ef7 qu\u00e1i v\u00e0 s\u00f3ng to.", "Ta s\u1ebd gi\u00fap ng\u01b0\u01a1i 1 c\u00e1ch v\u01b0\u1ee3t s\u00f4ng r\u1ea5t d\u1ec5 d\u00e0ng, nh\u01b0ng ng\u01b0\u01a1i ph\u1ea3i l\u00e0m m\u1ed9t vi\u1ec7c...", "Trong khu r\u1eebng tr\u00fac, c\u00f3 1 con heo r\u1eebng th\u00e0nh tinh r\u1ea5t hung h\u0103ng..", "H\u00e3y gi\u1ebft n\u00f3, mang v\u1ec1 \u0111\u00e2y 1 quy\u1ec3n b\u00ed k\u00edp n\u00f3i v\u1ec1 thu\u1eadt l\u1eb7n n\u01b0\u1edbc, ta s\u1ebd h\u01b0\u1edbng d\u1eabn ng\u01b0\u01a1i luy\u1ec7n.", "\u0110i \u0111i, v\u00e0 h\u00e3y c\u1ea9n th\u1eadn t\u00ednh m\u1ea1ng nh\u00e0 ng\u01b0\u01a1i."}, {"Ng\u01b0\u01a1i \u0111\u00e3 h\u1ecdc \u0111\u01b0\u1ee3c k\u1ef9 n\u0103ng ch\u1ea1y n\u01b0\u1edbc r\u1ed3i!", "N\u1ebfu ng\u01b0\u01a1i c\u00f3 th\u1ec3 qua b\u00ean kia con s\u00f4ng,\u0111\u1ebfn khu ngh\u0129a \u0111\u1ecba, nh\u1eb7t quy\u1ec3n b\u00ed k\u00edp Ninja bi\u1ebfn h\u00ecnh..", "..ta s\u1ebd truy\u1ec1n th\u1ee5 k\u1ef9 n\u0103ng bi\u1ebfn h\u00ecnh cho ng\u01b0\u01a1i.", "H\u00e3y nh\u1edb ng\u01b0\u01a1i ph\u1ea3i \u0111\u1ed1i \u0111\u1ea7u v\u1edbi m\u1ed9t con qu\u00e1i v\u1eadt to l\u1edbn b\u00ean kia s\u00f4ng \u0111\u1ea5y!", "\u0110i \u0111i, ch\u00fac ng\u01b0\u01a1i may m\u1eafn."}, {"Con bi\u1ebft, ta \u0111ang nghi\u00ean c\u1ee9u l\u00e0m ra 1 th\u1ee9 thu\u1ed1c, \u0111\u1ec3 ch\u1eefa tr\u1ecb v\u1ebft th\u01b0\u01a1ng...", "Tuy nhi\u00ean ta thi\u1ebfu 1 lo\u1ea1i nguy\u00ean li\u1ec7u r\u1ea5t kh\u00f3 ki\u1ebfm.", "N\u1ebfu con c\u00f3 th\u1ec3 t\u00ecm gi\u00fap ta 15 c\u00e1nh d\u01a1i v\u00e0ng, ta s\u1ebd c\u00f3 \u0111\u1ec1n \u0111\u00e1p x\u1ee9ng \u0111\u00e1ng.", "B\u1ecdn d\u01a1i v\u00e0ng c\u00f3 m\u1eb7t \u1edf nh\u1eefng ng\u00f4i \u0111\u1ec1n ph\u00eda nam. B\u1ecdn ch\u00fang r\u1ea5t nhanh.", "Tuy th\u1ebf, ta v\u1eabn tin con c\u00f3 th\u1ec3 mang v\u1ec1 cho ta.", "Ch\u00fac con may m\u1eafn!"}, {"C\u00f4 Ayumi n\u00f3i cho con nghe r\u1ed3i \u00e0...", "N\u00f3i ra th\u1eadt \u0111\u00e1ng bu\u1ed3n, c\u0169ng nh\u01b0 bao tr\u01b0\u1eddng kh\u00e1c, tr\u01b0\u1eddng ta lu\u00f4n c\u00f3 nh\u1eefng h\u1ecdc sinh c\u00e1 bi\u1ec7t...", "Toniku l\u00e0 m\u1ed9t trong s\u1ed1 \u0111\u00f3, n\u00f3 \u0111\u00e3 b\u1ecf tr\u01b0\u1eddng th\u00e0nh l\u1eadp b\u0103ng nh\u00f3m...", "Hi\u1ec7n \u0111ang t\u1ee5 t\u1eadp qu\u1eady ph\u00e1 \u1edf 3 ng\u00f4i mi\u1ebfu ph\u00eda nam tr\u01b0\u1eddng...", "Con l\u00e0 m\u1ed9t h\u1ecdc sinh t\u1ed1t, h\u00e3y \u0111i t\u00ecm b\u1ecdn ch\u00fang b\u1eaft v\u1ec1 cho ta.", "L\u00e0m \u0111i, r\u1ed3i b\u00e1o c\u00e1o k\u1ebft qu\u1ea3 v\u1edbi ta nh\u00e9!"}, {"C\u00f4 b\u00e9 em g\u00e1i c\u00f4 Ayumi c\u0169ng xinh, y nh\u01b0 c\u00f4 \u1ea5y v\u1eady, con c\u00f3 th\u1ea5y th\u1ebf kh\u00f4ng!", "N\u1ebfu con c\u00f3 th\u1ec3 v\u00e0o khu r\u1eebng g\u1ed7, \u0111\u00e1nh g\u1ee5c 20 t\u00ean Qu\u1ef7 kh\u1ed1, ta s\u1ebd mai m\u1ed1i cho, haha!", "Nh\u01b0ng h\u00e3y c\u1ea9n th\u1eadn, b\u1ecdn qu\u00e1i v\u1eadt trong khu r\u1eebng \u1ea5y r\u1ea5t \u0111\u00f4ng v\u00e0 m\u1ea1nh \u0111\u1ea5y!"}, {"Hi\u1ec7n t\u1ea1i kh\u1ea3 n\u0103ng con \u0111\u00e3 r\u1ea5t kh\u00e1 r\u1ed3i, ta quy\u1ebft \u0111\u1ecbnh d\u1ea1y nh\u1eefng k\u1ef9 n\u0103ng c\u1ea5p cao cho con.", "\u0110\u1ea7u ti\u00ean l\u00e0 khinh c\u00f4ng c\u1ea5p 2.", "Tuy nhi\u00ean, con ph\u1ea3i ch\u1ee9ng t\u1ecf \u0111\u00e3 th\u00e0nh th\u1ea1o khinh c\u00f4ng c\u1ea5p 1 tr\u01b0\u1edbc...", "H\u00e3y nh\u1ea3y l\u00ean kh\u00f4ng tung, \u0111\u00e1nh \u0111\u01b0\u1ee3c 20 \u0111\u00f2n tr\u01b0\u1edbc khi ch\u1ea1m \u0111\u1ea5t!", "Kh\u00f4ng d\u1ec5 ph\u1ea3i kh\u00f4ng..."}, {"Shuriken , m\u1ed9t trong nh\u1eefng v\u0169 kh\u00ed c\u01a1 b\u1ea3n c\u1ee7a Ninja, ta s\u1ebd truy\u1ec1n cho ng\u01b0\u01a1i b\u1ed9 \u00e1m kh\u00ed m\u1edbi n\u00e0y", "V\u1eabn nh\u01b0 c\u00e1c l\u1ea7n tr\u01b0\u1edbc, ng\u01b0\u01a1i ph\u1ea3i cho ta th\u1ea5y \u0111\u00e3 \u0111\u1ee7 s\u1ee9c h\u1ecdc...", "\u0110\u1ebfn khu r\u1eebng ch\u00f4ng tre. Nh\u1ed5 g\u1ed1c 70 con qu\u1ef7 hoa \u0111\u1ecf r\u1ed3i v\u1ec1 \u0111\u00e2y, ta s\u1ebd d\u1ea1y cho.", "C\u1ea9n th\u1eadn t\u00ednh m\u1ea1ng ng\u01b0\u01a1i \u0111\u1ea5y."}, {"M\u1ea5y h\u00f4m nay ta nh\u1eadn th\u1ea5y con ch\u1ea1y c\u00f2n r\u1ea5t ch\u1eadm..", "M\u1ed9t ninja ch\u1eadm ch\u1ea1p nh\u01b0 con kh\u00f4ng th\u1ec3 l\u00e0m vi\u1ec7c l\u1edbn \u0111\u01b0\u1ee3c.", "H\u00e3y \u0111\u1ebfn v\u00e1ch \u0111\u00e1 Ito, mang v\u1ec1 cho ta 50 \u0111u\u00f4i b\u00f2 c\u1ea1p..", "Ta s\u1ebd d\u00f9ng \u0111\u1ed9c c\u1ee7a ch\u00fang, \u0111i\u1ec1u ch\u1ebf cho con 1 lo\u1ea1i thu\u1ed1c t\u0103ng l\u1ef1c...", "C\u1ea9n th\u1eadn n\u1ecdc \u0111\u1ed9c b\u1ecdn ch\u00fang nh\u00e9! H\u00e3y d\u1eabn c\u00f4 b\u00e9 Sakura \u0111i, n\u00f3 s\u1ebd gi\u00fap con nhi\u1ec1u \u0111\u1ea5y."}, {"Ta kh\u00f4ng mu\u1ed1n n\u00f3i nhi\u1ec1u. M\u1ed9t ph\u1ea7n th\u01b0\u1edfng \u0111\u1eb7t bi\u1ec7t cho ng\u01b0\u01a1i..", "..n\u1ebfu ng\u01b0\u01a1i c\u00f3 th\u1ec3 gi\u1ebft \u0111\u01b0\u1ee3c 30 con c\u00e1 \u0111\u1ecf!"}, {"\u0110\u00e2y l\u00e0 k\u1ef9 n\u0103ng cu\u1ed1i c\u00f9ng c\u1ee7a m\u1ed9t ninja... k\u1ef9 thu\u1eadt \u1ea9n th\u00e2n", "K\u1ef9 n\u0103ng n\u00e0y mu\u1ed1n h\u1ecdc c\u0169ng kh\u00f4ng kh\u00f3, t\u1ea1i m\u1ed9t thung l\u0169ng ph\u00eda nam..", "c\u00f3 m\u1ed9t quy\u1ec3n b\u00ed k\u00edp \u0111\u01b0\u1ee3c gi\u1ea5u t\u1eeb ng\u00e0n n\u0103m nay.", "H\u00e3y t\u00ecm v\u1ec1, ta s\u1ebd gi\u00fap con c\u00f3 \u0111\u01b0\u1ee3c k\u1ef9 n\u0103ng l\u1ee3i h\u1ea1i n\u00e0y."}, {"Con c\u00f2n nh\u1edb t\u00ean ph\u1ea3n \u0111\u1ed3 Toniku l\u1ea7n tr\u01b0\u1edbc kh\u00f4ng!", "Sau khi b\u1ecb con \u0111\u00e1nh, h\u1eafn \u0111\u00e3 b\u1ecf ch\u1ea1y \u0111\u1ebfn ng\u00f4i mi\u1ebfu ph\u00eda \u0111\u00f4ng b\u1eafc.", "N\u00f3 v\u1eeba b\u1eaft m\u1ea5t c\u00f4 b\u00e9 Sakura \u0111i r\u1ed3i.", "Ta \u0111\u00e3 sai t\u1ea5t c\u1ea3 \u0111\u1ec7 t\u1eed \u0111i t\u00ecm n\u00f3, c\u1ee9u c\u00f4 b\u00e9 v\u1ec1!", "Con c\u0169ng ph\u1ea3i th\u1ebf nh\u00e9! C\u00f4 b\u00e9 \u1ea5y \u0111ang ch\u1edd con \u0111\u1ea5y!"}};
        lD = new String[][]{{"Th\u1ec3 l\u1ef1c con r\u1ea5t t\u1ed1t, ta s\u1ebd d\u1ea1y khinh c\u00f4ng cho con...", "H\u00e3y t\u1eadp trung kh\u00ed huy\u1ebft, h\u00edt th\u1edf nh\u1eb9, ng\u01b0\u1eddi con nh\u1eb9 d\u1ea7n, nh\u1eb9 d\u1ea7n t\u1ef1a chi\u1ebfc l\u00e1 kh\u00f4...", "Ch\u00fac m\u1eebng con! H\u00e3y th\u1eed nh\u1ea3y l\u00ean xem k\u1ebft qu\u1ea3 th\u1ebf n\u00e0o.", "\u00e0, su\u00fdt n\u1eefa ta qu\u00ean, l\u00fac n\u00e3y th\u1ea7y Hiroyaki tr\u00ean t\u1ea7ng 1 g\u1ecdi con \u0111\u1ea5y."}, {"T\u1ed1t. \u0110\u00e2y l\u00e0 c\u00e1ch ph\u00f3ng shuriken, h\u00e3y nh\u1edb k\u1ef9:", "B\u1ea5m ph\u00edm 0 \u0111\u1ec3 chuy\u1ec3n sang ph\u00f3ng shuriken, b\u1ea5m l\u1ea7n n\u1eefa \u0111\u1ec3 \u0111\u00e1nh ki\u1ebfm.", "N\u1ebfu ch\u0103m ch\u1ec9 t\u1eadp luy\u1ec7n, phi ti\u00eau s\u1ebd l\u00e0 v\u0169 kh\u00ed l\u1ee3i h\u1ea1i nh\u1ea5t c\u1ee7a ng\u01b0\u01a1i.", "Th\u1ea7y Honda c\u00f3 vi\u1ec7c c\u1ea7n g\u1eb7p ng\u01b0\u01a1i. Nhanh l\u1ea1i \u0111\u00f3 \u0111i!"}, {"Gi\u1ecfi l\u1eafm, \u0111\u00fang l\u00e0 quy\u1ec3n b\u00ed k\u00edp n\u00e0y.", "C\u1ee9 luy\u1ec7n t\u1eadp theo quy\u1ec3n b\u00ed k\u00edp n\u00e0y: h\u00e3y \u0111\u1ebfn khu v\u1ef1c c\u00f3 \u0111\u1ea5t..", "Ng\u1ed3i th\u1ea5p xu\u1ed1ng v\u00e0 t\u1eadp trung, ng\u01b0\u01a1i s\u1ebd \u0111\u1ed9n th\u1ed5 \u0111\u01b0\u1ee3c.", "H\u00e3y luy\u1ec7n t\u1eadp \u0111i, ch\u00fa \u00fd MP ng\u01b0\u01a1i s\u1ebd b\u1ecb gi\u1ea3m khi \u0111\u1ed9n th\u1ed5 \u0111\u00f3.", "C\u00f2n chuy\u1ec7n n\u00e0y n\u1eefa, v\u1eeba n\u00e3y ta th\u1ea5y th\u1ea7y ch\u1ee7 nhi\u1ec7m ng\u01b0\u01a1i c\u00f3 v\u1ebb m\u1ec7t. Ng\u01b0\u01a1i h\u00e3y nhanh gh\u00e9 th\u0103m!"}, {"Ng\u01b0\u01a1i gi\u1ecfi l\u1eafm, ta s\u1ebd \u0111\u00e3 th\u00f4ng kinh m\u1ea1ch cho...", "\u00fam ba la !! soda cacao cola!", "Ng\u01b0\u01a1i c\u00f3 c\u1ea3m nh\u1eadn \u0111\u01b0\u1ee3c lu\u1ed3n ch\u00e2n kh\u00ed trong ng\u01b0\u1eddi kh\u00f4ng", "\u0110i \u0111i, h\u00e3y d\u00f9ng s\u1ee9c m\u1ea1nh n\u00e0y \u0111\u1ec3 b\u1ea3o v\u1ec7 d\u00e2n l\u00e0ng!"}, {"Quy\u1ec3n b\u00ed k\u00edp \u0111\u00e2y r\u1ed3i, kh\u00e1 l\u1eafm c\u1eadu b\u00e9.", "Ng\u01b0\u01a1i \u0111\u00e3 c\u00f3 th\u1ec3 l\u1eb7n n\u01b0\u1edbc \u0111\u01b0\u1ee3c r\u1ed3i. H\u00e3y nh\u1edb, tuy l\u1eb7n \u0111\u01b0\u1ee3c nh\u01b0ng thanh tr\u00fac d\u00f9ng \u0111\u1ec3 th\u1edf r\u1ea5t ng\u1eafn...", "V\u00ec th\u1ebf, \u0111\u1eebng l\u1eb7n s\u00e2u qu\u00e1 \u0111\u00f3.", "Th\u1ea7y Hiroyaki t\u00ecm ng\u01b0\u01a1i. H\u00e3y mau \u0111\u1ebfn g\u1eb7p \u0111i."}, {"Ng\u01b0\u01a1i c\u00f3 th\u1ec3 v\u01b0\u1ee3t qua con s\u00f4ng \u1ea5y \u01b0?", "Ta s\u1ebd gi\u00fap ng\u01b0\u01a1i g\u1ecdi \u0111\u01b0\u1ee3c s\u1ee9c m\u1ea1nh c\u1ee7a d\u00f2ng t\u1ed9c m\u00ecnh: X\u00edch Mao", "B\u1ea5t k\u1ef3 khi n\u00e0o, h\u00e3y \u1ea5n ph\u00edm s\u1ed1 7, ng\u01b0\u01a1i s\u1ebd c\u00f3 \u0111\u01b0\u1ee3c s\u1ee9c m\u1ea1nh t\u1eeb g\u1ed1c c\u1ed9i c\u1ee7a d\u00f2ng t\u1ed9c m\u00ecnh", "h\u00e3y th\u1eed xem, nh\u01b0ng ng\u01b0\u01a1i c\u1ea7n c\u1ea9n th\u1eadn, v\u00ec thi tri\u1ec3n thu\u1eadt n\u00e0y,ng\u01b0\u01a1i s\u1ebd ch\u00f3ng m\u1ec7t l\u1eafm \u0111\u1ea5y."}, {"C\u1ea3m \u01a1n con nhi\u1ec1u l\u1eafm. \u0110\u00e2y l\u00e0 ph\u1ea7n th\u01b0\u1edfng cho con, 5000$", "Tuy kh\u00f4ng bao nhi\u00eau nh\u01b0ng con c\u00f3 th\u1ec3 d\u00f9ng n\u00f3 \u0111\u1ec3 mua HP, MP.", "\u00c0, tr\u01b0\u1eddng ta \u0111ang c\u00f3 vi\u1ec7c l\u1edbn \u0111\u1ea5y!", "Con h\u00e3y g\u1eb7p th\u1ea7y ch\u1ee7 nhi\u1ec7m \u0111\u1ec3 h\u1ecfi xem c\u00f3 gi\u00fap \u0111\u01b0\u1ee3c g\u00ec hay kh\u00f4ng!"}, {"H\u1eafn b\u1ecf tr\u1ed1n tho\u00e1t r\u1ed3i \u01b0?", "Ta th\u1eadt \u0111au l\u00f2ng khi c\u00f3 m\u1ed9t \u0111\u1ee9a h\u1ecdc tr\u00f2 nh\u01b0 n\u00f3!", "K\u1ec3 t\u1eeb nay, ta cho ph\u00e9p con t\u1ea5n c\u00f4ng n\u00f3, kh\u00f4ng n\u01b0\u01a1ng tay, b\u1ea5t k\u1ef3 khi n\u00e0o g\u1eb7p!"}, {"Ta bi\u1ebft ch\u1eafc ch\u1eafn th\u1ebf n\u00e0o con c\u0169ng gi\u1ebft \u0111\u01b0\u1ee3c b\u1ecdn qu\u1ef7 kh\u1ed1 hung h\u0103ng \u0111\u00f3!", "L\u00fac n\u00e3y, ta \u0111\u00e3 n\u00f3i chuy\u1ec7n v\u1edbi c\u00f4 Ayumi r\u1ed3i.", "H\u00e3y l\u1ea1i b\u1eaft chuy\u1ec7n v\u1edbi c\u00f4 b\u00e9 \u1ea5y \u0111i!"}, {"T\u1ed1t l\u1eafm. Con \u0111\u00e3 ch\u1ee9ng t\u1ecf \u0111\u01b0\u1ee3c m\u00ecnh.", "\u0110\u00e2y l\u00e0 k\u1ef9 n\u0103ng khinh c\u00f4ng c\u1ea5p 2. Con \u0111\u00e3 c\u00f3 th\u1ec3 nh\u1ea3y cao h\u01a1n tr\u01b0\u1edbc r\u1ed3i.", "Ti\u1ebfp theo, h\u00e3y g\u1eb7p th\u1ea7y Hiroyaki, \u00f4ng ta s\u1ebd d\u1ea1y con nh\u1eefng k\u1ef9 n\u0103ng m\u1edbi!"}, {"T\u1ed1t. \u0110\u00e2y l\u00e0 k\u1ef9 n\u0103ng shuriken c\u1ea5p 2.", "S\u1ee9c t\u1ea5n c\u00f4ng t\u1eeb shuriken c\u1ee7a con \u0111\u00e3 t\u0103ng l\u00ean r\u1ea5t nhi\u1ec1u!", "Con ph\u1ea3i si\u00eang n\u0103ng t\u1eadp luy\u1ec7n m\u1edbi c\u00f3 th\u1ec3 gi\u1eef \u0111\u01b0\u1ee3c s\u1ee9c m\u1ea1nh n\u00e0y. Nh\u1edb \u0111\u1ea5y!"}, {"Kh\u00e1 l\u1eafm. \u0110\u00e2y l\u00e0 ph\u01b0\u01a1ng thu\u1ed1c ch\u1ea1y nhanh cho con!", "H\u00e3y ch\u1ea1y 1 \u0111o\u1ea1n ng\u1eafn, sau \u0111\u00f3 t\u1ed1c \u0111\u1ed9 c\u1ee7a con s\u1ebd t\u0103ng nhanh..", "H\u00e3y th\u1eed xem n\u00e0o!"}, {"Gi\u1ecfi. K\u1ec3 t\u1eeb nay con s\u1ebd kh\u00f4ng bao gi\u1edd b\u1ecb ch\u00ecm d\u01b0\u1edbi n\u01b0\u1edbc n\u1eefa!", "..\u0111\u00e2y l\u00e0 k\u1ef9 thu\u1eadt \u0111\u1ee9ng n\u01b0\u1edbc b\u00ed truy\u1ec1n c\u1ee7a ninja. Ta ch\u1ec9 d\u1ea1y cho m\u00ecnh con \u0111\u1ea5y!"}, {"Ng\u01b0\u01a1i c\u00f3 th\u1ec3 t\u00ecm \u0111\u01b0\u1ee3c n\u00f3 \u01b0?", "T\u1ed1t l\u1eafm, t\u1eeb nay con c\u00f3 th\u1ec3 s\u1eed d\u1ee5ng n\u00f3 r\u1ed3i \u0111\u1ea5y.", "H\u00e3y nh\u1ea5n ph\u00edm s\u1ed1 9 \u0111\u1ec3 c\u00f3 th\u1ec3 \u1ea9n th\u00e2n. K\u1ef9 n\u0103ng n\u00e0y r\u1ea5t l\u1ee3i h\u1ea1i..", "tuy nhi\u00ean n\u00f3 s\u1ebd ti\u00eau th\u1ee5 r\u1ea5t nhi\u1ec1u MP c\u1ee7a con \u0111\u1ea5y!"}, {"C\u00e1c con \u0111\u1ec1u b\u00ecnh an v\u00f4 s\u1ef1, r\u1ea5t t\u1ed1t!", "Nh\u1eefng g\u00ec \u0111\u00e3 l\u00e0 qu\u00e1 kh\u1ee9 th\u00ec ta cho qua, nh\u01b0ng h\u00e3y nh\u1edb v\u00e0 kh\u00f4ng t\u00e1i ph\u1ea1m", "N\u00e0o, ta cho c\u00e1c con ngh\u1ec9, \u0111i\u1ec7n tho\u1ea1i c\u0169ng c\u00f3 l\u00fac ph\u1ea3i ngh\u00ec ng\u01a1i ch\u1ee9, hehe. Nay , ta ch\u00fac m\u1eebng c\u00e1c con \u0111\u00e3 ho\u00e0n th\u00e0nh kh\u00f3a h\u1ecdc ninja s\u01a1 c\u1ea5p: c\u00e1c con kh\u00f4ng c\u00f2n l\u00e0 Chunin n\u1eefa !", "Nay ta tuy\u00ean b\u1ed1 th\u0103ng c\u1ea5p cho c\u00e1c con l\u00e0 Genin !"}};
        String[][] stringArrayArray = new String[][]{{"Ta \u0111ang b\u1eadn."}, {"Ng\u01b0\u01a1i \u0111\u00e3 h\u1ecdc \u0111\u01b0\u1ee3c chi\u00eau shuriken cu\u1ed1i c\u00f9ng, ta ko c\u00f2n g\u00ec d\u1ea1y ng\u01b0\u01a1i n\u1eefa. \u0110i \u0111i."}};
        lE = new String[][]{{"Nhanh l\u00ean, h\u00e3y sang khu luy\u1ec7n t\u1eadp v\u00e0 gi\u1ebft 10 con \u1ed1c s\u00ean."}, {"H\u00e3y \u0111\u00e1nh s\u1eadp 2 b\u00f9 nh\u00ecn r\u01a1m g\u1ea7n c\u1ed5ng tr\u01b0\u1eddng. Nhanh l\u00ean!"}, {"B\u1ecdn qu\u1ef7 m\u1ed9t ch\u00e2n c\u00f3 m\u1eb7t \u1edf th\u00e1c n\u01b0\u1edbc, b\u00ean kia khu luy\u1ec7n t\u1eadp, mau gi\u1ebft ch\u00fang \u0111\u1ec3 t\u00ecm quy\u1ec3n b\u00ed k\u00edp!"}, {"H\u00e3y tr\u1edf l\u1ea1i khi ng\u01b0\u01a1i \u0111\u00e3 gi\u1ebft \u0111\u1ee7 20 con ong."}, {"Con heo r\u1eebng r\u1ea5t nguy hi\u1ec3m. Nh\u01b0ng ta tin ng\u01b0\u01a1i c\u00f3 th\u1ec3 gi\u1ebft \u0111\u01b0\u1ee3c n\u00f3 v\u00e0 c\u1ea7m v\u1ec1 cho ta quy\u1ec3n b\u00ed k\u00edp. \u0110i \u0111i!"}, {"H\u00e3y \u0111\u1ebfn khu ngh\u0129a \u0111\u1ecba b\u00ean kia s\u00f4ng! Mang v\u1ec1 quy\u1ec3n b\u00ed k\u00edp. Nhanh l\u00ean!"}, {"Nhanh l\u00ean, h\u00e3y gi\u1ebft 15 con d\u01a1i v\u00e0ng \u1edf nh\u1eefng ng\u00f4i mi\u1ebfu ph\u00eda nam!"}, {"H\u00e3y t\u00ecm t\u00ean Toniku, h\u1eafn tr\u1ed1n \u1edf nh\u1eefng ng\u00f4i mi\u1ebfu ph\u00eda nam.", "T\u00ecm g\u1eb7p h\u1eafn quay l\u1ea1i b\u00e1o c\u00e1o v\u1edbi ta nh\u00e9!"}, {"H\u00e3y gi\u1ebft 20 con qu\u1ef7 kh\u1ed1 \u1edf khu r\u1eebng g\u1ed7!", "Xong vi\u1ec7c ta s\u1ebd gi\u00fap con c\u01b0a con b\u00e9 em g\u00e1i c\u00f4 gi\u00e1o Ayumi!"}, {"\u0110\u1ec3 ta d\u1ea1y kinh c\u00f4ng c\u1ea5p 2 cho con, con ph\u1ea3i ch\u1ee9ng t\u1ecf \u0111\u01b0\u1ee3c kh\u1ea3 n\u0103ng hi\u1ec7n t\u1ea1i.", "H\u00e3y nh\u1ea3y l\u00ean cao v\u00e0 ch\u00e9m \u0111\u01b0\u1ee3c \u00edt nh\u1ea5t 20 ph\u00e1t khi ch\u01b0a ch\u1ea1m \u0111\u1ea5t!", "Kh\u00e1 kh\u00f3, nh\u01b0ng ta tin con s\u1ebd kh\u1eb3ng \u0111\u1ecbnh \u0111\u01b0\u1ee3c m\u00ecnh!"}, {"H\u00e3y \u0111\u1ebfn r\u1eebng ch\u00f4ng tre. Gi\u1ebft 70 qu\u1ef7 hoa \u0111\u1ecf r\u1ed3i v\u1ec1 \u0111\u00e2y, ta cho ng\u01b0\u01a1i shuriken c\u1ea5p 2!"}, {"Con h\u00e3y \u0111\u1ebfn v\u00e1ch \u0111\u00e1 Ito, gi\u1ebft 50 con b\u00f2 c\u1ea1p, mang \u0111u\u00f4i v\u1ec1 cho ta!"}, {"Ta kh\u00f4ng mu\u1ed1n n\u00f3i nhi\u1ec1u. M\u1ed9t ph\u1ea7n th\u01b0\u1edfng \u0111\u1eb7t bi\u1ec7t cho ng\u01b0\u01a1i..", "..n\u1ebfu ng\u01b0\u01a1i c\u00f3 th\u1ec3 gi\u1ebft \u0111\u01b0\u1ee3c 30 con c\u00e1 \u0111\u1ecf!"}, {"\u0110\u1ec3 h\u1ecdc k\u1ef9 n\u0103ng \u1ea9n th\u00e2n, h\u00e3y \u0111i t\u00ecm 1 quy\u1ec3n b\u00ed k\u00edp...", "C\u1ea5t gi\u1ea5u t\u1ea1i 1 thung l\u0169ng ph\u00eda \u0111\u00f4ng."}, {"Nhanh l\u00ean, c\u00f4 b\u00e9 \u1ea5y \u0111\u00e3 b\u1ecb b\u1eaft t\u1eeb m\u1ea5y h\u00f4m r\u1ed3i!"}};
        lF = new String[][]{{"Ta \u0111ang b\u1eadn, Con h\u00e3y \u0111i t\u1eadp luy\u1ec7n \u0111i."}, {"C\u00e1c ng\u01b0\u01a1i ph\u1ea3i c\u1ed1 g\u1eafng t\u1eadp luy\u1ec7n \u0111\u1ec3 tr\u1edf th\u00e0nh nh\u1eefng Ninja t\u1ed1t"}, {"H\u1eb1ng ng\u00e0y ng\u01b0\u01a1i ph\u1ea3i si\u00eang n\u0103ng t\u1eadp th\u1ec3 l\u1ef1c, \u0111\u00f3 l\u00e0 n\u1ec1n t\u1ea3ng cho m\u1ecdi ho\u1ea1t \u0111\u1ed9ng."}, {"C\u00f4 Ayumi xinh th\u1eadt. Con c\u00f3 th\u1ea5y th\u1ebf kh\u00f4ng?"}, {"Ng\u01b0\u01a1i bi\u1ebft kh\u00f4ng....\u00e0..\u00e0...m\u00e0 ta \u0111\u1ecbnh n\u00f3i g\u00ec v\u1edbi ng\u01b0\u01a1i nh\u1ec9."}, {"Ta kh\u00f4ng c\u00f2n g\u00ec \u0111\u1ec3 d\u1ea1y con n\u1eefa...", "Nh\u01b0ng con ph\u1ea3i c\u1ed1 g\u1eafng t\u1eadp luy\u1ec7n h\u00e0ng ng\u00e0y. Bi\u1ebft kh\u00f4ng!"}, {"Con c\u1ea7n ph\u1ea3i \u0111\u1ec3 m\u1eaft t\u1edbi c\u00f4 b\u00e9 Sakura nh\u00e9..", "Con b\u00e9 \u1ea5y qu\u1eady l\u1eafm \u0111\u1ea5y!"}, {"D\u00f9 sau n\u00e0y c\u00f3 \u0111i \u0111\u00e2u, ng\u01b0\u01a1i c\u0169ng ph\u1ea3i tr\u1edf th\u00e0nh 1 ninja t\u1ed1t nh\u00e9!"}, {"Ta kh\u00f4ng c\u00f2n g\u00ec \u0111\u1ec3 d\u1ea1y con n\u1eefa!", "H\u00e3y r\u00e8n luy\u1ec7n s\u1ee9c kh\u1ecfe h\u00e0ng ng\u00e0y, bi\u1ebft kh\u00f4ng!"}, {"..\u00e0.. \u1eeb.. ng\u01b0\u01a1i mu\u1ed1n h\u1ecdc ph\u00e9p \u00e0..", ".. \u0111\u1ec3 ta ngh\u0129 ra th\u00eam v\u00e0i ph\u00e9p m\u1edbi..", "..tr\u01b0\u1edbc m\u1eaft h\u00e3y h\u1ecdc nh\u1eefng k\u1ef9 n\u0103ng c\u0169 \u0111\u00e3 nh\u00e9..."}};
        (new String[1])[0] = "Ng\u01b0\u01a1i kh\u00f4ng c\u00f3 \u0111\u1ee7 v\u00e0ng.";
        lG = new String[]{"H\u00e3y ho\u00e0n th\u00e0nh nhi\u1ec7m v\u1ee5 \u0111\u01b0\u1ee3c giao r\u1ed3i quay l\u1ea1i g\u1eb7p ta!"};
        lH = new int[]{1, 7, 100, 100, 6, 100, 100, 13, 100, 15, 100, 100, 18, 17, 10, 100, 100, 100};
        int[] nArray68 = new int[18];
        nArray68[0] = 10;
        nArray68[1] = 2;
        nArray68[4] = 20;
        nArray68[7] = 15;
        nArray68[9] = 20;
        nArray68[12] = 70;
        nArray68[13] = 50;
        nArray68[14] = 30;
        lI = nArray68;
        lJ = new int[]{7, 6, 6, 5, 2, 4};
        lK = -1;
        lP = "sms://8722";
        lQ = new String[]{"1. Tiep tuc", "2. Kich hoat", "3. Nhap ma kich hoat"};
        lR = new String[]{"1. Kich hoat", "2. Nhap ma kich hoat"};
        lU = Font.getFont((int)0, (int)0, (int)0);
        lV = Font.getFont((int)0, (int)1, (int)0);
        lW = lU.getHeight() + 6;
        ma = new int[9];
        mb = 10;
        n = 0;
        while (n < 9) {
            a.ma[n] = (n << 1) + 1;
            int n4 = n++;
            ma[n4] = ma[n4] + aY;
        }
    }

    public static void a() {
        int[] nArray = new int[500];
        int n = 0;
        if (gP > 0L) {
            gO += (int)((System.currentTimeMillis() - gP) / 1000L);
        }
        gP = System.currentTimeMillis();
        ++n;
        nArray[0] = gO;
        ++n;
        nArray[1] = v;
        ++n;
        nArray[2] = w;
        ++n;
        nArray[3] = eH ? 1 : 0;
        ++n;
        nArray[4] = dg;
        ++n;
        nArray[5] = dz ? 1 : 0;
        ++n;
        nArray[6] = eI ? 1 : 0;
        ++n;
        nArray[7] = eJ ? 1 : 0;
        ++n;
        nArray[8] = eK ? 1 : 0;
        ++n;
        nArray[9] = gn ? 1 : 0;
        ++n;
        nArray[10] = go;
        ++n;
        nArray[11] = gp;
        ++n;
        nArray[12] = im;
        ++n;
        nArray[13] = x;
        ++n;
        nArray[14] = eZ ? 1 : 0;
        int n2 = 0;
        while (n2 < W.length) {
            nArray[n++] = W[n2] ? 1 : 0;
            ++n2;
        }
        n2 = 0;
        while (n2 < V.length) {
            nArray[n++] = V[n2];
            ++n2;
        }
        nArray[n++] = eL ? 1 : 0;
        nArray[n++] = eM ? 1 : 0;
        nArray[n++] = eN ? 1 : 0;
        nArray[n++] = eO ? 1 : 0;
        nArray[n++] = eP ? 1 : 0;
        nArray[n++] = eQ ? 1 : 0;
        nArray[n++] = eR ? 1 : 0;
        nArray[n++] = eS ? 1 : 0;
        nArray[n++] = eT ? 1 : 0;
        nArray[n++] = eU ? 1 : 0;
        nArray[n++] = eV ? 1 : 0;
        nArray[n++] = eW ? 1 : 0;
        nArray[n++] = eX ? 1 : 0;
        nArray[n++] = eY ? 1 : 0;
        nArray[n++] = fa ? 1 : 0;
        nArray[n++] = fb ? 1 : 0;
        nArray[n++] = fc ? 1 : 0;
        nArray[n++] = fd ? 1 : 0;
        nArray[n++] = fe ? 1 : 0;
        nArray[n++] = ff;
        nArray[n++] = fg;
        nArray[n++] = fh;
        nArray[n++] = fi;
        nArray[n++] = y;
        nArray[n++] = z;
        nArray[n++] = A;
        nArray[n++] = B;
        nArray[n++] = fj;
        nArray[n++] = fk;
        nArray[n++] = fl;
        nArray[n++] = fm;
        nArray[n++] = fn;
        nArray[n++] = fo;
        nArray[n++] = fp;
        nArray[n++] = C;
        nArray[n++] = D;
        nArray[n++] = fq;
        nArray[n++] = E;
        nArray[n++] = F;
        nArray[n++] = G;
        nArray[n++] = H;
        nArray[n++] = I;
        nArray[n++] = J;
        nArray[n++] = K;
        nArray[n++] = L;
        nArray[n++] = fr;
        nArray[n++] = fs;
        nArray[n++] = ft;
        nArray[n++] = fv;
        nArray[n++] = fw;
        nArray[n++] = M;
        nArray[n++] = N;
        nArray[n++] = fx ? 1 : 0;
        nArray[n++] = fy ? 1 : 0;
        nArray[n++] = fz ? 1 : 0;
        nArray[n++] = fA ? 1 : 0;
        nArray[n++] = O;
        nArray[n++] = fB;
        nArray[n++] = fC;
        nArray[n++] = fD ? 1 : 0;
        nArray[n++] = fF;
        nArray[n++] = fG;
        nArray[n++] = fH;
        nArray[n++] = ij;
        nArray[n++] = ik;
        nArray[n++] = fI;
        nArray[n++] = fJ;
        nArray[n++] = fK;
        nArray[n++] = fL;
        nArray[n++] = fM;
        nArray[n++] = fN;
        nArray[n++] = fO;
        nArray[n++] = fP;
        nArray[n++] = fQ;
        nArray[n++] = fR;
        nArray[n++] = fS;
        nArray[n++] = fT;
        nArray[n++] = fU;
        nArray[n++] = ih ? 1 : 0;
        nArray[n++] = ig;
        nArray[n++] = ii;
        nArray[n++] = ge;
        nArray[n++] = gf;
        nArray[n++] = gg;
        nArray[n++] = gh;
        nArray[n++] = gi;
        nArray[n++] = gj;
        nArray[n++] = gk;
        nArray[n++] = gl;
        nArray[n++] = gm;
        nArray[n++] = gq;
        nArray[n++] = gr;
        nArray[n++] = gs;
        nArray[n++] = gt;
        nArray[n++] = gu;
        nArray[n++] = gv;
        nArray[n++] = gw;
        nArray[n++] = gx;
        nArray[n++] = gy;
        nArray[n++] = gz;
        nArray[n++] = gA;
        nArray[n++] = gB;
        nArray[n++] = gC;
        nArray[n++] = gD;
        nArray[n++] = gE;
        nArray[n++] = gF;
        nArray[n++] = gG;
        nArray[n++] = gH;
        nArray[n++] = gI;
        nArray[n++] = gJ;
        nArray[n++] = gK;
        nArray[n++] = gL;
        nArray[n++] = gM;
        nArray[n++] = hc;
        nArray[n++] = hj;
        nArray[n++] = hp;
        nArray[n++] = hP;
        nArray[n++] = hu;
        nArray[n++] = il;
        if (il > 0) {
            n2 = 0;
            while (n2 < ir.length) {
                nArray[n++] = ir[n2];
                ++n2;
            }
            n2 = 0;
            while (n2 < ip.length) {
                nArray[n++] = ip[n2];
                ++n2;
            }
            n2 = 0;
            while (n2 < iq.length) {
                nArray[n++] = iq[n2];
                ++n2;
            }
            n2 = 0;
            while (n2 < is.length) {
                nArray[n++] = is[n2];
                ++n2;
            }
            n2 = 0;
            while (n2 < in.length) {
                nArray[n++] = in[n2];
                ++n2;
            }
            n2 = 0;
            while (n2 < io.length) {
                nArray[n++] = io[n2];
                ++n2;
            }
        }
        nArray[n++] = it;
        if (it > 0) {
            nArray[n++] = iu;
            if (ix != null) {
                n2 = 0;
                while (n2 < ix.length) {
                    nArray[n++] = ix[n2];
                    ++n2;
                }
            }
            if (iB != null) {
                n2 = 0;
                while (n2 < iB.length) {
                    nArray[n++] = iB[n2];
                    ++n2;
                }
            }
            if (iA != null) {
                n2 = 0;
                while (n2 < iA.length) {
                    nArray[n++] = iA[n2];
                    ++n2;
                }
            }
            if (iy != null) {
                n2 = 0;
                while (n2 < iy.length) {
                    nArray[n++] = iy[n2];
                    ++n2;
                }
            }
            if (iz != null) {
                n2 = 0;
                while (n2 < iz.length) {
                    nArray[n++] = iz[n2];
                    ++n2;
                }
            }
            if (iv != null) {
                n2 = 0;
                while (n2 < iv.length) {
                    nArray[n++] = iv[n2];
                    ++n2;
                }
            }
            if (iw != null) {
                n2 = 0;
                while (n2 < iw.length) {
                    nArray[n++] = iw[n2];
                    ++n2;
                }
            }
        }
        int[] nArray2 = nArray;
        int n3 = nArray.length;
        byte[] byArray = new byte[n3 << 2];
        int n4 = 0;
        while (n4 < n3) {
            byArray[n4 << 2] = nArray2[n4] >> 24;
            byArray[(n4 << 2) + 1] = (byte)(nArray2[n4] >>> 16);
            byArray[(n4 << 2) + 2] = (byte)(nArray2[n4] >>> 8);
            byArray[(n4 << 2) + 3] = (byte)nArray2[n4];
            ++n4;
        }
        Midlet.a("ninjasave", byArray);
        Midlet.a(true);
    }

    public static void b() {
        Object object = a.a(Midlet.b("ninjasave"));
        int n = 0;
        ++n;
        gO = object[0];
        ++n;
        v = object[1];
        ++n;
        w = object[2];
        ++n;
        eH = object[3] == 1;
        ++n;
        dg = object[4];
        ++n;
        dz = object[5] == 1;
        ++n;
        eI = object[6] == 1;
        ++n;
        eJ = object[7] == 1;
        ++n;
        eK = object[8] == 1;
        ++n;
        gn = object[9] == 1;
        ++n;
        go = object[10];
        ++n;
        gp = object[11];
        ++n;
        im = object[12];
        ++n;
        x = object[13];
        ++n;
        eZ = object[14] == 1;
        int n2 = 0;
        while (n2 < W.length) {
            a.W[n2] = object[n++] == 1;
            ++n2;
        }
        n2 = 0;
        while (n2 < V.length) {
            a.V[n2] = object[n++];
            ++n2;
        }
        eL = object[n++] == 1;
        eM = object[n++] == 1;
        eN = object[n++] == 1;
        eO = object[n++] == 1;
        eP = object[n++] == 1;
        eQ = object[n++] == 1;
        eR = object[n++] == 1;
        eS = object[n++] == 1;
        eT = object[n++] == 1;
        eU = object[n++] == 1;
        eV = object[n++] == 1;
        eW = object[n++] == 1;
        eX = object[n++] == 1;
        eY = object[n++] == 1;
        fa = object[n++] == 1;
        fb = object[n++] == 1;
        fc = object[n++] == 1;
        fd = object[n++] == 1;
        fe = object[n++] == 1;
        ff = object[n++];
        fg = object[n++];
        fh = object[n++];
        fi = object[n++];
        y = object[n++];
        z = object[n++];
        A = object[n++];
        B = object[n++];
        fj = object[n++];
        fk = object[n++];
        fl = object[n++];
        fm = object[n++];
        fn = object[n++];
        fo = object[n++];
        fp = object[n++];
        C = object[n++];
        D = object[n++];
        fq = object[n++];
        E = object[n++];
        F = object[n++];
        G = object[n++];
        H = object[n++];
        I = object[n++];
        J = object[n++];
        K = object[n++];
        L = object[n++];
        fr = object[n++];
        fs = object[n++];
        ft = object[n++];
        fv = object[n++];
        fw = object[n++];
        M = object[n++];
        N = object[n++];
        fx = object[n++] == 1;
        fy = object[n++] == 1;
        fz = object[n++] == 1;
        fA = object[n++] == 1;
        O = object[n++];
        fB = object[n++];
        fC = object[n++];
        fD = object[n++] == 1;
        fF = object[n++];
        fG = object[n++];
        fH = object[n++];
        ij = object[n++];
        ik = object[n++];
        fI = object[n++];
        fJ = object[n++];
        fK = object[n++];
        fL = object[n++];
        fM = object[n++];
        fN = object[n++];
        fO = object[n++];
        fP = object[n++];
        fQ = object[n++];
        fR = object[n++];
        fS = object[n++];
        fT = object[n++];
        fU = object[n++];
        ih = object[n++] == 1;
        ig = object[n++];
        ii = object[n++];
        ge = object[n++];
        gf = object[n++];
        gg = object[n++];
        gh = object[n++];
        gi = object[n++];
        gj = object[n++];
        gk = object[n++];
        gl = object[n++];
        gm = object[n++];
        gq = object[n++];
        gr = object[n++];
        gs = object[n++];
        gt = object[n++];
        gu = object[n++];
        gv = object[n++];
        gw = object[n++];
        gx = object[n++];
        gy = object[n++];
        gz = object[n++];
        gA = object[n++];
        gB = object[n++];
        gC = object[n++];
        gD = object[n++];
        gE = object[n++];
        gF = object[n++];
        gG = object[n++];
        gH = object[n++];
        gI = object[n++];
        gJ = object[n++];
        gK = object[n++];
        gL = object[n++];
        gM = object[n++];
        if ((hc = object[n++]) <= 0) {
            hc = hd;
        }
        hj = object[n++];
        hp = object[n++];
        hP = object[n++];
        hu = object[n++];
        if ((n2 = object[n++]) > 0) {
            try {
                n2 = 0;
                while (n2 < ir.length) {
                    ++n;
                    ++n2;
                }
                n2 = 0;
                while (n2 < ip.length) {
                    ++n;
                    ++n2;
                }
                n2 = 0;
                while (n2 < iq.length) {
                    ++n;
                    ++n2;
                }
                n2 = 0;
                while (n2 < is.length) {
                    ++n;
                    ++n2;
                }
                n2 = 0;
                while (n2 < in.length) {
                    ++n;
                    ++n2;
                }
                n2 = 0;
                while (n2 < io.length) {
                    ++n;
                    ++n2;
                }
            }
            catch (Exception exception) {}
        }
        if ((it = object[n++]) > 0) {
            iu = object[n++];
            try {
                n2 = 0;
                while (n2 < ix.length) {
                    a.ix[n2] = object[n++];
                    ++n2;
                }
                n2 = 0;
                while (n2 < iB.length) {
                    a.iB[n2] = object[n++];
                    ++n2;
                }
                n2 = 0;
                while (n2 < iA.length) {
                    a.iA[n2] = object[n++];
                    ++n2;
                }
                n2 = 0;
                while (n2 < iy.length) {
                    a.iy[n2] = object[n++];
                    ++n2;
                }
                n2 = 0;
                while (n2 < iz.length) {
                    a.iz[n2] = object[n++];
                    ++n2;
                }
                n2 = 0;
                while (n2 < iv.length) {
                    a.iv[n2] = object[n++];
                    ++n2;
                }
                n2 = 0;
                while (n2 < iw.length) {
                    a.iw[n2] = object[n++];
                    ++n2;
                }
            }
            catch (Exception exception) {}
        }
        if (fA) {
            if (es == null) {
                es = new Image[4];
                try {
                    a.es[0] = Image.createImage((String)"/cp/ch0x.png");
                    a.es[1] = Image.createImage((String)"/cp/ch1x.png");
                }
                catch (Exception exception) {}
                a.es[2] = es[1];
                a.es[3] = es[0];
            }
            n2 = 0;
            while (n2 < 4) {
                object = er[n2];
                a.er[n2] = es[n2];
                a.es[n2] = (Image)object;
                ++n2;
            }
        }
    }

    private static int[] a(byte[] byArray) {
        int n = byArray.length;
        int[] nArray = new int[n >> 2];
        int n2 = 0;
        while (n2 < nArray.length) {
            nArray[n2] = (byArray[n2 << 2] << 24) + ((byArray[(n2 << 2) + 1] & 0xFF) << 16) + ((byArray[(n2 << 2) + 2] & 0xFF) << 8) + (byArray[(n2 << 2) + 3] & 0xFF);
            ++n2;
        }
        return nArray;
    }

    public static final int a(int n) {
        if (n > 0) {
            return n;
        }
        return -n;
    }

    private static void a(int n, int n2, int n3, int n4, int n5) {
        int n6 = 0;
        while (n6 < dh) {
            if (dk[n6] == 0) break;
            ++n6;
        }
        if (n6 == dh) {
            return;
        }
        a.di[n6] = n;
        a.dj[n6] = n2;
        a.dl[n6] = n3;
        a.dm[n6] = n4;
        a.dk[n6] = 1;
        a.do[n6] = n5;
        if (n5 < 160) {
            a.dp[n6] = 0;
            return;
        }
        if (n5 < 600) {
            a.dp[n6] = 1;
            return;
        }
        a.dp[n6] = 2;
    }

    private static void a(int n, int n2, int n3, int n4, int n5, int n6) {
        n4 = 0;
        while (n4 < cP) {
            if (cT[n4] == 0) break;
            ++n4;
        }
        if (n4 == cP) {
            return;
        }
        a.cR[n4] = n;
        a.cS[n4] = n2;
        a.cU[n4] = n3;
        a.cV[n4] = -5;
        a.cT[n4] = 1;
        a.cX[n4] = n5;
        a.cQ[n4] = n6;
        if (n5 < 100) {
            a.cW[n4] = 0;
            return;
        }
        if (n5 < 200) {
            a.cW[n4] = 1;
            return;
        }
        a.cW[n4] = 2;
    }

    private static void u(int n) {
        if ((N += n) > ak[M - 1]) {
            N = 0;
            if (Midlet.a && M == 6) {
                a.s(6);
                a.a("Xin \u0111\u0103ng k\u00fd \u0111\u1ec3 c\u00f3 th\u1ec3 l\u00ean c\u1ea5p 7!");
                d = 7;
                return;
            }
            E += aj[++M - 2];
            F += 2;
            G = H += al[M - 2];
            I = J;
            a.a("level-up", 2, fh, fi - fk, 0, -2);
            a.s(6);
            a.a("Ch\u00fac m\u1eebng! B\u1ea1n \u0111\u1ea1t \u0111\u01b0\u1ee3c c\u1ea5p " + M + '\n' + "HP: " + H + '\n' + "S\u1ee9c \u0111\u00e1nh: " + (E + (E >> 2) * (x + 1)));
            d = 7;
        } else {
            a.a("+" + n, 2, fh, fi - fk, 0, -2);
        }
        c = true;
    }

    private static void u() {
        if (O == 11 && z != 0) {
            ++fF;
            if (O >= 20) {
                a.c("Th\u00e0nh c\u00f4ng 20 hit!");
            } else {
                a.c("Hit: " + fF);
            }
        }
        int n = E + w % (E >> 1);
        n += (n >> 1) * x;
        if (fA) {
            n <<= 1;
        }
        if (fg == 1) {
            a.a(fh + (fp << 3), fi - 12, (fp << 3) + y, z << 1, false);
            return;
        }
        if (z > 0 && y == 0) {
            z = -2;
        }
        int n2 = a.A();
        fu = false;
        if (n2 != -1) {
            fu = true;
            a.k(n2, n, -fp);
            a.f(jN[n2], jO[n2] - (jP[n2] >> 1), fp);
            a.a("-" + n, 0, jN[n2], jO[n2] - jP[n2], 0, -2);
            if (z > 0) {
                z = -6;
            }
            a.Q();
        }
        n2 = 0;
        while (n2 < it) {
            if (a.a(fi - fm - iw[n2]) < 20 && a.a(fh + (fp << 4) - iv[n2]) < 24) {
                a.ix[n2] = 2;
                a.iy[n2] = fp << 3;
                a.f(iv[n2], iw[n2] - 12, fp);
                a.Q();
                break;
            }
            ++n2;
        }
        if (gQ && a.a(fi - gT) < 30 && a.a(fh + (fp << 4) - gS) < 40) {
            if (!a.c(n, -fp)) {
                a.f(fh + (gS - fh >> 1), fi - 12, fp);
                a.a("-" + n, 0, gS, gT - gZ, 0, -2);
            }
            a.Q();
        }
        n2 = 0;
        while (n2 < cs) {
            if (a.a(fi - cy[n2]) <= 12 && a.a(fh + (fp << 4) - cx[n2]) < 24) {
                a.a(n2, n, -fp);
                a.f(cx[n2], cy[n2] - 15, fp);
                a.a("-" + n, 0, cx[n2], cy[n2] - 28, 0, -2);
                a.Q();
                break;
            }
            ++n2;
        }
        if (hM && a.a(fi - (hT - 40)) < 30 && a.a(fh + (fp << 4) - hS) < 40) {
            boolean bl;
            n2 = n;
            if ((hP -= n2) <= 0) {
                hP = 0;
                ie = 0;
                if = 0;
                ia = 1;
                a.a(hS, hT, 4, -10, 600);
                a.a(hS, hT, -4, -17, 600);
                a.a(hS, hT, -3, -18, 600);
                a.a(hS, hT, 4, -19, 600);
                a.a(hS, hT, 0, -10, 600);
                bl = false;
            } else {
                if = 50;
                bl = false;
            }
            a.f(fh + (hS - fh >> 1), fi - 12, fp);
            a.a("-" + n, 0, hS, hT - hO, 0, -2);
            a.Q();
        }
        if (hf) {
            if (a.a(fi - dD[0]) < 5 && a.a(fh + (fp << 4) - dC[0]) < 30) {
                a.d(n, fp);
            }
            a.Q();
        }
        if (hl) {
            if (a.a(fi - (ht - 30)) < 30 && a.a(fh + (fp << 4) - hs) < 30) {
                boolean bl;
                n2 = n;
                if ((hp -= n2) <= 0) {
                    hp = 0;
                    hy = 0;
                    hr = 0;
                    hu = 6;
                    a.a(hs, ht, 4, -10, 500);
                    a.a(hs, ht, -4, -17, 500);
                    a.a(hs, ht, -3, -18, 500);
                    a.a(hs, ht, 4, -19, 500);
                    a.a(hs, ht, 0, -10, 500);
                    bl = false;
                } else {
                    hr = 50;
                    a.f(hs, ht - 47, fp);
                    a.a("-" + n2, 0, hs, ht - 55, 0, -2);
                    bl = false;
                }
            }
            a.Q();
        }
    }

    private static void v() {
        int n;
        int n2;
        int n3;
        block7: {
            if (ik == 1) {
                int n4 = gC << 3;
                int n5 = fi - gr >> 3;
                a.a(gq + (gC << 3), gr - 12, n4, n5 + (n4 - 8), true);
                return;
            }
            n3 = E << 1;
            n2 = gC > 0 ? gq - 20 : gq - 40;
            int n6 = gC > 0 ? gq + 40 : gq + 20;
            int n7 = gr - gx - 15;
            int n8 = gr + 5;
            int n9 = 0;
            while (n9 < jK) {
                if (jS[n9] != 5 && jN[n9] >= n2 && jN[n9] <= n6 && jO[n9] >= n7 && jO[n9] <= n8) {
                    n = n9;
                    break block7;
                }
                ++n9;
            }
            n = n2 = -1;
        }
        if (n != -1) {
            a.k(n2, n3, -gC);
            a.f(jN[n2], jO[n2] - (jP[n2] >> 1), gC);
            a.a("-" + n3, 0, jN[n2], jO[n2] - jP[n2], 0, -2);
        }
        if (gQ && a.a(gr - gT) < 30 && a.a(gq + (gC << 4) - gS) < 40 && !a.c(n3, -gC)) {
            a.f(gq + (gS - gq >> 1), gr - 12, gC);
            a.a("-" + n3, 0, gS, gT - gZ, 0, -2);
        }
        n2 = 0;
        while (n2 < cs) {
            if (gr == cy[n2] && a.a(gq + (gC << 4) - cx[n2]) < 24) {
                a.a(n2, n3, -gC);
                a.f(cx[n2], cy[n2] - 15, gC);
                a.a("-" + n3, 0, cx[n2], cy[n2] - 28, 0, -2);
                return;
            }
            ++n2;
        }
    }

    private static void w() {
        int n = 0;
        while (n < it) {
            if (ix[n] != 4 && a.a(fh - iv[n]) < 20 && iw[n] > fi && iw[n] - 24 <= fi) {
                y = 0;
                z = 0;
                fi = iw[n] - 24;
                A = 1;
                ff = -1;
                iu = n;
                if (ix[n] != 11) break;
                a.g(iv[n], iw[n] - 10);
                int n2 = n;
                iw[n2] = iw[n2] + 4;
                int n3 = n;
                iz[n3] = iz[n3] + 4;
                return;
            }
            ++n;
        }
    }

    private static void x() {
        int n = 0;
        while (n < it) {
            if (a.a(fh - iv[n]) < 20 && a.a(iw[n] - fi) < 4) {
                y = 0;
                fh = fh < iv[n] ? iv[n] - 20 : iv[n] + 20;
                if (ix[n] != 4) {
                    a.ix[n] = 2;
                    a.iy[n] = -fp << 1;
                    return;
                }
            }
            ++n;
        }
    }

    private static void v(int n) {
        if (a.a(fi - cy[n]) <= 24 && a.a(cx[n] + (cK[n] << 3) - fh) < 25 && (cG[n] == 6 || cG[n] == 2 || cG[n] == 3)) {
            a.cI[n] = 0;
            a.cJ[n] = 0;
            a.cH[n] = 4;
        }
    }

    private static void w(int n) {
        if (fz) {
            return;
        }
        if (a.a(cx[n] - fh) < 100 && a.a(cy[n] - fi) < 20) {
            switch (cG[n]) {
                case 2: 
                case 3: 
                case 6: {
                    a.cJ[n] = fh;
                    if (cx[n] < fh) {
                        int n2 = n;
                        cJ[n2] = cJ[n2] - 10;
                    } else {
                        int n3 = n;
                        cJ[n3] = cJ[n3] + 10;
                    }
                    a.cK[n] = cJ[n] < fh ? 1 : -1;
                    a.cH[n] = 7;
                    a.cB[n] = cK[n] << 3;
                    if (cJ[n] < cN[n]) {
                        a.cJ[n] = cN[n];
                    }
                    if (cJ[n] <= cO[n]) break;
                    a.cJ[n] = cO[n];
                    return;
                }
                case 0: 
                case 1: 
                case 4: 
                case 5: {
                    a.cK[n] = cx[n] < fh ? 1 : -1;
                    a.cI[n] = 0;
                    a.cJ[n] = 0;
                    a.cH[n] = 4;
                }
            }
        }
    }

    private static void y() {
        int n = 0;
        while (n < it) {
            if (a.a(fh - iv[n]) < 20 && iw[n] == fi) {
                y = 0;
                fh = fh < iv[n] ? iv[n] - 20 : iv[n] + 20;
                if (ix[n] != 4) {
                    a.ix[n] = 2;
                    a.iy[n] = fp << 2;
                    return;
                }
            }
            ++n;
        }
    }

    static void c() {
        int n = 0;
        while (n < 14) {
            a.aU[n] = false;
            ++n;
        }
    }

    static void d() {
        int n = 0;
        while (n < 14) {
            a.aV[n] = false;
            ++n;
        }
    }

    private static void z() {
        switch (iG) {
            case 3: {
                O = 0;
                fF = 0;
                a.c("NV: Gi\u1ebft 10 \u1ed1c s\u00ean");
                break;
            }
            case 4: {
                eL = true;
                eM = true;
                O = -1;
                fF = 0;
                a.c("\u0111\u00e3 h\u1ecdc ph\u00e9p khinh c\u00f4ng");
                break;
            }
            case 8: {
                a.c(null);
                break;
            }
            case 5: {
                O = 1;
                fF = 0;
                a.c("NV: \u0111\u00e1nh ng\u00e3 2 b\u00f9 nh\u00ecn");
                break;
            }
            case 6: {
                eN = true;
                O = -1;
                fF = 0;
                a.c("\u0111\u00e3 h\u1ecdc n\u00e9m phi ti\u00eau");
                break;
            }
            case 9: {
                O = 2;
                fB = 103;
                fC = 2;
                fD = false;
                a.c("NV: T\u00ecm b\u00ed k\u00edp");
                break;
            }
            case 10: {
                eO = true;
                O = -1;
                fB = 0;
                fC = 0;
                fD = false;
                a.c("\u0111\u00e3 h\u1ecdc thu\u1eadt \u0111\u1ed9n th\u1ed5");
                break;
            }
            case 11: {
                O = 3;
                gp = 3;
                gq = 2808;
                gr = 216;
                gC = -1;
                gu = 1;
                gs = 5;
                break;
            }
            case 13: {
                O = 4;
                fF = 0;
                a.c("NV: gi\u1ebft 20 con ong");
                break;
            }
            case 12: {
                eQ = true;
                E <<= 1;
                O = -1;
                fB = 0;
                fC = 0;
                fD = false;
                a.c("s\u1ee9c c\u00f4ng nh\u00e2n \u0111\u00f4i!");
                break;
            }
            case 14: {
                O = 5;
                fD = false;
                fF = 0;
                fC = 10;
                a.c("NV: gi\u1ebft heo r\u1eebng");
                break;
            }
            case 15: {
                O = -1;
                eR = true;
                eS = true;
                a.c("\u0111\u00e3 h\u1ecdc ch\u1ea1y n\u01b0\u1edbc.");
                break;
            }
            case 16: {
                O = 6;
                fD = false;
                fC = 10;
                a.c("NV: v\u01b0\u1ee3t s\u00f4ng");
                break;
            }
            case 17: {
                O = -1;
                eT = true;
                a.c("\u0111\u00e3 h\u1ecdc bi\u1ebfn h\u00ecnh.");
                break;
            }
            case 18: {
                O = 7;
                fF = 0;
                a.c("NV: gi\u1ebft 15 d\u01a1i v\u00e0ng");
                break;
            }
            case 19: {
                eU = true;
                dg += 5000;
                c = true;
                O = -1;
                fB = 0;
                fC = 0;
                fD = false;
                a.c("Nh\u1eadn \u0111\u01b0\u1ee3c ti\u1ec1n 5000$");
                break;
            }
            case 22: {
                O = 8;
                a.c("NV: t\u00ecm \u0111\u00e1nh Toniku");
                break;
            }
            case 23: {
                O = -1;
                eV = true;
                a.c("Ho\u00e0n th\u00e0nh nhi\u1ec7m v\u1ee5.");
                break;
            }
            case 24: {
                O = 9;
                fF = 0;
                a.c("NV: gi\u1ebft 20 th\u1ed5 d\u00e2n");
                break;
            }
            case 25: {
                eW = true;
                O = -1;
                fB = 0;
                fC = 0;
                fD = false;
                a.c("\u0111\u1ebfn g\u1eb7p c\u00f4 b\u00e9!");
                break;
            }
            case 26: {
                O = 10;
                fF = 0;
                gn = true;
                a.c("NV: d\u1eabn c\u00f4 b\u00e9 \u0111i ch\u01a1i");
                break;
            }
            case 28: {
                O = 11;
                fF = 0;
                a.c("NV: 20 hit tr\u00ean kh\u00f4ng");
                break;
            }
            case 27: {
                O = -1;
                eY = true;
                fF = 0;
                a.c("H\u1ecdc khinh c\u00f4ng c\u1ea5p 2");
                break;
            }
            case 30: {
                O = 12;
                fF = 0;
                a.c("NV: \u0111\u00e1nh 70 qu\u1ef7 hoa \u0111\u1ecf");
                break;
            }
            case 29: {
                eZ = true;
                F <<= 2;
                O = -1;
                fF = 0;
                a.c("\u0111\u00e3 h\u1ecdc phi ti\u00eau c\u1ea5p 2");
                int n = 0;
                while (n < 2) {
                    try {
                        a.en[n] = Image.createImage((String)("/c/pt" + (n + 2) + ".png"));
                    }
                    catch (IOException iOException) {}
                    ++n;
                }
                break;
            }
            case 32: {
                O = 13;
                fF = 0;
                a.c("NV: Gi\u1ebft 50 b\u00f2 c\u1ea1p");
                break;
            }
            case 31: {
                fa = true;
                O = -1;
                fF = 0;
                a.c("\u0111\u00e3 c\u00f3 th\u1ec3 ch\u1ea1y nhanh");
                break;
            }
            case 34: {
                O = 14;
                fF = 0;
                a.c("NV: Gi\u1ebft 30 c\u00e1 \u0111\u1ecf");
                break;
            }
            case 33: {
                fb = true;
                O = -1;
                fF = 0;
                a.c("\u0111\u00e3 c\u00f3 th\u1ec3 \u0111\u1ee9ng n\u01b0\u1edbc");
                break;
            }
            case 20: {
                O = 15;
                fF = 0;
                fD = false;
                a.c("NV: t\u00ecm b\u00ed k\u00edp");
                break;
            }
            case 21: {
                fc = true;
                O = -1;
                fF = 0;
                fD = false;
                a.c("\u0111\u00e3 c\u00f3 th\u1ec3 \u1ea9n th\u00e2n");
                break;
            }
            case 36: {
                O = 16;
                fF = 0;
                fD = false;
                a.c("NV: c\u1ee9u Sakura");
                break;
            }
            case 35: {
                O = -1;
                fF = 0;
                fD = false;
            }
        }
        if (lK >= 0) {
            a.a(lK, lL, lM, lN);
            lK = -1;
        }
    }

    private static int A() {
        int n = fp > 0 ? fh - 20 : fh - 40;
        int n2 = fp > 0 ? fh + 40 : fh + 20;
        int n3 = fi - fk - 15;
        int n4 = fi + 5;
        int n5 = 0;
        while (n5 < jK) {
            if (jS[n5] != 5 && jN[n5] >= n && jN[n5] <= n2 && jO[n5] >= n3 && jO[n5] <= n4) {
                return n5;
            }
            ++n5;
        }
        return -1;
    }

    private static void B() {
        lw = null;
        System.gc();
        ee = null;
        eh = null;
        ey = null;
        System.gc();
        eA = null;
        System.gc();
        jJ = null;
        System.gc();
        a.C();
        ev = null;
        el = null;
        eb = null;
        System.gc();
    }

    private static void C() {
        ex = null;
        ew = null;
        System.gc();
    }

    private static void D() {
        int n = 0;
        while (n < jK) {
            if (jS[n] != 5 && a.a(jO[n] - gr) < 20 && a.a(jN[n] - gq) < 40) {
                gC = jN[n] > gq ? 1 : -1;
                gu = 5;
                ik = 0;
                gD = 0;
                gE = 0;
                return;
            }
            ++n;
        }
        n = 0;
        while (n < cs) {
            if (cH[n] != 5 && cy[n] == gr && a.a(cx[n] - gq) < 40) {
                gC = cx[n] > gq ? 1 : -1;
                gu = 5;
                ik = 0;
                gD = 0;
                gE = 0;
                return;
            }
            ++n;
        }
        if (a.a(fh - gq) > 25 && fi != gr && A == 5) {
            gC = fh > gq ? 1 : -1;
            gu = 5;
            ik = 1;
            gD = 0;
            gE = 0;
            return;
        }
    }

    private static void E() {
        block32: {
            if (gn) {
                if (fi <= gr - 24 && a.g(gq, gr - 1, 2)) {
                    gu = 3;
                    gs = 0;
                    gt = -7;
                    return;
                }
                if (fi <= gr - 24 && a.g(gq, gr - 25, 2)) {
                    gu = 3;
                    gs = 0;
                    gt = -10;
                    return;
                }
                if (gq < fR - 24 || gq > fR + q + 24) {
                    int n;
                    gC = fh + (fp << 5) > gq ? 1 : -1;
                    int n2 = fh > gq ? fR - 24 : fR + q + 24;
                    if (!a.g(n2, n = fi - 20, 8192)) {
                        gq = n2;
                        gr = n;
                        gu = 4;
                        gs = 4;
                        gt = 3;
                        return;
                    }
                    if (a.g(gq + gC * gw, gr, 2)) {
                        gC = fh > gq ? 1 : -1;
                        gu = 2;
                        gs = gC * 12;
                        return;
                    }
                } else {
                    if (gr < fS - 30 || gr > fS + r + 30) {
                        gC = fh + (fp << 5) > gq ? 1 : -1;
                        int n = fh;
                        int n3 = fi > gr ? fS - 20 : fS + r;
                        gq = n;
                        gr = n3;
                        gu = 3;
                        gs = gC << 2;
                        gt = -4;
                        return;
                    }
                    if (a.a(fh + (fp << 5) - gq) > (A == 5 ? 10 : 60)) {
                        if (fi < gr) {
                            if (a.g(gq + gC * 48, gr - 24, 2)) {
                                gu = 3;
                                gs = gC * 5;
                                gt = -8;
                                return;
                            }
                            int n = 1;
                            while (n <= 3) {
                                if (a.g(gq + gC * 24, gr - n * 24, 2)) {
                                    gu = 3;
                                    gs = gC * 6;
                                    gt = -5 - n * 3;
                                    return;
                                }
                                ++n;
                            }
                            if (!a.g(gq + gC * 48, gr - 2, 64)) {
                                if (gr + 24 >= iS || a.g(gq + gC * 48, gr + 24, 2)) {
                                    gu = 3;
                                    gs = gC * 7;
                                    gt = -4;
                                    return;
                                }
                                n = 1;
                                while (n <= 5) {
                                    if (gr + n * 24 < iS && !a.g(gq + gC * 24, gr + n * 24, 64)) {
                                        if (a.g(gq + gC * 48, gr + n * 24, 2)) {
                                            gu = 3;
                                            gs = gC * 6;
                                            gt = -4;
                                            return;
                                        }
                                        if (a.g(gq + gC * 24, gr + n * 24, 2)) {
                                            gu = 3;
                                            gs = gC << 2;
                                            gt = -4;
                                            return;
                                        }
                                        ++n;
                                        continue;
                                    }
                                    break block32;
                                }
                                return;
                            }
                        } else if (fi > gr) {
                            if (a.g(gq + gC * gw, gr, 2)) {
                                gC = fh > gq ? 1 : -1;
                                gu = 2;
                                gs = gC * P;
                                return;
                            }
                            if (a.g(gq + gC * 48, gr + 24, 2)) {
                                gu = 3;
                                gs = gC * 7;
                                gt = -5;
                                return;
                            }
                            int n = 1;
                            while (n <= 5) {
                                if (gr + n * 24 >= iS || a.g(gq + gC * 24, gr + n * 24, 64)) break;
                                if (a.g(gq + gC * 24, gr + n * 24, 2)) {
                                    gu = 3;
                                    gs = gC << 2;
                                    gt = -4;
                                    return;
                                }
                                if (a.g(gq + gC * 48, gr + n * 24, 2)) {
                                    gu = 3;
                                    gs = gC * 6;
                                    gt = -4;
                                    return;
                                }
                                ++n;
                            }
                            if (a.g(gq + gC * 24, gr - 24, 2)) {
                                gu = 3;
                                gs = gC * 5;
                                gt = -7;
                                return;
                            }
                            if (a.g(gq + gC * 48, gr - 24, 2)) {
                                gu = 3;
                                gs = gC * 6;
                                gt = -8;
                                return;
                            }
                            if (a.g(gq + gC * 24, gr - 48, 2)) {
                                gu = 3;
                                gs = gC * 5;
                                gt = -11;
                                return;
                            }
                        } else if (a.g(gq + gC * gw, gr, 2)) {
                            gC = fh + (fp << 5) > gq ? 1 : -1;
                            gu = 2;
                            gs = gC * P;
                            return;
                        }
                    } else {
                        gu = 1;
                    }
                }
            }
        }
    }

    private static void a(int n, int n2) {
        ff = -1;
        c = true;
        iu = -1;
        if ((G -= n) <= 0) {
            G = 0;
            z = -7;
            y = n2 << 2;
            A = 6;
            im = -1;
        } else if (im == -1 && A != 23) {
            y = n2 << 2;
            z = -2;
            C = 0;
            D = 0;
            A = 7;
        }
        a.f(fh, fi - 12, -fp);
        a.a("-" + n, 0, fh, fi - fk, 0, -2);
    }

    private static void b(int n, int n2) {
        ij = -1;
        gs = n2 << 2;
        gt = -2;
        gD = 0;
        gE = 0;
        gu = 7;
        a.f(gq, gr - 12, -gC);
        a.a("-" + n, 0, gq, gr - gx, 0, -2);
    }

    private static boolean a(int n, int n2, int n3) {
        int n4 = n;
        cu[n4] = cu[n4] - n2;
        if (cH[n] == 1 || cH[n] == 5) {
            return true;
        }
        if (cu[n] <= 0) {
            a.cu[n] = 0;
            a.cK[n] = n3;
            a.cB[n] = 0;
            a.cC[n] = 0;
            a.cD[n] = -(n3 << 3);
            a.cE[n] = -6;
            a.cI[n] = 0;
            a.ct[n] = 0;
            a.cH[n] = 1;
            a.u(ae[cG[n]]);
            a.a(cx[n], cy[n], -(cK[n] << 2), -4, ac[cG[n]] + w % 10);
            if (w % 20 > 5) {
                a.a(cx[n], cy[n], -(cK[n] << 3), -5, ad[cG[n]] << 2, (w + 1) % 2);
            }
            return true;
        }
        a.cI[n] = 0;
        a.cK[n] = n3;
        a.ct[n] = 100;
        a.cH[n] = 6;
        return false;
    }

    private static boolean c(int n, int n2) {
        if (gV == 1 || gV == 2) {
            if ((hc -= n) <= 0) {
                hc = 0;
                ha = -5;
                gU = -(n2 << 3);
                he = 0;
                gV = 6;
                a.a(gS, gT, 4, -6, 50);
                a.a(gS, gT, -5, -7, 50);
                a.a(gS, gT, -4, -8, 50);
                a.a(gS, gT, 5, -9, 50);
                a.a(gS, gT, 0, -10, 50);
                if (O == 5) {
                    a.e(gS, gT);
                }
                return true;
            }
            ha = 0;
            gW = n2;
            gV = 7;
            he = 10;
            gX = 0;
            hb = n <= F ? 0 : 2 + w % 5;
        }
        return false;
    }

    private static void d(int n, int n2) {
        a.dL[0] = -1;
        if ((hj -= n) <= 0) {
            hj = 0;
            return;
        }
        a.dE[0] = n2 << 2;
        a.dF[0] = -2;
        a.dH[0] = 0;
        a.dI[0] = 0;
        a.dG[0] = 7;
        a.f(dC[0], dD[0] - 12, fp);
        a.a("-" + n, 0, dC[0], dD[0] - 32, 0, -2);
    }

    public static final void b(int n) {
        bP = 0;
        if (n == 0) {
            bP = 0;
            return;
        }
        if (eb == null) {
            eb = new Image[4][2];
            n = 0;
            while (n < 4) {
                int n2 = 0;
                while (n2 < 2) {
                    try {
                        a.eb[n][n2] = Image.createImage((String)("/c/ar" + n + n2 + ".png"));
                    }
                    catch (IOException iOException) {}
                    ++n2;
                }
                ++n;
            }
        }
        bP = 10;
        bQ = new int[10];
        bR = new int[bP];
        bS = new int[bP];
        bT = new int[bP];
        bU = new int[bP];
        bV = new int[bP];
        n = 0;
        while (n < bP) {
            a.bS[n] = -1;
            ++n;
        }
    }

    public static void e() {
        if (ec == null) {
            ec = new Image[4];
            int n = 0;
            while (n < 4) {
                try {
                    a.ec[n] = Image.createImage((String)("/u/arr" + n + ".png"));
                }
                catch (Exception exception) {}
                ++n;
            }
        }
    }

    public static void f() {
        bW = 8;
        bX = new int[8];
        bY = new int[bW];
        bZ = new int[bW];
        ca = new int[bW];
        cb = new int[bW];
        cc = new int[bW];
        try {
            ed = Image.createImage((String)"/e/b.png");
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void c(int n) {
        int n2;
        il = 0;
        int n3 = n2 = im == -1 ? 0 : 1;
        if ((n == 0 || n == 1 || n == 7 || n == 5 || n == 18 || n >= 94) && n2 == 1) {
            il = 1;
            a.F();
            a.b(0, fh, fi);
            im = 0;
            a.ip[a.im] = fp;
        }
        if (n == 2) {
            a.d(n2, 18, 12);
        }
        if (n == 3) {
            a.d(n2, 2, 9);
        }
        if (n == 4) {
            a.d(n2, 3, 6);
        }
        if (n == 6) {
            a.d(n2, 12, 9);
        }
        if (n == 8) {
            a.d(n2, 3, 12);
        }
        if (n == 9) {
            a.d(n2, 2, 11);
        }
        if (n == 10) {
            a.d(n2, 3, 12);
        }
        if (n == 11) {
            a.d(n2, 5, 6);
        }
        if (n == 12) {
            a.d(n2, 4, 8);
        }
        if (n == 13) {
            a.d(n2, 16, 11);
        }
        if (n == 14) {
            a.d(n2, 18, 7);
        }
        if (n == 15) {
            a.d(n2, 1, 11);
        }
        if (n == 16) {
            a.d(n2, 7, 11);
        }
        if (n == 17) {
            a.d(n2, 14, 12);
        }
    }

    private static void F() {
        if (ee == null) {
            ee = new Image[3];
            try {
                int n = 0;
                while (n < 3) {
                    a.ee[n] = Image.createImage((String)("/c/v" + n + ".png"));
                    ++n;
                }
            }
            catch (Exception exception) {}
        }
        in = new int[il];
        io = new int[il];
        ip = new int[il];
        iq = new int[il];
        ir = new int[il];
        is = new int[il];
    }

    private static void b(int n, int n2, int n3) {
        a.in[n] = n2 * jh + 12;
        a.io[n] = n3 * jh;
        a.iq[n] = 0;
        a.ip[n] = 1;
    }

    public static void g() {
        try {
            ef = Image.createImage((String)"/i/bk.png");
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void d(int n) {
        it = 0;
        if (n == 1) {
            it = 2;
            a.G();
            a.c(0, 21, 9);
            a.c(1, 13, 8);
        }
        if (n == 11) {
            it = 2;
            a.G();
            a.c(0, 33, 7);
            a.c(1, 44, 10);
        }
        if (n == 5) {
            it = 2;
            a.G();
            a.c(0, 5, 46);
            a.c(1, 15, 46);
        }
        if (n == 97) {
            it = 4;
            a.G();
            a.c(0, 3, 6);
            a.c(1, 7, 4);
            a.c(2, 12, 3);
            a.c(3, 22, 6);
        }
    }

    private static void G() {
        if (eh == null) {
            try {
                eh = Image.createImage((String)"/i/b.png");
            }
            catch (Exception exception) {}
        }
        iv = new int[it];
        iw = new int[it];
        ix = new int[it];
        iy = new int[it];
        iz = new int[it];
        iA = new int[it];
        iB = new int[it];
    }

    private static void c(int n, int n2, int n3) {
        a.iv[n] = n2 * jh + 12;
        a.iw[n] = n3 * jh + 24;
        a.ix[n] = 1;
    }

    public static final void h() {
        q = aW;
        if (aX >= 208) {
            bh = true;
            bl = 50;
            r = aX - bl;
            bk = q;
            bi = 0;
            bj = r + 1;
        } else {
            bl = 16;
            r = aX - bl;
            bh = false;
            bl = 0;
            bk = q;
            bi = 0;
            bj = r + 1;
            br = q / 3;
            bt = br + 29;
            bu = br + 50;
        }
        bw = aX / 6;
        if (bw < 48) {
            bw = 48;
        }
        bF = q >> 1;
        bG = r >> 1;
        bH = 3 * r / 4;
        bI = q / 6;
        bB = q / jh + 2;
        bC = r / jh + 2;
        if (q % 24 != 0) {
            ++bB;
        }
        fT = (iP - 1) * jh - q;
        fU = (iQ - 1) * jh - r;
        fR = fL = fh - bF + bI * fp;
        fS = fM = fi - bH;
        if (fR < 0) {
            fR = 0;
        }
        if (fR > fT) {
            fR = fT;
        }
        if (fS < 0) {
            fS = 0;
        }
        if (fS > fU) {
            fS = fU;
        }
        if ((bz = fR / jh - 1) < 0) {
            bz = 0;
        }
        bA = fS / jh;
        bD = bz + bB;
        bE = bA + bC;
        if (bA < 0) {
            bA = 0;
        }
        if (bE > iQ - 1) {
            bE = iQ - 1;
        }
    }

    public static final void i() {
        ff = -1;
        if (eg == null) {
            try {
                eg = new Image[20];
                int n = 0;
                while (n < 20) {
                    a.eg[n] = Image.createImage((String)("/cp/cb" + n + ".png"));
                    ++n;
                }
                er = new Image[11];
                n = 0;
                while (n < 11) {
                    a.er[n] = Image.createImage((String)("/cp/ch" + n + ".png"));
                    ++n;
                }
                et = new Image[10];
                n = 0;
                while (n < 10) {
                    a.et[n] = Image.createImage((String)("/cp/cl" + n + ".png"));
                    ++n;
                }
            }
            catch (Exception exception) {}
        }
        a.H();
        fj = 22;
        fk = 32;
        fl = fj >> 1;
        fm = fk >> 1;
        fn = fj >> 2;
        fo = fk >> 2;
        fG = -1;
    }

    private static void H() {
        eE = new Image[11];
        System.gc();
        int n = 0;
        while (n < 11) {
            try {
                a.eE[n] = Image.createImage((String)("/c/w" + n + "t" + x + ".png"));
            }
            catch (Exception exception) {
                try {
                    a.eE[n] = Image.createImage((String)("/c/w" + n + "t0.png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
            }
            ++n;
        }
    }

    public static void j() {
        a.I();
        if (!bh) {
            return;
        }
        try {
            if (ei == null) {
                ei = new Image[2];
                int n = 0;
                while (n < 2) {
                    a.ei[n] = Image.createImage((String)("/u/c" + n + ".png"));
                    ++n;
                }
            }
            bm = ei[0].getWidth();
            bn = ei[1].getWidth();
            bo = q - bm - bn + 1;
            bp = 78;
            bq = bj + 7;
            br = q - 134;
            bs = q - 84;
            bv = 5;
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    private static void I() {
        try {
            if (ek == null) {
                ek = Image.createImage((String)"/u/co.png");
            }
            Image[] imageArray = new Image[2];
            eF = imageArray;
            imageArray[1] = Image.createImage((String)"/u/wpt1.png");
            if (x == 0) {
                a.eF[0] = Image.createImage((String)"/u/wpt0.png");
                return;
            }
            a.eF[0] = Image.createImage((String)("/u/wpt" + (x + 1) + ".png"));
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static void k() {
        dh = 5;
        if (ej == null) {
            try {
                ej = new Image[3];
                int n = 0;
                while (n < 3) {
                    a.ej[n] = Image.createImage((String)("/i/dm" + n + ".png"));
                    ++n;
                }
            }
            catch (Exception exception) {}
        }
        di = new int[dh];
        dj = new int[dh];
        dk = new int[dh];
        dl = new int[dh];
        dm = new int[dh];
        dn = new int[dh];
        dp = new int[dh];
        do = new int[dh];
    }

    public static void e(int n) {
        dq = false;
        if (n == 0) {
            if (el == null) {
                el = new Image[2];
                n = 0;
                while (n < 2) {
                    try {
                        a.el[n] = Image.createImage((String)("/npc/d" + n + ".png"));
                    }
                    catch (Exception exception) {}
                    ++n;
                }
            }
            dq = true;
            dr = 348;
            ds = 408;
            dt = 1;
            du = 252;
            dv = 708;
        }
    }

    public static final void l() {
        if (a) {
            return;
        }
        if (em == null) {
            em = new Image[2][5];
            int n = 0;
            while (n < 2) {
                int n2 = 0;
                while (n2 < 5) {
                    try {
                        a.em[n][n2] = Image.createImage((String)("/e/d" + n + n2 + ".png"));
                    }
                    catch (IOException iOException) {}
                    ++n2;
                }
                ++n;
            }
        }
        cm = new int[2];
        cn = new int[2];
        int[] nArray = new int[2];
        co = nArray;
        a.co[1] = -1;
        nArray[0] = -1;
    }

    public static final void m() {
        int n;
        if (en == null) {
            en = new Image[2];
            n = 0;
            while (n < 2) {
                try {
                    a.en[n] = eZ ? Image.createImage((String)("/c/pt" + (n + 2) + ".png")) : Image.createImage((String)("/c/pt" + n + ".png"));
                }
                catch (IOException iOException) {}
                ++n;
            }
        }
        cY = 10;
        cZ = new int[10];
        da = new int[cY];
        db = new int[cY];
        dc = new int[cY];
        dd = new int[cY];
        df = new boolean[cY];
        de = new int[cY];
        n = 0;
        while (n < cY) {
            a.db[n] = -1;
            ++n;
        }
    }

    public static final void n() {
        dS = new int[5];
        dT = new int[5];
        dU = new int[5];
        dV = new int[5];
        dW = new int[5];
        dX = new int[5];
        dR = new String[5];
        int n = 0;
        while (n < 5) {
            a.dW[n] = -1;
            ++n;
        }
    }

    private static void x(int n) {
        d = 2;
        v = n;
        Midlet.d = 1;
        a.ak();
        new Thread(Midlet.c).start();
    }

    public static final void f(int n) {
        if (gn) {
            gp = n;
        }
        if (gp != n) {
            return;
        }
        ij = -1;
        if (eo == null) {
            try {
                eo = new Image[8];
                n = 0;
                while (n < 8) {
                    a.eo[n] = Image.createImage((String)("/cp/gb" + n + ".png"));
                    ++n;
                }
                ep = new Image[3];
                n = 0;
                while (n < 3) {
                    a.ep[n] = Image.createImage((String)("/cp/gh" + n + ".png"));
                    ++n;
                }
                eq = new Image[9];
                n = 0;
                while (n < 9) {
                    a.eq[n] = Image.createImage((String)("/cp/gl" + n + ".png"));
                    ++n;
                }
            }
            catch (Exception exception) {}
        }
        gw = 22;
        gx = 32;
        gy = gw >> 1;
        gz = gx >> 1;
        gA = gw >> 2;
        gB = gx >> 2;
        if (gn) {
            gq = fh - fp * 10;
            gr = fi;
            gC = fp;
            gu = 4;
        }
    }

    public static void o() {
        if (eu == null) {
            eu = new Image[2];
            int n = 0;
            while (n < 2) {
                try {
                    a.eu[n] = Image.createImage((String)("/e/l" + n + ".png"));
                }
                catch (IOException iOException) {}
                ++n;
            }
        }
    }

    public static void g(int n) {
        String[] stringArray;
        dx = 0;
        dA = false;
        if (n == 0) {
            dx = 7;
            a.J();
            a.a(0, 0, 14, 17, 130, 11, 22, new String[]{"Ch\u00e0o, c\u1eadu l\u00e0 ai th\u1ebf, m\u00ecnh ch\u01b0a t\u1eebng g\u1eb7p b\u1ea1n th\u00ec ph\u1ea3i?", "N\u00f3i c\u1eadu nghe nh\u00e9, th\u1ea7y c\u00f4 tr\u01b0\u1eddng n\u00e0y kh\u00f3 t\u00ednh l\u1eafm \u0111\u00f3.", "C\u1ea9n th\u1eadn v\u1edbi \u00f4ng th\u1ea7y Hiroyaki nh\u00e9. \u0110\u1eebng ch\u1ecdc gi\u1eadn \u1ed5ng."});
            a.a(1, 1, 24, 17, 160, 15, 30, new String[]{"M\u00ecnh l\u00e0 MinhXP, l\u1edbp tr\u01b0\u1edfng l\u1edbp ki\u1ebfm thu\u1eadt.", "Ki\u1ebfm l\u00e0 v\u0169 kh\u00ed l\u1ee3i h\u1ea1i nh\u1ea5t c\u1ee7a m\u1ed9t ninja \u0111\u1ea5y b\u1ea1n bi\u1ebft kh\u00f4ng?", "Khu v\u1ef1c luy\u1ec7n t\u1eadp n\u1eb1m \u1edf b\u00ean tr\u00e1i. H\u00e3y sang \u0111\u1ea5y t\u1eadp luy\u1ec7n nhi\u1ec1u h\u01a1n n\u1eefa."});
            if (eT) {
                a.a(2, 6, 17, 12, 200, 7, 29, new String[]{"Th\u1eb1ng b\u00ean kia l\u00e0 s\u00e1t th\u1ee7 gi\u1ea3 m\u1ea1o.", "Ta \u0111\u00e2y m\u1edbi l\u00e0 ninja s\u00e1t th\u1ee7 th\u1ee9 thi\u1ec7t \u0111\u00e2y!"});
            } else {
                a.a(2, 2, 17, 12, 200, 7, 29, new String[]{"Ch\u00e0o, tao t\u00ean l\u00e0 Tonic, m\u00e0y l\u00e0 th\u1eb1ng n\u00e0o?", "Nh\u00ecn l\u00e3o hi\u1ec7u tr\u01b0\u1edfng k\u00eca, l\u00e3o c\u00f3 m\u1ed9t h\u0169 r\u01b0\u1ee3u tr\u00f4ng h\u1ea5p d\u1eabn qu\u00e1!", "N\u1ebfu mu\u1ed1n r\u1ee7 tao \u0111i nh\u1eadu th\u00ec ok, c\u00f2n kh\u00f4ng th\u00ec bi\u1ebfn \u0111i.", "\u00c0, n\u1ebfu m\u00e0y c\u00f3 th\u1ea5y con ch\u00f3 c\u1ee7a tao th\u00ec b\u00e1o tao 1 ti\u1ebfng nh\u00e9."});
            }
            a.a(3, 3, 48, 17, 200, 44, 63, new String[]{"Hi, m\u00ecnh l\u00e0 Craze, tr\u00f4ng b\u1ea1n c\u00f3 v\u1ebb h\u01a1i bu\u1ed3n th\u00ec ph\u1ea3i?", "B\u1ea1n c\u00f3 th\u1ea5y ch\u00e1n khi t\u1ed1i ng\u00e0y quanh qu\u1ea9n trong tr\u01b0\u1eddng kh\u00f4ng?", "\u0110i v\u1ec1 ph\u00eda tay ph\u1ea3i b\u1ea1n s\u1ebd \u0111\u1ebfn \u0111\u01b0\u1ee3c ng\u00f4i l\u00e0ng, ra \u0111\u00f3 \u0111\u1ec3 t\u00ecm ni\u1ec1m vui \u0111i!"});
            a.a(4, 4, 46, 12, 200, 40, 54, new String[]{"Th\u1eb1ng nh\u00e0 qu\u00ea kia, m\u00e0y mau ki\u1ebfm ti\u1ec1n \u0111\u00f3ng thu\u1ebf th\u00e2n cho tao.", "Tao cho m\u00e0y 2 ng\u00e0y, n\u1ebfu kh\u00f4ng \u0111\u01b0a tao 1000$ th\u00ec m\u00e0y \u0103n ch\u00e9m \u0111\u00f3."});
            a.a(5, 5, 38, 17, 200, 34, 49, new String[]{"Xin ch\u00e0o, b\u1ea1n l\u00e0 h\u1ecdc sinh m\u1edbi ph\u1ea3i kh\u00f4ng?", "Nh\u00ecn th\u1ea7y hi\u1ec7u tr\u01b0\u1edfng k\u00eca, th\u1ea7y v\u1ed1n l\u00e0 m\u1ed9t ninja s\u00e1t th\u1ee7 \u0111\u1ea5y.", "Th\u1ea7y \u0111\u00e3 b\u1ecf ngh\u1ec1 s\u00e1t th\u1ee7 l\u00e2u r\u1ed3i, nh\u01b0ng t\u00e0i n\u0103ng v\u1eabn c\u00f2n \u0111\u00f3", "Ch\u00fang ta c\u00f3 t\u1eadp luy\u1ec7n c\u1ea3 \u0111\u1eddi c\u0169ng kh\u00f4ng b\u1eb1ng th\u1ea7y \u0111\u00e2u."});
            a.a(6, 6, 58, 12, 200, 46, 60, new String[]{"\u0110\u1eebng h\u1ecfi ta l\u00e0 ai...", "Ta ch\u1ec9 l\u00e0 m\u1ed9t ninja s\u00e1t th\u1ee7 th\u00f4i, ng\u01b0\u01a1i bi\u1ebft th\u1ebf l\u00e0 \u0111\u01b0\u1ee3c r\u1ed3i.", "Bi\u1ebfn \u0111i cho ta luy\u1ec7n t\u1eadp."});
            a.y(7);
        }
        if (n == 1) {
            dx = 3;
            a.J();
            a.a(0, 5, 35, 9, 40, 33, 36, new String[]{"B\u1ea1n c\u00f3 th\u1ec3 nh\u1ea3y l\u00ean c\u00e2y c\u1ea7u n\u00e0y n\u1ebfu \u0111\u00e3 h\u1ecdc xong khinh c\u00f4ng t\u1eeb c\u00f4 Ayumi xinh \u0111\u1eb9p."});
            if (eT) {
                a.a(1, 6, 25, 10, 40, 22, 28, new String[]{"Ta \u0111\u00e2y l\u00e0 m\u1ed9t ninja s\u00e1t th\u1ee7!", "Ha ha ha!"});
            } else {
                a.a(1, 2, 25, 10, 40, 22, 28, new String[]{"M\u00e0y \u0111\u00e3 h\u1ecdc khinh c\u00f4ng r\u1ed3i h\u1ea3?", "\u00c0, m\u00e0y c\u00f3 th\u1ea5y con ch\u00f3 c\u1ee7a tao ch\u1ea1y \u0111\u00e2u \u0111\u00e2u kh\u00f4ng?"});
            }
            a.a(2, 1, 6, 9, 40, 2, 10, new String[]{"B\u00ean kia l\u00e0 m\u1ed9t th\u00e1c n\u01b0\u1edbc r\u1ea5t l\u1edbn \u0111\u1ea5y.", "N\u00f3 kh\u00f4ng thu\u1ed9c ph\u1ea1m vi tr\u01b0\u1eddng ch\u00fang ta n\u1eefa n\u00ean c\u00f3 r\u1ea5t nhi\u1ec1u qu\u00e1i v\u1eadt hung d\u1eef.", "Nghe n\u00f3i c\u00f3 m\u1ed9t con qu\u00e1i v\u1eadt r\u1ea5t to \u0111\u1ea5y, t\u1ed1t h\u01a1n ng\u01b0\u01a1i \u0111\u1eebng qua l\u00e0m g\u00ec!"});
            a.y(7);
        }
        if (n == 2) {
            dx = 7;
            a.J();
            a.a(0, 3, 16, 12, 60, 14, 21, new String[]{"C\u1eadu nh\u00ecn th\u1ea5y chi\u1ebfc xe m\u00e1y kia kh\u00f4ng?", "Ph\u1ea3i c\u00f3 b\u1eb1ng l\u00e1i t\u1eeb th\u1ea7y ch\u1ee7 nhi\u1ec7m b\u1ea1n m\u1edbi c\u00f3 th\u1ec3 ch\u1ea1y \u0111\u01b0\u1ee3c."});
            a.a(1, 4, 8, 12, 50, 2, 15, new String[]{"Th\u1eb1ng nh\u00e0 qu\u00ea kia, m\u00e0y \u0111i \u0111\u00e2u \u0111\u1ea5y?"});
            if (eT) {
                a.a(2, 6, 29, 13, 200, 27, 40, new String[]{"Ninja s\u00e1t th\u1ee7 l\u00e0 ta - Thi\u00ean h\u1ea1 v\u00f4 \u0111\u1ecbch!"});
            } else {
                a.a(2, 2, 29, 13, 200, 27, 40, new String[]{"M\u00e0y c\u00f3 th\u1ea5y con ch\u00f3 c\u1ee7a tao \u1edf \u0111\u00e2u kh\u00f4ng?"});
            }
            a.a(3, 5, 46, 13, 200, 41, 49, new String[]{"N\u1ebfu b\u1ea1n mu\u1ed1n l\u00ean kia th\u00ec ph\u1ea3i h\u1ecdc khinh c\u00f4ng t\u1eeb c\u00f4 Ayumi \u0111\u00e3."});
            a.a(4, 1, 46, 11, 200, 43, 53, new String[]{"C\u1eadu c\u00f3 th\u1ea5y \u1edf \u0111\u00e2y ai c\u0169ng t\u00e0i gi\u1ecfi kh\u00f4ng?", "H\u00e3y c\u1ed1 g\u1eafng t\u1eadp luy\u1ec7n h\u01a1n n\u1eefa nh\u00e9"});
            a.a(5, 0, 54, 11, 200, 50, 58, new String[]{"Ra ngo\u00e0i r\u1eebng hoa \u0111\u00e0o c\u00f3 r\u1ea5t nhi\u1ec1u \u0111i\u1ec1u th\u00fa v\u1ecb \u0111\u1ea5y.", "B\u1ea1n n\u00ean ra th\u1eed m\u1ed9t l\u1ea7n \u0111\u1ec3 bi\u1ebft."});
            a.a(6, 6, 34, 13, 200, 28, 48, new String[]{"\u0110\u1eebng h\u1ecfi ta l\u00e0 ai...", "Ta ch\u1ec9 l\u00e0 m\u1ed9t ninja s\u00e1t th\u1ee7 th\u00f4i, ng\u01b0\u01a1i bi\u1ebft th\u1ebf l\u00e0 \u0111\u01b0\u1ee3c r\u1ed3i.", "Bi\u1ebfn \u0111i cho ta luy\u1ec7n t\u1eadp."});
            a.y(7);
        }
        if (n == 99 || n == 101) {
            dx = 7;
            a.J();
            stringArray = new String[]{"N\u00e0y, c\u1eadu \u0111i t\u00ecm con heo \u0111\u00f3 tr\u01b0\u1edbc \u0111i. T\u1edb \u0111i theo b\u1ecdc l\u00f3t cho!"};
            a.a(0, 0, 8, 13, 0, 0, 1000, stringArray);
            a.a(1, 1, 5, 13, 0, 0, 1000, stringArray);
            a.a(2, 6, 6, 13, 0, 0, 1000, stringArray);
            a.a(3, 2, 7, 13, 0, 0, 1000, stringArray);
            a.a(4, 4, 6, 13, 0, 0, 1000, stringArray);
            a.a(5, 5, 5, 13, 0, 0, 1000, stringArray);
            a.a(6, 3, 8, 13, 0, 0, 1000, stringArray);
            a.y(7);
        }
        if (n == 94) {
            dx = 7;
            a.J();
            stringArray = new String[]{"Ch\u00fac m\u1eebng, s\u1eafp \u0111\u01b0\u1ee3c ra tr\u01b0\u1eddng r\u1ed3i nh\u00e9!"};
            a.a(0, 0, 5, 12, 0, 0, 1000, stringArray);
            a.a(1, 1, 5, 12, 0, 0, 1000, stringArray);
            a.a(2, 6, 6, 12, 0, 0, 1000, stringArray);
            a.a(3, 7, 3, 12, 0, 0, 1000, stringArray);
            a.a(4, 4, 6, 12, 0, 0, 1000, stringArray);
            a.a(5, 5, 5, 12, 0, 0, 1000, stringArray);
            a.a(6, 3, 8, 12, 0, 0, 1000, stringArray);
            a.y(8);
        }
    }

    private static void J() {
        dC = new int[dx];
        dD = new int[dx];
        dE = new int[dx];
        dF = new int[dx];
        dG = new int[dx];
        dH = new int[dx];
        dM = new int[dx];
        dI = new int[dx];
        dJ = new int[dx];
        dK = new int[dx];
        dL = new int[dx];
        dN = new int[dx];
        dO = new int[dx];
        dP = new int[dx];
        dQ = new int[dx];
        dy = new String[dx][];
    }

    private static void y(int n) {
        if (ev == null) {
            ev = new Image[n][2];
            int n2 = 0;
            while (n2 < n) {
                int n3 = 0;
                while (n3 < 2) {
                    try {
                        a.ev[n2][n3] = Image.createImage((String)("/cp/f" + n2 + "h" + n3 + ".png"));
                    }
                    catch (Exception exception) {}
                    ++n3;
                }
                ++n2;
            }
        }
    }

    private static void a(int n, int n2, int n3, int n4, int n5, int n6, int n7, String[] stringArray) {
        a.dC[n] = n3 * 24 + (n << 2);
        a.dD[n] = n4 * 24;
        a.dE[n] = 0;
        a.dF[n] = 0;
        a.dG[n] = 1;
        a.dM[n] = -1;
        a.dH[n] = 0;
        a.dI[n] = (n + 1) * 20 + n;
        a.dJ[n] = 0;
        a.dK[n] = n2;
        a.dL[n] = -1;
        a.dO[n] = -1;
        a.dP[n] = n6 * 24 + 12;
        a.dQ[n] = n7 * 24 + 12;
        a.dN[n] = n5;
        a.dy[n] = stringArray;
    }

    private static void K() {
        String string;
        d = 2;
        a.ak();
        Midlet.d = 3;
        v = 99;
        try {
            eD = Image.createImage((String)"/u/tt.png");
        }
        catch (Exception exception) {}
        bf = aX - 30;
        bg = aX;
        bb = 30;
        bc = aX - 50;
        bd = be = bb + bc >> 1;
        if (Midlet.b()) {
            ba = 1;
        }
        if ((string = System.getProperty("microedition.platform")).endsWith("JAVASDK") || string.endsWith("wtk")) {
            Midlet.a = true;
        }
        new Thread(Midlet.c).start();
    }

    public static void h(int n) {
        cs = 0;
        if (n == 9) {
            cs = 6;
            boolean[] blArray = new boolean[7];
            blArray[2] = true;
            dw = blArray;
            a.L();
            a.b(0, 10, 12, 9, 12, 2);
            a.b(1, 7, 8, 6, 8, 2);
            a.b(2, 30, 8, 27, 33, 2);
            a.b(3, 29, 12, 27, 33, 2);
            a.b(4, 45, 5, 42, 50, 2);
            a.b(5, 64, 9, 62, 66, 2);
        }
        if (n == 10) {
            cs = 12;
            boolean[] blArray = new boolean[7];
            blArray[0] = true;
            blArray[2] = true;
            dw = blArray;
            a.L();
            a.b(0, 5, 8, 4, 6, 2);
            a.b(1, 6, 12, 5, 9, 0);
            a.b(2, 16, 12, 9, 21, 2);
            a.b(3, 28, 15, 26, 29, 0);
            a.b(4, 25, 15, 22, 27, 2);
            a.b(5, 16, 25, 14, 17, 0);
            a.b(6, 4, 29, 2, 6, 0);
            a.b(7, 8, 38, 2, 28, 2);
            a.b(8, 14, 38, 2, 28, 2);
            a.b(9, 20, 38, 2, 28, 2);
            a.b(10, 31, 30, 28, 37, 2);
            a.b(11, 33, 30, 28, 37, 0);
        }
        if (n == 11) {
            cs = 8;
            boolean[] blArray = new boolean[7];
            blArray[0] = true;
            blArray[1] = true;
            blArray[2] = true;
            dw = blArray;
            a.L();
            a.b(0, 6, 11, 3, 7, 1);
            a.b(1, 19, 12, 18, 20, 0);
            a.b(2, 33, 8, 28, 36, 2);
            a.b(3, 53, 11, 53, 54, 1);
            a.b(5, 54, 11, 53, 54, 0);
            a.b(4, 68, 12, 62, 79, 2);
            a.b(6, 71, 12, 70, 72, 0);
            a.b(7, 72, 8, 70, 73, 1);
        }
        if (n == 16) {
            cs = 4;
            boolean[] blArray = new boolean[7];
            blArray[3] = true;
            dw = blArray;
            a.L();
            a.b(0, 5, 11, 3, 10, 3);
            a.b(1, 26, 12, 24, 29, 3);
            a.b(2, 47, 10, 30, 55, 3);
            a.b(3, 60, 11, 56, 64, 3);
        }
        if (n == 17) {
            cs = 8;
            boolean[] blArray = new boolean[7];
            blArray[3] = true;
            blArray[4] = true;
            dw = blArray;
            a.L();
            a.b(0, 11, 8, 9, 13, 3);
            a.b(1, 20, 6, 18, 21, 4);
            a.b(2, 20, 11, 18, 21, 3);
            a.b(3, 26, 3, 24, 29, 3);
            a.b(4, 26, 8, 24, 29, 4);
            a.b(5, 26, 13, 24, 29, 4);
            a.b(6, 33, 6, 32, 35, 3);
            a.b(7, 33, 10, 32, 35, 4);
        }
        if (n == 18) {
            cs = 8;
            boolean[] blArray = new boolean[7];
            blArray[4] = true;
            blArray[5] = true;
            blArray[6] = true;
            dw = blArray;
            a.L();
            a.b(0, 2, 8, 1, 4, 5);
            a.b(1, 2, 12, 1, 5, 6);
            a.b(2, 18, 12, 12, 23, 6);
            a.b(3, 18, 8, 15, 21, 5);
            a.b(4, 42, 9, 38, 45, 4);
            a.b(5, 42, 13, 34, 67, 6);
            a.b(6, 62, 13, 34, 67, 6);
            a.b(7, 60, 9, 57, 64, 4);
        }
    }

    private static void L() {
        int n = 0;
        if (ex == null) {
            try {
                ex = new Image[4];
                n = 0;
                while (n < 4) {
                    a.ex[n] = Image.createImage((String)("/mob/x" + n + ".png"));
                    ++n;
                }
                ew = new Image[7][2];
                n = 0;
                while (n < 7) {
                    if (dw[n]) {
                        int n2 = 0;
                        while (n2 < 2) {
                            a.ew[n][n2] = Image.createImage((String)("/mob/y" + n + n2 + ".png"));
                            ++n2;
                        }
                    }
                    ++n;
                }
            }
            catch (Exception exception) {}
        }
        cv = new int[cs];
        cw = new int[cs];
        cx = new int[cs];
        cy = new int[cs];
        cz = new int[cs];
        cA = new int[cs];
        cG = new int[cs];
        cH = new int[cs];
        cI = new int[cs];
        cJ = new int[cs];
        cK = new int[cs];
        cL = new int[cs];
        cM = new int[cs];
        cF = new int[cs];
        cN = new int[cs];
        cO = new int[cs];
        cB = new int[cs];
        cC = new int[cs];
        cD = new int[cs];
        cE = new int[cs];
        cu = new int[cs];
        ct = new int[cs];
    }

    private static void b(int n, int n2, int n3, int n4, int n5, int n6) {
        a.cv[n] = n2 * 24 + 12;
        a.cw[n] = n3 * 24;
        a.cx[n] = cv[n];
        a.cy[n] = cw[n];
        a.cz[n] = cx[n];
        a.cA[n] = cy[n] - 9;
        a.cG[n] = n6;
        a.cH[n] = 3;
        a.cI[n] = n << 1;
        a.cJ[n] = 0;
        a.cK[n] = 1;
        a.cL[n] = 0;
        a.cM[n] = 0;
        a.cF[n] = 0;
        a.cN[n] = n4 * 24 + 12;
        a.cO[n] = n5 * 24 + 12;
        a.cu[n] = af[cG[n]];
    }

    public static void i(int n) {
        int n2;
        hd = 1200;
        hc = 1200;
        gR = 30;
        gQ = false;
        if (n == 4) {
            gQ = true;
            if (ey == null) {
                ey = new Image[5];
                n2 = 0;
                while (n2 < 5) {
                    try {
                        a.ey[n2] = Image.createImage((String)("/mob/b0" + n2 + ".png"));
                    }
                    catch (Exception exception) {}
                    ++n2;
                }
            }
            gY = ey[0].getWidth();
            gZ = ey[0].getHeight();
            gS = 55 * jh + 12;
            gT = 13 * jh;
            gV = 1;
            gW = -1;
            gX = 0;
        }
        if (n == 5) {
            gQ = true;
            if (ey == null) {
                ey = new Image[5];
                n2 = 0;
                while (n2 < 5) {
                    try {
                        a.ey[n2] = Image.createImage((String)("/mob/b0" + n2 + ".png"));
                    }
                    catch (Exception exception) {}
                    ++n2;
                }
            }
            gY = ey[0].getWidth();
            gZ = ey[0].getHeight();
            gS = 8 * jh + 12;
            gT = 56 * jh;
            gV = 1;
            gW = -1;
            gX = 0;
        }
    }

    public static void p() {
        cP = 5;
        if (ez == null) {
            try {
                Image[] imageArray = new Image[2];
                ez = imageArray;
                imageArray[0] = Image.createImage((String)"/i/pt0.png");
                a.ez[1] = Image.createImage((String)"/i/pt1.png");
            }
            catch (Exception exception) {}
        }
        cR = new int[cP];
        cS = new int[cP];
        cT = new int[cP];
        cU = new int[cP];
        cV = new int[cP];
        cW = new int[cP];
        cX = new int[cP];
        cQ = new int[cP];
    }

    public static void j(int n) {
        hM = false;
        if (n == 97 && !eI) {
            hM = true;
            eA = new Image[4];
            n = 0;
            while (n < 4) {
                try {
                    a.eA[n] = Image.createImage((String)("/mob/b1" + n + ".png"));
                }
                catch (IOException iOException) {}
                ++n;
            }
            hS = 168;
            hT = 226;
            ic = 144;
            id = 480;
            ia = 2;
            ib = -1;
            hQ = 8000;
            hP = 8000;
        }
    }

    public static final void q() {
        if (eB == null) {
            eB = new Image[3];
            int n = 0;
            while (n < 3) {
                try {
                    a.eB[n] = Image.createImage((String)("/e/sp" + n + ".png"));
                }
                catch (IOException iOException) {}
                ++n;
            }
        }
        cd = new int[2];
        ce = new int[2];
        cf = new int[2];
        cg = new int[2];
        ch = new int[2];
        a.cf[1] = -1;
        a.cf[0] = -1;
    }

    public static void k(int n) {
        hl = false;
        if (n == 95 && !eK) {
            hl = true;
            eC = new Image[6];
            try {
                a.eC[0] = Image.createImage((String)"/mob/b20.png");
                a.eC[1] = Image.createImage((String)"/mob/b21.png");
                a.eC[2] = Image.createImage((String)"/mob/b22.png");
                a.eC[3] = eC[1];
                a.eC[4] = Image.createImage((String)"/mob/b23.png");
                a.eC[5] = Image.createImage((String)"/cp/f2h0.png");
            }
            catch (IOException iOException) {}
            hs = 192;
            ht = 264;
            hw = 120;
            hx = 504;
            hu = 0;
            hv = -1;
            hq = 90000;
            hp = 90000;
            hG = new int[6];
            hH = new int[6];
            hK = new int[6];
            hL = new int[6];
            int[] nArray = new int[6];
            nArray[0] = 20;
            nArray[1] = 10;
            nArray[3] = -10;
            nArray[4] = -16;
            nArray[5] = 2;
            hI = nArray;
            int[] nArray2 = new int[6];
            nArray2[0] = -16;
            nArray2[2] = -12;
            nArray2[4] = -18;
            nArray2[5] = -42;
            hJ = nArray2;
            int[][] nArrayArray = new int[2][];
            nArrayArray[0] = new int[6];
            int[] nArray3 = new int[6];
            nArray3[2] = 1;
            nArray3[4] = 1;
            nArray3[5] = 1;
            nArrayArray[1] = nArray3;
            hA = nArrayArray;
            int[][] nArrayArray2 = new int[2][];
            nArrayArray2[0] = new int[6];
            int[] nArray4 = new int[6];
            nArray4[0] = 1;
            nArray4[2] = 1;
            nArray4[4] = 2;
            nArray4[5] = 1;
            nArrayArray2[1] = nArray4;
            hB = nArrayArray2;
            hC = new int[][]{{6, -1, 1, 1, 5, 1}, {14, -1, 2, 1, 12, 2}};
            int[][] nArrayArray3 = new int[2][];
            int[] nArray5 = new int[6];
            nArray5[0] = 1;
            nArray5[4] = 1;
            nArrayArray3[0] = nArray5;
            int[] nArray6 = new int[6];
            nArray6[0] = 2;
            nArray6[2] = 1;
            nArray6[4] = 2;
            nArray6[5] = 1;
            nArrayArray3[1] = nArray6;
            hD = nArrayArray3;
            int[][] nArrayArray4 = new int[3][];
            int[] nArray7 = new int[6];
            nArray7[0] = -3;
            nArray7[1] = 3;
            nArray7[3] = -3;
            nArray7[4] = 3;
            nArrayArray4[0] = nArray7;
            nArrayArray4[1] = new int[6];
            int[] nArray8 = new int[6];
            nArray8[0] = 3;
            nArray8[1] = -3;
            nArray8[3] = 3;
            nArray8[4] = -3;
            nArrayArray4[2] = nArray8;
            hE = nArrayArray4;
            int[][] nArrayArray5 = new int[3][];
            int[] nArray9 = new int[6];
            nArray9[0] = -1;
            nArray9[1] = -2;
            nArray9[2] = 1;
            nArray9[4] = 1;
            nArray9[5] = 1;
            nArrayArray5[0] = nArray9;
            nArrayArray5[1] = new int[6];
            int[] nArray10 = new int[6];
            nArray10[0] = 1;
            nArray10[2] = 1;
            nArray10[3] = -2;
            nArray10[4] = -1;
            nArray10[5] = 1;
            nArrayArray5[2] = nArray10;
            hF = nArrayArray5;
        }
    }

    public static void l(int n) {
        hf = false;
        if (n == 96 && !eJ) {
            hf = true;
            dx = 1;
            a.J();
            a.a(0, 2, 5, 11, 200, 4, 22, null);
            a.y(3);
            hk = 15000;
            hj = 15000;
            hi = 120;
            dx = 0;
            a.dG[0] = 0;
            a.dM[0] = 1;
        }
    }

    public static void m(int n) {
        if (n == 94) {
            dz = true;
            fh = 24;
            fi = 288;
            gp = 94;
            gq = 12;
            gr = 288;
            gC = 1;
            gn = false;
            fp = 1;
        }
        if (n == 0 && O == 15 && fD) {
            gp = -1;
            gn = false;
        }
        if (n == 0 && O == 16 && fe) {
            fd = true;
            gp = 0;
            gn = true;
            fx = true;
            O = -1;
            fh = 984;
            fi = 408;
            return;
        }
        if (n == 95 && !eK) {
            gn = false;
            gq = 599;
            gr = 264;
            gC = -1;
            gp = 95;
        }
    }

    public static void n(int n) {
        int n2;
        ih = false;
        fV = false;
        fK = 0;
        if (n == 15 && O == 15) {
            a.e(216, 432);
        }
        if (n == 8 && O == 6) {
            a.e(72, 240);
        }
        if (n == 4 && eH) {
            T = true;
            eL = true;
            eT = true;
            eS = true;
            H = 1000;
            G = 1000;
            J = 2000;
            I = 2000;
            E = 25;
            A = 4;
            hd = 500;
            hc = 500;
            a.D(0);
        } else if (n == 99) {
            eH = true;
            dz = true;
            fh = 108;
            fi = 312;
            fp = -1;
            a.D(1);
        } else if (n == 94) {
            a.D(10);
        } else if (n == 0 && eH) {
            T = false;
            eL = false;
            eS = false;
            eT = false;
            fA = false;
            G = 200;
            H = 200;
            I = 200;
            J = 200;
            hd = 1000;
            hc = 1000;
            N = 0;
            M = 1;
            E = 20;
            fh = 864;
            fi = 336;
            n2 = 0;
            while (n2 < 19) {
                a.W[n2] = false;
                ++n2;
            }
            a.W[0] = true;
            fp = -1;
            dz = false;
            dA = false;
            a.D(2);
            eH = false;
        } else if (n == 3 && O == 3 && !gn) {
            a.D(3);
        } else if (n == 0 && O == 3 && gn) {
            a.D(4);
        } else if (n == 97 && !eI) {
            a.D(5);
        } else if (n == 96 && !eJ) {
            a.D(6);
        } else if (n == 95 && !eK) {
            a.D(7);
        } else if (n == 101) {
            a.D(8);
            fS = fM = 336 - r;
            fL = 24;
            fR = 24;
        } else if (n == 9 && O == 10) {
            a.D(9);
        }
        if (n == 97 && eI) {
            n2 = 0;
            while (n2 <= 6) {
                a.i(24, n2, 0);
                ++n2;
            }
        }
        if (n == 96 && eJ) {
            n2 = 0;
            while (n2 <= 11) {
                a.i(24, n2, 0);
                ++n2;
            }
        }
    }

    public static final void r() {
        if (a) {
            return;
        }
        eG = new Image[3];
        int n = 0;
        while (n < 3) {
            try {
                a.eG[n] = Image.createImage((String)("/e/w" + n + ".png"));
            }
            catch (IOException iOException) {}
            ++n;
        }
        ci = new int[2];
        cj = new int[2];
        ck = new int[2];
        cl = new int[2];
        a.ck[1] = -1;
        a.ck[0] = -1;
    }

    private static void z(int n) {
        if (dD[0] == fi && a.a(dC[0] + (dM[0] << 5) - fh) < 25) {
            a.a(hi, dM[0]);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void A(int n) {
        int n2 = E;
        int n3 = -dM[n];
        int n4 = n2;
        int n5 = dO[n];
        if (jS[n5] == 2 || jS[n5] == 3 || jS[n5] == 4) {
            int n6 = n5;
            kc[n6] = kc[n6] - n4;
            if (kc[n5] <= 0) {
                a.kc[n5] = 0;
                a.jX[n5] = -5;
                a.jY[n5] = -(n3 << 3);
                a.jZ[n5] = 0;
                a.jR[n5] = 0;
                a.jS[n5] = 1;
                return;
            }
            a.jX[n5] = 0;
            a.kb[n5] = n3;
            a.jS[n5] = 6;
            a.ka[n5] = 3;
        }
        boolean bl = false;
        if (bl) return;
        a.f(jN[dO[n]], jO[dO[n]] - (jP[dO[n]] >> 1), kb[dO[n]]);
        a.a("-" + n2, 0, jN[dO[n]], jO[dO[n]] - jP[dO[n]], 0, -2);
    }

    private static void B(int n) {
        int n2 = 0;
        while (n2 < jK) {
            if (jS[n2] != 5 && jO[n2] == dD[n] && a.a(dC[n] + (dM[n] << 3) - jN[n2]) < 20) {
                a.dM[n] = jN[n2] < dC[n] ? -1 : 1;
                a.dG[n] = 5;
                a.dI[n] = 0;
                a.dH[n] = 0;
                a.dO[n] = n2;
            }
            ++n2;
        }
    }

    private static void M() {
        if (iL < iH[iF].length) {
            iL = iH[iF].length;
            iK = 0;
            return;
        }
        ++iF;
        iL = 0;
        iK = 0;
    }

    private static void a(Graphics graphics) {
        if (b && !a) {
            graphics.setColor(i);
            graphics.fillRect(0, 0, q, p);
            int n = -((fR >> 1) % 24);
            while (n < q) {
                graphics.drawImage(s[0], n, j, 0);
                n += 24;
            }
            n = -((fR >> 2) % 24);
            while (n < q) {
                graphics.drawImage(s[1], n, k, 0);
                n += 24;
            }
            n = -((fR >> 4) % 64);
            while (n < q) {
                graphics.drawImage(s[3], n, m, 0);
                n += 64;
            }
            graphics.drawImage(u, g, h, 3);
            n = 0;
            while (n < 2) {
                graphics.drawImage(t, e[n], f[n], 3);
                ++n;
            }
            n = -((fR >> 3) % 192);
            while (n < q) {
                graphics.drawImage(s[2], n, l, 0);
                n += 192;
            }
            return;
        }
        graphics.setColor(i);
        graphics.fillRect(0, 0, q, r);
        if (a) {
            return;
        }
        graphics.drawImage(u, g, h, 3);
        int n = 0;
        while (n < 2) {
            graphics.drawImage(t, e[n], f[n], 3);
            ++n;
        }
    }

    private static void b(Graphics graphics) {
        if (fz) {
            B = w % 20 > 2 ? 25 : 26;
        }
        if (fp == 1) {
            graphics.drawImage(er[as[B][0][0]], fh + as[B][0][1], fi - as[B][0][2], 0);
            graphics.drawImage(et[as[B][1][0]], fh + as[B][1][1], fi - as[B][1][2], 0);
            graphics.drawImage(eg[as[B][2][0]], fh + as[B][2][1], fi - as[B][2][2], 0);
            if (ff != -1) {
                graphics.drawImage(eE[ff], fh - at[ff], fi - au[ff], 0);
                return;
            }
        } else {
            graphics.drawRegion(er[as[B][0][0]], 0, 0, er[as[B][0][0]].getWidth(), er[as[B][0][0]].getHeight(), 2, fh - as[B][0][1], fi - as[B][0][2], 24);
            graphics.drawRegion(et[as[B][1][0]], 0, 0, et[as[B][1][0]].getWidth(), et[as[B][1][0]].getHeight(), 2, fh - as[B][1][1], fi - as[B][1][2], 24);
            graphics.drawRegion(eg[as[B][2][0]], 0, 0, eg[as[B][2][0]].getWidth(), eg[as[B][2][0]].getHeight(), 2, fh - as[B][2][1], fi - as[B][2][2], 24);
            if (ff != -1) {
                graphics.drawRegion(eE[ff], 0, 0, eE[ff].getWidth(), eE[ff].getHeight(), 2, fh + at[ff], fi - au[ff], 24);
            }
        }
    }

    private static void c(Graphics graphics) {
        if (bh) {
            return;
        }
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.drawImage(er[0], 0, 2, 0);
        graphics.setColor(0);
        graphics.drawRect(23, 2, br + 1, 4);
        graphics.drawRect(23, 8, br + 1, 4);
        graphics.drawRect(23, 14, br + 1, 3);
        graphics.setColor(0xFF0000);
        int n = G * br / H;
        graphics.fillRect(24, 3, n, 3);
        graphics.setColor(0x550000);
        graphics.fillRect(n + 24, 3, br - n, 3);
        graphics.setColor(255);
        n = I * br / J;
        graphics.fillRect(24, 9, n, 3);
        graphics.setColor(0x550000);
        graphics.fillRect(n + 24, 9, br - n, 3);
        graphics.setColor(7274435);
        n = N * br / ak[M - 1];
        graphics.fillRect(24, 15, n, 2);
        graphics.setColor(19506);
        graphics.fillRect(n + 24, 15, br - n, 2);
        Midlet.a(graphics, "lv:" + M, 2, bt, 7, 0);
        Midlet.a(graphics, String.valueOf(dg) + "$", 1, bu, 7, 0);
    }

    private static void d(Graphics graphics) {
        c = false;
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(-1, bj - 1, bk + 2, 100);
        graphics.setColor(0);
        if (!bh) {
            graphics.fillRect(0, bj, bk, 15);
            Midlet.a(graphics, n, 3, bj + 3, 0);
            Midlet.a(graphics, o, bk - 3, bj + 3, 1);
            return;
        }
        graphics.fillRect(0, bj + 33, bk, 15);
        Midlet.a(graphics, n, 3, bj + 36, 0);
        Midlet.a(graphics, o, bk - 3, bj + 36, 1);
        graphics.setColor(4352);
        graphics.fillRect(bm, bj, bo, 1);
        graphics.fillRect(bm, bj + 35, bo, 1);
        graphics.fillRect(bm, bj + 33, bo, 1);
        graphics.fillRect(bm, bj + 30, bo, 1);
        graphics.fillRect(bm, bj + 28, bo, 1);
        graphics.fillRect(bm, bj + 26, bo, 1);
        graphics.fillRect(bm, bj + 12, bo, 1);
        graphics.fillRect(bm, bj + 24, bo, 1);
        graphics.fillRect(bm, bj + 18, bo, 1);
        graphics.fillRect(bm, bj + 16, bo, 1);
        graphics.fillRect(bm, bj + 2, bo, 1);
        graphics.fillRect(bm, bj + 4, bo, 1);
        graphics.fillRect(bm, bj + 6, bo, 1);
        graphics.fillRect(bm, bj + 14, bo, 1);
        graphics.setColor(0xEECC33);
        graphics.fillRect(bm, bj + 1, bo, 1);
        graphics.fillRect(bm, bj + 5, bo, 1);
        graphics.fillRect(bm, bj + 17, bo, 1);
        graphics.fillRect(bm, bj + 34, bo, 1);
        graphics.fillRect(bm, bj + 29, bo, 1);
        graphics.setColor(16767871);
        graphics.fillRect(bm, bj + 3, bo, 1);
        graphics.fillRect(bm, bj + 15, bo, 1);
        graphics.fillRect(bm, bj + 27, bo, 1);
        graphics.setColor(0x333333);
        graphics.fillRect(bm, bj + 7, bo, 5);
        graphics.fillRect(bm, bj + 19, bo, 5);
        graphics.fillRect(bm, bj + 31, bo, 2);
        graphics.setColor(0xBB6611);
        graphics.fillRect(bm, bj + 25, bo, 1);
        graphics.fillRect(bm, bj + 13, bo, 1);
        graphics.drawImage(ei[0], 0, bj, 0);
        graphics.drawImage(ei[1], 0 + bk, bj, 24);
        int n = G * br / H;
        graphics.setColor(0x770000);
        graphics.fillRect(bp, bq, n, 2);
        graphics.setColor(0xCC0000);
        graphics.fillRect(bp, bq + 1, n, 4);
        n = I * br / J;
        graphics.setColor(119);
        graphics.fillRect(bp, bq + 12, n, 2);
        graphics.setColor(204);
        graphics.fillRect(bp, bq + 14, n, bv - 2);
        Midlet.a(graphics, "" + dg, 1, bk - 18, bj + 6, 2);
        n = N * bs / ak[M - 1];
        graphics.setColor(94373);
        graphics.fillRect(31, bj + 31, n, 1);
        graphics.setColor(65535);
        graphics.fillRect(31, bj + 32, n, 1);
        Midlet.a(graphics, "" + M, 2, 26, bj + 11, 2);
        graphics.drawImage(eF[fg], 43, bj + 16, 3);
        Midlet.a(graphics, "" + (V[0] + V[1] + V[2]), 2, bk - 29, bj + 19, 2);
        Midlet.a(graphics, "" + (V[3] + V[4] + V[5]), 2, bk - 9, bj + 19, 2);
    }

    private static void e(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(-1, -1, aW + 2, aX + 2);
        graphics.setColor(0);
        graphics.fillRect(0, 0, q, bx);
        if (bh) {
            graphics.fillRect(0, aX - bx + 1, q, bx);
        } else {
            graphics.fillRect(0, aX - 13, aW, 13);
        }
        if (fY) {
            int n = 0;
            while (n < fZ.length) {
                Midlet.a(graphics, fZ[n], bF, ga + (n << 4), 2);
                ++n;
            }
        }
    }

    private static void f(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(-1, -1, aW + 2, aX + 2);
        iD = iH[iF].length;
        int n = iJ - fS - (iD << 4);
        boolean bl = true;
        if (n < 10) {
            bl = false;
            n = 10;
        }
        graphics.setColor(0xFFFFFF);
        if (a) {
            graphics.fillRect(iN, n - 5, iM, (iD << 4) + 7);
        } else {
            graphics.fillRoundRect(iN, n - 5, iM, (iD << 4) + 7, 10, 10);
        }
        graphics.setColor(17408);
        if (a) {
            graphics.drawRect(iN - 1, n - 5, iM + 1, (iD << 4) + 7);
        } else {
            graphics.drawRoundRect(iN - 1, n - 5, iM + 1, (iD << 4) + 7, 10, 10);
        }
        int n2 = 0;
        while (n2 < iL) {
            Midlet.b(graphics, iH[iF][n2], iN + (iM >> 1), n + (n2 << 4), 2);
            ++n2;
        }
        if (iL < iH[iF].length) {
            Midlet.b(graphics, iH[iF][iL].substring(0, iK), iN + (iM >> 1), n + (iL << 4), 2);
        }
        graphics.setColor(0);
        graphics.fillRect(0, bj + 33, bk, 15);
        if (bl && iI > fR + 10 && iI < fR + q - 10) {
            graphics.drawImage(ek, iI - fR, iJ - fS + 2, 17);
        }
        if (w % 10 < 8) {
            Midlet.a(graphics, "Ti\u1ebfp t\u1ee5c", bF, aX - 13, 2);
        }
    }

    private static void g(Graphics graphics) {
        int n = 0;
        while (n < dx) {
            if (dC[n] + fl >= fR && dC[n] - fl <= fR + q && dD[n] >= fS && dD[n] - fk <= fS + r) {
                if (dM[n] == 1) {
                    graphics.drawImage(ev[dK[n]][aJ[dJ[n]][0][0]], dC[n] + aJ[dJ[n]][0][1], dD[n] - aJ[dJ[n]][0][2], 0);
                    graphics.drawImage(et[aJ[dJ[n]][1][0]], dC[n] + aJ[dJ[n]][1][1], dD[n] - aJ[dJ[n]][1][2], 0);
                    graphics.drawImage(eg[aJ[dJ[n]][2][0]], dC[n] + aJ[dJ[n]][2][1], dD[n] - aJ[dJ[n]][2][2], 0);
                    if (dL[n] != -1) {
                        graphics.drawImage(eE[dL[n]], dC[n] - at[dL[n]], dD[n] - au[dL[n]], 0);
                    }
                } else {
                    graphics.drawRegion(ev[dK[n]][aJ[dJ[n]][0][0]], 0, 0, ev[dK[n]][aJ[dJ[n]][0][0]].getWidth(), ev[dK[n]][aJ[dJ[n]][0][0]].getHeight(), 2, dC[n] - aJ[dJ[n]][0][1], dD[n] - aJ[dJ[n]][0][2], 24);
                    graphics.drawRegion(et[aJ[dJ[n]][1][0]], 0, 0, et[aJ[dJ[n]][1][0]].getWidth(), et[aJ[dJ[n]][1][0]].getHeight(), 2, dC[n] - aJ[dJ[n]][1][1], dD[n] - aJ[dJ[n]][1][2], 24);
                    graphics.drawRegion(eg[aJ[dJ[n]][2][0]], 0, 0, eg[aJ[dJ[n]][2][0]].getWidth(), eg[aJ[dJ[n]][2][0]].getHeight(), 2, dC[n] - aJ[dJ[n]][2][1], dD[n] - aJ[dJ[n]][2][2], 24);
                    if (dL[n] != -1) {
                        graphics.drawRegion(eE[dL[n]], 0, 0, eE[dL[n]].getWidth(), eE[dL[n]].getHeight(), 2, dC[n] + at[dL[n]], dD[n] - au[dL[n]], 24);
                    }
                }
            }
            ++n;
        }
    }

    private static void h(Graphics graphics) {
        graphics.setClip(-1, -1, aW + 2, aX + 1);
        a.a(graphics);
        graphics.translate(-fR, -fS);
        if (a) {
            a.j(graphics);
        } else {
            a.k(graphics);
        }
        a.i(graphics);
        a.g(graphics);
        a.b(graphics);
        a.r(graphics);
        a.l(graphics);
        a.e(graphics);
        if (iC) {
            a.f(graphics);
        }
    }

    private static void N() {
        if (!fz && A != 14 && a.a(fi - gT) < 30 && a.a(fh - gS) < 200) {
            int n;
            gV = 5;
            gW = gS < fh ? 1 : -1;
            hb = n = fh + (gW << 5);
            gU = gW * 6;
            if (hc < hd >> 1) {
                gU = gW << 3;
            }
        }
    }

    private static void d(int n, int n2, int n3) {
        il = n + 1;
        a.F();
        a.b(0, n2, n3);
        if (n == 1) {
            a.b(1, fh, fi);
            im = 1;
            a.ip[a.im] = fp;
        }
    }

    private static void b(int n, int n2, int n3, int n4, int n5) {
        int n6 = -1;
        int n7 = 0;
        while (n7 < bP) {
            if (bS[n7] == -1) {
                n6 = n7;
                break;
            }
            ++n7;
        }
        if (n6 == -1) {
            return;
        }
        a.bS[n6] = 1;
        a.bQ[n6] = n;
        a.bR[n6] = n2;
        a.bT[n6] = n3;
        a.bU[n6] = n4;
        a.bV[n6] = n5;
    }

    private static void a(int n, int n2, int n3, int n4) {
        ge = n;
        gf = n2;
        gg = n3;
        gm = n4;
    }

    private static void c(int n, int n2, int n3, int n4, int n5) {
        int n6 = -1;
        int n7 = 0;
        while (n7 < bW) {
            if (bZ[n7] == 0) {
                n6 = n7;
                break;
            }
            ++n7;
        }
        if (n6 == -1) {
            return;
        }
        a.bZ[n6] = 1;
        a.bX[n6] = n;
        a.bY[n6] = n2;
        a.ca[n6] = n3;
        a.cb[n6] = n4;
        a.cc[n6] = n5;
    }

    private static void e(int n, int n2) {
        fI = n;
        fJ = n2;
        fK = 1;
    }

    private static void C(int n) {
        gc = 0;
        fW = n;
        fX = 0;
        fV = true;
        gd = false;
        a.c();
        a.d();
    }

    private static void a(String[] stringArray, int n, int n2, int n3) {
        iG = n;
        iI = n2;
        iJ = n3;
        iE = stringArray.length;
        iF = 0;
        iH = new String[iE][];
        iM = q >> 1;
        if (iM < 120) {
            iM = 120;
        }
        n = 0;
        while (n < iE) {
            a.iH[n] = Midlet.b(stringArray[n], iM - 20);
            ++n;
        }
        iN = iI - (iM >> 1) - fR;
        if (iN < 2) {
            iN = 2;
        }
        if (iN + iM > q - 2) {
            iN = q - 2 - iM;
        }
        iC = true;
        iK = 0;
        iL = 0;
    }

    private static boolean e(int n, int n2, int n3) {
        if (a) {
            return false;
        }
        int n4 = n = n == 1 ? 0 : 1;
        if (co[n] != -1) {
            return false;
        }
        a.co[n] = 0;
        a.cm[n] = n2;
        a.cn[n] = n3;
        return true;
    }

    private static void a(int n, int n2, int n3, int n4, boolean bl) {
        int n5 = -1;
        int n6 = 0;
        while (n6 < cY) {
            if (db[n6] == -1) {
                n5 = n6;
                break;
            }
            ++n6;
        }
        if (n5 == -1) {
            return;
        }
        a.db[n5] = 1;
        a.cZ[n5] = n;
        a.da[n5] = n2;
        a.dd[n5] = n3;
        a.de[n5] = n4;
        a.df[n5] = bl;
    }

    private static void a(String string, int n, int n2, int n3, int n4, int n5) {
        n4 = -1;
        int n6 = 0;
        while (n6 < 5) {
            if (dW[n6] == -1) {
                n4 = n6;
                break;
            }
            ++n6;
        }
        if (n4 == -1) {
            return;
        }
        a.dR[n4] = string;
        a.dS[n4] = n2;
        a.dT[n4] = n3;
        a.dU[n4] = 0;
        a.dV[n4] = n5;
        a.dW[n4] = 0;
        a.dX[n4] = n;
    }

    private static void f(int n, int n2) {
        ea = 1;
        dY = n;
        dZ = n2;
    }

    private static void c(String string) {
        cr = string;
        cq = 0;
        cp = true;
    }

    private static boolean f(int n, int n2, int n3) {
        int n4;
        int n5 = n4 = cf[0] == -1 ? 0 : 1;
        if (cf[n4] != -1) {
            return false;
        }
        a.cf[n4] = 0;
        a.ch[n4] = n3;
        a.cd[n4] = n;
        a.ce[n4] = n2;
        return true;
    }

    private static void D(int n) {
        ii = 0;
        ig = n;
        ih = true;
    }

    private static boolean g(int n, int n2) {
        int n3;
        if (a) {
            return false;
        }
        int n4 = n3 = ck[0] == -1 ? 0 : 1;
        if (ck[n3] != -1) {
            return false;
        }
        a.ck[n3] = 0;
        a.ci[n3] = n;
        a.cj[n3] = n2;
        return true;
    }

    private static void O() {
        int n = 0;
        while (n < bP) {
            if (bS[n] == 1) {
                int n2 = n;
                bQ[n2] = bQ[n2] + bT[n];
                if (bQ[n] < fR || bQ[n] > fR + q || bR[n] < fS || bR[n] > fS + r) {
                    a.bS[n] = -1;
                } else if (a.a(fh - bQ[n]) < fl && a.a(fi - fm - bR[n]) < fm) {
                    if (A == 5 && fg == 0 && bV[n] < 3) {
                        a.bT[n] = -bT[n];
                        int n3 = n;
                        bQ[n3] = bQ[n3] + bT[n];
                        return;
                    }
                    a.a(bU[n], bQ[n] < fh ? 1 : -1);
                    a.bS[n] = -1;
                    return;
                }
            }
            ++n;
        }
    }

    private static void P() {
        int n = 0;
        while (n < bW) {
            if (bZ[n] != 0) {
                int n2 = n;
                bX[n2] = bX[n2] + ca[n];
                int n3 = n;
                bY[n3] = bY[n3] + cb[n];
                int n4 = n;
                cb[n4] = cb[n4] + 1;
                if (bY[n] > fS + r) {
                    a.bZ[n] = 0;
                }
                if (a.a(fh - bX[n]) < fl && a.a(fi - fm - bY[n]) < fm) {
                    a.a(cc[n], bX[n] < fh ? 1 : -1);
                    a.bZ[n] = -1;
                    return;
                }
            }
            ++n;
        }
    }

    private static void Q() {
        if (fA) {
            aM = 0;
        }
    }

    private static void R() {
        if (fx) {
            fL = fh - bF + bI * fp;
            fM = fi - bH;
        }
        block1 : switch (A) {
            case 1: {
                if (++C > 30) {
                    C = 0;
                }
                B = C % 15 < 5 ? 0 : 1;
                a.S();
                int n = 0;
                while (n < dx) {
                    if (dG[n] == 1 && fi == dD[n] && a.a(fh - dC[n]) < 30 && fH == -1) {
                        o = "Tr\u00f2 chuy\u1ec7n";
                        c = true;
                        fH = n;
                        break;
                    }
                    ++n;
                }
                if (gp == v && gu == 1 && fi == gr && a.a(fh - gq) < 30) {
                    o = "N\u00f3i chuy\u1ec7n";
                    c = true;
                    fE = true;
                }
                n = 0;
                while (n < lx) {
                    if (fi == lz[n] && a.a(fh - ly[n]) < 30 && fG == -1) {
                        o = "Giao ti\u1ebfp";
                        c = true;
                        fG = n;
                        break block1;
                    }
                    ++n;
                }
                break;
            }
            case 2: {
                if (++C >= 10) {
                    C = 0;
                    fr = 0;
                    if (fa) {
                        fr = fp << 1;
                    }
                }
                B = (C >> 1) + 2;
                if (fp == 1) {
                    if (a.g(fh + fl, fi - fm, 4)) {
                        y = 0;
                        fh = a.I(fh + fl) - fl;
                    }
                } else if (a.g(fh - fl - 1, fi - fm, 8)) {
                    y = 0;
                    fh = a.I(fh - fl - 1) + jh + fl;
                }
                fh = (a.i(fh, fi - 1) & 0x40) == 64 ? (fh += y >> 1) : (fh += y);
                if (y > 0) {
                    --y;
                } else if (y < 0) {
                    ++y;
                } else {
                    A = 1;
                    fr = 0;
                }
                if (iu != -1) {
                    if (a.a(fh - iv[iu]) >= 20) {
                        iu = -1;
                        if (a.g(fh, fi, 2)) {
                            A = 1;
                        } else {
                            A = 4;
                            z = 2;
                            y = 3 * fp;
                            D = 0;
                        }
                    }
                } else if ((a.i(fh, fi) & 2) != 2) {
                    A = 4;
                    y = 3 * fp;
                    D = 0;
                }
                a.y();
                a.e(fp, fh - (fp << 3), fi);
                a.S();
                break;
            }
            case 3: {
                fh += y;
                if ((fi += z) < 0) {
                    fi = 0;
                    z = -1;
                }
                B = C == 0 ? 7 : 23;
                ++z;
                if (eL && !fA) {
                    if (z == -3) {
                        B = 8;
                    } else if (z == -2) {
                        B = 9;
                    } else if (z == -1) {
                        B = 10;
                    } else if (z == 0) {
                        B = 11;
                    }
                }
                if (z == 0) {
                    A = 4;
                    C = 0;
                    D = eL ? 1 : 0;
                    z = 1;
                    c = true;
                }
                if (fq < 0) {
                    ++fq;
                }
                if (fp == 1) {
                    if ((a.i(fh + fl, fi) & 4) != 4) break;
                    fh = a.I(fh + fl) - fl;
                    y = 0;
                    break;
                }
                if ((a.i(fh - fl, fi) & 8) != 8) break;
                fh = a.I(fh + 24 - fl) + fl;
                y = 0;
                break;
            }
            case 4: {
                int n;
                ff = -1;
                fh += y;
                fi += z;
                if (z < 18) {
                    ++z;
                }
                if (y > 0) {
                    --y;
                }
                B = 7;
                if (fi >= iS) {
                    A = 6;
                    G = 0;
                    break;
                }
                if (fi + 4 >= iS) {
                    A = 6;
                    G = 0;
                    break;
                }
                if (z > 4 && (a.i(fh, fi + 3) & 2) == 2) {
                    A = z > 12 ? 8 : 1;
                    z = 0;
                    y = 0;
                    D = 0;
                    C = 0;
                    fi = a.I(fi + 3);
                    a.e(-1, fh - -8, fi);
                    a.e(1, fh - 8, fi);
                    if (eP) {
                        n = 0;
                        while (n < il) {
                            if (fi == io[n] && a.a(fh - in[n]) < 16) {
                                fh = in[n];
                                A = 18;
                                im = n;
                                a.ip[n] = fp;
                                a.iq[n] = 0;
                            }
                            ++n;
                        }
                    }
                    if (O != 11 || fF >= 20) break;
                    fF = 0;
                    a.c("Hit: " + fF);
                    break;
                }
                if (a.g(fh, fi, 64) && fi % jh > 8) {
                    a.g(fh, a.H(fi) + 8);
                    fi = a.H(fi) + 8;
                    A = 11;
                    y = fp << 1;
                    z >>= 2;
                    fi = a.H(fi) + 12;
                    if (I == 0 || !eS) {
                        A = 12;
                    }
                }
                if (a.g(fh, fi, 4096)) {
                    A = 12;
                }
                if (D == 1 && !fA) {
                    if (z == 3) {
                        B = 11;
                    } else if (z == 2) {
                        B = 8;
                    } else if (z == 1) {
                        B = 9;
                    } else if (z == 0) {
                        B = 10;
                    }
                } else {
                    B = 23;
                }
                if (z > 6) {
                    B = 12;
                }
                if (fq < 0) {
                    ++fq;
                }
                if (fp == 1) {
                    if ((a.i(fh + fl, fi) & 4) == 4) {
                        fh = a.I(fh + fl) - fl;
                        y = 0;
                    }
                } else if ((a.i(fh - fl, fi) & 8) == 8) {
                    fh = a.I(fh + 24 - fl) + fl;
                    y = 0;
                }
                a.w();
                if (!gQ || z < 4 || a.a(fh - gS) >= 30 || a.a(fi - (gT - 25)) >= 5) break;
                z = -(z >> 1);
                y = fp << 3;
                C = 0;
                break;
            }
            case 5: {
                int n;
                ++C;
                if (fp == 1) {
                    if ((a.i(fh + fl, fi - fm) & 4) == 4) {
                        y = 0;
                    }
                } else if ((a.i(fh - fl, fi - fm) & 8) == 8) {
                    y = 0;
                }
                fh += y;
                if ((fi += z) < 0) {
                    z = 0;
                    fi = 0;
                }
                if (z == 0) {
                    if (iu == -1 && (a.i(fh, fi) & 2) != 2) {
                        A = 4;
                        ff = -1;
                        y = (P >> 1) * fp;
                        D = 0;
                        C = 0;
                    }
                    if (iu != -1 && a.a(fh - iv[iu]) >= 20) {
                        iu = -1;
                        if (a.g(fh, fi, 2)) {
                            A = 1;
                        } else {
                            A = 4;
                            z = 2;
                            y = 3 * fp;
                            ff = -1;
                            D = 0;
                        }
                    }
                } else if (z < 0) {
                    if (++z == 0) {
                        z = 1;
                    }
                } else {
                    if (z < 20 && C % 5 == 0) {
                        ++z;
                    }
                    if (z > 3) {
                        z = 3;
                    }
                    if (z > 2 && (a.i(fh, fi + 3) & 2) == 2) {
                        z = 0;
                        y = 0;
                        fi = a.I(fi + 3);
                        if (O == 11 && fF < 20) {
                            fF = 0;
                            a.c("Hit: " + fF);
                        }
                    }
                    if (a.g(fh, fi, 64) && fi % jh > 8) {
                        a.g(fh, a.H(fi) + 8);
                        A = 11;
                        y = fp << 1;
                        z >>= 2;
                        fi = a.H(fi) + 12;
                        ff = -1;
                        if (I != 0 && eS) break;
                        A = 12;
                        break;
                    }
                    if (a.g(fh, fi, 4096)) {
                        A = 12;
                        ff = -1;
                        break;
                    }
                    a.w();
                }
                if (y > 0) {
                    --y;
                } else if (y < 0) {
                    ++y;
                }
                if (y != 0) {
                    a.y();
                }
                block23 : switch (fw) {
                    case 0: {
                        switch (C) {
                            case 1: 
                            case 2: 
                            case 3: {
                                ff = -1;
                                B = 13;
                                break;
                            }
                            case 4: {
                                ff = 0;
                                B = 14;
                                break;
                            }
                            case 5: {
                                ff = 1;
                                B = 15;
                                break;
                            }
                            case 6: {
                                ff = 1;
                                B = 15;
                                a.u();
                                break;
                            }
                            case 7: {
                                ff = 2;
                                B = 16;
                                break;
                            }
                            case 8: {
                                ff = 2;
                                B = 16;
                                if (fy) {
                                    fy = false;
                                    fw = 2;
                                } else {
                                    fw = 1;
                                }
                                C = 1;
                            }
                        }
                        break;
                    }
                    case 3: {
                        switch (C) {
                            case 1: {
                                ff = 1;
                                B = 15;
                                break;
                            }
                            case 2: {
                                ff = 1;
                                B = 15;
                                break;
                            }
                            case 3: {
                                ff = 2;
                                B = 16;
                                break;
                            }
                            case 4: {
                                ff = 2;
                                B = 16;
                                if (fy) {
                                    fy = false;
                                    fw = 2;
                                } else {
                                    fw = 1;
                                }
                                C = 1;
                            }
                        }
                        break;
                    }
                    case 1: {
                        switch (C) {
                            case 1: {
                                ff = 1;
                                B = 15;
                                break;
                            }
                            case 2: {
                                ff = 1;
                                B = 15;
                                fw = 10;
                            }
                        }
                        break;
                    }
                    case 2: {
                        switch (C) {
                            case 1: 
                            case 2: 
                            case 3: {
                                ff = 3;
                                B = 17;
                                break;
                            }
                            case 4: {
                                ff = 4;
                                B = 18;
                                break;
                            }
                            case 5: {
                                ff = 5;
                                B = 19;
                                break;
                            }
                            case 6: {
                                ff = 6;
                                B = 20;
                                a.u();
                                break;
                            }
                            case 7: {
                                ff = 6;
                                B = 20;
                                if (fy && fv < 2) {
                                    if (z == 0 && fu) {
                                        C = 0;
                                        fy = false;
                                        fw = 9;
                                    } else {
                                        fy = false;
                                        fw = 3;
                                        ++fv;
                                    }
                                } else {
                                    fw = 10;
                                }
                                C = 1;
                            }
                        }
                        break;
                    }
                    case 9: {
                        switch (C) {
                            case 1: 
                            case 2: 
                            case 3: {
                                ff = -1;
                                B = 13;
                                break;
                            }
                            case 4: {
                                ff = 0;
                                B = 14;
                                break;
                            }
                            case 5: 
                            case 6: {
                                ff = 9;
                                B = 15;
                                n = a.A();
                                fu = false;
                                if (n == -1) break block23;
                                fu = true;
                                a.f(jN[n], jO[n] - (jP[n] >> 1), fp);
                                a.jS[n] = 9;
                                a.jX[n] = -8;
                                a.Q();
                                break;
                            }
                            case 7: 
                            case 8: {
                                ff = 10;
                                B = 16;
                                break;
                            }
                            case 9: {
                                ff = -1;
                                B = 16;
                                if (fu) {
                                    A = 3;
                                    y = 0;
                                    z = -8;
                                    break;
                                }
                                C = 0;
                                D = 0;
                                fw = 10;
                            }
                        }
                        break;
                    }
                    case 10: {
                        switch (C) {
                            case 1: {
                                ff = 2;
                                B = 16;
                                break block23;
                            }
                            case 2: {
                                ff = 0;
                                B = 14;
                                break block23;
                            }
                            case 3: 
                            case 4: {
                                B = 13;
                                ff = -1;
                                break block23;
                            }
                            case 5: {
                                B = 13;
                                ff = -1;
                                A = z != 0 ? 4 : 1;
                                fv = 0;
                                D = 0;
                            }
                        }
                    }
                }
                if (fg == 1) {
                    ff = -1;
                }
                if (A != 5) break;
                if (z != 0) {
                    B += 14;
                    break;
                }
                if (y == 0) break;
                B += 22;
                break;
            }
            case 6: {
                fh += y;
                if (y > 1) {
                    --y;
                }
                if (y < -1) {
                    ++y;
                }
                B = 21;
                if ((fi += ++z) < iS && fi <= fS + r + fk && !a.g(fh, fi - fk, 4096)) break;
                fh = fs;
                fi = ft;
                A = 4;
                G = H / 3;
                I = J / 3;
                z = 0;
                y = 0;
                D = 0;
                C = 0;
                N >>= 1;
                c = true;
                if (hf) {
                    hj = hk;
                }
                if (hl) {
                    hp = hq;
                }
                a.aw();
                a.s(5);
                d = 7;
                break;
            }
            case 7: {
                if ((a.i(fh + fl, fi - fm) & 4) == 4) {
                    y = -y;
                }
                if ((a.i(fh - fl, fi - fm) & 8) == 8) {
                    y = -y;
                }
                fh += y;
                fi += z;
                B = 21;
                if (y > 1) {
                    --y;
                }
                if (y < -1) {
                    ++y;
                }
                if (++z > 0) {
                    z = 0;
                }
                if (++C >= 7) {
                    A = 4;
                    fq = -20;
                    D = 0;
                    C = 0;
                    z = 0;
                }
                a.x();
                break;
            }
            case 8: {
                B = 22;
                if (C < 5) {
                    ++C;
                } else if (!aU[8]) {
                    A = 1;
                }
                if (++D <= 6 || !a.g(fh + fl, fi + 2, 8192) || !a.g(fh - fl, fi + 2, 8192) || !(eO & I > 0)) break;
                A = 13;
                break;
            }
            case 11: {
                a.U();
                break;
            }
            case 12: {
                fi += z;
                if (z < 20 && w % 2 == 0) {
                    ++z;
                }
                B = 7;
                if (fi >= iS) {
                    G = 0;
                    A = 6;
                    ff = -1;
                    break;
                }
                if (a.g(fh, fi, 2)) {
                    if (a.g(fh, (fi = a.H(fi)) - 2, 64)) {
                        A = 1;
                        z = 0;
                        y = 0;
                        D = 0;
                        C = 0;
                    } else if (eR) {
                        A = 17;
                        z = 0;
                        y = 0;
                        D = 0;
                        C = 0;
                    } else {
                        A = 6;
                    }
                    fi = a.I(fi);
                    break;
                }
                if (!a.g(fh, fi - fk - 5, 4096)) break;
                G = 0;
                A = 6;
                ff = -1;
                break;
            }
            case 13: {
                B = 24;
                fi += 28;
                A = 14;
                break;
            }
            case 14: {
                B = w % 20 > 2 ? 25 : 26;
                if (w % 8 != 0) break;
                if (--I <= 0) {
                    I = 0;
                    A = 15;
                }
                c = true;
                break;
            }
            case 15: {
                fi -= 10;
                z = -10;
                A = 3;
                break;
            }
            case 17: {
                if (++C > 30) {
                    C = 0;
                }
                if (C % 15 < 5) {
                    B = 0;
                    ff = 7;
                    break;
                }
                B = 1;
                ff = 8;
                break;
            }
            case 16: {
                if (++C >= 20) {
                    C = 0;
                }
                ff = (B = (C >> 2) + 2) % 2 == 0 ? 7 : 8;
                if (fp == 1) {
                    if (a.g(fh + fl, fi - fm, 4)) {
                        y = 0;
                        fh = a.I(fh + fl) - fl;
                    }
                } else if (a.g(fh - fl - 1, fi - fm, 8)) {
                    y = 0;
                    fh = a.I(fh - fl - 1) + jh + fl;
                }
                fh += y;
                if (y > 0) {
                    --y;
                } else if (y < 0) {
                    ++y;
                } else {
                    A = 17;
                }
                if ((a.i(fh, fi) & 2) == 2) break;
                A = 12;
                y = fp << 2;
                D = 0;
                break;
            }
            case 19: {
                if (w % 8 > 3) {
                    B = 43;
                } else {
                    B = 44;
                    a.e(fp, fh - (fp << 4), fi - 5);
                }
                if (C > 0) {
                    --C;
                    B = 45;
                }
                if (fp == 1) {
                    if (a.g(fh + fl, fi - fm, 4)) {
                        y = 0;
                        fh = a.I(fh + fl) - fl;
                    }
                } else if (a.g(fh - fl - 1, fi - fm, 8)) {
                    y = 0;
                    fh = a.I(fh - fl - 1) + jh + fl;
                }
                fh = (a.i(fh, fi - 1) & 0x40) == 64 ? (fh += y >> 1) : (fh += y);
                a.in[a.im] = fh;
                a.io[a.im] = fi;
                if (y > 0) {
                    --y;
                } else if (y < 0) {
                    ++y;
                } else {
                    A = 18;
                    fr = 0;
                }
                if (iu != -1) {
                    if (a.a(fh - iv[iu]) >= 20) {
                        iu = -1;
                        if (a.g(fh, fi, 2)) {
                            A = 18;
                        } else {
                            A = 20;
                            z = 2;
                            y = 3 * fp;
                            D = 0;
                        }
                    }
                } else if ((a.i(fh, fi) & 2) != 2) {
                    A = 20;
                    y = 3 * fp;
                    D = 0;
                }
                a.e(fp, fh - (fp << 3), fi);
                a.S();
                int n = a.A();
                if (n == -1 || jS[n] != 2 && jS[n] != 3) break;
                a.k(n, 2, -fp);
                a.f(jN[n], jO[n] - (jP[n] >> 1), fp);
                a.a("-2", 0, jN[n], jO[n] - jP[n], 0, -2);
                break;
            }
            case 18: {
                if (w % 8 > 3) {
                    B = 43;
                } else {
                    B = 44;
                    a.e(fp, fh - (fp << 4), fi - 5);
                }
                C = 0;
                break;
            }
            case 20: {
                try {
                    if (fp == 1) {
                        if ((a.i(fh + fl, fi - fm) & 4) == 4) {
                            y = 0;
                        }
                    } else if ((a.i(fh - fl, fi - fm) & 8) == 8) {
                        y = 0;
                    }
                    ff = -1;
                    fh += y;
                    fi += z;
                    if (z < 20) {
                        ++z;
                    }
                    if (y > 0) {
                        --y;
                    }
                    if (w % 8 > 3) {
                        B = 43;
                    } else {
                        B = 44;
                        a.e(fp, fh - (fp << 4), fi - 5);
                    }
                    if (z > 4 && (a.i(fh, fi) & 2) == 2) {
                        if (z < 8) {
                            A = 18;
                            z = 0;
                            y = 0;
                            D = 0;
                            C = 0;
                            fi = a.I(fi);
                        } else {
                            A = 21;
                            z = -(z / 3);
                            fi = a.I(fi);
                        }
                    }
                    if (a.g(fh, fi, 64) && fi % jh > 8) {
                        a.g(fh, a.H(fi) + 8);
                    }
                    a.in[a.im] = fh;
                    a.io[a.im] = fi;
                    if (a.g(fh, fi, 4096)) {
                        a.ir[a.im] = 1;
                        im = -1;
                        A = 12;
                    }
                }
                catch (Exception exception) {}
                if (fq >= 0) break;
                ++fq;
                break;
            }
            case 21: {
                if (fp == 1) {
                    if ((a.i(fh + fl, fi - fm) & 4) == 4) {
                        y = 0;
                    }
                } else if ((a.i(fh - fl, fi - fm) & 8) == 8) {
                    y = 0;
                }
                a.in[a.im] = fh += y;
                a.io[a.im] = fi += z;
                if (y > 0) {
                    --y;
                }
                if (++z == 0) {
                    A = 20;
                    C = 0;
                    D = eL ? 1 : 0;
                    z = 1;
                    c = true;
                }
                if (fq < 0) {
                    ++fq;
                }
                if (w % 8 > 3) {
                    B = 43;
                } else {
                    B = 44;
                    a.e(fp, fh - (fp << 4), fi - 5);
                }
                if (C <= 0) break;
                --C;
                B = 45;
                break;
            }
            case 23: {
                a.T();
            }
        }
        if (w % 3 == 0) {
            if (fz) {
                if (--I <= 0) {
                    I = 0;
                    fz = false;
                }
                c = true;
            }
            if (fA) {
                if (--I <= 0) {
                    I = 0;
                    fA = false;
                    int n = 0;
                    while (n < 4) {
                        Image image = er[n];
                        a.er[n] = es[n];
                        a.es[n] = image;
                        ++n;
                    }
                }
                c = true;
            }
        }
    }

    private static final void S() {
        if (a) {
            return;
        }
        if (a.g(fh, fi + 1, 2048)) {
            a.h(fh, fi + 1, 1024);
            a.h(fh, fi - 2, 1024);
        }
        if (a.g(fh - jh, fi + 1, 1024)) {
            a.j(fh - jh, fi + 1, 1024);
            a.j(fh - jh, fi - 2, 1024);
        }
        if (a.g(fh + jh, fi + 1, 1024)) {
            a.j(fh + jh, fi + 1, 1024);
            a.j(fh + jh, fi - 2, 1024);
        }
    }

    private static void T() {
        if (++C == 1) {
            a.f(fh, fi - 40);
            aM = 0;
            if (es == null) {
                es = new Image[4];
                try {
                    a.es[0] = Image.createImage((String)"/cp/ch0x.png");
                    a.es[1] = Image.createImage((String)"/cp/ch1x.png");
                }
                catch (Exception exception) {}
                a.es[2] = es[1];
                a.es[3] = es[0];
            }
        } else if (C >= 30) {
            A = 1;
            C = 0;
            if (I == 0) {
                return;
            }
            int n = 0;
            while (n < 4) {
                Image image = er[n];
                a.er[n] = es[n];
                a.es[n] = image;
                ++n;
            }
            fA = !fA;
        } else if (C <= 10) {
            if (C % 5 == 0) {
                int n = 0;
                while (n < 4) {
                    Image image = er[n];
                    a.er[n] = es[n];
                    a.es[n] = image;
                    ++n;
                }
            }
        } else if (C < 20) {
            if (C % 3 == 0) {
                int n = 0;
                while (n < 4) {
                    Image image = er[n];
                    a.er[n] = es[n];
                    a.es[n] = image;
                    ++n;
                }
            }
        } else if (C < 30 && C % 2 == 0) {
            int n = 0;
            while (n < 4) {
                Image image = er[n];
                a.er[n] = es[n];
                a.es[n] = image;
                ++n;
            }
        }
        if (C % 2 > 0) {
            B = 0;
            return;
        }
        B = 1;
    }

    private static void U() {
        if (++C >= 5) {
            C = 0;
            fr = 0;
        }
        B = C + 2;
        if (fp == 1) {
            if (a.g(fh + fl, fi - 1, 4)) {
                y = 0;
                fh = a.I(fh + fl) - fl;
            }
        } else if (a.g(fh - fl - 1, fi - 1, 8)) {
            y = 0;
            fh = a.I(fh - fl - 1) + jh + fl;
        }
        fh += y;
        if (y > 0) {
            --y;
        } else if (y < 0) {
            ++y;
        } else if (!fb) {
            A = 12;
            fr = 0;
        } else {
            A = 3;
            z = -8;
            D = 0;
            C = 0;
        }
        if (w % 8 == 0) {
            if ((I -= 3) <= 0) {
                I = 0;
                y = 0;
                A = 12;
            }
            c = true;
        }
        int n = 0;
        while (n < it) {
            if (a.a(fh - iv[n]) < 20 && iw[n] > fi && iw[n] - 24 < fi) {
                y = 0;
                fh = fh < iv[n] ? iv[n] - 20 : iv[n] + 20;
                A = 12;
                break;
            }
            ++n;
        }
        a.g(fh, fi);
        a.e(fp, fh - (fp << 3), fi);
    }

    private static boolean V() {
        if (!fV) {
            if (bx > 1) {
                bx >>= 1;
                c = true;
            }
            return false;
        }
        if (gd) {
            if (aV[5]) {
                a.d();
                a.M();
                if (iF == iE) {
                    iC = false;
                    gd = false;
                    a.z();
                }
            }
        } else {
            if (bx < bw) {
                bx += (bw - bx >> 1) + 1;
            }
            if (fY) {
                ga = ga < gb ? (ga += 2) : gb;
            }
            if (++gc >= aB[fW][fX]) {
                gc = aB[fW][fX];
                switch (az[fW][fX]) {
                    case 1: {
                        a.F(aA[fW][fX]);
                        ++fX;
                        break;
                    }
                    case 2: {
                        a.G(aA[fW][fX]);
                        ++fX;
                        break;
                    }
                    case 4: {
                        if (A != 1) break;
                        fY = false;
                        int n = 0;
                        int n2 = 0;
                        switch (aE[aA[fW][fX]]) {
                            case 0: {
                                n = fh;
                                n2 = fi;
                                break;
                            }
                            case 1: {
                                n = ly[aD[aA[fW][fX]]];
                                n2 = lz[aD[aA[fW][fX]]];
                                break;
                            }
                            case 2: {
                                n = dC[aD[aA[fW][fX]]];
                                n2 = dD[aD[aA[fW][fX]]];
                                break;
                            }
                            case 3: 
                            case 4: {
                                n = gq;
                                n2 = gr;
                            }
                        }
                        a.a(aG[aA[fW][fX]], -1, n, n2 -= 40);
                        ++fX;
                        gd = true;
                        break;
                    }
                    case 5: {
                        a.a(v, aA[fW][fX], fi - 50, -1);
                        ++fX;
                        break;
                    }
                    case 6: {
                        ge = -1;
                        ++fX;
                        break;
                    }
                    case 3: {
                        fV = false;
                        c = true;
                        ++fX;
                        break;
                    }
                    case 8: {
                        fx = false;
                        ++fX;
                        break;
                    }
                    case 9: {
                        fR = aC[aA[fW][fX]][0] - bF;
                        fS = aC[aA[fW][fX]][1] - bG;
                        if (fR < 0) {
                            fR = 0;
                        }
                        if (fR > fT) {
                            fR = fT;
                        }
                        if (fS < 0) {
                            fS = 0;
                        }
                        if (fS > fU) {
                            fS = fU;
                        }
                        fL = fR;
                        fM = fS;
                        ++fX;
                        break;
                    }
                    case 10: {
                        fL = aC[aA[fW][fX]][0] - bF;
                        fM = aC[aA[fW][fX]][1] - bG;
                        if (fL < 0) {
                            fL = 0;
                        }
                        if (fL > fT) {
                            fL = fT;
                        }
                        if (fM < 0) {
                            fM = 0;
                        }
                        if (fM > fU) {
                            fM = fU;
                        }
                        ++fX;
                        break;
                    }
                    case 7: {
                        fx = true;
                        ++fX;
                        break;
                    }
                    case 23: {
                        fx = false;
                        fL = dC[aA[fW][fX]] - bF;
                        fM = dD[aA[fW][fX]] - bG;
                        if (fL < 0) {
                            fL = 0;
                        }
                        if (fL > fT) {
                            fL = fT;
                        }
                        if (fM < 0) {
                            fM = 0;
                        }
                        if (fM > fU) {
                            fM = fU;
                        }
                        ++fX;
                        break;
                    }
                    case 27: {
                        fx = false;
                        fL = hs - bF;
                        fM = ht - bG;
                        if (fL < 0) {
                            fL = 0;
                        }
                        if (fL > fT) {
                            fL = fT;
                        }
                        if (fM < 0) {
                            fM = 0;
                        }
                        if (fM > fU) {
                            fM = fU;
                        }
                        ++fX;
                        break;
                    }
                    case 31: {
                        fx = false;
                        fL = gq - bF;
                        fM = gr - bG;
                        if (fL < 0) {
                            fL = 0;
                        }
                        if (fL > fT) {
                            fL = fT;
                        }
                        if (fM < 0) {
                            fM = 0;
                        }
                        if (fM > fU) {
                            fM = fU;
                        }
                        ++fX;
                        break;
                    }
                    case 11: {
                        fL = gS - bF;
                        fM = gT - bG;
                        if (fL < 0) {
                            fL = 0;
                        }
                        if (fL > fT) {
                            fL = fT;
                        }
                        if (fM < 0) {
                            fM = 0;
                        }
                        if (fM > fU) {
                            fM = fU;
                        }
                        ++fX;
                        break;
                    }
                    case 12: {
                        fM = fS += 2;
                        if (fS <= aC[aA[fW][fX]][1] - bG) break;
                        ++fX;
                        break;
                    }
                    case 14: {
                        fL = fR -= 2;
                        if (fR >= aC[aA[fW][fX]][0] - bF) break;
                        ++fX;
                        break;
                    }
                    case 15: {
                        fY = true;
                        fZ = Midlet.a(aH[aA[fW][fX]], q - 40);
                        gb = (bw >> 1) - (fZ.length << 3);
                        if (gb < 3) {
                            gb = 2;
                        }
                        ga = -fZ.length << 3;
                        ++fX;
                        break;
                    }
                    case 16: {
                        dA = true;
                        dB = aA[fW][fX];
                        ++fX;
                        break;
                    }
                    case 24: {
                        dA = true;
                        dB = aA[fW][fX];
                        ++fX;
                        break;
                    }
                    case 17: {
                        fs = fh = jx;
                        ft = fi = jy;
                        a.B();
                        Midlet.f = true;
                        a.x(aA[fW][fX]);
                        return true;
                    }
                    case 18: {
                        a.V(aA[fW][fX]);
                        ++fX;
                        break;
                    }
                    case 19: {
                        gn = true;
                        ++fX;
                        break;
                    }
                    case 20: {
                        gn = false;
                        gp = 0;
                        gq = 1080;
                        gr = 408;
                        ++fX;
                        break;
                    }
                    case 21: {
                        O = -1;
                        eP = true;
                        a.c("nh\u1eadn b\u1eb1ng l\u00e1i m\u00f4t\u00f4");
                        a.a(0, ly[4], lz[4] - 40, 4);
                        ++fX;
                        break;
                    }
                    case 22: {
                        int n = 0;
                        while (n <= 6) {
                            a.i(24, n, 0);
                            ++n;
                        }
                        eI = true;
                        ++fX;
                        break;
                    }
                    case 25: {
                        hh = true;
                        ++fX;
                        break;
                    }
                    case 26: {
                        int n = 0;
                        while (n <= 10) {
                            a.i(24, n, 0);
                            ++n;
                        }
                        eJ = true;
                        ++fX;
                        break;
                    }
                    case 28: {
                        hu = 1;
                        ++fX;
                        break;
                    }
                    case 29: {
                        a.d();
                        a.c();
                        iW = null;
                        iX = null;
                        iY = null;
                        iZ = null;
                        iT = null;
                        iU = null;
                        System.gc();
                        lw = null;
                        System.gc();
                        ee = null;
                        eh = null;
                        ej = null;
                        ey = null;
                        System.gc();
                        eA = null;
                        System.gc();
                        jJ = null;
                        System.gc();
                        a.C();
                        ev = null;
                        el = null;
                        eb = null;
                        em = null;
                        en = null;
                        eu = null;
                        eB = null;
                        eG = null;
                        ec = null;
                        System.gc();
                        et = null;
                        eg = null;
                        er = null;
                        es = null;
                        eE = null;
                        System.gc();
                        a.K();
                        return true;
                    }
                    case 30: {
                        O = -1;
                        eW = false;
                        eX = true;
                        gp = 0;
                        gq = 648;
                        gr = 288;
                        gn = false;
                        a.c("g\u1eb7p c\u00f4 Ayumi");
                        a.a(0, 1032, 368, 4);
                        ++fX;
                        break;
                    }
                    case 32: {
                        gn = true;
                        gu = 2;
                        gs = -5;
                        gC = -1;
                        gN = 8;
                        ++fX;
                        break;
                    }
                    case 33: {
                        fe = true;
                        gn = true;
                        ++fX;
                    }
                }
            }
        }
        a.aU[5] = false;
        a.aV[5] = false;
        return false;
    }

    private static void W() {
        int n = 0;
        while (n < dh) {
            if (dk[n] != 0) {
                if (a.a(fi - (fk >> 1) - (dj[n] - 7)) < 10 && a.a(fh - di[n]) < 10) {
                    dg += do[n];
                    c = true;
                    a.dk[n] = 301;
                    a.a("+" + do[n], 1, di[n], dj[n] - 15, 0, -1);
                }
                int n2 = n;
                di[n2] = di[n2] + dl[n];
                int n3 = n;
                dj[n3] = dj[n3] + dm[n];
                if (dl[n] > 1) {
                    int n4 = n;
                    dl[n4] = dl[n4] - 1;
                }
                if (dl[n] < -1) {
                    int n5 = n;
                    dl[n5] = dl[n5] + 1;
                }
                if (dm[n] < 20) {
                    int n6 = n;
                    dm[n6] = dm[n6] + 1;
                }
                int n7 = n;
                dk[n7] = dk[n7] + 1;
                if (dk[n] > 200) {
                    a.dk[n] = 0;
                }
                a.dn[n] = dk[n] % 20 > 18 ? 0 : (dk[n] % 20 > 16 ? 1 : 2);
                if (dj[n] < 0 || dj[n] >= iS || di[n] >= iR || di[n] < 0) {
                    a.dk[n] = 0;
                } else if (dm[n] > 2 && (a.i(di[n], dj[n]) & 2) == 2) {
                    a.dj[n] = a.H(dj[n]);
                    if (dm[n] > 2) {
                        a.dm[n] = -(dm[n] >> 1);
                    } else {
                        a.dl[n] = 0;
                        a.dm[n] = 0;
                    }
                }
            }
            ++n;
        }
    }

    private static void X() {
        if (iC && iL < iH[iF].length && ++iK >= iH[iF][iL].length()) {
            iK = 0;
            ++iL;
        }
    }

    private static void Y() {
        int n = 0;
        while (n < dx) {
            block0 : switch (dG[n]) {
                case 1: {
                    int n2;
                    int n3 = n2 = n;
                    dH[n3] = dH[n3] + 1;
                    if (dH[n2] > 30) {
                        a.dH[n2] = 0;
                    }
                    a.dJ[n2] = dH[n2] % (n2 + 18) < n2 + 6 ? 0 : 1;
                    if (dA) {
                        a.dI[n2] = dB;
                        a.dM[n2] = dB > dC[n2] ? 1 : -1;
                        a.dE[n2] = dM[n2] << 2;
                        a.dE[n2] = dM[n2] > 0 ? dE[n2] + n2 % 2 : dE[n2] - n2 % 2;
                        a.dG[n2] = 2;
                        break;
                    }
                    if (dz) break;
                    int n4 = n2;
                    dI[n4] = dI[n4] + 1;
                    if (dI[n2] > (dN[n2] + dC[n2]) % 20 + (dN[n2] >> 1)) {
                        if (iC && fH == n2) break;
                        a.dI[n2] = 0;
                        int n5 = (w + dN[n2]) % 120 - 60;
                        n5 = n5 > 0 ? (n5 += dN[n2]) : (n5 -= dN[n2]);
                        a.dI[n2] = dC[n2] + n5;
                        a.dM[n2] = n5 > 0 ? 1 : -1;
                        a.dE[n2] = dM[n2] << 2;
                        a.dG[n2] = 2;
                        if (fH == n2) {
                            fH = -1;
                            o = " ";
                            c = true;
                        }
                    }
                    if (w % 10 != 1) break;
                    a.B(n2);
                    break;
                }
                case 2: {
                    int n2;
                    int n6 = n2 = n;
                    dH[n6] = dH[n6] + 1;
                    if (dH[n2] >= 10) {
                        a.dH[n2] = 0;
                    }
                    a.dJ[n2] = (dH[n2] >> 1) + 2;
                    int n7 = n2;
                    dC[n7] = dC[n7] + dE[n2];
                    if (dC[n2] > dQ[n2] || dC[n2] < dP[n2] || a.a(dC[n2] - dI[n2]) < 8) {
                        a.dI[n2] = 0;
                        a.dH[n2] = 0;
                        int n8 = n2;
                        dC[n8] = dC[n8] - dE[n2];
                        a.dE[n2] = 0;
                        a.dG[n2] = 1;
                        break;
                    }
                    if (w % 10 != 0) break;
                    a.B(n2);
                    break;
                }
                case 5: {
                    int n2;
                    int n9 = n2 = n;
                    dH[n9] = dH[n9] + 1;
                    switch (dI[n2]) {
                        case 0: {
                            switch (dH[n2]) {
                                case 1: 
                                case 2: 
                                case 3: {
                                    a.dL[n2] = -1;
                                    a.dJ[n2] = 13;
                                    break block0;
                                }
                                case 4: {
                                    a.dL[n2] = 0;
                                    a.dJ[n2] = 14;
                                    break block0;
                                }
                                case 5: {
                                    a.dL[n2] = 1;
                                    a.dJ[n2] = 15;
                                    break block0;
                                }
                                case 6: {
                                    a.dL[n2] = 1;
                                    a.dJ[n2] = 15;
                                    a.A(n2);
                                    break block0;
                                }
                                case 7: {
                                    a.dL[n2] = 2;
                                    a.dJ[n2] = 16;
                                    break block0;
                                }
                                case 8: {
                                    a.dL[n2] = 2;
                                    a.dJ[n2] = 16;
                                    a.dI[n2] = 2;
                                    a.dH[n2] = 1;
                                }
                            }
                            break block0;
                        }
                        case 3: {
                            switch (dH[n2]) {
                                case 1: {
                                    a.dL[n2] = 1;
                                    a.dJ[n2] = 15;
                                    break block0;
                                }
                                case 2: {
                                    a.dL[n2] = 1;
                                    a.dJ[n2] = 15;
                                    break block0;
                                }
                                case 3: {
                                    a.dL[n2] = 2;
                                    a.dJ[n2] = 16;
                                    break block0;
                                }
                                case 4: {
                                    a.dL[n2] = 2;
                                    a.dJ[n2] = 16;
                                    a.dI[n2] = 1;
                                    a.dH[n2] = 1;
                                }
                            }
                            break block0;
                        }
                        case 1: {
                            switch (dH[n2]) {
                                case 1: {
                                    a.dL[n2] = 1;
                                    a.dJ[n2] = 15;
                                    break block0;
                                }
                                case 2: {
                                    a.dL[n2] = 1;
                                    a.dJ[n2] = 15;
                                    a.dI[n2] = 10;
                                }
                            }
                            break block0;
                        }
                        case 2: {
                            switch (dH[n2]) {
                                case 1: 
                                case 2: 
                                case 3: {
                                    a.dL[n2] = 3;
                                    a.dJ[n2] = 17;
                                    break block0;
                                }
                                case 4: {
                                    a.dL[n2] = 4;
                                    a.dJ[n2] = 18;
                                    break block0;
                                }
                                case 5: {
                                    a.dL[n2] = 5;
                                    a.dJ[n2] = 19;
                                    break block0;
                                }
                                case 6: {
                                    a.dL[n2] = 6;
                                    a.dJ[n2] = 20;
                                    a.A(n2);
                                    break block0;
                                }
                                case 7: {
                                    a.dL[n2] = 6;
                                    a.dJ[n2] = 20;
                                    a.dI[n2] = 3;
                                    a.dH[n2] = 1;
                                }
                            }
                            break block0;
                        }
                        case 10: {
                            switch (dH[n2]) {
                                case 1: {
                                    a.dL[n2] = 2;
                                    a.dJ[n2] = 16;
                                    break block0;
                                }
                                case 2: {
                                    a.dL[n2] = 0;
                                    a.dJ[n2] = 14;
                                    break block0;
                                }
                                case 3: 
                                case 4: {
                                    a.dJ[n2] = 13;
                                    a.dL[n2] = -1;
                                    break block0;
                                }
                                case 5: {
                                    a.dJ[n2] = 13;
                                    a.dL[n2] = -1;
                                    a.dG[n2] = 1;
                                    a.dI[n2] = 120;
                                    a.dH[n2] = 0;
                                }
                            }
                        }
                    }
                }
            }
            ++n;
        }
    }

    private static void Z() {
        if (aP) {
            aP = false;
            a.aV[5] = true;
        }
        if (++w == 10000) {
            w = 0;
        }
        a.ad();
        if (a.V()) {
            return;
        }
        a.Y();
        a.R();
        a.X();
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void aa() {
        int n = 0;
        while (n < cs) {
            block40: {
                if (ct[n] > 0) {
                    int n2 = n;
                    ct[n2] = ct[n2] - 1;
                }
                block0 : switch (cH[n]) {
                    case 3: {
                        int n3;
                        a.cF[n] = (w + n) % 10 > 5 ? 1 : 0;
                        int n4 = n;
                        cI[n4] = cI[n4] + 1;
                        a.cL[n] = 0;
                        a.cM[n] = 0;
                        if (cI[n] > 50) {
                            n3 = (w - n) % (n + 80) - 40;
                            n3 = n3 > 0 ? (n3 += 20) : (n3 -= 20);
                            a.cJ[n] = cx[n] + n3;
                            int n5 = a.cK[n] = n3 > 0 ? 1 : -1;
                            if (w % 4 > 1) {
                                a.cH[n] = 2;
                                a.cB[n] = cK[n] << 1;
                            } else {
                                a.cH[n] = 7;
                                a.cB[n] = cK[n] << 2;
                            }
                            if (cJ[n] < cN[n]) {
                                a.cJ[n] = cN[n];
                            }
                            if (cJ[n] > cO[n]) {
                                a.cJ[n] = cO[n];
                            }
                        }
                        if (w % 30 != 28) break;
                        a.w(n);
                        a.v(n);
                        break;
                    }
                    case 2: {
                        if (w % 12 > 7) {
                            a.cF[n] = 1;
                            a.cL[n] = 1;
                            if (w % 12 < 10) {
                                if (a.a(cx[n] - cJ[n]) < 4) {
                                    a.cJ[n] = 0;
                                    a.cI[n] = 0;
                                    a.cH[n] = 3;
                                    break;
                                }
                                int n6 = n;
                                cx[n6] = cx[n6] + cB[n];
                            }
                            a.w(n);
                            break;
                        }
                        a.cL[n] = 0;
                        a.cF[n] = 0;
                        break;
                    }
                    case 7: {
                        if (a.a(cx[n] - cJ[n]) < 12) {
                            a.cJ[n] = 0;
                            a.cI[n] = 0;
                            a.cH[n] = 3;
                            a.v(n);
                            break;
                        }
                        int n7 = n;
                        cx[n7] = cx[n7] + cB[n];
                        if (w % 2 == 1) {
                            a.cF[n] = 1;
                            a.cL[n] = 3;
                            break;
                        }
                        a.cL[n] = 2;
                        a.cF[n] = 0;
                        break;
                    }
                    case 4: {
                        int n3;
                        int n8 = n;
                        cI[n8] = cI[n8] + 1;
                        if (cI[n] < 3) {
                            a.cF[n] = 0;
                            a.cL[n] = 0;
                            a.cM[n] = 0;
                        } else if (cI[n] < 14) {
                            a.cF[n] = 1;
                            a.cL[n] = 1;
                            a.cM[n] = 1;
                        } else {
                            a.cH[n] = 3;
                            a.cI[n] = 1;
                        }
                        if (cI[n] != 3) break;
                        switch (cG[n]) {
                            case 2: 
                            case 3: 
                            case 6: {
                                n3 = n;
                                if (a.a(fi - cy[n3]) >= 5 || a.a(fh - cx[n3]) >= 30) break block0;
                                a.a(ad[cG[n3]], cK[n3]);
                                break;
                            }
                            case 0: 
                            case 4: {
                                a.b(cx[n] + (cK[n] << 3), cy[n] - 15, cK[n] * 12, ad[cG[n]], 0);
                                break;
                            }
                            case 1: 
                            case 5: {
                                a.b(cx[n] + (cK[n] << 3), cy[n] - 15, cK[n] << 3, ad[cG[n]], 1);
                                break;
                            }
                        }
                        break;
                    }
                    case 6: {
                        a.cF[n] = (w + n) % 10 > 5 ? 1 : 0;
                        a.cL[n] = 0;
                        a.cM[n] = 0;
                        int n9 = n;
                        cI[n9] = cI[n9] + 1;
                        if (cI[n] < 5) break;
                        a.cJ[n] = fh;
                        if (cx[n] < fh) {
                            int n10 = n;
                            cJ[n10] = cJ[n10] - 80;
                        } else {
                            int n11 = n;
                            cJ[n11] = cJ[n11] + 80;
                        }
                        a.cI[n] = 0;
                        a.cK[n] = cJ[n] < fh ? -1 : 1;
                        a.cH[n] = 7;
                        a.cB[n] = cK[n] << 3;
                        if (cJ[n] < cN[n]) {
                            a.cJ[n] = cN[n];
                        }
                        if (cJ[n] <= cO[n]) break;
                        a.cJ[n] = cO[n];
                        break;
                    }
                    case 5: {
                        int n12 = n;
                        cI[n12] = cI[n12] + 1;
                        if (cI[n] <= 550 + n * 5) break;
                        a.cx[n] = cv[n];
                        a.cy[n] = cw[n];
                        a.cI[n] = 0;
                        a.cH[n] = 3;
                        a.cu[n] = af[cG[n]];
                        break;
                    }
                    case 1: {
                        a.cM[n] = 1;
                        int n13 = n;
                        cI[n13] = cI[n13] + 1;
                        int n14 = n;
                        cx[n14] = cx[n14] + cB[n];
                        int n15 = n;
                        cy[n15] = cy[n15] + cC[n];
                        int n16 = n;
                        cz[n16] = cz[n16] + cD[n];
                        int n17 = n;
                        cA[n17] = cA[n17] + cE[n];
                        if (cI[n] > 14) {
                            if (cB[n] > 1) {
                                int n18 = n;
                                cB[n18] = cB[n18] - 1;
                            } else if (cB[n] < -1) {
                                int n19 = n;
                                cB[n19] = cB[n19] + 1;
                            }
                            int n20 = n;
                            cC[n20] = cC[n20] + 1;
                        }
                        if (cD[n] > 1) {
                            int n21 = n;
                            cD[n21] = cD[n21] - 1;
                        } else if (cD[n] < -1) {
                            int n22 = n;
                            cD[n22] = cD[n22] + 1;
                        }
                        int n23 = n;
                        cE[n23] = cE[n23] + 1;
                        if (cy[n] <= fS + r + 32 && cy[n] < iS) break block40;
                        a.cI[n] = 0;
                        a.cJ[n] = 0;
                        a.cH[n] = 5;
                    }
                }
                a.cz[n] = cx[n];
                a.cA[n] = cy[n] - 9 + cF[n] + ah[cG[n]][cM[n]];
            }
            ++n;
        }
    }

    private static void ab() {
        int n = 0;
        while (n < cP) {
            if (cT[n] != 0) {
                if (a.a(fi - (fk >> 1) - (cS[n] - 7)) < 10 && a.a(fh - cR[n]) < 10) {
                    if (cQ[n] == 0) {
                        if ((G += cX[n]) > H) {
                            G = H;
                        }
                        a.a("+" + cX[n], 0, cR[n], cS[n] - 15, 0, -1);
                    } else {
                        if ((I += cX[n]) > J) {
                            I = J;
                        }
                        a.a("+" + cX[n], 2, cR[n], cS[n] - 15, 0, -1);
                    }
                    c = true;
                    a.cT[n] = 301;
                }
                int n2 = n;
                cR[n2] = cR[n2] + cU[n];
                int n3 = n;
                cS[n3] = cS[n3] + cV[n];
                if (cU[n] > 1) {
                    int n4 = n;
                    cU[n4] = cU[n4] - 1;
                }
                if (cU[n] < -1) {
                    int n5 = n;
                    cU[n5] = cU[n5] + 1;
                }
                if (cV[n] < 20) {
                    int n6 = n;
                    cV[n6] = cV[n6] + 1;
                }
                int n7 = n;
                cT[n7] = cT[n7] + 1;
                if (cT[n] > 200) {
                    a.cT[n] = 0;
                }
                if (cS[n] >= iS || cS[n] < 0 || cR[n] >= iR || cR[n] < 0) {
                    a.cT[n] = 0;
                } else if (cV[n] > 2 && (a.i(cR[n], cS[n]) & 2) == 2) {
                    a.cS[n] = a.H(cS[n]);
                    if (cV[n] > 2) {
                        a.cV[n] = -(cV[n] >> 1);
                    } else {
                        a.cU[n] = 0;
                        a.cV[n] = 0;
                    }
                }
            }
            ++n;
        }
    }

    private static void ac() {
        if (!hl) {
            return;
        }
        int n = w % 10;
        int n2 = a.a(fh - hs);
        int n3 = a.a(fi - ht);
        switch (hu) {
            case 1: {
                if (n < 7) {
                    hK = hA[0];
                    hL = hB[0];
                } else {
                    hK = hA[1];
                    hL = hB[1];
                }
                if (n2 > 90 && n2 < 100 && n < 2) {
                    hv = hs < fh ? 1 : -1;
                    hu = 23;
                    hy = 0;
                    break;
                }
                if ((n2 > 120 && n2 < 150 || fi < ht && n2 > 40) && n < 2) {
                    hv = hs < fh ? 1 : -1;
                    hy = 0;
                    hu = 8;
                    break;
                }
                if (n2 < 40 && n == 1) {
                    hv = hs < fh ? 1 : -1;
                    hy = 0;
                    hu = 5;
                    break;
                }
                if (n2 > 40 && n2 < 100 && n < 3) {
                    hv = hs < fh ? 1 : -1;
                    hy = 0;
                    hz = fh;
                    if (hz > hx) {
                        hz = hx;
                    } else if (hz < hw) {
                        hz = hw;
                    }
                    hu = 24;
                    break;
                }
                if (n2 <= 80 || n != 2) break;
                hy = 0;
                hu = 2;
                hz = fh;
                if (hz > hx) {
                    hz = hx;
                    break;
                }
                if (hz >= hw) break;
                hz = hw;
                break;
            }
            case 5: {
                if (++hy < 2) {
                    hK = hA[0];
                    hL = hB[0];
                } else if (hy < 2) {
                    hK = hC[0];
                    hL = hD[0];
                } else if (hy < 12) {
                    hK = hC[1];
                    hL = hD[1];
                } else if (hy < 14) {
                    hK = hC[0];
                    hL = hD[0];
                } else if (hy == 14) {
                    hy = 0;
                    hu = 1;
                }
                if (hy != 3 || n2 >= 40 || n3 >= 10) break;
                a.a(hm, hv);
                break;
            }
            case 8: {
                if (++hy < 2) {
                    hK = hA[0];
                    hL = hB[0];
                } else if (hy < 2) {
                    hK = hC[0];
                    hL = hD[0];
                } else if (hy < 12) {
                    hK = hC[1];
                    hL = hD[1];
                } else if (hy < 14) {
                    hK = hC[0];
                    hL = hD[0];
                } else if (hy == 14) {
                    hy = 0;
                    hu = 1;
                }
                if (hy % 5 != 0) break;
                a.a(hs + (hv << 3), ht - 20, hv << 3, -n + 2, false);
                break;
            }
            case 2: {
                n3 = ++hy % 6 >> 1;
                if (n3 == 3) {
                    aM = 0;
                }
                hK = hE[n3];
                hL = hF[n3];
                if ((hs += hv << 2) > hz && hv > 0 || hs < hz && hv < 0 || hs > hx || hs < hw || hy > 100) {
                    hy = 0;
                    hu = 1;
                }
                if (n2 <= 50 || n2 >= 100 || n != 1) break;
                hv = hs < fh ? 1 : -1;
                hu = 23;
                hy = 0;
                break;
            }
            case 24: {
                n = ++hy % 6 >> 1;
                hK = hE[n];
                hL = hF[n];
                if (!((hs += hv << 3) > hz && hv > 0 || hs < hz && hv < 0 || hs > hx || hs < hw) && hy <= 100) break;
                hy = 0;
                hu = 5;
                break;
            }
            case 23: {
                n = ++hy % 4 >> 1;
                hK = hC[n];
                hL = hD[n];
                if (hy > 20 && hy % 10 == 0) {
                    a.b(hs + (hv << 3), ht - 24, hv << 3, hm, 3);
                }
                if (hy != 100) break;
                hv = hs < fh ? 1 : -1;
                hy = 0;
                hz = fh;
                if (hz > hx) {
                    hz = hx;
                } else if (hz < hw) {
                    hz = hw;
                }
                hu = 24;
                break;
            }
            case 0: {
                if (n < 7) {
                    hK = hA[0];
                    hL = hB[0];
                    break;
                }
                hK = hA[1];
                hL = hB[1];
                break;
            }
            case 6: {
                ++hy;
                n = 0;
                while (n < hJ.length) {
                    int n4 = n;
                    hJ[n4] = hJ[n4] + (a.a(n - 3) + 2);
                    int n5 = n;
                    hI[n5] = hI[n5] + (n - 3 << 1);
                    ++n;
                }
                if (hy < 50) break;
                hu = 22;
            }
        }
        n3 = 0;
        while (n3 < 6) {
            a.hG[n3] = hv == 1 ? hs + hI[n3] + hK[n3] : hs - hI[n3] - hK[n3];
            a.hH[n3] = ht + hJ[n3] + hL[n3];
            ++n3;
        }
    }

    private static void ad() {
        block19: {
            block18: {
                if (!ih) break block18;
                switch (av[ig][ii]) {
                    case 2: {
                        a.C(aw[ig][ii]);
                        ++ii;
                        return;
                    }
                    case 1: {
                        if (fh > ax[ig][ii]) {
                            a.C(aw[ig][ii]);
                            ++ii;
                            return;
                        }
                        break block19;
                    }
                    case 4: {
                        if (fh > fR + q) {
                            a.C(aw[ig][ii]);
                            ++ii;
                            return;
                        }
                        break block19;
                    }
                    case 6: {
                        if (fh < ax[ig][ii] && fh > ax[ig][ii] - 96 && fi == ay[ig][ii]) {
                            a.C(aw[ig][ii]);
                            ++ii;
                        }
                    }
                    case 5: {
                        if (gV == 22) {
                            a.C(aw[ig][ii]);
                            ++ii;
                            return;
                        }
                        break block19;
                    }
                    case 7: {
                        if (ia == 5) {
                            a.C(aw[ig][ii]);
                            ++ii;
                            return;
                        }
                        break block19;
                    }
                    case 8: {
                        if (hj < 10) {
                            a.C(aw[ig][ii]);
                            ++ii;
                            return;
                        }
                        break block19;
                    }
                    case 9: {
                        if (hu == 22) {
                            a.C(aw[ig][ii]);
                            eK = true;
                            ++ii;
                            return;
                        }
                        break block19;
                    }
                    default: {
                        return;
                    }
                }
            }
            if (ig == 10) {
                Display.getDisplay((MIDlet)Midlet.c).setCurrent((Displayable)Midlet.b);
                ig = 0;
            }
        }
    }

    public static void s() {
        Midlet.b = new a();
        a.ad();
        a a2 = Midlet.b;
        System.out.println("may lan chay");
        Midlet.g = Midlet.a();
        Midlet.a(Midlet.g == 0 ? 1 : 0);
        lO = false;
        d = 13;
        new Thread(a2).start();
    }

    public a() {
        this.setFullScreenMode(true);
        this.md = false;
        System.gc();
        this.ae();
        aN = this;
        aT = this.getKeyCode(8) == -20;
        aW = this.getWidth();
        aX = this.getHeight();
        aY = aW / 2;
        aZ = aX / 2;
        lO = a.aJ() == 1;
        this.af();
        v0.lY = 100;
        this.aA();
        a a2 = this;
        Object object = a.f("np");
        mb = object == null ? -1 : object[0];
        if (mb == -1) {
            mb = a2.lY / 10;
            a.W(mb);
            a.g(String.valueOf(Z) + " PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "009" + "007");
            a.X(0);
        }
        a.c();
        a2 = this;
        String string = System.getProperty("phone.imei");
        object = string;
        if (string == null || object.equals("")) {
            object = System.getProperty("com.nokia.IMEI");
        }
        if (object != null && !object.equals("")) {
            a2.lX = object;
        }
        byte[] byArray = a.f("im");
        object = byArray;
        String string2 = byArray == null ? null : new String((byte[])object);
        object = string2;
        if (string2 == null) {
            object = a2.lX;
            a.a("im", object.getBytes());
        } else if (!object.equals(a2.lX)) {
            mb = 2;
            a.W(2);
            a.g(String.valueOf(Z) + " PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "009" + "007");
            a.X(0);
        }
        a2 = this;
        d = 13;
        byte[] byArray2 = a.f("r");
        object = byArray2;
        Y = byArray2 == null ? 0 : object[0];
        lT = aY - (lU.stringWidth(lR[1]) >> 1);
    }

    private void ae() {
        Object object = exception3.getClass().getResourceAsStream("/prefix.txt");
        StringBuffer stringBuffer = new StringBuffer();
        try {
            int n;
            while ((n = ((InputStream)object).read()) != -1) {
                stringBuffer.append((char)n);
            }
            object = stringBuffer.toString();
            Z = object;
            System.out.println("pree load  " + Z);
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
        }
        InputStream inputStream = exception3.getClass().getResourceAsStream("/sms.txt");
        object = new StringBuffer();
        try {
            int n;
            while ((n = inputStream.read()) != -1) {
                ((StringBuffer)object).append((char)n);
            }
            String[] stringArray = ((StringBuffer)object).toString();
            stringArray = stringArray.trim();
            stringArray = a.a((String)stringArray, ",");
            ab = new String[stringArray.length];
            int n2 = 0;
            while (n2 < stringArray.length) {
                a.ab[n2] = stringArray[n2];
                System.out.println(String.valueOf(ab[n2]) + " \n");
                ++n2;
            }
            kC = new String[][]{{"sms://" + ab[1], "sms://" + ab[2], "sms://" + ab[2], "sms://" + ab[1], "sms://" + ab[1], "sms://" + ab[1]}, {"sms://" + ab[0], "sms://" + ab[2], "sms://" + ab[3], "sms://" + ab[4]}};
            lP = "sms://" + stringArray[6];
            return;
        }
        catch (Exception exception) {
            Exception exception3 = exception;
            exception.printStackTrace();
            return;
        }
    }

    private static String[] a(String string, String string2) {
        int n = 0;
        int n2 = 0;
        int n3 = string2.length();
        n2 = string.indexOf(string2, 0);
        while (n2 != -1) {
            n2 += n3;
            n2 = string.indexOf(string2, n2);
            ++n;
        }
        String[] stringArray = new String[n + 1];
        n2 = string.indexOf(string2);
        int n4 = 0;
        int n5 = 0;
        while (n2 != -1) {
            stringArray[n5] = string.substring(n4, n2);
            n4 = n2 + n3;
            n2 = string.indexOf(string2, n4);
            ++n5;
        }
        stringArray[n5] = string.substring(n4, string.length());
        return stringArray;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void af() {
        Object object = System.getProperty("microedition.platform");
        String string = "";
        if (object == null) {
            object = "TeaMobile";
        }
        if (lO) {
            Object object2 = a.f("lv");
            String string2 = object2 == null ? null : new String((byte[])object2);
            object2 = string2;
            if (string2 == null) {
                a.X(0);
                a.Y(0);
                a.e((String)object);
                return;
            }
            if (!object2.equals(object)) {
                a.X(0);
                a.Y(0);
                a.e((String)object);
                return;
            }
            int n = 0;
            while (n < 4) {
                string = String.valueOf(string) + (char)(iO.charAt(n) - '\u0001');
                ++n;
            }
            string = String.valueOf(string) + 'a';
            if (((String)object).toLowerCase().indexOf(string) == -1) return;
            d = 13;
            byte[] byArray = a.f("bc");
            object = byArray;
            n = (int)(byArray == null ? 0 : object[0]);
            if (n >= iO.length() - 1) {
                byte[] byArray2 = a.f("girl");
                object = byArray2;
                object = byArray2 == null ? null : new String((byte[])object);
                string = null;
                try {
                    string = LocalDevice.getLocalDevice().getBluetoothAddress();
                }
                catch (Exception exception) {}
                if (string == null) {
                    aO = !aO;
                    return;
                } else if (object == null) {
                    a.d(string);
                    return;
                } else if (!string.equals(object)) {
                    a.X(0);
                    a.Y(0);
                    a.d(string);
                    a.g(String.valueOf(Z) + " PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "009" + "007");
                    return;
                } else {
                    a.E(0);
                }
                return;
            }
            a.E(n + 1);
            return;
        }
        a.e((String)object);
    }

    private static void E(int n) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n;
        a.a("bc", byArray2);
    }

    private static void d(String string) {
        a.a("girl", string.getBytes());
    }

    private static void e(String string) {
        a.a("lv", string.getBytes());
    }

    protected final void keyPressed(int n) {
        if (n == 35) {
            if (by.equals("1984")) {
                eL = true;
                eM = true;
                fc = true;
                eO = true;
                eR = true;
                eP = true;
                eN = true;
                eS = true;
                eT = true;
                eY = true;
                a.c("Learned All Skill");
            } else if (by.equals("30538")) {
                J = 500000;
                I = 500000;
                H = 500000;
                G = 500000;
                a.c("Unlimit HP MP");
            } else if (by.equals("38305")) {
                E = 10000;
                a.c("Unlimit Damage");
            } else if (by.equals("1982")) {
                a.u(100000);
            } else if (by.equals("1983")) {
                dg += 50000;
                a.c("Money+=50000");
            } else if (by.equals("12456")) {
                TextBox textBox = new TextBox("Type anything:", "", 50, 0);
                textBox.addCommand(new Command("OK", 4, 0));
                textBox.addCommand(new Command("Back", 2, 0));
                textBox.setCommandListener((CommandListener)this);
                Display.getDisplay((MIDlet)Midlet.c).setCurrent((Displayable)textBox);
            } else if (by.equals("99999")) {
                a.a(a.aI(), "sms://" + ab[0], 0);
                d = 13;
            } else if (by.equals("1981")) {
                int n2 = 0;
                while (n2 < 19) {
                    a.W[n2] = true;
                    ++n2;
                }
                a.c("Unlock all map!");
            } else if (by.equals("8463")) {
                int n3 = gO + (int)((System.currentTimeMillis() - gP) / 1000L);
                a.c(String.valueOf(n3 / 3600) + ":" + n3 % 3600 / 60 + ":" + n3 % 60);
            }
            c = true;
            by = "";
        }
        if (n >= 48 && n <= 57 && (by = String.valueOf(by) + (char)n).length() > 10) {
            by = "";
        }
        if (aT) {
            switch (n) {
                case -21: 
                case 21: {
                    n = -6;
                    break;
                }
                case -22: 
                case 22: {
                    n = -7;
                    break;
                }
                case -23: {
                    n = -8;
                    break;
                }
                case -2: {
                    n = -3;
                    break;
                }
                case -5: {
                    n = -4;
                    break;
                }
                case -6: {
                    n = -2;
                    break;
                }
                case -20: {
                    n = -5;
                }
            }
        }
        if (fV && n != -5) {
            return;
        }
        a.F(n);
    }

    protected final void keyReleased(int n) {
        if (aT) {
            switch (n) {
                case -21: 
                case 21: {
                    n = -6;
                    break;
                }
                case -22: 
                case 22: {
                    n = -7;
                    break;
                }
                case -23: {
                    n = -8;
                    break;
                }
                case -2: {
                    n = -3;
                    break;
                }
                case -5: {
                    n = -4;
                    break;
                }
                case -6: {
                    n = -2;
                    break;
                }
                case -20: {
                    n = -5;
                }
            }
        }
        if (fV && n != -5) {
            return;
        }
        a.G(n);
    }

    private static void F(int n) {
        switch (n) {
            case -1: {
                a.aU[2] = true;
                a.aV[2] = true;
                return;
            }
            case -2: {
                a.aU[8] = true;
                a.aV[8] = true;
                return;
            }
            case -3: {
                a.aU[4] = true;
                a.aV[4] = true;
                return;
            }
            case -4: {
                a.aU[6] = true;
                a.aV[6] = true;
                return;
            }
            case -5: 
            case 10: {
                a.aU[5] = true;
                a.aV[5] = true;
                return;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                a.aU[n - 48] = true;
                a.aV[n - 48] = true;
                return;
            }
            case 42: {
                a.aU[10] = true;
                a.aV[10] = true;
                return;
            }
            case 35: {
                a.aU[11] = true;
                a.aV[11] = true;
                return;
            }
            case -21: 
            case -6: {
                a.aU[12] = true;
                a.aV[12] = true;
                return;
            }
            case -22: 
            case -7: {
                a.aU[13] = true;
                a.aV[13] = true;
                return;
            }
        }
    }

    private static void G(int n) {
        switch (n) {
            case -1: {
                a.aU[2] = false;
                a.aV[2] = false;
                return;
            }
            case -2: {
                a.aU[8] = false;
                a.aV[8] = false;
                return;
            }
            case -3: {
                a.aU[4] = false;
                a.aV[4] = false;
                return;
            }
            case -4: {
                a.aU[6] = false;
                a.aV[6] = false;
                return;
            }
            case -5: 
            case 10: {
                a.aU[5] = false;
                return;
            }
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                a.aU[n - 48] = false;
                return;
            }
            case 42: {
                a.aU[10] = false;
                return;
            }
            case 35: {
                a.aU[11] = false;
                return;
            }
            case -21: 
            case -6: {
                a.aU[12] = false;
                return;
            }
            case -22: 
            case -7: {
                a.aU[13] = false;
                return;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected final void paint(Graphics var1_1) {
        block111: {
            block113: {
                block112: {
                    try {
                        switch (a.d) {
                            case 1: {
                                this = var1_1 /* !! */ ;
                                this.setClip(-1, -1, a.q + 2, a.r + 2);
                                a.a((Graphics)this);
                                this.translate(-a.fR, -a.fS);
                                var1_1 /* !! */  = this;
                                var2_2 = 0;
                                while (true) {
                                    if (var2_2 < a.ja) ** GOTO lbl15
                                    if (!a.a) break;
                                    a.j((Graphics)this);
                                    ** GOTO lbl24
lbl15:
                                    // 1 sources

                                    if (a.jc[var2_2] + a.je[var2_2] >= a.fR && a.jc[var2_2] - a.je[var2_2] <= a.fR + a.q && a.jd[var2_2] >= a.fS && a.jd[var2_2] - a.jf[var2_2] <= a.fS + a.r) {
                                        a.ji = a.jc[var2_2] - a.fR;
                                        a.jj = a.ji - a.bF;
                                        a.jl = (a.jh - 2) * a.jj / a.jh;
                                        a.jk = a.jl + a.bF;
                                        var1_1 /* !! */ .drawImage(a.iZ[a.jb[var2_2]], a.fR + a.jk, a.jd[var2_2], 33);
                                    }
                                    ++var2_2;
                                }
                                a.k((Graphics)this);
lbl24:
                                // 2 sources

                                a.i((Graphics)this);
                                a.r((Graphics)this);
                                var1_1 /* !! */  = this;
                                if (a.hM) {
                                    if (a.ib < 0) {
                                        var1_1 /* !! */ .drawImage(a.eA[0], a.hS, a.hT, 33);
                                        var1_1 /* !! */ .drawImage(a.eA[2], a.hU, a.hY, 33);
                                        var1_1 /* !! */ .drawImage(a.eA[3], a.hX, a.hZ, 33);
                                        var1_1 /* !! */ .drawImage(a.eA[1], a.hV, a.hW, 33);
                                    } else {
                                        var1_1 /* !! */ .drawRegion(a.eA[0], 0, 0, a.eA[0].getWidth(), a.eA[0].getHeight(), 2, a.hS, a.hT, 33);
                                        var1_1 /* !! */ .drawRegion(a.eA[2], 0, 0, a.eA[2].getWidth(), a.eA[2].getHeight(), 2, a.hU, a.hY, 33);
                                        var1_1 /* !! */ .drawRegion(a.eA[3], 0, 0, a.eA[3].getWidth(), a.eA[3].getHeight(), 2, a.hX, a.hZ, 33);
                                        var1_1 /* !! */ .drawRegion(a.eA[1], 0, 0, a.eA[1].getWidth(), a.eA[1].getHeight(), 2, a.hV, a.hW, 33);
                                    }
                                    if (a.if != 0) {
                                        var1_1 /* !! */ .setColor(0x330011);
                                        var1_1 /* !! */ .drawRect(a.hS - (a.hR >> 1) - 1, a.hT - a.hO - 7, a.hR + 2, 4);
                                        var2_2 = a.hP * a.hR / a.hQ;
                                        var1_1 /* !! */ .setColor(0xEE0000);
                                        var1_1 /* !! */ .fillRect(a.hS - (a.hR >> 1), a.hT - a.hO - 6, var2_2, 3);
                                        var1_1 /* !! */ .setColor(0x440022);
                                        var1_1 /* !! */ .fillRect(a.hS - (a.hR >> 1) + var2_2, a.hT - a.hO - 6, a.hR - var2_2 + 1, 3);
                                    }
                                }
                                var1_1 /* !! */  = this;
                                var2_2 = 0;
                                while (true) {
                                    if (var2_2 < a.it) ** GOTO lbl53
                                    var1_1 /* !! */  = this;
                                    var2_2 = 0;
                                    if (true) ** GOTO lbl226
lbl53:
                                    // 1 sources

                                    if (a.fR <= a.iv[var2_2] + 12 && a.fR + a.q >= a.iv[var2_2] - 12 && a.fS <= a.iw[var2_2] && a.fS + a.r >= a.iw[var2_2] - 24) {
                                        var1_1 /* !! */ .drawImage(a.eh, a.iv[var2_2], a.iw[var2_2], 33);
                                    }
                                    ++var2_2;
                                }
                            }
                            case 2: {
                                this = var1_1 /* !! */ ;
                                this.setColor(0);
                                this.fillRect(0, 0, a.aW, 21);
                                Midlet.a((Graphics)this, "Loading... " + a.S + "%", a.aW >> 1, 4, 2);
                                return;
                            }
                            case 3: {
                                this = var1_1 /* !! */ ;
                                this.setClip(-1, -1, a.aW + 2, a.aX + 1);
                                a.a((Graphics)this);
                                this.translate(-a.fR, -a.fS);
                                if (a.a) {
                                    a.j((Graphics)this);
                                } else {
                                    a.k((Graphics)this);
                                }
                                a.i((Graphics)this);
                                a.l((Graphics)this);
                                this.translate(-this.getTranslateX(), -this.getTranslateY());
                                this.setColor(0);
                                this.fillRect(0, 0, a.q, a.bd);
                                this.fillRect(0, a.be, a.q, a.aX - a.be + 1);
                                this.setColor(10275899);
                                this.fillRect(0, a.bd - 1, a.aW, 1);
                                this.fillRect(0, a.be + 1, a.aW, 1);
                                this.drawImage(a.eD, a.aY, a.bb + a.be >> 1, 3);
                                Midlet.a((Graphics)this, "*", a.aY - 50 - a.w % 3, a.bg, 0);
                                Midlet.a((Graphics)this, "/", a.aY + 50 + a.w % 3, a.bg, 1);
                                Midlet.a((Graphics)this, a.aF[a.ba], a.aY, a.bg, 3);
                                return;
                            }
                            case 4: {
                                this = var1_1 /* !! */ ;
                                this.setColor(0);
                                this.fillRect(0, 0, a.aW, a.aX);
                                if (a.jH < 0) {
                                    this.drawImage(null, a.aY, a.aZ, 3);
                                    return;
                                }
                                if (a.jF == null) return;
                                if (a.jI < 0) return;
                                this.drawRegion(a.jF, 0, a.jI * 51, 71, 51, 0, a.aW >> 1, a.aX >> 1, 3);
                                return;
                            }
                            case 5: {
                                a.o(var1_1 /* !! */ );
                                return;
                            }
                            case 6: {
                                a.p(var1_1 /* !! */ );
                                return;
                            }
                            case 7: {
                                a.q(var1_1 /* !! */ );
                                return;
                            }
                            case 8: {
                                a.n(var1_1 /* !! */ );
                                return;
                            }
                            case 9: {
                                a.m(var1_1 /* !! */ );
                                return;
                            }
                            case 10: {
                                this = var1_1 /* !! */ ;
                                this.setColor(0);
                                this.fillRect(0, 0, a.aW, a.aX);
                                if (a.lo != false) return;
                                this.drawImage(a.ln, a.aY, a.aZ - 30, 3);
                                if (a.lp == 0) {
                                    Midlet.a((Graphics)this, "B\u1eadt \u00e2m thanh?", a.aY, a.aZ + 15, 2);
                                    Midlet.a((Graphics)this, "B\u1eadt", 4, a.aX - 14, 0);
                                    Midlet.a((Graphics)this, "T\u1eaft", a.aW - 4, a.aX - 14, 1);
                                    return;
                                }
                                Midlet.a((Graphics)this, "Ch\u1ea5t l\u01b0\u1ee3ng h\u00ecnh \u1ea3nh?", a.aY, a.aZ + 15, 2);
                                Midlet.a((Graphics)this, "Cao", 4, a.aX - 14, 0);
                                Midlet.a((Graphics)this, "Th\u1ea5p", a.aW - 4, a.aX - 14, 1);
                                return;
                            }
                            case 11: {
                                a.h(var1_1 /* !! */ );
                                return;
                            }
                            case 12: {
                                a.h(var1_1 /* !! */ );
                                return;
                            }
                            case 13: {
                                var1_1 /* !! */ .setColor(0xEAEAEA);
                                var1_1 /* !! */ .fillRect(0, 0, a.aW, a.aX);
                                var1_1 /* !! */ .setColor(11788624);
                                var1_1 /* !! */ .fillRect(0, 0, a.aW, a.lW);
                                var1_1 /* !! */ .fillRect(0, a.aX - a.lW, a.aW, a.lW);
                                var1_1 /* !! */ .setColor(0xFFFFFF);
                                var1_1 /* !! */ .setFont(a.lV);
                                var1_1 /* !! */ .drawString("Ninja School", a.aY, 3, 17);
                                var1_1 /* !! */ .setFont(a.lU);
                                switch (a.Y) {
                                    case 0: {
                                        super.t(var1_1 /* !! */ );
                                        return;
                                    }
                                    case 2: {
                                        var2_3 /* !! */  = var1_1 /* !! */ ;
                                        var2_3 /* !! */ .setColor(0x6D6D6D);
                                        var2_3 /* !! */ .setFont(a.lU);
                                        var2_3 /* !! */ .drawString("Gui tin kich hoat ton phi", a.aY, a.aZ - (a.lW << 1), 17);
                                        var2_3 /* !! */ .drawString("15.000\u0111. Ban se nhan", a.aY, a.aZ - a.lW, 17);
                                        var2_3 /* !! */ .drawString("duoc ma kich hoat trong", a.aY, a.aZ, 17);
                                        var2_3 /* !! */ .drawString("tin nhan tra loi.", a.aY, a.aZ + a.lW, 17);
                                        var2_3 /* !! */ .setColor(0xFFFFFF);
                                        var2_3 /* !! */ .setFont(a.lV);
                                        var2_3 /* !! */ .drawString("Dong y", 2, a.aX - a.lW + 3, 20);
                                        var2_3 /* !! */ .drawString("Khong", a.aW - 2, a.aX - a.lW + 3, 24);
                                        return;
                                    }
                                    case 1: {
                                        var2_4 /* !! */  = var1_1 /* !! */ ;
                                        var2_4 /* !! */ .setColor(a.lS % 10 > 5 ? 0xFFFFFF : 0x6D6D6D);
                                        var2_4 /* !! */ .drawString("Dang gui tin", a.aY, a.aZ - a.lW, 17);
                                        var2_4 /* !! */ .drawString("kich hoat...", a.aY, a.aZ, 17);
                                        return;
                                    }
                                    case 5: {
                                        var2_5 /* !! */  = var1_1 /* !! */ ;
                                        var2_5 /* !! */ .setColor(0x6D6D6D);
                                        var2_5 /* !! */ .drawString("Da gui tin kich hoat.", a.aY, a.aZ - (a.lW << 1), 17);
                                        var2_5 /* !! */ .drawString("Xin thoat game va", a.aY, a.aZ - a.lW, 17);
                                        var2_5 /* !! */ .drawString("cho giay lat. Ban se", a.aY, a.aZ, 17);
                                        var2_5 /* !! */ .drawString("nhan duoc ma kich hoat.", a.aY, a.aZ + a.lW, 17);
                                        var2_5 /* !! */ .setColor(0xFFFFFF);
                                        var2_5 /* !! */ .setFont(a.lV);
                                        var2_5 /* !! */ .drawString("OK", 2, a.aX - a.lW + 3, 20);
                                        return;
                                    }
                                    case 6: {
                                        var2_6 /* !! */  = var1_1 /* !! */ ;
                                        var2_6 /* !! */ .setColor(0x6D6D6D);
                                        var2_6 /* !! */ .drawString("Khong the gui SMS", a.aY, a.aZ - (a.lW << 1), 17);
                                        var2_6 /* !! */ .drawString("Xin hay thu lai. Vao", a.aY, a.aZ - a.lW, 17);
                                        var2_6 /* !! */ .drawString("http://vechai321.xtgem.com", a.aY, a.aZ, 17);
                                        var2_6 /* !! */ .drawString("de biet them thong tin.", a.aY, a.aZ + a.lW, 17);
                                        var2_6 /* !! */ .setColor(0xFFFFFF);
                                        var2_6 /* !! */ .setFont(a.lV);
                                        var2_6 /* !! */ .drawString("OK", 2, a.aX - a.lW + 3, 20);
                                        return;
                                    }
                                    case 4: {
                                        super.s(var1_1 /* !! */ );
                                        return;
                                    }
                                    case 7: {
                                        var3_10 /* !! */  = var1_1 /* !! */ ;
                                        var2_7 = this;
                                        var3_10 /* !! */ .setColor(0);
                                        var3_10 /* !! */ .fillRect(0, 0, a.aW, a.aX);
                                        var4_12 = var2_7.me;
                                        if (a.lS % 10 > 5) {
                                            var4_12 = String.valueOf(var4_12) + "|";
                                        }
                                        var3_10 /* !! */ .drawString(var4_12, a.aY - 20, a.aZ - 40, 20);
                                        a.X(7);
                                        a.aj();
                                        a.d = 4;
                                        a.lO = a.aJ() == 1;
                                        return;
                                    }
                                    case 8: {
                                        var2_8 /* !! */  = var1_1 /* !! */ ;
                                        var2_8 /* !! */ .setColor(0x6D6D6D);
                                        var2_8 /* !! */ .drawString("Xin vui long", a.aY, a.aZ - (a.lW << 1), 17);
                                        var2_8 /* !! */ .drawString("Bat Bluetooth", a.aY, a.aZ - a.lW, 17);
                                        var2_8 /* !! */ .setColor(0xFFFFFF);
                                        var2_8 /* !! */ .setFont(a.lV);
                                        var2_8 /* !! */ .drawString("OK", 2, a.aX - a.lW + 3, 20);
                                    }
                                }
                            }
                            default: {
                                return;
                            }
                        }
                    }
                    catch (Exception v0) {
                        this = v0;
                        v0.printStackTrace();
                    }
                    return;
                    do {
                        if (a.fR <= a.in[var2_2] + 12 && a.fR + a.q >= a.in[var2_2] - 12 && a.fS <= a.io[var2_2] && a.fS + a.r >= a.io[var2_2] - 24) {
                            if (a.ip[var2_2] == 1) {
                                var1_1 /* !! */ .drawImage(a.ee[a.iq[var2_2]], a.in[var2_2], a.io[var2_2], 33);
                            } else {
                                var1_1 /* !! */ .drawRegion(a.ee[a.iq[var2_2]], 0, 0, a.ee[a.iq[var2_2]].getWidth(), a.ee[a.iq[var2_2]].getHeight(), 2, a.in[var2_2], a.io[var2_2], 33);
                            }
                        }
                        ++var2_2;
lbl226:
                        // 2 sources

                    } while (var2_2 < a.il);
                    var1_1 /* !! */  = this;
                    var2_2 = 0;
                    while (var2_2 < a.dh) {
                        if (a.dk[var2_2] != 0 && a.di[var2_2] >= a.fR - 10 && a.di[var2_2] <= a.fR + a.q + 10 && a.dj[var2_2] >= a.fS && a.dj[var2_2] <= a.fS + a.r + 20) {
                            var1_1 /* !! */ .drawRegion(a.ej[a.dp[var2_2]], 0, a.dn[var2_2] * 15, 8, 15, 0, a.di[var2_2], a.dj[var2_2], 33);
                        }
                        ++var2_2;
                    }
                    var1_1 /* !! */  = this;
                    var2_2 = 0;
                    while (var2_2 < a.cP) {
                        if (a.cT[var2_2] != 0 && a.cR[var2_2] >= a.fR - 10 && a.cR[var2_2] <= a.fR + a.q + 10 && a.cS[var2_2] >= a.fS && a.cS[var2_2] <= a.fS + a.r + 20) {
                            var1_1 /* !! */ .drawRegion(a.ez[a.cQ[var2_2]], 0, a.cW[var2_2] * 15, 13, 15, 0, a.cR[var2_2], a.cS[var2_2], 33);
                        }
                        ++var2_2;
                    }
                    var1_1 /* !! */  = this;
                    if (a.gQ && a.gV != 22 && a.fR + a.q >= a.gS - (a.gY >> 1) && a.fR <= a.gS + (a.gY >> 1) && a.fS <= a.gT && a.fS + a.r >= a.gT - a.gZ) {
                        var2_2 = 0;
                        if (a.gX == 0) {
                            var2_2 = -2;
                            if (a.gV == 1 && a.w % 10 > 5) {
                                var2_2 = -1;
                            }
                            if (a.gW == 1) {
                                var1_1 /* !! */ .drawImage(a.ey[4], a.gS, a.gT + 1, 33);
                            } else {
                                var1_1 /* !! */ .drawRegion(a.ey[4], 0, 0, a.ey[4].getWidth(), a.ey[4].getHeight(), 2, a.gS, a.gT + 1, 33);
                            }
                        }
                        if (a.gW == 1) {
                            var1_1 /* !! */ .drawImage(a.ey[a.gX], a.gS, a.gT + var2_2 + 1, 33);
                        } else {
                            var1_1 /* !! */ .drawRegion(a.ey[a.gX], 0, 0, a.ey[a.gX].getWidth(), a.ey[a.gX].getHeight(), 2, a.gS, a.gT + var2_2 + 1, 33);
                        }
                        if (a.gV == 7) {
                            if (a.gW == 1) {
                                var1_1 /* !! */ .drawImage(a.ey[3], a.gS + 10, a.gT - 25 + 1, 0);
                            } else {
                                var1_1 /* !! */ .drawRegion(a.ey[3], 0, 0, a.ey[3].getWidth(), a.ey[3].getHeight(), 2, a.gS - 10, a.gT - 25 + 1, 24);
                            }
                        }
                        if (a.he != 0) {
                            var1_1 /* !! */ .setColor(0x330011);
                            var1_1 /* !! */ .drawRect(a.gS - (a.gY >> 1) - 1, a.gT - a.gZ - 7, a.gY + 2, 4);
                            var3_9 = a.hc * a.gY / a.hd;
                            var1_1 /* !! */ .setColor(0xEE0000);
                            var1_1 /* !! */ .fillRect(a.gS - (a.gY >> 1), a.gT - a.gZ - 6, var3_9, 3);
                            var1_1 /* !! */ .setColor(0x440022);
                            var1_1 /* !! */ .fillRect(a.gS - (a.gY >> 1) + var3_9, a.gT - a.gZ - 6, a.gY - var3_9 + 1, 3);
                        }
                    }
                    var1_1 /* !! */  = this;
                    if (!a.hl) break block111;
                    if (a.hv <= 0) break block112;
                    var2_2 = 0;
                    if (true) ** GOTO lbl280
                }
                var2_2 = 0;
                if (true) ** GOTO lbl285
                do {
                    var1_1 /* !! */ .drawImage(a.eC[var2_2], a.hG[var2_2], a.hH[var2_2], 33);
                    ++var2_2;
lbl280:
                    // 2 sources

                } while (var2_2 < 6);
                break block113;
                do {
                    var1_1 /* !! */ .drawRegion(a.eC[var2_2], 0, 0, a.eC[var2_2].getWidth(), a.eC[var2_2].getHeight(), 2, a.hG[var2_2], a.hH[var2_2], 33);
                    ++var2_2;
lbl285:
                    // 2 sources

                } while (var2_2 < 6);
            }
            if (a.hr != 0) {
                var1_1 /* !! */ .setColor(0x330011);
                var1_1 /* !! */ .drawRect(a.hs - (a.hn >> 1) - 1, a.ht - a.ho - 7, a.hn + 2, 4);
                var2_2 = a.hp * a.hn / a.hq;
                var1_1 /* !! */ .setColor(0xEE0000);
                var1_1 /* !! */ .fillRect(a.hs - (a.hn >> 1), a.ht - a.ho - 6, var2_2, 3);
                var1_1 /* !! */ .setColor(0x440022);
                var1_1 /* !! */ .fillRect(a.hs - (a.hn >> 1) + var2_2, a.ht - a.ho - 6, a.hn - var2_2 + 1, 3);
            }
        }
        var1_1 /* !! */  = this;
        var2_2 = 0;
        while (var2_2 < a.jK) {
            if (a.jS[var2_2] != 5 && a.jS[var2_2] != 8 && a.jN[var2_2] >= a.fR - 10 && a.jN[var2_2] <= a.fR + a.q + 10 && a.jO[var2_2] >= a.fS && a.jO[var2_2] <= a.fS + a.r + 20) {
                var1_1 /* !! */ .drawImage(a.jJ[a.kk[var2_2]][a.kb[var2_2] >= 0 ? 0 : 1][a.ka[var2_2]], a.jN[var2_2], a.jO[var2_2], 33);
                if (a.jR[var2_2] != 0) {
                    var1_1 /* !! */ .setColor(13073);
                    var1_1 /* !! */ .drawRect(a.jN[var2_2] - (a.jQ[var2_2] >> 1) - 1, a.jO[var2_2] - a.jP[var2_2] - 7, a.jQ[var2_2] + 2, 4);
                    var3_9 = a.kc[var2_2] * a.jQ[var2_2] / a.kd[var2_2];
                    var1_1 /* !! */ .setColor(60928);
                    var1_1 /* !! */ .fillRect(a.jN[var2_2] - (a.jQ[var2_2] >> 1), a.jO[var2_2] - a.jP[var2_2] - 6, var3_9, 3);
                    var1_1 /* !! */ .setColor(17442);
                    var1_1 /* !! */ .fillRect(a.jN[var2_2] - (a.jQ[var2_2] >> 1) + var3_9, a.jO[var2_2] - a.jP[var2_2] - 6, a.jQ[var2_2] - var3_9 + 1, 3);
                }
            }
            ++var2_2;
        }
        var1_1 /* !! */  = this;
        var2_2 = 0;
        while (var2_2 < a.cs) {
            if (a.cH[var2_2] != 5 && a.cx[var2_2] + 15 >= a.fR && a.cx[var2_2] - 15 <= a.fR + a.q && a.cy[var2_2] >= a.fS && a.cy[var2_2] - 30 <= a.fS + a.r) {
                if (a.cK[var2_2] == -1) {
                    var1_1 /* !! */ .drawImage(a.ex[a.cL[var2_2]], a.cx[var2_2] + a.ai[a.cG[var2_2]], a.cy[var2_2], 33);
                    var1_1 /* !! */ .drawImage(a.ew[a.cG[var2_2]][a.cM[var2_2]], a.cz[var2_2] + a.ag[a.cG[var2_2]][a.cM[var2_2]], a.cA[var2_2], 33);
                } else {
                    var1_1 /* !! */ .drawRegion(a.ex[a.cL[var2_2]], 0, 0, a.ex[a.cL[var2_2]].getWidth(), a.ex[a.cL[var2_2]].getHeight(), 2, a.cx[var2_2] - a.ai[a.cG[var2_2]], a.cy[var2_2], 33);
                    var1_1 /* !! */ .drawRegion(a.ew[a.cG[var2_2]][a.cM[var2_2]], 0, 0, a.ew[a.cG[var2_2]][a.cM[var2_2]].getWidth(), a.ew[a.cG[var2_2]][a.cM[var2_2]].getHeight(), 2, a.cz[var2_2] - a.ag[a.cG[var2_2]][a.cM[var2_2]], a.cA[var2_2], 33);
                }
                if (a.ct[var2_2] != 0) {
                    var1_1 /* !! */ .setColor(13073);
                    var1_1 /* !! */ .drawRect(a.cx[var2_2] - 13, a.cy[var2_2] - 40, 26, 4);
                    var3_9 = a.cu[var2_2] * 24 / a.af[a.cG[var2_2]];
                    var1_1 /* !! */ .setColor(0xEE0000);
                    var1_1 /* !! */ .fillRect(a.cx[var2_2] - 12, a.cy[var2_2] - 39, var3_9, 3);
                    var1_1 /* !! */ .setColor(0x440022);
                    var1_1 /* !! */ .fillRect(a.cx[var2_2] - 12 + var3_9, a.cy[var2_2] - 39, 24 - var3_9 + 1, 3);
                }
            }
            ++var2_2;
        }
        a.g((Graphics)this);
        var1_1 /* !! */  = this;
        if (a.dq && a.dr + 15 >= a.fR && a.dr - 15 <= a.fR + a.q && a.ds >= a.fS && a.ds - 10 <= a.fS + a.r) {
            v1 = var2_2 = a.w % 8 > 3 ? 0 : 1;
            if (a.dt > 0) {
                var1_1 /* !! */ .drawImage(a.el[var2_2], a.dr, a.ds, 33);
            } else {
                var1_1 /* !! */ .drawRegion(a.el[var2_2], 0, 0, a.el[var2_2].getWidth(), a.el[var2_2].getHeight(), 2, a.dr, a.ds, 33);
            }
        }
        var1_1 /* !! */  = this;
        if (a.hf) {
            if (a.hg) {
                var1_1 /* !! */ .setColor(0xFFDDDD);
                var2_2 = a.fR + 50;
                var3_9 = a.fS + 15;
                if (!a.bh) {
                    var3_9 += 20;
                }
                var1_1 /* !! */ .drawRect(var2_2 - 35 - 1, var3_9 - 7, 72, 4);
                var4_11 = a.hj * 70 / a.hk;
                var1_1 /* !! */ .setColor(0xEE0000);
                var1_1 /* !! */ .fillRect(var2_2 - 35, var3_9 - 6, var4_11, 3);
                var1_1 /* !! */ .setColor(0x440022);
                var1_1 /* !! */ .fillRect(var2_2 - 35 + var4_11, var3_9 - 6, 70 - var4_11 + 1, 3);
            }
            if (a.dC[0] + a.fl >= a.fR && a.dC[0] - a.fl <= a.fR + a.q && a.dD[0] >= a.fS && a.dD[0] - a.fk <= a.fS + a.r) {
                if (a.dM[0] == 1) {
                    var1_1 /* !! */ .drawImage(a.ev[a.dK[0]][a.aJ[a.dJ[0]][0][0]], a.dC[0] + a.aJ[a.dJ[0]][0][1], a.dD[0] - a.aJ[a.dJ[0]][0][2], 0);
                    var1_1 /* !! */ .drawImage(a.et[a.aJ[a.dJ[0]][1][0]], a.dC[0] + a.aJ[a.dJ[0]][1][1], a.dD[0] - a.aJ[a.dJ[0]][1][2], 0);
                    var1_1 /* !! */ .drawImage(a.eg[a.aJ[a.dJ[0]][2][0]], a.dC[0] + a.aJ[a.dJ[0]][2][1], a.dD[0] - a.aJ[a.dJ[0]][2][2], 0);
                    if (a.dL[0] != -1) {
                        var1_1 /* !! */ .drawImage(a.eE[a.dL[0]], a.dC[0] - a.at[a.dL[0]], a.dD[0] - a.au[a.dL[0]], 0);
                    }
                } else {
                    var1_1 /* !! */ .drawRegion(a.ev[a.dK[0]][a.aJ[a.dJ[0]][0][0]], 0, 0, a.ev[a.dK[0]][a.aJ[a.dJ[0]][0][0]].getWidth(), a.ev[a.dK[0]][a.aJ[a.dJ[0]][0][0]].getHeight(), 2, a.dC[0] - a.aJ[a.dJ[0]][0][1], a.dD[0] - a.aJ[a.dJ[0]][0][2], 24);
                    var1_1 /* !! */ .drawRegion(a.et[a.aJ[a.dJ[0]][1][0]], 0, 0, a.et[a.aJ[a.dJ[0]][1][0]].getWidth(), a.et[a.aJ[a.dJ[0]][1][0]].getHeight(), 2, a.dC[0] - a.aJ[a.dJ[0]][1][1], a.dD[0] - a.aJ[a.dJ[0]][1][2], 24);
                    var1_1 /* !! */ .drawRegion(a.eg[a.aJ[a.dJ[0]][2][0]], 0, 0, a.eg[a.aJ[a.dJ[0]][2][0]].getWidth(), a.eg[a.aJ[a.dJ[0]][2][0]].getHeight(), 2, a.dC[0] - a.aJ[a.dJ[0]][2][1], a.dD[0] - a.aJ[a.dJ[0]][2][2], 24);
                    if (a.dL[0] != -1) {
                        var1_1 /* !! */ .drawRegion(a.eE[a.dL[0]], 0, 0, a.eE[a.dL[0]].getWidth(), a.eE[a.dL[0]].getHeight(), 2, a.dC[0] + a.at[a.dL[0]], a.dD[0] - a.au[a.dL[0]], 24);
                    }
                }
            }
        }
        var1_1 /* !! */  = this;
        if (a.ea != 0 && (var2_2 = a.ea % 3) < 2) {
            var1_1 /* !! */ .drawImage(a.eu[var2_2], a.dY, a.dZ, 17);
        }
        var1_1 /* !! */  = this;
        if (a.gp == a.v) {
            if (a.gC == 1) {
                var1_1 /* !! */ .drawImage(a.ep[a.ar[a.gv][0][0]], a.gq + a.ar[a.gv][0][1], a.gr - a.ar[a.gv][0][2], 0);
                var1_1 /* !! */ .drawImage(a.eq[a.ar[a.gv][1][0]], a.gq + a.ar[a.gv][1][1], a.gr - a.ar[a.gv][1][2], 0);
                var1_1 /* !! */ .drawImage(a.eo[a.ar[a.gv][2][0]], a.gq + a.ar[a.gv][2][1], a.gr - a.ar[a.gv][2][2], 0);
                if (a.ij != -1) {
                    var1_1 /* !! */ .drawImage(a.eE[a.ij], a.gq - a.at[a.ij], a.gr - a.au[a.ij], 0);
                }
            } else {
                var1_1 /* !! */ .drawRegion(a.ep[a.ar[a.gv][0][0]], 0, 0, a.ep[a.ar[a.gv][0][0]].getWidth(), a.ep[a.ar[a.gv][0][0]].getHeight(), 2, a.gq - a.ar[a.gv][0][1], a.gr - a.ar[a.gv][0][2], 24);
                var1_1 /* !! */ .drawRegion(a.eq[a.ar[a.gv][1][0]], 0, 0, a.eq[a.ar[a.gv][1][0]].getWidth(), a.eq[a.ar[a.gv][1][0]].getHeight(), 2, a.gq - a.ar[a.gv][1][1], a.gr - a.ar[a.gv][1][2], 24);
                var1_1 /* !! */ .drawRegion(a.eo[a.ar[a.gv][2][0]], 0, 0, a.eo[a.ar[a.gv][2][0]].getWidth(), a.eo[a.ar[a.gv][2][0]].getHeight(), 2, a.gq - a.ar[a.gv][2][1], a.gr - a.ar[a.gv][2][2], 24);
                if (a.ij != -1) {
                    var1_1 /* !! */ .drawRegion(a.eE[a.ij], 0, 0, a.eE[a.ij].getWidth(), a.eE[a.ij].getHeight(), 2, a.gq + a.at[a.ij], a.gr - a.au[a.ij], 24);
                }
            }
        }
        a.b((Graphics)this);
        var1_1 /* !! */  = this;
        if (a.fK != 0) {
            var1_1 /* !! */ .drawImage(a.ef, a.fI, a.fJ, 33);
        }
        var1_1 /* !! */  = this;
        var2_2 = 0;
        while (var2_2 < 2) {
            if (a.cf[var2_2] != -1) {
                if (a.ch[var2_2] == 1) {
                    var1_1 /* !! */ .drawImage(a.eB[a.cg[var2_2]], a.cd[var2_2], a.ce[var2_2], 3);
                } else {
                    var1_1 /* !! */ .drawRegion(a.eB[a.cg[var2_2]], 0, 0, a.eB[a.cg[var2_2]].getWidth(), a.eB[a.cg[var2_2]].getHeight(), 2, a.cd[var2_2], a.ce[var2_2], 3);
                }
            }
            ++var2_2;
        }
        var1_1 /* !! */  = this;
        if (!a.a) {
            var2_2 = 0;
            while (var2_2 < 2) {
                if (a.co[var2_2] != -1) {
                    var1_1 /* !! */ .drawImage(a.em[var2_2][a.co[var2_2]], a.cm[var2_2], a.cn[var2_2], 3);
                }
                ++var2_2;
            }
        }
        var1_1 /* !! */  = this;
        var2_2 = 0;
        while (var2_2 < a.bP) {
            if (a.bS[var2_2] != -1) {
                var1_1 /* !! */ .drawImage(a.eb[a.bV[var2_2]][a.bT[var2_2] > 0 ? 0 : 1], a.bQ[var2_2], a.bR[var2_2], 3);
            }
            ++var2_2;
        }
        var1_1 /* !! */  = this;
        var2_2 = 0;
        while (var2_2 < a.cY) {
            if (a.db[var2_2] != -1) {
                var1_1 /* !! */ .drawImage(a.en[a.dc[var2_2]], a.cZ[var2_2], a.da[var2_2], 3);
            }
            ++var2_2;
        }
        var1_1 /* !! */  = this;
        var2_2 = 0;
        while (var2_2 < a.bW) {
            if (a.bZ[var2_2] != 0) {
                var1_1 /* !! */ .drawImage(a.ed, a.bX[var2_2], a.bY[var2_2], 3);
            }
            ++var2_2;
        }
        var1_1 /* !! */  = this;
        if (!a.a) {
            var2_2 = 0;
            while (var2_2 < 2) {
                if (a.ck[var2_2] != -1) {
                    var1_1 /* !! */ .drawImage(a.eG[a.cl[var2_2]], a.ci[var2_2], a.cj[var2_2], 3);
                }
                ++var2_2;
            }
        }
        a.l((Graphics)this);
        var1_1 /* !! */  = this;
        var2_2 = 0;
        while (var2_2 < 5) {
            if (a.dW[var2_2] != -1) {
                Midlet.a(var1_1 /* !! */ , a.dR[var2_2], a.dX[var2_2], a.dS[var2_2], a.dT[var2_2], 0);
            }
            ++var2_2;
        }
        var1_1 /* !! */  = this;
        if (a.ge == a.v) {
            var1_1 /* !! */ .drawImage(a.ec[a.gh], a.gi, a.gj, 3);
        }
        a.c((Graphics)this);
        if (a.c) {
            a.d((Graphics)this);
        }
        if (a.bx > 1) {
            a.e((Graphics)this);
        }
        if (a.cp) {
            var1_1 /* !! */  = this;
            var1_1 /* !! */ .translate(-var1_1 /* !! */ .getTranslateX(), -var1_1 /* !! */ .getTranslateY());
            var1_1 /* !! */ .setClip(-1, -1, a.aW + 2, a.aX + 2);
            var1_1 /* !! */ .setColor(0);
            var1_1 /* !! */ .fillRect(0, 0, a.aW, 21);
            var1_1 /* !! */ .setColor(10275899);
            var1_1 /* !! */ .fillRect(0, 19, a.aW, 1);
            if (a.w % 10 > 2) {
                Midlet.a(var1_1 /* !! */ , a.cr, a.bF, 4, 2);
            }
        }
        if (a.iC == false) return;
        a.f((Graphics)this);
    }

    protected final void pointerDragged(int n, int n2) {
        aQ = n;
        aR = n2;
    }

    protected final void pointerPressed(int n, int n2) {
        aS = true;
        aQ = n;
        aR = n2;
    }

    protected final void pointerReleased(int n, int n2) {
        aS = false;
        aP = true;
        aQ = n;
        aR = n2;
    }

    /*
     * Unable to fully structure code
     */
    public final void run() {
        if (!a.aO) {
            a.aO = true;
            a.d = 13;
            a.Y = 8;
        }
        while (a.aO) {
            block754: {
                var1_1 = System.currentTimeMillis();
                block3 : switch (a.d) {
                    case 1: {
                        if (a.aP) {
                            a.aP = false;
                            a.c();
                            if (a.fV) {
                                a.aV[5] = true;
                            } else if (a.aQ > a.aW - 40 && a.aR < 40) {
                                a.aV[5] = true;
                            } else if (a.aR > a.aX - 30) {
                                if (a.aQ < a.aY) {
                                    a.aV[12] = true;
                                } else {
                                    a.aV[13] = true;
                                }
                            } else if (a.aR <= a.fi - a.fS + 24 && a.aR >= a.fi - a.fS - 80 && a.aQ <= a.fh - a.fR + 48 && a.aQ >= a.fh - a.fR - 48) {
                                a.fp = a.fh - a.fR < a.aQ ? 1 : -1;
                                a.aV[5] = true;
                            }
                        }
                        if (a.aS && !a.fV && (a.aQ <= a.aW - 40 || a.aR >= 40) && a.aR <= a.aX - 30) {
                            if (a.aR > a.fi - a.fS + 24) {
                                a.aU[8] = true;
                            } else if (a.aR < a.fi - a.fS - 80) {
                                a.aU[2] = true;
                            } else if (a.aQ > a.fh - a.fR + 48) {
                                a.aU[6] = true;
                            } else if (a.aQ < a.fh - a.fR - 48) {
                                a.aU[4] = true;
                            }
                        }
                        if (++a.w == 10000) {
                            a.w = 0;
                        }
                        if (a.fh < 0) {
                            if (a.jr != -1) {
                                a.B();
                                a.fs = a.fh = a.jv - 1;
                                a.ft = a.fi = a.jw;
                                Midlet.f = true;
                                a.x(a.jr);
                                break;
                            }
                        } else if (a.fh >= a.iR - 24) {
                            if (a.js != -1) {
                                if (!a.fc && a.js == 95) {
                                    a.s(6);
                                    a.a("Khu v\u1ef1c ph\u00eda tr\u01b0\u1edbc r\u1ea5t nguy hi\u1ec3m! Ch\u1ec9 nh\u1eefng ng\u01b0\u1eddi c\u00f3 th\u1ec3 \u1ea9n th\u00e2n c\u00f3 th\u1ec3 v\u00e0o!");
                                    a.fh = a.iR - 60;
                                    a.A = 4;
                                    a.d = 7;
                                    break;
                                }
                                a.B();
                                if (a.jt != -1 && a.fi > a.jD) {
                                    a.fs = a.fh = a.jz;
                                    a.ft = a.fi = a.jA;
                                    Midlet.f = true;
                                    a.x(a.jt);
                                    break;
                                }
                                a.fs = a.fh = a.jx;
                                a.ft = a.fi = a.jy;
                                Midlet.f = true;
                                a.x(a.js);
                                break;
                            }
                        } else if (a.fi + a.z >= a.iS && a.A != 6 && a.ju != -1) {
                            a.B();
                            a.fs = a.fh = a.jB;
                            a.ft = a.fi = a.jC;
                            Midlet.f = true;
                            a.x(a.ju);
                            a.A = 4;
                            break;
                        }
                        a.ad();
                        a.X();
                        if (a.V()) break;
                        if (!a.a) {
                            var3_2 = 0;
                            while (var3_2 < 2) {
                                if (a.w % (var3_2 + 2 << 3) == 0) {
                                    v0 = var3_2;
                                    a.e[v0] = a.e[v0] + 1;
                                    if (a.e[var3_2] > a.q + (a.t.getWidth() >> 1)) {
                                        a.e[var3_2] = -(a.t.getWidth() >> 1);
                                    }
                                }
                                ++var3_2;
                            }
                        }
                        if (!a.iC) ** GOTO lbl102
                        if (a.aV[5]) {
                            a.d();
                            a.M();
                            if (a.iF == a.iE) {
                                a.iC = false;
                                a.z();
                            }
                        }
                        a.c = true;
                        ** GOTO lbl817
lbl102:
                        // 1 sources

                        if (!a.aV[12]) ** GOTO lbl108
                        a.ar();
                        a.d = 5;
                        a.c();
                        a.d();
                        ** GOTO lbl165
lbl108:
                        // 1 sources

                        if (!a.aV[0]) ** GOTO lbl117
                        if (a.fg == 0) {
                            if (a.eN) {
                                a.fg = 1;
                            }
                        } else {
                            a.fg = 0;
                        }
                        a.c = true;
                        a.aV[0] = false;
                        ** GOTO lbl165
lbl117:
                        // 1 sources

                        if (!a.aV[6]) ** GOTO lbl121
                        a.fr = 2;
                        a.aV[6] = false;
                        ** GOTO lbl165
lbl121:
                        // 1 sources

                        if (!a.aV[4]) ** GOTO lbl125
                        a.fr = -2;
                        a.aV[4] = false;
                        ** GOTO lbl165
lbl125:
                        // 1 sources

                        if (!a.aV[9]) ** GOTO lbl130
                        if (a.fc) {
                            a.fz = a.fz == false;
                        }
                        a.aV[9] = false;
                        ** GOTO lbl817
lbl130:
                        // 1 sources

                        if (!a.aV[7]) ** GOTO lbl136
                        if (a.A == 1 && a.eT) {
                            a.A = 23;
                            a.C = 0;
                        }
                        a.aV[7] = false;
                        ** GOTO lbl165
lbl136:
                        // 1 sources

                        if (!a.aV[10]) ** GOTO lbl151
                        a.aV[10] = false;
                        if (a.G >= a.H) ** GOTO lbl165
                        var3_2 = 0;
                        while (var3_2 < 3) {
                            if (a.V[var3_2] <= 0) ** GOTO lbl148
                            v1 = var3_2;
                            a.V[v1] = a.V[v1] - 1;
                            if ((a.G += a.kJ[var3_2]) > a.H) {
                                a.G = a.H;
                            }
                            a.c = true;
                            ** GOTO lbl165
lbl148:
                            // 1 sources

                            ++var3_2;
                        }
                        ** GOTO lbl165
lbl151:
                        // 1 sources

                        if (!a.aV[11]) ** GOTO lbl165
                        a.aV[11] = false;
                        if (a.I >= a.J) ** GOTO lbl165
                        var3_2 = 3;
                        while (var3_2 < 6) {
                            if (a.V[var3_2] <= 0) ** GOTO lbl163
                            v2 = var3_2;
                            a.V[v2] = a.V[v2] - 1;
                            if ((a.I += a.kJ[var3_2]) > a.J) {
                                a.I = a.J;
                            }
                            a.c = true;
                            break;
lbl163:
                            // 1 sources

                            ++var3_2;
                        }
lbl165:
                        // 12 sources

                        if (a.A != 1) ** GOTO lbl573
                        if (!a.aV[13]) ** GOTO lbl489
                        a.d();
                        if (a.o.equals("Giao ti\u1ebfp")) {
                            var4_8 = a.fG;
                            switch (a.lA[var4_8]) {
                                case 0: {
                                    var5_9 = var4_8;
                                    if (a.fe) {
                                        a.a(a.lF[7], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.eL) {
                                        a.V(2);
                                        a.a(a.lC[0], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (a.fc && !a.fd) {
                                        if (a.O == 16) {
                                            if (a.eK) {
                                                a.a(a.lD[14], 35, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.a(a.lE[14], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[22], 36, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    a.a(a.lF[1], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                    break;
                                }
                                case 1: {
                                    var5_9 = var4_8;
                                    if (a.fe) {
                                        a.a(a.lF[5], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.eL) {
                                        if (a.O == 0) {
                                            if (a.fF < a.lI[0]) {
                                                a.a(a.lE[0], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            var3_2 = 4;
                                            var8_12 = 240;
                                            var7_11 = 1044;
                                            var6_10 = 0;
                                            a.lK = 0;
                                            a.lN = 4;
                                            a.lL = 1044;
                                            a.lM = 240;
                                            a.a(a.lD[0], 4, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[1], 3, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.eU && a.eT) {
                                        if (a.M < 10) {
                                            a.a(new String[]{"H\u00e3y quay l\u1ea1i g\u1eb7p ta khi con \u0111\u1ea1t c\u1ea5p \u0111\u1ed9 10"}, -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == 7) {
                                            if (a.fF < a.lI[7]) {
                                                a.a(a.lE[6], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(2);
                                            a.a(a.lD[6], 19, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[14], 18, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (a.eX && !a.eY) {
                                        if (a.M < 12) {
                                            a.a(new String[]{"H\u00e3y quay l\u1ea1i g\u1eb7p ta khi con \u0111\u1ea1t c\u1ea5p \u0111\u1ed9 12"}, -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == 11) {
                                            if (a.fF < 20) {
                                                a.a(a.lE[9], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(4);
                                            a.a(a.lD[9], 27, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[17], 28, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.fa && a.eZ) {
                                        if (a.M < 15) {
                                            a.a(new String[]{"H\u00e3y quay l\u1ea1i g\u1eb7p ta khi con \u0111\u1ea1t c\u1ea5p \u0111\u1ed9 15"}, -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == 13) {
                                            if (a.fF < a.lI[13]) {
                                                a.a(a.lE[11], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(3);
                                            a.a(a.lD[11], 31, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[19], 32, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    a.a(a.lF[0], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                    break;
                                }
                                case 2: {
                                    var5_9 = var4_8;
                                    if (a.fe) {
                                        a.a(a.lF[9], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.eL) {
                                        a.V(1);
                                        a.a(a.lC[2], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (a.eO && !a.eP) {
                                        if (a.O == 3) {
                                            a.a(a.lC[9], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[10], 11, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (a.eU && !a.eV) {
                                        if (a.O == 8) {
                                            if (a.eJ) {
                                                a.V(2);
                                                a.a(a.lD[7], 23, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.a(a.lE[7], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[15], 22, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (a.eV && !a.eW) {
                                        if (a.O == 9) {
                                            if (a.fF < a.lI[9]) {
                                                a.a(a.lE[8], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(3);
                                            a.a(a.lD[8], 25, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[16], 24, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    a.a(a.lF[3], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                    break;
                                }
                                case 3: {
                                    var5_9 = var4_8;
                                    if (a.fe) {
                                        a.a(a.lF[9], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.eL || !a.eN) {
                                        a.a(a.lC[5], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.eO) {
                                        if (a.O == 2) {
                                            if (!a.fD) {
                                                a.a(a.lE[2], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(2);
                                            a.a(a.lD[2], 10, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[8], 9, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.eR && a.eQ) {
                                        if (a.O == 5) {
                                            if (!a.fD) {
                                                a.a(a.lE[4], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(4);
                                            a.a(a.lD[4], 15, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[12], 14, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.fb && a.fa) {
                                        if (a.M < 16) {
                                            a.a(new String[]{"H\u00e3y quay l\u1ea1i g\u1eb7p ta khi con \u0111\u1ea1t c\u1ea5p \u0111\u1ed9 16"}, -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == 14) {
                                            if (a.fF < a.lI[14]) {
                                                a.a(a.lE[12], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(3);
                                            a.a(a.lD[12], 33, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[20], 34, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.fc && a.fb) {
                                        if (a.O == 15) {
                                            if (!a.fD) {
                                                a.a(a.lE[13], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(0);
                                            a.a(a.lD[13], 21, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[21], 20, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    a.a(a.lF[4], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                    break;
                                }
                                case 4: {
                                    var5_9 = var4_8;
                                    if (a.fe) {
                                        a.a(a.lF[8], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (a.v == 99) {
                                        a.a(a.lC[7], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.eL) {
                                        a.a(a.lC[4], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.eN) {
                                        if (a.O == 1) {
                                            if (a.fF < a.lI[1]) {
                                                a.a(a.lE[3], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(3);
                                            a.a(a.lD[1], 6, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[6], 5, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (a.eP && !a.eQ) {
                                        if (a.O == 4) {
                                            if (a.fF < a.lI[4]) {
                                                a.a(a.lE[3], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(3);
                                            a.a(a.lD[3], 12, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[11], 13, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (a.eR && !a.eT) {
                                        if (a.M < 8) {
                                            a.a(new String[]{"H\u00e3y quay l\u1ea1i g\u1eb7p ta khi con \u0111\u1ea1t c\u1ea5p \u0111\u1ed9 8"}, -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == 6) {
                                            if (!a.fD) {
                                                a.a(a.lE[5], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(1);
                                            a.a(a.lD[5], 17, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[13], 16, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    if (!a.eZ && a.eY) {
                                        if (a.O == 12) {
                                            if (a.fF < a.lI[12]) {
                                                a.a(a.lE[10], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                                break;
                                            }
                                            a.V(1);
                                            a.a(a.lD[10], 29, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        if (a.O == -1) {
                                            a.a(a.lC[18], 30, a.ly[var5_9], a.lz[var5_9] - 40);
                                            break;
                                        }
                                        a.a(a.lG, 1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        break;
                                    }
                                    a.a(a.lF[2], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                    break;
                                }
                                case 5: {
                                    var5_9 = var4_8;
                                    if (a.v == 0) {
                                        if (var5_9 == 5) {
                                            a.a(a.lB[0], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                        }
                                        if (var5_9 == 6) {
                                            a.ao();
                                            a.d = 9;
                                            a.kv = 0;
                                        }
                                    }
                                    if (a.v == 2 && var5_9 == 0) {
                                        a.ao();
                                        a.d = 9;
                                        a.kv = 1;
                                    }
                                    if (a.v == 1 && var5_9 == 0) {
                                        a.a(a.lB[1], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                    }
                                    if (a.v == 5 && var5_9 == 0) {
                                        a.a(a.lB[2], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                    }
                                    if (a.v != 6 || var5_9 != 0) break;
                                    a.a(a.lB[3], -1, a.ly[var5_9], a.lz[var5_9] - 40);
                                }
                            }
                            if (a.v == a.ge && var4_8 == a.gm) {
                                a.ge = -1;
                            }
                        } else if (a.o.equals("Tr\u00f2 chuy\u1ec7n")) {
                            a.d();
                            a.dM[a.fH] = -a.fp;
                            a.a(a.dy[a.fH], 0, a.dC[a.fH], a.dD[a.fH] - 44);
                        } else if (a.o.equals("N\u00f3i chuy\u1ec7n") && a.fE) {
                            if (a.gn) {
                                a.a(a.aI[0], 0, a.gq, a.gr - 44);
                            } else if (a.eX) {
                                a.a(a.aI[2], 0, a.gq, a.gr - 44);
                                a.gn = true;
                            } else if (a.eW) {
                                a.a(a.aI[3], 26, a.gq, a.gr - 44);
                                a.eW = false;
                            } else {
                                a.a(a.aI[1], 0, a.gq, a.gr - 44);
                            }
                        }
                        ** GOTO lbl817
lbl489:
                        // 1 sources

                        if (!a.aU[2]) ** GOTO lbl512
                        if (!a.eP) ** GOTO lbl503
                        var3_2 = 0;
                        while (var3_2 < a.il) {
                            if (a.fi != a.io[var3_2] || a.a(a.fh - a.in[var3_2]) >= 20) ** GOTO lbl501
                            a.fh = a.in[var3_2];
                            a.A = 18;
                            a.c();
                            a.im = var3_2;
                            a.ip[var3_2] = a.fp;
                            a.iq[var3_2] = 0;
                            ** GOTO lbl817
lbl501:
                            // 1 sources

                            ++var3_2;
                        }
lbl503:
                        // 2 sources

                        a.z = a.eL != false ? -10 : -8;
                        a.A = 3;
                        a.iu = -1;
                        a.C = 0;
                        a.fG = -1;
                        a.fH = -1;
                        a.o = " ";
                        a.c = true;
                        ** GOTO lbl562
lbl512:
                        // 1 sources

                        if (a.aU[4]) {
                            if (a.fp == 1) {
                                a.fp = -1;
                            } else {
                                a.A = 2;
                                a.y = -a.P;
                            }
                            a.fG = -1;
                            a.fH = -1;
                            a.o = " ";
                            a.c = true;
                        } else if (a.aU[6]) {
                            if (a.fp == -1) {
                                a.fp = 1;
                            } else {
                                a.A = 2;
                                a.y = a.P;
                            }
                            a.fH = -1;
                            a.fG = -1;
                            a.o = " ";
                            a.c = true;
                        } else if (a.aU[8]) {
                            a.C = 0;
                            a.D = 0;
                            a.A = 8;
                        } else if (a.aU[1]) {
                            a.z = a.eL != false ? -10 : -8;
                            a.fp = -1;
                            a.y = -4;
                            a.A = 3;
                            a.iu = -1;
                            a.C = 0;
                            a.fG = -1;
                            a.fH = -1;
                            a.o = " ";
                            a.c = true;
                        } else if (a.aU[3]) {
                            a.z = a.eL != false ? -10 : -8;
                            a.fp = 1;
                            a.y = 4;
                            a.A = 3;
                            a.iu = -1;
                            a.C = 0;
                            a.fG = -1;
                            a.fH = -1;
                            a.o = " ";
                            a.c = true;
                        }
lbl562:
                        // 8 sources

                        if (a.aV[5]) {
                            a.A = 5;
                            a.fw = 0;
                            a.C = 0;
                            a.z = 0;
                            a.aV[5] = false;
                            a.fG = -1;
                            a.fH = -1;
                            a.o = " ";
                            a.c = true;
                        }
                        ** GOTO lbl817
lbl573:
                        // 1 sources

                        if (a.A == 2) {
                            if (a.aU[2]) {
                                a.iu = -1;
                                a.z = a.eL != false ? -10 : -8;
                                a.A = 3;
                                a.C = 0;
                            } else if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                } else {
                                    a.y = -a.P + a.fr;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                } else {
                                    a.y = a.P + a.fr;
                                }
                            }
                            if (a.aV[5]) {
                                a.A = 5;
                                a.fw = 0;
                                a.C = 0;
                                a.z = 0;
                                a.aV[5] = false;
                            }
                        } else if (a.A == 11) {
                            if (a.aU[2]) {
                                a.z = a.eL != false ? -10 : -8;
                                a.A = 3;
                                a.C = 0;
                            } else if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                } else {
                                    a.y = -a.K + a.fr;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                } else {
                                    a.y = a.K + a.fr;
                                }
                            }
                        } else if (a.A == 3) {
                            if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                } else {
                                    a.y = -a.P;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                } else {
                                    a.y = a.P;
                                }
                            }
                            if (a.aV[5]) {
                                a.A = 5;
                                a.fw = 0;
                                a.C = 0;
                                a.aV[5] = false;
                            }
                            if (a.aU[2] && a.I > 0 && a.z > -4 && (a.C == 0 && a.eM || a.C == 1 && a.eY)) {
                                ++a.C;
                                a.z = -7;
                                if ((a.I -= 3) < 0) {
                                    a.I = 0;
                                }
                                a.c = true;
                                a.e(-1, a.fh - -8, a.fi);
                                a.e(1, a.fh - 8, a.fi);
                                a.g(a.fh, a.fi);
                            }
                        } else if (a.A == 4) {
                            if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                } else {
                                    a.y = -a.P;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                } else {
                                    a.y = a.P;
                                }
                            }
                            if (a.aV[5] && a.z > 4) {
                                a.A = 5;
                                a.C = 0;
                                a.fw = 0;
                                a.aV[5] = false;
                            }
                        } else if (a.A == 5) {
                            if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                } else {
                                    a.y = -a.P + 2;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                } else {
                                    a.y = a.P - 2;
                                }
                            }
                            if (a.aV[5]) {
                                a.fy = true;
                                a.aV[5] = false;
                            }
                        } else if (a.A == 18) {
                            if (a.aU[2]) {
                                a.z = -10;
                                a.A = 21;
                                a.C = 10;
                                a.iu = -1;
                            } else if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                    a.ip[a.im] = -1;
                                } else {
                                    a.C = 1;
                                    a.A = 19;
                                    a.C = 10;
                                    a.y = -a.Q;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                    a.ip[a.im] = 1;
                                } else {
                                    a.A = 19;
                                    a.C = 10;
                                    a.y = a.Q;
                                }
                            } else if (a.aU[8]) {
                                a.A = 1;
                                a.im = -1;
                            }
                        } else if (a.A == 19) {
                            if (a.aU[2]) {
                                a.z = -10;
                                a.A = 21;
                                a.iu = -1;
                                a.C = 10;
                            } else if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                    a.ip[a.im] = -1;
                                } else {
                                    a.y = -a.Q;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                    a.ip[a.im] = 1;
                                } else {
                                    a.y = a.Q;
                                }
                            }
                        } else if (a.A == 21) {
                            if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                    a.ip[a.im] = -1;
                                } else {
                                    a.y = -a.Q;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                    a.ip[a.im] = 1;
                                } else {
                                    a.y = a.Q;
                                }
                            }
                        } else if (a.A == 20) {
                            if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                    a.ip[a.im] = -1;
                                } else {
                                    a.y = -a.Q;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                    a.ip[a.im] = 1;
                                } else {
                                    a.y = a.Q;
                                }
                            }
                        } else if (a.A == 17) {
                            if (a.aU[2]) {
                                a.z = -10;
                                a.A = 3;
                                a.ff = -1;
                                a.C = 0;
                            } else if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                } else {
                                    a.A = 16;
                                    a.y = -a.L;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                } else {
                                    a.A = 16;
                                    a.y = a.L;
                                }
                            }
                        } else if (a.A == 16) {
                            if (a.aU[2]) {
                                a.z = -10;
                                a.A = 3;
                                a.ff = -1;
                                a.C = 0;
                            } else if (a.aU[4]) {
                                if (a.fp == 1) {
                                    a.fp = -1;
                                } else {
                                    a.y = -a.L;
                                }
                            } else if (a.aU[6]) {
                                if (a.fp == -1) {
                                    a.fp = 1;
                                } else {
                                    a.y = a.L;
                                }
                            }
                        } else if (a.A == 12) {
                            if (a.aU[2] && a.eS) {
                                a.z = -10;
                                a.A = 3;
                                a.ff = -1;
                                a.C = 0;
                            }
                        } else if (a.A == 14) {
                            if (a.aU[4]) {
                                a.fp = -1;
                            } else if (a.aU[6]) {
                                a.fp = 1;
                            } else if (a.aU[2]) {
                                a.A = 15;
                            }
                        }
lbl817:
                        // 56 sources

                        if (a.fR != a.fL || a.fS != a.fM) {
                            a.fN = a.fL - a.fR << 2;
                            a.fO = a.fM - a.fS << 2;
                            a.fP &= 15;
                            a.fS += (a.fQ += a.fO) >> 4;
                            a.fQ &= 15;
                            if ((a.fR += (a.fP += a.fN) >> 4) < 0) {
                                a.fR = 0;
                            }
                            if (a.fR > a.fT) {
                                a.fR = a.fT;
                            }
                            if (a.fS < 0) {
                                a.fS = 0;
                            }
                            if (a.fS > a.fU) {
                                a.fS = a.fU;
                            }
                        }
                        if (a.aM >= 0) {
                            a.fR += a.aK[a.aM];
                            a.fS += a.aL[a.aM];
                            if (++a.aM == 4) {
                                a.aM = -1;
                            }
                            if (a.fR < 0) {
                                a.fR = 0;
                            }
                            if (a.fR > a.fT) {
                                a.fR = a.fT;
                            }
                            if (a.fS < 0) {
                                a.fS = 0;
                            }
                            if (a.fS > a.fU) {
                                a.fS = a.fU;
                            }
                        }
                        if ((a.bz = a.fR / a.jh - 1) < 0) {
                            a.bz = 0;
                        }
                        a.bA = a.fS / a.jh;
                        a.bD = a.bz + a.bB;
                        a.bE = a.bA + a.bC;
                        if (a.bA < 0) {
                            a.bA = 0;
                        }
                        if (a.bE > a.iQ - 1) {
                            a.bE = a.iQ - 1;
                        }
                        var3_2 = 0;
                        while (var3_2 < a.it) {
                            switch (a.ix[var3_2]) {
                                case 2: {
                                    v3 = var3_2;
                                    a.iv[v3] = a.iv[v3] + a.iy[var3_2];
                                    if (a.iy[var3_2] > 0) {
                                        v4 = var3_2;
                                        a.iy[v4] = a.iy[v4] - 1;
                                    } else if (a.iy[var3_2] < 0) {
                                        v5 = var3_2;
                                        a.iy[v5] = a.iy[v5] + 1;
                                    } else {
                                        a.ix[var3_2] = 1;
                                    }
                                    if (!a.g(a.iv[var3_2] - 12, a.iw[var3_2] + 1, 2) && !a.g(a.iv[var3_2] + 12, a.iw[var3_2] + 1, 2)) {
                                        a.ix[var3_2] = 4;
                                        a.iz[var3_2] = 2;
                                    }
                                    if (!a.g(a.iv[var3_2] + 12, a.iw[var3_2] - 2, 4) && !a.g(a.iv[var3_2] - 12, a.iw[var3_2] - 2, 8)) break;
                                    a.iv[var3_2] = a.I(a.iv[var3_2]) + 12;
                                    break;
                                }
                                case 4: {
                                    v6 = var3_2;
                                    a.iw[v6] = a.iw[v6] + a.iz[var3_2];
                                    if (a.iz[var3_2] < 20) {
                                        v7 = var3_2;
                                        a.iz[v7] = a.iz[v7] + 1;
                                    }
                                    if (a.g(a.iv[var3_2] + 12, a.iw[var3_2], 2) || a.g(a.iv[var3_2] - 12, a.iw[var3_2], 2)) {
                                        a.iw[var3_2] = a.H(a.iw[var3_2]);
                                        a.iz[var3_2] = 0;
                                        a.ix[var3_2] = 1;
                                        break;
                                    }
                                    if (!a.g(a.iv[var3_2], a.iw[var3_2] - a.jh, 64)) break;
                                    a.iw[var3_2] = a.H(a.iw[var3_2]) + 1;
                                    a.g(a.iv[var3_2], a.iw[var3_2] - 13);
                                    a.g(a.iv[var3_2] + 1, a.iw[var3_2] - 10);
                                    a.iz[var3_2] = 1;
                                    a.iA[var3_2] = -1;
                                    a.iB[var3_2] = 1;
                                    a.ix[var3_2] = 11;
                                    break;
                                }
                                case 11: {
                                    if (a.w % 2 != 0) break;
                                    v8 = var3_2;
                                    a.iw[v8] = a.iw[v8] + a.iA[var3_2];
                                    v9 = var3_2;
                                    a.iz[v9] = a.iz[v9] + a.iA[var3_2];
                                    if (a.iz[var3_2] >= 1) {
                                        a.iA[var3_2] = -1;
                                    }
                                    if (a.iz[var3_2] <= -1) {
                                        a.iA[var3_2] = 1;
                                    }
                                    v10 = var3_2;
                                    a.iv[v10] = a.iv[v10] + a.iB[var3_2];
                                    if (var3_2 == a.iu) {
                                        a.fi = a.iw[a.iu] - 24;
                                        a.fh += a.iB[var3_2];
                                    }
                                    if (!a.g(a.iv[var3_2] + 12, a.iw[var3_2], 4) && !a.g(a.iv[var3_2] - 12, a.iw[var3_2], 8)) break;
                                    a.iB[var3_2] = -a.iB[var3_2];
                                }
                            }
                            ++var3_2;
                        }
                        var3_2 = 0;
                        while (var3_2 < a.il) {
                            if (var3_2 == a.im) {
                                a.iq[var3_2] = a.C > 1 ? 2 : (a.w % 8 > 3 ? 1 : 0);
                            }
                            if (a.ir[var3_2] == 1) {
                                v11 = var3_2;
                                a.io[v11] = a.io[v11] + a.is[var3_2];
                                if (a.is[var3_2] < 20) {
                                    v12 = var3_2;
                                    a.is[v12] = a.is[v12] + 1;
                                }
                                if (a.io[var3_2] >= a.iS || a.g(a.in[var3_2], a.io[var3_2], 2)) {
                                    a.is[var3_2] = 0;
                                    a.ir[var3_2] = 0;
                                    a.io[var3_2] = a.H(a.io[var3_2]);
                                }
                            }
                            ++var3_2;
                        }
                        var3_2 = 0;
                        while (var3_2 < 5) {
                            if (a.dW[var3_2] != -1) {
                                v13 = var3_2;
                                a.dW[v13] = a.dW[v13] + a.a(a.dV[var3_2]);
                                if (a.dW[var3_2] > 30) {
                                    a.dW[var3_2] = -1;
                                }
                                v14 = var3_2;
                                a.dS[v14] = a.dS[v14] + a.dU[var3_2];
                                v15 = var3_2;
                                a.dT[v15] = a.dT[v15] + a.dV[var3_2];
                            }
                            ++var3_2;
                        }
                        if (!a.a) {
                            var3_2 = 0;
                            while (var3_2 < 2) {
                                if (a.ck[var3_2] != -1) {
                                    v16 = var3_2;
                                    a.cj[v16] = a.cj[v16] - 1;
                                    if (a.w % 2 == 0) {
                                        v17 = var3_2;
                                        a.ck[v17] = a.ck[v17] + 1;
                                        if (a.ck[var3_2] > 2) {
                                            a.ck[var3_2] = -1;
                                        } else {
                                            a.cl[var3_2] = a.ck[var3_2];
                                        }
                                    }
                                }
                                ++var3_2;
                            }
                        }
                        if (!a.a) {
                            var3_2 = 0;
                            while (var3_2 < 2) {
                                if (a.co[var3_2] != -1) {
                                    v18 = var3_2;
                                    a.co[v18] = a.co[v18] + 1;
                                    if (a.co[var3_2] >= 5) {
                                        a.co[var3_2] = -1;
                                    }
                                    if (var3_2 == 0) {
                                        v19 = var3_2;
                                        a.cm[v19] = a.cm[v19] - 1;
                                    } else {
                                        v20 = var3_2;
                                        a.cm[v20] = a.cm[v20] + 1;
                                    }
                                    v21 = var3_2;
                                    a.cn[v21] = a.cn[v21] - 1;
                                }
                                ++var3_2;
                            }
                        }
                        var3_2 = 0;
                        while (var3_2 < 2) {
                            if (a.cf[var3_2] != -1) {
                                v22 = var3_2;
                                a.cf[v22] = a.cf[v22] + 1;
                                v23 = var3_2;
                                a.cd[v23] = a.cd[v23] + (a.ch[var3_2] << 2);
                                v24 = var3_2;
                                a.ce[v24] = a.ce[v24] - 1;
                                if (a.cf[var3_2] >= 6) {
                                    a.cf[var3_2] = -1;
                                } else {
                                    a.cg[var3_2] = (a.cf[var3_2] >> 1) % 3;
                                }
                            }
                            ++var3_2;
                        }
                        var3_2 = 0;
                        while (var3_2 < a.cY) {
                            if (a.db[var3_2] != 1) ** GOTO lbl1107
                            v25 = var3_2;
                            a.cZ[v25] = a.cZ[v25] + a.dd[var3_2];
                            v26 = var3_2;
                            a.da[v26] = a.da[v26] + a.de[var3_2];
                            if (a.cZ[var3_2] >= a.fR && a.cZ[var3_2] <= a.fR + a.q && a.da[var3_2] >= a.fS && a.da[var3_2] <= a.fS + a.r) ** GOTO lbl1002
                            a.db[var3_2] = -1;
                            ** GOTO lbl1114
lbl1002:
                            // 1 sources

                            if (a.dd[var3_2] > 0 && (a.i(a.cZ[var3_2], a.da[var3_2]) & 4) == 4) {
                                a.db[var3_2] = 2;
                                a.cZ[var3_2] = a.I(a.cZ[var3_2]);
                            } else if (a.dd[var3_2] < 0 && (a.i(a.cZ[var3_2], a.da[var3_2]) & 8) == 8) {
                                a.db[var3_2] = 2;
                                a.cZ[var3_2] = a.I(a.cZ[var3_2]) + a.jh;
                            }
                            v27 = var3_2;
                            a.dc[v27] = a.dc[v27] + 1;
                            if (a.dc[var3_2] > 1) {
                                a.dc[var3_2] = 0;
                            }
                            if (a.dd[var3_2] == 0) ** GOTO lbl1114
                            var4_8 = 0;
                            while (var4_8 < a.jK) {
                                if (a.jS[var4_8] == 5 || a.jS[var4_8] == 1 || a.a(a.jN[var4_8] - a.cZ[var3_2]) >= a.jQ[var4_8] || a.a(a.jO[var4_8] - (a.jP[var4_8] >> 1) - a.da[var3_2]) >= a.jP[var4_8]) ** GOTO lbl1055
                                var8_12 = var5_9 = a.df[var3_2] != false ? a.F << 1 : a.F;
                                var7_11 = var3_2;
                                var6_10 = var4_8;
                                if (a.jS[var6_10] != 2 && a.jS[var6_10] != 3 && a.jS[var6_10] != 4) ** GOTO lbl1049
                                if (a.jM[var6_10] == 9 && a.dd[var7_11] * a.kb[var6_10] < 0) {
                                    a.jS[var6_10] = 4;
                                    a.jX[var6_10] = 4;
                                    a.dd[var7_11] = -a.dd[var7_11];
                                    a.de[var7_11] = -a.de[var7_11];
                                    v28 = var7_11;
                                    a.cZ[v28] = a.cZ[v28] + a.dd[var7_11];
                                    v29 = var7_11;
                                    a.da[v29] = a.da[v29] + a.de[var7_11];
                                    v30 = true;
                                } else {
                                    v31 = var6_10;
                                    a.kc[v31] = a.kc[v31] - var8_12;
                                    if (a.kc[var6_10] <= 0) {
                                        a.kc[var6_10] = 0;
                                        a.jX[var6_10] = -5;
                                        a.jY[var6_10] = a.dd[var7_11];
                                        a.jZ[var6_10] = 0;
                                        a.jR[var6_10] = 0;
                                        a.jS[var6_10] = 1;
                                        a.L(var6_10);
                                        v30 = true;
                                    } else {
                                        a.jX[var6_10] = 0;
                                        a.kb[var6_10] = a.dd[var7_11] < 0 ? 1 : -1;
                                        a.jS[var6_10] = 6;
                                        a.ka[var6_10] = 3;
                                        a.db[var7_11] = -1;
lbl1049:
                                        // 2 sources

                                        v30 = false;
                                    }
                                }
                                if (v30) break;
                                a.f(a.jN[var4_8], a.jO[var4_8] - (a.jP[var4_8] >> 1), a.kb[var4_8]);
                                a.a("-" + var5_9, 0, a.jN[var4_8], a.jO[var4_8] - a.jP[var4_8], 0, -2);
                                break;
lbl1055:
                                // 1 sources

                                ++var4_8;
                            }
                            var4_8 = 0;
                            while (var4_8 < a.cs) {
                                if (a.cH[var4_8] != 5 && a.cH[var4_8] != 1 && a.a(a.cx[var4_8] - a.cZ[var3_2]) < 20 && a.a(a.cy[var4_8] - 15 - a.da[var3_2]) < 20) {
                                    if (a.df[var3_2]) {
                                        a.a(var4_8, a.F << 1, a.cK[var3_2]);
                                        a.f(a.cx[var4_8], a.cy[var4_8] - 15, a.cK[var4_8]);
                                        a.a("-" + (a.F << 1), 0, a.cx[var4_8], a.cy[var4_8] - 32, 0, -2);
                                        a.db[var3_2] = -1;
                                        break;
                                    }
                                    a.cH[var4_8] = 4;
                                    a.cI[var4_8] = 0;
                                    a.cJ[var4_8] = 0;
                                    a.cK[var4_8] = a.dd[var3_2] > 0 ? -1 : 1;
                                    a.dd[var3_2] = -a.dd[var3_2];
                                    break;
                                }
                                ++var4_8;
                            }
                            if (!a.df[var3_2] && a.a(a.fh - a.cZ[var3_2]) < a.fl && a.a(a.fi - a.fm - a.da[var3_2]) < a.fm) {
                                if (a.A == 5 && a.fg == 0) {
                                    a.dd[var3_2] = -a.dd[var3_2];
                                    a.de[var3_2] = -3;
                                    break;
                                }
                                a.a(a.F, -a.fp);
                                a.db[var3_2] = -1;
                                break;
                            }
                            if (a.gQ && a.a(a.gS - a.cZ[var3_2]) < a.gY >> 1 && a.a(a.gT - (a.gZ >> 1) - a.da[var3_2]) < a.gZ >> 1) {
                                a.c(a.F, -a.gW);
                                a.f(a.cZ[var3_2], a.da[var3_2], a.gW);
                                a.a("-" + a.F, 0, a.gS, a.gT - a.gZ, 0, -2);
                                a.db[var3_2] = -1;
                            }
                            if (a.hM && a.a(a.hS - a.cZ[var3_2]) < a.hR >> 1 && a.a(a.hT - (a.hO >> 1) - a.da[var3_2]) < a.hO >> 1) {
                                a.dd[var3_2] = -a.dd[var3_2];
                                a.de[var3_2] = -1;
                            }
                            if (a.hf && a.a(a.dC[0] - a.cZ[var3_2]) < 12 && a.a(a.dD[0] - 16 - a.da[var3_2]) < 32) {
                                if (a.dG[0] == 5 && a.dd[var3_2] * a.dM[0] < 0) {
                                    a.dd[var3_2] = -a.dd[var3_2];
                                } else if (a.dG[0] == 1 && a.dd[var3_2] * a.dM[0] < 0) {
                                    a.dd[var3_2] = -a.dd[var3_2];
                                    a.dI[0] = 0;
                                    a.dH[0] = 0;
                                    a.dG[0] = 5;
                                } else {
                                    a.d(a.F, -a.dM[0]);
                                    a.db[var3_2] = -1;
                                }
                            }
                            ** GOTO lbl1114
lbl1107:
                            // 1 sources

                            if (a.db[var3_2] > 1) {
                                v32 = var3_2;
                                a.db[v32] = a.db[v32] + 1;
                                if (a.db[var3_2] >= 10) {
                                    a.db[var3_2] = 1;
                                    a.de[var3_2] = 12;
                                    a.dd[var3_2] = 0;
                                }
                            }
lbl1114:
                            // 10 sources

                            ++var3_2;
                        }
                        a.O();
                        if (a.ea > 0 && ++a.ea > 30) {
                            a.ea = 0;
                        }
                        a.P();
                        if (a.ge >= 0) {
                            if (a.gf > a.fR + 10 && a.gf < a.fR + a.q - 10 && a.gg > a.fS + 10 && a.gg < a.fS + a.r - 10) {
                                a.gh = 2;
                                a.gi = a.gf;
                                a.gj = a.gg + a.gl;
                            } else if (a.a(a.fh - a.gf) > a.a(a.fi - a.gg)) {
                                if (a.fh > a.gf) {
                                    a.gh = 1;
                                    a.gi = a.fR + 20 + a.gl;
                                } else {
                                    a.gh = 0;
                                    a.gi = a.fR + a.q - 20 + a.gl;
                                }
                                a.gj = a.gg + 24;
                                if (a.gj < a.fS + 10) {
                                    a.gj = a.fS + 10;
                                }
                                if (a.gj > a.fS + a.r - 10) {
                                    a.gj = a.fS + a.r - 10;
                                }
                            } else if (a.fi > a.gg) {
                                a.gh = 3;
                                a.gi = a.fh;
                                a.gj = a.fS + 20 + a.gl;
                            } else {
                                a.gh = 2;
                                a.gi = a.fh;
                                a.gj = a.fS + a.r - 20 + a.gl;
                            }
                            if ((a.gl += a.gk) == 5 || a.gl == -5) {
                                a.gk = -a.gk;
                            }
                        }
                        a.R();
                        if (a.gp == a.v) {
                            try {
                                switch (a.gu) {
                                    case 1: {
                                        if (++a.gD > 30) {
                                            a.gD = 0;
                                        }
                                        a.gv = a.gD % 15 < 5 ? 0 : 1;
                                        if (a.gn && a.w % 6 == 0) {
                                            a.D();
                                        }
                                        if (a.w % 15 == 0) {
                                            a.E();
                                        }
                                        break;
                                    }
                                    case 2: {
                                        if (++a.gD >= 10) {
                                            a.gD = 0;
                                        }
                                        a.gv = (a.gD >> 1) + 2;
                                        if (a.gC == 1) {
                                            if (a.g(a.gq + a.gy, a.gr - a.gz, 4)) {
                                                a.gs = 0;
                                                a.gq = a.I(a.gq + a.gy) - a.gy;
                                            }
                                        } else if (a.g(a.gq - a.gy - 1, a.gr - a.gz, 8)) {
                                            a.gs = 0;
                                            a.gq = a.I(a.gq - a.gy - 1) + a.jh + a.gy;
                                        }
                                        if ((a.gq += a.gs) < 0) {
                                            a.gq = 0;
                                        }
                                        if (a.gq >= a.iR) {
                                            a.gq = a.iR - 1;
                                        }
                                        if (a.iu != -1) {
                                            if (a.a(a.gq - a.iv[a.iu]) >= 20) {
                                                a.iu = -1;
                                                if (a.g(a.gq, a.gr, 2)) {
                                                    a.gu = 1;
                                                } else {
                                                    a.gu = 4;
                                                    a.gt = 2;
                                                    a.gs = 3 * a.gC;
                                                    a.gE = 0;
                                                }
                                            }
                                        } else if ((a.i(a.gq + a.gC * a.gw, a.gr) & 2) != 2) {
                                            a.gu = 1;
                                            a.gv = 0;
                                            a.gs = 0;
                                            a.gD = 0;
                                            a.gE = 0;
                                        }
                                        a.e(a.gC, a.gq - (a.gC << 3), a.gr);
                                        if (a.gN > 0) {
                                            --a.gN;
                                            break;
                                        }
                                        a.E();
                                        break;
                                    }
                                    case 3: {
                                        if (a.gs == 0) {
                                            a.gs = a.gC << 2;
                                        }
                                        if (a.gC == 1) {
                                            if ((a.i(a.gq + a.gy, a.gr - a.gz) & 4) == 4) {
                                                a.gs = 0;
                                            }
                                        } else if ((a.i(a.gq - a.gy, a.gr - a.gz) & 8) == 8) {
                                            a.gs = 0;
                                        }
                                        if (a.fi < 0) {
                                            a.fi = 0;
                                            a.z = -1;
                                        }
                                        if ((a.gq += a.gs) < 0) {
                                            a.gq = 0;
                                        }
                                        if (a.gq >= a.iR) {
                                            a.gq = a.iR - 1;
                                        }
                                        a.gr += a.gt;
                                        a.gv = a.gD == 0 ? 7 : 8;
                                        if (a.gs > 1) {
                                            --a.gs;
                                        }
                                        if (++a.gt == 0) {
                                            a.gu = 4;
                                            a.gD = 0;
                                            a.gE = 0;
                                            a.gt = 1;
                                        }
                                        if (a.gF < 0) {
                                            ++a.gF;
                                        }
                                        break;
                                    }
                                    case 4: {
                                        if (a.gC == 1) {
                                            if ((a.i(a.gq + a.gw, a.gr) & 4) == 4) {
                                                a.gs = 0;
                                            }
                                        } else if ((a.i(a.gq - a.gw, a.gr) & 8) == 8) {
                                            a.gs = 0;
                                        }
                                        a.ij = -1;
                                        if ((a.gq += a.gs) < 0) {
                                            a.gq = 0;
                                        }
                                        if (a.gq >= a.iR) {
                                            a.gq = a.iR - 1;
                                        }
                                        a.gr += a.gt;
                                        if (a.gt < 18) {
                                            ++a.gt;
                                        }
                                        if (a.gt > 5) {
                                            a.gs = 0;
                                        }
                                        if (a.gs > 1) {
                                            --a.gs;
                                        }
                                        a.gv = 8;
                                        if (a.gr >= a.iS) {
                                            a.gr = a.iS - 1;
                                            a.gu = 3;
                                            a.gt = -20;
                                        }
                                        if (a.gt > 4 && (a.i(a.gq, a.gr) & 2) == 2) {
                                            a.gu = 1;
                                            a.gt = 0;
                                            a.gs = 0;
                                            a.gE = 0;
                                            a.gD = 0;
                                            a.gr = a.I(a.gr);
                                            a.e(-1, a.gq - -8, a.gr);
                                            a.e(1, a.gq - 8, a.gr);
                                        }
                                        if (a.g(a.gq, a.gr, 64) && a.g(a.gq, a.gr + 24, 4096) && a.gr % a.jh > 8) {
                                            a.g(a.gq, a.H(a.gr) + 8);
                                            a.gu = 3;
                                            a.gC = a.fh > a.gq ? 1 : -1;
                                            a.gs = a.gC << 2;
                                            a.gt = -11;
                                        }
                                        if (a.gt > 6) {
                                            a.gv = 6;
                                        }
                                        if (a.gF < 0) {
                                            ++a.gF;
                                        }
                                        break;
                                    }
                                    case 5: {
                                        switch (++a.gD) {
                                            case 1: 
                                            case 2: 
                                            case 3: {
                                                a.ij = -1;
                                                a.gv = 9;
                                                break;
                                            }
                                            case 4: 
                                            case 5: {
                                                a.ij = 0;
                                                a.gv = 10;
                                                break;
                                            }
                                            case 6: {
                                                a.ij = 1;
                                                a.gv = 11;
                                                a.v();
                                                break;
                                            }
                                            case 7: {
                                                a.ij = 2;
                                                a.gv = 11;
                                                break;
                                            }
                                            case 8: {
                                                a.ij = -1;
                                                a.gv = 12;
                                                break;
                                            }
                                            case 9: {
                                                a.ij = -1;
                                                a.gv = 9;
                                                a.gu = 1;
                                                a.gD = 0;
                                                a.gE = 0;
                                            }
                                        }
                                        if (a.ik == 1) {
                                            a.ij = -1;
                                        }
                                        break;
                                    }
                                    case 7: {
                                        if ((a.i(a.gq + a.gy, a.gr - a.gz) & 4) == 4) {
                                            a.gs = -a.gs;
                                        }
                                        if ((a.i(a.gq - a.gy, a.gr - a.gz) & 8) == 8) {
                                            a.gs = -a.gs;
                                        }
                                        a.gq += a.gs;
                                        a.gr += a.gt;
                                        a.gv = 7;
                                        if (a.gs > 1) {
                                            --a.gs;
                                        }
                                        if (a.gs < -1) {
                                            ++a.gs;
                                        }
                                        if (++a.gt > 0) {
                                            a.gt = 0;
                                        }
                                        if (++a.gD >= 7) {
                                            a.gu = 4;
                                            a.gF = -20;
                                            a.gE = 0;
                                            a.gD = 0;
                                            a.gt = 1;
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                            catch (Exception v33) {
                                a.gq = a.fh;
                                a.gr = a.fi;
                                a.gs = 0;
                                a.gt = 0;
                                a.gu = 4;
                            }
                        }
                        a.Y();
                        if (a.w % 2 == 0 && ((a.dr += a.dt) > a.dv || a.dr < a.du)) {
                            a.dt = -a.dt;
                            a.dr += a.dt;
                        }
                        a.al();
                        a.aa();
                        if (a.gQ) {
                            if (a.he > 0) {
                                --a.he;
                            }
                            switch (a.gV) {
                                case 1: {
                                    if (a.ha > 0) {
                                        --a.ha;
                                    } else {
                                        var3_2 = a.w % 80 - 40;
                                        var3_2 = var3_2 > 0 ? (var3_2 += 20) : (var3_2 -= 20);
                                        a.hb = a.gS + var3_2;
                                        a.gW = var3_2 > 0 ? 1 : -1;
                                        a.gU = a.gW << 2;
                                        if (a.hc < a.hd >> 1) {
                                            a.gU = a.gW << 3;
                                        }
                                        a.gV = 2;
                                        if (a.w % 5 == 0) {
                                            a.N();
                                        }
                                    }
                                    a.gX = 0;
                                    if (a.w % 10 != 6) break;
                                    a.e(-a.gW, a.gS + a.gW * 28, a.gT - 5);
                                    break;
                                }
                                case 2: {
                                    if (a.w % 8 == 0) {
                                        a.N();
                                    }
                                    if (!a.g(a.gS + (a.gW << 4), a.gT + 1, 2)) {
                                        a.gV = 1;
                                        a.ha = 0;
                                        break;
                                    }
                                    if (a.a(a.gS - a.hb) < 8) {
                                        a.gV = 1;
                                        a.ha = 20;
                                    }
                                    if (a.w % 2 == 0) {
                                        a.gS += a.gU;
                                        a.gX = 0;
                                        break;
                                    }
                                    a.gX = 1;
                                    break;
                                }
                                case 5: {
                                    if (!a.g(a.gS + (a.gW << 4), a.gT + 1, 2)) {
                                        a.gV = 1;
                                        a.ha = 0;
                                        break;
                                    }
                                    if (a.a(a.gS - a.hb) < 4) {
                                        a.gV = 1;
                                        a.ha = 35;
                                    }
                                    if (a.a(a.fi - a.gT) < 18 && a.a(a.gS + (a.gW << 4) - a.fh) < 10) {
                                        a.a(a.gR, a.gW);
                                        a.gV = 1;
                                        a.ha = 45;
                                        if (a.hc < a.hd >> 1) {
                                            a.ha = 18;
                                        }
                                    }
                                    a.gS += a.gU;
                                    a.gX = a.w % 3;
                                    break;
                                }
                                case 7: {
                                    a.gS -= a.gW;
                                    a.gX = 2;
                                    if (++a.ha != 4) break;
                                    a.gV = 1;
                                    a.ha = a.hb;
                                    if (a.ha <= 0 || a.hc >= a.hd >> 1) break;
                                    a.ha = 5;
                                    break;
                                }
                                case 6: {
                                    a.gT += a.ha;
                                    ++a.ha;
                                    a.gS += a.gU;
                                    if (a.gU > 1) {
                                        --a.gU;
                                    }
                                    if (a.gU < -1) {
                                        ++a.gU;
                                    }
                                    if (a.gT <= a.fS + a.r + a.gZ && a.gT < a.iS + a.gZ) break;
                                    a.ha = 0;
                                    a.gV = 22;
                                }
                            }
                        }
                        if (a.hM) {
                            var3_2 = a.w % 10;
                            if (a.if > 0) {
                                --a.if;
                            }
                            switch (a.ia) {
                                case 2: {
                                    a.hS = a.hP < a.hQ >> 1 ? (a.hS += a.ib << 2) : (a.hS += a.ib << 1);
                                    if (a.hS < a.ic) {
                                        a.ib = 1;
                                    }
                                    if (a.hS > a.id) {
                                        a.ib = -1;
                                    }
                                    if (a.ie > 0) {
                                        --a.ie;
                                    }
                                    if (a.ie == 0 && a.a(a.fh - (a.hS - 40)) < 30) {
                                        a.ie = 0;
                                        a.ia = 4;
                                        v34 = a.ib = a.hS < a.fh ? 1 : -1;
                                    }
                                    if (a.w % 122 == 0) {
                                        a.c(a.hS, a.hT - 45, -1, -12, a.hN);
                                        a.c(a.hS, a.hT - 50, 0, -15, a.hN);
                                        a.c(a.hS, a.hT - 45, 1, -12, a.hN);
                                        break;
                                    }
                                    if (a.w % 200 != 0) break;
                                    a.ia = 7;
                                    a.ie = 0;
                                    break;
                                }
                                case 7: {
                                    if (++a.ie == 50) {
                                        a.ia = 2;
                                        a.ie = 1;
                                        break;
                                    }
                                    if (a.ie == 30) {
                                        a.hS = a.fh + (a.fp << 6);
                                        if (a.hS > a.id) {
                                            a.hS = a.id - 30;
                                        }
                                        if (a.hS < a.ic) {
                                            a.hS = a.ic + 30;
                                        }
                                        a.ib = a.hS < a.fh ? 1 : -1;
                                        break;
                                    }
                                    if (a.ie < 10) {
                                        a.hT += 10;
                                        break;
                                    }
                                    if (a.ie <= 40) break;
                                    a.hT -= 10;
                                    break;
                                }
                                case 4: {
                                    if (a.ie == 110) {
                                        a.ia = 2;
                                        a.ie = 30;
                                    } else if (a.ie < 35) {
                                        --a.hT;
                                    } else if (a.ie >= 75) {
                                        ++a.hT;
                                        if (var3_2 == 5) {
                                            a.c(a.hS, a.hT - 45, -2, -10, a.hN);
                                        } else if (var3_2 == 7 && a.hP < a.hQ >> 1) {
                                            a.c(a.hS, a.hT - 50, 0, -11, a.hN);
                                        } else if (var3_2 == 9) {
                                            a.c(a.hS, a.hT - 45, 2, -10, a.hN);
                                        }
                                    }
                                    if (a.ie > 20 && a.ie < 50 && var3_2 == 0) {
                                        a.b(a.hS + a.ib * 20, a.hT - 29, a.ib << 3, a.hN, 2);
                                    }
                                    if (a.ie > 45 && a.ie < 50) {
                                        a.bL += a.ib;
                                    } else if (a.ie > 50 && a.ie < 55) {
                                        a.bL -= a.ib;
                                    }
                                    ++a.ie;
                                    break;
                                }
                                case 1: {
                                    a.hT += 2;
                                    ++a.bM;
                                    ++a.bN;
                                    ++a.bO;
                                    if (++a.ie <= 50) break;
                                    a.ia = 5;
                                }
                            }
                            if (var3_2 < 4) {
                                --a.hT;
                            } else if (var3_2 < 8) {
                                ++a.hT;
                            }
                            if (var3_2 > 4) {
                                --a.bM;
                                ++a.bN;
                            } else {
                                ++a.bM;
                                --a.bN;
                            }
                            if (a.ib == -1) {
                                a.hU = a.hS + a.bJ;
                                a.hY = a.hT + a.bM;
                                a.hX = a.hS + a.bK;
                                a.hZ = a.hT + a.bN;
                                a.hV = a.hS + a.bL;
                                a.hW = a.hT + a.bO;
                            } else {
                                a.hU = a.hS - a.bJ;
                                a.hY = a.hT + a.bM;
                                a.hX = a.hS - a.bK;
                                a.hZ = a.hT + a.bN;
                                a.hV = a.hS - a.bL;
                                a.hW = a.hT + a.bO;
                            }
                        }
                        a.ac();
                        if (!a.hf) ** GOTO lbl1909
                        var3_2 = a.w % 50;
                        var5_9 = a.a(a.dC[0] - a.fh);
                        switch (a.dG[0]) {
                            case 1: {
                                a.dH[0] = a.dH[0] + 1;
                                if (a.dH[0] > 30) {
                                    a.dH[0] = 0;
                                }
                                a.dJ[0] = a.dH[0] % 18 < 6 ? 0 : 1;
                                if (a.hh) {
                                    a.dG[0] = 0;
                                    a.dI[0] = 0;
                                    a.dH[0] = 0;
                                }
                                if (var5_9 < 40 && a.A == 5 && a.fp != a.dM[0]) {
                                    a.dG[0] = 3;
                                    a.dM[0] = -a.fp;
                                    if (var3_2 > 35) {
                                        a.dE[0] = a.fp * 5;
                                        a.dF[0] = -9;
                                    } else {
                                        a.dE[0] = -a.fp * 6;
                                        a.dF[0] = -9;
                                    }
                                    if (a.dC[0] < a.dP[0] + 40) {
                                        a.dE[0] = 10;
                                        a.dM[0] = -1;
                                    } else if (a.dC[0] > a.dQ[0] - 40) {
                                        a.dE[0] = -10;
                                        a.dM[0] = 1;
                                    }
                                    a.dI[0] = 0;
                                    a.dH[0] = 0;
                                    break;
                                }
                                if (var5_9 < 40 && var3_2 < 10) {
                                    a.dM[0] = a.fh > a.dC[0] ? 1 : -1;
                                    a.dG[0] = 5;
                                    a.dO[0] = 0;
                                    a.dI[0] = 0;
                                    a.dH[0] = 0;
                                    break;
                                }
                                if (var5_9 < 100 && var5_9 > 40 && var3_2 < 1) {
                                    a.dM[0] = a.fh > a.dC[0] ? 1 : -1;
                                    a.dG[0] = 24;
                                    a.dO[0] = 0;
                                    a.dI[0] = 0;
                                    a.dH[0] = 0;
                                    a.dE[0] = (a.a(a.dC[0] - a.fh) >> 3) * a.dM[0];
                                    break;
                                }
                                if (var5_9 > 100 && var5_9 < 160 && var3_2 < 30) {
                                    a.dM[0] = a.fh > a.dC[0] ? 1 : -1;
                                    a.dG[0] = 8;
                                    a.dO[0] = 0;
                                    a.dI[0] = 0;
                                    a.dH[0] = 0;
                                    break;
                                }
                                if (var5_9 < 20 || var3_2 == 42) {
                                    a.dM[0] = -a.fp;
                                    a.dI[0] = a.dC[0] + (a.dM[0] << 6);
                                    if (a.dI[0] < a.dP[0] || a.dI[0] > a.dQ[0]) {
                                        a.dM[0] = -a.dM[0];
                                        a.dI[0] = a.dC[0] + (a.dM[0] << 6);
                                    }
                                    a.dH[0] = 0;
                                    a.dG[0] = 2;
                                    a.dE[0] = a.dM[0] << 3;
                                    break;
                                }
                                if (var5_9 <= 160) break;
                                a.dM[0] = a.fh > a.dC[0] ? 1 : -1;
                                a.dI[0] = a.dC[0] + (a.dM[0] << 7);
                                if (a.dI[0] < a.dP[0]) {
                                    a.dI[0] = a.dP[0];
                                } else if (a.dI[0] > a.dQ[0]) {
                                    a.dI[0] = a.dQ[0];
                                }
                                a.dH[0] = 0;
                                a.dG[0] = 2;
                                a.dE[0] = a.dM[0] << 2;
                                break;
                            }
                            case 2: {
                                a.dH[0] = a.dH[0] + 1;
                                if (a.dH[0] >= 10) {
                                    a.dH[0] = 0;
                                }
                                a.dJ[0] = (a.dH[0] >> 1) + 2;
                                a.dC[0] = a.dC[0] + a.dE[0];
                                if (a.dA || (a.dM[0] <= 0 || a.dC[0] <= a.dI[0]) && (a.dM[0] >= 0 || a.dC[0] >= a.dI[0])) break;
                                a.dM[0] = a.fh > a.dC[0] ? 1 : -1;
                                a.dH[0] = 0;
                                a.dI[0] = 0;
                                a.dG[0] = 1;
                                break;
                            }
                            case 3: 
                            case 7: {
                                a.dC[0] = a.dC[0] + a.dE[0];
                                if (a.dC[0] < a.dP[0]) {
                                    a.dC[0] = a.dP[0];
                                }
                                if (a.dC[0] > a.dQ[0]) {
                                    a.dC[0] = a.dQ[0];
                                }
                                a.dD[0] = a.dD[0] + a.dF[0];
                                a.dJ[0] = 23;
                                if (a.dE[0] > 0) {
                                    a.dE[0] = a.dE[0] - 1;
                                }
                                a.dF[0] = a.dF[0] + 1;
                                if (a.dF[0] != 0) break;
                                a.dG[0] = 4;
                                a.dH[0] = 0;
                                a.dF[0] = 1;
                                break;
                            }
                            case 4: {
                                a.dC[0] = a.dC[0] + a.dE[0];
                                if (a.dC[0] < a.dP[0]) {
                                    a.dC[0] = a.dP[0];
                                }
                                if (a.dC[0] > a.dQ[0]) {
                                    a.dC[0] = a.dQ[0];
                                }
                                a.dD[0] = a.dD[0] + a.dF[0];
                                a.dJ[0] = 7;
                                if (a.dF[0] < 18) {
                                    a.dF[0] = a.dF[0] + 1;
                                }
                                if (a.dE[0] > 0) {
                                    a.dE[0] = a.dE[0] - 1;
                                }
                                a.dJ[0] = 7;
                                if (a.dD[0] >= a.iS) {
                                    a.dG[0] = 6;
                                    break;
                                }
                                if ((a.i(a.dC[0], a.dD[0]) & 2) != 2) break;
                                a.dG[0] = 1;
                                a.dI[0] = 0;
                                a.dH[0] = 0;
                                a.dF[0] = 0;
                                a.dE[0] = 0;
                                a.dD[0] = a.I(a.dD[0]);
                                a.e(-1, a.dC[0] - -8, a.dD[0]);
                                a.e(1, a.dC[0] - 8, a.dD[0]);
                                if (a.a(a.dC[0] - a.fh) < 40 && var3_2 < 25) {
                                    a.dM[0] = a.fh > a.dC[0] ? 1 : -1;
                                    a.dG[0] = 5;
                                    a.dO[0] = 0;
                                    a.dI[0] = 0;
                                    a.dH[0] = 0;
                                }
                                if (var5_9 >= 40) break;
                                a.dM[0] = a.fp;
                                a.dI[0] = a.dC[0] + a.dM[0] * 110;
                                if (a.dI[0] < a.dP[0] || a.dI[0] > a.dQ[0]) {
                                    a.dM[0] = -a.dM[0];
                                    a.dI[0] = a.dC[0] + (a.dM[0] << 6);
                                }
                                a.dH[0] = 0;
                                a.dG[0] = 2;
                                a.dE[0] = a.dM[0] * 10;
                                break;
                            }
                            case 8: {
                                a.dH[0] = a.dH[0] + 1;
                                switch (a.dH[0]) {
                                    case 1: 
                                    case 2: 
                                    case 3: {
                                        a.dJ[0] = 13;
                                        break;
                                    }
                                    case 4: {
                                        a.dJ[0] = 14;
                                        break;
                                    }
                                    case 5: {
                                        a.dJ[0] = 15;
                                        break;
                                    }
                                    case 6: {
                                        a.dJ[0] = 15;
                                        a.a(a.dC[0] + (a.dM[0] << 3), a.dD[0] - 12, a.dM[0] << 3, 0, false);
                                        break;
                                    }
                                    case 7: {
                                        a.dJ[0] = 16;
                                        break;
                                    }
                                    case 8: {
                                        a.dJ[0] = 16;
                                        a.dI[0] = 2;
                                        break;
                                    }
                                    case 9: {
                                        a.dH[0] = 0;
                                        a.dG[0] = 1;
                                    }
                                }
                                break;
                            }
                            case 24: {
                                a.dI[0] = a.dI[0] + 1;
                                if (a.dI[0] >= 7) ** GOTO lbl1726
                                a.dH[0] = a.dH[0] + 1;
                                if (a.dH[0] >= 10) {
                                    a.dH[0] = 0;
                                }
                                a.dJ[0] = (a.dH[0] >> 1) + 2;
                                a.dC[0] = a.dC[0] + a.dE[0];
                                if (a.a(a.dC[0] - a.fh) >= 30) break;
                                if (a.A == 5 && a.fp != a.dM[0]) {
                                    a.dG[0] = 3;
                                    a.dM[0] = -a.fp;
                                    a.dE[0] = a.fp << 1;
                                    if (a.dC[0] < a.dP[0] + 30) {
                                        a.dE[0] = 6;
                                        a.dM[0] = -1;
                                    } else if (a.dC[0] > a.dQ[0] - 30) {
                                        a.dE[0] = -6;
                                        a.dM[0] = 1;
                                    }
                                    a.dF[0] = -9;
                                } else {
                                    a.dI[0] = 6;
                                    break;
lbl1726:
                                    // 1 sources

                                    if (a.dI[0] == 7) {
                                        a.dH[0] = 0;
                                        break;
                                    }
                                    if (a.dI[0] < 15) {
                                        a.dH[0] = a.dH[0] + 1;
                                        switch (a.dH[0]) {
                                            case 1: 
                                            case 2: 
                                            case 3: {
                                                a.dL[0] = -1;
                                                a.dJ[0] = 13;
                                                break;
                                            }
                                            case 4: {
                                                a.dL[0] = 0;
                                                a.dJ[0] = 14;
                                                break;
                                            }
                                            case 5: {
                                                a.dL[0] = 1;
                                                a.dJ[0] = 15;
                                                break;
                                            }
                                            case 6: {
                                                a.dL[0] = 1;
                                                a.dJ[0] = 15;
                                                a.z(0);
                                                break;
                                            }
                                            case 7: {
                                                a.dL[0] = 2;
                                                a.dJ[0] = 16;
                                                break;
                                            }
                                            case 8: {
                                                a.dL[0] = 2;
                                                a.dJ[0] = 16;
                                                a.dI[0] = 2;
                                                a.dH[0] = 1;
                                            }
                                        }
                                        break;
                                    }
                                    a.dL[0] = -1;
                                    a.dG[0] = 1;
                                }
                                ** GOTO lbl1907
                            }
                            case 5: {
                                a.dH[0] = a.dH[0] + 1;
                                block87 : switch (a.dI[0]) {
                                    case 0: {
                                        switch (a.dH[0]) {
                                            case 1: 
                                            case 2: 
                                            case 3: {
                                                a.dL[0] = -1;
                                                a.dJ[0] = 13;
                                                break;
                                            }
                                            case 4: {
                                                a.dL[0] = 0;
                                                a.dJ[0] = 14;
                                                break;
                                            }
                                            case 5: {
                                                a.dL[0] = 1;
                                                a.dJ[0] = 15;
                                                break;
                                            }
                                            case 6: {
                                                a.dL[0] = 1;
                                                a.dJ[0] = 15;
                                                a.z(0);
                                                break;
                                            }
                                            case 7: {
                                                a.dL[0] = 2;
                                                a.dJ[0] = 16;
                                                break;
                                            }
                                            case 8: {
                                                a.dL[0] = 2;
                                                a.dJ[0] = 16;
                                                a.dI[0] = 2;
                                                a.dH[0] = 1;
                                            }
                                        }
                                        break;
                                    }
                                    case 3: {
                                        switch (a.dH[0]) {
                                            case 1: {
                                                a.dL[0] = 1;
                                                a.dJ[0] = 15;
                                                break;
                                            }
                                            case 2: {
                                                a.dL[0] = 1;
                                                a.dJ[0] = 15;
                                                break;
                                            }
                                            case 3: {
                                                a.dL[0] = 2;
                                                a.dJ[0] = 16;
                                                break;
                                            }
                                            case 4: {
                                                a.dL[0] = 2;
                                                a.dJ[0] = 16;
                                                a.dI[0] = 1;
                                                a.dH[0] = 1;
                                            }
                                        }
                                        break;
                                    }
                                    case 1: {
                                        switch (a.dH[0]) {
                                            case 1: {
                                                a.dL[0] = 1;
                                                a.dJ[0] = 15;
                                                break;
                                            }
                                            case 2: {
                                                a.dL[0] = 1;
                                                a.dJ[0] = 15;
                                                a.dI[0] = 10;
                                            }
                                        }
                                        break;
                                    }
                                    case 2: {
                                        switch (a.dH[0]) {
                                            case 1: 
                                            case 2: 
                                            case 3: {
                                                a.dL[0] = 3;
                                                a.dJ[0] = 17;
                                                break;
                                            }
                                            case 4: {
                                                a.dL[0] = 4;
                                                a.dJ[0] = 18;
                                                break;
                                            }
                                            case 5: {
                                                a.dL[0] = 5;
                                                a.dJ[0] = 19;
                                                break;
                                            }
                                            case 6: {
                                                a.dL[0] = 6;
                                                a.dJ[0] = 20;
                                                a.z(0);
                                                break;
                                            }
                                            case 7: {
                                                a.dL[0] = 6;
                                                a.dJ[0] = 20;
                                                a.dI[0] = 3;
                                                a.dH[0] = 1;
                                            }
                                        }
                                        break;
                                    }
                                    case 10: {
                                        switch (a.dH[0]) {
                                            case 1: {
                                                a.dL[0] = 2;
                                                a.dJ[0] = 16;
                                                break block87;
                                            }
                                            case 2: {
                                                a.dL[0] = 0;
                                                a.dJ[0] = 14;
                                                break block87;
                                            }
                                            case 3: 
                                            case 4: {
                                                a.dJ[0] = 13;
                                                a.dL[0] = -1;
                                                break block87;
                                            }
                                            case 5: {
                                                a.dJ[0] = 13;
                                                a.dL[0] = -1;
                                                a.dG[0] = 1;
                                                a.dI[0] = 120;
                                                a.dH[0] = 0;
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                            case 0: {
                                a.dH[0] = a.dH[0] + 1;
                                if (a.dH[0] > 30) {
                                    a.dH[0] = 0;
                                }
                                a.dJ[0] = a.dH[0] % 18 < 6 ? 0 : 1;
                                if (a.dA) {
                                    a.hg = false;
                                    a.dI[0] = a.dB;
                                    a.dM[0] = a.dB > a.dC[0] ? 1 : -1;
                                    a.dE[0] = a.dM[0] << 2;
                                    a.dE[0] = a.dM[0] > 0 ? a.dE[0] : a.dE[0];
                                    a.dG[0] = 2;
                                    break;
                                }
                                if (var5_9 >= 40 || a.A != 5) break;
                                a.hg = true;
                                a.dG[0] = 3;
                                a.dM[0] = -a.fp;
                                if (var3_2 > 35) {
                                    a.dE[0] = a.fp * 5;
                                    a.dF[0] = -9;
                                } else {
                                    a.dE[0] = -a.fp * 6;
                                    a.dF[0] = -9;
                                }
                                if (a.dC[0] < a.dP[0] + 40) {
                                    a.dE[0] = 10;
                                    a.dM[0] = -1;
                                } else if (a.dC[0] > a.dQ[0] - 40) {
                                    a.dE[0] = -10;
                                    a.dM[0] = 1;
                                }
lbl1907:
                                // 6 sources

                                a.dI[0] = 0;
                                a.dH[0] = 0;
                            }
                        }
lbl1909:
                        // 26 sources

                        a.W();
                        a.ab();
                        if (a.fK != 0) {
                            if (a.fK == 1) {
                                if (a.a(a.fi - (a.fk >> 1) - (a.fJ - 7)) < 10 && a.a(a.fh - a.fI) < 10) {
                                    a.fK = 2;
                                    a.fD = true;
                                    a.c("\u0111\u00e3 l\u1ea5y \u0111\u01b0\u1ee3c b\u00ed k\u00edp");
                                }
                            } else {
                                a.fJ -= 3;
                                if (++a.fK > 40) {
                                    a.fK = 0;
                                }
                            }
                        }
                        if (!a.cp || ++a.cq < 150) break;
                        a.cp = false;
                        break;
                    }
                    case 2: {
                        if (a.S >= a.R) break;
                        a.S += 5;
                        break;
                    }
                    case 3: {
                        if (a.aP) {
                            a.aP = false;
                            if (a.aR > a.aX - 40) {
                                if (a.aQ < a.aY - 40) {
                                    a.aV[4] = true;
                                } else if (a.aQ > a.aY + 40) {
                                    a.aV[6] = true;
                                } else {
                                    a.aV[5] = true;
                                }
                            }
                        }
                        if (++a.w == 10000) {
                            a.w = 0;
                        }
                        if (a.aV[4]) {
                            if (--a.ba < 0) {
                                a.ba = a.aF.length - 1;
                            }
                            a.aV[4] = false;
                        }
                        if (a.aV[6]) {
                            if (++a.ba == a.aF.length) {
                                a.ba = 0;
                            }
                            a.aV[6] = false;
                        }
                        if (!a.aV[5]) ** GOTO lbl2001
                        a.d();
                        if (a.ba != 0) ** GOTO lbl1958
                        a.eD = null;
                        a.B();
                        Midlet.f = true;
                        a.gP = System.currentTimeMillis();
                        a.x(99);
                        ** GOTO lbl2000
lbl1958:
                        // 1 sources

                        if (a.ba != 1) ** GOTO lbl1982
                        if (!Midlet.b()) ** GOTO lbl2001
                        a.eD = null;
                        a.B();
                        var3_3 = a.a(Midlet.b("ninjasave"));
                        a.gO = var3_3[0];
                        a.v = var3_3[1];
                        a.w = var3_3[2];
                        a.eH = var3_3[3] == 1;
                        a.dg = var3_3[4];
                        a.dz = var3_3[5] == 1;
                        a.eI = var3_3[6] == 1;
                        a.eJ = var3_3[7] == 1;
                        a.eK = var3_3[8] == 1;
                        a.gn = var3_3[9] == 1;
                        a.go = var3_3[10];
                        a.gp = var3_3[11];
                        a.im = var3_3[12];
                        a.x = var3_3[13];
                        a.eZ = var3_3[14] == 1;
                        Midlet.e = true;
                        a.gP = System.currentTimeMillis();
                        a.x(a.v);
                        ** GOTO lbl2000
lbl1982:
                        // 1 sources

                        if (a.ba == 2) {
                            a.eD = null;
                            a.B();
                            a.d = 2;
                            a.ak();
                            Midlet.d = 11;
                            a.v = 101;
                            new Thread(Midlet.c).start();
                        } else if (a.ba == 3) {
                            try {
                                Midlet.c.platformRequest("http://vechai321.xtgem.com");
                            }
                            catch (Exception v35) {}
                        } else if (a.ba == 4) {
                            Midlet.c.c();
                        }
lbl2000:
                        // 8 sources

                        a.aV[5] = false;
lbl2001:
                        // 3 sources

                        if (a.fR == 0) {
                            a.fR = 960;
                            a.fS = 48;
                            a.fN = 1;
                            a.fO = 1;
                        }
                        a.fS += a.fO;
                        if ((a.fR += a.fN) > a.fT) {
                            a.fR = a.fT;
                            a.fN = -a.fN;
                        }
                        if (a.fR < 24) {
                            a.fR = 24;
                            a.fN = -a.fN;
                        }
                        if (a.fS < 0) {
                            a.fS = 0;
                            a.fO = -a.fO;
                        }
                        if (a.fS > a.fU) {
                            a.fS = a.fU;
                            a.fO = -a.fO;
                        }
                        if ((a.bz = a.fR / a.jh - 1) < 0) {
                            a.bz = 0;
                        }
                        a.bA = a.fS / a.jh;
                        a.bD = a.bz + a.bB;
                        a.bE = a.bA + a.bC;
                        if (a.bA < 0) {
                            a.bA = 0;
                        }
                        if (a.bE > a.iQ - 1) {
                            a.bE = a.iQ - 1;
                        }
                        if (a.bd > a.bb) {
                            a.bd -= 4;
                        }
                        if (a.be < a.bc) {
                            a.be += 4;
                        }
                        if (a.bg <= a.bf) break;
                        a.bg -= 2;
                        break;
                    }
                    case 4: {
                        if (a.jH < 4) {
                            a.jI = a.jH;
                        }
                        if (a.jH > 51) {
                            a.jF = null;
                            a.jG = null;
                            a.av();
                            a.d = 10;
                        } else if (a.jH > 46) {
                            a.jI = 4 - (a.jH - 46);
                        }
                        a.aW = a.aN.getWidth();
                        a.aX = a.aN.getHeight();
                        ++a.jH;
                        break;
                    }
                    case 5: {
                        a.as();
                        break;
                    }
                    case 6: {
                        if (a.aP) {
                            a.aP = false;
                            if (a.aR > a.aX - 40) {
                                if (a.aQ < a.aY) {
                                    a.aV[12] = true;
                                } else {
                                    a.aV[13] = true;
                                }
                            }
                            a.c();
                        }
                        if (a.aS) {
                            var3_4 = a.aQ + a.lh;
                            var4_8 = a.aR + a.li;
                            if (var3_4 > a.kX) {
                                a.aU[6] = true;
                            }
                            if (var3_4 < a.kX) {
                                a.aU[4] = true;
                            }
                            if (var4_8 > a.kY) {
                                a.aU[8] = true;
                            }
                            if (var4_8 < a.kY) {
                                a.aU[2] = true;
                            }
                        }
                        if (++a.la > 10000) {
                            a.la = 0;
                        }
                        if (a.lh != a.lb || a.li != a.lc) {
                            a.ld = a.lb - a.lh << 1;
                            a.le = a.lc - a.li << 1;
                            a.lf &= 15;
                            a.li += (a.lg += a.le) >> 4;
                            a.lg &= 15;
                            if ((a.lh += (a.lf += a.ld) >> 4) < -40) {
                                a.lh = -40;
                            }
                            if (a.lh > a.lj) {
                                a.lh = a.lj;
                            }
                            if (a.li < -40) {
                                a.li = -40;
                            }
                            if (a.li > a.lk) {
                                a.li = a.lk;
                            }
                        }
                        var3_5 = false;
                        if (a.aV[5] || a.aV[12]) {
                            a.d();
                            if (a.kZ != -1) {
                                if (!a.W[a.kZ]) {
                                    a.s(1);
                                    a.d = 7;
                                } else if (a.ll == a.kZ) {
                                    a.s(3);
                                    a.d = 7;
                                } else {
                                    a.s(2);
                                    a.d = 7;
                                }
                            }
                        }
                        if (a.aV[13]) {
                            a.at();
                            a.kK = null;
                            a.ai();
                            a.d = 1;
                            a.d();
                            a.c();
                            a.aV[12] = true;
                            break;
                        }
                        if (a.aU[2]) {
                            if ((a.kY -= 4) < -20) {
                                a.kY = -20;
                            }
                            var3_5 = true;
                        }
                        if (a.aU[8]) {
                            if ((a.kY += 4) > 279) {
                                a.kY = 279;
                            }
                            var3_5 = true;
                        }
                        if (a.aU[4]) {
                            if ((a.kX -= 4) < -20) {
                                a.kX = -20;
                            }
                            var3_5 = true;
                        }
                        if (a.aU[6]) {
                            if ((a.kX += 4) > 279) {
                                a.kX = 279;
                            }
                            var3_5 = true;
                        }
                        if (!var3_5) break;
                        a.lb = a.kX - a.bF;
                        a.lc = a.kY - a.bG;
                        a.au();
                        break;
                    }
                    case 7: {
                        if (a.aP) {
                            a.aP = false;
                            if (a.aR > a.aX - 40) {
                                if (a.aQ < a.aY) {
                                    a.aV[12] = true;
                                } else {
                                    a.aV[13] = true;
                                }
                            }
                        }
                        if (++a.ls < 10) {
                            a.d();
                        }
                        if (a.aV[12]) {
                            a.d();
                            switch (a.lq) {
                                case 1: 
                                case 3: 
                                case 4: {
                                    a.d = 6;
                                    break;
                                }
                                case 5: 
                                case 6: {
                                    a.kK = null;
                                    a.d = 1;
                                    break;
                                }
                                case 2: {
                                    if (a.lr >= a.dg) {
                                        a.s(4);
                                        break;
                                    }
                                    a.dg -= a.lr;
                                    a.at();
                                    a.kK = null;
                                    a.ai();
                                    a.B();
                                    a.fs = a.fh = a.kT[a.kZ] * 24;
                                    a.ft = a.fi = a.kU[a.kZ] * 24;
                                    Midlet.f = true;
                                    a.x(a.kZ);
                                }
                            }
                        }
                        if (a.aV[13]) {
                            a.d();
                            switch (a.lq) {
                                case 1: {
                                    break;
                                }
                                case 2: {
                                    a.d = 6;
                                    break;
                                }
                                case 7: {
                                    a.d = 9;
                                }
                            }
                        }
                        a.c = true;
                        break;
                    }
                    case 8: {
                        a.aq();
                        break;
                    }
                    case 9: {
                        a.ap();
                        break;
                    }
                    case 10: {
                        if (a.aP) {
                            a.aP = false;
                            if (a.aR > a.aX - 40) {
                                if (a.aQ < a.aY) {
                                    a.aV[12] = true;
                                } else {
                                    a.aV[13] = true;
                                }
                            }
                        }
                        if (a.lo) {
                            a.ln = null;
                            a.K();
                        }
                        var3_6 = false;
                        if (a.aV[12]) {
                            if (a.lp == 0) {
                                Midlet.h = false;
                            } else {
                                a.a = false;
                            }
                            var3_6 = true;
                        } else if (a.aV[13]) {
                            if (a.lp == 0) {
                                Midlet.h = true;
                            } else {
                                a.a = true;
                            }
                            var3_6 = true;
                        }
                        if (!var3_6) break;
                        a.d();
                        a.c();
                        if (a.lp == 0) {
                            try {
                                a.ln = Image.createImage((String)"/u/m2.png");
                            }
                            catch (Exception v36) {}
                            a.lp = 1;
                            break;
                        }
                        Midlet.a = a.lO == false;
                        a.lo = true;
                        break;
                    }
                    case 11: {
                        a.Z();
                        break;
                    }
                    case 12: {
                        a.Z();
                        break;
                    }
                    case 13: {
                        var3_7 = this;
                        if (++a.lS > 10000) {
                            a.lS = 0;
                        }
                        a.aW = var3_7.getWidth();
                        a.aX = var3_7.getHeight();
                        if (a.aP && a.aR > a.aX - 20) {
                            if (a.aQ < a.aY) {
                                a.aV[12] = true;
                            } else {
                                a.aV[13] = true;
                            }
                            a.aP = false;
                        }
                        switch (a.Y) {
                            case 0: {
                                var3_7.aG();
                                break block3;
                            }
                            case 2: {
                                var3_7.aF();
                                break block3;
                            }
                            case 5: {
                                if (a.aV[5] || a.aV[12]) {
                                    Midlet.c.notifyDestroyed();
                                    break block3;
                                }
                                break block754;
                            }
                            case 6: {
                                a.aE();
                                break block3;
                            }
                            case 4: {
                                var3_7.az();
                                break block3;
                            }
                            case 7: {
                                a.ay();
                                break block3;
                            }
                            case 8: {
                                if (a.aV[12]) {
                                    Midlet.c.notifyDestroyed();
                                    break block3;
                                }
                                break block754;
                            }
                            case 14: {
                                if (a.aV[13] || a.aV[12] || a.aV[5]) {
                                    a.aV[13] = false;
                                    a.aV[5] = false;
                                    a.aV[12] = false;
                                    Midlet.c.notifyDestroyed();
                                } else {
                                    ** GOTO lbl2272
                                }
                            }
                        }
                    }
                }
            }
            this.repaint();
            this.serviceRepaints();
            while (System.currentTimeMillis() - var1_1 < (long)a.aa) {
                Thread.yield();
            }
        }
        a.a();
        Midlet.c.c();
    }

    public static void o(int n) {
        jq = 0;
        if (n == 0) {
            jq = 16;
            a.ag();
            a.a(0, "B\u1ed9 m\u00f4n Kh\u00ed", 1044, 336);
            a.a(1, "C\u00f4 Ayumi", 1044, 347);
            a.a(2, "Ph\u00f2ng ch\u1ee7 nhi\u1ec7m", 1212, 336);
            a.a(3, "Th\u1ea7y Takanashi", 1212, 347);
            a.a(4, "Ph\u00f2ng", 1380, 336);
            a.a(5, "\u0111\u00e0o t\u1ea1o", 1380, 347);
            a.a(6, "Ph\u00f2ng", 1212, 216);
            a.a(7, "Hi\u1ec7u tr\u01b0\u1edfng", 1212, 227);
            a.a(8, "B\u1ed9 m\u00f4n L\u1ef1c", 1044, 216);
            a.a(9, "Th\u1ea7y Hiroyaki", 1044, 227);
            a.a(10, "B\u1ed9 m\u00f4n Thu\u1eadt", 1380, 216);
            a.a(11, "Th\u1ea7y Honda", 1380, 227);
            a.a(12, "K\u00ed T\u00fac X\u00e1", 444, 341);
            a.a(13, "* Khu luy\u1ec7n t\u1eadp", 72, 341);
            a.a(14, "C\u1ed5ng /", 1860, 317);
            a.a(15, "B\u00e1n HP-MP", 612, 341);
            return;
        }
        if (n == 1) {
            jq = 2;
            a.ag();
            a.a(0, "Tr\u01b0\u1eddng /", 1356, 173);
            a.a(1, "* Th\u00e1c n\u01b0\u1edbc", 48, 173);
            return;
        }
        if (n == 2) {
            jq = 4;
            a.ag();
            a.a(0, "* L\u1ed1i v\u00e0o", 60, 245);
            a.a(1, "Tr\u01b0\u1eddng Hirosaki", 240, 197);
            a.a(2, "L\u1ed1i ra /", 420, 245);
            a.a(2, "Mua \u0111\u1ed3 ti\u1ec1n th\u1eadt", 552, 245);
            a.a(3, "\u0111\u01b0\u1eddng hoa \u0111\u00e0o /", 1356, 221);
            return;
        }
        if (n == 3) {
            jq = 2;
            a.ag();
            a.a(0, "* Ng\u00f4i l\u00e0ng", 48, 173);
            a.a(1, "R\u1eebng tr\u00fac /", 2808, 173);
            return;
        }
        if (n == 4) {
            jq = 2;
            a.ag();
            a.a(0, "* V\u01b0\u1eddn \u0111\u00e0o", 48, 101);
            a.a(1, "R\u1eebng t\u1ed1i", 1704, 245);
            return;
        }
        if (n == 5) {
            jq = 2;
            a.ag();
            a.a(0, "Tr\u01b0\u1eddng /", 420, 77);
            a.a(1, "R\u1eebng t\u1ed1i /", 408, 1829);
            return;
        }
        if (n == 6) {
            jq = 3;
            a.ag();
            a.a(0, "* Th\u00e1c n\u01b0\u1edbc", 72, 101);
            a.a(1, "R\u1eebng t\u1ed1i /", 504, 53);
            a.a(2, "S\u00f4ng Watamoro /", 5916, 197);
            return;
        }
        if (n == 99) {
            jq = 1;
            a.ag();
            a.a(0, "Tr\u01b0\u1eddng Hirosaki", 744, 173);
        }
    }

    private static void a(int n, String string, int n2, int n3) {
        a.jm[n] = string;
        a.jn[n] = n2;
        a.jo[n] = n3;
        int n4 = n;
        jn[n4] = jn[n4] - (Midlet.a(string) >> 1);
        a.jp[n] = Midlet.a(string);
    }

    private static void i(Graphics graphics) {
        int n = 0;
        while (n < jq) {
            if (jn[n] + jp[n] >= fR && jn[n] <= fR + q && jo[n] >= fS && jo[n] - 14 <= fS + r) {
                Midlet.a(graphics, jm[n], jn[n], jo[n], 0);
            }
            ++n;
        }
    }

    private static void ag() {
        jm = new String[jq];
        jn = new int[jq];
        jo = new int[jq];
        jp = new int[jq];
    }

    public static void p(int n) {
        ja = 0;
        if (n == 2) {
            if (iZ == null) {
                iZ = new Image[3];
                try {
                    a.iZ[0] = Image.createImage((String)"/t/h0.png");
                    a.iZ[1] = Image.createImage((String)"/t/h1.png");
                    a.iZ[2] = Image.createImage((String)"/t/h2.png");
                }
                catch (Exception exception) {}
            }
            ja = 5;
            a.ah();
            a.b(0, 0, 29, 13);
            a.b(1, 1, 34, 13);
            a.b(2, 2, 39, 13);
            a.b(3, 1, 46, 11);
            a.b(4, 2, 51, 11);
        }
        if (n == 16) {
            if (iZ == null) {
                iZ = new Image[3];
                try {
                    a.iZ[0] = Image.createImage((String)"/t/h0.png");
                    a.iZ[1] = Image.createImage((String)"/t/h1.png");
                    a.iZ[2] = Image.createImage((String)"/t/h2.png");
                }
                catch (Exception exception) {}
            }
            ja = 5;
            a.ah();
            a.b(0, 0, 5, 11);
            a.b(1, 1, 14, 12);
            a.b(2, 2, 33, 10);
            a.b(3, 1, 53, 10);
            a.b(4, 0, 59, 11);
        }
    }

    private static void b(int n, int n2, int n3, int n4) {
        a.jb[n] = n2;
        a.jc[n] = n3 * jh + 12;
        a.jd[n] = n4 * jh;
        a.je[n] = iZ[n2].getWidth() >> 1;
        a.jf[n] = iZ[n2].getHeight();
    }

    private static void ah() {
        jb = new int[ja];
        jc = new int[ja];
        jd = new int[ja];
        je = new int[ja];
        jf = new int[ja];
    }

    private static void ai() {
        try {
            if (iW == null) {
                iW = Image.createImage((String)"/t/t.png");
            }
            if (iX == null) {
                iX = Image.createImage((String)"/t/wtf.png");
            }
            if (iY == null) {
                iY = Image.createImage((String)"/t/wts.png");
            }
            if (jg == null && !a) {
                jg = Image.createImage((String)"/t/tb.png");
            }
            System.gc();
            return;
        }
        catch (Exception exception) {
            Midlet.c.c();
            return;
        }
    }

    public static void q(int n) {
        iV = new boolean[200];
        if (n == 101) {
            n = 99;
        }
        if (n == 0) {
            iP = 80;
            iQ = 19;
            jr = 1;
            jv = 59 * jh;
            jw = 9 * jh;
            js = 2;
            jx = 3;
            jy = 12 * jh;
            jt = -1;
            ju = -1;
        } else if (n == 1) {
            iP = 60;
            iQ = 14;
            jr = 5;
            jv = 19 * jh;
            jw = 5 * jh;
            js = 0;
            jx = 3;
            jy = 16 * jh;
            jt = -1;
            ju = -1;
        } else if (n == 2) {
            iP = 60;
            iQ = 16;
            jr = 0;
            jv = 79 * jh;
            jw = 15 * jh;
            js = 3;
            jx = 3;
            jy = 9 * jh;
            jt = -1;
            ju = -1;
        } else if (n == 3) {
            iP = 120;
            iQ = 12;
            jr = 2;
            jv = 59 * jh;
            jw = 11 * jh;
            js = 4;
            jx = 3;
            jy = 144;
            jt = -1;
            ju = -1;
        } else if (n == 4) {
            iP = 80;
            iQ = 15;
            jr = 3;
            jv = 119 * jh;
            jw = 9 * jh;
            js = -1;
            jt = -1;
            ju = 6;
            jB = 26 * jh;
            jC = 10;
        } else if (n == 5) {
            iP = 20;
            iQ = 80;
            jr = -1;
            js = 1;
            jx = 3;
            jy = 9 * jh;
            jD = 40 * jh;
            jt = 6;
            jz = 3;
            jA = 5 * jh;
            ju = -1;
        } else if (n == 6) {
            iP = 250;
            iQ = 12;
            jr = 5;
            jv = 19 * jh;
            jw = 78 * jh;
            js = 7;
            jx = 1 * jh;
            jy = 7 * jh;
            jt = -1;
            ju = -1;
        } else if (n == 7) {
            iP = 80;
            iQ = 14;
            jr = 6;
            jv = 249 * jh;
            jw = 10 * jh;
            js = 97;
            jx = 1 * jh;
            jy = 3 * jh;
            jt = -1;
            ju = -1;
        } else if (n == 97) {
            iP = 25;
            iQ = 13;
            jr = 7;
            jv = 79 * jh;
            jw = 11 * jh;
            js = 8;
            jx = 1 * jh;
            jy = 12 * jh;
            jt = -1;
            ju = -1;
        } else if (n == 8) {
            iP = 80;
            iQ = 14;
            jr = 97;
            jv = 23 * jh;
            jw = 7 * jh;
            js = 9;
            jx = 1 * jh;
            jy = 11 * jh;
            jt = -1;
            ju = -1;
        } else if (n == 9) {
            iP = 80;
            iQ = 14;
            jr = 8;
            jv = 79 * jh;
            jw = 9 * jh;
            js = -1;
            jx = 1 * jh;
            jy = 11 * jh;
            jt = -1;
            ju = 10;
            jB = 2 * jh;
            jC = 0 * jh;
        } else if (n == 10) {
            iP = 40;
            iQ = 40;
            jr = -1;
            js = 11;
            jx = 24;
            jy = 264;
            jt = -1;
            ju = -1;
        } else if (n == 11) {
            iP = 90;
            iQ = 14;
            jr = 10;
            jv = 936;
            jw = 720;
            js = 96;
            jx = 24;
            jy = 192;
            jt = -1;
            ju = -1;
        } else if (n == 96) {
            iP = 25;
            iQ = 14;
            jr = 11;
            jv = 2112;
            jw = 288;
            js = 12;
            jx = 24;
            jy = 192;
            jt = -1;
            ju = -1;
        } else if (n == 12) {
            iP = 40;
            iQ = 25;
            jr = 96;
            jv = 552;
            jw = 264;
            js = 13;
            jx = 24;
            jy = 216;
            jt = -1;
            ju = -1;
        } else if (n == 13) {
            iP = 80;
            iQ = 14;
            jr = 12;
            jv = 936;
            jw = 456;
            js = 14;
            jx = 24;
            jy = 168;
            jt = -1;
            ju = -1;
        } else if (n == 14) {
            iP = 60;
            iQ = 16;
            jr = 13;
            jv = 1896;
            jw = 264;
            js = 15;
            jx = 24;
            jy = 120;
            jt = -1;
            ju = -1;
        } else if (n == 15) {
            iP = 40;
            iQ = 30;
            jr = 14;
            jv = 1416;
            jw = 144;
            js = 16;
            jx = 24;
            jy = 264;
            jt = -1;
            ju = -1;
        } else if (n == 16) {
            iP = 70;
            iQ = 15;
            jr = 15;
            jv = 936;
            jw = 168;
            js = 17;
            jx = 24;
            jy = 432;
            jt = -1;
            ju = -1;
        } else if (n == 17) {
            iP = 50;
            iQ = 20;
            jr = 16;
            jv = 1656;
            jw = 144;
            js = 18;
            jx = 24;
            jy = 288;
            jt = -1;
            ju = -1;
        } else if (n == 18) {
            iP = 70;
            iQ = 15;
            jr = 17;
            jv = 1176;
            jw = 384;
            js = 95;
            jx = 24;
            jy = 192;
            jt = -1;
            ju = -1;
        } else if (n == 95) {
            iP = 25;
            iQ = 14;
            jr = 18;
            jv = 1632;
            jw = 312;
            js = -1;
            jt = -1;
            ju = -1;
        } else if (n == 94) {
            iP = 24;
            iQ = 14;
            jr = -1;
            js = -1;
            jt = -1;
            ju = -1;
        } else if (n == 99) {
            iP = 70;
            iQ = 15;
            jr = -1;
            js = 4;
            jx = 2 * jh;
            jy = 5 * jh;
            jt = -1;
            ju = -1;
        }
        iR = iP * jh;
        iS = iQ * jh;
        try {
            InputStream inputStream = "".getClass().getResourceAsStream("/m/" + n);
            iT = new byte[iP * iQ];
            iU = new int[iP * iQ];
            int n2 = 0;
            while (n2 < iP * iQ) {
                a.iT[n2] = (byte)inputStream.read();
                if (iT[n2] != 0) {
                    a.iV[a.iT[n2] - 1] = true;
                }
                if (iT[n2] == 1 || iT[n2] == 2 || iT[n2] == 3 || iT[n2] == 4 || iT[n2] == 5 || iT[n2] == 6 || iT[n2] == 7 || iT[n2] == 36 || iT[n2] == 37 || iT[n2] == 54 || iT[n2] == 91 || iT[n2] == 92 || iT[n2] == 93 || iT[n2] == 94 || iT[n2] == 73 || iT[n2] == 74 || iT[n2] == 97 || iT[n2] == 98 || iT[n2] == 120) {
                    int n3 = n2;
                    iU[n3] = iU[n3] | 2;
                }
                if (iT[n2] == 2 || iT[n2] == 3 || iT[n2] == 4 || iT[n2] == 5 || iT[n2] == 6 || iT[n2] == 20 || iT[n2] == 21 || iT[n2] == 22 || iT[n2] == 23 || iT[n2] == 36 || iT[n2] == 37 || iT[n2] == 38 || iT[n2] == 39) {
                    int n4 = n2;
                    iU[n4] = iU[n4] | 0x2000;
                }
                if (iT[n2] == 8 || iT[n2] == 9 || iT[n2] == 10 || iT[n2] == 12 || iT[n2] == 13 || iT[n2] == 14 || iT[n2] == 30) {
                    int n5 = n2;
                    iU[n5] = iU[n5] | 0x10;
                }
                if (iT[n2] == 17) {
                    int n6 = n2;
                    iU[n6] = iU[n6] | 0x20;
                }
                if (iT[n2] == 18) {
                    int n7 = n2;
                    iU[n7] = iU[n7] | 0x80;
                }
                if (iT[n2] == 37 || iT[n2] == 38) {
                    int n8 = n2;
                    iU[n8] = iU[n8] | 4;
                }
                if (iT[n2] == 36 || iT[n2] == 39) {
                    int n9 = n2;
                    iU[n9] = iU[n9] | 8;
                }
                if (iT[n2] == 19) {
                    int n10 = n2;
                    iU[n10] = iU[n10] | 0x40;
                    if ((iU[n2 - iP] & 0x2000) == 8192) {
                        int n11 = n2;
                        iU[n11] = iU[n11] | 0x2000;
                    }
                }
                if (iT[n2] == 35) {
                    int n12 = n2;
                    iU[n12] = iU[n12] | 0x1000;
                }
                if (iT[n2] == 32) {
                    int n13 = n2;
                    iU[n13] = iU[n13] | 0x100;
                }
                if (iT[n2] == 7) {
                    int n14 = n2;
                    iU[n14] = iU[n14] | 0x800;
                }
                if (iT[n2] == 33 || iT[n2] == 34) {
                    int n15 = n2;
                    iU[n15] = iU[n15] | 0x200;
                }
                ++n2;
            }
        }
        catch (Exception exception) {
            Midlet.c.c();
        }
        a.ai();
    }

    private static void j(Graphics graphics) {
        int n = bz;
        while (n < bD) {
            int n2 = bA;
            while (n2 < bE) {
                int n3 = iT[n2 * iP + n] - 1;
                if (n3 != -1) {
                    graphics.drawRegion(iW, 0, n3 * jh, 24, 24, 0, n * jh, n2 * jh, 0);
                }
                if ((a.h(n, n2) & 0x20) == 32) {
                    graphics.drawRegion(iX, 0, 24 * (w % 4), 24, 24, 0, n * jh, n2 * jh, 0);
                } else if ((a.h(n, n2) & 0x40) == 64) {
                    if ((a.h(n, n2 - 1) & 0x20) == 32) {
                        graphics.drawRegion(iX, 0, 24 * (w % 4), 24, 24, 0, n * jh, n2 * jh, 0);
                    } else if ((a.h(n, n2 - 1) & 0x2000) == 8192) {
                        graphics.drawRegion(iW, 0, 504, 24, 24, 0, n * jh, n2 * jh, 0);
                    }
                    graphics.drawRegion(iY, 0, (w % 8 >> 2) * 24, 24, 24, 0, n * jh, n2 * jh, 0);
                }
                if ((a.h(n, n2) & 0x1000) == 4096) {
                    if ((a.h(n, n2 - 1) & 0x20) == 32) {
                        graphics.drawRegion(iX, 0, 24 * (w % 4), 24, 24, 0, n * jh, n2 * jh, 0);
                    } else if ((a.h(n, n2 - 1) & 0x2000) == 8192) {
                        graphics.drawRegion(iW, 0, 504, 24, 24, 0, n * jh, n2 * jh, 0);
                    }
                    graphics.drawRegion(iW, 0, (iT[n2 * iP + n] - 1) * jh, 24, 24, 0, n * jh, n2 * jh, 0);
                }
                ++n2;
            }
            ++n;
        }
    }

    private static void k(Graphics graphics) {
        int n = bz;
        while (n < bD) {
            int n2 = bA;
            while (n2 < bE) {
                int n3 = iT[n2 * iP + n] - 1;
                if ((a.h(n, n2) & 0x10) == 16) {
                    ji = n * jh - fR;
                    jj = ji - bF;
                    jl = (jh - 2) * jj / jh;
                    jk = jl + bF;
                    graphics.drawRegion(iW, 0, n3 * jh, 24, 24, 0, jk + fR, n2 * jh, 0);
                } else if ((a.h(n, n2) & 0x100) != 256 && (a.h(n, n2) & 0x200) != 512) {
                    if ((a.h(n, n2) & 0x20) == 32) {
                        graphics.drawRegion(iX, 0, 24 * (w % 4), 24, 24, 0, n * jh, n2 * jh, 0);
                    } else if ((a.h(n, n2) & 0x40) == 64 || (a.h(n, n2) & 0x1000) == 4096) {
                        if ((a.h(n, n2 - 1) & 0x20) == 32) {
                            graphics.drawRegion(iX, 0, 24 * (w % 4), 24, 24, 0, n * jh, n2 * jh, 0);
                        } else if ((a.h(n, n2 - 1) & 0x2000) == 8192) {
                            graphics.drawRegion(iW, 0, 504, 24, 24, 0, n * jh, n2 * jh, 0);
                        }
                    } else if ((a.h(n, n2) & 0x400) == 1024) {
                        graphics.drawRegion(iW, 0, n3 * jh, 24, 24, 0, n * jh, n2 * jh + 1, 0);
                    } else if (n3 != -1) {
                        graphics.drawRegion(iW, 0, n3 * jh, 24, 24, 0, n * jh, n2 * jh, 0);
                    }
                }
                ++n2;
            }
            ++n;
        }
    }

    private static void l(Graphics graphics) {
        if (a) {
            return;
        }
        int n = bz;
        while (n < bD) {
            int n2 = bA;
            while (n2 < bE) {
                if ((a.h(n, n2) & 0x40) == 64) {
                    graphics.drawRegion(iY, 0, (w % 8 >> 2) * 24, 24, 24, 0, n * jh, n2 * jh, 0);
                } else if ((a.h(n, n2) & 0x100) == 256) {
                    ji = n * jh - fR;
                    jj = ji - bF;
                    jl = (jh - 1) * jj / jh;
                    jk = jl + bF;
                    graphics.drawImage(jg, jk + fR - 2 + (n + n2) % 5, n2 * jh - 3 + n2 % 5, 0);
                }
                if ((a.h(n, n2) & 0x200) == 512) {
                    graphics.drawRegion(iW, 0, (iT[n2 * iP + n] - 1) * jh, 24, 24, 0, n * jh, n2 * jh + 2, 0);
                }
                if ((a.h(n, n2) & 0x1000) == 4096) {
                    graphics.drawRegion(iW, 0, (iT[n2 * iP + n] - 1) * jh, 24, 24, 0, n * jh, n2 * jh, 0);
                }
                ++n2;
            }
            ++n;
        }
    }

    private static int h(int n, int n2) {
        return iU[n2 * iP + n];
    }

    private static int i(int n, int n2) {
        return iU[n2 / jh * iP + n / jh];
    }

    private static boolean g(int n, int n2, int n3) {
        return (iU[n2 / jh * iP + n / jh] & n3) == n3;
    }

    private static void h(int n, int n2, int n3) {
        int n4 = n2 / jh * iP + n / jh;
        iU[n4] = iU[n4] | 0x400;
    }

    private static void i(int n, int n2, int n3) {
        a.iU[n2 * a.iP + 24] = 0;
    }

    private static void j(int n, int n2, int n3) {
        int n4 = n2 / jh * iP + n / jh;
        iU[n4] = iU[n4] & 0xFFFFFBFF;
    }

    private static int H(int n) {
        return n / jh * jh;
    }

    private static int I(int n) {
        return n / jh * jh;
    }

    private static void aj() {
        try {
            jF = Image.createImage((String)"/u/lg.png");
        }
        catch (Exception exception) {}
        jH = 0;
    }

    private static void ak() {
        S = 0;
        R = 0;
        jE = 0;
    }

    private static void J(int n) {
        if (kf[n] > 30) {
            int n2 = n;
            int n3 = kb[n2] > 0 ? jN[n2] : jN[n2] - kg[n2];
            int n4 = kb[n2] >= 0 ? jN[n2] + kg[n2] : jN[n2];
            int n5 = jO[n2] - kh[n2];
            n2 = jO[n2] + kh[n2];
            if (A == 6 ? false : (fz ? false : (A == 14 ? false : (fh < n3 ? false : (fh > n4 ? false : (fi < n5 ? false : fi <= n2)))))) {
                a.kf[n] = 0;
                a.jS[n] = 4;
                a.jX[n] = 0;
            }
        }
    }

    private static void K(int n) {
        if (kf[n] > 30) {
            int n2 = n;
            int n3 = kb[n2] > 0 ? jN[n2] : jN[n2] - kg[n2];
            int n4 = kb[n2] >= 0 ? jN[n2] + kg[n2] : jN[n2];
            int n5 = jO[n2] - kh[n2];
            n2 = jO[n2] + kh[n2];
            if (gu == 14 ? false : (gq < n3 ? false : (gq > n4 ? false : (gr < n5 ? false : gr <= n2)))) {
                a.kf[n] = 0;
                a.jS[n] = 4;
                a.jX[n] = 0;
                a.kl[n] = true;
            }
        }
    }

    private static boolean k(int n, int n2, int n3) {
        if (jS[n] == 2 || jS[n] == 3 || jS[n] == 4 || jS[n] == 9) {
            int n4 = n;
            kc[n4] = kc[n4] - n2;
            if (kc[n] <= 0) {
                a.kc[n] = 0;
                a.jX[n] = -5;
                a.jY[n] = -(n3 << 3);
                a.jZ[n] = 0;
                a.jR[n] = 0;
                a.jS[n] = 1;
                a.L(n);
                return true;
            }
            if (jS[n] == 9) {
                int n5 = n;
                jX[n5] = jX[n5] - 7;
                a.kb[n] = n3;
            } else {
                a.jX[n] = 0;
                a.kb[n] = n3;
                a.jS[n] = 6;
                a.ka[n] = 3;
            }
        }
        return false;
    }

    private static void L(int n) {
        a.u(aq[jM[n]]);
        int n2 = w % 20;
        if (n2 > 15) {
            a.a(jN[n], jO[n], -(kb[n] << 3), -5, ke[n] << 2, (w + 1) % 2);
        } else if (n2 == 15) {
            a.a(jN[n], jO[n], -(kb[n] << 2), -4, (kj[n] << 3) + w % 10);
        } else {
            n2 = ((jM[n] << 2) / 10 + 1) * 10;
            a.a(jN[n], jO[n], -(kb[n] << 1), -6, n2);
            a.a(jN[n], jO[n], -(kb[n] << 4), -3, n2);
        }
        if (O != -1 && lH[O] == jM[n]) {
            a.c(String.valueOf(am[lH[O]]) + ": " + ++fF + "#" + lI[O]);
        }
        if (jM[n] == fB - 100 && w % 10 < fC) {
            a.e(jN[n], jO[n]);
            a.f(jN[n], jO[n] - 28);
        }
    }

    public static final void r(int n) {
        int n2;
        int n3;
        jK = 0;
        switch (n) {
            case 0: {
                kp = new int[]{7};
                a.am();
                a.km[0] = new int[]{68, 71, 74, 64};
                a.kn[0] = new int[]{15, 15, 15, 17};
                a.an();
                a.M(2);
                a.q(0, 2);
                break;
            }
            case 1: {
                int[] nArray = new int[3];
                nArray[0] = 1;
                nArray[1] = 7;
                kp = nArray;
                a.am();
                a.km[0] = new int[]{44, 40, 42, 39, 41, 39, 34};
                a.kn[0] = new int[]{10, 11, 9, 9, 7, 5, 9};
                a.km[1] = new int[]{48, 51};
                a.kn[1] = new int[]{9, 9};
                a.km[2] = new int[]{8, 3, 24, 25, 26};
                a.kn[2] = new int[]{9, 9, 10, 8, 6};
                a.an();
                a.M(7);
                a.u(0, 3);
                a.q(1, 1);
                a.s(2, 3);
                break;
            }
            case 2: {
                kp = new int[]{1, 6};
                a.am();
                a.km[0] = new int[]{32, 41, 44, 48};
                a.kn[0] = new int[]{13, 13, 11, 11};
                a.km[1] = new int[]{41, 47, 57, 36};
                a.kn[1] = new int[]{9, 7, 7, 8};
                a.an();
                a.M(4);
                a.u(0, 2);
                a.o(1, 2);
                break;
            }
            case 3: {
                int[] nArray = new int[3];
                nArray[0] = 1;
                nArray[1] = 6;
                kp = nArray;
                a.am();
                a.km[0] = new int[]{6, 10, 17, 26, 42, 83, 96};
                a.kn[0] = new int[]{9, 10, 10, 10, 10, 10, 11};
                a.km[1] = new int[]{10, 19, 24, 32, 41, 49, 59, 68, 80, 87, 110};
                a.kn[1] = new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 7};
                a.km[2] = new int[]{17, 24, 28, 35, 42, 49, 59, 69, 78, 86, 97, 107, 113};
                a.kn[2] = new int[]{10, 10, 8, 8, 10, 9, 9, 9, 10, 10, 8, 8, 9};
                a.an();
                a.M(20);
                a.u(0, 4);
                a.o(1, 8);
                a.s(2, 8);
                break;
            }
            case 4: {
                int[] nArray = new int[5];
                nArray[0] = 6;
                nArray[2] = 4;
                nArray[3] = 9;
                nArray[4] = 1;
                kp = nArray;
                a.am();
                a.km[0] = new int[]{29, 26, 37, 43, 58, 67, 11, 74, 57};
                a.kn[0] = new int[]{11, 10, 11, 10, 8, 3, 8, 7, 7};
                a.km[1] = new int[]{21, 39, 37, 45, 25, 62, 58};
                a.kn[1] = new int[]{12, 12, 12, 8, 12, 9, 5};
                a.km[2] = new int[]{29, 33, 68, 42, 57};
                a.kn[2] = new int[]{13, 12, 7, 12, 9};
                a.km[3] = new int[]{55, 59, 53};
                a.kn[3] = new int[]{5, 5, 5};
                a.km[4] = new int[]{19, 11};
                a.kn[4] = new int[]{12, 11};
                a.an();
                a.M(20);
                a.o(0, 6);
                a.s(1, 6);
                a.r(2, 4);
                a.l(3, 2);
                a.u(4, 2);
                break;
            }
            case 5: {
                int[] nArray = new int[3];
                nArray[1] = 3;
                nArray[2] = 4;
                kp = nArray;
                a.am();
                a.km[0] = new int[]{7, 14, 7, 14, 7, 6, 15, 9};
                a.kn[0] = new int[]{9, 13, 18, 28, 29, 35, 42, 70};
                a.km[1] = new int[]{6, 4, 14, 4, 16, 7, 15, 11};
                a.kn[1] = new int[]{14, 26, 33, 44, 47, 62, 65, 78};
                a.km[2] = new int[]{14, 14, 7, 12, 8, 14, 8};
                a.kn[2] = new int[]{23, 39, 56, 62, 66, 70, 78};
                a.an();
                a.M(20);
                a.s(0, 7);
                n3 = 7;
                n = 1;
                n2 = 0;
                while (n2 < jK) {
                    if (jM[n2] == -1) break;
                    ++n2;
                }
                if (n2 != jK) {
                    n = n2;
                    while (n < n2 + 7) {
                        a.kk[n] = 1;
                        a.jM[n] = 3;
                        a.kg[n] = 30;
                        a.kh[n] = 10;
                        a.jP[n] = 27;
                        a.jQ[n] = 15;
                        a.kb[n] = 1;
                        a.jS[n] = 5;
                        a.jX[n] = n << 3;
                        a.kc[n] = a.kd[n] = an[jM[n]];
                        a.ke[n] = ao[jM[n]];
                        a.kj[n] = ap[jM[n]];
                        a.kf[n] = 0;
                        ++n;
                    }
                }
                a.r(2, 6);
                break;
            }
            case 6: {
                kp = new int[]{9, 2, 5};
                a.am();
                a.km[0] = new int[]{42, 47, 72, 82, 85, 93, 104, 130, 139, 151, 165, 182, 189, 200, 207, 214, 225, 225, 237};
                a.kn[0] = new int[]{10, 8, 10, 8, 6, 6, 6, 9, 9, 7, 4, 7, 9, 10, 10, 8, 4, 10, 8};
                a.km[1] = new int[]{39, 40, 41, 72, 73, 74, 132, 106, 107, 130, 131, 142, 149, 157, 168, 182, 183, 194, 214, 215, 216, 236, 39, 40, 56, 57};
                a.kn[1] = new int[]{5, 5, 6, 6, 4, 3, 2, 3, 4, 5, 5, 5, 2, 1, 3, 4, 5, 6, 6, 5, 4, 5, 6, 6, 6, 6};
                a.km[2] = new int[]{45, 64, 83, 101, 119, 147, 173, 220};
                a.kn[2] = new int[]{6, 8, 4, 3, 6, 7, 5, 4};
                a.an();
                a.M(26);
                a.l(0, 5);
                a.j(1, 18);
                n3 = 3;
                n = 2;
                n2 = 0;
                while (n2 < jK) {
                    if (jM[n2] == -1) break;
                    ++n2;
                }
                if (n2 == jK) break;
                n = n2;
                while (n < n2 + 3) {
                    a.kk[n] = 2;
                    a.jM[n] = 5;
                    a.kg[n] = 7;
                    a.kh[n] = 10;
                    a.jP[n] = 16;
                    a.jQ[n] = 15;
                    a.kb[n] = 0;
                    a.jS[n] = 5;
                    a.jX[n] = n << 3;
                    a.kc[n] = a.kd[n] = an[jM[n]];
                    a.ke[n] = ao[jM[n]];
                    a.kj[n] = ap[jM[n]];
                    a.kf[n] = 100;
                    ++n;
                }
                break;
            }
            case 7: {
                kp = new int[]{9, 8, 10};
                a.am();
                a.km[0] = new int[]{38, 39, 47, 52, 56, 61, 66, 73, 71};
                a.kn[0] = new int[]{5, 5, 3, 8, 4, 4, 4, 4, 8};
                a.km[1] = new int[]{16, 23, 26, 29, 32, 35, 38, 41, 44, 47, 56, 59, 61, 64, 66};
                a.kn[1] = new int[]{11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
                a.km[2] = new int[]{36, 42, 45, 487, 56, 60, 64};
                a.kn[2] = new int[]{11, 11, 11, 11, 11, 11, 11};
                a.an();
                a.M(23);
                a.l(0, 7);
                a.m(1, 10);
                a.n(2, 6);
                break;
            }
            case 8: {
                kp = new int[]{11, 8, 10, 2};
                a.am();
                a.km[0] = new int[]{12, 29, 6, 12, 24, 18, 37, 50, 65, 72, 47};
                a.kn[0] = new int[]{10, 8, 12, 12, 11, 12, 10, 8, 8, 7, 7};
                a.km[1] = new int[]{29, 45, 48, 70, 73};
                a.kn[1] = new int[]{12, 12, 12, 12, 12};
                a.km[2] = new int[]{46, 47, 71, 72};
                a.kn[2] = new int[]{13, 13, 13, 13};
                a.km[3] = new int[]{25, 7, 69, 69, 50, 51};
                a.kn[3] = new int[]{25, 8, 6, 7, 5, 5};
                a.an();
                a.M(22);
                n3 = 10;
                n = 0;
                n2 = 0;
                while (n2 < jK) {
                    if (jM[n2] == -1) break;
                    ++n2;
                }
                if (n2 != jK) {
                    n = n2;
                    while (n < n2 + 10) {
                        a.kk[n] = 0;
                        a.jM[n] = 11;
                        a.kg[n] = 32;
                        a.kh[n] = 10;
                        a.jP[n] = 27;
                        a.jQ[n] = 15;
                        a.kb[n] = 1;
                        a.jS[n] = 5;
                        a.jX[n] = jL - n;
                        a.kc[n] = a.kd[n] = an[jM[n]];
                        a.ke[n] = ao[jM[n]];
                        a.kj[n] = ap[jM[n]];
                        a.kf[n] = 0;
                        ++n;
                    }
                }
                a.m(1, 4);
                a.n(2, 3);
                a.j(3, 5);
                break;
            }
            case 9: {
                kp = new int[]{13};
                a.am();
                a.km[0] = new int[]{10, 37, 23, 65};
                a.kn[0] = new int[]{7, 6, 6, 4};
                a.an();
                a.M(3);
                a.k(0, 3);
                break;
            }
            case 10: {
                kp = new int[]{13};
                a.am();
                a.km[0] = new int[]{31, 12, 25, 12};
                a.kn[0] = new int[]{19, 23, 23, 6};
                a.an();
                a.M(3);
                a.k(0, 3);
                break;
            }
            case 11: {
                kp = new int[]{14, 8};
                a.am();
                a.km[0] = new int[]{15, 16, 17, 31, 32, 33, 68, 69, 72};
                a.kn[0] = new int[]{12, 12, 12, 12, 8, 8, 8, 8, 8};
                a.km[1] = new int[]{47, 48, 50, 49};
                a.kn[1] = new int[]{12, 12, 12, 12};
                a.an();
                a.M(10);
                n3 = 7;
                n = 0;
                n2 = 0;
                while (n2 < jK) {
                    if (jM[n2] == -1) break;
                    ++n2;
                }
                if (n2 != jK) {
                    n = n2;
                    while (n < n2 + 7) {
                        a.kk[n] = 0;
                        a.jM[n] = 14;
                        a.kg[n] = 7;
                        a.kh[n] = 10;
                        a.jP[n] = 16;
                        a.jQ[n] = 15;
                        a.kb[n] = 1;
                        a.jS[n] = 5;
                        a.jX[n] = n << 3;
                        a.kc[n] = a.kd[n] = an[jM[n]];
                        a.ke[n] = ao[jM[n]];
                        a.kj[n] = ap[jM[n]];
                        a.kf[n] = 500;
                        ++n;
                    }
                }
                a.m(1, 3);
                break;
            }
            case 12: {
                kp = new int[]{15, 16, 10};
                a.am();
                a.km[0] = new int[]{11, 18, 20, 9, 9, 17, 18, 22, 31};
                a.kn[0] = new int[]{7, 5, 10, 14, 17, 15, 20, 19, 21};
                a.km[1] = new int[]{5, 10, 5, 10, 14, 21, 16, 37};
                a.kn[1] = new int[]{6, 12, 15, 19, 16, 17, 9, 17};
                a.km[2] = new int[]{25, 26, 27, 34};
                a.kn[2] = new int[]{23, 23, 23, 23};
                a.an();
                a.M(19);
                a.t(0, 7);
                a.p(1, 8);
                a.n(2, 4);
                break;
            }
            case 13: {
                kp = new int[]{18, 15, 13};
                a.am();
                a.km[0] = new int[]{6, 12, 30, 34, 34, 43, 54, 53, 70, 76};
                a.kn[0] = new int[]{9, 11, 11, 10, 5, 5, 5, 11, 11, 11};
                a.km[1] = new int[]{11, 16, 18, 29, 50, 38, 48, 58, 62, 74};
                a.kn[1] = new int[]{11, 9, 7, 5, 11, 5, 5, 5, 5, 11};
                a.km[2] = new int[]{22, 41, 42, 61};
                a.kn[2] = new int[]{9, 8, 2, 8};
                a.an();
                a.M(18);
                a.v(0, 8);
                a.t(1, 6);
                a.k(2, 4);
                break;
            }
            case 14: {
                kp = new int[]{12, 17, 16};
                a.am();
                a.km[0] = new int[]{7, 12, 16, 25, 28, 45, 50};
                a.kn[0] = new int[]{4, 7, 11, 14, 8, 14, 9};
                a.km[1] = new int[]{14, 18, 29, 28, 29, 53, 47, 51};
                a.kn[1] = new int[]{7, 11, 12, 8, 4, 14, 9, 4};
                a.km[2] = new int[]{4, 41, 26, 26, 34, 41, 49, 56};
                a.kn[2] = new int[]{4, 9, 11, 2, 2, 5, 7, 2};
                a.an();
                a.M(19);
                a.y(0, 6);
                a.x(1, 7);
                a.p(2, 6);
                break;
            }
            case 15: {
                kp = new int[]{19, 18, 17};
                a.am();
                a.km[0] = new int[]{22, 7, 19, 32};
                a.kn[0] = new int[]{28, 25, 28, 14};
                a.km[1] = new int[]{3, 13, 31, 35, 24};
                a.kn[1] = new int[]{6, 25, 25, 21, 27};
                a.km[2] = new int[]{35, 29, 24, 17, 13, 2};
                a.kn[2] = new int[]{8, 11, 9, 7, 7, 17};
                a.an();
                a.M(15);
                a.w(0, 4);
                a.v(1, 5);
                a.x(2, 6);
                break;
            }
            case 16: {
                kp = new int[]{19, 13};
                a.am();
                a.km[0] = new int[]{15, 33, 53};
                a.kn[0] = new int[]{12, 10, 10};
                a.km[1] = new int[]{20, 39, 59};
                a.kn[1] = new int[]{10, 8, 8};
                a.an();
                a.M(6);
                a.w(0, 3);
                a.k(1, 3);
                break;
            }
            case 17: {
                kp = new int[]{12};
                a.am();
                a.km[0] = new int[]{8, 14, 39, 45};
                a.kn[0] = new int[]{12, 12, 9, 16};
                a.an();
                a.M(4);
                a.y(0, 4);
                break;
            }
            case 95: {
                kp = new int[]{13};
                a.am();
                a.km[0] = new int[]{7, 10, 14, 18};
                a.kn[0] = new int[]{8, 8, 8, 8};
                a.an();
                a.M(4);
                a.k(0, 4);
            }
        }
        if (jK > 0) {
            jJ = new Image[kp.length][2][4];
            n = 0;
            while (n < kp.length) {
                n3 = 0;
                while (n3 < 2) {
                    n2 = 0;
                    while (n2 < 4) {
                        try {
                            a.jJ[n][n3][n2] = Image.createImage((String)("/mob/m" + kp[n] + n3 + n2 + ".png"));
                        }
                        catch (Exception exception) {}
                        if (jJ[n][n3][n2] == null && n3 == 1) {
                            a.jJ[n][1][n2] = jJ[n][0][n2];
                        }
                        ++n2;
                    }
                    if (jJ[n][n3][2] == null) {
                        a.jJ[n][n3][2] = jJ[n][n3][1];
                    }
                    ++n3;
                }
                ++n;
            }
        }
    }

    private static void al() {
        int n = 0;
        while (n < jK) {
            if (kf[n] <= 30) {
                int n2 = n;
                kf[n2] = kf[n2] + 1;
            }
            if (jR[n] > 0) {
                int n3 = n;
                jR[n3] = jR[n3] - 1;
            }
            block0 : switch (jS[n]) {
                case 5: {
                    int n4;
                    int n5;
                    int n6 = n5 = n;
                    jX[n6] = jX[n6] + 1;
                    if (jX[n5] >= jL) {
                        a.jX[n5] = 0;
                        n4 = w % km[kk[n5]].length;
                        if (ko[kk[n5]][n4]) {
                            a.jX[n5] = jL;
                            break;
                        }
                        a.jN[n5] = km[kk[n5]][n4];
                        a.jO[n5] = kn[kk[n5]][n4];
                        a.ko[a.kk[n5]][n4] = true;
                        a.ki[n5] = n4;
                        a.jS[n5] = 2;
                        a.kc[n5] = kd[n5];
                        switch (jM[n5]) {
                            case 0: {
                                a.jT[n5] = jN[n5] - 20;
                                a.jU[n5] = jN[n5] + 20;
                                break;
                            }
                            case 1: {
                                a.jT[n5] = jN[n5] - 10;
                                a.jU[n5] = jN[n5] + 10;
                                break;
                            }
                            case 2: {
                                a.jT[n5] = jN[n5] + 12;
                                a.jU[n5] = jO[n5] + 12;
                                break;
                            }
                            case 3: {
                                a.jT[n5] = jN[n5] - 20;
                                a.jU[n5] = jN[n5] + 20;
                                a.jY[n5] = -4;
                            }
                            case 4: {
                                a.jT[n5] = jN[n5] - 20;
                                a.jU[n5] = jN[n5] + 20;
                                a.jY[n5] = -3;
                                break;
                            }
                            case 5: {
                                a.jT[n5] = jO[n5] - 10;
                                a.jU[n5] = jO[n5] + 10;
                                a.jX[n5] = 1;
                                break;
                            }
                            case 6: {
                                a.jT[n5] = jN[n5];
                                a.jU[n5] = jO[n5];
                                break;
                            }
                            case 7: {
                                a.jS[n5] = 3;
                                break;
                            }
                            case 8: 
                            case 9: 
                            case 11: 
                            case 12: {
                                a.jT[n5] = jN[n5] - 20;
                                a.jU[n5] = jN[n5] + 20;
                                break;
                            }
                            case 10: {
                                a.jT[n5] = jN[n5] - 30;
                                a.jU[n5] = jN[n5] + 30;
                                break;
                            }
                            case 13: {
                                a.jT[n5] = jN[n5] + 12;
                                a.jU[n5] = jO[n5] + 12;
                                break;
                            }
                            case 14: {
                                a.jT[n5] = jN[n5] - 40;
                                a.jU[n5] = jN[n5] + 40;
                                break;
                            }
                            case 15: {
                                a.jT[n5] = jN[n5] - 20;
                                a.jU[n5] = jN[n5] + 20;
                                a.jY[n5] = -4;
                                break;
                            }
                            case 16: {
                                a.jT[n5] = jN[n5];
                                a.jU[n5] = jO[n5];
                                break;
                            }
                            case 17: {
                                a.jT[n5] = jN[n5] - 30;
                                a.jU[n5] = jN[n5] + 30;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    int n4;
                    int n5 = n;
                    switch (jM[n5]) {
                        case 0: {
                            n4 = n5;
                            if (w % 4 == 0) {
                                int n7 = n4;
                                ka[n7] = ka[n7] + 1;
                                if (ka[n4] > 1) {
                                    a.ka[n4] = 0;
                                }
                            }
                            int n8 = n4;
                            jN[n8] = jN[n8] + kb[n4];
                            if (jN[n4] > jU[n4]) {
                                a.kb[n4] = -1;
                            } else if (jN[n4] < jT[n4]) {
                                a.kb[n4] = 1;
                            }
                            a.J(n4);
                            if (w % 9 != 0) break block0;
                            a.K(n4);
                            break;
                        }
                        case 1: {
                            a.U(n5);
                            break;
                        }
                        case 2: {
                            int n9 = n4 = n5;
                            jN[n9] = jN[n9] + jV[n4];
                            int n10 = n4;
                            jO[n10] = jO[n10] + jW[n4];
                            if (jN[n4] - jT[n4] > 40) {
                                a.jV[n4] = -(w % 3 + 1);
                            }
                            if (jN[n4] - jT[n4] < -40) {
                                a.jV[n4] = w % 3 + 1;
                            }
                            if (jO[n4] - jU[n4] > 40) {
                                a.jW[n4] = -(w % 3 + 1);
                            }
                            if (jO[n4] - jU[n4] < -40) {
                                a.jW[n4] = w % 3 + 1;
                            }
                            if (w % 15 == 0) {
                                a.jV[n4] = w % 4 - 1;
                                a.jW[n4] = (w >> 1) % 4 - 1;
                            }
                            if (w % 2 == 0) {
                                int n11 = n4;
                                ka[n11] = ka[n11] + 1;
                                if (ka[n4] > 1) {
                                    a.ka[n4] = 0;
                                }
                                if (ka[n4] == 0) {
                                    int n12 = n4;
                                    jO[n12] = jO[n12] - 3;
                                } else {
                                    int n13 = n4;
                                    jO[n13] = jO[n13] + 2;
                                }
                            }
                            a.J(n4);
                            break;
                        }
                        case 3: {
                            int n14 = n4 = n5;
                            jN[n14] = jN[n14] + jX[n4];
                            if (jX[n4] > 1) {
                                int n15 = n4;
                                jX[n15] = jX[n15] - 1;
                            } else if (jX[n4] < -1) {
                                int n16 = n4;
                                jX[n16] = jX[n16] + 1;
                            }
                            int n17 = n4;
                            jO[n17] = jO[n17] + jY[n4];
                            int n18 = n4;
                            jY[n18] = jY[n18] + 1;
                            if (jY[n4] > 4) {
                                a.jY[n4] = -4;
                                a.jX[n4] = kb[n4] * 3;
                            }
                            if (a.a(jY[n4]) == 3) {
                                if (jN[n4] > jU[n4]) {
                                    a.kb[n4] = -1;
                                } else if (jN[n4] < jT[n4]) {
                                    a.kb[n4] = 1;
                                }
                                a.ka[n4] = 0;
                            } else {
                                a.ka[n4] = 1;
                            }
                            a.J(n4);
                            if (w % 9 != 0) break block0;
                            a.K(n4);
                            break;
                        }
                        case 4: {
                            int n19 = n4 = n5;
                            jN[n19] = jN[n19] + jZ[n4];
                            if (jZ[n4] > 1) {
                                int n20 = n4;
                                jZ[n20] = jZ[n20] - 1;
                            } else if (jZ[n4] < -1) {
                                int n21 = n4;
                                jZ[n21] = jZ[n21] + 1;
                            }
                            int n22 = n4;
                            jO[n22] = jO[n22] + jY[n4];
                            int n23 = n4;
                            jY[n23] = jY[n23] + 1;
                            if (jY[n4] > 3) {
                                a.jY[n4] = -3;
                                a.jZ[n4] = kb[n4] << 2;
                                a.jX[n4] = 0;
                                a.jO[n4] = kn[kk[n4]][ki[n4]];
                                a.jS[n4] = 3;
                            }
                            if (jY[n4] == -2) {
                                if (jN[n4] > jU[n4]) {
                                    a.kb[n4] = -1;
                                } else if (jN[n4] < jT[n4]) {
                                    a.kb[n4] = 1;
                                }
                                a.jZ[n4] = kb[n4] * 6;
                                a.ka[n4] = 0;
                            } else {
                                a.ka[n4] = 1;
                            }
                            a.J(n4);
                            if (w % 9 != 0) break block0;
                            a.K(n4);
                            break;
                        }
                        case 5: {
                            n4 = n5;
                            if (w % 4 == 0) {
                                int n24 = n4;
                                ka[n24] = ka[n24] + 1;
                                if (ka[n4] > 1) {
                                    a.ka[n4] = 0;
                                }
                                int n25 = n4;
                                jO[n25] = jO[n25] + jX[n4];
                            }
                            if (jO[n4] > jU[n4]) {
                                a.jX[n4] = -1;
                            } else if (jO[n4] < jT[n4]) {
                                a.jX[n4] = 1;
                            }
                            a.J(n4);
                            break;
                        }
                        case 6: {
                            a.P(n5);
                            break;
                        }
                        case 8: {
                            int n26 = n4 = n5;
                            ka[n26] = ka[n26] + 1;
                            if (ka[n4] > 1) {
                                a.ka[n4] = 0;
                            }
                            int n27 = n4;
                            jN[n27] = jN[n27] + w % 5 * kb[n4];
                            if (jN[n4] > jU[n4]) {
                                a.kb[n4] = -1;
                            } else if (jN[n4] < jT[n4]) {
                                a.kb[n4] = 1;
                            }
                            a.J(n4);
                            break;
                        }
                        case 9: {
                            a.Q(n5);
                            break;
                        }
                        case 10: {
                            int n28 = n4 = n5;
                            ka[n28] = ka[n28] + 1;
                            if (ka[n4] > 1) {
                                a.ka[n4] = 0;
                            }
                            int n29 = n4;
                            jN[n29] = jN[n29] + 5 * kb[n4];
                            if (jN[n4] > jU[n4]) {
                                a.kb[n4] = -1;
                            } else if (jN[n4] < jT[n4]) {
                                a.kb[n4] = 1;
                            }
                            a.J(n4);
                            break;
                        }
                        case 11: {
                            n4 = n5;
                            if (w % 4 == 0) {
                                int n30 = n4;
                                ka[n30] = ka[n30] + 1;
                                a.jX[n4] = 0;
                                if (ka[n4] > 1) {
                                    a.ka[n4] = 0;
                                    a.jX[n4] = 1;
                                }
                            }
                            int n31 = n4;
                            jN[n31] = jN[n31] + jX[n4] * kb[n4];
                            if (jN[n4] > jU[n4]) {
                                a.kb[n4] = -1;
                            } else if (jN[n4] < jT[n4]) {
                                a.kb[n4] = 1;
                            }
                            a.J(n4);
                            if (w % 9 != 0) break block0;
                            a.K(n4);
                            break;
                        }
                        case 13: {
                            int n32 = n4 = n5;
                            jN[n32] = jN[n32] + jV[n4];
                            if (jN[n4] - jT[n4] > 100) {
                                a.kb[n4] = -1;
                                a.jV[n4] = kb[n4] * (w % 4 + 1);
                                int n33 = n4;
                                jN[n33] = jN[n33] + (kb[n4] << 2);
                            } else if (jN[n4] - jT[n4] < -100) {
                                a.kb[n4] = 1;
                                a.jV[n4] = kb[n4] * (w % 4 + 1);
                                int n34 = n4;
                                jN[n34] = jN[n34] + (kb[n4] << 2);
                            }
                            a.jW[n4] = jO[n4] - jU[n4] > 20 ? -(w % 4 + 1) : (jO[n4] - jU[n4] < -20 ? w % 4 + 1 : 0);
                            int n35 = n4;
                            jO[n35] = jO[n35] + jW[n4];
                            if (w % 2 == 0) {
                                int n36 = n4;
                                ka[n36] = ka[n36] + 1;
                                if (ka[n4] > 1) {
                                    a.ka[n4] = 0;
                                    int n37 = n4;
                                    jO[n37] = jO[n37] - 12;
                                } else {
                                    int n38 = n4;
                                    jO[n38] = jO[n38] + 12;
                                }
                            }
                            a.J(n4);
                            break;
                        }
                        case 14: {
                            int n39 = n4 = n5;
                            ka[n39] = ka[n39] + 1;
                            if (ka[n4] > 1) {
                                a.ka[n4] = 0;
                            }
                            if (w % 2 == 0) {
                                int n40 = n4;
                                jN[n40] = jN[n40] + (kb[n4] << 2);
                            } else {
                                int n41 = n4;
                                jN[n41] = jN[n41] + (kb[n4] << 1);
                            }
                            if (jN[n4] > jU[n4]) {
                                a.kb[n4] = -1;
                            } else if (jN[n4] < jT[n4]) {
                                a.kb[n4] = 1;
                            }
                            a.J(n4);
                            if (w % 5 != 0) break block0;
                            a.K(n4);
                            break;
                        }
                        case 15: {
                            int n42 = n4 = n5;
                            jN[n42] = jN[n42] + jX[n4];
                            if (jX[n4] > 1) {
                                int n43 = n4;
                                jX[n43] = jX[n43] - 1;
                            } else if (jX[n4] < -1) {
                                int n44 = n4;
                                jX[n44] = jX[n44] + 1;
                            }
                            int n45 = n4;
                            jO[n45] = jO[n45] + jY[n4];
                            int n46 = n4;
                            jY[n46] = jY[n46] + 1;
                            if (jY[n4] > 4) {
                                a.jY[n4] = -4;
                                a.jX[n4] = kb[n4] * 3;
                            }
                            if (a.a(jY[n4]) == 3) {
                                if (jN[n4] > jU[n4]) {
                                    a.kb[n4] = -1;
                                } else if (jN[n4] < jT[n4]) {
                                    a.kb[n4] = 1;
                                }
                                a.ka[n4] = 0;
                            } else {
                                a.ka[n4] = 1;
                            }
                            a.J(n4);
                            if (w % 9 != 0) break block0;
                            a.K(n4);
                            break;
                        }
                        case 16: {
                            a.P(n5);
                            break;
                        }
                        case 18: 
                        case 19: {
                            n4 = n5;
                            if (w % 4 == 0) {
                                int n47 = n4;
                                ka[n47] = ka[n47] + 1;
                                if (ka[n4] > 1) {
                                    a.ka[n4] = 0;
                                }
                            }
                            a.kb[n4] = jN[n4] > fh ? -1 : 1;
                            a.J(n4);
                            if (w % 9 != 0) break block0;
                            a.K(n4);
                            break;
                        }
                        case 17: {
                            a.U(n5);
                            break;
                        }
                        case 12: {
                            a.Q(n5);
                        }
                    }
                    break;
                }
                case 6: {
                    int n4;
                    int n5 = n;
                    a.jR[n5] = 100;
                    a.ka[n5] = 2;
                    switch (jM[n5]) {
                        case 0: {
                            n4 = n5;
                            if (kf[n4] < 30) {
                                int n48 = n4;
                                jN[n48] = jN[n48] - (kb[n4] << 1);
                            } else {
                                int n49 = n4;
                                jN[n49] = jN[n49] - kb[n4];
                            }
                            int n50 = n4;
                            jX[n50] = jX[n50] + 1;
                            if (jX[n4] == 1) {
                                int n51 = n4;
                                jO[n51] = jO[n51] - 2;
                                break;
                            }
                            if (jX[n4] != 5) break block0;
                            int n52 = n4;
                            jO[n52] = jO[n52] + 2;
                            a.jS[n4] = 2;
                            break;
                        }
                        case 1: {
                            a.S(n5);
                            break;
                        }
                        case 2: {
                            int n53 = n4 = n5;
                            jX[n53] = jX[n53] + 1;
                            int n54 = n4;
                            jN[n54] = jN[n54] - (jV[n4] >> 3);
                            int n55 = n4;
                            jO[n55] = jO[n55] - (jW[n4] >> 3);
                            if (jV[n4] == 0) {
                                a.jV[n4] = 2;
                            }
                            if (jW[n4] == 0) {
                                a.jW[n4] = 2;
                            }
                            if (jX[n4] != 5) break block0;
                            a.jS[n4] = 2;
                            a.jX[n4] = 0;
                            a.jW[n4] = 0;
                            a.jV[n4] = 0;
                            a.kf[n4] = 10;
                            break;
                        }
                        case 3: {
                            int n56 = n4 = n5;
                            jN[n56] = jN[n56] - kb[n4];
                            a.ka[n4] = 2;
                            int n57 = n4;
                            jX[n57] = jX[n57] + 1;
                            if (jX[n4] != 7) break block0;
                            a.jO[n4] = kn[kk[n4]][ki[n4]];
                            a.jS[n4] = 2;
                            a.jY[n4] = -4;
                            a.jX[n4] = kb[n4] * 3;
                            break;
                        }
                        case 4: {
                            int n58 = n4 = n5;
                            jN[n58] = jN[n58] - kb[n4];
                            a.ka[n4] = 2;
                            int n59 = n4;
                            jX[n59] = jX[n59] + 1;
                            if (jX[n4] != 7) break block0;
                            a.jO[n4] = kn[kk[n4]][ki[n4]];
                            a.jS[n4] = 2;
                            a.jY[n4] = -4;
                            a.jX[n4] = kb[n4] * 3;
                            break;
                        }
                        case 5: {
                            int n60 = n4 = n5;
                            jX[n60] = jX[n60] + 1;
                            int n61 = n4;
                            jO[n61] = jO[n61] - 1;
                            if (jX[n4] == 3) {
                                a.jS[n4] = 2;
                                a.jX[n4] = 1;
                            }
                            a.ka[n4] = 2;
                            break;
                        }
                        case 6: {
                            a.O(n5);
                            break;
                        }
                        case 7: {
                            int n62 = n4 = n5;
                            jX[n62] = jX[n62] + 1;
                            a.kb[n4] = -fp;
                            a.ka[n4] = jX[n4] % 4 > 1 ? 0 : 2;
                            if (jX[n4] != 8) break block0;
                            a.jS[n4] = 3;
                            a.kb[n4] = 0;
                            a.jW[n4] = 0;
                            a.jV[n4] = 0;
                            a.kf[n4] = 0;
                            break;
                        }
                        case 8: {
                            n4 = n5;
                            a.jS[n4] = 4;
                            break;
                        }
                        case 9: {
                            int n63 = n4 = n5;
                            jN[n63] = jN[n63] - kb[n4];
                            a.ka[n4] = 2;
                            int n64 = n4;
                            jX[n64] = jX[n64] + 1;
                            if (jX[n4] != 6) break block0;
                            a.jO[n4] = kn[kk[n4]][ki[n4]];
                            a.jS[n4] = 4;
                            a.jX[n4] = 4;
                            int n65 = n4;
                            jN[n65] = jN[n65] + (kb[n4] << 3);
                            break;
                        }
                        case 10: {
                            n4 = n5;
                            a.jS[n4] = 4;
                            break;
                        }
                        case 11: {
                            int n66 = n4 = n5;
                            jN[n66] = jN[n66] - kb[n4];
                            a.ka[n4] = 2;
                            int n67 = n4;
                            jX[n67] = jX[n67] + 1;
                            if (jX[n4] != 6) break block0;
                            a.jO[n4] = kn[kk[n4]][ki[n4]];
                            a.jS[n4] = 4;
                            a.jX[n4] = 0;
                            int n68 = n4;
                            jN[n68] = jN[n68] + (kb[n4] << 3);
                            break;
                        }
                        case 13: {
                            int n69 = n4 = n5;
                            jX[n69] = jX[n69] + 1;
                            int n70 = n4;
                            jN[n70] = jN[n70] - (jV[n4] >> 3);
                            int n71 = n4;
                            jO[n71] = jO[n71] - (jW[n4] >> 3);
                            if (jV[n4] == 0) {
                                a.jV[n4] = 2;
                            }
                            if (jW[n4] == 0) {
                                a.jW[n4] = 2;
                            }
                            if (jX[n4] != 5) break block0;
                            a.jS[n4] = 2;
                            a.jX[n4] = 0;
                            a.kb[n4] = jN[n4] > jT[n4] ? -1 : 1;
                            a.jV[n4] = kb[n4] << 3;
                            a.kf[n4] = 0;
                            break;
                        }
                        case 14: {
                            a.T(n5);
                            break;
                        }
                        case 15: {
                            int n72 = n4 = n5;
                            jN[n72] = jN[n72] - kb[n4];
                            a.ka[n4] = 2;
                            int n73 = n4;
                            jX[n73] = jX[n73] + 1;
                            if (jX[n4] != 3) break block0;
                            a.jO[n4] = kn[kk[n4]][ki[n4]];
                            a.jS[n4] = 2;
                            a.jY[n4] = -4;
                            a.jX[n4] = kb[n4] * 3;
                            break;
                        }
                        case 16: {
                            a.O(n5);
                            break;
                        }
                        case 18: {
                            int n74 = n4 = n5;
                            jX[n74] = jX[n74] + 1;
                            int n75 = n4;
                            jN[n75] = jN[n75] - kb[n4];
                            if (jX[n4] != 4) break block0;
                            int n76 = n4;
                            jN[n76] = jN[n76] + (kb[n4] << 2);
                            a.jS[n4] = 2;
                            break;
                        }
                        case 19: {
                            int n77 = n4 = n5;
                            jX[n77] = jX[n77] + 1;
                            int n78 = n4;
                            jN[n78] = jN[n78] - kb[n4];
                            if (jX[n4] != 4) break block0;
                            int n79 = n4;
                            jN[n79] = jN[n79] + (kb[n4] << 2);
                            a.jX[n4] = 0;
                            a.jS[n4] = 4;
                            break;
                        }
                        case 17: {
                            a.T(n5);
                            break;
                        }
                        case 12: {
                            a.S(n5);
                        }
                    }
                    break;
                }
                case 4: {
                    int n4;
                    int n5 = n;
                    if (A == 14 || fz) {
                        a.ka[n5] = 0;
                        a.jS[n5] = 2;
                        a.jX[n5] = 0;
                        a.jY[n5] = 0;
                    }
                    switch (jM[n5]) {
                        case 0: {
                            int n80 = n4 = n5;
                            jX[n80] = jX[n80] + 1;
                            if (jX[n4] == 2) {
                                if (kl[n4]) {
                                    a.kl[n4] = false;
                                    a.b(ke[n4], kb[n4]);
                                } else {
                                    a.a(ke[n4], kb[n4]);
                                }
                            }
                            if ((jX[n4] >> 2) % 2 == 0) {
                                int n81 = n4;
                                jN[n81] = jN[n81] + kb[n4];
                                a.ka[n4] = 3;
                            } else {
                                a.ka[n4] = 0;
                                int n82 = n4;
                                jN[n82] = jN[n82] - kb[n4];
                            }
                            if (jX[n4] != 12) break block0;
                            a.jS[n4] = 3;
                            a.jX[n4] = 0;
                            break;
                        }
                        case 1: {
                            n4 = n5;
                            a.a(ke[n4], -fp);
                            a.jS[n4] = 3;
                            a.jX[n4] = 0;
                            a.ka[n4] = 2;
                            break;
                        }
                        case 2: {
                            n4 = n5;
                            if (jN[n4] < fh) {
                                a.jV[n4] = 3 + w % 4;
                            } else if (jN[n4] > fh) {
                                a.jV[n4] = -(3 + w % 4);
                            }
                            if (jO[n4] < fi) {
                                a.jW[n4] = w % 3;
                            } else if (jO[n4] > fi) {
                                a.jW[n4] = -w % 3;
                            }
                            int n83 = n4;
                            jN[n83] = jN[n83] + jV[n4];
                            int n84 = n4;
                            jO[n84] = jO[n84] + jW[n4];
                            if (w % 2 == 0) {
                                int n85 = n4;
                                ka[n85] = ka[n85] + 1;
                                if (ka[n4] > 1) {
                                    a.ka[n4] = 0;
                                }
                            }
                            if (a.a(jN[n4] - fh) < 5 && a.a(jO[n4] - fi) < 5) {
                                a.kf[n4] = 0;
                                a.a(ke[n4], -fp);
                                a.jX[n4] = 0;
                                a.jS[n4] = 2;
                            }
                            if (a.a(jN[n4] - fh) <= 160 && a.a(jO[n4] - fi) <= 160) break block0;
                            a.jS[n4] = 2;
                            a.jX[n4] = 0;
                            a.jW[n4] = 0;
                            a.jV[n4] = 0;
                            a.kf[n4] = 0;
                            break;
                        }
                        case 3: {
                            int n86 = n4 = n5;
                            jX[n86] = jX[n86] + 1;
                            if (jX[n4] == 3) {
                                if (kl[n4]) {
                                    a.kl[n4] = false;
                                    a.b(ke[n4], kb[n4]);
                                } else {
                                    a.a(ke[n4], kb[n4]);
                                }
                            }
                            if ((jX[n4] >> 2) % 2 == 0) {
                                int n87 = n4;
                                jN[n87] = jN[n87] + kb[n4];
                                a.ka[n4] = 0;
                            } else {
                                a.ka[n4] = 3;
                                int n88 = n4;
                                jN[n88] = jN[n88] - kb[n4];
                            }
                            if (jX[n4] != 12) break block0;
                            a.jO[n4] = kn[kk[n4]][ki[n4]];
                            a.jS[n4] = 3;
                            a.jY[n4] = -4;
                            a.jX[n4] = 0;
                            break;
                        }
                        case 4: {
                            int n89 = n4 = n5;
                            jX[n89] = jX[n89] + 1;
                            if (jX[n4] == 3) {
                                if (kl[n4]) {
                                    a.kl[n4] = false;
                                    a.b(ke[n4], kb[n4]);
                                } else {
                                    a.a(ke[n4], kb[n4]);
                                }
                            }
                            if ((jX[n4] >> 2) % 2 == 0) {
                                int n90 = n4;
                                jN[n90] = jN[n90] + kb[n4];
                                a.ka[n4] = 0;
                            } else {
                                a.ka[n4] = 1;
                                int n91 = n4;
                                jN[n91] = jN[n91] - kb[n4];
                            }
                            if (jX[n4] != 12) break block0;
                            a.jO[n4] = kn[kk[n4]][ki[n4]];
                            a.jS[n4] = 3;
                            a.jY[n4] = -4;
                            a.jX[n4] = 0;
                            break;
                        }
                        case 5: {
                            n4 = n5;
                            a.a(ke[n4], -fp);
                            a.jS[n4] = 2;
                            a.jX[n4] = 1;
                            break;
                        }
                        case 6: {
                            a.N(n5);
                            break;
                        }
                        case 8: {
                            int n92 = n4 = n5;
                            jX[n92] = jX[n92] + 1;
                            if (jX[n4] <= 10) {
                                if (jO[n4] > fi) {
                                    int n93 = n4;
                                    jO[n93] = jO[n93] - 4;
                                }
                                if (jN[n4] < fh) {
                                    int n94 = n4;
                                    jN[n94] = jN[n94] + 2;
                                } else if (jN[n4] > fh) {
                                    int n95 = n4;
                                    jN[n95] = jN[n95] - 2;
                                }
                                if (a.a(jN[n4] - fh) < 16 && a.a(jO[n4] - (fi - 16)) < 20) {
                                    a.a(ke[n4], -fp);
                                    a.jX[n4] = 10;
                                }
                            }
                            if (jX[n4] == 10) {
                                a.jY[n4] = 1;
                            }
                            if (jX[n4] > 10) {
                                int n96 = n4;
                                jO[n96] = jO[n96] + jY[n4];
                                int n97 = n4;
                                jY[n97] = jY[n97] + 1;
                                if (jO[n4] > kn[kk[n4]][ki[n4]]) {
                                    a.jO[n4] = kn[kk[n4]][ki[n4]];
                                    a.jY[n4] = 0;
                                    a.jX[n4] = 0;
                                    a.jS[n4] = 2;
                                }
                            }
                            a.ka[n4] = jX[n4] % 2 == 0 ? 0 : 1;
                            break;
                        }
                        case 9: {
                            int n98 = n4 = n5;
                            jX[n98] = jX[n98] + 1;
                            if (jX[n4] < 6) {
                                int n99 = n4;
                                jN[n99] = jN[n99] + (kb[n4] << 2);
                            } else if (jX[n4] == 6) {
                                if (a.a(jN[n4] + (kb[n4] << 3) - fh) < 13 + fl) {
                                    a.a(ke[n4], kb[n4]);
                                }
                            } else if (jX[n4] < 12) {
                                int n100 = n4;
                                jN[n100] = jN[n100] - (kb[n4] << 1);
                            } else if (jX[n4] == 12) {
                                a.jO[n4] = kn[kk[n4]][ki[n4]];
                                a.jS[n4] = 3;
                                a.jY[n4] = -4;
                                a.jX[n4] = 0;
                            }
                            a.ka[n4] = 1;
                            if (jX[n4] <= 3 || jX[n4] >= 9 || jX[n4] % 2 != 0) break block0;
                            a.ka[n4] = 3;
                            break;
                        }
                        case 10: {
                            int n101 = n4 = n5;
                            jX[n101] = jX[n101] + 1;
                            a.ka[n4] = 3;
                            if (jX[n4] <= 15) {
                                int n102 = n4;
                                jO[n102] = jO[n102] - 7;
                                if (jN[n4] < fh) {
                                    int n103 = n4;
                                    jN[n103] = jN[n103] + 1;
                                } else if (jN[n4] > fh) {
                                    int n104 = n4;
                                    jN[n104] = jN[n104] - 1;
                                }
                                if (a.a(jN[n4] - fh) < 16 && a.a(jO[n4] - (fi - 16)) < 20) {
                                    a.a(ke[n4], -fp);
                                    a.jX[n4] = 15;
                                }
                            }
                            if (jX[n4] == 15) {
                                a.jY[n4] = 1;
                            }
                            if (jX[n4] <= 15) break block0;
                            a.ka[n4] = 2;
                            int n105 = n4;
                            jO[n105] = jO[n105] + jY[n4];
                            int n106 = n4;
                            jY[n106] = jY[n106] + 1;
                            if (a.a(jN[n4] - fh) < 16 && a.a(jO[n4] - (fi - 16)) < 20) {
                                a.a(ke[n4], -fp);
                            }
                            if (jO[n4] <= kn[kk[n4]][ki[n4]]) break block0;
                            a.jO[n4] = kn[kk[n4]][ki[n4]];
                            a.jY[n4] = 0;
                            a.jX[n4] = 0;
                            a.jS[n4] = 2;
                            break;
                        }
                        case 11: {
                            int n107 = n4 = n5;
                            jX[n107] = jX[n107] + 1;
                            if (jX[n4] == 4 && jO[n4] == fi) {
                                a.a(ke[n4], kb[n4]);
                            }
                            if (jX[n4] < 8) {
                                int n108 = n4;
                                jN[n108] = jN[n108] + (kb[n4] << 1);
                                a.ka[n4] = 3;
                            } else {
                                a.ka[n4] = 0;
                                int n109 = n4;
                                jN[n109] = jN[n109] - (kb[n4] << 1);
                            }
                            if (jX[n4] != 12) break block0;
                            a.jS[n4] = 3;
                            a.jX[n4] = 0;
                            a.kf[n4] = 25;
                            break;
                        }
                        case 13: {
                            n4 = n5;
                            if (jN[n4] < fh) {
                                a.jV[n4] = 4 + w % 6;
                            } else if (jN[n4] > fh) {
                                a.jV[n4] = -(4 + w % 6);
                            }
                            if (jO[n4] < fi) {
                                a.jW[n4] = 2 + w % 4;
                            } else if (jO[n4] > fi) {
                                a.jW[n4] = -(2 + w % 4);
                            }
                            int n110 = n4;
                            jN[n110] = jN[n110] + jV[n4];
                            int n111 = n4;
                            jO[n111] = jO[n111] + jW[n4];
                            if (w % 2 == 0) {
                                int n112 = n4;
                                ka[n112] = ka[n112] + 1;
                                if (ka[n4] > 1) {
                                    a.ka[n4] = 0;
                                }
                            }
                            if (a.a(jN[n4] - fh) < 5 && a.a(jO[n4] - fi) < 5) {
                                a.a(ke[n4], -fp);
                                a.jS[n4] = 2;
                                a.jX[n4] = 0;
                                a.kb[n4] = jN[n4] > jT[n4] ? -1 : 1;
                                a.jV[n4] = kb[n4] * (w % 4 + 1);
                                a.kf[n4] = 10;
                            }
                            if (a.a(jN[n4] - fh) <= 130 && a.a(jO[n4] - fi) <= 130) break block0;
                            a.jS[n4] = 2;
                            a.jX[n4] = 0;
                            a.jW[n4] = 0;
                            a.jV[n4] = 0;
                            a.kb[n4] = jN[n4] > jT[n4] ? -1 : 1;
                            a.jV[n4] = kb[n4] * (w % 4 + 1);
                            a.kf[n4] = 0;
                            break;
                        }
                        case 14: {
                            a.R(n5);
                            break;
                        }
                        case 15: {
                            int n113 = n4 = n5;
                            jX[n113] = jX[n113] + 1;
                            if (jX[n4] == 3) {
                                if (kl[n4]) {
                                    a.kl[n4] = false;
                                    a.b(ke[n4], kb[n4]);
                                } else {
                                    a.a(ke[n4], kb[n4]);
                                }
                            }
                            if ((jX[n4] >> 2) % 2 == 0) {
                                int n114 = n4;
                                jN[n114] = jN[n114] + kb[n4];
                                a.ka[n4] = 0;
                            } else {
                                a.ka[n4] = 3;
                                int n115 = n4;
                                jN[n115] = jN[n115] - kb[n4];
                            }
                            if (jX[n4] != 12) break block0;
                            a.jO[n4] = kn[kk[n4]][ki[n4]];
                            a.jS[n4] = 3;
                            a.jY[n4] = -4;
                            a.jX[n4] = 0;
                            break;
                        }
                        case 16: {
                            a.N(n5);
                            break;
                        }
                        case 17: 
                        case 18: {
                            int n116 = n4 = n5;
                            jX[n116] = jX[n116] + 1;
                            a.ka[n4] = 3;
                            if (jX[n4] == 3) {
                                a.b(jN[n4] + (kb[n4] << 3), jO[n4] - 12, kb[n4] << 3, ke[n4], 2);
                                break;
                            }
                            if (jX[n4] != 6) break block0;
                            a.jS[n4] = 3;
                            a.jX[n4] = 0;
                            break;
                        }
                        case 19: {
                            int n117 = n4 = n5;
                            jX[n117] = jX[n117] + 1;
                            a.ka[n4] = 3;
                            if (jX[n4] == 3) {
                                int n118 = n4;
                                jY[n118] = jY[n118] + 1;
                                if (jY[n4] == 3) {
                                    a.jY[n4] = 0;
                                }
                                if (jY[n4] == 2) {
                                    a.c(jN[n4], jO[n4] - 22, kb[n4], -10, ke[n4]);
                                } else if (jY[n4] == 0) {
                                    a.c(jN[n4], jO[n4] - 22, kb[n4] << 1, -9, ke[n4]);
                                } else {
                                    a.c(jN[n4], jO[n4] - 22, 0, -11, ke[n4]);
                                }
                                a.g(jN[n4], jO[n4] - 20);
                                break;
                            }
                            if (jX[n4] != 6) break block0;
                            a.jS[n4] = 3;
                            a.jX[n4] = 0;
                            break;
                        }
                        case 12: {
                            a.R(n5);
                        }
                    }
                    break;
                }
                case 3: {
                    int n5;
                    int n119 = n5 = n;
                    jX[n119] = jX[n119] + 1;
                    if (jX[n5] >= 35) {
                        a.jX[n5] = 0;
                        a.jS[n5] = 2;
                    }
                    a.ka[n5] = 0;
                    a.J(n5);
                    break;
                }
                case 1: {
                    int n5;
                    int n120 = n5 = n;
                    jX[n120] = jX[n120] + 1;
                    int n121 = n5;
                    jO[n121] = jO[n121] + jX[n5];
                    if (w % 2 == 0) {
                        if (jY[n5] > 1) {
                            int n122 = n5;
                            jY[n122] = jY[n122] - 1;
                        } else if (jY[n5] < -1) {
                            int n123 = n5;
                            jY[n123] = jY[n123] + 1;
                        }
                    }
                    int n124 = n5;
                    jN[n124] = jN[n124] + jY[n5];
                    a.ka[n5] = 2;
                    if (jO[n5] >= iS || jN[n5] < 0 || jN[n5] >= iR) {
                        a.jX[n5] = -300;
                        a.jS[n5] = 5;
                        if (T) {
                            a.jS[n5] = 8;
                        }
                        a.ko[a.kk[n5]][a.ki[n5]] = false;
                        break;
                    }
                    if ((jZ[n5] == 0 || jZ[n5] < 3 && jX[n5] > 7) && (a.i(jN[n5], jO[n5]) & 2) == 2) {
                        a.jX[n5] = jX[n5] > 4 ? -4 : -jX[n5];
                        int n125 = n5;
                        jZ[n125] = jZ[n125] + 1;
                    }
                    if (!a.g(jN[n5], jO[n5], 64) || jO[n5] % jh <= 8) break;
                    a.g(jN[n5], a.H(jO[n5]) + 8);
                    break;
                }
                case 9: {
                    int n5;
                    int n126 = n5 = n;
                    jO[n126] = jO[n126] + jX[n5];
                    int n127 = n5;
                    jX[n127] = jX[n127] + 1;
                    a.ka[n5] = 2;
                    if (jO[n5] <= kn[kk[n5]][ki[n5]]) break;
                    if (jM[n5] != 2 && jM[n5] != 6 && jM[n5] != 16 && jM[n5] != 13) {
                        a.jO[n5] = kn[kk[n5]][ki[n5]];
                    }
                    a.jS[n5] = 3;
                }
            }
            ++n;
        }
    }

    private static void j(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 2;
            a.kg[n4] = 120;
            a.kh[n4] = 100;
            a.jP[n4] = 20;
            a.jQ[n4] = 30;
            a.kb[n4] = 0;
            a.jS[n4] = 5;
            a.jX[n4] = n4 * 7 + n4;
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 100;
            a.jV[n4] = 2;
            a.jW[n4] = 3;
            ++n4;
        }
    }

    private static void k(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 13;
            a.kg[n4] = 120;
            a.kh[n4] = 50;
            a.jP[n4] = 20;
            a.jQ[n4] = 20;
            a.kb[n4] = -1;
            a.jS[n4] = 5;
            a.jX[n4] = n4 << 1;
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 100;
            a.jV[n4] = -3;
            a.jW[n4] = 3;
            ++n4;
        }
    }

    private static void l(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 9;
            a.kg[n4] = 36;
            a.kh[n4] = 20;
            a.jP[n4] = 34;
            a.jQ[n4] = 32;
            a.kb[n4] = 1;
            a.jS[n4] = 5;
            a.jX[n4] = n4 * 6;
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 100;
            ++n4;
        }
    }

    private static void m(int n, int n2) {
        n = 0;
        while (n < jK) {
            if (jM[n] == -1) break;
            ++n;
        }
        if (n == jK) {
            return;
        }
        int n3 = n;
        while (n3 < n + n2) {
            a.kk[n3] = 1;
            a.jM[n3] = 8;
            a.kg[n3] = 40;
            a.kh[n3] = 30;
            a.jP[n3] = 12;
            a.jQ[n3] = 16;
            int n4 = n3;
            jO[n4] = jO[n4] + 5;
            a.kb[n3] = 1;
            a.jS[n3] = 5;
            a.jX[n3] = n3 * 5;
            a.kc[n3] = a.kd[n3] = an[jM[n3]];
            a.ke[n3] = ao[jM[n3]];
            a.kj[n3] = ap[jM[n3]];
            a.kf[n3] = 60;
            ++n3;
        }
    }

    private static void n(int n, int n2) {
        n = 0;
        while (n < jK) {
            if (jM[n] == -1) break;
            ++n;
        }
        if (n == jK) {
            return;
        }
        int n3 = n;
        while (n3 < n + n2) {
            a.kk[n3] = 2;
            a.jM[n3] = 10;
            a.kg[n3] = 80;
            a.kh[n3] = 100;
            a.jP[n3] = 12;
            a.jQ[n3] = 16;
            a.kb[n3] = 1;
            a.jS[n3] = 5;
            a.jX[n3] = n3 * 5;
            a.kc[n3] = a.kd[n3] = an[jM[n3]];
            a.ke[n3] = ao[jM[n3]];
            a.kj[n3] = ap[jM[n3]];
            a.kf[n3] = 40;
            ++n3;
        }
    }

    private static void o(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 6;
            a.kg[n4] = 60;
            a.kh[n4] = 60;
            a.jP[n4] = 16;
            a.jQ[n4] = 16;
            a.kb[n4] = -1;
            a.jS[n4] = 5;
            a.jX[n4] = n4 << 3;
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 100;
            a.jV[n4] = 2;
            a.jW[n4] = 3;
            ++n4;
        }
    }

    private static void p(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 16;
            a.kg[n4] = 60;
            a.kh[n4] = 60;
            a.jP[n4] = 16;
            a.jQ[n4] = 16;
            a.kb[n4] = -1;
            a.jS[n4] = 5;
            a.jX[n4] = n4 + 60;
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 100;
            a.jV[n4] = 2;
            a.jW[n4] = 3;
            ++n4;
        }
    }

    private static void q(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 7;
            a.kg[n4] = 10;
            a.kh[n4] = 10;
            a.jP[n4] = 30;
            a.jQ[n4] = 25;
            a.kb[n4] = 1;
            a.jS[n4] = 5;
            a.jX[n4] = n4 << 2;
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 0;
            ++n4;
        }
    }

    private static void r(int n, int n2) {
        n = 0;
        while (n < jK) {
            if (jM[n] == -1) break;
            ++n;
        }
        if (n == jK) {
            return;
        }
        int n3 = n;
        while (n3 < n + n2) {
            a.kk[n3] = 2;
            a.jM[n3] = 4;
            a.kg[n3] = 20;
            a.kh[n3] = 10;
            a.jP[n3] = 18;
            a.jQ[n3] = 18;
            a.kb[n3] = 1;
            a.jS[n3] = 5;
            a.jX[n3] = n3 << 3;
            a.kc[n3] = a.kd[n3] = an[jM[n3]];
            a.ke[n3] = ao[jM[n3]];
            a.kj[n3] = ap[jM[n3]];
            a.kf[n3] = 0;
            ++n3;
        }
    }

    private static void am() {
        km = new int[kp.length][];
        kn = new int[kp.length][];
        ko = new boolean[kp.length][];
    }

    private static void M(int n) {
        jK = n;
        jL = 70;
        jM = new int[jK];
        kk = new int[jK];
        jN = new int[jK];
        jO = new int[jK];
        jS = new int[jK];
        jT = new int[jK];
        jU = new int[jK];
        jV = new int[jK];
        jW = new int[jK];
        jX = new int[jK];
        jY = new int[jK];
        jZ = new int[jK];
        ka = new int[jK];
        kb = new int[jK];
        kc = new int[jK];
        kl = new boolean[jK];
        kd = new int[jK];
        ke = new int[jK];
        kf = new int[jK];
        jP = new int[jK];
        jQ = new int[jK];
        jQ = new int[jK];
        jR = new int[jK];
        kg = new int[jK];
        kh = new int[jK];
        ki = new int[jK];
        kj = new int[jK];
        n = 0;
        while (n < jK) {
            a.jM[n] = -1;
            ++n;
        }
    }

    private static void s(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 0;
            a.kg[n4] = 25;
            a.kh[n4] = 10;
            a.jP[n4] = 27;
            a.jQ[n4] = 15;
            a.kb[n4] = 1;
            a.jS[n4] = 5;
            a.jX[n4] = n4 * 7;
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 0;
            ++n4;
        }
    }

    private static void t(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 15;
            a.kg[n4] = 36;
            a.kh[n4] = 10;
            a.jP[n4] = 27;
            a.jQ[n4] = 15;
            a.kb[n4] = 1;
            a.jS[n4] = 5;
            a.jX[n4] = n4 << 1;
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 0;
            ++n4;
        }
    }

    private static void u(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 1;
            a.kg[n4] = 7;
            a.kh[n4] = 10;
            a.jP[n4] = 16;
            a.jQ[n4] = 15;
            a.kb[n4] = 1;
            a.jS[n4] = 5;
            a.jX[n4] = n4 << 3;
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 100;
            ++n4;
        }
    }

    private static void v(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 18;
            a.kg[n4] = 120;
            a.kh[n4] = 10;
            a.jP[n4] = 24;
            a.jQ[n4] = 20;
            a.kb[n4] = 1;
            a.jS[n4] = 5;
            a.jX[n4] = 60;
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 100;
            ++n4;
        }
    }

    private static void w(int n, int n2) {
        n = 0;
        while (n < jK) {
            if (jM[n] == -1) break;
            ++n;
        }
        if (n == jK) {
            return;
        }
        int n3 = n;
        while (n3 < n + n2) {
            a.kk[n3] = 0;
            a.jM[n3] = 19;
            a.kg[n3] = 60;
            a.kh[n3] = 30;
            a.jP[n3] = 24;
            a.jQ[n3] = 20;
            a.kb[n3] = 1;
            a.jS[n3] = 5;
            a.jX[n3] = 62;
            a.kc[n3] = a.kd[n3] = an[jM[n3]];
            a.ke[n3] = ao[jM[n3]];
            a.kj[n3] = ap[jM[n3]];
            a.kf[n3] = 70;
            ++n3;
        }
    }

    private static void x(int n, int n2) {
        int n3 = 0;
        while (n3 < jK) {
            if (jM[n3] == -1) break;
            ++n3;
        }
        if (n3 == jK) {
            return;
        }
        int n4 = n3;
        while (n4 < n3 + n2) {
            a.kk[n4] = n;
            a.jM[n4] = 17;
            a.kg[n4] = 60;
            a.kh[n4] = 10;
            a.jP[n4] = 22;
            a.jQ[n4] = 22;
            a.kb[n4] = 1;
            a.jS[n4] = 5;
            a.jX[n4] = 50 + (n4 << 1);
            a.kc[n4] = a.kd[n4] = an[jM[n4]];
            a.ke[n4] = ao[jM[n4]];
            a.kj[n4] = ap[jM[n4]];
            a.kf[n4] = 500;
            ++n4;
        }
    }

    private static void y(int n, int n2) {
        n = 0;
        while (n < jK) {
            if (jM[n] == -1) break;
            ++n;
        }
        if (n == jK) {
            return;
        }
        int n3 = n;
        while (n3 < n + n2) {
            a.kk[n3] = 0;
            a.jM[n3] = 12;
            a.kg[n3] = 24;
            a.kh[n3] = 10;
            a.jP[n3] = 16;
            a.jQ[n3] = 22;
            a.kb[n3] = 1;
            a.jS[n3] = 5;
            a.jX[n3] = 70;
            a.kc[n3] = a.kd[n3] = an[jM[n3]];
            a.ke[n3] = ao[jM[n3]];
            a.kj[n3] = ap[jM[n3]];
            a.kf[n3] = 500;
            ++n3;
        }
    }

    private static void an() {
        int n = 0;
        while (n < kp.length) {
            a.ko[n] = new boolean[km[n].length];
            ++n;
        }
        n = 0;
        while (n < km.length) {
            int n2 = 0;
            while (n2 < km[n].length) {
                a.km[n][n2] = km[n][n2] * jh + 12;
                a.kn[n][n2] = kn[n][n2] * jh;
                if (n == 8) {
                    int[] nArray = kn[n];
                    int n3 = n2;
                    nArray[n3] = nArray[n3] - 12;
                }
                ++n2;
            }
            ++n;
        }
    }

    private static void N(int n) {
        if (jN[n] < fh) {
            a.jV[n] = w % 3 + 3;
            a.kb[n] = 1;
        } else if (jN[n] > fh) {
            a.jV[n] = -w % 3 - 3;
            a.kb[n] = -1;
        }
        if (jO[n] < fi) {
            a.jW[n] = w % 3 + 3;
        } else if (jO[n] > fi) {
            a.jW[n] = -w % 3 - 3;
        }
        int n2 = n;
        jN[n2] = jN[n2] + jV[n];
        int n3 = n;
        jO[n3] = jO[n3] + jW[n];
        int n4 = n;
        ka[n4] = ka[n4] + 1;
        if (ka[n] > 1) {
            a.ka[n] = 0;
        }
        if (a.a(jN[n] - fh) < 10 && a.a(jO[n] - fi) < 10) {
            a.kf[n] = 0;
            if (kl[n]) {
                a.kl[n] = false;
                a.b(ke[n], kb[n]);
            } else {
                a.a(ke[n], kb[n]);
            }
            a.jX[n] = 0;
            a.jS[n] = 2;
        }
        if (a.a(jN[n] - fh) > 160 || a.a(jO[n] - fi) > 160) {
            a.jS[n] = 2;
            a.jX[n] = 0;
            a.jW[n] = 0;
            a.jV[n] = 0;
            a.kf[n] = 0;
        }
    }

    private static void O(int n) {
        int n2 = n;
        jX[n2] = jX[n2] + 1;
        int n3 = n;
        jN[n3] = jN[n3] - jV[n];
        int n4 = n;
        jO[n4] = jO[n4] - jW[n];
        if (jV[n] == 0) {
            a.jV[n] = 2;
        }
        if (jW[n] == 0) {
            a.jW[n] = 2;
        }
        if (jX[n] == 5) {
            a.jS[n] = 2;
            a.jX[n] = 0;
            a.jW[n] = 0;
            a.jV[n] = 0;
            a.kf[n] = 0;
        }
    }

    private static void P(int n) {
        int n2 = n;
        jN[n2] = jN[n2] + jV[n];
        int n3 = n;
        jO[n3] = jO[n3] + jW[n];
        if (jN[n] - jT[n] > 40) {
            a.jV[n] = -(w % 2 + 1);
        }
        if (jN[n] - jT[n] < -40) {
            a.jV[n] = w % 2 + 1;
        }
        if (jO[n] - jU[n] > 40) {
            a.jW[n] = -(w % 2 + 1);
        }
        if (jO[n] - jU[n] < -40) {
            a.jW[n] = w % 2 + 1;
        }
        if (w % 15 == 0) {
            a.jV[n] = w % 3 - 1;
            a.jW[n] = (w >> 1) % 3 - 1;
        }
        int n4 = n;
        ka[n4] = ka[n4] + 1;
        if (ka[n] > 1) {
            a.ka[n] = 0;
        }
        a.J(n);
        if (w % 5 == 0) {
            a.K(n);
        }
    }

    private static void Q(int n) {
        if (w % 4 == 1) {
            int n2 = n;
            ka[n2] = ka[n2] + 1;
            if (ka[n] > 1) {
                a.ka[n] = 0;
            }
            int n3 = n;
            jN[n3] = jN[n3] + (kb[n] << 1);
            if (jN[n] > jU[n]) {
                a.kb[n] = -1;
            } else if (jN[n] < jT[n]) {
                a.kb[n] = 1;
            }
        }
        a.J(n);
    }

    private static void R(int n) {
        a.a(ke[n], -fp);
        a.jS[n] = 2;
        a.jX[n] = 0;
        a.ka[n] = 2;
    }

    private static void S(int n) {
        int n2 = n;
        jX[n2] = jX[n2] + 1;
        int n3 = n;
        jN[n3] = jN[n3] - kb[n];
        if (jX[n] == 5) {
            a.jS[n] = 2;
        }
    }

    private static void T(int n) {
        int n2 = n;
        jX[n2] = jX[n2] + 1;
        int n3 = n;
        jN[n3] = jN[n3] - kb[n];
        if (jX[n] == 3) {
            a.jS[n] = 2;
        }
    }

    private static void U(int n) {
        if (w % 4 == 0) {
            int n2 = n;
            ka[n2] = ka[n2] + 1;
            if (ka[n] > 1) {
                a.ka[n] = 0;
            }
            int n3 = n;
            jN[n3] = jN[n3] + kb[n];
        }
        if (jN[n] > jU[n]) {
            a.kb[n] = -1;
        } else if (jN[n] < jT[n]) {
            a.kb[n] = 1;
        }
        a.J(n);
        if (w % 5 == 0) {
            a.K(n);
        }
    }

    private static void ao() {
        U = bG - 40;
        kr = bF - 50;
        iW = null;
        kA = new Image[4];
        try {
            a.kA[0] = Image.createImage((String)"/u/wpt0.png");
            a.kA[1] = Image.createImage((String)"/u/wpt2.png");
            a.kA[2] = Image.createImage((String)"/u/wpt3.png");
            a.kA[3] = Image.createImage((String)"/u/wpt4.png");
        }
        catch (Exception exception) {}
        if (kK == null) {
            kK = new Image[4];
            int n = 0;
            while (n < 4) {
                try {
                    a.kK[n] = Image.createImage((String)("/u/bd" + n + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n;
            }
        }
        System.gc();
    }

    private static void m(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        a.d(graphics);
        a.c(graphics);
        graphics.setClip(0, 0, aW, aX);
        graphics.setColor(0);
        graphics.fillRect(bF - 64, U - 19, 129, 102);
        graphics.fillRect(0, aX - 13, aW, 13);
        Midlet.a(graphics, "Mua", 3, aX - 13, 0);
        Midlet.a(graphics, "Quay l\u1ea1i", aW - 3, aX - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(bF - 63, U - 18, 126, 99);
        graphics.setColor(6562304);
        graphics.fillRect(bF - 61, U - 16, 123, 97);
        if (kv == 0) {
            Midlet.a(graphics, "Ti\u1ec1n: " + dg + "$", bF, U - 8, 2);
        } else {
            Midlet.a(graphics, kx[ku][ks], bF, U - 8, 2);
        }
        int n = 0;
        while (n < 3) {
            graphics.setColor(0);
            graphics.fillRect(kr - 10 + n * 20, U + 10, 19, 20);
            graphics.fillRect(kr + 50 + n * 20, U + 10, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(kr - 10 + n * 20, U + 10, 20, 20);
            graphics.drawRect(kr + 50 + n * 20, U + 10, 20, 20);
            graphics.drawRegion(ez[0], 0, n * 15, 13, 15, 0, kr + n * 20, U + 20, 3);
            graphics.drawRegion(ez[1], 0, n * 15, 13, 15, 0, kr + 60 + n * 20, U + 20, 3);
            ++n;
        }
        n = 0;
        while (n < 4) {
            graphics.setColor(0);
            graphics.fillRect(kr + 10 + n * 20, U + 32, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(kr + 10 + n * 20, U + 32, 18, 20);
            graphics.drawImage(kA[n], kr + 20 + n * 20, U + 42, 3);
            ++n;
        }
        graphics.setColor(kw[kt]);
        if (ku == 0) {
            graphics.drawRect(kr - 10 + ks * 20, U + 10, 19, 20);
        } else {
            graphics.drawRect(kr + 10 + ks * 20, U + 32, 19, 20);
        }
        if (kv == 0) {
            Midlet.a(graphics, String.valueOf(kx[ku][ks]) + "-" + kz[ku][ks] + "$", bF, U + 58, 2);
        } else {
            Midlet.a(graphics, ky[ku][ks], bF, U + 58, 2);
        }
        graphics.drawImage(kK[0], bF - 64, U - 19, 20);
        graphics.drawImage(kK[1], bF + 65, U - 19, 24);
        graphics.drawImage(kK[2], bF - 64, U + 83, 36);
        graphics.drawImage(kK[3], bF + 65, U + 83, 40);
    }

    private static void ap() {
        if (aP) {
            aP = false;
            if (aR > aX - 40) {
                if (aQ < aY) {
                    a.aV[12] = true;
                } else {
                    a.aV[13] = true;
                }
            }
            if (aR > U && aR < U + 32) {
                ku = 0;
                ks = 0;
                int n = 0;
                while (n < 6) {
                    if (aQ > kr - 10 + n * 20 && aQ < kr - 10 + n * 20 + 20) {
                        ks = n;
                    }
                    ++n;
                }
            } else if (aR < U + 60) {
                ku = 1;
                ks = 0;
                int n = 0;
                while (n < 4) {
                    if (aQ > kr + 10 + n * 20 && aQ < kr + 10 + n * 20 + 20) {
                        ks = n;
                    }
                    ++n;
                }
            }
        }
        if (kt > 0) {
            --kt;
        }
        if (aV[5] || aV[12]) {
            a.d();
            if (ku == 0) {
                if (kv == 0) {
                    if (ks < 3 ? V[0] + V[1] + V[2] > 99 : V[3] + V[4] + V[5] > 99) {
                        return;
                    }
                    if (dg >= kz[ku][ks]) {
                        int n = ks;
                        V[n] = V[n] + 1;
                        dg -= kz[ku][ks];
                    }
                } else {
                    a.a(String.valueOf(Z) + " BUY " + "009" + "007" + " " + kB[ku][ks], kC[ku][ks], ks + 1);
                }
            } else {
                if (x >= ks) {
                    a.s(7);
                    a.a("Kh\u00f4ng c\u1ea7n thi\u1ebft ph\u1ea3i mua v\u0169 kh\u00ed n\u00e0y v\u00ec v\u0169 kh\u00ed \u0111ang s\u1eed d\u1ee5ng m\u1ea1nh h\u01a1n!");
                    X = U;
                    d = 7;
                    return;
                }
                if (M < kq[ks]) {
                    a.s(7);
                    a.a("B\u1ea1n ph\u1ea3i \u0111\u1ea1t c\u1ea5p \u0111\u1ed9 " + kq[ks] + " m\u1edbi c\u00f3 th\u1ec3 mua v\u0169 kh\u00ed n\u00e0y!");
                    X = U;
                    d = 7;
                    return;
                }
                if (kv == 0) {
                    if (dg >= kz[ku][ks]) {
                        dg -= kz[ku][ks];
                        x = ks;
                        a.t();
                        return;
                    }
                    a.s(7);
                    a.a("Kh\u00f4ng c\u00f3 \u0111\u1ee7 ti\u1ec1n: " + kz[ku][ks] + "$. B\u1ea1n ch\u1ec9 c\u00f3: " + dg + "$. H\u00e3y \u0111i t\u00ecm th\u00eam.");
                    X = U;
                    d = 7;
                    return;
                }
                a.a(String.valueOf(Z) + " BUY " + "009" + "007" + " " + kB[ku][ks], kC[ku][ks], ks + 6);
            }
        }
        if (aV[13]) {
            kK = null;
            kA = null;
            System.gc();
            a.ai();
            a.d();
            a.c();
            d = 1;
            c = true;
            return;
        }
        if (aV[2]) {
            a.d();
            if (ku == 1) {
                ku = 0;
                ++ks;
            }
        }
        if (aV[8]) {
            a.d();
            if (ku == 0) {
                ku = 1;
                if (--ks < 0) {
                    ks = 0;
                }
                if (ks > 3) {
                    ks = 3;
                }
            }
        }
        if (aV[4]) {
            a.d();
            if (--ks < 0) {
                ks = ku == 0 ? 5 : 3;
            }
        }
        if (aV[6]) {
            a.d();
            if (ku == 0 && ++ks == 6) {
                ks = 0;
            }
            if (ku == 1 && ks == 4) {
                ks = 0;
            }
        }
    }

    private static void n(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        a.d(graphics);
        a.c(graphics);
        graphics.setClip(0, 0, aW, aX);
        graphics.setColor(0);
        graphics.fillRect(bF - 64, kD - 19, 129, 102);
        graphics.fillRect(0, aX - 13, aW, 13);
        Midlet.a(graphics, "S\u1eed d\u1ee5ng", 3, aX - 13, 0);
        Midlet.a(graphics, "Quay l\u1ea1i", aW - 3, aX - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(bF - 63, kD - 18, 126, 99);
        graphics.setColor(6562304);
        graphics.fillRect(bF - 61, kD - 16, 123, 97);
        Midlet.a(graphics, "S\u1eed d\u1ee5ng Item", bF, kD - 8, 2);
        int n = 0;
        while (n < 3) {
            graphics.setColor(0);
            graphics.fillRect(kE - 10 + n * 20, kD + 10, 19, 20);
            graphics.fillRect(kE + 50 + n * 20, kD + 10, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(kE - 10 + n * 20, kD + 10, 20, 20);
            graphics.drawRect(kE + 50 + n * 20, kD + 10, 20, 20);
            graphics.drawRegion(ez[0], 0, n * 15, 13, 15, 0, kE + n * 20, kD + 20, 3);
            graphics.drawRegion(ez[1], 0, n * 15, 13, 15, 0, kE + 60 + n * 20, kD + 20, 3);
            ++n;
        }
        graphics.setColor(kH[kG]);
        graphics.drawRect(kE - 10 + kF * 20, kD + 10, 20, 20);
        Midlet.a(graphics, kI[kF], bF, kD + 40, 2);
        Midlet.a(graphics, "S\u1ed1 l\u01b0\u1ee3ng: " + V[kF], bF, kD + 55, 2);
        graphics.drawImage(kK[0], bF - 64, kD - 19, 20);
        graphics.drawImage(kK[1], bF + 65, kD - 19, 24);
        graphics.drawImage(kK[2], bF - 64, kD + 83, 36);
        graphics.drawImage(kK[3], bF + 65, kD + 83, 40);
    }

    private static void aq() {
        if (aP) {
            aP = false;
            if (aR > aX - 40) {
                if (aQ < aY) {
                    a.aV[12] = true;
                } else {
                    a.aV[13] = true;
                }
            }
            if (aR > kD - 20 && aR < kD + 50) {
                int n = 0;
                while (n < 6) {
                    if (aQ > kE - 10 + n * 20 && aQ < kE - 10 + n * 20 + 20) {
                        kF = n;
                    }
                    ++n;
                }
            }
        }
        if (kG > 0) {
            --kG;
        }
        if (aV[5] || aV[12]) {
            if (V[kF] > 0) {
                int n = kF;
                V[n] = V[n] - 1;
                kG = kH.length - 1;
                if (kF <= 2) {
                    if (G < H) {
                        G += kJ[kF];
                    }
                    if (G > H) {
                        G = H;
                    }
                } else {
                    if (I < J) {
                        I += kJ[kF];
                    }
                    if (I > J) {
                        I = J;
                    }
                }
            }
            a.d();
        }
        if (aV[13]) {
            a.d();
            a.c();
            d = 5;
            return;
        }
        if (aV[4]) {
            a.d();
            if (--kF < 0) {
                kF = 5;
            }
        }
        if (aV[6]) {
            a.d();
            if (++kF == 6) {
                kF = 0;
            }
        }
    }

    private static void ar() {
        kL = bG - 40;
        iW = null;
        if (kK == null) {
            kK = new Image[4];
            int n = 0;
            while (n < 4) {
                try {
                    a.kK[n] = Image.createImage((String)("/u/bd" + n + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n;
            }
        }
    }

    private static void o(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(bF - 64, kL - 19, 129, 102);
        graphics.fillRect(0, aX - 13, aW, 13);
        Midlet.a(graphics, "Ch\u1ecdn", 3, aX - 13, 0);
        Midlet.a(graphics, "Quay l\u1ea1i", aW - 3, aX - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(bF - 63, kL - 18, 126, 99);
        graphics.setColor(6562304);
        graphics.fillRect(bF - 61, kL - 16, 123, 97);
        int n = 0;
        while (n < 4) {
            Midlet.a(graphics, kO[n], aY - 30, kL + n * 18, 0);
            ++n;
        }
        graphics.drawImage(eF[0], aY - 55 + kN % 2, kL + kM * 18 - kN % 2, 0);
        graphics.drawImage(kK[0], bF - 64, kL - 19, 20);
        graphics.drawImage(kK[1], bF + 65, kL - 19, 24);
        graphics.drawImage(kK[2], bF - 64, kL + 83, 36);
        graphics.drawImage(kK[3], bF + 65, kL + 83, 40);
    }

    private static void as() {
        int n;
        if (aP) {
            aP = false;
            if (aR > aX - 40) {
                if (aQ < aY) {
                    a.aV[12] = true;
                } else {
                    a.aV[13] = true;
                }
            }
            n = 0;
            while (n < 4) {
                if (aR > kL + n * 18 - 4 && aR < kL + n * 18 + 14) {
                    kM = n;
                }
                ++n;
            }
        }
        if (++kN > 10000) {
            kN = 0;
        }
        n = 0;
        if (aV[13]) {
            n = 1;
            kK = null;
            a.ai();
            d = 1;
            c = true;
        } else if (aV[5] || aV[12]) {
            n = 1;
            if (kM == 0) {
                kD = bG - 40;
                kE = bF - 50;
                if (kK == null) {
                    kK = new Image[4];
                    int n2 = 0;
                    while (n2 < 4) {
                        try {
                            a.kK[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                        }
                        catch (IOException iOException) {
                            IOException iOException2 = iOException;
                            iOException.printStackTrace();
                        }
                        ++n2;
                    }
                }
                d = 8;
            }
            if (kM == 1) {
                lj = 280 - q;
                lk = 280 - r;
                kZ = 0;
                try {
                    kV = Image.createImage((String)"/wm.png");
                    kW = Image.createImage((String)"/u/x.png");
                }
                catch (Exception exception) {}
                ll = v;
                if (v == 99) {
                    ll = 0;
                }
                if (v == 97) {
                    ll = 7;
                }
                if (v == 96) {
                    ll = 11;
                }
                if (v == 95) {
                    ll = 18;
                }
                kX = kR[ll];
                kY = kS[ll];
                a.au();
                lb = kX - bF;
                lc = kY - bG;
                if (lh < -40) {
                    lh = -40;
                }
                if (lh > lj) {
                    lh = lj;
                }
                if (li < -40) {
                    li = -40;
                }
                if (li > lk) {
                    li = lk;
                }
                d = 6;
            }
            if (kM == 2) {
                if (A != 1) {
                    kK = null;
                    a.ai();
                    a.aw();
                    a.s(5);
                    a.a("B\u1ea1n ch\u1ec9 c\u00f3 th\u1ec3 l\u01b0u v\u00e0 tho\u00e1t khi nh\u00e2n v\u1eadt \u0111ang \u0111\u1ee9ng y\u00ean.");
                    X = bG - 40;
                    d = 7;
                } else {
                    aO = false;
                }
            }
        } else if (aV[8]) {
            n = 1;
            if (++kM > 3) {
                kM = 0;
            }
        } else if (aV[2]) {
            n = 1;
            if (--kM < 0) {
                kM = 3;
            }
        } else if (aV[4]) {
            n = 1;
        } else if (aV[6]) {
            n = 1;
        }
        if (n != 0) {
            a.d();
        }
    }

    private static void at() {
        kV = null;
        kW = null;
        System.gc();
    }

    private static void p(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(0, 0, q, r);
        graphics.fillRect(0, aX - 13, aW, 13);
        Midlet.a(graphics, lm, 3, aX - 13, 0);
        Midlet.a(graphics, "Quay l\u1ea1i", aW - 3, aX - 13, 1);
        graphics.setClip(0, 0, q, r);
        graphics.translate(-lh, -li);
        graphics.drawImage(kV, 0, 0, 0);
        int n = 0;
        while (n < 19) {
            if (n == ll) {
                graphics.drawImage(er[0], kR[n] + 10, kS[n], 3);
            }
            if (W[n] && (n != kZ || la % 10 > 5)) {
                graphics.drawImage(kW, kR[n], kS[n], 3);
            }
            ++n;
        }
        graphics.drawImage(eF[0], kX, kY, 24);
        if (kZ != -1) {
            graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
            graphics.setColor(6562304);
            graphics.fillRect(aY - 60, r - 35, 120, 33);
            graphics.setColor(0xBB6611);
            graphics.drawRect(aY - 59, r - 34, 117, 30);
            Midlet.a(graphics, kP[kZ], aY, r - 25, 2);
            graphics.drawImage(kK[0], aY - 60, r - 35, 20);
            graphics.drawImage(kK[1], aY + 60, r - 35, 24);
            graphics.drawImage(kK[2], aY - 60, r - 2, 36);
            graphics.drawImage(kK[3], aY + 60, r - 2, 40);
        }
    }

    private static void au() {
        kZ = -1;
        lm = "";
        int n = 0;
        while (n < 19) {
            if (a.a(kX - kR[n]) < 8 && a.a(kY - kS[n]) < 8) {
                kZ = n;
                lm = "Chuy\u1ec3n \u0111\u1ebfn";
                return;
            }
            ++n;
        }
    }

    private static void av() {
        try {
            ln = Image.createImage((String)"/u/m.png");
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static void a(String string) {
        lv = Midlet.a(string, 100);
        X = bG - lv.length * 9;
    }

    public static void s(int n) {
        a.aw();
        lq = n;
        if (n == 1) {
            a.a("Kh\u00f4ng th\u1ec3 chuy\u1ec3n. V\u00ec b\u1ea1n ch\u01b0a t\u1eebng \u0111i b\u1ed9 \u0111\u1ebfn \u0111\u00e2y!");
            lt = "OK";
            lu = "";
            return;
        }
        if (n == 3) {
            a.a("Kh\u00f4ng c\u1ea7n ph\u1ea3i chuy\u1ec3n v\u00ec b\u1ea1n \u0111ang \u1edf v\u1ecb tr\u00ed n\u00e0y!");
            lt = "OK";
            lu = "";
            return;
        }
        if (n == 2) {
            n = ll < kZ ? ll : kZ;
            int n2 = ll > kZ ? ll : kZ;
            int n3 = 0;
            while (n <= n2) {
                n3 += kQ[n];
                ++n;
            }
            lr = n3 = (n3 << 1) / 3;
            a.a("Chuy\u1ec3n \u0111\u1ebfn \u0111\u00e2y ph\u1ea3i tr\u1ea3 " + n3 + "$. B\u1ea1n c\u00f3 " + dg + "$. Chuy\u1ec3n?");
            lt = "Chuy\u1ec3n";
            lu = "Kh\u00f4ng";
            return;
        }
        if (n == 4) {
            a.a("B\u1ea1n kh\u00f4ng c\u00f3 \u0111\u1ee7 ti\u1ec1n!");
            lt = "OK";
            lu = "";
            return;
        }
        if (n == 5) {
            a.a("B\u1ea1n \u0111\u00e3 b\u1ecb tr\u1ecdng th\u01b0\u01a1ng! M\u1ea5t m\u1ed9t s\u1ed1 \u0111i\u1ec3m kinh nghi\u1ec7m!");
            lt = "OK";
            lu = "";
            return;
        }
        if (n == 6) {
            a.a("Level up!");
            lt = "OK";
            lu = "";
            return;
        }
        if (n == 7) {
            a.a("Buy OK!");
            lt = "";
            lu = "OK";
        }
    }

    private static void aw() {
        ls = 0;
        if (kK == null) {
            kK = new Image[4];
            int n = 0;
            while (n < 4) {
                try {
                    a.kK[n] = Image.createImage((String)("/u/bd" + n + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n;
            }
        }
    }

    private static void q(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(bF - 64, X - 19, 129, 102);
        graphics.fillRect(0, aX - 13, aW, 13);
        Midlet.a(graphics, lt, 3, aX - 13, 0);
        Midlet.a(graphics, lu, aW - 3, aX - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(bF - 63, X - 18, 126, 99);
        graphics.setColor(6562304);
        graphics.fillRect(bF - 61, X - 16, 123, 97);
        int n = 0;
        while (n < lv.length) {
            Midlet.a(graphics, lv[n], aY, X + n * 18, 2);
            ++n;
        }
        graphics.drawImage(kK[0], bF - 64, X - 19, 20);
        graphics.drawImage(kK[1], bF + 65, X - 19, 24);
        graphics.drawImage(kK[2], bF - 64, X + 83, 36);
        graphics.drawImage(kK[3], bF + 65, X + 83, 40);
    }

    private static void V(int n) {
        lK = v;
        lN = n;
        lL = ly[n];
        lM = lz[n] - 44;
    }

    public static void t(int n) {
        lx = 0;
        if (n == 0) {
            lw = new Image[6][2];
            try {
                a.lw[0][0] = Image.createImage((String)"/npc/00.png");
                a.lw[0][1] = Image.createImage((String)"/npc/01.png");
                a.lw[1][0] = Image.createImage((String)"/npc/10.png");
                a.lw[1][1] = Image.createImage((String)"/npc/11.png");
                a.lw[2][0] = Image.createImage((String)"/npc/20.png");
                a.lw[2][1] = Image.createImage((String)"/npc/21.png");
                a.lw[3][0] = Image.createImage((String)"/npc/30.png");
                a.lw[3][1] = Image.createImage((String)"/npc/31.png");
                a.lw[4][0] = Image.createImage((String)"/npc/40.png");
                a.lw[4][1] = Image.createImage((String)"/npc/41.png");
                a.lw[5][0] = Image.createImage((String)"/npc/50.png");
                a.lw[5][1] = Image.createImage((String)"/npc/51.png");
            }
            catch (Exception exception) {}
            lx = 7;
            a.ax();
            a.c(0, 0, 34, 13);
            a.c(1, 1, 43, 17);
            a.c(2, 2, 50, 17);
            a.c(3, 3, 57, 12);
            a.c(4, 4, 43, 12);
            a.c(5, 5, 77, 15);
            a.c(6, 5, 25, 17);
        }
        if (n == 1) {
            lw = new Image[6][2];
            try {
                a.lw[5][0] = Image.createImage((String)"/npc/50.png");
                a.lw[5][1] = Image.createImage((String)"/npc/51.png");
            }
            catch (Exception exception) {}
            lx = 1;
            a.ax();
            a.c(0, 5, 54, 9);
        }
        if (n == 2) {
            lw = new Image[6][2];
            try {
                a.lw[5][0] = Image.createImage((String)"/npc/50.png");
                a.lw[5][1] = Image.createImage((String)"/npc/51.png");
            }
            catch (Exception exception) {}
            lx = 1;
            a.ax();
            a.c(0, 5, 23, 12);
        }
        if (n == 5) {
            lw = new Image[6][2];
            try {
                a.lw[5][0] = Image.createImage((String)"/npc/50.png");
                a.lw[5][1] = Image.createImage((String)"/npc/51.png");
            }
            catch (Exception exception) {}
            lx = 1;
            a.ax();
            a.c(0, 5, 12, 5);
        }
        if (n == 6) {
            lw = new Image[6][2];
            try {
                a.lw[5][0] = Image.createImage((String)"/npc/50.png");
                a.lw[5][1] = Image.createImage((String)"/npc/51.png");
            }
            catch (Exception exception) {}
            lx = 1;
            a.ax();
            a.c(0, 5, 14, 9);
        }
        if (n == 99 || n == 101) {
            lw = new Image[6][2];
            try {
                a.lw[4][0] = Image.createImage((String)"/npc/40.png");
                a.lw[4][1] = Image.createImage((String)"/npc/41.png");
            }
            catch (Exception exception) {}
            lx = 1;
            a.ax();
            a.c(0, 4, 3, 12);
        }
        if (n == 94) {
            lw = new Image[6][2];
            try {
                a.lw[0][0] = Image.createImage((String)"/npc/00.png");
                a.lw[0][1] = Image.createImage((String)"/npc/01.png");
                a.lw[4][0] = Image.createImage((String)"/npc/40.png");
                a.lw[4][1] = Image.createImage((String)"/npc/41.png");
                a.lw[2][0] = Image.createImage((String)"/npc/20.png");
                a.lw[2][1] = Image.createImage((String)"/npc/21.png");
            }
            catch (Exception exception) {}
            lx = 3;
            a.ax();
            a.c(0, 4, 2, 10);
            a.c(1, 0, 3, 10);
            a.c(2, 2, 4, 10);
        }
    }

    private static void c(int n, int n2, int n3, int n4) {
        a.lA[n] = n2;
        a.ly[n] = n3 * jh + 12;
        a.lz[n] = n4 * jh;
    }

    private static void ax() {
        ly = new int[lx];
        lz = new int[lx];
        lA = new int[lx];
    }

    private static void r(Graphics graphics) {
        int n = 0;
        while (n < lx) {
            int n2 = n;
            Graphics graphics2 = graphics;
            if (ly[n2] + 10 >= fR && ly[n2] - 10 <= fR + q && lz[n2] >= fS && lz[n2] - 32 <= fS + r) {
                graphics2.drawImage(lw[lA[n2]][1], ly[n2], lz[n2], 33);
                graphics2.drawImage(lw[lA[n2]][0], ly[n2], lz[n2] - lJ[lA[n2]] + (w % (15 + lA[n2]) > 4 ? 0 : 1), 33);
            }
            ++n;
        }
    }

    private static void ay() {
        if (aV[12]) {
            Midlet.c.notifyDestroyed();
        }
        a.Y(1);
        ma[0] = ma[0] + 1;
    }

    private void az() {
        int n;
        if (aV[2]) {
            --this.mg;
            if (this.mg < 0) {
                this.mg = 1;
            }
            a.d();
        }
        if (aV[8]) {
            ++this.mg;
            if (this.mg > 1) {
                this.mg = 0;
            }
            a.d();
        }
        if (aV[4]) {
            --this.mf;
            if (this.mf < 0) {
                this.mf = 4;
            }
            a.d();
        }
        if (aV[6]) {
            ++this.mf;
            if (this.mf > 4) {
                this.mf = 0;
            }
            a.d();
        }
        if (aV[5]) {
            this.aD();
            a.d();
        }
        if (aV[12]) {
            int n2 = Z.length() + 5;
            a.ma[5] = Integer.parseInt(lZ.substring(n2++, n2));
            a.ma[6] = Integer.parseInt(lZ.substring(n2++, n2));
            a.ma[7] = Integer.parseInt(lZ.substring(n2++, n2));
            a.ma[8] = Integer.parseInt(lZ.substring(n2++, n2));
            a a2 = this;
            if (a2.me.length() < 4) {
                Y = 0;
            } else {
                a.ma[1] = Integer.parseInt(a2.me.substring(0, 1));
                a.ma[2] = Integer.parseInt(a2.me.substring(1, 2));
                a.ma[3] = Integer.parseInt(a2.me.substring(2, 3));
                a.ma[4] = Integer.parseInt(a2.me.substring(3, 4));
                a.ma[8] = (ma[8] + ma[7]) % 10;
                a.ma[7] = (ma[7] + ma[6]) % 10;
                a.ma[6] = (ma[6] + ma[5]) % 10;
                a.ma[5] = (ma[5] + ma[8]) % 10;
                n = 1;
                while (n < 8) {
                    if (ma[n - 1] != ma[n]) {
                        a2.aB();
                    } else if (ma[n] != ma[n] + 1) {
                        a2.aC();
                    } else {
                        a a3 = a2;
                        a.d();
                        a3.aF();
                        a3.aC();
                        a.ay();
                        a.d();
                    }
                    ++n;
                }
            }
            a.d();
        }
        if (aV[13]) {
            this.aA();
            a.d();
        }
        if (aP) {
            int n3 = 0;
            while (n3 < 2) {
                n = 0;
                while (n < 5) {
                    if (aQ > n * 20 + aY - 50 && aQ < n * 20 + aY - 50 + 20 && aR > n3 * 25 + aZ && aR < n3 * 25 + aZ + 25) {
                        this.mf = n;
                        this.mg = n3;
                        this.aD();
                        aP = false;
                    }
                    ++n;
                }
                ++n3;
            }
        }
    }

    private void aA() {
        if (this.me.length() != 0) {
            this.me = this.me.substring(0, this.me.length() - 1);
        }
    }

    private void aB() {
        a.d();
        Y = 0;
        a.aE();
        a.d();
    }

    private void aC() {
        a.d();
        this.aF();
        Y = 4;
        this.aB();
        a.d();
    }

    private void aD() {
        if (this.me.length() < 4) {
            this.me = String.valueOf(this.me) + (this.mg * 5 + this.mf);
        }
    }

    private static void aE() {
        if (aV[5] || aV[12]) {
            a.d();
            Y = 0;
        }
    }

    private void aF() {
        if (aV[5] || aV[12]) {
            Object object = a.aI();
            if (object == null || ((String)object).equals("")) {
                object = String.valueOf(Z) + " PAY " + (System.currentTimeMillis() % 8000L + 1000L) + "009" + "007";
                a.g((String)object);
            }
            Y = 1;
            a.a((String)object, lP, 0);
            a.d();
        }
        if (aV[13]) {
            Y = 0;
            a.d();
        }
    }

    private void aG() {
        if (aV[2]) {
            --this.mc;
            if (this.mc < 0) {
                this.mc = mb == 0 ? 1 : 2;
            }
            a.d();
        }
        if (aV[8]) {
            ++this.mc;
            if (this.mc > (mb == 0 ? 1 : 2)) {
                this.mc = 0;
            }
            a.d();
        }
        if (aV[5] || aV[12]) {
            this.aH();
            a.d();
        }
        if (aV[13]) {
            Midlet.c.notifyDestroyed();
        }
        if (aP) {
            int n = 0;
            while (n < (mb == 0 ? 2 : 3)) {
                if (aR > aZ - 5 + n * lW && aR < aZ - 5 + n * lW + lW) {
                    if (this.mc != n) {
                        this.mc = n;
                    } else {
                        this.aH();
                    }
                    aP = false;
                    return;
                }
                ++n;
            }
        }
    }

    private void aH() {
        if (this.mc == 0 && mb > 0) {
            a.W(--mb);
            d = 4;
            a.aj();
            System.gc();
        }
        if (this.mc == 1 && mb > 0 || this.mc == 0 && mb == 0) {
            Y = 2;
        }
        if (this.mc == 2 && mb > 0 || this.mc == 1 && mb == 0) {
            lZ = a.aI();
            Y = 4;
        }
    }

    private void s(Graphics graphics) {
        graphics.setColor(0x6D6D6D);
        graphics.drawString("Nhap ma kich hoat:", aY, aZ - 60, 17);
        graphics.setColor(11788624);
        graphics.fillRect(this.mf * 20 + aY - 50, this.mg * 25 + aZ, 18, 23);
        int n = 0;
        int n2 = 0;
        while (n2 < 2) {
            int n3 = 0;
            while (n3 < 5) {
                graphics.setColor(11788624);
                graphics.drawRect(n3 * 20 + aY - 50, n2 * 25 + aZ, 18, 23);
                graphics.setColor(n3 == this.mf && n2 == this.mg ? 0xFFFFFF : 0x6D6D6D);
                graphics.drawString(String.valueOf(n), n3 * 20 + aY - 50 + 10, n2 * 25 + aZ + 3, 17);
                ++n;
                ++n3;
            }
            ++n2;
        }
        String string = this.me;
        if (lS % 10 > 5) {
            string = String.valueOf(string) + "|";
        }
        graphics.setColor(0x6D6D6D);
        graphics.drawString(string, aY - 20, aZ - 40, 20);
        graphics.setColor(0xFFFFFF);
        graphics.setFont(lV);
        graphics.drawString("OK", 2, aX - lW + 3, 20);
        graphics.drawString("Xoa", aW - 2, aX - lW + 3, 24);
    }

    private void t(Graphics graphics) {
        graphics.setColor(0x6D6D6D);
        if (ma[1] != ma[5] && ma[2] != ma[6] && ma[3] != ma[7] && ma[4] != ma[8]) {
            Y = 7;
        }
        graphics.setColor(11788624);
        graphics.drawRect(3, aZ - 8 + this.mc * lW, aW - 6, lW);
        graphics.drawRect(4, aZ - 8 + this.mc * lW + 1, aW - 8, lW - 2);
        graphics.setColor(208472);
        if (mb > 0) {
            graphics.setFont(lV);
            graphics.drawString("VeChai321", aY, aZ - (lW << 1), 17);
            graphics.setFont(lU);
            int n = 0;
            while (n < 3) {
                graphics.setColor(0x6D6D6D);
                graphics.drawString(lQ[n], lT, aZ - 5 + n * lW, 20);
                ++n;
            }
        } else {
            graphics.drawString("Da het han dung thu", aY, aZ - (lW << 1), 17);
            graphics.drawString("Moi ban kich hoat.", aY, aZ - lW, 17);
            int n = 0;
            while (n < 2) {
                graphics.setColor(0x6D6D6D);
                graphics.drawString(lR[n], lT, aZ - 5 + n * lW, 20);
                ++n;
            }
        }
        graphics.setColor(0xFFFFFF);
        graphics.setFont(lV);
        graphics.drawString("Chon", 2, aX - lW + 3, 20);
        graphics.drawString("Thoat", aW - 2, aX - lW + 3, 24);
    }

    private static void a(String string, String string2, int n) {
        new Thread(new b(string2, string, n)).start();
    }

    public static void t() {
        a.H();
        a.I();
        a.s(7);
        a.a("Xin ch\u00fac m\u1eebng. B\u1ea1n \u0111\u00e3 c\u00f3 v\u0169 kh\u00ed m\u1edbi: " + kx[ku][ks] + "!");
        X = U;
        d = 7;
    }

    private static byte[] f(String string) {
        byte[] byArray;
        string = "NINJASCHOOL" + string;
        try {
            string = RecordStore.openRecordStore((String)string, (boolean)false);
            byArray = string.getRecord(1);
            string.closeRecordStore();
        }
        catch (Exception exception) {
            return null;
        }
        return byArray;
    }

    private static void a(String string, byte[] byArray) {
        string = "NINJASCHOOL" + string;
        try {
            string = RecordStore.openRecordStore((String)string, (boolean)true);
            if (string.getNumRecords() > 0) {
                string.setRecord(1, byArray, 0, byArray.length);
            } else {
                string.addRecord(byArray, 0, byArray.length);
            }
            string.closeRecordStore();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static void g(String string) {
        a.a("xxx", string.getBytes());
    }

    private static String aI() {
        byte[] byArray = a.f("xxx");
        if (byArray == null) {
            return null;
        }
        return new String(byArray);
    }

    private static void W(int n) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n;
        a.a("np", byArray2);
    }

    private static void X(int n) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n;
        a.a("r", byArray2);
    }

    private static void Y(int n) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n;
        a.a("g", byArray2);
    }

    private static int aJ() {
        byte[] byArray = a.f("g");
        if (byArray == null) {
            return 0;
        }
        return byArray[0];
    }

    public static String b(String string) {
        int n = Integer.parseInt(string.substring(0, 1));
        int n2 = Integer.parseInt(string.substring(1, 2));
        int n3 = Integer.parseInt(string.substring(2, 3));
        int n4 = Integer.parseInt(string.substring(3, 4));
        n4 = (n4 + n3) % 10;
        n3 = (n3 + n2) % 10;
        n2 = (n2 + n) % 10;
        n = (n + n4) % 10;
        return "" + n + n2 + n3 + n4;
    }

    public final void commandAction(Command object, Displayable displayable) {
        if (object.getLabel().equals("OK")) {
            object = String.valueOf(a.aI()) + " >> " + ((TextBox)displayable).getString();
            a.a((String)object, "sms://+84914710711", 0);
        }
        Display.getDisplay((MIDlet)Midlet.c).setCurrent((Displayable)this);
        this.setFullScreenMode(true);
    }
}

