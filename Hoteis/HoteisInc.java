import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.io.*;



/**
 * Write a description of class HoteisInc here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HoteisInc implements Serializable
{
 //Key:1,2,3 -> é o Id
 //Value: Hotel A, Hotel B, Hotel C
 //Map<Integer,Hotel> hoteis;
 private String nomeCadeia;
 private Map<String,Hotel> hoteis;
 private static Map<String,Comparator<Hotel>> comparators;
 
 
 static{ // Bloco de inicialização
     comparators = new HashMap<>();
     comparators.put("numQuartos",new ComparatorNumQuartos());
     comparators.put("numEstrelas",(a,b) -> a.getCategoria() -b.getCategoria());
    }
    
 public static Comparator getComparator(String nome){
     return HoteisInc.comparators.get(nome);
    }
    
 public HoteisInc(){
     this.nomeCadeia="";
     this.hoteis = new HashMap<>();
    }
    
 public HoteisInc(String nomeCadeia,  Map<String,Hotel> hoteis){
     this.nomeCadeia= nomeCadeia;
     this.setHoteis(hoteis);
     
  }
  
 public HoteisInc(HoteisInc HI){
     this.nomeCadeia=HI.getNomeCadeia();
     this.hoteis=HI.getHoteis();
    }
 public boolean existeHotel(String cod){
     return this.hoteis.containsKey(cod);
     
     
    }
    
 public int quantos(){
     return this.hoteis.size();
    }
    
 public int quantos(String loc){
   return (int)this.hoteis.values().stream()
                .filter(h->h.getLocalidade().equals(loc))
                .count();
 }
 
 public Hotel getHotel(String cod){
   return this.hoteis.get(cod).clone();  
   }
   
   //e-> fazer com put
   
 public Map<String,Hotel> getHoteis(){
     return this.hoteis;
    }
    
 public void setHoteis(Map<String,Hotel> novohoteis){
     this.hoteis=novohoteis;
    }
    
 public String getNomeCadeia(){
     return this.nomeCadeia;
    }
    
    public void exportarCSV(String nome)throws IOException{ //Porque é que então fazemos o try catch quando podemos só fazer isto?
    //escrita para ficheiros   
    PrintWriter pw = new PrintWriter(nome);
    for(Hotel h:hoteis.values()){
     pw.print(h.getNome());
     pw.print(",");
     pw.print(h.getCategoria()+","+h.getLocalidade()+",");
     //...
     pw.println(h.getPreco());
    }
    pw.flush(); //É só aqui que passa de memoria para texto
    pw.close();
    
    }
    //PARA ISTO FUNCIONAR (SAVE, CARREGAR) IMPLEMENTAR SERIALIZABLE NAS CLASSES QUE SE LIGAM AO HOTEISINC
    public static HoteisInc carregar(String nome) throws IOException, FileNotFoundException, ClassNotFoundException{ // nao percebi bem o porquê do static
        HoteisInc h = new HoteisInc();
        try{
        FileInputStream fis = new FileInputStream(nome);
        ObjectInputStream ois =  new ObjectInputStream(fis);
        h = (HoteisInc) ois.readObject();
        ois.close();
        fis.close();
    }
    catch(ClassNotFoundException e){
    }
    catch(FileNotFoundException e){   
    } 
    catch(IOException e){
    }
        return h;
        
        
    }
    
    public void save(String nome) throws FileNotFoundException,IOException{
     FileOutputStream fos = new FileOutputStream(nome);
     ObjectOutputStream oos = new ObjectOutputStream(fos);
     oos.writeObject(this);
     oos.close();
     fos.close();
    }
    
 public TreeSet<Hotel> ordenarHoteis(){
       TreeSet<Hotel> r = new TreeSet<>(); //usamos um treeSet porque se usassemos outra estrutura nao teriamos ordenação
       for(Hotel h: hoteis.values()){
         r.add(h.clone());  //sabe como ordenar pois hotel implementa o comparable 
        }
       return r;
 }
 
 
 
public TreeSet<Hotel> ordenarHoteis(Comparator<Hotel> c){
TreeSet<Hotel> r = new TreeSet <>(c);
for(Hotel h : hoteis.values()){
    r.add(h.clone());
    }
    return r;
}

public Iterator<Hotel> ordenaHoteis(String criterio){
 Comparator<Hotel> c = HoteisInc.getComparator(criterio);
 TreeSet<Hotel> res = ordenarHoteis(c);
 return res.iterator();
}

public void insereHotel(Hotel h){
 hoteis.put(h.getId(), h.clone());   
}


public List<CartaoHoteis> daoPontos(){
  List <CartaoHoteis> r = new ArrayList<>();
  for(Hotel h:hoteis.values()){
      if(h instanceof CartaoHoteis){
         r.add((CartaoHoteis)h.clone()); 
        }
    }
    return r;
}


}
