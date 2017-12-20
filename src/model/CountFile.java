package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Куддус on 16.12.2017.
 */
public class CountFile {
    private HashMap<String,Integer>filebase;
    File file;
    public CountFile(String n)
    {
        filebase = new HashMap<>();
        file = new File(n);
    }
    public void read() {
        String s;
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while ((s = in.readLine()) != null) {
                s.trim();
                String str[] = s.split(" ");
                filebase.put(str[0], Integer.parseInt(str[1]));

            }
        } catch (Exception e) {

        }
    }
    public synchronized void fileAdd(String name)
    {
            int con = filebase.get(name);
            if(con == 0)
            {
                filebase.put(name,1);
                write();
                return;
            }
            con++;
            filebase.remove(name);
            filebase.put(name, con);
            write();

    }
    public synchronized void fileAdds(String name)
    {
        filebase.put(name,0);
        write();
    }
    public synchronized int gets(String name)
    {
        if( !filebase.containsKey(name))
        {
            filebase.put(name,0);
            write();
        }
        return filebase.get(name);
    }
    public synchronized void delete(String name)
    {
        if( filebase.containsKey(name))
        {
            filebase.remove(name);
            write();
        }

    }
    public synchronized void write()
    {

            StringBuilder str = new StringBuilder();
            for (Map.Entry<String, Integer> entry : filebase.entrySet()) {
                str.append(entry.getKey() + " " + entry.getValue() + "\n");
            }
            try{
                BufferedWriter buf = new BufferedWriter(new FileWriter(file));
                buf.write(str.toString());
                buf.flush();
                buf.close();
            }
            catch (Exception e)
            {}
        }
    }
