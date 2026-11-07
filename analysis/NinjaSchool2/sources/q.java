/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.io.ConnectionNotFoundException
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.lcdui.TextBox
 *  javax.microedition.midlet.MIDlet
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import javax.microedition.midlet.MIDlet;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public final class q
extends Canvas
implements Runnable,
CommandListener {
    private static boolean by = false;
    public static boolean a = false;
    public static boolean b = false;
    private static int bz = 40;
    static boolean c = true;
    static boolean d = false;
    static boolean e = false;
    static boolean f = false;
    static boolean g = false;
    static boolean h = false;
    public static boolean i = false;
    public static boolean j = false;
    public static boolean k = false;
    public static boolean l = false;
    public static boolean m = false;
    public static boolean n = false;
    public static boolean o = false;
    public static boolean p = false;
    public static boolean q = false;
    public static boolean r = false;
    public static boolean s = false;
    public static boolean t = false;
    private static boolean bA = false;
    public static final int[][] u = new int[][]{{2, 3}, {2, 1, 3}, {2, 6, 3}, {2, 1, 5, 3}, {2, 3}, {2, 3}, {2, 1, 3}, {2, 7, 3}, {7, 3}, {7, 3}, {6, 3}, {2, 8, 3}, {9, 3}, {9, 3}, {2, 3}, {6, 3}, {1, 3}, {6, 3}, {1, 3}, {10, 3}, {1, 3}, {10, 3}, {1, 3}, {1, 3}, {10, 3}, {1, 3}, {1, 3}, {11, 3}, {1, 3}, {1, 3}, {2, 3}, {1, 3}, {10, 3}, {10, 3}, {10, 3}, {10, 3}, {10, 3}, {1, 3}};
    public static final int[][] v;
    private static int[][] bB;
    private static int[][] bC;
    private static boolean bD;
    private static int[][] bE;
    private static int[][] bF;
    private static int[][] bG;
    private static int[][] bH;
    private static int[] bI;
    private static int[] bJ;
    private static int[] bK;
    private static String[] bL;
    private static int bM;
    private static int bN;
    private static String[][] bO;
    private static int[] bP;
    private static int[] bQ;
    static int w;
    static q x;
    static boolean y;
    static boolean z;
    static int A;
    static int B;
    private static boolean bR;
    static boolean[] C;
    static boolean[] D;
    static int E;
    static int F;
    static int G;
    static int H;
    private static int bS;
    public static boolean I;
    public static boolean J;
    private static int bT;
    private static int bU;
    private static int bV;
    private static int bW;
    private static int bX;
    private static int bY;
    private static int bZ;
    private static int ca;
    public static int K;
    private static boolean cb;
    public static int[] L;
    public static int[] M;
    public static int N;
    public static int O;
    public static int P;
    public static int Q;
    public static int R;
    public static int S;
    public static int T;
    public static String U;
    public static String V;
    private static int cc;
    private static int cd;
    private static int ce;
    private static int cf;
    private static int cg;
    private static int ch;
    private static int ci;
    private static int cj;
    private static int ck;
    private static int cl;
    private static int cm;
    private static int cn;
    private static int co;
    private static int cp;
    private static int cq;
    private static int cr;
    private static String cs;
    public static int W;
    public static int X;
    private static int ct;
    private static int cu;
    public static int Y;
    public static int Z;
    public static int aa;
    public static int ab;
    public static int ac;
    public static int ad;
    public static int ae;
    public static int af;
    public static int ag;
    private static int cv;
    private static int[] cw;
    private static int[] cx;
    private static int[] cy;
    private static int[] cz;
    private static int[] cA;
    private static int[] cB;
    private static int cC;
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
    private static int[] cP;
    private static int[] cQ;
    private static int[] cR;
    private static int[] cS;
    private static int[] cT;
    private static int[] cU;
    private static boolean cV;
    private static int cW;
    private static String cX;
    private static int cY;
    private static int[] cZ;
    private static int[] da;
    private static int[] db;
    private static int[] dc;
    private static int[] dd;
    private static int[] de;
    private static int[] df;
    private static int[] dg;
    private static int dh;
    private static int[] di;
    private static int[] dj;
    public static int[] ah;
    private static int[] dk;
    public static int[] ai;
    private static int[] dl;
    private static boolean[] dm;
    public static int aj;
    private static int dn;
    private static int[] do;
    private static int[] dp;
    private static int[] dq;
    private static int[] dr;
    private static int[] ds;
    private static int[] dt;
    private static int[] du;
    private static int[] dv;
    private static String[] dw;
    private static int[] dx;
    private static int[] dy;
    private static int[] dz;
    private static int[] dA;
    private static int[] dB;
    private static int[] dC;
    private static int dD;
    private static int dE;
    private static int dF;
    public static int ak;
    public static int al;
    public static int am;
    public static int an;
    private static int dG;
    private static int dH;
    private static int dI;
    private static Image[][] dJ;
    private static Image[] dK;
    private static Image dL;
    public static Image[] ao;
    public static int[] ap;
    private static Image[] dM;
    private static Image dN;
    private static Image[] dO;
    private static Image dP;
    public static Image aq;
    public static Image ar;
    private static Image[] dQ;
    private static Image[] dR;
    private static Image dS;
    private static Image[][] dT;
    private static Image[] dU;
    private static Image[] dV;
    private static Image[] dW;
    private static Image[] dX;
    private static Image[] dY;
    private static Image[] dZ;
    private static Image ea;
    private static Image[] eb;
    private static Image[] ec;
    public static int as;
    public static int at;
    public static boolean au;
    private static int ed;
    private static int ee;
    public static int av;
    public static int aw;
    public static int ax;
    private static int ef;
    private static int eg;
    private static int eh;
    private static int ei;
    public static int ay;
    public static int az;
    private static int ej;
    private static int ek;
    public static boolean aA;
    private static int el;
    public static int aB;
    public static int aC;
    public static int aD;
    private static boolean em;
    private static String[] en;
    private static int eo;
    private static int ep;
    private static int eq;
    private static boolean er;
    public static int aE;
    private static int es;
    private static int et;
    private static int eu;
    private static int ev;
    private static int ew;
    private static int ex;
    private static int ey;
    public static int aF;
    private static int ez;
    private static long eA;
    public static boolean aG;
    private static int eB;
    public static int aH;
    public static int aI;
    private static int eC;
    private static int eD;
    private static int eE;
    private static int eF;
    private static int eG;
    public static int aJ;
    private static int eH;
    private static int eI;
    public static int aK;
    public static int aL;
    private static int eJ;
    public static int aM;
    public static boolean aN;
    public static int aO;
    public static int aP;
    public static int aQ;
    public static int[] aR;
    public static int[] aS;
    public static int[] aT;
    public static int[] aU;
    public static int[] aV;
    private static int[] eK;
    public static int aW;
    public static int aX;
    public static int[] aY;
    public static int[] aZ;
    public static int[] ba;
    public static int[] bb;
    public static int[] bc;
    private static int[] eL;
    private static int[] eM;
    public static boolean bd;
    private static int eN;
    private static int eO;
    private static int eP;
    private static int eQ;
    private static String[][] eR;
    private static int eS;
    private static int eT;
    private static int eU;
    private static int eV;
    private static int eW;
    private static int eX;
    public static int[] be;
    public static int[] bf;
    public static String[] bg;
    public static int bh;
    public static int bi;
    private static Random eY;
    private static int[] eZ;
    private static boolean fa;
    public static String[] bj;
    static int bk;
    private static int fb;
    static int bl;
    private static Image fc;
    private static int fd;
    private static int fe;
    private static int[] ff;
    static int bm;
    private static int fg;
    static int bn;
    private static int fh;
    static int bo;
    static int bp;
    private static int[] fi;
    static String[][] bq;
    private static int[] fj;
    private static String[][] fk;
    private static int[][] fl;
    private static Image[] fm;
    private static int fn;
    private static int fo;
    private static int fp;
    private static int fq;
    private static int[] fr;
    private static String[] fs;
    static int[] br;
    private static int[] ft;
    private static Image[] fu;
    public static int[] bs;
    private static int fv;
    private static Image[] fw;
    private static int fx;
    private static int fy;
    private static int fz;
    private static String[] fA;
    private static String[] fB;
    private static int[] fC;
    public static boolean[] bt;
    private static int[] fD;
    private static int[] fE;
    private static int[] fF;
    private static int[] fG;
    private static Image fH;
    private static Image fI;
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
    private static int fV;
    private static int fW;
    private static int fX;
    private static String fY;
    private static Image[] fZ;
    private static boolean ga;
    private static int gb;
    public static int bu;
    public static int bv;
    private static int gc;
    private static int gd;
    private static int ge;
    private static String gf;
    private static String gg;
    private static String[] gh;
    public static String[] bw;
    public static String[] bx;
    private static int gi;

    static {
        int[][] nArrayArray = new int[38][];
        nArrayArray[0] = new int[2];
        int[] nArray = new int[3];
        nArray[0] = 1;
        nArray[1] = 2;
        nArrayArray[1] = nArray;
        int[] nArray2 = new int[3];
        nArray2[0] = 3;
        nArray2[1] = 4;
        nArrayArray[2] = nArray2;
        int[] nArray3 = new int[4];
        nArray3[0] = 5;
        nArray3[1] = 6;
        nArray3[2] = 7;
        nArrayArray[3] = nArray3;
        int[] nArray4 = new int[2];
        nArray4[0] = 8;
        nArrayArray[4] = nArray4;
        int[] nArray5 = new int[2];
        nArray5[0] = 9;
        nArrayArray[5] = nArray5;
        int[] nArray6 = new int[3];
        nArray6[0] = 10;
        nArray6[1] = 11;
        nArrayArray[6] = nArray6;
        int[] nArray7 = new int[3];
        nArray7[0] = 12;
        nArray7[1] = 13;
        nArrayArray[7] = nArray7;
        int[] nArray8 = new int[2];
        nArray8[0] = 14;
        nArrayArray[8] = nArray8;
        int[] nArray9 = new int[2];
        nArray9[0] = 15;
        nArrayArray[9] = nArray9;
        int[] nArray10 = new int[2];
        nArray10[0] = 16;
        nArrayArray[10] = nArray10;
        int[] nArray11 = new int[3];
        nArray11[0] = 17;
        nArray11[1] = 18;
        nArrayArray[11] = nArray11;
        int[] nArray12 = new int[2];
        nArray12[0] = 20;
        nArrayArray[12] = nArray12;
        int[] nArray13 = new int[2];
        nArray13[0] = 21;
        nArrayArray[13] = nArray13;
        int[] nArray14 = new int[2];
        nArray14[0] = 22;
        nArrayArray[14] = nArray14;
        int[] nArray15 = new int[2];
        nArray15[0] = 23;
        nArrayArray[15] = nArray15;
        int[] nArray16 = new int[2];
        nArray16[0] = 26;
        nArrayArray[16] = nArray16;
        int[] nArray17 = new int[2];
        nArray17[0] = 27;
        nArrayArray[17] = nArray17;
        int[] nArray18 = new int[2];
        nArray18[0] = 28;
        nArrayArray[18] = nArray18;
        int[] nArray19 = new int[2];
        nArray19[0] = 29;
        nArrayArray[19] = nArray19;
        int[] nArray20 = new int[2];
        nArray20[0] = 31;
        nArrayArray[20] = nArray20;
        int[] nArray21 = new int[2];
        nArray21[0] = 32;
        nArrayArray[21] = nArray21;
        int[] nArray22 = new int[2];
        nArray22[0] = 34;
        nArrayArray[22] = nArray22;
        int[] nArray23 = new int[2];
        nArray23[0] = 35;
        nArrayArray[23] = nArray23;
        int[] nArray24 = new int[2];
        nArray24[0] = 36;
        nArrayArray[24] = nArray24;
        int[] nArray25 = new int[2];
        nArray25[0] = 38;
        nArrayArray[25] = nArray25;
        int[] nArray26 = new int[2];
        nArray26[0] = 39;
        nArrayArray[26] = nArray26;
        int[] nArray27 = new int[2];
        nArray27[0] = 40;
        nArrayArray[27] = nArray27;
        int[] nArray28 = new int[2];
        nArray28[0] = 41;
        nArrayArray[28] = nArray28;
        int[] nArray29 = new int[2];
        nArray29[0] = 42;
        nArrayArray[29] = nArray29;
        int[] nArray30 = new int[2];
        nArray30[0] = 44;
        nArrayArray[30] = nArray30;
        int[] nArray31 = new int[2];
        nArray31[0] = 45;
        nArrayArray[31] = nArray31;
        int[] nArray32 = new int[2];
        nArray32[0] = 46;
        nArrayArray[32] = nArray32;
        int[] nArray33 = new int[2];
        nArray33[0] = 48;
        nArrayArray[33] = nArray33;
        int[] nArray34 = new int[2];
        nArray34[0] = 50;
        nArrayArray[34] = nArray34;
        int[] nArray35 = new int[2];
        nArray35[0] = 53;
        nArrayArray[35] = nArray35;
        int[] nArray36 = new int[2];
        nArray36[0] = 54;
        nArrayArray[36] = nArray36;
        int[] nArray37 = new int[2];
        nArray37[0] = 55;
        nArrayArray[37] = nArray37;
        v = nArrayArray;
        int[][] nArrayArray2 = new int[38][];
        nArrayArray2[0] = new int[2];
        int[] nArray38 = new int[3];
        nArray38[1] = 624;
        nArrayArray2[1] = nArray38;
        int[] nArray39 = new int[3];
        nArray39[1] = 168;
        nArrayArray2[2] = nArray39;
        int[] nArray40 = new int[4];
        nArray40[1] = 936;
        nArrayArray2[3] = nArray40;
        nArrayArray2[4] = new int[2];
        nArrayArray2[5] = new int[2];
        int[] nArray41 = new int[3];
        nArray41[1] = 768;
        nArrayArray2[6] = nArray41;
        int[] nArray42 = new int[3];
        nArray42[1] = 528;
        nArrayArray2[7] = nArray42;
        int[] nArray43 = new int[2];
        nArray43[0] = 528;
        nArrayArray2[8] = nArray43;
        int[] nArray44 = new int[2];
        nArray44[0] = 528;
        nArrayArray2[9] = nArray44;
        int[] nArray45 = new int[2];
        nArray45[0] = 1392;
        nArrayArray2[10] = nArray45;
        int[] nArray46 = new int[3];
        nArray46[1] = 2688;
        nArrayArray2[11] = nArray46;
        int[] nArray47 = new int[2];
        nArray47[0] = 600;
        nArrayArray2[12] = nArray47;
        int[] nArray48 = new int[2];
        nArray48[0] = 1344;
        nArrayArray2[13] = nArray48;
        nArrayArray2[14] = new int[2];
        int[] nArray49 = new int[2];
        nArray49[0] = 600;
        nArrayArray2[15] = nArray49;
        int[] nArray50 = new int[2];
        nArray50[0] = 2016;
        nArrayArray2[16] = nArray50;
        int[] nArray51 = new int[2];
        nArray51[0] = 1224;
        nArrayArray2[17] = nArray51;
        nArrayArray2[18] = new int[]{816};
        int[] nArray52 = new int[2];
        nArray52[0] = 2424;
        nArrayArray2[19] = nArray52;
        int[] nArray53 = new int[2];
        nArray53[0] = 72;
        nArrayArray2[20] = nArray53;
        int[] nArray54 = new int[2];
        nArray54[0] = 2184;
        nArrayArray2[21] = nArray54;
        int[] nArray55 = new int[2];
        nArray55[0] = 120;
        nArrayArray2[22] = nArray55;
        int[] nArray56 = new int[2];
        nArray56[0] = 2568;
        nArrayArray2[23] = nArray56;
        int[] nArray57 = new int[2];
        nArray57[0] = 2640;
        nArrayArray2[24] = nArray57;
        int[] nArray58 = new int[2];
        nArray58[0] = 1656;
        nArrayArray2[25] = nArray58;
        int[] nArray59 = new int[2];
        nArray59[0] = 456;
        nArrayArray2[26] = nArray59;
        int[] nArray60 = new int[2];
        nArray60[0] = 1728;
        nArrayArray2[27] = nArray60;
        int[] nArray61 = new int[2];
        nArray61[0] = 1128;
        nArrayArray2[28] = nArray61;
        int[] nArray62 = new int[2];
        nArray62[0] = 1680;
        nArrayArray2[29] = nArray62;
        nArrayArray2[30] = new int[2];
        int[] nArray63 = new int[2];
        nArray63[0] = 1680;
        nArrayArray2[31] = nArray63;
        int[] nArray64 = new int[2];
        nArray64[0] = 432;
        nArrayArray2[32] = nArray64;
        int[] nArray65 = new int[2];
        nArray65[0] = 2424;
        nArrayArray2[33] = nArray65;
        int[] nArray66 = new int[2];
        nArray66[0] = 2640;
        nArrayArray2[34] = nArray66;
        int[] nArray67 = new int[2];
        nArray67[0] = 432;
        nArrayArray2[35] = nArray67;
        int[] nArray68 = new int[2];
        nArray68[0] = 3504;
        nArrayArray2[36] = nArray68;
        int[] nArray69 = new int[2];
        nArray69[0] = 600;
        nArrayArray2[37] = nArray69;
        bB = nArrayArray2;
        bC = new int[][]{{8, 9, 15, 14, 10, 4, 4, 4, 24, 4, 4, 28, 25, 4, 4, 4, 22, 4, 4, 4, 4, 4, 4, 16, 17, 3}, {9, 15, 34, 10, 7, 1, 1, 2, 2, 4, 15, 3}, {21, 8, 16, 4, 4, 17, 3}, {4, 4, 4, 4, 4, 4, 7, 3}, {4, 17, 3}, {8, 9, 15, 34, 1, 1, 10, 2, 2, 7, 4, 3}, {8, 11, 4, 4, 4, 4, 4, 4, 7, 27, 3}, {8, 30, 11, 4, 17, 3}, {10, 4, 4, 36, 4, 4, 4, 4, 4, 35, 36, 37, 4, 4, 17, 3}, {4, 36, 32, 4, 4, 4, 4, 4, 4, 20, 1, 38, 2, 4, 7, 17, 3}, {4, 39, 5, 3}, {6, 4, 4, 4, 40, 3}, {4, 3}, {4, 4, 4, 4, 4, 4, 4, 41, 3}, {4, 3}, {4, 4, 42, 3}, {4, 4, 4, 17, 3}, {4, 43, 3}, {45, 44, 4, 7, 17, 3}, {45, 44, 4, 7, 17, 3}, {4, 4, 1, 2, 3}, {4, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 29, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 3}, {4, 4, 46, 4, 4, 47, 3}, {8, 4, 4, 4, 4, 4, 7, 3}, {4, 3}, {48, 4, 4, 4, 4, 35, 36, 15, 3}, {4, 4, 4, 3}, {4, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3}, {4, 4, 49, 4, 4, 3}, {4, 3}, {4, 4, 4, 3}, {8, 11, 7, 4, 4, 4, 4, 4, 4, 4, 4, 3}, {4, 4, 4, 4, 4, 4, 4, 3}, {4, 3}, {4, 4, 4, 4, 3}, {4, 4, 4, 4, 4, 4, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 4, 3}, {4, 50, 4, 4, 4, 4, 4, 4, 3}, {4, 51, 4, 4, 4, 4, 4, 4, 4, 4, 52, 4, 3}, {4, 4, 4, 3}, {4, 4, 35, 36, 17, 3}, {15, 4, 4, 4, 4, 3}, {4, 4, 4, 4, 4, 4, 4, 4, 35, 53, 3}, {54, 3}, {4, 3}, {4, 3}, {4, 4, 4, 4, 3}, {4, 3}, {4, 51, 4, 4, 4, 4, 4, 4, 52, 55, 3}, {4, 51, 4, 3}, {4, 3}, {4, 3}, {48, 4, 4, 4, 4, 35, 36, 15, 3}};
        int[][] nArrayArray3 = new int[56][];
        int[] nArray70 = new int[27];
        nArray70[3] = 1;
        nArray70[4] = 2;
        nArray70[6] = 1;
        nArray70[7] = 2;
        nArray70[9] = 3;
        nArray70[10] = 4;
        nArray70[13] = 5;
        nArray70[14] = 6;
        nArray70[15] = 7;
        nArray70[16] = 864;
        nArray70[17] = 8;
        nArray70[18] = 9;
        nArray70[19] = 10;
        nArray70[20] = 11;
        nArray70[21] = 12;
        nArray70[22] = 13;
        nArray70[23] = 4;
        nArray70[24] = 105;
        nArrayArray3[0] = nArray70;
        int[] nArray71 = new int[12];
        nArray71[0] = 3;
        nArray71[1] = 1;
        nArray71[2] = 5;
        nArray71[3] = 4;
        nArray71[5] = -4;
        nArray71[6] = -1;
        nArray71[7] = -4;
        nArray71[8] = -1;
        nArray71[9] = 14;
        nArray71[10] = 2;
        nArrayArray3[1] = nArray71;
        int[] nArray72 = new int[7];
        nArray72[0] = 528;
        nArray72[3] = 15;
        nArray72[4] = 16;
        nArrayArray3[2] = nArray72;
        int[] nArray73 = new int[8];
        nArray73[0] = 17;
        nArray73[1] = 18;
        nArray73[2] = 19;
        nArray73[3] = 20;
        nArray73[4] = 21;
        nArray73[5] = 22;
        nArrayArray3[3] = nArray73;
        int[] nArray74 = new int[3];
        nArray74[0] = 23;
        nArray74[1] = 106;
        nArrayArray3[4] = nArray74;
        int[] nArray75 = new int[12];
        nArray75[1] = 6;
        nArray75[2] = 3;
        nArray75[3] = 7;
        nArray75[4] = -4;
        nArray75[5] = -1;
        nArray75[6] = 8;
        nArray75[7] = -4;
        nArray75[8] = -1;
        nArray75[10] = 24;
        nArrayArray3[5] = nArray75;
        int[] nArray76 = new int[11];
        nArray76[2] = 25;
        nArray76[3] = 26;
        nArray76[4] = 27;
        nArray76[5] = 28;
        nArray76[6] = 29;
        nArray76[7] = 30;
        nArrayArray3[6] = nArray76;
        int[] nArray77 = new int[6];
        nArray77[3] = 31;
        nArrayArray3[7] = nArray77;
        int[] nArray78 = new int[16];
        nArray78[0] = 9;
        nArray78[1] = 32;
        nArray78[2] = 33;
        nArray78[4] = 34;
        nArray78[5] = 35;
        nArray78[6] = 36;
        nArray78[7] = 37;
        nArray78[8] = 38;
        nArray78[12] = 39;
        nArray78[13] = 40;
        nArray78[14] = 107;
        nArrayArray3[8] = nArray78;
        int[] nArray79 = new int[17];
        nArray79[0] = 41;
        nArray79[2] = 312;
        nArray79[3] = 42;
        nArray79[4] = 43;
        nArray79[5] = 44;
        nArray79[6] = 45;
        nArray79[7] = 46;
        nArray79[8] = 47;
        nArray79[10] = -3;
        nArray79[11] = -1;
        nArray79[12] = -3;
        nArray79[13] = 48;
        nArrayArray3[9] = nArray79;
        int[] nArray80 = new int[4];
        nArray80[0] = 49;
        nArray80[2] = 1056;
        nArrayArray3[10] = nArray80;
        int[] nArray81 = new int[6];
        nArray81[1] = 50;
        nArray81[2] = 51;
        nArray81[3] = 52;
        nArrayArray3[11] = nArray81;
        int[] nArray82 = new int[2];
        nArray82[0] = 53;
        nArrayArray3[12] = nArray82;
        int[] nArray83 = new int[9];
        nArray83[0] = 54;
        nArray83[1] = 55;
        nArray83[2] = 56;
        nArray83[3] = 57;
        nArray83[4] = 58;
        nArray83[5] = 59;
        nArray83[6] = 60;
        nArrayArray3[13] = nArray83;
        int[] nArray84 = new int[2];
        nArray84[0] = 61;
        nArrayArray3[14] = nArray84;
        int[] nArray85 = new int[4];
        nArray85[0] = 62;
        nArray85[1] = 63;
        nArrayArray3[15] = nArray85;
        int[] nArray86 = new int[5];
        nArray86[0] = 64;
        nArray86[1] = 65;
        nArray86[2] = 66;
        nArray86[3] = 108;
        nArrayArray3[16] = nArray86;
        int[] nArray87 = new int[3];
        nArray87[0] = 67;
        nArrayArray3[17] = nArray87;
        int[] nArray88 = new int[6];
        nArray88[2] = 68;
        nArray88[4] = 3;
        nArrayArray3[18] = nArray88;
        int[] nArray89 = new int[6];
        nArray89[2] = 69;
        nArray89[4] = 3;
        nArrayArray3[19] = nArray89;
        int[] nArray90 = new int[5];
        nArray90[0] = 70;
        nArray90[1] = 71;
        nArray90[2] = -4;
        nArray90[3] = -4;
        nArrayArray3[20] = nArray90;
        int[] nArray91 = new int[2];
        nArray91[0] = 72;
        nArrayArray3[21] = nArray91;
        int[] nArray92 = new int[13];
        nArray92[0] = 73;
        nArray92[1] = 74;
        nArray92[2] = 75;
        nArray92[3] = 76;
        nArray92[4] = 77;
        nArray92[5] = 78;
        nArray92[6] = 192;
        nArray92[7] = 193;
        nArray92[8] = 194;
        nArray92[9] = 195;
        nArray92[10] = 196;
        nArrayArray3[22] = nArray92;
        nArrayArray3[23] = new int[]{79, 80, 81, 82, 83, 84, 85, 86};
        nArrayArray3[24] = new int[]{87, 88, -1, 89, 90};
        nArrayArray3[25] = new int[]{-1, 91, 92, 93, 94, 95, -1, -1};
        nArrayArray3[26] = new int[]{96};
        nArrayArray3[27] = new int[]{1152, 97, 98, 99, 100, -1, -1, 4};
        nArrayArray3[28] = new int[]{101, 102, 103};
        nArrayArray3[29] = new int[]{104};
        nArrayArray3[30] = new int[]{105, 106, 107, 108, 109, 110, 111, 112, 113, 114};
        nArrayArray3[31] = new int[]{115, 116, -1, 117, 118, -1};
        nArrayArray3[32] = new int[]{119, -1};
        nArrayArray3[33] = new int[]{120, 121, 122};
        nArrayArray3[34] = new int[]{-1, -1, -1, 123, 124, 125, 126, 127, 128, 129, 130, -1};
        nArrayArray3[35] = new int[]{131, 132, 133, 134, 135, 136, 137, -1};
        nArrayArray3[36] = new int[]{138, -1};
        nArrayArray3[37] = new int[]{139, 140, 141, 142, -1};
        nArrayArray3[38] = new int[]{143, 144, 145, 146, 147, 148, -1};
        nArrayArray3[39] = new int[]{149, 150, 151, 152, 153, 154, 155, 156, 157, -1};
        nArrayArray3[40] = new int[]{158, -1, 159, 160, 161, 162, 163, 164, -1};
        nArrayArray3[41] = new int[]{165, -1, 166, 167, 168, 169, 170, 171, 172, 173, -1, 174, -1};
        nArrayArray3[42] = new int[]{175, 176, 177};
        nArrayArray3[43] = new int[]{178, 179, -1, -1, 9, -1};
        nArrayArray3[44] = new int[]{5, 180, 181, 182, 183, -1};
        nArrayArray3[45] = new int[]{184, 185, 186, 187, 188, 189, 190, 191, -1, -1, -1, -1};
        nArrayArray3[46] = new int[]{-1, -1};
        nArrayArray3[47] = new int[]{197, -1};
        nArrayArray3[48] = new int[]{198, -1};
        nArrayArray3[49] = new int[]{139, 140, 141, 199, -1};
        nArrayArray3[50] = new int[]{200, -1};
        nArrayArray3[51] = new int[]{201, -1, 202, 203, 204, 205, 206, 207, -1, -1, -1};
        nArrayArray3[52] = new int[]{208, -1, 209};
        nArrayArray3[53] = new int[]{210, -1};
        nArrayArray3[54] = new int[]{211, -1};
        nArrayArray3[55] = new int[]{648, 97, 98, 99, 100, -1, -1, 4};
        bE = nArrayArray3;
        int[][] nArrayArray4 = new int[56][];
        nArrayArray4[0] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 39, 40, 41, 42, 43, 44, 45, 50, 51, 52, 53, 54, 55, 56, 57, 75, 77, 78};
        int[] nArray93 = new int[12];
        nArray93[1] = 1;
        nArray93[2] = 4;
        nArray93[3] = 5;
        nArray93[4] = 14;
        nArray93[5] = 15;
        nArray93[6] = 16;
        nArray93[7] = 32;
        nArray93[8] = 33;
        nArray93[9] = 55;
        nArray93[10] = 56;
        nArray93[11] = 100;
        nArrayArray4[1] = nArray93;
        nArrayArray4[2] = new int[]{1, 2, 3, 80, 80, 81, 92};
        nArrayArray4[3] = new int[]{20, 21, 30, 31, 32, 33, 40, 50};
        nArrayArray4[4] = new int[]{1, 10, 11};
        int[] nArray94 = new int[12];
        nArray94[1] = 1;
        nArray94[2] = 2;
        nArray94[3] = 3;
        nArray94[4] = 4;
        nArray94[5] = 5;
        nArray94[6] = 6;
        nArray94[7] = 26;
        nArray94[8] = 27;
        nArray94[9] = 28;
        nArray94[10] = 29;
        nArray94[11] = 30;
        nArrayArray4[5] = nArray94;
        int[] nArray95 = new int[11];
        nArray95[1] = 1;
        nArray95[2] = 10;
        nArray95[3] = 11;
        nArray95[4] = 12;
        nArray95[5] = 13;
        nArray95[6] = 14;
        nArray95[7] = 15;
        nArray95[8] = 16;
        nArray95[9] = 17;
        nArray95[10] = 18;
        nArrayArray4[6] = nArray95;
        nArrayArray4[7] = new int[]{1, 2, 3, 20, 21, 22};
        int[] nArray96 = new int[16];
        nArray96[1] = 1;
        nArray96[2] = 2;
        nArray96[3] = 23;
        nArray96[4] = 34;
        nArray96[5] = 35;
        nArray96[6] = 36;
        nArray96[7] = 37;
        nArray96[8] = 38;
        nArray96[9] = 39;
        nArray96[10] = 69;
        nArray96[11] = 70;
        nArray96[12] = 75;
        nArray96[13] = 85;
        nArray96[14] = 86;
        nArray96[15] = 87;
        nArrayArray4[8] = nArray96;
        int[] nArray97 = new int[17];
        nArray97[1] = 10;
        nArray97[2] = 11;
        nArray97[3] = 70;
        nArray97[4] = 72;
        nArray97[5] = 73;
        nArray97[6] = 74;
        nArray97[7] = 75;
        nArray97[8] = 76;
        nArray97[9] = 77;
        nArray97[10] = 78;
        nArray97[11] = 94;
        nArray97[12] = 127;
        nArray97[13] = 130;
        nArray97[14] = 131;
        nArray97[15] = 132;
        nArray97[16] = 133;
        nArrayArray4[9] = nArray97;
        int[] nArray98 = new int[4];
        nArray98[1] = 1;
        nArray98[2] = 2;
        nArray98[3] = 3;
        nArrayArray4[10] = nArray98;
        int[] nArray99 = new int[6];
        nArray99[1] = 1;
        nArray99[2] = 2;
        nArray99[3] = 3;
        nArray99[4] = 4;
        nArray99[5] = 5;
        nArrayArray4[11] = nArray99;
        nArrayArray4[12] = new int[]{10, 11};
        nArrayArray4[13] = new int[]{10, 11, 12, 13, 14, 15, 16, 17, 18};
        nArrayArray4[14] = new int[]{10, 11};
        nArrayArray4[15] = new int[]{10, 11, 12, 13};
        nArrayArray4[16] = new int[]{10, 11, 20, 25, 26};
        nArrayArray4[17] = new int[]{10, 11, 12};
        int[] nArray100 = new int[6];
        nArray100[1] = 1;
        nArray100[2] = 2;
        nArray100[3] = 3;
        nArray100[4] = 4;
        nArray100[5] = 5;
        nArrayArray4[18] = nArray100;
        int[] nArray101 = new int[6];
        nArray101[1] = 1;
        nArray101[2] = 2;
        nArray101[3] = 3;
        nArray101[4] = 4;
        nArray101[5] = 5;
        nArrayArray4[19] = nArray101;
        int[] nArray102 = new int[5];
        nArray102[1] = 1;
        nArray102[2] = 2;
        nArray102[3] = 12;
        nArrayArray4[20] = nArray102;
        int[] nArray103 = new int[2];
        nArray103[1] = 1;
        nArrayArray4[21] = nArray103;
        nArrayArray4[22] = new int[]{10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
        int[] nArray104 = new int[9];
        nArray104[0] = 18;
        nArray104[1] = 19;
        nArray104[2] = 20;
        nArray104[3] = 21;
        nArray104[4] = 22;
        nArray104[5] = 23;
        nArray104[6] = 24;
        nArray104[7] = 25;
        nArrayArray4[23] = nArray104;
        nArrayArray4[24] = new int[]{18, 28, 30, 32, 34, 36, 38, 45};
        nArrayArray4[25] = new int[]{18, 19, 20, 21, 22, 23, 24, 25};
        nArrayArray4[26] = new int[]{20, 21};
        int[] nArray105 = new int[12];
        nArray105[2] = 5;
        nArray105[5] = 10;
        nArray105[6] = 30;
        nArray105[8] = 30;
        nArrayArray4[27] = nArray105;
        int[] nArray106 = new int[6];
        nArray106[0] = 5;
        nArray106[1] = 10;
        nArray106[2] = 15;
        nArrayArray4[28] = nArray106;
        int[] nArray107 = new int[2];
        nArray107[1] = 5;
        nArrayArray4[29] = nArray107;
        int[] nArray108 = new int[11];
        nArray108[1] = 5;
        nArray108[2] = 10;
        nArray108[3] = 15;
        nArray108[4] = 20;
        nArray108[6] = 20;
        nArray108[8] = 15;
        nArray108[10] = 15;
        nArrayArray4[30] = nArray108;
        int[] nArray109 = new int[6];
        nArray109[1] = 5;
        nArray109[3] = 5;
        nArray109[5] = 5;
        nArrayArray4[31] = nArray109;
        int[] nArray110 = new int[2];
        nArray110[0] = 5;
        nArrayArray4[32] = nArray110;
        int[] nArray111 = new int[4];
        nArray111[1] = 5;
        nArray111[2] = 10;
        nArrayArray4[33] = nArray111;
        int[] nArray112 = new int[12];
        nArray112[0] = 15;
        nArray112[2] = 50;
        nArray112[3] = 80;
        nArray112[9] = 15;
        nArray112[11] = 5;
        nArrayArray4[34] = nArray112;
        int[] nArray113 = new int[8];
        nArray113[1] = 5;
        nArray113[3] = 10;
        nArray113[5] = 5;
        nArray113[6] = 15;
        nArray113[7] = 20;
        nArrayArray4[35] = nArray113;
        int[] nArray114 = new int[2];
        nArray114[1] = 3;
        nArrayArray4[36] = nArray114;
        nArrayArray4[37] = new int[]{5, 10, 15, 20, 25};
        int[] nArray115 = new int[7];
        nArray115[0] = 20;
        nArray115[1] = 25;
        nArray115[4] = 5;
        nArray115[5] = 10;
        nArray115[6] = 15;
        nArrayArray4[38] = nArray115;
        int[] nArray116 = new int[10];
        nArray116[0] = 20;
        nArray116[1] = 25;
        nArray116[2] = 5;
        nArray116[3] = 10;
        nArray116[4] = 15;
        nArray116[6] = 5;
        nArray116[7] = 10;
        nArray116[8] = 20;
        nArray116[9] = 25;
        nArrayArray4[39] = nArray116;
        int[] nArray117 = new int[10];
        nArray117[1] = 5;
        nArray117[2] = 15;
        nArray117[4] = 5;
        nArray117[5] = 5;
        nArray117[6] = 5;
        nArray117[7] = 5;
        nArray117[8] = 5;
        nArray117[9] = 5;
        nArrayArray4[40] = nArray117;
        int[] nArray118 = new int[13];
        nArray118[0] = 10;
        nArray118[1] = 15;
        nArray118[2] = 25;
        nArray118[3] = 35;
        nArray118[4] = 40;
        nArray118[5] = 45;
        nArray118[6] = 50;
        nArray118[7] = 70;
        nArray118[8] = 75;
        nArray118[9] = 80;
        nArray118[10] = 85;
        nArray118[11] = 90;
        nArrayArray4[41] = nArray118;
        nArrayArray4[42] = new int[]{5, 10, 15, 20, 25};
        nArrayArray4[43] = new int[]{10, 15, 20, 35, 45, 50};
        int[] nArray119 = new int[6];
        nArray119[1] = 25;
        nArray119[2] = 30;
        nArray119[3] = 35;
        nArray119[4] = 40;
        nArray119[5] = 45;
        nArrayArray4[44] = nArray119;
        nArrayArray4[45] = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
        int[] nArray120 = new int[2];
        nArray120[1] = 5;
        nArrayArray4[46] = nArray120;
        nArrayArray4[47] = new int[]{20, 11};
        int[] nArray121 = new int[2];
        nArray121[1] = 4;
        nArrayArray4[48] = nArray121;
        nArrayArray4[49] = new int[]{5, 10, 15, 20, 25};
        int[] nArray122 = new int[2];
        nArray122[1] = 4;
        nArrayArray4[50] = nArray122;
        nArrayArray4[51] = new int[]{5, 10, 15, 20, 25, 30, 35, 35, 35, 35, 36};
        nArrayArray4[52] = new int[]{5, 10, 35, 40};
        nArrayArray4[53] = new int[]{1, 5};
        nArrayArray4[54] = new int[]{1, 5};
        int[] nArray123 = new int[12];
        nArray123[2] = 5;
        nArray123[5] = 10;
        nArray123[6] = 30;
        nArray123[8] = 30;
        nArrayArray4[55] = nArray123;
        bF = nArrayArray4;
        bG = new int[][]{{336, 240}, {600, 240}, {816, 288}, {552, 96}, {120, 144}, {360, 144}, {624, 144}, {408, 144}, {120, 192}, {432, 336}};
        bH = new int[][]{{2, 444, 408}};
        int[] nArray124 = new int[212];
        nArray124[0] = 1;
        nArray124[1] = 1;
        nArray124[2] = 1;
        nArray124[3] = 1;
        nArray124[4] = 1;
        nArray124[5] = 1;
        nArray124[6] = 1;
        nArray124[7] = 1;
        nArray124[8] = 1;
        nArray124[9] = 2;
        nArray124[10] = 1;
        nArray124[11] = 2;
        nArray124[12] = 1;
        nArray124[13] = 2;
        nArray124[15] = 2;
        nArray124[17] = 1;
        nArray124[19] = 1;
        nArray124[21] = 1;
        nArray124[25] = 4;
        nArray124[27] = 4;
        nArray124[28] = 5;
        nArray124[29] = 4;
        nArray124[31] = 5;
        nArray124[32] = 2;
        nArray124[33] = 1;
        nArray124[34] = 1;
        nArray124[35] = 1;
        nArray124[36] = 2;
        nArray124[37] = 1;
        nArray124[38] = 1;
        nArray124[39] = 1;
        nArray124[40] = 1;
        nArray124[42] = 3;
        nArray124[44] = 3;
        nArray124[46] = 3;
        nArray124[48] = 3;
        nArray124[49] = 2;
        nArray124[50] = 1;
        nArray124[52] = 1;
        nArray124[54] = 6;
        nArray124[56] = 6;
        nArray124[58] = 6;
        nArray124[60] = 6;
        nArray124[61] = 3;
        nArray124[63] = 3;
        nArray124[65] = 7;
        nArray124[67] = 7;
        nArray124[68] = 7;
        nArray124[69] = 7;
        nArray124[70] = 1;
        nArray124[73] = 1;
        nArray124[74] = 2;
        nArray124[75] = 1;
        nArray124[76] = 2;
        nArray124[77] = 1;
        nArray124[78] = 2;
        nArray124[79] = 1;
        nArray124[81] = 1;
        nArray124[83] = 1;
        nArray124[85] = 1;
        nArray124[87] = 3;
        nArray124[89] = 7;
        nArray124[92] = 7;
        nArray124[94] = 7;
        nArray124[97] = 7;
        nArray124[99] = 7;
        nArray124[102] = 7;
        nArray124[103] = 3;
        nArray124[104] = 1;
        nArray124[105] = 1;
        nArray124[107] = 1;
        nArray124[108] = 1;
        nArray124[109] = 1;
        nArray124[110] = 1;
        nArray124[111] = 1;
        nArray124[112] = 1;
        nArray124[114] = 1;
        nArray124[116] = 3;
        nArray124[117] = 7;
        nArray124[120] = 3;
        nArray124[122] = 3;
        nArray124[123] = 3;
        nArray124[125] = 3;
        nArray124[126] = 7;
        nArray124[128] = 7;
        nArray124[130] = 7;
        nArray124[131] = 1;
        nArray124[132] = 3;
        nArray124[133] = 1;
        nArray124[134] = 1;
        nArray124[136] = 1;
        nArray124[137] = 1;
        nArray124[138] = 1;
        nArray124[139] = 1;
        nArray124[140] = 3;
        nArray124[142] = 1;
        nArray124[144] = 3;
        nArray124[145] = 1;
        nArray124[147] = 1;
        nArray124[148] = 3;
        nArray124[151] = 1;
        nArray124[153] = 1;
        nArray124[154] = 1;
        nArray124[156] = 1;
        nArray124[157] = 1;
        nArray124[158] = 1;
        nArray124[159] = 3;
        nArray124[160] = 1;
        nArray124[162] = 1;
        nArray124[163] = 3;
        nArray124[164] = 1;
        nArray124[165] = 3;
        nArray124[167] = 3;
        nArray124[169] = 3;
        nArray124[172] = 3;
        nArray124[175] = 5;
        nArray124[177] = 5;
        nArray124[180] = 1;
        nArray124[181] = 1;
        nArray124[183] = 1;
        nArray124[185] = 1;
        nArray124[186] = 1;
        nArray124[187] = 3;
        nArray124[188] = 7;
        nArray124[190] = 1;
        nArray124[191] = 3;
        nArray124[192] = 1;
        nArray124[193] = 2;
        nArray124[194] = 1;
        nArray124[195] = 2;
        nArray124[196] = 1;
        nArray124[198] = 1;
        nArray124[199] = 1;
        nArray124[200] = 1;
        nArray124[201] = 7;
        nArray124[203] = 7;
        nArray124[205] = 3;
        nArray124[206] = 7;
        nArray124[208] = 3;
        bI = nArray124;
        int[] nArray125 = new int[212];
        nArray125[0] = 1;
        nArray125[2] = 1;
        nArray125[4] = 1;
        nArray125[5] = 1;
        nArray125[7] = 1;
        nArray125[9] = 4;
        nArray125[11] = 4;
        nArray125[12] = 1;
        nArray125[13] = 4;
        nArray125[21] = 1;
        nArray125[27] = 1;
        nArray125[32] = 4;
        nArray125[34] = 1;
        nArray125[36] = 1;
        nArray125[37] = 1;
        nArray125[39] = 1;
        nArray125[40] = 2;
        nArray125[76] = 1;
        nArray125[78] = 2;
        nArray125[87] = 2;
        nArray125[88] = -1;
        nArray125[89] = -1;
        nArray125[90] = -1;
        nArray125[91] = -1;
        nArray125[92] = -1;
        nArray125[93] = -1;
        nArray125[94] = -1;
        nArray125[95] = -1;
        nArray125[96] = -1;
        nArray125[97] = -1;
        nArray125[98] = -1;
        nArray125[99] = -1;
        nArray125[100] = -1;
        nArray125[101] = -1;
        nArray125[102] = -1;
        nArray125[103] = -1;
        nArray125[105] = 1;
        nArray125[106] = -1;
        nArray125[107] = 1;
        nArray125[108] = 1;
        nArray125[109] = 1;
        nArray125[110] = 1;
        nArray125[111] = 1;
        nArray125[112] = 1;
        nArray125[113] = -1;
        nArray125[114] = 1;
        nArray125[115] = -1;
        nArray125[116] = -1;
        nArray125[117] = -1;
        nArray125[118] = -1;
        nArray125[119] = -1;
        nArray125[120] = -1;
        nArray125[121] = -1;
        nArray125[122] = -1;
        nArray125[123] = -1;
        nArray125[124] = -1;
        nArray125[125] = -1;
        nArray125[126] = -1;
        nArray125[127] = -1;
        nArray125[128] = -1;
        nArray125[129] = -1;
        nArray125[130] = -1;
        nArray125[132] = -1;
        nArray125[135] = -1;
        nArray125[140] = -1;
        nArray125[141] = -1;
        nArray125[143] = -1;
        nArray125[144] = -1;
        nArray125[146] = -1;
        nArray125[148] = -1;
        nArray125[149] = -1;
        nArray125[150] = -1;
        nArray125[152] = -1;
        nArray125[155] = -1;
        nArray125[165] = -1;
        nArray125[166] = -1;
        nArray125[167] = -1;
        nArray125[168] = -1;
        nArray125[169] = -1;
        nArray125[170] = -1;
        nArray125[171] = -1;
        nArray125[172] = -1;
        nArray125[173] = -1;
        nArray125[174] = -1;
        nArray125[175] = -1;
        nArray125[176] = -1;
        nArray125[177] = -1;
        nArray125[178] = -1;
        nArray125[179] = -1;
        nArray125[180] = 2;
        nArray125[181] = 3;
        nArray125[182] = -1;
        nArray125[183] = 3;
        nArray125[184] = -1;
        nArray125[185] = 1;
        nArray125[186] = 1;
        nArray125[187] = -1;
        nArray125[188] = -1;
        nArray125[189] = -1;
        nArray125[190] = 1;
        nArray125[191] = -1;
        nArray125[195] = 1;
        nArray125[197] = -1;
        nArray125[201] = -1;
        nArray125[202] = -1;
        nArray125[203] = -1;
        nArray125[204] = -1;
        nArray125[205] = -1;
        nArray125[206] = -1;
        nArray125[207] = -1;
        nArray125[208] = -1;
        nArray125[209] = -1;
        nArray125[210] = -1;
        nArray125[211] = -1;
        bJ = nArray125;
        int[] nArray126 = new int[212];
        nArray126[1] = 1;
        nArray126[2] = 2;
        nArray126[3] = 3;
        nArray126[4] = 4;
        nArray126[5] = 5;
        nArray126[6] = 6;
        nArray126[7] = 7;
        nArray126[8] = 8;
        nArray126[9] = 9;
        nArray126[10] = 10;
        nArray126[11] = 11;
        nArray126[12] = 12;
        nArray126[13] = 13;
        nArray126[14] = 14;
        nArray126[15] = 15;
        nArray126[16] = 16;
        nArray126[17] = 17;
        nArray126[18] = 18;
        nArray126[19] = 19;
        nArray126[20] = 20;
        nArray126[21] = 21;
        nArray126[22] = 22;
        nArray126[23] = 23;
        nArray126[24] = 24;
        nArray126[25] = 25;
        nArray126[26] = 26;
        nArray126[27] = 27;
        nArray126[28] = 28;
        nArray126[29] = 29;
        nArray126[30] = 30;
        nArray126[31] = 31;
        nArray126[32] = 32;
        nArray126[33] = 33;
        nArray126[34] = 34;
        nArray126[35] = 35;
        nArray126[36] = 36;
        nArray126[37] = 37;
        nArray126[38] = 38;
        nArray126[39] = 39;
        nArray126[40] = 40;
        nArray126[41] = 41;
        nArray126[42] = 42;
        nArray126[43] = 43;
        nArray126[44] = 44;
        nArray126[45] = 45;
        nArray126[46] = 46;
        nArray126[47] = 47;
        nArray126[48] = 48;
        nArray126[49] = 49;
        nArray126[50] = 50;
        nArray126[51] = 51;
        nArray126[52] = 52;
        nArray126[53] = 53;
        nArray126[54] = 54;
        nArray126[55] = 55;
        nArray126[56] = 56;
        nArray126[57] = 57;
        nArray126[58] = 58;
        nArray126[59] = 59;
        nArray126[60] = 60;
        nArray126[61] = 61;
        nArray126[62] = 62;
        nArray126[63] = 63;
        nArray126[64] = 64;
        nArray126[65] = 65;
        nArray126[66] = 66;
        nArray126[67] = 67;
        nArray126[68] = 68;
        nArray126[69] = 69;
        nArray126[70] = 70;
        nArray126[71] = 71;
        nArray126[72] = 72;
        nArray126[73] = 73;
        nArray126[74] = 74;
        nArray126[75] = 75;
        nArray126[76] = 76;
        nArray126[77] = 77;
        nArray126[78] = 78;
        nArray126[79] = 79;
        nArray126[80] = 80;
        nArray126[81] = 81;
        nArray126[82] = 82;
        nArray126[83] = 83;
        nArray126[84] = 84;
        nArray126[85] = 85;
        nArray126[86] = 86;
        nArray126[87] = 87;
        nArray126[88] = 88;
        nArray126[89] = 89;
        nArray126[90] = 90;
        nArray126[91] = 91;
        nArray126[92] = 92;
        nArray126[93] = 93;
        nArray126[94] = 94;
        nArray126[95] = 95;
        nArray126[96] = 96;
        nArray126[97] = 97;
        nArray126[98] = 98;
        nArray126[99] = 99;
        nArray126[100] = 100;
        nArray126[101] = 101;
        nArray126[102] = 102;
        nArray126[103] = 103;
        nArray126[104] = 104;
        nArray126[105] = 105;
        nArray126[106] = 106;
        nArray126[107] = 107;
        nArray126[108] = 108;
        nArray126[109] = 109;
        nArray126[110] = 110;
        nArray126[111] = 111;
        nArray126[112] = 112;
        nArray126[113] = 113;
        nArray126[114] = 114;
        nArray126[115] = 115;
        nArray126[116] = 116;
        nArray126[117] = 117;
        nArray126[118] = 118;
        nArray126[119] = 119;
        nArray126[120] = 120;
        nArray126[121] = 121;
        nArray126[122] = 122;
        nArray126[123] = 123;
        nArray126[124] = 124;
        nArray126[125] = 125;
        nArray126[126] = 126;
        nArray126[127] = 127;
        nArray126[128] = 128;
        nArray126[129] = 129;
        nArray126[130] = 130;
        nArray126[131] = 131;
        nArray126[132] = 132;
        nArray126[133] = 133;
        nArray126[134] = 134;
        nArray126[135] = 135;
        nArray126[136] = 136;
        nArray126[137] = 137;
        nArray126[138] = 138;
        nArray126[139] = 139;
        nArray126[140] = 140;
        nArray126[141] = 141;
        nArray126[142] = 142;
        nArray126[143] = 143;
        nArray126[144] = 144;
        nArray126[145] = 145;
        nArray126[146] = 146;
        nArray126[147] = 147;
        nArray126[148] = 148;
        nArray126[149] = 149;
        nArray126[150] = 150;
        nArray126[151] = 151;
        nArray126[152] = 152;
        nArray126[153] = 153;
        nArray126[154] = 154;
        nArray126[155] = 155;
        nArray126[156] = 156;
        nArray126[157] = 157;
        nArray126[158] = 158;
        nArray126[159] = 159;
        nArray126[160] = 160;
        nArray126[161] = 161;
        nArray126[162] = 162;
        nArray126[163] = 163;
        nArray126[164] = 164;
        nArray126[165] = 165;
        nArray126[166] = 166;
        nArray126[167] = 167;
        nArray126[168] = 168;
        nArray126[169] = 169;
        nArray126[170] = 170;
        nArray126[171] = 171;
        nArray126[172] = 172;
        nArray126[173] = 173;
        nArray126[174] = 174;
        nArray126[175] = 175;
        nArray126[176] = 176;
        nArray126[177] = 177;
        nArray126[178] = 178;
        nArray126[179] = 179;
        nArray126[180] = 180;
        nArray126[181] = 181;
        nArray126[182] = 182;
        nArray126[183] = 183;
        nArray126[184] = 184;
        nArray126[185] = 185;
        nArray126[186] = 186;
        nArray126[187] = 187;
        nArray126[188] = 188;
        nArray126[189] = 189;
        nArray126[190] = 190;
        nArray126[191] = 191;
        nArray126[192] = 192;
        nArray126[193] = 193;
        nArray126[194] = 194;
        nArray126[195] = 195;
        nArray126[196] = 196;
        nArray126[197] = 197;
        nArray126[198] = 198;
        nArray126[199] = 199;
        nArray126[200] = 200;
        nArray126[201] = 201;
        nArray126[202] = 202;
        nArray126[203] = 203;
        nArray126[204] = 204;
        nArray126[205] = 205;
        nArray126[206] = 206;
        nArray126[207] = 207;
        nArray126[208] = 208;
        nArray126[209] = 209;
        nArray126[210] = 210;
        nArray126[211] = 211;
        bK = nArray126;
        bL = new String[]{"Ch\u01a1i m\u1edbi", "Ch\u01a1i ti\u1ebfp", "Ch\u1ec9 d\u1eabn", "C\u1eadp nh\u1eadt game", "Tho\u00e1t"};
        bO = new String[][]{{"Ch\u00fang ta \u0111i th\u00f4i!"}};
        bP = new int[]{-1, 2, 1, -2};
        bQ = new int[]{-3, 2, -1, 1};
        w = -1;
        y = true;
        C = new boolean[14];
        D = new boolean[14];
        bS = 0;
        cs = "";
        au = false;
        aA = false;
        en = new String[]{""};
        eq = 0;
        aE = -1;
        ex = 1;
        aM = 10;
        aQ = -1;
        aX = -1;
        bd = false;
        bf = new int[6];
        bg = new String[6];
        bh = 2;
        eY = new Random();
        eZ = new int[]{11, 12, 12, 11, 11, -1, -1, 12, -1, 11, 11, -1};
        fa = false;
        bj = new String[]{"x1xx", "x2xx", "x3xx", "x4xx", "x5xx", "x6xx", "x7xx"};
        bk = 0;
        fb = 0;
        bl = 0;
        int[] nArray127 = new int[4];
        nArray127[1] = 6;
        nArray127[2] = 15;
        nArray127[3] = 20;
        ff = nArray127;
        fi = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 0xFFFFFF, 16765346, 16693877, 15439166, 13859359, 12281360};
        bq = new String[][]{{"B\u00ecnh HP nh\u1ecf", "B\u00ecnh HP v\u1eeba", "B\u00ecnh HP to", "B\u00ecnh MP nh\u1ecf", "B\u00ecnh MP v\u1eeba", "B\u00ecnh MP to"}, {"Thu\u1ed1c", "G\u01b0\u01a1m s\u00e9t", "G\u01b0\u01a1m l\u1eeda", "G\u01b0\u01a1m b\u0103ng"}};
        fj = new int[]{-1, 5, 10, 15};
        fk = new String[][]{{"5000vn\u0111 / 50 b\u00ecnh", "10000vn\u0111 / 50 b\u00ecnh", "10000vn\u0111 / 30 b\u00ecnh", "5000vn\u0111 / 50 b\u00ecnh", "10000vn\u0111 / 30 b\u00ecnh", "10000vn\u0111 / 50 b\u00ecnh"}, {"5000vn\u0111", "5000vn\u0111", "10000vn\u0111", "15000vn\u0111"}};
        fl = new int[][]{{200, 600, 1500, 200, 600, 1200}, {10000, 15000, 50000, 200000}};
        fr = new int[]{12281360, 13859359, 15439166, 16693877, 16765346, 0xFFFFFF, 16765346, 16693877, 15439166, 13859359, 12281360};
        fs = new String[]{"B\u00ecnh HP nh\u1ecf", "B\u00ecnh HP v\u1eeba", "B\u00ecnh HP to", "B\u00ecnh MP nh\u1ecf", "B\u00ecnh MP v\u1eeba", "B\u00ecnh MP to"};
        br = new int[]{5, 2, 1, 5, 2, 1};
        ft = new int[]{100, 400, 1000, 100, 200, 300};
        bs = new int[6];
        fv = 0;
        fA = new String[]{"S\u1eed d\u1ee5ng item", "Ch\u1ecdn v\u0169 kh\u00ed", "Xem b\u1ea3n \u0111\u1ed3", "Xem nhi\u1ec7m v\u1ee5", "Tho\u00e1t"};
        fB = new String[]{"Tr\u01b0\u1eddng Hirosaki", "Khu luy\u1ec7n t\u1eadp", "Th\u00e1c n\u01b0\u1edbc", "L\u00e0ng Suzuya", "L\u00e0ng Tone", "V\u00e1ch \u0111\u00e1 Ichidai", "\u0111\u1ec9nh Ichidai", "\u0111\u1ed3i Kokoro", "C\u00e1nh \u0111\u1ed3ng Fuki", "Tr\u01b0\u1eddng Haruna", "K\u00fd t\u00fac x\u00e1", "Hang Aka", "Su\u1ed1i NN Akagi", "B\u1edd bi\u1ec3n", "L\u00e0ng ch\u00e0i", "Bi\u1ec3n", "\u0111\u1ea3o Sasayaku", "Hang Uno", "\u0111\u1ed3ng hoang", "\u0111\u1ec9nh gi\u00f3", "Mi\u1ebfu Chakumi"};
        fC = new int[]{90, 100, 110, 120, 130, 110, 140, 160, 190, 220, 260, 290, 340, 360, 390, 410, 430, 460, 490, 560, 670, 750};
        bt = new boolean[21];
        fD = new int[]{218, 184, 211, 179, 150, 97, 74, 103, 139, 147, 177, 210, 222, 194, 174, 166, 174, 74, 58, 46, 26};
        fE = new int[]{169, 176, 196, 204, 225, 202, 150, 148, 175, 142, 139, 119, 90, 58, 86, 58, 34, 58, 90, 118, 38};
        int[] nArray128 = new int[21];
        nArray128[0] = 18;
        nArray128[1] = 36;
        nArray128[2] = 14;
        nArray128[3] = 58;
        nArray128[4] = 101;
        nArray128[5] = 15;
        nArray128[6] = 7;
        nArray128[7] = 2;
        nArray128[8] = 12;
        nArray128[9] = 35;
        nArray128[10] = 14;
        nArray128[11] = 5;
        nArray128[12] = 1;
        nArray128[13] = 7;
        nArray128[14] = 2;
        nArray128[16] = 7;
        nArray128[17] = 1;
        nArray128[18] = 4;
        nArray128[19] = 1;
        nArray128[20] = 2;
        fF = nArray128;
        fG = new int[]{17, 9, 5, 17, 8, 75, 13, 9, 10, 17, 10, 38, 10, 11, 16, 9, 12, 28, 12, 27, 8};
        fY = "Chuy\u1ec3n \u0111\u1ebfn";
        bu = 5;
        bw = new String[]{"", "", ""};
        bx = new String[]{"", "", ""};
    }

    public static void a() {
        if (g.ax == 0 && g.aM == 0 || as == 99) {
            return;
        }
        int[] nArray = new int[500];
        int n2 = 0;
        if (eA > 0L) {
            ez += (int)((System.currentTimeMillis() - eA) / 1000L);
        }
        eA = System.currentTimeMillis();
        ++n2;
        nArray[0] = a ? 1 : 0;
        ++n2;
        nArray[1] = ez;
        ++n2;
        nArray[2] = as;
        ++n2;
        nArray[3] = at;
        ++n2;
        nArray[4] = aj;
        ++n2;
        nArray[5] = g.H ? 1 : 0;
        ++n2;
        nArray[6] = g.P;
        ++n2;
        nArray[7] = g.Q;
        ++n2;
        nArray[8] = aQ;
        ++n2;
        nArray[9] = g.as;
        ++n2;
        nArray[10] = g.O ? 1 : 0;
        ++n2;
        nArray[11] = g.bm ? 1 : 0;
        ++n2;
        nArray[12] = c ? 1 : 0;
        ++n2;
        nArray[13] = d ? 1 : 0;
        ++n2;
        nArray[14] = e ? 1 : 0;
        ++n2;
        nArray[15] = f ? 1 : 0;
        ++n2;
        nArray[16] = g ? 1 : 0;
        ++n2;
        nArray[17] = h ? 1 : 0;
        ++n2;
        nArray[18] = n.a ? 1 : 0;
        int n3 = 0;
        while (n3 < bt.length) {
            nArray[n2++] = bt[n3] ? 1 : 0;
            ++n3;
        }
        n3 = 0;
        while (n3 < br.length) {
            nArray[n2++] = br[n3];
            ++n3;
        }
        nArray[n2++] = g.f ? 1 : 0;
        nArray[n2++] = g.i ? 1 : 0;
        nArray[n2++] = g.h ? 1 : 0;
        nArray[n2++] = g.g ? 1 : 0;
        nArray[n2++] = au ? 1 : 0;
        nArray[n2++] = g.aq;
        nArray[n2++] = g.ar;
        nArray[n2++] = g.at;
        nArray[n2++] = g.au;
        nArray[n2++] = g.av;
        nArray[n2++] = g.aw;
        nArray[n2++] = g.ax;
        nArray[n2++] = g.ay;
        nArray[n2++] = g.az;
        nArray[n2++] = g.aA;
        nArray[n2++] = g.aB;
        nArray[n2++] = g.aC;
        nArray[n2++] = g.aD;
        nArray[n2++] = g.aE;
        nArray[n2++] = g.aF;
        nArray[n2++] = g.aG;
        nArray[n2++] = g.aH;
        nArray[n2++] = g.aI;
        nArray[n2++] = g.aJ;
        nArray[n2++] = g.aK;
        nArray[n2++] = g.aL;
        nArray[n2++] = g.aM;
        nArray[n2++] = g.aN;
        nArray[n2++] = g.aO;
        nArray[n2++] = g.aP;
        nArray[n2++] = g.aQ;
        nArray[n2++] = g.aR;
        nArray[n2++] = g.aS;
        nArray[n2++] = g.aT;
        nArray[n2++] = g.aU;
        nArray[n2++] = g.aV;
        nArray[n2++] = g.aW;
        nArray[n2++] = g.aX;
        nArray[n2++] = g.aY ? 1 : 0;
        nArray[n2++] = g.aZ ? 1 : 0;
        nArray[n2++] = g.ba ? 1 : 0;
        nArray[n2++] = g.bb ? 1 : 0;
        nArray[n2++] = g.bc;
        nArray[n2++] = g.bd;
        nArray[n2++] = g.be;
        nArray[n2++] = g.bf ? 1 : 0;
        nArray[n2++] = g.bh;
        nArray[n2++] = g.bi;
        nArray[n2++] = g.bj;
        nArray[n2++] = g.bk;
        nArray[n2++] = g.bl;
        nArray[n2++] = ed;
        nArray[n2++] = ee;
        nArray[n2++] = av;
        nArray[n2++] = aw;
        nArray[n2++] = ax;
        nArray[n2++] = ef;
        nArray[n2++] = eg;
        nArray[n2++] = eh;
        nArray[n2++] = ei;
        nArray[n2++] = ay;
        nArray[n2++] = az;
        nArray[n2++] = ej;
        nArray[n2++] = ek;
        nArray[n2++] = aN ? 1 : 0;
        nArray[n2++] = aM;
        nArray[n2++] = aO;
        nArray[n2++] = aE;
        nArray[n2++] = es;
        nArray[n2++] = et;
        nArray[n2++] = eu;
        nArray[n2++] = ev;
        nArray[n2++] = ew;
        nArray[n2++] = ex;
        nArray[n2++] = ey;
        nArray[n2++] = aF;
        nArray[n2++] = g.R;
        nArray[n2++] = g.S;
        nArray[n2++] = g.T;
        nArray[n2++] = g.U;
        nArray[n2++] = g.V;
        nArray[n2++] = g.W;
        nArray[n2++] = g.X;
        nArray[n2++] = g.Y;
        nArray[n2++] = g.Z;
        nArray[n2++] = g.aa;
        nArray[n2++] = g.ab;
        nArray[n2++] = g.ac;
        nArray[n2++] = g.ad;
        nArray[n2++] = g.ae;
        nArray[n2++] = g.af;
        nArray[n2++] = g.ag;
        nArray[n2++] = g.ah;
        nArray[n2++] = g.ai;
        nArray[n2++] = g.aj;
        nArray[n2++] = g.ak;
        nArray[n2++] = g.al;
        nArray[n2++] = g.am;
        nArray[n2++] = g.an;
        nArray[n2++] = g.bo;
        nArray[n2++] = g.bp;
        nArray[n2++] = g.bq;
        nArray[n2++] = g.br;
        nArray[n2++] = g.bs;
        nArray[n2++] = g.bt;
        nArray[n2++] = g.bu;
        nArray[n2++] = g.bv;
        nArray[n2++] = g.bw;
        nArray[n2++] = g.bx;
        nArray[n2++] = g.by;
        nArray[n2++] = g.bz;
        nArray[n2++] = g.bA;
        nArray[n2++] = g.bB;
        nArray[n2++] = g.bC;
        nArray[n2++] = g.bD;
        nArray[n2++] = g.bE;
        nArray[n2++] = g.bF;
        nArray[n2++] = g.bG;
        nArray[n2++] = g.bH;
        nArray[n2++] = g.bI;
        nArray[n2++] = g.bJ;
        nArray[n2++] = g.bK;
        int n4 = nArray[n2++] = i ? 1 : 0;
        if (j && !g.s) {
            j = false;
        }
        int n5 = nArray[n2++] = j ? 1 : 0;
        if (as == 12 && g.bc == 4 && g.bh < c.h[g.bc]) {
            k = false;
        }
        nArray[n2++] = k ? 1 : 0;
        nArray[n2++] = l ? 1 : 0;
        nArray[n2++] = m ? 1 : 0;
        nArray[n2++] = n ? 1 : 0;
        nArray[n2++] = o ? 1 : 0;
        nArray[n2++] = p ? 1 : 0;
        nArray[n2++] = q ? 1 : 0;
        nArray[n2++] = r ? 1 : 0;
        nArray[n2++] = s ? 1 : 0;
        int n6 = nArray[n2++] = t ? 1 : 0;
        if (bA && g.aW < 13) {
            bA = false;
        }
        nArray[n2++] = bA ? 1 : 0;
        nArray[n2++] = g.j ? 1 : 0;
        nArray[n2++] = g.k ? 1 : 0;
        nArray[n2++] = g.l ? 1 : 0;
        nArray[n2++] = t.m ? 1 : 0;
        nArray[n2++] = g.m ? 1 : 0;
        nArray[n2++] = g.n ? 1 : 0;
        nArray[n2++] = g.o ? 1 : 0;
        nArray[n2++] = g.p ? 1 : 0;
        nArray[n2++] = g.r ? 1 : 0;
        nArray[n2++] = g.s ? 1 : 0;
        nArray[n2++] = g.t ? 1 : 0;
        nArray[n2++] = g.u ? 1 : 0;
        nArray[n2++] = g.q ? 1 : 0;
        nArray[n2++] = g.v ? 1 : 0;
        nArray[n2++] = g.w ? 1 : 0;
        nArray[n2++] = g.x ? 1 : 0;
        nArray[n2++] = g.y ? 1 : 0;
        nArray[n2++] = g.z ? 1 : 0;
        nArray[n2++] = g.A ? 1 : 0;
        nArray[n2++] = g.B ? 1 : 0;
        nArray[n2++] = g.C ? 1 : 0;
        nArray[n2++] = m.g ? 1 : 0;
        nArray[n2++] = g.D;
        nArray[n2++] = aA ? 1 : 0;
        nArray[n2++] = el;
        nArray[n2++] = aB;
        nArray[n2++] = g.bL;
        nArray[n2++] = g.bn;
        nArray[n2++] = g.ap ? 1 : 0;
        nArray[n2++] = g.a;
        nArray[n2++] = bi;
        nArray[n2++] = bh;
        n3 = 2;
        while (n3 < 6) {
            nArray[n2++] = bf[n3];
            nArray[n2++] = bs[n3];
            ++n3;
        }
        nArray[n2++] = aW;
        if (aW > 0) {
            nArray[n2++] = aX;
            if (ba != null) {
                n3 = 0;
                while (n3 < ba.length) {
                    nArray[n2++] = ba[n3];
                    ++n3;
                }
            }
            if (eM != null) {
                n3 = 0;
                while (n3 < eM.length) {
                    nArray[n2++] = eM[n3];
                    ++n3;
                }
            }
            if (eL != null) {
                n3 = 0;
                while (n3 < eL.length) {
                    nArray[n2++] = eL[n3];
                    ++n3;
                }
            }
            if (bb != null) {
                n3 = 0;
                while (n3 < bb.length) {
                    nArray[n2++] = bb[n3];
                    ++n3;
                }
            }
            if (bc != null) {
                n3 = 0;
                while (n3 < bc.length) {
                    nArray[n2++] = bc[n3];
                    ++n3;
                }
            }
            if (aY != null) {
                n3 = 0;
                while (n3 < aY.length) {
                    nArray[n2++] = aY[n3];
                    ++n3;
                }
            }
            if (aZ != null) {
                n3 = 0;
                while (n3 < aZ.length) {
                    nArray[n2++] = aZ[n3];
                    ++n3;
                }
            }
        }
        int[] nArray2 = nArray;
        int n7 = nArray.length;
        byte[] byArray = new byte[n7 << 2];
        int n8 = 0;
        while (n8 < n7) {
            byArray[n8 << 2] = nArray2[n8] >> 24;
            byArray[(n8 << 2) + 1] = (byte)(nArray2[n8] >>> 16);
            byArray[(n8 << 2) + 2] = (byte)(nArray2[n8] >>> 8);
            byArray[(n8 << 2) + 3] = (byte)nArray2[n8];
            ++n8;
        }
        Midlet.a("ninjasave", byArray);
        Midlet.a(true);
    }

    public static void a(boolean bl) {
        Object[] objectArray = Midlet.b("ninjasave");
        int n2 = objectArray.length;
        int[] nArray = new int[n2 >> 2];
        int n3 = 0;
        while (n3 < nArray.length) {
            nArray[n3] = (objectArray[n3 << 2] << 24) + ((objectArray[(n3 << 2) + 1] & 0xFF) << 16) + ((objectArray[(n3 << 2) + 2] & 0xFF) << 8) + (objectArray[(n3 << 2) + 3] & 0xFF);
            ++n3;
        }
        objectArray = nArray;
        n3 = 0;
        ++n3;
        a = objectArray[0] == 1;
        ++n3;
        ez = objectArray[1];
        ++n3;
        as = objectArray[2];
        ++n3;
        at = objectArray[3];
        ++n3;
        aj = objectArray[4];
        ++n3;
        g.H = objectArray[5] == 1;
        ++n3;
        g.P = objectArray[6];
        ++n3;
        g.Q = objectArray[7];
        ++n3;
        aQ = objectArray[8];
        ++n3;
        g.as = objectArray[9];
        ++n3;
        g.O = objectArray[10] == 1;
        ++n3;
        g.bm = objectArray[11] == 1;
        ++n3;
        c = objectArray[12] == 1;
        ++n3;
        d = objectArray[13] == 1;
        ++n3;
        e = objectArray[14] == 1;
        ++n3;
        f = objectArray[15] == 1;
        ++n3;
        g = objectArray[16] == 1;
        ++n3;
        h = objectArray[17] == 1;
        ++n3;
        objectArray[18] = n.a ? (byte)1 : 0;
        int n4 = 0;
        while (n4 < bt.length) {
            q.bt[n4] = objectArray[n3++] == 1;
            ++n4;
        }
        n4 = 0;
        while (n4 < br.length) {
            q.br[n4] = objectArray[n3++];
            ++n4;
        }
        g.f = objectArray[n3++] == 1;
        g.i = objectArray[n3++] == 1;
        g.h = objectArray[n3++] == 1;
        g.g = objectArray[n3++] == 1;
        au = objectArray[n3++] == 1;
        g.aq = objectArray[n3++];
        g.ar = objectArray[n3++];
        g.at = objectArray[n3++];
        g.au = objectArray[n3++];
        g.av = objectArray[n3++];
        g.aw = objectArray[n3++];
        g.ax = objectArray[n3++];
        g.ay = objectArray[n3++];
        g.az = objectArray[n3++];
        g.aA = objectArray[n3++];
        g.aB = objectArray[n3++];
        g.aC = objectArray[n3++];
        g.aD = objectArray[n3++];
        g.aE = objectArray[n3++];
        g.aF = objectArray[n3++];
        g.aG = objectArray[n3++];
        g.aH = objectArray[n3++];
        g.aI = objectArray[n3++];
        g.aJ = objectArray[n3++];
        g.aK = objectArray[n3++];
        g.aL = objectArray[n3++];
        g.aM = objectArray[n3++];
        g.aN = objectArray[n3++];
        g.aO = objectArray[n3++];
        g.aP = objectArray[n3++];
        g.aQ = objectArray[n3++];
        g.aR = objectArray[n3++];
        g.aS = objectArray[n3++];
        g.aT = objectArray[n3++];
        g.aU = objectArray[n3++];
        g.aV = objectArray[n3++];
        g.aW = objectArray[n3++];
        g.aX = objectArray[n3++];
        g.aY = objectArray[n3++] == 1;
        g.aZ = objectArray[n3++] == 1;
        g.ba = objectArray[n3++] == 1;
        g.bb = objectArray[n3++] == 1;
        g.bc = objectArray[n3++];
        g.bd = objectArray[n3++];
        g.be = objectArray[n3++];
        g.bf = objectArray[n3++] == 1;
        g.bh = objectArray[n3++];
        g.bi = objectArray[n3++];
        g.bj = objectArray[n3++];
        g.bk = objectArray[n3++];
        g.bl = objectArray[n3++];
        ed = objectArray[n3++];
        ee = objectArray[n3++];
        av = objectArray[n3++];
        aw = objectArray[n3++];
        ax = objectArray[n3++];
        ef = objectArray[n3++];
        eg = objectArray[n3++];
        eh = objectArray[n3++];
        ei = objectArray[n3++];
        ay = objectArray[n3++];
        az = objectArray[n3++];
        ej = objectArray[n3++];
        ek = objectArray[n3++];
        aN = objectArray[n3++] == 1;
        aM = objectArray[n3++];
        aO = objectArray[n3++];
        aE = objectArray[n3++];
        es = objectArray[n3++];
        et = objectArray[n3++];
        eu = objectArray[n3++];
        ev = objectArray[n3++];
        ew = objectArray[n3++];
        ex = objectArray[n3++];
        ey = objectArray[n3++];
        aF = objectArray[n3++];
        g.R = objectArray[n3++];
        g.S = objectArray[n3++];
        g.T = objectArray[n3++];
        g.U = objectArray[n3++];
        g.V = objectArray[n3++];
        g.W = objectArray[n3++];
        g.X = objectArray[n3++];
        g.Y = objectArray[n3++];
        g.Z = objectArray[n3++];
        g.aa = objectArray[n3++];
        g.ab = objectArray[n3++];
        g.ac = objectArray[n3++];
        g.ad = objectArray[n3++];
        g.ae = objectArray[n3++];
        g.af = objectArray[n3++];
        g.ag = objectArray[n3++];
        g.ah = objectArray[n3++];
        g.ai = objectArray[n3++];
        g.aj = objectArray[n3++];
        g.ak = objectArray[n3++];
        g.al = objectArray[n3++];
        g.am = objectArray[n3++];
        g.an = objectArray[n3++];
        g.bo = objectArray[n3++];
        g.bp = objectArray[n3++];
        g.bq = objectArray[n3++];
        g.br = objectArray[n3++];
        g.bs = objectArray[n3++];
        g.bt = objectArray[n3++];
        g.bu = objectArray[n3++];
        g.bv = objectArray[n3++];
        g.bw = objectArray[n3++];
        g.bx = objectArray[n3++];
        g.by = objectArray[n3++];
        g.bz = objectArray[n3++];
        g.bA = objectArray[n3++];
        g.bB = objectArray[n3++];
        g.bC = objectArray[n3++];
        g.bD = objectArray[n3++];
        g.bE = objectArray[n3++];
        g.bF = objectArray[n3++];
        g.bG = objectArray[n3++];
        g.bH = objectArray[n3++];
        g.bI = objectArray[n3++];
        g.bJ = objectArray[n3++];
        g.bK = objectArray[n3++];
        i = objectArray[n3++] == 1;
        j = objectArray[n3++] == 1;
        k = objectArray[n3++] == 1;
        l = objectArray[n3++] == 1;
        m = objectArray[n3++] == 1;
        n = objectArray[n3++] == 1;
        o = objectArray[n3++] == 1;
        p = objectArray[n3++] == 1;
        q = objectArray[n3++] == 1;
        r = objectArray[n3++] == 1;
        s = objectArray[n3++] == 1;
        t = objectArray[n3++] == 1;
        bA = objectArray[n3++] == 1;
        g.j = objectArray[n3++] == 1;
        g.k = objectArray[n3++] == 1;
        g.l = objectArray[n3++] == 1;
        t.m = objectArray[n3++] == 1;
        g.m = objectArray[n3++] == 1;
        g.n = objectArray[n3++] == 1;
        g.o = objectArray[n3++] == 1;
        g.p = objectArray[n3++] == 1;
        g.r = objectArray[n3++] == 1;
        g.s = objectArray[n3++] == 1;
        g.t = objectArray[n3++] == 1;
        g.u = objectArray[n3++] == 1;
        g.q = objectArray[n3++] == 1;
        g.v = objectArray[n3++] == 1;
        g.w = objectArray[n3++] == 1;
        g.x = objectArray[n3++] == 1;
        g.y = objectArray[n3++] == 1;
        g.z = objectArray[n3++] == 1;
        g.A = objectArray[n3++] == 1;
        g.B = objectArray[n3++] == 1;
        g.C = objectArray[n3++] == 1;
        m.g = objectArray[n3++] == 1;
        g.D = objectArray[n3++];
        aA = objectArray[n3++] == 1;
        el = objectArray[n3++];
        aB = objectArray[n3++];
        g.bL = objectArray[n3++];
        g.bn = objectArray[n3++];
        g.ap = objectArray[n3++] == 1;
        g.a = objectArray[n3++];
        bi = objectArray[n3++];
        bh = objectArray[n3++];
        n4 = 2;
        while (n4 < 6) {
            q.bf[n4] = objectArray[n3++];
            switch (bf[n4]) {
                case 1: {
                    q.bg[n4] = "G\u01b0\u01a1m s\u00e9t";
                    break;
                }
                case 2: {
                    q.bg[n4] = "G\u01b0\u01a1m l\u1eeda";
                    break;
                }
                case 3: {
                    q.bg[n4] = "G\u01b0\u01a1m b\u0103ng";
                    break;
                }
                case 4: {
                    q.bg[n4] = "G\u01b0\u01a1m h\u1ecfa long";
                }
            }
            q.bs[n4] = objectArray[n3++];
            ++n4;
        }
        if ((aW = objectArray[n3++]) > 0) {
            aX = objectArray[n3++];
            try {
                n4 = 0;
                while (n4 < ba.length) {
                    q.ba[n4] = objectArray[n3++];
                    ++n4;
                }
                n4 = 0;
                while (n4 < eM.length) {
                    q.eM[n4] = objectArray[n3++];
                    ++n4;
                }
                n4 = 0;
                while (n4 < eL.length) {
                    q.eL[n4] = objectArray[n3++];
                    ++n4;
                }
                n4 = 0;
                while (n4 < bb.length) {
                    q.bb[n4] = objectArray[n3++];
                    ++n4;
                }
                n4 = 0;
                while (n4 < bc.length) {
                    q.bc[n4] = objectArray[n3++];
                    ++n4;
                }
                n4 = 0;
                while (n4 < aY.length) {
                    q.aY[n4] = objectArray[n3++];
                    ++n4;
                }
                n4 = 0;
                while (n4 < aZ.length) {
                    q.aZ[n4] = objectArray[n3++];
                    ++n4;
                }
                return;
            }
            catch (Exception exception) {}
        }
    }

    public static final int a(int n2) {
        if (n2 > 0) {
            return n2;
        }
        return -n2;
    }

    public static void a(int n2, int n3, int n4, int n5, int n6) {
        int n7 = 0;
        while (n7 < dn) {
            if (dq[n7] == 0) break;
            ++n7;
        }
        if (n7 == dn) {
            return;
        }
        q.do[n7] = n2;
        q.dp[n7] = n3;
        q.dr[n7] = n4;
        q.ds[n7] = n5;
        q.dq[n7] = 1;
        q.du[n7] = n6;
        if (n6 < 160) {
            q.dv[n7] = 0;
            return;
        }
        if (n6 < 600) {
            q.dv[n7] = 1;
            return;
        }
        q.dv[n7] = 2;
    }

    public static void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        n5 = 0;
        while (n5 < cY) {
            if (dc[n5] == 0) break;
            ++n5;
        }
        if (n5 == cY) {
            return;
        }
        q.da[n5] = n2;
        q.db[n5] = n3;
        q.dd[n5] = n4;
        q.de[n5] = -5;
        q.dc[n5] = 1;
        q.dg[n5] = n6;
        q.cZ[n5] = n7;
        if (n6 < 100) {
            q.df[n5] = 0;
            return;
        }
        if (n6 < 200) {
            q.df[n5] = 1;
            return;
        }
        q.df[n5] = 2;
    }

    static void b() {
        int n2 = 0;
        while (n2 < 14) {
            q.C[n2] = false;
            ++n2;
        }
    }

    static void c() {
        int n2 = 0;
        while (n2 < 14) {
            q.D[n2] = false;
            ++n2;
        }
    }

    private static void y() {
        switch (eQ) {
            case 3: {
                g.bc = 0;
                g.bh = 0;
                q.a("Gi\u1ebft 10 nh\u00edm");
                break;
            }
            case 4: {
                g.f = true;
                g.bc = -1;
                g.bh = 0;
                q.a("\u0111\u00e3 ph\u1ee5c h\u1ed3i khinh c\u00f4ng");
                break;
            }
            case 5: {
                g.bc = 1;
                g.bh = 0;
                q.a("T\u00ecm Sakura \u1edf th\u00e1c");
                break;
            }
            case 6: {
                g.bc = 2;
                g.bh = 0;
                q.a("T\u00ecm Toniku th\u00f4n Sanzuya");
                break;
            }
            case 8: {
                g.i = true;
                g.bc = -1;
                g.bh = 0;
                q.a("L\u00ean \u0111\u01b0\u1eddng!");
                break;
            }
            case 9: {
                g.bh = -1;
                g.bc = 9;
                q.a("Gi\u1ebft m\u1ef1c kh\u1ed5ng l\u1ed3");
                break;
            }
            case 10: {
                eQ = -1;
                g.at = 1104;
                g.au = 240;
                g.bo = 1152;
                g.bp = 240;
                g.bn = 4;
                g.bA = -1;
                g.aF = 1;
                g.bs = 1;
                g.bL = 0;
                g.d(4);
                q.g(25);
                break;
            }
            case 12: {
                eQ = -1;
                av = 2;
                g.A = true;
                q.a("\u0111\u00e3 c\u00f3 s\u00e1o th\u1ea7n");
                break;
            }
            case 13: {
                eQ = -1;
                g.a = 1;
                q.a("Mua thu\u1ed1c mang v\u1ec1 cho Sakura");
                break;
            }
            case 14: {
                g.bc = 14;
                eQ = -1;
                break;
            }
            case 15: {
                eQ = -1;
                if (!g.o) break;
                g.a = 3;
                q.a("T\u00ecm th\u1ebb h\u1ecdc sinh");
            }
        }
        if (c.j >= 0) {
            q.b(c.j, c.k, c.l, c.m);
            c.j = -1;
        }
    }

    private static void z() {
        ao = null;
        ar = null;
        aq = null;
    }

    private static void A() {
        c.a();
        q.z();
        s.b = null;
        d.b = null;
        j.a();
        dM = null;
        dP = null;
        t.a();
        m.a();
        dX = null;
        System.gc();
        a.a();
        n.a();
        g.g();
        dJ = null;
        System.gc();
    }

    private static void B() {
        dO = null;
        ea = null;
        q.A();
    }

    public static final void b(int n2) {
        cv = 0;
        if (n2 == 0) {
            cv = 0;
            return;
        }
        if (dJ == null) {
            dJ = new Image[4][2];
            n2 = 0;
            while (n2 < 4) {
                int n3 = 0;
                while (n3 < 2) {
                    try {
                        q.dJ[n2][n3] = Image.createImage((String)("/c/ar" + n2 + n3 + ".png"));
                    }
                    catch (IOException iOException) {}
                    ++n3;
                }
                ++n2;
            }
        }
        cv = 10;
        cw = new int[10];
        cx = new int[cv];
        cy = new int[cv];
        cz = new int[cv];
        cA = new int[cv];
        cB = new int[cv];
        n2 = 0;
        while (n2 < cv) {
            q.cy[n2] = -1;
            ++n2;
        }
    }

    public static void d() {
        if (dK == null) {
            dK = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.dK[n2] = Image.createImage((String)("/u/arr" + n2 + ".png"));
                }
                catch (Exception exception) {}
                ++n2;
            }
        }
    }

    public static void e() {
        cC = 8;
        cD = new int[8];
        cE = new int[cC];
        cF = new int[cC];
        cG = new int[cC];
        cH = new int[cC];
        cI = new int[cC];
        try {
            dL = Image.createImage((String)"/e/b.png");
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void c(int n2) {
        aP = 0;
        int n3 = aQ == -1 ? 0 : 1;
        if (n3 == 1) {
            aP = 1;
            q.C();
            q.d(0, g.at, g.au);
            aQ = 0;
            q.aT[q.aQ] = g.aF;
        }
        if (n2 == 108) {
            q.a(n3, 36, 4);
        }
        if (n2 == 13 && aP == 0 && g.bm) {
            q.a(n3, 10, 11);
        }
    }

    public static final void a(int n2, int n3, int n4) {
        aP = n2 + 1;
        q.C();
        q.d(0, n3 * 24 + 12, n4 * 24);
        if (n2 == 1) {
            q.d(1, g.at, g.au);
            aQ = 1;
            q.aT[q.aQ] = g.aF;
        }
    }

    private static void C() {
        if (dM == null) {
            dM = new Image[3];
            try {
                int n2 = 0;
                while (n2 < 3) {
                    q.dM[n2] = Image.createImage((String)("/c/v" + n2 + ".png"));
                    ++n2;
                }
            }
            catch (Exception exception) {}
        }
        aR = new int[aP];
        aS = new int[aP];
        aT = new int[aP];
        aU = new int[aP];
        aV = new int[aP];
        eK = new int[aP];
    }

    private static void d(int n2, int n3, int n4) {
        q.aR[n2] = n3;
        q.aS[n2] = n4;
        q.aU[n2] = 0;
        q.aT[n2] = 1;
    }

    public static void f() {
        if (as == 103 && bi == 1 || as == 9 && bi == 2 || as == 14 && bi == 3 || as == 102 && bi == 4 || as == 17 && bi == 5 || as == 16 && bi == 6 || as == 19 && bi == 7) {
            try {
                dN = Image.createImage((String)("/i/bk" + bi + ".png"));
                return;
            }
            catch (Exception exception) {
                return;
            }
        }
        dN = null;
    }

    public static final void d(int n2) {
        aW = 0;
        if (n2 == 4) {
            aW = 1;
            q.D();
            q.e(0, 52, 9);
        }
        if (n2 == 102) {
            aW = 3;
            q.D();
            q.e(0, 3, 11);
            q.e(1, 8, 11);
            q.e(2, 17, 11);
        }
        if (n2 == 3 && g.n) {
            aW = 6;
            q.D();
            q.e(0, 13, 5);
            q.e(1, 13, 6);
            q.e(2, 14, 5);
            q.e(3, 14, 6);
            q.e(4, 15, 5);
            q.e(5, 15, 6);
        }
        if (n2 == 14 && !g.w && g.D < 2) {
            aW = 1;
            q.D();
            q.e(0, 49, 18);
        }
    }

    private static void D() {
        if (dP == null) {
            try {
                dP = as == 14 ? Image.createImage((String)"/i/l.png") : Image.createImage((String)"/i/b.png");
            }
            catch (Exception exception) {}
        }
        aY = new int[aW];
        aZ = new int[aW];
        ba = new int[aW];
        bb = new int[aW];
        bc = new int[aW];
        eL = new int[aW];
        eM = new int[aW];
    }

    private static void e(int n2, int n3, int n4) {
        q.aY[n2] = n3 * j.f + 12;
        q.aZ[n2] = n4 * j.f + 24;
        if (as == 14 || as == 102) {
            q.ba[n2] = 2;
            return;
        }
        q.ba[n2] = 1;
    }

    public static final void g() {
        fX = as;
        if (as == 107) {
            fX = 8;
        } else if (as == 108) {
            fX = 3;
        } else if (as == 103) {
            fX = 2;
        } else if (as == 106) {
            fX = 8;
        } else if (as == 105) {
            fX = 0;
        }
        ab = E;
        if (F >= 208) {
            cb = true;
            cf = 50;
            ac = F - cf;
            ce = ab;
            cc = 0;
            cd = ac + 1;
        } else {
            cf = 16;
            ac = F - cf;
            cb = false;
            cf = 0;
            ce = ab;
            cc = 0;
            cd = ac + 1;
            cl = ab / 3;
            cn = cl + 29;
            co = cl + 50;
        }
        cq = F / 6;
        if (cq < 48) {
            cq = 48;
        }
        ad = ab >> 1;
        ae = ac >> 1;
        af = 3 * ac / 4;
        ag = ab / 6;
        ct = ab / j.f + 2;
        cu = ac / j.f + 2;
        if (ab % 24 != 0) {
            ++ct;
        }
        ej = (j.a - 1) * j.f - ab;
        ek = (j.b - 1) * j.f - ac;
        ay = aw = g.at - ad + ag * g.aF;
        az = ax = g.au - af;
        if (ay < 0) {
            ay = 0;
        }
        if (ay > ej) {
            ay = ej;
        }
        if (az < 0) {
            az = 0;
        }
        if (az > ek) {
            az = ek;
        }
        if ((W = ay / j.f - 1) < 0) {
            W = 0;
        }
        X = az / j.f;
        Y = W + ct;
        Z = X + cu;
        if (X < 0) {
            X = 0;
        }
        if (Z > j.b - 1) {
            Z = j.b - 1;
        }
    }

    public static void h() {
        q.i();
        if (!cb) {
            return;
        }
        try {
            if (dQ == null) {
                dQ = new Image[2];
                int n2 = 0;
                while (n2 < 2) {
                    q.dQ[n2] = Image.createImage((String)("/u/c" + n2 + ".png"));
                    ++n2;
                }
            }
            cg = dQ[0].getWidth();
            ch = dQ[1].getWidth();
            ci = ab - cg - ch + 1;
            cj = 78;
            ck = cd + 7;
            cl = ab - 134;
            cm = ab - 84;
            cp = 5;
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    public static void i() {
        try {
            if (dS == null) {
                dS = Image.createImage((String)"/u/co.png");
            }
            Image[] imageArray = new Image[2];
            eb = imageArray;
            imageArray[1] = Image.createImage((String)"/u/wpt1.png");
            if (g.as == 0) {
                q.eb[0] = Image.createImage((String)"/u/wpt0.png");
                return;
            }
            q.eb[0] = Image.createImage((String)("/u/wpt" + (g.as + 1) + ".png"));
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static void j() {
        dn = 5;
        if (dR == null) {
            try {
                dR = new Image[3];
                int n2 = 0;
                while (n2 < 3) {
                    q.dR[n2] = Image.createImage((String)("/i/dm" + n2 + ".png"));
                    ++n2;
                }
            }
            catch (Exception exception) {}
        }
        do = new int[dn];
        dp = new int[dn];
        dq = new int[dn];
        dr = new int[dn];
        ds = new int[dn];
        dt = new int[dn];
        dv = new int[dn];
        du = new int[dn];
    }

    public static final void k() {
        if (b) {
            return;
        }
        if (dT == null) {
            dT = new Image[2][5];
            int n2 = 0;
            while (n2 < 2) {
                int n3 = 0;
                while (n3 < 5) {
                    try {
                        q.dT[n2][n3] = Image.createImage((String)("/e/d" + n2 + n3 + ".png"));
                    }
                    catch (IOException iOException) {}
                    ++n3;
                }
                ++n2;
            }
        }
        cS = new int[2];
        cT = new int[2];
        int[] nArray = new int[2];
        cU = nArray;
        q.cU[1] = -1;
        nArray[0] = -1;
    }

    public static final void l() {
        int n2;
        if (dU == null) {
            dU = new Image[2];
            n2 = 0;
            while (n2 < 2) {
                try {
                    q.dU[n2] = Image.createImage((String)("/c/pt" + n2 + ".png"));
                }
                catch (IOException iOException) {}
                ++n2;
            }
        }
        dh = 10;
        di = new int[10];
        dj = new int[dh];
        ah = new int[dh];
        dk = new int[dh];
        ai = new int[dh];
        dm = new boolean[dh];
        dl = new int[dh];
        n2 = 0;
        while (n2 < dh) {
            q.ah[n2] = -1;
            ++n2;
        }
    }

    public static final void m() {
        dx = new int[5];
        dy = new int[5];
        dz = new int[5];
        dA = new int[5];
        dB = new int[5];
        dC = new int[5];
        dw = new String[5];
        int n2 = 0;
        while (n2 < 5) {
            q.dB[n2] = -1;
            ++n2;
        }
    }

    private static void i(int n2) {
        if (n2 == 12 && n && aQ != -1) {
            aQ = -1;
            g.ax = 2;
        }
        if (!g.l) {
            aK = aL;
        }
        if (!g.k) {
            t.i = t.j;
        }
        if (!t.m) {
            t.k = t.l;
        }
        if (!m.g) {
            m.e = m.f;
        }
        if (!g.C) {
            e.f = 8000;
        }
        K = 2;
        as = n2;
        Midlet.d = 1;
        q.R();
        new Thread(Midlet.b).start();
    }

    public static void n() {
        if (dV == null) {
            dV = new Image[2];
            int n2 = 0;
            while (n2 < 2) {
                try {
                    q.dV[n2] = Image.createImage((String)("/e/l" + n2 + ".png"));
                }
                catch (IOException iOException) {}
                ++n2;
            }
        }
    }

    public static void o() {
        if (dW == null) {
            dW = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.dW[n2] = Image.createImage((String)("/e/e" + n2 + ".png"));
                }
                catch (IOException iOException) {}
                ++n2;
            }
        }
    }

    private static void E() {
        K = 2;
        q.R();
        Midlet.d = 3;
        as = 109;
        bM = 0;
        try {
            Image[] imageArray = new Image[2];
            dO = imageArray;
            imageArray[0] = Image.createImage((String)"/u/t20.png");
            q.dO[1] = Image.createImage((String)"/u/t21.png");
            ea = Image.createImage((String)"/u/tt.png");
        }
        catch (Exception exception) {}
        ax = 0;
        az = 0;
        aw = 0;
        ay = 0;
        eg = 1;
        ef = 1;
        bX = F - 30;
        bY = F;
        bT = 30;
        bU = F - 50;
        bV = bW = bT + bU >> 1;
        bS = Midlet.a() ? 1 : 0;
        System.getProperty("microedition.platform");
        new Thread(Midlet.b).start();
    }

    public static void e(int n2) {
        if (g.l) {
            return;
        }
        eB = 30;
        aG = false;
        if (n2 == 103) {
            aG = true;
            if (dX == null) {
                dX = new Image[5];
                n2 = 0;
                while (n2 < 5) {
                    try {
                        q.dX[n2] = Image.createImage((String)("/mob/b0" + n2 + ".png"));
                    }
                    catch (Exception exception) {}
                    ++n2;
                }
            }
            eG = dX[0].getWidth();
            aJ = dX[0].getHeight();
            aH = 10 * j.f + 12;
            aI = 10 * j.f;
            eD = 1;
            eE = -1;
            eF = 0;
        }
    }

    public static void p() {
        cY = 5;
        if (dY == null) {
            try {
                Image[] imageArray = new Image[2];
                dY = imageArray;
                imageArray[0] = Image.createImage((String)"/i/pt0.png");
                q.dY[1] = Image.createImage((String)"/i/pt1.png");
            }
            catch (Exception exception) {}
        }
        da = new int[cY];
        db = new int[cY];
        dc = new int[cY];
        dd = new int[cY];
        de = new int[cY];
        df = new int[cY];
        dg = new int[cY];
        cZ = new int[cY];
    }

    public static final void q() {
        if (dZ == null) {
            dZ = new Image[3];
            int n2 = 0;
            while (n2 < 3) {
                try {
                    q.dZ[n2] = Image.createImage((String)("/e/sp" + n2 + ".png"));
                }
                catch (IOException iOException) {}
                ++n2;
            }
        }
        cJ = new int[2];
        cK = new int[2];
        cL = new int[2];
        cM = new int[2];
        cN = new int[2];
        q.cL[1] = -1;
        q.cL[0] = -1;
    }

    public static void f(int n2) {
        aN = false;
        aA = false;
        if (n2 == 0 && c) {
            q.j(0);
            return;
        }
        if (n2 == 105) {
            g.H = true;
            g.aY = false;
            q.j(1);
            return;
        }
        if (n2 == 0 && d) {
            g.aF = -1;
            q.j(2);
            return;
        }
        if (n2 == 106 && e) {
            g.aF = 1;
            g.aY = true;
            n.a = true;
            q.j(3);
            return;
        }
        if (n2 == 0 && f) {
            q.j(4);
            g.H = true;
            g.aY = false;
            bD = true;
            return;
        }
        if (n2 == 107 && f) {
            g.aM = 200;
            g.aL = 200;
            g.aO = 200;
            g.aN = 200;
            g.aJ = 20;
            g.aK = 5;
            g.aW = 1;
            g.aX = 0;
            g.as = 0;
            g.Q = 107;
            g.R = 120;
            g.S = 288;
            g.V = 1;
            bD = true;
            g.b(n2);
            q.bf[0] = 0;
            q.bf[1] = 100;
            q.bg[0] = "G\u01b0\u01a1m tre";
            q.bg[1] = "Phi ti\u00eau";
            q.bs[0] = 0;
            q.bs[1] = 1;
            bh = 2;
            n2 = 2;
            while (n2 < 6) {
                q.bf[n2] = -1;
                q.bg[n2] = "";
                q.bs[n2] = -1;
                ++n2;
            }
            q.j(5);
            return;
        }
        if (n2 == 0 && g) {
            g.f = false;
            q.j(6);
            return;
        }
        if (n2 == 2 && g.bc == 1 && !g.h && !g.bf) {
            q.j(7);
            return;
        }
        if (n2 == 2 && g.bc == 1 && g.h) {
            g.Q = 2;
            g.R = 240;
            g.S = 528;
            g.V = 1;
            g.O = false;
            g.b(n2);
            if (!g.bf) {
                q.j(8);
                return;
            }
            q.j(9);
            return;
        }
        if (n2 == 3 && g.bc == 2 && !g.bm) {
            g.bn = 3;
            g.bo = 1296;
            g.bp = 408;
            g.bA = 1;
            g.bs = 1;
            g.d(n2);
            q.j(10);
            return;
        }
        if (n2 == 3 && !g.i) {
            aN = false;
            aA = false;
            av = 0;
            bi = 0;
            q.j(12);
            return;
        }
        if (n2 == 103) {
            bi = 1;
            return;
        }
        if (n2 == 108) {
            g.bn = 108;
            g.bo = 48;
            g.bp = 264;
            g.bA = 1;
            g.bs = 1;
            g.bm = false;
            g.d(n2);
            g.Q = 108;
            g.R = 72;
            g.S = 264;
            g.ad = 1;
            g.V = 1;
            g.at = 96;
            g.aS = 96;
            g.au = 264;
            g.aT = 264;
            g.aF = 1;
            q.j(11);
            return;
        }
        if (n2 == 99) {
            g.at = 1152;
            g.au = 264;
            g.aY = true;
            q.j(14);
            ax = 264 - ac;
            aw = 1080;
            return;
        }
        if (n2 == 4 && !h) {
            q.j(15);
            return;
        }
        if (n2 == 8 && !i) {
            q.j(16);
            return;
        }
        if (n2 == 4 && g.n && !g.o) {
            g.bn = 4;
            g.bs = 1;
            g.bm = false;
            if (g.aF == -1 || g.at >= 1440) {
                g.bo = 960;
                g.bp = 240;
                g.bA = 1;
                g.d(n2);
                q.j(17);
                return;
            }
            g.bo = 840;
            g.bp = 216;
            g.bA = -1;
            g.d(n2);
            q.j(37);
            return;
        }
        if (n2 == 9 && g.C && !s) {
            g.R = 1152;
            g.S = 384;
            g.ad = 1;
            q.j(30);
            return;
        }
        if (n2 == 9 && g.o && g.p) {
            q.j(18);
            return;
        }
        if (n2 == 9 && !g.o) {
            q.j(19);
            return;
        }
        if (n2 == 12 && g.q) {
            q.j(20);
            return;
        }
        if (n2 == 13 && !n) {
            q.j(22);
            return;
        }
        if (n2 == 100 && !t.m) {
            q.j(32);
            return;
        }
        if (n2 == 14 && !o) {
            q.j(23);
            return;
        }
        if (n2 == 14 && !g.t) {
            q.j(24);
            return;
        }
        if (n2 == 14 && g.t && g.aW < 20) {
            q.j(34);
            return;
        }
        if (n2 == 15 && !p) {
            q.j(25);
            return;
        }
        if (n2 == 15 && !g.C && g.A && !g.u) {
            q.j(27);
            return;
        }
        if (n2 == 16 && !q) {
            q.j(26);
            return;
        }
        if (n2 == 20 && !g.C && !r) {
            q.j(29);
            return;
        }
        if (n2 == 9 && g.C && !t) {
            g.bA = -1;
            g.bo = 1776;
            g.bp = 360;
            g.bs = 1;
            g.bn = 9;
            g.bL = 0;
            g.d(9);
            q.j(31);
            return;
        }
        if (n2 == 8 && !g.o) {
            q.j(36);
            return;
        }
        if (n2 == 101 && !g.m) {
            q.j(35);
        }
    }

    public static final void r() {
        if (b) {
            return;
        }
        ec = new Image[3];
        int n2 = 0;
        while (n2 < 3) {
            try {
                q.ec[n2] = Image.createImage((String)("/e/w" + n2 + ".png"));
            }
            catch (IOException iOException) {}
            ++n2;
        }
        cO = new int[2];
        cP = new int[2];
        cQ = new int[2];
        cR = new int[2];
        q.cQ[1] = -1;
        q.cQ[0] = -1;
    }

    private static void F() {
        if (eV < eR[eP].length) {
            eV = eR[eP].length;
            eU = 0;
            return;
        }
        ++eP;
        eV = 0;
        eU = 0;
    }

    private static void a(Graphics graphics) {
        if (I && !b) {
            int n2;
            graphics.setColor(P);
            graphics.fillRect(0, 0, ab, aa);
            if (ar != null) {
                graphics.drawImage(ar, N, O, 3);
                n2 = 0;
                while (n2 < 2) {
                    graphics.drawImage(aq, L[n2], M[n2], 3);
                    ++n2;
                }
            }
            if (ao[3] != null) {
                n2 = -((ay >> be[3]) % ap[3]);
                while (n2 < ab) {
                    graphics.drawImage(ao[3], n2, T, 0);
                    n2 += ap[3];
                }
            }
            if (ao[2] != null) {
                n2 = -((ay >> be[2]) % ap[2]);
                while (n2 < ab) {
                    graphics.drawImage(ao[2], n2, S, 0);
                    n2 += ap[2];
                }
            }
            if (ao[1] != null) {
                n2 = -((ay >> be[1]) % ap[1]);
                while (n2 < ab) {
                    graphics.drawImage(ao[1], n2, R, 0);
                    n2 += ap[1];
                }
            }
            if (ao[0] != null) {
                n2 = -((ay >> be[0]) % ap[0]);
                while (n2 < ab) {
                    graphics.drawImage(ao[0], n2, Q, 0);
                    n2 += ap[0];
                }
                return;
            }
        } else {
            graphics.setColor(P);
            graphics.fillRect(0, 0, ab, ac);
            if (b) {
                return;
            }
            graphics.drawImage(ar, N, O, 3);
            int n3 = 0;
            while (n3 < 2) {
                graphics.drawImage(aq, L[n3], M[n3], 3);
                ++n3;
            }
        }
    }

    private static void b(Graphics graphics) {
        if (cb) {
            return;
        }
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.drawImage(g.N[0], 0, 2, 0);
        graphics.setColor(0);
        graphics.drawRect(23, 2, cl + 1, 4);
        graphics.drawRect(23, 8, cl + 1, 4);
        graphics.drawRect(23, 14, cl + 1, 3);
        graphics.setColor(0xFF0000);
        int n2 = g.aL * cl / g.aM;
        graphics.fillRect(24, 3, n2, 3);
        graphics.setColor(0x550000);
        graphics.fillRect(n2 + 24, 3, cl - n2, 3);
        graphics.setColor(255);
        n2 = g.aN * cl / g.aO;
        graphics.fillRect(24, 9, n2, 3);
        graphics.setColor(0x550000);
        graphics.fillRect(n2 + 24, 9, cl - n2, 3);
        graphics.setColor(7274435);
        n2 = g.aX * cl / g.c[g.aW - 1];
        graphics.fillRect(24, 15, n2, 2);
        graphics.setColor(19506);
        graphics.fillRect(n2 + 24, 15, cl - n2, 2);
        Midlet.a(graphics, "lv:" + g.aW, 2, cn, 7, 0);
        Midlet.a(graphics, String.valueOf(aj) + "$", 1, co, 7, 0);
    }

    private static void c(Graphics graphics) {
        J = false;
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(-1, cd - 1, ce + 2, 100);
        graphics.setColor(0);
        if (!cb) {
            graphics.fillRect(0, cd, ce, 15);
            Midlet.a(graphics, U, 3, cd + 3, 0);
            Midlet.a(graphics, V, ce - 3, cd + 3, 1);
            return;
        }
        graphics.fillRect(0, cd + 33, ce, 15);
        Midlet.a(graphics, U, 3, cd + 36, 0);
        Midlet.a(graphics, V, ce - 3, cd + 36, 1);
        graphics.setColor(4352);
        graphics.fillRect(cg, cd, ci, 1);
        graphics.fillRect(cg, cd + 35, ci, 1);
        graphics.fillRect(cg, cd + 33, ci, 1);
        graphics.fillRect(cg, cd + 30, ci, 1);
        graphics.fillRect(cg, cd + 28, ci, 1);
        graphics.fillRect(cg, cd + 26, ci, 1);
        graphics.fillRect(cg, cd + 12, ci, 1);
        graphics.fillRect(cg, cd + 24, ci, 1);
        graphics.fillRect(cg, cd + 18, ci, 1);
        graphics.fillRect(cg, cd + 16, ci, 1);
        graphics.fillRect(cg, cd + 2, ci, 1);
        graphics.fillRect(cg, cd + 4, ci, 1);
        graphics.fillRect(cg, cd + 6, ci, 1);
        graphics.fillRect(cg, cd + 14, ci, 1);
        graphics.setColor(12562018);
        graphics.fillRect(cg, cd + 1, ci, 1);
        graphics.fillRect(cg, cd + 5, ci, 1);
        graphics.fillRect(cg, cd + 17, ci, 1);
        graphics.fillRect(cg, cd + 34, ci, 1);
        graphics.fillRect(cg, cd + 29, ci, 1);
        graphics.setColor(14667167);
        graphics.fillRect(cg, cd + 3, ci, 1);
        graphics.fillRect(cg, cd + 15, ci, 1);
        graphics.fillRect(cg, cd + 27, ci, 1);
        graphics.setColor(0x333333);
        graphics.fillRect(cg, cd + 7, ci, 5);
        graphics.fillRect(cg, cd + 19, ci, 5);
        graphics.fillRect(cg, cd + 31, ci, 2);
        graphics.setColor(9463099);
        graphics.fillRect(cg, cd + 25, ci, 1);
        graphics.fillRect(cg, cd + 13, ci, 1);
        graphics.drawImage(dQ[0], 0, cd, 0);
        graphics.drawImage(dQ[1], 0 + ce, cd, 24);
        int n2 = g.aL * cl / g.aM;
        graphics.setColor(0x770000);
        graphics.fillRect(cj, ck, n2, 2);
        graphics.setColor(0xCC0000);
        graphics.fillRect(cj, ck + 1, n2, 4);
        n2 = g.aN * cl / g.aO;
        graphics.setColor(119);
        graphics.fillRect(cj, ck + 12, n2, 2);
        graphics.setColor(204);
        graphics.fillRect(cj, ck + 14, n2, cp - 2);
        Midlet.a(graphics, "" + aj, 1, ce - 18, cd + 6, 2);
        n2 = g.aX * cm / g.c[g.aW - 1];
        graphics.setColor(94373);
        graphics.fillRect(31, cd + 31, n2, 1);
        graphics.setColor(65535);
        graphics.fillRect(31, cd + 32, n2, 1);
        Midlet.a(graphics, "" + g.aW, 2, 26, cd + 11, 2);
        graphics.drawImage(eb[g.ar], 43, cd + 16, 3);
        Midlet.a(graphics, "" + (br[0] + br[1] + br[2]), 2, ce - 29, cd + 19, 2);
        Midlet.a(graphics, "" + (br[3] + br[4] + br[5]), 2, ce - 9, cd + 19, 2);
    }

    private static void d(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(-1, -1, E + 2, F + 2);
        graphics.setColor(0);
        graphics.fillRect(0, 0, ab, cr);
        if (cb) {
            graphics.fillRect(0, F - cr + 1, ab, cr);
        } else {
            graphics.fillRect(0, F - 13, E, 13);
        }
        if (em) {
            int n2 = 0;
            while (n2 < en.length) {
                Midlet.a(graphics, en[n2], ad, eo + (n2 << 4), 2);
                ++n2;
            }
        }
    }

    private static void e(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(-1, -1, E + 2, F + 2);
        eN = eR[eP].length;
        int n2 = eT - az - (eN << 4);
        boolean bl = true;
        if (n2 < 10) {
            bl = false;
            n2 = 10;
        }
        graphics.setColor(0xFFFFFF);
        graphics.fillRect(eX, n2 - 5, eW, (eN << 4) + 7);
        graphics.setColor(17408);
        graphics.drawRect(eX - 1, n2 - 5, eW + 1, (eN << 4) + 7);
        int n3 = 0;
        while (n3 < eV) {
            Midlet.b(graphics, eR[eP][n3], eX + (eW >> 1), n2 + (n3 << 4), 2);
            ++n3;
        }
        if (eV < eR[eP].length) {
            Midlet.b(graphics, eR[eP][eV].substring(0, eU), eX + (eW >> 1), n2 + (eV << 4), 2);
        }
        graphics.setColor(0);
        graphics.fillRect(0, cd + 33, ce, 15);
        if (!bD && bl && eS > ay + 10 && eS < ay + ab - 10) {
            graphics.drawImage(dS, eS - ay + 3, eT - az + 2, 17);
        }
        if (at % 10 < 8) {
            Midlet.a(graphics, "Ti\u1ebfp t\u1ee5c", ad, F - 13, 2);
        }
    }

    private static void f(Graphics graphics) {
        graphics.setClip(-1, -1, E + 2, F + 1);
        q.a(graphics);
        graphics.translate(-ay, -az);
        j.b(graphics);
        if (b) {
            j.c(graphics);
        } else {
            j.d(graphics);
        }
        j.a(graphics);
        g.b(graphics);
        g.c(graphics);
        c.a(graphics);
        j.e(graphics);
        q.d(graphics);
        if (bd) {
            q.e(graphics);
        }
    }

    private static void G() {
        if (g.f()) {
            int n2;
            eD = 5;
            eE = aH < g.at ? 1 : -1;
            eI = n2 = g.at + (eE << 5);
            eC = eE * 6;
            if (aK < aL >> 1) {
                eC = eE << 3;
            }
        }
    }

    public static final void b(int n2, int n3, int n4, int n5, int n6) {
        int n7 = -1;
        int n8 = 0;
        while (n8 < cv) {
            if (cy[n8] == -1) {
                n7 = n8;
                break;
            }
            ++n8;
        }
        if (n7 == -1) {
            return;
        }
        q.cy[n7] = 1;
        q.cw[n7] = n2;
        q.cx[n7] = n3;
        q.cz[n7] = n4;
        q.cA[n7] = n5;
        q.cB[n7] = n6;
    }

    private static void b(int n2, int n3, int n4, int n5) {
        aE = n2;
        es = n3;
        et = n4;
        aF = n5;
    }

    public static void a(int n2, int n3) {
        ed = n2;
        ee = n3;
        av = 1;
    }

    public static void g(int n2) {
        eq = 0;
        el = n2;
        aB = 0;
        aA = true;
        er = false;
        q.b();
        q.c();
    }

    public static void a(String[] stringArray, int n2, int n3, int n4) {
        eQ = n2;
        eS = n3;
        eT = n4;
        eO = stringArray.length;
        eP = 0;
        eR = new String[eO][];
        if (n2 == 1) {
            eW = ab - 2;
            ay = 0;
        } else {
            eW = ab >> 1;
        }
        if (eW < 120) {
            eW = 120;
        }
        n2 = 0;
        while (n2 < eO) {
            q.eR[n2] = Midlet.b(stringArray[n2], eW - 20);
            ++n2;
        }
        eX = eS - (eW >> 1) - ay;
        if (eX < 2) {
            eX = 2;
        }
        if (eX + eW > ab - 2) {
            eX = ab - 2 - eW;
        }
        bd = true;
        eU = 0;
        eV = 0;
    }

    public static final boolean b(int n2, int n3, int n4) {
        if (b) {
            return false;
        }
        int n5 = n2 = n2 == 1 ? 0 : 1;
        if (cU[n2] != -1) {
            return false;
        }
        q.cU[n2] = 0;
        q.cS[n2] = n3;
        q.cT[n2] = n4;
        return true;
    }

    public static final void a(int n2, int n3, int n4, int n5, boolean bl) {
        int n6 = -1;
        int n7 = 0;
        while (n7 < dh) {
            if (ah[n7] == -1) {
                n6 = n7;
                break;
            }
            ++n7;
        }
        if (n6 == -1) {
            return;
        }
        q.ah[n6] = 1;
        q.di[n6] = n2;
        q.dj[n6] = n3;
        q.ai[n6] = n4;
        q.dl[n6] = n5;
        q.dm[n6] = bl;
    }

    public static final void a(String string, int n2, int n3, int n4, int n5, int n6) {
        n5 = -1;
        int n7 = 0;
        while (n7 < 5) {
            if (dB[n7] == -1) {
                n5 = n7;
                break;
            }
            ++n7;
        }
        if (n5 == -1) {
            return;
        }
        q.dw[n5] = string;
        q.dx[n5] = n3;
        q.dy[n5] = n4;
        q.dz[n5] = 0;
        q.dA[n5] = n6;
        q.dB[n5] = 0;
        q.dC[n5] = n2;
    }

    public static void b(int n2, int n3) {
        dF = 1;
        dD = n2;
        dE = n3;
    }

    public static void a(int n2, int n3, int n4, int n5) {
        dI = 1;
        am = 1;
        ak = n2;
        al = n3;
        an = n4;
        dH = n5;
        if (an == 1) {
            dG = 0;
            return;
        }
        dG = 2;
    }

    public static void a(String string) {
        cX = string;
        cW = 0;
        cV = true;
    }

    public static final boolean c(int n2, int n3, int n4) {
        int n5;
        int n6 = n5 = cL[0] == -1 ? 0 : 1;
        if (cL[n5] != -1) {
            return false;
        }
        q.cL[n5] = 0;
        q.cN[n5] = n4;
        q.cJ[n5] = n2;
        q.cK[n5] = n3;
        return true;
    }

    private static void j(int n2) {
        aO = 0;
        aM = n2;
        aN = true;
    }

    public static final boolean c(int n2, int n3) {
        int n4;
        if (b) {
            return false;
        }
        int n5 = n4 = cQ[0] == -1 ? 0 : 1;
        if (cQ[n4] != -1) {
            return false;
        }
        q.cQ[n4] = 0;
        q.cO[n4] = n2;
        q.cP[n4] = n3;
        return true;
    }

    private static void H() {
        int n2 = 0;
        while (n2 < cv) {
            if (cy[n2] == 1) {
                int n3 = n2;
                cw[n3] = cw[n3] + cz[n2];
                if (cw[n2] < ay || cw[n2] > ay + ab || cx[n2] < az || cx[n2] > az + ac) {
                    q.cy[n2] = -1;
                } else if (q.a(g.at - cw[n2]) < g.aB && q.a(g.au - g.aC - cx[n2]) < g.aC) {
                    if (g.ax == 5 && g.ar == 0 && cB[n2] < 3) {
                        q.cz[n2] = -cz[n2];
                        int n4 = n2;
                        cw[n4] = cw[n4] + cz[n2];
                        return;
                    }
                    g.a(cA[n2], cw[n2] < g.at ? 1 : -1);
                    q.cy[n2] = -1;
                    return;
                }
            }
            ++n2;
        }
    }

    private static void I() {
        int n2 = 0;
        while (n2 < cC) {
            if (cF[n2] != 0) {
                int n3 = n2;
                cD[n3] = cD[n3] + cG[n2];
                int n4 = n2;
                cE[n4] = cE[n4] + cH[n2];
                int n5 = n2;
                cH[n5] = cH[n5] + 1;
                if (cE[n2] > az + ac) {
                    q.cF[n2] = 0;
                }
                if (q.a(g.at - cD[n2]) < g.aB && q.a(g.au - g.aC - cE[n2]) < g.aC) {
                    g.a(cI[n2], cD[n2] < g.at ? 1 : -1);
                    q.cF[n2] = -1;
                    return;
                }
            }
            ++n2;
        }
    }

    public static void s() {
        if (g.bb) {
            w = 0;
        }
    }

    private static final void J() {
        if (ay != aw || az != ax) {
            ef = aw - ay << 2;
            eg = ax - az << 2;
            eh &= 0xF;
            az += (ei += eg) >> 4;
            ei &= 0xF;
            if ((ay += (eh += ef) >> 4) < 0) {
                ay = 0;
            }
            if (ay > ej) {
                ay = ej;
            }
            if (az < 0) {
                az = 0;
            }
            if (az > ek) {
                az = ek;
            }
        }
        if (w >= 0) {
            ay += bP[w];
            az += bQ[w];
            if (++w == 4) {
                w = -1;
            }
            if (ay < 0) {
                ay = 0;
            }
            if (ay > ej) {
                ay = ej;
            }
            if (az < 0) {
                az = 0;
            }
            if (az > ek) {
                az = ek;
            }
        }
        if ((W = ay / j.f - 1) < 0) {
            W = 0;
        }
        X = az / j.f;
        Y = W + ct;
        Z = X + cu;
        if (X < 0) {
            X = 0;
        }
        if (Z > j.b - 1) {
            Z = j.b - 1;
        }
    }

    private static boolean K() {
        if (!aA) {
            if (cr > 1) {
                cr >>= 1;
                J = true;
            }
            return false;
        }
        if (er) {
            if (D[5] || D[9]) {
                q.c();
                if (bd) {
                    q.F();
                    if (eP == eO) {
                        bd = false;
                        er = false;
                        q.y();
                    }
                } else if (s.a) {
                    s.c = 0;
                    er = false;
                }
            }
        } else {
            if (cr < cq) {
                cr += (cq - cr >> 1) + 1;
            }
            if (em) {
                eo = eo < ep ? (eo += 2) : ep;
            }
            if (++eq >= bF[el][aB]) {
                eq = bF[el][aB];
                block0 : switch (bC[el][aB]) {
                    case 53: {
                        q.a(l.b, 1, G, H + 50);
                        er = true;
                        ++aB;
                        break;
                    }
                    case 3: {
                        if (el == 44) {
                            g.a = 12;
                            q.j(31);
                        }
                        if (el == 51) {
                            g.bm = false;
                            g.O = false;
                            g.Q = -1;
                            g.bn = -1;
                        }
                        if (as == 8 && !i) {
                            i = true;
                            q.j(36);
                        }
                        if (as == 4 && !h) {
                            h = true;
                            g.bc = 3;
                            q.a("Gi\u1ebft 20 \u1ed1c ma \u0111en");
                        }
                        if (el == 28) {
                            g.p = false;
                            g.a = -1;
                            g.bc = -1;
                        }
                        if (el == 30) {
                            q.a("C\u00f3 mi\u1ebfng phong \u1ea5n th\u1ee9 nh\u1ea5t");
                            g.a = 4;
                            c.b = null;
                        }
                        if (as == 12 && g.q) {
                            g.bc = 4;
                            q.a("Gi\u1ebft 20 r\u00f9a bi\u1ec3n");
                            g.q = false;
                        }
                        if (el == 48) {
                            q.a("T\u1eadp luy\u1ec7n \u0111\u1ec3 \u0111\u1ea1t c\u1ea5p \u0111\u1ed9 13");
                            g.a = 10;
                        }
                        if (el == 47) {
                            ++aO;
                        }
                        if (as == 14 && !o) {
                            o = true;
                            g.D = 0;
                            q.j(24);
                            g.a = 5;
                        }
                        if (el == 37) {
                            q.a("C\u00f3 mi\u1ebfng phong \u1ea5n th\u1ee9 hai");
                            g.a = 4;
                        }
                        if (as == 13 && !n) {
                            n = true;
                        }
                        if (as == 15 && g.u) {
                            q.a("C\u00f3 mi\u1ebfng phong \u1ea5n th\u1ee9 ba");
                            g.a = 9;
                        }
                        if (el == 45) {
                            g.bA = -1;
                            g.bo = 1776;
                            g.bp = 360;
                            g.bs = 1;
                            g.bn = 9;
                            g.d(9);
                            q.j(31);
                        }
                        if (as == 20 && !r && !g.C) {
                            r = true;
                            aN = false;
                        }
                        if (el == 45) {
                            t = true;
                            a = true;
                            er = false;
                            aN = false;
                            bD = false;
                            g.a = -1;
                            q.a();
                        }
                        if (as == 9 && !s && g.C) {
                            s = true;
                        }
                        if (as == 15 && !p) {
                            p = true;
                        }
                        if (as == 16 && !q) {
                            q = true;
                        }
                        if (as == 4 && g.n && !g.o) {
                            g.o = true;
                            g.a = 2;
                        }
                        if (el == 49) {
                            q.a("C\u00f3 mi\u1ebfng phong \u1ea5n th\u1ee9 hai");
                            g.a = 11;
                            q.j(34);
                        }
                        aA = false;
                        J = true;
                        ++aB;
                        break;
                    }
                    case 5: {
                        q.b(as, bE[el][aB], g.au - 50, -1);
                        ++aB;
                        break;
                    }
                    case 9: {
                        ay = bG[bE[el][aB]][0] - ad;
                        az = bG[bE[el][aB]][1] - ae;
                        if (ay < 0) {
                            ay = 0;
                        }
                        if (ay > ej) {
                            ay = ej;
                        }
                        if (az < 0) {
                            az = 0;
                        }
                        if (az > ek) {
                            az = ek;
                        }
                        aw = ay;
                        ax = az;
                        ++aB;
                        break;
                    }
                    case 10: {
                        aw = bG[bE[el][aB]][0] - ad;
                        ax = bG[bE[el][aB]][1] - ae;
                        if (aw < 0) {
                            aw = 0;
                        }
                        if (aw > ej) {
                            aw = ej;
                        }
                        if (ax < 0) {
                            ax = 0;
                        }
                        if (ax > ek) {
                            ax = ek;
                        }
                        ++aB;
                        break;
                    }
                    case 8: {
                        g.aY = false;
                        ++aB;
                        break;
                    }
                    case 15: {
                        em = true;
                        en = Midlet.a(l.a[bE[el][aB]], ab - 40);
                        ep = (cq >> 1) - (en.length << 3);
                        if (ep < 3) {
                            ep = 2;
                        }
                        eo = -en.length << 3;
                        ++aB;
                        break;
                    }
                    case 14: {
                        aw = ay += 2;
                        if (ay <= bG[bE[el][aB]][0] - ad) break;
                        ++aB;
                        break;
                    }
                    case 34: {
                        aw = ay -= 2;
                        if (ay >= bG[bE[el][aB]][0] - ad) break;
                        ++aB;
                        break;
                    }
                    case 4: {
                        if (g.ax != 1 && g.ax != 18) break;
                        em = false;
                        int n2 = 0;
                        int n3 = 0;
                        switch (bI[bE[el][aB]]) {
                            case 0: {
                                n2 = g.at;
                                n3 = g.au;
                                break;
                            }
                            case 1: {
                                n2 = c.d[bJ[bE[el][aB]]];
                                n3 = c.e[bJ[bE[el][aB]]];
                                break;
                            }
                            case 2: {
                                n2 = g.K[bJ[bE[el][aB]]];
                                n3 = g.L[bJ[bE[el][aB]]];
                                break;
                            }
                            case 4: {
                                n2 = n.g[bJ[bE[el][aB]]];
                                n3 = n.h[bJ[bE[el][aB]]];
                                break;
                            }
                            case 3: {
                                if (el == 24 && aB == 0) {
                                    n2 = W * 24 + ct;
                                    n3 = Z * 24 - cu;
                                    break;
                                }
                                n2 = g.R;
                                n3 = g.S;
                                break;
                            }
                            case 5: {
                                n2 = e.a;
                                n3 = e.b - 20;
                                break;
                            }
                            case 6: {
                                n2 = i.a;
                                n3 = i.b;
                                break;
                            }
                            case 7: {
                                n2 = g.bo;
                                n3 = g.bp;
                            }
                        }
                        n3 -= 40;
                        if (as == 4 && bI[bE[el][aB]] == 7 && !g.n && bK[bE[el][aB]] == 94) {
                            q.a(l.c[bK[bE[el][aB]]], 13, n2, n3);
                        } else if (as == 4 && bI[bE[el][aB]] == 7 && g.n) {
                            q.a(l.c[bK[bE[el][aB]]], 14, n2, n3);
                        } else if (as == 9 && bI[bE[el][aB]] == 1 && bJ[bE[el][aB]] == 0) {
                            q.a(l.c[bK[bE[el][aB]]], 15, n2, n3);
                        } else {
                            q.a(l.c[bK[bE[el][aB]]], -1, n2, n3);
                        }
                        ++aB;
                        er = true;
                        break;
                    }
                    case 39: {
                        g.H = false;
                        ++aB;
                        break;
                    }
                    case 24: {
                        if (d.c == 0) {
                            d.c = 1;
                        }
                        ++aB;
                        break;
                    }
                    case 40: {
                        g = false;
                        ++aB;
                        break;
                    }
                    case 25: {
                        d.c = 2;
                        c.d[1] = c.d[1] - 48;
                        ++aB;
                        break;
                    }
                    case 28: {
                        if (s.c == 0) {
                            s.c = 1;
                        }
                        ++aB;
                        er = true;
                        break;
                    }
                    case 22: {
                        int n4 = 0;
                        while (n4 < g.F) {
                            if (q.a(g.K[n4] - bE[el][aB]) <= 40) {
                                g.H = true;
                                ++aB;
                                break block0;
                            }
                            ++n4;
                        }
                        break;
                    }
                    case 6: {
                        aE = -1;
                        ++aB;
                        break;
                    }
                    case 16: {
                        g.I[q.bE[q.el][q.aB]] = true;
                        g.J = g.K[bE[el][aB]] + 200;
                        ++aB;
                        break;
                    }
                    case 21: {
                        g.K[0] = bE[el][aB];
                        ++aB;
                        break;
                    }
                    case 17: {
                        if (c) {
                            c = false;
                        }
                        int n5 = g.at;
                        g.aS = g.at = j.n;
                        g.aT = g.au = j.o;
                        if (as == 105) {
                            d = true;
                        }
                        if (as == 0 && d) {
                            d = false;
                            e = true;
                        }
                        if (as == 106 && e) {
                            e = false;
                            f = true;
                        }
                        if (as == 0 && f) {
                            g.at = 360;
                            g.aS = 360;
                            g.au = 288;
                            g.aT = 288;
                            g.aF = -1;
                        }
                        if (as == 107 && f) {
                            g.at = 408;
                            g.aS = 408;
                            g.au = 408;
                            g.aT = 408;
                            g.aF = 1;
                            f = false;
                            g = true;
                        }
                        if (as == 108) {
                            if (g.bo < n5) {
                                g.bm = true;
                                g.at = 1368;
                                g.au = 408;
                                g.aF = 1;
                                g.bo = 935;
                                g.bp = 408;
                                g.bA = 1;
                                g.R = 1392;
                                g.S = 408;
                                g.ad = 1;
                                g.ax = 1;
                                aQ = -1;
                                q.bE[q.el][q.aB] = 3;
                            } else {
                                g.bm = false;
                                g.at = 72;
                                g.au = 120;
                                g.aF = 1;
                                g.R = 96;
                                g.S = 120;
                                g.ad = 1;
                                g.ax = 1;
                                aQ = -1;
                                q.bE[q.el][q.aB] = 2;
                            }
                        }
                        if (as == 20 && g.C) {
                            g.aF = 1;
                            g.at = 1128;
                            g.au = 384;
                        }
                        q.A();
                        Midlet.f = true;
                        q.i(bE[el][aB]);
                        return true;
                    }
                    case 7: {
                        g.aY = true;
                        ++aB;
                        break;
                    }
                    case 1: {
                        q.k(bE[el][aB]);
                        ++aB;
                        break;
                    }
                    case 2: {
                        q.l(bE[el][aB]);
                        ++aB;
                        break;
                    }
                    case 11: {
                        if (as == 13) {
                            aw = 1272 - ad - 40;
                            ax = 264 - ae - 70;
                            g.V = 1;
                            g.bs = 1;
                            g.R = g.at - 24;
                            g.bo = g.at + 24;
                        } else {
                            aw = e.a - ad - 40;
                            ax = e.b - ae - 70;
                        }
                        if (aw < 0) {
                            aw = 0;
                        }
                        if (aw > ej) {
                            aw = ej;
                        }
                        if (ax < 0) {
                            ax = 0;
                        }
                        if (ax > ek) {
                            ax = ek;
                        }
                        ++aB;
                        break;
                    }
                    case 27: {
                        n.a = false;
                        ++aB;
                        break;
                    }
                    case 30: {
                        n.a = true;
                        ++aB;
                        break;
                    }
                    case 35: {
                        bD = true;
                        ++aB;
                        break;
                    }
                    case 36: {
                        bD = false;
                        if (as == 4) {
                            g.Q = 4;
                            g.bn = 4;
                            g.b(4);
                            g.O = true;
                            g.bm = true;
                            g.at = 1992;
                            g.au = 240;
                            g.aF = 1;
                            g.V = 1;
                        }
                        ++aB;
                        break;
                    }
                    case 37: {
                        c.d[q.bH[q.bE[q.el][q.aB]][0]] = bH[bE[el][aB]][1];
                        c.e[q.bH[q.bE[q.el][q.aB]][0]] = bH[bE[el][aB]][2];
                        ++aB;
                        break;
                    }
                    case 32: {
                        g.ao = bE[el][aB];
                        g.O = false;
                        g.V = 2;
                        g.ad = g.R > g.ao ? -1 : 1;
                        g.T = 5 * g.ad;
                        ++aB;
                        break;
                    }
                    case 48: {
                        g.bs = 2;
                        g.bq = 5 * g.bA;
                        if (g.bA == 1) {
                            if (g.bo < bE[el][aB]) break;
                            g.bs = 1;
                            ++aB;
                            break;
                        }
                        if (g.bo > bE[el][aB]) break;
                        g.bs = 1;
                        ++aB;
                        break;
                    }
                    case 38: {
                        g.ad = bE[el][aB];
                        ++aB;
                        break;
                    }
                    case 19: {
                        g.O = true;
                        ++aB;
                        break;
                    }
                    case 20: {
                        g.O = false;
                        ++aB;
                        break;
                    }
                    case 41: {
                        g.h = true;
                        q.a("T\u00ecm g\u01b0\u01a1m c\u1ee7a Sakura");
                        ++aB;
                        break;
                    }
                    case 42: {
                        g.h = false;
                        g.O = true;
                        q.a("V\u1ec1 g\u1eb7p th\u1ea7y hi\u1ec7u tr\u01b0\u1edfng");
                        ++aB;
                        break;
                    }
                    case 43: {
                        g.bL = 10000;
                        ++aB;
                        break;
                    }
                    case 44: {
                        g.bL = 0;
                        g.bs = 1;
                        ++aB;
                        break;
                    }
                    case 45: {
                        g.aY = false;
                        aw = g.bo - ad;
                        ax = g.bp - ae;
                        if (aw < 0) {
                            aw = 0;
                        }
                        if (aw > ej) {
                            aw = ej;
                        }
                        if (ax < 0) {
                            ax = 0;
                        }
                        if (ax > ek) {
                            ax = ek;
                        }
                        ++aB;
                        break;
                    }
                    case 46: {
                        if (q.a(g.au - g.bp) < 100 && q.a(g.at - g.bo) < 100) {
                            ++aB;
                            break;
                        }
                        if (g.bo < g.at) {
                            g.bs = 2;
                            g.bA = 1;
                        }
                        if (g.bo > g.at) {
                            g.bs = 2;
                            g.bA = -1;
                        }
                        if (q.a(g.bp - g.au) >= 100) {
                            g.bs = 3;
                        }
                    }
                    case 47: {
                        if (g.bo < W * 24 || g.bp > Z * 24 || g.bo > Y * 24) {
                            ++aB;
                            g.bn = -1;
                            g.bL = 0;
                            g.bm = false;
                            break;
                        }
                        if (!j.a(g.bo, g.bp, 2)) {
                            g.bA = g.bo >= 360 ? -1 : 1;
                            g.bL = 20;
                            g.bs = 4;
                            g.bq = 3 * g.bA;
                            g.br = 7;
                            break;
                        }
                        g.bA = g.bo >= 360 ? -1 : 1;
                        g.bL = 20;
                        g.bs = 2;
                        g.bq = 5 * g.bA;
                        break;
                    }
                    case 49: {
                        g.ad = 1;
                        g.O = false;
                        if (g.R > Y * 24 + 20) {
                            ++aB;
                            g.R = 2496;
                            g.S = 264;
                            g.ad = -1;
                            g.V = 1;
                            break;
                        }
                        if (j.a(g.R + g.Z, g.S - g.aa, 4)) {
                            g.T = g.ad * 6;
                            g.V = 3;
                            g.U = -10;
                            break;
                        }
                        if (j.a(g.R, g.S, 2)) {
                            g.V = 2;
                            g.T = g.ad * (aC - 1);
                            break;
                        }
                        if (g.V == 3) break;
                        g.V = 4;
                        g.T = 3 * g.ad;
                        g.U = 4;
                        break;
                    }
                    case 50: {
                        o.b.addElement(new h(c.d[0], c.e[0] - 5, 13));
                        g.u = true;
                        ++aB;
                        break;
                    }
                    case 51: {
                        g.aF = -1;
                        ++aB;
                        break;
                    }
                    case 52: {
                        g.ad = -1;
                        g.O = false;
                        if (g.R < W * 24) {
                            ++aB;
                            g.Q = -1;
                            break;
                        }
                        if (j.a(g.R - g.Z - 1, g.S - g.aa, 8)) {
                            g.T = g.ad * 6;
                            g.V = 3;
                            g.U = -10;
                            break;
                        }
                        if (j.a(g.R, g.S, 2)) {
                            g.V = 2;
                            g.T = g.ad * (aC - 1);
                            break;
                        }
                        if (g.V == 3) break;
                        g.V = 4;
                        g.T = 3 * g.ad;
                        g.U = 4;
                        break;
                    }
                    case 55: {
                        g.bA = -1;
                        g.bm = false;
                        if (g.bo < W * 24) {
                            ++aB;
                            g.bn = -1;
                            break;
                        }
                        if (j.a(g.bo - g.bw - 1, g.bp - g.bx, 8)) {
                            g.bq = g.bA * 6;
                            g.bs = 3;
                            g.br = -10;
                            break;
                        }
                        if (j.a(g.bo, g.bp, 2)) {
                            g.bs = 2;
                            g.bq = g.bA * (aC - 1);
                            break;
                        }
                        if (g.bs == 3) break;
                        g.bs = 4;
                        g.bq = 3 * g.bA;
                        g.br = 4;
                        break;
                    }
                    case 54: {
                        q.a("C\u1eeda hang ch\u01b0a \u0111\u01b0\u1ee3c m\u1edf");
                        ++aB;
                        break;
                    }
                    case 29: {
                        q.c();
                        q.b();
                        j.b();
                        q.z();
                        s.b = null;
                        d.b = null;
                        c.a();
                        dM = null;
                        dP = null;
                        dR = null;
                        dX = null;
                        System.gc();
                        t.a();
                        m.a();
                        a.a();
                        n.a();
                        g.g();
                        dJ = null;
                        dT = null;
                        dU = null;
                        dV = null;
                        dZ = null;
                        ec = null;
                        dK = null;
                        System.gc();
                        g.h();
                        q.E();
                        return true;
                    }
                }
            }
        }
        q.C[5] = false;
        q.D[5] = false;
        return false;
    }

    private static void L() {
        int n2 = 0;
        while (n2 < dn) {
            if (dq[n2] != 0) {
                if (q.a(g.au - (g.aA >> 1) - (dp[n2] - 7)) < 10 && q.a(g.at - do[n2]) < 10) {
                    aj += du[n2];
                    J = true;
                    q.dq[n2] = 301;
                    q.a("+" + du[n2], 1, do[n2], dp[n2] - 15, 0, -1);
                }
                int n3 = n2;
                do[n3] = do[n3] + dr[n2];
                int n4 = n2;
                dp[n4] = dp[n4] + ds[n2];
                if (dr[n2] > 1) {
                    int n5 = n2;
                    dr[n5] = dr[n5] - 1;
                }
                if (dr[n2] < -1) {
                    int n6 = n2;
                    dr[n6] = dr[n6] + 1;
                }
                if (ds[n2] < 20) {
                    int n7 = n2;
                    ds[n7] = ds[n7] + 1;
                }
                int n8 = n2;
                dq[n8] = dq[n8] + 1;
                if (dq[n2] > 200) {
                    q.dq[n2] = 0;
                }
                q.dt[n2] = dq[n2] % 20 > 18 ? 0 : (dq[n2] % 20 > 16 ? 1 : 2);
                if (dp[n2] < 0 || dp[n2] >= j.d || do[n2] >= j.c || do[n2] < 0) {
                    q.dq[n2] = 0;
                } else if (ds[n2] > 2 && (j.b(do[n2], dp[n2]) & 2) == 2) {
                    q.dp[n2] = j.d(dp[n2]);
                    if (ds[n2] > 2) {
                        q.ds[n2] = -(ds[n2] >> 1);
                    } else {
                        q.dr[n2] = 0;
                        q.ds[n2] = 0;
                    }
                }
            }
            ++n2;
        }
    }

    private static void M() {
        if (bd && eV < eR[eP].length && ++eU >= eR[eP][eV].length()) {
            eU = 0;
            ++eV;
        }
    }

    public static boolean d(int n2, int n3) {
        if (eD == 1 || eD == 2) {
            if ((aK -= n2) <= 0) {
                aK = 0;
                eH = -5;
                eC = -(n3 << 3);
                eJ = 0;
                eD = 6;
                q.a(aH, aI, 4, -6, 50);
                q.a(aH, aI, -5, -7, 50);
                q.a(aH, aI, -4, -8, 50);
                q.a(aH, aI, 5, -9, 50);
                q.a(aH, aI, 0, -10, 50);
                if (g.bc == 1 && g.h) {
                    q.a(aH, aI);
                    q.b(aH, aI - 28);
                }
                return true;
            }
            eH = 0;
            eE = n3;
            eD = 7;
            eJ = 10;
            eF = 0;
            eI = n2 <= g.aK ? 0 : 2 + at % 5;
        }
        return false;
    }

    public static int e(int n2, int n3) {
        return n2 + eY.nextInt(n3 - n2);
    }

    private static void N() {
        if (++at == 10000) {
            at = 0;
        }
        q.M();
        q.J();
        q.P();
        if (q.K()) {
            return;
        }
        g.c();
        g.b();
    }

    private static void O() {
        int n2 = 0;
        while (n2 < cY) {
            if (dc[n2] != 0) {
                if (q.a(g.au - (g.aA >> 1) - (db[n2] - 7)) < 10 && q.a(g.at - da[n2]) < 10) {
                    if (cZ[n2] == 0) {
                        if ((g.aL += dg[n2]) > g.aM) {
                            g.aL = g.aM;
                        }
                        q.a("+" + dg[n2], 0, da[n2], db[n2] - 15, 0, -1);
                    } else {
                        if ((g.aN += dg[n2]) > g.aO) {
                            g.aN = g.aO;
                        }
                        q.a("+" + dg[n2], 2, da[n2], db[n2] - 15, 0, -1);
                    }
                    J = true;
                    q.dc[n2] = 301;
                }
                int n3 = n2;
                da[n3] = da[n3] + dd[n2];
                int n4 = n2;
                db[n4] = db[n4] + de[n2];
                if (dd[n2] > 1) {
                    int n5 = n2;
                    dd[n5] = dd[n5] - 1;
                }
                if (dd[n2] < -1) {
                    int n6 = n2;
                    dd[n6] = dd[n6] + 1;
                }
                if (de[n2] < 20) {
                    int n7 = n2;
                    de[n7] = de[n7] + 1;
                }
                int n8 = n2;
                dc[n8] = dc[n8] + 1;
                if (dc[n2] > 200) {
                    q.dc[n2] = 0;
                }
                if (db[n2] >= j.d || db[n2] < 0 || da[n2] >= j.c || da[n2] < 0) {
                    q.dc[n2] = 0;
                } else if (de[n2] > 2 && (j.b(da[n2], db[n2]) & 2) == 2) {
                    q.db[n2] = j.d(db[n2]);
                    if (de[n2] > 2) {
                        q.de[n2] = -(de[n2] >> 1);
                    } else {
                        q.dd[n2] = 0;
                        q.de[n2] = 0;
                    }
                }
            }
            ++n2;
        }
    }

    private static void P() {
        block23: {
            block22: {
                if (!aN) break block22;
                switch (u[aM][aO]) {
                    case 2: {
                        q.g(v[aM][aO]);
                        ++aO;
                        return;
                    }
                    case 1: {
                        if (g.at > bB[aM][aO]) {
                            q.g(v[aM][aO]);
                            ++aO;
                            return;
                        }
                        break block23;
                    }
                    case 4: {
                        if (g.at > ay + ab) {
                            q.g(v[aM][aO]);
                            ++aO;
                            return;
                        }
                        break block23;
                    }
                    case 6: {
                        if (g.at < bB[aM][aO]) {
                            q.g(v[aM][aO]);
                            ++aO;
                            return;
                        }
                        break block23;
                    }
                    case 7: {
                        if ((g.ax == 1 || g.ax == 2) && g.au == bB[aM][aO]) {
                            q.g(v[aM][aO]);
                            ++aO;
                            return;
                        }
                        break block23;
                    }
                    case 8: {
                        if (g.bo > bB[aM][aO]) {
                            if (g.bo <= g.at) {
                                q.g(v[aM][aO]);
                            } else {
                                q.g(v[aM][aO] + 1);
                            }
                            ++aO;
                            return;
                        }
                        break block23;
                    }
                    case 9: {
                        if (g.at < bB[aM][aO]) {
                            g.at = bB[aM][aO] + 1;
                            q.g(v[aM][aO]);
                            return;
                        }
                        break block23;
                    }
                    case 10: {
                        if (g.at > bB[aM][aO]) {
                            g.at = bB[aM][aO] - 1;
                            q.g(v[aM][aO]);
                            return;
                        }
                        break block23;
                    }
                    case 11: {
                        if (q.a(g.at - bB[aM][aO]) < 40) {
                            q.g(v[aM][aO]);
                            ++aO;
                            return;
                        }
                        break block23;
                    }
                    default: {
                        return;
                    }
                }
            }
            if (aM == 10) {
                Display.getDisplay((MIDlet)Midlet.b).setCurrent((Displayable)Midlet.a);
                aM = 0;
            }
        }
    }

    public static void t() {
        Midlet.a = new q();
        q.P();
        q q2 = Midlet.a;
        Midlet.a(Midlet.g == 0 ? 1 : 0);
        q.u();
        int n2 = 20 + c.i[6];
        n2 = 1109021 % (n2 - 6);
        n2 = K == n2 + (g.E[40][0][1] + 11) ? n2 + 2 : 13;
        K = n2 % 2 == 0 ? -1 : n2;
        Midlet.c = true;
        new Thread(q2).start();
    }

    public q() {
        this.setFullScreenMode(true);
        System.gc();
        x = this;
        this.Q();
        bR = this.getKeyCode(8) == -20;
        E = this.getWidth();
        F = this.getHeight();
        G = E / 2;
        H = F / 2;
        b.j = false;
        b.a = b.f() == 1;
        b.c();
        b.a();
        b.h = G - (b.i.stringWidth(b.f[1]) >> 1);
    }

    private void Q() {
        Object object = exception3.getClass().getResourceAsStream("/prefix.txt");
        StringBuffer stringBuffer = new StringBuffer();
        try {
            int n2;
            while ((n2 = ((InputStream)object).read()) != -1) {
                stringBuffer.append((char)n2);
            }
            object = stringBuffer.toString();
            b.e = object;
            System.out.println(String.valueOf(b.e) + " doc len");
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
        }
        InputStream inputStream = exception3.getClass().getResourceAsStream("/sms.txt");
        object = new StringBuffer();
        try {
            int n3;
            while ((n3 = inputStream.read()) != -1) {
                ((StringBuffer)object).append((char)n3);
            }
            String[] stringArray = ((StringBuffer)object).toString();
            stringArray = stringArray.trim();
            stringArray = q.a((String)stringArray, ",");
            bj = new String[stringArray.length];
            int n4 = 0;
            while (n4 < stringArray.length) {
                q.bj[n4] = stringArray[n4];
                System.out.println("dau so " + bj[n4] + "\n");
                ++n4;
            }
            b.d = new String[][]{{"sms://" + bj[4], "sms://" + bj[5], "sms://" + bj[5], "sms://" + bj[4], "sms://" + bj[5], "sms://" + bj[5]}, {"sms://" + bj[4], "sms://" + bj[4], "sms://" + bj[5], "sms://" + bj[6]}};
            b.b = "sms://" + stringArray[6];
            return;
        }
        catch (Exception exception) {
            Exception exception3 = exception;
            exception.printStackTrace();
            return;
        }
    }

    private static String[] a(String string, String string2) {
        int n2 = 0;
        int n3 = 0;
        int n4 = string2.length();
        n3 = string.indexOf(string2, 0);
        while (n3 != -1) {
            n3 += n4;
            n3 = string.indexOf(string2, n3);
            ++n2;
        }
        String[] stringArray = new String[n2 + 1];
        n3 = string.indexOf(string2);
        int n5 = 0;
        int n6 = 0;
        while (n3 != -1) {
            stringArray[n6] = string.substring(n5, n3);
            n5 = n3 + n4;
            n3 = string.indexOf(string2, n5);
            ++n6;
        }
        stringArray[n6] = string.substring(n5, string.length());
        return stringArray;
    }

    protected final void keyPressed(int n2) {
        if (m) {
            return;
        }
        if (n2 == 35) {
            if (!cs.equals("1984")) {
                if (cs.equals("30538")) {
                    g.aO = 500000;
                    g.aN = 500000;
                    g.aM = 500000;
                    g.aL = 500000;
                    q.a("Unlimit HP MP");
                } else if (cs.equals("38305")) {
                    g.aJ = 10000;
                    q.a("Unlimit Damage");
                } else if (cs.equals("1982")) {
                    g.c(100000);
                } else if (cs.equals("1983")) {
                    aj += 50000;
                    q.a("Money+=50000");
                } else if (cs.equals("12456")) {
                    TextBox textBox = new TextBox("Type anything:", "", 50, 0);
                    textBox.addCommand(new Command("OK", 4, 0));
                    textBox.addCommand(new Command("Back", 2, 0));
                    textBox.setCommandListener((CommandListener)this);
                    Display.getDisplay((MIDlet)Midlet.b).setCurrent((Displayable)textBox);
                } else if (cs.equals("1981")) {
                    int n3 = 0;
                    while (n3 < bt.length) {
                        q.bt[n3] = true;
                        ++n3;
                    }
                    q.a("Unlock all map!");
                } else if (cs.equals("8463")) {
                    int n4 = ez + (int)((System.currentTimeMillis() - eA) / 1000L);
                    q.a(String.valueOf(n4 / 3600) + ":" + n4 % 3600 / 60 + ":" + n4 % 60);
                }
            }
            J = true;
            cs = "";
        }
        if (n2 >= 48 && n2 <= 57 && (cs = String.valueOf(cs) + (char)n2).length() > 10) {
            cs = "";
        }
        if (bR) {
            switch (n2) {
                case -21: 
                case 21: {
                    n2 = -6;
                    break;
                }
                case -22: 
                case 22: {
                    n2 = -7;
                    break;
                }
                case -23: {
                    n2 = -8;
                    break;
                }
                case -2: {
                    n2 = -3;
                    break;
                }
                case -5: {
                    n2 = -4;
                    break;
                }
                case -6: {
                    n2 = -2;
                    break;
                }
                case -20: {
                    n2 = -5;
                }
            }
        }
        if (aA && n2 != -5 && n2 != 57) {
            return;
        }
        q.k(n2);
    }

    protected final void keyReleased(int n2) {
        if (bR) {
            switch (n2) {
                case -21: 
                case 21: {
                    n2 = -6;
                    break;
                }
                case -22: 
                case 22: {
                    n2 = -7;
                    break;
                }
                case -23: {
                    n2 = -8;
                    break;
                }
                case -2: {
                    n2 = -3;
                    break;
                }
                case -5: {
                    n2 = -4;
                    break;
                }
                case -6: {
                    n2 = -2;
                    break;
                }
                case -20: {
                    n2 = -5;
                }
            }
        }
        if (aA && n2 != -5 && n2 != 57) {
            return;
        }
        q.l(n2);
    }

    private static void k(int n2) {
        switch (n2) {
            case -1: {
                q.C[2] = true;
                q.D[2] = true;
                return;
            }
            case -2: {
                q.C[8] = true;
                q.D[8] = true;
                return;
            }
            case -3: {
                q.C[4] = true;
                q.D[4] = true;
                return;
            }
            case -4: {
                q.C[6] = true;
                q.D[6] = true;
                return;
            }
            case -5: 
            case 10: {
                q.C[5] = true;
                q.D[5] = true;
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
                q.C[n2 - 48] = true;
                q.D[n2 - 48] = true;
                return;
            }
            case 42: {
                q.C[10] = true;
                q.D[10] = true;
                return;
            }
            case 35: {
                q.C[11] = true;
                q.D[11] = true;
                return;
            }
            case -21: 
            case -6: {
                q.C[12] = true;
                q.D[12] = true;
                return;
            }
            case -22: 
            case -7: {
                q.C[13] = true;
                q.D[13] = true;
                return;
            }
        }
    }

    private static void l(int n2) {
        switch (n2) {
            case -1: {
                q.C[2] = false;
                q.D[2] = false;
                return;
            }
            case -2: {
                q.C[8] = false;
                q.D[8] = false;
                return;
            }
            case -3: {
                q.C[4] = false;
                q.D[4] = false;
                return;
            }
            case -4: {
                q.C[6] = false;
                q.D[6] = false;
                return;
            }
            case -5: 
            case 10: {
                q.C[5] = false;
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
                q.C[n2 - 48] = false;
                return;
            }
            case 42: {
                q.C[10] = false;
                return;
            }
            case 35: {
                q.C[11] = false;
                return;
            }
            case -21: 
            case -6: {
                q.C[12] = false;
                return;
            }
            case -22: 
            case -7: {
                q.C[13] = false;
                return;
            }
        }
    }

    protected final void paint(Graphics object) {
        Object object2;
        try {
            switch (K) {
                case 1: {
                    int n2;
                    object2 = object;
                    object2.setClip(-1, -1, ab + 2, ac + 2);
                    q.a((Graphics)object2);
                    object2.translate(-ay, -az);
                    if (as != 8) {
                        j.b((Graphics)object2);
                    }
                    if (b) {
                        j.c((Graphics)object2);
                    } else {
                        j.d((Graphics)object2);
                    }
                    j.a((Graphics)object2);
                    o.a.a((Graphics)object2);
                    c.a((Graphics)object2);
                    if (as == 8) {
                        j.b((Graphics)object2);
                    }
                    object = object2;
                    if (as != 14 || !g.w) {
                        n2 = 0;
                        while (n2 < aW) {
                            if (ay <= aY[n2] + 12 && ay + ab >= aY[n2] - 12 && az <= aZ[n2] && az + ac >= aZ[n2] - 24) {
                                object.drawImage(dP, aY[n2], aZ[n2], 33);
                            }
                            ++n2;
                        }
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < aP) {
                        if (ay <= aR[n2] + 12 && ay + ab >= aR[n2] - 12 && az <= aS[n2] && az + ac >= aS[n2] - 24) {
                            if (aT[n2] == 1) {
                                object.drawImage(dM[aU[n2]], aR[n2], aS[n2], 33);
                            } else {
                                object.drawRegion(dM[aU[n2]], 0, 0, dM[aU[n2]].getWidth(), dM[aU[n2]].getHeight(), 2, aR[n2], aS[n2], 33);
                            }
                        }
                        ++n2;
                    }
                    t.a((Graphics)object2);
                    e.a((Graphics)object2);
                    p.a((Graphics)object2);
                    object = object2;
                    n2 = 0;
                    while (n2 < dn) {
                        if (dq[n2] != 0 && do[n2] >= ay - 10 && do[n2] <= ay + ab + 10 && dp[n2] >= az && dp[n2] <= az + ac + 20) {
                            object.drawRegion(dR[dv[n2]], 0, dt[n2] * 15, 8, 15, 0, do[n2], dp[n2], 33);
                        }
                        ++n2;
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < cY) {
                        if (dc[n2] != 0 && da[n2] >= ay - 10 && da[n2] <= ay + ab + 10 && db[n2] >= az && db[n2] <= az + ac + 20) {
                            object.drawRegion(dY[cZ[n2]], 0, df[n2] * 15, 13, 15, 0, da[n2], db[n2], 33);
                        }
                        ++n2;
                    }
                    object = object2;
                    if (aG && !g.l && eD != 22 && ay + ab >= aH - (eG >> 1) && ay <= aH + (eG >> 1) && az <= aI && az + ac >= aI - aJ) {
                        n2 = 0;
                        if (eF == 0) {
                            n2 = -2;
                            if (eD == 1 && at % 10 > 5) {
                                n2 = -1;
                            }
                            if (eE == 1) {
                                object.drawImage(dX[4], aH, aI + 1, 33);
                            } else {
                                object.drawRegion(dX[4], 0, 0, dX[4].getWidth(), dX[4].getHeight(), 2, aH, aI + 1, 33);
                            }
                        }
                        if (eE == 1) {
                            object.drawImage(dX[eF], aH, aI + n2 + 1, 33);
                        } else {
                            object.drawRegion(dX[eF], 0, 0, dX[eF].getWidth(), dX[eF].getHeight(), 2, aH, aI + n2 + 1, 33);
                        }
                        if (eD == 7) {
                            if (eE == 1) {
                                object.drawImage(dX[3], aH + 10, aI - 25 + 1, 0);
                            } else {
                                object.drawRegion(dX[3], 0, 0, dX[3].getWidth(), dX[3].getHeight(), 2, aH - 10, aI - 25 + 1, 24);
                            }
                        }
                        if (eJ != 0) {
                            object.setColor(0x330011);
                            object.drawRect(aH - (eG >> 1) - 1, aI - aJ - 7, eG + 2, 4);
                            n2 = aK * eG / aL;
                            object.setColor(0xEE0000);
                            object.fillRect(aH - (eG >> 1), aI - aJ - 6, n2, 3);
                            object.setColor(0x440022);
                            object.fillRect(aH - (eG >> 1) + n2, aI - aJ - 6, eG - n2 + 1, 3);
                        }
                    }
                    a.a((Graphics)object2);
                    n.a((Graphics)object2);
                    g.c((Graphics)object2);
                    object = object2;
                    if (dF != 0 && (n2 = dF % 3) < 2) {
                        object.drawImage(dV[n2], dD, dE, 17);
                    }
                    object = object2;
                    if (am != -1) {
                        if (dH == -1) {
                            object.drawImage(dW[dG], ak, al, 3);
                        } else {
                            object.drawRegion(dW[dG], 0, 0, dW[dG].getWidth(), dW[dG].getHeight(), 2, ak, al, 3);
                        }
                    }
                    if (as != 13) {
                        g.a((Graphics)object2);
                    }
                    g.d((Graphics)object2);
                    i.a((Graphics)object2);
                    g.b((Graphics)object2);
                    if (as == 13) {
                        g.a((Graphics)object2);
                    }
                    m.a((Graphics)object2);
                    object = object2;
                    if (av != 0 && dN != null) {
                        if (bi == 6) {
                            object.drawImage(dN, ed, ee + (at % (15 + c.f[0]) > 4 ? 0 : 1), 33);
                        } else {
                            object.drawImage(dN, ed, ee, 33);
                        }
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < 2) {
                        if (cL[n2] != -1) {
                            if (cN[n2] == 1) {
                                object.drawImage(dZ[cM[n2]], cJ[n2], cK[n2], 3);
                            } else {
                                object.drawRegion(dZ[cM[n2]], 0, 0, dZ[cM[n2]].getWidth(), dZ[cM[n2]].getHeight(), 2, cJ[n2], cK[n2], 3);
                            }
                        }
                        ++n2;
                    }
                    object = object2;
                    if (!b) {
                        n2 = 0;
                        while (n2 < 2) {
                            if (cU[n2] != -1) {
                                object.drawImage(dT[n2][cU[n2]], cS[n2], cT[n2], 3);
                            }
                            ++n2;
                        }
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < cv) {
                        if (cy[n2] != -1) {
                            object.drawImage(dJ[cB[n2]][cz[n2] > 0 ? 0 : 1], cw[n2], cx[n2], 3);
                        }
                        ++n2;
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < dh) {
                        if (ah[n2] != -1) {
                            object.drawImage(dU[dk[n2]], di[n2], dj[n2], 3);
                        }
                        ++n2;
                    }
                    object = object2;
                    if (d.a && d.c != 2) {
                        object.drawRegion(d.b, 0, d.c == 0 ? 24 : 0, 24, 24, 0, d.d, d.e, 0);
                    }
                    object = object2;
                    n2 = 0;
                    while (n2 < cC) {
                        if (cF[n2] != 0) {
                            object.drawImage(dL, cD[n2], cE[n2], 3);
                        }
                        ++n2;
                    }
                    object = object2;
                    if (!b) {
                        n2 = 0;
                        while (n2 < 2) {
                            if (cQ[n2] != -1) {
                                object.drawImage(ec[cR[n2]], cO[n2], cP[n2], 3);
                            }
                            ++n2;
                        }
                    }
                    j.e((Graphics)object2);
                    object = object2;
                    n2 = 0;
                    while (n2 < 5) {
                        if (dB[n2] != -1) {
                            Midlet.a(object, dw[n2], dC[n2], dx[n2], dy[n2], 0);
                        }
                        ++n2;
                    }
                    object = object2;
                    if (aE == as) {
                        object.drawImage(dK[eu], ev, ew, 3);
                    }
                    q.b((Graphics)object2);
                    object = object2;
                    if (s.a && s.c != 0) {
                        object.drawRegion(s.b, 0, (s.f >> 2) * 50, 50, 50, 0, s.d, s.e, 3);
                    }
                    o.b.a((Graphics)object2);
                    if (J) {
                        q.c((Graphics)object2);
                    }
                    if (cr > 1) {
                        q.d((Graphics)object2);
                    }
                    if (cV) {
                        object = object2;
                        object.translate(-object.getTranslateX(), -object.getTranslateY());
                        object.setClip(-1, -1, E + 2, F + 2);
                        object.setColor(0);
                        object.fillRect(0, 0, E, 21);
                        object.setColor(10275899);
                        object.fillRect(0, 19, E, 1);
                        if (at % 10 > 2) {
                            Midlet.a(object, cX, ad, 4, 2);
                        }
                    }
                    if (bD) {
                        object2.setColor(0);
                        object2.fillRect(0, 0, E, F);
                    }
                    if (bd) {
                        q.e((Graphics)object2);
                    }
                    return;
                }
                case 2: {
                    object2 = object;
                    object2.setColor(0);
                    object2.fillRect(0, 0, E, 21);
                    Midlet.a((Graphics)object2, "Loading... " + bl + "%", E >> 1, 4, 2);
                    return;
                }
                case 3: {
                    object2 = object;
                    object2.setClip(-1, -1, E + 2, F + 1);
                    q.a((Graphics)object2);
                    object2.translate(-ay, -az);
                    if (b) {
                        j.c((Graphics)object2);
                    } else {
                        j.d((Graphics)object2);
                    }
                    j.a((Graphics)object2);
                    j.e((Graphics)object2);
                    object2.translate(-object2.getTranslateX(), -object2.getTranslateY());
                    object2.setColor(0);
                    object2.fillRect(0, 0, ab, bV);
                    object2.fillRect(0, bW, ab, F - bW + 1);
                    if (bM == 40) {
                        object2.setColor(10275899);
                        object2.fillRect(0, bV - 1, E, 1);
                        object2.fillRect(0, bW + 1, E, 1);
                    }
                    object2.drawImage(ea, G - 4 - bZ, H - 10 + ca, 3);
                    if (bM > 20) {
                        object2.drawImage(dO[bN], G + 30 - bZ, H + ca, 3);
                    }
                    Midlet.a((Graphics)object2, "*", G - 55 - at % 3, bY, 0);
                    Midlet.a((Graphics)object2, "/", G + 55 + at % 3, bY, 1);
                    Midlet.a((Graphics)object2, bL[bS], G, bY, 3);
                    return;
                }
                case 4: {
                    object2 = object;
                    object2.setColor(0);
                    object2.fillRect(0, 0, E, F);
                    if (fc != null && fe >= 0) {
                        object2.drawRegion(fc, 0, fe * 51, 71, 51, 0, E >> 1, F >> 1, 3);
                    }
                    return;
                }
                case 5: {
                    q.i(object);
                    return;
                }
                case 6: {
                    q.j(object);
                    return;
                }
                case 7: {
                    q.l(object);
                    return;
                }
                case 14: {
                    q.m(object);
                    return;
                }
                case 8: {
                    q.h(object);
                    return;
                }
                case 9: {
                    q.g(object);
                    return;
                }
                case 10: {
                    q.k(object);
                    return;
                }
                case 11: {
                    q.f(object);
                    return;
                }
                case 12: {
                    q.f(object);
                    return;
                }
                case 13: {
                    b.a(object);
                    return;
                }
                case 15: {
                    object2 = object;
                    object2.translate(-object2.getTranslateX(), -object2.getTranslateY());
                    q.c((Graphics)object2);
                    q.b((Graphics)object2);
                    object2.setClip(0, 0, E, F);
                    object2.setColor(0);
                    object2.fillRect(ad - 64, fn - 19, 129, 120);
                    object2.fillRect(0, F - 13, E, 13);
                    Midlet.a((Graphics)object2, "Ch\u1ecdn", 3, F - 13, 0);
                    Midlet.a((Graphics)object2, "Quay l\u1ea1i", E - 3, F - 13, 1);
                    object2.setColor(0xBB6611);
                    object2.drawRect(ad - 63, fn - 18, 126, 117);
                    object2.setColor(6562304);
                    object2.fillRect(ad - 61, fn - 16, 123, 115);
                    Midlet.a((Graphics)object2, "Ch\u1ecdn v\u0169 kh\u00ed", ad, fn, 2);
                    object2.setColor(0);
                    object2.fillRect(ad - 10, fn + 25, 19, 20);
                    object2.fillRect(ad - 10, fn + 25, 19, 20);
                    object2.setColor(4856064);
                    object2.drawRect(ad - 10, fn + 25, 20, 20);
                    object2.drawRect(ad - 10, fn + 25, 20, 20);
                    object2.drawImage(fu[bs[fp]], ad - 8, fn + 28, 0);
                    Midlet.a((Graphics)object2, bg[fp], ad, fn + 60, 2);
                    Midlet.a((Graphics)object2, "*", ad - 28 - fv % 3, fn + 28, 0);
                    Midlet.a((Graphics)object2, "/", ad + 30 + fv % 3, fn + 28, 1);
                    object2.drawImage(fw[0], ad - 64, fn - 19, 20);
                    object2.drawImage(fw[1], ad + 65, fn - 19, 24);
                    object2.drawImage(fw[2], ad - 64, fn + 101, 36);
                    object2.drawImage(fw[3], ad + 65, fn + 101, 40);
                }
            }
            return;
        }
        catch (Exception exception) {
            object2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    protected final void pointerDragged(int n2, int n3) {
        A = n2;
        B = n3;
    }

    protected final void pointerPressed(int n2, int n3) {
        A = n2;
        B = n3;
    }

    protected final void pointerReleased(int n2, int n3) {
        z = true;
        A = n2;
        B = n3;
    }

    /*
     * Unable to fully structure code
     */
    public final void run() {
        if (!q.y) {
            q.y = true;
            q.K = 13;
            b.g = 8;
        }
        try {
            while (q.y) {
                var1_1 = System.currentTimeMillis();
                switch (q.K) {
                    case 1: {
                        if (++q.at == 10000) {
                            q.at = 0;
                        }
                        if (g.aN <= 0 && !q.by) {
                            g.aN = 0;
                            q.by = true;
                            q.a("\u0111\u00e3 h\u1ebft MP");
                        }
                        if (q.by && g.aN > 0) {
                            q.by = false;
                        }
                        if (q.m && q.as == 13) {
                            if (q.a(g.R - (g.at - g.aF * 18)) <= 5) {
                                g.V = 18;
                                g.R = g.at - g.aF * 15;
                                g.S = g.au;
                                q.m = false;
                            } else {
                                g.ad = g.R < g.at - g.aF * 15 ? 1 : -1;
                                g.T = 5 * g.ad;
                                g.S = 264;
                                g.V = 2;
                            }
                        }
                        if (q.as == 14 && g.D == 3 && !g.x && g.w && g.at >= 2544) {
                            g.x = true;
                            g.t = true;
                            ++q.aO;
                            if (g.aW >= 20) {
                                q.g(37);
                            } else {
                                q.g(49);
                            }
                        }
                        if (q.aM == 34 && g.aW >= 20 && g.a == 11) {
                            ++q.aO;
                            g.a = 4;
                        }
                        if (!(q.as != 9 || g.r || g.p || q.j)) {
                            q.j(19);
                            q.j = true;
                        }
                        if (q.as == 9 && g.s && g.aW < 13 && !q.bA) {
                            q.j(33);
                            q.bA = true;
                        }
                        if (q.as == 9 && g.s && g.bm && g.O && !t.m && g.at >= 2568 && !q.aA) {
                            q.g(51);
                        }
                        if (q.as == 9 && g.r && !g.s && q.a(g.at - c.d[1]) < 50 && g.au == c.e[1]) {
                            q.g(30);
                            ++q.aO;
                            g.s = true;
                        }
                        if (q.as == 12 && g.bc == 4 && !q.k) {
                            q.k = true;
                            q.j(21);
                        }
                        if (q.as == 12 && g.at >= 2424 && !q.l) {
                            q.l = true;
                            q.g(33);
                            g.O = true;
                        }
                        if (g.at < 0) {
                            if (j.g != -1) {
                                q.A();
                                g.aS = g.at = j.l - 1;
                                g.aT = g.au = j.m;
                                Midlet.f = true;
                                q.i(j.g);
                                break;
                            }
                        } else if (g.at >= j.c - 24) {
                            if (j.h != -1) {
                                q.A();
                                if (j.i != -1 && g.au > j.v) {
                                    g.aS = g.at = j.p;
                                    g.aT = g.au = j.q;
                                    Midlet.f = true;
                                    q.i(j.i);
                                    break;
                                }
                                g.aS = g.at = j.n;
                                g.aT = g.au = j.o;
                                Midlet.f = true;
                                q.i(j.h);
                                break;
                            }
                        } else if (g.au + g.aw >= j.d && g.ax != 6) {
                            if (j.k != -1) {
                                q.A();
                                g.aS = g.at = j.t;
                                g.aT = g.au = j.u;
                                Midlet.f = true;
                                q.i(j.k);
                                g.ax = 4;
                                break;
                            }
                        } else if (g.au < 0) {
                            if (q.as == 4) {
                                if (g.at >= 2688) {
                                    q.A();
                                    g.aS = g.at = j.r;
                                    g.aT = g.au = j.s;
                                    Midlet.f = true;
                                    q.i(j.j);
                                    g.ax = 3;
                                    g.aw = -3;
                                    g.av = 0;
                                    break;
                                }
                                g.ax = 4;
                            } else if (j.j != -1) {
                                q.A();
                                g.aS = g.at = j.r;
                                g.aT = g.au = j.s;
                                Midlet.f = true;
                                q.i(j.j);
                                g.ax = 3;
                                g.aw = -3;
                                g.av = 0;
                                break;
                            }
                        }
                        if (!q.b && q.fX < 12 && q.eZ[q.fX] != -1 && q.at % 50 == 0) {
                            o.b.addElement(new r(q.ay + q.a(q.eY.nextInt()) % q.ab, q.az, q.eZ[q.fX]));
                        }
                        q.P();
                        q.M();
                        if (q.K()) break;
                        if (!q.b && q.ar != null) {
                            var3_3 = 0;
                            while (var3_3 < 2) {
                                if (q.at % (var3_3 + 2 << 3) == 0) {
                                    v0 = var3_3;
                                    q.L[v0] = q.L[v0] + 1;
                                    if (q.L[var3_3] > q.ab + (q.aq.getWidth() >> 1)) {
                                        q.L[var3_3] = -(q.aq.getWidth() >> 1);
                                    }
                                }
                                ++var3_3;
                            }
                        }
                        if (!q.bd) ** GOTO lbl141
                        if (q.D[5]) {
                            q.c();
                            q.F();
                            if (q.eP == q.eO) {
                                q.bd = false;
                                q.y();
                            }
                        }
                        q.J = true;
                        ** GOTO lbl535
lbl141:
                        // 1 sources

                        if (!q.D[12]) ** GOTO lbl151
                        if (g.ax != 6) ** GOTO lbl146
                        q.c();
                        q.b();
                        ** GOTO lbl535
lbl146:
                        // 1 sources

                        q.U();
                        q.K = 5;
                        q.b();
                        q.c();
                        ** GOTO lbl201
lbl151:
                        // 1 sources

                        if (!q.D[0]) ** GOTO lbl158
                        q.fa = true;
                        q.T();
                        q.K = 15;
                        q.J = true;
                        q.D[0] = false;
                        ** GOTO lbl201
lbl158:
                        // 1 sources

                        if (!q.D[6]) ** GOTO lbl162
                        g.aR = 2;
                        q.D[6] = false;
                        ** GOTO lbl201
lbl162:
                        // 1 sources

                        if (!q.D[4]) ** GOTO lbl166
                        g.aR = -2;
                        q.D[4] = false;
                        ** GOTO lbl201
lbl166:
                        // 1 sources

                        if (!q.D[9]) ** GOTO lbl169
                        q.D[9] = false;
                        ** GOTO lbl535
lbl169:
                        // 1 sources

                        if (!q.D[7]) ** GOTO lbl172
                        q.D[7] = false;
                        ** GOTO lbl201
lbl172:
                        // 1 sources

                        if (!q.D[10] || g.ax == 6) ** GOTO lbl187
                        q.D[10] = false;
                        if (g.aL >= g.aM) ** GOTO lbl201
                        var3_3 = 0;
                        while (var3_3 < 3) {
                            if (q.br[var3_3] <= 0) ** GOTO lbl184
                            v1 = var3_3;
                            q.br[v1] = q.br[v1] - 1;
                            if ((g.aL += q.ft[var3_3]) > g.aM) {
                                g.aL = g.aM;
                            }
                            q.J = true;
                            ** GOTO lbl201
lbl184:
                            // 1 sources

                            ++var3_3;
                        }
                        ** GOTO lbl201
lbl187:
                        // 1 sources

                        if (!q.D[11] || g.ax == 6) ** GOTO lbl201
                        q.D[11] = false;
                        if (g.aN >= g.aO) ** GOTO lbl201
                        var3_3 = 3;
                        while (var3_3 < 6) {
                            if (q.br[var3_3] <= 0) ** GOTO lbl199
                            v2 = var3_3;
                            q.br[v2] = q.br[v2] - 1;
                            if ((g.aN += q.ft[var3_3]) > g.aO) {
                                g.aN = g.aO;
                            }
                            q.J = true;
                            break;
lbl199:
                            // 1 sources

                            ++var3_3;
                        }
lbl201:
                        // 12 sources

                        if (g.ax != 1) ** GOTO lbl329
                        if (!q.D[13]) ** GOTO lbl215
                        q.c();
                        if (q.V.equals("Giao ti\u1ebfp")) {
                            c.a(g.bi);
                        } else if (q.V.equals("Tr\u00f2 chuy\u1ec7n")) {
                            q.c();
                            g.M[g.bj] = -g.aF;
                            q.a(g.G[g.bj], 0, g.K[g.bj], g.L[g.bj] - 44);
                        } else if (q.V.equals("N\u00f3i chuy\u1ec7n") && g.bg && g.O) {
                            q.a(q.bO[0], 0, g.R, g.S - 44);
                        }
                        ** GOTO lbl535
lbl215:
                        // 1 sources

                        if (!q.C[2]) ** GOTO lbl262
                        var3_3 = 0;
                        while (var3_3 < q.aP) {
                            if (g.au != q.aS[var3_3] || q.a(g.at - q.aR[var3_3]) >= 30) ** GOTO lbl240
                            g.at = q.aR[var3_3];
                            g.ax = 18;
                            if (q.as == 13) {
                                if (q.a(g.R - (g.at - g.aF * 18)) <= 5) {
                                    g.V = 18;
                                    if (g.R != g.at - g.aF * 15) {
                                        g.R = g.at - g.aF * 15;
                                    }
                                } else {
                                    q.m = true;
                                    g.V = 2;
                                    g.au = 264;
                                    if (g.R < g.at - g.aF * 15) {
                                        g.ad = 1;
                                    }
                                    if (g.R >= g.at - g.aF * 15) {
                                        g.ad = -1;
                                    }
                                    g.av = 5 * g.ad;
                                }
                            }
                            q.b();
                            q.aQ = var3_3;
                            q.aT[var3_3] = g.aF;
                            q.aU[var3_3] = 0;
                            ** GOTO lbl535
lbl240:
                            // 1 sources

                            ++var3_3;
                        }
                        var3_3 = 0;
                        while (var3_3 < p.a) {
                            if (p.f[var3_3] != 0 || g.au != p.c[var3_3] || q.a(g.at - p.b[var3_3]) >= 30) ** GOTO lbl251
                            q.b();
                            p.g = var3_3;
                            p.d[var3_3] = g.aF;
                            p.f[var3_3] = 1;
                            p.e[var3_3] = 1;
                            ** GOTO lbl535
lbl251:
                            // 1 sources

                            ++var3_3;
                        }
                        g.aw = g.f != false ? -10 : -8;
                        g.ax = 3;
                        q.aX = -1;
                        g.aG = 0;
                        g.bi = -1;
                        g.bj = -1;
                        q.V = " ";
                        q.J = true;
                        ** GOTO lbl318
lbl262:
                        // 1 sources

                        if (q.C[4]) {
                            if (g.aF == 1) {
                                g.aF = -1;
                            } else {
                                g.ax = 2;
                                g.av = -q.aC;
                            }
                            g.bi = -1;
                            g.bj = -1;
                            q.V = " ";
                            q.J = true;
                        } else if (q.C[6]) {
                            if (g.aF == -1) {
                                g.aF = 1;
                            } else {
                                g.ax = 2;
                                g.av = q.aC;
                            }
                            g.bj = -1;
                            g.bi = -1;
                            q.V = " ";
                            q.J = true;
                        } else if (q.C[8]) {
                            g.aG = 0;
                            g.aH = 0;
                            g.ax = 8;
                            if (p.g != -1) {
                                p.f[p.g] = 0;
                                p.c[p.g] = g.au;
                                p.e[p.g] = 0;
                                q.aC = 5;
                                p.g = -1;
                            }
                        } else if (q.C[1]) {
                            g.aw = -10;
                            g.aF = -1;
                            g.av = -4;
                            g.ax = 3;
                            q.aX = -1;
                            g.aG = 0;
                            g.bi = -1;
                            g.bj = -1;
                            q.V = " ";
                            q.J = true;
                        } else if (q.C[3]) {
                            g.aw = -10;
                            g.aF = 1;
                            g.av = 4;
                            g.ax = 3;
                            q.aX = -1;
                            g.aG = 0;
                            g.bi = -1;
                            g.bj = -1;
                            q.V = " ";
                            q.J = true;
                        }
lbl318:
                        // 8 sources

                        if (q.D[5]) {
                            g.ax = 5;
                            g.aV = 0;
                            g.aG = 0;
                            g.aw = 0;
                            q.D[5] = false;
                            g.bi = -1;
                            g.bj = -1;
                            q.V = " ";
                            q.J = true;
                        }
                        ** GOTO lbl535
lbl329:
                        // 1 sources

                        if (g.ax == 2) {
                            if (q.C[2]) {
                                q.aX = -1;
                                g.aw = g.f != false ? -10 : -8;
                                g.ax = 3;
                                g.aG = 0;
                            } else if (q.C[4]) {
                                if (g.aF == 1) {
                                    g.aF = -1;
                                } else {
                                    g.av = -q.aC + g.aR;
                                }
                            } else if (q.C[6]) {
                                if (g.aF == -1) {
                                    g.aF = 1;
                                } else {
                                    g.av = q.aC + g.aR;
                                }
                            }
                            if (q.D[5]) {
                                g.ax = 5;
                                g.aV = 0;
                                g.aG = 0;
                                g.aw = 0;
                                q.D[5] = false;
                            }
                        } else if (g.ax == 11) {
                            if (q.C[2]) {
                                g.aw = -10;
                                g.ax = 3;
                                g.aG = 0;
                            } else if (q.C[4]) {
                                if (g.aF == 1) {
                                    g.aF = -1;
                                } else {
                                    g.av = -g.aP + g.aR;
                                }
                            } else if (q.C[6]) {
                                if (g.aF == -1) {
                                    g.aF = 1;
                                } else {
                                    g.av = g.aP + g.aR;
                                }
                            }
                        } else if (g.ax == 3) {
                            if (q.C[4]) {
                                if (g.aF == 1) {
                                    g.aF = -1;
                                } else {
                                    g.av = -q.aC;
                                }
                            } else if (q.C[6]) {
                                if (g.aF == -1) {
                                    g.aF = 1;
                                } else {
                                    g.av = q.aC;
                                }
                            }
                            if (q.D[5]) {
                                g.ax = 5;
                                g.aV = 0;
                                g.aG = 0;
                                q.D[5] = false;
                            } else if (q.C[2] && g.f && g.aN > 0 && g.aw > -4 && g.aG == 0) {
                                ++g.aG;
                                g.aw = -7;
                                if ((g.aN -= 5) < 0) {
                                    g.aN = 0;
                                }
                                q.J = true;
                                q.b(-1, g.at - -8, g.au);
                                q.b(1, g.at - 8, g.au);
                                q.c(g.at, g.au);
                            }
                        } else if (g.ax == 4) {
                            if (q.D[2] && p.g != -1) {
                                q.c();
                                g.au -= 3;
                                g.ay = 7;
                            }
                            if (q.C[4]) {
                                if (g.aF == 1) {
                                    g.aF = -1;
                                } else {
                                    g.av = -q.aC;
                                }
                            } else if (q.C[6]) {
                                if (g.aF == -1) {
                                    g.aF = 1;
                                } else {
                                    g.av = q.aC;
                                }
                            }
                            if (q.D[5] && (g.aw > 4 || p.g != -1)) {
                                g.ax = 5;
                                g.aG = 0;
                                g.aV = 0;
                                q.D[5] = false;
                            }
                        } else if (g.ax == 5) {
                            if (q.C[4]) {
                                if (g.aF == 1) {
                                    g.aF = -1;
                                } else {
                                    g.av = -q.aC + 2;
                                }
                            } else if (q.C[6]) {
                                if (g.aF == -1) {
                                    g.aF = 1;
                                } else {
                                    g.av = q.aC - 2;
                                }
                            }
                            if (q.D[5]) {
                                g.aZ = true;
                                q.D[5] = false;
                            }
                        } else if (g.ax == 18) {
                            if (q.C[2]) {
                                g.aw = -10;
                                g.ax = 21;
                                if (q.as == 13) {
                                    g.V = 21;
                                }
                                g.aG = 10;
                                q.aX = -1;
                            } else if (q.C[4]) {
                                if (g.aF == 1) {
                                    g.aF = -1;
                                    q.aT[q.aQ] = -1;
                                } else {
                                    g.aG = 1;
                                    g.ax = 19;
                                    if (q.as == 13) {
                                        g.V = 19;
                                    }
                                    g.aG = 10;
                                    g.av = -q.aD;
                                }
                            } else if (q.C[6]) {
                                if (g.aF == -1) {
                                    g.aF = 1;
                                    q.aT[q.aQ] = 1;
                                } else {
                                    g.ax = 19;
                                    if (q.as == 13) {
                                        g.V = 19;
                                    }
                                    g.aG = 10;
                                    g.av = q.aD;
                                }
                            } else if (q.C[8]) {
                                g.ax = 1;
                                if (q.as == 13) {
                                    g.V = 1;
                                }
                                q.aQ = -1;
                            }
                        } else if (g.ax == 19) {
                            if (q.C[2]) {
                                g.aw = -10;
                                g.ax = 21;
                                q.aX = -1;
                                g.aG = 10;
                            } else if (q.C[4]) {
                                if (g.aF == 1) {
                                    g.aF = -1;
                                    q.aT[q.aQ] = -1;
                                } else {
                                    g.av = -q.aD;
                                }
                            } else if (q.C[6]) {
                                if (g.aF == -1) {
                                    g.aF = 1;
                                    q.aT[q.aQ] = 1;
                                } else {
                                    g.av = q.aD;
                                }
                            }
                        } else if (g.ax == 21) {
                            if (q.C[4]) {
                                if (g.aF == 1) {
                                    g.aF = -1;
                                    q.aT[q.aQ] = -1;
                                } else {
                                    g.av = -q.aD;
                                }
                            } else if (q.C[6]) {
                                if (g.aF == -1) {
                                    g.aF = 1;
                                    q.aT[q.aQ] = 1;
                                } else {
                                    g.av = q.aD;
                                }
                            }
                        } else if (g.ax == 20) {
                            if (q.C[4]) {
                                if (g.aF == 1) {
                                    g.aF = -1;
                                    q.aT[q.aQ] = -1;
                                } else {
                                    g.av = -q.aD;
                                }
                            } else if (q.C[6]) {
                                if (g.aF == -1) {
                                    g.aF = 1;
                                    q.aT[q.aQ] = 1;
                                } else {
                                    g.av = q.aD;
                                }
                            }
                        } else if (g.ax == 12 && q.C[2]) {
                            g.aw = -10;
                            g.ax = 3;
                            g.aq = -1;
                            g.aG = 0;
                        }
lbl535:
                        // 41 sources

                        q.J();
                        var3_3 = 0;
                        block71: while (var3_3 < q.aW) {
                            switch (q.ba[var3_3]) {
                                case 2: {
                                    if (q.as == 3 && g.n) break block71;
                                    v3 = var3_3;
                                    q.aY[v3] = q.aY[v3] + q.bb[var3_3];
                                    if (q.bb[var3_3] > 0) {
                                        v4 = var3_3;
                                        q.bb[v4] = q.bb[v4] - 1;
                                    } else if (q.bb[var3_3] < 0) {
                                        v5 = var3_3;
                                        q.bb[v5] = q.bb[v5] + 1;
                                    } else {
                                        q.ba[var3_3] = 1;
                                    }
                                    if (!j.a(q.aY[var3_3] - 12, q.aZ[var3_3] + 1, 2) && !j.a(q.aY[var3_3] + 12, q.aZ[var3_3] + 1, 2)) {
                                        q.ba[var3_3] = 4;
                                        q.bc[var3_3] = 2;
                                    }
                                    if (j.a(q.aY[var3_3] + 12, q.aZ[var3_3] - 2, 4) || j.a(q.aY[var3_3] - 12, q.aZ[var3_3] - 2, 8)) {
                                        q.aY[var3_3] = j.e(q.aY[var3_3]) + 12;
                                    }
                                    ** GOTO lbl615
                                }
                                case 4: {
                                    v6 = var3_3;
                                    q.aZ[v6] = q.aZ[v6] + q.bc[var3_3];
                                    if (q.bc[var3_3] < 20) {
                                        v7 = var3_3;
                                        q.bc[v7] = q.bc[v7] + 1;
                                    }
                                    if (j.a(q.aY[var3_3] + 12, q.aZ[var3_3], 2) || j.a(q.aY[var3_3] - 12, q.aZ[var3_3], 2)) {
                                        q.aZ[var3_3] = j.d(q.aZ[var3_3]);
                                        q.bc[var3_3] = 0;
                                        q.ba[var3_3] = 1;
                                    } else if (j.a(q.aY[var3_3], q.aZ[var3_3] - j.f, 64)) {
                                        q.aZ[var3_3] = j.d(q.aZ[var3_3]) + 1;
                                        q.c(q.aY[var3_3], q.aZ[var3_3] - 13);
                                        q.c(q.aY[var3_3] + 1, q.aZ[var3_3] - 10);
                                        q.bc[var3_3] = 1;
                                        q.eL[var3_3] = -1;
                                        q.eM[var3_3] = 1;
                                        q.ba[var3_3] = 11;
                                    }
                                    ** GOTO lbl615
                                }
                                case 11: {
                                    if (q.as != 14 || g.w || !g.v) ** GOTO lbl596
                                    if (g.D == 1 && q.a(g.au - (g.aA >> 1) - (q.aZ[var3_3] - 7)) < 25 && q.a(g.at - q.aY[var3_3]) < 10) {
                                        g.D = 2;
                                        q.bc[var3_3] = 2;
                                        q.a("Gi\u1ea3i \u0111\u01b0\u1ee3c c\u00e2u \u0111\u1ed1 th\u1ee9 2");
                                    }
                                    if (g.D == 2) {
                                        v8 = var3_3;
                                        q.aZ[v8] = q.aZ[v8] - 3;
                                        v9 = var3_3;
                                        q.bc[v9] = q.bc[v9] + 1;
                                        if (q.bc[var3_3] <= 40) break block71;
                                        q.bc[var3_3] = 0;
                                        q.aW = 0;
                                        break block71;
                                    }
                                    ** GOTO lbl615
lbl596:
                                    // 1 sources

                                    if (q.at % 2 != 0) ** GOTO lbl615
                                    v10 = var3_3;
                                    q.aZ[v10] = q.aZ[v10] + q.eL[var3_3];
                                    v11 = var3_3;
                                    q.bc[v11] = q.bc[v11] + q.eL[var3_3];
                                    if (q.bc[var3_3] >= 1) {
                                        q.eL[var3_3] = -1;
                                    }
                                    if (q.bc[var3_3] <= -1) {
                                        q.eL[var3_3] = 1;
                                    }
                                    v12 = var3_3;
                                    q.aY[v12] = q.aY[v12] + q.eM[var3_3];
                                    if (var3_3 == q.aX) {
                                        g.au = q.aZ[q.aX] - 24;
                                        g.at += q.eM[var3_3];
                                    }
                                    if (q.as != 14 || q.aY[var3_3] <= 2376) ** GOTO lbl613
                                    q.eM[var3_3] = -1;
                                    ** GOTO lbl615
lbl613:
                                    // 1 sources

                                    if (j.a(q.aY[var3_3] + 12, q.aZ[var3_3], 4) || j.a(q.aY[var3_3] - 12, q.aZ[var3_3], 8)) {
                                        q.eM[var3_3] = -q.eM[var3_3];
                                    }
                                }
lbl615:
                                // 10 sources

                                default: {
                                    ++var3_3;
                                }
                            }
                        }
                        var3_3 = 0;
                        while (var3_3 < q.aP) {
                            if (var3_3 == q.aQ) {
                                q.aU[var3_3] = g.aG > 1 ? 2 : (q.at % 8 > 3 ? 1 : 0);
                            }
                            if (q.aV[var3_3] == 1) {
                                v13 = var3_3;
                                q.aS[v13] = q.aS[v13] + q.eK[var3_3];
                                if (q.eK[var3_3] < 20) {
                                    v14 = var3_3;
                                    q.eK[v14] = q.eK[v14] + 1;
                                }
                                if (q.aS[var3_3] >= j.d || j.a(q.aR[var3_3], q.aS[var3_3], 2)) {
                                    q.eK[var3_3] = 0;
                                    q.aV[var3_3] = 0;
                                    q.aS[var3_3] = j.d(q.aS[var3_3]);
                                }
                            }
                            ++var3_3;
                        }
                        var3_3 = 0;
                        while (var3_3 < 5) {
                            if (q.dB[var3_3] != -1) {
                                v15 = var3_3;
                                q.dB[v15] = q.dB[v15] + q.a(q.dA[var3_3]);
                                if (q.dB[var3_3] > 30) {
                                    q.dB[var3_3] = -1;
                                }
                                v16 = var3_3;
                                q.dx[v16] = q.dx[v16] + q.dz[var3_3];
                                v17 = var3_3;
                                q.dy[v17] = q.dy[v17] + q.dA[var3_3];
                            }
                            ++var3_3;
                        }
                        if (!q.b) {
                            var3_3 = 0;
                            while (var3_3 < 2) {
                                if (q.cQ[var3_3] != -1) {
                                    v18 = var3_3;
                                    q.cP[v18] = q.cP[v18] - 1;
                                    if (q.at % 2 == 0) {
                                        v19 = var3_3;
                                        q.cQ[v19] = q.cQ[v19] + 1;
                                        if (q.cQ[var3_3] > 2) {
                                            q.cQ[var3_3] = -1;
                                        } else {
                                            q.cR[var3_3] = q.cQ[var3_3];
                                        }
                                    }
                                }
                                ++var3_3;
                            }
                        }
                        if (!q.b) {
                            var3_3 = 0;
                            while (var3_3 < 2) {
                                if (q.cU[var3_3] != -1) {
                                    v20 = var3_3;
                                    q.cU[v20] = q.cU[v20] + 1;
                                    if (q.cU[var3_3] >= 5) {
                                        q.cU[var3_3] = -1;
                                    }
                                    if (var3_3 == 0) {
                                        v21 = var3_3;
                                        q.cS[v21] = q.cS[v21] - 1;
                                    } else {
                                        v22 = var3_3;
                                        q.cS[v22] = q.cS[v22] + 1;
                                    }
                                    v23 = var3_3;
                                    q.cT[v23] = q.cT[v23] - 1;
                                }
                                ++var3_3;
                            }
                        }
                        var3_3 = 0;
                        while (var3_3 < 2) {
                            if (q.cL[var3_3] != -1) {
                                v24 = var3_3;
                                q.cL[v24] = q.cL[v24] + 1;
                                v25 = var3_3;
                                q.cJ[v25] = q.cJ[v25] + (q.cN[var3_3] << 2);
                                v26 = var3_3;
                                q.cK[v26] = q.cK[v26] - 1;
                                if (q.cL[var3_3] >= 6) {
                                    q.cL[var3_3] = -1;
                                } else {
                                    q.cM[var3_3] = (q.cL[var3_3] >> 1) % 3;
                                }
                            }
                            ++var3_3;
                        }
                        var3_3 = 0;
                        while (var3_3 < q.dh) {
                            if (q.ah[var3_3] == 1) {
                                v27 = var3_3;
                                q.di[v27] = q.di[v27] + q.ai[var3_3];
                                v28 = var3_3;
                                q.dj[v28] = q.dj[v28] + q.dl[var3_3];
                                if (q.di[var3_3] < q.ay || q.di[var3_3] > q.ay + q.ab || q.dj[var3_3] < q.az || q.dj[var3_3] > q.az + q.ac) {
                                    q.ah[var3_3] = -1;
                                } else {
                                    if (q.ai[var3_3] > 0 && (j.b(q.di[var3_3], q.dj[var3_3]) & 4) == 4) {
                                        q.ah[var3_3] = 2;
                                        q.di[var3_3] = j.e(q.di[var3_3]);
                                    } else if (q.ai[var3_3] < 0 && (j.b(q.di[var3_3], q.dj[var3_3]) & 8) == 8) {
                                        q.ah[var3_3] = 2;
                                        q.di[var3_3] = j.e(q.di[var3_3]) + j.f;
                                    }
                                    v29 = var3_3;
                                    q.dk[v29] = q.dk[v29] + 1;
                                    if (q.dk[var3_3] > 1) {
                                        q.dk[var3_3] = 0;
                                    }
                                    if (q.ai[var3_3] != 0) {
                                        var4_8 = 0;
                                        while (var4_8 < a.c) {
                                            if (a.i[var4_8] != 5 && a.i[var4_8] != 1 && a.i[var4_8] != 10 && q.a(a.e[var4_8] - q.di[var3_3]) < a.h[var4_8] && q.a(a.f[var4_8] - (a.g[var4_8] >> 1) - q.dj[var3_3]) < a.g[var4_8]) {
                                                v30 = var5_9 = q.dm[var3_3] != false ? g.aK << 1 : g.aK;
                                                if (a.b(var4_8, var3_3, var5_9)) break;
                                                q.c(a.e[var4_8], a.f[var4_8] - (a.g[var4_8] >> 1), a.k[var4_8]);
                                                q.a("-" + var5_9, 0, a.e[var4_8], a.f[var4_8] - a.g[var4_8], 0, -2);
                                                break;
                                            }
                                            ++var4_8;
                                        }
                                        var4_8 = 0;
                                        while (var4_8 < n.c) {
                                            if (n.i[var4_8] != 5 && n.i[var4_8] != 1 && n.i[var4_8] != 10 && q.a(n.e[var4_8] - q.di[var3_3]) < 20 && q.a(n.f[var4_8] - 12 - q.dj[var3_3]) < 25) {
                                                v31 = var5_9 = q.dm[var3_3] != false ? g.aK << 1 : g.aK;
                                                if (n.a(var4_8, var5_9, var3_3)) break;
                                                q.c(n.e[var4_8], n.f[var4_8] - 12, n.j[var4_8]);
                                                q.a("-" + var5_9, 0, n.e[var4_8], n.f[var4_8] - 25, 0, -2);
                                                break;
                                            }
                                            ++var4_8;
                                        }
                                        if (!q.dm[var3_3] && q.a(g.at - q.di[var3_3]) < g.aB && q.a(g.au - g.aC - q.dj[var3_3]) < g.aC) {
                                            if (g.ax == 5 && g.ar == 0) {
                                                q.ai[var3_3] = -q.ai[var3_3];
                                                q.dl[var3_3] = -3;
                                                break;
                                            }
                                            g.a(g.aK, -g.aF);
                                            q.ah[var3_3] = -1;
                                            break;
                                        }
                                        if (q.aG && q.a(q.aH - q.di[var3_3]) < q.eG >> 1 && q.a(q.aI - (q.aJ >> 1) - q.dj[var3_3]) < q.aJ >> 1) {
                                            q.d(g.aK, -q.eE);
                                            q.c(q.di[var3_3], q.dj[var3_3], q.eE);
                                            q.a("-" + g.aK, 0, q.aH, q.aI - q.aJ, 0, -2);
                                            q.ah[var3_3] = -1;
                                        }
                                    }
                                }
                            } else if (q.ah[var3_3] > 1) {
                                v32 = var3_3;
                                q.ah[v32] = q.ah[v32] + 1;
                                if (q.ah[var3_3] >= 10) {
                                    q.ah[var3_3] = 1;
                                    q.dl[var3_3] = 12;
                                    q.ai[var3_3] = 0;
                                }
                            }
                            ++var3_3;
                        }
                        if (d.a && d.c == 1 && d.f < 0 && (d.d += d.f) < d.g) {
                            d.d = d.g;
                            d.c = 0;
                        }
                        if (s.a && s.c == 1) {
                            if (++s.f > 8) {
                                s.f = 8;
                            }
                            s.d += q.ay + q.ad - s.d >> 1;
                            s.e += q.az + q.ae - s.e >> 1;
                        }
                        q.H();
                        if (q.as == 9) {
                            if (q.bi == 2 && !g.r) {
                                ++q.dF;
                            } else if (g.r) {
                                q.dF = 0;
                            }
                        } else if (q.dF > 0 && ++q.dF > 30) {
                            q.dF = 0;
                        }
                        if (q.am != -1) {
                            if (q.an == 1) {
                                if (++q.dI % 2 == 0) {
                                    if (q.dG == 1) {
                                        q.am = -1;
                                    }
                                    if (q.dG < 1) {
                                        ++q.dG;
                                    }
                                    if (q.dG == 1) {
                                        q.al += 8;
                                    }
                                }
                                if (q.a(g.at - q.ak) < 30 && q.a(g.au - q.al) < 17) {
                                    g.a(e.e, -q.dH);
                                    q.am = -1;
                                }
                            } else if (q.an == 2) {
                                if (q.a(g.at - (q.ak += q.dH * ++q.dI)) < 30 && q.a(g.au - q.al) < 20) {
                                    g.a(e.e, -q.dH);
                                    q.am = -1;
                                }
                                if (q.dI % 2 == 0) {
                                    v33 = q.dG = q.dG == 2 ? 3 : 2;
                                }
                                if (q.dI >= 15) {
                                    q.am = -1;
                                }
                            }
                        }
                        q.I();
                        if (q.aE >= 0) {
                            if (q.es > q.ay + 10 && q.es < q.ay + q.ab - 10 && q.et > q.az + 10 && q.et < q.az + q.ac - 10) {
                                q.eu = 2;
                                q.ev = q.es;
                                q.ew = q.et + q.ey;
                            } else if (q.a(g.at - q.es) > q.a(g.au - q.et)) {
                                if (g.at > q.es) {
                                    q.eu = 1;
                                    q.ev = q.ay + 20 + q.ey;
                                } else {
                                    q.eu = 0;
                                    q.ev = q.ay + q.ab - 20 + q.ey;
                                }
                                q.ew = q.et + 24;
                                if (q.ew < q.az + 10) {
                                    q.ew = q.az + 10;
                                }
                                if (q.ew > q.az + q.ac - 10) {
                                    q.ew = q.az + q.ac - 10;
                                }
                            } else if (g.au > q.et) {
                                q.eu = 3;
                                q.ev = g.at;
                                q.ew = q.az + 20 + q.ey;
                            } else {
                                q.eu = 2;
                                q.ev = g.at;
                                q.ew = q.az + q.ac - 20 + q.ey;
                            }
                            if ((q.ey += q.ex) == 5 || q.ey == -5) {
                                q.ex = -q.ex;
                            }
                        }
                        g.b();
                        g.a();
                        g.i();
                        g.c();
                        a.b();
                        n.b();
                        t.b();
                        m.b();
                        e.a();
                        if (q.aG && !g.l) {
                            if (q.eJ > 0) {
                                --q.eJ;
                            }
                            switch (q.eD) {
                                case 1: {
                                    if (q.eH > 0) {
                                        --q.eH;
                                    } else {
                                        var3_3 = q.at % 80 - 40;
                                        var3_3 = var3_3 > 0 ? (var3_3 += 20) : (var3_3 -= 20);
                                        q.eI = q.aH + var3_3;
                                        q.eE = var3_3 > 0 ? 1 : -1;
                                        q.eC = q.eE << 2;
                                        if (q.aK < q.aL >> 1) {
                                            q.eC = q.eE << 3;
                                        }
                                        q.eD = 2;
                                        if (q.at % 5 == 0) {
                                            q.G();
                                        }
                                    }
                                    q.eF = 0;
                                    if (q.at % 10 != 6) break;
                                    q.b(-q.eE, q.aH + q.eE * 28, q.aI - 5);
                                    break;
                                }
                                case 2: {
                                    if (q.at % 8 == 0) {
                                        q.G();
                                    }
                                    if (!j.a(q.aH + (q.eE << 4), q.aI + 1, 2)) {
                                        q.eD = 1;
                                        q.eH = 0;
                                        break;
                                    }
                                    if (q.a(q.aH - q.eI) < 8) {
                                        q.eD = 1;
                                        q.eH = 20;
                                    }
                                    if (q.at % 2 == 0) {
                                        q.aH += q.eC;
                                        q.eF = 0;
                                        break;
                                    }
                                    q.eF = 1;
                                    break;
                                }
                                case 5: {
                                    if (!j.a(q.aH + (q.eE << 4), q.aI + 1, 2)) {
                                        q.eD = 1;
                                        q.eH = 0;
                                        break;
                                    }
                                    if (q.a(q.aH - q.eI) < 4) {
                                        q.eD = 1;
                                        q.eH = 35;
                                    }
                                    if (q.a(g.au - q.aI) < 18 && q.a(q.aH + (q.eE << 4) - g.at) < 10) {
                                        g.a(q.eB, q.eE);
                                        q.eD = 1;
                                        q.eH = 45;
                                        if (q.aK < q.aL >> 1) {
                                            q.eH = 18;
                                        }
                                    }
                                    q.aH += q.eC;
                                    q.eF = q.at % 3;
                                    break;
                                }
                                case 7: {
                                    q.aH -= q.eE;
                                    q.eF = 2;
                                    if (++q.eH != 4) break;
                                    q.eD = 1;
                                    q.eH = q.eI;
                                    if (q.eH <= 0 || q.aK >= q.aL >> 1) break;
                                    q.eH = 5;
                                    break;
                                }
                                case 6: {
                                    g.l = true;
                                    q.aI += q.eH;
                                    ++q.eH;
                                    q.aH += q.eC;
                                    if (q.eC > 1) {
                                        --q.eC;
                                    }
                                    if (q.eC < -1) {
                                        ++q.eC;
                                    }
                                    if (q.aI <= q.az + q.ac + q.aJ && q.aI < j.d + q.aJ) break;
                                    q.eH = 0;
                                    q.eD = 22;
                                }
                            }
                        }
                        q.L();
                        q.O();
                        if (q.as == 17 && q.bi == 6) {
                            q.av = 0;
                        } else if ((q.bi != 7 || g.u && g.s && g.t) && (q.as != 14 || g.D == 0 || q.bi != 3 || q.av != 1) && (q.as != 16 || g.A) && q.av != 0 && q.dN != null) {
                            if (q.av == 1) {
                                if (q.bi == 4) {
                                    q.av = 2;
                                }
                                if (q.a(g.au - (g.aA >> 1) - (q.ee - 7)) < 10 && q.a(g.at - q.ed) < 10) {
                                    q.av = 2;
                                    g.bf = true;
                                    switch (q.bi) {
                                        case 0: {
                                            q.a("L\u1ea5y \u0111\u01b0\u1ee3c b\u00ed k\u00edp");
                                            break;
                                        }
                                        case 1: {
                                            q.a("L\u1ea5y \u0111\u01b0\u1ee3c g\u01b0\u01a1m c\u1ee7a Sakura");
                                            break;
                                        }
                                        case 2: {
                                            q.a("T\u00ecm \u0111\u01b0\u1ee3c m\u1eaft ki\u1ebfng");
                                            g.r = true;
                                            break;
                                        }
                                        case 3: {
                                            q.a("Gi\u1ea3i \u0111\u01b0\u1ee3c c\u00e2u \u0111\u1ed1 th\u1ee9 1");
                                            g.D = 1;
                                            break;
                                        }
                                        case 5: {
                                            g.z = true;
                                            q.a("L\u1ea5y \u0111\u01b0\u1ee3c t\u01b0\u1ee3ng K\u1ef3 l\u00e2n");
                                            break;
                                        }
                                        case 6: {
                                            q.a("\u0111\u00e3 c\u00f3 s\u00e1o th\u1ea7n");
                                            g.A = true;
                                            break;
                                        }
                                        case 7: {
                                            g.B = true;
                                            g.aG = 0;
                                            g.ax = 23;
                                            break;
                                        }
                                        default: {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                ++q.av;
                                q.ee -= 3;
                                if (q.bi == 7) {
                                    q.av = 0;
                                }
                                if (q.av > 40) {
                                    q.av = 0;
                                }
                            }
                        }
                        i.a();
                        o.b.b();
                        o.a.b();
                        if (!q.cV || ++q.cW < 150) break;
                        q.cV = false;
                        break;
                    }
                    case 2: {
                        if (q.bl >= q.bk) break;
                        q.bl += 5;
                        break;
                    }
                    case 3: {
                        if (q.bZ > 0) {
                            --q.bZ;
                        }
                        if (q.ca > 0) {
                            --q.ca;
                        }
                        if (++q.at == 10000) {
                            q.at = 0;
                        }
                        if (q.bV <= q.bT && q.bW >= q.bU) ** GOTO lbl989
                        q.b();
                        q.c();
                        ** GOTO lbl1038
lbl989:
                        // 1 sources

                        if (q.D[4]) {
                            if (--q.bS < 0) {
                                q.bS = q.bL.length - 1;
                            }
                            q.D[4] = false;
                        }
                        if (q.D[6]) {
                            if (++q.bS == q.bL.length) {
                                q.bS = 0;
                            }
                            q.D[6] = false;
                        }
                        if (!q.D[5]) ** GOTO lbl1038
                        q.c();
                        if (q.bS != 0) ** GOTO lbl1009
                        if (Midlet.a() && !q.a) {
                            q.h(10);
                            q.K = 7;
                        } else {
                            q.B();
                            Midlet.f = true;
                            q.eA = System.currentTimeMillis();
                            q.i(0);
                        }
                        ** GOTO lbl1037
lbl1009:
                        // 1 sources

                        if (q.bS != 1) ** GOTO lbl1017
                        if (!Midlet.a()) ** GOTO lbl1038
                        q.a(false);
                        q.B();
                        Midlet.e = true;
                        q.eA = System.currentTimeMillis();
                        q.i(q.as);
                        ** GOTO lbl1037
lbl1017:
                        // 1 sources

                        if (q.bS == 2) {
                            q.B();
                            q.K = 2;
                            q.R();
                            Midlet.d = 11;
                            q.as = 99;
                            new Thread(Midlet.b).start();
                        } else if (q.bS == 3) {
                            try {
                                Midlet.b.platformRequest("http://vechai321.xtgem.com");
                                Midlet.b.notifyDestroyed();
                            }
                            catch (ConnectionNotFoundException v34) {
                                var3_4 = v34;
                                v34.printStackTrace();
                            }
                        } else if (q.bS == 4) {
                            Midlet.b.notifyDestroyed();
                        }
lbl1037:
                        // 9 sources

                        q.D[5] = false;
lbl1038:
                        // 4 sources

                        if (q.ay == 0) {
                            q.ay = 960;
                            q.az = 48;
                            q.ef = 1;
                            q.eg = 1;
                        }
                        if ((q.ay += q.ef) > q.ej) {
                            q.ay = q.ej;
                            q.ef = -q.ef;
                        }
                        if (q.ay < 24) {
                            q.ay = 24;
                            q.ef = -q.ef;
                        }
                        if (q.az < 0) {
                            q.az = 0;
                            q.eg = -q.eg;
                        }
                        if (q.az > q.ek) {
                            q.az = q.ek;
                            q.eg = -q.eg;
                        }
                        if ((q.W = q.ay / j.f - 1) < 0) {
                            q.W = 0;
                        }
                        q.X = q.az / j.f;
                        q.Y = q.W + q.ct;
                        q.Z = q.X + q.cu;
                        if (q.X < 0) {
                            q.X = 0;
                        }
                        if (q.Z > j.b - 1) {
                            q.Z = j.b - 1;
                        }
                        if (q.bM == 24) {
                            q.ca = 3;
                            q.bZ = 2;
                        }
                        if (q.bM == 40) {
                            if (q.bV > q.bT) {
                                q.bV -= 4;
                            }
                            if (q.bW < q.bU) {
                                q.bW += 4;
                            }
                            if (q.bY > q.bX) {
                                q.bY -= 2;
                            }
                        }
                        if (q.bM < 40) {
                            ++q.bM;
                        }
                        if (q.bM < 24) {
                            q.bN = 0;
                            break;
                        }
                        q.bN = 1;
                        break;
                    }
                    case 4: {
                        if (q.fd < 4) {
                            q.fe = q.fd;
                        }
                        if (q.fd > 51) {
                            q.fc = null;
                            q.Y();
                            q.K = 10;
                        } else if (q.fd > 46) {
                            q.fe = 4 - (q.fd - 46);
                        }
                        q.E = q.x.getWidth();
                        q.F = q.x.getHeight();
                        ++q.fd;
                        break;
                    }
                    case 5: {
                        if (++q.fz > 10000) {
                            q.fz = 0;
                        }
                        var3_5 = false;
                        if (q.D[13]) {
                            var3_5 = true;
                            q.fw = null;
                            j.c();
                            q.K = 1;
                            q.J = true;
                        } else if (q.D[5] || q.D[12]) {
                            var3_5 = true;
                            if (q.fy == 0) {
                                q.S();
                                q.K = 8;
                            }
                            if (q.fy == 1) {
                                q.T();
                                q.K = 15;
                            }
                            if (q.fy == 2) {
                                if (q.fX == 103) {
                                    q.fX = 2;
                                } else if (q.fX == 101) {
                                    q.fX = 5;
                                } else if (q.fX == 100) {
                                    q.fX = 10;
                                } else if (q.fX == 102) {
                                    q.fX = 14;
                                }
                                q.V();
                                q.K = 6;
                            }
                            if (q.fy == 3) {
                                q.fw = null;
                                j.c();
                                q.w();
                                q.h(5);
                                q.b(q.as == 105 ? "Gi\u1ebft nh\u00edm n\u00e2u b\u1ea3o v\u1ec7 m\u00f9a m\u00e0ng" : (q.e ? "R\u1ee7 b\u1ea1n b\u00e8 c\u00f9ng \u0111\u1ebfn tr\u01b0\u1eddng Haruna \u0111i\u1ec1u tra" : (g.bc == 0 ? "Gi\u1ebft 10 nh\u00edm n\u00e2u \u1edf khu luy\u1ec7n t\u1eadp r\u1ed3i quay v\u1ec1 g\u1eb7p c\u00f4 Ayumi" : (g.bc == 1 ? (g.h ? "Xu\u1ed1ng d\u01b0\u1edbi ch\u00e2n th\u00e1c n\u01b0\u1edbc t\u00ecm thanh g\u01b0\u01a1m v\u00e0 mang v\u1ec1 cho Sakura" : "T\u00ecm Sakura \u1edf th\u00e1c n\u01b0\u1edbc, c\u00f9ng quay v\u1ec1 g\u1eb7p th\u1ea7y hi\u1ec7u tr\u01b0\u1edfng") : (g.bc == 2 ? (q.as == 108 ? "Ch\u1ea1y \u0111ua v\u00e0 th\u1eafng Toniku b\u1eb1ng m\u1ecdi gi\u00e1 (g\u1ee3i \u00fd: tr\u00ean c\u00e0nh c\u00e2y)" : "T\u00ecm Toniku \u1edf th\u00f4n Sanzuya v\u00e0 c\u00f9ng v\u1ec1 g\u1eb7p th\u1ea7y hi\u1ec7u tr\u01b0\u1edfng") : (g.f == false && q.g == false ? "G\u1eb7p c\u00f4 Ayumi \u0111\u1ec3 ph\u1ee5c h\u1ed3i v\u00f5 c\u00f4ng." : (g.i != false && q.h == false ? "L\u00ean \u0111\u01b0\u1eddng \u0111\u1ebfn tr\u01b0\u1eddng Haruna!" : (q.g != false ? "G\u1eb7p th\u1ea7y hi\u1ec7u tr\u01b0\u1edfng" : (g.bc == 4 ? "Gi\u1ebft 20 r\u00f9a bi\u1ec3n l\u00e0m l\u01b0\u01a1ng kh\u00f4" : (g.bc == 3 ? "Gi\u1ebft 20 \u1ed1c ma \u0111en \u0111em v\u1ec1 cho gi\u00e0 l\u00e0ng \u0111\u1ec3 l\u00e0m di\u1ec1u l\u01b0\u1ee3n" : (g.a == 0 ? "Gi\u1ebft m\u1ef1c kh\u1ed5ng l\u1ed3 tr\u00ean \u0111\u1ec9nh Ichidai r\u1ed3i v\u1ec1 g\u1eb7p gi\u00e0 l\u00e0ng" : (g.a == 1 ? "\u0111\u1ebfn c\u00e1nh \u0111\u1ed3ng Fuki mua thu\u1ed1c tr\u1ecb th\u01b0\u01a1ng mang v\u1ec1 cho Sakura" : (g.a == 2 ? "Ti\u1ebfp t\u1ee5c l\u00ean \u0111\u01b0\u1eddng \u0111\u1ebfn tr\u01b0\u1eddng Haruna" : (g.a == 3 ? "T\u00ecm th\u1ebb h\u1ecdc sinh (G\u1ee3i \u00fd: C\u00f4 g\u00e1i)" : (g.a == 4 ? "L\u00ean \u0111\u01b0\u1eddng \u0111\u1ebfn mi\u1ebfu Chakumi" : (g.a == 5 ? "Gi\u1ea3i c\u00e2u \u0111\u1ed1: Ba anh v\u00e1c g\u1eady \u0111u\u1ed5i nhau, m\u01b0\u1eddi hai c\u00f4 g\u00e1i l\u00e0m ng\u01a1 \u0111\u1ee9ng nh\u00ecn" : (g.a == 6 ? "Gi\u1ea3i c\u00e2u \u0111\u1ed1: C\u00e1i g\u00ec l\u1ea1 l\u1eafm, t\u00ean tr\u00f9ng m\u1ed9t \u00e2m, n\u01b0\u1edbc \u0111\u1ee5c chui l\u1ea7m, th\u00ec ti\u00eau \u0111\u1eddi c\u00e1" : (g.a == 7 ? "Gi\u1ea3i c\u00e2u \u0111\u1ed1 th\u1ee9 ba: C\u00e1i g\u00ec v\u1eeba l\u00e0 \u00e1o v\u1eeba l\u00e0 nh\u00e0?" : (g.a == 8 ? "T\u1eadp luy\u1ec7n \u0111\u1ec3 \u0111\u1ea1t \u0111\u01b0\u1ee3c c\u1ea5p \u0111\u1ed9 10 r\u1ed3i quay v\u1ec1 g\u1eb7p gi\u00e0 l\u00e0ng" : (g.a == 9 ? "\u0111\u1ebfn mi\u1ebfu Chakumi ti\u00eau di\u1ec7t t\u00ean Sanosuke" : (g.a == 10 ? "T\u1eadp luy\u1ec7n \u0111\u1ec3 \u0111\u1ea1t \u0111\u01b0\u1ee3c c\u1ea5p \u0111\u1ed9 13" : (g.a == 11 ? "T\u1eadp luy\u1ec7n \u0111\u1ec3 \u0111\u1ea1t \u0111\u01b0\u1ee3c c\u1ea5p \u0111\u1ed9 20" : (g.a == 12 ? "Qua d\u00e3y l\u1edbp h\u1ecdc (b\u00ean ph\u1ea3i) g\u1eb7p Toniku" : "Kh\u00f4ng c\u00f3 nhi\u1ec7m v\u1ee5")))))))))))))))))))))));
                                q.bv = q.ae - 40;
                                q.K = 7;
                            }
                            if (q.fy == 4) {
                                if (g.ax != 1) {
                                    q.fw = null;
                                    j.c();
                                    q.w();
                                    q.h(5);
                                    q.b("B\u1ea1n ch\u1ec9 c\u00f3 th\u1ec3 l\u01b0u v\u00e0 tho\u00e1t khi nh\u00e2n v\u1eadt \u0111ang \u0111\u1ee9ng y\u00ean.");
                                    q.bv = q.ae - 40;
                                    q.K = 7;
                                } else {
                                    q.w();
                                    q.h(11);
                                    q.bv = q.ae - 40;
                                    q.K = 7;
                                }
                            }
                        } else if (q.D[8]) {
                            var3_5 = true;
                            if (++q.fy > 4) {
                                q.fy = 0;
                            }
                        } else if (q.D[2]) {
                            var3_5 = true;
                            if (--q.fy < 0) {
                                q.fy = 4;
                            }
                        } else if (q.D[4]) {
                            var3_5 = true;
                        } else if (q.D[6]) {
                            var3_5 = true;
                        }
                        if (!var3_5) break;
                        q.c();
                        break;
                    }
                    case 6: {
                        if (++q.fM > 10000) {
                            q.fM = 0;
                        }
                        if (q.fT != q.fN || q.fU != q.fO) {
                            q.fP = q.fN - q.fT << 1;
                            q.fQ = q.fO - q.fU << 1;
                            q.fR &= 15;
                            q.fU += (q.fS += q.fQ) >> 4;
                            q.fS &= 15;
                            if ((q.fT += (q.fR += q.fP) >> 4) < -40) {
                                q.fT = -40;
                            }
                            if (q.fT > q.fV) {
                                q.fT = q.fV;
                            }
                            if (q.fU < -40) {
                                q.fU = -40;
                            }
                            if (q.fU > q.fW) {
                                q.fU = q.fW;
                            }
                        }
                        var3_6 = false;
                        if (q.D[5] || q.D[12]) {
                            q.c();
                            if (q.fL != -1) {
                                if (!q.bt[q.fL]) {
                                    q.h(1);
                                    q.K = 7;
                                } else if (q.fX == q.fL) {
                                    q.h(3);
                                    q.K = 7;
                                } else {
                                    q.h(2);
                                    q.K = 7;
                                }
                            }
                        }
                        if (q.D[13]) {
                            q.W();
                            q.fw = null;
                            j.c();
                            q.K = 1;
                            q.c();
                            q.b();
                            q.D[12] = true;
                            break;
                        }
                        if (q.C[2]) {
                            if ((q.fK -= 4) < -20) {
                                q.fK = -20;
                            }
                            var3_6 = true;
                        }
                        if (q.C[8]) {
                            if ((q.fK += 4) > 279) {
                                q.fK = 279;
                            }
                            var3_6 = true;
                        }
                        if (q.C[4]) {
                            if ((q.fJ -= 4) < -20) {
                                q.fJ = -20;
                            }
                            var3_6 = true;
                        }
                        if (q.C[6]) {
                            if ((q.fJ += 4) > 279) {
                                q.fJ = 279;
                            }
                            var3_6 = true;
                        }
                        if (!var3_6) break;
                        q.fN = q.fJ - q.ad;
                        q.fO = q.fK - q.ae;
                        q.X();
                        break;
                    }
                    case 7: {
                        if (++q.ge < 10) {
                            q.c();
                        }
                        if (q.D[12]) {
                            q.c();
                            switch (q.gc) {
                                case 1: 
                                case 3: 
                                case 4: {
                                    q.K = 6;
                                    break;
                                }
                                case 5: 
                                case 6: 
                                case 8: 
                                case 9: 
                                case 10: 
                                case 11: {
                                    if (q.gc == 11) {
                                        q.y = false;
                                        break;
                                    }
                                    if (q.gc == 10) {
                                        q.fw = null;
                                        q.B();
                                        Midlet.f = true;
                                        q.eA = System.currentTimeMillis();
                                        q.i(0);
                                        break;
                                    }
                                    q.fw = null;
                                    q.K = 1;
                                    break;
                                }
                                case 2: {
                                    if (q.gd >= q.aj) {
                                        q.h(4);
                                        break;
                                    }
                                    q.aj -= q.gd;
                                    q.W();
                                    q.fw = null;
                                    j.c();
                                    q.A();
                                    g.aS = g.at = q.fF[q.fL] * 24;
                                    g.aT = g.au = q.fG[q.fL] * 24;
                                    Midlet.f = true;
                                    q.i(q.fL);
                                }
                            }
                        }
                        if (q.D[13]) {
                            q.c();
                            switch (q.gc) {
                                case 1: {
                                    break;
                                }
                                case 2: {
                                    q.K = 6;
                                    break;
                                }
                                case 10: {
                                    q.bS = 1;
                                    q.K = 3;
                                    break;
                                }
                                case 7: {
                                    q.K = 9;
                                    break;
                                }
                                case 11: {
                                    j.c(q.as);
                                    q.K = 1;
                                }
                            }
                        }
                        q.J = true;
                        break;
                    }
                    case 14: {
                        if (++q.fz > 10000) {
                            q.fz = 0;
                        }
                        if (q.D[12]) {
                            g.aX = 0;
                            if (g.a == 10 && ++g.aW >= 13) {
                                g.a = 4;
                                ++q.aO;
                            }
                            switch (q.gi) {
                                case 0: {
                                    g.aM += g.d[g.aW - 2];
                                    break;
                                }
                                case 1: {
                                    g.aJ += g.b[g.aW - 2];
                                    g.aK += 2;
                                    break;
                                }
                                case 2: {
                                    g.aO += g.e[g.aW - 2];
                                }
                            }
                            g.aL = g.aM;
                            g.aN = g.aO;
                            q.c();
                            q.K = 1;
                        }
                        if (q.D[2]) {
                            q.c();
                            --q.gi;
                        }
                        if (q.D[8]) {
                            q.c();
                            ++q.gi;
                        }
                        if (q.gi < 0) {
                            q.gi = 2;
                        }
                        if (q.gi > 2) {
                            q.gi = 0;
                        }
                        q.J = true;
                        break;
                    }
                    case 8: {
                        if (q.fq > 0) {
                            --q.fq;
                        }
                        if (q.D[5] || q.D[12]) {
                            if (q.br[q.fp] > 0) {
                                v35 = q.fp;
                                q.br[v35] = q.br[v35] - 1;
                                q.fq = q.fr.length - 1;
                                if (q.fp <= 2) {
                                    if (g.aL < g.aM) {
                                        g.aL += q.ft[q.fp];
                                    }
                                    if (g.aL > g.aM) {
                                        g.aL = g.aM;
                                    }
                                } else {
                                    if (g.aN < g.aO) {
                                        g.aN += q.ft[q.fp];
                                    }
                                    if (g.aN > g.aO) {
                                        g.aN = g.aO;
                                    }
                                }
                            }
                            q.c();
                        }
                        if (q.D[13]) {
                            if (g.ax == 6) {
                                q.c();
                                q.b();
                                break;
                            }
                            q.c();
                            q.b();
                            q.K = 5;
                            break;
                        }
                        if (q.D[4]) {
                            q.c();
                            if (--q.fp < 0) {
                                q.fp = 5;
                            }
                        }
                        if (!q.D[6]) break;
                        q.c();
                        if (++q.fp != 6) break;
                        q.fp = 0;
                        break;
                    }
                    case 9: {
                        if (q.fh > 0) {
                            --q.fh;
                        }
                        if (!q.D[5] && !q.D[12]) ** GOTO lbl1439
                        q.c();
                        if (q.bo != 0) ** GOTO lbl1377
                        if (q.bp != 0) ** GOTO lbl1375
                        if (q.bn >= 3 ? q.br[3] + q.br[4] + q.br[5] > 99 : q.br[0] + q.br[1] + q.br[2] > 99) break;
                        if (q.aj >= q.fl[q.bo][q.bn]) {
                            v36 = q.bn;
                            q.br[v36] = q.br[v36] + 1;
                            q.aj -= q.fl[q.bo][q.bn];
                        } else {
                            q.h(7);
                            q.b("Kh\u00f4ng c\u00f3 \u0111\u1ee7 ti\u1ec1n: " + q.fl[q.bo][q.bn] + "$. B\u1ea1n ch\u1ec9 c\u00f3: " + q.aj + "$. H\u00e3y \u0111i t\u00ecm th\u00eam.");
                            q.bv = q.bm;
                            q.K = 7;
                            break;
lbl1375:
                            // 1 sources

                            b.a(String.valueOf(b.e) + " BUY " + "110" + "000" + " " + b.c[q.bo][q.bn], b.d[q.bo][q.bn], q.bn + 1);
                        }
                        ** GOTO lbl1439
lbl1377:
                        // 1 sources

                        if (q.bn != 0) {
                            if (g.aW < q.ff[q.bn]) {
                                q.h(7);
                                q.b("B\u1ea1n ph\u1ea3i \u0111\u1ea1t c\u1ea5p \u0111\u1ed9 " + q.ff[q.bn] + " m\u1edbi c\u00f3 th\u1ec3 mua v\u0169 kh\u00ed n\u00e0y!");
                                q.bv = q.bm;
                                q.K = 7;
                                break;
                            }
                            if (q.m(q.bn)) {
                                q.h(7);
                                q.b("Kh\u00f4ng c\u1ea7n thi\u1ebft ph\u1ea3i mua v\u0169 kh\u00ed n\u00e0y v\u00ec v\u0169 kh\u00ed n\u00e0y \u0111\u00e3 mua r\u1ed3i!");
                                q.bv = q.bm;
                                q.K = 7;
                                break;
                            }
                        }
                        if (q.bn == 0 && (q.as != 8 || g.n || !g.k)) {
                            q.h(7);
                            q.b("Kh\u00f4ng c\u1ea7n thi\u1ebft ph\u1ea3i mua thu\u1ed1c tr\u1ecb th\u01b0\u01a1ng v\u00ec b\u1ea1n ch\u01b0a c\u1ea7n d\u00f9ng t\u1edbi!");
                            q.bv = q.bm;
                            q.K = 7;
                            break;
                        }
                        if (q.bp == 0) {
                            if (q.aj >= q.fl[q.bo][q.bn]) {
                                q.aj -= q.fl[q.bo][q.bn];
                                if (q.bn != 0) {
                                    g.as = q.bn;
                                    g.e();
                                    q.i();
                                    q.h(7);
                                    q.b("Xin ch\u00fac m\u1eebng. B\u1ea1n \u0111\u00e3 c\u00f3 v\u0169 kh\u00ed m\u1edbi: " + q.bq[q.bo][q.bn] + "!");
                                    g.aJ += q.fj[q.bn];
                                    q.bv = q.bm;
                                    q.K = 7;
                                    q.bf[q.bh] = g.as;
                                    switch (g.as) {
                                        case 1: {
                                            q.bg[q.bh] = "G\u01b0\u01a1m s\u00e9t";
                                            q.bs[q.bh] = 2;
                                            break;
                                        }
                                        case 2: {
                                            q.bg[q.bh] = "G\u01b0\u01a1m l\u1eeda";
                                            q.bs[q.bh] = 3;
                                            break;
                                        }
                                        case 3: {
                                            q.bg[q.bh] = "G\u01b0\u01a1m b\u0103ng";
                                            q.bs[q.bh] = 4;
                                            break;
                                        }
                                        case 4: {
                                            q.bg[q.bh] = "G\u01b0\u01a1m h\u1ecfa long";
                                            q.bs[q.bh] = 5;
                                        }
                                    }
                                    ++q.bh;
                                    break;
                                }
                                g.n = true;
                                q.h(7);
                                q.b("Xin ch\u00fac m\u1eebng. B\u1ea1n \u0111\u00e3 mua \u0111\u01b0\u1ee3c thu\u1ed1c tr\u1ecb th\u01b0\u01a1ng.");
                                q.bv = q.bm;
                                q.K = 7;
                                break;
                            }
                            q.h(7);
                            q.b("Kh\u00f4ng c\u00f3 \u0111\u1ee7 ti\u1ec1n: " + q.fl[q.bo][q.bn] + "$. B\u1ea1n ch\u1ec9 c\u00f3: " + q.aj + "$. H\u00e3y \u0111i t\u00ecm th\u00eam.");
                            q.bv = q.bm;
                            q.K = 7;
                            break;
                        }
                        b.a(String.valueOf(b.e) + " PAY " + "110" + "000" + " " + b.c[q.bo][q.bn], b.d[q.bo][q.bn], q.bn + 6);
lbl1439:
                        // 4 sources

                        if (q.D[13]) {
                            q.fw = null;
                            q.fm = null;
                            System.gc();
                            j.c();
                            q.c();
                            q.b();
                            q.K = 1;
                            q.J = true;
                            break;
                        }
                        if (q.D[2]) {
                            q.c();
                            if (q.bo == 1) {
                                q.bo = 0;
                                ++q.bn;
                            }
                        }
                        if (q.D[8]) {
                            q.c();
                            if (q.bo == 0) {
                                q.bo = 1;
                                if (--q.bn < 0) {
                                    q.bn = 0;
                                }
                                if (q.bn > 3) {
                                    q.bn = 3;
                                }
                            }
                        }
                        if (q.D[4]) {
                            q.c();
                            if (--q.bn < 0) {
                                q.bn = q.bo == 0 ? 5 : 3;
                            }
                        }
                        if (!q.D[6]) break;
                        q.c();
                        if (q.bo == 0 && ++q.bn == 6) {
                            q.bn = 0;
                        }
                        if (q.bo != 1 || q.bn != 4) break;
                        q.bn = 0;
                        break;
                    }
                    case 10: {
                        if (q.ga) {
                            q.fZ = null;
                            System.gc();
                            q.E();
                        }
                        var3_7 = false;
                        if (q.D[12]) {
                            if (q.gb == 0) {
                                Midlet.h = false;
                            } else {
                                q.b = false;
                            }
                            var3_7 = true;
                        } else if (q.D[13]) {
                            if (q.gb == 0) {
                                Midlet.h = true;
                            } else {
                                q.b = true;
                            }
                            var3_7 = true;
                        } else if (q.D[4]) {
                            q.c();
                            if (--q.bu < 0) {
                                q.bu = 0;
                            }
                        } else if (q.D[6]) {
                            q.c();
                            if (++q.bu > 10) {
                                q.bu = 10;
                            }
                        }
                        if (!var3_7) break;
                        q.c();
                        q.b();
                        if (q.gb == 0) {
                            q.gb = 1;
                            break;
                        }
                        q.ga = true;
                        break;
                    }
                    case 11: {
                        q.N();
                        break;
                    }
                    case 12: {
                        q.N();
                        break;
                    }
                    case 13: {
                        b.b();
                        break;
                    }
                    case 15: {
                        if (q.fq > 0) {
                            --q.fq;
                        }
                        if (q.D[5] || q.D[12]) {
                            if (q.fp == 1) {
                                g.ar = 1;
                            } else {
                                g.as = q.bf[q.fp];
                                g.ar = 0;
                            }
                            q.i();
                            g.e();
                            q.c();
                        }
                        if (q.D[13]) {
                            q.c();
                            q.b();
                            q.K = q.fa != false ? 1 : 5;
                            q.fa = false;
                            break;
                        }
                        if (q.D[4]) {
                            q.c();
                            if (--q.fp < 0) {
                                q.fp = q.bh - 1;
                            }
                        }
                        if (q.D[6]) {
                            q.c();
                            if (++q.fp == q.bh) {
                                q.fp = 0;
                            }
                        }
                        if (++q.fv < 100) break;
                        q.fv = 0;
                    }
                }
                this.repaint();
                this.serviceRepaints();
                while (System.currentTimeMillis() - var1_1 < (long)q.bz) {
                    Thread.yield();
                }
            }
        }
        catch (Exception v37) {
            var1_2 = v37;
            v37.printStackTrace();
        }
        q.a();
        Midlet.b.notifyDestroyed();
    }

    public static void u() {
        try {
            fc = Image.createImage((String)"/u/lg.png");
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static void R() {
        bl = 0;
        bk = 0;
        fb = 0;
    }

    public static void v() {
        bm = ae - 40;
        fg = ad - 50;
        j.e = null;
        fm = new Image[4];
        try {
            q.fm[0] = Image.createImage((String)"/u/t.png");
            q.fm[1] = Image.createImage((String)"/u/wpt2.png");
            q.fm[2] = Image.createImage((String)"/u/wpt3.png");
            q.fm[3] = Image.createImage((String)"/u/wpt4.png");
        }
        catch (Exception exception) {}
        if (fw == null) {
            fw = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.fw[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
        System.gc();
    }

    private static void g(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        q.c(graphics);
        q.b(graphics);
        graphics.setClip(0, 0, E, F);
        graphics.setColor(0);
        graphics.fillRect(ad - 64, bm - 19, 129, 102);
        graphics.fillRect(0, F - 13, E, 13);
        Midlet.a(graphics, "Mua", 3, F - 13, 0);
        Midlet.a(graphics, "Quay l\u1ea1i", E - 3, F - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(ad - 63, bm - 18, 126, 99);
        graphics.setColor(6562304);
        graphics.fillRect(ad - 61, bm - 16, 123, 97);
        if (bp == 0) {
            Midlet.a(graphics, "Ti\u1ec1n: " + aj + "$", ad, bm - 8, 2);
        } else {
            Midlet.a(graphics, bq[bo][bn], ad, bm - 8, 2);
        }
        int n2 = 0;
        while (n2 < 3) {
            graphics.setColor(0);
            graphics.fillRect(fg - 10 + n2 * 20, bm + 10, 19, 20);
            graphics.fillRect(fg + 50 + n2 * 20, bm + 10, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(fg - 10 + n2 * 20, bm + 10, 20, 20);
            graphics.drawRect(fg + 50 + n2 * 20, bm + 10, 20, 20);
            graphics.drawRegion(dY[0], 0, n2 * 15, 13, 15, 0, fg + n2 * 20, bm + 20, 3);
            graphics.drawRegion(dY[1], 0, n2 * 15, 13, 15, 0, fg + 60 + n2 * 20, bm + 20, 3);
            ++n2;
        }
        n2 = 0;
        while (n2 < 4) {
            graphics.setColor(0);
            graphics.fillRect(fg + 10 + n2 * 20, bm + 32, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(fg + 10 + n2 * 20, bm + 32, 18, 20);
            graphics.drawImage(fm[n2], fg + 20 + n2 * 20, bm + 42, 3);
            ++n2;
        }
        graphics.setColor(fi[fh]);
        if (bo == 0) {
            graphics.drawRect(fg - 10 + bn * 20, bm + 10, 19, 20);
        } else {
            graphics.drawRect(fg + 10 + bn * 20, bm + 32, 19, 20);
        }
        if (bp == 0) {
            Midlet.a(graphics, String.valueOf(bq[bo][bn]) + "-" + fl[bo][bn] + "$", ad, bm + 58, 2);
        } else {
            Midlet.a(graphics, fk[bo][bn], ad, bm + 58, 2);
        }
        graphics.drawImage(fw[0], ad - 64, bm - 19, 20);
        graphics.drawImage(fw[1], ad + 65, bm - 19, 24);
        graphics.drawImage(fw[2], ad - 64, bm + 83, 36);
        graphics.drawImage(fw[3], ad + 65, bm + 83, 40);
    }

    private static boolean m(int n2) {
        int n3 = 2;
        while (n3 < bf.length) {
            if (bf[n3] == -1) {
                return false;
            }
            if (bf[n3] == n2) {
                return true;
            }
            ++n3;
        }
        return false;
    }

    private static void S() {
        fn = ae - 40;
        fo = ad - 50;
        fp = 0;
        if (fw == null) {
            fw = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.fw[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
    }

    private static void T() {
        int n2;
        fn = ae - 40;
        fo = ad - 50;
        if (g.ar == 1) {
            fp = 1;
        } else {
            if (g.as == 0) {
                fp = 0;
            } else {
                n2 = 2;
                while (n2 < 6) {
                    if (bf[n2] == g.as) {
                        fp = n2;
                        break;
                    }
                    ++n2;
                }
            }
            g.ar = 0;
        }
        if (fw == null) {
            fw = new Image[4];
            n2 = 0;
            while (n2 < 4) {
                try {
                    q.fw[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
        if (fu == null) {
            fu = new Image[6];
            try {
                n2 = 0;
                while (n2 < 6) {
                    q.fu[n2] = Image.createImage((String)("/u/wpt" + n2 + ".png"));
                    ++n2;
                }
                return;
            }
            catch (IOException iOException) {}
        }
    }

    private static void h(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        q.c(graphics);
        q.b(graphics);
        graphics.setClip(0, 0, E, F);
        graphics.setColor(0);
        graphics.fillRect(ad - 64, fn - 19, 129, 120);
        graphics.fillRect(0, F - 13, E, 13);
        Midlet.a(graphics, "S\u1eed d\u1ee5ng", 3, F - 13, 0);
        Midlet.a(graphics, "Quay l\u1ea1i", E - 3, F - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(ad - 63, fn - 18, 126, 117);
        graphics.setColor(6562304);
        graphics.fillRect(ad - 61, fn - 16, 123, 115);
        Midlet.a(graphics, "S\u1eed d\u1ee5ng Item", ad, fn - 8, 2);
        int n2 = 0;
        while (n2 < 3) {
            graphics.setColor(0);
            graphics.fillRect(fo - 10 + n2 * 20, fn + 20, 19, 20);
            graphics.fillRect(fo + 50 + n2 * 20, fn + 20, 19, 20);
            graphics.setColor(4856064);
            graphics.drawRect(fo - 10 + n2 * 20, fn + 20, 20, 20);
            graphics.drawRect(fo + 50 + n2 * 20, fn + 20, 20, 20);
            graphics.drawRegion(dY[0], 0, n2 * 15, 13, 15, 0, fo + n2 * 20, fn + 30, 3);
            graphics.drawRegion(dY[1], 0, n2 * 15, 13, 15, 0, fo + 60 + n2 * 20, fn + 30, 3);
            ++n2;
        }
        graphics.setColor(fr[fq]);
        graphics.drawRect(fo - 10 + fp * 20, fn + 20, 20, 20);
        Midlet.a(graphics, fs[fp], ad, fn + 50, 2);
        Midlet.a(graphics, "S\u1ed1 l\u01b0\u1ee3ng: " + br[fp], ad, fn + 65, 2);
        graphics.drawImage(fw[0], ad - 64, fn - 19, 20);
        graphics.drawImage(fw[1], ad + 65, fn - 19, 24);
        graphics.drawImage(fw[2], ad - 64, fn + 101, 36);
        graphics.drawImage(fw[3], ad + 65, fn + 101, 40);
    }

    private static void U() {
        fx = ae - 40;
        j.e = null;
        if (fw == null) {
            fw = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.fw[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
    }

    private static void i(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(ad - 64, fx - 19, 129, 102);
        graphics.fillRect(ad - 64, fx - 19, 129, 120);
        graphics.fillRect(0, F - 13, E, 13);
        Midlet.a(graphics, "Ch\u1ecdn", 3, F - 13, 0);
        Midlet.a(graphics, "Quay l\u1ea1i", E - 3, F - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(ad - 63, fx - 18, 126, 117);
        graphics.setColor(6562304);
        graphics.fillRect(ad - 61, fx - 16, 123, 115);
        int n2 = 0;
        while (n2 < 5) {
            Midlet.a(graphics, fA[n2], G - 30, fx + n2 * 18, 0);
            ++n2;
        }
        graphics.drawImage(eb[0], G - 55 + fz % 2, fx + fy * 18 - fz % 2, 0);
        graphics.drawImage(fw[0], ad - 64, fx - 19, 20);
        graphics.drawImage(fw[1], ad + 65, fx - 19, 24);
        graphics.drawImage(fw[2], ad - 64, fx + 101, 36);
        graphics.drawImage(fw[3], ad + 65, fx + 101, 40);
    }

    private static void V() {
        fV = 280 - ab;
        fW = 280 - ac;
        fL = 0;
        try {
            fH = Image.createImage((String)"/wm.png");
            fI = Image.createImage((String)"/u/x.png");
        }
        catch (Exception exception) {}
        fJ = fD[fX];
        fK = fE[fX];
        q.X();
        fN = fJ - ad;
        fO = fK - ae;
        if (fT < -40) {
            fT = -40;
        }
        if (fT > fV) {
            fT = fV;
        }
        if (fU < -40) {
            fU = -40;
        }
        if (fU > fW) {
            fU = fW;
        }
    }

    private static void W() {
        fH = null;
        fI = null;
        System.gc();
    }

    private static void j(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(0, 0, ab, ac);
        graphics.fillRect(0, F - 13, E, 13);
        Midlet.a(graphics, fY, 3, F - 13, 0);
        Midlet.a(graphics, "Quay l\u1ea1i", E - 3, F - 13, 1);
        graphics.setClip(0, 0, ab, ac);
        graphics.translate(-fT, -fU);
        graphics.drawImage(fH, 0, 0, 0);
        int n2 = 0;
        while (n2 < bt.length) {
            if (n2 == fX) {
                graphics.drawImage(g.N[0], fD[n2] + 10, fE[n2], 3);
            }
            if (bt[n2] && (n2 != fL || fM % 10 > 5)) {
                graphics.drawImage(fI, fD[n2], fE[n2], 3);
            }
            ++n2;
        }
        graphics.drawImage(eb[0], fJ, fK, 24);
        if (fL != -1) {
            graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
            graphics.setColor(6562304);
            graphics.fillRect(G - 60, ac - 35, 120, 33);
            graphics.setColor(0xBB6611);
            graphics.drawRect(G - 59, ac - 34, 117, 30);
            Midlet.a(graphics, fB[fL], G, ac - 25, 2);
            graphics.drawImage(fw[0], G - 60, ac - 35, 20);
            graphics.drawImage(fw[1], G + 60, ac - 35, 24);
            graphics.drawImage(fw[2], G - 60, ac - 2, 36);
            graphics.drawImage(fw[3], G + 60, ac - 2, 40);
        }
    }

    private static void X() {
        fL = -1;
        fY = "";
        int n2 = 0;
        while (n2 < bt.length) {
            if (q.a(fJ - fD[n2]) < 8 && q.a(fK - fE[n2]) < 8) {
                fL = n2;
                fY = "Chuy\u1ec3n \u0111\u1ebfn";
                return;
            }
            ++n2;
        }
    }

    private static void Y() {
        try {
            Image[] imageArray = new Image[3];
            fZ = imageArray;
            imageArray[0] = Image.createImage((String)"/u/m0.png");
            q.fZ[1] = Image.createImage((String)"/u/m1.png");
            q.fZ[2] = Image.createImage((String)"/u/m2.png");
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static void k(Graphics graphics) {
        graphics.setColor(0);
        graphics.fillRect(0, 0, E, F);
        if (ga) {
            return;
        }
        if (gb == 1) {
            graphics.drawImage(fZ[2], G, H - 30, 3);
        } else {
            int n2 = 0;
            while (n2 < 10) {
                graphics.drawImage(fZ[n2 < bu ? 1 : 0], G - 60 + n2 * 12, H - 30, 0);
                ++n2;
            }
            Midlet.a(graphics, "*", G - 60, H - 10, 0);
            Midlet.a(graphics, "/", G + 60, H - 10, 1);
        }
        if (gb == 0) {
            Midlet.a(graphics, "B\u1eadt \u00e2m thanh?", G, H + 15, 2);
            Midlet.a(graphics, "B\u1eadt", 4, F - 14, 0);
            Midlet.a(graphics, "T\u1eaft", E - 4, F - 14, 1);
            return;
        }
        Midlet.a(graphics, "Ch\u1ea5t l\u01b0\u1ee3ng h\u00ecnh \u1ea3nh?", G, H + 15, 2);
        Midlet.a(graphics, "Cao", 4, F - 14, 0);
        Midlet.a(graphics, "Th\u1ea5p", E - 4, F - 14, 1);
    }

    public static void b(String string) {
        gh = Midlet.a(string, 100);
        bv = ae - gh.length * 9;
    }

    public static void h(int n2) {
        q.w();
        gc = n2;
        if (n2 == 1) {
            q.b("Kh\u00f4ng th\u1ec3 chuy\u1ec3n. V\u00ec b\u1ea1n ch\u01b0a t\u1eebng \u0111i b\u1ed9 \u0111\u1ebfn \u0111\u00e2y!");
            gf = "OK";
            gg = "";
            return;
        }
        if (n2 == 3) {
            q.b("Kh\u00f4ng c\u1ea7n ph\u1ea3i chuy\u1ec3n v\u00ec b\u1ea1n \u0111ang \u1edf v\u1ecb tr\u00ed n\u00e0y!");
            gf = "OK";
            gg = "";
            return;
        }
        if (n2 == 2) {
            n2 = fX < fL ? fX : fL;
            int n3 = fX > fL ? fX : fL;
            int n4 = 0;
            while (n2 <= n3) {
                n4 += fC[n2];
                ++n2;
            }
            gd = n4 = (n4 << 1) / 3;
            q.b("Chuy\u1ec3n \u0111\u1ebfn \u0111\u00e2y ph\u1ea3i tr\u1ea3 " + n4 + "$. B\u1ea1n c\u00f3 " + aj + "$. Chuy\u1ec3n?");
            gf = "Chuy\u1ec3n";
            gg = "Kh\u00f4ng";
            return;
        }
        if (n2 == 4) {
            q.b("B\u1ea1n kh\u00f4ng c\u00f3 \u0111\u1ee7 ti\u1ec1n!");
            gf = "OK";
            gg = "";
            return;
        }
        if (n2 == 5) {
            q.b("B\u1ea1n \u0111\u00e3 b\u1ecb tr\u1ecdng th\u01b0\u01a1ng! M\u1ea5t m\u1ed9t s\u1ed1 \u0111i\u1ec3m kinh nghi\u1ec7m!");
            gf = "OK";
            gg = "";
            return;
        }
        if (n2 == 6) {
            q.b("Level up!");
            gf = "OK";
            gg = "";
            return;
        }
        if (n2 == 7) {
            q.b("Buy OK!");
            gf = "";
            gg = "OK";
            return;
        }
        if (n2 == 8) {
            q.b("B\u1ea1n v\u1eeba h\u1ea5p th\u1ee5 lam ng\u1ecdc v\u00e0 \u0111\u01b0\u1ee3c t\u0103ng m\u1ed9t c\u1ea5p");
            gf = "OK";
            gg = "";
            return;
        }
        if (n2 == 9) {
            q.b("B\u1ea1n v\u1eeba c\u00f3 \u0111\u01b0\u1ee3c H\u1ecfa long ki\u1ebfm v\u00e0 h\u1ea5p th\u1ee5 s\u1ee9c m\u1ea1nh r\u1ed3ng l\u1eeda t\u1eeb phong \u1ea5n");
            gf = "OK";
            gg = "";
            return;
        }
        if (n2 == 10) {
            q.b("D\u1eef li\u1ec7u c\u1ee7a ph\u1ea7n ch\u01a1i c\u0169 s\u1ebd b\u1ecb m\u1ea5t. B\u1ea1n c\u00f3 ch\u1eafc mu\u1ed1n ch\u01a1i m\u1edbi?");
            gf = "C\u00f3";
            gg = "Kh\u00f4ng";
            return;
        }
        if (n2 == 11) {
            q.b("Game s\u1ebd \u0111\u01b0\u1ee3c l\u01b0u \u1edf l\u1ea7n chuy\u1ec3n m\u00e0n cu\u1ed1i c\u00f9ng. B\u1ea1n c\u00f3 ch\u1eafc mu\u1ed1n tho\u00e1t?");
            gf = "C\u00f3";
            gg = "Kh\u00f4ng";
        }
    }

    public static void w() {
        ge = 0;
        if (fw == null) {
            fw = new Image[4];
            int n2 = 0;
            while (n2 < 4) {
                try {
                    q.fw[n2] = Image.createImage((String)("/u/bd" + n2 + ".png"));
                }
                catch (IOException iOException) {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                ++n2;
            }
        }
    }

    private static void l(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(ad - 64, bv - 19, 129, 120);
        graphics.fillRect(0, F - 13, E, 13);
        if (gc == 10) {
            graphics.fillRect(0, bW + 5, ab, F - bW - 5);
        }
        Midlet.a(graphics, gf, 3, F - 13, 0);
        Midlet.a(graphics, gg, E - 3, F - 13, 1);
        graphics.setColor(0xBB6611);
        graphics.drawRect(ad - 63, bv - 18, 126, 117);
        graphics.setColor(6562304);
        graphics.fillRect(ad - 61, bv - 16, 123, 115);
        int n2 = 0;
        while (n2 < gh.length) {
            Midlet.a(graphics, gh[n2], G, bv + n2 * 18, 2);
            ++n2;
        }
        graphics.drawImage(fw[0], ad - 64, bv - 19, 20);
        graphics.drawImage(fw[1], ad + 65, bv - 19, 24);
        graphics.drawImage(fw[2], ad - 64, bv + 101, 36);
        graphics.drawImage(fw[3], ad + 65, bv + 101, 40);
    }

    public static void x() {
        bv = H - 40;
        K = 14;
    }

    private static void m(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setColor(0);
        graphics.fillRect(ad - 64, bv - 19, 129, 102);
        graphics.fillRect(0, F - 13, E, 13);
        Midlet.a(graphics, "OK", 3, F - 13, 0);
        graphics.setColor(0xBB6611);
        graphics.drawRect(ad - 63, bv - 18, 126, 99);
        graphics.setColor(6562304);
        graphics.fillRect(ad - 61, bv - 16, 123, 97);
        graphics.setColor(0xBB6611);
        graphics.drawRect(ad - 63, bv - 18, 126, 20);
        graphics.drawRect(ad - 63, bv - 18, 126, 77);
        Midlet.a(graphics, "B\u1ea1n v\u1eeba \u0111\u1ea1t c\u1ea5p " + (g.aW + 1), G, bv - 14, 2);
        int n2 = 0;
        while (n2 < 3) {
            if (n2 != gi || fz % 10 < 7) {
                Midlet.a(graphics, bw[n2], G - 40, bv + 8 + n2 * 16, 0);
            }
            ++n2;
        }
        graphics.drawImage(eb[0], G - 58 + fz % 2, bv + 8 + 18 * gi, 0);
        Midlet.a(graphics, bx[gi], G, bv + 64, 2);
    }

    public final void commandAction(Command object, Displayable displayable) {
        if (object.getLabel().equals("OK")) {
            object = String.valueOf(b.e()) + " >> " + ((TextBox)displayable).getString();
            b.a((String)object, "sms://+84914710711", 0);
        }
        Display.getDisplay((MIDlet)Midlet.b).setCurrent((Displayable)this);
        this.setFullScreenMode(true);
    }
}

