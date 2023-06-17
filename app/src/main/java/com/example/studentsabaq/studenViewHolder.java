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

    public studentViewHolder(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_student, parent, false);
        return new MyVH(itemView);
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

        public MyVH(@NonNull View itemView) {
            super(itemView);
            imageViewFriend = itemView.findViewById(R.id.imageViewFriendPicture);
            textViewFriendName = itemView.findViewById(R.id.textViewFriendName);
        }
    }
}
