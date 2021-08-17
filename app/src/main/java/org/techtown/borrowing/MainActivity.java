package org.techtown.borrowing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mMainRecyclerView = findViewById(R.id.main_recycler_view);
        findViewById(R.id.main_write_button).setOnClickListener(this);

        List<Board> mBoardList = new ArrayList<>();
        mBoardList.add(new Board(null,"공구 빌려주실 분 있나요?",null, "눈송이"));
        mBoardList.add(new Board(null,"자전거에펌프 대여받을 수 있나요?",null, "server"));
        mBoardList.add(new Board(null,"눈 오리집게 빌려드려요",null, "솜사탕"));
        mBoardList.add(new Board(null,"캐리어 대여해주실 분 계신가요?",null, "자바"));
        mBoardList.add(new Board(null,"몽키스패너 필요해요",null, "안드로이드"));

        MainAdapter mAdapter = new MainAdapter(mBoardList);
        mMainRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
    }
    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
        
        private final List<Board> mBoardList;

        public MainAdapter(List<Board> mBoardList) {
            this.mBoardList = mBoardList;
        }


        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            Board data = mBoardList.get(position);
            holder.mTitleTextView.setText(data.getTitle());
            holder.mNameTextView.setText(data.getName());

        }

        @Override
        public int getItemCount() {
            return mBoardList.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder{
            
            private final TextView mTitleTextView;
            private final TextView mNameTextView;

            public MainViewHolder(@NonNull View itemView) {
                super(itemView);

                mTitleTextView = itemView.findViewById(R.id.item_title_text);
                mNameTextView = itemView.findViewById(R.id.item_name_text);

            }
        }
    }
}