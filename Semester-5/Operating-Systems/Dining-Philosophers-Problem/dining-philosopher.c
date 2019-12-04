#include <semaphore.h>
#include <pthread.h>
#include <unistd.h>
#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>

#define THINKING 0
#define HUNGRY 1
#define EATING 2

#define N 5

void *_philosopher(void *);
void test(int);
void take_chopstick(int);
void put_chopstick(int);

int state[N];

sem_t philosophers_sem[N];
sem_t mutex;

int main(int argc, char *argv[]) {

    sem_init(&mutex, 0, 1);
    pthread_t philosophers[N];

    for(int i = 0; i < N; i++) {
        sem_init(philosophers_sem + i, 0, 1);
        state[i] = THINKING;
        pthread_create(&philosophers[i], NULL, _philosopher, (void *) (intptr_t) i);
    }

    for(int i = 0; i < N; i++) {
        pthread_join(philosophers[i], NULL);
    }

    return 0;
}

void *_philosopher(void* id) {
    int pid = (intptr_t) id;
    do {
        printf("\nphilosopher %d is thinking", pid);
        sleep(1 + rand() % 5);
        take_chopstick(pid);
        sleep(1 + rand() % 5);
        put_chopstick(pid);
        sleep(1 + rand() % 5);
    } while(1);
}

void take_chopstick(int pid) {
    sem_wait(&mutex);
    state[pid] = HUNGRY;
    printf("\nphilosopher %d is hungry", pid);
    test(pid);
    sem_post(&mutex);
    sem_wait(&philosophers_sem[pid]);
}

void put_chopstick(int pid) {
    sem_wait(&mutex);
    state[pid] = THINKING;
    printf("\nphilosopher %d is thinking", pid);
    int left = (pid - 1) % N;
    int right = (pid + 1) % N;
    test(left);
    test(right);
    sem_post(&mutex);
}

void test(int pid) {
    int left = (pid - 1) % N;
    int right = (pid + 1) % N;
    if(state[pid] == HUNGRY && state[left] != EATING && state[right] != EATING) {
        state[pid] = EATING;
        printf("\nphilosopher %d is eating", pid);
        sem_post(&philosophers_sem[pid]);
    }
}