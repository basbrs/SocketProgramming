import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client{
  public void start(String ip, int port){
    try{
      Socket socket = new Socket(ip, port);
      System.out.println("found a server and connected!");

      ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
      outputStream.writeObject(new IAmObject("IamString", 16));

      new Thread(() -> {
        try{
          while(true){
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            IAmObject obj = (IAmObject) inputStream.readObject();

            System.out.println("client: " + obj);
          }
        }catch(ClassNotFoundException | IOException e){
          e.printStackTrace();
        }
      }).start();
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}