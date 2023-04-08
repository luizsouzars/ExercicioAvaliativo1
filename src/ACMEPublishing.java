// Imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMEPublishing {
    private Scanner entrada = null;       // Atributo para entrada de dados
    private Biblioteca biblioteca;
    private Grupo grupo;
    private PrintStream saidaPadrao = System.out;   // Guarda saida padrao

    // Construtor
    public ACMEPublishing(){
        entrada = new Scanner(System.in);
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("dados.txt"));
            entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
            PrintStream streamSaida = new PrintStream(new File("saida.txt"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        entrada.useLocale(Locale.ENGLISH);

        grupo = new Grupo();
    }

    public void executa(){
        biblioteca = new Biblioteca();
        grupo = new Grupo();

        cadastraLivro(); //PASSO 1: CADASTRA LIVROS
        mostraQntLivros(); //PASSO 2: MOSTRA QNTS LIVROS CADASTRADOS
        cadastraAutores(); //PASSO 3: CADASTRA AUTORES
        mostraQntAutores(); //PASSO 4: MOSTRA QNTS AUTORES CADASTRADOS
        addLivroAutor(); //PASSO 5: ADICIONA LIVRO À AUTOR
        mostraLivrosAutor(); //PASSO 6: MOSTRA OS LIVROS DE UM DETERMINADO AUTOR
        mostraAutoresLivro(); //PASSO 7: MOSTRA OS AUTORES DE UM LIVRO
        livrosMaisDeUmAutor(); //PASSO 8: MOSTRA OS LIVROS COM MAIS DE UM AUTOR

    }

    private void cadastraLivro(){
        while (entrada.hasNextLine()){
            
            String isbn = entrada.nextLine();
            if (!isbn.equals("-1")){
                String titulo = entrada.nextLine();
                int ano = entrada.nextInt();
                entrada.nextLine(); //Apaga buffer após ler int
                Livro livro = new Livro(isbn,titulo,ano);
                if(biblioteca.cadastraLivro(livro)){
                    System.out.println(1+";"+isbn+";"+titulo+";"+ano);
                }
            }
            else{
                return;
            }
        }
    }

    public void mostraQntLivros(){
        System.out.println(2+";"+biblioteca.getQntLivros());
    }

    public void cadastraAutores(){

        while (entrada.hasNextLine()){
            int codigo = entrada.nextInt();
            entrada.nextLine(); //Apaga buffer após ler int
            if (codigo != -1){
                String nome = entrada.nextLine();
                String isbn = entrada.nextLine();
                Autor autor = new Autor(codigo,nome);
                if(grupo.cadastraAutor(autor)){ //Cadastra autor
                    Livro livro = biblioteca.pesquisaLivro(isbn); //Busca o livro
                    if(autor.adicionaLivro(livro) && livro.adicionaAutor(autor)){ //Adiciona o livro aos autores e os autores aos livros
                        System.out.println(3+";"+codigo+";"+nome+";"+isbn);
                    }
                }
            }
            else{
                return;
            }
        }
    }

    public void mostraQntAutores(){
        System.out.println(4+";"+grupo.getQntAutores());
    }
    
    public void addLivroAutor(){
        while (entrada.hasNextLine()){
            int codigoAutor = entrada.nextInt();
            entrada.nextLine(); //Apaga buffer após ler int
            if (codigoAutor != -1){
                // Busca informações do Autor
                Autor autor = grupo.pesquisaAutor(codigoAutor);
                String nome = autor.getNomeAutor();
                // Busca informações do Livro
                String isbn = entrada.nextLine();
                Livro livro = biblioteca.pesquisaLivro(isbn);
                String titulo = livro.getTitulo();
                int ano = livro.getAno();
                if (grupo.addLivro(codigoAutor, livro)){
                    System.out.println(5+";"+codigoAutor+";"+nome+";"+isbn+";"+titulo+";"+ano);
                }
            }
            else{
                return;
            }             
        }
    }

    public void mostraLivrosAutor(){
        int codigoAutor = entrada.nextInt();
        entrada.nextLine(); //Apaga buffer após ler int

        Autor autor = grupo.pesquisaAutor(codigoAutor);
        String nomeAutor = autor.getNomeAutor();

        Livro[] livros = autor.pesquisaLivros();
        int qntLivros = autor.getQntLivros();
        for (int i=0;i<qntLivros;i++){
            Livro livro_aux = livros[i];
            String isbn = livro_aux.getIsbn();
            String titulo = livro_aux.getTitulo();
            int ano = livro_aux.getAno();
            System.out.println(6+";"+codigoAutor+";"+nomeAutor+";"+isbn+";"+titulo+";"+ano);
        }
    }

    public void mostraAutoresLivro(){
        String isbn = entrada.nextLine();
        Livro livro = biblioteca.pesquisaLivro(isbn);
        Autor[] autores = livro.getAutores();
        int qntAutores = livro.getQntAutores();

        String txt = isbn;
        for (int i=0;i<qntAutores;i++){
            String nomeAutor = autores[i].getNomeAutor();
            txt += ";"+nomeAutor;
        }
        System.out.println(txt);
    }

    public void livrosMaisDeUmAutor(){
        Livro[] livros = biblioteca.getLivros();
        int qnt = biblioteca.getQntLivros();

        for (int i=0;i<qnt;i++){
            if(livros[i].getQntAutores()>1){
                System.out.println(8+";"+livros[i].getTitulo());
            }
        }
    }
} //Final classe ACMEPublishing
