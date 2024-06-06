
public class ManejoString {

    String informacion;
    int valor1;
    int valor2;
    int ultimaComa;

    ManejoString(String info){
        this.informacion = info;
        buscarValor1();
        buscarValor2( );
    }//fin de constructor

    public int getValor1() {
        return valor1;
    }
    public int getValor2(){
        return valor2;
    }// fin

    public void buscarValor1( ){
        int index1 = informacion.indexOf(',',0)+1;
        int index2 = informacion.indexOf(',',index1);
        ultimaComa = index2;
        this.valor1 = Integer.parseInt(informacion.substring(index1, index2));
        
    }//fin de metodo buscarInt

    public void buscarValor2( ){
        int index1 = informacion.indexOf(',',ultimaComa)+1;
        int index2 = informacion.indexOf(',',index1);
        this.valor2 = Integer.parseInt(informacion.substring(index1, index2));
    }//fin de metodo buscarInt

    public static void main(String[] args){

        String informacion = "ButtonDesign1[,279,384,20x20,";
        ManejoString obj = new ManejoString(informacion);
        System.out.println(obj.getValor1());
        System.out.println(obj.getValor2());

    }// fin de metodo main
    
}// fin de la clase ManejoString