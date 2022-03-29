#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>

#define FIFO_NAME1 "RESP_PIPE_47395"
#define FIFO_NAME2 "REQ_PIPE_47395"
#define loop 1

char sir[100];
unsigned int shm;
char *share = NULL;
unsigned char dim;
unsigned char size;

int main(void){
    if(access(FIFO_NAME1, 0 ) == 0){
        printf("Deleting pipe 1\n");
        unlink(FIFO_NAME1);
    }
    int fd[2];
    fd[1] = -1; fd[0] = -1;

    // 1. Se creaza pipe-ul pentru scriere
    if(mkfifo(FIFO_NAME1, 0600) != 0){
        perror("ERROR\ncannot create the response pipe | cannot open the respone pipe");
        exit(1);
    }

    // 2. Se deschide pipe-ul in citire
    fd[1] = open(FIFO_NAME2, O_RDONLY);
    if(fd[1] == -1){
        perror("ERROR\ncannot create the response pipe | cannot open the respone pipe");
        exit(1);
    }

    // 3. Se deschode in scriere pipe-ul
    fd[0] = open(FIFO_NAME1, O_WRONLY);
    if(fd[0] == -1){
        perror("ERROR\ncannot create the response pipe | cannot open the respone pipe");
        exit(1);
    }
    // 4. Se scrie mesajul
    unsigned char sizeC;
    sizeC = strlen("CONNECT");
    if(write(fd[0], &sizeC, 1) != -1 && write(fd[0], "CONNECT", 7) != -1){
        printf("SUCCESS\n");
    }
    
    unsigned char sizePING = strlen("PING");
    unsigned char sizePONG = strlen("PONG");
    

    while(loop){
        //char sir[100];
        read(fd[1], &sizePING, 1);
        read(fd[1], sir, sizePING);
        //sir[4] = '\0';
        printf("%s\n", sir);
        if(strcmp(sir, "PING") == 0){
            int value = 47395;
            write(fd[0], &sizePING, 1);
            write(fd[0], sir, sizePING);
            write(fd[0], &sizePONG, 1);
            write(fd[0], "PONG", sizePONG);
            write(fd[0], &value, sizeof(int));
        }
        
        if(strstr(sir, "EXIT")){
            printf("EXIT\n");
            close(fd[0]);
            close(fd[1]);
            unlink(FIFO_NAME1);
            break;
            //exit(1);
            
        }
        //printf("%s\n", sir);
        if(strcmp(sir, "CREATE_SHM") == 0){
            unsigned char oct;
            int x;
            read(fd[1], &oct, 1);
            read(fd[1], &x, oct);
            //int value = x;
            //printf("value : %d\n", value);
            shm = shm_open("/wWdeMuR", O_CREAT | O_RDWR, 0664);
            if(shm < 0){
                perror("Could not auire shm");
                write(fd[0], &sizePING, 1);
                write(fd[0], &sir, sizePING);

                unsigned char dimE = 5;
                write(fd[0], &dimE, 1);
                write(fd[0], "ERROR", dimE);
                //return 0;
            }
            ftruncate(shm, 3979578);
            share = (char*)mmap(0, sizeof(char), PROT_READ| PROT_WRITE, MAP_SHARED, shm, 0);
            if(share == (void*) -1){
                perror("Could not map the shared memory");
                write(fd[0], &sizePING, 1);
                write(fd[0], &sir, sizePING);

                unsigned char dimE = 5;
                write(fd[0], &dimE, 1);
                write(fd[0], "ERROR", dimE);
                //return 0;
            }
            //printf("adasda");
            unsigned char dimS = 7;
            write(fd[0], &sizePING, 1);
            write(fd[0], &sir, sizePING);
            write(fd[0], &dimS, 1);
            write(fd[0], "SUCCESS", dimS);
        }

        if(strcmp("WRITE_TO_SHM", sir) == 0){
            //int shm = shm_open("/wWdeMuR", O_CREAT | O_RDWR, 0664);
            unsigned int value, offset;   
            read(fd[1], &offset, 4);
            read(fd[1], &value, 4);
            //printf("%d %d\n", offset, value);
            
            

            if(offset >= 0 && offset <= 3979574){
                //munmap(&offset, value);
                lseek(shm, offset, SEEK_CUR);
                write(shm, &value, sizeof(value));

                write(fd[0], &sizePING, 1);
                write(fd[0], &sir, sizePING);
                unsigned char dimS = 7;
                write(fd[0], &dimS, 1);
                write(fd[0], "SUCCESS", dimS);
                //printf("SDAD");
            }
            else{
                
                write(fd[0], &sizePING, 1);
                write(fd[0], &sir, sizePING);
                unsigned char dimE = 5;
                write(fd[0], &dimE, 1);
                write(fd[0], "ERROR", dimE);
            }
        }

        if(strstr("MAP_FILE", sir)){
            unsigned char sizef;
            char s[100];
            read(fd[1], &sizef, 1);
            //s = (char*)malloc(sizef);
            read(fd[1], &s, sizef);
            s[sizef] = '\0';
            //char c[100] = "/";
            //strcat(c, s);
            printf("\nSIR : %s\n", s);
            char* aux = NULL;
            dim = open(s, O_RDONLY);
            size = lseek(dim, 0, SEEK_END);
            aux = (char*)mmap(0, size, PROT_READ, MAP_SHARED, dim, 0);
            if(aux == (void*) -1){
                perror("Could not map the shared memory");
                write(fd[0], &sizePING, 1);
                write(fd[0], &sir, sizePING);

                unsigned char dimE = 5;
                write(fd[0], &dimE, 1);
                write(fd[0], "ERROR", dimE);
                //return 0;
            }
            else{
                write(fd[0], &sizePING, 1);
                write(fd[0], &sir, sizePING);
                unsigned char dimS = 7;
                write(fd[0], &dimS, 1);
                write(fd[0], "SUCCESS", dimS);
            }
            //free(s);
            return 0;
        }

        if(strcmp("READ_FROM_FILE_OFFSET", sir) == 0){
            return 0;
        }

        if(strcmp("READ_FROM_FILE_SECTION", sir) == 0){
            return 0;
        }

        if(strcmp("READ_FROM_LOGICAL_SPACE_OFFSET", sir) == 0){
            return 0;
        }

        //free(sir);

    }
    

    return 0;
    
}