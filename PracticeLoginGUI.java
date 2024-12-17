import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

class Myframe extends JFrame {
    private JTextField nameField, addressField, companyNameField, salaryField, loanAmountField, interestField, PaymentTextField;
    private JComboBox<String> modeOfPaymentComboBox;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JButton calculateButton, resetButton, exitButton;

    public Myframe() {
        setTitle("FINAL PROJECT");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        // Modified background color
        Color backgroundColor =  Color.WHITE; // Light gray background color
        getContentPane().setBackground(backgroundColor);

        JLabel titleLabel = new JLabel("SFP-MLAS (MODIFIED LOAN AMORTIZATION PROGRAM)");
        Font titleFont = new Font("Arial", Font.BOLD, 30);
        titleLabel.setForeground(new Color(0, 153, 255)); // Light blue color
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(400, 10, 1000, 30);
        add(titleLabel);

        // Borrower's Name
        JLabel nameLabel = new JLabel("Borrower's Name:");
        nameLabel.setBounds(120, 95, 300, 30);
        TextFieldStyler.TextStyle(nameLabel);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(400, 85, 300, 35);
        TextFieldStyler.TextStyle(nameField, backgroundColor);
        add(nameField);

        // Business/Company Name
        JLabel companyNameLabel = new JLabel("Business/Company Name:");
        companyNameLabel.setBounds(120, 160, 300, 30);
        TextFieldStyler.TextStyle(companyNameLabel);
        add(companyNameLabel);

        companyNameField = new JTextField();
        companyNameField.setBounds(400, 155, 300, 35);
        TextFieldStyler.TextStyle(companyNameField, backgroundColor);
        add(companyNameField);

        // Borrower's Address
        JLabel addressLabel = new JLabel("Borrower's Address:");
        addressLabel.setBounds(750, 95, 300, 30);
        TextFieldStyler.TextStyle(addressLabel);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(1000, 85, 300, 35);
        TextFieldStyler.TextStyle(addressField, backgroundColor);
        add(addressField);

        // Monthly Income/Salary
        JLabel salaryLabel = new JLabel("Monthly Income/Salary:");
        salaryLabel.setBounds(750, 155, 300, 30);
        TextFieldStyler.TextStyle(salaryLabel);
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(1000, 155, 300, 35);
        TextFieldStyler.TextStyle(salaryField, backgroundColor);
        salaryField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ValidateInput().handleInput(salaryField, "Salary");
            }
        });
        add(salaryField);

        // Mode of Payment
        JLabel modeOfPaymentLabel = new JLabel("Mode of Payment:");
        modeOfPaymentLabel.setBounds(120, 220, 300, 30);
        TextFieldStyler.TextStyle(modeOfPaymentLabel);
        add(modeOfPaymentLabel);

        String[] paymentModes = {"|","Daily", "Weekly", "Monthly"};
        modeOfPaymentComboBox = new JComboBox<>(paymentModes);
        modeOfPaymentComboBox.setBounds(400, 220, 300, 35);
        TextFieldStyler.TextStyle(modeOfPaymentComboBox, backgroundColor);  // Pass the background color
        add(modeOfPaymentComboBox);
        // Loan Amount
        JLabel loanAmountLabel = new JLabel("Loan Amount:");
        loanAmountLabel.setBounds(750, 220, 300, 30);
        TextFieldStyler.TextStyle(loanAmountLabel);
        add(loanAmountLabel);

        loanAmountField = new JTextField();
        loanAmountField.setBounds(1000, 220, 300, 35);
        TextFieldStyler.TextStyle(loanAmountField, backgroundColor);
        loanAmountField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ValidateInput().handleInput(loanAmountField, "Loan Amount");
            }
        });
        add(loanAmountField);

         // Interest Rate
         JLabel interestRateLabel = new JLabel("Interest Rate %:");
         interestRateLabel.setBounds(850, 280, 300, 30);
         TextFieldStyler.TextStyle(interestRateLabel);
         add(interestRateLabel);
 
         interestField = new JTextField();
         interestField.setBounds(1050, 280, 300, 35);
        
         Font textFieldFont = new Font(interestField.getFont().getName(), Font.BOLD, 20);
         interestField.setFont(textFieldFont);
         interestField.setBackground(backgroundColor);
         interestField.setBorder(null);
         interestField.setEditable(false);
         add(interestField);

        // Terms of Payment
        JLabel paymentLabel = new JLabel("Terms of Payment:");
        paymentLabel.setBounds(850, 340, 200, 30);
        TextFieldStyler.TextStyle(paymentLabel);
        add(paymentLabel);

        JLabel SchedLabel = new JLabel("SCHEDULE OF PAYMENT");
        TextFieldStyler.TextStyle(SchedLabel);
        SchedLabel.setBounds(50, 340, 300, 30);
        add(SchedLabel);

        PaymentTextField = new JTextField();
        PaymentTextField.setBounds(1050, 340, 300, 35);
        PaymentTextField.setFont(new Font(PaymentTextField.getFont().getName(), Font.BOLD, 20));
       
        Font textFieldFont1 = new Font(PaymentTextField.getFont().getName(), Font.BOLD, 20);
        PaymentTextField.setFont(textFieldFont1);
        PaymentTextField.setBackground(backgroundColor);
        PaymentTextField.setBorder(null);
        PaymentTextField.setEditable(false);
        add(PaymentTextField);

        resetButton = new JButton("Borrow Again");
        resetButton.setBounds(150, 760, 300, 50);
        TextFieldStyler.TextStyle(resetButton);
        add(resetButton);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(550, 760, 450, 50);
        TextFieldStyler.TextStyle(calculateButton);
        add(calculateButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(1100, 760, 300, 50);
        TextFieldStyler.TextStyle(exitButton);
        add(exitButton);


        // Initialize table
        String[] columnNames = {"No. of Payments", "Payment Date", "Beginning Balance", "Principal Payment", "Interest Amount", "D/W/M Payment", "Ending Balance"};
        tableModel = new DefaultTableModel(columnNames, 0);
        resultTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultTable.getTableHeader().setReorderingAllowed(false);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < resultTable.getColumnCount(); i++) {
        resultTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        resultTable.setFont(new Font("Monospaced", Font.BOLD, 16));
        TextFieldStyler.TextStyle(resultTable);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBounds(50, 380, 1445, 350);
        add(scrollPane);

        // Buttons
        modeOfPaymentComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mode = (String) modeOfPaymentComboBox.getSelectedItem();
                int rate = CheckRate_Terms.getRate(mode);
                interestField.setText(Integer.toString(rate));
        
                String paymentDays = CheckRate_Terms.getPayterms(mode);
                PaymentTextField.setText(paymentDays);
            }
        });    

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                addressField.setText("");
                companyNameField.setText("");
                salaryField.setText("");
                loanAmountField.setText("");
                interestField.setText(""); // Clear the interest rate field
                PaymentTextField.setText(""); // Clear the terms of payment field
                modeOfPaymentComboBox.setSelectedIndex(0);
                tableModel.setRowCount(0); // Clear the table
            }
        });
        

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Confirm Exit", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (response == JOptionPane.YES_OPTION) {
                    dispose(); // Close the frame
                }
            }
        });
        

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String address = addressField.getText();
                    String companyName = companyNameField.getText();
                    double salary = Double.parseDouble(salaryField.getText());
                    String modeOfPayment = (String) modeOfPaymentComboBox.getSelectedItem();
                    if (modeOfPayment.equals("|")) {
                        JOptionPane.showMessageDialog(null, "Please choose a mode of payment.", "Missing Mode of Payment", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int rate = new CheckRate_Terms().getRate(modeOfPayment);
                        double loanAmount = Double.parseDouble(loanAmountField.getText());
                        if (name.isEmpty() || address.isEmpty() || companyName.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill in all information.", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
                        } else if (salary <= 0) {
                            JOptionPane.showMessageDialog(null, "INVALID SALARY VALUE: Negative values are not allowed", "ERROR", JOptionPane.ERROR_MESSAGE);
                            salaryField.setText("");
                            salaryField.requestFocus();
                        } else if (loanAmount <= 0) {
                            JOptionPane.showMessageDialog(null, "INVALID LOAN VALUE: Negative values are not allowed", "ERROR", JOptionPane.ERROR_MESSAGE);
                            loanAmountField.setText("");
                            loanAmountField.requestFocus();
                        } else {
                            SchedPay.schedPay(modeOfPayment, loanAmount, rate, tableModel);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numerical values for salary and loan amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // USING LAMBDA EXPRESSION PARA MO GAMAY NALANG ANG LINES
    
        nameField.addActionListener(e -> addressField.requestFocusInWindow());

        addressField.addActionListener(e -> companyNameField.requestFocusInWindow());
        
        companyNameField.addActionListener(e -> salaryField.requestFocusInWindow());
        
        salaryField.addActionListener(e -> loanAmountField.requestFocusInWindow());
        
        interestField.addActionListener(e -> calculateButton.doClick());
        
        loanAmountField.addActionListener(e -> calculateButton.doClick());
        
    }
}

class ValidateInput {
    public void handleInput(JTextField textField, String fieldName) {
        String input = textField.getText();
        try {
            double amount = Double.parseDouble(input);
            if (amount < 0) {
                throw new NumberFormatException("Negative " + fieldName + " is not allowed.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "INVALID INPUT. Please enter a positive " + fieldName.toLowerCase() + " number only.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textField.setText("");
            textField.requestFocus();
        }
    }
}

class TextFieldStyler {
    public static void TextStyle(JTextField textField, Color backgroundColor) {
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0, 102, 204), 2, true), // Dark blue color border with thickness 2
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        Font textFieldFont = new Font(textField.getFont().getName(), Font.BOLD, 17);
        textField.setFont(textFieldFont);
        
        textField.setBackground(backgroundColor);
    }
    public static void TextStyle(JLabel textLabel) {
        textLabel.setFont(new Font(textLabel.getFont().getName(), Font.BOLD, 18));
    }

    public static void TextStyle(JComboBox<String> textCombo, Color backgroundColor) {
        textCombo.setFont(new Font(textCombo.getFont().getName(), Font.BOLD, 20));
        textCombo.setBackground(backgroundColor); 

    }

    public static void TextStyle(JButton TextButton){
        TextButton.setFont(new Font(TextButton.getFont().getName(), Font.BOLD, 20));
        TextButton.setFocusable(false);
    }

    public static void TextStyle(JTable TextTable){
        TextTable.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 16));
    }
}
class CheckRate_Terms {
    public static int getRate(String mop) {
        int rate;
        if (mop.equalsIgnoreCase("Daily")) {
            rate = 20;
        } else if (mop.equalsIgnoreCase("Weekly")) {
            rate = 15;
        } else if (mop.equalsIgnoreCase("Monthly")) {
            rate = 10;
        } else {
            rate = 0; // Return 0 if mop is empty
        }
        return rate;
    }
    public static String getPayterms(String mode) {
        String paymentDays;
        if (mode.equalsIgnoreCase("Daily")) {
            paymentDays = "30 days";
        } else if (mode.equalsIgnoreCase("Weekly")) {
            paymentDays = "5 weeks";
        } else if (mode.equalsIgnoreCase("Monthly")) {
            paymentDays = "1 month";
        } else {
            paymentDays = " "; // Default case, should not be reached
        }
        return paymentDays;
    }
}

