package logicLayer;

public class Issue {
    private String desc, assignee, status, creationDate;
    private int ID;

    //constructors
    public Issue(){}
    public Issue(int ID, String desc, String assignee, String status, String creationDate) {
        this.ID = ID;
        this.desc = desc;
        this.assignee = assignee;
        this.status = status;
        this.creationDate = creationDate;
    }

    //getters
    public int getID() {return ID;}
    public String getAssignee() {return assignee;}
    public String getCreationDate() {return creationDate;}
    public String getDesc() {return desc;}
    public String getStatus() {return status;}

    //setters
    public void setAssignee(String assignee) {this.assignee = assignee;}
    public void setCreationDate(String creationDate) {this.creationDate = creationDate;}
    public void setDesc(String desc) {this.desc = desc;}
    public void setID(int ID) {this.ID = ID;}
    public void setStatus(String status) {this.status = status;}
}
