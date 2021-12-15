import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        Jogador player1 = new Jogador(1);
        Jogador player2 = new Jogador(2);

        //vetores de servies
        JogadorService[] service1 = new JogadorService[9];
        JogadorService[] service2 = new JogadorService[9];

        //Gerador de "alvos"
        Gerador gerador = new Gerador();

        // variaveis auxiliares
        int count = 0;
        int lastWinner = 0;
        double lastNumber = 0;

        //laco principal
        for (int i = 0; i < 9 && player1.getPontos() < 5 && player2.getPontos() < 5; i++) {

            //instanciamento de services 
            service1[i] = new JogadorService("1", i);
            service2[i] = new JogadorService("2", i);

            //disparo de threads
            service1[i].start();
            service2[i].start();
            service1[i].join();
            service2[i].join();

            // lógica para modificar o valor do gerador de acordo com o ultimo vencedor
            if (lastWinner == 1) {
                do {
                    gerador.sortear();
                } while (gerador.getNumero() < lastNumber);
            } else if (lastWinner == 2) {
                do {
                    gerador.sortear();
                } while (gerador.getNumero() > lastNumber);
            } else {
                gerador.sortear();
            }
            

            //interface do user
            lastWinner = calcularVencedor(service1[i].getChute(),service2[i].getChute(), player1, player2,gerador.getNumero());
            System.out.println("----------RODADA " + (i + 1) + "---------");
            System.out.println("ALVO : " + (int) gerador.getNumero());
            System.out.println("Chute jogador 1 : " + (int) service1[i].getChute());
            System.out.println("Chute jogador 2 : " + (int) service2[i].getChute() + "\n---------------------------");
            System.out.println("\n-----------PLACAR----------\nJOGADOR 1: " + player1.getPontos()+ " pontos\n---------------------------\nJOGADOR 2: " + player2.getPontos()+ " pontos\n---------------------------");            
            System.out.println("\nEnter para continuar");
            String enter = scanner.nextLine();
        }

        //calcular vencedor
        if (player1.getPontos() > player2.getPontos())
            System.out.println("\nGRANDE VENCEDOR COM " + player1.getPontos() + " PONTOS : JOGADOR 1!");
        else
            System.out.println("\nGRANDE VENCEDOR COM " + player2.getPontos() + " PONTOS : JOGADOR 2!");
    }
    
    // funcao que retorna 1 se o player 1 vencer, 2 se o player 2 vencer e 3 para um
    // empate
    private static int calcularVencedor(double chute1, double chute2, Jogador player1, Jogador player2, double alvo) {
        double proximidade1 = alvo - chute1;
        if (proximidade1 < 0.0)
            proximidade1 = proximidade1 * -1;

        double proximidade2 = alvo - chute2;
        if (proximidade2 < 0.0)
            proximidade2 = proximidade2 * -1;

        if (proximidade1 < proximidade2) {
            player1.pontuar();
            System.out.println("\nO jogador 1 pontuou!" + "\n---------------------------\n");
            return 1;
        } else if (proximidade2 < proximidade1) {
            player2.pontuar();
            System.out.println("\nO jogador 2 pontuou!" + "\n---------------------------\n");
            return 2;
        } else {
            System.out.println("\nEmpate! Nenhum jogador pontua!" + "\n---------------------------\n2");
            return 3;
        }
    }
}
