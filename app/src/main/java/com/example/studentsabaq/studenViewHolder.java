package com.example.studentsabaq;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class studentViewHolder extends RecyclerView.Adapter<studentViewHolder.MyVH> {

    List<Student> studentList;
    private OnItemClickListener listener;

    public studentViewHolder(List<Student> studentList) {
        this.studentList = studentList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_student, parent, false);
        return new MyVH(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        Student student = studentList.get(position);
        holder.textViewFriendName.setText(student.getName());

        int imageResourceId = student.getImage();
        Bitmap bitmap = BitmapFactory.decodeResource(holder.itemView.getContext().getResources(), imageResourceId);
        holder.imageViewFriend.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        ImageView imageViewFriend;
        TextView textViewFriendName;

        public MyVH(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageViewFriend = itemView.findViewById(R.id.imageViewFriendPicture);
            textViewFriendName = itemView.findViewById(R.id.textViewFriendName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
