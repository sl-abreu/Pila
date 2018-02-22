/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocalculadora;

import pila.PilaA;


/**
 *
 * @author robot
 */
public class CalculadoraControlador {
    /**
     * 
     * @param op Caracter a analizar.
     * @return <ul>
     *  <li> 1: si op es '+' o '-'
     *  <li> 2: si op es '*' o '/'
     *  <li> 3: si op es '^'
     *  <li> -1: si op es cualquier otro caracter.
     *  </ul>
     */
    public static int prioridad(char op){
        int prio=-1;
        
        switch (op) {
            case '+':
            case '-':
                prio=1;
                break;
            case '*':
            case '/':
                prio=2;
                break;
            case '^':
                prio=3;
                break;
            default:
                break;
        }
        return prio;
    }
    /**
     * 
     * @param op    Caracter a analizar.
     * @return <ul>
     *  <li> True: si op es alguno de los siguientes caracteres: <ul>
     *      <li> +
     *      <li> -
     *      <li> *
     *      <li> /
     *      <li> ^
     *      </ul>
     *  <li> False: si no se cumple ninguno de los anteriores.
     *  </ul>
     */
    public static boolean esOperador(char op){
        return op=='+' || op=='-' || op=='*' || op=='/' || op=='^';
    }
    /**
     * 
     * @param exp   Expresión matemática a evaluar
     * @return <ul>
     *  <li> False: si la expresión contiene uno de los siguientes errores: <ul>
     *      <li> Operador seguido de operador (con excepción de un signo negativo)
     *      <li> Punto decimal no contenido entre dos dígitos.
     *      <li> Paréntesis desbalanceados.
     *      <li> Paréntesis vacíos.
     *      <li> Operador no seguido por un dígito o un signo negativo.
     *      <li> Falta de operador entre expresiones contenidas en paréntesis.
     *      <li> La expresión contiene letras.
     *      <li> La expresión contiene únicamente espacios.
     *      </ul>
     *  <li> True: si no presenta ninguno de los errores anteriores.
     *  </ul>
     */
    public static boolean revisaExpresion(String exp){
        PilaA<Character> ver=new PilaA();
        char aux;
        boolean res=false,banOp=false,banPun=false;
        int i=0;
        
        while(i<exp.length()-1 && exp.charAt(i)==' ')
            i++;
        aux=exp.charAt(i);
        if(Character.isDigit(aux) || aux=='(' || aux=='-'){
            ver.push(aux);
            res=true;
            i++;
            while(i<exp.length() && res){
                aux=exp.charAt(i);
                if(aux!=' '){
                    if(Character.isDigit(aux) || aux=='('){
                        if(ver.peek()!=')'){
                            if(ver.peek()!='(')
                                ver.pop();
                            ver.push(aux);
                        }
                        else
                            res=false;
                    }
                    else if(esOperador(aux)){
                        if(Character.isDigit(ver.peek()) || ver.peek()==')' || (aux=='-' && !banOp && ver.peek()!='.')){
                            banPun=false;
                            if(aux=='-' && ver.peek()=='-')
                                banOp=true;
                            else
                                banOp=false;
                            if(ver.peek()!='(')
                                ver.pop();
                            ver.push(aux);
                        }
                        else
                            res=false;
                    }
                    else if(aux=='.'){
                        if(Character.isDigit(ver.peek()) && !banPun){
                            banPun=true;
                            ver.pop();
                            ver.push(aux);
                        }
                        else
                            res=false;
                    }
                    else if(aux==')'){
                        if(!ver.isEmpty() && Character.isDigit(ver.pop()) && !ver.isEmpty() && ver.peek()=='('){
                            banPun=false;
                            ver.pop();
                            ver.push(')');
                        }
                        else res=false;
                    }
                    else if(Character.isLetter(aux))
                        res=false;
                }
                i++;
            }
            if(!ver.isEmpty() && !(Character.isDigit(ver.peek()) || ver.peek()==')'))
                res=false;
        }
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(revisaExpresion(" ( (- 0.2+44.53.0) +(2-2)-0.2*-2)"));
    }
}
