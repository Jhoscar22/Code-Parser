# Lexical Analyzer by Oscar Valdés 30-03-2022

## Description

This program searches regular expression occurrences in a file

## Instructions to execute program

1. Compile the **generator.cpp** file with the following command:
    *g++ -o generator.exe generator.cpp*

2. Write the regular expressions on a file with the name **patterns.txt**.

3. Execute **generator.exe**, this will turn the **patterns.txt** file into a flex file called **rules.l**

4. Use flex to create a **lex.yy.c** file with the following command:
    *flex rules.l*

5. Compile the **lex.yy.c** and **main.c** files with the following command:
    *gcc -o scanner.exe lex.yy.c main.c*

6. Write the text you wish to analyze in a file with the name **text.txt**.

7. Feed the input file **text.txt** to the **scanner.exe** application with the following command:
    *scanner.exe < text.txt*

In case you want to change only the **text.txt** file, repeat steps 6 and 7. Otherwise, if you want to change the regular expressions in the file **patterns.txt** repeat steps 2 through 7.

## Rules for writing regular expressions in lex

<https://westes.github.io/flex/manual/Patterns.html>
