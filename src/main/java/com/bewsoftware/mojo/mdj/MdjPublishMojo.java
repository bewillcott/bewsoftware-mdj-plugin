/*
 * Copyright (C) 2020 <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
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

import com.bewsoftware.fileio.ini.IniFileFormatException;
import com.bewsoftware.mdj.cli.Main;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_RESOURCES;

/**
 * MdjPublishMojo class executes the underlying program, causing it to
 * publish the files from either a directory in the file system, or from
 * a 'jar' file.
 * <p>
 * <b>To use:</b><br>
 * Add the following to your {@code pom.xml} file.
 * <pre><code>
 *&lt;!-- Preferred setup using one or more separate profiles &gt;
 *&lt;project ...&gt;
 *  ...
 *  &lt;profiles&gt;
 *    &lt;profile&gt;
 *      &lt;id&gt;publish-manual&lt;/id&gt;
 *      &lt;build&gt;
 *        &lt;plugins&gt;
 *          &lt;plugin&gt;
 *            &lt;groupId&gt;com.bewsoftware.mojo&lt;/groupId&gt;
 *            &lt;artifactId&gt;bewsoftware-mdj-plugin&lt;/artifactId&gt;
 *            &lt;version&gt;1.0.0&lt;/version&gt;
 *            &lt;executions&gt;
 *              &lt;execution&gt;
 *                &lt;id&gt;MDj-Publish-Manual&lt;/id&gt;
 *                &lt;goals&gt;
 *                  &lt;goal&gt;mdj&lt;/goal&gt;
 *                  &lt;goal&gt;publish&lt;/goal&gt;
 *                &lt;/goals&gt;
 *                &lt;configuration&gt;
 *                  &lt;serverContexts&gt;
 *                    &lt;serverContext&gt;
 *                      &lt;context&gt;/&lt;/context&gt;
 *                      &lt;htmlSource&gt;target/docs&lt;/htmlSource&gt;
 *                    &lt;/serverContext&gt;
 *                    &lt;serverContext&gt;
 *                      &lt;context&gt;/api&lt;/context&gt;
 *                      &lt;htmlSource&gt;target/${project.build.finalName}-javadoc.jar&lt;/htmlSource&gt;
 *                    &lt;/serverContext&gt;
 *                  &lt;/serverContexts&gt;
 *                &lt;/configuration&gt;
 *              &lt;/execution&gt;
 *            &lt;/executions&gt;
 *          &lt;/plugin&gt;
 *        &lt;/plugins&gt;
 *      &lt;/build&gt;
 *    &lt;/profile&gt;
 *  &lt;/profiles&gt;
 *  ...
 *&lt;/project&gt;
 *
 *&lt;!-- Alternative setup placing the plugin into the default build profile &gt;
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
 *              &lt;id&gt;MDj-Publish-Manual&lt;/id&gt;
 *              &lt;goals&gt;
 *                &lt;goal&gt;publish&lt;/goal&gt;
 *              &lt;/goals&gt;
 *              &lt;configuration&gt;
 *                &lt;serverContexts&gt;
 *                  &lt;serverContext&gt;
 *                    &lt;context&gt;/&lt;/context&gt;
 *                    &lt;htmlSource&gt;target/docs&lt;/htmlSource&gt;
 *                  &lt;/serverContext&gt;
 *                &lt;/serverContexts&gt;
 *              &lt;/configuration&gt;
 *            &lt;/execution&gt;
 *          &lt;/executions&gt;
 *       &lt;/plugin&gt;
 *       &lt;plugin&gt;
 *          &lt;groupId&gt;com.bewsoftware.mojo&lt;/groupId&gt;
 *          &lt;artifactId&gt;mdj-maven-plugin&lt;/artifactId&gt;
 *          &lt;version&gt;1.0.0&lt;/version&gt;
 *          &lt;executions&gt;
 *            &lt;execution&gt;
 *              &lt;id&gt;MDj-Publish-API&lt;/id&gt;
 *              &lt;goals&gt;
 *                &lt;goal&gt;publish&lt;/goal&gt;
 *              &lt;/goals&gt;
 *              &lt;configuration&gt;
 *                &lt;serverContexts&gt;
 *                  &lt;serverContext&gt;
 *                    &lt;context&gt;/api&lt;/context&gt;
 *                    &lt;htmlSource&gt;target/${project.build.finalName}-javadoc.jar&lt;/htmlSource&gt;
 *                  &lt;/serverContext&gt;
 *                &lt;/serverContexts&gt;
 *              &lt;/configuration&gt;
 *            &lt;/execution&gt;
 *          &lt;/executions&gt;
 *       &lt;/plugin&gt;
 *       ...
 *    &lt;/plugins&gt;
 *  &lt;/build&gt;
 *&lt;/project&gt;
 * </code></pre>
 * The configuration settings above are only a suggestion. You will need to
 * set them as you require.<br>
 * I suggest that putting the plugin into a separate profile would be a good idea,
 * so it only runs when you need it to.
 * <p>
 * NOTE: Combining them, as shown in the top example, publishes them under the one
 * server. Therefore, both are available using the same port number.
 * <p>
 * By putting them in separate plugins, as in the bottom example, each
 * is published under a separate instance of the server, with a different port
 * number.
 * <p>
 * Either configuration can be used in either profile setup.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 0.1.7
 * @version 1.0.33
 */
