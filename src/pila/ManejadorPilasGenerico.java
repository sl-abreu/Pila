/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

import utiles.ListaArregloDesordenada;

/**
 *
 * @author SLGA
 */
public class ManejadorPilasGenerico {
    
    /**
     * 
     * @param <T>   Tipo de elementos contenidos en p.
     * @param p Pila a analizar.
     * @return Número de elementos contenidos en p.
     */
    public static <T> int cuentaElementos(PilaADT<T> p){
        int cont=0;
        PilaA<T> aux=new PilaA();
        
        while(!p.isEmpty()){
            aux.push(p.pop());
            cont++;
        }
        while(!aux.isEmpty())
            p.push(aux.pop());
        return cont;
    }
    /**
     * 
     * @param <T>   Tipo de elementos contenidos en las pilas.
     * @param p1    Pila de referencia.
     * @param p2    Pila a analizar contención.
     * @return <ul>
     *  <li> True: si: <ol>
     *      <li> Ninguna de las pilas es null,
     *      <li> p1 contiene los elemntos de p2, tomando en cuenta el orden;
     *      </ol>
     *  <li> False: si: <ul>
     *      <li> Alguna de las pilas es null,
     *      <li> La primera pila está vacía y la segunda no,
     *      <li> La primera pila no contiene todos los elementos de la segunda en orden;
     *      </ul>
     *  </ul>
     */
    public static <T> boolean contiene(PilaADT<T> p1,PilaADT<T> p2){
        PilaA<T> aux1=new PilaA(),aux2=new PilaA();
        boolean res=false;
        
        if(p1!=null && p2!=null){
            if(p2.isEmpty())
                res=true;
            else{
                while(!p1.isEmpty() && !p1.peek().equals(p2.peek()))
                    aux1.push(p1.pop());
                while(!p1.isEmpty() && !p2.isEmpty()){
                    if(p1.peek().equals(p2.peek())){
                        aux2.push(p2.pop());
                    }
                    aux1.push(p1.pop());
                }
                if(p2.isEmpty())
                    res=true;
                while(!aux1.isEmpty())
                    p1.push(aux1.pop());
                while(!aux2.isEmpty())
                    p2.push(aux2.pop());
            }
        }
        return res;
    }
    /**
     * 
     * @param <T>   Tipo de elementos contenidos en p.
     * @param p     Pila a invertir.
     */
    public static <T> void invierte(PilaADT<T> p){
        ListaArregloDesordenada<T> aux=new ListaArregloDesordenada();
        
        while(!p.isEmpty())
            aux.addLast(p.pop());
        while(!aux.isEmpty())
            p.push(aux.remove(0));
    }
    /**
     * 
     * @param <T>   Tipo de elemntos contenidos en p.
     * @param p     Pila a modificar.
     */
    public static <T> void eliminaRepetidosConsecutivos(PilaADT<T> p){
        PilaA<T> aux=new PilaA();
        T temp;
        
        if(!p.isEmpty()){
            aux.push(p.pop());
            while(!p.isEmpty()){
                temp=p.pop();
                if(!temp.equals(aux.peek()))
                    aux.push(temp);
            }
            while(!aux.isEmpty())
                p.push(aux.pop());
        }
    }
    /**
     * 
     * @param <T> Tipo de elementos de la pila.
     * @param p Pila a convertir.
     * @return Una cadena de tipo String con la información de los elementos de la pila.
     */
    public static <T> String imprimePila(PilaADT<T> p){
        PilaA<T> aux=new PilaA();
        StringBuilder cad=new StringBuilder();
        
        while(!p.isEmpty()){
            aux.push(p.pop());
            cad.append("|\t"+aux.peek().toString()+"\t|\n");
        }
        cad.append("|\t__\t|");
        while(!aux.isEmpty())
            p.push(aux.pop());
        return cad.toString();
    }
    
    public static void main(String[] args) {
        PilaA<Integer> pila=new PilaA(),pila2=new PilaA();
        pila.push(4);
        pila.push(7);
        pila.push(7);
        pila.push(3);
        pila.push(2);
        pila.push(2);
        pila.push(3);
        pila.push(3);
        System.out.println(imprimePila(pila));
        invierte(pila);
        eliminaRepetidosConsecutivos(pila);
        System.out.println(imprimePila(pila));
    }
}
