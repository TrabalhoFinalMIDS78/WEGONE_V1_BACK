package br.com.wegone.view;

public class AuxiliarDeConsole {

    public static String lerLinha() {
        return new java.util.Scanner(System.in).nextLine();
    }

    public static void exibirTitulo(String titulo) {

        int largura = 50;
        String borda = repeatChar('═', largura);
        String linhaCentral = String.format("║%-50s║", "  " + titulo);

        System.out.println("\n╔" + borda + "╗");
        System.out.println(linhaCentral);
        System.out.println("╚" + borda + "╝\n");

    }

    private static String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void separador() {

        System.out.println("\n" + repeatChar('═', 52));

    }

}
