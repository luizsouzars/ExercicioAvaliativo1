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

        biblioteca = new Biblioteca();
        grupo = new Grupo();
    }

    public void executa(){
        System.out.print(entrada);
    }
    
}
