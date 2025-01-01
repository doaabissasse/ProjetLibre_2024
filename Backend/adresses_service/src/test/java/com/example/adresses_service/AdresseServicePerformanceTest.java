package com.example.adresses_service;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

public class AdresseServicePerformanceTest {

    @Test
    public void testAdresseServicePerformance() {
        try {
            // Load JMeter properties
            String propertiesPath = new File("src/test/resources/jmeter.properties").getAbsolutePath();
            JMeterUtils.loadJMeterProperties(propertiesPath);
            JMeterUtils.initLocale();
            JMeterUtils.initLogging();

            // Initialize JMeter Engine
            StandardJMeterEngine jmeter = new StandardJMeterEngine();

            // Create Test Plan tree
            HashTree testPlanTree = new HashTree();

            // Add HTTP GET Sampler for /api/adresses
            HTTPSamplerProxy getAllAdressesSampler = createHttpSampler("GET", "/api/adresses", null);

            // Add HTTP GET Sampler for /api/adresses/{id}
            HTTPSamplerProxy getAdresseByIdSampler = createHttpSampler("GET", "/api/adresses/1", null);

            // Add HTTP GET Sampler for /api/adresses/ville/{ville}
            HTTPSamplerProxy getAdressesByVilleSampler = createHttpSampler("GET", "/api/adresses/ville/test-ville", null);

            // Add HTTP DELETE Sampler
            HTTPSamplerProxy deleteAdresseSampler = createHttpSampler("DELETE", "/api/adresses/1", null);

            // Add HTTP GET Sampler for contacts by adresse id
            HTTPSamplerProxy getContactsByAdresseSampler = createHttpSampler("GET", "/api/adresses/1/contacts", null);

            // Configure Loop Controller
            LoopController loopController = new LoopController();
            loopController.setLoops(5); // Number of loops
            loopController.setFirst(true);
            loopController.initialize();

            // Configure Thread Group
            ThreadGroup threadGroup = new ThreadGroup();
            threadGroup.setNumThreads(10); // Number of users
            threadGroup.setRampUp(5); // Ramp-up period in seconds
            threadGroup.setSamplerController(loopController);

            // Configure Test Plan
            TestPlan testPlan = new TestPlan("Performance Test - Adresse Service");

            // Build Test Plan tree
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);

            // Add samplers to Thread Group
            threadGroupTree.add(getAllAdressesSampler);
            threadGroupTree.add(getAdresseByIdSampler);
            threadGroupTree.add(getAdressesByVilleSampler);
            threadGroupTree.add(deleteAdresseSampler);
            threadGroupTree.add(getContactsByAdresseSampler);

            // Run Test Plan
            jmeter.configure(testPlanTree);
            jmeter.run();

        } catch (Exception e) {
            e.printStackTrace();
            fail("Performance test failed: " + e.getMessage());
        }
    }

    private HTTPSamplerProxy createHttpSampler(String method, String path, String payload) {
        HTTPSamplerProxy sampler = new HTTPSamplerProxy();
        sampler.setDomain("localhost"); // Remplacez par votre domaine de service
        sampler.setPort(8093); // Remplacez par le port utilisé par votre Spring Boot application
        sampler.setPath(path);
        sampler.setMethod(method);
        sampler.setName("HTTP Request - " + method + " " + path);
        sampler.setFollowRedirects(true);

        // Add Headers
        HeaderManager headerManager = new HeaderManager();
        headerManager.add(new Header("Content-Type", "application/json"));
        sampler.setHeaderManager(headerManager);

        if (payload != null) {
            sampler.addNonEncodedArgument("", payload, "");
            sampler.setPostBodyRaw(true);
        }

        return sampler;
    }
}
