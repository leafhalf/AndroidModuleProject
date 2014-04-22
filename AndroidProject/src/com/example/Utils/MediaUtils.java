package com.example.Utils;

import java.util.Random;

public class MediaUtils {
     public static int getRandom(int size){
    	 Random mRandom=new Random();
    	 return Math.abs((mRandom.nextInt()%size));
     }
}
