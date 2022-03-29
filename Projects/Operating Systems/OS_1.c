#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>


int succ;

int findAll(const char *path){
    int version=0;
    int header_size=0;
    char magic[2] = "";
    int nr_sections=0;
    char name[17] = "";
    int type=0;
    int offset=0;
    int size=0;
    //char m[2]; m[0] = '1'; m[1] = 'P'; m[2] = '\0';
    int fd = -1; 
    fd = open(path, O_RDONLY);
    
    if(fd == -1) {
        perror("Could not open output file");
        close(fd);
        return 0;
    }
    
    lseek(fd, -4, SEEK_END);
    
    read(fd, &header_size, 2);
    
    read(fd, magic, 2);
    magic[2] = '\0';

    if(strcmp(magic, "1P") != 0){
        //printf("ERROR\nwrong magic\n");
        return 0;
    }

    lseek(fd, -header_size, SEEK_END);

    read(fd, &version, 4);
    if( version < 44 || version >96){
        //printf("ERROR\nwrong version\n");
        return 0;
    }
    
    read(fd, &nr_sections, 1);
    if( nr_sections < 2 || nr_sections >20){
        //printf("ERROR\nwrong sect_nr\n");
        return 0;
    }

    int nrS = 0;
    int i = 0;
    while( i < nr_sections){
        read(fd, &name, 17);
        read(fd, &type, 4);

        if(type != 58 && type != 62 && type != 59 && type != 97 && type != 43 && type != 36){
            //printf("ERROR\nwrong sect_types\n");
            return 0;
        }
        

        read(fd, &offset, 4);
        read(fd, &size, 4);

        if(size <= 1098){
            nrS++;
        }

        i++;
    }

    if(nrS == nr_sections){
        return 1;
    }
    
    return 0;
}

void list(const char *path, int x, int y, int size, const char* sir)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];
    struct stat statbuf;

    dir = opendir(path);
    if(dir == NULL) {
        printf("ERROR\ninvalid directory path\n");
        return;
    }
    else{
        if(succ == 0){
            printf("SUCCESS\n");
            succ++;
        }
    }
    while((entry = readdir(dir)) != NULL) {
        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
            snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
            if(lstat(fullPath, &statbuf) == 0 && x == 0 && y == 0) {
                printf("%s\n", fullPath);
            }
            if(lstat(fullPath, &statbuf) == 0 && x == 1) {
                if((int)statbuf.st_size > size){
                    printf("%s\n", fullPath);
                }
            }
            if(lstat(fullPath, &statbuf) == 0 && y == 1) {

                char *p = strstr(entry->d_name, sir);
                if(p != NULL){
                    int s = strlen(sir);
                    int k=0;
                    for(int i=0 ; i < s; i++){
                        if(p[i] != entry->d_name[i]){
                            k++;
                        }
                    }
                    if(k == 0 ){
                    printf("%s\n", fullPath);
                    }
                }
            }
        }
    }
    closedir(dir);
} 

void listRec(const char *path, int x, int y, int size, const char *sir)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];
    struct stat statbuf;

    dir = opendir(path);
    if(dir == NULL) {
        printf("ERROR\ninvalid directory path\n");
        return;
    }
    else{
        if(succ == 0){
            printf("SUCCESS\n");
            succ++;
        }
    }
    while((entry = readdir(dir)) != NULL) {
        if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
            snprintf(fullPath, 512, "%s/%s", path, entry->d_name);
            if(lstat(fullPath, &statbuf) == 0 && x == 0 && y == 0) {
                printf("%s\n", fullPath);
                if(S_ISDIR(statbuf.st_mode)) {
                    listRec(fullPath, x, y, size, sir);
                }
            }
            if(lstat(fullPath, &statbuf) == 0 && x == 1) {
                if(statbuf.st_size > size){
                    printf("%s\n", fullPath);
                }
                if(S_ISDIR(statbuf.st_mode)) {
                    listRec(fullPath, x, y, size, sir);
                }
            }
            if(lstat(fullPath, &statbuf) == 0 && y == 1) {

                char *p = strstr(entry->d_name, sir);
                if(p != NULL){
                    int s = strlen(sir);
                    int k=0;
                    for(int i=0 ; i < s; i++){
                        if(p[i] != entry->d_name[i]){
                            k++;
                        }
                    }
                    if(k == 0 ){
                    printf("%s\n", fullPath);
                    }
                }

                if(S_ISDIR(statbuf.st_mode)) {
                    listRec(fullPath, x, y, size, sir);
                }
            }

            if(lstat(fullPath, &statbuf) == 0 && x == 2 && y == 2) {
                
                if(S_ISREG(statbuf.st_mode)){
                if(findAll(fullPath)){
                    printf("%s\n", fullPath);
                }
                }
                if(S_ISDIR(statbuf.st_mode)) {
                    listRec(fullPath, x, y, size, sir);
                }
            }
        }
    }
    closedir(dir);
} 



