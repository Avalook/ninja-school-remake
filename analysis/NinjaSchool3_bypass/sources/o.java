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
        j.a(0);
        j.a(3);
        j.a(10);
        j.a(13);
        j.a(14);
        j.a(15);
        j.a(16);
        j.a(17);
        j.a(18);
        j.a(19);
        j.a(20);
        j.a(21);
        j.a(22);
        j.a(23);
        j.a(24);
        j.a(25);
        j.a(26);
        j.a(27);
        j.a(28);
        j.a(29);
        j.a(30);
        j.a(31);
        j.a(32);
        j.a(33);
        j.a(34);
        j.a(35);
        j.a(36);
        j.a(37);
        j.a(38);
        j.a(39);
        j.a(40);
        j.a(41);
        j.a(42);
        j.a(44);
    }

    public final void b() {
        int n2 = this.size() - 1;
        while (n2 >= 0) {
            j j2 = (j)this.elementAt(n2);
            j2.a();
            if (j2.b) {
                this.removeElementAt(n2);
            }
            --n2;
        }
    }

    public final void a(Graphics graphics) {
        int n2 = 0;
        while (n2 < this.size()) {
            ((j)this.elementAt(n2)).a(graphics);
            ++n2;
        }
    }

    public final void b(Graphics graphics) {
        int n2 = 0;
        while (n2 < this.size()) {
            ((j)this.elementAt(n2)).b(graphics);
            ++n2;
        }
    }

    public final void c() {
        this.removeAllElements();
    }
}

