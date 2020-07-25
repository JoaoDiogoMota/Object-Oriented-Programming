
public class UtilizadorInexistenteException extends Exception
{
    /**
     * Função de exceção : Utilizador não exisistente 
     */
    public UtilizadorInexistenteException(){
        super("O utilizador não existe");
    }
}
