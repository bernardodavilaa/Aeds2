
import java.util.Date;


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

	public double compareTo (Personagem p, int op){
		double resp = 0.0;
	
		switch (op){	
			case 1:
  			 resp = this.altura - p.altura;
			 break;
			case 2:
			 resp = this.peso - p.peso;
			 break;
			case 3:
			 resp = this.corDoCabelo.compareTo(p.corDoCabelo);
			 break;
			case 4:
			 resp = this.corDaPele.compareTo(p.corDaPele);
			 break;
			case 5:
			 resp = this.corDosOlhos.compareTo(p.corDosOlhos);
			 break;
			case 6:
			 resp = this.anoNascimento.compareTo(p.anoNascimento);
			 break;
			case 7:
			 resp = this.genero.compareTo(p.genero);
			 break;
			case 8:
			 resp = this.homeworld.compareTo(p.homeworld);
			 break;
			default:
				resp = this.nome.compareTo(p.nome);
		}
		
		if (resp==0){
			resp = this.nome.compareTo(p.nome);
		}
		return resp;
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


/**
*Classe Lista
*/
class Lista{
 //atributos
	private int tamanho;
	private int fim;
	private Personagem[] list;

 //construtores
	Lista (int tamanho){
		this.tamanho=tamanho;
		this.fim = 0;
		this.list = new Personagem[tamanho];
	}

	Lista (){
		this(100);
	}

 //metodos
  //inserir
	/**
	*inserirInicio - insere um elemento no inicio da lista
	*@param Personagem elemento
	*/
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

	/**
	*inserirFim - insere um elemento no fim da lista
	*@param Personagem elemento
	*/
	public void inserirFim(Personagem person) throws Exception{
		if (this.fim < this.tamanho){
			this.list[fim] = person;
			this.fim++;
		}
		else{
			throw new Exception("Erro - Lista Cheia");
		}
	}
	
	/**
	*inserir - insere um elemento em determinada posicao
	*@param Personagem elemento, int posicao
	*/
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

  //remover
	/**
	*removerInicio - remove um elemento do inicio da lista
	*@return Personagem elemento
	*/
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

	/**
	*removerFim - remove um elemento do fim da lista
	*@return Personagem elemento
	*/
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
	
	/**
	*remover - remove um elemento da posicao
	*@param int posicao
	*@return Personagem elemento
	*/
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

 //mostrar
	/**
	*mostrar lista
	*/
	public void mostrar(){
		for (int i=0; i<this.fim; i++){
			MyIO.println("["+i+"] "+ this.list[i].ler());
		}
	}

	/**
	*mostrar lista s/ posicao
	*/
	public void mostrar(char c){
		for (int i=0; i<this.fim; i++){
			MyIO.println(this.list[i].ler());
		}
	}
 //comandos
	/**
	*comandos - executa os comandos contidos em uma string
	*@param String com comandos
	*/
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
 //zerarPeso
	/**
	*zerarPeso - zera o peso dos elementos da lista
	*/
	public void zerarPeso(){
		for (int i=0; i<this.fim; i++){
			this.list[i].setPeso(0.0);
		}
	}
 
 //swap
	/**
	*swap - troca dois elemetos da lista de posicao
	*@param int i int j posicoes
	*/
	public void swap(int i, int j){
		Personagem buffer = this.list[i];
		this.list[i] = this.list[j];
		this.list[j] = buffer;
	}

 //ordenar CountingSort
	/**
	*ordernarCountingSort - ordena a lista pelo metodo HeapSort 
	*@param int[] log para n comparacoes e n movimentacoes
	*@return Lista ordenada
	*/
	public Lista ordenarCountingSort(int[] log){
		Lista ordenada = new Lista();
		ordenada.fim = this.fim;

		int maior = (int) (this.list[0].getPeso()*10);		

		for (int i=1; i<this.fim; i++){
			log[0]++;
			if (maior < ( (int) (this.list[i].getPeso()*10))){
				maior = (int) (this.list[i].getPeso()*10);
			}
		} 

		int[] contagem = new int[maior+1];

		for (int i=0; i<this.fim; i++){
			contagem[(int) (this.list[i].getPeso()*10)]++;
		}

		for (int i=1; i<maior+1; i++){
			contagem[i] += contagem[i-1];
		}

		for (int i=this.fim-1; i>=0; i--){
			log[1]++;
			ordenada.list[contagem[((int)(this.list[i].getPeso() * 10))]-1]=this.list[i];
			contagem[(int) (this.list[i].getPeso()*10)]--; 
		}
		return ordenada;
	}
}


/**
*Classe Main
*/
public class TP02Q15{

	/**
	*tempo
	*@return long tempo
	*/
	public static long tempo(){
		return (new Date().getTime());
	}
	
	/**
	*Metodo main
	*/
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
		int[] log = new int[2];
		list = list.ordenarCountingSort(log);
		fim = tempo();			

		double segundos = ((double)(fim-inicio)) / 1000.0;

		//arquivo log
		Arq.openWrite("651230_countingsort.txt");
		Arq.print("651230\t"+log[0]+"\t"+log[1]+"\t"+segundos);
		Arq.close();
	
		list.mostrar('c');
	}	
}
