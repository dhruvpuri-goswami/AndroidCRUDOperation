package com.example.androidcrudoperation;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidcrudoperation.data.MyDBHandler;
import com.example.androidcrudoperation.modal.Courses;

import java.util.ArrayList;
import java.util.List;

public class UpdateCourse extends AppCompatActivity {

    ArrayList<String> arrayList=new ArrayList<String>();

    MyDBHandler db =new MyDBHandler(UpdateCourse.this);
    EditText name,duration,desc;
    Button add,update,delete,show;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatecourse);

        name = (EditText)findViewById(R.id.name);
        duration = (EditText)findViewById(R.id.duration);
        desc = (EditText)findViewById(R.id.desc);
        add=(Button)findViewById(R.id.add);
        update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete);
        show=(Button)findViewById(R.id.show);
        lv=(ListView) findViewById(R.id.lv);

    }

    public void getAllItems(View view){
        List<Courses> allUsers = null;
        allUsers = db.getAllUsers();
        for(Courses courses :allUsers) {
            String name = courses.getName();
            String dura = courses.getDuration();
            String desc = courses.getDescription();
            arrayList.add("Course Name : " + name + "\nDuration : " +dura+"\nDescription : "+desc);
        }
        ArrayAdapter<String> adt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(adt);
    }

    public void addItem(View view) {
        Intent intent = new Intent(UpdateCourse.this,MainActivity.class);
        startActivity(intent);
    }

    public void updateItem(View view) {
        String cname=name.getText().toString();
        String dura = duration.getText().toString();
        String descri = desc.getText().toString();
        Courses courses=new Courses();
        courses.setName(cname);
        courses.setDuration(dura);
        courses.setDescription(descri);
        int ar = db.updatecourse(courses);
        if(ar==1){
            Toast.makeText(getApplicationContext(),"Course Updated Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteItem(View view) {
        Intent intent = new Intent(UpdateCourse.this,delete.class);
        startActivity(intent);
    }
}

