#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct Personagem{

	char* nome;
	int altura;
	double peso;
	char* corDoCabelo;
	char* corDaPele;
	char* corDosOlhos;
	char* anoNascimento;
	char* genero;
	char* homeworld;

} Personagem;


//declaracoes

void mallocPerson (Personagem* p_person);

void preencherPersonagem(Personagem* p_person, char* s);


//construtores

/*
*constructor1
*@param todos atributos
*@return Personagem*
*/
Personagem* constructor1 (char* nome, int altura, double peso, char* corDoCabelo,
		   char* corDaPele, char* corDosOlhos, char* anoNascimento,
		   char* genero, char* homeworld){

	Personagem* p_person = (Personagem*) malloc(sizeof(Personagem)*1);
	p_person->nome = nome;
	p_person->altura = altura;
	p_person->peso = peso;
	p_person->corDoCabelo = corDoCabelo;
	p_person->corDaPele = corDaPele;
	p_person->corDosOlhos = corDosOlhos;
	p_person->anoNascimento = anoNascimento;
	p_person->genero = genero;
	p_person->homeworld = homeworld;

	return(p_person);
}

/*
*constructor2
*@param char* endereco arquivo
*@return Personagem
*/
Personagem* constructor2(char* endereco){
	//declaracoes
	FILE* arquivo = fopen(endereco, "r");
	Personagem* p_person = (Personagem*) malloc(sizeof(Personagem)*1);

	//teste
	if (arquivo==NULL)
		printf("%s\n", "Erro ao abrir arquivo");
	else{
		char* lida = (char*) malloc(sizeof(char)*1000);
		fgets(lida, 999, arquivo); 	
		lida[strlen(lida)-1]='\0';
		
		//alocar memoria
		mallocPerson(p_person);

		//preencher personagem			
		preencherPersonagem(p_person, lida);		
	}

	return (p_person);
}

/*
*removeChar
*@param char, char*
*/
void removeChar (char c, char* s){
	int pre = 0;
	int pos = 0;
		
	while (s[pre]!='\0'){
		if (s[pre]!=c){
			s[pos] = s[pre];
			pos++;
		}
		pre++;
	}
	s[pos]='\0';
}

/*
*mallocPerson
*@param Personagem*
*/
void mallocPerson (Personagem* p_person){
	p_person->nome = (char*) malloc(sizeof(char)*100);
	p_person->corDoCabelo = (char*) malloc(sizeof(char)*100);
	p_person->corDaPele = (char*) malloc(sizeof(char)*100);
	p_person->corDosOlhos = (char*) malloc(sizeof(char)*100);
	p_person->anoNascimento = (char*) malloc(sizeof(char)*100);
	p_person->genero = (char*) malloc(sizeof(char)*100);
	p_person->homeworld = (char*) malloc(sizeof(char)*100);
}

/*
*freePerson
*@param Personagem*
*/
void freePerson (Personagem* p_person){
	free(p_person->nome);
	free(p_person->corDoCabelo);
	free(p_person->corDaPele);
	free(p_person->corDosOlhos);
	free(p_person->anoNascimento);
	free(p_person->genero);
	free(p_person->homeworld);

}


/*
*clone
*@param Personagem*
*@return Personagem*
*/
Personagem* clone(Personagem* p_person){
	//delcaracoes
	Personagem* p_clone = (Personagem*) malloc(sizeof(Personagem)*1);
	
	//alocar memoria para clone
	mallocPerson(p_clone);
	
	strcpy(p_clone->nome, p_person->nome);
	p_clone->altura = p_person->altura;	
	p_clone->peso = p_person->peso;
	strcpy(p_clone->corDoCabelo, p_person->corDoCabelo);
	strcpy(p_clone->corDaPele, p_person->corDaPele);
	strcpy(p_clone->corDosOlhos, p_person->corDosOlhos);
	strcpy(p_clone->anoNascimento, p_person->anoNascimento);
	strcpy(p_clone->genero, p_person->genero);
	strcpy(p_clone->homeworld, p_person->homeworld);
	
	return (p_clone);
}

