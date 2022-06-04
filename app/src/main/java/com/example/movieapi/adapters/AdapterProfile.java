package com.example.movieapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.movieapi.R;
import com.example.movieapi.response.Account;
import com.example.movieapi.ui.HomeActivity;
import com.example.movieapi.ui.Profile;
import com.example.movieapi.ui.UpdateProfile;

import java.util.List;


public class AdapterProfile extends BaseAdapter {
    private Profile context;
    private int layout;
    private List<Account> listAccount;

    public AdapterProfile(Profile context, int layout, List<Account> listAccount) {
        this.context = context;
        this.layout = layout;
        this.listAccount = listAccount;
    }

    @Override
    public int getCount() {
        return listAccount.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        private TextView tvIDP, tvName, tvEmail, tvPhone, tvDiaChi, tvFB;
        private ImageView update, imgBack;
        private RelativeLayout rtlUser;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.tvIDP = (TextView) view.findViewById(R.id.tv_PIDProfile);
            holder.tvName = (TextView) view.findViewById(R.id.tv_nameProfile);
            holder.tvEmail = (TextView) view.findViewById(R.id.tv_EmailProfile);
            holder.tvPhone = (TextView) view.findViewById(R.id.tv_PhoneProfile);
            holder.tvDiaChi = (TextView) view.findViewById(R.id.tv_DiaChiProfile);
            holder.tvFB = (TextView) view.findViewById(R.id.tv_FaceBookProfile);

            holder.rtlUser = view.findViewById(R.id.rtlUser);
            holder.update = view.findViewById(R.id.img_Update);
            holder.imgBack = view.findViewById(R.id.img_backProfile);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

        }

        Account account = listAccount.get(i);
        holder.tvIDP.setText("ID:" + account.getPIDProfile());
        holder.tvName.setText(account.getNameProfile());
        holder.tvEmail.setText(account.getEmailProfile());
        holder.tvPhone.setText(account.getPhoneProfile());
        holder.tvDiaChi.setText(account.getDiaChiProfile());
        holder.tvFB.setText(account.getFbProfile());

        // Cicle Animation
        Runnable runnableCycle = new Runnable() {
            @Override
            public void run() {
                holder.rtlUser.animate().rotationBy(360).withEndAction(this).setDuration(10000)
                        .setInterpolator(new LinearInterpolator()).start();
            }
        };
        holder.rtlUser.animate().rotationBy(360).withEndAction(runnableCycle).setDuration(10000)
                .setInterpolator(new LinearInterpolator()).start();

        // Update
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateProfile.class);
                intent.putExtra("dataAccount", account);
                context.startActivity(intent);
            }
        });

        // Back
        holder.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
