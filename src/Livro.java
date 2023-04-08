// Imports

public class Livro {

    private String isbn;
    private String titulo;
    private int ano;

    private Autor[] autores; //Coleção de Autores (array)
    private final int MAX = 100; //Quantidade máxima de autores para um livro
    private int qntAutores; //Quantidade de autores
    
    //Construtor
    public Livro(String isbn, String titulo, int ano){
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
        autores = new Autor[MAX];
    }

    public boolean adicionaAutor(Autor autor){
        if(qntAutores < MAX){
            autores[qntAutores] = autor;
            qntAutores++;
            return true;
        }
        else{
            return false;
        }
    }

    public String getIsbn(){
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public Autor[] getAutores() {
        return autores;
    }

    public int getQntAutores() {
        return qntAutores;
    }

    public Livro getLivro(){
        return this;
    }

    public String toString(){
        String aut = "[";
        for (int i=0;i<qntAutores;i++){
            aut += this.autores[i];
        }
        aut += "]";

        return "Título: "+this.titulo+"\n"+
                "isbn: "+this.isbn+"\n"+
                "Ano: "+this.ano+"\n"+
                "Autores:\n"+aut;
    }

}
