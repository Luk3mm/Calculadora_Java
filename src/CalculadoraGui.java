import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalculadoraGui extends JFrame implements ActionListener{
    private final SpringLayout springLayout = new SpringLayout();
    private CalculadoraServico calculadoraServico;

    private JTextField campoDisplay;

    private JButton[] botoes;

    private boolean operadorPressionado = false;
    private boolean igualPressionado = false;

    public CalculadoraGui(){
        super(ConstantesComum.APP_NAME);
        setSize(ConstantesComum.APP_SIZE[0], ConstantesComum.APP_SIZE[1]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(springLayout);

        calculadoraServico = new CalculadoraServico();

        addComponenteGui();
    }

    public void addComponenteGui(){
        addCampoDisplayComponente();

        addBotaoComponente();
    }

    public void addCampoDisplayComponente(){
        JPanel campoPainelDisplay = new JPanel();
        campoDisplay = new JTextField(ConstantesComum.TEXTFIELD_LENGTH);
        campoDisplay.setFont(new Font("Dialog", Font.PLAIN, ConstantesComum.TEXTFIELD_FONTSIZE));
        campoDisplay.setEditable(false);
        campoDisplay.setText("0");
        campoDisplay.setHorizontalAlignment(SwingConstants.RIGHT);

        campoPainelDisplay.add(campoDisplay);
        this.getContentPane().add(campoPainelDisplay);
        springLayout.putConstraint(SpringLayout.NORTH, campoPainelDisplay, ConstantesComum.TEXTFIELD_SPRINGLAYOUT_NORTHPAD, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, campoPainelDisplay, ConstantesComum.TEXTFIELD_SPRINGLAYOUT_WESTPAD, SpringLayout.WEST, this);
    }

    public void addBotaoComponente(){
        GridLayout gridLayout = new GridLayout(ConstantesComum.BUTTON_ROWCOUNT, ConstantesComum.BUTTON_COLCOUNT);
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(gridLayout);
        botoes = new JButton[ConstantesComum.BUTTON_COUNT];
        for(int i = 0; i < ConstantesComum.BUTTON_COUNT; i++){
            JButton botao = new JButton(getButtonLabel(i));
            botao.setFont(new Font("Dialog", Font.PLAIN, ConstantesComum.BUTTON_FONTSIZE));
            botao.addActionListener(this);

            painelBotoes.add(botao);
        }

        gridLayout.setHgap(ConstantesComum.BUTTON_HGAP);
        gridLayout.setVgap(ConstantesComum.BUTTON_VGAP);

        this.getContentPane().add(painelBotoes);

        springLayout.putConstraint(SpringLayout.NORTH, painelBotoes, ConstantesComum.BUTTON_SPRINGLAYOUT_NORTHPAD, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, painelBotoes, ConstantesComum.BUTTON_SPRINGLAYOUT_WESTPAD, SpringLayout.WEST, this);
    }

    public String getButtonLabel(int indexDoBotao){
        switch(indexDoBotao){
            case 0:
                return "7";
            case 1:
                return "8";
            case 2:
                return "9";
            case 3:
                return "/";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "x";
            case 8:
                return "1";
            case 9:
                return "2";
            case 10:
                return "3";
            case 11:
                return "-";
            case 12:
                return "0";
            case 13:
                return ".";
            case 14:
                return "=";
            case 15:
                return "+";
        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String botaoDoComando = e.getActionCommand();
        if(botaoDoComando.matches("[0-9]")){
            if(igualPressionado || operadorPressionado || campoDisplay.getText().equals("0")){
                campoDisplay.setText(botaoDoComando);
            }
            else{
                campoDisplay.setText(campoDisplay.getText() + botaoDoComando);
            }

            operadorPressionado = false;
            igualPressionado = false;
        }
        else if(botaoDoComando.equals("=")){
            calculadoraServico.setNumero2(Double.parseDouble(campoDisplay.getText()));

            double resultado = 0;
            switch(calculadoraServico.getSimboloMatematico()){
                case '+':
                    resultado = calculadoraServico.adicionar();
                    break;
                case '-':
                    resultado = calculadoraServico.subtrair();
                    break;
                case 'x':
                    resultado = calculadoraServico.multiplicar();
                    break;
                case '/':
                    resultado = calculadoraServico.dividir();
                    break;
            }

            campoDisplay.setText(Double.toString(resultado));

            igualPressionado = true;
            operadorPressionado = false;
        }
        else if(botaoDoComando.equals(".")){
            if(campoDisplay.getText().contains(".")){
                campoDisplay.setText(campoDisplay.getText() + botaoDoComando);
            }
        }
        else{
            if(!operadorPressionado){
                calculadoraServico.setNumero1(Double.parseDouble(campoDisplay.getText()));
            }

            calculadoraServico.setSimboloMatematico(botaoDoComando.charAt(0));

            operadorPressionado = true;
            igualPressionado = false;
        }
    }
}
