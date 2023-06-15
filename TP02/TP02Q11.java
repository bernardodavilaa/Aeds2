
import java.util.Date;


public class TP02Q11{
	
	public static void main(String[] args) throws Exception{
		Lista list = new Lista();
		long inicio;
		long fim; 

		String input = MyIO.readLine();
		input = Personagem.toUtf(input);
		while(!Personagem.isFim(input)){
			list.inserirFim( new Personagem(input));
			input = MyIO.readLine();
			input = Personagem.toUtf(input);
		}
		
		inicio = tempo();
		int[] n = list.ordenarInsercao();
		fim = tempo();			

		double segundos = ((double)(fim-inicio)) / 1000.0;
		Arq.openWrite("651230_insercao.txt");
		Arq.print("651230\t"+n[0]+"\t"+n[1]+"\t"+segundos);
		Arq.close();
	
		list.mostrar('c');
	}	

	public static long tempo(){
		return (new Date().getTime());
	}
}



class Lista{
	private int tamanho;
	private int fim;
	private Personagem[] list;

	Lista (int tamanho){
		this.tamanho=tamanho;
		this.fim = 0;
		this.list = new Personagem[tamanho];
	}

	Lista (){
		this(100);
	}

 
	public void inserirInicio(Personagem person) throws Exception{
		if (this.fim < this.tamanho){
			for (int i=fim; i>0; i--){
				this.list[i]=this.list[i-1];
			}
			this.list[0] = person;
			this.fim++;
		}	
		else{
			throw new Exception("Erro - Lista Cheia");
		}
	}

	
	public void inserirFim(Personagem person) throws Exception{
		if (this.fim < this.tamanho){
			this.list[fim] = person;
			this.fim++;
		}
		else{
			throw new Exception("Erro - Lista Cheia");
		}
	}
	
	
	public void inserir(Personagem person, int pos) throws Exception{
		if (this.fim < pos){
			throw new Exception("Erro - Lista menor que posicao");
		}
		else if (this.fim < this.tamanho){
			for (int i=fim; i>pos; i--){
				this.list[i]=this.list[i-1];
				
			}
			this.list[pos] = person;
			this.fim++;
		}	
		else{
			throw new Exception("Erro - Lista Cheia");
		}
	}

  
	public Personagem removerInicio() throws Exception{
		Personagem person;

		if (this.fim < 1){
			throw new Exception("Erro - Lista Vazia");
		}
		else{
			person = this.list[0];
			int i=1;
			while (i < this.fim){
				this.list[i-1] = this.list[i];
				i++;
			}
			this.fim--;
		}
		return person;
	}

	
	public Personagem removerFim() throws Exception{
		Personagem person;

		if (this.fim < 1){
			throw new Exception("Erro - Lista Vazia");
		}
		else{
			person = this.list[this.fim - 1];
			this.fim--;
		}
		return person;
	}
	
	
	public Personagem remover(int pos) throws Exception{
		Personagem person;
		
		if (this.fim < 1){
			throw new Exception("Erro - Lista Vazia");
		}
		else if (this.fim <= pos){
			throw new Exception("Erro - Posicao Vazia");
		}
		else{
			person = this.list[pos];
			while (pos+1 < this.fim){
				this.list[pos] = this.list[pos+1];
				pos++;
			}
			this.fim--;
		}
		return person;
	}

 
	public void mostrar(){
		for (int i=0; i<this.fim; i++){
			MyIO.println("["+i+"] "+ this.list[i].ler());
		}
	}

	
	public void mostrar(char c){
		for (int i=0; i<this.fim; i++){
			MyIO.println(this.list[i].ler());
		}
	}
 
	public void comandos(String s) throws Exception{
		String[] parsed = s.split(" ");

		if (parsed[0].equals("II")){
			this.inserirInicio(new Personagem(parsed[1]));
		}
		else if (parsed[0].equals("IF")){
			this.inserirFim(new Personagem(parsed[1]));
		}
		else if (parsed[0].equals("I*")){
			this.inserir(new Personagem(parsed[2]), Integer.parseInt(parsed[1]));
		}
		else if (parsed[0].equals("RI")){
			Personagem p = this.removerInicio();
			MyIO.println("(R) "+p.getNome());
		}
		else if (parsed[0].equals("RF")){
			Personagem p = this.removerFim();
			MyIO.println("(R) "+p.getNome());
		}		
		else if (parsed[0].equals("R*")){
			Personagem p = this.remover(Integer.parseInt(parsed[1]));
			MyIO.println("(R) "+p.getNome());

		}
	}
 
	public void zerarPeso(){
		for (int i=0; i<this.fim; i++){
			this.list[i].setPeso(0.0);
		}
	}
 
 
	public void swap(int i, int j){
		Personagem buffer = this.list[i];
		this.list[i] = this.list[j];
		this.list[j] = buffer;
	}

 
	public void ordenarHeapSort(int[] log){

		
	  //criar heap
	  for (int i=1; i<this.fim; i++){
	  	int j = i+1;

		log[0]++;
	    	while (j>1 && (this.list[j-1].compareTo(this.list[(j/2)-1], 1)>0)){
		  	
			log[1]+=3;
			swap(j-1,(j/2)-1);
		  	j=j/2;

			log[0]++;
	    	}
	  }
	
	  
	  for (int i=this.fim-1; i>0; i--){
		
		log[1]+=3;
		swap(0, i);
		
		
		int j=1;
		while ((j*2)-1 < i){
		   
		   log[0]++;
		   if ((j*2 < i) && (this.list[j*2].compareTo(this.list[(j*2)-1], 1)>0)){
			
			log[0]++;
			if (this.list[j*2].compareTo(this.list[j-1],1)>0){
				
				log[1]+=3;
				swap(j-1, j*2);
				j=(j*2)+1;
			}
			else{
				j=i;
			}	
		   }
		   else {
			
			log[0]++;
			if (this.list[(j*2)-1].compareTo(this.list[j-1],1)>0){
				
				log[1]+=3;
				swap(j-1, (j*2)-1);
				j=(j*2);
		   	}
			else{
				j=i;
			}
		   }
		
		}
	  }
	}
}

