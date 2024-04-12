package jpabook.jpamarket.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany // @ManyToMany : 실무에서는 지양
    @JoinTable(name = "CATEGORY_ITEM", // 중간 테이블 생성
            joinColumns = @JoinColumn(name = "CATEGORY_ID"), // 내가 조인하는 애는 얘
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID ") // 반대 쪽이 조인하는 애는 얘
    )
    private List<Item> items = new ArrayList<>();
}
