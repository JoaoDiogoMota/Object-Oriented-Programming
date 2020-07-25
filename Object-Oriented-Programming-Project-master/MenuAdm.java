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

public class MenuAdm extends JFrame
{
    private JButton visualizar, sair; 
    
    public MenuAdm(Utilizadores b,long nif){
        setLayout(new FlowLayout());
        
        visualizar = new JButton ("Estatística");
        add(visualizar);
        sair = new JButton ("Sair");
        add(sair);
        
        visualizarA v = new visualizarA(b);
        visualizar.addActionListener(v);
        
        sairA s = new sairA(b); 
        sair.addActionListener(s);
        
        setVisible(true);
    }
    
    public class visualizarA implements ActionListener{
           private Utilizadores u; 
           private long nif;
           
           visualizarA (Utilizadores u1){
               super();
               this.u = u1; 
           }
            
           public void actionPerformed(ActionEvent v){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               
               MenuAdm e = new MenuAdm(u,nif); 
               RelacaoEmp r = new RelacaoEmp(u,nif);
           }
    }
    
    
    public class sairA implements ActionListener{
           private Utilizadores u; 
           private long nif;
           
           sairA (Utilizadores u1){
               super();
               this.u = u1; 
           }
            
           public void actionPerformed(ActionEvent s){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               
               MenuAdm e = new MenuAdm(u,nif); 
               System.exit(0);
           }
    }
    
    public static void menuadm(Utilizadores u, long nif){
        MenuAdm ma = new MenuAdm(u,nif);
        ma.setTitle("Menu Administrador");
        ma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ma.setSize(new Dimension(1000,100)); 
        ma.setVisible(true);
    }
} 
