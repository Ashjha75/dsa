package array;

public class basics {

    public static void main(String[] args) {
        int[] array={9,1,4,11,6,2,7,8,9,5};
//        call customSearch
        int index=customSearch(array,111);
        System.out.println(index);
    }


//    simple custom search
    public  static  int customSearch(int[] array,int searchValue){
        for(int i=0;i<array.length;i++){
            if(array[i]==searchValue){
                return i;
            }

        }
        return  -1;
    }

//    insertion
    public  static int insertion(int[] array,int value){

        

    }
}
