import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class TP04Q02{
    public static void main(String[] args) throws Exception {
        Personagem pers;
        ArvoreArvore a1=new ArvoreArvore();
        String enderecoArq = MyIO.readLine();
        a1.inserir(7);a1.inserir(3);a1.inserir(11);a1.inserir(1);a1.inserir(5);a1.inserir(9);a1.inserir(12);
        a1.inserir(0); a1.inserir(2); a1.inserir(4); a1.inserir(6); a1.inserir(8); a1.inserir(10); a1.inserir(13); a1.inserir(14); a1.inserir(15);
        while (!(enderecoArq.equals("FIM"))) {
            enderecoArq = "./personagens/" + enderecoArq;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                pers = new Personagem(linhaPersonagem);
                a1.inserir(pers);
            } catch (IOException e) {
                Personagem lixo = new Personagem();
                a1.inserir(lixo);
            }
            enderecoArq = MyIO.readLine();
        }
        String nomePersonagem= MyIO.readLine();
        while(!(nomePersonagem.equals("FIM"))){
            MyIO.print(nomePersonagem + " ");
            a1.mostrar();
            enderecoArq = nomePersonagem.replaceAll(" ", "");
            enderecoArq = enderecoArq +".txt";
            enderecoArq = "./personagens/" + enderecoArq;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                pers = new Personagem(linhaPersonagem);
            } catch (IOException e) {
                Personagem lixo = new Personagem();
                pers=lixo;
            }
            if(a1.pesquisar(pers))    MyIO.println("SIM");
            else    MyIO.println("NAO");
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
class No2 {
	public Personagem elemento; // Conteudo do no.
	public No2 esq; // No da esquerda.
	public No2 dir; // No da direita.
	
   /**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 */
	No2(Personagem elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq No2 da esquerda.
	 * @param dir No2 da direita.
	 */
	No2(Personagem elemento, No2 esq, No2 dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}

   public No2 inserir(Personagem s, No2 i) throws Exception {
		if (i == null) {
         i = new No2(s);

      } else if (s.nome.compareTo(i.elemento.nome) < 0) {
         i.esq = inserir(s, i.esq);

      } else if (s.nome.compareTo(i.elemento.nome) > 0) {
         i.dir = inserir(s, i.dir);

      } else {
         throw new Exception("Erro ao inserir: elemento existente!");
      }

		return i;
	}

}

class No {
	public int elemento; // Conteudo do no.
	public No esq; // No da esquerda.
	public No dir; // No da direita.
   public No2 outro;
   ArrayList<Personagem>nomes;
	
   /**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 */
	No(int elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
      this.outro = null;
      this.nomes= new ArrayList<>();
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq No da esquerda.
	 * @param dir No da direita.
	 */
	No(int elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
      this.outro = null;
      this.nomes=new ArrayList<>();
	}

}


class ArvoreArvore {
	public No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public ArvoreArvore() {
		raiz = null;
	}

   public void inserir(int x) throws Exception {
		raiz = inserir(x, raiz);
	}

   public No inserir(int x, No i) throws Exception {
		if (i == null) {
         i = new No(x);

      } else if (x<i.elemento) {
         i.esq = inserir(x, i.esq);

      } else if (x>i.elemento) {
         i.dir = inserir(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }
		return i;
	}

   public void inserir(Personagem x) throws Exception {
		raiz = inserir(x, raiz);
	}


	public No inserir(Personagem x, No i) throws Exception {
        int modAltura = (x.altura/15);
		if (i == null) {
         i = new No((x.altura)/15);

      } else if (modAltura<i.elemento) {
         i.esq = inserir(x, i.esq);

      } else if (modAltura>i.elemento) {
         i.dir = inserir(x, i.dir);

      } else {
        if(i.outro==null) i.outro= new No2(x);
        else i.outro.inserir(x, i.outro);
        i.nomes.add(x);
      }
		return i;
	}




   public void mostrar(){
    MyIO.print("raiz ");
      mostrar(raiz);
   }

   public void mostrar(No i){
      if (i != null){
          MyIO.print("esq ");
          mostrar(i.outro);
         mostrar(i.esq);
         MyIO.print("dir ");
         mostrar(i.dir);
      }
   }

   public void mostrar(No2 i){
      if (i != null){
        MyIO.print("ESQ ");
         mostrar(i.esq);
         MyIO.print("DIR ");
         mostrar(i.dir);
      }
   }


	public boolean pesquisar(Personagem elemento) {
		return pesquisar(raiz, elemento);
	}

	public boolean pesquisar(No no, Personagem x) {
        int modAltura=(x.altura/15);
      boolean resp;
		if (no == null) { 
         resp = false;

      } else if (modAltura<no.elemento) {
         resp = pesquisar(no.esq, x); 

      } else if (modAltura>no.elemento) { 
         resp = pesquisar(no.dir, x); 
      
      } else { 
        resp = pesquisarSegundaArvore(no.outro, x.nome); 
      }
      return resp;
	}

	public boolean pesquisarSegundaArvore(No2 no, String x) {
      boolean resp;
		if (no == null) { 
         resp = false;

      } else if (x.compareTo(no.elemento.nome)>0) { 
         resp = pesquisarSegundaArvore(no.esq, x); 

      } else if (x.compareTo(no.elemento.nome)<0) { 
         resp = pesquisarSegundaArvore(no.dir, x); 

      } else { 
         resp = true; 
      }
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