class SchedPay {
    public static void schedPay(String mop, double loan, int rate, DefaultTableModel tableModel) {
        DecimalFormat df = new DecimalFormat("#.00");
    
        double principalPayment, numPaymentFloat;
        double[] begBalance = new double[30];
        double[] principalPay = new double[30];
        double[] interestAmount = new double[30];
        double[] endBalance = new double[30];
        double[] wdmPay = new double[30];
        double currentBalance = loan;
        double totalInterest = 0.0, totalWdmPay = 0.0, totalPrincipal = 0.0;
    
        if (mop.equalsIgnoreCase("Daily")) {
            numPaymentFloat = 30;
            principalPayment = loan / 30;
            for (int payNum = 0; payNum < 30; payNum++) {
                begBalance[payNum] = currentBalance;
                principalPay[payNum] = principalPayment;
    
                if (payNum < 10) {
                    interestAmount[payNum] = currentBalance * (rate / 16.0) / 100;
                } else if (payNum < 30) {
                    interestAmount[payNum] = currentBalance * (rate / 15.0) / 100;
                } else {
                    interestAmount[payNum] = currentBalance * (rate / 14.0) / 100;
                }
    
                wdmPay[payNum] = principalPay[payNum] + interestAmount[payNum];
                endBalance[payNum] = currentBalance - principalPay[payNum];
                currentBalance = endBalance[payNum];
    
                totalInterest += interestAmount[payNum];
                totalWdmPay += wdmPay[payNum];
                totalPrincipal += principalPay[payNum];
            }
        } else if (mop.equalsIgnoreCase("Weekly")) {
            numPaymentFloat = 5;
            principalPayment = loan / 5;
            for (int payNum = 0; payNum < 5; payNum++) {
                begBalance[payNum] = currentBalance;
                principalPay[payNum] = principalPayment;
                interestAmount[payNum] = currentBalance * (rate / 3.0) / 100;
                wdmPay[payNum] = principalPay[payNum] + interestAmount[payNum];
                endBalance[payNum] = currentBalance - principalPay[payNum];
                currentBalance = endBalance[payNum];
    
                totalInterest += interestAmount[payNum];
                totalWdmPay += wdmPay[payNum];
                totalPrincipal += principalPay[payNum];
            }
        } else {
            numPaymentFloat = 2;
            principalPayment = loan / 1;
            for (int payNum = 0; payNum < 2; payNum++) {
                begBalance[payNum] = currentBalance;
                if (payNum > 0) {
                    interestAmount[payNum] = currentBalance / rate;
                    principalPay[payNum] = principalPayment;
                    wdmPay[payNum] = principalPay[payNum] + interestAmount[payNum];
                    endBalance[payNum] = currentBalance - principalPay[payNum];
                    currentBalance = endBalance[payNum];
                } else {
                    // For the first day, set principal payment, interest amount, and DWM payment to 0.00
                    principalPay[payNum] = 0.00;
                    interestAmount[payNum] = 0.00;
                    wdmPay[payNum] = 0.00;
                    endBalance[payNum] = currentBalance;
                }
        
                totalInterest += interestAmount[payNum];
                totalWdmPay += wdmPay[payNum];
                totalPrincipal += principalPay[payNum];
            }
        }
        
    
        // Clear the table before adding new data
        tableModel.setRowCount(0);
    
        for (int i = 0; i < numPaymentFloat; i++) {
            int daysToAdd;
            if (mop.equalsIgnoreCase("Daily")) {
                daysToAdd = i;
            } else if (mop.equalsIgnoreCase("Weekly")) {
                daysToAdd = i * 7;
            } else {
                daysToAdd = i * 30;
            }
            int day = 1 + daysToAdd;
            int month = 12 + (day - 1) / 30;
            int year;
    
            if (month > 12) {
                day = 1 + (day - 1) % 30;
                month = 1;
                year = 2024;
            } else {
                day = 1 + (day - 1) % 30;
                year = 2023;
            }
    
            // Formatting ending balance
            String formattedEndBalance;
            if (endBalance[i] < 0.01) {
                formattedEndBalance = "0.00";
            } else {
                formattedEndBalance = df.format(endBalance[i]);
            }
             // Adding row to the table
            String formattedPrincipalPay = df.format(principalPay[i]);
            String formattedInterestAmount = df.format(interestAmount[i]);
            String formattedWdmPay = df.format(wdmPay[i]);

            if (principalPay[i] < 0.01) {
                formattedPrincipalPay = "0.00";
            }
            if (interestAmount[i] < 0.01) {
                formattedInterestAmount = "0.00";
            }
            if (wdmPay[i] < 0.01) {
                formattedWdmPay = "0.00";
            }

            tableModel.addRow(new Object[]{
                i + 1, String.format("%02d/%02d/%d", day, month, year),
                df.format(begBalance[i]), 
                formattedPrincipalPay, 
                formattedInterestAmount,
                formattedWdmPay, 
                formattedEndBalance // Use formattedEndBalance here
            });
        }
        tableModel.addRow(new Object[]{"", "", "", "", "", "", ""}); // Empty row for spacing
        tableModel.addRow(new Object[]{"Total", "", "", df.format(totalPrincipal), df.format(totalInterest), df.format(totalWdmPay), "", ""});
    }
}
public class PracticeLoginGUI {
    public static void main(String[] args) {
        new Myframe().setVisible(true);
    }
}



