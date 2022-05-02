package com.dayamina.common.utility;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Utility Class to Generate Test Data on Runtime.
 * @author dayamina
 */

public class DataGenerator{

	public DataGenerator(){
	}

    public static Random rp = new Random();

    public static String generateNumber(int len){
    	int i;
    	if (len == 0){
    		return "0";
    		}
    	StringBuilder numString = new StringBuilder();
    	for (i = 0; i < len; i++){
    		numString.append((int)(getRandomNumber()));
    		}
    	return numString.toString();
       }

    public static int getRandomNumber(){
    	return rp.nextInt();
    }

    public static String jobKeywords(){
        List<String> jobTitles = Arrays.asList("QA Engineer", "Software Developer", "DevOps Engineer");
        return jobTitles.get(new Random().nextInt(jobTitles.size()));
    }
}

