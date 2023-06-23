public class TP01Q09 {
    public static void main(String[] args){
        int n;
        n = MyIO.readInt();
        String[] array = new String[1000];
        String[] paralela = new String[1000];
        for(int i=0;i<n;i++){
            array[i]=MyIO.readLine();
        }
        for(int i=n-1;i>=0;i--){
            paralela[i]="";
            int x=0;
            int p=0;
            for(int a=0;a<array[i].length();a++){
                if(array[i].charAt(a)==46){
                    p=1;
                }
            }
            for(int a=array[i].length()-1;a>0;a--){
                if((array[i].charAt(a)==48)&&p==1){
                    x++;
                }
                else{
                    a=0;
                }
            }
            for(int a=0;a<array[i].length()-x;a++){
                paralela[i] += array[i].charAt(a);
            }           
            if(array[i].charAt(0)==46){
                MyIO.println("0"+paralela[i]);
            }
            else{
                MyIO.println(paralela[i]);
            }
        }
    }
}
