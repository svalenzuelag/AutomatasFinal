/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocupjlex;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author Enmanuel
 */
public class Nodo {
    
    Nodo izq;
    String valor;
    Nodo der;
    
    private static int correlativo=1;
    
    private final int id;    
    
    public int acum=0;


     public Nodo(String valor) {
        this.valor = valor;
        this.izq = null;
        this.der = null;
        this.id = correlativo++;
    }

    public void agregarDer(String valor){
        
        
        Nodo a=null;
        if(valor.length()==1){
            a = new Nodo(valor);
        }
        else{
            int plus, mius;
            
            plus=valor.lastIndexOf("+");
            mius=valor.lastIndexOf("-");
            
            if(plus>0 && plus>mius){
                a = new Nodo(valor.substring(plus,plus+1));
                a.agregarDer(valor.substring(plus+1));
                a.agregarIzq(valor.substring(0,plus));
            }
            else{
                if(mius>0){
                    a = new Nodo(valor.substring(mius,mius+1));
                    a.agregarDer(valor.substring(mius+1));
                    a.agregarIzq(valor.substring(0,mius));
                }
                else{
                    if(mius==(-1)  && plus==(-1)){
                        plus=valor.lastIndexOf("/");
                        mius=valor.lastIndexOf("*");

                        if(plus>0 && plus>mius){
                            a = new Nodo(valor.substring(plus,plus+1));
                            a.agregarDer(valor.substring(plus+1));
                            a.agregarIzq(valor.substring(0,plus));
                        }
                        else{
                            if(mius>0){
                                a = new Nodo(valor.substring(mius,mius+1));
                                a.agregarDer(valor.substring(mius+1));
                                a.agregarIzq(valor.substring(0,mius));
                            }
                            else{
                                if(mius==(-1)  && plus==(-1)){

                                    a = new Nodo(valor);
                                    

                                }
                            }
                        }
                    }
                }
            }
            
        }
        this.der = a;
        
    }
    
    public void agregarIzq(String valor){
        
        Nodo a=null;
        if(valor.length()==1){
            a = new Nodo(valor);
        }
        else{
            int plus, mius;
            
            plus=valor.lastIndexOf("+");
            mius=valor.lastIndexOf("-");
            
            if(plus>0 && plus>mius){
                a = new Nodo(valor.substring(plus,plus+1));
                a.agregarDer(valor.substring(plus+1));
                a.agregarIzq(valor.substring(0,plus));
            }
            else{
                if(mius>0){
                    a = new Nodo(valor.substring(mius,mius+1));
                    a.agregarDer(valor.substring(mius+1));
                    a.agregarIzq(valor.substring(0,mius));
                }
                else{
                    if(mius==(-1)  && plus==(-1)){
                        plus=valor.lastIndexOf("/");
                        mius=valor.lastIndexOf("*");

                        if(plus>0 && plus>mius){
                            a = new Nodo(valor.substring(plus,plus+1));
                            a.agregarDer(valor.substring(plus+1));
                            a.agregarIzq(valor.substring(0,plus));
                        }
                        else{
                            if(mius>0){
                                a = new Nodo(valor.substring(mius,mius+1));
                                a.agregarDer(valor.substring(mius+1));
                                a.agregarIzq(valor.substring(0,mius));
                            }
                            else{
                                if(mius==(-1)  && plus==(-1)){

                                    a = new Nodo(valor);

                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        this.izq = a;
        
    }
    
   
    public void recorrer(){
        
        FileWriter fichero = null;
        PrintWriter escritor;
        try
        {
            fichero = new FileWriter("C:\\Users\\josue\\Desktop\\Automatas\\arbol.txt");
            escritor = new PrintWriter(fichero);
            escritor.print("digraph g {\n" +
            "node [shape = record,height=.1];" +
            getCodigoInterno()+"\n"
                + "}");
        } 
        catch (Exception e){
            System.err.println("Error al escribir el archivo aux_grafico.dot");
        }finally{
           try {
                if (null != fichero)
                    fichero.close();
                
           }catch (Exception e2){
               System.err.println("Error al cerrar el archivo aux_grafico.dot");
           } 
        }
        try{
          Runtime rt = Runtime.getRuntime();
          //Este es el comando cmd que utiliza generar el grafico
          rt.exec( "dot -Tjpg C:\\Users\\josue\\Desktop\\Automatas\\arbol.txt -o C:\\Users\\josue\\Desktop\\Automatas\\arbol.png");
          //Esperamos medio segundo para dar tiempo a que la imagen se genere.
          //Para que no sucedan errores en caso de que se decidan graficar varios
          //árboles sucesivamente.
          Thread.sleep(800);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo aux_grafico.dot");
            System.out.println(ex);
        }       
        
    }
    
    
    private String getCodigoInterno() {
        String etiqueta;
        if(izq==null && der==null){
            etiqueta="nodo"+id+" [ label =\""+valor+"\"];\n";
        }else{
            etiqueta="nodo"+id+" [ label =\"<C0>|"+valor+"|<C1>\"];\n";
        }
        if(izq!=null){
            etiqueta=etiqueta + izq.getCodigoInterno() +
               "nodo"+id+":C0->nodo"+izq.id+"\n";
        }
        if(der!=null){
            etiqueta=etiqueta + der.getCodigoInterno() +
               "nodo"+id+":C1->nodo"+der.id+"\n";                    
        }
        return etiqueta;
    }  
    
    
}
