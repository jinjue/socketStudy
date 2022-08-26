package cha18;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 一个服务端，多个客户端
 */
public class TalkClient1 {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",8888);

        System.out.println("--------客户端启动--------");

        new ThreadClient(client).start();

        while(true){

            DataInputStream dataIn = new DataInputStream(client.getInputStream());
            String message = dataIn.readUTF();
            System.out.println("客户端-"+message);
        }


    }

    private static class ThreadClient extends Thread{

        Socket client;
        private static Scanner scanner = new Scanner(System.in);

        public ThreadClient(){}

        public ThreadClient(Socket client){
            this.client = client;
        }

        @Override
        public void run() {

            try {
                while(true){
                    DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());

                    dataOut.writeUTF(scanner.next());
                    dataOut.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
