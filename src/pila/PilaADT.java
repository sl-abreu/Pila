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
public interface PilaADT<T> {
    public void push(T dato);
    public T pop();
    public T peek();
    public boolean isEmpty();
    public boolean equals(PilaADT<T> otra);
}
