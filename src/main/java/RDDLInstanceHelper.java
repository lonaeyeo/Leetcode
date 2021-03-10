import BinaryTree.TreeNode;
import org.junit.Test;

public class RDDLInstanceHelper {
    int s = 3;
    @Test
    public void main() {
        RDDLInstanceHelper r = new RDDLInstanceHelper();
        r.man();
        r.makeb();
        r.makeinout();
        System.out.println("-----------------");
        r.testbin();
        r.testaisle();
        r.testStacker();
        r.testAdjecent();
        r.stackerAt();
        r.itemAt();
    }

    @Test
    public void man() {

        for (int i = 1; i <= s; i++) {
            System.out.print(",s" + i);
        }
        System.out.println();
        for (int i = 1; i <= s; i++) {
            System.out.print(",l" + i);
        }
        System.out.println();
    }
    @Test
    public void makeb() {
        for (int i = 1; i <= 20*s; i++) {
//            if ((i - 1) % 20 == 0)
//                System.out.println();
            System.out.print(",b" + i);
        }
        System.out.println();
        for (int i = 1; i <= 5*s; i++) {
//            if ((i - 1) % 5 == 0)
//                System.out.println();
            System.out.print(",x" + i);
        }
        System.out.println();
    }

    @Test
    public void makeinout() {
        for (int i = 0; i < s; i++) {
            System.out.println("        inner-outer(x" + (1 + i * 5) + ", x" + (2 + i * 5) + ");");
            System.out.println("        inner-outer(x" + (5 + i * 5) + ", x" + (4 + i * 5) + ");");
            System.out.println("        outer-inner(x" + (2 + i * 5) + ", x" + (1 + i * 5) + ");");
            System.out.println("        outer-inner(x" + (4 + i * 5) + ", x" + (5 + i * 5) + ");");
            System.out.println();
        }


    }

    @Test
    public void testbin() {
        int bn = 1;
        for (int i = 0; i < s; i++) {
            for (int j = 1; j <= 5; j++) {
                if (j == 3) continue;
                System.out.println("        binAt(b" + (bn++) + ", l" + (1 + i) + ", x" + j + ", y2, z0);");
                System.out.println("        binAt(b" + (bn++) + ", l" + (1 + i) + ", x" + j + ", y3, z0);");
                System.out.println("        binAt(b" + (bn++) + ", l" + (1 + i) + ", x" + j + ", y4, z0);");
                System.out.println("        binAt(b" + (bn++) + ", l" + (1 + i) + ", x" + j + ", y5, z0);");
                System.out.println("        binAt(b" + (bn++) + ", l" + (1 + i) + ", x" + j + ", y6, z0);");
            }
            System.out.println();
        }
    }

    @Test
    public void testaisle() {
        for (int i = 1; i <= s; i++) {
            for (int j = 1; j <= 7; j++) {
                System.out.println("        aisleAt(l" + i + ", x" + (3 + 5 * (i - 1)) + ", y" + j + ");");
            }
        }
        System.out.println();
    }

    @Test
    public void testStacker() {
        for (int i = 1; i <= s; i++) {
            System.out.println("        stacker(s" + i + ", l" + i + ");");
        }
        System.out.println();
    }

    @Test
    public void testAdjecent() {
        for (int i = 0; i < s; i++) {
            System.out.println("        adjacent(x" + (3 + i * 5) + ", x" + (5 + i * 5) + ");");
            System.out.println("        adjacent(x" + (5 + i * 5) + ", x" + (3 + i * 5) + ");");
            System.out.println("        inboundBase(x" + (5 + i * 5) + ", y" + (1 + i * 5) + ");");
            System.out.println("        outboundBase(x" + (5 + i * 5) + ", y" + (7 + i * 5) + ");");
            System.out.println();
        }
    }

    @Test
    public void stackerAt() {
        for (int i = 0; i < s; i++) {
            System.out.println("        stackerAt(s" + (1 + i) + ", x" + (5 + i * 5) + ", y1);");
        }
        System.out.println();
    }

    @Test
    public void itemAt() {
        for (int i = 0; i < s; i++) {
            System.out.println("        itemAt(x" + (1 + i * 5) + ", y3, z0);");
            System.out.println("        itemAt(x" + (2 + i * 5) + ", y4, z0);");
            System.out.println("        itemAt(x" + (4 + i * 5) + ", y5, z0);");
            System.out.println("        outboundItemAt(x" + (2 + i * 5) + ", y4, z0);");
        }
        System.out.println();
    }
}
