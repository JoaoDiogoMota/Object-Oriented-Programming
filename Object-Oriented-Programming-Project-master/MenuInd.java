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

public class MenuInd extends JFrame 
{
    private JButton buttonFaturas, buttonSair, buttonPendentes;
    
    public MenuInd(Utilizadores b, long nif){
        setLayout(new FlowLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        buttonFaturas = new JButton ("Consultar Faturas");
        add(buttonFaturas);
        
        buttonSair = new JButton ("Cancelar");
        add(buttonSair);
 
        buttonFaturasI bf = new buttonFaturasI(b, nif); 
        buttonFaturas.addActionListener(bf);
        
        buttonSairI bs = new buttonSairI(b); 
        buttonSair.addActionListener(bs);
        
        setVisible(true);
    }
    
    public class buttonFaturasI implements ActionListener{
           private Utilizadores u; 
           private long nif;
           
           buttonFaturasI (Utilizadores u1, long nif1){
               super();
               this.u = u1; 
               this.nif = nif1;
           }
            
           public void actionPerformed(ActionEvent bf){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               
               MenuInd e = new MenuInd(u, nif);
               ConsultarFaturas b = new ConsultarFaturas(u,nif);
               b.consultarfaturas(u,nif);
           }
    }
    
    public class buttonSairI implements ActionListener{
           private Utilizadores u; 
           
           buttonSairI (Utilizadores u1){
               super();
               this.u = u1; 
           }
            
           public void actionPerformed(ActionEvent ae){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }

               System.exit(0);
            }
    }  
    
    public static void menuind(Utilizadores u, long nif){
        MenuInd m = new MenuInd(u, nif);
        m.setTitle("Menu de Contribuinte Individual");
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setSize(new Dimension(1000,100)); 
        m.setVisible(true);
    }
}
