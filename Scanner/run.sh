#!/bin/bash
# This script is used to run the scanner
g++ -o generator-app generator.cpp
./generator-app
flex rules.l
gcc -o scanner-app main.c lex.yy.c
cat example.txt | ./scanner-app example.html