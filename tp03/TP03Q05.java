class TP03Q05{
    public static void main(String[]args){
        int casos=MyIO.readInt();
        for(int p=0;p<casos;p++){
            int linha=MyIO.readInt();
            int coluna=MyIO.readInt();
            Matriz m1=new Matriz(linha, coluna);
            Celula aux=m1.inicio,marcador=m1.inicio;
            for(int i=0;i<linha;i++){
                for(int j=0;j<coluna;j++){
                    aux.elemento=MyIO.readInt();
                    if(aux.dir!=null)aux=aux.dir;
                }
                aux=marcador;
                marcador=marcador.inf;
                aux=aux.inf;
            }
            m1.mostrarDiagonalPrincipal(linha);
            m1.mostrarDiagonalSecundaria(linha);
            linha=MyIO.readInt();
            coluna=MyIO.readInt();
            Matriz m2=new Matriz(linha, coluna);
            aux=m2.inicio;marcador=m2.inicio;
            for(int i=0;i<linha;i++){
                for(int j=0;j<coluna;j++){
                    aux.elemento=MyIO.readInt();
                    if(aux.dir!=null)aux=aux.dir;
                }
                aux=marcador;
                marcador=marcador.inf;
                aux=aux.inf;
            }
            m1.soma(m2);
            m1.multiplicacao(m2);        }

    }
}


class Celula {
   public int elemento;
   public Celula inf, sup, esq, dir;

   public Celula(){
      this(0);
   }

   public Celula(int elemento){
      this(elemento, null, null, null, null);
   }

   public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
      this.elemento = elemento;
      this.inf = inf;
      this.sup = sup;
      this.esq = esq;
      this.dir = dir;
   }
}


class Matriz {
   public Celula inicio=new Celula(0, null, null, null, null);
   public int linha, coluna;


   public Matriz (int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;
      Celula aux=inicio,marcador=inicio;
      for(int i=0;i<linha;i++){
        for(int j=0;j<coluna;j++){
            if(i==0){
                if(j==0);
                else{
                aux.dir=new Celula(0, null, null, inicio, null); 
                aux=aux.dir;   
                }      
            }
            else{
                if(j==0){if(aux.sup!=null) aux.sup.inf=aux;}
                else{
                aux.dir=new Celula(0, null, aux.sup.dir, aux,null);
                if(aux.sup!=null) aux.sup.inf=aux;        
                if(aux.dir.sup!=null) aux.dir.sup.inf=aux.dir;
                aux=aux.dir;
            }
            }
        }
        aux=marcador;
        aux.inf= new Celula(0, null, aux, null, null);
        marcador=marcador.inf;
        aux=aux.inf;
      }

      //alocar a matriz com this.linha linhas e this.coluna colunas
   }



   public void soma (Matriz m2) {

      if(this.linha == m2.linha && this.coluna == m2.coluna){
            int sominha=0;Celula aux=inicio,aux2=m2.inicio,marcador=inicio,marcador2=m2.inicio;
            int [][] mat= new int[linha][coluna];
         for(int i=0;i<linha;i++){
        for(int j=0;j<coluna;j++){
            sominha=aux.elemento+ aux2.elemento;
            mat[i][j]=sominha;
            if(aux.dir!=null){
                aux=aux.dir;aux2=aux2.dir;
            }
         }
         aux=marcador;aux2=marcador2;
         marcador=marcador.inf;marcador2=marcador2.inf;
         aux=aux.inf;aux2=aux2.inf;
        }

        imprimirMatriz(mat);
      }

   }

   public void multiplicacao (Matriz m2) {

    if(this.linha == m2.linha && this.coluna == m2.coluna){
       Celula aux=inicio,aux2=m2.inicio,marcador=inicio,marcador2=m2.inicio;
        int [][] mat= new int[linha][coluna];
        int sominha=0;
     for(int i=0;i<linha;i++){
    for(int j=0;j<coluna;j++){
        for(int k=0;k<linha;k++){
            sominha += (aux.elemento * aux2.elemento);
            aux=aux.dir;aux2=aux2.inf;
        }
        mat[i][j]=sominha;
        aux2=marcador2;
        if(marcador2.dir!=null)marcador2=marcador2.dir;
        aux2=aux2.dir;
        aux=marcador;
        sominha=0;
     }
     aux=marcador;
     marcador=marcador.inf;
     aux=aux.inf;
     aux2=m2.inicio;
     marcador2=m2.inicio;
    }

    imprimirMatriz(mat);
  }

}

   public  void imprimirMatriz(int[][] matriz) {
    for (int i = 0; i < matriz.length; i++) {
        for (int j = 0; j < matriz[0].length; j++) {
            MyIO.print(matriz[i][j] + " ");
        }
        MyIO.println("");
    }
}

   public boolean isQuadrada(){
      if (this.linha == this.coluna)
      return true;
      else
      return false;
   }

   public void mostrarDiagonalPrincipal (int linha){
      if(isQuadrada() == true){
        Celula aux=inicio;
        for(int i=0;i<linha;i++){
                MyIO.print(aux.elemento + " ");
                if(aux.inf!=null)aux=aux.inf.dir;
            }
        }
        MyIO.println("");
   }

   public void mostrarDiagonalSecundaria (int linha){
      if(isQuadrada() == true){
        Celula aux=inicio;
        for(int i=0;i<linha && aux.dir!=null;i++,aux=aux.dir);
        for(int i=linha;i>0;i--){
            MyIO.print(aux.elemento + " ");
            if(aux.inf!=null)
                if(aux.inf.esq!=null)aux=aux.inf.esq;
        }
        MyIO.println("");
   }
}
}