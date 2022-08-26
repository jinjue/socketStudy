package cha16;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 一个客户端,一个服务端
 * 客户端服务端收发信息分离
 */
public class ServerIO {

    private static Scanner scanner = new Scanner(System.in);
    private static String s;

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8888);

        System.out.println("--------服务端启动--------");

        Socket client = server.accept();

        new ThreadClient(client).start();

        while(true){
            InputStream in = client.getInputStream();
            DataInputStream dataIn = new DataInputStream(in);
            String message = dataIn.readUTF();
            System.out.println("客户端："+message);

            if(s!=null && (s.equals("q")||s.equals("Q")))
                break;
        }

        client.close();
        server.close();

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
                    OutputStream out  = client.getOutputStream();
                    DataOutputStream dataOut = new DataOutputStream(out);
                    System.out.print("输入消息：");
                    s = scanner.next();
                    dataOut.writeUTF(s);
                    dataOut.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
