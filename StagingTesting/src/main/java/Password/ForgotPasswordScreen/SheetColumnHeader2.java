package Password.ForgotPasswordScreen;

public class SheetColumnHeader2 {

    String username1;
    String testcaseDescription2;
    String expectedResult2;


    public SheetColumnHeader2() {
    }


    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getTestcaseDescription2() {
        return testcaseDescription2;
    }

    public void setTestcaseDescription2(String testcaseDescription2) {
        this.testcaseDescription2 = testcaseDescription2;
    }

    public String getExpectedResult2() {
        return expectedResult2;
    }

    public void setExpectedResult2(String expectedResult2) {
        this.expectedResult2 = expectedResult2;
    }

    @Override
    public String toString() {
        return "SheetColumnHeader2{" +
                "username1='" + username1 + '\'' +
                ", testcaseDescription2='" + testcaseDescription2 + '\'' +
                ", expectedResult2='" + expectedResult2 + '\'' +
                '}';
    }
}
