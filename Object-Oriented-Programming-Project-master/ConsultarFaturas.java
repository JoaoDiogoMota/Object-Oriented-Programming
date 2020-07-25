import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.util.stream.Collectors;
import java.util.*;
import java.io.PrintStream;
import java.io.IOException;
import java.time.LocalDate;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane; 

public class ConsultarFaturas extends JFrame {
    private JLabel dataD1,dataM1,dataA1, dataD2,dataM2,dataA2;
    private JTextField DataD,DataM,DataA,DataD2,DataM2,DataA2;
    private JButton confirmar,cancelar;
    public ConsultarFaturas(Utilizadores b, long nif) {
        setTitle("Consultar Faturas");
        setSize(500,500);
        
        JMenuBar menuBar = new JMenuBar();
        JTextPane pane = new JTextPane();
        
        setJMenuBar(menuBar);
        
        add(pane);
        pane.setEditable(false);
         
        JMenu editMenu = new JMenu("Ficheiro");
        JMenu fileMenu = new JMenu("Visualizar");
        menuBar.add(editMenu);
        menuBar.add(fileMenu);
        
        JMenuItem action = new JMenuItem("Guardar");
        JMenuItem exitAction = new JMenuItem("Sair");
        
        actionE ae = new actionE(b,nif);
        action.addActionListener(ae);
        
        exitActionE ea = new exitActionE(b,nif);
        exitAction.addActionListener(ea);
         
        JRadioButtonMenuItem radioAction1 = new JRadioButtonMenuItem("Todas as Faturas");
        JRadioButtonMenuItem radioAction2 = new JRadioButtonMenuItem("Faturas Pendentes");
        JRadioButtonMenuItem deducaoAcAction = new JRadioButtonMenuItem("Deduções Fiscais Acumuladas");
        JRadioButtonMenuItem valorAction =  new JRadioButtonMenuItem("Faturas Ordenadas por Valor");
        JRadioButtonMenuItem dataAction =  new JRadioButtonMenuItem("Faturas por Data de Emissão");
        JRadioButtonMenuItem contDatas = new JRadioButtonMenuItem("Faturas por Contribuinte em Intervalo de Data");
        JRadioButtonMenuItem contValor = new JRadioButtonMenuItem("Faturas por Contribuinte em Valor Decrescente");
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioAction1);
        bg.add(radioAction2);
        bg.add(deducaoAcAction);
        bg.add(valorAction);
        bg.add(contDatas);
        bg.add(contValor);
        bg.add(dataAction);
        editMenu.add(action);
        editMenu.addSeparator();
        editMenu.add(exitAction);
        fileMenu.add(radioAction1);
        if(b.getUtilizadores().get(nif) instanceof Individuais){
        fileMenu.add(radioAction2);
        fileMenu.addSeparator();
        
        fileMenu.add(deducaoAcAction);
        fileMenu.addSeparator();
       }
        if (b.getUtilizadores().get(nif) instanceof Empresas){
        fileMenu.add(valorAction);
        fileMenu.add(dataAction);
        fileMenu.addSeparator();
        fileMenu.add(contDatas);
        fileMenu.add(contValor);
        fileMenu.addSeparator();
       }
       
      radioAction1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String apresenta;
                
                List<Faturas> faturas = new ArrayList<>();
                int length = 0; 
                if (b.getUtilizadores().containsKey(nif)){
                    faturas = b.getUtilizadores().get(nif).getFaturas();
                    length = faturas.size();
                } 
       
                if(length == 0) apresenta = new String ("Não há faturas associadas a este contribuinte");
                else{
                    StringBuilder sb = new StringBuilder(); 
                    faturas.forEach(x -> sb.append(x.toString())); 
                    apresenta = sb.toString();
                }
                
