@echo off
cd src
javac -d ../classes -cp ../jars/javax.mail.jar;../jars/activation.jar entity/*.java controller/*.java boundary/*.java
cd ../classes
java -cp ../jars/javax.mail.jar;../jars/activation.jar; boundary.MyStarsInterface