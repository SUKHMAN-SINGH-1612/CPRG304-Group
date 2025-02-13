import java.util.Scanner;
/**********************************************************
 * Sort an array of numbers using one of (bubble, selection,
 * insertion or quicksort) in desceding order
*********************************************************** */
public class Sorting
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter number of elements =>");
        int n = input.nextInt();
        
        int[] arr = new int[n];
        
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print("Enter number =>");
            int num = input.nextInt();
            arr[i] = num;
        }
        
        input.nextLine();
        
        System.out.print("Choose the sorting algorithm(b, i, s or q) =>");
        String choice = input.nextLine();
        
        if(choice.equals("b"))
        {
            bubble(arr);
            
        }
        else if(choice.equals("i"))
        {
            insertion(arr);
        }
        else if(choice.equals("s"))
        {
            selection(arr);
        }
        else
        {
            quickSort(arr, 0, n - 1);
        }
        
        System.out.print("Sorted array in descending order :");
        for(int i = 0; i < arr.length; i++)
        {
            System.out.format("%d ",arr[i]);
        }
    }
    
    /*****************************************************
     * bubble() - sort an integer array using bubble sort
     * 
     * parameters - int[] arr
     * return type - int[]
    ****************************************************** */
    public static int[] bubble(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr.length - i - 1; j++)
            {
                if(arr[j] < arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        
        return arr;
    }
    
    
    /************************************************************
     * insertion() - sort an integer array using insertion sort
     * 
     * parameters - int[] arr
     * return type - int[]
    ************************************************************* */
    public static int[] insertion(int[] arr)
    {
        for(int i = 1; i < arr.length; i++)
        {
            int current = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] < current)
            {
                arr[j+1] = arr[j];
                j--;
            }
            
            arr[j+1] = current;
        }
        
        return arr;
    }
    
    
    /************************************************************
     * selection() - sort an integer array using selection sort
     * 
     * parameters - int[] arr
     * return type - int[]
    ************************************************************* */
    public static int[] selection(int[] arr)
    {
        for(int i = 0; i < arr.length - 1; i++)
        {
            int max = i;
            for(int j = i + 1; j < arr.length; j++)
            {
                if(arr[max] < arr[j])
                {
                    max = j;
                }
            }
            
            int temp = arr[max];
            arr[max] = arr[i];
            arr[i] = temp;
        }
        
        return arr;
    }
    
    /************************************************************
     * partition() - helper function for quickSort
     * 
     * parameters - int[] arr, int lowerBound, int upperBound
     * return type - int
    ************************************************************* */
    public static int partition(int[] arr, int lowerBound, int upperBound)
    {
        int pivot = arr[lowerBound];
        int start = lowerBound;
        int end = upperBound;
        
        while(start < end)
        {
            while(start < upperBound && arr[start] >= pivot)
            {
                start++;
            }
            
            while(end > lowerBound && arr[end] < pivot)
            {
                end--;
            }
            
            if(start < end)
            {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }
        
        int temp = arr[lowerBound];
        arr[lowerBound] = arr[end];
        arr[end] = temp;

        return end;
    }
    
    
    /************************************************************
     * quickSort() - sort an integer array using quick sort
     * 
     * parameters - int[] arr, int lowerBound, int upperBound
     * return type - int[]
    ************************************************************* */
    public static int[] quickSort(int[] arr, int lowerBound, int upperBound)
    {
        if(lowerBound < upperBound)
        {
            int location = partition(arr, lowerBound, upperBound);
            quickSort(arr, lowerBound, location - 1);
            quickSort(arr, location + 1, upperBound);
        }
        return arr;
    }
}
