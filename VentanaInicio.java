
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VentanaInicio extends JFrame implements ActionListener{

	JLabel titulo = new JLabel("CLUE");
	JButton salir = new JButton("Salir");
	JButton iniciar = new JButton("Iniciar");

	VentanaInicio(){

		DimensionesPantalla dimensionesPantalla = new DimensionesPantalla();
    	int anchoPantalla = dimensionesPantalla.getWidth();
    	int altoPantalla = dimensionesPantalla.getHeight();

    	final int anchoFrame = 640;
    	final int altoFrame  = 480;

    	final int anchoBoton = 150;
    	final int altoBoton = 25;

    	OrdenMatriz jframe = new OrdenMatriz(anchoFrame, altoFrame, anchoPantalla, altoPantalla, 1, 1);
		OrdenMatriz panel = new OrdenMatriz(anchoBoton, altoBoton, anchoFrame, altoFrame, 3, 2);

		setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setUndecorated(true);
        setResizable(false);
		setBounds(jframe.getPosicionX(),jframe.getPosicionY(),anchoFrame,altoFrame);
		salir.setBounds(panel.getPosicionX(2),panel.getPosicionY(1),anchoBoton,altoBoton);
		iniciar.setBounds(panel.getPosicionX(0),panel.getPosicionY(1),anchoBoton,altoBoton);
		titulo.setBounds(panel.getPosicionX(1),panel.getPosicionY(),anchoBoton,altoBoton);

		salir.addActionListener(this);
		iniciar.addActionListener(this);

		add( titulo);
		add(salir);
		add(iniciar);
		setVisible(true);


	}// fin de metodo constructor

	public void actionPerformed(ActionEvent e){
		Object objeto = e.getSource(); // aqui guardamos el objeto de entrada que activo el evento.
		
		if( objeto == iniciar){
			int[] cartas = {0,1,2,3,4,5};
			MisCartas misCartas = new MisCartas(cartas, false);
			dispose();
			return;
		}

		if( objeto == salir){
			this.dispose();
			return;
		}
	}//fin de metodo actionPerformed

	public static void main(String[] args) {
		VentanaInicio ventana = new VentanaInicio();
	}// fin de metodo main

}// fin de clase VentanaInicio