public class App {
    public static void main(String[] args)throws Exception {

        
        Jogador player1 = new Jogador(1);
        Jogador player2 = new Jogador(2);

        JogadorService service1 = new JogadorService();
        JogadorService service2 = new JogadorService();

        Gerador gerador = new Gerador();
        

        //threads para gerar os chutes
        service1.start();
        service2.start();
        service1.join();
        service2.join();

        //variaveis auxiliares
        int count = 0;
        int lastWinner = 0;
        double lastNumber = 0;

        //laco principal
        while(player1.getPontos() < 5 && player2.getPontos() < 5){
            System.out.print("\n\n");
            
            //lógica para modificar o valor do gerador de acordo com o ultimo vencedor
            if(lastWinner == 1){
                do{
                    gerador.sortear();
                }while(gerador.getNumero() < lastNumber);
            }else if(lastWinner == 2){
                do{
                    gerador.sortear();
                }while(gerador.getNumero() > lastNumber);
            }else{
                gerador.sortear();
            }

            //logica para mostrar na tela os resultados e calcular o atual vencedor
            lastNumber = gerador.getNumero();
            System.out.println("----------RODADA " + (count+1) + "---------");
            System.out.println("ALVO : " + (int)gerador.getNumero());
            System.out.println("Chute jogador 1 : " + (int)service1.getChute(count));
            System.out.println("Chute jogador 2 : " + (int)service2.getChute(count) + "\n---------------------------");
            lastWinner = calcularVencedor(service1.getChute(count), service2.getChute(count), player1, player2, gerador.getNumero());
            System.out.println("\n-----------PLACAR----------\nJOGADOR 1: " + player1.getPontos() + " pontos\n---------------------------\nJOGADOR 2: " + player2.getPontos() + " pontos\n---------------------------");
            count++;
        }

        if(player1.getPontos() > player2.getPontos())System.out.println("\n\nGRANDE VENCEDOR COM " + player1.getPontos() + " PONTOS : JOGADOR 1!");
        else System.out.println("\n\nGRANDE VENCEDOR COM " + player2.getPontos() + " PONTOS : JOGADOR 2!");
        
    }

    //funcao que retorna 1 se o player 1 vencer, 2 se o player 2 vencer e 3 para um empate
    private static int calcularVencedor(double chute1, double chute2, Jogador player1, Jogador player2, double alvo) {
        double proximidade1 = alvo - chute1;
        if(proximidade1 < 0.0) proximidade1 = proximidade1 * -1;

        double proximidade2 = alvo - chute2;
        if(proximidade2 < 0.0) proximidade2 = proximidade2 * -1;

        if(proximidade1 < proximidade2){
            player1.pontuar();
            System.out.println("O jogador 1 pontuou!"+ "\n---------------------------");
            return 1;
        }
        else if(proximidade2 < proximidade1){
            player2.pontuar();
            System.out.println("O jogador 2 pontuou!"+ "\n---------------------------");
            return 2;
        }
        else{
            System.out.println("Empate! Nenhum jogador pontua!"+ "\n---------------------------");
            return 3;
        }        
    }
}
