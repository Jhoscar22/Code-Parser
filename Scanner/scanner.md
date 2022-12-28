# Lexical Analyzer by Oscar Valdés

## Description

This application searches the specified regular expressions in a file and generates a html file highlighting the matches. The regular expressions are specified in a file called **patterns.txt**. The text to be analyzed is specified in a file called **text.txt**. The output is a file called **text.html**.

## Instructions to execute program

Run the `run.sh` file in the terminal or:

1. Compile the **generator.cpp** file with the following command:
    `g++ -o generator-app generator.cpp`

2. Write the regular expressions on a file with the name **patterns.txt**. Leave a empty line at the end. The limit is 18, but you can push it further by defining your own classes in **styles.css**.

3. Execute **generator-app**, this will use the **patterns.txt** file to generate a flex file called **rules.l**

4. Use the flex command to create a **lex.yy.c** file with the following command:
    `flex rules.l`

5. Compile the **lex.yy.c** and **main.c** files with the following command:
    `gcc -o scanner-app lex.yy.c main.c`

6. Write the text you wish to analyze in a file with the name **example.txt**.

7. Feed the input file **example.txt** to the **scanner-app** application with the following command:
    `cat example.txt | ./scanner-app < example.html`

In case you want to change only the **example.txt** file, repeat steps 6 and 7. Otherwise, if you want to change the regular expressions in the file **patterns.txt** repeat steps 2 through 7.

## Rules for writing regular expressions in lex

<https://westes.github.io/flex/manual/Patterns.html>
