Currently this is built for Java 15, though currently not using any of its specific new features. https://adoptopenjdk.net/installation.html?variant=openjdk15&jvmVariant=openj9#x64_linux-jre is where I got my binaries, though remember to change the platform to be specific to the place you plan on running this. 

In order to run there are several options:

    - Loading the source code into an IDE (I developed within IntelliJ so that would be most seamless) and running the App.java

    - Running mvn spring-boot:run from the pom.xml level folder (assuming you  have mvn installed)

    - Running `java -jar target/user-micro-service-test-alfiesm.jar` (this may have issues as it isn't a fat jar)

    - Running `java -jar target/user-micro-service-test-alfiesm-with-dependencies-jar.jar` which is the fat jar version containing dependencies within
