package analizadores;
import java_cup.runtime.Symbol; 

%% 
%class Lexico
%public 
%line 
%char 
%cup 
%unicode
%ignorecase

%init{ 
    yyline = 1; 
    yychar = 1; 
%init} 

BLANCOS=[ \r\t]+
D=[0-9]+
DD=[0-9]+("."[  |0-9]+)?
PALABRA= [a-zA-Z]+([a-zA-Z]|[0-9])*

%%

"Evaluar" {return new Symbol(sym.REVALUAR,yyline,yychar, yytext());} 
"If" {return new Symbol(sym.SI,yyline,yychar, yytext());} 
"Escribir" {return new Symbol(sym.ESCRIBIR,yyline,yychar, yytext());} 
"Ciclo" {return new Symbol(sym.CICLO,yyline,yychar, yytext());}


";" {return new Symbol(sym.PTCOMA,yyline,yychar, yytext());} 
"," {return new Symbol(sym.COMA,yyline,yychar, yytext());} 
"(" {return new Symbol(sym.PARIZQ,yyline,yychar, yytext());} 
")" {return new Symbol(sym.PARDER,yyline,yychar, yytext());} 
"[" {return new Symbol(sym.CORIZQ,yyline,yychar, yytext());} 
"]" {return new Symbol(sym.CORDER,yyline,yychar, yytext());} 
    


"+" {return new Symbol(sym.MAS,yyline,yychar, yytext());} 
"-" {return new Symbol(sym.MENOS,yyline,yychar, yytext());} 
"*" {return new Symbol(sym.POR,yyline,yychar, yytext());} 
"/" {return new Symbol(sym.DIVIDIDO,yyline,yychar, yytext());} 

">" {return new Symbol(sym.MAX,yyline,yychar, yytext());} 
"<" {return new Symbol(sym.MIN,yyline,yychar, yytext());} 
"=" {return new Symbol(sym.EQUAL,yyline,yychar, yytext());} 




\n {yychar=1;}

{BLANCOS}   {} 
{D}         {return new Symbol(sym.ENTERO,yyline,yychar, yytext());} 
{DD}        {return new Symbol(sym.DECIMAL,yyline,yychar, yytext());} 
{PALABRA}   {return new Symbol(sym.PALABRA,yyline,yychar, yytext());} 

. {
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "
    +yyline+", en la columna: "+yychar);
}