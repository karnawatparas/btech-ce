%{
    int number_of_lines = 0;
    int number_of_characters = 0;
    FILE* yyin;
%}

%%

\n number_of_lines++;
. number_of_characters++;

%%

int yywrap() {
    return 1;
}

int main(int argc, char *argv[]) {
    yyin = fopen(argv[1], "r");
    yylex();
    fclose(yyin);
    printf("\nNumber of characters: %d", number_of_characters);
    printf("\nNumber of lines: %d\n", number_of_lines);
}