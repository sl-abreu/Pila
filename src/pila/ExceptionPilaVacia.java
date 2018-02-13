/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

/**
 *
 * @author robot
 */
public class ExceptionPilaVacia extends RuntimeException {

    public ExceptionPilaVacia() {
        super("Pila vacía.");
    }
    public ExceptionPilaVacia(String mensaje){
        super(mensaje);
    }
}
