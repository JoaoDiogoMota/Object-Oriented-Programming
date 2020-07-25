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
 * Escreva a descrição da classe Lares aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Lares extends Despesas implements Serializable
{
    private static float deducao = (float) 0.25;
    private static double maximo = 403.75;
    private static String codigo = "0001";
    static final long serialVersionUID = 34L;

    /**
     * Construtor para objetos da classe Saude
     */
    public Lares()
    {
        super(deducao,maximo,codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param maximo - valor máximo a deduzir
     * @param codigo - código da atividade económica
     */
    public Lares( float deducao, double maximo, String codigo){
        super(deducao,maximo,codigo); 
    }
    
    /**
     * Construtor cópia
     * @param l 
     * É dado uma despesa de um lar
     */
    public Lares(Lares l){
        super(l);
    }
    
    /**
     * Cria uma despesa de lares igual
     * @return um clone
     */ 
    public Lares clone() { 
       return new Lares(this);
    }
    
    public double valorDeduzir(Entidades e){
        double d3=0;
        for(Faturas f : e.getFaturas()){
            d3+=f.getValor();
        }
        d3=d3*deducao;
    
        if (d3>maximo) d3 = maximo;
        return d3;
    }
    
}
