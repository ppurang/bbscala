# First steps

Before we run the first _helloworld_ example we need to setup some stuff. You can skip to the _Running helloworld_ below if you have _scala_ and _sbt_ already setup.

## Needed software

The following is how my setup is on a *nix/osx. For windows I am clueless but I am sure we can work out something at the meetup.

### Scala

[Download 2.9.1](http://www.scala-lang.org/downloads). Installation is as easy as unpacking it. My scala lives under _/opt_ -- you might have another suitable place for it. A great thing to do is to soft link _scala -> scala-2.9.1.final/_. After that export the correct path (assuming you created the soft link):

```bash  
> export PATH = $PATH:/opt/scala/bin  
```
After that you should be able to invoke _scala_ from anywhere at the command-line for some [Scala REPL](http://www.youtube.com/watch?v=YpjKzKzC7jI) goodness. 

### Simple Build Tool

Install [sbt](https://github.com/harrah/xsbt/wiki/Getting-Started-Setup). 

My setup includes a _~/bin_ directory that is in my _PATH_. In there I have a _sbt-launch-0.11.2.jar_ and a script _sbt11_ that goes

```bash
#!/bin/sh
if test -f ~/.sbtconfig; then
  . ~/.sbtconfig
fi
echo $SBT_OPTS

java  ${SBT_OPTS} -jar `dirname $0`/sbt-launch-0.11.2.jar "$@"
``` 
The _~/.sbtconfig_ file is:

```bash
export SBT_OPTS="-Dfile.encoding=UTF8 -Xms1024m -Xmx1024m  -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m -Dsbt.boot.directory=$HOME/.sbt/boot/"
```

Create a directory:

```bash
> mkdir ~/.sbt
```

We will cover plugins for _sbt_ at the meetup.

### Git

[Download and install git.](http://git-scm.com/download)

## Running Helloworld

### Checkout _helloworld_

```bash
> git clone https://github.com/ppurang/bbscala
> cd bbscala/00000_meetup/helloworld
> sbt11
-Dfile.encoding=UTF8 -Xms1024m -Xmx1024m -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m -Dsbt.boot.directory=/home/ppurang/.sbt/boot/
[info] Set current project to helloworld (in build file:/home/ppurang/repositories/github/meri/bbscala/00000_meetup/helloworld/)
```

Ready to run our first scala program:

```bash
> run
[info] Running HelloWorld 
Hello World!
[success] Total time: 0 s, completed Dec 4, 2011 9:52:23 AM
```

Congrats your setup clicked. And don't worry if things weren't as successful - that is one of the reasons why we meet.

### IDE

I would recommend [Intellij Community Edition](http://www.jetbrains.com/idea/download/) with the scala plugin. Here is a good writeup on setting up your intellij and other IDEs:

https://github.com/coreyhaines/coderetreat/tree/master/starting_points/scala

### Create a github account

In case you don't have one [do create one](https://github.com/signup/free). Another great place is https://bitbucket.org/.

