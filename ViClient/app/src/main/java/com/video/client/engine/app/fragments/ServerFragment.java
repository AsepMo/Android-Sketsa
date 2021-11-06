package com.video.client.engine.app.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.video.client.R;
import com.video.client.application.Application;

public class ServerFragment extends Fragment implements View.OnClickListener{

    public static String TAG = ServerFragment.class.getSimpleName();
    
    private static final String EXTRA_TEXT = "text";
    private Context mContext;
    //initializes all the private properties
    //For any server the ServerSocket and the Socket corresponding to the temp client
    // to be activated must be initialized
    private ServerSocket serverSocket;
    private Socket tempClientSocket;

    //here it sets the Thread initially to null
    Thread serverThread = null;

    //the SERVER_PORT is initialized which must correspond to the port of the client
    public static final int SERVER_PORT = 5050;

    //the msgList is initialized corresponding to the Linearlayout
    private LinearLayout msgList;
    private Handler handler;
    private int greenColor;
    private EditText edMessage;
    
    public static ServerFragment createFor(String text) {
        ServerFragment fragment = new ServerFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_app_server, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        
        final String text = getArguments().getString(EXTRA_TEXT);
        mContext = getActivity();
        //initializes the identifier greenColor to be used anywhere within this file
        greenColor = ContextCompat.getColor(mContext, R.color.green);

        //initializes a new handler for message queueing
        handler = new Handler();
        msgList = view.findViewById(R.id.msgList);
        edMessage = view.findViewById(R.id.edMessage);
        view.findViewById(R.id.start_server).setOnClickListener(this);
        view.findViewById(R.id.send_data).setOnClickListener(this);
        
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
    
    //method to implement the different Textviews widget and display the message on
    //the Scrollview LinearLayout...
    public TextView textView(String message, int color, Boolean value) {

        //it checks if the message is empty then displays empty message
        if (null == message || message.trim().isEmpty()) {
            message = "<Empty Message>";
        }
        TextView tv = new TextView(mContext);
        tv.setTextColor(color);
        tv.setText(message + " [" + getTime() +"]");
        tv.setTextSize(20);
        tv.setPadding(0, 5, 0, 0);
        if (value) {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        }
        return tv;
    }

    //showMessage method to handle posting of mesage to the textView
    public void showMessage(final String message, final int color, final Boolean value) {
        handler.post(new Runnable() {
                @Override
                public void run() {
                    msgList.addView(textView(message, color, value));
                }
            });
    }

    //onClick method to handle clicking events whether to start up the  server or
    //send a message to the client
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_server:
                msgList.removeAllViews();
            showMessage("Server Started.", Color.BLACK, false);

            //this initiates the serverthread defined later and starts the thread
            this.serverThread = new Thread(new ServerThread());
            this.serverThread.start();
            view.setVisibility(View.GONE);
                break;

            case R.id.send_data:
                String msg = edMessage.getText().toString().trim();
                showMessage("Server : " + msg, Color.BLUE, false);
                if (msg.length() > 0) {

                    sendMessage(msg);
                }
                edMessage.setText("");
                break;
        }
    }
 
    //method implemented to send message to the client
    private void sendMessage(final String message) {
        try {
            if (null != tempClientSocket) {
                new Thread(new Runnable() {
                        @Override
                        public void run() {
                            PrintWriter out = null;
                            try {
                                out = new PrintWriter(new BufferedWriter(
                                                          new OutputStreamWriter(tempClientSocket.getOutputStream())),
                                                      true);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            out.println(message);
                        }
                    }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* serverthread method implemented here to activate the server network */
    class ServerThread implements Runnable {

        public void run() {
            Socket socket;
            try {
                serverSocket = new ServerSocket(SERVER_PORT);

                //deactivates the visibility of the button
//               Button button = (Button) findViewById(R.id.start_server);
//               button.setVisibility(View.GONE);
            } catch (IOException e) {
                e.printStackTrace();
                showMessage("Error Starting Server : " + e.getMessage(), Color.RED, false);
            }

            //communicates to client and displays error if communication fails
            if (null != serverSocket) {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        socket = serverSocket.accept();
                        CommunicationThread commThread = new CommunicationThread(socket);
                        new Thread(commThread).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                        showMessage("Error Communicating to Client :" + e.getMessage(), Color.RED, false);
                    }
                }
            }
        }
    }

    /* communicationThread class that implements the runnable class to communicate with the client */
    class CommunicationThread implements Runnable {

        private Socket clientSocket;

        private BufferedReader input;

        public CommunicationThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
            tempClientSocket = clientSocket;
            try {
                this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
                showMessage("Error Connecting to Client!!", Color.RED, false);
            }
            showMessage("Connected to Client!!", greenColor, true);
        }

        public void run() {

            while (!Thread.currentThread().isInterrupted()) {
                try {

                    //checks to see if the client is still connected and displays disconnected if disconnected
                    String read = input.readLine();
                    if (null == read || "Disconnect".contentEquals(read)) {
                        Thread.interrupted();
                        read = "Offline....";
                        
                        showMessage("Client : " + read, greenColor, true);
                        break;
                    }
                    else if (null == read || "Exit".contentEquals(read)) {
                        
                        Application.getInstance().exitApplication(mContext);
                        showMessage("Client : " + read, greenColor, true);
                    }
                    showMessage("Client : " + read, greenColor, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }


    //getTime method implemented to format the date into H:m:s
    String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    //personally described onDestroy method to disconnect from the network on destroy of the activity
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != serverThread) {
            sendMessage("Disconnect");
            serverThread.interrupt();
            serverThread = null;
        }
    }
}

