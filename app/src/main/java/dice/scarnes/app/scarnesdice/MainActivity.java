package dice.scarnes.app.scarnesdice;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;



public class MainActivity extends AppCompatActivity {
    public static int diceNum;
    public static int useroverall;
    public static int computeroverall;
    public static int userturn;
    public static int computerturn;
    public static int x;
    public static int compint;
    public static int flag;
    public int diceRoll() {
        ImageView img = findViewById(R.id.diceimg);
        int[] dice= {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };
        diceNum=ThreadLocalRandom.current().nextInt(1,7);
        img.setImageResource(dice[diceNum-1]);
        return diceNum;
    }

    public void computerTurn() {
        computerturn=0;
        Button roll = findViewById(R.id.roll);
        Button hold = findViewById(R.id.hold);
        flag=0;
        roll.setEnabled(false);
        hold.setEnabled(false);
        for(x=1; x<=4;x++) {
            if(flag==1)
                break;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Button roll = findViewById(R.id.roll);
                    Button hold = findViewById(R.id.hold);

                    roll.setEnabled(false);
                    hold.setEnabled(false);
                    compint = diceRoll();
                    if(compint==1)
                    {
                        computerturn=0;
                        flag=1;
                    }
                    else {
                        computerturn = computerturn+ compint;
                        x++;
                    }

                    TextView compturn=findViewById(R.id.compturn);
                    compturn.setText("Computer Turn Score: "+ Integer.toString(computerturn));

                }


            }, 1000*x);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Button roll = findViewById(R.id.roll);
                Button hold = findViewById(R.id.hold);
                roll.setEnabled(true);
                hold.setEnabled(true);
                TextView comptotal = findViewById(R.id.comptotal);
                computeroverall=computeroverall+computerturn;
                comptotal.setText("Computer Score: "+ Integer.toString(computeroverall));
                if(computeroverall>=100) {
                    TextView computerwins=findViewById(R.id.computerwins);
                    TextView playerwins=findViewById(R.id.playerwins);
                    computerwins.setVisibility(TextView.VISIBLE);
                    playerwins.setVisibility(TextView.GONE);

                    roll.setEnabled(false);
                    hold.setEnabled(false);
                }
            }},1000*x);




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        useroverall=0;
        userturn=0;
        computeroverall=0;
        computerturn=0;
        TextView computerwins=findViewById(R.id.computerwins);
        TextView playerwins=findViewById(R.id.playerwins);
        computerwins.setVisibility(TextView.GONE);
        playerwins.setVisibility(TextView.GONE);



        Button roll = findViewById(R.id.roll);
        Button hold = findViewById(R.id.hold);
        Button reset= findViewById(R.id.reset);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button roll = findViewById(R.id.roll);
                Button hold = findViewById(R.id.hold);
                roll.setEnabled(true);
                hold.setEnabled(true);
                TextView userturntext=findViewById(R.id.userturn);
                int number=diceRoll();
                if(number==1) {
                    userturn = 0;
                    roll.setEnabled(false);
                    hold.setEnabled(false);
                    computerTurn();

                    roll.setEnabled(true);
                    hold.setEnabled(true);
                }
                else
                    userturn=userturn+number;
                userturntext.setText("Your Turn Score: " + Integer.toString(userturn));


            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            TextView total = findViewById(R.id.usertotal);
            TextView userturntext=findViewById(R.id.userturn);

            @Override
            public void onClick(View view) {
                Button roll = findViewById(R.id.roll);
                Button hold = findViewById(R.id.hold);
                useroverall=useroverall+userturn;
                userturn=0;
                total.setText("Your Total: " + Integer.toString(useroverall));
                userturntext.setText("Your Turn Score:" + Integer.toString(userturn) );
                if(useroverall>=100) {
                    TextView computerwins=findViewById(R.id.computerwins);
                    TextView playerwins=findViewById(R.id.playerwins);
                    computerwins.setVisibility(TextView.GONE);
                    playerwins.setVisibility(TextView.VISIBLE);

                    roll.setEnabled(false);
                    hold.setEnabled(false);

                }
                else {
                    roll.setEnabled(false);
                    hold.setEnabled(false);
                    computerTurn();
                    roll.setEnabled(true);
                    hold.setEnabled(true);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {

            TextView total = findViewById(R.id.usertotal);
            TextView userturntext=findViewById(R.id.userturn);
            TextView compturn=findViewById(R.id.compturn);
            TextView comptotal = findViewById(R.id.comptotal);
            @Override
            public void onClick(View view) {
                useroverall=0;
                userturn=0;
                computeroverall=0;
                computerturn=0;
                Button roll = findViewById(R.id.roll);
                Button hold = findViewById(R.id.hold);
                roll.setEnabled(true);
                hold.setEnabled(true);
                total.setText("Your Total: " + Integer.toString(useroverall));
                userturntext.setText("Your Turn Score:" + Integer.toString(userturn) );
                compturn.setText("Computer Turn Score: " + Integer.toString(computerturn));
                comptotal.setText("Computer Score: " + Integer.toString(computeroverall));
                TextView computerwins=findViewById(R.id.computerwins);
                TextView playerwins=findViewById(R.id.playerwins);
                computerwins.setVisibility(TextView.GONE);
                playerwins.setVisibility(TextView.GONE);

            }

        });

    }
}
