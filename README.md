ModJam2 - Insectia
=======

Insectia, my submission for ModJam 2, is a mod all about bug collecting. 
Using different tools, you can collect insects, which you can then use to help you collect materials. 
For modjam, things will be really simple, with only 4 (possibly 5?) types of insects that produce different items.

### Watch the livestream!

http://twitch.tv/lomeli12

### Want to test the Beta Builds?

Get them at: http://anthony-lomeli.net/MinecraftMods/ModJam2/

## How to compile!

#### Prerequisites
1. **WARNING:  Make sure you know EXACTLY what you're doing!  It's not any of our faults if your OS crashes, becomes corrupted, etc.**
2. Download and install the Java JDK [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).  Scroll down, accept the `Oracle Binary Code License Agreement for Java SE`, and download the one pertaining to your OS (necessary for MCP).
    * Go to `Control Panel\System and Security\System`, and click on `Advanced System Settings` on the left-hand side.
	* Click on `Environment Variables`.
  * Under `System Variables`, click `New`.
  * For `Variable Name`, input `JAVA_HOME`.
  * For `Variable Value`, input something similar to `;C:\Program Files (x86)\Java\jdk1.7.0_25` exactly as shown to the end (or wherever your Java JDK installation is), and click `Ok`.
  * Scroll down to a variable named `Path`, and double-click on it.
  * Append `;C:\Program Files (x86)\Java\jdk1.7.0_25\bin` (or your Java JDK installation directory\bin), and click `Ok`.
3. Download Apache Ant [here](http://ant.apache.org).
	* Unzip the files anywhere you want, eg `C:\Program Files (x86)\Ant`.
  * Again, go to `Environment Variables` just like you did for the Java JDK.
  * Under `System Variables`, click `New`.
  * For `Variable Name`, input `ANT_HOME`.
  * For `Variable Value`, input `C:\Ant\apache-ant-1.9.0` (or your Ant directory\apache-ant-1.9.0).
  * Scroll down to `Path`, and double-click on it.
  * Append `;C:\Ant\apache-ant-1.9.0\bin` exactly as shown to the end (or your Ant directory\apache-ant-1.9.0\bin).
4. Download and install git from [here](http://git-scm.com/)

#### Compiling the mod

1. Open up the git Bash terminal where you would like the compile mod to be saved.
On Windows you can `Left-Shift`+`Right-Click` in a folder and select `git Bash` to open it up quickly.
2. Clone the repository to a folder using the following git command.
`git clone https://github.com/Lomeli12/ModJam.git` and press `Enter` to get the latest version of the source.
Or alternatively, download the source from [here](https://github.com/Lomeli12/ModJam2/archive/master.zip). 
Just make sure it's contents are in a folder called `ModJam2`. Do NOT close git Bash yet.
3. Download Minecraft Forge 9.10.0.804 or higher (src not universal) and unzip it to the same folder as the ModJam 2 Folder.
4. Your current folder sturcture should look like this:

***
    <root>
    \-forge
        \-client
        \-common
        \-fml
        \-patches
    \-ModJam2
        \-common
        \-resources

***

5. Run the install.cmd `Windows` or install.sh `Unix` inside the forge folder to download MCP and apply the Forge Patches. WAIT FOR IT TO FINISH! Your folder sturcture should now look like this:

***
    <root>
    \-forge
        \-client
        \-common
        \-fml
        \-mcp
            \-bin
            \-conf
            \-docs
            \-eclipse
            \-jars
            \-lib
            \-logs
            \-runtime
            \src
        \-patches
    \-ModJam2
        \-common
        \-resources

***

6. Back in git Bash use the command `cd ModJam2` and press `Enter` to enter the ModJam2 Folder
7. Now type in the command `ant release` and press `Enter` to start compiling the mod. WAIT FOR IT TO FINISH! Your folder structure should look like this afterwards:

***
    <root>
    \-forge
        \-client
        \-common
        \-fml
        \-mcp
            \-bin
            \-conf
            \-docs
            \-eclipse
            \-jars
            \-lib
            \-logs
            \-runtime
            \src
        \-patches
    \-ModJam2
        \-common
        \-resources
    \-releases
        \-ModJam2
            \-1.6.x
                \-1.0

***

8. You should now find a jar file called ModJam2-Universal-1.0.x.jar* within `releases\ModJam2\1.6.x\1.0` that you can now use in modded Minecraft

*Where x = latest build number