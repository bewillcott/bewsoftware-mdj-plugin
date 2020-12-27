@@@
use: articles2
title: ${document.name} | mdj
@@@

# mdj (goal)

This goal executes the underlying program, causing it to process
the markdown files (*.md) in the `source` directory,
storing the output files (*.html) in the `destination` directory.

Additional options are available for:

- `recursive` directory processing
- `wrapper` processing
- `verbosity` setting

## pom.xml  
Add the following to your `pom.xml` file.
~~~
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
                        <id>MDj-Compile</id>
                        <goals>
                            <goal>mdj</goal>
                        </goals>
                        <configuration>
                            <source>src/docs/manual</source>
                            <destination>target/docs/manual</destination>
                            <verbosity>1</verbosity>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            ...
        </plugins>
    </build>
</project>
~~~

I suggest that putting the plugin into a separate profile would be a good idea,
so it only runs when you need it to.

## Configuration ## [][@upArrow](#top)

###[#src] &lt;source&gt;
|property [*][p]|defaultValue|
|---|---|
|mdj.directory.source|src/docs|

The source directory for markdown files.

###[#dest] &lt;destination&gt;
|property [*][p]|defaultValue|
|---|---|
|mdj.directory.destination|target/docs|

The destination directory for HTML files.

###[#wrap] &lt;wrapper&gt;
|defaultValue|
|---|
|target/docs|

Process meta block, wrapping your document with templates and style sheets.

###[#rec] &lt;recursive&gt;
|defaultValue|
|---|
|true|

Recursively process directories.

###[#verb] &lt;verbosity&gt;
|defaultValue|
|---|
|0|

Set the level of verbosity.

- '0' is off.
- '1' - '3' are active levels, from limited information to a lot of information.



[p]:# "The value can be added as a global setting in the <properties> section of your pom.xml file."


@@@[navbar]
- [Home]
- [clean]
- [help]
- [jar]
- [manual]
- [@active] [mdj](#)
- [publish]
- [@right dropdown] [About]
[@dropdown-content]
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
