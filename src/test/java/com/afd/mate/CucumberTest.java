package com.afd.mate;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
        glue = {"com.afd.mate.e2e", "com.afd.mate.configurations"},
        plugin = {"pretty", "json:target/cucumber-report.json"})
//@TestPropertySource("classpath:application.properties")
public class CucumberTest {
}
