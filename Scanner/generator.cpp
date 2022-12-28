//Autor: Oscar Jahir Valdés Caballero
//Última modificación: 30 de marzo de 2022
//Descripción: Este programa lee un archivo con el nombre de "patterns.txt" y genera un archivo llamado "rules.l" a partir de él. El archivo "rules.l" es necesario para generar un archivo llamado "lex.yy.c".

#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

int main(){
    vector<string> keys; //User-defined patterns are stored here
    string key;          //Temporarily stores each key
    ifstream patterns;   //File in which the user defines the regular expressions
    ofstream rules;      //Flex file generated as output


    patterns.open("patterns.txt"); //Se abre el archivo que contiene las expresiones regulares.
    while (getline(patterns, key)) //Mientras haya líneas restantes en el archivo de lectura.
    {
        key.erase(key.size() - 1);
        keys.push_back(key); //Almacena la línea en un vector de strings.
    }
    patterns.close(); //Cierra el archivo de lectura.

    
    rules.open("rules.l"); //Abre el archivo en el que se definiran las reglas que requiere flex.
    rules << "%{\n#include <stdio.h>\n%}\n\n%%\n\n"; //Escribe en la cabecera del archivo.

    int i; //Variable de tipo entero sobre la que itera el ciclo for.
    for (i = 0; i < keys.size(); i++) //Ciclo que itera una vez por cada elemento del vector de strings.
    {
        rules << keys[i] << "\treturn " << i+1 << ";\n"; //Crea una nueva regla que regrese un entero en orden ascendente.
    }

    rules << "\n%%\n\nint num_tokens = " << i << ";\n\nint yywrap(void){\n\treturn 1;\n}"; //Escribe una función en el final del archivo.

    rules.close(); //Se cierra el archivo de escritura

    return 1;
}