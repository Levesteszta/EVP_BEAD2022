package com.levesteszta.towerdefend;

import java.util.*;

public class Map {
    int[][] map2D = null;
    boolean wasSetup = false;
    int widthRange = -1, heightRange = -1;
    int startIndex = -1, endIndex = -1;
    Random rand = new Random();

    private void setup(int get_YRange, int get_XRange){
        try{
            widthRange = (int)Math.floor(get_XRange/32.0);      //Hány 32x32 es kockát tudunk az X (szélesség) számegyenesre rakni 
            heightRange = (int)Math.floor(get_YRange/32.0);     //Hány 32x32 es kockát tudunk az Y (magasság) száemgyenesre rakni 
            
            map2D = new int[heightRange][widthRange];
            for(int i = 0; i < map2D.length;i++)
                for(int y = 0; y < map2D[i].length;y++)
                    map2D[i][y] = 0;
            wasSetup = true;
        }catch(Exception e){wasSetup=false; return;}
    }

    private void GenerateIndexes(){
        startIndex  = (int)(rand.nextInt(((heightRange-2) - 1) + 1) + 1);   //Hanyas sorindexből indul -> rand.nextInt((max - min) + 1) + min;
        endIndex    = (int)(rand.nextInt(((heightRange-2) - 1) + 1) + 1);   //Hanyas sorindexre végződik
        while(this.startIndex == this.endIndex || this.startIndex == this.endIndex-1 || this.startIndex == this.endIndex+1)
            endIndex = (int)(rand.nextInt(((heightRange-2) - 1) + 1) + 1); 
    };

    public int[][] generate(int get_YRange, int get_XRange){
        setup(get_YRange, get_XRange);
        if(!wasSetup)
            return null;
        GenerateIndexes();
        System.out.println("Start: "+map2D.length+" : "+startIndex+" , End: "+map2D[0].length+" : "+endIndex);
        int w = widthRange;                         //szélesség hátra                       --(Hányszor mehetek jobbra)
        int h = endIndex - startIndex;              //magasság (+ lefele, - felfele) hátra  --(Hányszor mehetek fel/le)
        int minPath = (int)(w + Math.abs(h))-1;     //Mennyi lesz a minimális út -> adott út adott számú jobbra és fel/le daraból áll, ennek elosztása mindegy ilyenkor

        LinkedList<String> utvonal = new LinkedList<String>();

        //Indulópont meg Végpont
        map2D[startIndex][0] = 1;
        map2D[endIndex][map2D[0].length-1] = 1;
        w-=4;               // az első és az utolsó lépést leveszem 
        minPath-=4;         // Az előző indokból ki kell venni az útvonal további generálásából

        while(minPath >= 0){
            while(w != 0){
                utvonal.add("X"+1);
                minPath--;
                w--;
            }
            while(h != 0){ 
                if(h > 0){
                    utvonal.add("Y"+1);
                    h--;
                }
                else{
                    utvonal.add("Y"+(-1));
                    h++;
                }
                minPath--;
            }
        }
        Collections.shuffle(utvonal, new Random());
       
        int currentX = 1; int currentY = startIndex;
        map2D[startIndex][currentX] = 1;
        map2D[endIndex][map2D[0].length-2] = 1;

        int x = utvonal.size();
        for(int i = 0; i < x; i++){
            String tmp = utvonal.get(i);
            int merre = tmp.toCharArray()[0] == 'Y' ? 1 : 0;
            int index = Integer.parseInt((tmp.substring(1)).toString());

            if(merre == 0){         // X mentén
                while(index-- != 0){
                    currentX++;
                    map2D[currentY][currentX] = 1;
                }
            }
            else if(merre == 1){                            // Y mentén
                while(index != 0){
                    if(index < 0){                          // Felfele
                        currentY--;
                        map2D[currentY][currentX] = 1;
                        index++;
                    }
                    else if( index > 0){                    // Lefele
                        currentY++;
                        map2D[currentY][currentX] = 1;
                        index--;
                    }
                }
            }
        }
        writeOut();
        return map2D;
    }

    public int[][] getMap(){
        return map2D;
    }

    public int getStart(){
        return startIndex;
    }

    protected void writeOut(){
        for(int[] x : map2D){
            for(int y : x)
                System.out.print(y+" ");
            System.out.print("\r\n");
        }
    }
}
