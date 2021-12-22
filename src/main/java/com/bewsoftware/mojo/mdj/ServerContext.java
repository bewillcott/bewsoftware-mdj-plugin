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

/**
 * ServerContext class is used by the {@link MdjPublishMojo} class to obtain
 * specific context information through the {@code pom.xml}.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 0.29.9
 * @version 1.0.30
 */
public class ServerContext
{

    /**
     * The HTTP Server context to publish your files to.
     */
    private String context;

    /**
     * The HTML source.
     * <p>
     * This is either the directory containing the HTML files to publish,
     * or it is the path to the 'jar' file (including it's filename and
     * extension).
     */
    private String htmlSource;

    /**
     * Create an instance of the class.
     */
    public ServerContext()
    {
    }

    /**
     * The server context to publish your files to.
     *
     * @return the value.
     */
    public String context()
    {
        return context;
    }

    /**
     * The HTML source.
     * <p>
     * This is either the directory containing the HTML files to publish,
     * or it is the path to the 'jar' file (including it's filename and
     * extension).
     *
     * @return the directory.
     */
    public String htmlSource()
    {
        return htmlSource;
    }

    @Override
    public String toString()
    {
        return context + " = " + htmlSource;
    }
}
