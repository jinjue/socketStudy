package cha5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 一个客户端,一个服务端
 * 服务端可以不断获取客户端发送的信息,并且发送信息给客户端
 */

public class Server2 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8888);

        System.out.println("--------服务端启动--------");

        Socket client = server.accept();


        while(true){
            InputStream in = client.getInputStream();
            DataInputStream dataIn = new DataInputStream(in);
            String message = dataIn.readUTF();
            System.out.println("客户端："+message);

            OutputStream out = client.getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(out);
            System.out.print("输入消息（按q|Q退出）：");
            String s = scanner.next();
            dataOut.writeUTF(s);
            dataOut.flush();

            if(s.equals("q")||s.equals("Q"))
                break;
        }


        client.close();
        server.close();



    }
}
