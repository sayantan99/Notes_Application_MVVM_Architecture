package com.example.simplenotesapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes= new ArrayList<>();
    private  OnItemClickListener listener;


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote= notes.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.description.setText(currentNote.getDescription());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public void setNotes(List<Note> notes)
    {
        this.notes=notes;
        notifyDataSetChanged();
    }
    public  Note getNoteAt(int position)
    {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView title,description, priority;
        public NoteHolder( View itemView) {
            super(itemView);

            title= itemView.findViewById(R.id.text_view_title);
            description=itemView.findViewById(R.id.text_view_Description);
            priority=itemView.findViewById(R.id.text_view_prioroty);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(notes.get(position));
                    }

                }
            });


        }
    }
    public  interface OnItemClickListener{
        void onItemClick(Note note);

    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener=listener;

    }

}
