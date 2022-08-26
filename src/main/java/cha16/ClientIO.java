package cha16;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientIO {

    private static Scanner scanner = new Scanner(System.in);
    private static String s;

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",8888);

        System.out.println("--------服务端启动--------");

        new ThreadClient(client).start();

        while(true){
//            OutputStream out = client.getOutputStream();
//            DataOutputStream dataOut = new DataOutputStream(out);
//            System.out.print("输入消息（按q|Q退出）：");
//            String s = scanner.next();
//            dataOut.writeUTF(s);
//            dataOut.flush();

            if(s!=null && (s.equals("q")||s.equals("Q")))
                break;

            InputStream in = client.getInputStream();
            DataInputStream dataIn = new DataInputStream(in);
            String message = dataIn.readUTF();
            System.out.println("服务端："+message);
        }


        client.close();

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
