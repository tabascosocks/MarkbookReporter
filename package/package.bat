@echo on
"C:\Program Files\Java\jdk-17.0.5\bin\jpackage.exe" --name MarkbookReporter --type app-image --input "..\target" --main-jar "..\target\MarkbookReporter-1.0-SNAPSHOT-jar-with-dependencies.jar"
pause