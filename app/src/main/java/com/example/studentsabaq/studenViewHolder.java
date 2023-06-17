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

class studenViewHolder extends RecyclerView.Adapter<studenViewHolder.MyVH> {

    List<Student> studentList;
    public studenViewHolder(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public studenViewHolder.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_student, parent, false);
        return new MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull studenViewHolder.MyVH holder, int position) {
        holder.data= studentList.get(position);
        holder.textViewFriendName.setText(holder.data.getName());

        byte[] imageBytes = holder.data.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.imageViewFriend.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public class MyVH extends RecyclerView.ViewHolder {
        ImageView imageViewFriend;
        TextView textViewFriendName;

        Student data;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            imageViewFriend = itemView.findViewById(R.id.imageViewFriendPicture);
            textViewFriendName = itemView.findViewById(R.id.textViewFriendName);

        }
    }
}