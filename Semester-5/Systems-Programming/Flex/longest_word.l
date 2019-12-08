%{
    #include <stdio.h>
    #include <string.h>

    FILE* yyin;

    int longest = 0;
    char longest_word[50];
%}

%%

[a-zA-Z]+ { if(yyleng > longest) {
                longest = yyleng;
                strncpy(longest_word, yytext, longest);
            }
        }
. ;
\n ;

%%

int yywrap() {
    return 1;
}

int main(int argc, char* argv[]) {
    yyin = fopen("test-file.txt", "r");
    yylex();
    fclose(yyin);
    printf("Longest word : %s (%d)\n", longest_word, longest);
    return 0;
}
