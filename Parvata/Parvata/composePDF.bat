@echo off
color 0a
set /P file="What do you want to name the PDF?: "
echo Composing %file%...
convert output-* %file%
