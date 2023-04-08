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
        autoresMaisDeUmLivro(); //PASSO 9: MOSTRA OS AUTORES COM MAIS DE UM LIVRO
        mostraLivrosAno(); // PASSO 10: MOSTRA OS LIVROS DE UM DETERMINADO ANO
        
        restauraES();
        int opcao = 0;
        do {
            menu(); //PONTO EXTRA
            System.out.print("Digite a opcao desejada: ");
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    cadastrarAutor();
                    break;
                case 2:
                    mostrarAutoresLivros();
                    break;
                default:
                    System.out.println("Opcao invalida! Redigite.");
            }

        } while(opcao != 0);
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
                if (autor.adicionaLivro(livro) && livro.adicionaAutor(autor)){
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

    public void autoresMaisDeUmLivro(){
        Autor[] autores = grupo.getAutores();
        int qntAutor = grupo.getQntAutores();

        for (int i=0;i<qntAutor;i++){
            Autor autor = autores[i];
            int qntLivro = autor.getQntLivros();
            if(qntLivro > 1){
                String nome = autor.getNomeAutor();
                String txt = "9;"+nome;

                Livro[] livros = autor.pesquisaLivros();
                for(int j=0;j<qntLivro;j++){
                    String isbn = livros[j].getIsbn();
                    txt += ";"+isbn;
                }
                System.out.println(txt);
            }
        }
    }

    public void mostraLivrosAno(){
        int ano = entrada.nextInt();
        entrada.nextLine(); //Apaga buffer após ler int
        int qntLivros = biblioteca.getQntLivrosAno(ano);
        if (qntLivros>=1){
            Livro[] livros = biblioteca.pesquisaLivro(ano);
            for (int i=0;i<qntLivros;i++){
                String isbn = livros[i].getIsbn();
                String titulo = livros[i].getTitulo();
                System.out.println(10+";"+isbn+";"+titulo+";"+ano);
            }
        }
        
    }

    public void menu(){
        System.out.println();
        System.out.println("=====================================");
        System.out.println("Menu de opcoes");
        System.out.println("[1] Cadastrar um novo autor e livro correspondente");
        System.out.println("[2] Mostrar todos os autores cadastrados e livros correspondentes");
        System.out.println("[0] Sair do sistema");
    }

    public void cadastrarAutor(){
        Autor autor;
        Livro livro;
        String isbn;
        String titulo;
        int ano;
        int new_cod = 0;

        System.out.print("Digite o nome do autor: ");
        String nome = entrada.nextLine();
        boolean flag = false;
        do{
            System.out.print("Digite o código numérico do autor ou em branco para sequencial: ");
            String cod = entrada.nextLine();
            if (cod.isEmpty()){
                int sum = 1;
                while(new_cod==0){
                    int idx = grupo.getQntAutores();
                    Autor[] autores = grupo.getAutores();
                    new_cod = (autores[idx-1].getCodigo())+sum;
                    if (grupo.pesquisaAutor(new_cod) != null){
                        new_cod = 0;
                        sum++;
                    }
                    else{
                        flag = true;
                    }
                }
            }
            else{
                if(grupo.pesquisaAutor(Integer.valueOf(cod))!=null){
                    System.out.println("Código já utilizado!");
                }
                else{
                    new_cod = Integer.valueOf(cod);
                    flag = true;
                }
            }
        }while(!flag);
        autor = new Autor(new_cod, nome);

        boolean flagLivro = false;
        do{
            System.out.print("Digite o código isbn do livro: ");
            isbn = entrada.nextLine();
            if(biblioteca.pesquisaLivro(isbn)==null){
                flagLivro = true;
            }

            System.out.print("Digite o titulo: ");
            titulo = entrada.nextLine();

            System.out.print("Digite o ano: ");
            ano = entrada.nextInt();
            entrada.nextLine();

            if (!flagLivro){
                System.out.println("Código isbn já utilizado!");
            }
            else{
            }
        }while(!flagLivro);
        livro = new Livro(isbn, titulo, ano);

        grupo.cadastraAutor(autor);
        biblioteca.cadastraLivro(livro);
        autor.adicionaLivro(livro);
        livro.adicionaAutor(autor);

    }

    public void mostrarAutoresLivros(){
        System.out.println("=====================================");
        Autor[] autores = grupo.getAutores();
        int qntAutores = grupo.getQntAutores();
        for (int i=0;i<qntAutores;i++){
            Autor autor = autores[i];
            System.out.println(autor);
            System.out.println();
            
            Livro[] livros = autor.pesquisaLivros();
            int qntLivros = autor.getQntLivros();
            for (int j=0;j<qntLivros;j++){
                Livro livro = livros[j];
                System.out.println(livro.getTitulo());
                System.out.println();
            }
            System.out.println("=====================================");
        }
    }

    private void restauraES() {
        System.setOut(saidaPadrao);
        entrada = new Scanner(System.in);
    }
} //Final classe ACMEPublishing