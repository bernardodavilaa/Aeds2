import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class TP02Q05 {
    public static void main(String[] args) throws Exception {
        Personagem pers, lixo;
        Pilha p1=new Pilha();
        int contPers=0, quant=0;
        String enderecoArq = MyIO.readLine();
        while (!(enderecoArq.equals("FIM"))) {
            //enderecoArq = "./personagens/" + enderecoArq;
            // enderecoArq = ".\\personagens\\" + enderecoArq; // endereco ou nome de personagem
            // System.out.println(enderecoArq);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                pers = new Personagem(linhaPersonagem);
                p1.InserirFim(pers);
                contPers++;
            } catch (IOException e) {
                e.printStackTrace();
            }
            enderecoArq = MyIO.readLine();
        }
        p1.n=contPers;
        quant=MyIO.readInt();
        for(int i=0;i<quant;i++){
        enderecoArq=MyIO.readLine();
        String[] entrada= enderecoArq.split(" ");
        if(entrada[0].equals("I")){
            //enderecoArq = "./personagens/" + entrada[1];
            try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(entrada[1]), "UTF-8"))){
                String linhaPersonagem = br.readLine();
                pers= new Personagem(linhaPersonagem);
                p1.InserirFim(pers);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(enderecoArq.equals("R")){
            lixo= p1.removerFim();
            MyIO.println("(R) "+ lixo.nome);
        }
        }
        p1.imprimir();
    }
}

class Pilha{
    public int tamanho;
    public Personagem[] p;
    public int n;

    public Pilha(){
        this.tamanho=500;
        p= new Personagem[tamanho];
    }


    public void InserirFim(Personagem p1) throws Exception{
        if(n>=this.tamanho){
            throw new Exception("ERRO! - Lista Cheia");
        }
        p[n]=p1;
        n++;
    }

    public Personagem removerFim() throws Exception{
        if(n==0){
            throw new Exception("Erro-Sem elementos para se remover");
        }
        Personagem lixo=p[n-1];
        n--;
        return lixo;
    }

    public void imprimir(){
        for(int i=0;i<n;i++){
        MyIO.print("["+ i+"] ");
        MyIO.println(" ## "+ p[i].nome + " ## " + p[i].altura + " ## " + p[i].peso + " ## " + p[i].CorDoCabelo + " ## " + p[i].CorDaPele + " ## " +
        p[i].CorDosOlhos + " ## " + p[i].AnoNascimento + " ## " +p[i].genero + " ## " +
        p[i].homeworld + " ##");
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

    Personagem(String endereco) {
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

    String getNome(String nome) {
        nome = this.nome;
        return nome;
    }

    int getAltura(int altura) {
        altura = this.altura;
        return altura;
    }

    double getPeso(double peso) {
        peso = this.peso;
        return peso;
    }

    String getCorDoCabelo(String CorDoCabelo) {
        CorDoCabelo = this.CorDoCabelo;
        return CorDoCabelo;
    }

    String getCorDaPele(String CorDaPele) {
        CorDaPele = this.CorDaPele;
        return CorDaPele;
    }

    String getCorDosOlhos(String CorDosOlhos) {
        CorDosOlhos = this.CorDosOlhos;
        return CorDosOlhos;
    }

    String getAnoNascimento(String AnoNascimento) {
        AnoNascimento = this.AnoNascimento;
        return AnoNascimento;
    }

    String getGenero(String genero) {
        genero = this.genero;
        return genero;
    }

    String getHomeWorld(String homeworld) {
        homeworld = this.homeworld;
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
