package MyProjectOs;
import java.util.*;

public class Directory {
    private String name;
    
    public Directory(String name) {
        this.name = name;
        
    }
    
    public String getName() {
        return name;
    }
    public void print(){
        System.out.println("Directory:" + name);
    }
    }
    