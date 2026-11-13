import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public void run() throws IOException {
    // Server logic here
    int port = 8010; // Example port
    ServerSocket socket = new ServerSocket(port);
    socket.setSoTimeout(10000); // Set timeout to 10 seconds
    while (true) {
      try {
        System.out.println("Server is listening on port " + port);
        Socket acceptedConnection = socket.accept();
        System.out.println("Connection accepted from client" + acceptedConnection.getRemoteSocketAddress());
        PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
        toClient.println("Hello from server");
        toClient.close();
        fromClient.close();
        acceptedConnection.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    Server server = new Server();
    try {
      server.run();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}