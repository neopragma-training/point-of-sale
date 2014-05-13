# Java Point of Sale

This is a student project designed to be used in Java programming classes. It provides a partial solution for students to build on.

You were given a VM configured to support the Point of Sale project. Sign into the VM with userid ```student``` and password ```@R34dy2W0rk!```. Documentation about the Point of Sale application and the class project is provided on the Desktop of the provided VM.

## Setup

### Step 1 - Clone the repository for the class project

Open a Terminal and navigate to directory ```~/workspace```. Clone the repo for your class:

```shell
git clone https://github.com/neopragma-training/point-of-sale-xxx
```

where xxx is the class identifier your instructor gave you.

### Step 2 - Install Maven dependencies

This will clear out the target directory of the project and download dependencies to ```~/.m2/repository```:

```shell
cd ~/workspace/point-of-sale
mvn clean install
```

### Step 3 - Connect Maven with Eclipse

This will update the Eclipse build path with the dependencies in ```~/.m2/repository```:

```shell
cd ~/workspace/point-of-sale
mvn eclipse:eclipse

### Step 4 - Import the class project

In Eclipse, open the Java perspective. Right-click in the project panel (on the left) and choose ```Import...``` Then choose ```Import existing project into workspace```. Navigate to ```~/workspace/point-of-sale``` and import the project.

### Step 5 - Point Eclipse and m2e to the correct version of Java

In Eclipse, ```Window => Preferences => Java Build Path => Compiler```, set the options to Java 1.7.

In Eclipse, ```[double-check where]```, set the JRE for Maven to use to Java 1.7.

### Step 6 - Verify the build will run from the command line

All the supplied unit tests should pass:

```shell
cd "$HOME/workspace/point-of-sale"
mvn test
```

All the supplied integration tests should pass:

```shell
cd "$HOME/workspace/point-of-sale"
mvn failsafe:integration-test
```

### Step 7 - Verify the build will run from within Eclipse

In Eclipse, right-click on the point-of-sale ```pom``` and choose Run As => Maven install from the context menu. The build should run the same as it did when you ran it from the command line.

### Step 8 - Verify the build will produce executable jars

These commands will create executable jars for the Store and Register applications.

```shell
cd "$HOME/workspace/point-of-sale"
mvn package -Pstore
mvn package -Pregister
```

### Step 9 - Verify the application will run

These commands will start the credit authorization server, the Store application, and one instance of the Register application:

```shell
authserver start
store start
register 1
```

If all is well, the ```authserver``` and ```store``` scripts will report no errors, and the ```register``` script will start the Swing application that simulates a point of sale cash register system.




