
import java.awt.geom.AffineTransform;
import java.lang.Math;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Timer;
import java.util.TimerTask;

public class Dados extends JFrame implements ChangeListener{

    JLabel etiqueta = new JLabel("Grados");
    JSpinner spinner = new JSpinner();

    int windowWidht = 800;
    int windowHeight = 600; 

    static int dado1 = 0;
    static int dado2 = 0;

    public int aleatorioSinRepetir(int rango, int valorDado){
        Random random = new Random();
        int aleatorio;
        aleatorio = random.nextInt(rango);
        int i = 0;
        while(aleatorio == valorDado ){ // mientras que aleatorio no sea mayor que min, entonces seguimos intentando
            aleatorio = random.nextInt(rango);
            i++;
            if( i > 20){
                if( valorDado == 0)
                return valorDado+1;
            }else{
                return valorDado-1;
            }
        }
        return aleatorio;
    }// fin de metodo

    Dados(){
        super("Prueba de imagenes");
        setLayout(null);

        Random random = new Random();
        do{
        Dados.dado1 = random.nextInt(6);
        }while( Dados.dado1 == 0 );
        do{
        Dados.dado2 = random.nextInt(6);
        }while( Dados.dado2 == 0 );

        int theta1 = random.nextInt(180);
        int theta2 = random.nextInt(180);
        PanelDados primerDado = new PanelDados(theta1,dado1);
        PanelDados segundoDado = new PanelDados(theta2,dado2);
        int eleX = 4;
        int eleY = 3;
        OrdenMatriz d = new OrdenMatriz( 200, 200, this.windowWidht, this.windowHeight, eleX, eleY);
        int X1 = random.nextInt(eleX - 1);
        int Y1 = random.nextInt(eleY - 1);
        int X2 = Math.abs(aleatorioSinRepetir( eleX-1,X1 ));
        int Y2 = Math.abs(aleatorioSinRepetir( eleY-1,Y1 ));
        primerDado.setOffset(d.getPosicionX(X1), d.getPosicionY(Y1));
        segundoDado.setOffset(d.getPosicionX(X2), d.getPosicionY(Y2));

        DimensionesPantalla miPantalla = new DimensionesPantalla();
        OrdenMatriz e = new OrdenMatriz( this.windowWidht, this.windowHeight, miPantalla.getWidth(), miPantalla.getHeight(), 1, 1);
         
        setUndecorated(true);
        setBackground( new Color(0,0,0,0) );

        etiqueta.setBounds(300,200,100,20);
        spinner.setBounds(300,250,100,20);

        add( primerDado );
        add( segundoDado );
        spinner.addChangeListener( this  );

        //add( etiqueta);
        //add(spinner);
        setResizable(false);
        setBounds(e.getPosicionX(), e.getPosicionY() ,this.windowWidht,this.windowHeight );     
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }// fin de constructor de clase

    public void stateChanged(ChangeEvent e){
        int valor = (int)spinner.getValue();

        if( valor < 0 ){
            spinner.setValue(0);
            return;
        }
        if( valor > 180){
            spinner.setValue(180);
            return;
        }
        
        //primerDado.theta = valor;
        repaint();
    }// fin de metodo stateChanged

    public static void main(String[] args){

        Dados ventana = new Dados();
        ventana.setVisible(false);
        Timer timer = new Timer();

        TimerTask timerTask1 = new TimerTask() {
            public void run(){
                ventana.setVisible(true);
            }
        };

        TimerTask timerTask2 = new TimerTask() {
            public void run(){
                ventana.dispose();
            }
        };

        System.out.print("El valor de los dados es: " + Dados.dado1 + " y " + Dados.dado2 );

        timer.schedule(timerTask1, 1500);// cerrar la ventana Dados
        timer.schedule(timerTask2, 3000);// cerrar la ventana Dados
    }// fin del metodo main

}// fin de la clase PruebaImagenes

class PanelDados extends JPanel{

    int theta = 0; // grados
    float M00 = cosine(theta);
    float M01 = -sine(theta);
    float M10 = sine(theta);
    float M11 = cosine(theta);
    float m02 = 135*M10;
    float m12 = 0;
    int ancho = Math.round(135*M11+m02);
    int alto = Math.round(191*sine(theta+45));
    int X,Y;

    Image imagen1;

    AffineTransform TX = new AffineTransform(M00, M10, M01, M11, m02, m12);

    float cosine(int alpha){
        return (float)Math.cos(Math.toRadians(alpha));
    }

    float sine(int alpha){
        return (float)Math.sin(Math.toRadians(alpha));
    }

    public void setOffset(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    public void calculaRotacion(){
        M00 = cosine(this.theta);
        M01 = -sine(this.theta);
        M10 = sine(this.theta);
        M11 = cosine(this.theta);

        if( this.theta <= 90 ){
        m02 = 135*M10;
        m12 = 0;
        this.ancho = Math.round(135*M11+m02);
        this.alto = Math.round(191*sine(this.theta+45));
        }
        if( this.theta > 90){
        m02 = 135*cosine(theta-90)+135*sine(theta-90);
        this.ancho = (int)m02;
        m12 = 135*sine(theta-90); 
        this.alto = Math.round(191*sine(this.theta+45-90));
        }

        this.TX = new AffineTransform(M00, M10, M01, M11, m02, m12);
    }

    public void cargarDado(int numero){

        String direccion = "Imagenes\\";
        String archivo;
        switch ( numero ) {
            case 1:archivo = "Dado1.jpg";break;
            case 2:archivo = "Dado2.jpg";break;
            case 3:archivo = "Dado3.jpg";break;
            case 4:archivo = "Dado4.jpg";break;
            case 5:archivo = "Dado5.jpg";break;
            case 6:archivo = "Dado6.jpg";break;
            default: archivo = "Dado6.jpg";break;
        }
        String direccionArchivo = direccion + archivo;
        this.imagen1 = new ImageIcon(direccionArchivo).getImage();

    }// fin de metood cargarDado

    public PanelDados(int theta, int dado){
        cargarDado( dado );
        this.theta = theta;
        calculaRotacion();
        setBounds(X,Y,500,500);
        setBackground(new Color( 0,0,0,0));
        setVisible(true);
    }

    @Override
    public void paintComponent( Graphics g ){
        super.paintComponent( g ); // llama al método paintComponent de la superclase
        Graphics2D g2d = ( Graphics2D ) g;
        calculaRotacion();
        setBounds(X,Y,this.ancho,alto);
        g2d.drawImage(imagen1,TX, null);        
    }

}// fin de la clase imagen
