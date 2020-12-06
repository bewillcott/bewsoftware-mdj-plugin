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
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugin.logging.Log;

import static org.apache.maven.plugins.annotations.LifecyclePhase.PREPARE_PACKAGE;

/**
 * MdjMojo class executes the underlying program, causing it to process
 * the markdown files (*.md) in the {@link #source} directory,
 * storing the output files (*.html) in the {@link #destination} directory.
 * <p>
 * Additional options are available for:
 * <ul>
 * <li>{@link #recursive} directory processing,</li>
 * <li>{@link #wrapper} processing and</li>
 * <li>{@link #verbosity} setting.</li>
 * </ul>
 * <p>
 * <b>To use:</b><br>
 * Add the following to your {@code pom.xml} file.
 * <pre><code>
 *&lt;project&gt;
 *  ...
 *  &lt;build&gt;
 *    &lt;plugins&gt;
 *      &lt;plugin&gt;
 *          &lt;plugin&gt;
 *             &lt;groupId&gt;com.bewsoftware.mojo&lt;/groupId&gt;
 *             &lt;artifactId&gt;mdj-maven-plugin&lt;/artifactId&gt;
 *             &lt;version&gt;1.0.7&lt;/version&gt;
 *             &lt;executions&gt;
 *                 &lt;execution&gt;
 *                     &lt;goals&gt;
 *                         &lt;goal&gt;mdj&lt;/goal&gt;
 *                     &lt;/goals&gt;
 *                 &lt;/execution&gt;
 *             &lt;/executions&gt;
 *         &lt;/plugin&gt;
 *         ...
 *      &lt;/plugin&gt;
 *    &lt;/plugins&gt;
 *  &lt;/build&gt;
 *&lt;/project&gt;
 * </code></pre>
 * I suggest that putting it into a separate profile would be a good idea,
 * so it only runs when you need it to.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 0.1
 * @version 0.1
 */
@Mojo(name = "mdj", defaultPhase = PREPARE_PACKAGE)
public class MdjMojo extends AbstractMojo {

    /**
     * Define a static logger variable so that it references the
     * Logger instance named "CreateXMLMojoTest".
     */
    private final Log log = getLog();

    /**
     * The source directory for markdown files.
     */
    @Parameter(property = "mdj.directory.source", defaultValue = "src/docs")
    private String source;

    /**
     * The destination directory for HTML files.
     */
    @Parameter(property = "mdj.directory.destination", defaultValue = "target/docs")
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
            args.add("-v");
            args.add("" + verbosity);
        }

        log.info(args.toString());

        try
        {
            int exitcode = Main.execute(args.toArray(new String[0]));

            if (exitcode == 4)
            {
                log.info("Initializing wrapper directories and files...");

                List<String> args2 = new ArrayList<>();
                if (source.isBlank())
                {
                    args2.add("-W");
                } else
                {
                    args2.add("-W");
                    args2.add(source);
                }

                if (verbosity > 0)
                {
                    args2.add("-v");
                    args2.add("" + verbosity);
                }

                exitcode = Main.execute(args2.toArray(new String[0]));

                if (exitcode == 0)
                {
                    log.info("Wrapper initialization complete.");
                }
            }

            log.info("Exit: " + exitcode);
        } catch (IOException | InvalidParameterValueException
                 | IniFileFormatException | InvalidProgramStateException
                 | URISyntaxException ex)
        {
            log.error(MdjMojo.class.getName(), ex);
        }
    }
}
