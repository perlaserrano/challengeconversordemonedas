import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaMoneda consultaMoneda = new ConsultaMoneda();
        GeneradorDeArchivo generadorDeArchivo = new GeneradorDeArchivo();

        while (true) {
            System.out.println("*******************************************");
            System.out.println("Sean bienvenidos/a al Conversor de Moneda");
            System.out.println("1) Dolar =>> Peso Argentino");
            System.out.println("2) Peso Argentino =>> Dolar");
            System.out.println("3) Dolar =>> Real brasileno");
            System.out.println("4) Real brasileno =>> Dolar");
            System.out.println("5) Dolar =>> Peso Colombiano");
            System.out.println("6) Peso Colombiano =>> Dolar");
            System.out.println("Salir");
            System.out.println("*******************************************");
            System.out.print("Elija una opcion valida: ");

            int option = scanner.nextInt();

            if (option < 1 || option > 6) {
                if (option == 7) {
                    System.out.println("Saliendo...");
                    break;
                } else {
                    System.out.println("Opción no válida.");
                    continue;
                }
            }

            System.out.print("Ingrese el valor que desea convertir: ");
            double amount = scanner.nextDouble();

            String baseCurrency = "USD";
            String targetCurrency = "";

            switch (option) {
                case 1:
                    targetCurrency = "ARS";
                    break;
                case 2:
                    baseCurrency = "ARS";
                    targetCurrency = "USD";
                    break;
                case 3:
                    targetCurrency = "BRL";
                    break;
                case 4:
                    baseCurrency = "BRL";
                    targetCurrency = "USD";
                    break;
                case 5:
                    targetCurrency = "COP";
                    break;
                case 6:
                    baseCurrency = "COP";
                    targetCurrency = "USD";
                    break;
            }

            try {
                Moneda moneda = consultaMoneda.buscarMoneda(baseCurrency);
                double convertedAmount = convertirMoneda(amount, targetCurrency, moneda);
                System.out.printf("El valor %.2f %s Corresponde al valor final de =>> [%.2f %s%n]", amount, baseCurrency, convertedAmount, targetCurrency);

                // Guardar las tasas de cambio en un archivo JSON
                generadorDeArchivo.guardarJson(moneda);

            } catch (Exception e) {
                System.out.println("Error al realizar la conversión: " + e.getMessage());
            }
        }
    }

    private static double convertirMoneda(double amount, String targetCurrency, Moneda moneda) {
        Map<String, Double> rates = moneda.conversion_rates();
        double exchangeRate = rates.get(targetCurrency);
        return amount * exchangeRate;
    }
}
