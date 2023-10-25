package dataLayer;

import logicLayer.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Vector;

public class writerAndReader {

    //function for writing headings in newly created file for issues
    public void writeHeadersInFile() {
        try {
            FileWriter fileWriter =
                    new FileWriter("./IssuesTrackingSystem/src/main/java/dataLayer/issues.csv", true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.append("ID,Description,Assignee,Status,CreationDate\n");
            bufferedWriter.close();
        } catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }

    //function for writing data into issues file
    public void writeIssueIntoFile(Issue i) {
        try {
            FileWriter fileWriter =
                    new FileWriter("./IssuesTrackingSystem/src/main/java/dataLayer/issues.csv", true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            bufferedWriter.append(i.getID() + "," + i.getDesc() + "," + i.getAssignee() + "," + i.getStatus() + ",");
            bufferedWriter.append(i.getCreationDate() + "\n");
            bufferedWriter.close();
        } catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }

    //function for reading data from issues file
    public void readIssuesFromFile(Vector<Issue> issues) {
        Issue i = new Issue();
        int value, temp = 1;
        String strLine = "";
        boolean check = false;

        try {
            FileInputStream fileStream =
                    new FileInputStream("./IssuesTrackingSystem/src/main/java/dataLayer/issues.csv");
            DataInputStream dataStream = new DataInputStream(fileStream);
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(dataStream));
            while ((value = bufferReader.read()) != -1) {
                char character = (char) value;
                if (character != ',' && character != '\n') {
                    strLine += character;
                } else {
                    //if the first line which consists headers is being read
                    if (check == false) {
                        if (character == '\n') {
                            check = true;
                            strLine = "";
                        }
                        continue;
                    }

                    //Saving data because now it's not the first line of headers
                    if (temp == 1) {
                        i.setID(Integer.parseInt(strLine));
                    } else if (temp == 2) {
                        i.setDesc(strLine);
                    } else if (temp == 3) {
                        i.setAssignee(strLine);
                    } else if (temp == 4) {
                        i.setStatus(strLine);
                    } else if (temp == 5) {
                        i.setCreationDate(strLine);
                        temp = 0;
                        issues.add(i);
                        i = new Issue();
                    }
                    temp++;
                    strLine = "";
                }
            }
            dataStream.close();
        }
        catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }

    //function to truncate a file
    public void truncateAFile(String fileName){
        try {
            FileWriter fileTruncate = new FileWriter(fileName);
            fileTruncate.close();
        }
        catch (Exception error) {
            System.err.println("Error: " + error.getMessage());
        }
    }
}