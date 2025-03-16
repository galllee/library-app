package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn;

    public UserLoanHistory() {}

    public UserLoanHistory(User user, String bookName){//, boolean isReturn) {
        this.user = user;
        this.bookName = bookName;
        //this.isReturn = isReturn;
    }


    public Long getId() {
        return id;
    }

//    public Long getUserId() {
//        return userId;
//    }

    public String getBookName() {
        return bookName;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public void doReturn() {
        isReturn = true;
    }
}
