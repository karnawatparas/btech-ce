#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

void* _producer(void *);
void* _consumer(void *);

int buffer_size;
int *buffer;

int number_of_producers;
int number_of_consumers;

sem_t full;
sem_t empty;
sem_t mutex;

pthread_t *producers;
pthread_t *consumers;

int counter;

int main(int argc, char* argv[]) {

    printf("\n> Enter the buffer size : ");
    scanf("%d", &buffer_size);

    int counter = 0;

    buffer = (int *) (malloc(sizeof(int) * buffer_size));

    printf("\n> Enter the number of producers : ");
    scanf("%d", &number_of_producers);

    printf("\n> Enter the number of consumers : ");
    scanf("%d", &number_of_consumers);

    sem_init(&empty, 0, buffer_size);
    sem_init(&full, 0, 0);
    sem_init(&mutex, 0, 1);

    producers = (pthread_t *) malloc(sizeof(pthread_t) * number_of_producers);
    consumers = (pthread_t *) malloc(sizeof(pthread_t) * number_of_consumers);

    for(int i = 0; i < number_of_producers; i++) {
        pthread_create(&producers[i], NULL, _producer, (void *) i);
    }
    for(int j = 0; j < number_of_consumers; j++) {
        pthread_create(&consumers[j], NULL, _consumer, (void *) j);
    }

    for(int i = 0; i < number_of_producers; i++) {
        pthread_join(producers[i], NULL);
    }
    for(int j = 0; j < number_of_consumers; j++) {
        pthread_join(consumers[j], NULL);
    }

    return 0;
}

void *_producer(void* argument) {

    int producer_id = (int) argument;
    int item;
    int item1;
    producer_id += 1;
    do {
        sleep(1 + (rand() % 5));

        item = rand() % 40;
        printf("\nProducer %d produced %d", producer_id, item);

        if(sem_getvalue(&empty, &item1) == 0) {
            printf("\nProducer %d blocked", producer_id);
        }

        sem_wait(&empty);
        sem_wait(&mutex);

        printf("\nProducer %d entered the critical section", producer_id);

        buffer[counter++] = item;
        printf("\nBuffer : [ ");
        for(int i = 0; i < counter; i++) {
            printf("%d ", buffer[i]);
        }
        printf("]");

        sem_post(&mutex);
        sem_post(&full);
    
    } while(1);
}

void *_consumer(void* argument) {

    int consumer_id = (int) argument;
    int item;
    do {

        sleep(1 + (rand() % 5));

        if(sem_getvalue(&full, &item) == 0) {
            printf("\nConsumer %d blocked", consumer_id + 1);
        }

        sem_wait(&full);
        sem_wait(&mutex);

        printf("\nConsumer %d entered the critical section", consumer_id + 1);

        item = buffer[--counter];
        printf("\nBuffer : [ ");
        for(int i = 0; i < counter; i++) {
            printf("%d ", buffer[i]);
        }
        printf("]");

        sem_post(&mutex);
        sem_post(&empty);

        printf("\nConsumer %d consumed %d", consumer_id, item);

    } while(1);
}