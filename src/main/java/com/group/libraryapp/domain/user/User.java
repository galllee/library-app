package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 20, name = "name") //name varchar(20)
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User() {}

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
        //아니 이거 생성자에 isreturn까지 변수 세갠데 강사님이 두개만해서 이걸로 하고, 생성자 오류나서 수정한거거든
        //근데 왜 isreturn이라는 변수에 fales를 안넣어줘도 대출반납이 정상처리되지
        //false가 isreturn에 그니까 불리언 변수의 기본값인건가? 그럼 정상 작동하는건 알겠는데
        //왜 그 부분을 뺐는지를 모르겠음 실수는 아닐텐데 왜냐면 생성자에서 오류가나니가
        //모르게씀.. 언제 갑자기 그 생성자에서 그값이 빠졌는지..
        //이걸로 말고 서비스코드에서 반납대출 처리할때는 생성자로 생성할 때 false를 넣어주는 걸로 했던 거 같은데
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }
}
