package cha5;

import org.apache.commons.lang.ObjectUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) throws IOException {

        //1. 创建一个客户端对象Socket,构造方法绑定服务器的IP地址和端口号
        Socket client = new Socket("127.0.0.1",8888);
        //2. 使用socket对象中的方法getoutputStream()获取网络字节输出流outputStream对象
        OutputStream out = client.getOutputStream();
        //3. 使用网络字节输出流outputstream对象中的方法write,给服务器发送数据
        out.write("hi！服务器~".getBytes());
       /**
        * 或者  将字节流包装成  数据字节输出流|特殊流（和上面的out.write作用差不多，都可以发送数据
        * DataOutputStream dataOut = new DataOutputStream(out);
        * dataOut.writeUTF("你好！服务器");
        * dataOut.flush();
        */

        //4. 使用socket对象中的方法getInputStream()获取网络字节输入流Inputstream对象
        InputStream in = client.getInputStream();
        //5. 使用网络字节输入流InputStream对象中的方法read ,读取服务器回写的数据
        byte[] bytes = new byte[1024];
        int len = in.read(bytes);
        System.out.println(new String(bytes,0,len));
        //6. 释放资源(Socket)
        client.close();

    }
}
