package jrails;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.lang.reflect.*;

public class Model {
    public static final String FileName = "database.txt";
    private static File file;
    private static BufferedWriter writer;
    private static FileWriter fileWriter;
    private static BufferedReader reader;
    private static FileReader fileReader;
    public  int id = 0;


    static {
        file = new File (FileName);
        if(!file.exists()) {
            try {
                file.createNewFile();
                //System.out.println("new file");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Database initialize fail");
            }
        }
    }
    private static void initWriter(){
        try{
            writer = new BufferedWriter (fileWriter = new FileWriter(file,true));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void initReader(){
        try{
            reader = new BufferedReader(fileReader = new FileReader(file));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String toStrings(){
        Class<?> clazz = this.getClass();
        Field [] fields = clazz.getFields();
        String strings = this.getClass().getSimpleName() +'\t';
        //System.out.println("dfd");
        //System.out.println(this.getClass().getSimpleName());
        for(Field f: fields){
            if(f.isAnnotationPresent(Column.class)){


                if((f.getType() == String.class) || (f.getType() == int.class) ||(f.getType() == boolean.class)) {
                    try {
                        strings += f.getName() + "\t" + f.get(this) + "\t";
                        //System.out.println(strings);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                else {throw new UnsupportedOperationException();}
            }
            //System.out.println(strings);
        }
        return strings;

    }




    public void save() {
        /* this is an intance of the current model */
        if(this.id == 0) {
            String tmpStr;
            try {
                initReader();
                while ((tmpStr = reader.readLine()) != null) {
                    String[] arr = tmpStr.split("\t");
                    //id = Integer.valueOf(arr[0]);
                    //System.out.println(arr[0]);
                    this.id = Math.max(Integer.parseInt(arr[0]),this.id);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    reader.close();
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.id++;

            try {
                initWriter();
                writer.write(this.id + "\t" + this.toStrings());
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            this.destroy();
            try {
                initWriter();
                writer.write(this.id + "\t" + this.toStrings());
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        //throw new UnsupportedOperationException();
    }



    public int id() {
        return this.id;
    }

    public static <T> T find(Class<T> c, int id){
            initReader();
            String tmpStr;
            String classname = c.getSimpleName();
            try {
                initReader();
                while ((tmpStr = reader.readLine()) != null) {
                    String[] arr = tmpStr.split("\t");
                    if (Integer.valueOf(arr[0]) == id && (arr[1]).equals(classname)) {
                        int length = arr.length;
                        Object element = c.newInstance();
                        //element.id = id;
                        Field fld1 = element.getClass().getField("id");
                        fld1.set(element, id);
                        for (int i = 2; i < length; i = i + 2) {
                            Field fld = element.getClass().getDeclaredField(arr[i]);
                            if (fld.getType() == int.class) {
                                fld.set(element, Integer.parseInt(arr[i + 1]));
                            } else if (fld.getType() == boolean.class) {
                                fld.set(element, Boolean.valueOf(arr[i + 1]));
                            } else {
                                fld.set(element, arr[i + 1]);
                            }
                        }
                        return (T) element;
                    }


                }
            } catch (IOException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            } finally {

                try {
                    reader.close();
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        return null;
        //throw new UnsupportedOperationException();
    }

    public static <T> List<T> all(Class<T> c) {
        List<T> alllist = new LinkedList<> ();
        initReader();
        String tmpStr;
        String classname = c.getSimpleName();
        try {
            initReader();
            while ((tmpStr = reader.readLine()) != null ) {
                String [] arr = tmpStr.split("\t");
                if( (arr[1]).equals(classname)){

                    int length = arr.length;
                    Object element = c.newInstance();
                    Field fld1 = element.getClass().getField("id");
                    fld1.set(element,Integer.valueOf(arr[0]));
                    for(int i = 2; i < length; i= i + 2){
                        Field fld = element.getClass().getDeclaredField(arr[i]);
                        if(fld.getType() == int.class)
                        {fld.set(element,Integer.parseInt(arr[i+1]));}
                        else if(fld.getType() == boolean.class)
                        {fld.set(element,Boolean.valueOf(arr[i+1]));  }
                        else{fld.set(element,arr[i+1]);}
                    }
                    alllist.add((T)element);
                }
            }
        }
        catch (IOException | InstantiationException | IllegalAccessException | NoSuchFieldException e){
            e.printStackTrace();
        }
        finally {

            try {
                reader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return alllist;
        //throw new UnsupportedOperationException();
                    //throw new UnsupportedOperationException();
    }

    public void destroy() {
        if(this.id() != 0){
            initReader();
            String tmpStr;
            int linenumber = -1;
            int currentnumber = 0;
            boolean isfind = false;
            try{
                initReader();
                while(((tmpStr = reader.readLine()) != null)&& !isfind ){
                    String [] arr = tmpStr.split("\t");
                    if(Integer.valueOf(arr[0]) == this.id ){
                        linenumber = currentnumber;
                        isfind = true;
                    }
                    currentnumber++;
                }

            }catch (IOException e) {
                e.printStackTrace();
            }finally {

                try {
                    reader.close();
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            List<String> lines = new ArrayList<>();
            RandomAccessFile oldFile = null,newFile = null;
            try {
                oldFile = new RandomAccessFile(file,"r");
                newFile = new RandomAccessFile(file,"rw");
                oldFile.seek(0);
                String tmpStr1;
                while((tmpStr1 = oldFile.readLine()) != null){
                    lines.add(tmpStr1);
                }
                new FileOutputStream(file, false);
                newFile.seek(0);
                lines.remove(linenumber);
                for(int i =0 ; i<lines.size();i++){
                    newFile.writeBytes(lines.get(i));
                    newFile.writeBytes("\n");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    oldFile.close();
                    newFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {throw new UnsupportedOperationException();}
    }

    public static void reset() {
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
