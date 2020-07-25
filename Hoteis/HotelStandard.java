
/**
 * Write a description of class HotelStandard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

//Nao temos de mexer em nada na classe HoteisInc(relativo à pergunta 2)
public  abstract class HotelStandard extends Hotel implements CartaoHoteis
{
    private boolean epocaAlta;
    int pontos;//True se for epoca alta
    
    public double getPreco(){
        double preco=super.getPreco();
        if(this.epocaAlta){
         preco+=20;   
        }
        return preco;
    }
    
    public HotelStandard(){
     this.epocaAlta=false;   
    }
    
    public HotelStandard( String codigo, String nome, String localidade, int categoria, int numQuartos, double preco,boolean epocaAlta){
        super(codigo,nome,localidade,categoria,numQuartos,preco);
        this.epocaAlta=epocaAlta;
    }
        
    
    public double precoNoite(){
     double v  = super.getPreco();
     if(epocaAlta){
         v+=20;
        }
        return v;
    }
    
    public abstract HotelStandard clone();
     /*return new HotelStandard(this);   
    }
    */
   
   public void setPontos(int pontos){
      this.pontos = pontos;  
    }
    
    public int getPontos(){
        return this.pontos;
    }
    
}
