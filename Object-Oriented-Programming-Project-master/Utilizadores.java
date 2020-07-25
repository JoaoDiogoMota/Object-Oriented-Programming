import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Iterator; 
import static java.util.stream.Collectors.toMap;
import java.util.*;
import java.io.ObjectInputStream; 
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter; 
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class Utilizadores implements Serializable 
{
    /** Utilizadores do sistema */
    private Map<Long,Entidades> utilizadores; 
    static final long serialVersionUID = 12L;
    
    /**
     * Construtor por omissão
     * 
     */
    public Utilizadores (){
       utilizadores = new HashMap<>(); // o long corresponde ao NIF
    }
    
    /**
     * Construtor cópia
     * função em que é dado um utilizador
     * @param Utilizador
     * 
     */
    public Utilizadores(Utilizadores u){
        this.utilizadores = new HashMap<>();
        for(Map.Entry<Long,Entidades> e : u.utilizadores.entrySet()){
            this.utilizadores.put(e.getKey(),e.getValue().clone());
        }
    }
    
    /**
     * Construtor parametrizado
     * @param Map<Long,Entidades> utilizadores 
     */
    public Utilizadores(Map<Long,Entidades> utilizadores){
        this.utilizadores = new HashMap<>();
        for(Map.Entry<Long,Entidades> e : utilizadores.entrySet()){
            this.utilizadores.put(e.getKey(),e.getValue().clone());
        }
    }
    
    /** 
     * Função que altera os utilizadores
     * @param utilizadores 
     */
    public void setUtilizadores(Map<Long,Entidades> utilizadores){
        this.utilizadores = utilizadores.entrySet()
                                        .stream()
                                        .collect(toMap(e->e.getKey(), e-> e.getValue().clone()));
    }
    
    /** 
     * Função que devolve um utilizador consoante o nif
     * @param nif
     * @return Entidades 
     */
    public Entidades getUtilizador(Long nif) throws UtilizadorInexistenteException{
        /*Entidades e; 
        e = utilizadores.get(nif);
        if (e != null){
            e = e.clone();
        }
        return e;
        */
        if (!utilizadores.containsKey(nif)){
            throw new UtilizadorInexistenteException();
        }
    
        return utilizadores.get(nif).clone();
    }
    
    /** 
     * Função que devolve a lista de utilizadores 
     * @return List<Entidades>
     */
    public Map<Long,Entidades> getUtilizadores(){
        Map<Long,Entidades> novo = new HashMap<>();
        for(Long i : this.utilizadores.keySet()){
            Entidades e = this.utilizadores.get(i);
            novo.put(i,e);
        }
        return novo;
    }
    
    /**
     * Função que verifica se dois utilizadores são iguais
     * @param objeto
     * @return True/False
     */
     public boolean equals(Object o) {
         if (o == this) return true; 
         if (o == null || o.getClass() != this.getClass()) return false; 
         Utilizadores e = (Utilizadores) o;
         return this.getUtilizadores().equals(e.getUtilizadores());
    }
    
    /**
     * Devolve uma representação textual do objeto
     * @return 
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
 
        sb.append(this.utilizadores);
        
        return sb.toString();
    }
   
    /**
     * Função que recolhe as faturas de todos os utilizadores
     * @return lista de faturas - faturas
     */
    public List<Faturas> recolheFaturas(){
        List<Faturas> faturas = new ArrayList<>();
        for(Map.Entry<Long,Entidades> e : this.utilizadores.entrySet()){
            for(Faturas f : e.getValue().getFaturas()){
            faturas.add(f.clone());
         }
        }
        return faturas;
    }
    
    
    /**
     * Função que regista um contribuinte, quer seja individual ou empresa
     * @param Entidades e
     */
    public void regista(Entidades e){
        if(e !=null){
            this.utilizadores.putIfAbsent(e.getNif(),e.clone());
        }
    }
    
    /**
     * Função que atribui uma fatura a um contribuinte
     * @param Utilizadores u 
     * @param Faturas f
     */
    public void atribuiFatura(Utilizadores u,Faturas f){
        Map<Long,Entidades> a = u.getUtilizadores();
        Entidades emit = a.get(f.getNifEmit());
        emit.addFatura(f);
        a.replace(f.getNifEmit(),emit);
        Entidades cliente = a.get(f.getNifCliente());
        cliente.addFatura(f);
        a.replace(f.getNifCliente(),cliente);
        u.setUtilizadores(a);
    }
    
   /**
   * Função que ordena pela ordem natural definido em Entidades
   */ 
   public TreeSet<Faturas> ordenarFaturas(){
        TreeSet<Faturas> f = new TreeSet<>(); 
        this.utilizadores.values()
                     .stream()
                     .map(Entidades::getFaturas);
        return f;
   }
    
   /**
    * Função que ordena de acordo com o Comparator fornecido 
    */
    public TreeSet<Faturas> ordenarFaturas(Comparator<Faturas> c){
        TreeSet<Faturas> r = new TreeSet<>(c); 
        this.utilizadores.values()
                         .stream()
                         .map(Entidades::getFaturas);
        return r;
    }
 
   /**
    * Função que devolve os 10 contribuintes que mais gastam 
    * @return contribuintes e valor gasto - Map<Entidades,Double>
    */
   public Map<Entidades,Double> top10(){
        List<Entidades> entidades = new ArrayList<>();
        
        for(Entidades e : this.getUtilizadores().values()){
            entidades.add(e);
        }
        
        Comparator<Entidades> cent = (a,b) -> {
           if(a.despesasTotais() > b.despesasTotais()) return 1; 
           else{
              
               if (a.despesasTotais() < b.despesasTotais()) return -1; 
               else return 0;
           
            }
        };          
        entidades.sort(cent);
        
        Map<Entidades,Double> reT = new HashMap<>();

        Iterator it = entidades.iterator();
        while(it.hasNext()){
            Entidades e = (Entidades) it.next();
            double insert = e.despesasTotais(); 
            reT.put(e,insert);
        }
                                         
        return reT;
   }
       
   /**
    * Função que devolve a lista das X empresas com mais faturas do Sistema
    * @param número de empresas a comparar - x 
    * @return lista de empresas ordenada - empresas
    */
   public List<Empresas> RelacaoXEmpresas(int x){
      List<Empresas> empresas = new ArrayList<>();
       
      for(Entidades e : this.getUtilizadores().values()){
           if(e instanceof Empresas){
               Empresas em = (Empresas) e;
               empresas.add(em);
            }
      }
   
       Comparator<Empresas> xemp = (a,b) -> {
           if((a.getFaturas().size() < b.getFaturas().size())) return 1;
           else if ((a.getFaturas().size() > b.getFaturas().size())) return -1;
           else return 0;
       };
       
       empresas.sort(xemp);
      
       
       if(empresas.size()>x){
           empresas.subList(x,empresas.size()).clear();
       }
       
       return empresas;
    }
   
   public void exportToCSV(String name) throws IOException{
     FileWriter fw = new FileWriter(name, false);
     for (Entidades e : utilizadores.values()){
         fw.write(e.getNif()+","+ e.getNome());
        }
        fw.flush();
        fw.close();
   }
       
   public void save() throws IOException{
       ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estado.txt")); 

       oos.writeObject(this);
       oos.flush();
       oos.close();
    }
    
   public Utilizadores load() throws IOException, ClassNotFoundException, FileNotFoundException{
       ObjectInputStream ois = new ObjectInputStream(new FileInputStream("estado.txt")); 
       Utilizadores u = (Utilizadores) ois.readObject(); 
       ois.close();
       return u; 
   }
} 