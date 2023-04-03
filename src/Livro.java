// Imports

public class Livro {
    private String isbn;
    private String titulo;
    private int ano;
    private Autor[] autores; //Coleção de Autores (array)
    private final int MAX = 100; //Quantidade máxima de autores
    private int qntLivros; //Quantidade de livros cadastrados
    
    //Construtor
    public Livro(String isbn, String titulo, int ano){
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
        autores = new Autor[MAX];
    }

    public boolean adicionaAutor(Autor autor){
        if(qntLivros < MAX){
            autores[qntLivros] = autor;
            qntLivros++;
            return true;
        }
        else{
            return false;
        }
    }
}
