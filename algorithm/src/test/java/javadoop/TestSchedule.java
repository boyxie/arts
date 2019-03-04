package javadoop;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TestSchedule {

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("AaAa",
            key -> map.
                computeIfAbsent("BBBB", key2 -> "value"));
//        System.out.println(f1(5));
//        LinkedHashMap map = new LinkedHashMap();
//        map.get("1");
//        Pattern p = Pattern.compile("a*b");
//        Matcher m = p.matcher("aaaaab");
//        m.matches();
    }

    static int f(int n){
        if (n<2) return n;
        return f(n - 1) + f(n - 2);
    }

    static int f1(int n){
        System.out.println(n);
        if (n<1) return 1;
        return n * f1(n - 1);
    }

}
