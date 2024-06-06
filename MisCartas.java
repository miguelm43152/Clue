import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MisCartas extends JFrame implements ActionListener{

	static String jugador;
	Baraja baraja = new Baraja();

    JButton salir = new JButton();
    JButton[] botonesSeleccion = new JButton[6];

    final String direccion = "Imagenes\\Cartas\\";
    final String extension = ".JPG";

	final int imageWidth  = 140;
    final int imageHeight = 300;

	MisCartas(int[] numeroDeCartas, boolean esSobreAmarillo){
		DimensionesPantalla dimensionesPantalla = new DimensionesPantalla();
    	int anchoPantalla = dimensionesPantalla.getWidth();
    	int altoPantalla = dimensionesPantalla.getHeight();

		final int anchoFrame = 1000;
    	final int altoFrame  = 500;
    	setUndecorated(true);
		int size = numeroDeCartas.length;
		Icon[] imagenCartas = new Icon[size];
		JLabel[] imagenes = new JLabel[size];
		String s;
		String t;
		if( size > 3 && esSobreAmarillo == false ){
			t = "Seleccion de personaje";
			s = "Salir";
		}else if( esSobreAmarillo == false){
			t = "Mis cartas";
			s = "Salir";
		}else{
			t = "Sobre Amarillo";
			s = "Fin";
		}

		salir.setText(s);
		JLabel titulo = new JLabel(t);

    	OrdenMatriz jframe = new OrdenMatriz(anchoFrame, altoFrame, anchoPantalla, altoPantalla, 1, 1);
		OrdenMatriz imageIconMatriz = new OrdenMatriz(imageWidth, imageHeight, anchoFrame, altoFrame, size, 1);

		for( int i = 0 ; i < size ; i++){
			imagenCartas[i] = new ImageIcon( direccion + baraja.cartas[ numeroDeCartas[i] ].nombre + extension);
			imagenes[i] = new JLabel( imagenCartas[i]);
			botonesSeleccion[i] = new JButton("Elígeme!");
			botonesSeleccion[i].setBounds(imageIconMatriz.getPosicionX(i),imageIconMatriz.getPosicionY()+230,140,50);
			imagenes[i].setBounds(imageIconMatriz.getPosicionX(i),imageIconMatriz.getPosicionY(),imageWidth,imageHeight);
			add(imagenes[i]);
			if( size > 3)
			add(botonesSeleccion[i]);
			botonesSeleccion[i].addActionListener(this);
		}

		// agregando el boton salir al Frame
		salir.setBounds( 832,55,140,25 );
		salir.addActionListener(this);
		if( size <= 3 )
		add(salir);

		// agregando la etiqueta de titulo al Frame
		titulo.setBounds( 100,30,300,50 );
		add(titulo);

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        
        setResizable(false);
		setVisible(true);

		setBounds(jframe.getPosicionX(),jframe.getPosicionY(),anchoFrame,altoFrame);

	}// fin de constructor de clase

	public void actionPerformed(ActionEvent e){
		Object objetoEvento = e.getSource();
		if( objetoEvento == salir ){
			if( salir.getText() == "Salir" ){
				setVisible(false);
				return;
			}
			if( salir.getText() == "Fin" ){
			System.out.println("Es el fin");
			setVisible(false);
			return;
			}
		}else{
			for(int i = 0 ; i < botonesSeleccion.length ; i++){
				//System.out.println(e.getSource());
				ManejoString obj = new ManejoString( objetoEvento.toString() );
				int indicePersonaje = (obj.getValor1() - 22)/162;
				MisCartas.jugador = baraja.cartas[indicePersonaje].nombre;
				//System.out.println( "Usted ha escogido a " + MisCartas.jugador );
				Tablero tablero = new Tablero();
				this.dispose();
				return;
			}
		}
	}// fin de metodo actionPerformed

	public static void main(String[] args) {
		int[] cartas = {0,1,2,3,4,5};
		MisCartas misCartas = new MisCartas(cartas, false);
	}// fin de metodo main
}// fin de la clase MisCartas.