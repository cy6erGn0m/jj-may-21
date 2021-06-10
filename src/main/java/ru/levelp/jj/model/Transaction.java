package ru.levelp.jj.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Column
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

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount should be positive: " + amount);
        }

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
}
