import java.lang.Math;
import java.util.Random;

public class CPU{

	Personaje personaje;

	String[][] opciones = {
		{"Cercano","Lejano","Aleatorio"},
		{"Precavido", "Cauteloso", "Arriesgado"}
	};

	String[] caracter = new String[2];

	Baraja listaDetective = new Baraja();

	Mansion mansion = new Mansion();

    BotonCasilla botones = new BotonCasilla();

	Puerta habitacionObjetivo;

	int direccionObjetivoHorizontal;
	int direccionObjetivoVertical;

	CPU( Personaje p ){
		Random random = new Random();
		int aleatorio = random.nextInt(3);
		caracter[0] = opciones[0][0];
		aleatorio = random.nextInt(3);
		caracter[1] = opciones[1][0];
		this.personaje = p;

		this.habitacionObjetivo = calculaObjetivo();
	}// fin de constructor de la clase

	public int buscaPuerta(int indicePuerta){
		for( int i = 0 ; i < mansion.puertasHabitaciones.length ; i++ ){
			for( int j = 0 ; j < mansion.puertasHabitaciones[i].length ; j++){
				if(mansion.puertasHabitaciones[i][j] == indicePuerta){
					return i;
					//return mansion.habitaciones[i];
				}
			}			
		}
		return 0;
	}// fin de metodo buscaPuerta.

	public Puerta calculaObjetivo(){
		int indiceMenor = 0;
		if( this.caracter[0] == "Cercano" ){  // en cuanto calculo de distancias "Cercano","Lejano","Aleatorio"
			double max = 35;			

			for( int i = 0 ; i < mansion.puertas.length ; i++ ){
				if( this.listaDetective.cartas[this.buscaPuerta(i) + 12].cartaAsignada == false ){ // significa que no esta tachada de la lista y es posible buscar esta sala
					double x = mansion.puertas[i].getCoordenadaI() - this.personaje.getX();
					double y = mansion.puertas[i].getCoordenadaJ() - this.personaje.getY();
					double r =  Math.sqrt( Math.pow( x , 2.0 ) + Math.pow( y , 2.0 ) );
					if( r < max ){
						max = r;
						indiceMenor = i;
					}
				}
			}
		}
		return mansion.puertas[indiceMenor];
	}// calculaObjetivo


	public Casillas objetivoEsParteDelPerimetro( DibujaCirculo c ){

		for( int i = 0 ; i < c.getPerimetro() ; i++ ){
			boolean a = c.getCasilla(i).getX() == this.habitacionObjetivo.getX();
			boolean b = c.getCasilla(i).getY() == this.habitacionObjetivo.getY();
			if( a && b )
				return c.getCasilla(i);
		}
		return null; // sino encontro el objetivo en el perimetro del circulo, entonces retornara un null
				     // por lo tanto es conveniente usar esta funcion en un condicional if primero.

	}// fin de metodo objetivoEsParteDelPerimetro

	public Casillas buscaCasillaMasCercanaAObjetivo( /* Clase dibujaCirculo  */ DibujaCirculo c  ){
		int indiceMenor = 0;

		for( int i = 0 ; i < c.getPerimetro() ; i++ ){
			double max = 35;
			double x = c.getCasilla(i).getX()- this.habitacionObjetivo.getX();
			double y = c.getCasilla(i).getY() - this.habitacionObjetivo.getY();
			double r =  Math.sqrt( Math.pow( x , 2.0 ) + Math.pow( y , 2.0 ) );

			if( r < max ){
				max = r;
				indiceMenor = i;
			}
		}
		return c.getCasilla(indiceMenor);
	}// fin de metodo buscaCasillaMasCercanaAObjetivo

	public void avanzar(){

		Random random = new Random();
		int radio = random.nextInt(12);
		this.personaje.getX();
		this.personaje.getY();
		DibujaCirculo circulo = new DibujaCirculo(this.personaje.getX(), this.personaje.getY(), radio);

		// paso 1: preguntar si la habitacion objetivo esta dentro del perimetro del circulo.

		Casillas casillas =  this.objetivoEsParteDelPerimetro( circulo );
		if(  casillas != null ){
			System.out.println("La puerta esta en el perimetro del circulo");
			this.personaje.setX( casillas.getX() );
			this.personaje.setY( casillas.getY() );
			//this.haceAcusasion();
			return;
		}else System.out.println("La puerta NO esta en el perimetro del circulo");

		// paso 2: preguntar si esta dentro del perimetro del circulo.

		Vector v = new Vector( habitacionObjetivo.getX(), habitacionObjetivo.getY() );
		Bobinador bobinador = new Bobinador( circulo.getCasillas(), v );
		if( bobinador.estaDentro() == true ){
			// directamente nos movemos al objetivo
			this.personaje.setX( this.habitacionObjetivo.getX() );
			this.personaje.setY( this.habitacionObjetivo.getY() );
			//this.haceAcusasion();
			return;
		}

		// paso 3: descartar las casillas de la trayectoria que no sean visibles.

		

		// paso 4: buscar cual casilla del perimetro esta mas cerca al objetivo.


	}// fin de metodo avanzar

	public static void main(String[] args) {
		double r =  Math.sqrt( Math.pow( 3 , 2.0 ) + Math.pow( 4 , 2.0 ) );
		//System.out.println( r );
		Sospechosos sospechosos = new Sospechosos();

		CPU cpu = new CPU( sospechosos.lista[2] );
		cpu.personaje.setX( 8 );
		cpu.personaje.setY( 24 );
		DibujaCirculo circulo = new DibujaCirculo(cpu.personaje.getX(), cpu.personaje.getY(), 8);


		Casillas casillas =  cpu.objetivoEsParteDelPerimetro( circulo );
		if(  casillas != null ){
			System.out.println("La puerta esta en el perimetro del circulo");
		}else{
			System.out.println("La puerta NO esta en el perimetro del circulo");
		}


		cpu.calculaObjetivo();
		System.out.println("Posicion actual: ");
		System.out.println("X: " + cpu.personaje.getX() + ", Y: " + cpu.personaje.getY() );
		System.out.println("Objetivo: ");
		System.out.println("X: " + cpu.habitacionObjetivo.getCoordenadaI() + ", Y:" + cpu.habitacionObjetivo.getCoordenadaJ() );

		Casillas c = cpu.buscaCasillaMasCercanaAObjetivo( circulo );

		System.out.println( "X: "  + c.getX() + ", Y: " + c.getY() );
	}// fin de metodo main

}// fin de la clase CPU
