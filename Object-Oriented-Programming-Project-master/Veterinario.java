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
 * Escreva a descrição da classe Veterinario aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Veterinario extends Despesas implements Serializable
{
    private static float deducao = (float) 0.15;
    private static double maximo = 250;
    private static String codigo = "0101"; 
    static final long serialVersionUID = 36L;
    /**
     * Construtor para objetos da classe Saude
     */
    public Veterinario()
    {
        super(deducao,maximo,codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param maximo - valor máximo a deduzir
     * @param codigo - código da atividade económica
     */
    public Veterinario(float deducao, double maximo, String codigo){
        super(deducao,maximo,codigo); 
    }
    
    /**
     * Construtor cópia
     * @param v
     * É dado uma despesa de veterinario
     */
    public Veterinario(Veterinario v){
        super(v);
    }
   
    /**
     * Cria uma despesa de veterinário igual
     * @return um clone
     */ 
    public Veterinario clone() { 
       return new Veterinario(this);
    }
    
    public double valorDeduzir(Entidades e){
        double d5 = 0;
        
        if (e instanceof Individuais){
            Individuais i = (Individuais) e;
            
            for(Faturas f : i.getFaturas()){
                d5+=f.getValor();
            }
            d5 = d5*0.23*deducao;
        
            maximo = i.getAgregado()*maximo; 
        }
        else {
            Empresas em = (Empresas) e;
            
            for(Faturas f : em.getFaturas()){
                d5+=f.getValor();
            }
            
            d5=d5*0.23*deducao;
            
            if (d5>maximo) d5 = maximo;
        }
        
        return d5;
    }
    }