package javadoop;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        while (true) {
            //连接后不一定直接能读
            SocketChannel channel = serverSocketChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            int num;
            // 每个连接新起一个线程或者放入线程池
            if((num = channel.read(buffer)) > 0) {
                buffer.flip();
                System.out.println(num);
                //取出字节放入新的数组，如果直接调用buffer.array()方法会解析分配的全部字节
                byte[] bytes = new byte[num];
                buffer.get(bytes);
                String msg = new String(bytes, "UTF-8");
                System.out.println(msg);
                channel.write(ByteBuffer.wrap(("server has send to " + msg).getBytes()));
                buffer.clear();
            }
        }

    }
}
