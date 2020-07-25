import java.time.LocalDateTime;
/**
 * Write a description of class facebook here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FBPost
{
    private int id;
    private String username;
    private LocalDateTime date;
    private String post;
    private int likes;
    private String FBPost;
    
    public void setPosts(List<FBPost> posts){
     this.posts = new ArrayList<>();
     posts.forEach(p->this.posts.add(p.clone()));
    }
    
    public int nrPosts(String user){
     int r=0;
     for(FBPost p:posts){
         if(p.getUsername().equals(user)){
             r++;
            }
        }
        return r;
    }
    
    public int nrPostsF(String user){
     return (int) posts.stream()
                        .filter(p->p.getUsername().equals(user))
                        .count();
    }
    
    
public List<FBPost> postsOf(String user){
    List<FBPost> res = new ArrayList<>();
    for(FBPost p:posts){
        if(p.getUsername().equals(user)){
         res.add(p.clone());   
        }
    }
    

}

public List<FBPost> postsOfF(String user){
    return posts.stream()
                   .filter(p->p.getUsername().equals(user))
                   .map(p->p.colne())
                   .collect(Collectors.toList());
    
}
//CTRL ESPAÇO PARA COMPLETAR    
public List<FBpsot> postsOf(String user, LocalDateTime inicio,  LocalDateTime fim ){
 List<FBPost> res = new ArrayList<>();
 for(FBPost p: posts){
     if(p.getUsername().equals(user) &&
     p.getData().isBefore(fim) &&
     p.getData().isAfter(inicio)){
         res.add(p.clone());
        }
    }
    return res;
}
/**
 * Devolve um post dado o seu id. Devolve null caso nao exista
 */
public FBPost getPost(int id){
    FBPost r = null;
    Iterator<FBPost> it = this.posts.iterator();
    while(it.hasNext() && r!= null){
     FBPost p = it.next();
     if(p.getId() == id){
     r=p.clone;    
        }
    }
    return r;
}

public FBPOst getPost(int id){
 return this.posts.stream()
                .filter(p->p.getId() == id)
                .findFirst().get().clone();
}

public void comment(FBPost post, String comentario){
 int ind = posts.indexOf(posts);
 FBPost p = this.posts.get(ind);
 List <String> cms = p.get Comentarios();
 cms.add(comentario);
 p.setComentario(cms);
}
}