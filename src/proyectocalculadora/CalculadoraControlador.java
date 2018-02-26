/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocalculadora;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pila.PilaA;


/**
 *
 * <pre>
 * CalculadoraControlador
 * 
 * Recibe, procesa y maneja la información recibida de la interfaz gráfica
 * </pre>
 * @author Silvestre Leonardo González Abreu
 * @deprecated 
 */
public class CalculadoraControlador extends CalculadoraVista{

    public CalculadoraControlador() {
        super();
        super.evaluaBut.addActionListener(new EscuchadorBut());
        super.limpBut.addActionListener(new EscuchadorBut());
    }
    
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
        PilaA<Character> verf=new PilaA();
        char aux;
        boolean res=false,banOp=false,banPun=false;
        int i=0;
        
        while(i<exp.length()-1 && exp.charAt(i)==' ')
            i++;
        aux=exp.charAt(i);
        if(Character.isDigit(aux) || aux=='(' || aux=='-'){
            verf.push(aux);
            res=true;
            i++;
            while(i<exp.length() && res){
                aux=exp.charAt(i);
                if(aux!=' '){
                    if(Character.isDigit(aux) && verf.peek()!=')'){
                        if(verf.peek()!='(')
                            verf.pop();
                        verf.push(aux);
                    }
                    else if(aux=='(' && (esOperador(verf.peek()) || verf.peek()=='(')){
                        if(verf.peek()!='(')
                            verf.pop();
                        verf.push(aux);
                    }
                    else if(esOperador(aux) && (Character.isDigit(verf.peek()) || verf.peek()==')' || (aux=='-' && !banOp && verf.peek()!='.'))){
                        banPun=false;
                        if(aux=='-' && verf.peek()=='-')
                            banOp=true;
                        else
                            banOp=false;
                        if(verf.peek()!='(')
                            verf.pop();
                        verf.push(aux);
                    }
                    else if(aux=='.' && (Character.isDigit(verf.peek()) && !banPun)){
                        banPun=true;
                        verf.pop();
                        verf.push(aux);
                    }
                    else if(aux==')' && !verf.isEmpty()){
                        char temp=verf.pop();
                        if((Character.isDigit(temp) || temp==')') && !verf.isEmpty() && verf.pop()=='('){
                            banPun=false;
                            verf.push(aux);
                        }
                        else
                            res=false;
                    }
                    else
                        res=false;
                }
                i++;
            }
            if(res && !verf.isEmpty()){
                aux=verf.pop();
                if(!verf.isEmpty() || !(Character.isDigit(aux) || aux==')'))
                    res=false;
            }  
        }
        return res;
    }
    
    private class EscuchadorBut implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().charAt(0)=='E'){
                String exp=expTxt.getText();
                if(!exp.isEmpty() && revisaExpresion(exp)){
                    resTxt.setText("Vas por buen camino.");
                }
                else
                    resTxt.setText("Error de sintaxis, favor de revisar la expresión.");
            }
            else{
                resTxt.setText("");
                expTxt.setText("");
            }
        }
    }
    
    public static void main(String[] args) {
        
        //System.out.println(revisaExpresion("((2-2))"));
        
        CalculadoraControlador pru=new CalculadoraControlador();
        pru.setVisible(true);
    }
}
