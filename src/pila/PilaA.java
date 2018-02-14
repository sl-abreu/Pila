/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

/**
 *
 * @author SLGA
 * @param <T>
 */
public class PilaA<T> implements PilaADT<T> {
    private T[] pila;
    private int tope;
    private final int MAX=20;
    
    public PilaA(){
        pila=(T[]) new Object[MAX];
        tope=-1;    //indica pila vacía, pues el tope indica la última casilla usada
    }
    public PilaA(int max){
        pila=(T[]) new Object[max];
        tope=-1;    //indica pila vacía, pues el tope indica la última casilla usada
    }

    @Override
    public boolean isEmpty(){
        return tope==-1;
    }
    @Override
    public void push(T dato){
        if(tope==pila.length-1)
            expandCapacity();
        tope++;
        pila[tope]=dato;
    }
    @Override
    public T pop(){
        if(tope!=-1){
            T res=pila[tope];
            pila[tope]=null;
            tope--;
            return res;
        }
        throw new ExceptionPilaVacia("Pila vacía");
    }
    @Override
    public T peek(){
        if(tope!=-1)
            return pila[tope];
        throw new ExceptionPilaVacia();
    }
    
    /*  versiones anteriores
    @Override
    public T pop(){
        T res=null;
        
        if(tope!=-1){
            res=pila[tope];
            pila[tope]=null;
            tope--;
        }
        return res;
    }
    @Override
    public T peek(){
        T res=null;
        
        if(tope!=-1)
            res=pila[tope];
        return res;
    }
    */
    
    private void expandCapacity(){
        T[] nuevo=(T[]) new Object[pila.length*2];
        
        for(int i=0;i<=tope;i++)
            nuevo[i]=pila[i];
        pila=nuevo;
    }
}
