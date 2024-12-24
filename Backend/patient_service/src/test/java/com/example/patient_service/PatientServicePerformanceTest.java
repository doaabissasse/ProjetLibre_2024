package com.example.patient_service;

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

public class PatientServicePerformanceTest {

    @org.junit.jupiter.api.Test
    public void testPatientServicePerformance() {
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

            // Add HTTP GET Sampler for /api/patients
            HTTPSamplerProxy getAllPatientsSampler = createHttpSampler("GET", "/api/patients", null);

            // Add HTTP GET Sampler for /api/patients/{id}
            HTTPSamplerProxy getPatientByIdSampler = createHttpSampler("GET", "/api/patients/1", null);

            // Add HTTP POST Sampler for creating a new patient
            HTTPSamplerProxy createPatientSampler = createHttpSampler("POST", "/api/patients", "{\"id\":1,\"name\":\"Test Patient\"}");

            // Add HTTP PUT Sampler for updating a patient
            HTTPSamplerProxy updatePatientSampler = createHttpSampler("PUT", "/api/patients/1", "{\"id\":1,\"name\":\"Updated Patient\"}");

            // Add HTTP DELETE Sampler
            HTTPSamplerProxy deletePatientSampler = createHttpSampler("DELETE", "/api/patients/1", null);

            // Add HTTP GET Sampler for /api/patients/{idPatient}/dossiers
            HTTPSamplerProxy getDossiersByPatientSampler = createHttpSampler("GET", "/api/patients/1/dossiers", null);

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
            TestPlan testPlan = new TestPlan("Performance Test - Patient Service");

            // Build Test Plan tree
            testPlanTree.add(testPlan);
            HashTree threadGroupTree = testPlanTree.add(threadGroup);

            // Add samplers to Thread Group
            threadGroupTree.add(getAllPatientsSampler);
            threadGroupTree.add(getPatientByIdSampler);
            threadGroupTree.add(createPatientSampler);
            threadGroupTree.add(updatePatientSampler);
            threadGroupTree.add(deletePatientSampler);
            threadGroupTree.add(getDossiersByPatientSampler);

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
        sampler.setPort(8089); // Replace with the port used by your Spring Boot application
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
