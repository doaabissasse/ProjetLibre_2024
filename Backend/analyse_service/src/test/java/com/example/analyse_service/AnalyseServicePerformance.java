package com.example.analyse_service;

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

public class AnalyseServicePerformance {

    @Test
    public void testAnalyseServicePerformance() {
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

            // Add HTTP GET Sampler
            HTTPSamplerProxy getSampler = createHttpSampler("GET", "/api/analyses", null);

            // Add HTTP POST Sampler
            String postPayload = "{\"name\": \"Test Analyse\", \"description\": \"Performance Test Analyse\"}";
            HTTPSamplerProxy postSampler = createHttpSampler("POST", "/api/analyses", postPayload);

            // Add HTTP PUT Sampler
            String putPayload = "{\"name\": \"Updated Analyse\", \"description\": \"Updated Description\"}";
            HTTPSamplerProxy putSampler = createHttpSampler("PUT", "/api/analyses/1", putPayload);

            // Add HTTP DELETE Sampler
            HTTPSamplerProxy deleteSampler = createHttpSampler("DELETE", "/api/analyses/1", null);

            // Configure Loop Controller
            LoopController loopController = new LoopController();
            loopController.setLoops(2); // Number of loops
            loopController.setFirst(true);
            loopController.initialize();

            // Configure Thread Group
            ThreadGroup threadGroup = new ThreadGroup();
            threadGroup.setNumThreads(10); // Number of users
            threadGroup.setRampUp(5); // Ramp-up period in seconds
            threadGroup.setSamplerController(loopController);

            // Configure Test Plan
            TestPlan testPlan = new TestPlan("Performance Test - Analyse Service");

            // Build Test Plan tree
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);

            // Add samplers to Thread Group
            threadGroupTree.add(getSampler);
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
        sampler.setDomain("localhost");
        sampler.setPort(8087);
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