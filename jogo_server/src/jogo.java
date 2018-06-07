
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Random;

public class jogo implements Runnable {
    public Socket cliente;
    public float v1 = 0, v2 = 0, t = 0;

    public jogo(Socket cliente) {
        this.cliente = cliente;
    }

    public void run() {
        System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());

        try (PrintWriter out = new PrintWriter(this.cliente.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));) {

            String inputLine, outputLine;
            outputLine = "ola";
            System.out.println("server: " + outputLine);
            out.println(outputLine);
            inputLine = in.readLine();
            Random gerador = new Random();
            System.out.println("Client: " + cliente.getRemoteSocketAddress() + " Diz " + inputLine);
            do {
                try {

                    outputLine = "Diz me um numero entre 1 e 12";
                    System.out.println("server: " + outputLine);
                    out.println(outputLine);
                    inputLine = in.readLine();
                    System.out.println("Client: " + cliente.getRemoteSocketAddress() + " Diz " + inputLine);
                    int i=(gerador.nextInt(12)+1);
                    if (inputLine.equals(i+"")){
                        outputLine = "Boa. Queres jogar mais? Se nao (bye)";
                        System.out.println("server: " + outputLine);
                        out.println(outputLine);
                    }else{
                        outputLine = "Erraste era "+i+". Queres jogar mais? Se nao (bye)";
                        System.out.println("server: " + outputLine);
                        out.println(outputLine);
                    }

                        if (inputLine == "bye") {
                            out.println("bye");
                            break;
                        }

                } catch (NumberFormatException e) {
                    System.out.println("Numero com formato errado!");
                }

            } while (true);


            this.cliente.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        System.out.println("Fim do Serviso!");


    }
}
