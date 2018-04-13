public class RadixSort {
private static void radixSort(int[] array,int d)
{
    int n=1;
    int k=0;
    int length=array.length;
    int[][] bucket=new int[10][length];
    int[] order=new int[length];
    while(n<d)
    {
        for(int num:array) 
        {
            int digit=(num/n)%10;
            bucket[digit][order[digit]]=num;
            order[digit]++;
        }
        for(int i=0;i<length;i++)
        {
            if(order[i]!=0)
            {
                for(int j=0;j<order[i];j++)
                {
                    array[k]=bucket[i][j];
                    k++;
                }
            }
            order[i]=0;
        }
        n*=10;
        k=0;
    }
    
}
public static void main(String[] args)
{
    int[] A=new int[]{73,22, 93, 43, 55, 14, 28, 65, 39, 81};
    radixSort(A, 100);
    for(int num:A)
    {
        System.out.println(num);
    }
}
}
