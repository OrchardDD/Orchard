package com.example.dell.kuang.DCZ_activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.dell.kuang.BaseActivity;
import com.example.dell.kuang.DCZ_bean.PersonBean;
import com.example.dell.kuang.R;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LitepalActivity extends BaseActivity {

    private Button button1;//创建表
    private Button button2;//添加数据
    private Button button3;//更新数据
    private Button button4;//删除数据
    private Button button5;//查询数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepal);
        setViews();
        setListener();
    }

    private void setViews() {
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        button4= (Button) findViewById(R.id.button4);
        button5= (Button) findViewById(R.id.button5);
        populateDataFromDB();
    }

    private void setListener() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Connector.getDatabase();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonBean person=new PersonBean();
                //   person.setId(1);
                person.setName("帅哥");
                person.setAge(20);
                person.setIsMale(0);
                person.save();
                populateDataFromDB();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonBean person=new PersonBean();
                person.setName("美女");
                person.setAge(19);
                //person.updateAll("name=? and age=?","帅哥","20");
                person.update(Long.parseLong("33"));    //把id为33的数据更新
                populateDataFromDB();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // person.delete();//直接删除这张表
                    //DataSupport.delete(person.class,1);//删除News表中id为1的数据
                    DataSupport.deleteAll(PersonBean.class, "name=? and age=?" ,"帅哥","20");
                    populateDataFromDB();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<PersonBean>list=DataSupport.findAll(PersonBean.class);
                for(PersonBean news:list){
                    Log.i("查询name",news.getName());
                }
            }
        });
    }
    private void populateDataFromDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = null;
                try {
                    //将表中的数据全部赋值到列表中
                    cursor = Connector.getDatabase().rawQuery("select * from PersonBean order by id",null);
                    if (cursor.moveToFirst()) {
                        do {
                            long id = cursor.getLong(cursor.getColumnIndex("id"));
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            int age = cursor.getInt(cursor.getColumnIndex("age"));
                            int isMale = cursor.getInt(cursor.getColumnIndex("ismale"));
                            Log.i("查询id",id+"");
                            Log.i("查询name",name);
                            Log.i("查询age",age+"");
                            Log.i("查询isMale",isMale+"");
                        } while (cursor.moveToNext());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        }).start();
    }
}
