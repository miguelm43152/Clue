import java.lang.Math;

class Carta {
    String nombre;
    String tipo;
    int numero;
    boolean cartaAsignada = false;
    Carta(String nombre, String tipo, int numero ){
        this.nombre = nombre;
        this.tipo =   tipo;
        this.numero = numero;
    }// fin de metodo constructor

    public String getNombre(){ return this.nombre; }
    public int getNumero(){ return this.numero; }

    public void imprimeCarta(){
        System.out.print( "Carta #" + this.getNumero() + ": " + this.nombre + ", tipo: " + this.tipo + ", asignada: " + this.cartaAsignada );
    }
}// fin de la clase Cartas

public class Baraja{
    
    final static String[][] nombre = {  
    {"Mora", "Celeste", "Prado", "Blanco", "Rubio", "Amapola"},
    //{"Mora", "Blanco", "Amapola", "Prado", "Rubio", "Celeste"}, 
    {"Cuerda", "Punal", "Herramienta", "Pistola", "Candelabro", "Tuberia De Plomo"}, 
    {"Patio", "Sala de Juegos", "Estudio", "Comedor", "Garaje", "Salon", "Cocina", "Dormitorio", "Bano"} };

    private String tipo;

    public Carta[] cartas = new Carta[21];

    Baraja(){
        for( int i = 0, k = 0 ; i < Baraja.nombre.length ; i++ )
            for( int j = 0; j < Baraja.nombre[i].length ; j++, k++){
                if( i == 0 ) tipo = "Sospechoso";
                else if( i == 1 ) tipo = "Arma";
                else if( i == 2) tipo = "Habitacion";
                
                //System.out.println(k);
                this.cartas[k] = new Carta(  nombre[i][j] , tipo, k );
            }
    }// fin de metodo constructor

    public void imprimeBaraja(){

        for( int i = 0 ; i < this.cartas.length ; i++ ){
            //System.out.print("Carta #" + cartas[i].getNumero() + ": ");
            this.cartas[i].imprimeCarta();
            System.out.println( );
        }

    }// fin de metodo imprimeBaraja

    public void shuffle(){
        Math.random();

    }// fin de metodo shuffle

    public static void main(String[] args){
        Baraja baraja = new Baraja();
        baraja.imprimeBaraja();
    }// fin de metodo main

}// fin de la clase Baraja
