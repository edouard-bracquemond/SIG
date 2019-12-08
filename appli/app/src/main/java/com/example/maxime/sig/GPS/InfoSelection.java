package com.example.maxime.sig.GPS;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.webkit.JavascriptInterface;

import com.example.maxime.sig.Activity.SignUpActivity;
import com.example.maxime.sig.Activity.TreeActivity;
import com.example.maxime.sig.Model.Tree;

public class InfoSelection {
    Context mContext;
    public InfoSelection(Context c)
    {
        mContext = c;
    }

    @JavascriptInterface
    public void  goToTreeActivity(String html){
        int id = 1;
        Intent treeI = new Intent(mContext, TreeActivity.class);
        treeI.putExtra("id",id);
        mContext.startActivity(treeI);


    }
    @JavascriptInterface
    public void goToBenchActivity(String html){
        int id=0;
       // Intent benchI = new Intent(mContext,)
    }

}
