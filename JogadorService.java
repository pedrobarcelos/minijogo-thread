import java.util.Scanner;
import java.io.*;

public class JogadorService extends Thread{

    // scanner
    Scanner scan = new Scanner(System.in);

    //variaveis usadas
    private String id = "";
    private int jogada;
    Gerador gerador = new Gerador();
    Console cnsl
            = System.console();
    
    private double chute;

    //construtor
    public JogadorService(String id, int jogada){
        this.id = id;
        this.jogada = jogada;
    }

    //thread
    @Override
    public void run() {
        String chute = cnsl.readLine("Chute " + (this.jogada + 1) + " do jogador "+ this.id + ": " );
        setChute(Integer.parseInt(chute));
    }
    
    public double getChute() {
        return chute;
    }

    public void setChute(double chute) {
        this.chute = chute;
    }
}