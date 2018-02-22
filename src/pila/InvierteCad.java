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
public class InvierteCad {
    private String cadInicial;
    private String cadInvertida;

    public InvierteCad(String cadInicial) {
        this.cadInicial = cadInicial;
        invierteCadInicial();
    }

    public String getCadInicial() {
        return cadInicial;
    }
    public String getCadInvertida() {
        return cadInvertida;
    }
    public void setCadInicial(String cadInicial) {
        this.cadInicial = cadInicial;
        invierteCadInicial();
    }
   
    private void invierteCadInicial(){
        PilaA<Character> pila=new PilaA();
        StringBuilder cad=new StringBuilder();
        
        cadInvertida=null;
        if(cadInicial!=null){
            for(int i=0;i<cadInicial.length();i++)
                pila.push(cadInicial.charAt(i));
            while(!pila.isEmpty())
                cad.append(pila.pop());
            cadInvertida=cad.toString();
        }
    }
    
    public static void main(String[] args) {
        InvierteCad prueba=new InvierteCad("Silvestre Leonardo Gonzalez Abreu");
        System.out.println(prueba.getCadInvertida());
    }
}
