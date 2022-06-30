digit       [0-9]
letter      [A-Za-z]
%{
    #include <stdio.h>
    int count;
%}
%%
{letter}({letter}|{digit})*     {  count++;printf("match %d-th identifier: %s\r\n",count, yytext);}
%%
int yywrap(void) {
    return 1;
}

int main() {
    yylex();
    printf("number of identifiers = %d\n", count);
    return 0;
}