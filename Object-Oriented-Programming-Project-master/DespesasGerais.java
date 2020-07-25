import java.io.ObjectInputStream; 
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter; 
import java.io.FileNotFoundException;

/**
 * Escreva a descrição da classe DespesasGerais aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class DespesasGerais extends Despesas implements Serializable
{
    private static float deducao = (float) 0.35;
    private static double maximo = 250 ;
    private static String codigo = "1010";
    static final long serialVersionUID = 37L;
    
    /**
     * Construtor para objetos da classe Saude
     */
    public DespesasGerais()
    {
        super(deducao,maximo,codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param codigo - código da atividade económica
     */
    public DespesasGerais(float deducao, String codigo){
        super(deducao,maximo,codigo); 
    }
    
    /**
     * Construtor cópia
     * @param t
     * É dado uma despesa de transportes publicos
     */
    public DespesasGerais(DespesasGerais t){
        super(t);
    }
    
    /**
     * Cria uma despesa de transportes igual
     * @return um clone
     */ 
    public DespesasGerais clone() { 
       return new DespesasGerais(this);
    }
    
    public double valorDeduzir(Entidades e){
       double d6=0;
    
       for(Faturas f : e.getFaturas()){
             d6+=f.getValor();
       }
       d6=d6*deducao;
    
       if (d6>maximo) d6 = maximo;
       return d6;
    }
}
