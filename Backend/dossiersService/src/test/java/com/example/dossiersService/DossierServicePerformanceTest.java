package com.example.dossiersService;

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

public class DossierServicePerformanceTest {

    @Test
    public void testDossierServicePerformance() {
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

            // Add HTTP GET Sampler for /api/dossiers
            HTTPSamplerProxy getAllDossiersSampler = createHttpSampler("GET", "/api/dossiers", null);

            // Add HTTP GET Sampler for /api/dossiers/{id}
            HTTPSamplerProxy getDossierByIdSampler = createHttpSampler("GET", "/api/dossiers/1", null);

            // Add HTTP PUT Sampler for updating a dossier
            HTTPSamplerProxy updateDossierSampler = createHttpSampler("PUT", "/api/dossiers/1", "{\"id\":1,\"nom\":\"Updated Dossier\"}");

            // Add HTTP DELETE Sampler
            HTTPSamplerProxy deleteDossierSampler = createHttpSampler("DELETE", "/api/dossiers/1", null);

            // Add HTTP GET Sampler for dossiers by patient ID
            HTTPSamplerProxy getDossiersByPatientIdSampler = createHttpSampler("GET", "/api/dossiers/patient/1", null);

            // Add HTTP GET Sampler for dossiers by utilisateur ID
            HTTPSamplerProxy getDossiersByUtilisateurIdSampler = createHttpSampler("GET", "/api/dossiers/by-utilisateur/1", null);

            // Add HTTP GET Sampler for examens by dossier ID
            HTTPSamplerProxy getExamensByDossierIdSampler = createHttpSampler("GET", "/api/dossiers/1/examens", null);

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
            TestPlan testPlan = new TestPlan("Performance Test - Dossier Service");

            // Build Test Plan tree
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);

            // Add samplers to Thread Group
            threadGroupTree.add(getAllDossiersSampler);
            threadGroupTree.add(getDossierByIdSampler);
            threadGroupTree.add(updateDossierSampler);
            threadGroupTree.add(deleteDossierSampler);
            threadGroupTree.add(getDossiersByPatientIdSampler);
            threadGroupTree.add(getDossiersByUtilisateurIdSampler);
            threadGroupTree.add(getExamensByDossierIdSampler);

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
        sampler.setPort(8093); // Replace with the port used by your Spring Boot application
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
