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

import com.bewsoftware.mdj.cli.Main;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static java.nio.file.Path.of;
import static org.apache.maven.plugins.annotations.LifecyclePhase.PACKAGE;

/**
 * MdjJarMojo class executes the underlying program, causing it to
 * archive the files in the {@link #jarSrcDir} directory tree
 * into a new jar file: {@link #jarFilename}.
 * <p>
 * An additional option is available for:
 * <ul>
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
 *              &lt;id&gt;MDj-Pack&lt;/id&gt;
 *              &lt;goals&gt;
 *                &lt;goal&gt;jar&lt;/goal&gt;
 *              &lt;/goals&gt;
 *              &lt;configuration&gt;
 *                &lt;jarFilename&gt;${project.build.finalName}-manual-src.jar&lt;/jarFilename&gt;
 *                &lt;jarSrcDir&gt;src/docs/manual&lt;/jarSrcDir&gt;
 *                &lt;docRootDir&gt;src/docs/manual&lt;/docRootDir&gt;
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
 * @since 0.1.7
 * @version 0.24.0
 */
@Mojo(name = "jar", defaultPhase = PACKAGE)
public class MdjJarMojo extends AbstractMojo
{

    /**
     * The project build output directory.
     */
    @Parameter(defaultValue = "${project.build.directory}", readonly = true)
    private String buildDir;

    /**
     * The document root directory.
     * <p>
     * This is the same directory as the root of your document source files
     * (*.md).<br>
     * For instance it might be: {@code src/docs/manual}. This must be supplied
     * even if you are packaging your document source files, giving the same
     * directory as for: {@link #jarSrcDir}. The program makes <b>no</b>
     * assumptions.
     */
    @Parameter(required = true)
    private String docRootDir;

    /**
     * The name of the new 'jar' file.
     * <p>
     * <b>File location:</b><br>
     * If you do not include a directory path, then it will be created in the
     * {@code ${project.build.directory}}. If you give a relative directory
     * path, then it will be taken to being relative to the project directory
     * where the {@code pom.xml} file is located.
     * <p>
     * <b>File name:</b><br>
     * You must provide the full name of the file including the extension,
     * whether or not you provide a directory path. This will NOT be vetted.
     * <p>
     * <b>Suggestions:</b><br>
     * To pack up a copy of your HTML files:<br>
     * if your 'destination' directory (*.html files) is:
     * {@code target/docs/manual},<br>
     * then a possible setting might be:
     * {@code <jarFilename>myprog-1.0-manual.jar</jarFilename>}.
     * <p>
     * To pack up a copy of your Markdown files:<br>
     * if your 'source' directory (*.md files) is: {@code src/docs/manual},<br>
     * then a possible setting might be:
     * {@code <jarFilename>myprog-1.0-manual-src.jar</jarFilename>}.
     */
    @Parameter(required = true)
    private String jarFilename;

    /**
     * The source directory for the files to be included in the new 'jar' file.
     * <p>
     * Any relative path will be taken as being relative to the project
     * directory where the {@code pom.xml} file is located.
     * <p>
     * <b>Recursion:</b><br>
     * This source directory and all subdirectories will be included in the jar
     * file. There is currently NO option to just pack up the specific
     * directory.
     * <p>
     * <b>Suggestions:</b><br>
     * To pack up a copy of your HTML files:<br>
     * if your 'destination' directory (*.html files) is:
     * {@code target/docs/manual},<br>
     * then set the following:
     * {@code <jarSrcDir>target/docs/manual</jarSrcDir>}.
     * <p>
     * To pack up a copy of your Markdown files:<br>
     * if your 'source' directory (*.md files) is: {@code src/docs/manual},<br>
     * then set the following: {@code <jarSrcDir>src/docs/manual</jarSrcDir>}.
     */
    @Parameter(required = true)
    private String jarSrcDir;

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
    public void execute() throws MojoExecutionException, MojoFailureException
    {
        getLog().info("MDj Maven Plugin");
        getLog().info("================");

        // Setup parameter list for mdj-cli program.
        List<String> args = new ArrayList<>();

        args.add("-j");
        args.add(prepareJarFilePath() + ";"
                + prepareDirectoryPath(jarSrcDir) + ";"
                + prepareDirectoryPath(docRootDir));

        if (verbosity > 0)
        {
            args.add("-v");
            args.add("" + verbosity);
        }

        getLog().info(args.toString());

        // Execute the program code...
        try
        {
            int exitcode = Main.execute(args.toArray(String[]::new));
            getLog().info("Exit: " + exitcode);
        } catch (IOException ex)
        {
            getLog().error(MdjJarMojo.class.getName(), ex);
            throw new MojoExecutionException("MDj CLI threw an exception:", ex);
        }
    }

    /**
     * Check the directory's path and make it absolute.
     *
     * @return full path to directory.
     */
    private String prepareDirectoryPath(String directory)
    {
        String rtn;
        Path dirPath = of(directory).normalize();

        if (dirPath.isAbsolute())
        {
            rtn = dirPath.toString();
        } else
        {
            rtn = dirPath.toAbsolutePath().toString();
        }

        return rtn;
    }

    /**
     * Check the path of the {@link #jarFilename} and prepend as needed, making
     * it absolute.
     *
     * @return full filename.
     */
    private String prepareJarFilePath()
    {
        String rtn;
        Path jarFilePath = of(jarFilename).normalize();

        if (jarFilePath.isAbsolute())
        {
            rtn = jarFilePath.toString();
        } else if (jarFilePath.getParent() == null)
        {
            rtn = of(buildDir, jarFilePath.toString()).toString();
        } else
        {
            rtn = jarFilePath.toAbsolutePath().toString();
        }

        return rtn;
    }
}
