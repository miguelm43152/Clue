import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

public class PanelTablero extends DisegnoPaneles implements ActionListener{

    // aqui vamos a repartir las cartas en el sobre amarillo.
    ControlDeJuego controlDeJuego = new ControlDeJuego();
        // ventanas emergentes por botones
        MisCartas misCartas;
        LibretaDetective libretaDetective;
        VentanaAcusacion ventanaAcusacion;
        Turno turno;

    int indiceJugador = 0;
    int[] indiceCartasJugador = {0,0,0};

    JLabel etiquetaImagen = new JLabel(imagen1);
//    final int ELEMENTOS_X = 24;
//    final int ELEMENTOS_Y = 25;
//    ButtonDesign1[][] btn1 = new ButtonDesign1[ELEMENTOS_X][ELEMENTOS_Y];

    // aqui vamos a repartir cada una de las cartas a los sospechosos.
    Sospechosos sospechosos = new Sospechosos();
    String jugador = MisCartas.jugador;
    BotonCasilla botones = new BotonCasilla();

    String[] textoOpciones = {"Avanzar","Acusacion","Libreta","Mis cartas"};
    JButton[] opciones = new JButton[textoOpciones.length];

    PanelTablero( ){

        setLayout(null);
    
        final int buttonWidth = 20;
        final int buttonHeight = 20;

        final int OFFSETX = 110;
        final int OFFSETY = 47;

        final int ELEMENTOS_X = 24;
        final int ELEMENTOS_Y = 25;
        
        OrdenMatriz d = new OrdenMatriz(imagen1.image1Width, imagen1.imageHeight, paneWidth, paneHeight, 1, 1);
        OrdenMatriz e = new OrdenMatriz(buttonWidth, buttonHeight, imagen1.image1Width, imagen1.imageHeight, ELEMENTOS_X, ELEMENTOS_Y);
        OrdenMatriz eOpc = new OrdenMatriz(100, 50, paneWidth, 100, textoOpciones.length, 1);
        Mansion mansion = new Mansion();
        
        controlDeJuego.repartirCartas(sospechosos);

        // botones para accion del jugador
        for(int i = 0 ; i < opciones.length ; i++){
            opciones[i] = new JButton( textoOpciones[i] );
            opciones[i].setBounds(eOpc.getPosicionX(i), d.getPosicionY() + imagen1.imageHeight + eOpc.getPosicionY(),100,25);
            opciones[i].addActionListener(this);
            add(opciones[i]);
        }

        // casillas del tablero
        for( int i = 0 ; i < ELEMENTOS_X ; i++){
            for( int j = 0 ; j < ELEMENTOS_Y ; j++){
                //botones.btn1[i][j] = new ButtonDesign1( );
                //setBounds(e.getPosicionX(), e.getPosicionY() ,this.windowWidht,this.windowHeight );  
                if( botones.btn1[i][j].visible == false ) botones.btn1[i][j].setVisible(false);
                botones.btn1[i][j].setBounds(e.getPosicionX(i) + OFFSETX ,e.getPosicionY(j) + OFFSETY,buttonWidth,buttonHeight );
                add( botones.btn1[i][j] );
            }
        }
        
        // escondemos las casillas que no sirvan
       /* boolean ocultarCasillas = true;
        if(ocultarCasillas == true){
            for( int i = 0 ; i < ELEMENTOS_X ; i++){
                for( int j = 0 ; j < ELEMENTOS_Y ; j++){
                    boolean dibujaSi = mansion.bagno.estaFuera(i, j) && mansion.salon.estaFuera(i, j) && mansion.garaje.estaFuera(i, j) && mansion.estudio.estaFuera(i, j) && mansion.salaDeJuegos.estaFuera(i, j) && mansion.comedor.estaFuera(i, j) && mansion.patio.estaFuera(i, j) && mansion.dormitorio.estaFuera(i, j) && mansion.cocina.estaFuera(i, j) && mansion.central.estaFuera(i, j);
                    //dibujaSi = true;
                    if( dibujaSi ){
                        btn1[i][j].setVisible(true);
                    //setBounds(e.getPosicionX(), e.getPosicionY() ,this.windowWidht,this.windowHeight );      
                    }else btn1[i][j].setVisible(false);
                }
            }

            for( int i = 0 ; i < mansion.casillasVisibles.length ; i++){
                btn1[mansion.casillasVisibles[i].getCoordenadaI()][ mansion.casillasVisibles[i].getCoordenadaJ()].setVisible(true);
                btn1[mansion.casillasVisibles[i].getCoordenadaI()][ mansion.casillasVisibles[i].getCoordenadaJ()].addActionListener(this);
            }

            for( int k = 0 ; k < mansion.puertas.length ; k++){
                int i = mansion.puertas[k].getCoordenadaI();
                int j = mansion.puertas[k].getCoordenadaJ();
                btn1[i][j].setVisible(true); // true para mostrar puertas y false para ocultarlas
                btn1[i][j].addActionListener(this);
            }

            for( int i = 0 ; i < mansion.casillasInvisibles.length ; i++){
                btn1[mansion.casillasInvisibles[i].getCoordenadaI()][ mansion.casillasInvisibles[i].getCoordenadaJ()].setVisible(false);            
            }
        }*/

        for( int i = 0 ; i < ELEMENTOS_X ; i++)
                for( int j = 0 ; j < ELEMENTOS_Y ; j++)
                    botones.btn1[i][j].addActionListener(this);
        etiquetaImagen.setBounds( d.getPosicionX(),d.getPosicionY(),paneWidth,paneHeight );

        for( int i = 0 ; i < sospechosos.lista.length ; i++){
            imprimirPersonaje(sospechosos.lista[i]);
        }
        habilitarCasillasTablero(true);
        habilitarAcusacion(true);
        habilitarMovimiento(true);
        add(etiquetaImagen);

    }// fin de metodo constructor

