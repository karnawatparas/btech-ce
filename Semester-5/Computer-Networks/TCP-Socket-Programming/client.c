#include <stdio.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>

#define SER_PORT 8080
int main(int count, char *arg[])
{
    int serverExitFlag;
    int clientSocket;
    char clientMessage[20];
    char serverMessage[20];
    struct sockaddr_in clientAddress;

    clientAddress.sin_port = htons(SER_PORT);
    clientAddress.sin_family = AF_INET;
    clientAddress.sin_addr.s_addr = inet_addr(arg[1]);
    
    clientSocket = socket(AF_INET, SOCK_STREAM, 0);
    
    if (clientSocket < 0)
    {
        perror("\n SOCKET");
        exit(0);
    }
    
    if (connect(clientSocket, (struct sockaddr *)&clientAddress, sizeof(clientAddress)) < 0)
    {
        perror("\n CONNECT");
        exit(0);
    }
    
    printf("\n Client connected to [%s]", arg[1]);
    printf("\n\n Client <Your> Message >> ");
    scanf("%s", clientMessage);
    
    if (send(clientSocket, clientMessage, sizeof(clientMessage), 0) < 0)
    {
        printf("\n Data could not be sent!");
    }
    
    do
    {
        listen(clientSocket, 1);
        read(clientSocket, serverMessage, sizeof(serverMessage));
        printf("\n Server Message : %s", serverMessage);
        printf("\n Client <Your> Message >> ");
        scanf("%s", clientMessage);
        send(clientSocket, clientMessage, sizeof(clientMessage), 0);

        read(clientSocket, clientMessage, sizeof(clientMessage));

        serverExitFlag = strcmp(serverMessage, "EXIT");

    } while (serverExitFlag != 0);

    close(clientSocket);
    return 0;
}