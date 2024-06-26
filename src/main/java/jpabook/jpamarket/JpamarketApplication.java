package jpabook.jpamarket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpamarket.domain.Album;

public class JpamarketApplication {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ash"); // 애플리케이션 로딩 시점에 딱 하나만 생성 (web server가 올라오는 시점에 db당 하나만 생성)
        EntityManager em = emf.createEntityManager(); // 트랜잭션 단위 별로 생성 (클라이언트 요청이 올 때마다 생성, 사용하고 바로 close)

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
            // 양방향
            Order order = new Order();
            order.addOderItem(new OrderItem());

            // 단방향
            Order order = new Order();
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            em.persist(orderItem);
            */

            Album album = new Album();
            album.setArtist("WOODZ");
            album.setEtc("OO-LI");

            em.persist(album);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
