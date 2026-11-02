public class Complaint{
    public String dateReceived;
    public String product;
    public String subProduct;
    public String issue;
    public String subIssue;
    public String companyPublicResponse;
    public String company;
    public int zipcode;
    public String dateSentToCompany;
    public String companyResponseToConsumer;
    public boolean timelyResponse;

    public Complaint(String dateReceived, String product, String subProduct, String issue, String subIssue,  
                     String companyPublicResponse, String company, int zipcode, String dateSentToCompany, 
                     String companyResponseToConsumer, boolean timelyResponse){

                        this.dateReceived = dateReceived;
                        this.product = product;
                        this.subProduct = subProduct;
                        this.issue = issue;
                        this.subIssue = subIssue;
                        this.companyPublicResponse = companyPublicResponse;
                        this.company = company;
                        this.zipcode = zipcode;
                        this.dateSentToCompany = dateSentToCompany;
                        this.companyResponseToConsumer = companyResponseToConsumer;
                        this.timelyResponse = timelyResponse;
                     }
    
    public int getComplaintYear(){
        return Integer.parseInt(this.dateReceived.substring(0,4));
    }

}