/*
*imprimir
*/
void imprimir(Personagem* p_person){

	printf("%s%s", " ## ", p_person->nome);
	printf("%s%d", " ## ", p_person->altura);
	printf("%s%g", " ## ", p_person->peso);
	printf("%s%s", " ## ", p_person->corDoCabelo);
	printf("%s%s", " ## ", p_person->corDaPele);
	printf("%s%s", " ## ", p_person->corDosOlhos);
	printf("%s%s", " ## ", p_person->anoNascimento);
	printf("%s%s", " ## ", p_person->genero);
	printf("%s%s", " ## ", p_person->homeworld);
	printf(" ## \n");

	}

char*  ler(Personagem* p_person){
	char buffer[50];
	char* lida = (char*) malloc(sizeof(char)*1000);
	
	strcat(lida, " ## ");
	strcat(lida, p_person->nome);
	
	strcat(lida, " ## ");
	sprintf(buffer, "%d", p_person->altura);
	strcat(lida, buffer);	
	
	strcat(lida, " ## ");
	strcat(lida, buffer);
	sprintf(buffer, "%g", p_person->peso);
	
	strcat(lida, " ## ");
	strcat(lida, p_person->corDoCabelo);

	strcat(lida, " ## ");
	strcat(lida, p_person->corDaPele);
	
	strcat(lida, " ## ");
	strcat(lida, p_person->corDosOlhos);
	
	strcat(lida, " ## ");
	strcat(lida, p_person->anoNascimento);
	
	strcat(lida, " ## ");
	strcat(lida, p_person->genero);
	
	strcat(lida, " ## ");
	strcat(lida, p_person->homeworld);

	strcat(lida, " ## ");
			
	return(lida);	
}


bool isFim(char* s){
	return (strcmp(s, "FIM") == 0);
}


void preencherPersonagem(Personagem* p_person, char* s){
	char buffer[9][100];
	int c_buffer= 0;
	int c_s=0;
	int init = 0;
	int end = -1;
		
	for (int i=0; i<9; i++){
		init=end+1;
		while (s[init]!='\''){
			init++;
		}
		end=init+1;
		while (s[end]!='\''){
			end++;
		}
		init=end+1;
		while (s[init]!='\''){
			init++;
		}
		end=init+1;
		while (s[end]!='\''){
			end++;
		}
		
		c_s=init+1;
		while(c_s<end){
			buffer[i][c_buffer] = s[c_s];
			buffer[i][c_buffer+1]='\0';
			c_buffer++;
			c_s++;
		}
		c_buffer=0;
	}

	strcpy(p_person->nome, buffer[0]);
	
	if (0 == sscanf(buffer[1], "%d", &p_person->altura))//testar unknown
		p_person->altura=0;

	removeChar(',', buffer[2]); //retirar virgula do peso	
	if (0 == sscanf(buffer[2], "%lf", &p_person->peso))//testar unknown
		p_person->peso=0;

	strcpy(p_person->corDoCabelo, buffer[3]);
	strcpy(p_person->corDaPele, buffer[4]);
	strcpy(p_person->corDosOlhos, buffer[5]);
	strcpy(p_person->anoNascimento, buffer[6]);
	strcpy(p_person->genero, buffer[7]);
	strcpy(p_person->homeworld, buffer[8]);
		
}


double compare(Personagem* p_person1, Personagem* p_person2, int op){
	double resp = 0;
	
	switch(op){
		case 1:
		 resp = p_person1->altura - p_person2->altura;
		 break;
		case 2:
		 resp = p_person1->peso - p_person2->peso;
		 break;
		case 3:
		 resp = strcmp(p_person1->corDoCabelo, p_person2->corDoCabelo);
		 break;
		case 4:
		 resp = strcmp(p_person1->corDaPele, p_person2->corDaPele);
		 break;
		case 5:
		 resp = strcmp(p_person1->corDosOlhos, p_person2->corDosOlhos);
		 break;
		case 6:
		 resp = strcmp(p_person1->anoNascimento, p_person2->anoNascimento);
		 break;
		case 7:
		 resp = strcmp(p_person1->genero, p_person2->genero);
		 break;
		case 8:
		 resp = strcmp(p_person1->homeworld, p_person2->homeworld);
		 break;
		default:
		 resp = strcmp(p_person1->nome, p_person2->nome);

	}
	
	if (resp == 0){
		resp = strcmp(p_person1->nome, p_person2->nome);
	}

	return resp;
} 
typedef struct Lista{
 
//atributos
	int tamanho;
	int fim;
	Personagem** list;
}Lista;

