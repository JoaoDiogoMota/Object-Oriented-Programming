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
import java.time.LocalDate;


public class MenuEmp extends JFrame
{
    private JButton buttonFaturas,buttonAdiciona,buttonTotal, buttonCancelar;  
    
    public MenuEmp(Utilizadores b, long nif){
        setLayout(new FlowLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        buttonFaturas = new JButton ("Consultar Faturas");
        add(buttonFaturas);
        buttonAdiciona = new JButton ("Adicionar Fatura"); 
        add(buttonAdiciona);
        buttonTotal = new JButton ("Estatística");
        add(buttonTotal);
        buttonCancelar = new JButton ("Cancelar");
        add(buttonCancelar);
        
        buttonFaturasE cf = new buttonFaturasE(b, nif); 
        buttonFaturas.addActionListener(cf);
        
        buttonTotalE bt = new buttonTotalE(b,nif); 
        buttonTotal.addActionListener(bt);
        
        buttonAdicionaE af = new buttonAdicionaE(b);
        buttonAdiciona.addActionListener(af);
        
        buttonCancelaE bc = new buttonCancelaE(b); 
        buttonCancelar.addActionListener(bc);
        
        setVisible(true);
    }
    
    public class buttonFaturasE implements ActionListener{
           private Utilizadores u; 
           private long nif;
           
           buttonFaturasE (Utilizadores u1, long nif1){
               super();
               this.u = u1; 
               this.nif = nif1;
           }
            
           public void actionPerformed(ActionEvent cf){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               
               MenuEmp b = new MenuEmp(u,nif); 
               ConsultarFaturas c = new ConsultarFaturas(u,nif);
               c.consultarfaturas(u,nif);
           }
    }
    
    public class buttonAdicionaE implements ActionListener{
           private Utilizadores u; 
           
            buttonAdicionaE (Utilizadores u1){
               super();
               this.u = u1; 
           }
            
           public void actionPerformed(ActionEvent cf){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               
               CriarFaturas c = new CriarFaturas(u); 
               c.adicionafaturas(u);
           }
    }
    
    public class buttonTotalE implements ActionListener{
           private Utilizadores u; 
           private long nif;
           
           buttonTotalE (Utilizadores u1, long nif1){
               super();
               this.u = u1; 
               this.nif = nif1;
           }
            
           public void actionPerformed(ActionEvent cf){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               
               MenuEmp b = new MenuEmp(u,nif); 
               TotalF c = new TotalF(u,nif);
               
           }
    }
    
    public class actionE implements ActionListener{
        private Utilizadores u; 
        private long nif;
           
        actionE (Utilizadores u1, long nif1){
           super();
           this.u = u1; 
           this.nif = nif1;
        }
            
        public void actionPerformed(ActionEvent ae){
          try{
              u.save();
          }
          catch(IOException e1) {
              e1.printStackTrace();
          }
          
          System.exit(0);
        }
    }
          
    public class exitActionE implements ActionListener{
        private Utilizadores u; 
        private long nif;
           
        exitActionE (Utilizadores u1, long nif1){
           super();
           this.u = u1; 
           this.nif = nif1;
        }
            
        public void actionPerformed(ActionEvent ea){
          System.exit(0);
        }
    }
    
    public class buttonCancelaE implements ActionListener{
       private Utilizadores u; 
       
       buttonCancelaE (Utilizadores u2){
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
            dispose();   
        }
    }
    
    public static void menuemp(Utilizadores u,long nif){
        MenuEmp m = new MenuEmp(u,nif);
        m.setTitle("Menu de Contribuinte Empresarial");
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setSize(new Dimension(1000,400)); 
        m.setVisible(true);
    }
}


