@echo off
rem set JAVA_HOME
set JAVA_HOME="C:\Program Files\Java\jdk1.7.0_79\bin"
rem set Classpath
set PWD_CLASSPATH=%cd%\bin

echo %JAVA_HOME%
echo %PWD_CLASSPATH%

rem %JAVA_HOME%\javac src\generate\Pwd.java
%JAVA_HOME%\java -classpath %PWD_CLASSPATH% generate.Pwd
pause