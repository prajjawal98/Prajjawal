package Password.EndToEndFunctionality;

import java.util.Date;

public class SheetColumnHeader1 {

    String emailId1;
    String username;
    String testcaseDescription1;
    String expectedResult1;


    public SheetColumnHeader1() {
    }


    public String getEmailId1() {
        return emailId1;
    }

    public void setEmailId1(String emailId1) {
        this.emailId1 = emailId1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTestcaseDescription1() {
        return testcaseDescription1;
    }

    public void setTestcaseDescription1(String testcaseDescription1) {
        this.testcaseDescription1 = testcaseDescription1;
    }

    public String getExpectedResult1() {
        return expectedResult1;
    }

    public void setExpectedResult1(String expectedResult1) {
        this.expectedResult1 = expectedResult1;
    }

    @Override
    public String toString() {
        return "SheetColumnHeader{" +
                "emailId1='" + emailId1 + '\'' +
                ", username='" + username + '\'' +
                ", testcaseDescription1='" + testcaseDescription1 + '\'' +
                ", expectedResult1='" + expectedResult1 + '\'' +
                '}';
    }
}
