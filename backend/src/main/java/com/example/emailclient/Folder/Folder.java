package com.example.emailclient.Folder;

import java.io.File;
import java.io.IOException;

public class Folder implements IFolder {
    protected String name;
    public boolean setName(String n){
        this.name=n;
        return false;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public boolean create(String name, String email) {
        try{
            String path ="src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+name;
            File f = new File(path);
            if(!f.exists()){
                new File(path).mkdir();
                new File(path+"/index.txt").createNewFile();
                return true;
            }
            else{
                throw new RuntimeException("this name is already exisited");
            }
        } catch (IOException e) {
            System.out.println("error , folder wasn't created");
            return false;
        }
    }

    @Override
    public boolean delete(String name, String email) throws Exception {
        BannedFolders x=new BannedFolders();
        x.IsValid(name);
        File sourceFile = new File("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+name);
        if(!sourceFile.exists()) {
            System.out.println("Couldn't find folder with such name");
        }

            if (deleteDirectory(sourceFile)) {
                System.out.println("Folder deleted successfully");
                return true;
            }
            else {
                System.out.println("Failed to delete Folder");
            }

        return false;
    }
    boolean deleteDirectory(File file){
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectory(entry);
                }
            }
        }
        if (!file.delete()) {
            return false;
        }
        else {return true;}
    }

    @Override
    public boolean rename(String newname, String old, String email) throws Exception {
        BannedFolders x=new BannedFolders();
        x.IsValid(name);
        File sourceFile = new File("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+old);
        if(!sourceFile.exists()) {
            System.out.println("Couldn't find folder with such name");
            return false;
        }
        File destFile = new File("src\\main\\java\\com\\example\\emailclient\\App\\"+email+"\\"+newname);
        if (sourceFile.renameTo(destFile)) {
            System.out.println("Folder renamed successfully");
            return true;
        }
        else {
            System.out.println("Failed to rename Folder");
            return false;
        }
    }

    @Override
    public String[] getNames(String email) {
            File x= new File("src\\main\\java\\com\\example\\emailclient\\App\\"+email);
            String[]temp=x.list();
            String[]res=new String[temp.length-2];
            for(int i=0,j=0;i<temp.length;i++,j++){
                if(temp[i].compareTo("attachment")==0||temp[i].compareTo("info.json")==0){
                    j--;
                    continue;
                }
                res[j]=temp[i];
        }
        return res;
    }
}
