import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMInterface extends JFrame {
    private BankAccount account;
    private JLabel balanceLabel;

    public ATMInterface(BankAccount account) {
        this.account = account;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        balanceLabel = new JLabel("Current Balance: $" + account.getBalance());
        panel.add(balanceLabel);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (account.withdraw(amount)) {
                        balanceLabel.setText("Current Balance: $" + account.getBalance());
                        JOptionPane.showMessageDialog(null, "Withdrawn: $" + amount);
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient funds. Withdrawal failed.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount entered.");
                }
            }
        });
        panel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
                try {
                    double amount = Double.parseDouble(amountStr);
                    account.deposit(amount);
                    balanceLabel.setText("Current Balance: $" + account.getBalance());
                    JOptionPane.showMessageDialog(null, "Deposited: $" + amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount entered.");
                }
            }
        });
        panel.add(depositButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the ATM window
            }
        });
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000); // Initialize with $1000 balance
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMInterface(userAccount);
            }
        });
    }
}
