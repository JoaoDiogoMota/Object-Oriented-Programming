import java.util.Scanner;
/**
 * Write a description of class olaMundo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ficha1
{
    
    public double celsiusParaFarenheit(double graus){
    double x= graus*(9/5)+32;
    System.out.println("O valor em Farenheit é: " +x);
    return x;
    }
   
    public static void main(String[] args){
     Scanner ler = new Scanner(System.in);
     double x = ler.nextDouble();
     double y = celsiusParaFarenheit(x);
     //System.out.println("O valor em Farenheit é: " +y); 
     ler.close();
    }
}

