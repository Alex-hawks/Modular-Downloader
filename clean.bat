@echo off
if "%1"=="build" (gradlew.bat clean build) else (gradlew.bat clean)
pause