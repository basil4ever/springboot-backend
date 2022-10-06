package net.javaguides.springbootbackend.model;


import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table(name = "invoices")

public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull(message = "User ID is required")
    private Employee employee;

    private String description;

    @Column(name = "amount")
    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "invioceData")
    private LocalDate invoiceData;

    @Column(name = "created", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;


    public Invoice() {

    }

    public Invoice(Employee employee, BigDecimal amount, Currency currency, LocalDateTime created) {
        this.employee = employee;
        this.amount = amount;
        this.currency = currency;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDate getInvoiceData() { return invoiceData; }

    public void setInvoiceData(LocalDate invoiceData) { this.invoiceData = invoiceData; }
}
