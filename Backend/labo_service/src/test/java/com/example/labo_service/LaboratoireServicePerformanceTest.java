package com.example.labo_service;

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

public class LaboratoireServicePerformanceTest {

    @org.junit.jupiter.api.Test
    public void testLaboratoireServicePerformance() {
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

            // Add HTTP GET Sampler for /laboratoires
            HTTPSamplerProxy getAllLaboratoiresSampler = createHttpSampler("GET", "/laboratoires", null);

            // Add HTTP GET Sampler for /laboratoires/{id}
            HTTPSamplerProxy getLaboratoireByIdSampler = createHttpSampler("GET", "/laboratoires/1", null);

            // Add HTTP POST Sampler for creating a new laboratoire
            HTTPSamplerProxy createLaboratoireSampler = createHttpSampler("POST", "/laboratoires", "{\"id\":1,\"nom\":\"Test Laboratoire\"}");

            // Add HTTP PUT Sampler for updating an laboratoire
            HTTPSamplerProxy updateLaboratoireSampler = createHttpSampler("PUT", "/laboratoires/1", "{\"id\":1,\"nom\":\"Updated Laboratoire\"}");

            // Add HTTP DELETE Sampler
            HTTPSamplerProxy deleteLaboratoireSampler = createHttpSampler("DELETE", "/laboratoires/1", null);

            // Add HTTP GET Sampler for /laboratoires/contactes/{labo_id}
            HTTPSamplerProxy getLaboratoireContactesSampler = createHttpSampler("GET", "/laboratoires/contactes/1", null);

            // Add HTTP GET Sampler for /laboratoires/users/{labo_id}
            HTTPSamplerProxy getLaboratoireUsersSampler = createHttpSampler("GET", "/laboratoires/users/1", null);

            // Add HTTP GET Sampler for /laboratoires/{idLabo}/analyses
            HTTPSamplerProxy getAnalysesByLaboratoireIdSampler = createHttpSampler("GET", "/laboratoires/1/analyses", null);

            // Add HTTP GET Sampler for /laboratoires/search
            HTTPSamplerProxy searchLaboratoiresSampler = createHttpSampler("GET", "/laboratoires/search?nom=Test Laboratoire", null);

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
            TestPlan testPlan = new TestPlan("Performance Test - Laboratoire Service");

            // Build Test Plan tree
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);

            // Add samplers to Thread Group
            threadGroupTree.add(getAllLaboratoiresSampler);
            threadGroupTree.add(getLaboratoireByIdSampler);
            threadGroupTree.add(createLaboratoireSampler);
            threadGroupTree.add(updateLaboratoireSampler);
            threadGroupTree.add(deleteLaboratoireSampler);
            threadGroupTree.add(getLaboratoireContactesSampler);
            threadGroupTree.add(getLaboratoireUsersSampler);
            threadGroupTree.add(getAnalysesByLaboratoireIdSampler);
            threadGroupTree.add(searchLaboratoiresSampler);

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
        sampler.setPort(8083); // Replace with the port used by your Spring Boot application
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
