%{
    #include <string.h>
    #include <math.h>
    void armstrong(char *);
%}

%%
[0-9]+ armstrong(yytext);
%%

int yywrap()
{
    return 1;
}

int main(int argc,char* argv[])
{
    yylex();
    return 0;
}

void armstrong(char *num){
    int n = atoi(num);
    int len = strlen(num);
    int x = n, y = 0, z;
    int res = 1;
    while(x > 0)
    {
        z = x % 10;
        res = 1;
        for(int i = 0; i < len; i++) {
            res = res * z;
        }
        y = y + res;
        x = x / 10;
    }

    if (y == n) {
        printf("It is an armstrong number");
    } else {
        printf("It is not an armstrong number");
    }
    
}
