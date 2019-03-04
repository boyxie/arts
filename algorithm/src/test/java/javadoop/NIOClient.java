package javadoop;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress(8080));
        channel.write(ByteBuffer.wrap("hello my name is ok".getBytes()));
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int num;
        while ((num = channel.read(buffer)) > 0) {
            buffer.flip();
            System.out.println(num);
            String msg = new String(buffer.array(), "UTF-8");
            System.out.println(msg);
            buffer.clear();
        }
    }

}
