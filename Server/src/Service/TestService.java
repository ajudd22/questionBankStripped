package Service;

import DAO.ItemDAO;
import Model.Item;
import Request.TestRequest;
import Response.TestResponse;

import java.util.*;


public class TestService {


    public TestResponse execute(TestRequest request)
    {

        request.setAssortment(3);
        TestResponse response = new TestResponse();

        double[] ratios = createRatios(request);//returns decimal values based on difficulty, assortment levels

        double[] decCounts = new double[]{ratios[0]*request.getLength(), ratios[1]*request.getLength(), ratios[2]*request.getLength(), ratios[3]*request.getLength(), ratios[4]*request.getLength()};


        int[] rounded = new int[5];
        for (int i = 0; i <decCounts.length; i++)
        {
            rounded[i] = (int)Math.round(decCounts[i]);
        }

        if (sumquestions(rounded) != request.getLength())
        {
            System.out.println(Arrays.toString(decCounts));
            System.out.println("diff:"+ (request.getLength()-sumquestions(rounded)));
            int diff =request.getLength()- sumquestions(rounded);

            rounded[request.getDifficulty()-1]+=diff;
        }
        System.out.println(Arrays.toString(rounded));


        ItemDAO iDao = new ItemDAO();
        ArrayList<Item> test = new ArrayList<>();

        for(int j = 0; j <rounded.length;j++) {
            ArrayList<Item> toAdd = iDao.getRandItems(j+1, rounded[j]);
            if (toAdd!=null) {
                test.addAll(toAdd);
            }
        }
        if (test.size() != request.getLength())
        {
            response.setMessage("Not enough questions in bank to create a test as requested.");
        }
        response.setQuestions(test);

        printTest(test);
        return response;
    }
    private int sumquestions (int[] roundedLength)
    {
        int sum = 0;
        for (Integer question : roundedLength) sum += question;
        return sum;
    }
    private void printTest (ArrayList<Item> test)
    {
        for (Item i: test)
        {
            System.out.println(i.getHead() +"\n"+i.getC()+"\n" + i.getOp1()+"\n"+i.getOp2()+"\n"+i.getOp3()+"\n");
        }
    }

   private double[] createRatios (TestRequest request)//returns decimal values to then be translated to whole number values

    {
        //assortment 1: low assortment - all one type
        //assortment 2: medium assortment - bell curve
        //assortment 3: varied - hit every index
        double[] ratios = new double[5];

        int difficulty = request.getDifficulty();
        int assortment = request.getAssortment();
        int length = request.getLength();
        if (assortment == 1)//all questions in one difficulty level
        {
            ratios[difficulty] = length;
            return ratios;
        }
        else if (assortment== 2)//~bell curve
        {
            if (difficulty == 3)
            {
                ratios[0] = .02;
                ratios[1] = .14;
                ratios[2] = .68;
                ratios[3] = .14;
                ratios[4] = .02;
            }
           else if (difficulty == 2)
            {
                ratios[0] = .14;
                ratios[1] = .7;
                ratios[2] = .14;
                ratios[3] = .02;
                ratios[4] = 0;
            }
            else if (difficulty == 1)
            {
                ratios[0] = .75;
                ratios[1] = .19;
                ratios[2] = .06;
                ratios[3] = 0;
                ratios[4] = 0;
            }
            else if (difficulty == 4)
            {
                ratios[0] = 0;
                ratios[1] = .02;
                ratios[2] = .14;
                ratios[3] = .7;
                ratios[4] = .14;
            }
            if (difficulty == 5)
            {
                ratios[0] = 0;
                ratios[1] = 0;
                ratios[2] = .06;
                ratios[3] = .19;
                ratios[4] = .75;
            }
        }
        else if (assortment ==3)//hit every index if possible
        {
            if (difficulty ==1)
            {
                ratios[0] = .4;
                ratios[1] = .3;
                ratios[2] = .2;
                ratios[3]= .1;
                ratios[4] = 0;
            }
            else if (difficulty ==2)
            {
                ratios[0] = .2;
                ratios[1] = .4;
                ratios[2] = .2;
                ratios[3] = .1;
                ratios[4] = .1;
            }
            else if (difficulty == 3)
            {
                ratios[0] = .1;
                ratios[1] = .2;
                ratios[2] = .4;
                ratios[3] = .2;
                ratios[4] = .1;
            }
            else if (difficulty == 4)
            {
                ratios[0] = .1;
                ratios[1] = .2;
                ratios[2] = .2;
                ratios[3] = .4;
                ratios[4] = .2;
            }
            else if (difficulty ==5)
            {
                ratios[0] = .0;
                ratios[1] = .1;
                ratios[2] = .2;
                ratios[3] = .3;
                ratios[4] = .4;
            }
        }
        return ratios;


    }


}
