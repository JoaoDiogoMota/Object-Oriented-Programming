import java.util.Comparator;
/**
 * Write a description of class ComparatorNumQuartos here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ComparatorNumQuartos implements Comparator<Hotel>{

   public int compare(Hotel h1,Hotel h2){
 int n1=h1.getNumQuartos();
 int n2=h2.getNumQuartos();
 int r=0;
 if(n1>n2) r=-1;
 else{
     if (n1<n2) r=1;
    }
    return r;
    //ou return h1.getNumQuartos()-h2.getNumqQuartos();

}


}
