package com.example.movieapi.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapi.R;
import com.example.movieapi.actions.IMovieUpdate;
import com.example.movieapi.response.Movies;
import com.squareup.picasso.Picasso;
import static com.example.movieapi.utils.Config.*;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.SongViewHolder>{
    public Context mContext;
    public List<Movies> mMovieList;
    public IMovieUpdate iMovieUpdate;
    public MovieClickListen movieClickListen;

    public interface MovieClickListen {
        void onClick(Movies movie);
    }

    public MoviesAdapter(Context mContext, List<Movies> mMovieList, MovieClickListen movieClickListen) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        this.movieClickListen = movieClickListen;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Movies mMovie = mMovieList.get(position);
//        holder.tvId.setText(mMovie.id + "");
        holder.tvName.setText(mMovie.title);
        holder.tvDate.setText(mMovie.release_date);
        holder.tvVote.setText(mMovie.vote_average+ "");
//        holder.tvOverView.setText(mMovie.overview);
//        holder.tvCreate.setText(mMovie.createdAt);
        Picasso.with(mContext).load(BASE_BACKDROP_PATH + mMovie.poster_path).into(holder.img);

//        holder.btnDelete.setOnClickListener(view -> {
//            new DeleteTask().execute(mMovie.id);
//        });
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
//    public interface RecycleViewListen {
//        void onClick(View v, int position);
//    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvId, tvName , tvOverView, tvDate, tvVote;
        public ImageView img;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);

//            tvOverView = itemView.findViewById(R.id.tv_overview);
            tvName = itemView.findViewById(R.id.item_movie_title);
            tvVote = itemView.findViewById(R.id.tv_rate);
//            tvId = itemView.findViewById(R.id.tv_id_movieItem);
            img = itemView.findViewById(R.id.item_movie_img);
            tvDate = itemView.findViewById(R.id.item_movie_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            movieClickListen.onClick(mMovieList.get(getAdapterPosition()));
        }
    }

    class DeleteTask extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {
            int id =params[0];
            OkHttpClient client = new OkHttpClient();
            try {
                Request.Builder builder = new Request.Builder();
                Request request = new Request.Builder().url(BASE_URL_API + id).delete().build();
                Response response = client.newCall(request).execute();
                return response.body().toString();

            }catch (Exception e) {
                Log.e("Delete Song", e.toString());
            }

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            iMovieUpdate.update();
        }

    }
}
