package cha6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPClient {

    public static void main(String[] args) throws IOException {

        DatagramSocket client = new DatagramSocket(9999);

        byte[] message = "UDP传输，可能会失败".getBytes();

        InetSocketAddress addr = new InetSocketAddress("127.0.0.1",8888);
        DatagramPacket packet = new DatagramPacket(message,message.length,addr);

        client.send(packet);
        System.out.println("------数据发送成功------");

        client.close();

    }

}
