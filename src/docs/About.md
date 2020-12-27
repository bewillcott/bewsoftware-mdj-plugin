@@@
use : articles2
title: ${document.name} | About
@@@


## About - v${document.version}

    BEWSoftware MDj Maven Plugin is a wrapper Maven plugin for the
    BEWSoftware MDj CLI program.

    Copyright (C) 2020 Bradley Willcott

    This plugin is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This plugin is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

This plugin came about as a result of my desire to fully automate the creation
of my program documentation. 

First, I found, updated and enhanced a fork of the [Markdownj library][markdownj]![@externalLink].
This modified code is now my own [BEWSoftware MDj Core Library][mjc]![@externalLink].
Then I developed a command-line program to use it: [BEWSoftware MDj CLI][mc]![@externalLink].

The next logical step was to develop a Maven Plugin.  The BEWSoftware MDj Maven Plugin
is one possible implementation of that requirement.



[markdownj]:https://github.com/myabc/markdownj "Original source on Github"
[mjc]:https://github.com/bewillcott/bewsoftware-mdj "Source on Github"
[mc]:https://github.com/bewillcott/bewsoftware-mdj-cli "Source on Github"


@@@[navbar]
- [Home]
- [clean]
- [help]
- [jar]
- [manual]
- [mdj]
- [publish]
- [@right dropdown active] [About](#)
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
