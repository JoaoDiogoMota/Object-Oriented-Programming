import java.applet.Applet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter; 
import java.io.FileNotFoundException;


public class mainMenu extends JFrame{ 
    private JButton button1,button2,button3,button4;
    private JLabel label;
    private JTextArea textArea; 
    
    public mainMenu(Utilizadores b){
        setLayout(new FlowLayout());
        
        button1 = new JButton ("Entrar");
        add(button1);
        
        button2 = new JButton ("Registar");
        add(button2);
        
        button4 = new JButton("Definições Administrativas"); 
        add(button4);
        
        button3 = new JButton ("Sair");
        add(button3);

        event2 e = new event2(b);  
        button2.addActionListener(e);
        event1 r = new event1(b); 
        button1.addActionListener(r);
        event3 s = new event3(b);
        button3.addActionListener(s);
        event4 a = new event4(b);
        button4.addActionListener(a);
        
        setVisible(true); 
    }
    
    public class event2 implements ActionListener{
           private Utilizadores u; 
           event2 (Utilizadores u2){
               super();
               this.u = u2; 
           }
            
           public void actionPerformed(ActionEvent e){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               
               Registar c = new Registar(u); 
               c.regista(u);
            }
    }
        
    public class event1 implements ActionListener{
           private Utilizadores u; 
           event1 (Utilizadores u2){
               super();
               this.u = u2; 
           }
            
           public void actionPerformed(ActionEvent r){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               
               IniciarS i = new IniciarS(u); 
               i.inicia(u);
            }
    }
    
    public class event3 implements ActionListener{
           private Utilizadores u; 
           event3 (Utilizadores u2){
               super();
               this.u = u2; 
           }
            
           public void actionPerformed(ActionEvent s){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               System.exit(0);
            }
    }
     
    public class event4 implements ActionListener{
           private Utilizadores u; 
           event4 (Utilizadores u2){
               super();
               this.u = u2; 
           }
            
           public void actionPerformed(ActionEvent s){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               
               IniciarS i = new IniciarS(u); 
               i.inicia(u);
            }
    }
    
    public static void run (Utilizadores b){
        mainMenu m = new mainMenu(b);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setSize(600,100);

        m.setVisible(true);
        m.setTitle("Acesso às Faturas");
    }
    
    public static void main (String args[]){
      Utilizadores b = new Utilizadores();
        try{
            b = b.load();
        }catch (FileNotFoundException e){
            try{
                b.save();
            } 
            catch(IOException e1){
                e.printStackTrace();
        }
    } catch (IOException e){
        e.printStackTrace();
    } catch (ClassNotFoundException e){
        e.printStackTrace();
    }
    run(b);
    }
}
