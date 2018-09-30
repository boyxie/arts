package algorithms;

class Reverse {
    public static void main(String[] args) {
        System.out.println(reverseString("hello"));
    }

    public static String reverseString(String s) {
        if (s == null || "".equals(s.trim())) {return s;}
        if (s.length() < 2) {return s;}
        char[] arr = s.toCharArray();
        int size = arr.length - 1;
        for (int i = 0; i <= size; i++) {
            if (arr[i] == arr[size - i]) {
                continue;
            }
            if (i >= (size + 1) / 2) {
                break;
            }
            char temp = arr[i];
            arr[i] = arr[size - i];
            arr[size - i] = temp;
        }
        return String.valueOf(arr);
    }
}
