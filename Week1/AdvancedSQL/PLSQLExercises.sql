
# CTS PL/SQL Exercises

# Exercise 1 - Control Structures

## Scenario 1 - Apply Discount to Senior Customers

DECLARE
    customerAge NUMBER;
BEGIN
    FOR recCustomer IN (
        SELECT CustomerID, DOB
        FROM Customers
    )
    LOOP
        customerAge := TRUNC(MONTHS_BETWEEN(SYSDATE, recCustomer.DOB) / 12);

        IF customerAge >= 60 THEN

            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = recCustomer.CustomerID;

            DBMS_OUTPUT.PUT_LINE(
                'Interest updated for Customer : ' ||
                recCustomer.CustomerID
            );

        END IF;

    END LOOP;

    COMMIT;
END;


## Scenario 2 - Promote Customers to VIP

ALTER TABLE Customers
ADD IsVIP VARCHAR2(5);


DECLARE
    CURSOR customerCursor IS
        SELECT CustomerID, Balance
        FROM Customers;
BEGIN

    FOR customerRecord IN customerCursor
    LOOP

        UPDATE Customers
        SET IsVIP =
            CASE
                WHEN customerRecord.Balance > 10000 THEN 'TRUE'
                ELSE 'FALSE'
            END
        WHERE CustomerID = customerRecord.CustomerID;

        IF customerRecord.Balance > 10000 THEN
            DBMS_OUTPUT.PUT_LINE(
                'VIP assigned to Customer ID : ' ||
                customerRecord.CustomerID
            );
        END IF;

    END LOOP;

    COMMIT;
END;


## Scenario 3 - Loan Due Reminder


BEGIN

    FOR reminderRec IN
    (
        SELECT
            C.Name,
            L.LoanID,
            L.EndDate
        FROM Customers C
        INNER JOIN Loans L
        ON C.CustomerID = L.CustomerID
        WHERE L.EndDate >= SYSDATE
        AND L.EndDate <= SYSDATE + 30
    )
    LOOP

        DBMS_OUTPUT.PUT_LINE(
            reminderRec.Name ||
            ' - Loan ' ||
            reminderRec.LoanID ||
            ' is due on ' ||
            TO_CHAR(reminderRec.EndDate,'DD-MON-YYYY')
        );

    END LOOP;

END;


# Exercise 3 - Stored Procedures

## Scenario 1 - Process Monthly Interest


CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
BEGIN

    UPDATE Accounts
    SET Balance = Balance * 1.01,
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Interest calculation completed.');

END;

### Execution


BEGIN
    ProcessMonthlyInterest;
END;


## Scenario 2 - Employee Bonus

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus
(
    pDept VARCHAR2,
    pPercentage NUMBER
)
AS
BEGIN

    UPDATE Employees
    SET Salary = Salary + (Salary * pPercentage / 100)
    WHERE Department = pDept;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(
        'Employee salaries updated.'
    );

END;


### Execution


BEGIN
    UpdateEmployeeBonus('IT',10);
END;


## Scenario 3 - Transfer Funds


CREATE OR REPLACE PROCEDURE TransferFunds
(
    fromAccount NUMBER,
    toAccount NUMBER,
    amountValue NUMBER
)
AS
    availableBalance NUMBER;
BEGIN

    SELECT Balance
    INTO availableBalance
    FROM Accounts
    WHERE AccountID = fromAccount;

    IF availableBalance >= amountValue THEN

        UPDATE Accounts
        SET Balance = Balance - amountValue,
            LastModified = SYSDATE
        WHERE AccountID = fromAccount;

        UPDATE Accounts
        SET Balance = Balance + amountValue,
            LastModified = SYSDATE
        WHERE AccountID = toAccount;

        COMMIT;

        DBMS_OUTPUT.PUT_LINE('Transfer completed.');

    ELSE

        DBMS_OUTPUT.PUT_LINE('Not enough balance.');

    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Account does not exist.');

    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error : ' || SQLERRM);

END;


### Execution


BEGIN
    TransferFunds(1,2,500);
END;

# Enable DBMS Output

```
SET SERVEROUTPUT ON;
```
