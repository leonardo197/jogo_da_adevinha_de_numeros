import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class jogo_cliente {
    public static void main(String[] args) throws IOException {
        String hostName = "127.0.0.1";//args[0];//127.0.0.1
        int portNumber = 10005;
        String memoria = "n";


        System.out.print("Server ip: ");
        //hostName=teclado.next().toString();
        System.out.println();
        System.out.print("Server porta: ");
        //portNumber=teclado.nextInt();
        System.out.println();

        try (
                Socket Socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(Socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(Socket.getInputStream()));) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            do {
                fromServer = in.readLine();
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("by")) {
                    break;
                }
                fromUser = stdIn.readLine();
                System.out.println("Client: " + fromUser);
                out.println(fromUser);

            } while (true);


        } catch (UnknownHostException e) {
            System.err.println("Não sabe sobre o host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Não foi possível obter a forma de I/O da conexão para " + hostName);
            System.exit(1);
        }


    }
}
