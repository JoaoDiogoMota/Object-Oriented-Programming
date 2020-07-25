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
 * Escreva a descrição da classe Educacao aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Educacao extends Despesas implements Serializable
{
    private static float deducao = (float) 0.3;
    private static double maximo = 800;
    private static String codigo = "0100";
    static final long serialVersionUID = 35L;
    
    /**
     * Construtor para objetos da classe Saude
     */
    public Educacao()
    {
        super(deducao,maximo,codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param maximo - valor máximo a deduzir
     * @param codigo - código da atividade económica
     */
    public Educacao(float deducao, double maximo, String codigo){
        super(deducao,maximo,codigo); 
    }
    
    /**
     * Construtor cópia
     * @param e
     * É dado uma despesa de educação
     */
    public Educacao(Educacao e){
        super(e);
    }
    
    /**
     * Cria uma despesa de educação igual
     * @return um clone
     */ 
    public Educacao clone() { 
       return new Educacao(this);
    }
    
    public double valorDeduzir(Entidades e){
       double d4 = 0;
        
       if (e instanceof Individuais){
        Individuais i = (Individuais) e;
        for(Faturas f : i.getFaturas()){
            d4+=f.getValor();
        }
        d4 = d4*0.23*deducao;
        
        if (i.getFilhos() >= 3) maximo = maximo + 142.50*(i.getFilhos()); 
        
       }
       else {
        Empresas em = (Empresas) e;
        for(Faturas f : em.getFaturas()){
            d4+=f.getValor();
        }
        d4=d4*0.23*deducao;
        if (d4>maximo) d4 = maximo;
       }
        
       return d4;
    }
}
