#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

void* _writer(void *);
void* _reader(void *);

int data;

int number_of_writers;
int number_of_readers;

sem_t write_block;
sem_t mutex;

pthread_t *writers;
pthread_t *readers;

int counter;

int main(int argc, char* argv[]) {

    int counter = 0;

    printf("\n> Enter the number of writers : ");
    scanf("%d", &number_of_writers);

    printf("\n> Enter the number of readers : ");
    scanf("%d", &number_of_readers);

    sem_init(&write_block, 0, 1);
    sem_init(&mutex, 0, 1);

    writers = (pthread_t *) malloc(sizeof(pthread_t) * number_of_writers);
    readers = (pthread_t *) malloc(sizeof(pthread_t) * number_of_readers);

    for(int i = 0; i < number_of_writers; i++) {
        pthread_create(&writers[i], NULL, _writer, (void *) i);
    }
    for(int j = 0; j < number_of_readers; j++) {
        pthread_create(&readers[j], NULL, _reader, (void *) j);
    }

    for(int i = 0; i < number_of_writers; i++) {
        pthread_join(writers[i], NULL);
    }
    for(int j = 0; j < number_of_readers; j++) {
        pthread_join(readers[j], NULL);
    }

    return 0;
}

void *_writer(void* argument) {

    int writer_id = (int) argument;
    int item;
    int item1;
    writer_id += 1;
    do {
        sleep(1 + (rand() % 5));
        data++;
        printf("\nwriter %d created %d", writer_id, data);
        sem_getvalue(&write_block, &item1);
        if(item1 == 0) {
            printf("\nwriter %d blocked", writer_id);
        }
        sem_wait(&write_block);
        printf("\nwriter %d entered the critical section", writer_id);
        printf("\nwriter %d wrote %d", writer_id, data);
        sem_post(&write_block);
        printf("\nwriter %d left the critical section", writer_id);
    } while(1);
}

void *_reader(void* argument) {

    int reader_id = (int) argument;
    int item;
    do {
        sleep(1 + (rand() % 5));
        
        sem_getvalue(&write_block, &item);
        if(item == 0) {
            printf("\nreader %d blocked", reader_id + 1);
        }

        sem_wait(&mutex);
        counter += 1;
        if(counter == 1) {
            sem_wait(&write_block);
        }
        sem_post(&mutex);

        printf("\nreader %d entered the critical section", reader_id + 1);
        printf("\nData reader by reader %d : [ ", reader_id + 1);
        printf("%d ", data);
        printf("]");
        sleep(1 + rand() % 2);
        
        sem_wait(&mutex);
        counter -= 1;
        if(counter == 0) {
            sem_post(&write_block);
        }
        sem_post(&mutex);

        printf("\nreader %d left the critical section", reader_id + 1);

    } while(1);
}