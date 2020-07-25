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
/**
 * Escreva a descrição da classe TotalF aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class TotalF extends JFrame
{
    private JLabel dataD1,dataM1,dataA1, dataD2,dataM2,dataA2;
    private JTextField DataD,DataM,DataA,DataD2,DataM2,DataA2;
    private JButton confirmar,cancelar;
    
    public TotalF(Utilizadores b, long nif) {
               setTitle("Total Faturado");
               setSize(800,600);
        
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
               
               JRadioButtonMenuItem totalFat = new JRadioButtonMenuItem("Total Faturado");
               ButtonGroup bg = new ButtonGroup();
               bg.add(totalFat);
               
               pane.setText("");
               
               editMenu.add(action);
               editMenu.addSeparator();
               editMenu.add(exitAction);
               fileMenu.add(totalFat);
            
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
                        String texto = ("");
                        LocalDate data1 = LocalDate.of((Integer.parseInt(dataAField.getText())),(Integer.parseInt(dataMField.getText())),(Integer.parseInt(dataDField.getText())));
                        LocalDate data2 = LocalDate.of((Integer.parseInt(dataA2Field.getText())),(Integer.parseInt(dataM2Field.getText())),(Integer.parseInt(dataD2Field.getText())));
 
                        try{
                            Entidades en = b.getUtilizador(nif);
                            if (en instanceof Empresas){
                                Empresas emp = (Empresas) en;
                                double total = emp.totalFaturado(data1,data2);
                                StringBuilder sb = new StringBuilder(); 
                                sb.append("O valor total faturado é:").append(total)
                                  .append("\n");
                                texto = sb.toString();
                            }
                        }
                        catch (UtilizadorInexistenteException ie){
                            ie.printStackTrace();
                        }
                        
                        pane.setText(texto);
                        pane.setEditable(false);
                    }
                });
                
                cancel.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        dispose();                   
                    }
                });
                setVisible(true);
                totalFat.setSelected(true);
    }
            
    public static void totalF(Utilizadores b, long nif) {
           TotalF cf = new TotalF(b,nif);
           cf.setBounds(300,100,700,600);
           cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           cf.setVisible(true);
    }
}
