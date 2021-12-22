/*
 * 'BEWSoftware MDj Maven Plugin' is a wrapper Maven plugin for the
 * 'BEWSoftware MDj Cli' program.
 *
 * Copyright (C) 2020, 2021 Bradley Willcott <mailto:bw.opensource@yahoo.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.bewsoftware.mojo.mdj;

import com.bewsoftware.common.InvalidParameterValueException;
import com.bewsoftware.common.InvalidProgramStateException;
import com.bewsoftware.mdj.cli.Main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import static org.apache.maven.plugins.annotations.LifecyclePhase.GENERATE_RESOURCES;

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
 *       &lt;plugin&gt;
 *          &lt;groupId&gt;com.bewsoftware.mojo&lt;/groupId&gt;
 *          &lt;artifactId&gt;mdj-maven-plugin&lt;/artifactId&gt;
 *          &lt;version&gt;1.0.0&lt;/version&gt;
 *          &lt;executions&gt;
 *            &lt;execution&gt;
 *              &lt;id&gt;MDj-Compile&lt;/id&gt;
 *              &lt;goals&gt;
 *                &lt;goal&gt;mdj&lt;/goal&gt;
 *              &lt;/goals&gt;
 *              &lt;configuration&gt;
 *                &lt;source&gt;src/docs/manual&lt;/source&gt;
 *                &lt;destination&gt;target/docs/manual&lt;/destination&gt;
 *                &lt;verbosity&gt;1&lt;/verbosity&gt;
 *              &lt;/configuration&gt;
 *            &lt;/execution&gt;
 *          &lt;/executions&gt;
 *       &lt;/plugin&gt;
 *       ...
 *    &lt;/plugins&gt;
 *  &lt;/build&gt;
 *&lt;/project&gt;
 * </code></pre>
 * I suggest that putting the plugin into a separate profile would be a good
 * idea, so it only runs when you need it to.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 0.1
 * @version 0.1
 */
@Mojo(name = "mdj", defaultPhase = GENERATE_RESOURCES)
public class MdjMojo extends AbstractMojo
{

    /**
     * The destination directory for HTML files.
     */
    @Parameter(property = "mdj.directory.destination", defaultValue = "target/docs")
    private String destination;

    /**
     * The maven project.
     */
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    /**
     * Recursively process directories.
     */
    @Parameter(defaultValue = "true")
    private boolean recursive;

    /**
     * The source directory for markdown files.
     */
    @Parameter(property = "mdj.directory.source", defaultValue = "src/docs")
    private String source;

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

    /**
     * Process meta block, wrapping your document with templates and style
     * sheets.
     */
    @Parameter(defaultValue = "true")
    private boolean wrapper;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException
    {
        getLog().info("MDj Maven Plugin");
        getLog().info("================");

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

        // pom.xml file
        args.add("-P");
        args.add("pom.xml");

        args.add("-D");
        args.add("filename=" + project.getBuild().getFinalName() + ".jar");

        getLog().info(args.toString());

        try
        {
            int exitcode = Main.execute(args.toArray(String[]::new));

            if (exitcode == 4)
            {
                getLog().info("Initializing wrapper directories and files...");

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

                exitcode = Main.execute(args2.toArray(String[]::new));

                if (exitcode == 0)
                {
                    getLog().info("Wrapper initialization complete.");
                }
            }

            getLog().info("Exit: " + exitcode);
        } catch (IOException | InvalidParameterValueException | InvalidProgramStateException ex)
        {
            getLog().error(ex);
        }
    }
}
