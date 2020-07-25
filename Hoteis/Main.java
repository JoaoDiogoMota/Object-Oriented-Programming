import java.util.Scanner;
import java.io.*;
import java.util.*;
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
 public static void main(String[] args){
    /*Scanner sc = new Scanner(System.in);
     HoteisInc hoteis = new HoteisInc();
     int op=-1;
     while(op!=0){
         menuPrincipal();
         op = sc.nextInt();
         switch(op){
             case 1: insereHotel(hoteis);
              break;
              case 2 : //listar hoteis
              break;
            }
        }
     System.out.println("Numero de hoteis:" + hoteis.quantos());
     System.out.println("Existe a:" + hoteis.existeHotel("a"));
    */
   HoteisInc hoteis = null;
   Scanner sc = new Scanner(System.in);
   while(hoteis==null){
     System.out.println("Insira o nome do ficheiro a ler:");
     String nome = sc.nextLine();
    }
   try{
    hoteis = HoteisInc.carregar("hoteis.obj");
} catch(FileNotFoundException e){
    System.out.println("Ficheiro inexistente");
          }
         try{
             hoteis.exportarCSV("hoteis.csv");
            }
            catch(Exception e){
             //...   
            }
            
            Comparator<Hotel> cq = new QuartosComparator();
            TreeSet<Hotel> r = hoteis.ordenarHoteis(cq);
            Hotel h=new HotelStandard();
            try{
             h.setPreco(10);   
            } catch(DadosInvalidosException e){
                
            }
            
            try{
             hoteis.save("...");   
            }
            catch(Exception ex){
             //...   
            }

    }
    
    public static void menuPrincipal(){
     System.out.println("--Menu--");
     System.out.println("1 - Inserir hotel");
     System.out.println("0 - Sair");
     
    }
    
    private static void  verificaHoteis(HoteisInc hoteis,String id){
           System.out.println("Existe a:" + hoteis.existeHotel("a"));
        }
        
    private static void contaHoteis(HoteisInc hoteis){
         System.out.println("Numero de hoteis:" + hoteis.quantos());

    }
    private static void insereHotel(HoteisInc hoteis){
     HotelStandard h = new HotelStandard("a","Hotel n 1","Braga",4,100,50,false);
     hoteis.insereHotel(h);
    }
}
