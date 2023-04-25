import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//candance raiz
class TP03Q04 {
    public static void main(String[] args) throws Exception {
        Personagem pers,resp;
        ListaDupla l1=new ListaDupla();
        int contPers=0;
        String[] entrada;
        String enderecoArq = MyIO.readLine();
        while (!(enderecoArq.equals("FIM"))) {
            enderecoArq = "./personagens/" + enderecoArq;
            // enderecoArq = ".\\personagens\\" + enderecoArq; // endereco ou nome de personagem
            // System.out.println(enderecoArq);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                pers = new Personagem(linhaPersonagem);
                if(contPers==0)
                l1.inserirFim(pers);
                else
                l1.inserirFim(pers);
                contPers++;
            } catch (IOException e) {
                Personagem lixo = new Personagem();
                l1.inserirFim(lixo);
            }
            enderecoArq = MyIO.readLine();
        }
        int quant=MyIO.readInt();
        for(int i=0;i<quant;i++){
            enderecoArq =MyIO.readLine();
           entrada=enderecoArq.split(" ");
            if(entrada[0].equals("II")){
                enderecoArq = "./personagens/" + entrada[1];
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                    String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                    pers = new Personagem(linhaPersonagem);
                    l1.inserirInicio(pers);
                } catch (IOException e) {
                    Personagem lixo = new Personagem();
                    l1.inserirInicio(lixo);
                }
            }
            else if(entrada[0].equals("IF")){
                enderecoArq = "./personagens/" + entrada[1];
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                    String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                    pers = new Personagem(linhaPersonagem);
                    l1.inserirFim(pers);
                } catch (IOException e) {
                    Personagem lixo = new Personagem();
                     l1.inserirFim(lixo);
                }
            }
            else if(entrada[0].equals("RI")){
                resp=l1.removerInicio();
                MyIO.println("(R) "+ resp.nome);
            }
            else if(entrada[0].equals("RF")){
                resp=l1.removerFim();
                MyIO.println("(R) "+ resp.nome);
            }
            else if(entrada[0].equals("R*")){
                String pos=entrada[1];
                resp=l1.remover(Integer.parseInt(pos));
                MyIO.println("(R) "+ resp.nome);
            }
            else if(entrada[0].equals("I*")){
                enderecoArq = "./personagens/" + entrada[2];
                String pos=entrada[1];
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                    String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                    pers = new Personagem(linhaPersonagem);
                    l1.inserir(pers, Integer.parseInt(pos));
                } catch (IOException e) {
                    Personagem lixo = new Personagem();
                    l1.inserir(lixo, Integer.parseInt(pos));
                }
            }
        }
        l1.imprimir();
    } 

    }


/**
 * Celula Dupla (lista dupla dinamica)
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class CelulaDupla {
	public Personagem elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	/**
	 * Construtor da classe.
	 */
	public CelulaDupla() {
		Personagem elemento=new Personagem();
	}


	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public CelulaDupla(Personagem elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}
/**
 * Lista dupla dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class ListaDupla {
	public CelulaDupla primeiro;
	public CelulaDupla ultimo;


	/**
	 * Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
	 */
	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}


	/**
	 * Insere um elemento na primeira posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(Personagem x) {
		CelulaDupla tmp = new CelulaDupla(x);

      tmp.ant = primeiro;
      tmp.prox = primeiro.prox;
      primeiro.prox = tmp;
      if(primeiro == ultimo){
         ultimo = tmp;
      }else{
         tmp.prox.ant = tmp;
      }
      tmp = null;
	}


	/**
	 * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirFim(Personagem x) {
		ultimo.prox = new CelulaDupla(x);
      ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}


	/**
	 * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Personagem removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

      CelulaDupla tmp = primeiro;
		primeiro = primeiro.prox;
		Personagem resp = primeiro.elemento;
      tmp.prox = primeiro.ant = null;
      tmp = null;
		return resp;
	}


	/**
	 * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Personagem removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 
      Personagem resp = ultimo.elemento;
      ultimo = ultimo.ant;
      ultimo.prox.ant = null;
      ultimo.prox = null;
		return resp;
	}


	/**
    * Insere um elemento em uma posicao especifica considerando que o 
    * primeiro elemento valido esta na posicao 0.
    * @param x int elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
   public void inserir(Personagem x, int pos) throws Exception {

      int tamanho = tamanho();

      if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
      } else if (pos == 0){
         inserirInicio(x);
      } else if (pos == tamanho){
         inserirFim(x);
      } else {
		   // Caminhar ate a posicao anterior a insercao
         CelulaDupla i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         CelulaDupla tmp = new CelulaDupla(x);
         tmp.ant = i;
         tmp.prox = i.prox;
         tmp.ant.prox = tmp.prox.ant = tmp;
         tmp = i = null;
      }
   }


	/**
    * Remove um elemento de uma posicao especifica da lista
    * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Personagem remover(int pos) throws Exception {
      Personagem resp;
      int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

      } else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
      } else if (pos == 0){
         resp = removerInicio();
      } else if (pos == tamanho - 1){
         resp = removerFim();
      } else {
		   // Caminhar ate a posicao anterior a insercao
         CelulaDupla i = primeiro.prox;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         i.ant.prox = i.prox;
         i.prox.ant = i.ant;
         resp = i.elemento;
         i.prox = i.ant = null;
         i = null;
      }

		return resp;
	}


	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ "); // Comeca a mostrar.
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] "); // Termina de mostrar.
	}


	/**
	 * Mostra os elementos da lista de forma invertida 
    * e separados por espacos.
	 */
	public void mostrarInverso() {
		System.out.print("[ ");
		for (CelulaDupla i = ultimo; i != primeiro; i = i.ant){
			System.out.print(i.elemento + " ");
      }
		System.out.println("] "); // Termina de mostrar.
	}


	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	/*public boolean pesquisar(int x) {
		boolean resp = false;
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
         if(i.elemento == x){
            resp = true;
            i = ultimo;
         }
		}
		return resp;
	}*/

	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
   public int tamanho() {
      int tamanho = 0; 
      for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }

   public void imprimir(){
    for(int j=0; primeiro.prox!=null;j++,primeiro=primeiro.prox){
        Personagem p10=primeiro.prox.elemento;
        MyIO.print("["+ j +"]  ");
        MyIO.println("## " + p10.nome + " ## " + p10.altura + " ## " + p10.peso + " ## " + p10.CorDoCabelo +
        " ## " + p10.CorDaPele + " ## " + p10.CorDosOlhos + " ## " + p10.AnoNascimento + " ## " + p10.genero + " ## " + p10.homeworld + " ## ");
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