@Mojo(name = "publish", defaultPhase = PROCESS_RESOURCES)
public class MdjPublishMojo extends AbstractMojo {

    /**
     * Allow a directory listing to be generated, if no 'index' file found.
     */
    @Parameter(defaultValue = "false")
    private boolean allowGeneratedIndex;

    /**
     * Disallow web browsers caching the files sent by this instance of the web server.
     */
    @Parameter(defaultValue = "false")
    private boolean disallowBrowserFileCaching;

    /**
     * Define a static logger variable so that it references the
     * Logger instance named "CreateXMLMojoTest".
     */
    private final Log log = getLog();

    /**
     * List of ServerContext objects.
     * <p>
     * Add:
     * <pre><code>
     *  &lt;serverContexts&gt;
     *    &lt;serverContext&gt;
     *     &lt;context&gt;/&lt;/context&gt;
     *     &lt;htmlSource&gt;&lt;/htmlSource&gt;
     *   &lt;/serverContext&gt;
     *  &lt;/serverContexts&gt;
     * </code></pre>
     * with your specific settings.
     * <p>
     * What is shown above is the default, should you <i>not</i> include a
     * {@code <serverContexts>} entry.
     */
    @Parameter()
    private List<ServerContext> serverContexts;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        log.info("MDj Maven Plugin");
        log.info("================");

        // Setup parameter list for mdj-cli program.
        List<String> args = new ArrayList<>();

        if (allowGeneratedIndex)
        {
            args.add("--allowGeneratedIndex");
        }

        if (disallowBrowserFileCaching)
        {
            args.add("--disallowBrowserFileCaching");
        }

        if (serverContexts != null && !serverContexts.isEmpty())
        {
            serverContexts.forEach(conf ->
            {
                args.add("-p");
                args.add((conf.context() != null ? conf.context() : "/") + "="
                         + (conf.htmlSource() != null ? conf.htmlSource() : ""));
            });
        } else
        {
            args.add("-p");
            args.add("/=");
        }
        log.info(args.toString());

        // Execute the program code...
        try
        {
            int exitcode = Main.execute(args.toArray(new String[args.size()]));
            log.info("Exit: " + exitcode);
        } catch (IOException | IniFileFormatException | URISyntaxException ex)
        {
            log.error(MdjPublishMojo.class.getName(), ex);
            throw new MojoExecutionException("MDj CLI threw an exception:", ex);
        }
    }
}
