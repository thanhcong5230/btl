package com.example.coffee;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> userList;
    private Clickitem  clickitem;

    public UserAdapter(Clickitem clickitem) {
        this.clickitem=clickitem;
    }

    //  public  UserAdapter( List<User> userList, Clickitem listener) {
    ////   this.userList = userList;
    //   this.clickitem=listener;
    // }


//code them


    public void setData(List<User> list)
    {
        this.userList= list;
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent, false);
        return  new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user =userList.get(position);
        if(user == null)
        {
            return;
        }
        holder.imgUser.setImageResource(user.getResouceImage());
        holder.imgName.setText(user.getName());
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // userList.remove(holder.getLayoutPosition());
                //  notifyItemRemoved(holder.getLayoutPosition());
                clickitem. clickdelete(user);



            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickitem.onClickitemtable(user);

            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickitem.clickupdate(user);
            }
        });


    }



    @Override
    public int getItemCount() {
        if(userList !=null)
        {
            return userList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgUser;
        private TextView imgName;
        private ImageButton imageButton;
        private CardView cardView;
        private  ImageButton img;



        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser=itemView.findViewById(R.id.img_user);
            imgName=itemView.findViewById(R.id.tv_name);
            imageButton=itemView.findViewById(R.id.btndDelete);
            cardView=itemView.findViewById(R.id.layout_item);
            img=itemView.findViewById(R.id.imgupdate);


        }
    }

}
