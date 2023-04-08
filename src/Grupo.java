public class Grupo {
    
    private Autor[] autores;
    private Livro[] livrosAutor;
    private final int MAX = 100;
    private int qntAutores; //Quantidade de autores cadastrados
    // private int qntLivrosAutor; //Quantidade de livros que um autor possui

    // Construtor
    public Grupo() {
        autores = new Autor[MAX];
        livrosAutor = new Livro[MAX];
    }

    public boolean cadastraAutor(Autor autor){
        for (int i=0; i<qntAutores;i++){
            Autor autor_aux = autores[i];
            if(autor.getCodigo() == autor_aux.getCodigo()){
                return false;
            }
        }
        if (qntAutores<MAX){
            autores[qntAutores] = autor;
            qntAutores++;
            return true;
        }
        else{
            return false;
        }
    }

    public Autor pesquisaAutor(int codigo){
        for (int i=0; i<qntAutores;i++){
            Autor autor_aux = autores[i];
            if(codigo == autor_aux.getCodigo()){
                return autor_aux;
            }
        }
        return null;
    }

    public Autor[] getAutores(){
        return autores;
    }

    public int getQntAutores(){
        return qntAutores;
    }

    public Livro[] getLivros(){
        return livrosAutor;
    }
}
