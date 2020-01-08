package com.example.maxime.sig.gps;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.example.maxime.sig.activity.ReportActivity;
import com.example.maxime.sig.activity.SignalerActivity;
import com.example.maxime.sig.activity.TreeActivity;
import com.example.maxime.sig.activity.TreePicturesActivity;

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
    public void  goToTreePicturesActivity(String html){
        //Dans le param html seul l'id de l'arbre est renvoyé
        int id =  Integer.parseInt(html);
        Intent treeI = new Intent(mContext, TreePicturesActivity.class);
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

    @JavascriptInterface
    public void gotoReportActivity(String eq, String idEq){
        int id =  Integer.parseInt(idEq);
        Intent intent = new Intent(mContext, ReportActivity.class);
        intent.putExtra("eq", eq);
        intent.putExtra("id", id);
        if (id!=0){
            mContext.startActivity(intent);
        }
    }

    @JavascriptInterface
    public void  goToSignalerActivity(String eq ,String idEq){

        int id =  Integer.parseInt(idEq);
        Intent intent = new Intent(mContext, SignalerActivity.class);
        intent.putExtra("eq", eq);
        intent.putExtra("id", id);
        if (id!=0){
            mContext.startActivity(intent);
        }
    }

}
