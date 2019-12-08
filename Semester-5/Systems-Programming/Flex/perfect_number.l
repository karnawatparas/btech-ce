%{

    void check(char *);

%}

%option noyywrap
%%

[0-9]+ check(yytext);

%%

int main() {
    yylex();
    return 0;
}

void check(char *number) {
    int num = atoi(number);
    int x = 0;
    for(int i = 1; i < num; i++) {
        if(num % i == 0) {
            x = x + i;
        }
    }
    if(x == num) {
        printf("A perfect number\n");
    } else {
        printf("Not a perfect number\n");
    }
}