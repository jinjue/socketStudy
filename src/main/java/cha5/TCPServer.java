package cha5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws IOException {
       // 1. 创建服务器serversocket对象和系统要指定的端口号
        ServerSocket server = new ServerSocket(8888);
        //2. 使用ServerSocket对象中的方法accept,获取到请求的客户端对象socket
        Socket client_socket = server.accept();
        //3. 使用socket对象中的方法getInputstream()获取网络字节输入流InputStream对象
        InputStream in = client_socket.getInputStream();
        //4. 使用网络字节输入流Inputstream对象中的方法read,读取客户端发送的数据
        byte[] bytes = new byte[1024];
        int len = in.read(bytes);
        System.out.println(new String(bytes,0,len));
        //5. 使用socket对象中的方法getoutputstream ()获取网络字节输出流outputstream对象
        OutputStream out = client_socket.getOutputStream();
        //6. 使用网络字节输出流outputstream对象中的方法write,给客户端回写数据
        out.write(bytes);
        //7. 释放资源(Socket , serversocket)
        client_socket.close();
        server.close();
    }



}
