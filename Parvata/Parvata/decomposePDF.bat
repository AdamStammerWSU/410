color 0a
set /P f="Which PDF do you want to decompose?: "
echo Decomposing %f%...
convert -density 300 %f% input-%%04d.png
pause