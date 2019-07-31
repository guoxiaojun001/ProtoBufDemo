package com.protobufdemo;

import android.os.Bundle;
import android.widget.TextView;

import com.google.protobuf.InvalidProtocolBufferException;
import com.protobufdemo.protobuf.PersonBean;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView before,after;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        before = findViewById(R.id.before);
        after = findViewById(R.id.after);

        try {
            testN();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }


    public void testN() throws InvalidProtocolBufferException {
        PersonBean.Person.Builder builder = PersonBean.Person.newBuilder();
        builder.setId(1);
        builder.setName("jihite");

        PersonBean.Person person = builder.build();
        System.out.println("before:" + person);
        before.setText("序列化之前：" + person.toString());
        System.out.println("===Person Byte:");
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }
        System.out.println("================");

        byte[] byteArray = person.toByteArray();
        PersonBean.Person p2 = PersonBean.Person.parseFrom(byteArray);
        System.out.println("after id:" + p2.getId());
        System.out.println("after name:" + p2.getName());
        after.setText("序列化之后：" + p2.toString());

    }
}
