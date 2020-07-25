import java.time.LocalDate;
import java.util.List;
import java.util.*;
import java.io.ObjectInputStream; 
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter; 
import java.io.FileNotFoundException;

public abstract class Despesas implements Serializable {
    public double maximo;
    public float deducao;
    public String codigo;
    static final long serialVersionUID = 3L;
    
    /**
     * Construtor para objetos da classe Despesas
     */
    public Despesas(){
        this.deducao = 0;
        this.maximo = 0;
        this.codigo = "";
    }
    
    /** 
     * Construtor parameterizado 
     * @param deducao - valor da taxa de dedução
     * @param maximo - valor máximo a deduzir
     * @param codigo - código da atividade económica
     */
    public Despesas(float deducao, double maximo, String codigo){
        this.deducao = deducao; 
        this.maximo = maximo;
        this.codigo = codigo;
    }
    
    /**
     * Construtor cópia
     * @param a 
     * É dado uma despesa de alojamento
     */
    public Despesas(Despesas d){
        this.deducao = d.getDeducao();
        this.maximo = d.getMaximo(); 
        this.codigo = d.getCodigo();
    }
    
    /**
     * Função que devolve o valor da dedução
     * @return deducao
     */
    public float getDeducao(){
        return this.deducao;
    }
    
    /**
     * Função que devolve o valor máximo da dedução
     * @return maximo
     */
    public double getMaximo(){
        return this.maximo;
    }
    
    /**
     * Função que devolve o valor do código da atividade económica
     * @return codigo
     */
    public String getCodigo(){
        return this.codigo;
    }
    
    /**
     * Função que define o valor da dedução fiscal
     * @param novoDeducao
     */
    public void setDeducao(float novoDeducao){
        this.deducao = novoDeducao;
    }
    
    /**
     * Função que define o valor da máximo de dedução
     * @param novoMaximo
     */
    public void setMaximo(double novoMaximo){
        this.maximo = novoMaximo;
    }

    /**
     * Função que define o código da atividade económica
     * @param novoCodigo
     */
    public void setCodigo(String novoCodigo){
        this.codigo = novoCodigo;
    }
    
    /**
     * Cria uma Despesa igual
     * @return clone
     */ 
    public abstract Despesas clone();
    
    /**
     * Verifica se duas despesas de alojamento são iguais
     * @return
     */ 
    public boolean equals(Object o){
        if (o == this) return true; 
        if (o == null || o.getClass()!=this.getClass()) return false;
        Alojamentos a = (Alojamentos) o; 
        return this.deducao == a.getDeducao() && 
               this.maximo == a.getMaximo() && 
               this.codigo == a.getCodigo();
    }
    
    /**
     * Devolve uma representação textual do objeto
     * @return 
     */
    public String toString(){
     StringBuilder s = new StringBuilder(); 
     s.append("Dedução = ").append(deducao)
             .append("\nMaximo de dedução = ").append(maximo)
             .append("\nCódigo CAE = ").append(codigo);
     return s.toString();
    }
    
    public abstract double valorDeduzir(Entidades e);
}