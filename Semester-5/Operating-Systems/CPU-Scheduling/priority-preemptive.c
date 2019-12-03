#include <stdio.h>
#include <stdlib.h>

struct _process {
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

Process* init(int);
void priority_preemptive(Process *, int);
void print_gantt_chart(int *, int);
void print_table(Process *, int);

int main(int argc, char* argv[]) {
    Process* processes = init(4);

    priority_preemptive(processes, 4);
    print_table(processes, 4);

    free(processes);
}

void priority_preemptive(Process* processes, int number_of_processes) {
    int completed = 0;
    int time = 0;
    int minimum_priority;
    int index = 0;
    int prev_index = 0;
    int flag = 0;

    int *remaining_time = (int *) malloc(sizeof(int) * number_of_processes);

    int* gantt_chart = (int *) malloc(sizeof(int) * 100);
    int gantt_counter = 0;

    for(int i = 0; i < number_of_processes; i++) {
        remaining_time[i] = processes[i].burst_time;
    }
    minimum_priority = 1000 + (rand() % 1000);
    while(completed != number_of_processes) {
        
        flag = 0;
        
        for(int i = 0; i < number_of_processes; i++) {
            
            if((time >= processes[i].arrival_time) && (minimum_priority > processes[i].priority)) {    
                if(processes[i].completed != 1) {
                    minimum_priority = processes[i].priority;
                    index = i;
                    flag = 1;
                }
            }

        }

        if(flag == 1 || index == prev_index) {
            remaining_time[index]--;
            minimum_priority = remaining_time[index];
            prev_index = index; 
            if(remaining_time[index] == 0) {
                processes[index].finish_time = time + 1;
                processes[index].turnaround_time = processes[index].finish_time - processes[index].arrival_time;
                processes[index].waiting_time = processes[index].turnaround_time - processes[index].burst_time;
                processes[index].completed = 1;
                completed++;
                prev_index = -1;
            }
            
            gantt_chart[gantt_counter++] = processes[index].process_id;

        } else {
            gantt_chart[gantt_counter++] = -1;
        }
        time++;
        minimum_priority = (remaining_time[index] == 0) ? rand() : remaining_time[index];
    }
    free(remaining_time);
    print_gantt_chart(gantt_chart, gantt_counter);
}

void print_gantt_chart(int *chart, int total) {

    int number_of_spaces = 0;
    printf("\n Gantt Chart is as follows: \n\n");

    for(int i = 0; i < total; i++) {
    
        printf("|");
        number_of_spaces = 0;
        while(chart[i] == chart[i+1]) {
            printf(" ");
            number_of_spaces++;
            i++;
        }
        if(chart[i] != -1) {
            printf(" P%d ", chart[i]);
        } else {
            printf("    ");
        }
        int k = 0;
        while(k < number_of_spaces) {
            printf(" ");
            k++;
        }
    }
    printf("|\n");
    
    for(int i = 0; i <= total; i++) {
        if(i > 9) {
            printf("\b");
        }
        printf("%d", i);
        number_of_spaces = 0;
        while(chart[i] == chart[i + 1]) {
            printf(" ");
            number_of_spaces++;
            i++;
        }
        printf("    ");
        int k = 0;
        while(k < number_of_spaces) {
            printf(" ");
            k++;
        }
    }
    printf("\n");
    
    free(chart);
}

Process* init(int number_of_processes) {
    Process* processes = (Process*) malloc(sizeof(Process) * number_of_processes);
    for(int i = 0; i < number_of_processes; i++) {
        processes[i].process_id = i + 1;
        processes[i].completed = 0;
        scanf("%d", &processes[i].arrival_time);
        scanf("%d", &processes[i].burst_time);
        scanf("%d", &processes[i].priority);
    }
    return processes;
}

void print_table(Process *processes, int number_of_processes) {
    printf("+-----+----+----+----+----+-----+----+\n");
    printf("| PID | AT | BT | PR | FT | TAT | WT |\n");
    printf("+-----+----+----+----+----+-----+----+\n");
    for(int i = 0; i < number_of_processes; i++) {
        printf("| %3d | %2d | %2d | %2d | %2d | %3d | %2d |\n", processes[i].process_id, processes[i].arrival_time,
            processes[i].burst_time, processes[i].priority, processes[i].finish_time, processes[i].turnaround_time,
            processes[i].waiting_time);
        printf("+-----+----+----+----+----+-----+----+\n");
    }
    printf("\n");
}