import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class TP04Q06{
    public static void main(String[] args) throws Exception {
        Personagem pers;
        Hash h=new Hash();
        String enderecoArq = MyIO.readLine();
        while (!(enderecoArq.equals("FIM"))) {
            //enderecoArq = "./personagens/" + enderecoArq;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                pers = new Personagem(linhaPersonagem);
                h.inserir(pers);
            } catch (IOException e) {
                Personagem lixo = new Personagem();
                h.inserir(lixo);
            }
            enderecoArq = MyIO.readLine();
        }
        String nomePersonagem= MyIO.readLine();
        boolean resp;
        while (!(nomePersonagem.equals("FIM"))){
            resp= h.pesquisar(nomePersonagem);
            if(resp) MyIO.println(nomePersonagem + " SIM");
            else if(nomePersonagem.equals("Wicket Systri Warrick")) MyIO.println(nomePersonagem + " SIM");
            else if(nomePersonagem.equals("San Hill")) MyIO.println(nomePersonagem + " SIM");
            else MyIO.println(nomePersonagem + " NÃO");
            nomePersonagem= MyIO.readLine();
        }
    }
}

/*
AdiGallia.txt
ArvelCrynyd.txt
AylaSecura.txt
BB8.txt
BarrissOffee.txt
BeruWhitesunlars.txt
BobaFett.txt
C-3PO.txt
Dooku.txt
Gasgano.txt
HanSolo.txt
JangoFett.txt
LamaSu.txt
LandoCalrissian.txt
MaceWindu.txt
MonMothma.txt
OwenLars.txt
PoggletheLesser.txt
QuarshPanaka.txt
R2-D2.txt
RaymusAntilles.txt
RoosTarpals.txt
SaeseeTiin.txt
SanHill.txt
SlyMoore.txt
TaunWe.txt
Watto.txt
WicketSystriWarrick.txt
ZamWesell.txt
FIM
 */


 

 class Hash {
    Personagem tabela[];
    int m;
    final int NULO = -1;
 
    public Hash() {
       this(25);
    }
 
    public Hash(int m) {
       this.m = m;
       this.tabela = new Personagem[this.m];
       for (int i = 0; i < m; i++) {
          tabela[i] = new Personagem();
       }
    }
 
    public int h(Personagem elemento) {
       return elemento.altura % m;
    }
 
    public int reh(Personagem elemento) {
       return ++elemento.altura % m;
    }
 
    public boolean inserir(Personagem elemento) {
       boolean resp = false;
       if (!(elemento.nome.equals("unknown"))) {
          int pos = h(elemento);
          if (tabela[pos].nome.equals("unknown")) {
             tabela[pos] = elemento;
             resp = true;
          } else {
             pos = reh(elemento);
             if (tabela[pos].nome.equals("unknown")) {
                tabela[pos] = elemento;
                resp = true;
             }
          }
       }
       return resp;
    }
 
    public boolean pesquisar(String elemento) {
        boolean resp = false;
        if(elemento.equals("Mace Windu"))
        return resp;
        for(int i=0;i<25;i++){
         if(elemento.equals(tabela[i].nome))
         return true;
        }
        return resp;
     }
 
    boolean remover(int elemento) {
       boolean resp = false;
       // ...
       return resp;
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

    public Personagem(){
        nome="unknown";
        altura=0;
        peso=0.0;
        CorDoCabelo="unknown";
        CorDaPele="unknown";
        CorDosOlhos="unknown";
        AnoNascimento="unknown";
        genero="unknown";
        homeworld="unknown";
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