void parse(const char *path){
    int version=0;
    int header_size=0;
    char magic[2] = "";
    int nr_sections=0;
    char name[17] = "";
    int type=0;
    int offset=0;
    int size=0;
    //char m[2]; m[0] = '1'; m[1] = 'P'; m[2] = '\0';
    int fd = -1; 
    fd = open(path, O_RDONLY);
    
    if(fd == -1) {
        perror("Could not open output file");
        close(fd);
        return;
    }
    
    lseek(fd, -4, SEEK_END);
    
    read(fd, &header_size, 2);
    
    read(fd, magic, 2);
    magic[2] = '\0';
    if(strcmp(magic, "1P") != 0){
        printf("ERROR\nwrong magic\n");
        return;
    }

    lseek(fd, -header_size, SEEK_END);

    read(fd, &version, 4);
    if( version < 44 || version >96){
        printf("ERROR\nwrong version\n");
        return;
    }
    
    read(fd, &nr_sections, 1);
    if( nr_sections < 2 || nr_sections >20){
        printf("ERROR\nwrong sect_nr\n");
        return;
    }

    int i = 0;
    while( i < nr_sections){
        read(fd, &name, 17);
        read(fd, &type, 4);

        if(type != 58 && type != 62 && type != 59 && type != 97 && type != 43 && type != 36){
            printf("ERROR\nwrong sect_types\n");
            return;
        }

        read(fd, &offset, 4);
        read(fd, &size, 4);

        i++;
    }
    
    lseek(fd, -header_size, SEEK_END);
    printf("SUCCESS\n");
    read(fd, &version, 4);
    printf("version=%d\n", version);
    read(fd, &nr_sections, 1);
    printf("nr_sections=%d\n", nr_sections);
    i = 0;
    while( i < nr_sections){
        read(fd, name, 17);
        
        read(fd, &type, 4);
       
        read(fd, &offset, 4);
        read(fd, &size, 4);
        name[17] = '\0';

        printf("section%d: %s %d %d\n", i + 1, name, type, size);
        

        i++;
    }
    //free(name);
    
    close(fd);

}


