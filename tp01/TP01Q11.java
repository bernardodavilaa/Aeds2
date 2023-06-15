public class TP01Q11 {
    public static boolean Chama(String str){
        for(int i=0; i<str.length()/2; i++){
            if(str.charAt(i) != str.charAt(str.length()-1-i))
                return false;
        }
        return true;
    }
    public static void main(String[] args){
         String entrada= MyIO.readLine();
         while(!entrada.equals("FIM")){
            if(Chama(entrada)== true)
            System.out.println("SIM");
            else
            System.out.println("NAO");
            entrada=MyIO.readLine();
         }
    }
}