class Personagem {

	private String nome;
	private int altura;
	private double peso;
	private String corDoCabelo;
	private String corDaPele;
	private String corDosOlhos;
	private String anoNascimento;
	private String genero;
	private String homeworld;

	Personagem(String nome, int altura, double peso, String corDoCabelo,
		   String corDaPele, String corDosOlhos, String anoNascimento,
		   String genero, String homeworld){

		this.nome = nome;
		this.altura = altura;
		this.peso = peso;
		this.corDoCabelo = corDoCabelo;
		this.corDaPele = corDaPele;
		this.corDosOlhos = corDosOlhos;
		this.anoNascimento = anoNascimento;
		this.genero = genero;
		this.homeworld = homeworld;
	}

	public int compareTo(Personagem personagem, int i) {
        return 0;
    }

    Personagem(String endereco){
		Arq.openRead(endereco);
		String lida = Arq.readLine();
		Arq.close();
		
		String[] dados = parsePersonagem(lida);
	
		this.nome = dados[0];
		try{
			this.altura = Integer.parseInt(dados[1]);
		}
		catch(Exception e){
			this.altura = 0;
		}
		try{
			dados[2]=dados[2].replaceAll(",", "");
			this.peso = Double.parseDouble(dados[2]);
		}
		catch(Exception e){
			this.peso = 0;
		}
		this.corDoCabelo = dados[3];
		this.corDaPele = dados[4];
		this.corDosOlhos = dados[5];
		this.anoNascimento = dados[6];
		this.genero = dados[7];
		this.homeworld = dados[8];		
	}


	public String getNome(){
		return(this.nome);
	}

	
	public int getAltura(){
		return(this.altura);
	}

	
	public double getPeso(){
		return(this.peso);
	}
	
	
	public String getCorDoCabelo(){
		return(this.corDoCabelo);
	}

	
	public String getCorDaPele(){
		return(this.corDaPele);
	}

	
	public String getCorDosOlhos(){
		return(this.corDosOlhos);
	}

	
	public String getAnoNascimento(){
		return(this.anoNascimento);
	}

	
	public String getGenero(){
		return(this.genero);
	}

	
	public String getHomeworld(){
		return(this.homeworld);
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}	

	
	public void setAlgura(int altura){
		this.altura = altura;
	}

	public void setPeso(double peso){
		this.peso = peso;
	}

	
	public void setCorDoCabelo (String corDoCabelo){
		this.corDoCabelo = corDoCabelo;
	}

	
	public void setCorDaPelo(String corDaPelo){
		this.corDaPele = corDaPele;
	}

	
	public void setCorDosOlhos(String corDosOlhos){
		this.corDosOlhos = corDosOlhos;
	}

	public void setAnoNascimento(String anoNascimento){
		this.anoNascimento = anoNascimento;
	}

	
	public void setGenero(String genero){
		this.genero = genero;
	}

	
	public void setHomeworld(String homeworld){
		this.homeworld = homeworld;
	}


	public Personagem clone(Personagem a){
		return (new Personagem(a.nome, a.altura, a.peso, a.corDoCabelo,
			a.corDaPele, a.corDosOlhos, a.anoNascimento, a.genero,
			a.homeworld));
	}


	public void imprimir(){
		MyIO.print(" ## "+this.nome);
		MyIO.print(" ## "+this.altura);
		if (this.peso%1==0)
			MyIO.print(" ## "+(int)this.peso);
		else
			MyIO.print(" ## "+this.peso);
		MyIO.print(" ## "+this.corDoCabelo);
		MyIO.print(" ## "+this.corDaPele);
		MyIO.print(" ## "+this.corDosOlhos);
		MyIO.print(" ## "+this.anoNascimento);
		MyIO.print(" ## "+this.genero);
		MyIO.print(" ## "+this.homeworld);
		MyIO.println(" ## ");

	}


	public String ler(){
		String s = " ## "+this.nome+" ## "+this.altura+" ## ";

		if (this.peso%1==0)
			s = s + (int)this.peso;
		else
			s = s + this.peso;
		s = s + " ## "+this.corDoCabelo+" ## "+this.corDaPele+" ## "+ 
		        this.corDosOlhos+" ## "+this.anoNascimento+" ## "+this.genero+
		        " ## "+this.homeworld+" ## ";	
		return s;
		}

	public static boolean isFim(String s){
		return (s.equals("FIM"));
	}

 
	public static String toIso(String s) throws Exception{
		return (new String(s.getBytes("UTF-8"), "ISO-8859-1"));
	}

 
	public static String toUtf(String s) throws Exception{
		return(new String(s.getBytes("ISO-8859-1"), "UTF-8"));
	}

 
	private static String[] parsePersonagem(String s){
		String[] dados = new String[9];

		int init = 0;
		int end = -1;
		
		for (int i=0; i<9; i++){
			init = s.indexOf("\'", end+1)+1;
			end = s.indexOf("\'", init);
			init = s.indexOf("\'", end+1)+1;
			end = s.indexOf("\'", init);
			dados[i] = s.substring(init, end);
		}
		return (dados);
	}
}

