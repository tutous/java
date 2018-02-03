Build:

  javac -d classes --module-source-path src src/de.tutous.myutil/module-info.java src/de.tutous.myutil/de/tutous/myutil/MyUtil.java
  javac -d classes --module-source-path src src/de.tutous.myapp/module-info.java src/de.tutous.myapp/de/tutous/myapp/MyApp.java

Execute MyApp in classes:

  java -p classes -m de.tutous.myapp/de.tutous.myapp.MyApp

Create Module jar:

  jar --create --file modules/de.tutous.myapp-1.0.jar  --module-version 1.0 --main-class de.tutous.myapp.MyApp -C classes/de.tutous.myapp .
  jar --create --file modules/de.tutous.myutil-1.0.jar --module-version 1.0 -C classes/de.tutous.myutil .

Execute MyApp in modules:

  java -p modules -m de.tutous.myapp/de.tutous.myapp.MyApp

Analysis:

  dependencies: 
  
  jdeps -s --module-path modules modules/*.jar
  jdeps --module-path modules modules/*.jar
  
  descriptor:
  
  jar --describe-module --file=modules/de.tutous.myapp-1.0.jar
  jar --describe-module --file=modules/de.tutous.myutil-1.0.jar
  
  javap -verbose classes/de.tutous.myapp/module-info.class
  javap -verbose classes/de.tutous.myutil/module-info.class
  
Jlink distribution (ca. 30MB):

  jlink --module-path "/usr/lib/jvm/java-9-openjdk/jmods:modules" --add-modules de.tutous.myapp --output distribution
  jlink --module-path "/usr/lib/jvm/java-9-openjdk/jmods:modules" --add-modules de.tutous.myapp --output distribution --excludes-files *.diz --strip-debug --compress=2
  
Execute MyApp in distribution:

  distribution/bin/java -m de.tutous.myapp
  distribution/bin/java --list-modules
  
  