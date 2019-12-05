#include <sys/socket.h>
#include <sys/types.h>
#include <stdio.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>

#define SER_PORT 8080

int main(int argc, char *argv[]) 
{
        int serverExitFlag;
        int serverSocket;
        int newSocket;
        int clientExitFlag;

        char clientMessage[25];
        char serverMessage[25];

        int option = 1;

        struct sockaddr_in serverAddress;
        struct sockaddr_in clientInfo;
        socklen_t size = sizeof(clientInfo);

        serverAddress.sin_family = AF_INET;
        serverAddress.sin_port = htons(SER_PORT);
        serverAddress.sin_addr.s_addr = INADDR_ANY;

        if ((serverSocket = socket(AF_INET, SOCK_STREAM, 0)) < 0)
        {
                perror("\n SOCKET ERROR");
                exit(0);
        }

        if (setsockopt(serverSocket, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &option, sizeof(option)) < 0)
        {
                perror("\n REUSABLITITY OF ADDRESS ERROR");
                exit(0);
        }

        if (bind(serverSocket, (struct sockaddr *)&serverAddress, sizeof(serverAddress)) < 0)
        {
                perror("\n BINDING ERROR");
                exit(0);
        }

        if (listen(serverSocket, 1) < 0)
        {
                perror("\n LISTEN ERROR");
        }

        if ((newSocket = accept(serverSocket, (struct sockaddr *)&clientInfo, &size)) < 0)
        {
                perror("\n ACCEPT ERROR");
                exit(0);
        }
        else
        {
                printf("\n NOW CONNECTED TO >> %s\n", inet_ntoa(clientInfo.sin_addr));
        }

        read(newSocket, clientMessage, sizeof(clientMessage));

        do
        {
                printf("\n Client Message : %s", clientMessage);

                printf("\n Server (Your) Message >> : ");
                scanf("%s", serverMessage);

                send(newSocket, serverMessage, sizeof(serverMessage), 0);

                listen(newSocket, 1);

                read(newSocket, clientMessage, sizeof(clientMessage));

                clientExitFlag = strcmp(clientMessage, "EXIT");
                serverExitFlag = strcmp(serverMessage, "EXIT");
        } while (clientExitFlag != 0 || serverExitFlag != 0);

        close(newSocket);
        close(serverSocket);

        return 0;
}