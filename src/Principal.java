import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ConversionDivisa conversionDivisa = new ConversionDivisa();
        String[] divisas = {"HNL", "GTQ", "BRL", "MXN"};
        String[] opciones = {"Lempira Hondureño (HNL)",
                            "Quetzal Guatemalteco (GTQ)",
                            "Real Brasileño (BRL)",
                            "Peso Mexicano (MXN)"};

        System.out.println("-------------------------------");
        System.out.println("|----Conversion De Divisas----|");
        System.out.println("-------------------------------");
        while (true) {
            try {
                System.out.println("\nElija una opción:");
                System.out.println("1. Convertir de dólar 'USD' a " + opciones[0]);
                System.out.println("2. Convertir de dólar 'USD' a " + opciones[1]);
                System.out.println("3. Convertir de dólar 'USD' a " + opciones[2]);
                System.out.println("4. Convertir de dólar 'USD' a " + opciones[3]);
                System.out.println("5. Convertir de " + opciones[0] + " a dólar 'USD'");
                System.out.println("6. Convertir de " + opciones[1] + " a dólar 'USD'");
                System.out.println("7. Convertir de " + opciones[2] + " a dólar 'USD'");
                System.out.println("8. Convertir de " + opciones[3] + " a dólar 'USD'");
                System.out.println("9. Salir");

                int opcion = entrada.nextInt();
                switch (opcion) {
                    case 1, 2, 3, 4 -> {
                        System.out.println("Ingrese la cantidad en dólares 'USD' que desea convertir:");
                        double cantidad = entrada.nextDouble();

                        Divisa divisa = conversionDivisa.ConversionDivisaUSD(divisas[opcion - 1], cantidad);
                        System.out.println("Resultado de la conversión: " + divisa.conversion_result() + " " + opciones[opcion - 1]);

                        GuardarJson guardarJson = new GuardarJson();
                        guardarJson.archivoJson(divisa);
                        break;
                    }
                    case 5, 6, 7, 8 ->{
                        System.out.println("Ingrese la cantidad en " + opciones[opcion - 5] + " que desea convertir a dólares 'USD':");
                        double cantidad = entrada.nextDouble();

                        ConversionDivisaOtra conversionDivisaOtra = new ConversionDivisaOtra();
                        Divisa divisa = conversionDivisaOtra.ConversionDivisaOther(divisas[opcion - 5], cantidad);
                        System.out.println("Resultado de la conversión: " + divisa.conversion_result() + " USD");

                        GuardarJson guardarJson = new GuardarJson();
                        guardarJson.archivoJson(divisa);
                        break;
                    }
                    case 9 -> {
                        System.out.println("Gracias por usar el convertidor de monedas. ¡Hasta luego!");
                        return;
                    }
                    default -> System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                }

            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }
}