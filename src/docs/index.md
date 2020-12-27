@@@
use: articles2
title: ${document.name}
@@@

# ${document.name}

This plugin is a wrapper for the BEWSoftware MDj Cli program which processes 
markdown files into html files.

This plugin has six goals:

- <b>clean</b>:  
Delete the &lt;destination&gt; directory (default: "`target/docs`").

- <b>help</b>:  
Auto generated during build.

- <b>jar</b>:  
Creates a 'jar' file (&lt;jarFilename&gt;) that includes the contents of the 
&lt;jarSrcDir&gt; directory along with additional files to facilitate the 
creation of a self-contained/self-publishing website document.

- <b>manual</b>:  
Launches the internal HTTP Server to publish the BEWSoftware MDj CLI manual
in the context: "`/`".

- <b>mdj</b>:  
Processes the markdown files from the &lt;source&gt; directory (default: "`src/docs`") 
into static html files in the &lt;destination&gt; directory (default: "`target/docs`").  

- <b>publish</b>:  
Launches the internal HTTP Server to publish the static html files from the 
&lt;htmlSource&gt; in the context: &lt;context&gt;.  The source can be either 
a directory accessible from the local system, or a 'jar' file that contains static
html files.

## Status
This plugin is fully functional.  However, I am still testing it.  You are welcome to
clone and compile it locally.  I plan on uploading it to the Maven Repository
once testing is complete.

## Dependencies
This plugin requires some other projects, to be able to compile this one:

- [BEWSoftware Libraries][bewl]![@externalLink]
- [BEWSoftware MDj Core Library][mjc]![@externalLink]
- [BEWSoftware MDj CLI][mc]![@externalLink]


[bewl]:https://github.com/bewillcott/bewsoftware-libs
[mjc]:https://github.com/bewillcott/bewsoftware-mdj
[mc]:https://github.com/bewillcott/bewsoftware-mdj-cli

@@@[navbar]
- [@active] [Home](#)
- [Clean]
- [Help]
- [Jar]
- [Manual]
- [Mdj]
- [Publish]
- [@right dropdown] [About]
[@dropdown-content]
    - [ToDo List]
    - [License]


[About]:About.html
[Clean]:Clean.html
[Help]:Help.html
[Home]:index.html
[Jar]:Jar.html
[License]:LICENSE.html
[Manual]:Manual.html
[Mdj]:Mdj.html
[Publish]:Publish.html
[ToDo List]:ToDo.html
@@@
