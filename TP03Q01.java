import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class TP02Q05 {
    public static void main(String[] args) throws Exception {
        Personagem pers;
        Lista l1=new Lista();
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
                l1.inserirLista(pers);
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
                    l1.inserirFim(lixo);
                }
            }
            else if(entrada[0].equals("IF")){
                enderecoArq = "./personagens/" + entrada[1];
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(enderecoArq), "UTF-8"))) {
                    String linhaPersonagem = br.readLine(); // abre e lê linha do arquivo
                    pers = new Personagem(linhaPersonagem);
                    l1.inserirInicio(pers);
                } catch (IOException e) {
                    Personagem lixo = new Personagem();
                     l1.inserirFim(lixo);
                }
            }
            else if(entrada[0].equals("RI")){
                l1.removerInicio();
            }
            else if(entrada[0].equals("RF")){
                l1.removerFim();
            }
            else if(entrada[0].equals("R*")){
                String pos=entrada[1];
                l1.remover(Integer.parseInt(pos));
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
                    l1.inserirFim(lixo);
                }
            }
        }
        l1.imprimir();
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

class Lista{
    public Personagem[] p;
    public Celula primeiro;
    public Celula ultimo;


    public Lista(){
        primeiro=new Celula();
        ultimo=primeiro;
    }

    public void inserirLista(Personagem p){
        ultimo.prox=new Celula(p);
        ultimo=ultimo.prox;
    }


    public void inserirInicio(Personagem x) {
		Celula tmp = new Celula(x);
      tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {                 
			ultimo = tmp;
		}
      tmp = null;
	}

    public void inserirFim(Personagem p) {
		ultimo.prox = new Celula(p);
		ultimo = ultimo.prox;
	}


    public Personagem removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

      Celula tmp = primeiro;
		primeiro = primeiro.prox;
		Personagem resp = primeiro.elemento;
      tmp.prox = null;
      tmp = null;
		return resp;
	}

    public Personagem removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 

		// Caminhar ate a penultima celula:
      Celula i;
      for(i = primeiro; i.prox != ultimo; i = i.prox);

      Personagem resp = ultimo.elemento; 
      ultimo = i; 
      i = ultimo.prox = null;
      
		return resp;
	} 

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
           Celula i = primeiro;
           for(int j = 0; j < pos; j++, i = i.prox);
          
           Celula tmp = new Celula(x);
           tmp.prox = i.prox;
           i.prox = tmp;
           tmp = i = null;
        }
     }

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
           Celula i = primeiro;
           for(int j = 0; j < pos; j++, i = i.prox);
          
           Celula tmp = i.prox;
           resp = tmp.elemento;
           i.prox = tmp.prox;
           tmp.prox = null;
           i = tmp = null;
        }
  
          return resp;
      }
      public void mostrar() {
		System.out.print("[ ");
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] ");
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(Personagem x) {
		boolean resp = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
         if(i.elemento == x){
            resp = true;
            i = ultimo;
         }
		}
		return resp;
	}

	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
   public int tamanho() {
      int tamanho = 0; 
      for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }

    public void imprimir(){
        int n=tamanho();
        Celula i= primeiro;
        for(int j=0;j<n;j++,i=i.prox){
        MyIO.print("["+ i+"] ");
        MyIO.println(" ## "+ p[j].nome + " ## " + p[j].altura + " ## " + p[j].peso + " ## " + p[j].CorDoCabelo + " ## " + p[j].CorDaPele + " ## " +
        p[j].CorDosOlhos + " ## " + p[j].AnoNascimento + " ## " +p[j].genero + " ## " +
        p[j].homeworld + " ##");
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
        nome="nao tem";
        altura=0;
        peso=0.0;
        CorDoCabelo="nao tem";
        CorDaPele="nao tem";
        CorDosOlhos="nao tem";
        AnoNascimento="13/02/2002";
        genero="pansexual";
        homeworld="ouro branco";
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
