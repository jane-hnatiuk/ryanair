package testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        strict = true,
        plugin = {"pretty",
                "html:target/cucumber",
                "json:build/cucumber.json",
                "junit:build/cucumber.xml"},
        features = "classpath:features",
        glue =  "classpath:steps",
        tags = "~@ignore")

public class DeclinedPaymentTest {
}
