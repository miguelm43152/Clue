
//import java.awt.Color;
//import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

public class Botones extends JFrame implements ActionListener{

    ButtonDesign1 btn1 = new ButtonDesign1("Disegno");
    boolean b = false;
    boolean visible = true;

    Botones(){
        
        setLayout(null);
        setBounds(200,200,500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btn1.setBounds(200,150,200,100);
        btn1.addActionListener(this);
        add( btn1 );
        setVisible(true);
    }
    public static void main(String[] args){

        new Botones();

    }

    public void actionPerformed(ActionEvent e){

        if( b == false){
        btn1.disegnoDelBoton2();
            b = !b;
        }else{
            btn1.disegnoDelBoton();
            b = !b;
        }

    }// fin de metodo actionPerfomed

}// fin de la clase botones

class BotonCasilla{

    final int ELEMENTOS_X = 24;
    final int ELEMENTOS_Y = 25;
    ButtonDesign1[][] btn1 = new ButtonDesign1[ELEMENTOS_X][ELEMENTOS_Y];
    Mansion mansion = new Mansion();

    BotonCasilla(){

        // casillas del tablero
        for( int i = 0 ; i < ELEMENTOS_X ; i++){
            for( int j = 0 ; j < ELEMENTOS_Y ; j++){
                this.btn1[i][j] = new ButtonDesign1( );
                //setBounds(e.getPosicionX(), e.getPosicionY() ,this.windowWidht,this.windowHeight );      
                //botones.btn1[i][j].setBounds(e.getPosicionX(i) + OFFSETX ,e.getPosicionY(j) + OFFSETY,buttonWidth,buttonHeight );
                //add( botones.btn1[i][j] );
            }
        }

      // escondemos las casillas que no sirvan
        boolean ocultarCasillas = true;
        if(ocultarCasillas == true){
            for( int i = 0 ; i < ELEMENTOS_X ; i++){
                for( int j = 0 ; j < ELEMENTOS_Y ; j++){
                    boolean dibujaSi = mansion.bagno.estaFuera(i, j) && mansion.salon.estaFuera(i, j) && mansion.garaje.estaFuera(i, j) && mansion.estudio.estaFuera(i, j) && mansion.salaDeJuegos.estaFuera(i, j) && mansion.comedor.estaFuera(i, j) && mansion.patio.estaFuera(i, j) && mansion.dormitorio.estaFuera(i, j) && mansion.cocina.estaFuera(i, j) && mansion.central.estaFuera(i, j);
                    //dibujaSi = true;
                    if( dibujaSi ){
                        btn1[i][j].visible = true;
                    //setBounds(e.getPosicionX(), e.getPosicionY() ,this.windowWidht,this.windowHeight );      
                    }else btn1[i][j].visible = false;
                }
            }

            for( int i = 0 ; i < mansion.casillasVisibles.length ; i++){
                btn1[mansion.casillasVisibles[i].getCoordenadaI()][ mansion.casillasVisibles[i].getCoordenadaJ()].setVisible(true);
                //btn1[mansion.casillasVisibles[i].getCoordenadaI()][ mansion.casillasVisibles[i].getCoordenadaJ()].addActionListener(this);
            }

            for( int k = 0 ; k < mansion.puertas.length ; k++){
                int i = mansion.puertas[k].getCoordenadaI();
                int j = mansion.puertas[k].getCoordenadaJ();
                btn1[i][j].visible = true; // true para mostrar puertas y false para ocultarlas
                //btn1[i][j].addActionListener(this);
            }

            for( int i = 0 ; i < mansion.casillasInvisibles.length ; i++){
                btn1[mansion.casillasInvisibles[i].getCoordenadaI()][ mansion.casillasInvisibles[i].getCoordenadaJ()].setVisible(false);            
            }
        }

    }// fin de metodo constructor
 
}// fin de clase BotonCasilla

class ButtonDesign1  extends JButton{

    boolean puerta = false;
    boolean centroCirculo = false;
    boolean jugador = false;
    boolean visible = true; // por defecto el boton es visible
    //String habitacion;

    ButtonDesign1(){
        disegnoDelBoton();
    }// fin del metodo constructor

    ButtonDesign1(ImageIcon imagen){
        super(imagen);
        disegnoDelBoton();
    }//fin de metodo constructor

    ButtonDesign1(String texto){
        super(texto);
        disegnoDelBoton();
    }//fin de metodo constructor

    public void disegnoDelBoton(){

        //Font fontStyle = new Font("chiller",Font.BOLD,5);
        //this.setFont(fontStyle);

        //Color fontclr = new Color(255,0,0);
        //this.setForeground(fontclr);

        //Color backclr = new Color(0,255,0);
        //this.setBackground(backclr);

        this.setBorderPainted(true);
        this.setContentAreaFilled(false);

        this.setFocusPainted(false);
        //this.setBounds(200,200,200,100);

    }// fin de metodo didsegnoDelBoton

    public void disegnoDelBoton2(){

        this.setBorderPainted(false);
        this.setContentAreaFilled(true);
        this.setFocusPainted(true);

    }
}// fin de clase ButtonDesign1