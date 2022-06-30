%{
    int nchar, nword, nline;
%}
%%
\n          { nline++; nchar++; }
[^ \t\n]+   { nword++; nchar += yyleng; }
.           { nchar++; }
%%
int yywrap(void) {
    return 1;
}

int main() {
    yylex();
    printf("chracter: %d, word: %d, line: %d\n", nchar, nword, nline);
}