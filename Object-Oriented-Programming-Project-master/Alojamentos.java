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
 * Escreva a descrição da classe Alojamentos aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Alojamentos extends Despesas implements Serializable
{
    private static float deducao = (float) 0.15;
    private static double maximo = 250;
    private static String codigo = "0010";
    static final long serialVersionUID = 39L;
    
    /**
     * Construtor para objetos da classe Saude
     */
    public Alojamentos()
    {
        super(deducao,maximo,codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param maximo - valor máximo a deduzir
     * @param codigo - código da atividade económica
     */
    public Alojamentos(float deducao, double maximo, String codigo){
        super(deducao,maximo,codigo); 
    }
    
    /**
     * Construtor cópia
     * @param a 
     * É dado uma despesa de alojamento
     */
    public Alojamentos(Alojamentos a){
        super(a);
    }
    
    /**
     * Cria uma despesa de alojamento igual
     * @return um clone
     */ 
    public Alojamentos clone() { 
       return new Alojamentos(this);
    }
    
    public double valorDeduzir(Entidades e){
        double d8=0;
    
        for(Faturas f : e.getFaturas()){
             d8+=f.getValor();
        }
        d8=d8*0.23*deducao;
   
        if (d8>maximo) d8 = maximo;
        return d8;
    }
}


