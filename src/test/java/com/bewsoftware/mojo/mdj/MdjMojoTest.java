/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bewsoftware.mojo.mdj;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 */
public class MdjMojoTest {

    /**
     * Define a static logger variable so that it references the
     * Logger instance named "MdjMojoTest".
     */
    private static final Logger log = LogManager.getLogger();

    @Rule
    public MojoRule rule = new MojoRule() {
        @Override
        protected void before() throws Throwable {
        }

        @Override
        protected void after() {
        }
    };

    /**
     * Test of mdj goal, of class MdjMojo.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testMdjGoal() throws Exception {
        log.traceEntry();

        File baseDir = new File("target/test-classes/unit/project-to-test");
        assertNotNull(baseDir);
        log.debug("baseDir: {}", baseDir);
        assertTrue(baseDir.exists());

        MdjMojo myMojo = (MdjMojo) rule.lookupConfiguredMojo(baseDir, "mdj");
        assertNotNull(myMojo);

        log.debug("myMojo: {}", myMojo);

        myMojo.execute();

        log.traceExit();
    }
}
