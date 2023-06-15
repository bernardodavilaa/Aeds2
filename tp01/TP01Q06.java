public class TP01Q06{

    public static char[] StringtoArray(String str){
        char[] array= new char[str.length()];
        for(int i=0; i<str.length(); i++){
            array[i]= str.charAt(i);
        }
        return array;
    }

    public static boolean IsVogal(String str){
       char[] vetor= StringtoArray(str);
        for(int i=0; i<str.length(); i++){
            if(!(vetor[i]==97 || vetor[i] == 101 || vetor[i]== 105 || vetor[i]== 111 || vetor[i]==117)){
                return false;
            }
        }
        return true;
    }

    public static boolean IsConsoante(String str){
        char[] vetor= StringtoArray(str);
         for(int i=0; i<str.length(); i++){
             if(!((vetor[i]>=65 && vetor[i]<=90)) || (vetor[i]>=97 && vetor[i]<=122) ){
                 return false;
             }
         }
         return true;
     }

    public static boolean IsNumerico(String str){
        for(int i=0; i<str.length(); i++){
            if(!(str.charAt(i)>=0 && str.charAt(i)<=9)){
                return false;
            }
        }
        return true;
    }

    public static boolean IsReal(String str){
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='.' || str.charAt(i)==','){
                if(str.charAt(i)>=0 && str.charAt(i)<=9)
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args){
        String str="";
        str =MyIO.readLine();
        while(!str.equals("FIM")){
            if(IsVogal(str)){
            MyIO.print("SIM ");
            }
            else {
            MyIO.print("NAO ");}
            
            if(IsConsoante(str)){
            MyIO.print("SIM ");}
            else{
            MyIO.print("NAO ");}

            if(IsNumerico(str)){
            MyIO.print("SIM ");}
            else {
            MyIO.print("NAO ");}

            if(IsReal(str)){
            MyIO.println("SIM");}
            else{
            MyIO.println("NAO");}
            str =MyIO.readLine();
        }
    }
}