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

public class IniciarS extends JFrame 
{
    private JLabel password,nif, message;
    private JTextField NIF,Password;
    private JButton confirmar,cancelar;
    
    public IniciarS(Utilizadores u){
        setLayout(new FlowLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        nif = new JLabel ("NIF");
        add(nif);
        NIF = new JTextField(9);
        add(NIF);
        
        password = new JLabel ("Password");
        add(password);
        Password = new JTextField(9);
        add(Password);
        
        confirmar = new JButton ("Confirmar");
        confirmar.setSize(80, 30);
        confirmar.setLocation(300, 300);
        add(confirmar);
        cancelar = new JButton ("Cancelar");
        cancelar.setSize(80, 30);
        cancelar.setLocation(500, 300);
        add(cancelar);
        
        message = new JLabel(""); 
        add(message);
        
        event c = new event(u);
        cancelar.addActionListener(c);
       
        inicia a = new inicia(u); 
        confirmar.addActionListener(a);
    }

    public class event implements ActionListener{
           private Utilizadores u; 
           event (Utilizadores u2){
               super();
               this.u = u2; 
           }
            
           public void actionPerformed(ActionEvent c){
               try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               dispose();
            }
        }
        
    public class inicia implements ActionListener{
        private Utilizadores u;
        
        inicia (Utilizadores u2){
               super();
               this.u = u2; 
        }
        
        public void actionPerformed(ActionEvent a){
            long nif = Long.parseLong(NIF.getText());
            String password = Password.getText();
            long nifadm = 000;
            String passwordadm = "000";

            if((nif == nifadm) && (password.equals(passwordadm))){
                MenuAdm ma = new MenuAdm(u,nifadm);
                ma.menuadm(u,nifadm);
            }
         
            else {
                if (u.getUtilizadores().containsKey(nif)){
                    if (u.getUtilizadores().get(nif).getPassword().equals(password)){
                        if (u.getUtilizadores().get(nif) instanceof Individuais){
                            MenuInd i = new MenuInd(u, nif);
                            i.menuind(u, nif); }
                        else {
                             MenuEmp e = new MenuEmp(u,nif);
                             e.menuemp(u,nif);
                            }
                    }
                    else message.setText("A password não corresponde ao utilizador.");
                }
               
                else {
                    message.setText("Utilizador Inexistente.");
                }
                
            }
        }
    }

    public static void inicia(Utilizadores u){
        IniciarS i = new IniciarS(u);
        i.setTitle("Iniciar Sessão");
        i.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        i.setSize(new Dimension(1000,100)); 
        i.setVisible(true);
    }
}
