package com.example.maxime.sig.gps;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.maxime.sig.activity.ReportActivity;
import com.example.maxime.sig.activity.SignalerActivity;
import com.example.maxime.sig.activity.SuggestionActivity;
import com.example.maxime.sig.activity.TreeActivity;
import com.example.maxime.sig.activity.TreePicturesActivity;

import java.util.HashSet;
import java.util.Set;

public class InfoSelection {
    Context mContext;
    static Set<String> couches = new HashSet<String>();;
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

    public static void addCouche(String couche){
        couches.add(couche);
    }

    public static void remove(String couche){
        couches.remove(couche);
    }

    @JavascriptInterface
    public String getCouches(){
        if (couches.size()>0){
            String c = ((couches.toString()).substring(1, (couches.toString().length())-1))
                    .replaceAll(" ", "");
            return c;
        }
        return "sigo:"; //espace_publicev_arbres,sigo:dechets_pav
    }

    @JavascriptInterface
    public void getCoordonnees(String longitude, String latitude){


        Intent intent = new Intent(mContext, SuggestionActivity.class);
        intent.putExtra("latitude", latitude );
        intent.putExtra("longitude", longitude);
        mContext.startActivity(intent);

    }

}