//construtor
/**
*construtorLista - inicializa uma lista
*@param int tamanho
*@return list*
*/
Lista* construtorLista (int size){

	Lista* p_lista;

	if (size < 1){
		printf("%s\n", "Erro - Tamanho da Lista Invalido");
	}
	else{
		p_lista = (Lista*) malloc (sizeof(Lista)*1);	
		p_lista->tamanho = size;
		p_lista->fim = 0;
		p_lista->list = (Personagem**) malloc(sizeof(Personagem*)*size);
	}
	return p_lista;
}

//metodos

/**
*inserirInicio - insere um elemento no inicio da lista
*@param Lista* Personagem*
*/
void inserirInicio(Lista* p_lista, Personagem* p_person){
	if (p_lista->fim < p_lista->tamanho){
		for (int i=p_lista->fim; i>0; i--){
			p_lista->list[i] = p_lista->list[i-1];
		}
		p_lista->list[0] = p_person;
		p_lista->fim++;
	}
	else{
		printf("%s\n", "Erro - Lista Cheia");
	}
}

/**
*inserirFim - insere um elemento no fim da lista
*@param Lista* Personagem*
*/
void inserirFim (Lista* p_lista, Personagem* p_person){
	if (p_lista->fim < p_lista->tamanho){
		p_lista->list[p_lista->fim] = p_person;
		p_lista->fim++;
	}
	else{
		printf("%s\n", "Erro - Lista Cheia");
	}
}

void inserir (Lista* p_lista, Personagem* p_person, int pos){
	if (p_lista->fim >= p_lista->tamanho){
		printf("%s\n", "Erro - Lista Cheia");
	}
	else{
		if (p_lista->fim < pos){
			printf("%s\n", "Erro - Lista menor que posicao");
		}
		else{
			for (int i=p_lista->fim; i>pos; i--){
				p_lista->list[i] = p_lista->list[i-1];
			}
			p_lista->list[pos] = p_person;
			p_lista->fim++;
		}
	}
}
Personagem* removerInicio (Lista* p_lista){
	Personagem* p_person;

	if (p_lista->fim < 1){
		printf("%s\n", "Erro - Lista Vazia");
	}
	else{
		p_person = p_lista->list[0];
		for (int i=1; i<p_lista->fim; i++){
			p_lista->list[i-1] = p_lista->list[i];
		}
		p_lista->fim--;
	}		
	return p_person;	
}

/**
*removerFim - remove um elemento do fim da lista
*@return Personagem*
*/
Personagem* removerFim (Lista* p_lista){
	Personagem* p_person;

	if (p_lista->fim < 1){
		printf("%s\n", "Erro - Lista Vazia");
	}
	else{
		p_person = p_lista->list[p_lista->fim-1];
		p_lista->fim--;
	}
	return p_person;
}

/**
*remover - remove um elemento da posicao
*@param int posicao
*@return Personagem*
*/
Personagem* remover (Lista* p_lista, int pos){
	Personagem* p_person;

	if (p_lista->fim < 1){
		printf("%s\n", "Erro - Lista Vazia");
	}
	else{
		if (pos >= p_lista->fim){
			printf("%s\n", "Erro - Posicao Vazia");
		} 
		else{
			p_person = p_lista->list[pos];
			while(pos+1 < p_lista->fim){
				p_lista->list[pos] = p_lista->list[pos+1];
				pos++;
			}
			p_lista->fim--;
		}
	}
	return p_person;
}

/**
*mostrar lista
*@param Lista*
*/
void mostrar(Lista* p_lista){
	for (int i=0; i<p_lista->fim; i++){
		printf("%s%d%s", "[", i, "] ");
		imprimir(p_lista->list[i]);
	} 
}

/**
*mostrar2 lista
*@param Lista*
*/
void mostrar2(Lista* p_lista){
	for (int i=0; i<p_lista->fim; i++){
		imprimir(p_lista->list[i]);
	} 
}

