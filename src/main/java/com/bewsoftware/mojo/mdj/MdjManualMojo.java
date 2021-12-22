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
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import static org.apache.maven.plugins.annotations.LifecyclePhase.INITIALIZE;

/**
 * MdjManualMojo class executes the underlying program, causing it to
 * launch it's internal HTTP Server, publishing the manual stored
 * in the program's 'jar' file.
 * <p>
 * In addition, it will launch your system default web browser, to
 * connect to the server just started. If however, you have set a
 * different program to be your default, you can still connect your
 * favorite browser to the server by setting the address to:
 * <pre><code>
 *
 *  http://localhost:&lt;port&gt;
 * </code></pre>
 * The port number will be found in the dialog box that will also
 * have a button to stop the server. You can start and stop the
 * server as many times as you want/need. The only limit, is it
 * has been set to use a port in the range: 9000 - 9010 (inclusive).
 * So, at most you can only have 11 copies running at once. Though
 * why you would need that I have no idea. Since this is a proper
 * compliant web server, it can take multiple client connections on
 * the one port.
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
 *              &lt;id&gt;MDj-Manual&lt;/id&gt;
 *              &lt;goals&gt;
 *                &lt;goal&gt;manual&lt;/goal&gt;
 *              &lt;/goals&gt;
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
 * @version 0.29.9
 */
@Mojo(name = "manual", defaultPhase = INITIALIZE)
public class MdjManualMojo extends AbstractMojo
{

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException
    {
        getLog().info("MDj Maven Plugin");
        getLog().info("================");

        // Setup parameter list for mdj-cli program.
        List<String> args = new ArrayList<>();

        args.add("-m");

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

}
