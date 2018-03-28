# pwdhasher

This application is a simple password generator. It generates different passwords for different web sites from only one master password. 

## Acknowledgement
The idea originates from the Mozilla plugin [Password Hasher](https://addons.mozilla.org/de/firefox/addon/password-hasher/), which is unfortunately not usable in current Firefoxes.

Another implementation of this idea has been made for the Android platform by Thilo-Alexander Ginkel. The source code for that Android app can be found here: [Hash It!](https://github.com/ginkel/hashit). The class [com.ginkel.hashit.PasswordHasher.java](https://github.com/ginkel/hashit/blob/master/src/main/java/com/ginkel/hashit/PasswordHasher.java) has been widely reused in this project, although some minor chnages have been applied, e.g. regarding the logging and base64 related stuff.

## Usage
This project uses Maven to build. So, after you would have cloned this repository, an easy 
    mvn package
should be enough to generate an executable jar file.

