@echo off
setlocal
set PATH_TO_LIB=lib\mysql-connector-j-9.1.0.jar
set CLASSPATH=src;"%PATH_TO_LIB%"
java -cp %CLASSPATH% src\com\example\chatbot\ChatbotUI.java
endlocal
