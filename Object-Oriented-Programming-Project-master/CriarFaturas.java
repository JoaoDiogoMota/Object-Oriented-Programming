import java.applet.Applet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Date;
import java.time.LocalDate;
import java.io.IOException;
import java.util.*;


public class CriarFaturas extends JFrame
{
    private JLabel numerofiscemt, designacao, dataD,dataM,dataA,numerofisccl, natureza,id, descricao, valor, nota;
    private JTextField NumeroFiscEmt, Designacao,DataD,DataM,DataA,NumeroFiscCl,Natureza,Id,Descricao, Valor;
    private JButton confirmar,cancelar; 
    
    public CriarFaturas(Utilizadores b){
        setLayout(new FlowLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        id = new JLabel ("ID"); 
        add(id); 
        Id = new JTextField(9);
        add(Id);
        
        numerofiscemt = new JLabel ("NIF Emitente");
        add(numerofiscemt);
        NumeroFiscEmt = new JTextField(9);
        add(NumeroFiscEmt);
        
        designacao = new JLabel ("Designação do Emitente"); 
        add(designacao); 
        Designacao = new JTextField(15); 
        add(Designacao);
        
        dataD = new JLabel ("Data de Criação da Despesa"); 
        add(dataD); 
        DataD = new JTextField(2);
        add(DataD);
        DataM = new JTextField(2);
        add(DataM);
        DataA = new JTextField(4);
        add(DataA); 
        
        numerofisccl = new JLabel ("Número Fiscal do Cliente"); 
        add(numerofisccl); 
        NumeroFiscCl = new JTextField(9); 
        add(NumeroFiscCl);
       
        natureza = new JLabel ("Natureza da Despesa"); 
        add(natureza); 
        Natureza = new JTextField(15); 
        add(Natureza);
        
        descricao = new JLabel ("Descricao da Despesa"); 
        add(descricao); 
        Descricao = new JTextField(15); 
        add(Descricao);
        
        valor = new JLabel ("Valor da Despesa");
        add(valor); 
        Valor = new JTextField(10); 
        add(Valor);
        
        nota = new JLabel ("A Natureza da Despesa pode ser do tipo Educacao | Saude | Manutencao | Alojamento | Saloes | Veterinario | DespesasGerais | PassesTP | Imoveis | Lares | Outros");
        add(nota);
        
        confirmar = new JButton ("Confirmar");
        add(confirmar);
        cancelar = new JButton ("Cancelar");
        add(cancelar);
        
        event c = new event(b);
        cancelar.addActionListener(c);
        
        criafatura a = new criafatura(b); 
        confirmar.addActionListener(a);
    }
    
    public class event implements ActionListener{
       private Utilizadores u; 
       
       event (Utilizadores u2){
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
    
    public class criafatura implements ActionListener{
        private Utilizadores u; 
        private long nif;
        
        criafatura (Utilizadores u2){
               super();
               this.u = u2; 
        }
        
        public void actionPerformed(ActionEvent e){
            int idF = Integer.parseInt(Id.getText());
            long numerofiscemtF = Long.parseLong(NumeroFiscEmt.getText());
            String designacaoF = Designacao.getText();
            LocalDate dataF = LocalDate.of((Integer.parseInt(DataA.getText())),(Integer.parseInt(DataM.getText())),(Integer.parseInt(DataD.getText())));
            long numerofiscclF = Long.parseLong(NumeroFiscCl.getText());
            String naturezaF = Natureza.getText();
            String descricaoF = Descricao.getText();
            double valorF = Double.parseDouble(Valor.getText());
            String estadoF = "Pendente";
            Despesas despesaF = new Outros();
            boolean alteradaF = false;
            
            if(naturezaF.contains("Educacao")){
                despesaF = new Educacao();
            }
            if(naturezaF.contains("Saude")){
                despesaF = new Saude();
            }
            if(naturezaF.contains("Outros")){
                despesaF = new Outros();
            }
            if(naturezaF.contains("Veterinario")){
                despesaF = new Veterinario();
            }
            if(naturezaF.contains("Imoveis")){
                despesaF = new Imoveis();
            }
            if(naturezaF.contains("Lares")){
                despesaF = new Lares();
            }
            if(naturezaF.contains("Manutencao")){
                despesaF = new Manutencao();
            }
            if(naturezaF.contains("DespesasGerais")){
                despesaF = new DespesasGerais();
            }
            if(naturezaF.contains("Saloes")){
                despesaF = new Saloes();
            }
            if(naturezaF.contains("Alojamento")){
                despesaF = new Alojamentos();
            }
            if(naturezaF.contains("PassesTP")){
                despesaF = new Transportes();
            }
            
            if (naturezaF.equals("Educacao") || naturezaF.equals("Outros") || naturezaF.equals("Saude") || naturezaF.equals("Veterinario") || 
            naturezaF.equals("Imoveis")|| naturezaF.equals("Lares") || naturezaF.equals("Manutencao") || naturezaF.equals("DespesasGerais") 
            || naturezaF.equals("Saloes") || naturezaF.equals("Alojamento") || naturezaF.equals("PassesTP")) estadoF = naturezaF;
            else estadoF = "Pendente";
            
            Faturas fatura = new Faturas(numerofiscemtF,designacaoF,dataF,numerofiscclF,descricaoF,naturezaF,valorF,idF,despesaF,estadoF, alteradaF);

            u.atribuiFatura(u,fatura);
            try{
                   u.save();
               }
               catch(IOException e1){
                   e1.printStackTrace();
               }
            dispose();
        }
    }
    
    public static void adicionafaturas(Utilizadores u){
        CriarFaturas r = new CriarFaturas(u);
        r.setTitle("Adição de Faturas");
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        r.setSize(new Dimension(1050,400)); 
        r.setVisible(true);
    }
    
}