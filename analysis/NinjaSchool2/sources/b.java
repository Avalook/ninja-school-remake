/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.bluetooth.LocalDevice
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.rms.RecordStore
 */
import javax.bluetooth.LocalDevice;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;

public final class b {
    public static boolean a;
    public static String b;
    static String[][] c;
    public static String[][] d;
    public static String e;
    private static String[] k;
    public static String[] f;
    public static int g;
    private static int l;
    public static int h;
    public static Font i;
    private static Font m;
    private static int n;
    static boolean j;
    private static String o;
    private static String p;
    private static int q;
    private static String r;
    private static int[] s;
    private static int t;
    private static int u;
    private static String v;
    private static int w;
    private static int x;

    static {
        b = "sms://8724";
        c = new String[][]{{"BinhHP_Nho(50)", "BinhHP_Vua(50)", "BinhHP_To(30)", "BinhMP_Nho(50)", "BinhMP_Vua(50)", "BinhMP_To(30)"}, {"Thuoc", "ThuyLinhKiem", "HoaGiacKiem", "LongGiaoKiem"}};
        d = new String[][]{{"sms://" + q.bj[4], "sms://" + q.bj[5], "sms://" + q.bj[5], "sms://" + q.bj[4], "sms://" + q.bj[5], "sms://" + q.bj[5]}, {"sms://" + q.bj[4], "sms://" + q.bj[4], "sms://" + q.bj[5], "sms://" + q.bj[6]}};
        e = "TD15";
        k = new String[]{"1. Tiep tuc", "2. Kich hoat", "3. Nhap ma kich hoat"};
        f = new String[]{"1. Kich hoat", "2. Nhap ma kich hoat"};
        i = Font.getFont((int)0, (int)0, (int)0);
        m = Font.getFont((int)0, (int)1, (int)0);
        n = i.getHeight() + 6;
        j = false;
        o = "oplj";
        p = "12345678";
        s = new int[9];
        t = 3;
        u = 0;
        v = "";
        int n2 = 0;
        while (n2 < 9) {
            b.s[n2] = (n2 << 1) + 1;
            int n3 = n2++;
            s[n3] = s[n3] + q.G;
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
        q = 30;
        b.h();
        Object object = b.d("np");
        t = object == null ? -1 : object[0];
        if (t == -1) {
            t = q / 10;
            b.b(t);
            b.e(String.valueOf(e) + " PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "110" + "000");
            b.c(0);
        }
        q.b();
        String string = System.getProperty("phone.imei");
        object = string;
        if (string == null || object.equals("")) {
            object = System.getProperty("com.nokia.IMEI");
        }
        if (object != null && !object.equals("")) {
            p = object;
        }
        byte[] byArray = b.d("im");
        object = byArray;
        String string2 = byArray == null ? null : new String((byte[])object);
        object = string2;
        if (string2 == null) {
            object = p;
            b.a("im", object.getBytes());
        } else if (!object.equals(p)) {
            t = 2;
            b.b(2);
            b.e(String.valueOf(e) + " PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "110" + "000");
            b.c(0);
        }
        q.K = 13;
        byte[] byArray2 = b.d("r");
        object = byArray2;
        g = byArray2 == null ? 0 : object[0];
    }

    public static void b() {
        if (++l > 10000) {
            l = 0;
        }
        q.E = q.x.getWidth();
        q.F = q.x.getHeight();
        if (q.z && q.B > q.F - 20) {
            if (q.A < q.G) {
                q.D[12] = true;
            } else {
                q.D[13] = true;
            }
            q.z = false;
        }
        block0 : switch (g) {
            case 0: {
                if (q.D[2]) {
                    if (--u < 0) {
                        u = t == 0 ? 1 : 2;
                    }
                    q.c();
                }
                if (q.D[8]) {
                    if (++u > (t == 0 ? 1 : 2)) {
                        u = 0;
                    }
                    q.c();
                }
                if (q.D[5] || q.D[12]) {
                    b.n();
                    q.c();
                }
                if (q.D[13]) {
                    Midlet.b.notifyDestroyed();
                }
                if (q.z) {
                    int n2 = 0;
                    while (n2 < (t == 0 ? 2 : 3)) {
                        if (q.B > q.H - 5 + n2 * n && q.B < q.H - 5 + n2 * n + n) {
                            if (u != n2) {
                                u = n2;
                            } else {
                                b.n();
                            }
                            q.z = false;
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
                if (q.D[5] || q.D[12]) {
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
                if (q.D[2]) {
                    if (--x < 0) {
                        x = 1;
                    }
                    q.c();
                }
                if (q.D[8]) {
                    if (++x > 1) {
                        x = 0;
                    }
                    q.c();
                }
                if (q.D[4]) {
                    if (--w < 0) {
                        w = 4;
                    }
                    q.c();
                }
                if (q.D[6]) {
                    if (++w > 4) {
                        w = 0;
                    }
                    q.c();
                }
                if (q.D[5]) {
                    b.k();
                    q.c();
                }
                if (q.D[12]) {
                    n3 = e.length() + 5;
                    b.s[5] = Integer.parseInt(r.substring(n3++, n3));
                    b.s[6] = Integer.parseInt(r.substring(n3++, n3));
                    b.s[7] = Integer.parseInt(r.substring(n3++, n3));
                    b.s[8] = Integer.parseInt(r.substring(n3++, n3));
                    if (v.length() < 4) {
                        g = 0;
                    } else {
                        b.s[1] = Integer.parseInt(v.substring(0, 1));
                        b.s[2] = Integer.parseInt(v.substring(1, 2));
                        b.s[3] = Integer.parseInt(v.substring(2, 3));
                        b.s[4] = Integer.parseInt(v.substring(3, 4));
                        b.s[8] = (s[8] + s[7]) % 10;
                        b.s[7] = (s[7] + s[6]) % 10;
                        b.s[6] = (s[6] + s[5]) % 10;
                        b.s[5] = (s[5] + s[8]) % 10;
                        n3 = 1;
                        while (n3 < 8) {
                            if (s[n3 - 1] != s[n3]) {
                                b.i();
                            } else if (s[n3] != s[n3] + 1) {
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
                if (q.D[13]) {
                    b.h();
                    q.c();
                }
                if (q.z) {
                    n3 = 0;
                    while (n3 < 2) {
                        int n4 = 0;
                        while (n4 < 5) {
                            if (q.A > n4 * 20 + q.G - 50 && q.A < n4 * 20 + q.G - 50 + 20 && q.B > n3 * 25 + q.H && q.B < n3 * 25 + q.H + 25) {
                                w = n4;
                                x = n3;
                                b.k();
                                q.z = false;
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
                if (q.D[12]) {
                    Midlet.b.notifyDestroyed();
                }
                return;
            }
            case 9: {
                if (!q.D[13]) break;
                q.D[13] = false;
                Midlet.b.notifyDestroyed();
            }
        }
    }

    private static void g() {
        if (q.D[12]) {
            Midlet.b.notifyDestroyed();
        }
        b.d(1);
        s[0] = s[0] + 1;
    }

    private static void h() {
        if (v.length() != 0) {
            v = v.substring(0, v.length() - 1);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void c() {
        Object object = System.getProperty("microedition.platform");
        String string = "";
        if (object == null) {
            object = "TeaMobile";
        }
        if (a) {
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
                string = String.valueOf(string) + (char)(o.charAt(n2) - '\u0001');
                ++n2;
            }
            string = String.valueOf(string) + 'a';
            if (((String)object).toLowerCase().indexOf(string) == -1) return;
            q.K = 13;
            byte[] byArray = b.d("bc");
            object = byArray;
            n2 = (int)(byArray == null ? 0 : object[0]);
            if (n2 >= o.length() - 1) {
                byte[] byArray2 = b.d("girl");
                object = byArray2;
                object = byArray2 == null ? null : new String((byte[])object);
                string = null;
                try {
                    string = LocalDevice.getLocalDevice().getBluetoothAddress();
                }
                catch (Exception exception) {}
                if (string == null) {
                    q.y = !q.y;
                    return;
                } else if (object == null) {
                    b.b(string);
                    return;
                } else if (!string.equals(object)) {
                    b.c(0);
                    b.d(0);
                    b.b(string);
                    b.e(String.valueOf(e) + " PAY " + (int)(System.currentTimeMillis() % 8599L + 1000L) + "110" + "000");
                    return;
                } else {
                    b.a(0);
                }
                return;
            }
            b.a(n2 + 1);
            return;
        }
        b.c((String)object);
    }

    private static void i() {
        q.c();
        g = 0;
        b.l();
        q.c();
    }

    private static void j() {
        q.c();
        b.m();
        g = 4;
        b.i();
        q.c();
    }

    private static void k() {
        if (v.length() < 4) {
            v = String.valueOf(v) + (x * 5 + w);
        }
    }

    private static void l() {
        if (q.D[5] || q.D[12]) {
            q.c();
            g = 0;
        }
    }

    private static void m() {
        if (q.D[5] || q.D[12]) {
            String string = b.e();
            if (string == null || string.equals("")) {
                string = String.valueOf(e) + " PAY " + (System.currentTimeMillis() % 8000L + 1000L) + "110" + "000";
                b.e(string);
            }
            g = 1;
            b.a(string, b, 0);
            q.c();
        }
        if (q.D[13]) {
            g = 0;
            q.c();
        }
    }

    private static void n() {
        if (u == 0 && t > 0) {
            b.b(--t);
            q.K = 4;
            q.u();
            System.gc();
        }
        if (u == 1 && t > 0 || u == 0 && t == 0) {
            g = 2;
        }
        if (u == 2 && t > 0 || u == 1 && t == 0) {
            r = b.e();
            g = 4;
        }
    }

    protected static void a(Graphics graphics) {
        graphics.setColor(0xEAEAEA);
        graphics.fillRect(0, 0, q.E, q.F);
        graphics.setColor(11788624);
        graphics.fillRect(0, 0, q.E, n);
        graphics.fillRect(0, q.F - n, q.E, n);
        graphics.setColor(0xFFFFFF);
        graphics.setFont(m);
        graphics.drawString("Ninja School 2", q.G, 3, 17);
        graphics.setFont(i);
        switch (g) {
            case 0: {
                graphics.setColor(0x6D6D6D);
                if (s[1] != s[5] && s[2] != s[6] && s[3] != s[7] && s[4] != s[8]) {
                    g = 7;
                }
                graphics.setColor(11788624);
                graphics.drawRect(3, q.H - 8 + u * n, q.E - 6, n);
                graphics.drawRect(4, q.H - 8 + u * n + 1, q.E - 8, n - 2);
                graphics.setColor(208472);
                if (t > 0) {
                    graphics.setFont(m);
                    graphics.drawString("VeChai321", q.G, q.H - (n << 1), 17);
                    graphics.setFont(i);
                    int n2 = 0;
                    while (n2 < 3) {
                        graphics.setColor(0x6D6D6D);
                        graphics.drawString(k[n2], h, q.H - 5 + n2 * n, 20);
                        ++n2;
                    }
                } else {
                    graphics.drawString("Da het han dung thu", q.G, q.H - (n << 1), 17);
                    graphics.drawString("Moi ban kich hoat.", q.G, q.H - n, 17);
                    int n3 = 0;
                    while (n3 < 2) {
                        graphics.setColor(0x6D6D6D);
                        graphics.drawString(f[n3], h, q.H - 5 + n3 * n, 20);
                        ++n3;
                    }
                }
                graphics.setColor(0xFFFFFF);
                graphics.setFont(m);
                graphics.drawString("Chon", 2, q.F - n + 3, 20);
                graphics.drawString("Thoat", q.E - 2, q.F - n + 3, 24);
                return;
            }
            case 2: {
                graphics.setColor(0x6D6D6D);
                graphics.drawString("Gui tin kich hoat ton phi", q.G, q.H - (n << 1), 17);
                graphics.drawString("15.000\u0111. Ban se nhan", q.G, q.H - n, 17);
                graphics.drawString("duoc ma kich hoat trong", q.G, q.H, 17);
                graphics.drawString("tin nhan tra loi.", q.G, q.H + n, 17);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(m);
                graphics.drawString("Dong y", 2, q.F - n + 3, 20);
                graphics.drawString("Khong", q.E - 2, q.F - n + 3, 24);
                return;
            }
            case 1: {
                graphics.setColor(l % 10 > 5 ? 0xFFFFFF : 0x6D6D6D);
                graphics.drawString("Dang gui tin", q.G, q.H - n, 17);
                graphics.drawString("kich hoat...", q.G, q.H, 17);
                return;
            }
            case 5: {
                graphics.setColor(0x6D6D6D);
                graphics.drawString("Da gui tin kich hoat.", q.G, q.H - (n << 1), 17);
                graphics.drawString("Xin thoat game va", q.G, q.H - n, 17);
                graphics.drawString("cho giay lat. Ban se", q.G, q.H, 17);
                graphics.drawString("nhan duoc ma kich hoat.", q.G, q.H + n, 17);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(m);
                graphics.drawString("OK", 2, q.F - n + 3, 20);
                return;
            }
            case 6: {
                graphics.setColor(0x6D6D6D);
                graphics.drawString("Khong the gui SMS", q.G, q.H - (n << 1), 17);
                graphics.drawString("Xin hay thu lai. Vao", q.G, q.H - n, 17);
                graphics.drawString("http://vechai321.xtgem.com", q.G, q.H, 17);
                graphics.drawString("de biet them thong tin.", q.G, q.H + n, 17);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(m);
                graphics.drawString("OK", 2, q.F - n + 3, 20);
                return;
            }
            case 4: {
                graphics.setColor(0x6D6D6D);
                graphics.drawString("Nhap ma kich hoat:", q.G, q.H - 60, 17);
                graphics.setColor(11788624);
                graphics.fillRect(w * 20 + q.G - 50, x * 25 + q.H, 18, 23);
                int n4 = 0;
                int n5 = 0;
                while (n5 < 2) {
                    int n6 = 0;
                    while (n6 < 5) {
                        graphics.setColor(11788624);
                        graphics.drawRect(n6 * 20 + q.G - 50, n5 * 25 + q.H, 18, 23);
                        graphics.setColor(n6 == w && n5 == x ? 0xFFFFFF : 0x6D6D6D);
                        graphics.drawString(String.valueOf(n4), n6 * 20 + q.G - 50 + 10, n5 * 25 + q.H + 3, 17);
                        ++n4;
                        ++n6;
                    }
                    ++n5;
                }
                String string = v;
                if (l % 10 > 5) {
                    string = String.valueOf(string) + "|";
                }
                graphics.setColor(0x6D6D6D);
                graphics.drawString(string, q.G - 20, q.H - 40, 20);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(m);
                graphics.drawString("OK", 2, q.F - n + 3, 20);
                graphics.drawString("Xoa", q.E - 2, q.F - n + 3, 24);
                return;
            }
            case 7: {
                graphics.setColor(0);
                graphics.fillRect(0, 0, q.E, q.F);
                String string = v;
                if (l % 10 > 5) {
                    string = String.valueOf(string) + "|";
                }
                graphics.drawString(string, q.G - 20, q.H - 40, 20);
                b.c(7);
                q.u();
                q.K = 4;
                a = b.f() == 1;
                return;
            }
            case 8: {
                graphics.setColor(0x6D6D6D);
                graphics.drawString("Xin vui long", q.G, q.H - (n << 1), 17);
                graphics.drawString("Bat Bluetooth", q.G, q.H - n, 17);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(m);
                graphics.drawString("OK", 2, q.F - n + 3, 20);
            }
        }
    }

    public static void a(String string, String string2, int n2) {
        new Thread(new f(string2, string, n2)).start();
    }

    public static void d() {
        g.e();
        q.i();
        q.h(7);
        q.b("Xin ch\u00fac m\u1eebng. B\u1ea1n \u0111\u00e3 c\u00f3 v\u0169 kh\u00ed m\u1edbi: " + q.bq[q.bo][q.bn] + "!");
        q.bv = q.bm;
        q.K = 7;
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

