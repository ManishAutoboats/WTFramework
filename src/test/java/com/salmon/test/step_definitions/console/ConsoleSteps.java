package com.salmon.test.step_definitions.console;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.SSHClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * Created by kadiyalam on 22/08/2017.
 */
public class ConsoleSteps {

    private SSHClient sshClient;
    public ConsoleSteps(SSHClient sshClient) {
        this.sshClient = sshClient;
    }

    @Given("^I have all the details$")
    public void iHaveAllTheDetails() throws Throwable {

    }

    @When("^I run the shell script$")
    public void iRunTheShellScript() throws Throwable {
        sshClient.connect(UrlBuilder.getSystestIP().toString(), UrlBuilder.getSystestUser().toString(), UrlBuilder.getSystestPassword().toString());
        sshClient.executeCommand(UrlBuilder.readValueFromConfig("interface.location") );
        sshClient.disconnect();


    }
}
