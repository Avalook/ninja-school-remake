/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class o
extends Vector {
    public static o a = new o();
    public static o b = new o();

    public static void a() {
        h.a(0);
        h.a(1);
        h.a(3);
        h.a(10);
        h.a(11);
        h.a(12);
        h.a(13);
        h.a(14);
        h.a(15);
        h.a(16);
        h.a(17);
        h.a(18);
        h.a(19);
        h.a(20);
        h.a(21);
        h.a(22);
        h.a(23);
        h.a(23);
        h.a(24);
        h.a(25);
        h.a(26);
        h.a(27);
        h.a(28);
        h.a(29);
        h.a(30);
        h.a(31);
        h.a(32);
    }

    public final void b() {
        int n2 = this.size() - 1;
        while (n2 >= 0) {
            h h2 = (h)this.elementAt(n2);
            h2.a();
            if (h2.g) {
                this.removeElementAt(n2);
            }
            --n2;
        }
    }

    public final void a(Graphics graphics) {
        int n2 = 0;
        while (n2 < this.size()) {
            ((h)this.elementAt(n2)).a(graphics);
            ++n2;
        }
    }
}

