package logicLayer;

import dataLayer.writerAndReader;
import java.util.Vector;
import java.io.*;

public class ITS {
    private writerAndReader readAndWrite;
    private Vector<Issue> issues;
    private static ITS instance;

    //constructor
    private ITS() {
        //initializing data members
        readAndWrite = new writerAndReader();
        issues = new Vector<>();

        //initializing issues from file
        try {
            File myObj = new File("./IssuesTrackingSystem/src/main/java/dataLayer/issues.csv");
            if (!myObj.createNewFile()) {   //if file has already created
                readAndWrite.readIssuesFromFile(issues);
            } else {
                readAndWrite.writeHeadersInFile();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //getters
    public writerAndReader getReadAndWrite() {
        return readAndWrite;
    }
    public Vector<Issue> getIssues() {return issues;}

    //setters
    public void setReadAndWrite(writerAndReader readAndWrite) {
        this.readAndWrite = readAndWrite;
    }
    public void setIssues(Vector<Issue> issues) {this.issues = issues;}

    //function to get instance of this class
    public static ITS getInstance(){
        if (instance == null) {
            instance = new ITS();
        }
        return instance;
    }

    //function to check if the issue exists or not
    public boolean validateIssueID(String ID) {
        for (Issue issue : issues) {
            if (Integer.parseInt(ID) == issue.getID()) {
                return true;
            }
        }
        return false;
    }

    //function for adding issue
    public void addIssue(String desc, String assignee, String status, String creationDate) {
        int ID = 0;

        //getting maximum ID
        for (Issue issue : issues) {
            if (issue.getID() > ID) {
                ID = issue.getID();
            }
        }
        ID++;

        //adding issue
        Issue i = new Issue(ID, desc, assignee, status, creationDate);
        issues.add(i);
        readAndWrite.writeIssueIntoFile(i);
    }

    //function for editing Issue
    public void editIssue(String ID, String desc, String assignee, String status, String creationDate){
        for (int i = 0; i < issues.size(); ++i) {
            if (Integer.parseInt(ID) == issues.get(i).getID()) {
                issues.set(i, new Issue(issues.get(i).getID(), desc, assignee, status, creationDate));
            }
        }
        readAndWrite.truncateAFile("./IssuesTrackingSystem/src/main/java/dataLayer/issues.csv");
        readAndWrite.writeHeadersInFile();
        for (Issue issue : issues) {
            readAndWrite.writeIssueIntoFile(issue);
        }
    }

    //function for deleting issue
    public void deleteIssue(String ID) {
        int index = -1;
        for(int i = 0; i < issues.size(); ++i){
            //Replacing IDs and Also Finding Index to Delete
            if (Integer.parseInt(ID) == issues.get(i).getID()){
                index = i;
                if (i == 0) {
                    issues.get(i).setID(0);
                } else if (i == issues.size() - 1) {
                    break;
                } else {
                    issues.get(i).setID(issues.get(i).getID() - 1);
                }
            } else if(index != -1){
                issues.get(i).setID(issues.get(i - 1).getID() + 1);
            }
        }
        issues.remove(index);   //Removing issue From Vector
        readAndWrite.truncateAFile("./IssuesTrackingSystem/src/main/java/dataLayer/issues.csv");
        readAndWrite.writeHeadersInFile();
        for (Issue issue : issues) {
            readAndWrite.writeIssueIntoFile(issue);
        }
    }

    // function for searching issue
    public Issue searchIssue(String ID){
        for (Issue issue : issues) {
            if (Integer.parseInt(ID) == issue.getID()) {
                return issue;
            }
        }
        return null;
    }
}
