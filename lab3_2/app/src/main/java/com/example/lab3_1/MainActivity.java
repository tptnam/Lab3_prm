package com.example.lab3_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

//    String[] list = {"Android", "PHP", "iOS", "Unity", "ASP.net"};
    ArrayList<String> list = new ArrayList<>(Arrays.asList("Android", "PHP", "iOS", "Unity", "ASP.net"));
    ArrayAdapter<String> myAdapter;
    ListView lv;
    EditText addInput;
    Button btnAdd, btnUp;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        addInput = findViewById(R.id.addInput);
        btnAdd = findViewById(R.id.btnAdd);
        btnUp = findViewById(R.id.btnUp);


        myAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(myAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course = addInput.getText().toString().trim();

                if (course.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a course name.", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if the input is empty
                }

                list.add(course);
                myAdapter.notifyDataSetChanged();
                addInput.setText("");
                Toast.makeText(MainActivity.this, "Add a new course successfully!", Toast.LENGTH_SHORT).show();
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() { //setText for input and get position
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                addInput.setText(list.get(position));
                index = position;
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course = addInput.getText().toString().trim();

                if (course.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a course name.", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if the input is empty
                }
                list.set(index, addInput.getText().toString());
                myAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Update this course successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            String courseToDelete = list.get(position);
            list.remove(position);
            myAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Deleted course: " + courseToDelete, Toast.LENGTH_SHORT).show();
            return true; // Indicates that the long-click event has been handled
        });
    }
}