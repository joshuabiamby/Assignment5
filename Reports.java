import java.util.ArrayList;

public class Reports{
    public static int countCompanies (ArrayList<Complaint> complaints){
        ArrayList<String> companies = new ArrayList<>();

        for(int i = 0; i < complaints.size(); i++){
            String company = complaints.get(i).company;

            if(!companies.contains(company)){
                companies.add(company);
            }
        }

        return companies.size();
    }

    public static int[] timelyResponses(ArrayList<Complaint> complaints){
        int[] counts = new int [2];

        for(int i = 0; i < complaints.size(); i++){
            Complaint c = complaints.get(i);

            if(c.timelyResponse){
                counts[0]++;
            }
            else{
                counts[1]++;
            }
        }

        return counts;
    }

    public static int[] periodRange(ArrayList<Complaint> complaints){
        int[] range = new int[2];

        if(complaints.size() == 0){
            range[0] = 0;
            range[1] = 0;
            return range;
        }

        int min = complaints.get(0).getComplaintYear();
        int max = complaints.get(0).getComplaintYear();

        for(int i = 0; i < complaints.size(); i++){
            int year = complaints.get(i).getComplaintYear();

            if(year < min){
                min = year;
            }
            
            if(year > max){
                max = year;
            }
        }

        range[0] = min;
        range[1] = max;
        return range;

    }

    public static int[] complaintsPerYear(ArrayList<Complaint> complaints){
        if(complaints.size() == 0){
            return new int[0];
        }

        int[] range = periodRange(complaints);

        int size = range[1] - range[0] + 1;
        int[] counts = new int[size];

        for(int i = 0; i < complaints.size(); i++){
            int year = complaints.get(i).getComplaintYear();
            counts[year - range[0]]++;
        }
        
        return  counts;
    }

    public static String[] topFiveSubProducts(ArrayList<Complaint> complaints) {

    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> counts = new ArrayList<>();

    // Count how many times each sub-product appears.
    for (int i = 0; i < complaints.size(); i++) {
        String subProduct = complaints.get(i).subProduct;

        boolean found = false;

        for (int j = 0; j < names.size(); j++) {
            if (names.get(j).equals(subProduct)) {
                counts.set(j, counts.get(j) + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            names.add(subProduct);
            counts.add(1);
        }
    }

    int resultSize = names.size();

    if (resultSize > 5) {
        resultSize = 5;
    }

    String[] result = new String[resultSize];
    boolean[] used = new boolean[names.size()];

    for (int i = 0; i < resultSize; i++) {

        int bestIndex = -1;

        for (int j = 0; j < names.size(); j++) {

            if (!used[j]) {

                if (bestIndex == -1 || counts.get(j) > counts.get(bestIndex)) {
                    bestIndex = j;
                }
            }
        }

        result[i] = names.get(bestIndex) + ": " + counts.get(bestIndex);

        used[bestIndex] = true;
    }

    return result;
    }
}