
/***** Esta clase crea un objeto para leer las dimensiones de pantalla de cada computador **************/

import java.awt.Dimension;
import java.awt.Toolkit;

interface InnerDimensionesPantalla {        // Prototipo de la clase

    public int getWidth();  // devuelve el ancho de la pantalla
    public int getHeight(); // devuelve el alto de la pantalla
    public void imprimeTamagno();   // imprime el mensaje "El tamaño de la pantalla es: WidthxHeight"
    
}

public class DimensionesPantalla implements InnerDimensionesPantalla {

    int ancho, alto;

    public DimensionesPantalla(){

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension tamagno = tk.getScreenSize();
    
        this.ancho = (int)tamagno.getWidth();
        this.alto = (int)tamagno.getHeight();

    }// fin de metodo constructor

    public int getWidth(){
        return this.ancho;
    }

    public int getHeight(){
        return this.alto;
    }

    public void imprimeTamagno(){
        System.out.println(  "El tamaño de la pantalla es: " + this.getWidth() + "x" + this.getHeight() );
    }

    public static void main(String args[]){
        DimensionesPantalla miPantalla = new DimensionesPantalla();
        miPantalla.imprimeTamagno();
    }

}// fin de la clase DimensionesPantalla
