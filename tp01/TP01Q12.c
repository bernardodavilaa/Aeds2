#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include <string.h>

bool Chama(char vetor[1000]){
  for(int i=0; i<strlen(vetor)/2;i++){
      if(vetor[i] != vetor[strlen(vetor)-1-i])
        return false;
  }
  return true;
}

int main(void) {
  char entrada[1000];
  scanf(" %[^\n]", entrada);
  while(!(entrada[0]=='F'&&entrada[1]=='I'&&entrada[2]=='M'&&entrada[3]=='\0')){
    if(Chama(entrada))
      printf("SIM\n");
    else
      printf("NAO\n");
    scanf(" %[^\n]", entrada);
  }
    
  }
