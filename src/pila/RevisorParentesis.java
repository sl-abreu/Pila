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
public class RevisorParentesis {
    private String expresion;

    public RevisorParentesis(String expresion) {
        this.expresion = expresion;
    }

    public String getExpresion() {
        return expresion;
    }
    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }
    
    public boolean analizaParentesis(){
        PilaA<Character> pila=new PilaA();
        boolean res=true;
        int i=0;
        
        while(i<expresion.length() && res){
            if(expresion.charAt(i)=='(')
                pila.push('(');
            else if(expresion.charAt(i)==')')
                try{
                    pila.pop();
                }catch(ExceptionPilaVacia e){
                    System.err.print(e);
                    res=false;
                }
            i++;
        }
        if(!pila.isEmpty())
            res=false;
        return res;
    }
    
    public static void main(String[] args) {
        RevisorParentesis prueba=new RevisorParentesis("(2+4)*(5-1)");
        System.out.println(prueba.analizaParentesis());
    }
}