    public void habilitarCasillasTablero( boolean habilitar){
        // true para habilitar, false para deshabilitar
        for( int i = 0 ; i < botones.btn1.length ; i++)
            for( int j = 0 ; j < botones.btn1[i].length ; j++)
                if( botones.btn1[i][j].jugador == false )
                    botones.btn1[i][j].setEnabled(habilitar);
    }// fin de metodo habilitarCasillasTablero

    public void habilitarMovimiento( boolean bandera){
        opciones[0].setEnabled(bandera);    // opciones[0] = "Avanzar"
    }// fin de metodo habilitarMovimiento

    public void habilitarAcusacion(boolean bandera){
        opciones[1].setEnabled(bandera);    // opciones[1] = "Acusacion"
    }// fin de metodo habilitarAcusacion

    //dibujando un circulo
    public void dibujandoUnCirculo(int X, int Y, int radio, boolean dibujar){// variable dibujar es true para dibujar o false para borrarlo.

        DibujaCirculo circulo = new DibujaCirculo(X,Y,radio);
        for( int k = 0 ; k < circulo.casillas.size() ; k++){
            int i = circulo.casillas.get(k).coordenadaI;
            int j = circulo.casillas.get(k).coordenadaJ;
            if( dibujar == true ){
                botones.btn1[i][j].disegnoDelBoton2();
            }else{
                botones.btn1[i][j].disegnoDelBoton();
            }
        }

    }// fin de metodo dibujandoUnCirculo

    public <T extends Personaje> void imprimirPersonaje( Personaje p ){
        botones.btn1[p.getX()][p.getY()].setIcon(p.getIcon());
        botones.btn1[p.getX()][p.getY()].jugador = true;
    }// fin de metodo imprimirPersonaje

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Activando evento");
        Object objeto = e.getSource();
        if( objeto == opciones[0] ){// avanzar

            Dados dados = new Dados();
            dados.setVisible(false);
            Timer timer = new Timer();

            TimerTask timerTask1 = new TimerTask() {
                public void run(){
                    dados.setVisible(true);
                }
            };
            TimerTask timerTask2 = new TimerTask() {
                public void run(){
                    dados.dispose();
                    habilitarCasillasTablero(true);
                    int radio = Dados.dado1 + Dados.dado2;

                    int coordenadaJ = 5;
                    int coordenadaI = 0;
                    dibujandoUnCirculo(coordenadaI,coordenadaJ,radio,true);
                    botones.btn1[coordenadaI][coordenadaJ].centroCirculo = true;
                }
            };
            
            timer.schedule(timerTask1, 1500);
            timer.schedule(timerTask2, 3000);
            return;
        }else if( objeto == opciones[1] ){// acusacion
            System.out.println("Acusacion");
            if( ventanaAcusacion == null)
                ventanaAcusacion = new VentanaAcusacion();
            else
                ventanaAcusacion.setVisible(true);
            return;

        }else if( objeto == opciones[2]){// libreta
            if( libretaDetective == null)
                libretaDetective = new LibretaDetective();
            else
                libretaDetective.setVisible(true);
            return;
        }else if( objeto == opciones[3]){// mis cartas
            if( misCartas == null){
            System.out.println("paso1");
            // buscamos el indice de sospechoso que le pertenece al jugador
            for(  ; indiceJugador < 6 ; indiceJugador++ ){
                if( sospechosos.lista[indiceJugador].getNombre() == this.jugador )
                    break;
            }

            System.out.println("paso2: indice: " + Integer.toString(  indiceJugador ) );
            for( int i = 0 ; i < 3 ; i++){
                indiceCartasJugador[i] = sospechosos.lista[indiceJugador].misCartas[i].getNumero();
            }
                misCartas = new MisCartas( indiceCartasJugador, false );
            }else
                misCartas.setVisible(true);
            return;
        }else{

        //System.out.println("SDsdsdfasdf");
        String informacion = e.getSource().toString();
        ManejoString obj = new ManejoString(informacion);
        int coordenadaI = (obj.getValor1()-111)/21;
        int coordenadaJ = (obj.getValor2()-48)/21;
        System.out.println( coordenadaI + "," + coordenadaJ );

        }

  /*      
        if( btn1[coordenadaI][coordenadaJ].centroCirculo == false){
            dibujandoUnCirculo(coordenadaI,coordenadaJ,5,true);
            btn1[coordenadaI][coordenadaJ].centroCirculo = true;
        }else{
            dibujandoUnCirculo(coordenadaI,coordenadaJ,5,false);
            btn1[coordenadaI][coordenadaJ].centroCirculo = false;
        }
*/
    }// fin de metodo actionPerformed
    
}// fin de clase PanelTablero