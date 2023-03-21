// Imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class App {
    private Scanner entrada = null;       // Atributo para entrada de dados

    // Construtor
    public App() {
        entrada = new Scanner(System.in);
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("entrada.txt"));
            entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
            PrintStream streamSaida = new PrintStream(new File("saida.txt"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        entrada.useLocale(Locale.ENGLISH);
        // Implemente aqui o seu codigo adicional do construtor
    }

    public void executar(){
        System.out.println("Digite uma Idade: ");
        int idade = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite um Nome: ");
        String nome = entrada.nextLine();
        System.out.println("Idade: "+ idade);
        System.out.println("Nome: "+ nome);
    }
}