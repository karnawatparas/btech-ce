#include <stdio.h>
#include <stdlib.h>

void get_processes_and_resources(int *, int *);
void get_maximum_resources(int[], int[], int *[], int, int);
void init_matrices(int *[], int *[], int *[], int, int);
void init_vectors(int *, int *, int);

void bankers_algorithm(int *[], int *[], int *, int p, int r);

int main(int argc, char *argv[])
{
	int number_of_processes;
	int number_of_resources;
	get_processes_and_resources(&number_of_processes, &number_of_resources);
	printf("\nNumber of processes: %d, Number of resources: %d\n", number_of_processes, number_of_resources);
	int **max;
	int **allocated;
	int **need;

	int *available_resources;
	int *maximum_resources;

	max = (int **)malloc(sizeof(int *) * number_of_processes);
	allocated = (int **)malloc(sizeof(int *) * number_of_processes);
	need = (int **)malloc(sizeof(int *) * number_of_processes);

	for (int i = 0; i < number_of_processes; i++)
	{
		max[i] = (int *)malloc(sizeof(int) * number_of_resources);
		allocated[i] = (int *)malloc(sizeof(int) * number_of_resources);
		need[i] = (int *)malloc(sizeof(int) * number_of_resources);
	}

	available_resources = (int *)malloc(sizeof(int) * number_of_resources);
	maximum_resources = (int *)malloc(sizeof(int) * number_of_resources);

	init_matrices(max, allocated, need, number_of_processes, number_of_resources);
	init_vectors(available_resources, maximum_resources, number_of_resources);

	get_maximum_resources(available_resources, maximum_resources, allocated, number_of_processes, number_of_resources);

	printf("\nNeed: \n");
	for(int i = 0; i < number_of_processes; i++) {
		printf("|");
		for(int j = 0; j < number_of_resources; j++) {
			printf(" %2d ", need[i][j]);
		}
		printf("|\n");
	}

	bankers_algorithm(allocated, need, available_resources, number_of_processes, number_of_resources);

	for (int i = 0; i < number_of_processes; i++)
	{
		free(max[i]);
		free(allocated[i]);
		free(need[i]);
	}

	free(max);
	free(allocated);
	free(available_resources);
	free(maximum_resources);

	return 1;
}

void bankers_algorithm(int *alloc[], int *need[], int avail[], int p, int r)
{
	int *safe_sequence;
	int *completed;

	int count_completed = 0;
	int flag = 0;
	int index = 0;
	int atleast_one = 0;

	safe_sequence = (int *)malloc(sizeof(int) * p);
	completed = (int *)malloc(sizeof(int) * p);

	for (int i = 0; i < p; i++)
	{
		completed[i] = 0;
	}

	while (count_completed != p)
	{
		atleast_one = 0;
		for (int i = 0; i < p; i++)
		{
			if (completed[i] == 0)
			{
				flag = 0;
				for (int j = 0; j < r; j++)
				{
					if (avail[j] < need[i][j])
					{
						flag = 1;
					}
				}

				if (!flag)
				{
					for (int k = 0; k < r; k++)
					{
						avail[k] = avail[k] + alloc[i][k];
					}
					safe_sequence[index] = i;
					index++;
					count_completed++;
					completed[i] = 1;
					atleast_one = 1;
				}
			}
		}

		if(!atleast_one) {
			printf("\nSystem in unsafe state!\n\n");
			exit(EXIT_FAILURE);
		}
	}

	printf("\nSystem in safe state.\nSafe sequence : < ");
	for(int i = 0; i < index; i++) {
		printf("P%d ", safe_sequence[i]);
	}
	printf(">\n\n");

	free(safe_sequence);
	free(completed);
}

void get_processes_and_resources(int *p, int *r)
{
	FILE *in = fopen("allocated.txt", "r");
	int number_of_resources = 1;
	int number_of_processes = 0;
	char temp;

	while ((temp = fgetc(in)) != EOF)
	{
		if (temp == '\n')
		{
			number_of_processes++;
		}
		else
		{
			if (temp == ' ')
			{
				if (number_of_processes == 0)
				{
					number_of_resources++;
				}
			}
		}
	}
	fclose(in);
	*p = number_of_processes;
	*r = number_of_resources;
}

void init_matrices(int *max[], int *alloc[], int *need[], int p, int r)
{
	FILE *allocated = fopen("allocated.txt", "r");
	FILE *maximum = fopen("max_processes.txt", "r");

	for (int i = 0; i < p; i++)
	{
		for (int j = 0; j < r; j++)
		{
			char string[10];
			fscanf(allocated, "%s", string);
			alloc[i][j] = atoi(string);
			fscanf(maximum, "%s", string);
			max[i][j] = atoi(string);
			need[i][j] = max[i][j] - alloc[i][j];
		}
	}

	fclose(allocated);
	fclose(maximum);
}

void init_vectors(int *avail, int *existing, int r)
{
	FILE *max_resources = fopen("maximum_resources.txt", "r");

	for (int i = 0; i < r; i++)
	{
		char string[10];
		fscanf(max_resources, "%s", string);
		existing[i] = atoi(string);
		avail[i] = 0;
	}
	fclose(max_resources);
}

void get_maximum_resources(int *avail, int *max, int *alloc[], int p, int r)
{
	for (int j = 0; j < r; j++)
	{
		for (int i = 0; i < p; i++)
		{
			avail[j] += alloc[i][j];
		}
		avail[j] = max[j] - avail[j];
	}
}