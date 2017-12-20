package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Base {
    public String file;
    ArrayList<Person> base = new ArrayList<Person>();
    int writeCount;
   public Base (String n){
       file = n;
       String s;
       String []str;
       try {
           BufferedReader in = new BufferedReader(new FileReader(file));
           while ((s = in.readLine()) !=null) {
               s.trim();
               str = s.split(" ");
               base.add(new Person(str[0],str[1]));
           }
           writeCount = base.size();
       }
       catch (Exception e){}
   }
   public boolean check (String name,String password)
   {

       for (int i = 0; i < base.size();i++)
       {
           if ((name.equals(base.get(i).getName())) && (password.equals(base.get(i).getPassword())))
               return true;

       }
       return false;
   }
   public boolean checkName(String name)
   {
       for (int i = 0; i < base.size();i++)
       {
           if (name.equals(base.get(i).getName()))
               return true;

       }
       return false;
   }
   public synchronized void addUser(String name,String password)
   {
       base.add(new Person(name,password));
       writeBase();
   }
   public synchronized void writeBase()
   {
        StringBuilder str = new StringBuilder();
        for(int i = writeCount - 1;i < base.size();i++)
            str.append(base.get(i).getName() +" "+ base.get(i).getPassword()+"\n");
        try{
            BufferedWriter buf = new BufferedWriter(new FileWriter(file,true));
            buf.write(str.toString());
            buf.flush();
            buf.close();
        }
        catch (Exception e)
        {}
   }
   public Person get(String name)
   {
       for (int i = 0; i < base.size();i++)
       {
           if (name.equals(base.get(i).getName()))
               return base.get(i);

       }
       return  null;
   }

}
