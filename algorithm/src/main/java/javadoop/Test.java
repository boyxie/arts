package javadoop;

import org.apache.commons.lang3.RandomUtils;

public class Test {

    private static final String BASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String toBase62(long num) {
        StringBuilder sb = new StringBuilder();
        int targetBase = BASE.length();
        do {
            int i = (int) (num % targetBase);
            sb.append(BASE.charAt(i));
            num /= targetBase;
        } while (num > 0);

        return sb.reverse().toString();
    }

    public static long toBase10(String input) {
        int srcBase = BASE.length();
        long id = 0;
        String r = new StringBuilder(input).reverse().toString();

        for (int i = 0; i < r.length(); i++) {
            int charIndex = BASE.indexOf(r.charAt(i));
            id += charIndex * (long) Math.pow(srcBase, i);
        }

        return id;
    }

    private static long insertRandomBitPer5Bits(long val) {
        long result = val;
        long high = val;
        for (int i = 0; i < 10; i++) {
            if (high == 0) {
                break;
            }
            int pos = 5 + 5 * i + i;
            high = result >> pos;
            result = ((high << 1 | RandomUtils.nextInt(0, 2)) << pos)
                | (result & (-1L >>> (64 - pos)));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(toBase10("https://www.baidu.com"));
        System.out.println(toBase62(toBase10("https://www.baidu.com")));
        System.out.println(insertRandomBitPer5Bits(100100050001L));
        System.out.println(toBase62(insertRandomBitPer5Bits(100100050001L)));
    }

}