/**
*comandos - executa os comandos contidos em uma string
*@param Lista*. char* com comando
*/
void comandos (Lista* p_lista, char* input){
	char* cmd;
	char* n;
	char* path;
	int pos;
	Personagem* p_person;

	cmd = strtok(input, " ");

	if (strcmp(cmd, "II")==0){
		path = strtok (NULL, " ");
		p_person = constructor2(path);
		inserirInicio(p_lista, p_person);
	}	
	if (strcmp(cmd, "IF")==0){
		path = strtok (NULL, " ");
		p_person = constructor2(path);
		inserirFim(p_lista, p_person);	
	}
	if (strcmp(cmd, "I*")==0){
		n = strtok(NULL, " ");
		sscanf(n, "%d", &pos);
		path = strtok (NULL, " ");
		p_person = constructor2(path);
		inserir(p_lista, p_person, pos);	
	}
	if (strcmp(cmd, "RI")==0){
		p_person = removerInicio(p_lista);
		printf("%s%s\n", "(R) ", p_person->nome);
		freePerson(p_person);
	}
	if (strcmp(cmd, "RF")==0){
		p_person = removerFim(p_lista);
		printf("%s%s\n", "(R) ", p_person->nome);
		freePerson(p_person);
	}

	if (strcmp(cmd, "R*")==0){
		n = strtok (NULL, " ");
		sscanf(n, "%d", &pos);
		p_person = remover(p_lista, pos);
		printf("%s%s\n", "(R) ", p_person->nome);
		freePerson(p_person);
	}
}

/**
*freeLista - libera a memoria da lista
*@param Lista*
*/
void freeLista (Lista* p_lista){
	for (int i=0; i<p_lista->fim; i++){
		freePerson(p_lista->list[i]);
	}
	free(p_lista);
}

/**
*zerarPesos
*@param Lista*c
*/
void zerarPesos(Lista* p_lista){
	for (int i=0; i<p_lista->fim; i++){
		p_lista->list[i]->peso=0.0;
	}
}

/**
*pesquisaBinNome
*@param Lista*, char* nome
*@return int (boolean);
*/
int pesquisaBinNome(Lista* p_lista, char* nome, int inicio, int fim){
	int resp = 0;

	int n = (fim-inicio)/2 + inicio ;

	if (strcmp(p_lista->list[n]->nome, nome)==0){
		resp = 1;
	}
	else{
		if(strcmp(p_lista->list[n]->nome, nome)>0){
			if(inicio<fim){
				resp = pesquisaBinNome(p_lista, nome, inicio, n);
			}
		}
		else{
			if(inicio<fim-1){
				resp = pesquisaBinNome(p_lista, nome, n, fim);
			}
		}
	}

	return resp;
}

/**
*swap - troca de posicao dois elementos da lista
*@param Lista*, int i, int j
*/
void swap(Lista* p_lista, int i, int j){
	Personagem* buffer = p_lista->list[i];
	p_lista->list[i] = p_lista->list[j];
	p_lista->list[j] = buffer;
}

/**
*ordenarQuickSort - ordena metodo quicksort
*@param Lista*, int log[]
*/
void ordenarQuickSort (Lista* p_lista, int esq, int dir, int log[]){
	
	int i = esq;
	int j = dir;

	Personagem* pivo = p_lista->list[(j+i)/2];
        
	while (i<=j){
		
		log[0]+=2;		

		while (compare(p_lista->list[i], pivo, 3)<0.0){
			i++;

			log[0]++;
		}
		while (compare(p_lista->list[j], pivo, 3)>0.0){
			j--;

			log[0]++;
		}

		if(i<=j){
			swap(p_lista, i,j);
			i++;
			j--;

			log[1]+=3;
		}
	}

        if (esq<j){
		ordenarQuickSort(p_lista, esq, j, log);
	}
	if (i<dir){
		ordenarQuickSort(p_lista, i, dir, log);
	}

	
}

int  main(void){
        
	Lista* p_lista = construtorLista(100);
	int log[] ={0, 0};
	Personagem* p;
	char* input = (char*) malloc(sizeof(char) * 100);
	
	
	fgets(input, 99, stdin);
	input[strlen(input)-1]='\0';
		
	while(!isFim(input)){
		p = constructor2(input);
		inserirFim(p_lista, p);
		fgets(input, 99, stdin);
		input[strlen(input)-1]='\0';
	}	

	clock_t inicio = clock();
	ordenarQuickSort(p_lista, 0, p_lista->fim-1,  log);
	clock_t fim = clock();

	mostrar2(p_lista);

	double segundos = (fim - inicio) / (double)CLOCKS_PER_SEC / 1000.0;

	//arquivo log
	FILE* arq = fopen("651230_quicksort.txt", "wt");
	fprintf (arq, "%s\t%d\t%d\t%lf","651230", log[0], log[1], segundos);
	fclose(arq);	
 
	freeLista(p_lista);
}	


