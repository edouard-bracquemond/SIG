package com.example.maxime.sig.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maxime.sig.R;
import com.example.maxime.sig.model.Report;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private List<Report> dataList;
    private Context context;

    public ReportAdapter(Context context,List<Report> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class ReportViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;

        ReportViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
        }
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.report_row, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {
        Report report = dataList.get(position);
        holder.txtTitle.setText(report.getComment()+"\nSoumis par: "+report.getPseudo()+"\n");

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}