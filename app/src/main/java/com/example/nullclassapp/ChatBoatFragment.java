package com.example.nullclassapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nullclassapp.Service.MyApi;
import com.example.nullclassapp.adapter.MessageAdapter;
import com.example.nullclassapp.model.ChatModel;
import com.example.nullclassapp.model.Message;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ChatBoatFragment extends Fragment {
    String url="https://api.stackexchange.com/";
    RecyclerView recyclerView;
    TextView welcomeMessage;
    EditText message_text_text;
    LinearLayout send_btn;
    List<Message> messageList = new ArrayList<>();
    MessageAdapter messageAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_chat_boat,container,false);

        messageList = new ArrayList<>();
        //====================================
        message_text_text = view.findViewById(R.id.messageEdittext);
        send_btn = view.findViewById(R.id.sendBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
        welcomeMessage = view.findViewById(R.id.welcome_text);
        // InstatiateWebSoket();

        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(requireContext());
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message  =  message_text_text.getText().toString();
                if(!message.isEmpty()){
                    //webSocket.send(message);
                    stackResp(message);
                    addToChat(message,Message.SENT_BY_ME,"");
                    //  sendMessage("1","Ali","365KY");
                    message_text_text.setText("");
                    welcomeMessage.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    private void stackResp(String message) {
        Retrofit retrofit= new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApi api = retrofit.create(MyApi.class);
        Call<ChatModel> call = api.getResponse(1,5,"asc","activity",message,"stackoverflow");
        call.enqueue(new Callback<ChatModel>() {
            @Override
            public void onResponse(Call<ChatModel> call, Response<ChatModel> response) {
                if (response.body() != null && !response.body().getItems().isEmpty()){
                    response.body().getItems().forEach(item -> {
                        //responseList.add(item.getTitle() + item.getLink());
                        addResponse(item.getTitle() ,item.getLink());
                    });
                }else addResponse("No response found","");
//            String title =    response.body().getItems().get(0).getTitle();
//            String link =    response.body().getItems().get(0).getLink();


            }

            @Override
            public void onFailure(Call<ChatModel> call, Throwable t) {

            }
        });
    }

    void addToChat(String message,String sentBy,String link){
        getActivity().runOnUiThread(new Runnable() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void run() {
                messageList.add(new Message(message,sentBy,link));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }

    void addResponse(String response,String link){
        addToChat(response,Message.SENT_BY_BOT,link);
    }

}