package com.example.emailclient.Folder;

import java.util.ArrayList;

public class BannedFolders implements Access {
    private ArrayList<String> banned=new ArrayList<String>();
    BannedFolders(){
        banned.add("inbox");
        banned.add("sent");
        banned.add("trash");
        banned.add("draft");
        banned.add("attachment");
    }

    @Override
    public boolean IsValid(String name) throws Exception {
        if(banned.contains(name.toLowerCase()))
        {
            throw new Exception("Access Denied");
        }
        return true;
    }
}
