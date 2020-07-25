import java.util.List;
import java.util.*;
import java.io.ObjectInputStream; 
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter; 
import java.util.stream.Stream;
import java.util.stream.Collectors;

public abstract class Entidades implements Comparable<Faturas>, Serializable 
{
    /** Nif */ 
    private long nif;
    /** email */
    private String email;
    /** nome */ 
    private String nome;
    /** morada */  
    private String morada;
    /** password de acesso */ 
    private String password;
    /** Faturas dos contribuintes */
    private List<Faturas> faturas;
    static final long serialVersionUID = 13L;
    
    /**
     * Construtor por omissão
     * 
     */
    public Entidades(){
        this.nif = 0;
        this.email = ""; 
        this.nome = "";
        this.morada = ""; 
        this.password = "";
        this.faturas = new ArrayList<>();
    }
    
    /**
     * Construtor parametrizado
     * @param nif  
     * @param email
     * @param nome
     * @param morada
     * @param password 
     */
    public Entidades(long nif, String email, String nome, String morada, String password,List<Faturas> faturas){
        this.nif = nif;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.password = password; 
        setFaturas(faturas);
    }
    
    /**
     * Construtor cópia
     * @param Entidade 
     * É dada uma entidade
     */
    public Entidades(Entidades en){
        this.nif = en.getNif(); 
        this.email = en.getEmail();
        this.nome = en.getNome();
        this.morada = en.getMorada();
        this.password = en.getPassword();
        this.faturas = en.getFaturas();
    }
    
    /**
     * Função que devolve o número de identificação fiscal 
     * @return NIF
     */
    public long getNif (){
        return this.nif; 
    }
    
    /**
     * Função que devolve o email
     * @return email
     */
    public String getEmail(){
        return this.email;
    }
    
    /**
     * Função que devolve o nome
     * @return nome
     */
    public String getNome(){
        return this.nome;
    }
    
    /**
     * Função que devolve a morada
     * @return morada
     */
    public String getMorada(){
        return this.morada;
    }
    
    /**
     * Função que devolve a password de acesso 
     * @return password
     */
    public String getPassword(){
        return this.password;
    }
    
    /**
     * Função que devolve a lista das faturas
     * @return faturas
     */
    public List<Faturas> getFaturas(){
        List<Faturas> res = new ArrayList<>(); 
        for(Faturas s : faturas){
            res.add(s.clone());
        } 
        return res;
    }
    
    /**
     * Define o valor do número fiscal 
     * @param novoNif 
     */
    public void setNif(long novoNif){
        this.nif = novoNif;
    }
    
    /**
     * Define o nome
     * @param novoNome 
     */
    public void setNome (String novoNome){
        this.nome = novoNome;
    } 
    
    /**
     * Define o email 
     * @param novoEmail 
     */
    public void setEmail (String novoEmail){
        this.email = novoEmail;
    }
    
    /**
     * Define a morada
     * @param novoMorada 
     */
    public void setMorada (String novoMorada){
        this.morada = novoMorada;
    }
    
    /**
     * Define a password de acesso 
     * @param novoPassword 
     */
    public void setPassword (String novoPassword){
        this.password = novoPassword; 
    }

    /**
     * Define uma fatura
     * @param novaFatura
     */
    public void setFaturas(List<Faturas> faturas){
        this.faturas = new ArrayList<>(); 
        faturas.forEach(s->{this.faturas.add(s);});
    }
    
    /**
     * Cria uma Entidade igual
     * @return clone
     */ 
    public abstract Entidades clone();
   
    /**
     * Retorna uma representação textual do objecto
     * @return 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NIF: ").append(nif)
          .append("\nEmail: ").append(email)
          .append("\nNome: ").append(nome)
          .append("\nMorada: ").append(morada)
          .append("\nPassword: ").append(password)
          .append("\nFaturas: ").append(faturas.toString());
        return sb.toString();
    }
    
    /**
     * Verifica a igualdade de dois objectos
     * @param obj
     * @return 
     */
    public boolean equals(Object objeto) {
        if(objeto==this) return true;
        if(objeto==null || objeto.getClass()!=this.getClass()) return false;
        Entidades e = (Entidades) objeto;
        return this.nif == e.getNif() && this.email.equals(e.getEmail()) && 
               this.nome.equals(e.getNome()) && this.morada.equals(e.getMorada()) 
               && this.password.equals(e.getPassword()) && this.faturas.equals(e.getFaturas());
    }
    
    /** 
     * Função hash
     * @return hash
     */
    public int hashCode(){
        int hash = 7; 
        
        hash = (int)(nif^(nif>>>32));
        hash = 31*hash + email.hashCode();
        hash = 31*hash + morada.hashCode();
        hash = 31*hash + nome.hashCode();
        hash = 31*hash + password.hashCode();
        
        return hash;
    }
    
    /**
     * Função que adiciona uma fatura à lista de faturas
     * @param fatura a adicionar - f
     */
    public void addFatura(Faturas f){
        this.faturas.add(f.clone());
    }
    
    /**
     * Função que devolve o valor das despesas totais de um contribuinte
     * @return double  
     */
    public double despesasTotais(){
        double total = 0; 
        Set<Double> t = this.getFaturas().stream()
                                         .map(Faturas::getValor)
                                         .collect(Collectors.toSet());
                                     
        for(Double d : t){
           total += d;
        }
        return total ;
    }
    
    /**
     * Ordem natural
     * @return ordenação das faturas por nif do cliente
     */
    public int compareTo(Faturas f){
        return Long.compare(nif,f.getNifCliente());
    }
}