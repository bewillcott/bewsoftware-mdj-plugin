/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bewsoftware.mojo.mdj;

import com.bewsoftware.common.InvalidParameterValueException;
import com.bewsoftware.common.InvalidProgramStateException;
import com.bewsoftware.fileio.ini.IniFileFormatException;
import com.bewsoftware.mdj.cli.Main;
import com.martiansoftware.jsap.JSAPException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static org.apache.maven.plugins.annotations.LifecyclePhase.COMPILE;

/**
 * MdjMojo class description.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 0.1
 * @version 0.1
 */
@Mojo(name = "mdj", defaultPhase = COMPILE)
public class MdjMojo extends AbstractMojo {

    /**
     * Define a static logger variable so that it references the
     * Logger instance named "CreateXMLMojoTest".
     */
    private static final Logger log = LogManager.getLogger();

    /**
     * The source directory for markdown files.
     */
    @Parameter(defaultValue = "src/docs")
    private String source;

    /**
     * The destination directory for HTML files.
     */
    @Parameter(defaultValue = "target/docs")
    private String destination;

    /**
     * Process meta block, wrapping your document with templates and style sheets.
     */
    @Parameter(defaultValue = "true")
    private boolean wrapper;

    /**
     * Recursively process directories.
     */
    @Parameter(defaultValue = "true")
    private boolean recursive;

    /**
     * Set the level of verbosity.
     * <ul>
     * <li>'0' is off.</li>
     * <li>'1' - '3' are active levels, from limited
     * information to a lot of information.</li>
     * </ul>
     */
    @Parameter(defaultValue = "0")
    private int verbosity;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        log.info("MDj Maven Plugin");
        log.info("================");

        List<String> args = new ArrayList<>();

        if (!source.isBlank())
        {
            args.add("-s");
            args.add(source);
        }

        if (!destination.isBlank())
        {
            args.add("-d");
            args.add(destination);
        }

        if (wrapper)
        {
            args.add("-w");
        }

        if (recursive)
        {
            args.add("-r");
        }

        if (verbosity > 0)
        {
            args.add("-v:" + verbosity);
        }

        log.info(args.toString());

        try
        {
            int exitcode = Main.execute(args.toArray(new String[0]));

            if (exitcode == 4)
            {
                List<String> args2 = new ArrayList<>();
                if (source.isBlank())
                {
                    args2.add("-W");
                } else
                {
                    args2.add("-W:" + source);
                }

                exitcode = Main.execute(args2.toArray(new String[0]));

            }

            log.info("Exit: " + exitcode);
        } catch (IOException | JSAPException | InvalidParameterValueException
                 | IniFileFormatException | InvalidProgramStateException
                 | URISyntaxException ex)
        {
            log.error(MdjMojo.class.getName(), ex);
        }
    }
}
