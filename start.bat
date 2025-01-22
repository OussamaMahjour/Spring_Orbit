@echo off
REM Exit immediately if a command exits with a non-zero status
setlocal enabledelayedexpansion

REM Loop through all subdirectories
for /d %%d in (*) do (
    REM Check if the directory contains a pom.xml file
    if exist "%%d\pom.xml" (
        echo Building Maven project in directory: %%d
        REM Navigate into the directory
        cd %%d
        REM Run Maven package
        call .\mvnw.cmd package
        REM Navigate back to the parent directory
        cd ..
    ) else (
        echo Skipping directory: %%d (no pom.xml found)
    )
)

echo All Maven projects have been built.

echo Starting the containers
docker-compose up --build
