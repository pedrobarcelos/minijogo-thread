

public class Jogador {
    private int id;
    private int pontos;

    public Jogador(int id) {
        this.id = id;
        this.pontos = 0;
    }

    public void imprimir(String msg){
        System.out.println(this.id + " : " + msg);
    }

    public void pontuar(){
        this.pontos += 1;
    }
    public int getPontos() {
        return this.pontos;
    }
    
}
