package com.example.examen_service;

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

public class ExamenServicePerformanceTest {

    @org.junit.jupiter.api.Test
    public void testExamenServicePerformance() {
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

            // Add HTTP GET Sampler for /api/examens
            HTTPSamplerProxy getAllExamensSampler = createHttpSampler("GET", "/api/examens", null);

            // Add HTTP GET Sampler for /api/examens/{id}
            HTTPSamplerProxy getExamenByIdSampler = createHttpSampler("GET", "/api/examens/1", null);

            // Add HTTP POST Sampler for creating a new examen
            HTTPSamplerProxy createExamenSampler = createHttpSampler("POST", "/api/examens", "{\"id\":1,\"nom\":\"Test Examen\"}");

            // Add HTTP PUT Sampler for updating an examen
            HTTPSamplerProxy updateExamenSampler = createHttpSampler("PUT", "/api/examens/1", "{\"id\":1,\"nom\":\"Updated Examen\"}");

            // Add HTTP DELETE Sampler
            HTTPSamplerProxy deleteExamenSampler = createHttpSampler("DELETE", "/api/examens/1", null);

            // Add HTTP GET Sampler for /api/examens/by-test-analyse/{idTestAnalyse}
            HTTPSamplerProxy getExamensByIdTestAnalyseSampler = createHttpSampler("GET", "/api/examens/by-test-analyse/1", null);

            // Add HTTP GET Sampler for /api/examens/by-dossier/{idDossier}
            HTTPSamplerProxy getExamensByIdDossierSampler = createHttpSampler("GET", "/api/examens/by-dossier/1", null);

            // Add HTTP GET Sampler for /api/examens/by-epreuve/{idEpreuve}
            HTTPSamplerProxy getExamensByIdEpreuveSampler = createHttpSampler("GET", "/api/examens/by-epreuve/1", null);

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
            TestPlan testPlan = new TestPlan("Performance Test - Examen Service");

            // Build Test Plan tree
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);

            // Add samplers to Thread Group
            threadGroupTree.add(getAllExamensSampler);
            threadGroupTree.add(getExamenByIdSampler);
            threadGroupTree.add(createExamenSampler);
            threadGroupTree.add(updateExamenSampler);
            threadGroupTree.add(deleteExamenSampler);
            threadGroupTree.add(getExamensByIdTestAnalyseSampler);
            threadGroupTree.add(getExamensByIdDossierSampler);
            threadGroupTree.add(getExamensByIdEpreuveSampler);

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
        sampler.setPort(8091); // Replace with the port used by your Spring Boot application
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
