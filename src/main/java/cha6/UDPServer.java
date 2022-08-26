package cha6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(8888);

        byte[] message = new byte[1024];
        DatagramPacket packet = new DatagramPacket(message,message.length);

        System.out.println("------等待传输数据-------");
        server.receive(packet);

        int length = packet.getLength();
        System.out.println("数据："+new String(message,0,length));

        server.close();

    }

}
