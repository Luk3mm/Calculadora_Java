public class CalculadoraServico {
    private double numero1;
    private double numero2;
    private char simboloMatematico;

    public char getSimboloMatematico() {
        return simboloMatematico;
    }

    public void setSimboloMatematico(char simboloMatematico) {
        this.simboloMatematico = simboloMatematico;
    }

    public void setNumero1(double numero1) {
        this.numero1 = numero1;
    }

    public void setNumero2(double numero2) {
        this.numero2 = numero2;
    }

    public double adicionar(){
        return this.numero1 + this.numero2;
    }

    public double subtrair(){
        return this.numero1 - this.numero2;
    }

    public double multiplicar(){
        return this.numero1 * this.numero2;
    }

    public double dividir(){
        return this.numero1 / this.numero2;
    }
}
