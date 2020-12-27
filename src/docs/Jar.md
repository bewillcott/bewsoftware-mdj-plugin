@@@
use: articles2
title: ${document.name} | jar
@@@

# jar (goal)

This goal executes the underlying program, causing it to archive the files in
the `jarSrcDir` directory tree into a new jar file: `jarFilename`.

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
                        <id>MDj-Package-HTML</id>
                        <goals>
                            <goal>jar</goal>
                        </goals>
                        <configuration>
                            <jarFilename>${project.build.finalName}-manual-src.jar</jarFilename>
                            <jarSrcDir>src/docs/manual</jarSrcDir>
                            <docRootDir>src/docs/manual</docRootDir>
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

###[#file] &lt;jarFilename&gt;
|required|
|---|
|true|

The name of the new 'jar' file.

**File location:**  
If you do not include a directory path, then it will be created in the
`${project.build.directory`. If you give a relative directory path, then it will
be taken to being relative to the project directory where the `pom.xml`
file is located.

**File name:**  
You must provide the full name of the file including the extension,
whether or not you provide a directory path. This will NOT be vetted.

**Suggestions:**  
To pack up a copy of your HTML files:  
if your 'destination' directory (*.html files) is: `target/docs/manual`,
then a possible setting might be: `<jarFilename>myprog-1.0-manual.jar</jarFilename>`.

To pack up a copy of your Markdown files:  
if your 'source' directory (*.md files) is: `src/docs/manual`,
then a possible setting might be: `<jarFilename>myprog-1.0-manual-src.jar</jarFilename>`.

###[#src] &lt;jarSrcDir&gt; ### [][@upArrow](#top)
|required|
|---|
|true|

The source directory for the files to be included in the new 'jar' file.

Any relative path will be taken as being relative to the project directory
where the `pom.xml` file is located.

**Recursion:**  
This source directory and all subdirectories will be included in the 'jar' file.
There is currently NO option to just pack up the specific directory.

**Suggestions:**  
To pack up a copy of your HTML files:  
if your [destination][dest] directory (*.html files) is: `target/docs/manual`,
then set the following: `<jarSrcDir>target/docs/manual</jarSrcDir>`.

To pack up a copy of your Markdown files:  
if your [source][src] directory (*.md files) is: `src/docs/manual`,
then set the following: `<jarSrcDir>src/docs/manual</jarSrcDir>`.

###[#doc] &lt;docRootDir&gt; ### [][@upArrow](#top)
|required|
|---|
|true|

The document root directory.

This is the same directory as the root of your document source files (*.md).
For instance it might be: `src/docs/manual`. This must be supplied even if
you are packaging your document source files, giving the same directory
as for: [jarSrcDir](#src). The program makes **no** assumptions.

###[#verb] &lt;verbosity&gt;
|defaultValue|
|---|
|0|

Set the level of verbosity.

- '0' is off.
- '1' - '3' are active levels, from limited information to a lot of information.



[dest]:Mdj.html#dest "mdj - destination."
[src]:Mdj.html#src "mdj - source."


@@@[navbar]
- [Home]
- [clean]
- [help]
- [@active] [jar](#)
- [manual]
- [mdj]
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
