


public class Gerador {

    private double num = Math.floor(Math.random() * (100 - 0 + 1)) + 0;

    public Gerador() {
    }

    public double getNumero() {
        return num;
    }

    public void sortear() {
        this.num = Math.floor(Math.random() * (100 - 0 + 1)) + 0;
    }

}
