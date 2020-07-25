



/**
 * Write a description of class HotelPremium here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class HotelPremium extends Hotel implements CartaoHoteis
{
private double taxaLuxo;
private int pontos;

public HotelPremium(){
 this.taxaLuxo=0;   
}

public HotelPremium(double tl){
 this.taxaLuxo=tl;   
}

public HotelPremium(HotelPremium h){
 this.taxaLuxo= h.getTaxaLuxo();   
}

public double getTaxaLuxo(){
 return this.taxaLuxo;   
}

public void setPontos(int pontos){
      this.pontos = pontos;  
}
    
public int getPontos(){
        return this.pontos;
}


}
