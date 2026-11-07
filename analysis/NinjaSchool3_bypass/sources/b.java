/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.rms.RecordStore
 */
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;

public final class b {
    public static boolean a;
    private static String[] f;
    public static String[] b;
    public static int c;
    private static int g;
    public static int d;
    public static Font e;
    private static Font h;
    private static int i;
    private static String j;
    private static String k;
    private static int l;
    private static String m;
    private static int[] n;
    private static int o;
    private static int p;
    private static String q;
    private static int r;
    private static int s;

    static {
        f = new String[]{"1. Tiep tuc", "2. Kich hoat", "3. Nhap ma kich hoat"};
        b = new String[]{"1. Kich hoat", "2. Nhap ma kich hoat"};
        e = Font.getFont((int)0, (int)0, (int)0);
        h = Font.getFont((int)0, (int)1, (int)0);
        i = e.getHeight() + 6;
        j = "oplj";
        k = "12345678";
        n = new int[9];
        o = 3;
        p = 0;
        q = "";
        int n2 = 0;
        while (n2 < 9) {
            b.n[n2] = (n2 << 1) + 1;
            int n3 = n2;
            n[n3] = n[n3] + q.x;
            if (n2 > 4) {
                b.n[n2] = -1;
            }
            ++n2;
        }
    }

