package cha18;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.util.List;

/**
 * 一个服务端，多个客户端
 */
public class TalkServer {

    //定义集合用于存储所有客户端套接字socket套接字
    public final static List<Socket> sockets = new ArrayList<Socket>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);

        System.out.println("--------------服务端启动-----------------");

        while(true){
            Socket client = server.accept();
            sockets.add(client);
            System.out.println("------  "+client.getRemoteSocketAddress()+"  上线  ------");
            System.out.println("在线人数："+sockets.size());

            new ThreadClient(client).start();

        }

    }

    private static class ThreadClient extends Thread{

        Socket client;
        public ThreadClient(){}

        public ThreadClient(Socket client){
            this.client = client;
        }

        @Override
        public void run() {

            try {
                while(true){
                    DataInputStream dataIn = new DataInputStream(client.getInputStream());
                    String str = dataIn.readUTF();
                    String message = client.getRemoteSocketAddress()+"："+str;

                    //对所有客户端进行广播
                    for (Socket socket : sockets) {
                        DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                        dataOut.writeUTF(message);
                        dataOut.flush();
                    }

                }

            } catch (Exception e) {
                System.out.println("------  "+client.getRemoteSocketAddress()+"  下线  ------");
                TalkServer.sockets.remove(client);
                System.out.println("在线人数："+sockets.size());

            }

        }
    }

}
