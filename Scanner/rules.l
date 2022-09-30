%{
#include <stdio.h>
%}

%%

"//".* return 1;
"/*"(.|\n)*"*/" return 2;
\"(\\.|[^"\\])*\" return 3;
\'(\\.|[^'\\])*\' return 4;
([-+/*\^=]|<<|>>) return 5;
("=="|"<="|">="|"!="|"==="|"!=="|"||"|"&&") return 6;
("int"|"char"|"if"|"else"|"for"|"while"|"return"|"extern"|"#include"|"def") return 7;
[\(\)\[\]{};:,\.<>] return 8;
0b[01]*[.]?[01]* return 9;
0o[0-7]*[.]?[0-7]* return 10;
0x[0-9A-Fa-f]*[.]?[0-9A-Fa-f]* return 11;
-?[0-9]*.[0-9]+[eE][-+]?[1-9][0-9]* return 12;
-?[0-9]* return 13;
-?[1-9]*[0-9][.][0-9]* return 14;
[a-zA-Z][_a-zA-Z0-9]{0,31} return 15;
"$"[_a-zA-Z][_a-zA-Z0-9]* return 16;
[ \t\n] return 17;
. return 18;

%%

int num_tokens = 18;

int yywrap(void){
return 1;
}