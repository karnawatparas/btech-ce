#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAX_SIZE 50

int track_number[MAX_SIZE];
int number_of_tracks;
int start_track;

void accept()
{
    number_of_tracks = 10;
    for (int i = 0; i < number_of_tracks; i++)
    {
        scanf("%d", &track_number[i]);
    }
    start_track = 50;
}

int main(int argc, char *argv[])
{
    int visited[MAX_SIZE];
    int total = 0;
    int index = 0, temp = 0, minimum = 0;
    int count = 0;

    accept();

    for (int i = 0; i < number_of_tracks; i++)
    {
        visited[i] = 0;
    }

    while (count < number_of_tracks)
    {

        minimum = 9090;
        for (int i = 0; i < number_of_tracks; i++)
        {
            if (!visited[i])
            {
                temp = abs(track_number[i] - start_track);
                if (temp < minimum)
                {
                    minimum = temp;
                    index = i;
                }
            }
        }

        if (minimum == 9090)
        {
            break;
        }

        printf("%4d \t %3d \n", track_number[index], minimum);
        total += minimum;
        visited[index] = 1;
        count++;
        start_track = track_number[index];
    }

    printf("Total distance: %d\n", total);
    return 0;
}