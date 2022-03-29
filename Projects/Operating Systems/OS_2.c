#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "a2_helper.h"
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>

#define threads_nr 4
typedef struct{
    int i;
    int p;
}th_struct;

pthread_mutex_t lock1, lock2;
pthread_mutex_t mutex1, mutex2, mutex3;
sem_t semaphore1, semaphore2;
int activeThreads = 0;
int closedThreads = 0;
int valid = 0;
//bool value = false;

void* thread_function23(void* params){
    th_struct* th = (th_struct*) params;
    
    if(th->i == 1){
        pthread_mutex_lock(&lock1);
    }

    info(BEGIN, 4, th->i);

    if(th->i == 4){
        pthread_mutex_unlock(&lock1);
        pthread_mutex_lock(&lock2);
    }

    info(END, 4, th->i);

    if(th->i == 1){
        pthread_mutex_unlock(&lock2);
    }
    return NULL;
}

void* thread_function24(void* params){
    th_struct* th = (th_struct*)params;
    
    sem_wait(&semaphore1);
    info(BEGIN, 6, th->i);

    sem_wait(&semaphore2);
    activeThreads++;
    sem_post(&semaphore2);

    if(th->i == 12){
        valid = 1;
        for(;;){
            sem_wait(&semaphore2);
            if(activeThreads == 4 || closedThreads == 43){
                info(END, 6, th->i);
                activeThreads--;
                valid = 0;
                sem_post(&semaphore2);
                break;
            }
            sem_post(&semaphore2);
        }
    }
    else{
        for(int q = 0; valid == 1; q++);
        sem_wait(&semaphore2);
        activeThreads--;
        closedThreads++;
        info(END, 6, th->i);
        sem_post(&semaphore2);
    }
    
    sem_post(&semaphore1);
    sem_destroy(&semaphore1);
    
    return NULL;
}

int main(){
    
    init();

    info(BEGIN, 1, 0);

    pid_t p2, p3, p4, p5, p6, p7, p8;
    p2 = fork();
    if(p2 == -1){
        perror("Could not create child process");
        return -1;
    }
    else{
        if(p2 == 0){
            //printf("\n[CHILD] My PID2 is %d. My parent's PID1 is %d.\n", getpid(), getppid());
            info(BEGIN, 2, 0);

            p3 = fork();
            if(p3 == -1){
                perror("Could not create child process");
                return -1;
            }
            else{
                if(p3 == 0){
                    info(BEGIN, 3, 0);
                    //printf("[CHILD] My PID3 is %d. My parent's PID2 is %d.\n", getpid(), getppid());
                    
                    p4 = fork();
                    if(p4 == -1){
                        perror("Could not create child process");
                        return -1;
                    }
                    else{
                        if(p4 == 0){
                            info(BEGIN, 4, 0);
                            //printf("[CHILD] My PID4 is %d. My parent's PID3 is %d.\n", getpid(), getppid());

                            pthread_t tid[4];
                            th_struct th[4];
                            pthread_mutex_init(&lock1, 0);
                            pthread_mutex_init(&lock2, 0);
                            pthread_mutex_lock(&lock1);
                            pthread_mutex_lock(&lock2);

                            for(int i = 0 ; i < threads_nr ; i++){
                                th[i].i = i + 1;
                                pthread_create(&tid[i], NULL, thread_function23, &th[i]);
                            }

                            for(int i = 0; i < threads_nr ; i++){
                                pthread_join(tid[i], NULL);
                            }

                            p7 = fork();
                            if(p7 == -1){
                                perror("Could not create child process");
                                return -1;
                            }
                            else{
                                if(p7 == 0){
                                    info(BEGIN, 7, 0);
                                    //printf("[CHILD] My PID7 is %d. My parent's PID4 is %d.\n", getpid(), getppid());
                                    info(END, 7, 0);
                                    exit(0);
                                }
                                else{
                                    wait(NULL);
                                }
                            }
                            info(END, 4, 0);
                            exit(0);
                        }
                        else{
                            wait(NULL);
                        }
                    }
                    info(END, 3, 0);
                    exit(0);
                }
                else{
                    wait(NULL);
                }
            }

            p5 = fork();
            if(p5 == -1){
                perror("Could not create child process");
                return -1;
            }
            else{
                if(p5 == 0){
                    info(BEGIN, 5, 0);
                    //printf("[CHILD] My PID5 is %d. My parent's PID2 is %d.\n", getpid(), getppid());

                    p6 = fork();
                    if(p6 == -1){
                        perror("Could not create child process");
                        return -1;
                    }
                    else{
                        if(p6 == 0){
                            info(BEGIN, 6, 0);
                            //printf("[CHILD] My PID6 is %d. My parent's PID5 is %d.\n", getpid(), getppid());
                            pthread_t tid[44];
                            th_struct th[44];
                            sem_init(&semaphore1, 0, 4);
                            if(sem_init(&semaphore2, 0, 1) != 0){
                                perror("Could not init the semaphore!");
                                return 0;
                            }
                            

                            for(int i = 0 ; i < 44 ; i++){
                                th[i].i = i + 1;
                                pthread_create(&tid[i], NULL, thread_function24, &th[i]);
                            }

                            for(int i = 0; i < 44 ; i++){
                                pthread_join(tid[i], NULL);
                            }

                            sem_destroy(&semaphore2);
                            info(END, 6, 0);
                            exit(0);
                        }
                        else{
                            wait(NULL);
                        }
                    }
                    info(END, 5, 0);
                    exit(0);
                }
                else{
                    wait(NULL);
                }
            }

            p8 = fork();
            if(p8 == -1){
                perror("Could not create child process");
                return -1;
            }
            else{
                if(p8 == 0){
                    info(BEGIN, 8, 0);
                    //printf("[CHILD] My PID8 is %d. My parent's PID2 is %d.\n", getpid(), getppid());
                    info(END, 8, 0);
                    exit(0);
                }
                else{
                    wait(NULL);
                }
            }
            info(END, 2, 0);
            exit(0);
        }
        else{
            wait(NULL);
        }
    }
    info(END, 1, 0);

    pthread_mutex_destroy(&lock1);
    pthread_mutex_destroy(&lock2);
    pthread_mutex_destroy(&mutex1);
    pthread_mutex_destroy(&mutex2);
    pthread_mutex_destroy(&mutex3);
    return 0;
}
