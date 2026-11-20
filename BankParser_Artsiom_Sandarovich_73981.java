import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Scanner;

public class BankParser_Artsiom_Sandarovich_73981 {
    private static final String NBP_URL = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";

    public String readDigits() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first three digits of bank account");
        return scanner.nextLine().trim();
    }

    public String findBankName(String digits) {
        System.out.println("Reads data from a file");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(URI.create(NBP_URL).toURL().openStream(), "Cp852"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split("\t");
                if (columns.length > 1 && columns[0].trim().equals(digits)) {
                    return columns[1].trim();
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        BankParser_Artsiom_Sandarovich_73981 parser = new BankParser_Artsiom_Sandarovich_73981();
        String digits = parser.readDigits();
        String bankName = parser.findBankName(digits);

        if (bankName != null) {
            System.out.println("Bank number : " + digits + " Your bank name is: " + bankName);
        } else {
            System.out.println("Bank number : " + digits + " not found");
        }
    }
}
