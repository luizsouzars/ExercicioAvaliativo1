public class Autor {

    private int codigo;
    private String nome;

    private Livro[] livros; //Coleção de livros de um autor (array)
    private final int MAX = 100; //Quantidade máxima de de livros para um autor
    private int qntLivros; //Quantidade de livros de um autor
    
    //Construtor
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

    public int getCodigo(){
        return codigo;
    }

    public String getNomeAutor(){
        return this.nome;
    }
    
    public String geraDescricao(){
        String descricao = "";
        descricao += "Código: "+codigo+"\n";
        descricao += "Nome do Autor: "+nome+"\n";
        descricao += "Autor de "+qntLivros+" Livros.";
        return descricao;
    }
}
