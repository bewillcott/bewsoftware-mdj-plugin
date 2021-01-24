/*
 * BEWSoftware MDj Maven Plugin is a wrapper Maven plugin for the
 * BEWSoftware MDj Cli program.
 *
 * Copyright (C) 2020 <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * This plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.bewsoftware.mojo.mdj;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.Path.of;
import static org.apache.maven.plugins.annotations.LifecyclePhase.INITIALIZE;

/**
 * MdjCleanMojo class deletes the destination directory.
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
 *              &lt;id&gt;MDj-Clean&lt;/id&gt;
 *              &lt;goals&gt;
 *                &lt;goal&gt;clean&lt;/goal&gt;
 *              &lt;/goals&gt;
 *              &lt;configuration&gt;
 *                &lt;destination&gt;target/docs/manual&lt;/destination&gt;
 *              &lt;/configuration&gt;
 *            &lt;/execution&gt;
 *          &lt;/executions&gt;
 *       &lt;/plugin&gt;
 *       ...
 *    &lt;/plugins&gt;
 *  &lt;/build&gt;
 *&lt;/project&gt;
 * </code></pre>
 * I suggest that putting the plugin into a separate profile would be a good idea,
 * so it only runs when you need it to.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
@Mojo(name = "clean", defaultPhase = INITIALIZE)
public class MdjCleanMojo extends AbstractMojo {

    /**
     * The destination directory for HTML files.
     */
    @Parameter(property = "mdj.directory.destination", defaultValue = "target/docs")
    private String destination;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("MDj Maven Plugin");
        getLog().info("================");

        Path destDirPath = of(destination).normalize().toAbsolutePath();

        if (Files.exists(destDirPath, NOFOLLOW_LINKS) && Files.isDirectory(destDirPath))
        {
            try
            {
                Files.walkFileTree(destDirPath, new SimpleFileVisitor<Path>() {

                               @Override
                               public FileVisitResult postVisitDirectory(Path dir, IOException ioe)
                                       throws IOException {
                                   if (ioe == null)
                                   {
                                       Files.delete(dir);
                                       return FileVisitResult.CONTINUE;
                                   } else
                                   {
                                       // directory iteration failed
                                       throw ioe;
                                   }
                               }

                               @Override
                               public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                                       throws IOException {
                                   Files.delete(file);
                                   return FileVisitResult.CONTINUE;
                               }
                           });
            } catch (IOException ex)
            {
                throw new MojoExecutionException("Directory iteration failed", ex);
            }
        }
    }
}
