package javadoop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class HashMapTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add( "3");
        list.add("4");


        Iterator<String> iterable = list.iterator();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (iterable.hasNext()) {
                    String s = iterable.next();
                    System.out.println(s);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (iterable.hasNext()) {
                    String s = iterable.next();
                    if (s.equals("1")) {
                        iterable.remove();
                    }
                }
            }
        }).start();

    }
}
