import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment5 {

    private static final String[] FIELD_NAMES = {
        "Date received",
        "Product",
        "Sub-product",
        "Issue",
        "Sub-issue",
        "Company public response",
        "Company",
        "ZIP code",
        "Date sent to company",
        "Company response to consumer",
        "Timely response"
    };

    static Complaint stringToComplaint(String record) throws IncompleteComplaint {

        String[] fields = record.split(",", -1);

        if (fields.length != FIELD_NAMES.length) {
            throw new IncompleteComplaint("Missing field", record);
        }

        for (int i = 0; i < fields.length; i++) {

            fields[i] = fields[i].trim();

            if (fields[i].isEmpty()) {
                throw new IncompleteComplaint(
                    "Missing field '" + FIELD_NAMES[i] + "'",
                    record
                );
            }
        }

        if (fields[2].equalsIgnoreCase("I do not know")) {
            throw new IncompleteComplaint("Invalid sub-product", record);
        }

        int zipcode = Integer.parseInt(fields[7]);

        boolean timelyResponse =
            fields[10].equalsIgnoreCase("Yes") ||
            fields[10].equalsIgnoreCase("True");

        return new Complaint(
            fields[0],
            fields[1],
            fields[2],
            fields[3],
            fields[4],
            fields[5],
            fields[6],
            zipcode,
            fields[8],
            fields[9],
            timelyResponse
        );
    }

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("[Complaint Report]");
        System.out.print("Enter file name: ");

        String fileName = keyboard.nextLine();

        try {

            Scanner inputFile = new Scanner(new File(fileName));

            PrintWriter errors = new PrintWriter("errors.txt");
            PrintWriter report = new PrintWriter("report.txt");

            ArrayList<Complaint> complaints = new ArrayList<Complaint>();

            int lineNumber = 0;

            while (inputFile.hasNextLine()) {

                String line = inputFile.nextLine();

                lineNumber++;

                if (lineNumber == 1) {
                    continue;
                }

                try {

                    Complaint complaint = stringToComplaint(line);
                    complaints.add(complaint);

                } catch (IncompleteComplaint e) {

                    errors.println("Line " + lineNumber + ": " + e.getMessage());

                } catch (NumberFormatException e) {

                    errors.println("Line " + lineNumber + ": Zip code is malformed");
                }
            }

            int uniqueCompanies = Reports.countCompanies(complaints);
            int[] timely = Reports.timelyResponses(complaints);
            int[] range = Reports.periodRange(complaints);
            int[] perYear = Reports.complaintsPerYear(complaints);
            String[] topFive = Reports.topFiveSubProducts(complaints);

            report.println("Number of unique companies: " + uniqueCompanies);
            report.println("Responses from companies");
            report.println("Timely: " + timely[0]);
            report.println("Untimely: " + timely[1]);
            report.println("Reporting period: " + range[0] + " - " + range[1]);
            report.println("Complaints per year");

            for (int i = 0; i < perYear.length; i++) {
                report.println((range[0] + i) + ": " + perYear[i]);
            }

            report.println("Top 5 complaints:");

            for (int i = 0; i < topFive.length; i++) {
                report.println(topFive[i]);
            }

            inputFile.close();
            errors.close();
            report.close();

            System.out.println("Report written to 'report.txt'");
            System.out.println("Errors written to 'errors.txt'");
            System.out.println("Program complete.");
            keyboard.close();
            

        } catch (FileNotFoundException e) {

            System.out.println("Could not find file '" + fileName + "'.");
            System.out.println("Program complete.");
            keyboard.close();
        }
    }
}
