import java.lang.Math;
import java.util.ArrayList;

public class Bobinador {

    double vueltas;
    boolean estaDentro;
    
    Bobinador(ArrayList<Casillas> circulo, Vector p){
        
        int tamagno = circulo.size();
        double sumatoria = 0;

        for(int i = 0 ; i < tamagno-1 ; i++){
            
            Vector a = new Vector( circulo.get(i).coordenadaI - p.getV1() ,circulo.get(i).coordenadaJ - p.getV2() );
            Vector b = new Vector( circulo.get(i+1).coordenadaI - p.getV1() ,circulo.get(i+1).coordenadaJ - p.getV2() );

            sumatoria = sumatoria + Matematica.sign(Matematica.cruz(a, b)) * Matematica.anguloEntreVectores(a,b);

        }

        Vector a = new Vector( circulo.get( tamagno-1 ).coordenadaI - p.getV1() ,circulo.get(tamagno-1).coordenadaJ - p.getV2() );
        Vector b = new Vector( circulo.get(0).coordenadaI - p.getV1() ,circulo.get(0).coordenadaJ - p.getV2() );

        sumatoria = sumatoria + Matematica.sign(Matematica.cruz(a, b)) * Matematica.anguloEntreVectores(a,b);

        this.vueltas = Math.abs(sumatoria/360 + .1);

        if( vueltas >= 1 )
            estaDentro = true;
        else
            estaDentro = false;

    }// fin de constructor

    public void imprimeResultado(){
        System.out.println("Se ha dado: " + this.vueltas + " vueltas.");
    }// fin de metodo imprimeResultado

    public boolean estaDentro(){
        return estaDentro;
    }// fin del metodo estaDentro

    public static void main(String[] args){
        DibujaCirculo circulo = new DibujaCirculo(7, 14, 2);
        //Vector p1 = new Vector(5, 14);
        //Vector p2 = new Vector(12, 17);
        Vector p3 = new Vector(9, 16);

        Bobinador bobinador = new Bobinador(circulo.getCasillas(), p3);
        
        bobinador.imprimeResultado();
        System.out.println( bobinador.estaDentro() );        

    }// fin de metodo main

}// fin de clase Bobinador

class Vector{
    double v1,v2;

    Vector(double v1, double v2){
        this.v1 = v1; // entiendase como X
        this.v2 = v2; //entiendase como Y
    }// fin de constructor

    public double getV1() {
        return v1;
    }
    public double getV2() {
        return v2;
    }
}// fin de clase Vector

class Matematica{
    static double punto(Vector u, Vector v){
        return u.getV1()*v.getV1()+u.getV2()*v.getV2();
    }//fin de metodo punto
    static double norm(Vector u){
        return Math.sqrt(punto(u, u));
    }
    static double anguloEntreVectores(Vector u, Vector v){
        return  Math.toDegrees(  Math.acos( punto(u, v)/norm(u)/norm(v) ) );
    }// fin de metodo anguloEntreVectores
    static double cruz(Vector u, Vector v){
        return u.getV1()*v.getV2() - v.getV1()*u.getV2();
    }// fin de metodo cruz

    static double sign(double entrada){
        if( entrada > 0 )
            return 1;
        else if( entrada < 0)
            return -1;
        else 
            return 0;
    }// fin de metodo sign
}// fin de clase Matematica