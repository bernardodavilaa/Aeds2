import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//candance raiz
class TP03Q02 {
    public static void main(String[] args) throws Exception {
        Personagem pers,resp;
        Pilha p1=new Pilha();
        String[] entrada;
        String enderecoArq = MyIO.readLine();
        while (!(enderecoArq.equals("FIM"))) {
            //enderecoArq = "./personagens/" + enderecoArq;
            // enderecoArq = ".\\personagens\\" + enderecoArq; // endereco ou nome de personagem
            // System.out.println(enderecoArq);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                pers = new Personagem(linhaPersonagem);
                p1.inserir(pers);
            } catch (IOException e) {
                Personagem lixo = new Personagem();
                p1.inserir(lixo);
            }
            enderecoArq = MyIO.readLine();
        }
        int quant=MyIO.readInt();
        for(int i=0;i<quant;i++){
            enderecoArq=MyIO.readLine();
            entrada=enderecoArq.split(" ");
            if(entrada[0].equals("I")){
                //enderecoArq = "./personagens/" + entrada[1];
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(entrada[1]), "UTF-8"))) {
                    String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                    pers = new Personagem(linhaPersonagem);
                    p1.inserir(pers);
                } catch (IOException e) {
                    Personagem lixo = new Personagem();
                    p1.inserir(lixo);
                }
            }
            else {
                resp=p1.remover();
                MyIO.println("(R) "+ resp.nome);
            }
        }
        
        p1.imprimir(p1);
    } 

    }


class Celula{
    public Personagem elemento;
    public Celula prox;

    public Celula(){
        Personagem elemento=new Personagem();
    }

    public Celula(Personagem elemento){
        this.elemento=elemento;
        this.prox=null;
    }
}


class Pilha {
	private Celula topo;

	/**
	 * Construtor da classe que cria uma fila sem elementos.
	 */
	public Pilha() {
		topo = null;
	}

	/**
	 * Insere elemento na pilha (politica FILO).
	 * 
	 * @param x int elemento a inserir.
	 */
	public void inserir(Personagem x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
	}

	/**
	 * Remove elemento da pilha (politica FILO).
	 * @param pers
	 * 
	 * @return Elemento removido.
	 * @trhows Exception Se a sequencia nao contiver elementos.
	 */
	public Personagem remover() throws Exception {
		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}
		Personagem resp = topo.elemento;
		Celula tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
		return resp;
	}

	/**
	 * Mostra os elementos separados por espacos, comecando do topo.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for (Celula i = topo; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] ");
	}

	public void mostraPilha() {
		mostraPilha(topo);
	}

	private void mostraPilha(Celula i) {
		if (i != null) {
			mostraPilha(i.prox);
			System.out.println("" + i.elemento);
		}
	}

    public int tamanho() {
        int tamanho = 0; 
        for(Celula i = topo; i != null; i = i.prox, tamanho++);
        return (tamanho);
     }

    public void imprimir(Pilha p1){
        int tamanho= tamanho(),k=-1;
        for(int a=tamanho;a>0;a--){
            Celula i=p1.topo;
            k++;
            for(int j=1;j!=a;i=i.prox,j++);
            Personagem p10=i.elemento;
            MyIO.print("["+ k +"]  ");
            MyIO.println("## " + p10.nome + " ## " + p10.altura + " ## " + p10.peso + " ## " + p10.CorDoCabelo +
            " ## " + p10.CorDaPele + " ## " + p10.CorDosOlhos + " ## " + p10.AnoNascimento + " ## " + p10.genero + " ## " + p10.homeworld + " ##");
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
/*
/tmp/personagens/AdiGallia.txt
/tmp/personagens/ArvelCrynyd.txt
/tmp/personagens/AylaSecura.txt
/tmp/personagens/BB8.txt
/tmp/personagens/BarrissOffee.txt
/tmp/personagens/BeruWhitesunlars.txt
/tmp/personagens/BobaFett.txt
/tmp/personagens/C-3PO.txt
/tmp/personagens/Dooku.txt
/tmp/personagens/Dormé.txt
/tmp/personagens/Gasgano.txt
/tmp/personagens/HanSolo.txt
/tmp/personagens/JangoFett.txt
/tmp/personagens/LamaSu.txt
/tmp/personagens/LandoCalrissian.txt
/tmp/personagens/MaceWindu.txt
/tmp/personagens/MonMothma.txt
/tmp/personagens/OwenLars.txt
/tmp/personagens/PoggletheLesser.txt
/tmp/personagens/QuarshPanaka.txt
/tmp/personagens/R2-D2.txt
/tmp/personagens/RaymusAntilles.txt
/tmp/personagens/RoosTarpals.txt
/tmp/personagens/SaeseeTiin.txt
/tmp/personagens/SanHill.txt
/tmp/personagens/SlyMoore.txt
/tmp/personagens/TaunWe.txt
/tmp/personagens/Watto.txt
/tmp/personagens/WicketSystriWarrick.txt
/tmp/personagens/ZamWesell.txt
 */