package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.dto.book.request.BookLoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    // 'select * from user_loan_history where book_name = ? and isReturn = ?'
    boolean existsByBookNameAndIsReturn(String name, boolean isReturn);
    //void saveBookLoan(BookLoanRequest request);

    //void save(UserLoanHistory userLoanHistory); 그래이건 안해도되겟지. 원래없는 jpa함수를 쓸때만 명시해주는 용도로 선언한다고했으니까.
}
