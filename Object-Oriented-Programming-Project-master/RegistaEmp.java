import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;


public class RegistaEmp extends JFrame
{
    private JLabel nif,email,nome,morada,password,atividades,deducao,message;
    private JTextField NIF,Email,Nome,Morada,Password,Atividades,DeducaoFiscal;
    private JButton confirmar,cancelar; 
    
    public RegistaEmp(Utilizadores b){
        setLayout(new FlowLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        nif = new JLabel ("NIF");
        add(nif);
        NIF = new JTextField(9);
        add(NIF);
        
        email = new JLabel ("Email"); 
        add(email); 
        Email = new JTextField(15); 
        add(Email);
        
        nome = new JLabel ("Nome"); 
        add(nome); 
        Nome = new JTextField(45); 
        add(Nome); 
        
        morada = new JLabel ("Morada"); 
        add(morada); 
        Morada = new JTextField(45); 
        add(Morada);
        
        password = new JLabel ("Password"); 
        add(password); 
        Password = new JTextField(15); 
        add(Password);
        
        atividades = new JLabel ("Atividades Económicas associadas");
        add(atividades); 
        Atividades = new JTextField(15); 
        add(Atividades);
        
        deducao = new JLabel ("Dedução Fiscal"); 
        add(deducao); 
        DeducaoFiscal = new JTextField(15); 
        add(DeducaoFiscal);
        
        message = new JLabel ("");
        add(message);
        
        confirmar = new JButton ("Confirmar");
        confirmar.setSize(80, 30);
        confirmar.setLocation(300, 300);
        add(confirmar);
        cancelar = new JButton ("Cancelar");
        cancelar.setSize(80, 30);
        cancelar.setLocation(500, 300);
        add(cancelar);
        
        event c = new event(b);
        cancelar.addActionListener(c);
        
        registaemps a = new registaemps (b); 
        confirmar.addActionListener(a);
        
    }
    
    public class registaemps implements ActionListener{
        private Utilizadores u;
        
        registaemps (Utilizadores u2){
               super();
               this.u = u2; 
           }
        
        public void actionPerformed(ActionEvent e){
            long nifI = Long.parseLong(NIF.getText());
            String emailI = Email.getText();
            String nomeI = Nome.getText();
            String moradaI = Morada.getText();
            String passwordI = Password.getText();
            List<String> AtivEconI = new ArrayList<>();
            AtivEconI.add(Atividades.getText());
            float deducaoFiscalI = Float.parseFloat(DeducaoFiscal.getText());
            List<Faturas> faturasI = new ArrayList<>();
            Empresas empresa = new Empresas(nifI,emailI,nomeI,moradaI,passwordI,faturasI,AtivEconI,deducaoFiscalI);
            
            if(u.getUtilizadores().containsKey(nifI)){
                 message.setText("Utilizador já registado.");
            }
             else{
                 u.regista(empresa);
            try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
               dispose();
            }
        }
    }
    
    public class event implements ActionListener{
           private Utilizadores u; 
           event (Utilizadores u2){
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
               dispose(); 
            }
    }
    
    public static void registaemp(Utilizadores u){
        RegistaEmp r = new RegistaEmp(u);
        r.setTitle("Registar Empresa");
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        r.setSize(new Dimension(1000,400)); 
        r.setVisible(true);
    }
}