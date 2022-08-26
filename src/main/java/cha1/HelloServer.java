package cha1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloServer {


    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8888);

        System.out.println("--------服务端启动--------");

        Socket client = server.accept();

        OutputStream out = client.getOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);

        String s = "Hello World!";
        dataOut.writeUTF(s);
        dataOut.flush();

        client.close();
        server.close();



    }
}
