# Java Timed Task: Bank Account Management System

## Project Overview

This project is a Bank Account Management System developed over a period of 48 hours. The system allows users to create typical bank accounts and savings accounts, transfer funds between them, and store account information securely.

### Disclaimer

This project was created using guidelines provided by Bournemouth University, as part of coursework in a Programming unit. 
## Features

- **Account Types**: 
  - Typical Bank Accounts
  - Savings Accounts

- **Core Functionalities**:
  - Create either type of account
  - Transfer funds between bank accounts and savings accounts
  - Store account information
  - Different attributes for each account type

## Account Classes

### BankAccount

Represents a standard bank account. Contains essential information related to the account holder.

### SavingsAccount

Extends the functionality of the typical bank account by including additional attributes specific to savings accounts.

## Data Storage

The system securely stores account information, allowing for retrieval and management of account details.

## JUnit Testing

JUnit tests were provided to validate the core functionalities of the system. These tests ensure that the following functionalities work as intended:

- Creating bank and savings accounts
- Transferring funds between accounts
- Managing account balances

### Testing Guidelines

Each JUnit test was designed to verify specific functionalities, with clear guidelines on what each test should achieve. This includes checking for correct account creation, fund transfers, and balance management.

## Implementation Notes

All other code in this project was developed independently, following standard Java programming practices.

## Future Improvements

- Consider implementing a user interface for improved interaction.
- Expand functionalities to include additional account types, such as loan accounts.
- Enhance data storage with a database solution for persistence.
