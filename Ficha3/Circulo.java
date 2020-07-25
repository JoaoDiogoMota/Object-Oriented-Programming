import java.lang.Object;
/**
 * Write a description of class Circulo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Circulo
{
private double x;
private double y;
private double raio;

public Circulo(){
 this.x=0;
 this.y=0;
 this.raio=1;
}
public Circulo(int cx, int cy,int craio){
    this.x=cx;
    this.y=cy;
    this.raio=craio;
}

public Circulo(Circulo circ){
    this.x=circ.getX();
    this.y=circ.getY();
    this.raio=circ.getRaio();
}
public double getX(){
 return this.x;   
}

public double getY(){
 return this.y;   
}

public double getRaio(){
 return this.raio;   
}

public void setX(double newX){
    this.x=newX;
}

public void setY(double newY){
    this.x=newY;
}

public void setRaio(double newRaio){
    this.x=newRaio;
}
public Circulo clone(){
 return new Circulo(this);   
}

public String toString(){
 return "X="+x+";Y="+y+";r="+raio;   
}

public boolean equals(Circulo c){
 if(c==this) return true;
 if((c==null) || c.getClass()!=this.getClass()) return false;
 Circulo nc =(Circulo) c;
 return nc.getX() == this.x && nc.getY() == this.y && nc.getRaio()==this.raio;
}
public void alteraCentro(double novox, double novoy){
 this.x=novox; //ou setX(novoX)
 this.y=novoy; //ou setY(novoY)
}
public double calculaArea(){
     return Math.PI*(this.raio*this.raio);
    }
public double calculaPerimetro(){
    return 2*Math.PI*raio;
}



    
}
