import java.util.*;
import java.time.LocalDate;
/**
 * Write a description of class EncEficiente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EncEficiente
{
    
    private String nome;
    private int nif;
    private String morada;
    private int nEncomenda;
    private LocalDate data;
    private List<LinhaEncomenda> encomendas;
   

public EncEficiente(){
    this.nome="";
    this.nif=0;
    this.morada="";
    this.nEncomenda=0;
    this.data = 0;
    this.encomendas = new ArrayList<>();
    
}

public EncEficiente(ArrayList<LinhaEncomenda> encomendas){
    setEnc(encomendas);
    
}

}