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
    int index = 0, temp = 0;

    accept();

    for (int i = 0; i < number_of_tracks - 1; i++)
    {
        for (int j = 0; j < number_of_tracks - 1; j++)
        {
            if (track_number[j] > track_number[j + 1])
            {
                temp = track_number[j];
                track_number[j] = track_number[j + 1];
                track_number[j + 1] = temp;
            }
        }
    }

    for (int i = 0; i < number_of_tracks; i++)
    {
        if(track_number[i] >= start_track) {
            index = i;
            break;
        }
    }

    for(int k = index; k < number_of_tracks; k++) {
        total = total + abs(track_number[k] - start_track);
        printf("%4d \t %3d\n", track_number[k], abs(track_number[k] - start_track));
        start_track = track_number[k];
    }

    for(int j = index - 1; j > -1; j--) {
        total = total + abs(track_number[j] - start_track);
        printf("%4d \t %3d\n", track_number[j], abs(track_number[j] - start_track));
        start_track = track_number[j];
    }

    printf("Total distance: %d\n", total);
    return 0;
}