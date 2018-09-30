package algorithms;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

public class NIOTest {

    public static void main(String[] args) {

        //        try {
        //            ServerSocketChannel socketChannel = ServerSocketChannel.open();
        //            socketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"), 8080));
        //            socketChannel.configureBlocking(false);
        //
        //            Selector selector = Selector.open();
        ////            new Thread(new ReactorTask()).run();
        ////
        ////            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_ACCEPT, ioHandler);
        //
        //            int count = selector.select();
        //            Set<SelectionKey> keys = selector.selectedKeys();
        //            Iterator<SelectionKey> iterable = keys.iterator();
        //            while (iterable.hasNext()) {
        //                SelectionKey key = iterable.next();
        //
        //            }
        //
        //            SocketChannel socket = socketChannel.accept();
        //            socket.configureBlocking(false);
        //            socket.socket().setReuseAddress(true);
        //
        //
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        Map map = new HashMap();
        map.put("1", 1);
        map.forEach((k, v) -> System.out.println(k+","+v));
//        System.out.println(a.size());
//        String s = "1000000000000000000000000000010500100000020000214130020000000000000000000000000000000000000000000000";
//        System.out.println(s.length());
//        System.out.println(s.charAt(50));
//        System.out.println(s.substring(50, 51));

    }

}
