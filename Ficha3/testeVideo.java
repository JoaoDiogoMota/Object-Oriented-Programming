
/**
 * Write a description of class testeVideo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class testeVideo
{
   public static void main(String[] args){
    Video v = new Video();
    v.thumbsUp();
    v.insereComentario("Teste");
    System.out.println(v.toString());
    }
}
