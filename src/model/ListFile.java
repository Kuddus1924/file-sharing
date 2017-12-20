package model;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Куддус on 10.12.2017.
 */
public class ListFile {
    ArrayList<String> list;
    File file;
    public ListFile(String p)
    {
        list = new ArrayList<>();
        file =  new File(p);
        try {
            BufferedReader buf = new BufferedReader(new FileReader(file));
            String str;
            while ((str = buf.readLine()) != null)
            {
                str.trim();
                list.add(str);
            }
        }
        catch (Exception e){}
    }
    public void addFile(String way)
    {
        list.add(way);
        write();
    }
    public void write()
    {
        try {
        BufferedWriter buf = new BufferedWriter(new FileWriter(file));
        for(int i = 0;i < list.size();i++)
        {
            buf.write(list.get(i)+"\n");
        }
        buf.flush();
        buf.close();
    }
        catch (Exception e)
        {}


    }
    public ArrayList<String> getList()
    {
        return list;
    }
    public void delete(String files)
    {
        for(int i = 0;i < list.size();i++)
        {
            if(list.get(i).equals(files)) {
                list.remove(i);
            }
        }
        write();
    }

}
