/* Martin Motilla Ayuso - 15-10-2026 */
import java.util.Scanner;
import java.util.Locale;

public class MedacPark {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        final double PRECIO_ESTANDAR = 40.00;
        final double PRECIO_ACUATICO = 55.00;
        final double PRECIO_VIP = 80.00;

        int opcion;
        int edad;
        int dia;
        boolean familiaNumerosa;

        double precioBase = 0;
        double precioEdad = 0;
        double ajusteDia = 0;
        double descuentoFamilia = 0;
        double total = 0;

        String nombrePase = "";
        String categoriaEdad = "";
        String ajusteCalendario = "";
        String condicionFamilia = "No";

        // 1. Selección del pase
        System.out.println("==================================");
        System.out.println("       BIENVENIDO A MEDAC PARK");
        System.out.println("==================================");
        System.out.println("1. Pase Estandar - 40.00 euros");
        System.out.println("2. Pase Acuatico - 55.00 euros");
        System.out.println("3. Pase Total VIP - 80.00 euros");
        System.out.print("Seleccione su pase: ");

        if (sc.hasNextInt()) {
            opcion = sc.nextInt();
        } else {
            System.out.println("Error: Opcion de pase no valida. Fin del programa.");
            sc.close();
            return;
        }

        switch (opcion) {
            case 1:
                nombrePase = "Pase Estandar";
                precioBase = PRECIO_ESTANDAR;
                break;

            case 2:
                nombrePase = "Pase Acuatico";
                precioBase = PRECIO_ACUATICO;
                break;

            case 3:
                nombrePase = "Pase Total VIP";
                precioBase = PRECIO_VIP;
                break;

            default:
                System.out.println(
                    "Error: Opcion de pase no valida. Fin del programa."
                );
                sc.close();
                return;
        }

        // 2. Edad del visitante
        System.out.print("Introduzca su edad: ");

        if (sc.hasNextInt()) {
            edad = sc.nextInt();
        } else {
            System.out.println("Error: Edad no valida. Fin del programa.");
            sc.close();
            return;
        }

        if (edad < 0 || edad > 120) {
            System.out.println("Error: Edad no valida. Fin del programa.");
            sc.close();
            return;
        } else if (edad < 4) {
            System.out.println();
            System.out.println("==================================");
            System.out.println("          TICKET MEDAC PARK");
            System.out.println("==================================");
            System.out.println("Pase: " + nombrePase);
            System.out.printf("Precio base: %.2f EUR%n", precioBase);
            System.out.println("Categoria: Bebe (menor de 4 anos)");
            System.out.println("Descuento por edad: 100%");
            System.out.println("Entrada gratuita.");
            System.out.printf("IMPORTE FINAL: %.2f EUR%n", 0.00);
            System.out.println("==================================");
            sc.close();
            return;
        } else if (edad <= 12) {
            categoriaEdad = "Infantil";
            precioEdad = precioBase * 0.75;
        } else if (edad <= 64) {
            categoriaEdad = "Adulto";
            precioEdad = precioBase;
        } else {
            categoriaEdad = "Senior";
            precioEdad = precioBase * 0.70;
        }

        total = precioEdad;

        // 3. Dia de la visita
        System.out.println();
        System.out.println("1. Lunes");
        System.out.println("2. Martes");
        System.out.println("3. Miercoles");
        System.out.println("4. Jueves");
        System.out.println("5. Viernes");
        System.out.println("6. Sabado");
        System.out.println("7. Domingo");
        System.out.print("Seleccione el dia de la visita (1-7): ");

        if (sc.hasNextInt()) {
            dia = sc.nextInt();
        } else {
            System.out.println("Error: Dia no valido. Fin del programa.");
            sc.close();
            return;
        }

        if (dia < 1 || dia > 7) {
            System.out.println("Error: Dia no valido. Fin del programa.");
            sc.close();
            return;
        } else if (dia == 3) {
            ajusteDia = -5.00;
            total = total - 5.00;

            if (total < 0) {
                total = 0;
            }

            ajusteCalendario = "Miercoles: rebaja de 5.00 EUR";
        } else if (dia == 6 || dia == 7) {
            ajusteDia = precioBase * 0.10;
            total = total + ajusteDia;
            ajusteCalendario = "Fin de semana: recargo del 10% del precio base";
        } else {
            ajusteCalendario = "Tarifa ordinaria: sin variacion";
        }

        // 4. Familia numerosa
        System.out.print("Tiene carné de Familia Numerosa? (true/false): ");

        if (sc.hasNextBoolean()) {
            familiaNumerosa = sc.nextBoolean();
        } else {
            System.out.println(
                "Error: Respuesta de familia numerosa no valida. Fin del programa."
            );
            sc.close();
            return;
        }

        if (familiaNumerosa) {
            descuentoFamilia = total * 0.15;
            total = total - descuentoFamilia;
            condicionFamilia = "Si: descuento del 15%";
        }

        // 5. Impresion del ticket
        System.out.println();
        System.out.println("==========================================");
        System.out.println("             TICKET MEDAC PARK");
        System.out.println("==========================================");
        System.out.println("Pase: " + nombrePase);
        System.out.printf("Precio base: %.2f EUR%n", precioBase);
        System.out.println("Categoria de edad: " + categoriaEdad);
        System.out.printf("Precio tras descuento por edad: %.2f EUR%n", precioEdad);
        System.out.println("Ajuste por dia: " + ajusteCalendario);

        if (ajusteDia < 0) {
            System.out.printf("Rebaja por dia: %.2f EUR%n", ajusteDia);
        } else if (ajusteDia > 0) {
            System.out.printf("Recargo por dia: +%.2f EUR%n", ajusteDia);
        } else {
            System.out.println("Ajuste economico por dia: 0.00 EUR");
        }

        System.out.println("Familia numerosa: " + condicionFamilia);

        if (familiaNumerosa) {
            System.out.printf("Descuento familia: -%.2f EUR%n", descuentoFamilia);
        } else {
            System.out.println("Descuento familia: 0.00 EUR");
        }

        System.out.println("------------------------------------------");
        System.out.printf("IMPORTE FINAL A PAGAR: %.2f EUR%n", total);
        System.out.println("==========================================");

        sc.close();
    }
}

