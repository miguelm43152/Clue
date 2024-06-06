
import java.util.Random;
public class ControlDeJuego {

    Sospechosos sospechosos = new Sospechosos();    
    Baraja baraja = new Baraja();
    private Carta[] sobreAmarillo = new Carta[3];
    int turnoActual = 0;

    public ControlDeJuego(){
        setSobreAmarillo();
        shuffleBaraja();
    }// fin de metodo constructor

    public void repartirCartas(Sospechosos s){
        
        int j = 0; int k = 0;
        for(int i = 0 ; i < 21 ; i++){
            if( baraja.cartas[i].cartaAsignada == false ){
                s.lista[k].misCartas[j] = baraja.cartas[i];
                baraja.cartas[i].cartaAsignada = true;
                j++;
                if( j > 2){
                    j = 0;
                    k++;
                }
            }// fin de if
        }// fin de ciclo for

    }// fin de metodo repartirCartas

    public void setSobreAmarillo() {
        // escogiendo una carta de Sospechoso
        Random random = new Random();
        int aleatorio = random.nextInt(6);
        sobreAmarillo[0] = baraja.cartas[aleatorio];
        baraja.cartas[aleatorio].cartaAsignada = true;

        // escogiendo carta de arma
        aleatorio = random.nextInt(6)+6;
        sobreAmarillo[1] = baraja.cartas[aleatorio];
        baraja.cartas[aleatorio].cartaAsignada = true;
        
        // escogiendo carta de habitacion
        aleatorio = random.nextInt(9)+12;
        sobreAmarillo[2] = baraja.cartas[aleatorio];
        baraja.cartas[aleatorio].cartaAsignada = true;

    }// fin de metodo setSobreAmarillo

    public Carta[] getSobreAmarillo(){  return sobreAmarillo;  }// fin de metodo getSobreAmarillo

    public void imprimeSobreAmarillo(){
        for( int i = 0 ; i < this.sobreAmarillo.length ; i++ ){
            //System.out.print("Carta #" + i + ": ");
            this.sobreAmarillo[i].imprimeCarta();
            System.out.println( );
        }
    }// fin de metodo getSobreAmarillo

    public void shuffleBaraja(){
        Random random = new Random();
        int longitud = baraja.cartas.length;

        for( int i = 0 ; i < longitud ; i++ ){
            int aleatorio = random.nextInt(longitud);
            Carta temp = baraja.cartas[i];
            baraja.cartas[i] = baraja.cartas[aleatorio];
            baraja.cartas[aleatorio] = temp;
        }
    }// fin de metodo shuffle

    public static void main(String[] args){
        ControlDeJuego controlDeJuego = new ControlDeJuego();
        controlDeJuego.imprimeSobreAmarillo();

        //controlDeJuego.baraja.imprimeBaraja();
        controlDeJuego.repartirCartas(controlDeJuego.sospechosos);
        System.out.println();
        System.out.println();
        
        for( int i = 0 ; i < 6 ; i++ ){
            System.out.println(controlDeJuego.sospechosos.lista[i].getNombre());
            for( int j = 0 ; j < 3 ; j++){
                controlDeJuego.sospechosos.lista[i].misCartas[j].imprimeCarta();
                System.out.println();
            }
        }
        
    }// fin de metodo main
    
}// fin de la clase ControlDeJuego