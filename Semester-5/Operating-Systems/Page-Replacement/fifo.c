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
    int index = 0;

    int page_faults = 0;

    int found = 0;
    int change = 0;
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

        for (int i = 0; i < number_of_frames; i++)
        {
            frames[i] = -1;
        }

        int length = 0;
        while(string[length] != '\0')
            length = length + 1;

        for (int i = 0; i < length; i++)
        {
            found = 0;
            int page = (int) string[i] - '0';
            for (int j = 0; j < number_of_frames; j++)
            {
                if (frames[j] == page)
                {
                    found = 1;
                    break;
                }
            }
            if (!found)
            {
                page_faults++;
                frames[change] = page;
                change = (change + 1) % number_of_frames;
            }

            printf("Page: %2d \t Page Frames: [ ", page);
            for(int k = 0; k < number_of_frames; k++) {
                if(frames[k] != -1) {
                    printf("%d ", frames[k]);
                }
            }
            printf("]");
            if(found) {
                printf(" <- HIT");
            } else {
                printf(" <- MISS");
            }
            printf("\n");
        }

        printf("\nNumber of page faults > %d\n", page_faults);

        printf("\nContinue? Enter 0 to exit > ");
        scanf("%d", &choice);
        printf("\n");
    } while (choice != 0);
    
    return 0;
}