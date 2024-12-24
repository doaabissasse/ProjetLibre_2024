package com.example.contactes_service;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

public class ContacteServicePerformanceTest {

    @Test
    public void testContacteServicePerformance() {
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

            // Add HTTP GET Sampler for /contactes
            HTTPSamplerProxy getSampler = createHttpSampler("GET", "/contactes", null);

            // Add HTTP GET Sampler for /contactes/laboratoire/{labo_id}
            HTTPSamplerProxy getLaboratoireSampler = createHttpSampler("GET", "/contactes/laboratoire/1", null);

            // Add HTTP GET Sampler for /contactes/adresse/{adresse_id}
            HTTPSamplerProxy getAdresseSampler = createHttpSampler("GET", "/contactes/adresse/1", null);

            // Add HTTP POST Sampler
            String postPayload = "{\"nom\": \"Jean Dupont\", \"email\": \"jean.dupont@example.com\", \"numero\": \"1234567890\"}";
            HTTPSamplerProxy postSampler = createHttpSampler("POST", "/contactes", postPayload);

            // Add HTTP PUT Sampler
            String putPayload = "{\"nom\": \"Jean Dupont\", \"email\": \"jean.updated@example.com\", \"numero\": \"9876543210\"}";
            HTTPSamplerProxy putSampler = createHttpSampler("PUT", "/contactes/1", putPayload);

            // Add HTTP DELETE Sampler
            HTTPSamplerProxy deleteSampler = createHttpSampler("DELETE", "/contactes/1", null);

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
            TestPlan testPlan = new TestPlan("Performance Test - Contacte Service");

            // Build Test Plan tree
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);

            // Add samplers to Thread Group
            threadGroupTree.add(getSampler);
            threadGroupTree.add(getLaboratoireSampler);
            threadGroupTree.add(getAdresseSampler);
            threadGroupTree.add(postSampler);
            threadGroupTree.add(putSampler);
            threadGroupTree.add(deleteSampler);

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
        sampler.setPort(8084); // Remplacez par le port utilisé par votre Spring Boot application
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
