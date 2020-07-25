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

public class Registar extends JFrame
{
    public Registar(Utilizadores b){ 
        setLayout(new FlowLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton buttonEmp = new JButton ("Registar como Empresa");
        add(buttonEmp);
        JButton buttonInd = new JButton ("Registar como Individual");
        add(buttonInd);
        JButton buttonSair = new JButton ("Sair");
        add(buttonSair);
        
        buttonEmpE be = new buttonEmpE(b); // b = base de dados 
        buttonEmp.addActionListener(be);
        
        buttonIndE bi = new buttonIndE(b); 
        buttonInd.addActionListener(bi);
        
        buttonSaiE e = new buttonSaiE(b);
        buttonSair.addActionListener(e);
        
        setVisible(true); 
    }
    
    public class buttonEmpE implements ActionListener{
           private Utilizadores u; 
           buttonEmpE (Utilizadores u1){
               super();
               this.u = u1; 
           }
            
           public void actionPerformed(ActionEvent be){
               RegistaEmp e = new RegistaEmp(u); 
               e.registaemp(u);
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
           }
    }
    
    public class buttonIndE implements ActionListener{
           private Utilizadores u; 
           buttonIndE (Utilizadores u2){
               super();
               this.u = u2; 
           }
            
           public void actionPerformed(ActionEvent bi){
               RegistaInd i = new RegistaInd(u); 
               i.registaind(u);
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
           }
    }
    
    public class buttonSaiE implements ActionListener{
        private Utilizadores u; 
        buttonSaiE (Utilizadores u3){
            super(); 
            this.u = u3;
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
    
    public static void regista(Utilizadores u){
        Registar r = new Registar(u);
        r.setTitle("Opções de Registo");
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        r.setSize(new Dimension(500,100)); 
        r.setVisible(true);
    }
}