    private static void a(int n2) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n2;
        b.a("bc", byArray2);
    }

    private static void b(String string) {
        b.a("girl", string.getBytes());
    }

    private static void c(String string) {
        b.a("lv", string.getBytes());
    }

    public static void a() {
        l = 30;
        b.h();
        Object object = b.d("np");
        o = object == null ? -1 : object[0];
        if (o == -1) {
            o = l / 10;
            b.b(o);
            b.e("TMNS PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "41" + "000");
            b.c(0);
        }
        q.b();
        String string = System.getProperty("phone.imei");
        object = string;
        if (string == null || object.equals("")) {
            object = System.getProperty("com.nokia.IMEI");
        }
        if (object != null && !object.equals("")) {
            k = object;
        }
        byte[] byArray = b.d("im");
        object = byArray;
        String string2 = byArray == null ? null : new String((byte[])object);
        object = string2;
        if (string2 == null) {
            object = k;
            b.a("im", object.getBytes());
        } else if (!object.equals(k)) {
            o = 2;
            b.b(2);
            b.e("TMNS PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "41" + "000");
            b.c(0);
        }
        q.B = 13;
        byte[] byArray2 = b.d("r");
        object = byArray2;
        c = byArray2 == null ? 0 : object[0];
    }

    public static void b() {
        if (++g > 10000) {
            g = 0;
        }
        q.v = q.o.getWidth();
        q.w = q.o.getHeight();
        if (q.q && q.s > q.w - 20) {
            if (q.r < q.x) {
                q.u[12] = true;
            } else {
                q.u[13] = true;
            }
            q.q = false;
        }
        block0 : switch (c) {
            case 0: {
                if (q.u[2]) {
                    if (--p < 0) {
                        p = o == 0 ? 1 : 2;
                    }
                    q.c();
                }
                if (q.u[8]) {
                    if (++p > (o == 0 ? 1 : 2)) {
                        p = 0;
                    }
                    q.c();
                }
                if (q.u[5] || q.u[12]) {
                    b.n();
                    q.c();
                }
                if (q.u[13]) {
                    Midlet.b.notifyDestroyed();
                }
                if (q.q) {
                    int n2 = 0;
                    while (n2 < (o == 0 ? 2 : 3)) {
                        if (q.s > q.y - 5 + n2 * i && q.s < q.y - 5 + n2 * i + i) {
                            if (p != n2) {
                                p = n2;
                            } else {
                                b.n();
                            }
                            q.q = false;
                            break block0;
                        }
                        ++n2;
                    }
                }
                return;
            }
            case 2: {
                b.m();
                return;
            }
            case 5: {
                if (q.u[5] || q.u[12]) {
                    Midlet.b.notifyDestroyed();
                }
                return;
            }
            case 6: {
                b.l();
                return;
            }
            case 4: {
                int n3;
                if (q.u[2]) {
                    if (--s < 0) {
                        s = 1;
                    }
                    q.c();
                }
                if (q.u[8]) {
                    if (++s > 1) {
                        s = 0;
                    }
                    q.c();
                }
                if (q.u[4]) {
                    if (--r < 0) {
                        r = 4;
                    }
                    q.c();
                }
                if (q.u[6]) {
                    if (++r > 4) {
                        r = 0;
                    }
                    q.c();
                }
                if (q.u[5]) {
                    b.k();
                    q.c();
                }
                if (q.u[12]) {
                    if (m != null) {
                        b.n[5] = Integer.parseInt(m.substring(9, 10));
                        b.n[6] = Integer.parseInt(m.substring(10, 11));
                        b.n[7] = Integer.parseInt(m.substring(11, 12));
                        b.n[8] = Integer.parseInt(m.substring(12, 13));
                    }
                    if (q.length() < 4) {
                        c = 0;
                    } else {
                        b.n[1] = Integer.parseInt(q.substring(0, 1));
                        b.n[2] = Integer.parseInt(q.substring(1, 2));
                        b.n[3] = Integer.parseInt(q.substring(2, 3));
                        b.n[4] = Integer.parseInt(q.substring(3, 4));
                        if (n[8] != -1 && n[7] != -1 && n[6] != -1 && n[5] != -1) {
                            b.n[8] = (n[8] + n[7]) % 10;
                            b.n[7] = (n[7] + n[6]) % 10;
                            b.n[6] = (n[6] + n[5]) % 10;
                            b.n[5] = (n[5] + n[8]) % 10;
                            System.out.println("doFinishNumber " + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7] + n[8]);
                        }
                        n3 = 1;
                        while (n3 < 8) {
                            if (n[n3 - 1] != n[n3]) {
                                b.i();
                            } else if (n[n3] != n[n3] + 1) {
                                b.j();
                            } else {
                                q.c();
                                b.m();
                                b.j();
                                b.g();
                                q.c();
                            }
                            ++n3;
                        }
                    }
                    q.c();
                }
                if (q.u[13]) {
                    b.h();
                    q.c();
                }
                if (q.q) {
                    n3 = 0;
                    while (n3 < 2) {
                        int n4 = 0;
                        while (n4 < 5) {
                            if (q.r > n4 * 20 + q.x - 50 && q.r < n4 * 20 + q.x - 50 + 20 && q.s > n3 * 25 + q.y && q.s < n3 * 25 + q.y + 25) {
                                r = n4;
                                s = n3;
                                b.k();
                                q.q = false;
                            }
                            ++n4;
                        }
                        ++n3;
                    }
                }
                return;
            }
            case 7: {
                b.g();
                return;
            }
            case 8: {
                if (!q.u[12]) break;
                Midlet.b.notifyDestroyed();
            }
        }
    }

    private static void g() {
        if (q.u[12]) {
            Midlet.b.notifyDestroyed();
        }
        b.d(1);
        n[0] = n[0] + 1;
    }

    private static void h() {
        if (q.length() != 0) {
            q = q.substring(0, q.length() - 1);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void c() {
        Object object = System.getProperty("microedition.platform");
        String string = "";
        if (object == null) {
            object = "TeaMobile";
        }
        if (!a) {
            b.c((String)object);
            return;
        }
        Object object2 = b.d("lv");
        String string2 = object2 == null ? null : new String((byte[])object2);
        object2 = string2;
        if (string2 == null) {
            b.c(0);
            b.d(0);
            b.c((String)object);
            return;
        }
        if (!object2.equals(object)) {
            b.c(0);
            b.d(0);
            b.c((String)object);
            return;
        }
        int n2 = 0;
        while (n2 < 4) {
            string = String.valueOf(string) + (char)(j.charAt(n2) - '\u0001');
            ++n2;
        }
        string = String.valueOf(string) + 'a';
        if (((String)object).toLowerCase().indexOf(string) == -1) return;
        q.B = 13;
        byte[] byArray = b.d("bc");
        object = byArray;
        n2 = (int)(byArray == null ? 0 : object[0]);
        if (n2 < j.length() - 1) {
            b.a(n2 + 1);
            return;
        }
        byte[] byArray2 = b.d("girl");
        object = byArray2;
        object = byArray2 == null ? null : new String((byte[])object);
        string = null;
        try {
            string = "12345678";
        }
        catch (Exception exception) {}
        b.a(0);
    }

    private static void i() {
        q.c();
        c = 0;
        b.l();
        q.c();
    }

    private static void j() {
        q.c();
        b.m();
        c = 4;
        b.i();
        q.c();
    }

    private static void k() {
        if (q.length() < 4) {
            q = String.valueOf(q) + (s * 5 + r);
        }
    }

    private static void l() {
        if (q.u[5] || q.u[12]) {
            q.c();
            c = 0;
        }
    }

    private static void m() {
        if (q.u[5] || q.u[12]) {
            String string = b.e();
            if (string == null || string.equals("")) {
                string = "TMNS PAY " + (System.currentTimeMillis() % 8000L + 1000L) + "41" + "000";
                b.e(string);
            }
            c = 1;
            b.a(string, "sms://8724", 0);
            q.c();
        }
        if (q.u[13]) {
            c = 0;
            q.c();
        }
    }

    private static void n() {
        if (p == 0 && o > 0) {
            b.b(--o);
            q.B = 4;
            q.x();
            System.gc();
        }
        if (p == 1 && o > 0 || p == 0 && o == 0) {
            c = 2;
        }
        if (p == 2 && o > 0 || p == 1 && o == 0) {
            m = b.e();
            c = 4;
        }
    }

    protected static void a(Graphics graphics) {
        graphics.setColor(0xEAEAEA);
        graphics.fillRect(0, 0, q.v, q.w);
        graphics.setColor(11788624);
        graphics.fillRect(0, 0, q.v, i);
        graphics.fillRect(0, q.w - i, q.v, i);
        graphics.setColor(0xFFFFFF);
        graphics.setFont(h);
        graphics.drawString("Ninja School 3", q.x, 3, 17);
        graphics.setFont(e);
        switch (c) {
            case 0: {
                graphics.setColor(0x6D6D6D);
                if (n[1] != n[5] && n[2] != n[6] && n[3] != n[7] && n[4] != n[8]) {
                    c = 7;
                }
                graphics.setColor(11788624);
                graphics.drawRect(3, q.y - 8 + p * i, q.v - 6, i);
                graphics.drawRect(4, q.y - 8 + p * i + 1, q.v - 8, i - 2);
                graphics.setColor(208472);
                if (o > 0) {
                    graphics.setFont(h);
                    graphics.drawString("..:TEA MOBILE:..", q.x, q.y - (i << 1), 17);
                    graphics.setFont(e);
                    int n2 = 0;
                    while (n2 < 3) {
                        graphics.setColor(0x6D6D6D);
                        graphics.drawString(f[n2], d, q.y - 5 + n2 * i, 20);
                        ++n2;
                    }
                } else {
                    graphics.drawString("Da het han dung thu", q.x, q.y - (i << 1), 17);
                    graphics.drawString("Moi ban kich hoat.", q.x, q.y - i, 17);
                    int n3 = 0;
                    while (n3 < 2) {
                        graphics.setColor(0x6D6D6D);
                        graphics.drawString(b[n3], d, q.y - 5 + n3 * i, 20);
                        ++n3;
                    }
                }
                graphics.setColor(0xFFFFFF);
                graphics.setFont(h);
                graphics.drawString("Chon", 2, q.w - i + 3, 20);
                graphics.drawString("Thoat", q.v - 2, q.w - i + 3, 24);
                return;
            }
            case 2: {
                graphics.setColor(0x6D6D6D);
                graphics.drawString("Gui tin kich hoat ton phi", q.x, q.y - (i << 1), 17);
                graphics.drawString("15.000\u0111. Ban se nhan", q.x, q.y - i, 17);
                graphics.drawString("duoc ma kich hoat trong", q.x, q.y, 17);
                graphics.drawString("tin nhan tra loi.", q.x, q.y + i, 17);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(h);
                graphics.drawString("Dong y", 2, q.w - i + 3, 20);
                graphics.drawString("Khong", q.v - 2, q.w - i + 3, 24);
                return;
            }
            case 1: {
                graphics.setColor(g % 10 > 5 ? 0xFFFFFF : 0x6D6D6D);
                graphics.drawString("Dang gui tin", q.x, q.y - i, 17);
                graphics.drawString("kich hoat...", q.x, q.y, 17);
                return;
            }
            case 5: {
                graphics.setColor(0x6D6D6D);
                graphics.drawString("Da gui tin kich hoat.", q.x, q.y - (i << 1), 17);
                graphics.drawString("Xin thoat game va", q.x, q.y - i, 17);
                graphics.drawString("cho giay lat. Ban se", q.x, q.y, 17);
                graphics.drawString("nhan duoc ma kich hoat.", q.x, q.y + i, 17);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(h);
                graphics.drawString("OK", 2, q.w - i + 3, 20);
                return;
            }
            case 6: {
                graphics.setColor(0x6D6D6D);
                graphics.drawString("Khong the gui SMS", q.x, q.y - (i << 1), 17);
                graphics.drawString("Xin hay thu lai. Vao", q.x, q.y - i, 17);
                graphics.drawString("www.teamobi.com", q.x, q.y, 17);
                graphics.drawString("de biet them thong tin.", q.x, q.y + i, 17);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(h);
                graphics.drawString("OK", 2, q.w - i + 3, 20);
                return;
            }
            case 4: {
                graphics.setColor(0x6D6D6D);
                graphics.drawString("Nhap ma kich hoat:", q.x, q.y - 60, 17);
                graphics.setColor(11788624);
                graphics.fillRect(r * 20 + q.x - 50, s * 25 + q.y, 18, 23);
                int n4 = 0;
                int n5 = 0;
                while (n5 < 2) {
                    int n6 = 0;
                    while (n6 < 5) {
                        graphics.setColor(11788624);
                        graphics.drawRect(n6 * 20 + q.x - 50, n5 * 25 + q.y, 18, 23);
                        graphics.setColor(n6 == r && n5 == s ? 0xFFFFFF : 0x6D6D6D);
                        graphics.drawString(String.valueOf(n4), n6 * 20 + q.x - 50 + 10, n5 * 25 + q.y + 3, 17);
                        ++n4;
                        ++n6;
                    }
                    ++n5;
                }
                String string = q;
                if (g % 10 > 5) {
                    string = String.valueOf(string) + "|";
                }
                graphics.setColor(0x6D6D6D);
                graphics.drawString(string, q.x - 20, q.y - 40, 20);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(h);
                graphics.drawString("OK", 2, q.w - i + 3, 20);
                graphics.drawString("Xoa", q.v - 2, q.w - i + 3, 24);
                return;
            }
            case 7: {
                graphics.setColor(0);
                graphics.fillRect(0, 0, q.v, q.w);
                String string = q;
                if (g % 10 > 5) {
                    string = String.valueOf(string) + "|";
                }
                graphics.drawString(string, q.x - 20, q.y - 40, 20);
                b.c(7);
                q.x();
                q.B = 4;
                a = b.f() == 1;
                return;
            }
            case 8: {
                graphics.setColor(0x6D6D6D);
                graphics.drawString("Xin vui long", q.x, q.y - (i << 1), 17);
                graphics.drawString("Bat Bluetooth", q.x, q.y - i, 17);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(h);
                graphics.drawString("OK", 2, q.w - i + 3, 20);
            }
        }
    }

    public static void a(String string, String string2, int n2) {
        new Thread(new g(string2, string, n2)).start();
    }

    public static void d() {
        h.e();
        q.j();
        q.g(7);
        q.b("Xin ch\u00fac m\u1eebng. B\u1ea1n \u0111\u00e3 c\u00f3 v\u0169 kh\u00ed m\u1edbi: " + q.bk[q.bi][q.bh] + "!");
        q.bt = q.bg;
        q.B = 7;
    }

    private static byte[] d(String string) {
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

    private static void e(String string) {
        System.out.println("saveID " + string);
        b.a("xxx", string.getBytes());
    }

    public static String e() {
        byte[] byArray = b.d("xxx");
        if (byArray == null) {
            return null;
        }
        return new String(byArray);
    }

    private static void b(int n2) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n2;
        b.a("np", byArray2);
    }

    private static void c(int n2) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n2;
        b.a("r", byArray2);
    }

    private static void d(int n2) {
        byte[] byArray = new byte[1];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n2;
        b.a("g", byArray2);
    }

    public static int f() {
        byte[] byArray = b.d("g");
        if (byArray == null) {
            return 0;
        }
        return byArray[0];
    }

    public static String a(String string) {
        int n2 = Integer.parseInt(string.substring(0, 1));
        int n3 = Integer.parseInt(string.substring(1, 2));
        int n4 = Integer.parseInt(string.substring(2, 3));
        int n5 = Integer.parseInt(string.substring(3, 4));
        n5 = (n5 + n4) % 10;
        n4 = (n4 + n3) % 10;
        n3 = (n3 + n2) % 10;
        n2 = (n2 + n5) % 10;
        return "" + n2 + n3 + n4 + n5;
    }
}

