
class LimitesHabitaciones{

    int izq = 0;
    int der = 0;
    int sup = 0;
    int inf = 0;

    LimitesHabitaciones(int izq, int der, int sup, int inf){
        this.izq = izq;
        this.der = der;
        this.sup = sup;
        this.inf = inf;
    }

    boolean estaDentro(int i, int j){
        return (i >= izq && i <= der) && (j >= sup && j <= inf);
    }
    boolean estaFuera(int i, int j){
        return !estaDentro(i, j);
    }
}// fin de la clase LimitesHabitaciones

class Casillas{
    int coordenadaI;
    int coordenadaJ;
    Casillas(int i, int j){
        this.coordenadaI = i;
        this.coordenadaJ = j;
    }
    public int getCoordenadaI() {
        return coordenadaI;
    }
    public int getCoordenadaJ() {
        return coordenadaJ;
    }

    public int getX() {
        return coordenadaI;
    }
    public int getY() {
        return coordenadaJ;
    }
}

class CasillaInicioJugador extends Casillas{
    CasillaInicioJugador(int i, int j){
        super(i, j);
    }
}// fin de la clase CasillasInicioJugador

class CasillaInvisible extends Casillas{
    CasillaInvisible(int i, int j){
        super(i, j);
    }
}// fin de clase CasillaInvisible

class CasillaVisible extends Casillas{
    CasillaVisible(int i, int j){
        super(i, j);
    }
}// fin de clase CasillaVisible

class Puerta extends Casillas{
    Puerta(int i, int j){
        super(i, j);
    }
}// fin de clase Puerta

public class Mansion{

    LimitesHabitaciones bagno = new LimitesHabitaciones(0,6,0,3);
    LimitesHabitaciones garaje = new LimitesHabitaciones(9,14,0,6);
    LimitesHabitaciones estudio = new LimitesHabitaciones(17,23,0,5);
    LimitesHabitaciones salaDeJuegos = new LimitesHabitaciones(0,6,6,10);
    LimitesHabitaciones comedor = new LimitesHabitaciones(16,23,9,15);
    LimitesHabitaciones patio= new LimitesHabitaciones(0,5,12,16);
    LimitesHabitaciones dormitorio= new LimitesHabitaciones(0,5,19,24);
    LimitesHabitaciones salon= new LimitesHabitaciones(8,15,17,24);
    LimitesHabitaciones cocina= new LimitesHabitaciones(18,23,18,24);
    LimitesHabitaciones central= new LimitesHabitaciones(9,13,8,14);

    CasillaInicioJugador mora = new CasillaInicioJugador(0, 5);
    CasillaInicioJugador celeste = new CasillaInicioJugador(0, 18);
    CasillaInicioJugador pardo = new CasillaInicioJugador(9, 24);
    CasillaInicioJugador amapola = new CasillaInicioJugador(16, 0);
    CasillaInicioJugador blanco = new CasillaInicioJugador(14, 24);
    CasillaInicioJugador rubio = new CasillaInicioJugador(23, 7);

    CasillaInvisible[] casillasInvisibles = new CasillaInvisible[14];
    CasillaVisible[] casillasVisibles = new CasillaVisible[12];
    Puerta[] puertas = new Puerta[19];

    String[] habitaciones = {"Patio", "Sala de Juegos", "Estudio", "Comedor", "Garaje", "Salon", "Cocina", "Dormitorio", "Bano"};
    
    int[][] puertasHabitaciones = {
        {3,4}, // Patio
        {1,2}, // Sala de juegos
        {14,15}, // Estudio
        {12,13}, // Comedor
        {16,17,18}, // Garaje
        {7,8,9,10}, // Salon
        {11}, // Cocina
        {5,6}, // Dormitorio
        {0} // Bano
    };

    
    Mansion(){

        casillasInvisibles[0] = new CasillaInvisible(0,4);
        casillasInvisibles[1] = new CasillaInvisible(0,11);
        casillasInvisibles[2] = new CasillaInvisible(0,17);
        casillasInvisibles[3] = new CasillaInvisible(6,24);
        casillasInvisibles[4] = new CasillaInvisible(7,24);
        casillasInvisibles[5] = new CasillaInvisible(6,23);
        casillasInvisibles[6] = new CasillaInvisible(16,24);
        casillasInvisibles[7] = new CasillaInvisible(17,24);
        casillasInvisibles[8] = new CasillaInvisible(17,23);
        casillasInvisibles[9] = new CasillaInvisible(23,16);
        casillasInvisibles[10] = new CasillaInvisible(23,8);
        casillasInvisibles[11] = new CasillaInvisible(23,6);
        casillasInvisibles[12] = new CasillaInvisible(15,0);
        casillasInvisibles[13] = new CasillaInvisible(8,0);

        casillasVisibles[0] = new CasillaVisible(8,23);
        casillasVisibles[1] = new CasillaVisible(9,23);
        casillasVisibles[2] = new CasillaVisible(9,24);
        casillasVisibles[3] = new CasillaVisible(14,24);
        casillasVisibles[4] = new CasillaVisible(14,23);
        casillasVisibles[5] = new CasillaVisible(15,23);
        casillasVisibles[6] = new CasillaVisible(6,6);
        casillasVisibles[7] = new CasillaVisible(6,10);
        casillasVisibles[8] = new CasillaVisible(16,15);
        casillasVisibles[9] = new CasillaVisible(17,15);
        casillasVisibles[10] = new CasillaVisible(18,15);
        casillasVisibles[11] = new CasillaVisible(5,19);

        puertas[0] =  new Puerta(6,3);   // bano
        puertas[1] =  new Puerta(6,8);   // sala de juegos frente
        puertas[2] =  new Puerta(3,10);  // sala de juegos lateral
        puertas[3] =  new Puerta(1,12);  // Patio lateral
        puertas[4] =  new Puerta(5,15);  // Patio frente
        puertas[5] =  new Puerta(4,19);  // Dormitorio (puerta doble)
        puertas[6] =  new Puerta(4,20);  // Dormitorio (puerta doble)
        puertas[7] =  new Puerta(8,19);  // Salon (puerta lateral)
        puertas[8] =  new Puerta(9,17);  // Salon puerta frente
        puertas[9] =  new Puerta(14,17); // Salon puerta de frente segunda
        puertas[10] = new Puerta(15,19); // Salon puerta lateral
        puertas[11] = new Puerta(19,18); // puerta de la cocina
        puertas[12] = new Puerta(16,12); // Puerta del comedor de frente
        puertas[13] = new Puerta(17,9);  // Puerta del comedor de lado
        puertas[14] = new Puerta(17,5);  // Puerta de estudio (puerta doble)
        puertas[15] = new Puerta(18,5);  // Puerta de estudio (puerta doble)
        puertas[16] = new Puerta(12,6);  // Puerta de garaje doble frente
        puertas[17] = new Puerta(11,6);  // Puerta de garaje doble frente
        puertas[18] = new Puerta(9,4);   // Puerta de garaje lateral

    }// fin de constructor de clase Mansion

    //casillaInvisible[0] = new CasillaInvisible();

    //casillasInicioJugador()

}//fin de clase Mansion