                pane.setEditable(false);
                pane.setText(apresenta);
            }
      });
        
        radioAction2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String apresenta;
                List<Faturas> faturas = new ArrayList<>(); 
                int length = 0; 
                
                if (b.getUtilizadores().containsKey(nif)){
                    for (Faturas f : b.getUtilizadores().get(nif).getFaturas()){
                        if (f.getEstado().equals("Pendente")) faturas.add(f);
                    }
                    length = faturas.size();
                }
                
                if (length == 0) apresenta = new String ("Não existem faturas pendentes"); 
                else{
                    StringBuilder sb = new StringBuilder(); 
                    faturas.forEach(x -> sb.append(x.toString())); 
                    apresenta = sb.toString();
                }
                
                pane.setText(apresenta);
                pane.setEditable(false);
              
                JPanel buttons = new JPanel();
                JButton ok = new JButton("OK");
                JButton cancel = new JButton("Cancel");
                JLabel ID = new JLabel("ID");
                JLabel Natureza = new JLabel("Natureza");
                JTextField IDField = new JTextField(3);
                JTextField NaturezaField = new JTextField(10);
                   
                buttons.setLayout(new FlowLayout());
            
                buttons.add(ID);
                buttons.add(IDField);
                buttons.add(Natureza);
                buttons.add(NaturezaField);
                buttons.add(ok);
                buttons.add(cancel);
               
                getContentPane().add(buttons, BorderLayout.SOUTH);
                
                
                ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        int idF = Integer.parseInt(IDField.getText());
                        String naturezaF = NaturezaField.getText();
                        
                        List<Faturas> faturasc = b.recolheFaturas()
                                                  .stream()
                                                  .filter(e -> e.getNifCliente() == nif)
                                                  .collect(Collectors.toList());
                                           
                        for (Faturas f : faturasc){
                            if (idF == f.getID()){
                                f.setAtividadeEcon(naturezaF);
                                f.setEstado(naturezaF);
                                f.setAlterada(true);
                            }
                        }
                        
                        b.getUtilizadores().get(nif).setFaturas(faturasc);
                        
                        try{
                            b.save();
                        }
                        catch(IOException e1){
                            e1.printStackTrace();
                        }
                        dispose();
                    }
                });
                
                cancel.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        dispose();                   
                    }
                });
                
                setVisible(true);
            }
      });
       
        deducaoAcAction.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                String texto = ("");
                String texto1 = ("");
                
                Map<Long,Entidades> a = b.getUtilizadores();
                Entidades em = a.get(nif);
                if (em instanceof Individuais){
                    Individuais i = (Individuais) em;
                    double acum = i.valorAcumulado();
                    double acumt = i.valorAcumuladoAgreg();
                
                    if(b.getUtilizadores().containsKey(nif)){
                        StringBuilder sb = new StringBuilder(); 
                        sb.append("O valor das deduções fiscais acumuladas é:").append(acum)
                          .append("\n")
                          .append("\n")
                          .append("O valor das deduções fiscais acumuladas do agregado familiar é:").append(acumt);
                        texto = sb.toString();
                    }
                }
                pane.setText(texto);
                pane.setEditable(false);
            }
      });
        
       valorAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            pane.setText("");
                try{
                Entidades en = b.getUtilizador(nif);
                if (en instanceof Empresas){
                Empresas emp = (Empresas) en;
                pane.setText(emp.ordenaValor().toString());
               }
            }
            catch (UtilizadorInexistenteException ie){
                ie.printStackTrace();
            }
            pane.setEditable(false);
            }
      });
    
        dataAction.addActionListener(new ActionListener(){
           @Override
            public void actionPerformed(ActionEvent e){
            pane.setText("");
                try{
                Entidades en = b.getUtilizador(nif);
                if (en instanceof Empresas){
                Empresas emp = (Empresas) en;
                pane.setText(emp.ordenaData().toString());
               }
            }
            catch (UtilizadorInexistenteException ie){
                ie.printStackTrace();
            }
            pane.setEditable(false);
        }
      });
     
      contDatas.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
              pane.setText("");
              
              pane.setEditable(false);
              
                JPanel buttons = new JPanel();
                JButton ok = new JButton("OK");
                JButton cancel = new JButton("Cancel");
                JLabel Data = new JLabel("Data Inicio");
                JLabel Data2 = new JLabel("Data Fim");
                JTextField dataDField = new JTextField(2);
                JTextField dataMField = new JTextField(2);
                JTextField dataAField = new JTextField(4);
                JTextField dataD2Field = new JTextField(2);
                JTextField dataM2Field = new JTextField(2);
                JTextField dataA2Field = new JTextField(4);
                   
                buttons.setLayout(new FlowLayout());
            
                buttons.add(Data);
                buttons.add(dataDField);
                buttons.add(dataMField);
                buttons.add(dataAField);
                buttons.add(Data2);
                buttons.add(dataD2Field);
                buttons.add(dataM2Field);
                buttons.add(dataA2Field);
                buttons.add(ok);
                buttons.add(cancel);
               
                getContentPane().add(buttons, BorderLayout.SOUTH);
               
            ok.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event) {  
                   LocalDate data1 = LocalDate.of((Integer.parseInt(dataAField.getText())),(Integer.parseInt(dataMField.getText())),(Integer.parseInt(dataDField.getText())));
                   LocalDate data2 = LocalDate.of((Integer.parseInt(dataA2Field.getText())),(Integer.parseInt(dataM2Field.getText())),(Integer.parseInt(dataD2Field.getText())));
 
                   try{
                      Entidades en = b.getUtilizador(nif);
                      if (en instanceof Empresas){
                         Empresas emp = (Empresas) en;
                         pane.setText(emp.ordenaNIFData(data1,data2).toString());
                      }
                   }
                   catch (UtilizadorInexistenteException ie){
                            ie.printStackTrace();
                   } 
                  }
            });
                
            cancel.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent event) {
                      dispose();                   
                 }
            });
            setVisible(true);
            } 
      }); 
      
      contValor.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
            pane.setText("");
            try{
               Entidades en = b.getUtilizador(nif);
               if (en instanceof Empresas){
                Empresas emp = (Empresas) en;
                pane.setText(emp.ordenaValorNIF().toString());
               }
            }
            catch (UtilizadorInexistenteException ie){
                ie.printStackTrace();
            }
            pane.setEditable(false);
            }
      });
      
        radioAction1.setSelected(true);
        radioAction2.setSelected(true);
        dataAction.setSelected(true);
        valorAction.setSelected(true);
        deducaoAcAction.setSelected(true);
        contValor.setSelected(true);
        contDatas.setSelected(true);
        
        JScrollPane scrollPane = new JScrollPane(pane);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
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
    
    public static void consultarfaturas(Utilizadores b, long nif) {
        ConsultarFaturas cf = new ConsultarFaturas(b,nif);
        cf.setBounds(300,100,700,600);
        cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cf.setVisible(true);
    }
}