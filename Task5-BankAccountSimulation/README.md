# Bank Account Simulation

## Objective
A simple Java-based simulation of basic bank operations using Object-Oriented Programming (OOP) principles.  
This project demonstrates how to manage an account balance, handle deposits and withdrawals, and maintain a transaction history.

---


## Features
- Open a bank account with an initial balance.
- Deposit money into the account.
- Withdraw money with validation for sufficient funds.
- View current account balance.
- Maintain and display transaction history.

---

## How to Run

1. **Clone or Download** this repository.

2. Open the project in **VS Code** (or any Java IDE).

3. Compile the program:
 ```bash
   javac BankAccountSimulation.java
 ```

## Run the program:
 ```bash
 java BankAccountSimulation
 ```

## Follow the on-screen menu to perform bank operations.


## Sample Execution

```bash
PS D:\ElevateLab-Java-Ranjan\Task5-BankAccountSimulation> javac BankAccountSimulation.java
PS D:\ElevateLab-Java-Ranjan\Task5-BankAccountSimulation> java BankAccountSimulation
Enter Account Holder Name: Ranjan
Enter Account Number: 1122334455
Enter Initial Balance: 500

--- Bank Account Menu ---
1. Deposit
2. Withdraw
3. View Balance
4. View Transaction History
5. Exit
Enter your choice (1-5): 3
Current Balance: $500.0

--- Bank Account Menu ---
1. Deposit
2. Withdraw
3. View Balance
4. View Transaction History
5. Exit
Enter your choice (1-5): 2
Enter amount to withdraw: 50
Withdrawal successful. New Balance: $450.0

--- Bank Account Menu ---
1. Deposit
2. Withdraw
3. View Balance
4. View Transaction History
5. Exit
Enter your choice (1-5): 4

Transaction History:
Account opened with balance: $500.0
Withdrew: $50.0, New Balance: $450.0

--- Bank Account Menu ---
1. Deposit
2. Withdraw
3. View Balance
4. View Transaction History
5. Exit
Enter your choice (1-5): 5
Thank you for using our bank simulation. Goodbye!
PS D:\ElevateLab-Java-Ranjan\Task5-BankAccountSimulation>
```