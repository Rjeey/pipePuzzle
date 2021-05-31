package com.rjeey.pipepuzzle.data;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScoresTable {

    static List<ScoreRecord> _globalScoresTable;
    final static String FILE_NAME = "PipeDreamScores.dat";

    static {
        _globalScoresTable = null;
    }

    public static void saveNewRecord(String nickname,int score)
    {
        if (_globalScoresTable == null)
        {
            System.out.println("in saveNewRecord, _globalScoresTable is null!");
            return;
        }
        ScoreRecord newRecord = new ScoreRecord(nickname,score,new Date());
        _globalScoresTable.add(newRecord);
    }

    @SuppressLint("NewApi")
    public static List<ScoreRecord> getAllScores()
    {
        _globalScoresTable.sort((scoreRecord, other) -> {
             if (scoreRecord.getScore() == other.getScore()) return 0;
             else if (scoreRecord.getScore() < other.getScore()) return 1;
             else return -1;
        });
        return _globalScoresTable;
    }

    public static void loadFromDevice(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            Object dataFromFile = is.readObject();
            if (dataFromFile != null) {

                    _globalScoresTable = (ArrayList<ScoreRecord>) dataFromFile;
                }
                is.close();
                fis.close();
            }
        catch(FileNotFoundException e){
                System.out.println("in saveToDevice, FileNotFoundException:" + e.getMessage());
            }
        catch(IOException e){
                System.out.println("in saveToDevice, IOException:" + e.getMessage());
            }
        catch(ClassNotFoundException e){
                System.out.println("in saveToDevice, ClassNotFoundException:" + e.getMessage());
            }
        if (_globalScoresTable == null) {
            _globalScoresTable = new ArrayList<>();
        }

    }

    public static void saveToDevice(Context context)
    {
        if (_globalScoresTable == null)
        {
            System.out.println("in saveToDevice, _globalScoresTable is null!");
            return;
        }
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(_globalScoresTable);
            os.close();
            fos.close();
        }
        catch (IOException e) {
            System.out.println("in saveToDevice, IOException:" + e.getMessage());
        }
    }
}
