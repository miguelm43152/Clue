
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Tablero extends MarcoTablero {

    public Tablero(){
        add( miPanelTablero );
        miPanelTablero.setVisible(true);
        setVisible(true);
    }// fin de metodo constructor de la clase Tablero

    public static void main( String[] args){

        Tablero tablero = new Tablero();

    }// fin de metodo main

}// fin de clase Tablero

class MarcoTablero extends JFrame{   // dimensiones y posicion de JFrame

    public PanelTablero miPanelTablero = new PanelTablero();
    public final int windowWidht = miPanelTablero.getPaneWidth() + 2*50;
    public final int windowHeight = miPanelTablero.getPaneHeight() + 2*100;

    MarcoTablero(){
        DimensionesPantalla miPantalla = new DimensionesPantalla();
        OrdenMatriz e = new OrdenMatriz( this.windowWidht, this.windowHeight, miPantalla.getWidth(), miPantalla.getHeight(), 1, 1);

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setBounds(e.getPosicionX(), e.getPosicionY() ,this.windowWidht,this.windowHeight );      
        setResizable( false);
        setTitle("Clue");

    }// fin de metodo constructor


} // fin de clase MarcoTablero

class ImagenTablero extends ImageIcon{

    ImagenTablero(){
        super("Imagenes\\tablero2.jpg");
    }
        
    final int image1Width = this.getIconWidth();
    final int imageHeight = this.getIconHeight();

}// fin de clase

class DisegnoPaneles extends JPanel{

    ImagenTablero imagen1 = new ImagenTablero();
    final int paneWidth = imagen1.image1Width + 2 *50;
    final int paneHeight = imagen1.imageHeight + 2*20;

    public int getPaneWidth(){
        return this.paneWidth;
    }
    public int getPaneHeight(){
        return this.paneHeight;
    }

}// fin de clase DisegnoPaneles