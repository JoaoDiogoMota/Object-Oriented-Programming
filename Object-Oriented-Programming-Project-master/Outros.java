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
 * Escreva a descrição da classe Outros aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Outros extends Despesas implements Serializable
{
    private static float deducao = (float) 0.35;
    private static double maximo = 250;
    private static String codigo = "1001";
    static final long serialVersionUID = 30L;

    /**
     * Construtor para objetos da classe Saude
     */
    public Outros()
    {
        super(deducao,maximo,codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param maximo - valor máximo a deduzir
     * @param codigo - código da atividade económica
     */
    public Outros(float deducao, double maximo, String codigo){
        super(deducao,maximo,codigo); 
    }
    
    /**
     * Construtor cópia
     * @param o
     * É dado uma despesa de outros
     */
    public Outros(Outros o){
        super(o);
    }
    
    /**
     * Cria uma despesa de outros igual
     * @return um clone
     */ 
    public Outros clone() { 
       return new Outros(this);
    }
    
    public double valorDeduzir(Entidades e){
        double d9=0;
        
        for(Faturas f : e.getFaturas()){
    
            d9+=f.getValor();
        }
        
        d9=d9*deducao;
    
        if (d9>maximo) d9 = maximo;
        return d9;
    }
}
