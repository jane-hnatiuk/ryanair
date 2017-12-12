package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json"},
        features = "src/test/resources/features",
        glue = { "src/test/java/steps"},
        tags = "~@ignore")
public class DeclinedPaymentTestRunner {
}
