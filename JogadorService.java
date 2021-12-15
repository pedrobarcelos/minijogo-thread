

public class JogadorService extends Thread{

    Gerador gerador = new Gerador();
    
    private double[] chute = new double[9];

    public JogadorService(){

    }

    @Override
    public void run() {
        for(int i = 0; i < 9; i++){
             gerador.sortear();
             setChute(gerador.getNumero(),i);
        }
       
        
    }

    public double getChute(int index) {
        return chute[index];
    }

    public void setChute(double chute, int index) {
        this.chute[index] = chute;
    }
}