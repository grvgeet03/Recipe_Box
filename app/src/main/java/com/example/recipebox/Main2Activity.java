package com.example.recipebox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    private ArrayList<RecipeItem> rList;
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Recipe Box");

        createRecipeList();
        buildRecyclerView();
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecipeAdapter(rList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("Image",rList.get(position).getmImageResource());
                intent.putExtra("Name",rList.get(position).getmRecipeName());
                intent.putExtra("Instruction",rList.get(position).getmRecipeDesc());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_log_out, menu);

        MenuItem searchItem = menu.findItem(R.id.rSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.log_out:
                showDialog();
                break;
            case R.id.profie:
                startActivity(new Intent(getApplicationContext(),profileActivity.class));
            default:

        }
        return super.onOptionsItemSelected(item);
    }
    public void showDialog(){

        final AlertDialog.Builder alert =new AlertDialog.Builder(Main2Activity.this);
        View mView = getLayoutInflater().inflate(R.layout.logout_dialog,null);
        Button btn_cancel=(Button)mView.findViewById(R.id.cancel);
        Button btn_ok=(Button)mView.findViewById(R.id.okLogout);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backToast=Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    public void createRecipeList(){
        rList = new ArrayList<>();
        rList.add(new RecipeItem(R.drawable.r1, "Aloo Gobhi",getString(R.string.recipe1)));
        rList.add(new RecipeItem(R.drawable.r2, "Aloo Parantha",getString(R.string.recipe2)));
        rList.add(new RecipeItem(R.drawable.r3, "Aloo Beans",getString(R.string.recipe3)));
        rList.add(new RecipeItem(R.drawable.r4, "Arhar Daal",getString(R.string.recipe4)));
        rList.add(new RecipeItem(R.drawable.r5, "Baati",getString(R.string.recipe5)));
        rList.add(new RecipeItem(R.drawable.r6, "Bhelpuri",getString(R.string.recipe6)));
        rList.add(new RecipeItem(R.drawable.r7, "Bhindi",getString(R.string.recipe7)));
        rList.add(new RecipeItem(R.drawable.r8, "Biryani",getString(R.string.recipe8)));
        rList.add(new RecipeItem(R.drawable.r9, "Butter naan",getString(R.string.recipe9)));
        rList.add(new RecipeItem(R.drawable.r10, "Chaat",getString(R.string.recipe10)));
        rList.add(new RecipeItem(R.drawable.r11, "Chana masala",getString(R.string.recipe11)));
        rList.add(new RecipeItem(R.drawable.r12, "Chapati/Roti/Phulke",getString(R.string.recipe12)));
        rList.add(new RecipeItem(R.drawable.r13, "Chhole Bhature",getString(R.string.recipe13)));
        rList.add(new RecipeItem(R.drawable.r14, "Dahi vada",getString(R.string.recipe14)));
        rList.add(new RecipeItem(R.drawable.r15, "Dal makhana",getString(R.string.recipe15)));
        rList.add(new RecipeItem(R.drawable.r16, "Dal Panchratan / Pancharatni",getString(R.string.recipe16)));
        rList.add(new RecipeItem(R.drawable.r17, "Dhokla",getString(R.string.recipe17)));
        rList.add(new RecipeItem(R.drawable.r18, "Gajar ka halwa",getString(R.string.recipe18)));
        rList.add(new RecipeItem(R.drawable.r19, "Gulab jamun",getString(R.string.recipe19)));
        rList.add(new RecipeItem(R.drawable.r20, "Idli",getString(R.string.recipe20)));
        rList.add(new RecipeItem(R.drawable.r21, "Jalebi",getString(R.string.recipe21)));
        rList.add(new RecipeItem(R.drawable.r22, "Jeera rice/Chawal",getString(R.string.recipe22)));
        rList.add(new RecipeItem(R.drawable.r23, "Kadhai Paneer",getString(R.string.recipe23)));
        rList.add(new RecipeItem(R.drawable.r24, "Kadhi",getString(R.string.recipe24)));
        rList.add(new RecipeItem(R.drawable.r25, "Kheer",getString(R.string.recipe25)));
        rList.add(new RecipeItem(R.drawable.r26, "Khichdi",getString(R.string.recipe26)));
        rList.add(new RecipeItem(R.drawable.r27, "Kulfi",getString(R.string.recipe27)));
        rList.add(new RecipeItem(R.drawable.r28, "Laddu",getString(R.string.recipe28)));
        rList.add(new RecipeItem(R.drawable.r29, "Maki ki roti",getString(R.string.recipe29)));
        rList.add(new RecipeItem(R.drawable.r30, "Malai kofta",getString(R.string.recipe30)));
        rList.add(new RecipeItem(R.drawable.r31, "Masala Chai / Tea",getString(R.string.recipe31)));
        rList.add(new RecipeItem(R.drawable.r32, "Masala Dosa",getString(R.string.recipe32)));
        rList.add(new RecipeItem(R.drawable.r33, "Matar paneer",getString(R.string.recipe33)));
        rList.add(new RecipeItem(R.drawable.r34, "Mix Veg",getString(R.string.recipe34)));
        rList.add(new RecipeItem(R.drawable.r35, "Panipuri/Golgappe/Phuchka",getString(R.string.recipe35)));
        rList.add(new RecipeItem(R.drawable.r36, "Papadum",getString(R.string.recipe36)));
        rList.add(new RecipeItem(R.drawable.r37, "Pav Bhaji",getString(R.string.recipe37)));
        rList.add(new RecipeItem(R.drawable.r38, "Pulao",getString(R.string.recipe38)));
        rList.add(new RecipeItem(R.drawable.r39, "Puri/Poori",getString(R.string.recipe39)));
        rList.add(new RecipeItem(R.drawable.r40, "Raita",getString(R.string.recipe40)));
        rList.add(new RecipeItem(R.drawable.r41, "Rajma",getString(R.string.recipe41)));
        rList.add(new RecipeItem(R.drawable.r42, "Sambar",getString(R.string.recipe42)));
        rList.add(new RecipeItem(R.drawable.r43, "Samosa",getString(R.string.recipe43)));
        rList.add(new RecipeItem(R.drawable.r44, "Sarso ka saag",getString(R.string.recipe44)));
        rList.add(new RecipeItem(R.drawable.r45, "Shahi paneer",getString(R.string.recipe45)));
        rList.add(new RecipeItem(R.drawable.r46, "Urad ki daal",getString(R.string.recipe46)));
        rList.add(new RecipeItem(R.drawable.r47, "Uttapam",getString(R.string.recipe47)));
        rList.add(new RecipeItem(R.drawable.r48, "Vada pav",getString(R.string.recipe48)));



    }

}
