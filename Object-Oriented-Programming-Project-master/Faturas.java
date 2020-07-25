import java.time.LocalDate;
import java.util.List;
import java.io.ObjectInputStream; 
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter; 

public class Faturas implements Serializable  {
    /** Número Fiscal do Emitente */
    private long nifEmit;
    /** Designação do Emitente */
    private String designacao;
    /** Data da Despesa */ 
    private LocalDate data;
    /** Número Fiscal do Cliente */
    private long nifCliente;
    /** Descrição da Despesa */ 
    private String descricao; 
    /** Natureza da Despesa */ 
    private String atividadeEcon;
    /** Valor da Despesa */ 
    private double valor;
    /** ID da Fatura */
    private int id;
    /** Tipo de Despesa */
    private Despesas despesa;
    /** Estado da Fatura. */
    private String estado;
    /** Alteração Rastreio*/
    private boolean alterada;
    
    static final long serialVersionUID = 21L;
    
    /**
     * Construtor por omissão
     * 
     */
    public Faturas (){
        this.nifEmit = 0;
        this.designacao = "";
        this.data = LocalDate.now();
        this.nifCliente = 0; 
        this.descricao = ""; 
        this.atividadeEcon = ""; 
        this.valor = 0;
        this.id = 0;
        this.despesa = null;
        this.estado = "";
        this.alterada = false;
    }
    
    /**
     * Construtor parametrizado
     * @param Numero fiscal do emitente - nifEmit
     * @param Designação do emitente - designacao
     * @param data da despesa - data
     * @param numero fiscal do cliente - nifCliente
     * @param descrição da despesa - descricao
     * @param atividade económica a que diz respeito a despesa - atividadeEcon
     * @param despesas - despesa
     * @param valor da despesa - valor
     * @param id da despesa - id
     */
    public Faturas(long nifEmit, String designacao, LocalDate data, long nifCliente, String descricao, String atividadeEcon, double valor, int id, Despesas despesa, String estado, boolean alterada){
        this.nifEmit = nifEmit;
        this.designacao = designacao;
        this.data = data;
        this.nifCliente = nifCliente; 
        this.descricao = descricao; 
        this.atividadeEcon = atividadeEcon; 
        this.valor = valor;
        this.id = id;
        this.despesa = despesa;
        this.estado = estado;
        this.alterada = alterada;
    }
    
    /**
     * Construtor cópia
     * é dada uma despesa
     * @param despesa 
     * 
     */
    public Faturas(Faturas f){
        this.nifEmit = f.getNifEmit();
        this.designacao = f.getDesignacao();
        this.data = f.getData();
        this.nifCliente = f.getNifCliente(); 
        this.descricao = f.getDescricao(); 
        this.atividadeEcon = f.getAtividadeEcon(); 
        this.valor = f.getValor();
        this.id = f.getID();
        this.despesa = f.getDespesa();
        this.estado = f.getEstado();
        this.alterada = f.getAlterada();
    }
    
    /**
     * Função que devolve o número fiscal do emitente
     * @return nifEmitente
     */
    public long getNifEmit(){
        return this.nifEmit;
    }
    
    /**
     * Função que devolve a designação do emitente
     * @return designacao
     */
    public String getDesignacao(){
        return this.designacao;
    }
    
    /**
     * Função que devolve a data da despesa
     * @return data
     */
    public LocalDate getData(){
        return this.data;
    }
    
    /**
     * Função que devolve o número fiscal do cliente
     * @return nifCliente
     */
    public Long getNifCliente(){
        return this.nifCliente;
    }
    
    /**
     * Função que devolve a descrição da despesa
     * @return descricao
     */
    public String getDescricao(){
        return this.descricao;
    }
    
    /**
     * Função que devolve a natureza da despesa
     * @return atividadeEcon
     */
    public String getAtividadeEcon(){
        return this.atividadeEcon;
    }
    
    /**
     * Função que devolve o valor da despesa
     * @return valor
     */
    public double getValor(){
        return this.valor;
    }
    
    /**
     * Função que devolve o id da fatura
     * @return id
     */
    public int getID(){
        return this.id;
    }
   
    /**
     * Função que devolve a despesa
     * @return despesa
     */
    public Despesas getDespesa(){
        return this.despesa;
    }
    
    /**
     * Função que devolve o estado da despesa
     * @return estado
     */
    public String getEstado(){
        return this.estado;
    }
    
    /**
     * Função que devolve a alteração da despesa
     * @return alterada
     */
    public boolean getAlterada(){
        return this.alterada;
    }
    
    /**
     * Define o valor do número fiscal do emitente
     * @param novoNifEmit 
     */
    public void setNifEmit(long novoNifEmit) {
        this.nifEmit = novoNifEmit;
    }
    
