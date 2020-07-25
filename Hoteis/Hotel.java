
/**
 * Write a description of class Hotel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Hotel implements Comparable<Hotel> //abstact devido à parte 2 && comparable devido ao compareTo
{
private String id;
private String nome;
private String localidade;
private int categoria;
private int numQuartos;
private double preco;

public Hotel(){
 this.id="";
 this.nome="";
 this.localidade="";
 this.categoria=0;
 this.numQuartos=0;
 this.preco=0;
}

public Hotel(String id, String nome, String localidade,int categoria, int numQuartos,double preco){
 this.id=id;
 this.nome=nome;
 this.localidade = localidade;
 this.categoria=categoria;
 this.numQuartos=numQuartos;
 this.preco=preco;
}

public Hotel(Hotel h){
    this.id=getId();
    this.nome=getNome();
    this.localidade=getLocalidade();
    this.categoria=getCategoria();
    this.numQuartos=getNumQuartos();
    this.preco=getPreco();
}

public String getId(){
    return this.id;
}

public String getNome(){
 return this.nome;
}

public String getLocalidade(){
 return this.localidade;   
}

public int getCategoria(){
 return this.categoria;   
}

public int getNumQuartos(){
 return this.numQuartos;   
}

public double getPreco(){
 return this.preco;   
}

public void setId(String newId){
    this.id=newId;
}

public void setNome(String newNome){
    this.nome=newNome;   
}

public void setLocalidade(String newLocalidade){
this.localidade=newLocalidade;   
}

public void setCategoria(int newCategoria){
 this.categoria=newCategoria;   
}

public void setNumQuartos(int newNumQuartos){
this.numQuartos=newNumQuartos;    
}

public void setPreco(double newPreco)throws DadosInvalidosException{
    if(preco<0){
     throw new DadosInvalidosException("O preço tem de ser positivo");   
    }
    this.preco=newPreco;   
}

public abstract Hotel clone(); // abstract devido à parte 2
/* return new Hotel(this);
}
*/

public boolean equals(Object o){
    if(o==this) return true;
    if(o==null || o.getClass() != this.getClass()) return false;
    Hotel h = (Hotel)o;
    return id.equals(h.getId()) && nome.equals(h.getNome()) && localidade.equals(h.getLocalidade())
                    && categoria == h.getCategoria() && numQuartos == h.getNumQuartos() &&  preco==h.getPreco(); 
    
}

public abstract double precoNoite();

@Override //para me certificar que nao compila caso nao esteja implementado o comparable em cima
public int compareTo(Hotel hotel){
    return this.nome.compareTo(hotel.getNome()); //compareTo compara strings
}







}
