import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class TP02Q13 {
    public static void main(String[] args) throws Exception {
        Personagem pers;
        Lista l1=new Lista();
        int contPers=0;
        String enderecoArq = MyIO.readLine();
        enderecoArq = Personagem.toUtf(enderecoArq);
        while (!(enderecoArq.equals("FIM"))) {
            //enderecoArq= "./personagens/"+ enderecoArq;
            // enderecoArq = ".\\personagens\\" + enderecoArq; // endereco ou nome de personagem
            // System.out.println(enderecoArq);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                pers = new Personagem(linhaPersonagem);
                l1.InserirFim(pers);
                contPers++;
            } catch (IOException e) {
                Personagem lixo = new Personagem();
                l1.InserirFim(lixo);
            }
            enderecoArq = MyIO.readLine();
            enderecoArq = Personagem.toUtf(enderecoArq);
        }
        l1.n=contPers;
        l1.heapSort();
        l1.ordenaAlf();
        l1.imprimir();
    }
}

class Lista{
    public int n; // qnt elem. lista
    public  Personagem[] p;
    public int tamanho; // tam. da lista

    public Lista(){
        this.tamanho=500;
        p = new Personagem[tamanho];
        n=0;
    }

    public void Inicializar(){
        for(int i=0;i<n;i++){
            p[i]=new Personagem();
        }
    }

    public void inserirInicio(Personagem p1) throws Exception{        
        if(n>=this.tamanho){
            throw new Exception("ERRO! - Lista Cheia");
        }
        //leva elementos para o n
        for(int i=n;i>0;i--){
            p[i]= p[i-1];
        }
        p[0]=p1;
        n++;
    }

    public void InserirFim(Personagem p1) throws Exception{
        if(n>=this.tamanho){
            throw new Exception("ERRO! - Lista Cheia");
        }
        p[n+1]=p1;
        n++;
    }

    public boolean PesquisaSequencial(String str){
            for (int i = 0; i < n; i++) {
                if (p[i].nome.equals(str)) {
                    return true; // retorna o índice do valor encontrado
                }
            }
            return false; // retorna -1 se o valor não foi encontrado
        }

    public void ordenaAlf(){
        for(int i=1;i<n;i++){
            for(int j=1+1;j<n-1;j++){
                if(p[i].altura==p[j].altura){
                    if(p[i].nome.compareTo(p[j].nome)>0){
                        swap(i, j);
                    }
                }
            }
        }
    }

    public void heapSort(){
        //Construição do heap
        for(int tam=2;tam<=n;tam++){
            construir(tam);
        }
        //ordenação propriamente dita
        int tam=n;
        while (tam>1){
            swap(1,tam--);
            reconstruir(tam);
        }
    }

    public void construir(int tam){
        for(int i=tam;i>1 && p[i].altura>p[i/2].altura;i/=2){
            swap(i, i/2);
        }
    }

    public void reconstruir(int tam){
        int i=1;
        while(hasFilho(i,tam)==true){
            int filho= getMaiorFilho(i,tam);
            if(p[i].altura < p[filho].altura){
                swap(i, filho);
                i= filho;
            }
            else{
                i=tam;
            }
        }
    }

    public boolean hasFilho(int i, int tam){
        return (i<=(tam/2));
    }

    public int getMaiorFilho(int i, int tam){
        int filho;
        if(2*i==tam || p[2*1].altura > p[2*i+1].altura){
            filho=2*i;
        }
        else{
            filho = 2*i+1;
        }
        return filho;
    }

    public void selectionSort(){
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(p[i].nome.compareTo(p[j].nome)>0){
                    swap(i, j);
                }
            }
        }
    }

    public void insertionSort() {  
        for (int i = 1+1; i < n; i++) {  
            Personagem aux = p[i];  
            int j = i;  
            while ( (j >=0 ) && ( p[j].AnoNascimento.compareTo(aux.AnoNascimento) > 0 ) ) {  
                p[j+1] = p[j];  
                j--;  
            }  
            p[j+1] = aux;  
        }  
}

    public void swap(int i, int j){
        Personagem temp=p[i];
            p[i]=p[j];
            p[j]=temp;
    }

    public void imprimir(){
        for(int i=1;i<=n;i++){
		MyIO.print(" ## "+p[i].nome);
		MyIO.print(" ## "+p[i].altura);
		if (p[i].peso%1==0)
			MyIO.print(" ## "+(int)p[i].peso);
		else
			MyIO.print(" ## "+p[i].peso);
		MyIO.print(" ## "+p[i].CorDoCabelo);
		MyIO.print(" ## "+p[i].CorDaPele);
		MyIO.print(" ## "+p[i].CorDosOlhos);
		MyIO.print(" ## "+p[i].AnoNascimento);
		MyIO.print(" ## "+p[i].genero);
		MyIO.print(" ## "+p[i].homeworld);
		MyIO.println(" ## ");
        }

	}

}

