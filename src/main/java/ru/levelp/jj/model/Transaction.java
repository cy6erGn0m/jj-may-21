package ru.levelp.jj.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.Objects;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Column
    @Positive
    private double amount;

    @ManyToOne(optional = false)
    private User sender;

    @ManyToOne(optional = false)
    private User recipient;

    public Transaction(Date time, double amount, User sender, User recipient) {
        this.time = time;
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;

//        if (amount <= 0) {
//            throw new IllegalArgumentException("Amount should be positive: " + amount);
//        }

        if (sender == recipient) {
            throw new IllegalArgumentException("Sender and recipient should be different: " + sender);
        }
    }

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", time=" + time +
                ", amount=" + amount +
                ", sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && time.equals(that.time) && sender.equals(that.sender) && recipient.equals(that.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, amount, sender, recipient);
    }
}
