package Basiclooping.intermediatelooping.nestedloops.Analyticalandcomplexproblems.ArraySample.Array.ArrayList;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class AtmSimulation {

    static class Transaction {
        private String type;
        private int amount;

        public Transaction(String type, int amount) {
            this.type = type;
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public int getAmount() {
            return amount;
        }

        public String toString() {
            return type + ": Rp" + amount;
        }
    }

    public static void main(String[] args) {

        Scanner inputY = new Scanner(System.in);

        int balance = 1000000;

        // ArrayList
        ArrayList<Transaction> transactions = new ArrayList<>();

        // Stack
        Stack<Transaction> transactionStack = new Stack<>();

        // Queue
        Queue<Transaction> pendingTransactions = new LinkedList<>();

        int menu;

        do {
            System.out.println("\n===== ATM SIMULATION =====");
            System.out.println("1) Deposit");
            System.out.println("2) Withdrawal");
            System.out.println("3) Check Balance");
            System.out.println("4) Amount Deposited");
            System.out.println("5) Amount Withdrawn");
            System.out.println("6) Transactions (ArrayList)");
            System.out.println("7) Latest Transaction (Stack)");
            System.out.println("8) Pending Transactions (Queue)");
            System.out.println("9) Process Transaction");
            System.out.println("0) Exit");

            System.out.print("\nSelect Menu: ");
            menu = inputY.nextInt();

            if (menu == 1 || menu == 2) {

                System.out.print("Input amount: ");
                int amount = inputY.nextInt();

                if (amount <= 0) {
                    System.out.println("Invalid amount.");
                    continue;
                }

                String type = menu == 1 ? "Deposit" : "Withdrawal";

                pendingTransactions.offer(
                    new Transaction(type, amount)
                );

                System.out.println("Transaction added to Queue.");

            } else if (menu == 3) {

                System.out.println("Balance: Rp" + balance);

            } else if (menu == 4 || menu == 5) {

                int total = 0;

                for (Transaction t : transactions) {

                    if (menu == 4 &&
                        t.getType().equals("Deposit")) {
                        total += t.getAmount();
                    }

                    if (menu == 5 &&
                        t.getType().equals("Withdrawal")) {
                        total += t.getAmount();
                    }
                }

                if (menu == 4) {
                    System.out.println("Total Deposited: Rp" + total);
                } else {
                    System.out.println("Total Withdrawn: Rp" + total);
                }

            } else if (menu == 6) {

                System.out.println("\nTransaction History:");

                if (transactions.isEmpty()) {
                    System.out.println("No transactions.");
                }

                for (int i = 0; i < transactions.size(); i++) {
                    System.out.println(
                        (i + 1) + ". " + transactions.get(i)
                    );
                }

            } else if (menu == 7) {

                if (transactionStack.isEmpty()) {
                    System.out.println("No transactions.");
                } else {
                    System.out.println(
                        "Latest Transaction: " +
                        transactionStack.peek()
                    );
                }

            } else if (menu == 8) {

                System.out.println("\nPending Transactions:");

                if (pendingTransactions.isEmpty()) {
                    System.out.println("Queue is empty.");
                } else {
                    for (Transaction t : pendingTransactions) {
                        System.out.println(t);
                    }
                }

            } else if (menu == 9) {

                if (pendingTransactions.isEmpty()) {
                    System.out.println("No pending transactions.");
                    continue;
                }

                Transaction t = pendingTransactions.poll();

                if (t.getType().equals("Deposit")) {

                    balance += t.getAmount();

                } else {

                    if (balance < t.getAmount()) {
                        System.out.println("Insufficient balance.");
                        continue;
                    }

                    balance -= t.getAmount();
                }

                transactions.add(t);
                transactionStack.push(t);

                System.out.println("Transaction Successful!");
                System.out.println(t);
                System.out.println("Balance: Rp" + balance);

            } else if (menu == 0) {

                System.out.println("Thank you for using ATM.");

            } else {

                System.out.println("Invalid menu selection.");
            }

        } while (menu != 0);

        inputY.close();
    }
}

