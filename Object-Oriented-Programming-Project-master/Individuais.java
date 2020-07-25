import java.util.*;
import java.util.List;
import java.io.ObjectInputStream; 
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter; 
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Individuais extends Entidades implements Serializable 
{
    /** Número de dependentes do agregado familiar */ 
    private int agregado;
    /** Rendimento da entidade */
    private int rendimento;
    /** Número de filhos */
    private int filhos;
    /** Números fiscais do agregado familiar */ 
    private List<String> numNifAgreg;
    /** Coeficiente Fiscal */ 
    private float coefFiscal; 
    /** Códigos das Atividades Económicas */ 
    private List<String> actEcon;
    static final long serialVersionUID = 14L;
    
    /**
     * Construtor por omissão
     * 
     */
    public Individuais (){
        super ();
        this.agregado = 0;
        this.rendimento = 0;
        this.filhos = 0;
        this.numNifAgreg = new ArrayList<String>(0);
        this.coefFiscal = 0;
        this.actEcon = new ArrayList<String>(0);
    }
    
    /**
     * Construtor parametrizado
     * @param agregado
     * @param numNifAgreg
     * @param coefFiscal 
     * @param actEcon 
     */
    public Individuais (long nif, String email, String nome, String morada, 
    String password,List<Faturas> faturas, int agregado, int rendimento, int filhos, List<String> numNifAgreg, float coefFiscal, List<String> actEcon){
        super(nif, email, nome, morada, password,faturas);
        setAgregado(agregado);
        this.rendimento = rendimento;
        this.filhos = filhos;
        this.numNifAgreg = numNifAgreg;
        this.coefFiscal = coefFiscal; 
        setActEcon(actEcon);
    }
    
    /**
     * Construtor cópia
     * @param i
     * É dada uma entidade individual
     */
    public Individuais(Individuais i){
        super(i.getNif(), i.getEmail(), i.getNome(), i.getMorada(), i.getPassword(),i.getFaturas());
        this.agregado = i.getAgregado();
        this.rendimento = i.getRendimento();
        this.filhos = i.getFilhos();
        this.numNifAgreg = i.getNumNifAgreg();
        this.coefFiscal = i.getCoefFiscal();
        this.actEcon = i.getActEcon();
    }
    
    /**
     * Função que devolve o número de dependentes do agregado familiar
     * @return agregado
     */
    public int getAgregado(){
        return this.agregado;
    }
    
    /**
     * Função que devolve o valor do rendimento
     * @return rendimento
     */
    public int getRendimento(){
        return this.rendimento;
    }
    
    /**
     * Função que devolve o numero de filhos
     * @return filhos
     */
    public int getFilhos(){
        return this.filhos;
    }
    
    /**
     * Função que devolve os números fiscais do agregado familiar
     * @return numeros NIF
     */
    public List<String> getNumNifAgreg(){
        List<String> res = new ArrayList<>();
        for(String s : numNifAgreg) {
            res.add(s);
        }
        return res;
    }
   
    /**
     * Função que devolve o valor do coeficiente fiscal para efeitos de dedução
     * @return coeficiente fiscal
     */
    public float getCoefFiscal(){
        return this.coefFiscal;
    }
    
    /** 
     * Função que devolve os códigos das atividades económicas
     * @return códigos 
     */
    public List<String> getActEcon(){
        List<String> res = new ArrayList<>();
        for(String s : actEcon) {
            res.add(s);
        }
        return res;
    }
  
    /**
     * Função que define o número de dependentes do agregado familiar
     */
    public void setAgregado(int novoAgregado){
        this.agregado = novoAgregado;
    }
    
    /**
     * Função que define o rendimento 
     */
    public void setRendimento(int novoRendimento){
        this.rendimento = novoRendimento;
    }
    
    /**
     * Função que define o numero de filhos 
     */
    public void setFilhos(int novoFilhos){
        this.filhos = novoFilhos;
    }
    
    /**
     * Função que define os números fiscais do agregado familiar
     */
    public void setNumNifAgreg(List<String> numNifAgreg){
        this.numNifAgreg = new ArrayList<>();
        numNifAgreg.forEach(s -> {this.numNifAgreg.add(s);});
    }
   
    /**
     * Função que define o valor do coeficiente fiscal para efeitos de dedução
     */
    public void setCoefFiscal(float novoCoefFiscal){
        this.coefFiscal = novoCoefFiscal;
    }
    
    /** 
     * Função que define os códigos das atividades económicas
     */
    public void setActEcon(List<String> actEcon){
        this.actEcon = new ArrayList<>();
        actEcon.forEach(s -> {this.actEcon.add(s);});
    }
    
    /**
     * Cria uma Entidade Individual igual
     * @return um clone
     */ 
    public Individuais clone() { 
       return new Individuais(this);
    }
        
    /**
     * Verifica se duas Entidades Individuais são iguais
     * @return
     */ 
    public boolean equals(Object o){
        if (o == this) return true; 
        if (o == null || o.getClass()!=this.getClass()) return false;
        Individuais i = (Individuais) o; 
        return this.agregado == i.getAgregado() && 
               this.rendimento == i.getRendimento() &&
               this.filhos == i.getFilhos() &&
               this.numNifAgreg.equals(i.getNumNifAgreg()) && 
               this.coefFiscal == i.getCoefFiscal() && 
               this.actEcon == i.getActEcon();
    }
    
    /**
     * Devolve uma representação textual do objeto
     * @return 
     */
    public String toString(){
     StringBuilder s = new StringBuilder(); 
     s.append("\nIndividuais:\n").append(super.toString())
                                 .append("\nAgregado = ").append(agregado)
                                 .append("\nRendimento = ").append(rendimento)
                                 .append("\nFilhos = ").append(filhos)
                                 .append("\nNumero Fiscal = ").append(numNifAgreg.toString())
                                 .append("\nCoeficiente Fiscal = ").append(coefFiscal)
                                 .append("\nActividade Económica = ").append(actEcon.toString())
                                 .append("\n--------------------------");
     return s.toString();
    }
    
    /** 
     * Função que indica qual o valor acumulado de deduções anuais para os rendimentos dos contribuintes
     * @return acum - valor acumulado
     */
    public double valorAcumulado(){
      double acum=0;
      double d1=0,d2=0,d3=0,d4=0,d5=0,d6=0,d7=0,d8=0,d9=0,d10=0,d11=0;
      double dt=0;
      
      int rendimento = this.getRendimento();
      double rendimentoColect; 
      
      for (Faturas f : getFaturas()){
          dt += f.getDespesa().valorDeduzir(this);
      }
      
      if ((0.11*rendimento*14) <= 4104) rendimentoColect = rendimento - 4104; //valor das deduções específicas
      else rendimentoColect = (rendimento - (0.11*rendimento*14)); // se uma pessoa descontar mais do que 4104€ anuais para a segurança social
      
      if(rendimentoColect <= 7091) acum= dt;
      else{
          if(rendimentoColect > 7091 && rendimentoColect <= 80640) acum = (1000+((2500-1000)*((80640-rendimentoColect)/(80640-7091))));
          else{
              if (rendimentoColect > 80640) acum = 1000;
          }
      }
      return acum;
    }
    
    /**
     * Função que calcula o valor acumulado de todo o agregado familiar
     * @return valor acumulado - double
     */
    public double valorAcumuladoAgreg(){
        List<String> agregado = new ArrayList<>(); 
        double acumulado = this.valorAcumulado();
        
        agregado = this.getNumNifAgreg();
        
        for(String s : agregado){
           acumulado += valorAcumulado();
        }
        
        return acumulado;
        }
    
    }
    
