import java.util.Random;
public class Turno{

	String[] turnoDePersonaje = {"Prado", "Rubio", "Amapola", "Blanco", "Celeste", "Mora"};
	int turno = 1;
	String jugador;

	Turno( String quienEsElJugador ){
		// asi garantizamos que siempre se pongan los turnos desde el principio
		this.jugador = quienEsElJugador;
		this.ordenDeTurrnos();
		this.imprimeTurnos();
	}// fin de metodo constructor

	public void aumentaTurno(){

		this.turno++;

		if( turno > 6 ) turno = 1;

	}// fin del metodos aumentaTurno.

	public int getTurno(){
		return this.turno;
	}

    public void imprimeTurnos(){
        for(int i = 0 ; i < turnoDePersonaje.length ; i++)
            System.out.println(turnoDePersonaje[i]);
    }// fin de metodo imprimeTurnos

    public void ordenDeTurrnos(){
	    Random random = new Random();
	    int longitud = turnoDePersonaje.length;

	    for( int i = 0 ; i < longitud ; i++ ){
	        int aleatorio = random.nextInt(longitud);
	        String temp = turnoDePersonaje[i];
	        turnoDePersonaje[i] = turnoDePersonaje[aleatorio];
	        turnoDePersonaje[aleatorio] = temp;
	    }
	}// fin de metodo ordenDeTurnos

	public static void main(String[] args) {
		
		Turno turno = new Turno("Amapola");
		for( int i = 0 ; i < 25 ; i++ ){
			System.out.println("Es el turno: " + Integer.toString(  turno.getTurno() ) + " para " + turno.turnoDePersonaje[turno.getTurno()-1]);
			turno.aumentaTurno();
		}
		//turno.imprimeTurnos();

	}// fin de metodo main.
}// fin de la clase Turnos.