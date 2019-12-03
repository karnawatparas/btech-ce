#include <stdio.h>
#include <stdlib.h>

struct _process
{
    int process_id;
    int arrival_time;
    int burst_time;
    int priority;
    int finish_time;
    int turnaround_time;
    int waiting_time;
    int completed;
};

typedef struct _process Process;

Process *init(int);
void round_robin(Process *, int);
void print_gantt_chart(int *, int);
void print_table(Process *, int);

int main(int argc, char *argv[])
{
    Process *processes = init(4);

    round_robin(processes, 4);
    print_table(processes, 4);

    free(processes);
}

void round_robin(Process *processes, int number_of_processes)
{
    int completed = 0;
    int time = 0;
    int index = 0;
    int flag = 0;
    int time_slice = 3;

    int *remaining_time = (int *)malloc(sizeof(int) * number_of_processes);

    int *gantt_chart = (int *)malloc(sizeof(int) * 100);
    int gantt_counter = 0;

    for (int k = 0; k < number_of_processes; k++)
    {
        remaining_time[k] = processes[k].burst_time;
    }

    int i = 0;

    for(int p = 0; p < number_of_processes - 1; p++) {
        for(int q = 0; q < number_of_processes - i - 1; q++) {
            if(processes[q].arrival_time > processes[q + 1].arrival_time) {
                Process temp = processes[q];
                processes[q] = processes[q + 1];
                processes[q + 1] = temp;
            }
        }
    }

    while (completed != number_of_processes)
    {
        flag = 0;

        for (i = 0; i < number_of_processes; i++)
        {
            index = i;
            if ((time >= processes[i].arrival_time) && (remaining_time[i] > 0))
            {
                flag = 1;
                if (remaining_time[index] > time_slice)
                {
                    for (int k = 0; k < time_slice; k++)
                    {
                        gantt_chart[gantt_counter++] = processes[index].process_id;
                    }
                    remaining_time[index] -= time_slice;
                    time += time_slice;
                }
                else
                {
                    for (int k = 0; k < remaining_time[index]; k++)
                    {
                        gantt_chart[gantt_counter++] = processes[index].process_id;
                    }
                    time += remaining_time[index];
                    remaining_time[index] = 0;
                    processes[index].finish_time = time + 1;
                    processes[index].turnaround_time = processes[index].finish_time - processes[index].arrival_time;
                    processes[index].waiting_time = processes[index].turnaround_time - processes[index].burst_time;
                    processes[index].completed = 1;
                    completed++;
                }
            }
        }
        if(flag == 0) {
            time++;
            gantt_chart[gantt_counter++] = -1;
        }
    }

    free(remaining_time);
    print_gantt_chart(gantt_chart, gantt_counter);
}

void print_gantt_chart(int *chart, int total)
{

    int number_of_spaces = 0;
    printf("\n Gantt Chart is as follows: \n\n");

    for (int i = 0; i < total; i++)
    {

        printf("|");
        number_of_spaces = 0;
        while (chart[i] == chart[i + 1])
        {
            printf(" ");
            number_of_spaces++;
            i++;
        }
        if (chart[i] != -1)
        {
            printf(" P%d ", chart[i]);
        }
        else
        {
            printf("    ");
        }
        int k = 0;
        while (k < number_of_spaces)
        {
            printf(" ");
            k++;
        }
    }
    printf("|\n");

    for (int i = 0; i <= total; i++)
    {
        if (i > 9)
        {
            printf("\b");
        }
        printf("%d", i);
        number_of_spaces = 0;
        while (chart[i] == chart[i + 1])
        {
            printf(" ");
            number_of_spaces++;
            i++;
        }
        printf("    ");
        int k = 0;
        while (k < number_of_spaces)
        {
            printf(" ");
            k++;
        }
    }
    printf("\n");

    free(chart);
}

Process *init(int number_of_processes)
{
    Process *processes = (Process *)malloc(sizeof(Process) * number_of_processes);
    for (int i = 0; i < number_of_processes; i++)
    {
        processes[i].process_id = i + 1;
        processes[i].completed = 0;
        scanf("%d", &processes[i].arrival_time);
        scanf("%d", &processes[i].burst_time);
        scanf("%d", &processes[i].priority);
    }
    return processes;
}

void print_table(Process *processes, int number_of_processes)
{
    printf("+-----+----+----+----+----+-----+----+\n");
    printf("| PID | AT | BT | PR | FT | TAT | WT |\n");
    printf("+-----+----+----+----+----+-----+----+\n");
    for (int i = 0; i < number_of_processes; i++)
    {
        printf("| %3d | %2d | %2d | %2d | %2d | %3d | %2d |\n", processes[i].process_id, processes[i].arrival_time,
               processes[i].burst_time, processes[i].priority, processes[i].finish_time, processes[i].turnaround_time,
               processes[i].waiting_time);
        printf("+-----+----+----+----+----+-----+----+\n");
    }
    printf("\n");
}