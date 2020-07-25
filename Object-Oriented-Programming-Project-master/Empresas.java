import java.util.*;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.io.ObjectInputStream; 
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter; 

public class Empresas extends Entidades implements Serializable 
{
    /** Atividades Económicas */  
    private List<String> AtivEcon; 
    /** Valor da Dedução Fiscal */ 
    private float deducaoFiscal;
    static final long serialVersionUID = 15L;
    
    /**
     * Construtor por omissão
     * 
     */
    public Empresas (){
        super ();
        this.AtivEcon = new ArrayList<String> ();
        this.deducaoFiscal = 0;
    }
    
    /**
     * Construtor parametrizado
     * @param variáveis de instância: Atividades económicas e dedução Fiscal
     */
    public Empresas (long nif, String email, String nome, String morada, String password, List<Faturas> faturas, 
    List<String> AtivEcon, float deducaoFiscal){
    super(nif,email,nome,morada,password,faturas);
    this.setAtivEcon(AtivEcon);
    this.deducaoFiscal = deducaoFiscal;
    }
    
    /**
     * Construtor cópia
     * @param é dado uma Empresa
     */
    public Empresas(Empresas e){
        super(e.getNif(), e.getEmail(), e.getNome(), e.getMorada(), e.getPassword(),e.getFaturas());
        this.AtivEcon = e.getAtivEcon();
        this.deducaoFiscal = e.getDeducaoFiscal();
    }
    
    /**
     * Função que devolve as informações relativas às atividades económicas
     * @return atividades económicas
     */
    public List<String> getAtivEcon(){
        List<String> res = new ArrayList<>();
        for(String s : AtivEcon) {
            res.add(s);
        }
        return res;
    }
    
    /**
     * Função que devolve o fator que a empresa tem no calculo da dedução fiscal
     * @return dedução fiscal
     */
    public float getDeducaoFiscal(){
        return this.deducaoFiscal;
    }
    
    /**
     * Função que atualiza as informações relativas às atividades económicas
     */
    public void setAtivEcon(List<String> AtivEcon){
        this.AtivEcon = new ArrayList<>();
        AtivEcon.forEach(s -> {this.AtivEcon.add(s);});
    }
    
    /**
     * Função que atualiza o valor da dedução fiscal para efeitos de dedução
     */
    public void setDeducaoFiscal(float novoDeducaoFiscal){
        this.deducaoFiscal = novoDeducaoFiscal;
    }    
    
    /**
        * Cria uma Empresa igual
        */ 
       public Empresas clone() { 
           return new Empresas(this);
        }
        
    /**
     * Verifica se duas Empresas são iguais
     * @return
     */ 
    public boolean equals(Object o){
        if (o == this) return true; 
        if (o == null || o.getClass()!=this.getClass()) return false;
        Empresas e = (Empresas) o; 
        return this.deducaoFiscal == e.getDeducaoFiscal() &&
               this.AtivEcon.equals(e.getAtivEcon());
    }
    
    /**
     * Devolve uma representação textual do objeto
     * @return 
     */
    public String toString(){
     StringBuilder s = new StringBuilder(); 
     s.append("\nEmpresa:\n").append(super.toString()) 
                             .append("\nAtividade Económica = ").append(AtivEcon.toString())
                             .append("\nDedução Fiscal = ").append(deducaoFiscal)
                             .append("\n--------------------------");
     return s.toString();
    }    
    
    /**
     * Função que indica o total faturado por uma empresa num determinado período
     * @param data de início - d1
     * @param data de fim - d2
     * @return valor faturado - t
     */
    public double totalFaturado(LocalDate d1, LocalDate d2){
     double t = 0;
     Set<Double> total = this.getFaturas().stream()
                                          .filter(e -> e.getData().isAfter(d1) && e.getData().isBefore(d2))
                                          .map(Faturas::getValor)
                                          .collect(Collectors.toSet());  
     for (Double d : total) {
          t += d;
     }
      
     return t;   
    }
    
    /**
     * Função que obtém a lista de faturas por ordem decrescente de Valor das faturas
     * @return lista de faturas ordenadas - faturas
     */
    public List<Faturas> ordenaValor(){
        List<Faturas> faturas = new ArrayList<>();
        for(Faturas f : getFaturas()){
                faturas.add(f);
        }
            
        Comparator<Faturas> cv = (a,b) -> {
             if (a.getValor() < b.getValor())
                     return 1;
             else if (a.getValor() > b.getValor())
                     return -1;
                else return 0;
        };
        faturas.sort(cv);
        
        return faturas;
    }
    
    /**
     * Função que obtém a lista de faturas por ordem de data de emissão das faturas
     * @return lista de faturas ordenadas - faturas
     */
    public List<Faturas> ordenaData(){
        List<Faturas> faturas = new ArrayList<>();
        for(Faturas f : getFaturas()){
                faturas.add(f);
        }
            
        Comparator<Faturas> cv = (a,b) -> {
             if ((a.getData()).isBefore(b.getData()))
                     return 1;
             else if ((a.getData().isAfter(b.getData())))
                     return -1;
                else return 0;
        };
        faturas.sort(cv);
            
        return faturas;
    }
    
    /**
     * Função que apresenta a lista de faturas por contribuinte ordenada por valor decrescente
     * @return lista de faturas e contribuinte associado - faturas
     */
    public Map<Long,List<Faturas>> ordenaValorNIF(){
        List<Faturas> faturas = new ArrayList<>();
        for(Faturas f : getFaturas()){
                faturas.add(f);
        }
        
        Comparator<Faturas> cv = (a,b) -> {
             if (a.getValor() < b.getValor())
                     return 1;
             else if (a.getValor() > b.getValor())
                     return -1;
                else return 0;
        };
        
        faturas.sort(cv);
        
        List<Faturas> f = faturas.stream()
                                 .map(Faturas::clone)
                                 .collect(Collectors.toList());
                                          
        Map<Long, List<Faturas>> fatcont = f.stream()   
                                            .collect(Collectors.groupingBy(Faturas::getNifCliente));
        
        return fatcont;
    } 
    
    /**
     * Função que apresenta a lista de faturas por contribuinte num intervalo de datas
     * @return lista de faturas e contribuinte associado - faturas
     */
    public Map<Long,List<Faturas>> ordenaNIFData(LocalDate primeira, LocalDate segunda){
        List<Faturas> faturas = new ArrayList<>();
        for(Faturas f : getFaturas()){
                faturas.add(f);
        }
        
        List<Faturas> f = faturas.stream()
                                 .filter(e -> e.getData().isAfter(primeira) && e.getData().isBefore(segunda))
                                 .map(Faturas::clone)
                                 .collect(Collectors.toList());
                                          
        Map<Long, List<Faturas>> fatcont = f.stream()   
                                            .collect(Collectors.groupingBy(Faturas::getNifCliente));
        
        return fatcont;
    }
}