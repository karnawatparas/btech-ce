#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 50

int main(int argc, char *argv[])
{

    char string[MAX_SIZE];
    char reference_prompt[] = "\nEnter the reference string > ";
    char frames_prompt[] = "\nEnter the number of frames > ";
    int number_of_frames;

    int *frames;
    int *distances;
    int index = 0;
    int flag = 1;

    int page_faults = 0;

    int found = 0;
    int lastFilledFrame = -1;
    int choice;

    do
    {
        page_faults = 0;

        printf("%s", reference_prompt);
        scanf("%s", string);
        fflush(stdin);
        printf("%s", frames_prompt);
        scanf("%d", &number_of_frames);
        printf("\n");
        frames = (int *)malloc(sizeof(int) * number_of_frames);
        distances = (int *)malloc(sizeof(int) * number_of_frames);

        for (int i = 0; i < number_of_frames; i++)
        {
            frames[i] = -1;
        }

        int length = 0;
        while (string[length] != '\0')
            length = length + 1;

        for (int i = 0; i < length; i++)
        {
            found = 0;
            flag = 0;
            int page = (int)string[i] - '0';
            for (int j = 0; j < number_of_frames; j++)
            {
                if (frames[j] == page)
                {
                    flag = 1;
                    found = 1;
                    break;
                }
            }
            if (!flag)
            {
                if (lastFilledFrame == number_of_frames - 1)
                {
                    for (int temp = 0; temp < number_of_frames; temp++)
                    {
                        for (int m = i + 1; m < length; m++)
                        {
                            distances[temp] = 0;
                            int t = (int)string[m] - '0';
                            if (t == frames[temp])
                            {
                                distances[temp] = m - i;
                                break;
                            }
                        }
                    }

                    found = 0;

                    for(int m = 0; m < number_of_frames; m++) {
                        if(distances[m] == 0) {
                            found = 1;
                            index = m;
                        }
                    }

                } else {
                    index = ++lastFilledFrame;
                    found = 1;
                }

                if(!found) {
                    int highest = distances[0];
                    index = 0;
                    for(int l = 1; l < number_of_frames; l++) {
                        if(highest < distances[l]) {
                            highest = distances[l];
                            index = l;
                        }
                    }
                }

                frames[index] = page;
                page_faults++;
            }

            printf("Page: %2d \t Page Frames: [ ", page);
            for (int k = 0; k < number_of_frames; k++)
            {
                if (frames[k] != -1)
                {
                    printf("%d ", frames[k]);
                }
            }
            printf("]");
            if(flag)
            {
                printf(" <- HIT");
            }
            else
            {
                printf(" <- MISS");
            }
            printf("\n");
        }

        printf("\nNumber of page faults > %d\n", page_faults);

        free(frames);
        free(distances);

        printf("\nContinue? Enter 0 to exit > ");
        scanf("%d", &choice);
    } while (choice != 0);
    printf("\n");

    return 0;
}