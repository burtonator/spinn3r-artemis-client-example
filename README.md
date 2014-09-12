# Spinn3r Artemis Example Client

Example project for building a Spinn3r client in Java using Maven.  

We only need to implement one interface, then use it in our code.

# Dependencies

You can add any maven dependencies (or JARs) that you like.

Simply run:

```
mvn dependency:copy-dependencies
```

Then all dependencies will be placed under:

```
target/dependency/
```

# Installation

Copy the .jar dependencies in ```target/dependency/``` to:
 
```
/usr/share/spinn3r-artemis-client-watcher/lib/ext 
```

Any .jars placed in the ext directory will automatically be added to your 
classpath the the watcher.
