package com.example.epreuve_service;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jorphan.collections.HashTree;

import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

public class EpreuveServicePerformanceTest {

    @org.junit.jupiter.api.Test
    public void testEpreuveServicePerformance() {
        try {
            // Load JMeter properties
            String propertiesPath = new File("src/test/resources/jmeter.properties").getAbsolutePath();
            org.apache.jmeter.util.JMeterUtils.loadJMeterProperties(propertiesPath);
            org.apache.jmeter.util.JMeterUtils.initLocale();
            org.apache.jmeter.util.JMeterUtils.initLogging();

            // Initialize JMeter Engine
            StandardJMeterEngine jmeter = new StandardJMeterEngine();

            // Create Test Plan tree
            HashTree testPlanTree = new HashTree();

            // Add HTTP GET Sampler for /api/epreuves
            HTTPSamplerProxy getAllEpreuvesSampler = createHttpSampler("GET", "/api/epreuves", null);

            // Add HTTP GET Sampler for /api/epreuves/{id}
            HTTPSamplerProxy getEpreuveByIdSampler = createHttpSampler("GET", "/api/epreuves/1", null);

            // Add HTTP POST Sampler for creating a new epreuve
            HTTPSamplerProxy createEpreuveSampler = createHttpSampler("POST", "/api/epreuves", "{\"id\":1,\"nom\":\"Test Epreuve\"}");

            // Add HTTP PUT Sampler for updating an epreuve
            HTTPSamplerProxy updateEpreuveSampler = createHttpSampler("PUT", "/api/epreuves/1", "{\"id\":1,\"nom\":\"Updated Epreuve\"}");

            // Add HTTP DELETE Sampler
            HTTPSamplerProxy deleteEpreuveSampler = createHttpSampler("DELETE", "/api/epreuves/1", null);

            // Add HTTP GET Sampler for /api/epreuves/by-analyse/{idAnalyse}
            HTTPSamplerProxy getEpreuvesByIdAnalyseSampler = createHttpSampler("GET", "/api/epreuves/by-analyse/1", null);

            // Add HTTP GET Sampler for /api/epreuves/{id}/examens
            HTTPSamplerProxy getExamensByEpreuveIdSampler = createHttpSampler("GET", "/api/epreuves/1/examens", null);

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
            TestPlan testPlan = new TestPlan("Performance Test - Epreuve Service");

            // Build Test Plan tree
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);

            // Add samplers to Thread Group
            threadGroupTree.add(getAllEpreuvesSampler);
            threadGroupTree.add(getEpreuveByIdSampler);
            threadGroupTree.add(createEpreuveSampler);
            threadGroupTree.add(updateEpreuveSampler);
            threadGroupTree.add(deleteEpreuveSampler);
            threadGroupTree.add(getEpreuvesByIdAnalyseSampler);
            threadGroupTree.add(getExamensByEpreuveIdSampler);

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
        sampler.setDomain("localhost"); // Replace with your service domain
        sampler.setPort(8088); // Replace with the port used by your Spring Boot application
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
