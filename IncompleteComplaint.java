public class IncompleteComplaint extends Exception{
    private String badRecord;

    public IncompleteComplaint(String message, String badRecord){
        super(message);
        this.badRecord = badRecord;
    }

    public String getBadRecord(){
        return badRecord;
    }
}