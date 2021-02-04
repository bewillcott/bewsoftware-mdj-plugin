@@@
use: articles2
title: ${document.name} | help
@@@

# help (goal)

This goal provides details on the use of each of the goals.

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
                        <id>MDj-Help</id>
                        <goals>
                            <goal>help</goal>
                        </goals>
                        <configuration>
                            
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



@@@[#navbar]
- [Home]
- [clean]
- [@active] [help](#)
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