class Personagem {
    public String nome;
    public int altura;
    public double peso;
    public String CorDoCabelo;
    public String CorDaPele;
    public String CorDosOlhos;
    public String AnoNascimento;
    public String genero;
    public String homeworld;

    public Personagem(){
        nome="name";
        altura=0;
        peso=0.0;
        CorDoCabelo="unknown";
        CorDaPele="unknown";
        CorDosOlhos="unknown";
        AnoNascimento="unknown";
        genero="unknown";
        homeworld="unknown";
    }

    public boolean isNotInt(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean isNotDouble(String str) {
        try {
            Double.parseDouble(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public String formatarPeso(Object obj) {
        String peso = obj.toString();
        int indexOfPoint = peso.toString().indexOf(".") == -1 ? peso.length() : peso.toString().indexOf(".");

        if (peso.substring(indexOfPoint, peso.length()).equals(".0")) {
            return peso.substring(0, indexOfPoint);
        } else {
            return peso;
        }
    }
    public static String toUtf(String s) throws Exception{
		return(new String(s.getBytes("ISO-8859-1"), "UTF-8"));
	}

       public Personagem(String endereco) {
        String[] dados = parsePersonagem(endereco); // trocar variavel de entrada
        setNome(dados[0]);
        this.altura = (isNotInt(dados[1]) ? 0 : Integer.parseInt(dados[1]));
        this.peso = (isNotDouble(dados[2]) ? 0 : Double.parseDouble(dados[2]));
        setCorDoCabelo(dados[3]);
        setCordaPele(dados[4]);
        setCorDosOlhos(dados[5]);
        setAnoNascimento(dados[6]);
        setGenero(dados[7]);
        setHomeWorld(dados[8]);
    }

    String[] parsePersonagem(String str) {

        String[] dados = new String[9];
        int inicio = 0;
        int fim = -1;
        for (int i = 0; i < 9; i++) {
            inicio = str.indexOf("'", fim + 1) + 1;
            fim = str.indexOf("'", inicio);
            dados[i] = str.substring(inicio, fim);
            switch (str.substring(inicio, fim)) {
                case "name":
                case "height":
                case "mass":
                case "hair_color":
                case "skin_color":
                case "eye_color":
                case "birth_year":
                case "gender":
                case "homeworld":
                    i--;
                    break;
            }
        }
        return dados;

    }

    String getNome() {
        return nome;
    }

    int getAltura() {
        return altura;
    }

    double getPeso() {
        return peso;
    }

    String getCorDoCabelo() {
        return CorDoCabelo;
    }

    String getCorDaPele() {
        return CorDaPele;
    }

    String getCorDosOlhos() {
        return CorDosOlhos;
    }

    String getAnoNascimento() {
        return AnoNascimento;
    }

    String getGenero() {
        return genero;
    }

    String getHomeWorld() {
        return homeworld;
    }

    void setNome(String nome) {
        this.nome = nome;
    }

    void setAltura(int altura) {
        this.altura = altura;
    }

    void setPeso(double peso) {
        this.peso = peso;
    }

    void setCorDoCabelo(String CorDoCabelo) {
        this.CorDoCabelo = CorDoCabelo;
    }

    void setCordaPele(String CorDaPele) {
        this.CorDaPele = CorDaPele;
    }

    void setCorDosOlhos(String CorDosOlhos) {
        this.CorDosOlhos = CorDosOlhos;
    }

    void setAnoNascimento(String AnoNascimento) {
        this.AnoNascimento = AnoNascimento;
    }

    void setGenero(String genero) {
        this.genero = genero;
    }

    void setHomeWorld(String homeworld) {
        this.homeworld = homeworld;
    }

}
