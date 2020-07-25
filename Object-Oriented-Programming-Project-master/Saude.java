import java.time.LocalDate;
import java.io.ObjectInputStream; 
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter; 
import java.io.FileNotFoundException;

/**
 * Escreva a descrição da classe Saude aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Saude extends Despesas implements Serializable
{
    private static float deducao = (float) 0.15;
    private static double maximo = 1000;
    private static String codigo = "0011";
    static final long serialVersionUID = 33L;

    /**
     * Construtor para objetos da classe Saude
     */
    public Saude()
    {
        super(deducao,maximo,codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param maximo - valor máximo a deduzir
     * @param codigo - código da atividade económica
     */
    public Saude(float deducao, double maximo, String codigo){
        super(deducao,maximo,codigo); 
    }
    
    /**
     * Construtor cópia
     * @param s 
     * É dado uma despesa de saude
     */
    public Saude(Saude s){
        super(s);
    }
  
    /**
     * Cria uma despesa de saúde igual
     * @return um clone
     */ 
    public Saude clone() { 
       return new Saude(this);
    }
    
    public double valorDeduzir(Entidades e){
        double d2 = 0;
        
        if (e instanceof Individuais){
            Individuais i = (Individuais) e;
            
            for(Faturas f : i.getFaturas()){
                d2+=f.getValor();
            }
        
            d2 = d2*0.23*deducao;
        
            if (i.getFilhos() >= 3) maximo = maximo + 127.77*(i.getFilhos()); 
        }
        else {
            Empresas em = (Empresas) e;
            for(Faturas f : em.getFaturas()){
                d2+=f.getValor();
            }
        
            d2=d2*0.23*deducao;
        
            if (d2>maximo) d2 = maximo;
        }

        return d2;
    }
}
