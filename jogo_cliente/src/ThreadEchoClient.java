/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.*;


public class ThreadEchoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = "";
        int portNumber = 10001;

        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = //é o que envia para o servidor.. 
                    new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( //é o que recebe do servidor...
                    new InputStreamReader(kkSocket.getInputStream()));){

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromClient;

            do {
                fromClient = stdIn.readLine();
                out.println(fromClient);
                System.out.println("Server " + kkSocket.getRemoteSocketAddress() + " replied sentence is "
                        + in.readLine() + " characters long.");
            } while (!fromClient.equals("Bye"));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
