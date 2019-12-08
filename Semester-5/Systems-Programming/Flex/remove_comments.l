%{
    
%}

start \/\*
end \*\/
%option noyywrap
%%

\/\/(.*) ;
{start}(.*\n)*.*{end} ;

%%

int main(int argc, char *argv[]) {
    yyin = fopen(argv[1], "r");
    yyout = fopen("no-comments.c", "w");
    yylex();
    fclose(yyin);
    return 0;
}