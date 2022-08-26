package cha5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",8888);

        System.out.println("--------服务端启动--------");

        while(true){
            OutputStream out = client.getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(out);
            System.out.print("输入消息（按q|Q退出）：");
            String s = scanner.next();
            dataOut.writeUTF(s);
            dataOut.flush();

            if(s.equals("q")||s.equals("Q"))
                break;

            InputStream in = client.getInputStream();
            DataInputStream dataIn = new DataInputStream(in);
            String message = dataIn.readUTF();
            System.out.println("服务端："+message);
        }


        client.close();

    }
}
