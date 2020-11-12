import org.junit.Test;

public class RotateRings {

    @Test
    public void test() {
        RotateRings rotateRings = new RotateRings();
        int steps = rotateRings.findRotateSteps("godding", "dog");
        System.out.println(steps);
    }

    public int findRotateSteps(String ring, String key) {
        char[] ringC = ring.toCharArray();
        char[] keyC = key.toCharArray();

        int steps = 0;

        int ringLen = ringC.length;
        int current = 0;
        int left;
        int right;

        for (int i = 0; i < keyC.length; ++i) {
            if (ringC[current] == keyC[i]) {
                ++steps;
                continue;
            }

            ++steps;
            left = (current - 1 + ringLen) % ringLen;
            right = (current + 1) % ringLen;

            while (left != current && right != current) {
                if (ringC[left] == keyC[i]) {
                    ++steps;
                    current = left;
                    System.out.println(ringC[left]);
                    break;
                } else if (ringC[right] == keyC[i]) {
                    ++steps;
                    current = right;
                    System.out.println(ringC[right]);
                    break;
                }

                left = (left - 1 + ringLen) % ringLen;
                right = (right + 1) % ringLen;
                ++steps;
            }
        }

        return steps;
    }
}
