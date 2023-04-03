public class Autor {

    private int codigo;
    private String nome;

    private Livro[] livros; //Coleção de livros de um autor (array)
    private final int MAX = 100; //Quantidade máxima de de livros para um autor
    private int qntAutores; //Quantidade de autores cadastrados
    private int qntLivros; //Quantidade de livros de um autor
    
    //Contrutor
    public Autor(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        livros = new Livro[MAX];
    }
    
    public boolean adicionaLivro(Livro livro){
        if(qntLivros<MAX){
            livros[qntLivros] = livro;
            qntLivros++;
            return true;
        }
        else{
            return false;
        }

    }

    public Livro[] pesquisaLivro(){
        return livros;
    }
    
    
}
