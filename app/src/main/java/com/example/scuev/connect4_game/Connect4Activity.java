package com.example.scuev.connect4_game;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;


public class Connect4Activity extends AppCompatActivity implements View.OnClickListener
{

    private Connect4                 objConnect4;
    private int                      varPosXInt,varPosYInt,childCount;
    private Button                   buttonCircle,buttonTurn,buttonNewGame;
    private TextView                 textViewRed,textViewBlue;
    private String                   won;
    private LinearLayout             row0,row1,row2,row3,row4,row5;
    private Map<String,LinearLayout> listRows;
    private ViewGroup                mainLayout;
    private Button                   buttonCirclePlay;

    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_connect4);

        row0 = (LinearLayout)findViewById(R.id.layout_circlesRow1);
        row1 = (LinearLayout)findViewById(R.id.layout_circlesRow2);
        row2 = (LinearLayout)findViewById(R.id.layout_circlesRow3);
        row3 = (LinearLayout)findViewById(R.id.layout_circlesRow4);
        row4 = (LinearLayout)findViewById(R.id.layout_circlesRow5);
        row5 = (LinearLayout)findViewById(R.id.layout_circlesRow6);

        generateVector();

        mainLayout = (RelativeLayout) findViewById(R.id.main);
        buttonCirclePlay = (Button) findViewById(R.id.image);

        buttonCirclePlay.setOnTouchListener(onTouchListener());

        ScrollView sView = (ScrollView)findViewById(R.id.my_scrolview);
        // Hide the Scollbar
        sView.setVerticalScrollBarEnabled(false);
        sView.setHorizontalScrollBarEnabled(false);

        //Get evens from imageButtons
        findViewById(R.id._0_0).setOnClickListener(this);
        findViewById(R.id._0_1).setOnClickListener(this);
        findViewById(R.id._0_2).setOnClickListener(this);
        findViewById(R.id._0_3).setOnClickListener(this);
        findViewById(R.id._0_4).setOnClickListener(this);
        findViewById(R.id._0_5).setOnClickListener(this);
        findViewById(R.id._0_6).setOnClickListener(this);
        //Get evens from imageButtons
        findViewById(R.id._1_0).setOnClickListener(this);
        findViewById(R.id._1_1).setOnClickListener(this);
        findViewById(R.id._1_2).setOnClickListener(this);
        findViewById(R.id._1_3).setOnClickListener(this);
        findViewById(R.id._1_4).setOnClickListener(this);
        findViewById(R.id._1_5).setOnClickListener(this);
        findViewById(R.id._1_6).setOnClickListener(this);
        //Get evens from imageButtons
        findViewById(R.id._2_0).setOnClickListener(this);
        findViewById(R.id._2_1).setOnClickListener(this);
        findViewById(R.id._2_2).setOnClickListener(this);
        findViewById(R.id._2_3).setOnClickListener(this);
        findViewById(R.id._2_4).setOnClickListener(this);
        findViewById(R.id._2_5).setOnClickListener(this);
        findViewById(R.id._2_6).setOnClickListener(this);
        //Get evens from imageButtons
        findViewById(R.id._3_0).setOnClickListener(this);
        findViewById(R.id._3_1).setOnClickListener(this);
        findViewById(R.id._3_2).setOnClickListener(this);
        findViewById(R.id._3_3).setOnClickListener(this);
        findViewById(R.id._3_4).setOnClickListener(this);
        findViewById(R.id._3_5).setOnClickListener(this);
        findViewById(R.id._3_6).setOnClickListener(this);
        //Get evens from imageButtons
        findViewById(R.id._4_0).setOnClickListener(this);
        findViewById(R.id._4_1).setOnClickListener(this);
        findViewById(R.id._4_2).setOnClickListener(this);
        findViewById(R.id._4_3).setOnClickListener(this);
        findViewById(R.id._4_4).setOnClickListener(this);
        findViewById(R.id._4_5).setOnClickListener(this);
        findViewById(R.id._4_6).setOnClickListener(this);
        //Get evens from imageButtons
        findViewById(R.id._5_0).setOnClickListener(this);
        findViewById(R.id._5_1).setOnClickListener(this);
        findViewById(R.id._5_2).setOnClickListener(this);
        findViewById(R.id._5_3).setOnClickListener(this);
        findViewById(R.id._5_4).setOnClickListener(this);
        findViewById(R.id._5_5).setOnClickListener(this);
        findViewById(R.id._5_6).setOnClickListener(this);
        //Get evens from button score and newGame
        findViewById(R.id.new_game_button).setOnClickListener(this);
        //Instance of the class Game connect4
        objConnect4     = new Connect4();
        //Get Button Turn and New Game
        buttonTurn      = (Button)findViewById(R.id.turn_label);
        buttonNewGame   = (Button)findViewById(R.id.new_game_button);
        //set Button Turn default
        buttonTurn.setText(R.string.blue_turn);
        buttonTurn.setBackgroundColor(Color.BLUE);
        //Get Score
        textViewRed     = (TextView) findViewById(R.id.red_score_label);
        textViewBlue    = (TextView) findViewById(R.id.blue_score_label);

        textViewRed.setText(String.valueOf(objConnect4.getScoreGameRed()));
        textViewBlue.setText(String.valueOf(objConnect4.getScoreGameBlue()));


    }


    @Override
    public void onClick(View v)
    {


        switch (v.getId())
        {

            case R.id.new_game_button:
                //Reset Game and recreate a new Game and show message
                objConnect4.newGame();
                resetGame();
                Toast.makeText(com.example.scuev.connect4_game.Connect4Activity.this,"New Game",Toast.LENGTH_SHORT).show();

                 break;
            default:

                try
                {
                    buttonCircle         = (Button)findViewById(v.getId());
                    String valuePosition = buttonCircle.getText().toString();
                    String[] parts       = valuePosition.split(",");
                    varPosXInt           = Integer.parseInt(parts[0]);
                    varPosYInt           = Integer.parseInt(parts[1]);

                }catch (Exception ex)
                {
                    break;

                }finally
                {

                    if (objConnect4.validateMovedOverCircle(varPosXInt,varPosYInt))
                    {
                        if(buttonCircle.getTag()==null)
                        {

                            if (objConnect4.getTurn() == 1)
                            {
                                buttonCircle.setBackgroundResource(R.drawable.blue);
                                objConnect4.setTurn(2);
                                buttonCircle.setTag("blue");
                                buttonTurn.setText(R.string.red_turn);
                                buttonTurn.setBackgroundColor(Color.RED);
                                objConnect4.fillMatrix(varPosXInt, varPosYInt, 1);
                                won = "Blue Win!";
                                buttonCirclePlay.setBackgroundResource(R.drawable.red);

                            } else if (objConnect4.getTurn() == 2)
                            {
                                buttonCircle.setBackgroundResource(R.drawable.red);
                                objConnect4.setTurn(1);
                                buttonTurn.setText(R.string.blue_turn);
                                buttonTurn.setBackgroundColor(Color.BLUE);
                                buttonCircle.setTag("red");
                                objConnect4.fillMatrix(varPosXInt, varPosYInt, 2);
                                won = "Red Win!";
                                buttonCirclePlay.setBackgroundResource(R.drawable.blue);
                            }
                        }
                    }

                    if (objConnect4.labelEndGame)
                    {
                        AlertDialog alert = new AlertDialog.Builder(this)
                                .setTitle("Information")
                                .setMessage(won.toUpperCase())
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        objConnect4.newGame();
                                        resetGame();
                                        textViewRed.setText(String.valueOf(objConnect4.getScoreGameRed()));
                                        textViewBlue.setText(String.valueOf(objConnect4.getScoreGameBlue()));
                                        Toast.makeText(com.example.scuev.connect4_game.Connect4Activity.this,"Very well, do not give up!",Toast.LENGTH_SHORT).show();

                                    }
                                }).create();
                        alert.setCanceledOnTouchOutside(false);
                        alert.show();
                    }
                }
                break;
        }
    }

    private void resetGame()
    {

        for (int x=0;x< 6;x++)
        {
            childCount = listRows.get("row"+x).getChildCount();
            for(int i=0;i<childCount;i++)
            {
                View child =  listRows.get("row"+x).getChildAt(i);
                //reset all Buttons in vector Layout
                child.setBackgroundResource(R.drawable.circle_grey);
                child.setTag(null);
            }
        }
        buttonTurn.setText(R.string.blue_turn);
        buttonTurn.setBackgroundColor(Color.BLUE);
        childCount = 0;
    }

    private void generateVector()
    {
        listRows = new HashMap<>();
        listRows.put("row0",row0);
        listRows.put("row1",row1);
        listRows.put("row2",row2);
        listRows.put("row3",row3);
        listRows.put("row4",row4);
        listRows.put("row5",row5);

        for (int x=0;x<6;x++)
        {
            childCount = listRows.get("row"+x).getChildCount();
            for(int i=0;i<childCount;i++)
            {
                Button child =  (Button) listRows.get("row"+x).getChildAt(i);
                //reset all Buttons in vector Layout
                child.setText(""+x+","+i);
            }
        }

        childCount = 0;

    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:
                        Toast.makeText(Connect4Activity.this,
                                "xDelta: "+x+" Ydelta"+y, Toast.LENGTH_SHORT)
                                .show();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }
}