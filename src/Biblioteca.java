public class Biblioteca {
    
    private Livro[] livros;
    private Livro[] livros_ano;
    private final int MAX = 100;
    private int qntLivros;
    private int qntLivrosAno;

    // Construtor
    public Biblioteca (){
        livros = new Livro[MAX];
    }

    public boolean cadastraLivro(Livro livro){
        for (int i=0;i<qntLivros;i++){
            Livro livro_aux = livros[i];
            if (livro.getIsbn().equals(livro_aux.getIsbn())){
                return false;
            }
        }
        if (qntLivros<MAX){
            livros[qntLivros] = livro;
            qntLivros++;
            return true;
        }
        else{
            return false;
        }
    }

    // Pesquisa um livro pelo código isbn
    // Retorna o livro localizado
    // Caso não exista, retorna null
    public Livro pesquisaLivro(String isbn){
        for (int i=0;i<qntLivros;i++){
            Livro livro_aux = livros[i];
            if (isbn.equals(livro_aux.getIsbn())){
                return livro_aux;
            }
        }
        return null;
    }

    // Pesquisa os livros de um ano indicado
    // Retorna lista com os livros
    // Caso não existam, retorna null
    public Livro[] pesquisaLivro(int ano){
        livros_ano = new Livro[MAX];
        for (int i=0;i<qntLivros;i++){
            Livro livro_aux = livros[i];
            if (ano == livro_aux.getAno()){
                livros_ano[i] = livro_aux;
                qntLivrosAno++;
            }
        }
        if (qntLivrosAno>=1){
            return livros_ano;
        }
        else{
            return null;
        }
    }

    public int getQntLivrosAno(int ano){
        livros_ano = new Livro[MAX];
        for (int i=0;i<qntLivros;i++){
            Livro livro_aux = livros[i];
            if (ano == livro_aux.getAno()){
                livros_ano[i] = livro_aux;
                qntLivrosAno++;
            }
        }
        return qntLivrosAno; 
    }

    public Livro[] getLivros() {
        return livros;
    }

    public int getQntLivros() {
        return qntLivros;
    }
}
