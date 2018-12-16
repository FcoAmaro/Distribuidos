import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class MyServerSocket extends Thread {
    private ServerSocket server;
    public MyServerSocket(String ipAddress, int port) throws Exception {
          System.out.println("Entro a crear server");

          this.server = new ServerSocket(port, 10, InetAddress.getByName(ipAddress));
          System.out.println("en server es "+InetAddress.getByName(ipAddress));
          System.out.println("creo server");

    }
    private void listen() throws Exception {
        while(true){
            String data = null;
            Socket client = this.server.accept();
            String clientAddress = client.getInetAddress().getHostAddress();
            System.out.println("\r\nNew connection from " + clientAddress);
            
            BufferedReader in = new BufferedReader(
            new InputStreamReader(client.getInputStream()));        
            data = in.readLine();
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
        }
        
    }
    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }
    
    public int getPort() {
        return this.server.getLocalPort();
    }
    public void run() {
        try {
            
            System.out.println("\r\nRunning Server: " + 
                    "Host=" + this.server.getInetAddress() + 
                    " Port=" + this.server.getLocalPort());

            listen();
        }
        catch (Exception e) {
                System.out.println("Entro a Exception server");
            }
        }
    }
