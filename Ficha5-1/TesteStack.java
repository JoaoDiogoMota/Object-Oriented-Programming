
/**
 * Write a description of class TesteStack here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TesteStack
{
public static void main(String[] args){
 Stack s = new Stack();
 s.push("E1");
 System.out.println(s);
 System.out.println(s.length()==1);
 System.out.println(s.top().equals("E1"));
 s.pop();
 System.out.println(s.length()==0);
}
}
