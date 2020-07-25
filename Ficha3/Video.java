import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 * Write a description of class YouTube here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Video
{
private String nome;
private byte[] conteudo;
private LocalDate data;
// e.g. 720, 1080/
private int resolucao;
// em segundos
private int duracao;
private String[] comentarios;
private int likes,dislikes;

private int index; //nao necessita de get && sets (vatiavel usada na insereCOmentario

//fazer construtores

public Video(){
 this.nome ="N/A";
 this.conteudo = new byte[100];
 this.data = LocalDate.now();
 this.resolucao=1080;
 this.duracao=0;
 this.comentarios= new String[100];
 this.likes=0;
 this.dislikes=0;
 this.index=0;
}

 public void insereComentario(String comment){
     if(index<comentarios.length){
         comentarios[index] = comment;
         index ++;
        }
    }
    
public long qtsDiasDepois(){
 LocalDate agora = LocalDate.now();
 long dias = data.until(agora,ChronoUnit.DAYS);
 return dias;
}

public String render(){
    return String.valueOf(conteudo);    
}

public void thumbsUp(){
    this.likes++;
    
}
}
