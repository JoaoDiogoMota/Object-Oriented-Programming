import java.time.LocalDate;
import java.io.ObjectInputStream; 
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter; 
import java.io.FileNotFoundException;
public class Manutencao extends Despesas implements Serializable{
   private static float deducao = (float) 0.15;
   private static double maximo = 250;
   private static String codigo = "0110";
   static final long serialVersionUID = 32L;
   
   /**
     * Construtor para objetos da classe Saude
     */
   public Manutencao(){
        super(deducao,maximo,codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param maximo - valor máximo a deduzir
     * @param codigo - código da atividade económica
     */
   public Manutencao(float deducao, double maximo, String codigo){
        super(deducao,maximo,codigo); 
   }
    
   /**
     * Construtor cópia
     * @param m
     * É dado uma despesa de manutenção
     */
   public Manutencao(Manutencao m){
        super(m);
   }
   
   /**
     * Cria uma Despesa de Manutençãoigual
     * @return um clone
     */ 
    public Manutencao clone() { 
       return new Manutencao(this);
    }
   
   public double valorDeduzir(Entidades e){
        double d1 = 0;
        for(Faturas f : e.getFaturas()){ 
            d1 += f.getValor();
        }
        d1 = d1*0.23*deducao;
        
        if (d1 > maximo) d1 = maximo;
        
        return d1;
   }
}
