import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;



public class Quiz implements ActionListener{
    

    // Stockage des questions dans un array
    String[] questions = {
                            "           Which country has the highest IQ ?",
                            "           Which year was Java created ?",
                            "           What was Java originally called ?",
                            "           Who is credited with creating Java ?"
                         };
    
    String[][] options = {
                            {"Korea","Japan","Russia","Taiwan"},
                            {"1989","1996","1972","1492"},
                            {"Apple","Latte","Oak","Koffing"},
                            {"Steve Jobs","Bill Gates","James Gosling","Marc Zuckerberg"},
                         };
    

    // Correct Answer here and will collect those datas in an array later on
    char[] answers =     {
                            'A',
                            'B',
                            'C',
                            'D'
                         };
                         
    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds=10;

    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    
    

    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();

    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();

    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JButton resetButton = new JButton("RESET");

    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();
    
    // Timer 
    Timer timer = new Timer(1000,new ActionListener(){

        @Override

        public void actionPerformed(ActionEvent e){
            // decrementation de la variable seconds
           seconds--;
           seconds_left.setText(String.valueOf(seconds));

           // Condition une fois que le countdown descend en dessous de 0 
           if(seconds<=0){
            displayAnswer();
           }
        }   
    });




    public Quiz(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color(45,85,10));
        frame.setLayout(null);
        frame.setResizable(false);

        // Setting up the platform to start the game calling the methods 
        textfield.setBounds(0,0,650,50);
        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25, 255,0));
        textfield.setFont(new Font("MV Boli",Font.BOLD,30));
        textfield.setBorder(BorderFactory.createBevelBorder(0));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);


        textarea.setBounds(0,50,650,50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(25,23,0));
        textarea.setForeground(new Color(25, 255,0));
        textarea.setFont(new Font("Ink Free",Font.BOLD,30));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);
        

        // Button components A
        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");


        // B
        buttonB.setBounds(0,200,100,100);
        buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");


        // C
        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");


        // D
        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        // Answer part with all the settings 
        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(25,255,0));
        answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,35));
        
        answer_labelB.setBounds(125,200,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(25,255,0));
        answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,35));
       


        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(25,255,0));
        answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,35));
       

        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(25,255,0));
        answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,35));
       


        seconds_left.setBounds(550,530,100,100);
        seconds_left.setBackground(new Color(25, 25, 25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(540,485,150,25);
        time_label.setBackground(new Color(50,50,50));
        time_label.setForeground(new Color(255,0,0));
        time_label.setFont(new Font("MV Boli",Font.ROMAN_BASELINE,20));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("Timer");

        number_right.setBounds(450,235,200,100);
        number_right.setBackground(new Color(25,25,25));
        number_right.setForeground(new Color(25, 255, 0));
        number_right.setFont(new Font("Ink Free",Font.BOLD,45));
        number_right.setBorder(BorderFactory.createBevelBorder(0));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(450,355,200,120);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(25,255,0));
        percentage.setFont(new Font("Ink Free",Font.BOLD,45));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);



        // Ajout des elements par la méthode en appellant le frame 
        frame.add(number_right);
        frame.add(percentage);
        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textarea);
        frame.add(textfield);
        frame.setVisible(true);


        nextQuestion();
    }


    public void nextQuestion(){

        if(index>=total_questions){
            results();
        }else{
            textfield.setText("Question " + (index+1));
            textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == resetButton){
            resetQuiz();
        }else{
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA){
            answer = 'A';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonB){
            answer = 'B';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonC){
            answer = 'C';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonD){
            answer = 'D';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
            // Après avoir conditionné et bindé les boutons, on appelle la méthode Display Answer qui va afficher la réponse, une fois 
            displayAnswer();
        }
        number_right.setText(null);
        percentage.setText(null);
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);

       
    }

    public void displayAnswer(){

        timer.stop();

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        // A chaque event de la réponse au clique la réponse s'afficherae dans une couleur parametrée en RGB grace à la méthode setForeground

        if(answers[index] != 'A')
            answer_labelA.setForeground(new Color(255,0,0));
        if(answers[index] != 'B')
            answer_labelB.setForeground(new Color(255,0,0));
        if(answers[index] != 'C')
            answer_labelC.setForeground(new Color(255,0,0));
        if(answers[index] != 'D')
            answer_labelD.setForeground(new Color(255,0,0));

        // On crée un délair pour le temps d'attnte
        Timer pause = new Timer(2000,new ActionListener(){

            @Override

            public void actionPerformed(ActionEvent e){

                // Changement de couleur dès action de l'user
                answer_labelA.setForeground(new Color(25,255,0));
                answer_labelB.setForeground(new Color(25,255,0));
                answer_labelC.setForeground(new Color(25,255,0));
                answer_labelD.setForeground(new Color(25,255,0));

                // On reset notre reponse
                answer = ' ';
                seconds =10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }   
        });
        pause.setRepeats(false);
        pause.start();
    }


    public void results(){
        
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/ (double)total_questions)*100); // Resultat du taux de reponse correct 

        textfield.setText("RESULTS !");
        textarea.setText("");

        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText("("+correct_guesses+"/"+total_questions+")");
        percentage.setText(result+"%");

        frame.add(number_right);
        frame.add(percentage);
        frame.add(resetButton);

        
        resetButton.setBounds(205, 450, 200, 100);
        // Position 
        resetButton.setFont(new Font("MV Boli", Font.TYPE1_FONT, 35));
        resetButton.setForeground(new Color(95,100,80));
        resetButton.setVisible(true);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        resetButton.setVisible(true);


    }

     public void resetQuiz(){
        index = 0;
        correct_guesses = 0;
        seconds = 10;
        resetButton.setVisible(false);
        nextQuestion();
    }
}
