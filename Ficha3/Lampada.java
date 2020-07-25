
/**
 * Write a description of class Lampada here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lampada
{
  private int estado; //desligado-0, ligada-1 , eco-2
  private double consumo;
  
  
  public Lampada(){
     this.estado=0;
     this.consumo=0;
    }
    
  public Lampada(int est, double con){
      this.estado = est;
      this.consumo = con;
      
    }
   
  public Lampada(Lampada lamp){
      this.estado=lamp.getEstado();
      this.consumo=lamp.getConsumo();
    }
    
  public int getEstado(){
     return this.estado;   
    }
    
  public double getConsumo(){
     return this.consumo; 
    }
    
  public void setEstado(int newEstado){
      this.estado=newEstado;
    }
    
  public void setConsumo(double newConsumo){
     this.consumo=newConsumo; 
    }
  
    public Lampada clone(){
        return new Lampada(this);
    }
 
  public String toString(){
      return "Estado=" +estado+";Consumo="+consumo;
    }
  public boolean equals(Object o ){
      if (this==o) return true;
      if ((o==null) || (this.getClass()!=o.getClass())) return false;
      Lampada l = (Lampada)o;
      return (this.estado==l.getEstado() && this.consumo==l.getConsumo());
    }
  public void lampON(){
     this.estado=1;
     this.consumo=0;
    }
  public void lampOFF(){
        this.estado=0;
        this.consumo=0;
        }
  public void lampECO(){
     this.estado=2;
     this.consumo=0;
    }
  public double totalConsumo(){
    return 0;
    }
}
