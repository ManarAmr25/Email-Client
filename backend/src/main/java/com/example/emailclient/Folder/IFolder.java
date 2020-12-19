package com.example.emailclient.Folder;

public interface IFolder {
    boolean create(String name,String email);
    boolean delete (String name,String email) throws Exception;
    boolean rename (String newname,String old ,String email) throws Exception;
    String[] getNames(String email);

}
