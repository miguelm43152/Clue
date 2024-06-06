
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LibretaDetective extends JFrame implements ActionListener {

    DimensionesPantalla dimensionesPantalla = new DimensionesPantalla();
    int anchoPantalla = dimensionesPantalla.getWidth();
    int altoPantalla = dimensionesPantalla.getHeight();
    JButton botonSalir = new JButton();
    static boolean[][] estadoBotones = new boolean[7][23];
    JButton[][] botonesSeleccion = new JButton[7][23];

    String carpeta = "Imagenes\\";

    ImageIcon imageIcon = new ImageIcon(carpeta + "libretaDetective.jpg");
    Icon marcaIcon = new ImageIcon( carpeta + "marca.jpg" );
    JLabel imagen = new JLabel(imageIcon);

    int imageWidth  = imageIcon.getIconWidth();
    int imageHeight = imageIcon.getIconHeight();

    int anchoFrame = imageWidth + 2*50;
    int altoFrame  = imageHeight + 2*50;

    OrdenMatriz jframe = new OrdenMatriz(anchoFrame, altoFrame, anchoPantalla, altoPantalla, 1, 1);
    OrdenMatriz imageIconMatriz = new OrdenMatriz(imageWidth, imageHeight, anchoFrame, altoFrame, 1, 1);

    public void disegnoBoton( JButton boton){
        boton.setBorderPainted(true);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(true);
    }//fin de metodo disegnoBoton()

    public void disegnoBotonInvisible( JButton boton){
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(true);
    }//fin de metodo disegnoBoton()

    public void initBotonSeleccion(){

        int anchoSeleccion = 279-196;
        int altoSeleccion  = 140-114;
        OrdenMatriz matriz = new OrdenMatriz(anchoSeleccion, altoSeleccion , 777-196, 712-114, 7, 23);
        
        for( int i = 0 ; i < botonesSeleccion.length ; i++)
            for( int j = 0 ; j < botonesSeleccion[i].length ; j++){
                if( j != 6 && j != 13){
                    botonesSeleccion[i][j] = new JButton();
                    disegnoBotonInvisible(botonesSeleccion[i][j]);
                    botonesSeleccion[i][j].setBounds(196 + matriz.getPosicionX(i), 65 + imageIconMatriz.getPosicionY() + matriz.getPosicionY(j),anchoSeleccion,altoSeleccion);
                    botonesSeleccion[i][j].addActionListener(this);
                    imagen.add(botonesSeleccion[i][j]);
                }
            }

    }// fin de metodo initBotonSeleccion

    LibretaDetective(){
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.disegnoBoton(botonSalir);
        botonSalir.setBounds( 783,14,104,35 );
        imagen.setBounds(imageIconMatriz.getPosicionX(),imageIconMatriz.getPosicionY(),imageWidth,imageHeight);
        setBounds(jframe.getPosicionX(),jframe.getPosicionY(),anchoFrame,altoFrame);

        setUndecorated(true);

        botonSalir.addActionListener(this);
        initBotonSeleccion();
        imagen.add(botonSalir);
        add( imagen );
        setResizable(false);
        setVisible(true);
    }// fin de constructor de clase

    @Override
    public void actionPerformed(ActionEvent e) {
        Object objeto = e.getSource();

        if( objeto == botonSalir){
            this.setVisible(false);
            return;
        }
        
        String informacion = objeto.toString();
        ManejoString obj = new ManejoString(informacion);
        int coordenadaI = (obj.getValor1()-196)/83;
        int coordenadaJ = (obj.getValor2()-115)/26;
        //System.out.println( coordenadaI + "," + coordenadaJ );
        
        if( botonesSeleccion[coordenadaI][coordenadaJ].getIcon() == null ){
            botonesSeleccion[coordenadaI][coordenadaJ].setIcon(marcaIcon);
            LibretaDetective.estadoBotones[coordenadaI][coordenadaJ] = true;
        }else{
            botonesSeleccion[coordenadaI][coordenadaJ].setIcon(null);
            LibretaDetective.estadoBotones[coordenadaI][coordenadaJ] = false;
        }

    }// fin de metodo actionPerformed

    public static void main(String[] args){
        new LibretaDetective();
    }// fin de metodo main
}// fin de la clase LibretaDetective