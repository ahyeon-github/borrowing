package org.techtown.borrowing;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final FirebaseFirestore mStore = FirebaseFirestore.getInstance();


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mMainRecyclerView = findViewById(R.id.main_recycler_view);
        findViewById(R.id.main_write_button).setOnClickListener(this);


        List<Board> mBoardList = new ArrayList<>();

        mStore.collection("board")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                for( DocumentChange dc : queryDocumentSnapshots.getDocumentChanges())  {
                    String id = (String) dc.getDocument().getData().get("id");
                    String title=(String) dc.getDocument().get("title");
                    String contents = (String)dc.getDocument().get("contents");
                    String name = (String) dc.getDocument().getData().get("name");
                    Board data = new Board(id, title, contents, name);

                    mBoardList.add(data);


                }

            }
        });

        mStore.collection("board").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                            //mBoardList.add(new Board(null,"공구 빌려주실 분 있나요?",null, "눈송이"));
                            //mBoardList.add(new Board(null,"자전거에펌프 대여받을 수 있나요?",null, "server"));
                            //mBoardList.add(new Board(null,"눈 오리집게 빌려드려요",null, "솜사탕"));
                            //mBoardList.add(new Board(null,"캐리어 대여해주실 분 계신가요?",null, "자바"));
                            //mBoardList.add(new Board(null,"몽키스패너 필요해요",null, "안드로이드"));

                        }

                        MainAdapter mAdapter = new MainAdapter(mBoardList);
                        mMainRecyclerView.setAdapter(mAdapter);
                    }
                });

    }


    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, WriteActivity.class));
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

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar ab = getSupportActionBar();
            ab.setDisplayShowCustomEnabled(true);
            ab.setDisplayShowTitleEnabled(false);

        }

        private void setSupportActionBar(Toolbar toolbar) {
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