void extract(const char *path, const char *sectionC, const char *lineC){
    int section = atoi(sectionC);
    int line = atoi(lineC);
    int version=0;
    int header_size=0;
    char magic[2] = "";
    int nr_sections=0;
    char name[17] = "";
    int type=0;
    int offset=0;
    int size=0;
    //char m[2]; m[0] = '1'; m[1] = 'P'; m[2] = '\0';
    int fd = -1; 
    fd = open(path, O_RDONLY);
    
    if(fd == -1) {
        perror("Could not open output file");
        close(fd);
        return;
    }
    
    lseek(fd, -4, SEEK_END);
    
    read(fd, &header_size, 2);
    
    read(fd, magic, 2);
    magic[2] = '\0';
    if(strcmp(magic, "1P") != 0){
        printf("ERROR\nwrong magic\n");
        return;
    }

    lseek(fd, -header_size, SEEK_END);

    read(fd, &version, 4);
    if( version < 44 || version >96){
        printf("ERROR\nwrong version\n");
        return;
    }
    
    read(fd, &nr_sections, 1);
    if( nr_sections < 2 || nr_sections >20){
        printf("ERROR\nwrong sect_nr\n");
        return;
    }

    int i = 0;
    while( i < nr_sections){
        read(fd, &name, 17);
        read(fd, &type, 4);

        if(type != 58 && type != 62 && type != 59 && type != 97 && type != 43 && type != 36){
            printf("ERROR\nwrong sect_types\n");
            return;
        }

        read(fd, &offset, 4);
        read(fd, &size, 4);

        if(i == section - 1){
            char *buff1 = (char*)malloc(sizeof(char) * size);
            int lines = 1;
            if(buff1 == NULL){
                printf("No memory\n");
                free(buff1);
                return;
            }
            if(lseek(fd, offset, SEEK_SET) == -1){
                printf("ERROR1\n");
                free(buff1);
                return;
            }
            //printf("%d\n", fd);
            if(read(fd, buff1, size) != size){
                printf("ERROR2\n");
                free(buff1);
                return;
            }
            for (int buffPoz = 0; buffPoz < size; buffPoz++){
                if(buff1[buffPoz] == '\n'){
                    lines++;
                }
            }
            lines++;
            line = lines-line;
            char *buff2 = (char*)malloc(sizeof(char) * size);
            
            if(lseek(fd, offset, SEEK_SET) == -1){
                printf("ERROR3\n");
                free(buff2);
                return;
            }
            //printf("%d\n", fd);
            //printf("%s\n", buff2);
            //read(fd, buff2, size);
            
            if(read(fd, buff2, size) != size){
                printf("ERROR4\n");
                free(buff2);
                return;
            }
            buff2[strlen(buff2)] = '\0';
            //printf("Bubuie1\n");
            char *stringLine = (char*)malloc(sizeof(char) * size);
            int iL = 0;
            lines = 0;
            //printf("Bubuie1\n");
            for(int buffPoz = 0; buffPoz < size; buffPoz++){
            
                if(lines + 1 == line){
                    if(buff2[buffPoz] == '\n'){
                        stringLine[iL] = '\0';
                        break;
                    }
                    stringLine[iL] = buff2[buffPoz];
                    iL++;
                }
                else{
                    if(buff2[buffPoz] == '\n'){
                        lines++;
                }
                }
            }
            printf("SUCCESS\n");
            stringLine[strlen(stringLine)] = '\0';
            for(int j = strlen(stringLine) - 1 ; j >= 0; j--){
                printf("%c", stringLine[j]);
            }
            printf("\n");
            free(buff2);
            free(buff1);
            free(stringLine);
            return;
        }
        i++;
    }
    
    
    printf("%d %d %d\n", section, line, i);
    printf("ERROR\ninvalid section\n");
    return;
    
    close(fd);

}




int main(int argc, char **argv){
    if(argc == 2){
        if(strcmp(argv[1], "variant") == 0){
            printf("47395 \n");
        }
    }
    
    if(argc > 2){
    char listA[512];
    snprintf(listA, 512, "%s", "list");
    if(!strcmp(listA, argv[1])){
        
        int okR = 0;
        int okB = 0;
        int okS = 0;
        int s = -1;
        char b[512];

       
        int nrarg = argc - 1;
        
        for(int i = 2 ; i < nrarg; i++){
            
            
            if(strcmp(argv[i], "recursive") == 0){
                okR = 1;
            }

            else{
                if(strstr(argv[i], "size_greater=")){
                    okS = 1;
                    s = atoi(argv[i]+13);
                }
                else{
                    
                    okB = 1;
                    //b = (char*)malloc(sizeof(char) * (strlen(argv[i])-17));
                    strcpy(b, argv[i] + 17);
                    
                }
            }

        }

        if(strstr(argv[nrarg], "path=")){
            
            char *p ;
            
            int nr = strlen(argv[nrarg])-5;
            p = (char*)malloc(sizeof(char) * nr);
            strcpy(p, argv[nrarg] + 5);
            

            if(okR){
                    listRec(p, okS, okB, s, b);
            }
            else{
                
                    list(p, okS, okB, s, b);
            }
            free(p);
        }
        else{
            printf("ERROR\ninvalid directory path\n");
            //free(b);

        }

    }
    }

    if(!strcmp(argv[1], "parse")){
        if(argc > 2){
            parse(argv[2] + 5);
        }
        else{
            printf("ERROR\ninvalid directory path\n");
        }
    }

    if(!strcmp(argv[1], "extract")){
        if(argc > 4){
            extract(argv[2] + 5, argv[3] + 8, argv[4] + 5);
        }
        else{
            printf("ERROR\ninvalid directory path\n");
        }
    }

    if(!strcmp(argv[1], "findall")){
        if(argc > 2){
            listRec(argv[2] + 5, 2, 2, 0, "");
        }
        else{
            printf("ERROR\ninvalid directory path\n");
        }
    }

   //list(argv[2]);
    return 0;
}
