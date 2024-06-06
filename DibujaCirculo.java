
import java.util.ArrayList;

/****************************************************/
// dibuja una circunferencia en sentido horario
/****************************************************/
// usar las coordenadas con un radio de 5
//7,14
public class DibujaCirculo  {
    ArrayList<Casillas> casillas = new ArrayList<Casillas>();

    DibujaCirculo(int iCentro, int jCentro, int radio){
        int x; // i
        int y; // j
        
        for( y = jCentro - radio, x = iCentro; y < jCentro ; x++, y++ )
            if( estaDentroDeLosLimitesDeMansion(x, y))
                casillas.add( new Casillas(x, y));
        for( ; y < (jCentro+radio) ; x--, y++ )
            if( estaDentroDeLosLimitesDeMansion(x, y))
                casillas.add( new Casillas(x, y));
        for( ; x > (iCentro-radio) ; x--, y-- )
            if( estaDentroDeLosLimitesDeMansion(x, y))
                casillas.add( new Casillas(x, y));
        for( ; y > (jCentro-radio) ; x++, y-- )
            if( estaDentroDeLosLimitesDeMansion(x, y))
                casillas.add( new Casillas(x, y));
        
    }// fin de metodo constructor

    public ArrayList<Casillas> getTrayecto(){
        return casillas;
    }// fin de metodo getTrayecto

    public boolean estaDentroDeLosLimitesDeMansion(int x, int y){
        boolean cumpleCondicion = x >= 0 && x <= 23 && y >= 0 && y <= 24;
        if( cumpleCondicion )
            return true;
        else
            return false;
    }// fin del metodo estaDentroDeLosLimitesDeMansion

    void imprimeCoordenadas(){
        for( int i = 0 ; i < casillas.size() ; i++)
            System.out.println(casillas.get(i).getX() + "," + casillas.get(i).getY());
    }

    public int getPerimetro(){
        return this.casillas.size();
    }

    public Casillas getCasilla(int indice){
        return this.casillas.get(indice);
    }

    public Casillas removeCasilla(int indice){
        return this.casillas.remove(indice);
    }

    public static void main(String[] args){

        DibujaCirculo circulo = new DibujaCirculo(7, 14, 5);

        for( int i = circulo.getPerimetro() - 1 ; i > 0 ; i-- ){
            //if( i == 3 || i == 5 || i == 8 || i == 2 || i == 0 )
                circulo.removeCasilla(i);
        }

        //circulo.removeCasilla();
        circulo.imprimeCoordenadas();

        System.out.println( "Perimetor del circulo: " + circulo.getPerimetro() );
        System.out.println( circulo.getCasilla(0).getX() );
        

    }// fin de metodo main

    public ArrayList<Casillas> getCasillas() {
        return casillas;
    }// fin de getter
}// Fin de clase DibujaCirculo
