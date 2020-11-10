package Controller;

import com.oops.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.String;
import java.util.ArrayList;

public class FileMgr {
    public ArrayList<Course> loadCourses("courseFile.dat")
    {
        ArrayList<Course> c = new ArrayList<Course>();
        FileInputStream fi = new FileInputStream(new File("courseFile.dat"));
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Read objects
        try{
            while(true)
            {
                c.add( (Course) oi.readObject());
            }
        catch(EOFException e){
            //do nothing
        }
        }

        oi.close();
        fi.close();
        return c;

    }

    public void saveCourses(ArrayList<Course> c )
    {
        try {
            FileOutputStream f = new FileOutputStream(new File("courseFile.dat",true));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
           
            while(true)
            {
                for (int i = 0; i<c.size(); i++)
                    o.writeObject(c[i]);
            }
           
            }

            o.close();
            f.close();
    }

}
