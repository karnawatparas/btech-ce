%{
    void check_palindrome(char *);
%}

%%

[a-zA-Z]+ check_palindrome(yytext);

%%

int yywrap() { return 1; }

int main(int argc, char *argv[]) {
    yylex();
    return 0;
}

void check_palindrome(char *string) {
    int flag = 0;
    int len = 0;
    while(string[len] != '\0') {
        len++;
    }
    for(int i = 0, j = len - 1; i <= j; i++, j--) {
        if(string[i] != string[j]) {
            flag = 1;
            break;
        }
    }
    if(flag == 1) {
        printf("Not ");
    }
    printf("Palindrome");
}