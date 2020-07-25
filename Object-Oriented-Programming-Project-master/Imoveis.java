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
 * Escreva a descrição da classe Imóveis aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Imoveis extends Despesas implements Serializable
{
    private static float deducao = (float) 0.15;
    private static double maximo = 502;
    private static String codigo = "1000";
    static final long serialVersionUID = 31L;

    /**
     * Construtor para objetos da classe Saude
     */
    public Imoveis()
    {
        super(deducao, maximo, codigo); 
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param maximoR - valor máximo a deduzir quando se trata de uma renda
     * @param maximoJ - valor máximo a deduzir quando se trata de juros 
     * @param codigo - código da atividade económica
     */
    public Imoveis(float deducao, double maximo,String codigo){
        super(deducao, maximo, codigo); 
    }
    
    /**
     * Construtor cópia
     * @param i
     * É dado uma despesa de imóveis
     */
    public Imoveis(Imoveis i){
        super(i);
    }
    
    /**
     * Cria uma Despesa de Imóveis igual
     * @return um clone
     */ 
    public Imoveis clone() { 
       return new Imoveis(this);
    }
    
    public double valorDeduzir(Entidades e){
        double d11=0;
        
        for(Faturas f : e.getFaturas()){
            d11+=f.getValor();
        }
        
        d11=d11*deducao;
    
        if (d11>maximo) d11 = maximo;
        
        return d11;
    }
}
