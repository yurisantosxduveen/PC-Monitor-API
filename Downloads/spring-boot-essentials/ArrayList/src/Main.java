import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> loja1 = new ArrayList<>();
        ArrayList<String> loja2 = new ArrayList<>();

        loja1.add("Calça, Camiseta, Meia, Jaqueta, Acessorios de Pulso, Perfume, Sapatos!");
        loja2.add("Celulares, Teclados, Mouse, Carregador, Fone de ouvido, Notebook!");

        System.out.println("=== ITENS DE CADA LOJA ===");
        System.out.println();
        System.out.println("LOJA 1: " + loja1.get(0));
        System.out.println("LOJA 2: " + loja2.get(0));

        scanner.close();

    }
}