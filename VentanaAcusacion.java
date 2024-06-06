import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAcusacion extends JFrame implements ActionListener{

	JLabel[] etiquetas = new JLabel[3];
	JComboBox[] cajitas = new JComboBox[3];
	JCheckBox definitiva = new JCheckBox("definitiva");
	JButton salir = new JButton("Salir");
	JButton confirmar = new JButton("Confirmar");

	VentanaAcusacion(){

		String[] etiquetasTxt = { "Sospechoso", "Arma","Habitacion"};

		DimensionesPantalla dimensionesPantalla = new DimensionesPantalla();
    	int anchoPantalla = dimensionesPantalla.getWidth();
    	int altoPantalla = dimensionesPantalla.getHeight();

    	final int anchoFrame = 640;
    	final int altoFrame  = 480;

    	final int anchoBoton = 125;
    	final int altoBoton = 25;

    	OrdenMatriz jframe = new OrdenMatriz(anchoFrame, altoFrame, anchoPantalla, altoPantalla, 1, 1);
    	OrdenMatriz panel = new OrdenMatriz(anchoBoton, altoBoton, anchoFrame, altoFrame, 3, 4);

		for( int i = 0 ; i < cajitas.length ; i++){
			cajitas[i] = new JComboBox( );
			cajitas[i].setBounds(panel.getPosicionX(i),panel.getPosicionY(1),anchoBoton,altoBoton);
			cajitas[i].addItem(etiquetasTxt[i]);
			for( int j = 0 ; j < Baraja.nombre[i].length ; j++ )
				cajitas[i].addItem( Baraja.nombre[i][j] );
			add( cajitas[i]);
		}

		definitiva.setBounds(panel.getPosicionX(0),panel.getPosicionY(2),anchoBoton,altoBoton);
		add(definitiva);
		
		confirmar.setBounds(panel.getPosicionX(2),panel.getPosicionY(3),anchoBoton,altoBoton);
		confirmar.addActionListener(this);
		add(confirmar);

		salir.setBounds(panel.getPosicionX(2),panel.getPosicionY(0),anchoBoton,altoBoton);
		salir.addActionListener(this);
		add(salir);

		setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setUndecorated(true);
        setResizable(false);
		setBounds(jframe.getPosicionX(),jframe.getPosicionY(),anchoFrame,altoFrame);

		setVisible(true);


	}// fin de metodo constructor

	public void actionPerformed(ActionEvent e){

		Object obj = e.getSource();

		if( obj == salir ){
			setVisible(false);
			return;
		}

	}// fin de metodo actionPerformed

	public static void main(String[] args) {
		VentanaAcusacion ventanaAcusacion = new VentanaAcusacion();
	}// fin de metodo main
}// fin de clase VentanaAcusacion