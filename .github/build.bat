@echo off

mkdir build
cmake -Bbuild %*
cmake --build build --config Debug

if errorlevel 1 exit /b %ERRORLEVEL%
