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
void fcfs(Process *, int);
void print_gantt_chart(int *, int);
void print_table(Process *, int);

int main(int argc, char* argv[]) {
    Process* processes = init(4);

    fcfs(processes, 4);
    print_table(processes, 4);

    free(processes);
}

void fcfs(Process* processes, int number_of_processes) {
    int completed = 0;
    int time = 0;
    int minimum_arrival_time;
    int index = 0;
    int flag = 0;

    int* gantt_chart = (int *) malloc(sizeof(int) * 100);
    int gantt_counter = 0;

    while(completed != number_of_processes) {
        
        minimum_arrival_time = 1000 + (rand() % 1000);
        flag = 0;
        
        for(int i = 0; i < number_of_processes; i++) {
            
            if((time >= processes[i].arrival_time) && (minimum_arrival_time > processes[i].arrival_time)) {    
                if(processes[i].completed != 1) {
                    minimum_arrival_time = processes[i].arrival_time;
                    index = i;
                    flag = 1;
                }
            }

        }

        if(flag == 1) {
            time += processes[index].burst_time;
            processes[index].finish_time = time;
            processes[index].turnaround_time = time - processes[index].arrival_time;
            processes[index].waiting_time = processes[index].turnaround_time - processes[index].burst_time;
            for(int k = 0; k < processes[index].burst_time; k++) {
                gantt_chart[gantt_counter++] = processes[index].process_id;
            }
            processes[index].completed = 1;
            completed++;
        } else {
            time++;
            gantt_chart[gantt_counter++] = -1;
        }

    }

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