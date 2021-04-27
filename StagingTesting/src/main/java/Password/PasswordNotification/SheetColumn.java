package Password.PasswordNotification;

public class SheetColumn {

    String emailId1;
    String emailId2;
    String username;
    String testcaseDescription;
    String expectedResult;

    public String getEmailId1() {
        return emailId1;
    }

    public void setEmailId1(String emailId1) {
        this.emailId1 = emailId1;
    }

    public String getEmailId2() {
        return emailId2;
    }

    public void setEmailId2(String emailId2) {
        this.emailId2 = emailId2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTestcaseDescription() {
        return testcaseDescription;
    }

    public void setTestcaseDescription(String testcaseDescription) {
        this.testcaseDescription = testcaseDescription;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Override
    public String toString() {
        return "SheetColumn{" +
                "emailId1='" + emailId1 + '\'' +
                ", emailId2='" + emailId2 + '\'' +
                ", username='" + username + '\'' +
                ", testcaseDescription='" + testcaseDescription + '\'' +
                ", expectedResult='" + expectedResult + '\'' +
                '}';
    }

}
