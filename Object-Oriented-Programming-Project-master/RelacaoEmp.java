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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
 
public class RelacaoEmp extends JFrame {
    public RelacaoEmp(Utilizadores b, long nif) {
        setTitle("Definições Administrativas");
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
         
        JRadioButtonMenuItem radioAction1 = new JRadioButtonMenuItem("10 Contribuintes que Mais Gastam");
        JRadioButtonMenuItem radioAction2 = new JRadioButtonMenuItem("X Empresas com Mais Faturas");
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioAction1);
        bg.add(radioAction2);
        fileMenu.add(radioAction1);
        fileMenu.addSeparator();
        fileMenu.add(radioAction2);
        editMenu.add(action);
        editMenu.addSeparator();
        editMenu.add(exitAction);
        
        radioAction1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pane.setText("");
                pane.setText(b.top10().toString());
                pane.setEditable(false);
            }
        });
        
        radioAction1.setSelected(true);
        
        radioAction2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            pane.setText("");
                
            JPanel buttons = new JPanel();
            JButton ok = new JButton("OK");
            JButton cancel = new JButton("Cancel");
            JLabel nr = new JLabel("Numero de Empresas");
            JTextField nrField = new JTextField(2);
                   
            buttons.setLayout(new FlowLayout());
            buttons.add(nr);
            buttons.add(nrField);
            buttons.add(ok);
            buttons.add(cancel);
               
            getContentPane().add(buttons, BorderLayout.SOUTH);
              
            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                   pane.setText("");
                   int nrF = Integer.parseInt(nrField.getText());  
                   
                   pane.setText(b.RelacaoXEmpresas(nrF).toString());
                   pane.setEditable(false);
                }
            });    
             
            cancel.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event) {
                   dispose();                   
                }
            });
            pane.setEditable(false);
            setVisible(true);
            }
        });
        
        radioAction2.setSelected(true);
        
        JScrollPane scrollPane = new JScrollPane(pane);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        
        setVisible(true);
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
    
    public static void relacaoemp(Utilizadores b, long nif) {
        RelacaoEmp cf = new RelacaoEmp(b,nif);
        cf.setBounds(300,100,700,600);
        cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cf.setVisible(true);
    }
}
