@@@
use: articles2
title: ${document.name} | publish
@@@

# publish (goal)

This goal executes the underlying program, causing it to
publish the files from either a directory in the file system, or from
a 'jar' file.

## pom.xml  
Add the following to your `pom.xml` file.
~~~
<!-- Preferred setup using one or more separate profiles >
<project ...>
    ...
    <profiles>
        <profile>
            <id>publish-manual</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>com.bewsoftware.mojo</groupId>
                        <artifactId>bewsoftware-mdj-plugin</artifactId>
                        <version>1.0.0</version>
                        <executions>
                            <execution>
                                <id>MDj-Publish-Manual</id>
                                <goals>
                                    <goal>mdj</goal>
                                    <goal>publish</goal>
                                </goals>
                                <configuration>
                                    <serverContexts>
                                        <serverContext>
                                            <context>/</context>
                                            <htmlSource>target/docs</htmlSource>
                                        </serverContext>
                                        <serverContext>
                                            <context>/api</context>
                                            <htmlSource>
                                                target/${project.build.finalName}-javadoc.jar
                                            </htmlSource>
                                        </serverContext>
                                    </serverContexts>
                                </configuration>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
    ...
</project>

<!-- Alternative setup placing the plugin into the default build profile >
<project>
    ...
    <build>
        <plugins>
            <plugin>
                <groupId>com.bewsoftware.mojo</groupId>
                <artifactId>mdj-maven-plugin</artifactId>
                <version>1.0.0</version>
                <executions>
                    <execution>
                        <id>MDj-Publish-Manual</id>
                        <goals>
                            <goal>publish</goal>
                        </goals>
                        <configuration>
                            <serverContexts>
                                <serverContext>
                                    <context>/</context>
                                    <htmlSource>target/docs</htmlSource>
                                </serverContext>
                            </serverContexts>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>com.bewsoftware.mojo</groupId>
                <artifactId>mdj-maven-plugin</artifactId>
                <version>1.0.0</version>
                <executions>
                    <execution>
                        <id>MDj-Publish-API</id>
                        <goals>
                            <goal>publish</goal>
                        </goals>
                        <configuration>
                            <serverContexts>
                                <serverContext>
                                    <context>/api</context>
                                    <htmlSource>
                                        target/${project.build.finalName}-javadoc.jar
                                    </htmlSource>
                                </serverContext>
                            </serverContexts>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            ...
        </plugins>
    </build>
</project>
~~~

The configuration settings above are only a suggestion. You will need to
set them as you require.
I suggest that putting the plugin into a separate profile would be a good idea,
so it only runs when you need it to.

NOTE: Combining them, as shown in the top example, publishes them under the one
server. Therefore, both are available using the same port number.

By putting them in separate plugins, as in the bottom example,
publishes each under a separate instance of the server, with a different port
number.

Either configuration can be used in either profile setup.

## Configuration ## [][@upArrow](#top)

###[#agi] &lt;allowGeneratedIndex&gt;
|defaultValue|
|---|
|false|

Allow a directory listing to be generated, if no 'index' file found.

###[#dbfc] &lt;disallowBrowserFileCaching&gt;
|defaultValue|
|---|
|false|

Disallow web browsers caching the files sent by this instance of the web server.

###[#scs] &lt;serverContexts&gt;

List of ServerContext objects.

Add:
~~~
 <serverContexts>
   <serverContext>
    <context>/</context>
    <htmlSource></htmlSource>
  </serverContext>
 </serverContexts>
~~~
with your specific settings.

What is shown above is the default, should you _not_ include a
`<serverContexts>` entry.

###[#sc] &lt;serverContext&gt; ### [][@upArrow](#top)

The `serverContext` holds specific context information.  Each instance is
a different HTTP context to be made available through this instance of the
HTTP Server. It contains:

- `context`
- `htmlSource`

###[#cont] &lt;context&gt;

The HTTP Server context to publish your files to.

###[#src] &lt;htmlSource&gt;

The HTML source. Either:

- the directory containing the HTML files to publish, or
- the path to the 'jar' file (including it's filename and extension).




@@@[#navbar]
- [Home]
- [clean]
- [help]
- [jar]
- [manual]
- [mdj]
- [@active] [publish](#)
- [@right] [About]
    - [ToDo List]
    - [License]


[About]:About.html
[clean]:Clean.html
[help]:Help.html
[Home]:index.html
[jar]:Jar.html
[License]:LICENSE.html
[manual]:Manual.html
[mdj]:Mdj.html
[publish]:Publish.html
[ToDo List]:ToDo.html
@@@