    /**
     * Define a designação do emitente
     * @param novoDesignacao 
     */
    public void setDesignacao(String novoDesignacao) {
        this.designacao = novoDesignacao;
    }
    
    /**
     * Define a data da despesa
     * @param novoData 
     */
    public void setData(LocalDate novoData) {
        this.data = novoData;
    }
    
    /**
     * Define o número fiscal do cliente
     * @param novoNifCliente 
     */
    public void setNifCliente(long novoNifCliente) {
        this.nifCliente = novoNifCliente;
    }
    
    /**
     * Define a descrição da despesa
     * @param novoDescricao 
     */
    public void setDescricao(String novoDescricao) {
        this.descricao = novoDescricao;
    }
    
    /**
     * Define a natureza da despesa
     * @param novoAtividadeEcon
     */
    public void setAtividadeEcon(String novoAtividadeEcon) {
        this.atividadeEcon = novoAtividadeEcon;
    }
    
    /**
     * Define o valor da despesa
     * @param novoValor 
     */
    public void setValor(double novoValor) {
        this.valor = novoValor;
    }
    
    /**
     * Define o id da fatura
     * @param novoID
     */
    public void setID(int novoID){
        this.id = novoID;
    }
    
    /**
     * Define a despesa
     * @param novoDespesa 
     */
    public void setDespesa(Despesas novoDespesa){
        this.despesa = novoDespesa;
    }
    
    /**
     * Define o estado da despesa
     * @param novoEstado 
     */
    public void setEstado(String novoEstado) {
        this.estado = novoEstado;
    }
    
    /**
     * Define a alteração da despesa
     * @param novoAlterada 
     */
    public void setAlterada(boolean novoAlterada) {
        this.alterada = novoAlterada;
    }
    
    /**
        * Cria uma Despesa igual
        * @return uma nova despesa idêntica
        */ 
       public Faturas clone(){
           return new Faturas(this);
        }
           
    /**
     * Verifica se duas Despesas são iguais
     * @return True/False
     */ 
    public boolean equals(Object obj){
        if (obj == this) return true; 
        if (obj == null || obj.getClass()!=this.getClass()) return false;
        Faturas f = (Faturas) obj; 
        return this.nifEmit == f.getNifEmit() && 
               this.designacao.equals(f.getDesignacao()) && 
               this.data == f.getData() && 
               this.nifCliente == f.getNifCliente() &&
               this.descricao.equals(f.getDescricao()) && 
               this.atividadeEcon.equals(f.getAtividadeEcon()) && 
               this.valor == f.getValor() &&
               this.id == f.getID() &&
               this.despesa.equals(f.getDespesa()) &&
               this.estado == f.getEstado() && 
               this.alterada == f.getAlterada();
    }
    
    /**
     * Devolve uma representação textual do objeto
     * @return 
     */
    public String toString(){
     StringBuilder s = new StringBuilder(); 
     s.append("Fatura:") 
             .append("\nID = ").append(id)
             .append("\nNúmero Fiscal Emitente = ").append(nifEmit)
             .append("\nDesignacao do Emitente = ").append(designacao)
             .append("\nData da Despesa = ").append(data)
             .append("\nNúmero Fiscal do Cliente = ").append(nifCliente)
             .append("\nDescrição da Despesa = ").append(descricao)
             .append("\nNatureza da Despesa = ").append(atividadeEcon)
             .append("\nValor da Despesa = ").append(valor)
             .append("\nEstado da Despesa = ").append(estado)
             .append("\nAlterada ? ").append(alterada)
             .append("\n");
     return s.toString();
    }    

    /**
     * Função que associa as faturas ao tipo de despesa
     * @param fatura - f 
     * @return Despesa 
     */
    public Despesas associaDesp(Faturas f){
        if (f.getAtividadeEcon().equals("Educacao")) return new Educacao();
        if (f.getAtividadeEcon().equals("Manutencao")) return new Manutencao();
        if (f.getAtividadeEcon().equals("Saloes")) return new Saloes();
        if (f.getAtividadeEcon().equals("Imoveis")) return new Imoveis();
        if (f.getAtividadeEcon().equals("Veterinario")) return new Veterinario();
        if (f.getAtividadeEcon().equals("DespesasGerais")) return new DespesasGerais();
        if (f.getAtividadeEcon().equals("Saude")) return new Saude();
        if (f.getAtividadeEcon().equals("Lares")) return new Lares();
        if (f.getAtividadeEcon().equals("Alojamentos")) return new Alojamentos();
        if (f.getAtividadeEcon().equals("Outros")) return new Outros();
        if (f.getAtividadeEcon().equals("Transportes")) return new Transportes();
        else return new Outros();
    }
}
  