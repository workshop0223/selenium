package panel.autoSelenium;

public class TestData {
    private String testid;
    private String username;
    private String password;
    private String operation;
    private boolean executable;

    public TestData(String testid, String username, String password, String operation, Boolean executable){
        this.testid = testid;
        this.username = username;
        this.password = password;
        this.executable = executable;
        this.operation = operation;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isExecutable() {
        return executable;
    }

    public void setExecutable(boolean executable) {
        this.executable = executable;
    }
}
