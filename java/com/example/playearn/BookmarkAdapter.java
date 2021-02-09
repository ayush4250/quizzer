package com.example.playearn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.Viewholder> {

    private List<QuestionsModel> list;

    public BookmarkAdapter(List<QuestionsModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BookmarkAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_item,parent,false);
       return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.Viewholder holder, int position) {
      holder.setData(list.get(position).getQuestion(),list.get(position).getCorrectAns(),position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView question,answer;
        private ImageButton deletebtn;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            question=itemView.findViewById(R.id.question1);
            answer=itemView.findViewById(R.id.answer);
            deletebtn=itemView.findViewById(R.id.delete_btn);
        }

        private void setData(String question, String answer, final int position){
            this.question.setText(question);
            this.answer.setText(answer);

            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }
    }
}
