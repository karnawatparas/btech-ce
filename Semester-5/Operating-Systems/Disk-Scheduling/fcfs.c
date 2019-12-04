#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAX_SIZE 50

int track_number[MAX_SIZE];
int number_of_tracks;
int start_track;

void accept() {
    number_of_tracks = 10;
    for(int i = 0; i < number_of_tracks; i++) {
        scanf("%d", &track_number[i]);
    }
    start_track = 50;
}

int main(int argc, char* argv[]) {
    int distances[MAX_SIZE];
    int total = 0;
    accept();

    distances[0] = abs(track_number[0] - start_track);
    total += distances[0];
    for(int i = 1; i < number_of_tracks; i++) {
        distances[i] = abs(track_number[i] - track_number[i - 1]);
        total += distances[i];
    }

    printf("Total distance: %d\n", total);
    return 0;
}