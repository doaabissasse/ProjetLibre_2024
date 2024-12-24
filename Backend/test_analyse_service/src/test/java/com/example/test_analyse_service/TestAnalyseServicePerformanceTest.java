package com.example.test_analyse_service;

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

public class TestAnalyseServicePerformanceTest {

    @org.junit.jupiter.api.Test
    public void testTestAnalyseServicePerformance() {
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

            // Add HTTP GET Sampler for /api/test-analyses
            HTTPSamplerProxy getAllTestAnalysesSampler = createHttpSampler("GET", "/api/test-analyses", null);

            // Add HTTP GET Sampler for /api/test-analyses/{id}
            HTTPSamplerProxy getTestAnalyseByIdSampler = createHttpSampler("GET", "/api/test-analyses/1", null);

            // Add HTTP POST Sampler for creating a new TestAnalyse
            HTTPSamplerProxy createTestAnalyseSampler = createHttpSampler("POST", "/api/test-analyses", "{\"id\":1,\"name\":\"Test Analyse\"}");

            // Add HTTP PUT Sampler for updating a TestAnalyse
            HTTPSamplerProxy updateTestAnalyseSampler = createHttpSampler("PUT", "/api/test-analyses/1", "{\"id\":1,\"name\":\"Updated Test Analyse\"}");

            // Add HTTP DELETE Sampler
            HTTPSamplerProxy deleteTestAnalyseSampler = createHttpSampler("DELETE", "/api/test-analyses/1", null);

            // Add HTTP GET Sampler for /api/test-analyses/{idTestAnalyse}/examens
            HTTPSamplerProxy getExamensByTestAnalyseSampler = createHttpSampler("GET", "/api/test-analyses/1/examens", null);

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
            TestPlan testPlan = new TestPlan("Performance Test - TestAnalyse Service");

            // Build Test Plan tree
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);

            // Add samplers to Thread Group
            threadGroupTree.add(getAllTestAnalysesSampler);
            threadGroupTree.add(getTestAnalyseByIdSampler);
            threadGroupTree.add(createTestAnalyseSampler);
            threadGroupTree.add(updateTestAnalyseSampler);
            threadGroupTree.add(deleteTestAnalyseSampler);
            threadGroupTree.add(getExamensByTestAnalyseSampler);

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
        sampler.setPort(8092); // Replace with the port used by your Spring Boot application
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
