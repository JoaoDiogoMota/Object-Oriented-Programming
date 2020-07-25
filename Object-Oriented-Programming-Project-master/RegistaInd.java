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

public class RegistaInd extends JFrame
{
    private JLabel nif,email,nome,morada,rendimento,filhos, password,numeroAgreg,nfagreg, coef, atividades, nota,obs,obs2,message;
    private JTextField NIF,Email,Nome,Morada,Rendimento,Filhos, Password,Atividades,NumeroAgreg,NFAgreg,Coef;
    private JButton confirmar,cancelar; 
    
    public RegistaInd(Utilizadores b){
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
        
        rendimento = new JLabel ("Rendimento"); 
        add(rendimento); 
        Rendimento = new JTextField(15); 
        add(Rendimento);
        
        password = new JLabel ("Password"); 
        add(password); 
        Password = new JTextField(15); 
        add(Password);
        
        filhos = new JLabel ("Número de Filhos"); 
        add(filhos); 
        Filhos = new JTextField(2); 
        add(Filhos);
        
        numeroAgreg = new JLabel ("Numero do Agregado Familiar");
        add(numeroAgreg); 
        NumeroAgreg = new JTextField(2); 
        add(NumeroAgreg);
        
        nfagreg = new JLabel ("Numeros Fiscais do Agregado"); 
        add(nfagreg); 
        NFAgreg = new JTextField(45); 
        add(NFAgreg);
        
        coef = new JLabel ("Coeficiente Fiscal"); 
        add(coef); 
        Coef = new JTextField(2); 
        add(Coef);
        
        atividades = new JLabel ("Código das Atividades"); 
        add(atividades); 
        Atividades = new JTextField(45); 
        add(Atividades);
        
        nota = new JLabel ("NOTA : No caso do seu estado civil seja 'Casado', o valor do Coeficiente Fiscal é 2. Caso contrário, este valor é 1.");
        add(nota);
        
        obs = new JLabel ("OBSERVAÇÃO : Manutencao-0110 | Saude-0011 | Lares-0001 | Educacao-0100 | Veterinario-0101 | DespesasGerais-1010 | Transportes-0111 ");
        add(obs);
        
        obs2 = new JLabel("Alojamento-0010 | Outros-1001 | Saloes-0000 | Imoveis-1000");
        add(obs2);
        
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
        
        registainds a = new registainds (b); 
        confirmar.addActionListener(a);
    }
    
    public class registainds implements ActionListener{
        private Utilizadores u; 
        registainds (Utilizadores u2){
               super();
               this.u = u2; 
           }
        
        public void actionPerformed(ActionEvent e){
             long nifI = Long.parseLong(NIF.getText());
             String emailI = Email.getText();
             String nomeI = Nome.getText();
             String moradaI = Morada.getText();
             int rendimentoI = Integer.parseInt(Rendimento.getText());
             String passwordI = Password.getText();
             int filhosI = Integer.parseInt(Filhos.getText());
             int agregadoI = Integer.parseInt(NumeroAgreg.getText());
             List<String> numNifAgregI = new ArrayList<>();
             numNifAgregI.add(NFAgreg.getText());
             float coefFiscalI = Float.parseFloat(Coef.getText());
             List<String> actEconI = new ArrayList<>();
             actEconI.add(Atividades.getText());
             List<Faturas> faturasI = new ArrayList<>();
             Individuais individual = new Individuais(nifI,emailI,nomeI,moradaI,passwordI,faturasI,agregadoI,rendimentoI,filhosI,numNifAgregI,coefFiscalI,actEconI);
             
             if(u.getUtilizadores().containsKey(nifI)){
                 message.setText("Utilizador já registado.");
             }
             else{
             u.regista(individual);
               
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
    
    public static void registaind(Utilizadores u){
        RegistaInd r = new RegistaInd(u);
        r.setTitle("Registar Individual");
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        r.setSize(new Dimension(1200,600)); 
        r.setVisible(true);
    }
}
