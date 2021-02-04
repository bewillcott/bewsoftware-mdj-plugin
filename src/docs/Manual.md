@@@
use: articles2
title: ${document.name} | manual
@@@

# manual (goal)

This goal executes the underlying program, causing it to
launch it's internal HTTP Server, publishing the manual stored
in the program's 'jar' file.

In addition, it will launch your system default web browser, to
connect to the server just started. If however, you have set a
different program to be your default html viewer, you can still connect your
favorite browser to the server by setting the address to:

`http://localhost:<port>`

The port number will be found in the pop-up dialog box that will also
have a button to stop the server. You can start and stop the
server as many times as you want/need. The only limit, is it
has been set to use a port in the range: 9000 - 9010 (inclusive).
So, at most you can only have 11 copies running at once. Though
why you would need that I have no idea, since this is a proper
compliant web server and can take multiple client connections on
the one port.

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
                        <id>MDj-Display-MDj-Manual</id>
                        <goals>
                            <goal>manual</goal>
                        </goals>
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



@@@[#navbar]
- [Home]
- [clean]
- [help]
- [jar]
- [@active] [manual](#)
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
