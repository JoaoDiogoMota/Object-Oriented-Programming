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
 * Escreva a descrição da classe Saloes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Saloes extends Despesas implements Serializable
{
    private static float deducao = (float) 0.15;
    private static double maximo = 250;
    private static String codigo = "0000";
    static final long serialVersionUID = 40L;
    
    /**
     * Construtor para objetos da classe Saude
     */
    public Saloes()
    {
        super(deducao,maximo,codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param maximo - valor máximo a deduzir
     * @param codigo - código da atividade económica
     */
    public Saloes(float deducao, double maximo, String codigo){
        super(deducao,maximo,codigo); 
    }
    
    /**
     * Construtor cópia
     * @param s
     * É dado uma despesa de um salão
     */
    public Saloes(Saloes s){
        super(s);
    }
    
    /**
     * Cria uma despesa de salões igual
     * @return um clone
     */ 
    public Saloes clone() { 
       return new Saloes(this);
    }
    
    public double valorDeduzir(Entidades e){
        double d10=0;
    
        for(Faturas f : e.getFaturas()){
            d10+=f.getValor();
        }
        d10=d10*0.23*deducao;
    
        if (d10>maximo) d10 = maximo;
        return d10;
    }
}
