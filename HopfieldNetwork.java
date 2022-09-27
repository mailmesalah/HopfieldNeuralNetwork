

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HopfieldNetwork {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<int[]> listOfPattern;
        List<int[]> listOfError;
        List<int[]> listOfOutput;
        int[][] weightMatrix;
        BufferedReader br = null;
        FileReader fr = null;

        
        if(args.length!=2){
            System.out.println("Wrong number of arguments");
            System.exit(0);
        }
        
        //Reading input pattern
        listOfPattern = new ArrayList<>();
        try {

            fr = new FileReader(args[0]);
            br = new BufferedReader(fr);

            String sCurrentLine;            
            br = new BufferedReader(new FileReader(args[0]));

            while ((sCurrentLine = br.readLine()) != null) {
                String ret[]=sCurrentLine.split(" ");
                int iPattern[] = new int[ret.length];
                for (int i = 0; i < ret.length; i++) {
                    iPattern[i]=Integer.parseInt(ret[i]);
                    //System.out.print(iPattern[i]+" ");
                }
                listOfPattern.add(iPattern);
                //System.out.println();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        
        //reading error pattern
        listOfError = new ArrayList<>();
        try {
            fr = new FileReader(args[1]);
            br = new BufferedReader(fr);

            String sCurrentLine;            
            br = new BufferedReader(new FileReader(args[1]));

            while ((sCurrentLine = br.readLine()) != null) {
                String ret[]=sCurrentLine.split(" ");
                int iError[] = new int[ret.length];
                for (int i = 0; i < ret.length; i++) {
                    iError[i]=Integer.parseInt(ret[i]);
                    //System.out.print(iError[i]+" ");
                }
                listOfError.add(iError);
                //System.out.println();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        
        //Learning Input Pattern
        weightMatrix=new int[listOfPattern.get(0).length][listOfPattern.get(0).length];
        for (int[] iPattern : listOfPattern) {
            for (int i = 0; i < weightMatrix.length; i++) {
                for (int j = 0; j < weightMatrix.length; j++) {
                    if(i==j){
                        weightMatrix[i][j]=0;
                    }else{
                        weightMatrix[i][j]+=iPattern[i]*iPattern[j];
                    }
                }
            }
        }
        
        //Print weight
        /*for (int i = 0; i < weightMatrix.length; i++) {
            for (int j = 0; j < weightMatrix.length; j++) {
                System.out.print(weightMatrix[i][j]+"\t");
            }
            System.out.println();
        }*/
        
        //Output Pattern
        listOfOutput = new ArrayList<>();
        for (int[] iError : listOfError) {
            int[] iOutput = new int[weightMatrix.length];
            for (int i = 0; i < weightMatrix.length; i++) {
                int val=0;
                for (int j = 0; j < weightMatrix.length; j++) {                    
                    val+=weightMatrix[i][j]*iError[j];
                }                
                iOutput[i]=val<0?-1:1;
                System.out.print(iOutput[i]+" ");
            }
            System.out.println();
            listOfOutput.add(iOutput);
        }
        
    }

}
