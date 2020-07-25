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
 * Escreva a descrição da classe Transportes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Transportes extends Despesas implements Serializable
{
    private static float deducao = 1;
    private static double maximo = 10000;
    private static String codigo = "0111";
    static final long serialVersionUID = 38L;
    
    /**
     * Construtor para objetos da classe Saude
     */
    public Transportes()
    {
        super(deducao,maximo,codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param codigo - código da atividade económica
     */
    public Transportes(float deducao, String codigo){
        super(deducao,maximo,codigo); 
    }
    
    /**
     * Construtor cópia
     * @param t
     * É dado uma despesa de transportes publicos
     */
    public Transportes(Transportes t){
        super(t);
    }
    
    /**
     * Cria uma despesa de transportes igual
     * @return um clone
     */ 
    public Transportes clone() { 
       return new Transportes(this);
    }
    
    public double valorDeduzir(Entidades e){
        double d7=0;
        
        for(Faturas f : e.getFaturas()){
            d7+=f.getValor();
        }
        d7=0.23*d7;
        return d7;
    }
}
