package com.example.db_hw1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ID;
    EditText Name;
    EditText Salary;
    EditText Sales;
    EditText Rate;

    TextView Search_view;
    Button Insert_btn;
    Button Delete_btn;
    Button Search_btn;
    Button Update_btn;
    Button Display_btn;


    SQLiteDatabase db;

RadioButton f;
RadioButton m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ID = findViewById(R.id.id );
        Name = findViewById(R.id.name );
        Salary =findViewById(R.id.salary );
        Sales = findViewById(R.id.sale );
        Rate = findViewById(R.id.rate );
        //////////////
f=findViewById ( R.id.F );
m=findViewById ( R.id.M );
        //////////////////
        Insert_btn = findViewById(R.id.insrtbtn);
        Delete_btn = findViewById(R.id.dltbtn);
        Search_btn = findViewById(R.id.srchbtn);
        Update_btn = findViewById(R.id.editbtn);
        Display_btn = findViewById(R.id.dspbtn);

        ///////////////


        Search_view =findViewById(R.id.srchView);
/////////////////////
        Insert_btn.setOnClickListener(this);
        Delete_btn.setOnClickListener(this);
        Search_btn.setOnClickListener(this);
        Update_btn.setOnClickListener(this);
        Display_btn.setOnClickListener(this);

     db = openOrCreateDatabase ( "firstDB.db", MODE_PRIVATE, null );
        db.execSQL ( "create table if not exists employee(id number(10) not null,name char(255)not null,sex char(1)not null,basesalary float not null,sales float not null,rate float not null,primary key(id));" );

    }

    @Override
    public void onClick(View v) {
        if (v==Insert_btn)
        {

            String nameText = Name.getText().toString();
            String idText = ID.getText().toString();

            String sexText="";
            if(f.isChecked ()==true)sexText="FEMAIL";
            else if(m.isChecked ()==true)sexText="MALE";
            String salaryText = Salary.getText().toString();
            String salesText = Sales.getText().toString();
            String rateText = Rate.getText().toString();
            InsertData(idText,nameText,sexText,salaryText,salesText,rateText);
   System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            Toast.makeText(getApplicationContext(),"clik",Toast.LENGTH_LONG);

        }

        if(v==Update_btn)
        {
            String nameText = Name.getText().toString();
            String idText = ID.getText().toString();
            String sexText="";
            if(f.isChecked ()==true)sexText="FEMAIL";
            else if(m.isChecked ()==true)sexText="MALE";
            String salaryText = Salary.getText().toString();
            String salesText = Sales.getText().toString();
            String rateText =Rate.getText ().toString ();
            UpdateData(idText,nameText,sexText,salaryText,salesText,rateText);

        }
        if(v==Delete_btn)
        {
            String idText = ID.getText().toString();
            DeleteData(idText);
        }
        if(v==Search_btn)
        {
            String idText = ID.getText().toString();
            Search_view.setText(Searchreturn_id(ID.getText().toString()));

        }
        if(v==Display_btn)
        {
           Cursor res = DisplayData();
            Search_view.setText("");
            if(res.getCount()==0)
            {
                //Result_Text.setText("No Database Available");
                return;
            }
            StringBuffer SB = new StringBuffer();
            while(res.moveToNext())
            {
                SB.append("ID : "+res.getString(0)+"\n");
                SB.append("Name : "+res.getString(1)+"\n");
                SB.append("Sex : "+res.getString(2)+"\n");
                SB.append("Salary: "+res.getString(3)+"\n");
                SB.append("Sales : "+res.getString(4)+"\n");
                SB.append("Rate: "+res.getString(5)+"\n\n\n");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Database Information");
            builder.setMessage(SB.toString());
            builder.show();
            Intent I = new Intent (this,display.class  );
            startActivity ( I );

        }

        }

    public void UpdateData(String id , String name , String sex,String basesalary,String sales,String rate)
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("sex",sex);
        contentValues.put("basesalary",basesalary);
        contentValues.put("sales",sales);
        contentValues.put("rate",rate);
        Cursor cursor = db.rawQuery("Select * from employee where id = ?",new String[]{id});
        if(cursor.getCount()>0) {
            db.update("employee", contentValues, "id=?", new String[]{id});
        }
    }

    public void InsertData(String id, String name , String sex,String basesalary,String sales,String rate)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("sex",sex);
        contentValues.put("basesalary",basesalary);
        contentValues.put("sales",sales);
        contentValues.put("rate",rate);
       db.insert("employee",null,contentValues);
        Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_LONG);
    }
    public void DeleteData(String id)
    {
        Cursor cursor = db.rawQuery("Select * from employee where id = ?",new String[]{id});
        if(cursor.getCount()>0) {
            db.delete ( "employee", "id=?", new String[]{id} );
        }

    }

    public StringBuilder Searchreturn_id(String id)
    {

        StringBuilder SB = new StringBuilder();
        StringBuilder SB2 = new StringBuilder();
        String failed = "Failed to recognize data";
        SB2.append(failed);
        Cursor cursor = db.rawQuery("Select * from employee where id = ?",new String[]{id});
        while (cursor.moveToNext ( )) {
            SB.append("ID : "+cursor.getString(0)+"\t\t\t\t\t      ");
            SB.append("Name : "+cursor.getString(1)+"\t\t\t\t\t");
            SB.append("Sex : "+cursor.getString(2)+"\n");
            SB.append("Salary: "+cursor.getString(3)+"\t\t\t\t\t");
            SB.append("Sales : "+cursor.getString(4)+"\t\t\t\t\t");
            SB.append("Rate: "+cursor.getString(5)+"\t\t\t\t\t");
        }

        return SB;

    }

    public Cursor DisplayData()
    {

        Cursor cursor = db.rawQuery("Select * from employee ",null);
        return cursor;
    }


}
