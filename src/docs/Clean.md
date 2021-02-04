@@@
use: articles2
title: ${document.name} | clean
@@@

# clean (goal)

This goal deletes the destination directory.

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
                        <id>MDj-Clean</id>
                        <goals>
                            <goal>clean</goal>
                        </goals>
                        <configuration>
                            <destination>target/docs/manual</destination>
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
There is only one option for this goal, the `<destination>` directory.

When combined with the `mdj` goal within the same `<execution>`, you would only
have the one entry for: `<destination>`.

###[#dest] &lt;destination&gt;
|property [*][p]|defaultValue|
|---|---|
|mdj.directory.destination|target/docs|


This is the destination directory for the HTML files.  It is the same option
as the one used with the goal `mdj`.

When setting it, be sure to change the parameter to your output directory.


[p]:# "This property can be added as a global setting in the <properties> section of your pom.xml file."


@@@[#navbar]
- [Home]
- [@active] [clean](#)
- [help]
- [jar]
- [manual]
- [mdj]
- [publish]
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
