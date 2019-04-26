import java.util.ArrayList;

public class DiceGame
{
    public static void main(String[] args){
        for(int i = 2; i<8; i++)
        {
            for(int j = 2; j<=i; j++)
            {
                for(int k = 1; k<26; k++)
                {
                    System.out.println("number of dice: "+i+", number to add: "+j+", biggest number on dice: "+k+", number possible: "+test(i,j,k));
                }
            }
        }
        //System.out.println(test(3,3,4));

    }
    //numDice is total dice rolled
    //numSum is number of dice to add together
    //bigNum is largest number on a dice
    public static int test(int numDice, int numSum, int bigNum)
    {
        int arrSize = numSum*bigNum;
        int[][] gen = generate(numDice, bigNum);
        return check(gen, numSum, arrSize);
    }
    public static int[][] generate(int numDice, int big)
    {
        int[] possible = new int[numDice];
        ArrayList<int[]> gen = new ArrayList();
        //TODO actually generate, probably recursively
        RecursiveGen(gen, possible, numDice, big, 1, 0);

        int[][] finalGen = new int[gen.size()][numDice];
        for(int i = 0; i<gen.size(); i++)
        {
            finalGen[i]=gen.get(i);
        }
        return finalGen;
    }
    //Dice is which dice we are currently rolling
    public static void RecursiveGen(ArrayList<int[]> gen, int[] arr, int numDice, int numBig, int prev, int Dice)
    {
        if(Dice+1==numDice)
        {
            for(int i = prev; i<=numBig; i++)
            {
                arr[Dice]=i;
                int[] copy = new int[arr.length];
                for(int j = 0; j< arr.length; j++)
                {
                    copy[j]=arr[j];
                }
                gen.add(copy);
            }
        }
        else
        {
            for(int i = prev; i<=numBig; i++)
            {
                arr[Dice]=i;
                RecursiveGen(gen, arr, numDice, numBig, i, Dice+1);
            }
        }
    }
    public static int check(int[][] gen, int num, int arrS)
    {
        int count = 0;
        for (int x = 0; x < gen.length; x++){
            int[] arr = new int[arrS];
            //TODO fill arr with sums, probably recursively

            recursiveCheck(gen[x], num, 0, 0,0, arr);


            boolean safe = true;
            for(int i =0; i< arrS;i++)
            {
                //System.out.println(arr[i]);
                if(arr[i]>1)
                {
                    safe = false;
                    break;
                }
            }
            if(safe){
                count++;
            }
        }
        return count;
    }
    public static void recursiveCheck(int[] gen, int numSum, int level, int place, int currSum, int arr[])
    {
        if(place == gen.length)
            return;
        if(level == (numSum-1))
        {
            for(int i = place; i < gen.length; i++)
            {
                arr[currSum+gen[i]-1]=arr[currSum+gen[i]-1]+1;
            }
        }
        else{
            for(int i = place; i < gen.length; i++)
            {
                recursiveCheck(gen, numSum, level+1, i+1, currSum+gen[i], arr);
            }
        }
    }
}
