package com.example.maxime.sig.GPS;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
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
        //Dans le param html seul l'id de l'arbre est renvoyé
        int id =  Integer.parseInt(html);
        Intent treeI = new Intent(mContext, TreeActivity.class);
        treeI.putExtra("id",id);
        Log.d("L'id est: ", html);
        //Log.d("L'id est: ", infos);
        if (id!=0){
            mContext.startActivity(treeI);
        }

    }

    @JavascriptInterface
    public void goToBenchActivity(String html){
        int id=0;
       // Intent benchI = new Intent(mContext,)
    }

}
