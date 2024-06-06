
import java.awt.Color;
//import java.awt.Font;
import javax.swing.*;
import java.util.Random;

public class TirarDados {

    public static void main(String[] args){

        Random rand = new Random();
        System.out.println( rand.nextInt( 6 ) );

    }// fin de main

}// fin de la clase TirarDados

class Dado{
    
}

/*
class Dados extends JFrame{

    ButtonDesign2 btn2 = new ButtonDesign2("adsf");

    Dados(){
        
        setLayout(null);
        setBounds(200,200,500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //btn1.setBounds(200,200,200,100);
        add( btn2 );
        setVisible(true);
        setTitle("dados");
    }

}// fin de la clase botones

*/
class ButtonDesign2  extends JButton{

    ButtonDesign2(){
        disegnoDelBoton();
    }// fin del metodo constructor

    ButtonDesign2(String texto){
        super(texto);
        disegnoDelBoton();
    }//fin de metodo constructor

    public void disegnoDelBoton(){

        //Font fontStyle = new Font("chiller",Font.BOLD,5);
        //this.setFont(fontStyle);

        //Color fontclr = new Color(255,0,0);
        //this.setForeground(fontclr);

        Color backclr = new Color(255,255,255);
        this.setBackground(backclr);

        this.setBorderPainted(true);
        this.setContentAreaFilled(true);

        this.setFocusPainted(false);
        this.setBounds(200,00,150,150);
        this.setEnabled(false);

    }
}// fin de clase ButtonDesign1

