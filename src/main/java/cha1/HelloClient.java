package cha1;

import java.io.*;
import java.net.Socket;

public class HelloClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",8888);

        System.out.println("--------客户端启动--------");

        InputStream in = client.getInputStream();
        DataInputStream dataIn = new DataInputStream(in);
        String message = dataIn.readUTF();
        System.out.println("服务端："+message);



        client.close();

    }
}
