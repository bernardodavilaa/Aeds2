import java.io.*;

class TP02Q03 {
    public static void main(String[] args) throws Exception {
        Personagem pers;
        Lista l1=new Lista();
        int contPers=0, quant=0;
        String enderecoArq = MyIO.readLine();
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
                MyIO.println();
            }
            enderecoArq = MyIO.readLine();
        }
        l1.n=contPers;
        quant= MyIO.readInt();
        for(int i=0;i<quant;i++){
            enderecoArq=MyIO.readLine();
            String[] entrada=enderecoArq.split(" ");
            if(entrada[0].equals("RI")){
                //remove inicio
                pers=l1.removerInicio();
                MyIO.println("(R) "+ pers.nome);
            }
            else if(entrada[0].equals("RF")){
                //remove fim
                pers=l1.removerFim();
                MyIO.println("(R) "+ pers.nome);
            }
            else if(entrada[0].equals("II")){
                //insere inicio
                //enderecoArq= "./personagens/" + entrada[1];
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(entrada[1]), "UTF-8"))) {
                    String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                    pers = new Personagem(linhaPersonagem);
                    l1.inserirInicio(pers);
                } catch (IOException e) {
                    pers= new Personagem();
                    l1.inserirInicio(pers);
            }
        }
            else if(entrada[0].equals("IF")){
                //insere fim
                //enderecoArq= "./personagens/" + entrada[1];
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(entrada[1]), "UTF-8"))) {
                    String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                    pers = new Personagem(linhaPersonagem);
                    l1.InserirFim(pers);
                } catch (IOException e) {
                    MyIO.println();
            }
            }
            else{
                int pos=0;
                try{
                    pos=Integer.parseInt(entrada[1]);
                    if(entrada[0].equals("R*")){
                        //remove na pos
                        pers=l1.remover(pos);
                        MyIO.println("(R) "+pers.nome);
                    }
                    else if(entrada[0].equals("I*")){
                        //insere na pos
                       // enderecoArq= "./personagens/" + entrada[1];
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(entrada[2]), "UTF-8"))) {
                            String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                            pers = new Personagem(linhaPersonagem);
                            l1.inserir(pers, pos);
                        } catch (IOException e) {
                            MyIO.println();
                    }
                    }
                }
                catch(Exception e){
                    pos=0;
                }

            }
                
            
        }
    
        for(int i=0; i<l1.n;i++){
            MyIO.println("["+i+"] " + " ## " + l1.p[i].nome + " ## "+ l1.p[i].altura +" ## "+ l1.p[i].peso+ " ## "+l1.p[i].CorDoCabelo+
            " ## "+ l1.p[i].CorDaPele + " ## "+ l1.p[i].CorDosOlhos + " ## "+ l1.p[i].AnoNascimento+ " ## "+ l1.p[i].genero + " ## " +
            l1.p[i].homeworld + " ## ");
        }
        

    }
    

}

class Lista{
    public int n; // qnt elem. lista
    public Personagem[] p;
    public int tamanho; // tam. da lista

    public Lista(){
        this.tamanho=500;
        p = new Personagem[tamanho];
        n=0;
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
        p[n]=p1;
        n++;
    }

    public void inserir(Personagem p1, int pos) throws Exception{
        if(n>=this.tamanho || pos<0 || pos>n ){
            throw new Exception("ERRO! - Lista Cheia");
        }
        for(int i=pos; i<n; i++){
            p[i]=p[i+1];
        }

        p[pos]=p1;
        n++;

    }

    public Personagem removerInicio() throws Exception{
        if(n==0){
            throw new Exception("Erro- Sem elementos para se remover");
        }
        Personagem Lixo = p[0];
        n--;
        for(int i=0; i<n;i++){
            p[i]=p[i+1];
        }
        return Lixo;
    }

    public Personagem removerFim() throws Exception{
        if(n==0){
            throw new Exception("Erro-Sem elementos para se remover");
        }
        Personagem lixo=p[n-1];
        n--;
        return lixo;
    }

    public Personagem remover(int pos) throws Exception{
        if(n==0 || pos<0 || pos>n){
            throw new Exception("Erro");
        }
        Personagem lixo=p[pos];
            n--;

        for(int i=pos;i<n;i++){
            p[i]= p[i+1];
        }
        return lixo;
    }


}

class Personagem {
    public String nome;
    public int altura;
    public int peso;
    public String CorDoCabelo;
    public String CorDaPele;
    public String CorDosOlhos;
    public String AnoNascimento;
    public String genero;
    public String homeworld;

    public Personagem(){
        nome="name";
        altura=0;
        peso=0;
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


       public Personagem(String endereco) {
        String[] dados = parsePersonagem(endereco); // trocar variavel de entrada
        setNome(dados[0]);
        this.altura = (isNotInt(dados[1]) ? 0 : Integer.parseInt(dados[1]));
        try{
            this.peso=Integer.parseInt(dados[2]);
        }
        catch{
            
        }
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

    void setPeso(int peso) {
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
