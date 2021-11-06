package com.video.client.engine.app.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.video.client.R;

public class ClientFragment extends Fragment implements View.OnClickListener {

    public static String TAG = ClientFragment.class.getSimpleName();

    private static final String EXTRA_TEXT = "text";
    public static final int SERVER_PORT = 5050;

    public static final String SERVER_IP = "192.168.43.76";
    private Context mContext;
    private ClientThread clientThread;
    private Thread thread;
    private LinearLayout msgList;
    private Handler handler;
    private int clientTextColor;
    private EditText edMessage;

    public static ClientFragment createFor(String text) {
        ClientFragment fragment = new ClientFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_app_client, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        final String text = getArguments().getString(EXTRA_TEXT);
        clientTextColor = ContextCompat.getColor(mContext, R.color.green);
        handler = new Handler();
        msgList = view.findViewById(R.id.msgList);
        edMessage = view.findViewById(R.id.edMessage);
        view.findViewById(R.id.connect_server).setOnClickListener(this);
        view.findViewById(R.id.send_data).setOnClickListener(this);
        
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();

    }

    public TextView textView(String message, int color, Boolean value) {
        if (null == message || message.trim().isEmpty()) {
            message = "<Empty Message>";
        }
        TextView tv = new TextView(mContext);
        tv.setTextColor(color);
        tv.setText(message + " [" + getTime() + "]");
        tv.setTextSize(20);
        tv.setPadding(0, 5, 0, 0);
        tv.setLayoutParams(new LinearLayout.LayoutParams
                           (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0));
        if (value) {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        }
        return tv;
    }

    public void showMessage(final String message, final int color, final Boolean value) {
        handler.post(new Runnable() {
                @Override
                public void run() {
                    msgList.addView(textView(message, color, value));
                }
            });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.connect_server:
                msgList.removeAllViews();
                clientThread = new ClientThread();
                thread = new Thread(clientThread);
                thread.start();
                break;

            case R.id.send_data:
                String clientMessage = edMessage.getText().toString().trim();
                showMessage(clientMessage, Color.BLUE, false);
                if (null != clientThread) {
                    if (clientMessage.length() > 0) {
                        clientThread.sendMessage(clientMessage);
                    }
                    edMessage.setText("");
                }
                break;
        }
    }

    /* clientThread class defined to run the client connection to the socket network using the server ip and port
     * and send message */
    class ClientThread implements Runnable {

        private Socket socket;
        private BufferedReader input;

        @Override
        public void run() {

            try {

                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                showMessage("Connecting to Server...", clientTextColor, true);

                socket = new Socket(serverAddr, SERVER_PORT);

                if (socket.isBound()) {

                    showMessage("Connected to Server...", clientTextColor, true);
                }


                while (!Thread.currentThread().isInterrupted()) {


                    this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = input.readLine();
                    if (null == message || "Disconnect".contentEquals(message)) {
                        Thread.interrupted();
                        message = "Server Disconnected...";
                        showMessage(message, Color.RED, false);
                        break;
                    }
                    showMessage("Server: " + message, clientTextColor, true);
                }

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                showMessage("Problem Connecting to server... Check your server IP and Port and try again", Color.RED, false);
                Thread.interrupted();
                e1.printStackTrace();
            } catch (NullPointerException e3) {
                showMessage("error returned", Color.RED, true);
            }

        }

        void sendMessage(final String message) {
            new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (null != socket) {
                                PrintWriter out = new PrintWriter(new BufferedWriter(
                                                                      new OutputStreamWriter(socket.getOutputStream())),
                                                                  true);
                                out.println(message);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
        }

    }

    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != clientThread) {
            clientThread.sendMessage("Disconnect");
            clientThread = null;
        }
    }
}

