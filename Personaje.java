
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Personaje{
    private Icon iconoPersonaje;
    static public String Direccion = "Imagenes\\";
    static public String extension = ".png";
    private String nombre;
    private int coordenadaX;
    private int coordenadaY;
    boolean personajeJugable = false;
    Carta[] misCartas = new Carta[3];

    // constructor
    Personaje( String nombre ){
        this.nombre = nombre;
        setIcon(Personaje.Direccion + nombre + Personaje.extension);
    }

    // Setters
    public void setIcon(String filename ){ iconoPersonaje = new ImageIcon(filename); }
    public void setX(int X){ this.coordenadaX = X; }
    public void setY(int Y){ this.coordenadaY = Y; }

    // getters
    public String getNombre( ){ return nombre; }
    public Icon getIcon( ){ return iconoPersonaje; }
    public int getX(){ return coordenadaX; }
    public int getY(){ return coordenadaY; }

}// fin de clase Personaje

class Sospechosos{

    Personaje[] lista = new Personaje[6];

    Sospechosos(){
        int[][] ubicacion = { {0,5}, {0,18}, {9,24}, {14,24}, {23,7}, {16,0} };
        String nombre[] = {"Mora", "Celeste", "Prado", "Blanco", "Rubio", "Amapola"};
        for( int i = 0 ; i < lista.length ; i++ ){
            lista[i] = new Personaje(nombre[i]);
            lista[i].setX(ubicacion[i][0]);
            lista[i].setY(ubicacion[i][1]);
        }
    }// fin de metodo constructor

}// fin de la clase Sospechosos


/*
class Mora extends Personaje {
    Mora(){
        super("Mora");
        setX(0);setY(5);
    }// fin de constructor    
}// fin de la clase Mora

class Celeste extends Personaje {
    Celeste(){
        super("Celeste");
        setX(0);setY(18);
    }// fin de constructor    
}// fin de la clase Celeste

class Prado extends Personaje {
    Prado(){
        super("Prado");
        setX(9);setY(24);
    }// fin de constructor    
}// fin de la clase Prado

class Blanco extends Personaje {
    Blanco(){
        super("Blanco");
        setX(14);setY(24);
    }// fin de constructor    
}// fin de la clase Blanco

class Rubio extends Personaje {
    Rubio(){
        super("Rubio");
        setX(23);setY(7);
    }// fin de constructor    
}// fin de la clase Rubio

class Amapola extends Personaje {
    Amapola(){
        super("Amapola");
        setX(16);setY(0);
    }// fin de constructor    
}// fin de la clase Amapola

*/