import java.util.*;
/**
 * Write a description of class Stack here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stack{

  private List<String> elems; //tenho list e nao arrayList para fazer o codigo o mais generico possivel depois em baixo especifico
  
  public Stack(){
    this.elems= new ArrayList<>(); // aqui especifico o tipo da lista
    }
    
  public Stack(List<String> elems2){
      //this.stack=stack; Não posso fazer isto porque vou ter dis objetos a partilhar a mesma arrayList
      setElems(elems2);
    }
    
    
  public Stack(Stack stack){
    this.elems =  stack.getElems();
    }
    
    
  public void setElems(List<String> elems2){
       this.elems = new ArrayList<>();
      for(String s : elems2){
         this.elems.add(s); 
    }
    
}

public List<String> getElems(){
 //return this.elems;    Não posso pois permite manipular o estado
 List<String> res = new ArrayList<>();
 elems.stream().forEach(s -> res.add(s));
 return res;
}

public Stack clone(){
 return new Stack(this);   
}

public String toString(){
    return this.elems.toString();
}

public boolean equals(Object o){
if (o==this) { return true;}
if(o==null || o.getClass() != this.getClass()) { return false;}
Stack s = (Stack) o;
return this.elems.equals(s.getElems());
}
/**
 * Devolve um elemento no topo da Stack
 * Devolver uma string vazia se a stack estiver vazia
 */
public String top(){
 String s ="";
 if(!this.elems.isEmpty()){
     s=this.elems.get(0);
    }
 return this.elems.get(0);   
}

public void push(String s){
 this.elems.add(0,s);   
}

public void pop(){
    if(!this.elems.isEmpty()){
   this.elems.remove(0);
}
}


public boolean empty(){
 return this.elems.isEmpty();
}

public int length(){
 return this.elems.size();
}
}
